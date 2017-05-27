// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.purchase.InAppPurchase;

@zziy
public class zzif implements InAppPurchase
{
    private final zzhw zzcbu;
    
    public zzif(final zzhw zzcbu) {
        this.zzcbu = zzcbu;
    }
    
    @Override
    public String getProductId() {
        try {
            return this.zzcbu.getProductId();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not forward getProductId to InAppPurchase", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public void recordPlayBillingResolution(final int n) {
        try {
            this.zzcbu.recordPlayBillingResolution(n);
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not forward recordPlayBillingResolution to InAppPurchase", (Throwable)ex);
        }
    }
    
    @Override
    public void recordResolution(final int n) {
        try {
            this.zzcbu.recordResolution(n);
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not forward recordResolution to InAppPurchase", (Throwable)ex);
        }
    }
}
