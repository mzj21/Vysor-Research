// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation;

import android.app.Activity;

@Deprecated
public interface MediationInterstitialAdapter<ADDITIONAL_PARAMETERS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> extends MediationAdapter<ADDITIONAL_PARAMETERS, SERVER_PARAMETERS>
{
    void requestInterstitialAd(final MediationInterstitialListener p0, final Activity p1, final SERVER_PARAMETERS p2, final MediationAdRequest p3, final ADDITIONAL_PARAMETERS p4);
    
    void showInterstitial();
}
