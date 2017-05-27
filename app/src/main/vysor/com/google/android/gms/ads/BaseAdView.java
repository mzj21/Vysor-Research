// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.view.View;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import android.util.AttributeSet;
import android.content.Context;
import com.google.android.gms.ads.internal.client.zzae;
import android.view.ViewGroup;

class BaseAdView extends ViewGroup
{
    protected final zzae zzajz;
    
    public BaseAdView(final Context context, final int n) {
        super(context);
        this.zzajz = new zzae(this, zzg(n));
    }
    
    public BaseAdView(final Context context, final AttributeSet set, final int n) {
        super(context, set);
        this.zzajz = new zzae(this, set, false, zzg(n));
    }
    
    public BaseAdView(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n);
        this.zzajz = new zzae(this, set, false, zzg(n2));
    }
    
    private static boolean zzg(final int n) {
        return n == 2;
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
    
    public InAppPurchaseListener getInAppPurchaseListener() {
        return this.zzajz.getInAppPurchaseListener();
    }
    
    public String getMediationAdapterClassName() {
        return this.zzajz.getMediationAdapterClassName();
    }
    
    public boolean isLoading() {
        return this.zzajz.isLoading();
    }
    
    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(final AdRequest adRequest) {
        this.zzajz.zza(adRequest.zzdg());
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
        if (adListener != null && adListener instanceof zza) {
            this.zzajz.zza((zza)adListener);
        }
        else if (adListener == null) {
            this.zzajz.zza((zza)null);
        }
    }
    
    public void setAdSize(final AdSize adSize) {
        this.zzajz.setAdSizes(adSize);
    }
    
    public void setAdUnitId(final String adUnitId) {
        this.zzajz.setAdUnitId(adUnitId);
    }
    
    public void setInAppPurchaseListener(final InAppPurchaseListener inAppPurchaseListener) {
        this.zzajz.setInAppPurchaseListener(inAppPurchaseListener);
    }
    
    public void setPlayStorePurchaseParams(final PlayStorePurchaseListener playStorePurchaseListener, final String s) {
        this.zzajz.setPlayStorePurchaseParams(playStorePurchaseListener, s);
    }
}
