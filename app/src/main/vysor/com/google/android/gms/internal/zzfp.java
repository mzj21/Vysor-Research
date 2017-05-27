// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.zzl;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.internal.client.zzw;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.zzq;

@zziy
class zzfp
{
    @Nullable
    zzq zzamy;
    @Nullable
    zzw zzboy;
    @Nullable
    zzhx zzboz;
    @Nullable
    zzdu zzbpa;
    @Nullable
    zzp zzbpb;
    @Nullable
    zzd zzbpc;
    
    void zzc(final zzl zzl) {
        if (this.zzamy != null) {
            zzl.zza(new zza(this.zzamy));
        }
        if (this.zzboy != null) {
            zzl.zza(this.zzboy);
        }
        if (this.zzboz != null) {
            zzl.zza(this.zzboz);
        }
        if (this.zzbpa != null) {
            zzl.zza(this.zzbpa);
        }
        if (this.zzbpb != null) {
            zzl.zza(this.zzbpb);
        }
        if (this.zzbpc != null) {
            zzl.zza(this.zzbpc);
        }
    }
    
    private static class zza extends zzq.zza
    {
        private final zzq zzbpd;
        
        zza(final zzq zzbpd) {
            this.zzbpd = zzbpd;
        }
        
        public void onAdClosed() throws RemoteException {
            this.zzbpd.onAdClosed();
            zzu.zzgo().zzmm();
        }
        
        public void onAdFailedToLoad(final int n) throws RemoteException {
            this.zzbpd.onAdFailedToLoad(n);
        }
        
        public void onAdLeftApplication() throws RemoteException {
            this.zzbpd.onAdLeftApplication();
        }
        
        public void onAdLoaded() throws RemoteException {
            this.zzbpd.onAdLoaded();
        }
        
        public void onAdOpened() throws RemoteException {
            this.zzbpd.onAdOpened();
        }
    }
}
