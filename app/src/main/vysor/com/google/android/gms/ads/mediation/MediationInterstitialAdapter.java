// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation;

import android.os.Bundle;
import android.content.Context;

public interface MediationInterstitialAdapter extends MediationAdapter
{
    void requestInterstitialAd(final Context p0, final MediationInterstitialListener p1, final Bundle p2, final MediationAdRequest p3, final Bundle p4);
    
    void showInterstitial();
}
