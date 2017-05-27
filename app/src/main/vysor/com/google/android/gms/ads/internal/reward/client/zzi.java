// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.reward.client;

import android.os.RemoteException;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import android.content.Context;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.ads.reward.RewardedVideoAd;

@zziy
public class zzi implements RewardedVideoAd
{
    private final Context mContext;
    private final Object zzakd;
    private final zzb zzcmz;
    private RewardedVideoAdListener zzgf;
    
    public zzi(final Context mContext, final zzb zzcmz) {
        this.zzakd = new Object();
        this.zzcmz = zzcmz;
        this.mContext = mContext;
    }
    
    @Override
    public void destroy() {
        this.destroy(null);
    }
    
    @Override
    public void destroy(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/ads/internal/reward/client/zzi.zzakd:Ljava/lang/Object;
        //     4: astore_2       
        //     5: aload_2        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/internal/reward/client/zzi.zzcmz:Lcom/google/android/gms/ads/internal/reward/client/zzb;
        //    11: ifnonnull       17
        //    14: aload_2        
        //    15: monitorexit    
        //    16: return         
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/ads/internal/reward/client/zzi.zzcmz:Lcom/google/android/gms/ads/internal/reward/client/zzb;
        //    21: aload_1        
        //    22: invokestatic    com/google/android/gms/dynamic/zze.zzac:(Ljava/lang/Object;)Lcom/google/android/gms/dynamic/zzd;
        //    25: invokeinterface com/google/android/gms/ads/internal/reward/client/zzb.zzh:(Lcom/google/android/gms/dynamic/zzd;)V
        //    30: aload_2        
        //    31: monitorexit    
        //    32: goto            16
        //    35: astore_3       
        //    36: aload_2        
        //    37: monitorexit    
        //    38: aload_3        
        //    39: athrow         
        //    40: astore          4
        //    42: ldc             "Could not forward destroy to RewardedVideoAd"
        //    44: aload           4
        //    46: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    49: goto            30
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      16     35     40     Any
        //  17     30     40     52     Landroid/os/RemoteException;
        //  17     30     35     40     Any
        //  30     38     35     40     Any
        //  42     49     35     40     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0017:
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
    public RewardedVideoAdListener getRewardedVideoAdListener() {
        synchronized (this.zzakd) {
            return this.zzgf;
        }
    }
    
    @Override
    public String getUserId() {
        com.google.android.gms.ads.internal.util.client.zzb.zzdf("RewardedVideoAd.getUserId() is deprecated. Please do not call this method.");
        return null;
    }
    
    @Override
    public boolean isLoaded() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_1       
        //     2: aload_0        
        //     3: getfield        com/google/android/gms/ads/internal/reward/client/zzi.zzakd:Ljava/lang/Object;
        //     6: astore_2       
        //     7: aload_2        
        //     8: monitorenter   
        //     9: aload_0        
        //    10: getfield        com/google/android/gms/ads/internal/reward/client/zzi.zzcmz:Lcom/google/android/gms/ads/internal/reward/client/zzb;
        //    13: ifnonnull       20
        //    16: aload_2        
        //    17: monitorexit    
        //    18: iload_1        
        //    19: ireturn        
        //    20: aload_0        
        //    21: getfield        com/google/android/gms/ads/internal/reward/client/zzi.zzcmz:Lcom/google/android/gms/ads/internal/reward/client/zzb;
        //    24: invokeinterface com/google/android/gms/ads/internal/reward/client/zzb.isLoaded:()Z
        //    29: istore          5
        //    31: iload           5
        //    33: istore_1       
        //    34: aload_2        
        //    35: monitorexit    
        //    36: goto            18
        //    39: astore_3       
        //    40: aload_2        
        //    41: monitorexit    
        //    42: aload_3        
        //    43: athrow         
        //    44: astore          4
        //    46: ldc             "Could not forward isLoaded to RewardedVideoAd"
        //    48: aload           4
        //    50: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    53: aload_2        
        //    54: monitorexit    
        //    55: iconst_0       
        //    56: istore_1       
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
    
    @Override
    public void loadAd(final String p0, final AdRequest p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/ads/internal/reward/client/zzi.zzakd:Ljava/lang/Object;
        //     4: astore_3       
        //     5: aload_3        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/internal/reward/client/zzi.zzcmz:Lcom/google/android/gms/ads/internal/reward/client/zzb;
        //    11: ifnonnull       17
        //    14: aload_3        
        //    15: monitorexit    
        //    16: return         
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/ads/internal/reward/client/zzi.zzcmz:Lcom/google/android/gms/ads/internal/reward/client/zzb;
        //    21: invokestatic    com/google/android/gms/ads/internal/client/zzh.zzjb:()Lcom/google/android/gms/ads/internal/client/zzh;
        //    24: aload_0        
        //    25: getfield        com/google/android/gms/ads/internal/reward/client/zzi.mContext:Landroid/content/Context;
        //    28: aload_2        
        //    29: invokevirtual   com/google/android/gms/ads/AdRequest.zzdg:()Lcom/google/android/gms/ads/internal/client/zzad;
        //    32: aload_1        
        //    33: invokevirtual   com/google/android/gms/ads/internal/client/zzh.zza:(Landroid/content/Context;Lcom/google/android/gms/ads/internal/client/zzad;Ljava/lang/String;)Lcom/google/android/gms/ads/internal/reward/client/RewardedVideoAdRequestParcel;
        //    36: invokeinterface com/google/android/gms/ads/internal/reward/client/zzb.zza:(Lcom/google/android/gms/ads/internal/reward/client/RewardedVideoAdRequestParcel;)V
        //    41: aload_3        
        //    42: monitorexit    
        //    43: goto            16
        //    46: astore          4
        //    48: aload_3        
        //    49: monitorexit    
        //    50: aload           4
        //    52: athrow         
        //    53: astore          5
        //    55: ldc             "Could not forward loadAd to RewardedVideoAd"
        //    57: aload           5
        //    59: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    62: goto            41
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      16     46     53     Any
        //  17     41     53     65     Landroid/os/RemoteException;
        //  17     41     46     53     Any
        //  41     50     46     53     Any
        //  55     62     46     53     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0017:
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
    public void pause() {
        this.pause(null);
    }
    
    @Override
    public void pause(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/ads/internal/reward/client/zzi.zzakd:Ljava/lang/Object;
        //     4: astore_2       
        //     5: aload_2        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/internal/reward/client/zzi.zzcmz:Lcom/google/android/gms/ads/internal/reward/client/zzb;
        //    11: ifnonnull       17
        //    14: aload_2        
        //    15: monitorexit    
        //    16: return         
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/ads/internal/reward/client/zzi.zzcmz:Lcom/google/android/gms/ads/internal/reward/client/zzb;
        //    21: aload_1        
        //    22: invokestatic    com/google/android/gms/dynamic/zze.zzac:(Ljava/lang/Object;)Lcom/google/android/gms/dynamic/zzd;
        //    25: invokeinterface com/google/android/gms/ads/internal/reward/client/zzb.zzf:(Lcom/google/android/gms/dynamic/zzd;)V
        //    30: aload_2        
        //    31: monitorexit    
        //    32: goto            16
        //    35: astore_3       
        //    36: aload_2        
        //    37: monitorexit    
        //    38: aload_3        
        //    39: athrow         
        //    40: astore          4
        //    42: ldc             "Could not forward pause to RewardedVideoAd"
        //    44: aload           4
        //    46: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    49: goto            30
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      16     35     40     Any
        //  17     30     40     52     Landroid/os/RemoteException;
        //  17     30     35     40     Any
        //  30     38     35     40     Any
        //  42     49     35     40     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0017:
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
    public void resume() {
        this.resume(null);
    }
    
    @Override
    public void resume(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/ads/internal/reward/client/zzi.zzakd:Ljava/lang/Object;
        //     4: astore_2       
        //     5: aload_2        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/internal/reward/client/zzi.zzcmz:Lcom/google/android/gms/ads/internal/reward/client/zzb;
        //    11: ifnonnull       17
        //    14: aload_2        
        //    15: monitorexit    
        //    16: return         
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/ads/internal/reward/client/zzi.zzcmz:Lcom/google/android/gms/ads/internal/reward/client/zzb;
        //    21: aload_1        
        //    22: invokestatic    com/google/android/gms/dynamic/zze.zzac:(Ljava/lang/Object;)Lcom/google/android/gms/dynamic/zzd;
        //    25: invokeinterface com/google/android/gms/ads/internal/reward/client/zzb.zzg:(Lcom/google/android/gms/dynamic/zzd;)V
        //    30: aload_2        
        //    31: monitorexit    
        //    32: goto            16
        //    35: astore_3       
        //    36: aload_2        
        //    37: monitorexit    
        //    38: aload_3        
        //    39: athrow         
        //    40: astore          4
        //    42: ldc             "Could not forward resume to RewardedVideoAd"
        //    44: aload           4
        //    46: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    49: goto            30
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      16     35     40     Any
        //  17     30     40     52     Landroid/os/RemoteException;
        //  17     30     35     40     Any
        //  30     38     35     40     Any
        //  42     49     35     40     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0017:
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
    public void setRewardedVideoAdListener(final RewardedVideoAdListener zzgf) {
        synchronized (this.zzakd) {
            this.zzgf = zzgf;
            if (this.zzcmz == null) {
                return;
            }
            try {
                this.zzcmz.zza(new zzg(zzgf));
            }
            catch (RemoteException ex) {
                com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not forward setRewardedVideoAdListener to RewardedVideoAd", (Throwable)ex);
            }
        }
    }
    
    @Override
    public void setUserId(final String s) {
        com.google.android.gms.ads.internal.util.client.zzb.zzdf("RewardedVideoAd.setUserId() is deprecated. Please do not call this method.");
    }
    
    @Override
    public void show() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/ads/internal/reward/client/zzi.zzakd:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/internal/reward/client/zzi.zzcmz:Lcom/google/android/gms/ads/internal/reward/client/zzb;
        //    11: ifnonnull       17
        //    14: aload_1        
        //    15: monitorexit    
        //    16: return         
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/ads/internal/reward/client/zzi.zzcmz:Lcom/google/android/gms/ads/internal/reward/client/zzb;
        //    21: invokeinterface com/google/android/gms/ads/internal/reward/client/zzb.show:()V
        //    26: aload_1        
        //    27: monitorexit    
        //    28: goto            16
        //    31: astore_2       
        //    32: aload_1        
        //    33: monitorexit    
        //    34: aload_2        
        //    35: athrow         
        //    36: astore_3       
        //    37: ldc             "Could not forward show to RewardedVideoAd"
        //    39: aload_3        
        //    40: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    43: goto            26
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      16     31     36     Any
        //  17     26     36     46     Landroid/os/RemoteException;
        //  17     26     31     36     Any
        //  26     34     31     36     Any
        //  37     43     31     36     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0017:
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
}
