// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.formats;

import com.google.android.gms.ads.VideoController;
import java.util.List;
import android.os.Bundle;

public abstract class NativeAppInstallAd extends NativeAd
{
    public abstract void destroy();
    
    public abstract CharSequence getBody();
    
    public abstract CharSequence getCallToAction();
    
    public abstract Bundle getExtras();
    
    public abstract CharSequence getHeadline();
    
    public abstract Image getIcon();
    
    public abstract List<Image> getImages();
    
    public abstract CharSequence getPrice();
    
    public abstract Double getStarRating();
    
    public abstract CharSequence getStore();
    
    public abstract VideoController getVideoController();
    
    public interface OnAppInstallAdLoadedListener
    {
        void onAppInstallAdLoaded(final NativeAppInstallAd p0);
    }
}
