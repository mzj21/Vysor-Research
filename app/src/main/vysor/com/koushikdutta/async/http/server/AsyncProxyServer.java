// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.server;

import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.http.AsyncHttpClient;

public class AsyncProxyServer extends AsyncHttpServer
{
    AsyncHttpClient proxyClient;
    
    public AsyncProxyServer(final AsyncServer asyncServer) {
        this.proxyClient = new AsyncHttpClient(asyncServer);
    }
    
    @Override
    protected void onRequest(final HttpServerRequestCallback p0, final AsyncHttpServerRequest p1, final AsyncHttpServerResponse p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: aload_2        
        //     3: aload_3        
        //     4: invokespecial   com/koushikdutta/async/http/server/AsyncHttpServer.onRequest:(Lcom/koushikdutta/async/http/server/HttpServerRequestCallback;Lcom/koushikdutta/async/http/server/AsyncHttpServerRequest;Lcom/koushikdutta/async/http/server/AsyncHttpServerResponse;)V
        //     7: aload_1        
        //     8: ifnull          12
        //    11: return         
        //    12: aload_2        
        //    13: invokeinterface com/koushikdutta/async/http/server/AsyncHttpServerRequest.getPath:()Ljava/lang/String;
        //    18: invokestatic    android/net/Uri.parse:(Ljava/lang/String;)Landroid/net/Uri;
        //    21: astore          9
        //    23: aload           9
        //    25: invokevirtual   android/net/Uri.getScheme:()Ljava/lang/String;
        //    28: ifnonnull       141
        //    31: new             Ljava/lang/Exception;
        //    34: dup            
        //    35: ldc             "no host or full uri provided"
        //    37: invokespecial   java/lang/Exception.<init>:(Ljava/lang/String;)V
        //    40: athrow         
        //    41: astore          4
        //    43: aload_2        
        //    44: invokeinterface com/koushikdutta/async/http/server/AsyncHttpServerRequest.getHeaders:()Lcom/koushikdutta/async/http/Headers;
        //    49: ldc             "Host"
        //    51: invokevirtual   com/koushikdutta/async/http/Headers.get:(Ljava/lang/String;)Ljava/lang/String;
        //    54: astore          7
        //    56: bipush          80
        //    58: istore          8
        //    60: aload           7
        //    62: ifnull          97
        //    65: aload           7
        //    67: ldc             ":"
        //    69: iconst_2       
        //    70: invokevirtual   java/lang/String.split:(Ljava/lang/String;I)[Ljava/lang/String;
        //    73: astore          11
        //    75: aload           11
        //    77: arraylength    
        //    78: iconst_2       
        //    79: if_icmpne       97
        //    82: aload           11
        //    84: iconst_0       
        //    85: aaload         
        //    86: astore          7
        //    88: aload           11
        //    90: iconst_1       
        //    91: aaload         
        //    92: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //    95: istore          8
        //    97: new             Ljava/lang/StringBuilder;
        //   100: dup            
        //   101: invokespecial   java/lang/StringBuilder.<init>:()V
        //   104: ldc             "http://"
        //   106: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   109: aload           7
        //   111: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   114: ldc             ":"
        //   116: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   119: iload           8
        //   121: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   124: aload_2        
        //   125: invokeinterface com/koushikdutta/async/http/server/AsyncHttpServerRequest.getPath:()Ljava/lang/String;
        //   130: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   133: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   136: invokestatic    android/net/Uri.parse:(Ljava/lang/String;)Landroid/net/Uri;
        //   139: astore          9
        //   141: aload_0        
        //   142: getfield        com/koushikdutta/async/http/server/AsyncProxyServer.proxyClient:Lcom/koushikdutta/async/http/AsyncHttpClient;
        //   145: new             Lcom/koushikdutta/async/http/AsyncHttpRequest;
        //   148: dup            
        //   149: aload           9
        //   151: aload_2        
        //   152: invokeinterface com/koushikdutta/async/http/server/AsyncHttpServerRequest.getMethod:()Ljava/lang/String;
        //   157: aload_2        
        //   158: invokeinterface com/koushikdutta/async/http/server/AsyncHttpServerRequest.getHeaders:()Lcom/koushikdutta/async/http/Headers;
        //   163: invokespecial   com/koushikdutta/async/http/AsyncHttpRequest.<init>:(Landroid/net/Uri;Ljava/lang/String;Lcom/koushikdutta/async/http/Headers;)V
        //   166: new             Lcom/koushikdutta/async/http/server/AsyncProxyServer$1;
        //   169: dup            
        //   170: aload_0        
        //   171: aload_3        
        //   172: invokespecial   com/koushikdutta/async/http/server/AsyncProxyServer$1.<init>:(Lcom/koushikdutta/async/http/server/AsyncProxyServer;Lcom/koushikdutta/async/http/server/AsyncHttpServerResponse;)V
        //   175: invokevirtual   com/koushikdutta/async/http/AsyncHttpClient.execute:(Lcom/koushikdutta/async/http/AsyncHttpRequest;Lcom/koushikdutta/async/http/callback/HttpConnectCallback;)Lcom/koushikdutta/async/future/Future;
        //   178: pop            
        //   179: goto            11
        //   182: astore          5
        //   184: aload_3        
        //   185: sipush          500
        //   188: invokeinterface com/koushikdutta/async/http/server/AsyncHttpServerResponse.code:(I)Lcom/koushikdutta/async/http/server/AsyncHttpServerResponse;
        //   193: pop            
        //   194: aload_3        
        //   195: aload           5
        //   197: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   200: invokeinterface com/koushikdutta/async/http/server/AsyncHttpServerResponse.send:(Ljava/lang/String;)V
        //   205: goto            11
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  12     41     41     141    Ljava/lang/Exception;
        //  43     179    182    208    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0097:
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
    
    @Override
    protected boolean onRequest(final AsyncHttpServerRequest asyncHttpServerRequest, final AsyncHttpServerResponse asyncHttpServerResponse) {
        return true;
    }
}
