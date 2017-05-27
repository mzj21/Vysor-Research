// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import java.nio.ByteBuffer;
import android.util.Log;
import java.nio.ByteOrder;
import java.util.LinkedList;
import java.util.ArrayList;
import java.lang.reflect.Method;
import java.util.Hashtable;
import com.koushikdutta.async.callback.DataCallback;

public class PushParser implements DataCallback
{
    static Hashtable<Class, Method> mTable;
    private ArrayList<Object> args;
    private Waiter byteArgWaiter;
    private ParseCallback<byte[]> byteArrayArgCallback;
    private ParseCallback<ByteBufferList> byteBufferListArgCallback;
    private Waiter intArgWaiter;
    private Waiter longArgWaiter;
    DataEmitter mEmitter;
    private LinkedList<Waiter> mWaiting;
    private Waiter noopArgWaiter;
    ByteOrder order;
    ByteBufferList pending;
    private Waiter shortArgWaiter;
    private ParseCallback<byte[]> stringArgCallback;
    
    static {
        PushParser.mTable = new Hashtable<Class, Method>();
    }
    
    public PushParser(final DataEmitter mEmitter) {
        this.noopArgWaiter = (Waiter)new Waiter(0) {
            @Override
            public Waiter onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                PushParser.this.args.add(null);
                return null;
            }
        };
        this.byteArgWaiter = (Waiter)new Waiter(1) {
            @Override
            public Waiter onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                PushParser.this.args.add(list.get());
                return null;
            }
        };
        this.shortArgWaiter = (Waiter)new Waiter(2) {
            @Override
            public Waiter onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                PushParser.this.args.add(list.getShort());
                return null;
            }
        };
        this.intArgWaiter = (Waiter)new Waiter(4) {
            @Override
            public Waiter onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                PushParser.this.args.add(list.getInt());
                return null;
            }
        };
        this.longArgWaiter = (Waiter)new Waiter(8) {
            @Override
            public Waiter onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                PushParser.this.args.add(list.getLong());
                return null;
            }
        };
        this.byteArrayArgCallback = (ParseCallback<byte[]>)new ParseCallback<byte[]>() {
            public void parsed(final byte[] array) {
                PushParser.this.args.add(array);
            }
        };
        this.byteBufferListArgCallback = (ParseCallback<ByteBufferList>)new ParseCallback<ByteBufferList>() {
            public void parsed(final ByteBufferList list) {
                PushParser.this.args.add(list);
            }
        };
        this.stringArgCallback = (ParseCallback<byte[]>)new ParseCallback<byte[]>() {
            public void parsed(final byte[] array) {
                PushParser.this.args.add(new String(array));
            }
        };
        this.mWaiting = new LinkedList<Waiter>();
        this.args = new ArrayList<Object>();
        this.order = ByteOrder.BIG_ENDIAN;
        this.pending = new ByteBufferList();
        (this.mEmitter = mEmitter).setDataCallback(this);
    }
    
    static Method getTap(final TapCallback tapCallback) {
        Method method = PushParser.mTable.get(tapCallback.getClass());
        if (method == null) {
            for (final Method method2 : tapCallback.getClass().getMethods()) {
                if ("tap".equals(method2.getName())) {
                    PushParser.mTable.put(tapCallback.getClass(), method2);
                    method = method2;
                    return method;
                }
            }
            final Method[] declaredMethods = tapCallback.getClass().getDeclaredMethods();
            if (declaredMethods.length != 1) {
                throw new AssertionError((Object)"-keep class * extends com.koushikdutta.async.TapCallback {\n    *;\n}\n");
            }
            method = declaredMethods[0];
        }
        return method;
    }
    
    public PushParser noop() {
        this.mWaiting.add(this.noopArgWaiter);
        return this;
    }
    
    @Override
    public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
        list.get(this.pending);
        while (this.mWaiting.size() > 0 && this.pending.remaining() >= this.mWaiting.peek().length) {
            this.pending.order(this.order);
            final Waiter onDataAvailable = this.mWaiting.poll().onDataAvailable(dataEmitter, this.pending);
            if (onDataAvailable != null) {
                this.mWaiting.addFirst(onDataAvailable);
            }
        }
        if (this.mWaiting.size() == 0) {
            this.pending.get(list);
        }
    }
    
    public PushParser readByte() {
        this.mWaiting.add(this.byteArgWaiter);
        return this;
    }
    
    public PushParser readByteArray(final int n) {
        PushParser pushParser;
        if (n == -1) {
            pushParser = this.readLenByteArray();
        }
        else {
            pushParser = this.readByteArray(n, this.byteArrayArgCallback);
        }
        return pushParser;
    }
    
    public PushParser readByteArray(final int n, final ParseCallback<byte[]> parseCallback) {
        this.mWaiting.add((Waiter)new ByteArrayWaiter(n, parseCallback));
        return this;
    }
    
    public PushParser readByteBufferList(final int n) {
        PushParser pushParser;
        if (n == -1) {
            pushParser = this.readLenByteBufferList();
        }
        else {
            pushParser = this.readByteBufferList(n, this.byteBufferListArgCallback);
        }
        return pushParser;
    }
    
    public PushParser readByteBufferList(final int n, final ParseCallback<ByteBufferList> parseCallback) {
        this.mWaiting.add((Waiter)new ByteBufferListWaiter(n, parseCallback));
        return this;
    }
    
    public PushParser readInt() {
        this.mWaiting.add(this.intArgWaiter);
        return this;
    }
    
    public PushParser readInt(final ParseCallback<Integer> parseCallback) {
        this.mWaiting.add((Waiter)new IntWaiter(parseCallback));
        return this;
    }
    
    public PushParser readLenByteArray() {
        this.mWaiting.add((Waiter)new LenByteArrayWaiter(this.byteArrayArgCallback));
        return this;
    }
    
    public PushParser readLenByteBufferList() {
        return this.readLenByteBufferList(this.byteBufferListArgCallback);
    }
    
    public PushParser readLenByteBufferList(final ParseCallback<ByteBufferList> parseCallback) {
        this.mWaiting.add((Waiter)new LenByteBufferListWaiter(parseCallback));
        return this;
    }
    
    public PushParser readLong() {
        this.mWaiting.add(this.longArgWaiter);
        return this;
    }
    
    public PushParser readShort() {
        this.mWaiting.add(this.shortArgWaiter);
        return this;
    }
    
    public PushParser readString() {
        this.mWaiting.add((Waiter)new LenByteArrayWaiter(this.stringArgCallback));
        return this;
    }
    
    public PushParser setOrder(final ByteOrder order) {
        this.order = order;
        return this;
    }
    
    public void tap(final TapCallback tapCallback) {
        this.mWaiting.add((Waiter)new TapWaiter(tapCallback));
    }
    
    public PushParser until(final byte b, final DataCallback dataCallback) {
        this.mWaiting.add((Waiter)new UntilWaiter(b, dataCallback));
        return this;
    }
    
    static class ByteArrayWaiter extends Waiter
    {
        ParseCallback<byte[]> callback;
        
        public ByteArrayWaiter(final int n, final ParseCallback<byte[]> callback) {
            super(n);
            if (n <= 0) {
                throw new IllegalArgumentException("length should be > 0");
            }
            this.callback = callback;
        }
        
        @Override
        public Waiter onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
            final byte[] array = new byte[this.length];
            list.get(array);
            this.callback.parsed(array);
            return null;
        }
    }
    
    static class ByteBufferListWaiter extends Waiter
    {
        ParseCallback<ByteBufferList> callback;
        
        public ByteBufferListWaiter(final int n, final ParseCallback<ByteBufferList> callback) {
            super(n);
            if (n <= 0) {
                throw new IllegalArgumentException("length should be > 0");
            }
            this.callback = callback;
        }
        
        @Override
        public Waiter onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
            this.callback.parsed(list.get(this.length));
            return null;
        }
    }
    
    static class IntWaiter extends Waiter
    {
        ParseCallback<Integer> callback;
        
        public IntWaiter(final ParseCallback<Integer> callback) {
            super(4);
            this.callback = callback;
        }
        
        @Override
        public Waiter onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
            this.callback.parsed(list.getInt());
            return null;
        }
    }
    
    static class LenByteArrayWaiter extends Waiter
    {
        private final ParseCallback<byte[]> callback;
        
        public LenByteArrayWaiter(final ParseCallback<byte[]> callback) {
            super(4);
            this.callback = callback;
        }
        
        @Override
        public Waiter onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
            final int int1 = list.getInt();
            Waiter waiter;
            if (int1 == 0) {
                this.callback.parsed(new byte[0]);
                waiter = null;
            }
            else {
                waiter = new ByteArrayWaiter(int1, this.callback);
            }
            return waiter;
        }
    }
    
    static class LenByteBufferListWaiter extends Waiter
    {
        private final ParseCallback<ByteBufferList> callback;
        
        public LenByteBufferListWaiter(final ParseCallback<ByteBufferList> callback) {
            super(4);
            this.callback = callback;
        }
        
        @Override
        public Waiter onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
            return new ByteBufferListWaiter(list.getInt(), this.callback);
        }
    }
    
    public interface ParseCallback<T>
    {
        void parsed(final T p0);
    }
    
    private class TapWaiter extends Waiter
    {
        private final TapCallback callback;
        
        public TapWaiter(final TapCallback callback) {
            super(0);
            this.callback = callback;
        }
        
        @Override
        public Waiter onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
            final Method tap = PushParser.getTap(this.callback);
            tap.setAccessible(true);
            while (true) {
                try {
                    tap.invoke(this.callback, PushParser.this.args.toArray());
                    PushParser.this.args.clear();
                    return null;
                }
                catch (Exception ex) {
                    Log.e("PushParser", "Error while invoking tap callback", (Throwable)ex);
                    continue;
                }
                break;
            }
        }
    }
    
    static class UntilWaiter extends Waiter
    {
        DataCallback callback;
        byte value;
        
        public UntilWaiter(final byte value, final DataCallback callback) {
            super(1);
            this.value = value;
            this.callback = callback;
        }
        
        @Override
        public Waiter onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
            boolean b = true;
            final ByteBufferList list2 = new ByteBufferList();
            while (list.size() > 0) {
                final ByteBuffer remove = list.remove();
                remove.mark();
                int n = 0;
                while (remove.remaining() > 0) {
                    if (remove.get() == this.value) {
                        b = true;
                    }
                    else {
                        b = false;
                    }
                    if (b) {
                        break;
                    }
                    ++n;
                }
                remove.reset();
                if (b) {
                    list.addFirst(remove);
                    list.get(list2, n);
                    list.get();
                    break;
                }
                list2.add(remove);
            }
            this.callback.onDataAvailable(dataEmitter, list2);
            final Waiter waiter;
            if (b) {
                waiter = null;
            }
            return waiter;
        }
    }
    
    abstract static class Waiter
    {
        int length;
        
        public Waiter(final int length) {
            this.length = length;
        }
        
        public abstract Waiter onDataAvailable(final DataEmitter p0, final ByteBufferList p1);
    }
}
