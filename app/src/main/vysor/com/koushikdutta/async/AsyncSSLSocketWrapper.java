// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import java.nio.ByteBuffer;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLEngineResult;
import com.koushikdutta.async.util.Allocator;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.X509TrustManager;
import android.os.Build$VERSION;
import javax.net.ssl.TrustManager;
import java.security.cert.X509Certificate;
import com.koushikdutta.async.callback.WritableCallback;
import com.koushikdutta.async.callback.CompletedCallback;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLEngine;
import com.koushikdutta.async.callback.DataCallback;
import javax.net.ssl.SSLContext;
import com.koushikdutta.async.wrapper.AsyncSocketWrapper;

public class AsyncSSLSocketWrapper implements AsyncSSLSocket, AsyncSocketWrapper
{
    static SSLContext defaultSSLContext;
    boolean clientMode;
    final DataCallback dataCallback;
    SSLEngine engine;
    boolean finishedHandshake;
    HandshakeCallback handshakeCallback;
    HostnameVerifier hostnameVerifier;
    DataCallback mDataCallback;
    CompletedCallback mEndCallback;
    Exception mEndException;
    boolean mEnded;
    private String mHost;
    private int mPort;
    BufferedDataSink mSink;
    AsyncSocket mSocket;
    boolean mUnwrapping;
    private boolean mWrapping;
    WritableCallback mWriteableCallback;
    X509Certificate[] peerCertificates;
    final ByteBufferList pending;
    TrustManager[] trustManagers;
    ByteBufferList writeList;
    
