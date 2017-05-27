// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.DatagramChannel;
import java.io.IOException;
import java.nio.channels.CancelledKeyException;
import java.net.InetSocketAddress;
import com.koushikdutta.async.callback.WritableCallback;
import java.nio.channels.SelectionKey;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.util.Allocator;

public class AsyncNetworkSocket implements AsyncSocket
{
    Allocator allocator;
    boolean closeReported;
    private ChannelWrapper mChannel;
    CompletedCallback mClosedHander;
    private CompletedCallback mCompletedCallback;
    DataCallback mDataHandler;
    boolean mEndReported;
    private SelectionKey mKey;
    boolean mPaused;
    Exception mPendingEndException;
    private AsyncServer mServer;
    WritableCallback mWriteableHandler;
    private ByteBufferList pending;
    InetSocketAddress socketAddress;
    
    AsyncNetworkSocket() {
        this.pending = new ByteBufferList();
        this.mPaused = false;
    }
    
    private void handleRemaining(final int n) throws IOException {
        if (!this.mKey.isValid()) {
            throw new IOException(new CancelledKeyException());
        }
        if (n > 0) {
            assert !this.mChannel.isChunked();
            this.mKey.interestOps(0x4 | this.mKey.interestOps());
        }
        else {
            this.mKey.interestOps(0xFFFFFFFB & this.mKey.interestOps());
        }
    }
    
    private void spitPending() {
        if (this.pending.hasRemaining()) {
            Util.emitAllData(this, this.pending);
        }
    }
    
    void attach(final DatagramChannel datagramChannel) throws IOException {
        this.mChannel = new DatagramChannelWrapper(datagramChannel);
        this.allocator = new Allocator(8192);
    }
    
    void attach(final SocketChannel socketChannel, final InetSocketAddress socketAddress) throws IOException {
        this.socketAddress = socketAddress;
        this.allocator = new Allocator();
        this.mChannel = new SocketChannelWrapper(socketChannel);
    }
    
    @Override
    public String charset() {
        return null;
    }
    
    @Override
    public void close() {
        this.closeInternal();
        this.reportClose(null);
    }
    
    public void closeInternal() {
        this.mKey.cancel();
        try {
            this.mChannel.close();
        }
        catch (IOException ex) {}
    }
    
    @Override
    public void end() {
        this.mChannel.shutdownOutput();
    }
    
    ChannelWrapper getChannel() {
        return this.mChannel;
    }
    
    @Override
    public CompletedCallback getClosedCallback() {
        return this.mClosedHander;
    }
    
    @Override
    public DataCallback getDataCallback() {
        return this.mDataHandler;
    }
    
    @Override
    public CompletedCallback getEndCallback() {
        return this.mCompletedCallback;
    }
    
    public int getLocalPort() {
        return this.mChannel.getLocalPort();
    }
    
    public InetSocketAddress getRemoteAddress() {
        return this.socketAddress;
    }
    
    @Override
    public AsyncServer getServer() {
        return this.mServer;
    }
    
    public Object getSocket() {
        return this.getChannel().getSocket();
    }
    
    @Override
    public WritableCallback getWriteableCallback() {
        return this.mWriteableHandler;
    }
    
    @Override
    public boolean isChunked() {
        return this.mChannel.isChunked();
    }
    
    @Override
    public boolean isOpen() {
        return this.mChannel.isConnected() && this.mKey.isValid();
    }
    
    @Override
    public boolean isPaused() {
        return this.mPaused;
    }
    
    public void onDataWritable() {
        if (!this.mChannel.isChunked()) {
            this.mKey.interestOps(0xFFFFFFFB & this.mKey.interestOps());
        }
        if (this.mWriteableHandler != null) {
            this.mWriteableHandler.onWriteable();
        }
    }
    
