// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.Semaphore;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.future.TransformFuture;
import java.util.Arrays;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.future.SimpleFuture;
import com.koushikdutta.async.future.Cancellable;
import java.nio.channels.DatagramChannel;
import java.util.Iterator;
import java.util.Set;
import java.nio.channels.CancelledKeyException;
import java.io.IOException;
import com.koushikdutta.async.callback.ListenCallback;
import java.nio.channels.ServerSocketChannel;
import android.util.Log;
import android.os.Handler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import com.koushikdutta.async.util.StreamUtility;
import java.io.Closeable;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.channels.ClosedChannelException;
import com.koushikdutta.async.callback.ConnectCallback;
import java.net.InetSocketAddress;
import java.net.Inet6Address;
import java.net.Inet4Address;
import android.os.Build$VERSION;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.WeakHashMap;
import java.net.InetAddress;
import java.util.Comparator;

public class AsyncServer
{
    public static final String LOGTAG = "NIO";
    private static final long QUEUE_EMPTY = Long.MAX_VALUE;
    private static final Comparator<InetAddress> ipSorter;
    static AsyncServer mInstance;
    static final WeakHashMap<Thread, AsyncServer> mServers;
    private static ExecutorService synchronousWorkers;
    Thread mAffinity;
    String mName;
    PriorityQueue<Scheduled> mQueue;
    private SelectorWrapper mSelector;
    int postCounter;
    