    static {
        boolean $assertionsDisabled2 = true;
        Label_0064: {
            if (AsyncSSLSocketWrapper.class.desiredAssertionStatus()) {
                break Label_0064;
            }
            while (true) {
                $assertionsDisabled = $assertionsDisabled2;
                Label_0069: {
                    try {
                        if (Build$VERSION.SDK_INT <= 15) {
                            throw new Exception();
                        }
                        break Label_0069;
                    }
                    catch (Exception ex) {
                        final String s = "TLS";
                        AsyncSSLSocketWrapper.defaultSSLContext = SSLContext.getInstance(s);
                        final int n = 1;
                        final TrustManager[] array = new TrustManager[n];
                        final TrustManager[] array3;
                        final TrustManager[] array2 = array3 = array;
                        final int n2 = 0;
                        final X509TrustManager x509TrustManager = new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(final X509Certificate[] array, final String s) {
                            }
                            
                            @Override
                            public void checkServerTrusted(final X509Certificate[] array, final String s) {
                                for (final X509Certificate x509Certificate : array) {
                                    if (x509Certificate != null && x509Certificate.getCriticalExtensionOIDs() != null) {
                                        x509Certificate.getCriticalExtensionOIDs().remove("2.5.29.15");
                                    }
                                }
                            }
                            
                            @Override
                            public X509Certificate[] getAcceptedIssuers() {
                                return new X509Certificate[0];
                            }
                        };
                        array3[n2] = x509TrustManager;
                        final SSLContext sslContext = AsyncSSLSocketWrapper.defaultSSLContext;
                        final KeyManager[] array4 = null;
                        final TrustManager[] array5 = array2;
                        final SecureRandom secureRandom = null;
                        sslContext.init(array4, array5, secureRandom);
                    }
                    try {
                        final String s = "TLS";
                        AsyncSSLSocketWrapper.defaultSSLContext = SSLContext.getInstance(s);
                        final int n = 1;
                        final TrustManager[] array = new TrustManager[n];
                        final TrustManager[] array3;
                        final TrustManager[] array2 = array3 = array;
                        final int n2 = 0;
                        final X509TrustManager x509TrustManager = new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(final X509Certificate[] array, final String s) {
                            }
                            
                            @Override
                            public void checkServerTrusted(final X509Certificate[] array, final String s) {
                                for (final X509Certificate x509Certificate : array) {
                                    if (x509Certificate != null && x509Certificate.getCriticalExtensionOIDs() != null) {
                                        x509Certificate.getCriticalExtensionOIDs().remove("2.5.29.15");
                                    }
                                }
                            }
                            
                            @Override
                            public X509Certificate[] getAcceptedIssuers() {
                                return new X509Certificate[0];
                            }
                        };
                        array3[n2] = x509TrustManager;
                        final SSLContext sslContext = AsyncSSLSocketWrapper.defaultSSLContext;
                        final KeyManager[] array4 = null;
                        final TrustManager[] array5 = array2;
                        final SecureRandom secureRandom = null;
                        sslContext.init(array4, array5, secureRandom);
                        return;
                        $assertionsDisabled2 = false;
                        continue;
                        AsyncSSLSocketWrapper.defaultSSLContext = SSLContext.getInstance("Default");
                    }
                    catch (Exception ex2) {
                        final Exception ex;
                        ex.printStackTrace();
                        ex2.printStackTrace();
                    }
                }
            }
        }
    }
    
    private AsyncSSLSocketWrapper(final AsyncSocket mSocket, final String mHost, final int mPort, final SSLEngine engine, final TrustManager[] trustManagers, final HostnameVerifier hostnameVerifier, final boolean b) {
        this.pending = new ByteBufferList();
        this.dataCallback = new DataCallback() {
            final Allocator allocator = new Allocator().setMinAlloc(8192);
            final ByteBufferList buffered = new ByteBufferList();
            
            @Override
            public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                if (!AsyncSSLSocketWrapper.this.mUnwrapping) {
                    try {
                        AsyncSSLSocketWrapper.this.mUnwrapping = true;
                        list.get(this.buffered);
                        if (this.buffered.hasRemaining()) {
                            this.buffered.add(this.buffered.getAll());
                        }
                        ByteBuffer byteBuffer = ByteBufferList.EMPTY_BYTEBUFFER;
                        while (true) {
                            if (byteBuffer.remaining() == 0 && this.buffered.size() > 0) {
                                byteBuffer = this.buffered.remove();
                            }
                            int remaining = byteBuffer.remaining();
                            final int remaining2 = AsyncSSLSocketWrapper.this.pending.remaining();
                            final ByteBuffer allocate = this.allocator.allocate();
                            final SSLEngineResult unwrap = AsyncSSLSocketWrapper.this.engine.unwrap(byteBuffer, allocate);
                            AsyncSSLSocketWrapper.this.addToPending(AsyncSSLSocketWrapper.this.pending, allocate);
                            this.allocator.track(AsyncSSLSocketWrapper.this.pending.remaining() - remaining2);
                            if (unwrap.getStatus() == SSLEngineResult.Status.BUFFER_OVERFLOW) {
                                this.allocator.setMinAlloc(2 * this.allocator.getMinAlloc());
                                remaining = -1;
                            }
                            else if (unwrap.getStatus() == SSLEngineResult.Status.BUFFER_UNDERFLOW) {
                                this.buffered.addFirst(byteBuffer);
                                if (this.buffered.size() <= 1) {
                                    break;
                                }
                                remaining = -1;
                                this.buffered.addFirst(this.buffered.getAll());
                                byteBuffer = ByteBufferList.EMPTY_BYTEBUFFER;
                            }
                            AsyncSSLSocketWrapper.this.handleHandshakeStatus(unwrap.getHandshakeStatus());
                            if (byteBuffer.remaining() == remaining && remaining2 == AsyncSSLSocketWrapper.this.pending.remaining()) {
                                this.buffered.addFirst(byteBuffer);
                                break;
                            }
                        }
                        AsyncSSLSocketWrapper.this.onDataAvailable();
                    }
                    catch (SSLException ex) {
                        ex.printStackTrace();
                        AsyncSSLSocketWrapper.this.report(ex);
                    }
                    finally {
                        AsyncSSLSocketWrapper.this.mUnwrapping = false;
                    }
                }
            }
        };
        this.writeList = new ByteBufferList();
        this.mSocket = mSocket;
        this.hostnameVerifier = hostnameVerifier;
        this.clientMode = b;
        this.trustManagers = trustManagers;
        this.engine = engine;
        this.mHost = mHost;
        this.mPort = mPort;
        this.engine.setUseClientMode(b);
        (this.mSink = new BufferedDataSink(mSocket)).setWriteableCallback(new WritableCallback() {
            @Override
            public void onWriteable() {
                if (AsyncSSLSocketWrapper.this.mWriteableCallback != null) {
                    AsyncSSLSocketWrapper.this.mWriteableCallback.onWriteable();
                }
            }
        });
        this.mSocket.setEndCallback(new CompletedCallback() {
            @Override
            public void onCompleted(final Exception mEndException) {
                if (!AsyncSSLSocketWrapper.this.mEnded) {
                    AsyncSSLSocketWrapper.this.mEnded = true;
                    AsyncSSLSocketWrapper.this.mEndException = mEndException;
                    if (!AsyncSSLSocketWrapper.this.pending.hasRemaining() && AsyncSSLSocketWrapper.this.mEndCallback != null) {
                        AsyncSSLSocketWrapper.this.mEndCallback.onCompleted(mEndException);
                    }
                }
            }
        });
        this.mSocket.setDataCallback(this.dataCallback);
    }
    
    public static SSLContext getDefaultSSLContext() {
        return AsyncSSLSocketWrapper.defaultSSLContext;
    }
    
    private void handleHandshakeStatus(final SSLEngineResult.HandshakeStatus p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getstatic       javax/net/ssl/SSLEngineResult$HandshakeStatus.NEED_TASK:Ljavax/net/ssl/SSLEngineResult$HandshakeStatus;
        //     4: if_acmpne       19
        //     7: aload_0        
        //     8: getfield        com/koushikdutta/async/AsyncSSLSocketWrapper.engine:Ljavax/net/ssl/SSLEngine;
        //    11: invokevirtual   javax/net/ssl/SSLEngine.getDelegatedTask:()Ljava/lang/Runnable;
        //    14: invokeinterface java/lang/Runnable.run:()V
        //    19: aload_1        
        //    20: getstatic       javax/net/ssl/SSLEngineResult$HandshakeStatus.NEED_WRAP:Ljavax/net/ssl/SSLEngineResult$HandshakeStatus;
        //    23: if_acmpne       34
        //    26: aload_0        
        //    27: aload_0        
        //    28: getfield        com/koushikdutta/async/AsyncSSLSocketWrapper.writeList:Lcom/koushikdutta/async/ByteBufferList;
        //    31: invokevirtual   com/koushikdutta/async/AsyncSSLSocketWrapper.write:(Lcom/koushikdutta/async/ByteBufferList;)V
        //    34: aload_1        
        //    35: getstatic       javax/net/ssl/SSLEngineResult$HandshakeStatus.NEED_UNWRAP:Ljavax/net/ssl/SSLEngineResult$HandshakeStatus;
        //    38: if_acmpne       58
        //    41: aload_0        
        //    42: getfield        com/koushikdutta/async/AsyncSSLSocketWrapper.dataCallback:Lcom/koushikdutta/async/callback/DataCallback;
        //    45: aload_0        
        //    46: new             Lcom/koushikdutta/async/ByteBufferList;
        //    49: dup            
        //    50: invokespecial   com/koushikdutta/async/ByteBufferList.<init>:()V
        //    53: invokeinterface com/koushikdutta/async/callback/DataCallback.onDataAvailable:(Lcom/koushikdutta/async/DataEmitter;Lcom/koushikdutta/async/ByteBufferList;)V
        //    58: aload_0        
        //    59: getfield        com/koushikdutta/async/AsyncSSLSocketWrapper.finishedHandshake:Z
        //    62: ifne            434
        //    65: aload_0        
        //    66: getfield        com/koushikdutta/async/AsyncSSLSocketWrapper.engine:Ljavax/net/ssl/SSLEngine;
        //    69: invokevirtual   javax/net/ssl/SSLEngine.getHandshakeStatus:()Ljavax/net/ssl/SSLEngineResult$HandshakeStatus;
        //    72: getstatic       javax/net/ssl/SSLEngineResult$HandshakeStatus.NOT_HANDSHAKING:Ljavax/net/ssl/SSLEngineResult$HandshakeStatus;
        //    75: if_acmpeq       91
        //    78: aload_0        
        //    79: getfield        com/koushikdutta/async/AsyncSSLSocketWrapper.engine:Ljavax/net/ssl/SSLEngine;
        //    82: invokevirtual   javax/net/ssl/SSLEngine.getHandshakeStatus:()Ljavax/net/ssl/SSLEngineResult$HandshakeStatus;
        //    85: getstatic       javax/net/ssl/SSLEngineResult$HandshakeStatus.FINISHED:Ljavax/net/ssl/SSLEngineResult$HandshakeStatus;
        //    88: if_acmpne       434
        //    91: aload_0        
        //    92: getfield        com/koushikdutta/async/AsyncSSLSocketWrapper.clientMode:Z
        //    95: ifeq            383
        //    98: aload_0        
        //    99: getfield        com/koushikdutta/async/AsyncSSLSocketWrapper.trustManagers:[Ljavax/net/ssl/TrustManager;
        //   102: astore          6
        //   104: aload           6
        //   106: ifnonnull       133
        //   109: invokestatic    javax/net/ssl/TrustManagerFactory.getDefaultAlgorithm:()Ljava/lang/String;
        //   112: invokestatic    javax/net/ssl/TrustManagerFactory.getInstance:(Ljava/lang/String;)Ljavax/net/ssl/TrustManagerFactory;
        //   115: astore          16
        //   117: aload           16
        //   119: aconst_null    
        //   120: checkcast       Ljava/security/KeyStore;
        //   123: invokevirtual   javax/net/ssl/TrustManagerFactory.init:(Ljava/security/KeyStore;)V
        //   126: aload           16
        //   128: invokevirtual   javax/net/ssl/TrustManagerFactory.getTrustManagers:()[Ljavax/net/ssl/TrustManager;
        //   131: astore          6
        //   133: aconst_null    
        //   134: astore          7
        //   136: aload           6
        //   138: arraylength    
        //   139: istore          8
        //   141: iconst_0       
        //   142: istore          9
        //   144: iconst_0       
        //   145: istore          10
        //   147: iload           9
        //   149: iload           8
        //   151: if_icmpge       252
        //   154: aload           6
        //   156: iload           9
        //   158: aaload         
        //   159: astore          12
        //   161: aload           12
        //   163: checkcast       Ljavax/net/ssl/X509TrustManager;
        //   166: astore          15
        //   168: aload_0        
        //   169: aload_0        
        //   170: getfield        com/koushikdutta/async/AsyncSSLSocketWrapper.engine:Ljavax/net/ssl/SSLEngine;
        //   173: invokevirtual   javax/net/ssl/SSLEngine.getSession:()Ljavax/net/ssl/SSLSession;
        //   176: invokeinterface javax/net/ssl/SSLSession.getPeerCertificates:()[Ljava/security/cert/Certificate;
        //   181: checkcast       [Ljava/security/cert/X509Certificate;
        //   184: checkcast       [Ljava/security/cert/X509Certificate;
        //   187: putfield        com/koushikdutta/async/AsyncSSLSocketWrapper.peerCertificates:[Ljava/security/cert/X509Certificate;
        //   190: aload           15
        //   192: aload_0        
        //   193: getfield        com/koushikdutta/async/AsyncSSLSocketWrapper.peerCertificates:[Ljava/security/cert/X509Certificate;
        //   196: ldc             "SSL"
        //   198: invokeinterface javax/net/ssl/X509TrustManager.checkServerTrusted:([Ljava/security/cert/X509Certificate;Ljava/lang/String;)V
        //   203: aload_0        
        //   204: getfield        com/koushikdutta/async/AsyncSSLSocketWrapper.mHost:Ljava/lang/String;
        //   207: ifnull          249
        //   210: aload_0        
        //   211: getfield        com/koushikdutta/async/AsyncSSLSocketWrapper.hostnameVerifier:Ljavax/net/ssl/HostnameVerifier;
        //   214: ifnonnull       302
        //   217: new             Lorg/apache/http/conn/ssl/StrictHostnameVerifier;
        //   220: dup            
        //   221: invokespecial   org/apache/http/conn/ssl/StrictHostnameVerifier.<init>:()V
        //   224: aload_0        
        //   225: getfield        com/koushikdutta/async/AsyncSSLSocketWrapper.mHost:Ljava/lang/String;
        //   228: aload_0        
        //   229: getfield        com/koushikdutta/async/AsyncSSLSocketWrapper.peerCertificates:[Ljava/security/cert/X509Certificate;
        //   232: iconst_0       
        //   233: aaload         
        //   234: invokestatic    org/apache/http/conn/ssl/StrictHostnameVerifier.getCNs:(Ljava/security/cert/X509Certificate;)[Ljava/lang/String;
        //   237: aload_0        
        //   238: getfield        com/koushikdutta/async/AsyncSSLSocketWrapper.peerCertificates:[Ljava/security/cert/X509Certificate;
        //   241: iconst_0       
        //   242: aaload         
        //   243: invokestatic    org/apache/http/conn/ssl/StrictHostnameVerifier.getDNSSubjectAlts:(Ljava/security/cert/X509Certificate;)[Ljava/lang/String;
        //   246: invokevirtual   org/apache/http/conn/ssl/StrictHostnameVerifier.verify:(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;)V
        //   249: iconst_1       
        //   250: istore          10
        //   252: aload_0        
        //   253: iconst_1       
        //   254: putfield        com/koushikdutta/async/AsyncSSLSocketWrapper.finishedHandshake:Z
        //   257: iload           10
        //   259: ifne            388
        //   262: new             Lcom/koushikdutta/async/AsyncSSLException;
        //   265: dup            
        //   266: aload           7
        //   268: invokespecial   com/koushikdutta/async/AsyncSSLException.<init>:(Ljava/lang/Throwable;)V
        //   271: astore          11
        //   273: aload_0        
        //   274: aload           11
        //   276: invokespecial   com/koushikdutta/async/AsyncSSLSocketWrapper.report:(Ljava/lang/Exception;)V
        //   279: aload           11
        //   281: invokevirtual   com/koushikdutta/async/AsyncSSLException.getIgnore:()Z
        //   284: ifne            388
        //   287: aload           11
        //   289: athrow         
        //   290: astore          4
        //   292: new             Ljava/lang/RuntimeException;
        //   295: dup            
        //   296: aload           4
        //   298: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //   301: athrow         
        //   302: aload_0        
        //   303: getfield        com/koushikdutta/async/AsyncSSLSocketWrapper.hostnameVerifier:Ljavax/net/ssl/HostnameVerifier;
        //   306: aload_0        
        //   307: getfield        com/koushikdutta/async/AsyncSSLSocketWrapper.mHost:Ljava/lang/String;
        //   310: aload_0        
        //   311: getfield        com/koushikdutta/async/AsyncSSLSocketWrapper.engine:Ljavax/net/ssl/SSLEngine;
        //   314: invokevirtual   javax/net/ssl/SSLEngine.getSession:()Ljavax/net/ssl/SSLSession;
        //   317: invokeinterface javax/net/ssl/HostnameVerifier.verify:(Ljava/lang/String;Ljavax/net/ssl/SSLSession;)Z
        //   322: ifne            249
        //   325: new             Ljavax/net/ssl/SSLException;
        //   328: dup            
        //   329: new             Ljava/lang/StringBuilder;
        //   332: dup            
        //   333: invokespecial   java/lang/StringBuilder.<init>:()V
        //   336: ldc_w           "hostname <"
        //   339: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   342: aload_0        
        //   343: getfield        com/koushikdutta/async/AsyncSSLSocketWrapper.mHost:Ljava/lang/String;
        //   346: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   349: ldc_w           "> has been denied"
        //   352: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   355: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   358: invokespecial   javax/net/ssl/SSLException.<init>:(Ljava/lang/String;)V
        //   361: athrow         
        //   362: astore          14
        //   364: aload           14
        //   366: astore          7
        //   368: iinc            9, 1
        //   371: goto            144
        //   374: astore          13
        //   376: aload           13
        //   378: astore          7
        //   380: goto            368
        //   383: aload_0        
        //   384: iconst_1       
        //   385: putfield        com/koushikdutta/async/AsyncSSLSocketWrapper.finishedHandshake:Z
        //   388: aload_0        
        //   389: getfield        com/koushikdutta/async/AsyncSSLSocketWrapper.handshakeCallback:Lcom/koushikdutta/async/AsyncSSLSocketWrapper$HandshakeCallback;
        //   392: aconst_null    
        //   393: aload_0        
        //   394: invokeinterface com/koushikdutta/async/AsyncSSLSocketWrapper$HandshakeCallback.onHandshakeCompleted:(Ljava/lang/Exception;Lcom/koushikdutta/async/AsyncSSLSocket;)V
        //   399: aload_0        
        //   400: aconst_null    
        //   401: putfield        com/koushikdutta/async/AsyncSSLSocketWrapper.handshakeCallback:Lcom/koushikdutta/async/AsyncSSLSocketWrapper$HandshakeCallback;
        //   404: aload_0        
        //   405: getfield        com/koushikdutta/async/AsyncSSLSocketWrapper.mSocket:Lcom/koushikdutta/async/AsyncSocket;
        //   408: aconst_null    
        //   409: invokeinterface com/koushikdutta/async/AsyncSocket.setClosedCallback:(Lcom/koushikdutta/async/callback/CompletedCallback;)V
        //   414: aload_0        
        //   415: invokevirtual   com/koushikdutta/async/AsyncSSLSocketWrapper.getServer:()Lcom/koushikdutta/async/AsyncServer;
        //   418: new             Lcom/koushikdutta/async/AsyncSSLSocketWrapper$6;
        //   421: dup            
        //   422: aload_0        
        //   423: invokespecial   com/koushikdutta/async/AsyncSSLSocketWrapper$6.<init>:(Lcom/koushikdutta/async/AsyncSSLSocketWrapper;)V
        //   426: invokevirtual   com/koushikdutta/async/AsyncServer.post:(Ljava/lang/Runnable;)Ljava/lang/Object;
        //   429: pop            
        //   430: aload_0        
        //   431: invokevirtual   com/koushikdutta/async/AsyncSSLSocketWrapper.onDataAvailable:()V
        //   434: return         
        //   435: astore_3       
        //   436: aload_0        
        //   437: aload_3        
        //   438: invokespecial   com/koushikdutta/async/AsyncSSLSocketWrapper.report:(Ljava/lang/Exception;)V
        //   441: goto            434
        //   444: astore_2       
        //   445: aload_0        
        //   446: aload_2        
        //   447: invokespecial   com/koushikdutta/async/AsyncSSLSocketWrapper.report:(Ljava/lang/Exception;)V
        //   450: goto            434
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                      
        //  -----  -----  -----  -----  ------------------------------------------
        //  58     161    290    302    Ljava/security/NoSuchAlgorithmException;
        //  58     161    435    444    Ljava/security/GeneralSecurityException;
        //  58     161    444    453    Lcom/koushikdutta/async/AsyncSSLException;
        //  161    249    362    368    Ljava/security/GeneralSecurityException;
        //  161    249    374    383    Ljavax/net/ssl/SSLException;
        //  161    249    290    302    Ljava/security/NoSuchAlgorithmException;
        //  161    249    444    453    Lcom/koushikdutta/async/AsyncSSLException;
        //  252    290    290    302    Ljava/security/NoSuchAlgorithmException;
        //  252    290    435    444    Ljava/security/GeneralSecurityException;
        //  252    290    444    453    Lcom/koushikdutta/async/AsyncSSLException;
        //  302    362    362    368    Ljava/security/GeneralSecurityException;
        //  302    362    374    383    Ljavax/net/ssl/SSLException;
        //  302    362    290    302    Ljava/security/NoSuchAlgorithmException;
        //  302    362    444    453    Lcom/koushikdutta/async/AsyncSSLException;
        //  383    434    290    302    Ljava/security/NoSuchAlgorithmException;
        //  383    434    435    444    Ljava/security/GeneralSecurityException;
        //  383    434    444    453    Lcom/koushikdutta/async/AsyncSSLException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0249:
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
    
    public static void handshake(final AsyncSocket asyncSocket, final String s, final int n, final SSLEngine sslEngine, final TrustManager[] array, final HostnameVerifier hostnameVerifier, final boolean b, final HandshakeCallback handshakeCallback) {
        final AsyncSSLSocketWrapper asyncSSLSocketWrapper = new AsyncSSLSocketWrapper(asyncSocket, s, n, sslEngine, array, hostnameVerifier, b);
        asyncSSLSocketWrapper.handshakeCallback = handshakeCallback;
        asyncSocket.setClosedCallback(new CompletedCallback() {
            @Override
            public void onCompleted(final Exception ex) {
                if (ex != null) {
                    handshakeCallback.onHandshakeCompleted(ex, null);
                }
                else {
                    handshakeCallback.onHandshakeCompleted(new SSLException("socket closed during handshake"), null);
                }
            }
        });
        try {
            asyncSSLSocketWrapper.engine.beginHandshake();
            asyncSSLSocketWrapper.handleHandshakeStatus(asyncSSLSocketWrapper.engine.getHandshakeStatus());
        }
        catch (SSLException ex) {
            asyncSSLSocketWrapper.report(ex);
        }
    }
    
    private void report(final Exception ex) {
        final HandshakeCallback handshakeCallback = this.handshakeCallback;
        if (handshakeCallback != null) {
            this.handshakeCallback = null;
            this.mSocket.setDataCallback(new DataCallback.NullDataCallback());
            this.mSocket.end();
            this.mSocket.setClosedCallback(null);
            this.mSocket.close();
            handshakeCallback.onHandshakeCompleted(ex, null);
        }
        else {
            final CompletedCallback endCallback = this.getEndCallback();
            if (endCallback != null) {
                endCallback.onCompleted(ex);
            }
        }
    }
    
    void addToPending(final ByteBufferList list, final ByteBuffer byteBuffer) {
        byteBuffer.flip();
        if (byteBuffer.hasRemaining()) {
            list.add(byteBuffer);
        }
        else {
            ByteBufferList.reclaim(byteBuffer);
        }
    }
    
    int calculateAlloc(final int n) {
        int n2 = n * 3 / 2;
        if (n2 == 0) {
            n2 = 8192;
        }
        return n2;
    }
    
    @Override
    public String charset() {
        return null;
    }
    
    @Override
    public void close() {
        this.mSocket.close();
    }
    
    @Override
    public void end() {
        this.mSocket.end();
    }
    
    @Override
    public CompletedCallback getClosedCallback() {
        return this.mSocket.getClosedCallback();
    }
    
    @Override
    public DataCallback getDataCallback() {
        return this.mDataCallback;
    }
    
    @Override
    public DataEmitter getDataEmitter() {
        return this.mSocket;
    }
    
    @Override
    public CompletedCallback getEndCallback() {
        return this.mEndCallback;
    }
    
    public String getHost() {
        return this.mHost;
    }
    
    @Override
    public X509Certificate[] getPeerCertificates() {
        return this.peerCertificates;
    }
    
    public int getPort() {
        return this.mPort;
    }
    
    @Override
    public SSLEngine getSSLEngine() {
        return this.engine;
    }
    
    @Override
    public AsyncServer getServer() {
        return this.mSocket.getServer();
    }
    
    @Override
    public AsyncSocket getSocket() {
        return this.mSocket;
    }
    
    @Override
    public WritableCallback getWriteableCallback() {
        return this.mWriteableCallback;
    }
    
    @Override
    public boolean isChunked() {
        return this.mSocket.isChunked();
    }
    
    @Override
    public boolean isOpen() {
        return this.mSocket.isOpen();
    }
    
    @Override
    public boolean isPaused() {
        return this.mSocket.isPaused();
    }
    
    public void onDataAvailable() {
        Util.emitAllData(this, this.pending);
        if (this.mEnded && !this.pending.hasRemaining() && this.mEndCallback != null) {
            this.mEndCallback.onCompleted(this.mEndException);
        }
    }
    
    @Override
    public void pause() {
        this.mSocket.pause();
    }
    
    @Override
    public void resume() {
        this.mSocket.resume();
        this.onDataAvailable();
    }
    
    @Override
    public void setClosedCallback(final CompletedCallback closedCallback) {
        this.mSocket.setClosedCallback(closedCallback);
    }
    
    @Override
    public void setDataCallback(final DataCallback mDataCallback) {
        this.mDataCallback = mDataCallback;
    }
    
    @Override
    public void setEndCallback(final CompletedCallback mEndCallback) {
        this.mEndCallback = mEndCallback;
    }
    
    @Override
    public void setWriteableCallback(final WritableCallback mWriteableCallback) {
        this.mWriteableCallback = mWriteableCallback;
    }
    
    @Override
    public void write(final ByteBufferList list) {
        if (!this.mWrapping && this.mSink.remaining() <= 0) {
            this.mWrapping = true;
            SSLEngineResult wrap = null;
            ByteBuffer byteBuffer = ByteBufferList.obtain(this.calculateAlloc(list.remaining()));
        Label_0139_Outer:
            while (!this.finishedHandshake || list.remaining() != 0) {
                int remaining = list.remaining();
                while (true) {
                    Label_0175: {
                        try {
                            final ByteBuffer[] allArray = list.getAllArray();
                            wrap = this.engine.wrap(allArray, byteBuffer);
                            list.addAll(allArray);
                            byteBuffer.flip();
                            this.writeList.add(byteBuffer);
                            assert !this.writeList.hasRemaining();
                            break Label_0175;
                        }
                        catch (SSLException ex) {
                            this.report(ex);
                        }
                        if (remaining == list.remaining() && (wrap == null || wrap.getHandshakeStatus() != SSLEngineResult.HandshakeStatus.NEED_WRAP)) {
                            break;
                        }
                        if (this.mSink.remaining() != 0) {
                            break;
                        }
                        continue Label_0139_Outer;
                    }
                    if (this.writeList.remaining() > 0) {
                        this.mSink.write(this.writeList);
                    }
                    final int capacity = byteBuffer.capacity();
                    if (wrap.getStatus() == SSLEngineResult.Status.BUFFER_OVERFLOW) {
                        byteBuffer = ByteBufferList.obtain(capacity * 2);
                        remaining = -1;
                        continue;
                    }
                    byteBuffer = ByteBufferList.obtain(this.calculateAlloc(list.remaining()));
                    this.handleHandshakeStatus(wrap.getHandshakeStatus());
                    continue;
                }
            }
            this.mWrapping = false;
            ByteBufferList.reclaim(byteBuffer);
        }
    }
    
    public interface HandshakeCallback
    {
        void onHandshakeCompleted(final Exception p0, final AsyncSSLSocket p1);
    }
}
