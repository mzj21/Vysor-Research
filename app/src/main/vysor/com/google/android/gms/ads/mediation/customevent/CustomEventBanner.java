// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation.customevent;

import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.AdSize;
import android.content.Context;

public interface CustomEventBanner extends CustomEvent
{
    void requestBannerAd(final Context p0, final CustomEventBannerListener p1, final String p2, final AdSize p3, final MediationAdRequest p4, final Bundle p5);
}
