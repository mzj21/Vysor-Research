// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.reward;

import com.google.android.gms.ads.AdRequest;
import android.content.Context;

public interface RewardedVideoAd
{
    @Deprecated
    void destroy();
    
    void destroy(final Context p0);
    
    RewardedVideoAdListener getRewardedVideoAdListener();
    
    @Deprecated
    String getUserId();
    
    boolean isLoaded();
    
    void loadAd(final String p0, final AdRequest p1);
    
    @Deprecated
    void pause();
    
    void pause(final Context p0);
    
    @Deprecated
    void resume();
    
    void resume(final Context p0);
    
    void setRewardedVideoAdListener(final RewardedVideoAdListener p0);
    
    @Deprecated
    void setUserId(final String p0);
    
    void show();
}
