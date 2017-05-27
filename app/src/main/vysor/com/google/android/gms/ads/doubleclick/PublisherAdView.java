// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.doubleclick;

import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.view.View;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdListener;
import android.util.AttributeSet;
import android.content.Context;
import com.google.android.gms.ads.internal.client.zzae;
import android.view.ViewGroup;

public final class PublisherAdView extends ViewGroup
{
    private final zzae zzajz;
    
    public PublisherAdView(final Context context) {
        super(context);
        this.zzajz = new zzae(this);
    }
    
    public PublisherAdView(final Context context, final AttributeSet set) {
        super(context, set);
        this.zzajz = new zzae(this, set, true);
    }
    
    public PublisherAdView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.zzajz = new zzae(this, set, true);
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
    
    public AdSize[] getAdSizes() {
        return this.zzajz.getAdSizes();
    }
    
    public String getAdUnitId() {
        return this.zzajz.getAdUnitId();
    }
    
    public AppEventListener getAppEventListener() {
        return this.zzajz.getAppEventListener();
    }
    
    public String getMediationAdapterClassName() {
        return this.zzajz.getMediationAdapterClassName();
    }
    
    public OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzajz.getOnCustomRenderedAdLoadedListener();
    }
    
    public boolean isLoading() {
        return this.zzajz.isLoading();
    }
    
    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(final PublisherAdRequest publisherAdRequest) {
        this.zzajz.zza(publisherAdRequest.zzdg());
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
    
    public void recordManualImpression() {
        this.zzajz.recordManualImpression();
    }
    
    public void resume() {
        this.zzajz.resume();
    }
    
    public void setAdListener(final AdListener adListener) {
        this.zzajz.setAdListener(adListener);
    }
    
    public void setAdSizes(final AdSize... array) {
        if (array == null || array.length < 1) {
            throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
        }
        this.zzajz.zza(array);
    }
    
    public void setAdUnitId(final String adUnitId) {
        this.zzajz.setAdUnitId(adUnitId);
    }
    
    public void setAppEventListener(final AppEventListener appEventListener) {
        this.zzajz.setAppEventListener(appEventListener);
    }
    
    public void setCorrelator(final Correlator correlator) {
        this.zzajz.setCorrelator(correlator);
    }
    
    public void setManualImpressionsEnabled(final boolean manualImpressionsEnabled) {
        this.zzajz.setManualImpressionsEnabled(manualImpressionsEnabled);
    }
    
    public void setOnCustomRenderedAdLoadedListener(final OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzajz.setOnCustomRenderedAdLoadedListener(onCustomRenderedAdLoadedListener);
    }
}
