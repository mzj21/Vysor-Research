// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.formats;

import com.google.android.gms.dynamic.zze;
import android.view.View;
import com.google.android.gms.ads.internal.client.zzab;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzdx;
import java.util.List;
import android.os.Bundle;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzeb;

@zziy
public class zzd extends zzeb.zza implements zzi.zza
{
    private Bundle mExtras;
    private Object zzakd;
    private String zzbjq;
    private List<zzc> zzbjr;
    private String zzbjs;
    private zzdx zzbjt;
    private String zzbju;
    private double zzbjv;
    private String zzbjw;
    private String zzbjx;
    @Nullable
    private zza zzbjy;
    @Nullable
    private zzab zzbjz;
    @Nullable
    private View zzbka;
    private zzi zzbkb;
    
    public zzd(final String zzbjq, final List zzbjr, final String zzbjs, final zzdx zzbjt, final String zzbju, final double zzbjv, final String zzbjw, final String zzbjx, @Nullable final zza zzbjy, final Bundle mExtras, final zzab zzbjz, final View zzbka) {
        this.zzakd = new Object();
        this.zzbjq = zzbjq;
        this.zzbjr = (List<zzc>)zzbjr;
        this.zzbjs = zzbjs;
        this.zzbjt = zzbjt;
        this.zzbju = zzbju;
        this.zzbjv = zzbjv;
        this.zzbjw = zzbjw;
        this.zzbjx = zzbjx;
        this.zzbjy = zzbjy;
        this.mExtras = mExtras;
        this.zzbjz = zzbjz;
        this.zzbka = zzbka;
    }
    
    public void destroy() {
        this.zzbjq = null;
        this.zzbjr = null;
        this.zzbjs = null;
        this.zzbjt = null;
        this.zzbju = null;
        this.zzbjv = 0.0;
        this.zzbjw = null;
        this.zzbjx = null;
        this.zzbjy = null;
        this.mExtras = null;
        this.zzakd = null;
        this.zzbkb = null;
        this.zzbjz = null;
        this.zzbka = null;
    }
    
    public String getBody() {
        return this.zzbjs;
    }
    
    public String getCallToAction() {
        return this.zzbju;
    }
    
    @Override
    public String getCustomTemplateId() {
        return "";
    }
    
    public Bundle getExtras() {
        return this.mExtras;
    }
    
    public String getHeadline() {
        return this.zzbjq;
    }
    
    public List getImages() {
        return this.zzbjr;
    }
    
    public String getPrice() {
        return this.zzbjx;
    }
    
    public double getStarRating() {
        return this.zzbjv;
    }
    
    public String getStore() {
        return this.zzbjw;
    }
    
    @Override
    public void zzb(final zzi zzbkb) {
        synchronized (this.zzakd) {
            this.zzbkb = zzbkb;
        }
    }
    
    public zzab zzdw() {
        return this.zzbjz;
    }
    
    public zzdx zzlo() {
        return this.zzbjt;
    }
    
    public com.google.android.gms.dynamic.zzd zzlp() {
        return zze.zzac(this.zzbkb);
    }
    
    @Override
    public String zzlq() {
        return "2";
    }
    
    @Override
    public zza zzlr() {
        return this.zzbjy;
    }
    
    public View zzls() {
        return this.zzbka;
    }
}
