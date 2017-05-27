// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import android.util.AttributeSet;
import android.content.Context;
import com.google.android.gms.internal.zziy;

@zziy
public final class NativeExpressAdView extends BaseAdView
{
    public NativeExpressAdView(final Context context) {
        super(context, 2);
    }
    
    public NativeExpressAdView(final Context context, final AttributeSet set) {
        super(context, set, 2);
    }
    
    public NativeExpressAdView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n, 2);
    }
    
    public VideoController getVideoController() {
        return this.zzajz.getVideoController();
    }
    
    public VideoOptions getVideoOptions() {
        return this.zzajz.getVideoOptions();
    }
    
    public void setVideoOptions(final VideoOptions videoOptions) {
        this.zzajz.setVideoOptions(videoOptions);
    }
}
