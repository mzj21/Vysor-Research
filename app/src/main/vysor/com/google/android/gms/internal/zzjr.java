// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.formats.zzi;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.reward.client.RewardedVideoAdRequestParcel;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.dynamic.zze;
import android.support.annotation.NonNull;
import java.util.Iterator;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzac;
import org.json.JSONException;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import org.json.JSONObject;
import java.util.HashMap;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.zzd;
import android.content.Context;
import java.util.Map;
import com.google.android.gms.ads.internal.zzb;

@zziy
public class zzjr extends zzb implements zzjv
{
    private static final zzgp zzcmu;
    private final Map<String, zzjz> zzcmv;
    private boolean zzcmw;
    
    static {
        zzcmu = new zzgp();
    }
    
    public zzjr(final Context context, final zzd zzd, final AdSizeParcel adSizeParcel, final zzgq zzgq, final VersionInfoParcel versionInfoParcel) {
        super(context, adSizeParcel, null, zzgq, versionInfoParcel, zzd);
        this.zzcmv = new HashMap<String, zzjz>();
    }
    
    private zzke.zza zzd(final zzke.zza zza) {
        zzkn.v("Creating mediation ad response for non-mediated rewarded ad.");
        try {
            final String string = zzjc.zzc(zza.zzcop).toString();
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("pubid", (Object)zza.zzcix.zzaqt);
            final Object zze = new zzke.zza(zza.zzcix, zza.zzcop, new zzgh(Arrays.asList(new zzgg(string, null, Arrays.asList("com.google.ads.mediation.admob.AdMobAdapter"), null, null, Collections.emptyList(), Collections.emptyList(), jsonObject.toString(), null, Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList())), -1L, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), false, "", -1L, 0, 1, null, 0, -1, -1L, false), zza.zzaqz, zza.errorCode, zza.zzcoj, zza.zzcok, zza.zzcod);
            return (zzke.zza)zze;
        }
        catch (JSONException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzb("Unable to generate ad state for non-mediated rewarded video.", (Throwable)ex);
            final Object zze = this.zze(zza);
            return (zzke.zza)zze;
        }
    }
    
    private zzke.zza zze(final zzke.zza zza) {
        return new zzke.zza(zza.zzcix, zza.zzcop, null, zza.zzaqz, 0, zza.zzcoj, zza.zzcok, zza.zzcod);
    }
    
    @Override
    public void destroy() {
        zzac.zzhq("destroy must be called on the main UI thread.");
        for (final String s : this.zzcmv.keySet()) {
            try {
                final zzjz zzjz = this.zzcmv.get(s);
                if (zzjz != null && zzjz.zzsv() != null) {
                    zzjz.zzsv().destroy();
                    continue;
                }
                continue;
            }
            catch (RemoteException ex) {
                final String value = String.valueOf(s);
                String concat;
                if (value.length() != 0) {
                    concat = "Fail to destroy adapter: ".concat(value);
                }
                else {
                    concat = new String("Fail to destroy adapter: ");
                }
                com.google.android.gms.ads.internal.util.client.zzb.zzdf(concat);
                continue;
            }
            break;
        }
    }
    
    public boolean isLoaded() {
        zzac.zzhq("isLoaded must be called on the main UI thread.");
        return this.zzall.zzaqx == null && this.zzall.zzaqy == null && this.zzall.zzara != null && !this.zzcmw;
    }
    
    public void onContextChanged(@NonNull final Context context) {
        for (final zzjz zzjz : this.zzcmv.values()) {
            try {
                zzjz.zzsv().zzj(zze.zzac(context));
            }
            catch (RemoteException ex) {
                com.google.android.gms.ads.internal.util.client.zzb.zzb("Unable to call Adapter.onContextChanged.", (Throwable)ex);
            }
        }
    }
    
    @Override
    public void onRewardedVideoAdClosed() {
        this.zzdx();
    }
    
    @Override
    public void onRewardedVideoAdLeftApplication() {
        this.zzdy();
    }
    
    @Override
    public void onRewardedVideoAdOpened() {
        this.zza(this.zzall.zzara, false);
        this.zzdz();
    }
    
    @Override
    public void onRewardedVideoStarted() {
        if (this.zzall.zzara != null && this.zzall.zzara.zzbte != null) {
            com.google.android.gms.ads.internal.zzu.zzgs().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, this.zzall.zzara, this.zzall.zzaqt, false, this.zzall.zzara.zzbte.zzbru);
        }
        this.zzeb();
    }
    
    @Override
    public void pause() {
        zzac.zzhq("pause must be called on the main UI thread.");
        for (final String s : this.zzcmv.keySet()) {
            try {
                final zzjz zzjz = this.zzcmv.get(s);
                if (zzjz != null && zzjz.zzsv() != null) {
                    zzjz.zzsv().pause();
                    continue;
                }
                continue;
            }
            catch (RemoteException ex) {
                final String value = String.valueOf(s);
                String concat;
                if (value.length() != 0) {
                    concat = "Fail to pause adapter: ".concat(value);
                }
                else {
                    concat = new String("Fail to pause adapter: ");
                }
                com.google.android.gms.ads.internal.util.client.zzb.zzdf(concat);
                continue;
            }
            break;
        }
    }
    
    @Override
    public void resume() {
        zzac.zzhq("resume must be called on the main UI thread.");
        for (final String s : this.zzcmv.keySet()) {
            try {
                final zzjz zzjz = this.zzcmv.get(s);
                if (zzjz != null && zzjz.zzsv() != null) {
                    zzjz.zzsv().resume();
                    continue;
                }
                continue;
            }
            catch (RemoteException ex) {
                final String value = String.valueOf(s);
                String concat;
                if (value.length() != 0) {
                    concat = "Fail to resume adapter: ".concat(value);
                }
                else {
                    concat = new String("Fail to resume adapter: ");
                }
                com.google.android.gms.ads.internal.util.client.zzb.zzdf(concat);
                continue;
            }
            break;
        }
    }
    
    public void zza(final RewardedVideoAdRequestParcel rewardedVideoAdRequestParcel) {
        zzac.zzhq("loadAd must be called on the main UI thread.");
        if (TextUtils.isEmpty((CharSequence)rewardedVideoAdRequestParcel.zzaqt)) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("Invalid ad unit id. Aborting.");
            zzkr.zzcrf.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    zzjr.this.zzh(1);
                }
            });
        }
        else {
            this.zzcmw = false;
            this.zzall.zzaqt = rewardedVideoAdRequestParcel.zzaqt;
            super.zzb(rewardedVideoAdRequestParcel.zzcfu);
        }
    }
    
    public void zza(final zzke.zza zzarb, final zzdq zzdq) {
        if (zzarb.errorCode != -2) {
            zzkr.zzcrf.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    zzjr.this.zzb(new zzke(zzarb, null, null, null, null, null, null, null));
                }
            });
        }
        else {
            this.zzall.zzarb = zzarb;
            if (zzarb.zzcof == null) {
                this.zzall.zzarb = this.zzd(zzarb);
            }
            this.zzall.zzarv = 0;
            this.zzall.zzaqy = com.google.android.gms.ads.internal.zzu.zzfy().zza(this.zzall.zzahn, this.zzall.zzarb, this);
        }
    }
    
    @Override
    protected boolean zza(final AdRequestParcel adRequestParcel, final zzke zzke, final boolean b) {
        return false;
    }
    
    public boolean zza(final zzke zzke, final zzke zzke2) {
        return true;
    }
    
    @Override
    public void zzc(@Nullable RewardItemParcel rewardItemParcel) {
        if (this.zzall.zzara != null && this.zzall.zzara.zzbte != null) {
            com.google.android.gms.ads.internal.zzu.zzgs().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, this.zzall.zzara, this.zzall.zzaqt, false, this.zzall.zzara.zzbte.zzbrv);
        }
        if (this.zzall.zzara != null && this.zzall.zzara.zzcof != null && !TextUtils.isEmpty((CharSequence)this.zzall.zzara.zzcof.zzbsk)) {
            rewardItemParcel = new RewardItemParcel(this.zzall.zzara.zzcof.zzbsk, this.zzall.zzara.zzcof.zzbsl);
        }
        this.zza(rewardItemParcel);
    }
    
    @Nullable
    public zzjz zzcl(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/internal/zzjr.zzcmv:Ljava/util/Map;
        //     4: aload_1        
        //     5: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    10: checkcast       Lcom/google/android/gms/internal/zzjz;
        //    13: astore_2       
        //    14: aload_2        
        //    15: ifnonnull       72
        //    18: aload_0        
        //    19: getfield        com/google/android/gms/internal/zzjr.zzals:Lcom/google/android/gms/internal/zzgq;
        //    22: astore          8
        //    24: ldc             "com.google.ads.mediation.admob.AdMobAdapter"
        //    26: aload_1        
        //    27: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    30: ifeq            142
        //    33: getstatic       com/google/android/gms/internal/zzjr.zzcmu:Lcom/google/android/gms/internal/zzgp;
        //    36: astore          9
        //    38: new             Lcom/google/android/gms/internal/zzjz;
        //    41: dup            
        //    42: aload           9
        //    44: aload_1        
        //    45: invokeinterface com/google/android/gms/internal/zzgq.zzbq:(Ljava/lang/String;)Lcom/google/android/gms/internal/zzgr;
        //    50: aload_0        
        //    51: invokespecial   com/google/android/gms/internal/zzjz.<init>:(Lcom/google/android/gms/internal/zzgr;Lcom/google/android/gms/internal/zzjv;)V
        //    54: astore          5
        //    56: aload_0        
        //    57: getfield        com/google/android/gms/internal/zzjr.zzcmv:Ljava/util/Map;
        //    60: aload_1        
        //    61: aload           5
        //    63: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    68: pop            
        //    69: aload           5
        //    71: astore_2       
        //    72: aload_2        
        //    73: areturn        
        //    74: astore_3       
        //    75: aload_3        
        //    76: astore          4
        //    78: aload_2        
        //    79: astore          5
        //    81: aload_1        
        //    82: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    85: astore          6
        //    87: aload           6
        //    89: invokevirtual   java/lang/String.length:()I
        //    92: ifeq            118
        //    95: ldc_w           "Fail to instantiate adapter "
        //    98: aload           6
        //   100: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   103: astore          7
        //   105: aload           7
        //   107: aload           4
        //   109: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   112: aload           5
        //   114: astore_2       
        //   115: goto            72
        //   118: new             Ljava/lang/String;
        //   121: dup            
        //   122: ldc_w           "Fail to instantiate adapter "
        //   125: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   128: astore          7
        //   130: goto            105
        //   133: astore          10
        //   135: aload           10
        //   137: astore          4
        //   139: goto            81
        //   142: aload           8
        //   144: astore          9
        //   146: goto            38
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  18     56     74     81     Ljava/lang/Exception;
        //  56     69     133    142    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0072:
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
    
    public void zzsn() {
        zzac.zzhq("showAd must be called on the main UI thread.");
        if (!this.isLoaded()) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("The reward video has not loaded.");
        }
        else {
            this.zzcmw = true;
            final zzjz zzcl = this.zzcl(this.zzall.zzara.zzbtg);
            if (zzcl != null && zzcl.zzsv() != null) {
                try {
                    zzcl.zzsv().showVideo();
                }
                catch (RemoteException ex) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call showVideo.", (Throwable)ex);
                }
            }
        }
    }
    
    @Override
    public void zzso() {
        this.onAdClicked();
    }
}
