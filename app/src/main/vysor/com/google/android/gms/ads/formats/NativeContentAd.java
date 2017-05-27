// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.formats;

import java.util.List;
import android.os.Bundle;

public abstract class NativeContentAd extends NativeAd
{
    public abstract void destroy();
    
    public abstract CharSequence getAdvertiser();
    
    public abstract CharSequence getBody();
    
    public abstract CharSequence getCallToAction();
    
    public abstract Bundle getExtras();
    
    public abstract CharSequence getHeadline();
    
    public abstract List<Image> getImages();
    
    public abstract Image getLogo();
    
    public interface OnContentAdLoadedListener
    {
        void onContentAdLoaded(final NativeContentAd p0);
    }
}
