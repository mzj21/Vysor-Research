// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Context;

public class zzuy
{
    private zzuz Uv;
    private boolean zzaom;
    
    public zzuy() {
        this.zzaom = false;
        this.Uv = null;
    }
    
    public void initialize(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/google/android/gms/internal/zzuy.zzaom:Z
        //     6: ifeq            12
        //     9: aload_0        
        //    10: monitorexit    
        //    11: return         
        //    12: aload_0        
        //    13: aload_1        
        //    14: getstatic       com/google/android/gms/internal/zzsu.Oy:Lcom/google/android/gms/internal/zzsu$zzb;
        //    17: ldc             "com.google.android.gms.flags"
        //    19: invokestatic    com/google/android/gms/internal/zzsu.zza:(Landroid/content/Context;Lcom/google/android/gms/internal/zzsu$zzb;Ljava/lang/String;)Lcom/google/android/gms/internal/zzsu;
        //    22: ldc             "com.google.android.gms.flags.impl.FlagProviderImpl"
        //    24: invokevirtual   com/google/android/gms/internal/zzsu.zzjd:(Ljava/lang/String;)Landroid/os/IBinder;
        //    27: invokestatic    com/google/android/gms/internal/zzuz$zza.asInterface:(Landroid/os/IBinder;)Lcom/google/android/gms/internal/zzuz;
        //    30: putfield        com/google/android/gms/internal/zzuy.Uv:Lcom/google/android/gms/internal/zzuz;
        //    33: aload_0        
        //    34: getfield        com/google/android/gms/internal/zzuy.Uv:Lcom/google/android/gms/internal/zzuz;
        //    37: aload_1        
        //    38: invokestatic    com/google/android/gms/dynamic/zze.zzac:(Ljava/lang/Object;)Lcom/google/android/gms/dynamic/zzd;
        //    41: invokeinterface com/google/android/gms/internal/zzuz.init:(Lcom/google/android/gms/dynamic/zzd;)V
        //    46: aload_0        
        //    47: iconst_1       
        //    48: putfield        com/google/android/gms/internal/zzuy.zzaom:Z
        //    51: aload_0        
        //    52: monitorexit    
        //    53: goto            11
        //    56: astore_2       
        //    57: aload_0        
        //    58: monitorexit    
        //    59: aload_2        
        //    60: athrow         
        //    61: astore_3       
        //    62: ldc             "FlagValueProvider"
        //    64: ldc             "Failed to initialize flags module."
        //    66: aload_3        
        //    67: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    70: pop            
        //    71: goto            51
        //    74: astore_3       
        //    75: goto            62
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                      
        //  -----  -----  -----  -----  ------------------------------------------
        //  2      11     56     61     Any
        //  12     51     74     78     Lcom/google/android/gms/internal/zzsu$zza;
        //  12     51     61     62     Landroid/os/RemoteException;
        //  12     51     56     61     Any
        //  51     59     56     61     Any
        //  62     71     56     61     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0012:
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
    
    public <T> T zzb(final zzuw<T> zzuw) {
        T t;
        synchronized (this) {
            if (!this.zzaom) {
                t = zzuw.zzkq();
            }
            else {
                // monitorexit(this)
                t = zzuw.zza(this.Uv);
            }
        }
        return t;
    }
}
