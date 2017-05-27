// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import com.koushikdutta.async.util.StreamUtility;
import java.io.Closeable;
import java.nio.ByteBuffer;
import java.io.FileInputStream;
import java.io.File;
import java.nio.channels.FileChannel;
import com.koushikdutta.async.callback.DataCallback;

public class FileDataEmitter extends DataEmitterBase
{
    DataCallback callback;
    FileChannel channel;
    File file;
    boolean paused;
    ByteBufferList pending;
    Runnable pumper;
    AsyncServer server;
    
    public FileDataEmitter(final AsyncServer server, final File file) {
        this.pending = new ByteBufferList();
        this.pumper = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    while (true) {
                        ByteBuffer obtain;
                        try {
                            if (FileDataEmitter.this.channel == null) {
                                FileDataEmitter.this.channel = new FileInputStream(FileDataEmitter.this.file).getChannel();
                            }
                            if (!FileDataEmitter.this.pending.isEmpty()) {
                                Util.emitAllData(FileDataEmitter.this, FileDataEmitter.this.pending);
                                if (!FileDataEmitter.this.pending.isEmpty()) {
                                    break;
                                }
                            }
                            obtain = ByteBufferList.obtain(8192);
                            if (-1 == FileDataEmitter.this.channel.read(obtain)) {
                                FileDataEmitter.this.report(null);
                                break;
                            }
                        }
                        catch (Exception ex) {
                            FileDataEmitter.this.report(ex);
                            break;
                        }
                        obtain.flip();
                        FileDataEmitter.this.pending.add(obtain);
                        Util.emitAllData(FileDataEmitter.this, FileDataEmitter.this.pending);
                        if (FileDataEmitter.this.pending.remaining() != 0 || FileDataEmitter.this.isPaused()) {
                            break;
                        }
                        continue;
                    }
                }
            }
        };
        this.server = server;
        this.file = file;
        if (!(this.paused = !server.isAffinityThread())) {
            this.doResume();
        }
    }
    
    private void doResume() {
        this.server.post(this.pumper);
    }
    
    @Override
    public void close() {
        try {
            this.channel.close();
        }
        catch (Exception ex) {}
    }
    
    @Override
    public DataCallback getDataCallback() {
        return this.callback;
    }
    
    @Override
    public AsyncServer getServer() {
        return this.server;
    }
    
    @Override
    public boolean isChunked() {
        return false;
    }
    
    @Override
    public boolean isPaused() {
        return this.paused;
    }
    
    @Override
    public void pause() {
        this.paused = true;
    }
    
    @Override
    protected void report(final Exception ex) {
        StreamUtility.closeQuietly(this.channel);
        super.report(ex);
    }
    
    @Override
    public void resume() {
        this.paused = false;
        this.doResume();
    }
    
    @Override
    public void setDataCallback(final DataCallback callback) {
        this.callback = callback;
    }
}
