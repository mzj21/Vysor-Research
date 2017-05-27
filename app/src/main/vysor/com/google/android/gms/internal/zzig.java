// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.InAppPurchaseResult;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;

@zziy
public final class zzig extends zzib.zza
{
    private final PlayStorePurchaseListener zzaza;
    
    public zzig(final PlayStorePurchaseListener zzaza) {
        this.zzaza = zzaza;
    }
    
    public boolean isValidPurchase(final String s) {
        return this.zzaza.isValidPurchase(s);
    }
    
    public void zza(final zzia zzia) {
        this.zzaza.onInAppPurchaseFinished(new zzie(zzia));
    }
}
