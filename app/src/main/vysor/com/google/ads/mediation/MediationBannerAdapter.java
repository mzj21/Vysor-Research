// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation;

import com.google.ads.AdSize;
import android.app.Activity;
import android.view.View;

@Deprecated
public interface MediationBannerAdapter<ADDITIONAL_PARAMETERS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> extends MediationAdapter<ADDITIONAL_PARAMETERS, SERVER_PARAMETERS>
{
    View getBannerView();
    
    void requestBannerAd(final MediationBannerListener p0, final Activity p1, final SERVER_PARAMETERS p2, final AdSize p3, final MediationAdRequest p4, final ADDITIONAL_PARAMETERS p5);
}