    static {
        Label_0075: {
            if (AsyncServer.class.desiredAssertionStatus()) {
                break Label_0075;
            }
            boolean $assertionsDisabled = true;
            while (true) {
                while (true) {
                    try {
                        if (Build$VERSION.SDK_INT <= 8) {
                            System.setProperty("java.net.preferIPv4Stack", "true");
                            System.setProperty("java.net.preferIPv6Addresses", "false");
                        }
                        AsyncServer.mInstance = new AsyncServer();
                        ipSorter = new Comparator<InetAddress>() {
                            @Override
                            public int compare(final InetAddress inetAddress, final InetAddress inetAddress2) {
                                Label_0022: {
                                    if (!(inetAddress instanceof Inet4Address)) {
                                        break Label_0022;
                                    }
                                    final boolean b = inetAddress2 instanceof Inet4Address;
                                    final int n = 0;
                                    if (!b) {
                                        break Label_0022;
                                    }
                                    return n;
                                }
                                if (inetAddress instanceof Inet6Address) {
                                    final boolean b2 = inetAddress2 instanceof Inet6Address;
                                    final int n = 0;
                                    if (b2) {
                                        return n;
                                    }
                                }
                                if (inetAddress instanceof Inet4Address && inetAddress2 instanceof Inet6Address) {
                                    return -1;
                                }
                                return 1;
                            }
                        };
                        AsyncServer.synchronousWorkers = newSynchronousWorkers();
                        mServers = new WeakHashMap<Thread, AsyncServer>();
                        return;
                        $assertionsDisabled = false;
                    }
                    catch (Throwable t) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    public AsyncServer() {
        this(null);
    }
    
    public AsyncServer(String mName) {
        this.postCounter = 0;
        this.mQueue = new PriorityQueue<Scheduled>(1, (Comparator<? super Scheduled>)Scheduler.INSTANCE);
        if (mName == null) {
            mName = "AsyncServer";
        }
        this.mName = mName;
    }
    
    private boolean addMe() {
        boolean b;
        synchronized (AsyncServer.mServers) {
            if (AsyncServer.mServers.get(this.mAffinity) != null) {
                // monitorexit(AsyncServer.mServers)
                b = false;
            }
            else {
                AsyncServer.mServers.put(this.mAffinity, this);
                // monitorexit(AsyncServer.mServers)
                b = true;
            }
        }
        return b;
    }
    
    private ConnectFuture connectResolvedInetSocketAddress(final InetSocketAddress inetSocketAddress, final ConnectCallback connectCallback) {
        final ConnectFuture connectFuture = new ConnectFuture();
        assert !inetSocketAddress.isUnresolved();
        this.post(new Runnable() {
            final /* synthetic */ AsyncServer this$0;
            
            @Override
            public void run() {
                if (!connectFuture.isCancelled()) {
                    connectFuture.callback = connectCallback;
                    SelectionKey selectionKey = null;
                    Closeable closeable = null;
                    SocketChannel open = null;
                    try {
                        final ConnectFuture val$cancel = connectFuture;
                        open = SocketChannel.open();
                        val$cancel.socket = open;
                        final SocketChannel socketChannel = open;
                        final boolean b = false;
                        socketChannel.configureBlocking(b);
                        final SocketChannel socketChannel2 = open;
                        final Runnable runnable = this;
                        final AsyncServer asyncServer = runnable.this$0;
                        final SelectorWrapper selectorWrapper = asyncServer.mSelector;
                        final Selector selector = selectorWrapper.getSelector();
                        final int n = 8;
                        final SelectionKey selectionKey2 = socketChannel2.register(selector, n);
                        final SelectionKey selectionKey3;
                        selectionKey = (selectionKey3 = selectionKey2);
                        final Runnable runnable2 = this;
                        final ConnectFuture connectFuture = connectFuture;
                        selectionKey3.attach(connectFuture);
                        final SocketChannel socketChannel3 = open;
                        final Runnable runnable3 = this;
                        final InetSocketAddress inetSocketAddress = inetSocketAddress;
                        socketChannel3.connect(inetSocketAddress);
                        return;
                    }
                    catch (Throwable t2) {}
                    while (true) {
                        try {
                            final SocketChannel socketChannel = open;
                            final boolean b = false;
                            socketChannel.configureBlocking(b);
                            final SocketChannel socketChannel2 = open;
                            final Runnable runnable = this;
                            final AsyncServer asyncServer = runnable.this$0;
                            final SelectorWrapper selectorWrapper = asyncServer.mSelector;
                            final Selector selector = selectorWrapper.getSelector();
                            final int n = 8;
                            final SelectionKey selectionKey2 = socketChannel2.register(selector, n);
                            final SelectionKey selectionKey3;
                            selectionKey = (selectionKey3 = selectionKey2);
                            final Runnable runnable2 = this;
                            final ConnectFuture connectFuture = connectFuture;
                            selectionKey3.attach(connectFuture);
                            final SocketChannel socketChannel3 = open;
                            final Runnable runnable3 = this;
                            final InetSocketAddress inetSocketAddress = inetSocketAddress;
                            socketChannel3.connect(inetSocketAddress);
                            return;
                            // iftrue(Label_0100:, selectionKey == null)
                            selectionKey.cancel();
                            Label_0100: {
                                StreamUtility.closeQuietly(closeable);
                            }
                            final Throwable t;
                            connectFuture.setComplete(new RuntimeException(t));
                        }
                        catch (Throwable t) {
                            closeable = open;
                            continue;
                        }
                        break;
                    }
                }
            }
        });
        return connectFuture;
    }
    
    public static AsyncServer getCurrentThreadServer() {
        return AsyncServer.mServers.get(Thread.currentThread());
    }
    
    public static AsyncServer getDefault() {
        return AsyncServer.mInstance;
    }
    
    private void handleSocket(final AsyncNetworkSocket asyncNetworkSocket) throws ClosedChannelException {
        final SelectionKey register = asyncNetworkSocket.getChannel().register(this.mSelector.getSelector());
        register.attach(asyncNetworkSocket);
        asyncNetworkSocket.setup(this, register);
    }
    
    private static long lockAndRunQueue(final AsyncServer asyncServer, final PriorityQueue<Scheduled> priorityQueue) {
        long n = Long.MAX_VALUE;
        while (true) {
            Scheduled scheduled;
            synchronized (asyncServer) {
                final long currentTimeMillis = System.currentTimeMillis();
                final int size = priorityQueue.size();
                scheduled = null;
                if (size > 0) {
                    final Scheduled scheduled2 = priorityQueue.remove();
                    if (scheduled2.time <= currentTimeMillis) {
                        scheduled = scheduled2;
                    }
                    else {
                        n = scheduled2.time - currentTimeMillis;
                        priorityQueue.add(scheduled2);
                        scheduled = null;
                    }
                }
                // monitorexit(asyncServer)
                if (scheduled == null) {
                    asyncServer.postCounter = 0;
                    return n;
                }
            }
            scheduled.runnable.run();
        }
    }
    
    private static ExecutorService newSynchronousWorkers() {
        return new ThreadPoolExecutor(1, 4, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new NamedThreadFactory("AsyncServer-worker-"));
    }
    
    public static void post(final Handler handler, final Runnable runnable) {
        final RunnableWrapper runnableWrapper = new RunnableWrapper();
        final ThreadQueue orCreateThreadQueue = ThreadQueue.getOrCreateThreadQueue(handler.getLooper().getThread());
        runnableWrapper.threadQueue = orCreateThreadQueue;
        runnableWrapper.handler = handler;
        runnableWrapper.runnable = runnable;
        orCreateThreadQueue.add(runnableWrapper);
        handler.post((Runnable)runnableWrapper);
        orCreateThreadQueue.queueSemaphore.release();
    }
    
    private static void run(final AsyncServer asyncServer, final SelectorWrapper selectorWrapper, final PriorityQueue<Scheduled> priorityQueue) {
        while (true) {
            try {
                while (true) {
                    runLoop(asyncServer, selectorWrapper, priorityQueue);
                    synchronized (asyncServer) {
                        if (selectorWrapper.isOpen() && (selectorWrapper.keys().size() > 0 || priorityQueue.size() > 0)) {
                            continue;
                        }
                        break;
                    }
                }
            }
            catch (AsyncSelectorException ex) {
                Log.i("NIO", "Selector exception, shutting down", (Throwable)ex);
                try {
                    selectorWrapper.getSelector().close();
                }
                catch (Exception ex2) {}
                continue;
            }
            break;
        }
        shutdownEverything(selectorWrapper);
        if (asyncServer.mSelector == selectorWrapper) {
            asyncServer.mQueue = new PriorityQueue<Scheduled>(1, (Comparator<? super Scheduled>)Scheduler.INSTANCE);
            asyncServer.mSelector = null;
            asyncServer.mAffinity = null;
        }
        // monitorexit(asyncServer)
        synchronized (AsyncServer.mServers) {
            AsyncServer.mServers.remove(Thread.currentThread());
        }
    }
    
    private void run(final boolean p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/koushikdutta/async/AsyncServer.mSelector:Lcom/koushikdutta/async/SelectorWrapper;
        //     6: ifnull          76
        //     9: ldc             "NIO"
        //    11: ldc_w           "Reentrant call"
        //    14: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //    17: pop            
        //    18: getstatic       com/koushikdutta/async/AsyncServer.$assertionsDisabled:Z
        //    21: ifne            47
        //    24: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
        //    27: aload_0        
        //    28: getfield        com/koushikdutta/async/AsyncServer.mAffinity:Ljava/lang/Thread;
        //    31: if_acmpeq       47
        //    34: new             Ljava/lang/AssertionError;
        //    37: dup            
        //    38: invokespecial   java/lang/AssertionError.<init>:()V
        //    41: athrow         
        //    42: astore_2       
        //    43: aload_0        
        //    44: monitorexit    
        //    45: aload_2        
        //    46: athrow         
        //    47: iconst_1       
        //    48: istore          7
        //    50: aload_0        
        //    51: getfield        com/koushikdutta/async/AsyncServer.mSelector:Lcom/koushikdutta/async/SelectorWrapper;
        //    54: astore_3       
        //    55: aload_0        
        //    56: getfield        com/koushikdutta/async/AsyncServer.mQueue:Ljava/util/PriorityQueue;
        //    59: astore          5
        //    61: aload_0        
        //    62: monitorexit    
        //    63: iload           7
        //    65: ifeq            221
        //    68: aload_0        
        //    69: aload_3        
        //    70: aload           5
        //    72: invokestatic    com/koushikdutta/async/AsyncServer.runLoop:(Lcom/koushikdutta/async/AsyncServer;Lcom/koushikdutta/async/SelectorWrapper;Ljava/util/PriorityQueue;)V
        //    75: return         
        //    76: new             Lcom/koushikdutta/async/SelectorWrapper;
        //    79: dup            
        //    80: invokestatic    java/nio/channels/spi/SelectorProvider.provider:()Ljava/nio/channels/spi/SelectorProvider;
        //    83: invokevirtual   java/nio/channels/spi/SelectorProvider.openSelector:()Ljava/nio/channels/spi/AbstractSelector;
        //    86: invokespecial   com/koushikdutta/async/SelectorWrapper.<init>:(Ljava/nio/channels/Selector;)V
        //    89: astore_3       
        //    90: aload_0        
        //    91: aload_3        
        //    92: putfield        com/koushikdutta/async/AsyncServer.mSelector:Lcom/koushikdutta/async/SelectorWrapper;
        //    95: aload_0        
        //    96: getfield        com/koushikdutta/async/AsyncServer.mQueue:Ljava/util/PriorityQueue;
        //    99: astore          5
        //   101: iload_1        
        //   102: ifeq            164
        //   105: aload_0        
        //   106: new             Lcom/koushikdutta/async/AsyncServer$14;
        //   109: dup            
        //   110: aload_0        
        //   111: aload_0        
        //   112: getfield        com/koushikdutta/async/AsyncServer.mName:Ljava/lang/String;
        //   115: aload_3        
        //   116: aload           5
        //   118: invokespecial   com/koushikdutta/async/AsyncServer$14.<init>:(Lcom/koushikdutta/async/AsyncServer;Ljava/lang/String;Lcom/koushikdutta/async/SelectorWrapper;Ljava/util/PriorityQueue;)V
        //   121: putfield        com/koushikdutta/async/AsyncServer.mAffinity:Ljava/lang/Thread;
        //   124: aload_0        
        //   125: invokespecial   com/koushikdutta/async/AsyncServer.addMe:()Z
        //   128: istore          6
        //   130: iload           6
        //   132: ifne            174
        //   135: aload_0        
        //   136: getfield        com/koushikdutta/async/AsyncServer.mSelector:Lcom/koushikdutta/async/SelectorWrapper;
        //   139: invokevirtual   com/koushikdutta/async/SelectorWrapper.close:()V
        //   142: aload_0        
        //   143: aconst_null    
        //   144: putfield        com/koushikdutta/async/AsyncServer.mSelector:Lcom/koushikdutta/async/SelectorWrapper;
        //   147: aload_0        
        //   148: aconst_null    
        //   149: putfield        com/koushikdutta/async/AsyncServer.mAffinity:Ljava/lang/Thread;
        //   152: aload_0        
        //   153: monitorexit    
        //   154: goto            75
        //   157: astore          4
        //   159: aload_0        
        //   160: monitorexit    
        //   161: goto            75
        //   164: aload_0        
        //   165: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
        //   168: putfield        com/koushikdutta/async/AsyncServer.mAffinity:Ljava/lang/Thread;
        //   171: goto            124
        //   174: iconst_0       
        //   175: istore          7
        //   177: iload_1        
        //   178: ifeq            61
        //   181: aload_0        
        //   182: getfield        com/koushikdutta/async/AsyncServer.mAffinity:Ljava/lang/Thread;
        //   185: invokevirtual   java/lang/Thread.start:()V
        //   188: aload_0        
        //   189: monitorexit    
        //   190: goto            75
        //   193: astore          8
        //   195: ldc             "NIO"
        //   197: ldc_w           "Selector closed"
        //   200: aload           8
        //   202: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   205: pop            
        //   206: aload_3        
        //   207: invokevirtual   com/koushikdutta/async/SelectorWrapper.getSelector:()Ljava/nio/channels/Selector;
        //   210: invokevirtual   java/nio/channels/Selector.close:()V
        //   213: goto            75
        //   216: astore          10
        //   218: goto            75
        //   221: aload_0        
        //   222: aload_3        
        //   223: aload           5
        //   225: invokestatic    com/koushikdutta/async/AsyncServer.run:(Lcom/koushikdutta/async/AsyncServer;Lcom/koushikdutta/async/SelectorWrapper;Ljava/util/PriorityQueue;)V
        //   228: goto            75
        //   231: astore          11
        //   233: goto            142
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                       
        //  -----  -----  -----  -----  -----------------------------------------------------------
        //  2      45     42     47     Any
        //  50     63     42     47     Any
        //  68     75     193    221    Lcom/koushikdutta/async/AsyncServer$AsyncSelectorException;
        //  76     101    157    164    Ljava/io/IOException;
        //  76     101    42     47     Any
        //  105    130    42     47     Any
        //  135    142    231    236    Ljava/lang/Exception;
        //  135    142    42     47     Any
        //  142    190    42     47     Any
        //  206    213    216    221    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0142:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static void runLoop(final AsyncServer asyncServer, final SelectorWrapper selectorWrapper, final PriorityQueue<Scheduled> priorityQueue) throws AsyncSelectorException {
        Set<SelectionKey> selectedKeys = null;
        Label_0530: {
        Label_0044_Outer:
            while (true) {
                int n = 1;
                final long lockAndRunQueue = lockAndRunQueue(asyncServer, priorityQueue);
            Label_0078_Outer:
                while (true) {
                    Label_0538: {
                        while (true) {
                        Label_0287:
                            while (true) {
                                Label_0278: {
                                    try {
                                        synchronized (asyncServer) {
                                            if (selectorWrapper.selectNow() != 0) {
                                                break Label_0538;
                                            }
                                            if (selectorWrapper.keys().size() == 0 && lockAndRunQueue == Long.MAX_VALUE) {
                                                return;
                                            }
                                            // monitorexit(asyncServer)
                                            if (n != 0) {
                                                if (lockAndRunQueue != Long.MAX_VALUE) {
                                                    break Label_0278;
                                                }
                                                selectorWrapper.select();
                                            }
                                            selectedKeys = selectorWrapper.selectedKeys();
                                            for (final SelectionKey selectionKey : selectedKeys) {
                                                try {
                                                    if (selectionKey.isAcceptable()) {
                                                        final ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectionKey.channel();
                                                        SocketChannel accept = null;
                                                        SelectionKey register = null;
                                                        try {
                                                            accept = serverSocketChannel.accept();
                                                            register = null;
                                                            if (accept == null) {
                                                                continue Label_0044_Outer;
                                                            }
                                                            accept.configureBlocking(false);
                                                            register = accept.register(selectorWrapper.getSelector(), 1);
                                                            final ListenCallback listenCallback = (ListenCallback)selectionKey.attachment();
                                                            final AsyncNetworkSocket asyncNetworkSocket = new AsyncNetworkSocket();
                                                            asyncNetworkSocket.attach(accept, (InetSocketAddress)accept.socket().getRemoteSocketAddress());
                                                            asyncNetworkSocket.setup(asyncServer, register);
                                                            register.attach(asyncNetworkSocket);
                                                            listenCallback.onAccepted(asyncNetworkSocket);
                                                        }
                                                        catch (IOException ex3) {
                                                            StreamUtility.closeQuietly(accept);
                                                            if (register == null) {
                                                                continue Label_0044_Outer;
                                                            }
                                                            register.cancel();
                                                        }
                                                        continue Label_0044_Outer;
                                                    }
                                                    break Label_0287;
                                                }
                                                catch (CancelledKeyException ex4) {}
                                            }
                                            break Label_0530;
                                        }
                                    }
                                    catch (Exception ex) {
                                        throw new AsyncSelectorException(ex);
                                    }
                                }
                                selectorWrapper.select(lockAndRunQueue);
                                continue Label_0078_Outer;
                            }
                            final SelectionKey selectionKey;
                            if (selectionKey.isReadable()) {
                                asyncServer.onDataReceived(((AsyncNetworkSocket)selectionKey.attachment()).onReadable());
                                continue;
                            }
                            if (selectionKey.isWritable()) {
                                ((AsyncNetworkSocket)selectionKey.attachment()).onDataWritable();
                                continue;
                            }
                            if (selectionKey.isConnectable()) {
                                final ConnectFuture connectFuture = (ConnectFuture)selectionKey.attachment();
                                final SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                                selectionKey.interestOps(1);
                                try {
                                    socketChannel.finishConnect();
                                    final AsyncNetworkSocket complete = new AsyncNetworkSocket();
                                    complete.setup(asyncServer, selectionKey);
                                    complete.attach(socketChannel, (InetSocketAddress)socketChannel.socket().getRemoteSocketAddress());
                                    selectionKey.attach(complete);
                                    try {
                                        if (connectFuture.setComplete(complete)) {
                                            connectFuture.callback.onConnectCompleted(null, complete);
                                            continue;
                                        }
                                        continue;
                                    }
                                    catch (Exception ex2) {
                                        throw new RuntimeException(ex2);
                                    }
                                }
                                catch (IOException complete2) {
                                    selectionKey.cancel();
                                    StreamUtility.closeQuietly(socketChannel);
                                    if (connectFuture.setComplete(complete2)) {
                                        connectFuture.callback.onConnectCompleted(complete2, null);
                                        continue;
                                    }
                                    continue;
                                }
                                break;
                            }
                            break;
                        }
                        break;
                    }
                    n = 0;
                    continue;
                }
            }
            Log.i("NIO", "wtf");
            throw new RuntimeException("Unknown key state.");
        }
        selectedKeys.clear();
    }
    
    private static void shutdownEverything(final SelectorWrapper selectorWrapper) {
        shutdownKeys(selectorWrapper);
        try {
            selectorWrapper.close();
        }
        catch (Exception ex) {}
    }
    
    private static void shutdownKeys(final SelectorWrapper selectorWrapper) {
        try {
            for (final SelectionKey selectionKey : selectorWrapper.keys()) {
                StreamUtility.closeQuietly(selectionKey.channel());
                try {
                    selectionKey.cancel();
                }
                catch (Exception ex) {}
            }
        }
        catch (Exception ex2) {}
    }
    
    private static void wakeup(final SelectorWrapper selectorWrapper) {
        AsyncServer.synchronousWorkers.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    selectorWrapper.wakeupOnce();
                }
                catch (Exception ex) {
                    Log.i("NIO", "Selector Exception? L Preview?");
                }
            }
        });
    }
    
