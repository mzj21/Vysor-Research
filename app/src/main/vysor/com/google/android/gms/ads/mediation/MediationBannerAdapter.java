// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation;

import com.google.android.gms.ads.AdSize;
import android.os.Bundle;
import android.content.Context;
import android.view.View;

public interface MediationBannerAdapter extends MediationAdapter
{
    View getBannerView();
    
    void requestBannerAd(final Context p0, final MediationBannerListener p1, final Bundle p2, final AdSize p3, final MediationAdRequest p4, final Bundle p5);
}
