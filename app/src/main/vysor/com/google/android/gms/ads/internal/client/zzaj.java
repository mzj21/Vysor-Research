// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzej;
import com.google.android.gms.internal.zzek;
import com.google.android.gms.internal.zzei;
import com.google.android.gms.internal.zzeh;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;

public class zzaj extends zzs.zza
{
    private zzq zzamy;
    
    public void zza(final NativeAdOptionsParcel nativeAdOptionsParcel) throws RemoteException {
    }
    
    public void zza(final zzeh zzeh) throws RemoteException {
    }
    
    public void zza(final zzei zzei) throws RemoteException {
    }
    
    public void zza(final String s, final zzek zzek, final zzej zzej) throws RemoteException {
    }
    
    public void zzb(final zzq zzamy) throws RemoteException {
        this.zzamy = zzamy;
    }
    
    public void zzb(final zzy zzy) throws RemoteException {
    }
    
    public zzr zzey() throws RemoteException {
        return new zza();
    }
    
    private class zza extends zzr.zza
    {
        public String getMediationAdapterClassName() throws RemoteException {
            return null;
        }
        
        public boolean isLoading() throws RemoteException {
            return false;
        }
        
        public void zzf(final AdRequestParcel adRequestParcel) throws RemoteException {
            zzb.e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
            com.google.android.gms.ads.internal.util.client.zza.zzctj.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (zzaj.this.zzamy == null) {
                        return;
                    }
                    try {
                        zzaj.this.zzamy.onAdFailedToLoad(1);
                    }
                    catch (RemoteException ex) {
                        zzb.zzd("Could not notify onAdFailedToLoad event.", (Throwable)ex);
                    }
                }
            });
        }
    }
}