    public AsyncDatagramSocket connectDatagram(final String s, final int n) throws IOException {
        final DatagramChannel open = DatagramChannel.open();
        final AsyncDatagramSocket asyncDatagramSocket = new AsyncDatagramSocket();
        asyncDatagramSocket.attach(open);
        this.run(new Runnable() {
            @Override
            public void run() {
                try {
                    final InetSocketAddress inetSocketAddress = new InetSocketAddress(s, n);
                    AsyncServer.this.handleSocket(asyncDatagramSocket);
                    open.connect(inetSocketAddress);
                }
                catch (IOException ex) {
                    Log.e("NIO", "Datagram error", (Throwable)ex);
                    StreamUtility.closeQuietly(open);
                }
            }
        });
        return asyncDatagramSocket;
    }
    
    public AsyncDatagramSocket connectDatagram(final SocketAddress socketAddress) throws IOException {
        final DatagramChannel open = DatagramChannel.open();
        final AsyncDatagramSocket asyncDatagramSocket = new AsyncDatagramSocket();
        asyncDatagramSocket.attach(open);
        this.run(new Runnable() {
            @Override
            public void run() {
                try {
                    AsyncServer.this.handleSocket(asyncDatagramSocket);
                    open.connect(socketAddress);
                }
                catch (IOException ex) {
                    StreamUtility.closeQuietly(open);
                }
            }
        });
        return asyncDatagramSocket;
    }
    
