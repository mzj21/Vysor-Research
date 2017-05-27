// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzu;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CancellationException;

@zziy
public class zzlg<T> implements zzlj<T>
{
    private final Object zzakd;
    private boolean zzbto;
    private T zzctv;
    private Throwable zzctw;
    private boolean zzctx;
    private final zzlk zzcty;
    
    public zzlg() {
        this.zzakd = new Object();
        this.zzcty = new zzlk();
    }
    
    private boolean zzvh() {
        return this.zzctw != null || this.zzctx;
    }
    
    @Override
    public boolean cancel(final boolean b) {
        boolean b2 = false;
        if (b) {
            synchronized (this.zzakd) {
                if (this.zzvh()) {
                    // monitorexit(this.zzakd)
                    b2 = false;
                    return b2;
                }
            }
            this.zzbto = true;
            this.zzctx = true;
            this.zzakd.notifyAll();
            this.zzcty.zzvi();
            // monitorexit(o)
            b2 = true;
        }
        return b2;
    }
    
    @Override
    public T get() throws CancellationException, ExecutionException, InterruptedException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/internal/zzlg.zzakd:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: invokespecial   com/google/android/gms/internal/zzlg.zzvh:()Z
        //    11: istore_3       
        //    12: iload_3        
        //    13: ifne            23
        //    16: aload_0        
        //    17: getfield        com/google/android/gms/internal/zzlg.zzakd:Ljava/lang/Object;
        //    20: invokevirtual   java/lang/Object.wait:()V
        //    23: aload_0        
        //    24: getfield        com/google/android/gms/internal/zzlg.zzctw:Ljava/lang/Throwable;
        //    27: ifnull          52
        //    30: new             Ljava/util/concurrent/ExecutionException;
        //    33: dup            
        //    34: aload_0        
        //    35: getfield        com/google/android/gms/internal/zzlg.zzctw:Ljava/lang/Throwable;
        //    38: invokespecial   java/util/concurrent/ExecutionException.<init>:(Ljava/lang/Throwable;)V
        //    41: athrow         
        //    42: astore_2       
        //    43: aload_1        
        //    44: monitorexit    
        //    45: aload_2        
        //    46: athrow         
        //    47: astore          5
        //    49: aload           5
        //    51: athrow         
        //    52: aload_0        
        //    53: getfield        com/google/android/gms/internal/zzlg.zzbto:Z
        //    56: ifeq            69
        //    59: new             Ljava/util/concurrent/CancellationException;
        //    62: dup            
        //    63: ldc             "CallbackFuture was cancelled."
        //    65: invokespecial   java/util/concurrent/CancellationException.<init>:(Ljava/lang/String;)V
        //    68: athrow         
        //    69: aload_0        
        //    70: getfield        com/google/android/gms/internal/zzlg.zzctv:Ljava/lang/Object;
        //    73: astore          4
        //    75: aload_1        
        //    76: monitorexit    
        //    77: aload           4
        //    79: areturn        
        //    Exceptions:
        //  throws java.util.concurrent.CancellationException
        //  throws java.util.concurrent.ExecutionException
        //  throws java.lang.InterruptedException
        //    Signature:
        //  ()TT;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  7      12     42     47     Any
        //  16     23     47     52     Ljava/lang/InterruptedException;
        //  16     23     42     47     Any
        //  23     45     42     47     Any
        //  49     77     42     47     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0023:
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
    public T get(final long p0, final TimeUnit p1) throws CancellationException, ExecutionException, InterruptedException, TimeoutException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/internal/zzlg.zzakd:Ljava/lang/Object;
        //     4: astore          4
        //     6: aload           4
        //     8: monitorenter   
        //     9: aload_0        
        //    10: invokespecial   com/google/android/gms/internal/zzlg.zzvh:()Z
        //    13: istore          6
        //    15: iload           6
        //    17: ifne            43
        //    20: aload_3        
        //    21: lload_1        
        //    22: invokevirtual   java/util/concurrent/TimeUnit.toMillis:(J)J
        //    25: lstore          9
        //    27: lload           9
        //    29: lconst_0       
        //    30: lcmp           
        //    31: ifeq            43
        //    34: aload_0        
        //    35: getfield        com/google/android/gms/internal/zzlg.zzakd:Ljava/lang/Object;
        //    38: lload           9
        //    40: invokevirtual   java/lang/Object.wait:(J)V
        //    43: aload_0        
        //    44: getfield        com/google/android/gms/internal/zzlg.zzctw:Ljava/lang/Throwable;
        //    47: ifnull          75
        //    50: new             Ljava/util/concurrent/ExecutionException;
        //    53: dup            
        //    54: aload_0        
        //    55: getfield        com/google/android/gms/internal/zzlg.zzctw:Ljava/lang/Throwable;
        //    58: invokespecial   java/util/concurrent/ExecutionException.<init>:(Ljava/lang/Throwable;)V
        //    61: athrow         
        //    62: astore          5
        //    64: aload           4
        //    66: monitorexit    
        //    67: aload           5
        //    69: athrow         
        //    70: astore          8
        //    72: aload           8
        //    74: athrow         
        //    75: aload_0        
        //    76: getfield        com/google/android/gms/internal/zzlg.zzctx:Z
        //    79: ifne            92
        //    82: new             Ljava/util/concurrent/TimeoutException;
        //    85: dup            
        //    86: ldc             "CallbackFuture timed out."
        //    88: invokespecial   java/util/concurrent/TimeoutException.<init>:(Ljava/lang/String;)V
        //    91: athrow         
        //    92: aload_0        
        //    93: getfield        com/google/android/gms/internal/zzlg.zzbto:Z
        //    96: ifeq            109
        //    99: new             Ljava/util/concurrent/CancellationException;
        //   102: dup            
        //   103: ldc             "CallbackFuture was cancelled."
        //   105: invokespecial   java/util/concurrent/CancellationException.<init>:(Ljava/lang/String;)V
        //   108: athrow         
        //   109: aload_0        
        //   110: getfield        com/google/android/gms/internal/zzlg.zzctv:Ljava/lang/Object;
        //   113: astore          7
        //   115: aload           4
        //   117: monitorexit    
        //   118: aload           7
        //   120: areturn        
        //    Exceptions:
        //  throws java.util.concurrent.CancellationException
        //  throws java.util.concurrent.ExecutionException
        //  throws java.lang.InterruptedException
        //  throws java.util.concurrent.TimeoutException
        //    Signature:
        //  (JLjava/util/concurrent/TimeUnit;)TT;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  9      15     62     70     Any
        //  20     43     70     75     Ljava/lang/InterruptedException;
        //  20     43     62     70     Any
        //  43     67     62     70     Any
        //  72     118    62     70     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0043:
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
    public boolean isCancelled() {
        synchronized (this.zzakd) {
            return this.zzbto;
        }
    }
    
