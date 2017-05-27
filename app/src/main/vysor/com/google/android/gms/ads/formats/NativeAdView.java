// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.formats;

import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzd;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.common.internal.zzac;
import android.util.AttributeSet;
import android.content.Context;
import com.google.android.gms.internal.zzdz;
import android.widget.FrameLayout;

public abstract class NativeAdView extends FrameLayout
{
    private final FrameLayout zzaks;
    private final zzdz zzakt;
    
    public NativeAdView(final Context context) {
        super(context);
        this.zzaks = this.zze(context);
        this.zzakt = this.zzdm();
    }
    
    public NativeAdView(final Context context, final AttributeSet set) {
        super(context, set);
        this.zzaks = this.zze(context);
        this.zzakt = this.zzdm();
    }
    
    public NativeAdView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.zzaks = this.zze(context);
        this.zzakt = this.zzdm();
    }
    
    public NativeAdView(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
        this.zzaks = this.zze(context);
        this.zzakt = this.zzdm();
    }
    
    private zzdz zzdm() {
        zzac.zzb(this.zzaks, "createDelegate must be called after mOverlayFrame has been created");
        return zzm.zzjs().zza(this.zzaks.getContext(), this, this.zzaks);
    }
    
    private FrameLayout zze(final Context context) {
        final FrameLayout zzf = this.zzf(context);
        zzf.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        this.addView((View)zzf);
        return zzf;
    }
    
    public void addView(final View view, final int n, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super.addView(view, n, viewGroup$LayoutParams);
        super.bringChildToFront((View)this.zzaks);
    }
    
    public void bringChildToFront(final View view) {
        super.bringChildToFront(view);
        if (this.zzaks != view) {
            super.bringChildToFront((View)this.zzaks);
        }
    }
    
    public void destroy() {
        try {
            this.zzakt.destroy();
        }
        catch (RemoteException ex) {
            zzb.zzb("Unable to destroy native ad view", (Throwable)ex);
        }
    }
    
    public void removeAllViews() {
        super.removeAllViews();
        super.addView((View)this.zzaks);
    }
    
    public void removeView(final View view) {
        if (this.zzaks != view) {
            super.removeView(view);
        }
    }
    
    public void setNativeAd(final NativeAd nativeAd) {
        try {
            this.zzakt.zze((zzd)nativeAd.zzdl());
        }
        catch (RemoteException ex) {
            zzb.zzb("Unable to call setNativeAd on delegate", (Throwable)ex);
        }
    }
    
    protected void zza(final String s, final View view) {
        try {
            this.zzakt.zzc(s, zze.zzac(view));
        }
        catch (RemoteException ex) {
            zzb.zzb("Unable to call setAssetView on delegate", (Throwable)ex);
        }
    }
    
    FrameLayout zzf(final Context context) {
        return new FrameLayout(context);
    }
    
    protected View zzs(final String s) {
        while (true) {
            try {
                final zzd zzas = this.zzakt.zzas(s);
                if (zzas != null) {
                    return zze.zzae(zzas);
                }
            }
            catch (RemoteException ex) {
                zzb.zzb("Unable to call getAssetView on delegate", (Throwable)ex);
            }
            return null;
        }
    }
}