    public Cancellable connectSocket(final String s, final int n, final ConnectCallback connectCallback) {
        return this.connectSocket(InetSocketAddress.createUnresolved(s, n), connectCallback);
    }
    
    public Cancellable connectSocket(final InetSocketAddress inetSocketAddress, final ConnectCallback connectCallback) {
        SimpleFuture connectResolvedInetSocketAddress;
        if (!inetSocketAddress.isUnresolved()) {
            connectResolvedInetSocketAddress = this.connectResolvedInetSocketAddress(inetSocketAddress, connectCallback);
        }
        else {
            connectResolvedInetSocketAddress = new SimpleFuture<AsyncNetworkSocket>();
            final Future<InetAddress> byName = this.getByName(inetSocketAddress.getHostName());
            connectResolvedInetSocketAddress.setParent(byName);
            byName.setCallback(new FutureCallback<InetAddress>() {
                @Override
                public void onCompleted(final Exception complete, final InetAddress inetAddress) {
                    if (complete != null) {
                        connectCallback.onConnectCompleted(complete, null);
                        connectResolvedInetSocketAddress.setComplete(complete);
                    }
                    else {
                        connectResolvedInetSocketAddress.setComplete(AsyncServer.this.connectResolvedInetSocketAddress(new InetSocketAddress(inetAddress, inetSocketAddress.getPort()), connectCallback));
                    }
                }
            });
        }
        return connectResolvedInetSocketAddress;
    }
    
