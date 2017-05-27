// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation.customevent;

import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import android.content.Context;

public interface CustomEventInterstitial extends CustomEvent
{
    void requestInterstitialAd(final Context p0, final CustomEventInterstitialListener p1, final String p2, final MediationAdRequest p3, final Bundle p4);
    
    void showInterstitial();
}