    int onReadable() {
        this.spitPending();
        int n = 0;
        if (this.mPaused) {
            n = 0;
        }
        else {
        Label_0094_Outer:
            while (true) {
                n = 0;
                while (true) {
                    ByteBuffer allocate = null;
                Label_0143:
                    while (true) {
                        long n2 = 0L;
                        Label_0130: {
                            try {
                                allocate = this.allocator.allocate();
                                n2 = this.mChannel.read(allocate);
                                final long n3 = lcmp(n2, 0L);
                                n = 0;
                                if (n3 >= 0) {
                                    break Label_0130;
                                }
                                this.closeInternal();
                                final int n4 = 1;
                                if (n2 <= 0L) {
                                    break Label_0143;
                                }
                                this.allocator.track(n2);
                                allocate.flip();
                                this.pending.add(allocate);
                                Util.emitAllData(this, this.pending);
                                if (n4 != 0) {
                                    this.reportEndPending(null);
                                    this.reportClose(null);
                                }
                            }
                            catch (Exception ex) {
                                this.closeInternal();
                                this.reportEndPending(ex);
                                this.reportClose(ex);
                            }
                            break;
                        }
                        n = (int)(n2 + 0);
                        final int n4 = 0;
                        continue Label_0094_Outer;
                    }
                    ByteBufferList.reclaim(allocate);
                    continue;
                }
            }
        }
        return n;
    }
    
    @Override
    public void pause() {
        if (this.mServer.getAffinity() != Thread.currentThread()) {
            this.mServer.run(new Runnable() {
                @Override
                public void run() {
                    AsyncNetworkSocket.this.pause();
                }
            });
        }
        else if (!this.mPaused) {
            this.mPaused = true;
            try {
                this.mKey.interestOps(0xFFFFFFFE & this.mKey.interestOps());
            }
            catch (Exception ex) {}
        }
    }
    
    protected void reportClose(final Exception ex) {
        if (!this.closeReported) {
            this.closeReported = true;
            if (this.mClosedHander != null) {
                this.mClosedHander.onCompleted(ex);
                this.mClosedHander = null;
            }
        }
    }
    
    void reportEnd(final Exception ex) {
        if (!this.mEndReported) {
            this.mEndReported = true;
            if (this.mCompletedCallback != null) {
                this.mCompletedCallback.onCompleted(ex);
            }
            else if (ex != null) {
                Log.e("NIO", "Unhandled exception", (Throwable)ex);
            }
        }
    }
    
    void reportEndPending(final Exception mPendingEndException) {
        if (this.pending.hasRemaining()) {
            this.mPendingEndException = mPendingEndException;
        }
        else {
            this.reportEnd(mPendingEndException);
        }
    }
    
    @Override
    public void resume() {
        if (this.mServer.getAffinity() != Thread.currentThread()) {
            this.mServer.run(new Runnable() {
                @Override
                public void run() {
                    AsyncNetworkSocket.this.resume();
                }
            });
        }
        else if (this.mPaused) {
            this.mPaused = false;
            while (true) {
                try {
                    this.mKey.interestOps(0x1 | this.mKey.interestOps());
                    this.spitPending();
                    if (!this.isOpen()) {
                        this.reportEndPending(this.mPendingEndException);
                    }
                }
                catch (Exception ex) {
                    continue;
                }
                break;
            }
        }
    }
    
    @Override
    public void setClosedCallback(final CompletedCallback mClosedHander) {
        this.mClosedHander = mClosedHander;
    }
    
    @Override
    public void setDataCallback(final DataCallback mDataHandler) {
        this.mDataHandler = mDataHandler;
    }
    
    @Override
    public void setEndCallback(final CompletedCallback mCompletedCallback) {
        this.mCompletedCallback = mCompletedCallback;
    }
    
    @Override
    public void setWriteableCallback(final WritableCallback mWriteableHandler) {
        this.mWriteableHandler = mWriteableHandler;
    }
    
    void setup(final AsyncServer mServer, final SelectionKey mKey) {
        this.mServer = mServer;
        this.mKey = mKey;
    }
    
    @Override
    public void write(final ByteBufferList list) {
        if (this.mServer.getAffinity() != Thread.currentThread()) {
            this.mServer.run(new Runnable() {
                @Override
                public void run() {
                    AsyncNetworkSocket.this.write(list);
                }
            });
        }
        else if (!this.mChannel.isConnected()) {
            assert !this.mChannel.isChunked();
        }
        else {
            try {
                final int remaining = list.remaining();
                final ByteBuffer[] allArray = list.getAllArray();
                this.mChannel.write(allArray);
                list.addAll(allArray);
                this.handleRemaining(list.remaining());
                this.mServer.onDataSent(remaining - list.remaining());
            }
            catch (IOException ex) {
                this.closeInternal();
                this.reportEndPending(ex);
                this.reportClose(ex);
            }
        }
    }
}