    public void dump() {
        this.post(new Runnable() {
            @Override
            public void run() {
                if (AsyncServer.this.mSelector == null) {
                    Log.i("NIO", "Server dump not possible. No selector?");
                }
                else {
                    Log.i("NIO", "Key Count: " + AsyncServer.this.mSelector.keys().size());
                    final Iterator<SelectionKey> iterator = AsyncServer.this.mSelector.keys().iterator();
                    while (iterator.hasNext()) {
                        Log.i("NIO", "Key: " + iterator.next());
                    }
                }
            }
        });
    }
    
    public Thread getAffinity() {
        return this.mAffinity;
    }
    
    public Future<InetAddress[]> getAllByName(final String s) {
        final SimpleFuture<InetAddress[]> simpleFuture = new SimpleFuture<InetAddress[]>();
        AsyncServer.synchronousWorkers.execute(new Runnable() {
            @Override
            public void run() {
                InetAddress[] allByName = null;
                Label_0053: {
                    try {
                        allByName = InetAddress.getAllByName(s);
                        Arrays.sort(allByName, AsyncServer.ipSorter);
                        if (allByName == null || allByName.length == 0) {
                            throw new HostnameResolutionException("no addresses for host");
                        }
                        break Label_0053;
                    }
                    catch (Exception ex) {
                        AsyncServer.this.post(new Runnable() {
                            @Override
                            public void run() {
                                simpleFuture.setComplete(ex, null);
                            }
                        });
                    }
                    return;
                }
                AsyncServer.this.post(new Runnable() {
                    @Override
                    public void run() {
                        simpleFuture.setComplete(null, allByName);
                    }
                });
            }
        });
        return simpleFuture;
    }
    
