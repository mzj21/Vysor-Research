// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import android.text.TextUtils;
import java.util.Map;

@zziy
public class zzfe implements zzev
{
    private final zza zzbnq;
    
    public zzfe(final zza zzbnq) {
        this.zzbnq = zzbnq;
    }
    
    public static void zza(final zzlt zzlt, final zza zza) {
        zzlt.zzvr().zza("/reward", new zzfe(zza));
    }
    
    private void zze(final Map<String, String> map) {
        while (true) {
            while (true) {
                try {
                    final int int1 = Integer.parseInt(map.get("amount"));
                    final String s = map.get("type");
                    if (!TextUtils.isEmpty((CharSequence)s)) {
                        final RewardItemParcel rewardItemParcel = new RewardItemParcel(s, int1);
                        this.zzbnq.zzb(rewardItemParcel);
                        return;
                    }
                }
                catch (NumberFormatException ex) {
                    zzb.zzd("Unable to parse reward amount.", ex);
                }
                final RewardItemParcel rewardItemParcel = null;
                continue;
            }
        }
    }
    
    private void zzf(final Map<String, String> map) {
        this.zzbnq.zzfb();
    }
    
    @Override
    public void zza(final zzlt zzlt, final Map<String, String> map) {
        final String s = map.get("action");
        if ("grant".equals(s)) {
            this.zze(map);
        }
        else if ("video_start".equals(s)) {
            this.zzf(map);
        }
    }
    
    public interface zza
    {
        void zzb(final RewardItemParcel p0);
        
        void zzfb();
    }
}
