// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.reward.mediation;

import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import android.content.Context;
import com.google.android.gms.ads.mediation.MediationAdapter;

public interface MediationRewardedVideoAdAdapter extends MediationAdapter
{
    public static final String CUSTOM_EVENT_SERVER_PARAMETER_FIELD = "parameter";
    
    void initialize(final Context p0, final MediationAdRequest p1, final String p2, final MediationRewardedVideoAdListener p3, final Bundle p4, final Bundle p5);
    
    boolean isInitialized();
    
    void loadAd(final MediationAdRequest p0, final Bundle p1, final Bundle p2);
    
    void showVideo();
}