    public Future<InetAddress> getByName(final String s) {
        return this.getAllByName(s).then((Future<InetAddress>)new TransformFuture<InetAddress, InetAddress[]>() {
            @Override
            protected void transform(final InetAddress[] array) throws Exception {
                this.setComplete((T)array[0]);
            }
        });
    }
    
    public boolean isAffinityThread() {
        return this.mAffinity == Thread.currentThread();
    }
    
    public boolean isAffinityThreadOrStopped() {
        final Thread mAffinity = this.mAffinity;
        return mAffinity == null || mAffinity == Thread.currentThread();
    }
    
    public boolean isRunning() {
        return this.mSelector != null;
    }
    
    public AsyncServerSocket listen(final InetAddress inetAddress, final int n, final ListenCallback listenCallback) {
        final ObjectHolder objectHolder = new ObjectHolder();
        this.run(new Runnable() {
            @Override
            public void run() {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aconst_null    
                //     1: astore_1       
                //     2: aconst_null    
                //     3: astore_2       
                //     4: invokestatic    java/nio/channels/ServerSocketChannel.open:()Ljava/nio/channels/ServerSocketChannel;
                //     7: astore_1       
                //     8: new             Lcom/koushikdutta/async/ServerSocketChannelWrapper;
                //    11: dup            
                //    12: aload_1        
                //    13: invokespecial   com/koushikdutta/async/ServerSocketChannelWrapper.<init>:(Ljava/nio/channels/ServerSocketChannel;)V
                //    16: astore          5
                //    18: aload_1        
                //    19: astore          6
                //    21: aload_0        
                //    22: getfield        com/koushikdutta/async/AsyncServer$5.val$host:Ljava/net/InetAddress;
                //    25: ifnonnull       128
                //    28: new             Ljava/net/InetSocketAddress;
                //    31: dup            
                //    32: aload_0        
                //    33: getfield        com/koushikdutta/async/AsyncServer$5.val$port:I
                //    36: invokespecial   java/net/InetSocketAddress.<init>:(I)V
                //    39: astore          7
                //    41: aload           6
                //    43: invokevirtual   java/nio/channels/ServerSocketChannel.socket:()Ljava/net/ServerSocket;
                //    46: aload           7
                //    48: invokevirtual   java/net/ServerSocket.bind:(Ljava/net/SocketAddress;)V
                //    51: aload           5
                //    53: aload_0        
                //    54: getfield        com/koushikdutta/async/AsyncServer$5.this$0:Lcom/koushikdutta/async/AsyncServer;
                //    57: invokestatic    com/koushikdutta/async/AsyncServer.access$300:(Lcom/koushikdutta/async/AsyncServer;)Lcom/koushikdutta/async/SelectorWrapper;
                //    60: invokevirtual   com/koushikdutta/async/SelectorWrapper.getSelector:()Ljava/nio/channels/Selector;
                //    63: invokevirtual   com/koushikdutta/async/ServerSocketChannelWrapper.register:(Ljava/nio/channels/Selector;)Ljava/nio/channels/SelectionKey;
                //    66: astore          8
                //    68: aload           8
                //    70: aload_0        
                //    71: getfield        com/koushikdutta/async/AsyncServer$5.val$handler:Lcom/koushikdutta/async/callback/ListenCallback;
                //    74: invokevirtual   java/nio/channels/SelectionKey.attach:(Ljava/lang/Object;)Ljava/lang/Object;
                //    77: pop            
                //    78: aload_0        
                //    79: getfield        com/koushikdutta/async/AsyncServer$5.val$handler:Lcom/koushikdutta/async/callback/ListenCallback;
                //    82: astore          10
                //    84: aload_0        
                //    85: getfield        com/koushikdutta/async/AsyncServer$5.val$holder:Lcom/koushikdutta/async/AsyncServer$ObjectHolder;
                //    88: astore          11
                //    90: new             Lcom/koushikdutta/async/AsyncServer$5$1;
                //    93: dup            
                //    94: aload_0        
                //    95: aload           6
                //    97: aload           5
                //    99: aload           8
                //   101: invokespecial   com/koushikdutta/async/AsyncServer$5$1.<init>:(Lcom/koushikdutta/async/AsyncServer$5;Ljava/nio/channels/ServerSocketChannel;Lcom/koushikdutta/async/ServerSocketChannelWrapper;Ljava/nio/channels/SelectionKey;)V
                //   104: astore          12
                //   106: aload           11
                //   108: aload           12
                //   110: putfield        com/koushikdutta/async/AsyncServer$ObjectHolder.held:Ljava/lang/Object;
                //   113: aload           10
                //   115: aload           12
                //   117: checkcast       Lcom/koushikdutta/async/AsyncServerSocket;
                //   120: invokeinterface com/koushikdutta/async/callback/ListenCallback.onListening:(Lcom/koushikdutta/async/AsyncServerSocket;)V
                //   125: goto            193
                //   128: new             Ljava/net/InetSocketAddress;
                //   131: dup            
                //   132: aload_0        
                //   133: getfield        com/koushikdutta/async/AsyncServer$5.val$host:Ljava/net/InetAddress;
                //   136: aload_0        
                //   137: getfield        com/koushikdutta/async/AsyncServer$5.val$port:I
                //   140: invokespecial   java/net/InetSocketAddress.<init>:(Ljava/net/InetAddress;I)V
                //   143: astore          7
                //   145: goto            41
                //   148: astore_3       
                //   149: ldc             "NIO"
                //   151: ldc             "wtf"
                //   153: aload_3        
                //   154: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
                //   157: pop            
                //   158: iconst_2       
                //   159: anewarray       Ljava/io/Closeable;
                //   162: dup            
                //   163: iconst_0       
                //   164: aload_2        
                //   165: aastore        
                //   166: dup            
                //   167: iconst_1       
                //   168: aload_1        
                //   169: aastore        
                //   170: invokestatic    com/koushikdutta/async/util/StreamUtility.closeQuietly:([Ljava/io/Closeable;)V
                //   173: aload_0        
                //   174: getfield        com/koushikdutta/async/AsyncServer$5.val$handler:Lcom/koushikdutta/async/callback/ListenCallback;
                //   177: aload_3        
                //   178: invokeinterface com/koushikdutta/async/callback/ListenCallback.onCompleted:(Ljava/lang/Exception;)V
                //   183: goto            193
                //   186: astore_3       
                //   187: aload           5
                //   189: astore_2       
                //   190: goto            149
                //   193: return         
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  4      18     148    149    Ljava/io/IOException;
                //  21     145    186    193    Ljava/io/IOException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0041:
                //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
                //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
                //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
                //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
                //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
                //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
                //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
                //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
                //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
                // 
                throw new IllegalStateException("An error occurred while decompiling this method.");
            }
        });
        return (AsyncServerSocket)objectHolder.held;
    }
    
