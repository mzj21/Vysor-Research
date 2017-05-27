// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.InAppPurchase;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;

@zziy
public final class zzic extends zzhx.zza
{
    private final InAppPurchaseListener zzayy;
    
    public zzic(final InAppPurchaseListener zzayy) {
        this.zzayy = zzayy;
    }
    
    public void zza(final zzhw zzhw) {
        this.zzayy.onInAppPurchaseRequested(new zzif(zzhw));
    }
}
