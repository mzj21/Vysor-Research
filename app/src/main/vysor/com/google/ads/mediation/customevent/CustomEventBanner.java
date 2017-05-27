// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation.customevent;

import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.AdSize;
import android.app.Activity;

@Deprecated
public interface CustomEventBanner extends CustomEvent
{
    void requestBannerAd(final CustomEventBannerListener p0, final Activity p1, final String p2, final String p3, final AdSize p4, final MediationAdRequest p5, final Object p6);
}
