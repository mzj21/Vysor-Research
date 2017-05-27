// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.purchase;

public interface InAppPurchase
{
    public static final int RESOLUTION_CANCELED = 2;
    public static final int RESOLUTION_FAILURE = 0;
    public static final int RESOLUTION_INVALID_PRODUCT = 3;
    public static final int RESOLUTION_SUCCESS = 1;
    
    String getProductId();
    
    void recordPlayBillingResolution(final int p0);
    
    void recordResolution(final int p0);
}
