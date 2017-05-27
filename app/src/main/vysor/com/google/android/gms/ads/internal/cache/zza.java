// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.cache;

import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzct;
import com.google.android.gms.internal.zzdi;
import android.os.Binder;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.ads.internal.zzu;
import android.os.DeadObjectException;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.os.Bundle;
import com.google.android.gms.common.internal.zze;
import android.support.annotation.Nullable;
import android.content.Context;
import com.google.android.gms.internal.zziy;

@zziy
public class zza
{
    @Nullable
    private Context mContext;
    private final Object zzakd;
    private final Runnable zzavq;
    @Nullable
    private zzc zzavr;
    @Nullable
    private zzf zzavs;
    
    public zza() {
        this.zzavq = new Runnable() {
            @Override
            public void run() {
                zza.this.disconnect();
            }
        };
        this.zzakd = new Object();
    }
    
    private void connect() {
        synchronized (this.zzakd) {
            if (this.mContext != null && this.zzavr == null) {
                (this.zzavr = this.zza(new zze.zzb() {
                    @Override
                    public void onConnected(@Nullable final Bundle bundle) {
                        synchronized (zza.this.zzakd) {
                            while (true) {
                                try {
                                    zza.this.zzavs = zza.this.zzavr.zziz();
                                    zza.this.zzakd.notifyAll();
                                }
                                catch (DeadObjectException ex) {
                                    com.google.android.gms.ads.internal.util.client.zzb.zzb("Unable to obtain a cache service instance.", (Throwable)ex);
                                    zza.this.disconnect();
                                    continue;
                                }
                                break;
                            }
                        }
                    }
                    
                    @Override
                    public void onConnectionSuspended(final int n) {
                        synchronized (zza.this.zzakd) {
                            zza.this.zzavr = null;
                            zza.this.zzavs = null;
                            zza.this.zzakd.notifyAll();
                            zzu.zzgp().zzuz();
                        }
                    }
                }, new zze.zzc() {
                    @Override
                    public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
                        synchronized (zza.this.zzakd) {
                            zza.this.zzavr = null;
                            zza.this.zzavs = null;
                            zza.this.zzakd.notifyAll();
                            zzu.zzgp().zzuz();
                        }
                    }
                })).zzatu();
            }
        }
    }
    
    private void disconnect() {
        synchronized (this.zzakd) {
            if (this.zzavr != null) {
                if (this.zzavr.isConnected() || this.zzavr.isConnecting()) {
                    this.zzavr.disconnect();
                }
                this.zzavr = null;
                this.zzavs = null;
                Binder.flushPendingCommands();
                zzu.zzgp().zzuz();
            }
        }
    }
    
    public void initialize(final Context context) {
        if (context != null) {
            synchronized (this.zzakd) {
                if (this.mContext != null) {
                    return;
                }
            }
            this.mContext = context.getApplicationContext();
            if (zzdi.zzbhl.get()) {
                this.connect();
            }
            else if (zzdi.zzbhk.get()) {
                this.zza(new zzct.zzb() {
                    @Override
                    public void zzk(final boolean b) {
                        if (b) {
                            zza.this.connect();
                        }
                        else {
                            zza.this.disconnect();
                        }
                    }
                });
            }
        }
        // monitorexit(o)
    }
    
    public CacheEntryParcel zza(final CacheOffering p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/ads/internal/cache/zza.zzakd:Ljava/lang/Object;
        //     4: astore_2       
        //     5: aload_2        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/internal/cache/zza.zzavs:Lcom/google/android/gms/ads/internal/cache/zzf;
        //    11: ifnonnull       28
        //    14: new             Lcom/google/android/gms/ads/internal/cache/CacheEntryParcel;
        //    17: dup            
        //    18: invokespecial   com/google/android/gms/ads/internal/cache/CacheEntryParcel.<init>:()V
        //    21: astore          4
        //    23: aload_2        
        //    24: monitorexit    
        //    25: aload           4
        //    27: areturn        
        //    28: aload_0        
        //    29: getfield        com/google/android/gms/ads/internal/cache/zza.zzavs:Lcom/google/android/gms/ads/internal/cache/zzf;
        //    32: aload_1        
        //    33: invokeinterface com/google/android/gms/ads/internal/cache/zzf.zza:(Lcom/google/android/gms/ads/internal/cache/CacheOffering;)Lcom/google/android/gms/ads/internal/cache/CacheEntryParcel;
        //    38: astore          6
        //    40: aload           6
        //    42: astore          4
        //    44: aload_2        
        //    45: monitorexit    
        //    46: goto            25
        //    49: astore_3       
        //    50: aload_2        
        //    51: monitorexit    
        //    52: aload_3        
        //    53: athrow         
        //    54: astore          5
        //    56: ldc             "Unable to call into cache service."
        //    58: aload           5
        //    60: invokestatic    com/google/android/gms/internal/zzkn.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    63: new             Lcom/google/android/gms/ads/internal/cache/CacheEntryParcel;
        //    66: dup            
        //    67: invokespecial   com/google/android/gms/ads/internal/cache/CacheEntryParcel.<init>:()V
        //    70: astore          4
        //    72: aload_2        
        //    73: monitorexit    
        //    74: goto            25
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      25     49     54     Any
        //  28     40     54     77     Landroid/os/RemoteException;
        //  28     40     49     54     Any
        //  44     52     49     54     Any
        //  56     74     49     54     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
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
    
    protected zzc zza(final zze.zzb zzb, final zze.zzc zzc) {
        return new zzc(this.mContext, zzu.zzgp().zzuy(), zzb, zzc);
    }
    
    protected void zza(final zzct.zzb zzb) {
        zzu.zzgc().zza(zzb);
    }
    
    public void zzit() {
        if (zzdi.zzbhm.get()) {
            synchronized (this.zzakd) {
                this.connect();
                zzu.zzfz();
                zzkr.zzcrf.removeCallbacks(this.zzavq);
                zzu.zzfz();
                zzkr.zzcrf.postDelayed(this.zzavq, (long)zzdi.zzbhn.get());
            }
        }
    }
}
