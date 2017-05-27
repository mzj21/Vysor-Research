// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.search;

import com.google.android.gms.ads.internal.util.client.zzb;
import android.view.View;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdListener;
import android.util.AttributeSet;
import android.content.Context;
import com.google.android.gms.ads.internal.client.zzae;
import com.google.android.gms.internal.zziy;
import android.view.ViewGroup;

@zziy
public final class SearchAdView extends ViewGroup
{
    private final zzae zzajz;
    
    public SearchAdView(final Context context) {
        super(context);
        this.zzajz = new zzae(this);
    }
    
    public SearchAdView(final Context context, final AttributeSet set) {
        super(context, set);
        this.zzajz = new zzae(this, set, false);
    }
    
    public SearchAdView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.zzajz = new zzae(this, set, false);
    }
    
    public void destroy() {
        this.zzajz.destroy();
    }
    
    public AdListener getAdListener() {
        return this.zzajz.getAdListener();
    }
    
    public AdSize getAdSize() {
        return this.zzajz.getAdSize();
    }
    
    public String getAdUnitId() {
        return this.zzajz.getAdUnitId();
    }
    
    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(final DynamicHeightSearchAdRequest dynamicHeightSearchAdRequest) {
        if (!AdSize.SEARCH.equals(this.getAdSize())) {
            throw new IllegalStateException("You must use AdSize.SEARCH for a DynamicHeightSearchAdRequest");
        }
        this.zzajz.zza(dynamicHeightSearchAdRequest.zzdg());
    }
    
    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(final SearchAdRequest searchAdRequest) {
        this.zzajz.zza(searchAdRequest.zzdg());
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        final View child = this.getChildAt(0);
        if (child != null && child.getVisibility() != 8) {
            final int measuredWidth = child.getMeasuredWidth();
            final int measuredHeight = child.getMeasuredHeight();
            final int n5 = (n3 - n - measuredWidth) / 2;
            final int n6 = (n4 - n2 - measuredHeight) / 2;
            child.layout(n5, n6, measuredWidth + n5, measuredHeight + n6);
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        final View child = this.getChildAt(0);
        int n3 = 0;
        int n4 = 0;
        Label_0038: {
            if (child != null && child.getVisibility() != 8) {
                this.measureChild(child, n, n2);
                n3 = child.getMeasuredWidth();
                n4 = child.getMeasuredHeight();
            }
            else {
                while (true) {
                    try {
                        final AdSize adSize = this.getAdSize();
                        if (adSize != null) {
                            final Context context = this.getContext();
                            n3 = adSize.getWidthInPixels(context);
                            n4 = adSize.getHeightInPixels(context);
                            break Label_0038;
                        }
                    }
                    catch (NullPointerException ex) {
                        zzb.zzb("Unable to retrieve ad size.", ex);
                        final AdSize adSize = null;
                        continue;
                    }
                    break;
                }
                n4 = 0;
                n3 = 0;
            }
        }
        this.setMeasuredDimension(View.resolveSize(Math.max(n3, this.getSuggestedMinimumWidth()), n), View.resolveSize(Math.max(n4, this.getSuggestedMinimumHeight()), n2));
    }
    
    public void pause() {
        this.zzajz.pause();
    }
    
    public void resume() {
        this.zzajz.resume();
    }
    
    public void setAdListener(final AdListener adListener) {
        this.zzajz.setAdListener(adListener);
    }
    
    public void setAdSize(final AdSize adSize) {
        this.zzajz.setAdSizes(adSize);
    }
    
    public void setAdUnitId(final String adUnitId) {
        this.zzajz.setAdUnitId(adUnitId);
    }
}
