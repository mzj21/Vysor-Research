// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation.customevent;

public interface CustomEventListener
{
    void onAdClicked();
    
    void onAdClosed();
    
    void onAdFailedToLoad(final int p0);
    
    void onAdLeftApplication();
    
    void onAdOpened();
}
