// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.stream;

import java.nio.ByteBuffer;
import com.koushikdutta.async.ByteBufferList;
import java.io.IOException;
import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.callback.WritableCallback;
import java.io.OutputStream;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.DataSink;

public class OutputStreamDataSink implements DataSink
{
    Exception closeException;
    boolean closeReported;
    CompletedCallback mClosedCallback;
    OutputStream mStream;
    WritableCallback mWritable;
    WritableCallback outputStreamCallback;
    AsyncServer server;
    
    public OutputStreamDataSink(final AsyncServer asyncServer) {
        this(asyncServer, null);
    }
    
    public OutputStreamDataSink(final AsyncServer server, final OutputStream outputStream) {
        this.server = server;
        this.setOutputStream(outputStream);
    }
    
    @Override
    public void end() {
        try {
            if (this.mStream != null) {
                this.mStream.close();
            }
            this.reportClose(null);
        }
        catch (IOException ex) {
            this.reportClose(ex);
        }
    }
    
    @Override
    public CompletedCallback getClosedCallback() {
        return this.mClosedCallback;
    }
    
    public OutputStream getOutputStream() throws IOException {
        return this.mStream;
    }
    
    @Override
    public AsyncServer getServer() {
        return this.server;
    }
    
    @Override
    public WritableCallback getWriteableCallback() {
        return this.mWritable;
    }
    
    @Override
    public boolean isOpen() {
        return this.closeReported;
    }
    
    public void reportClose(final Exception closeException) {
        if (!this.closeReported) {
            this.closeReported = true;
            this.closeException = closeException;
            if (this.mClosedCallback != null) {
                this.mClosedCallback.onCompleted(this.closeException);
            }
        }
    }
    
    @Override
    public void setClosedCallback(final CompletedCallback mClosedCallback) {
        this.mClosedCallback = mClosedCallback;
    }
    
    public void setOutputStream(final OutputStream mStream) {
        this.mStream = mStream;
    }
    
    public void setOutputStreamWritableCallback(final WritableCallback outputStreamCallback) {
        this.outputStreamCallback = outputStreamCallback;
    }
    
    @Override
    public void setWriteableCallback(final WritableCallback mWritable) {
        this.mWritable = mWritable;
    }
    
    @Override
    public void write(final ByteBufferList list) {
        try {
            Label_0060: {
                try {
                    while (list.size() > 0) {
                        final ByteBuffer remove = list.remove();
                        this.getOutputStream().write(remove.array(), remove.arrayOffset() + remove.position(), remove.remaining());
                        ByteBufferList.reclaim(remove);
                    }
                    break Label_0060;
                }
                catch (IOException ex) {
                    this.reportClose(ex);
                }
                return;
            }
            list.recycle();
        }
        finally {
            list.recycle();
        }
    }
}
