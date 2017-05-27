// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.formats;

import com.google.android.gms.ads.internal.util.client.zzb;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;

public final class NativeAppInstallAdView extends NativeAdView
{
    public NativeAppInstallAdView(final Context context) {
        super(context);
    }
    
    public NativeAppInstallAdView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public NativeAppInstallAdView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    public NativeAppInstallAdView(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
    }
    
    public final View getBodyView() {
        return super.zzs("2004");
    }
    
    public final View getCallToActionView() {
        return super.zzs("2002");
    }
    
    public final View getHeadlineView() {
        return super.zzs("2001");
    }
    
    public final View getIconView() {
        return super.zzs("2003");
    }
    
    public final View getImageView() {
        return super.zzs("2007");
    }
    
    public final MediaView getMediaView() {
        final View zzs = super.zzs("2011");
        MediaView mediaView;
        if (zzs instanceof MediaView) {
            mediaView = (MediaView)zzs;
        }
        else {
            if (zzs != null) {
                zzb.zzdd("View is not an instance of MediaView");
            }
            mediaView = null;
        }
        return mediaView;
    }
    
    public final View getPriceView() {
        return super.zzs("2006");
    }
    
    public final View getStarRatingView() {
        return super.zzs("2008");
    }
    
    public final View getStoreView() {
        return super.zzs("2005");
    }
    
    public final void setBodyView(final View view) {
        super.zza("2004", view);
    }
    
    public final void setCallToActionView(final View view) {
        super.zza("2002", view);
    }
    
    public final void setHeadlineView(final View view) {
        super.zza("2001", view);
    }
    
    public final void setIconView(final View view) {
        super.zza("2003", view);
    }
    
    public final void setImageView(final View view) {
        super.zza("2007", view);
    }
    
    public final void setMediaView(final MediaView mediaView) {
        super.zza("2011", (View)mediaView);
    }
    
    public final void setPriceView(final View view) {
        super.zza("2006", view);
    }
    
    public final void setStarRatingView(final View view) {
        super.zza("2008", view);
    }
    
    public final void setStoreView(final View view) {
        super.zza("2005", view);
    }
}
