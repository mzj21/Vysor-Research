// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.purchase;

public interface PlayStorePurchaseListener
{
    boolean isValidPurchase(final String p0);
    
    void onInAppPurchaseFinished(final InAppPurchaseResult p0);
}
