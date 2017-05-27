// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation.customevent;

import com.google.ads.mediation.MediationAdRequest;
import android.app.Activity;

@Deprecated
public interface CustomEventInterstitial extends CustomEvent
{
    void requestInterstitialAd(final CustomEventInterstitialListener p0, final Activity p1, final String p2, final String p3, final MediationAdRequest p4, final Object p5);
    
    void showInterstitial();
}
