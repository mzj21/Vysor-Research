// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.formats;

import java.util.List;

public interface NativeCustomTemplateAd
{
    List<String> getAvailableAssetNames();
    
    String getCustomTemplateId();
    
    NativeAd.Image getImage(final String p0);
    
    CharSequence getText(final String p0);
    
    void performClick(final String p0);
    
    void recordImpression();
    
    public interface OnCustomClickListener
    {
        void onCustomClick(final NativeCustomTemplateAd p0, final String p1);
    }
    
    public interface OnCustomTemplateAdLoadedListener
    {
        void onCustomTemplateAdLoaded(final NativeCustomTemplateAd p0);
    }
}
