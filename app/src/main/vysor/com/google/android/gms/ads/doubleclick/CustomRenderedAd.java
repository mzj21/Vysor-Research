// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.doubleclick;

import android.view.View;

public interface CustomRenderedAd
{
    String getBaseUrl();
    
    String getContent();
    
    void onAdRendered(final View p0);
    
    void recordClick();
    
    void recordImpression();
}
