// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzib;
import com.google.android.gms.internal.zzhx;
import com.google.android.gms.internal.zzdu;
import com.google.android.gms.ads.internal.reward.client.zzd;

public class zzak extends zzu.zza
{
    private zzq zzamy;
    
    public void destroy() {
    }
    
    public String getMediationAdapterClassName() {
        return null;
    }
    
    public boolean isLoading() {
        return false;
    }
    
    public boolean isReady() {
        return false;
    }
    
    public void pause() {
    }
    
    public void resume() {
    }
    
    public void setManualImpressionsEnabled(final boolean b) {
    }
    
    public void setUserId(final String s) {
    }
    
    public void showInterstitial() {
    }
    
    public void stopLoading() {
    }
    
    public void zza(final AdSizeParcel adSizeParcel) {
    }
    
    public void zza(final VideoOptionsParcel videoOptionsParcel) {
    }
    
    public void zza(final zzp zzp) {
    }
    
    public void zza(final zzq zzamy) {
        this.zzamy = zzamy;
    }
    
    public void zza(final zzw zzw) {
    }
    
    public void zza(final zzy zzy) {
    }
    
    public void zza(final zzd zzd) {
    }
    
    public void zza(final zzdu zzdu) {
    }
    
    public void zza(final zzhx zzhx) {
    }
    
    public void zza(final zzib zzib, final String s) {
    }
    
    public boolean zzb(final AdRequestParcel adRequestParcel) {
        zzb.e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zza.zzctj.post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (zzak.this.zzamy == null) {
                    return;
                }
                try {
                    zzak.this.zzamy.onAdFailedToLoad(1);
                }
                catch (RemoteException ex) {
                    zzb.zzd("Could not notify onAdFailedToLoad event.", (Throwable)ex);
                }
            }
        });
        return false;
    }
    
    public com.google.android.gms.dynamic.zzd zzds() {
        return null;
    }
    
    public AdSizeParcel zzdt() {
        return null;
    }
    
    public void zzdv() {
    }
    
    public zzab zzdw() {
        return null;
    }
}
