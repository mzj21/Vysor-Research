// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.zze;
import android.view.View;
import com.google.android.gms.dynamic.zzd;
import java.util.Iterator;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.internal.formats.zzc;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;

@zziy
public class zzha extends zzgv.zza
{
    private final NativeContentAdMapper zzbue;
    
    public zzha(final NativeContentAdMapper zzbue) {
        this.zzbue = zzbue;
    }
    
    public String getAdvertiser() {
        return this.zzbue.getAdvertiser();
    }
    
    public String getBody() {
        return this.zzbue.getBody();
    }
    
    public String getCallToAction() {
        return this.zzbue.getCallToAction();
    }
    
    public Bundle getExtras() {
        return this.zzbue.getExtras();
    }
    
    public String getHeadline() {
        return this.zzbue.getHeadline();
    }
    
    public List getImages() {
        final List<NativeAd.Image> images = this.zzbue.getImages();
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
        return this.zzbue.getOverrideClickHandling();
    }
    
    public boolean getOverrideImpressionRecording() {
        return this.zzbue.getOverrideImpressionRecording();
    }
    
    public void recordImpression() {
        this.zzbue.recordImpression();
    }
    
    public void zzk(final zzd zzd) {
        this.zzbue.handleClick(zze.zzae(zzd));
    }
    
    public void zzl(final zzd zzd) {
        this.zzbue.trackView(zze.zzae(zzd));
    }
    
    public zzdx zzlt() {
        final NativeAd.Image logo = this.zzbue.getLogo();
        zzc zzc;
        if (logo != null) {
            zzc = new zzc(logo.getDrawable(), logo.getUri(), logo.getScale());
        }
        else {
            zzc = null;
        }
        return zzc;
    }
    
    public void zzm(final zzd zzd) {
        this.zzbue.untrackView(zze.zzae(zzd));
    }
}