    protected void onDataReceived(final int n) {
    }
    
    protected void onDataSent(final int n) {
    }
    
    public AsyncDatagramSocket openDatagram() throws IOException {
        return this.openDatagram(null, false);
    }
    
    public AsyncDatagramSocket openDatagram(final SocketAddress socketAddress, final boolean b) throws IOException {
        final DatagramChannel open = DatagramChannel.open();
        final AsyncDatagramSocket asyncDatagramSocket = new AsyncDatagramSocket();
        asyncDatagramSocket.attach(open);
        this.run(new Runnable() {
            @Override
            public void run() {
                try {
                    if (b) {
                        open.socket().setReuseAddress(b);
                    }
                    open.socket().bind(socketAddress);
                    AsyncServer.this.handleSocket(asyncDatagramSocket);
                }
                catch (IOException ex) {
                    Log.e("NIO", "Datagram error", (Throwable)ex);
                    StreamUtility.closeQuietly(open);
                }
            }
        });
        return asyncDatagramSocket;
    }
    
    public Object post(final CompletedCallback completedCallback, final Exception ex) {
        return this.post(new Runnable() {
            @Override
            public void run() {
                completedCallback.onCompleted(ex);
            }
        });
    }
    
    public Object post(final Runnable runnable) {
        return this.postDelayed(runnable, 0L);
    }
    
    public Object postDelayed(final Runnable runnable, final long n) {
        // monitorenter(this)
        Label_0072: {
            if (n <= 0L) {
                break Label_0072;
            }
            while (true) {
                long min;
                PriorityQueue<Scheduled> mQueue;
                Scheduled scheduled;
                Block_5_Outer:Block_6_Outer:
                while (true) {
                    try {
                        min = n + System.currentTimeMillis();
                        mQueue = this.mQueue;
                        scheduled = new Scheduled(runnable, min);
                        mQueue.add(scheduled);
                        if (this.mSelector == null) {
                            this.run(true);
                        }
                        if (!this.isAffinityThread()) {
                            wakeup(this.mSelector);
                        }
                        return scheduled;
                        // iftrue(Label_0100:, n != 0L)
                        while (true) {
                            min = this.postCounter++;
                            continue Block_5_Outer;
                            continue Block_6_Outer;
                        }
                        while (true) {
                            min = Math.min(0L, this.mQueue.peek().time - 1L);
                            continue Block_5_Outer;
                            Label_0100: {
                                continue;
                            }
                        }
                    }
                    // iftrue(Label_0141:, this.mQueue.size() <= 0)
                    finally {
                    }
                    // monitorexit(this)
                    Label_0141: {
                        min = 0L;
                    }
                    continue;
                }
            }
        }
    }
    
