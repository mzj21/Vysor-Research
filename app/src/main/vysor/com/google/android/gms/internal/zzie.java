// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.purchase.InAppPurchaseResult;

@zziy
public class zzie implements InAppPurchaseResult
{
    private final zzia zzcci;
    
    public zzie(final zzia zzcci) {
        this.zzcci = zzcci;
    }
    
    @Override
    public void finishPurchase() {
        try {
            this.zzcci.finishPurchase();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not forward finishPurchase to InAppPurchaseResult", (Throwable)ex);
        }
    }
    
    @Override
    public String getProductId() {
        try {
            return this.zzcci.getProductId();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not forward getProductId to InAppPurchaseResult", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public Intent getPurchaseData() {
        try {
            return this.zzcci.getPurchaseData();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not forward getPurchaseData to InAppPurchaseResult", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public int getResultCode() {
        try {
            return this.zzcci.getResultCode();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not forward getPurchaseData to InAppPurchaseResult", (Throwable)ex);
            return 0;
        }
    }
    
    @Override
    public boolean isVerified() {
        try {
            return this.zzcci.isVerified();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not forward isVerified to InAppPurchaseResult", (Throwable)ex);
            return false;
        }
    }
}