    @Override
    public boolean isDone() {
        synchronized (this.zzakd) {
            return this.zzvh();
        }
    }
    
    @Override
    public void zzc(final Runnable runnable) {
        this.zzcty.zzc(runnable);
    }
    
    public void zzd(final Runnable runnable) {
        this.zzcty.zzd(runnable);
    }
    
    public void zze(final Throwable zzctw) {
        synchronized (this.zzakd) {
            if (this.zzbto) {
                return;
            }
            if (this.zzvh()) {
                zzu.zzgd().zza(new IllegalStateException("Provided CallbackFuture with multiple values."), "CallbackFuture.provideException");
                return;
            }
        }
        this.zzctw = zzctw;
        this.zzakd.notifyAll();
        this.zzcty.zzvi();
    }
    // monitorexit(o)
    
    public void zzh(@Nullable final T zzctv) {
        synchronized (this.zzakd) {
            if (this.zzbto) {
                return;
            }
            if (this.zzvh()) {
                zzu.zzgd().zza(new IllegalStateException("Provided CallbackFuture with multiple values."), "CallbackFuture.provideValue");
                return;
            }
        }
        this.zzctx = true;
        this.zzctv = zzctv;
        this.zzakd.notifyAll();
        this.zzcty.zzvi();
    }
    // monitorexit(o)
}
