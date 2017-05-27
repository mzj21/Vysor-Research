// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation;

import android.os.Bundle;
import android.content.Context;

public interface MediationNativeAdapter extends MediationAdapter
{
    void requestNativeAd(final Context p0, final MediationNativeListener p1, final Bundle p2, final NativeMediationAdRequest p3, final Bundle p4);
}
