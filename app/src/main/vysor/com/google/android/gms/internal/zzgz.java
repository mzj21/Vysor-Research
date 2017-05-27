// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.zze;
import android.view.View;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.ads.internal.client.zzab;
import java.util.Iterator;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.internal.formats.zzc;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;

@zziy
public class zzgz extends zzgu.zza
{
    private final NativeAppInstallAdMapper zzbud;
    
    public zzgz(final NativeAppInstallAdMapper zzbud) {
        this.zzbud = zzbud;
    }
    
    public String getBody() {
        return this.zzbud.getBody();
    }
    
    public String getCallToAction() {
        return this.zzbud.getCallToAction();
    }
    
    public Bundle getExtras() {
        return this.zzbud.getExtras();
    }
    
    public String getHeadline() {
        return this.zzbud.getHeadline();
    }
    
    public List getImages() {
        final List<NativeAd.Image> images = this.zzbud.getImages();
        ArrayList<zzc> list2;
        if (images != null) {
            final ArrayList<zzc> list = new ArrayList<zzc>();
            for (final NativeAd.Image image : images) {
                list.add(new zzc(image.getDrawable(), image.getUri(), image.getScale()));
            }
            list2 = list;
        }
        else {
            list2 = null;
        }
        return list2;
    }
    
    public boolean getOverrideClickHandling() {
        return this.zzbud.getOverrideClickHandling();
    }
    
    public boolean getOverrideImpressionRecording() {
        return this.zzbud.getOverrideImpressionRecording();
    }
    
    public String getPrice() {
        return this.zzbud.getPrice();
    }
    
    public double getStarRating() {
        return this.zzbud.getStarRating();
    }
    
    public String getStore() {
        return this.zzbud.getStore();
    }
    
    public void recordImpression() {
        this.zzbud.recordImpression();
    }
    
    public zzab zzdw() {
        zzab zzdj;
        if (this.zzbud.getVideoController() != null) {
            zzdj = this.zzbud.getVideoController().zzdj();
        }
        else {
            zzdj = null;
        }
        return zzdj;
    }
    
    public void zzk(final zzd zzd) {
        this.zzbud.handleClick(zze.zzae(zzd));
    }
    
    public void zzl(final zzd zzd) {
        this.zzbud.trackView(zze.zzae(zzd));
    }
    
    public zzdx zzlo() {
        final NativeAd.Image icon = this.zzbud.getIcon();
        zzc zzc;
        if (icon != null) {
            zzc = new zzc(icon.getDrawable(), icon.getUri(), icon.getScale());
        }
        else {
            zzc = null;
        }
        return zzc;
    }
    
    public void zzm(final zzd zzd) {
        this.zzbud.untrackView(zze.zzae(zzd));
    }
}
