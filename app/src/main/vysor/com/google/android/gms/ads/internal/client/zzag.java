// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.ads.internal.reward.client.zzi;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.internal.zzgp;
import android.content.Context;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.internal.zziy;

@zziy
public class zzag
{
    private static final Object zzaok;
    private static zzag zzazh;
    private zzz zzazi;
    private RewardedVideoAd zzazj;
    
    static {
        zzaok = new Object();
    }
    
    public static zzag zzki() {
        synchronized (zzag.zzaok) {
            if (zzag.zzazh == null) {
                zzag.zzazh = new zzag();
            }
            return zzag.zzazh;
        }
    }
    
    public RewardedVideoAd getRewardedVideoAdInstance(final Context context) {
        RewardedVideoAd rewardedVideoAd;
        synchronized (zzag.zzaok) {
            if (this.zzazj != null) {
                rewardedVideoAd = this.zzazj;
            }
            else {
                this.zzazj = new zzi(context, zzm.zzjs().zza(context, new zzgp()));
                rewardedVideoAd = this.zzazj;
            }
        }
        return rewardedVideoAd;
    }
    
    public void openDebugMenu(final Context context, final String s) {
        Label_0030: {
            if (this.zzazi == null) {
                break Label_0030;
            }
            boolean b = true;
            while (true) {
                zzac.zza(b, (Object)"MobileAds.initialize() must be called prior to opening debug menu.");
                try {
                    this.zzazi.zzb(zze.zzac(context), s);
                    return;
                    b = false;
                }
                catch (RemoteException ex) {
                    zzb.zzb("Unable to open debug menu.", (Throwable)ex);
                }
            }
        }
    }
    
    public void setAppMuted(final boolean appMuted) {
        Label_0026: {
            if (this.zzazi == null) {
                break Label_0026;
            }
            boolean b = true;
            while (true) {
                zzac.zza(b, (Object)"MobileAds.initialize() must be called prior to setting the app volume.");
                try {
                    this.zzazi.setAppMuted(appMuted);
                    return;
                    b = false;
                }
                catch (RemoteException ex) {
                    zzb.zzb("Unable to set app mute state.", (Throwable)ex);
                }
            }
        }
    }
    
    public void setAppVolume(final float appVolume) {
        boolean b = true;
        while (true) {
            while (true) {
                boolean b2 = false;
                Label_0016: {
                    if (0.0f <= appVolume && appVolume <= 1.0f) {
                        b2 = b;
                        break Label_0016;
                    }
                    Label_0046: {
                        break Label_0046;
                        while (true) {
                            zzac.zza(b, (Object)"MobileAds.initialize() must be called prior to setting the app volume.");
                            try {
                                this.zzazi.setAppVolume(appVolume);
                                return;
                                b = false;
                                continue;
                                b2 = false;
                                break;
                            }
                            catch (RemoteException ex) {
                                zzb.zzb("Unable to set app volume.", (Throwable)ex);
                                return;
                            }
                        }
                    }
                }
                zzac.zzb(b2, (Object)"The app volume must be a value between 0 and 1 inclusive.");
                if (this.zzazi != null) {
                    continue;
                }
                break;
            }
            continue;
        }
    }
    
    public void zza(final Context context, final String s, final zzah zzah) {
        synchronized (zzag.zzaok) {
            if (this.zzazi != null) {
                return;
            }
            if (context == null) {
                throw new IllegalArgumentException("Context cannot be null.");
            }
        }
        while (true) {
            try {
                (this.zzazi = zzm.zzjs().zzl(context)).initialize();
                if (s != null) {
                    this.zzazi.zzw(s);
                }
            }
            // monitorexit(o)
            catch (RemoteException ex) {
                zzb.zzd("Fail to initialize or set applicationCode on mobile ads setting manager", (Throwable)ex);
                continue;
            }
            break;
        }
    }
}
