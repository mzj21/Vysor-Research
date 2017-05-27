// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation.customevent;

import android.view.View;

@Deprecated
public interface CustomEventBannerListener extends CustomEventListener
{
    void onClick();
    
    void onReceivedAd(final View p0);
}
