// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import java.nio.ByteBuffer;
import com.koushikdutta.async.util.StreamUtility;
import java.io.Closeable;
import com.koushikdutta.async.util.Allocator;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import com.koushikdutta.async.callback.WritableCallback;
import com.koushikdutta.async.wrapper.AsyncSocketWrapper;
import com.koushikdutta.async.wrapper.DataEmitterWrapper;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.DataCallback;

public class Util
{
    public static boolean SUPRESS_DEBUG_EXCEPTIONS;
    
    static {
        Util.SUPRESS_DEBUG_EXCEPTIONS = false;
    }
    
    public static void emitAllData(final DataEmitter dataEmitter, final ByteBufferList list) {
        DataCallback dataCallback = null;
        while (!dataEmitter.isPaused()) {
            dataCallback = dataEmitter.getDataCallback();
            if (dataCallback == null) {
                break;
            }
            final int remaining = list.remaining();
            if (remaining <= 0) {
                break;
            }
            dataCallback.onDataAvailable(dataEmitter, list);
            if (remaining != list.remaining() || dataCallback != dataEmitter.getDataCallback() || dataEmitter.isPaused()) {
                continue;
            }
            System.out.println("handler: " + dataCallback);
            list.recycle();
            if (Util.SUPRESS_DEBUG_EXCEPTIONS) {
                return;
            }
            assert false;
            throw new RuntimeException("mDataHandler failed to consume data, yet remains the mDataHandler.");
        }
        if (list.remaining() == 0 || dataEmitter.isPaused()) {
            return;
        }
        System.out.println("handler: " + dataCallback);
        System.out.println("emitter: " + dataEmitter);
        list.recycle();
        if (Util.SUPRESS_DEBUG_EXCEPTIONS) {
            return;
        }
        assert false;
        throw new RuntimeException("Not all data was consumed by Util.emitAllData");
    }
    
    public static void end(final DataEmitter dataEmitter, final Exception ex) {
        if (dataEmitter != null) {
            end(dataEmitter.getEndCallback(), ex);
        }
    }
    
    public static void end(final CompletedCallback completedCallback, final Exception ex) {
        if (completedCallback != null) {
            completedCallback.onCompleted(ex);
        }
    }
    
    public static DataEmitter getWrappedDataEmitter(DataEmitter socket, final Class clazz) {
        if (!clazz.isInstance(socket)) {
            while (socket instanceof DataEmitterWrapper) {
                socket = ((AsyncSocketWrapper)socket).getSocket();
                if (clazz.isInstance(socket)) {
                    return socket;
                }
            }
            socket = null;
        }
        return socket;
    }
    
    public static <T extends AsyncSocket> T getWrappedSocket(AsyncSocket socket, final Class<T> clazz) {
        if (!clazz.isInstance(socket)) {
            while (socket instanceof AsyncSocketWrapper) {
                socket = ((AsyncSocketWrapper)socket).getSocket();
                if (clazz.isInstance(socket)) {
                    return (T)socket;
                }
            }
            socket = null;
        }
        return (T)socket;
    }
    
