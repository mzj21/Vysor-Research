// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.reward.client;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.ads.reward.RewardItem;

@zziy
public class zze implements RewardItem
{
    private final zza zzcmy;
    
    public zze(final zza zzcmy) {
        this.zzcmy = zzcmy;
    }
    
    @Override
    public int getAmount() {
        final zza zzcmy = this.zzcmy;
        int amount = 0;
        if (zzcmy != null) {
            try {
                amount = this.zzcmy.getAmount();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not forward getAmount to RewardItem", (Throwable)ex);
                amount = 0;
            }
        }
        return amount;
    }
    
    @Override
    public String getType() {
        final zza zzcmy = this.zzcmy;
        String type = null;
        if (zzcmy != null) {
            try {
                type = this.zzcmy.getType();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not forward getType to RewardItem", (Throwable)ex);
                type = null;
            }
        }
        return type;
    }
}
