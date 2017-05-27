// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.reward.client.RewardedVideoAdRequestParcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.reward.client.zzb;

public class zzan extends zzb.zza
{
    private zzd zzazn;
    
    public void destroy() throws RemoteException {
    }
    
    public boolean isLoaded() throws RemoteException {
        return false;
    }
    
    public void pause() throws RemoteException {
    }
    
    public void resume() throws RemoteException {
    }
    
    public void setUserId(final String s) throws RemoteException {
    }
    
    public void show() throws RemoteException {
    }
    
    public void zza(final RewardedVideoAdRequestParcel rewardedVideoAdRequestParcel) throws RemoteException {
        com.google.android.gms.ads.internal.util.client.zzb.e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zza.zzctj.post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (zzan.this.zzazn == null) {
                    return;
                }
                try {
                    zzan.this.zzazn.onRewardedVideoAdFailedToLoad(1);
                }
                catch (RemoteException ex) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not notify onRewardedVideoAdFailedToLoad event.", (Throwable)ex);
                }
            }
        });
    }
    
    public void zza(final zzd zzazn) throws RemoteException {
        this.zzazn = zzazn;
    }
    
    public void zzf(final com.google.android.gms.dynamic.zzd zzd) throws RemoteException {
    }
    
    public void zzg(final com.google.android.gms.dynamic.zzd zzd) throws RemoteException {
    }
    
    public void zzh(final com.google.android.gms.dynamic.zzd zzd) throws RemoteException {
    }
}