    public Object postImmediate(final Runnable runnable) {
        Object postDelayed;
        if (Thread.currentThread() == this.getAffinity()) {
            runnable.run();
            postDelayed = null;
        }
        else {
            postDelayed = this.postDelayed(runnable, -1L);
        }
        return postDelayed;
    }
    
    public void removeAllCallbacks(final Object o) {
        synchronized (this) {
            this.mQueue.remove(o);
        }
    }
    
    public void run(final Runnable runnable) {
        if (Thread.currentThread() == this.mAffinity) {
            this.post(runnable);
            lockAndRunQueue(this, this.mQueue);
        }
        else {
            final Semaphore semaphore = new Semaphore(0);
            this.post(new Runnable() {
                @Override
                public void run() {
                    runnable.run();
                    semaphore.release();
                }
            });
            try {
                semaphore.acquire();
            }
            catch (InterruptedException ex) {
                Log.e("NIO", "run", (Throwable)ex);
            }
        }
    }
    
    public void stop() {
        synchronized (this) {
            final boolean affinityThread = this.isAffinityThread();
            final SelectorWrapper mSelector = this.mSelector;
            if (mSelector != null) {
                synchronized (AsyncServer.mServers) {
                    AsyncServer.mServers.remove(this.mAffinity);
                    // monitorexit(AsyncServer.mServers)
                    final Semaphore semaphore = new Semaphore(0);
                    this.mQueue.add(new Scheduled(new Runnable() {
                        @Override
                        public void run() {
                            shutdownEverything(mSelector);
                            semaphore.release();
                        }
                    }, 0L));
                    mSelector.wakeupOnce();
                    shutdownKeys(mSelector);
                    this.mQueue = new PriorityQueue<Scheduled>(1, (Comparator<? super Scheduled>)Scheduler.INSTANCE);
                    this.mSelector = null;
                    this.mAffinity = null;
                    // monitorexit(this)
                    if (!affinityThread) {
                        try {
                            semaphore.acquire();
                        }
                        catch (Exception ex) {}
                    }
                }
            }
        }
    }
    
    private static class AsyncSelectorException extends IOException
    {
        public AsyncSelectorException(final Exception ex) {
            super(ex);
        }
    }
    
    private class ConnectFuture extends SimpleFuture<AsyncNetworkSocket>
    {
        ConnectCallback callback;
        SocketChannel socket;
        
        @Override
        protected void cancelCleanup() {
            super.cancelCleanup();
            try {
                if (this.socket != null) {
                    this.socket.close();
                }
            }
            catch (IOException ex) {}
        }
    }
    
    private static class NamedThreadFactory implements ThreadFactory
    {
        private final ThreadGroup group;
        private final String namePrefix;
        private final AtomicInteger threadNumber;
        
        NamedThreadFactory(final String namePrefix) {
            this.threadNumber = new AtomicInteger(1);
            final SecurityManager securityManager = System.getSecurityManager();
            ThreadGroup group;
            if (securityManager != null) {
                group = securityManager.getThreadGroup();
            }
            else {
                group = Thread.currentThread().getThreadGroup();
            }
            this.group = group;
            this.namePrefix = namePrefix;
        }
        
        @Override
        public Thread newThread(final Runnable runnable) {
            final Thread thread = new Thread(this.group, runnable, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            return thread;
        }
    }
    
    private static class ObjectHolder<T>
    {
        T held;
    }
    
    private static class RunnableWrapper implements Runnable
    {
        Handler handler;
        boolean hasRun;
        Runnable runnable;
        ThreadQueue threadQueue;
        
        @Override
        public void run() {
            synchronized (this) {
                if (this.hasRun) {
                    return;
                }
                this.hasRun = true;
                // monitorexit(this)
                final RunnableWrapper runnableWrapper = this;
                final Runnable runnable = runnableWrapper.runnable;
                runnable.run();
                return;
            }
            try {
                final RunnableWrapper runnableWrapper = this;
                final Runnable runnable = runnableWrapper.runnable;
                runnable.run();
            }
            finally {
                this.threadQueue.remove(this);
                this.handler.removeCallbacks((Runnable)this);
                this.threadQueue = null;
                this.handler = null;
                this.runnable = null;
            }
        }
    }
    
    private static class Scheduled
    {
        public Runnable runnable;
        public long time;
        
        public Scheduled(final Runnable runnable, final long time) {
            this.runnable = runnable;
            this.time = time;
        }
    }
    
    static class Scheduler implements Comparator<Scheduled>
    {
        public static Scheduler INSTANCE;
        
        static {
            Scheduler.INSTANCE = new Scheduler();
        }
        
        @Override
        public int compare(final Scheduled scheduled, final Scheduled scheduled2) {
            int n;
            if (scheduled.time == scheduled2.time) {
                n = 0;
            }
            else if (scheduled.time > scheduled2.time) {
                n = 1;
            }
            else {
                n = -1;
            }
            return n;
        }
    }
}