    public static void pump(final DataEmitter dataEmitter, final DataSink dataSink, final CompletedCallback completedCallback) {
        dataEmitter.setDataCallback(new DataCallback() {
            @Override
            public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                dataSink.write(list);
                if (list.remaining() > 0) {
                    dataEmitter.pause();
                }
            }
        });
        dataSink.setWriteableCallback(new WritableCallback() {
            @Override
            public void onWriteable() {
                dataEmitter.resume();
            }
        });
        final CompletedCallback endCallback = new CompletedCallback() {
            boolean reported;
            
            @Override
            public void onCompleted(final Exception ex) {
                if (!this.reported) {
                    this.reported = true;
                    dataEmitter.setDataCallback(null);
                    dataEmitter.setEndCallback(null);
                    dataSink.setClosedCallback(null);
                    dataSink.setWriteableCallback(null);
                    completedCallback.onCompleted(ex);
                }
            }
        };
        dataEmitter.setEndCallback(endCallback);
        dataSink.setClosedCallback(new CompletedCallback() {
            @Override
            public void onCompleted(Exception ex) {
                if (ex == null) {
                    ex = new IOException("sink was closed before emitter ended");
                }
                endCallback.onCompleted(ex);
            }
        });
    }
    
    public static void pump(final File file, final DataSink dataSink, final CompletedCallback completedCallback) {
        Label_0018: {
            if (file != null) {
                if (dataSink != null) {
                    break Label_0018;
                }
            }
            try {
                completedCallback.onCompleted(null);
                return;
                final FileInputStream fileInputStream = new FileInputStream(file);
                pump(fileInputStream, dataSink, new CompletedCallback() {
                    @Override
                    public void onCompleted(final Exception ex) {
                        try {
                            fileInputStream.close();
                            completedCallback.onCompleted(ex);
                        }
                        catch (IOException ex2) {
                            completedCallback.onCompleted(ex2);
                        }
                    }
                });
            }
            catch (Exception ex) {
                completedCallback.onCompleted(ex);
            }
        }
    }
    
    public static void pump(final InputStream inputStream, final long n, final DataSink dataSink, final CompletedCallback completedCallback) {
        final CompletedCallback closedCallback = new CompletedCallback() {
            boolean reported;
            
            @Override
            public void onCompleted(final Exception ex) {
                if (!this.reported) {
                    this.reported = true;
                    completedCallback.onCompleted(ex);
                }
            }
        };
        final WritableCallback writeableCallback = new WritableCallback() {
            Allocator allocator = new Allocator();
            ByteBufferList pending = new ByteBufferList();
            int totalRead = 0;
            
            private void cleanup() {
                dataSink.setClosedCallback(null);
                dataSink.setWriteableCallback(null);
                this.pending.recycle();
                StreamUtility.closeQuietly(inputStream);
            }
            
            @Override
            public void onWriteable() {
                try {
                    do {
                        if (!this.pending.hasRemaining()) {
                            final ByteBuffer allocate = this.allocator.allocate();
                            final int read = inputStream.read(allocate.array(), 0, (int)Math.min(n - this.totalRead, allocate.capacity()));
                            if (read == -1 || this.totalRead == n) {
                                this.cleanup();
                                closedCallback.onCompleted(null);
                                break;
                            }
                            this.allocator.track(read);
                            this.totalRead += read;
                            allocate.position(0);
                            allocate.limit(read);
                            this.pending.add(allocate);
                        }
                        dataSink.write(this.pending);
                    } while (!this.pending.hasRemaining());
                }
                catch (Exception ex) {
                    this.cleanup();
                    closedCallback.onCompleted(ex);
                }
            }
        };
        dataSink.setWriteableCallback(writeableCallback);
        dataSink.setClosedCallback(closedCallback);
        writeableCallback.onWriteable();
    }
    
    public static void pump(final InputStream inputStream, final DataSink dataSink, final CompletedCallback completedCallback) {
        pump(inputStream, 2147483647L, dataSink, completedCallback);
    }
    
    public static void stream(final AsyncSocket asyncSocket, final AsyncSocket asyncSocket2, final CompletedCallback completedCallback) {
        pump(asyncSocket, asyncSocket2, completedCallback);
        pump(asyncSocket2, asyncSocket, completedCallback);
    }
    
    public static void writable(final DataSink dataSink) {
        if (dataSink != null) {
            writable(dataSink.getWriteableCallback());
        }
    }
    
    public static void writable(final WritableCallback writableCallback) {
        if (writableCallback != null) {
            writableCallback.onWriteable();
        }
    }
    
    public static void writeAll(final DataSink dataSink, final ByteBufferList list, final CompletedCallback completedCallback) {
        final WritableCallback writeableCallback = new WritableCallback() {
            @Override
            public void onWriteable() {
                dataSink.write(list);
                if (list.remaining() == 0 && completedCallback != null) {
                    dataSink.setWriteableCallback(null);
                    completedCallback.onCompleted(null);
                }
            }
        };
        dataSink.setWriteableCallback(writeableCallback);
        writeableCallback.onWriteable();
    }
    
    public static void writeAll(final DataSink dataSink, final byte[] array, final CompletedCallback completedCallback) {
        final ByteBuffer obtain = ByteBufferList.obtain(array.length);
        obtain.put(array);
        obtain.flip();
        final ByteBufferList list = new ByteBufferList();
        list.add(obtain);
        writeAll(dataSink, list, completedCallback);
    }
}
