// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation.customevent;

import android.os.Bundle;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import android.content.Context;

public interface CustomEventNative extends CustomEvent
{
    void requestNativeAd(final Context p0, final CustomEventNativeListener p1, final String p2, final NativeMediationAdRequest p3, final Bundle p4);
}
