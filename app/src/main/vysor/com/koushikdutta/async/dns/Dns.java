// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.dns;

import com.koushikdutta.async.future.Cancellable;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.DatagramSocket;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.AsyncDatagramSocket;
import com.koushikdutta.async.future.SimpleFuture;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.util.Random;
import java.nio.ByteOrder;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.AsyncServer;
import java.nio.ByteBuffer;

public class Dns
{
    private static void addName(final ByteBuffer byteBuffer, final String s) {
        for (final String s2 : s.split("\\.")) {
            byteBuffer.put((byte)s2.length());
            byteBuffer.put(s2.getBytes());
        }
        byteBuffer.put((byte)0);
    }
    
    public static Future<DnsResponse> lookup(final AsyncServer asyncServer, final String s) {
        return lookup(asyncServer, s, false, null);
    }
    
    public static Future<DnsResponse> lookup(final AsyncServer asyncServer, final String s, final boolean b, final FutureCallback<DnsResponse> futureCallback) {
        final ByteBuffer order = ByteBufferList.obtain(1024).order(ByteOrder.BIG_ENDIAN);
        final short n = (short)new Random().nextInt();
        short recursion = (short)setQuery(0);
        if (!b) {
            recursion = (short)setRecursion(recursion);
        }
        order.putShort(n);
        order.putShort(recursion);
        short n2;
        if (b) {
            n2 = 1;
        }
        else {
            n2 = 2;
        }
        order.putShort(n2);
        order.putShort((short)0);
        order.putShort((short)0);
        order.putShort((short)0);
        addName(order, s);
        short n3;
        if (b) {
            n3 = 12;
        }
        else {
            n3 = 1;
        }
        order.putShort(n3);
        order.putShort((short)1);
        if (!b) {
            addName(order, s);
            order.putShort((short)28);
            order.putShort((short)1);
        }
        order.flip();
        while (true) {
            if (!b) {
                AsyncDatagramSocket asyncDatagramSocket = null;
                SimpleFuture<DnsResponse> simpleFuture = null;
                Label_0386: {
                    try {
                        asyncDatagramSocket = asyncServer.connectDatagram(new InetSocketAddress("8.8.8.8", 53));
                        while (true) {
                            simpleFuture = new SimpleFuture<DnsResponse>() {
                                @Override
                                protected void cleanup() {
                                    super.cleanup();
                                    asyncDatagramSocket.close();
                                }
                            };
                            asyncDatagramSocket.setDataCallback(new DataCallback() {
                                @Override
                                public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                                    while (true) {
                                        try {
                                            final DnsResponse parse = DnsResponse.parse(list);
                                            parse.source = asyncDatagramSocket.getRemoteAddress();
                                            if (!b) {
                                                asyncDatagramSocket.close();
                                                simpleFuture.setComplete(parse);
                                            }
                                            else {
                                                futureCallback.onCompleted(null, parse);
                                            }
                                            list.recycle();
                                        }
                                        catch (Exception ex) {
                                            continue;
                                        }
                                        break;
                                    }
                                }
                            });
                            if (!b) {
                                asyncDatagramSocket.write(new ByteBufferList(new ByteBuffer[] { order }));
                                return simpleFuture;
                            }
                            break Label_0386;
                            asyncDatagramSocket = AsyncServer.getDefault().openDatagram(new InetSocketAddress(0), true);
                            final Field declaredField = DatagramSocket.class.getDeclaredField("impl");
                            declaredField.setAccessible(true);
                            final Object value = declaredField.get(asyncDatagramSocket.getSocket());
                            final Method declaredMethod = value.getClass().getDeclaredMethod("join", InetAddress.class);
                            declaredMethod.setAccessible(true);
                            declaredMethod.invoke(value, InetAddress.getByName("224.0.0.251"));
                            ((DatagramSocket)asyncDatagramSocket.getSocket()).setBroadcast(true);
                            continue;
                        }
                    }
                    catch (Exception complete) {
                        final SimpleFuture<DnsResponse> simpleFuture2 = new SimpleFuture<DnsResponse>();
                        simpleFuture2.setComplete(complete);
                        if (b) {
                            futureCallback.onCompleted(complete, null);
                        }
                        return simpleFuture2;
                    }
                }
                asyncDatagramSocket.send(new InetSocketAddress("224.0.0.251", 5353), order);
                return simpleFuture;
            }
            continue;
        }
    }
    
    public static Future<DnsResponse> lookup(final String s) {
        return lookup(AsyncServer.getDefault(), s, false, null);
    }
    
    public static Cancellable multicastLookup(final AsyncServer asyncServer, final String s, final FutureCallback<DnsResponse> futureCallback) {
        return lookup(asyncServer, s, true, futureCallback);
    }
    
    public static Cancellable multicastLookup(final String s, final FutureCallback<DnsResponse> futureCallback) {
        return multicastLookup(AsyncServer.getDefault(), s, futureCallback);
    }
    
    private static int setFlag(final int n, final int n2, final int n3) {
        return n | n2 << n3;
    }
    
    private static int setQuery(final int n) {
        return setFlag(n, 0, 0);
    }
    
    private static int setRecursion(final int n) {
        return setFlag(n, 1, 8);
    }
}
