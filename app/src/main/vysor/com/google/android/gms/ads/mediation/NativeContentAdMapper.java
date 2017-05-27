// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation;

import com.google.android.gms.ads.formats.NativeAd;
import java.util.List;

public abstract class NativeContentAdMapper extends NativeAdMapper
{
    private String zzbjq;
    private List<NativeAd.Image> zzbjr;
    private String zzbjs;
    private String zzbju;
    private String zzbkd;
    private NativeAd.Image zzcxh;
    
    public final String getAdvertiser() {
        return this.zzbkd;
    }
    
    public final String getBody() {
        return this.zzbjs;
    }
    
    public final String getCallToAction() {
        return this.zzbju;
    }
    
    public final String getHeadline() {
        return this.zzbjq;
    }
    
    public final List<NativeAd.Image> getImages() {
        return this.zzbjr;
    }
    
    public final NativeAd.Image getLogo() {
        return this.zzcxh;
    }
    
    public final void setAdvertiser(final String zzbkd) {
        this.zzbkd = zzbkd;
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
    
    public final void setImages(final List<NativeAd.Image> zzbjr) {
        this.zzbjr = zzbjr;
    }
    
    public final void setLogo(final NativeAd.Image zzcxh) {
        this.zzcxh = zzcxh;
    }
}
