// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.zzab;
import com.google.android.gms.internal.zziy;

@zziy
public final class VideoController
{
    private final Object zzakd;
    @Nullable
    private zzab zzake;
    @Nullable
    private VideoLifecycleCallbacks zzakf;
    
    public VideoController() {
        this.zzakd = new Object();
    }
    
    public float getAspectRatio() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: fconst_0       
        //     1: fstore_1       
        //     2: aload_0        
        //     3: getfield        com/google/android/gms/ads/VideoController.zzakd:Ljava/lang/Object;
        //     6: astore_2       
        //     7: aload_2        
        //     8: monitorenter   
        //     9: aload_0        
        //    10: getfield        com/google/android/gms/ads/VideoController.zzake:Lcom/google/android/gms/ads/internal/client/zzab;
        //    13: ifnonnull       20
        //    16: aload_2        
        //    17: monitorexit    
        //    18: fload_1        
        //    19: freturn        
        //    20: aload_0        
        //    21: getfield        com/google/android/gms/ads/VideoController.zzake:Lcom/google/android/gms/ads/internal/client/zzab;
        //    24: invokeinterface com/google/android/gms/ads/internal/client/zzab.getAspectRatio:()F
        //    29: fstore          5
        //    31: fload           5
        //    33: fstore_1       
        //    34: aload_2        
        //    35: monitorexit    
        //    36: goto            18
        //    39: astore_3       
        //    40: aload_2        
        //    41: monitorexit    
        //    42: aload_3        
        //    43: athrow         
        //    44: astore          4
        //    46: ldc             "Unable to call getAspectRatio on video controller."
        //    48: aload           4
        //    50: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    53: aload_2        
        //    54: monitorexit    
        //    55: fconst_0       
        //    56: fstore_1       
        //    57: goto            18
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  9      18     39     44     Any
        //  20     31     44     60     Landroid/os/RemoteException;
        //  20     31     39     44     Any
        //  34     42     39     44     Any
        //  46     55     39     44     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0020:
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
    
    @Nullable
    public VideoLifecycleCallbacks getVideoLifecycleCallbacks() {
        synchronized (this.zzakd) {
            return this.zzakf;
        }
    }
    
    public boolean hasVideoContent() {
        while (true) {
            synchronized (this.zzakd) {
                if (this.zzake != null) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public void setVideoLifecycleCallbacks(final VideoLifecycleCallbacks p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ldc             "VideoLifecycleCallbacks may not be null."
        //     3: invokestatic    com/google/android/gms/common/internal/zzac.zzb:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //     6: pop            
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/VideoController.zzakd:Ljava/lang/Object;
        //    11: astore_3       
        //    12: aload_3        
        //    13: monitorenter   
        //    14: aload_0        
        //    15: aload_1        
        //    16: putfield        com/google/android/gms/ads/VideoController.zzakf:Lcom/google/android/gms/ads/VideoController$VideoLifecycleCallbacks;
        //    19: aload_0        
        //    20: getfield        com/google/android/gms/ads/VideoController.zzake:Lcom/google/android/gms/ads/internal/client/zzab;
        //    23: ifnonnull       29
        //    26: aload_3        
        //    27: monitorexit    
        //    28: return         
        //    29: aload_0        
        //    30: getfield        com/google/android/gms/ads/VideoController.zzake:Lcom/google/android/gms/ads/internal/client/zzab;
        //    33: new             Lcom/google/android/gms/ads/internal/client/zzap;
        //    36: dup            
        //    37: aload_1        
        //    38: invokespecial   com/google/android/gms/ads/internal/client/zzap.<init>:(Lcom/google/android/gms/ads/VideoController$VideoLifecycleCallbacks;)V
        //    41: invokeinterface com/google/android/gms/ads/internal/client/zzab.zza:(Lcom/google/android/gms/ads/internal/client/zzac;)V
        //    46: aload_3        
        //    47: monitorexit    
        //    48: goto            28
        //    51: astore          4
        //    53: aload_3        
        //    54: monitorexit    
        //    55: aload           4
        //    57: athrow         
        //    58: astore          5
        //    60: ldc             "Unable to call setVideoLifecycleCallbacks on video controller."
        //    62: aload           5
        //    64: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    67: goto            46
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  14     28     51     58     Any
        //  29     46     58     70     Landroid/os/RemoteException;
        //  29     46     51     58     Any
        //  46     55     51     58     Any
        //  60     67     51     58     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0029:
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
    
    public void zza(final zzab zzake) {
        synchronized (this.zzakd) {
            this.zzake = zzake;
            if (this.zzakf != null) {
                this.setVideoLifecycleCallbacks(this.zzakf);
            }
        }
    }
    
    public zzab zzdj() {
        synchronized (this.zzakd) {
            return this.zzake;
        }
    }
    
    public abstract static class VideoLifecycleCallbacks
    {
        public void onVideoEnd() {
        }
    }
}
