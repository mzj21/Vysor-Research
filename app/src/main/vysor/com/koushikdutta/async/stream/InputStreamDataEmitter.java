// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.stream;

import java.nio.ByteBuffer;
import com.koushikdutta.async.Util;
import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.ByteBufferList;
import java.io.InputStream;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.DataEmitter;

public class InputStreamDataEmitter implements DataEmitter
{
    DataCallback callback;
    CompletedCallback endCallback;
    InputStream inputStream;
    int mToAlloc;
    boolean paused;
    ByteBufferList pending;
    Runnable pumper;
    AsyncServer server;
    
    public InputStreamDataEmitter(final AsyncServer server, final InputStream inputStream) {
        this.mToAlloc = 0;
        this.pending = new ByteBufferList();
        this.pumper = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    while (true) {
                        ByteBuffer obtain;
                        int read;
                        try {
                            if (!InputStreamDataEmitter.this.pending.isEmpty()) {
                                InputStreamDataEmitter.this.getServer().run(new Runnable() {
                                    @Override
                                    public void run() {
                                        Util.emitAllData(InputStreamDataEmitter.this, InputStreamDataEmitter.this.pending);
                                    }
                                });
                                if (!InputStreamDataEmitter.this.pending.isEmpty()) {
                                    break;
                                }
                            }
                            obtain = ByteBufferList.obtain(Math.min(Math.max(InputStreamDataEmitter.this.mToAlloc, 4096), 262144));
                            read = InputStreamDataEmitter.this.inputStream.read(obtain.array());
                            if (-1 == read) {
                                InputStreamDataEmitter.this.report(null);
                                break;
                            }
                        }
                        catch (Exception ex) {
                            InputStreamDataEmitter.this.report(ex);
                            break;
                        }
                        InputStreamDataEmitter.this.mToAlloc = read * 2;
                        obtain.limit(read);
                        InputStreamDataEmitter.this.pending.add(obtain);
                        InputStreamDataEmitter.this.getServer().run(new Runnable() {
                            @Override
                            public void run() {
                                Util.emitAllData(InputStreamDataEmitter.this, InputStreamDataEmitter.this.pending);
                            }
                        });
                        if (InputStreamDataEmitter.this.pending.remaining() != 0 || InputStreamDataEmitter.this.isPaused()) {
                            break;
                        }
                        continue;
                    }
                }
            }
        };
        this.server = server;
        this.inputStream = inputStream;
        this.doResume();
    }
    
    private void doResume() {
        new Thread(this.pumper).start();
    }
    
    private void report(final Exception ex) {
        this.getServer().post(new Runnable() {
            @Override
            public void run() {
                Exception val$e = ex;
                while (true) {
                    try {
                        InputStreamDataEmitter.this.inputStream.close();
                        if (InputStreamDataEmitter.this.endCallback != null) {
                            InputStreamDataEmitter.this.endCallback.onCompleted(val$e);
                        }
                    }
                    catch (Exception ex) {
                        val$e = ex;
                        continue;
                    }
                    break;
                }
            }
        });
    }
    
    @Override
    public String charset() {
        return null;
    }
    
    @Override
    public void close() {
        this.report(null);
        try {
            this.inputStream.close();
        }
        catch (Exception ex) {}
    }
    
    @Override
    public DataCallback getDataCallback() {
        return this.callback;
    }
    
    @Override
    public CompletedCallback getEndCallback() {
        return this.endCallback;
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
    public void resume() {
        this.paused = false;
        this.doResume();
    }
    
    @Override
    public void setDataCallback(final DataCallback callback) {
        this.callback = callback;
    }
    
    @Override
    public void setEndCallback(final CompletedCallback endCallback) {
        this.endCallback = endCallback;
    }
}
