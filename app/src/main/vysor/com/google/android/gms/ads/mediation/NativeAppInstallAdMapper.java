// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation;

import com.google.android.gms.ads.formats.NativeAd;
import java.util.List;
import com.google.android.gms.ads.VideoController;

public abstract class NativeAppInstallAdMapper extends NativeAdMapper
{
    private VideoController zzayu;
    private String zzbjq;
    private List<NativeAd.Image> zzbjr;
    private String zzbjs;
    private String zzbju;
    private double zzbjv;
    private String zzbjw;
    private String zzbjx;
    private NativeAd.Image zzcxg;
    
    public final String getBody() {
        return this.zzbjs;
    }
    
    public final String getCallToAction() {
        return this.zzbju;
    }
    
    public final String getHeadline() {
        return this.zzbjq;
    }
    
    public final NativeAd.Image getIcon() {
        return this.zzcxg;
    }
    
    public final List<NativeAd.Image> getImages() {
        return this.zzbjr;
    }
    
    public final String getPrice() {
        return this.zzbjx;
    }
    
    public final double getStarRating() {
        return this.zzbjv;
    }
    
    public final String getStore() {
        return this.zzbjw;
    }
    
    public final VideoController getVideoController() {
        return this.zzayu;
    }
    
    public final void setBody(final String zzbjs) {
        this.zzbjs = zzbjs;
    }
    
    public final void setCallToAction(final String zzbju) {
        this.zzbju = zzbju;
    }
    
    public final void setHeadline(final String zzbjq) {
        this.zzbjq = zzbjq;
    }
    
    public final void setIcon(final NativeAd.Image zzcxg) {
        this.zzcxg = zzcxg;
    }
    
    public final void setImages(final List<NativeAd.Image> zzbjr) {
        this.zzbjr = zzbjr;
    }
    
    public final void setPrice(final String zzbjx) {
        this.zzbjx = zzbjx;
    }
    
    public final void setStarRating(final double zzbjv) {
        this.zzbjv = zzbjv;
    }
    
    public final void setStore(final String zzbjw) {
        this.zzbjw = zzbjw;
    }
    
    public final void zza(final VideoController zzayu) {
        this.zzayu = zzayu;
    }
}
