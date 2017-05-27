// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.formats;

import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzdx;
import android.support.annotation.Nullable;
import java.util.List;
import android.os.Bundle;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzed;

@zziy
public class zze extends zzed.zza implements zzi.zza
{
    private Bundle mExtras;
    private Object zzakd;
    private String zzbjq;
    private List<zzc> zzbjr;
    private String zzbjs;
    private String zzbju;
    @Nullable
    private zza zzbjy;
    private zzi zzbkb;
    private zzdx zzbkc;
    private String zzbkd;
    
    public zze(final String zzbjq, final List zzbjr, final String zzbjs, final zzdx zzbkc, final String zzbju, final String zzbkd, @Nullable final zza zzbjy, final Bundle mExtras) {
        this.zzakd = new Object();
        this.zzbjq = zzbjq;
        this.zzbjr = (List<zzc>)zzbjr;
        this.zzbjs = zzbjs;
        this.zzbkc = zzbkc;
        this.zzbju = zzbju;
        this.zzbkd = zzbkd;
        this.zzbjy = zzbjy;
        this.mExtras = mExtras;
    }
    
    public void destroy() {
        this.zzbjq = null;
        this.zzbjr = null;
        this.zzbjs = null;
        this.zzbkc = null;
        this.zzbju = null;
        this.zzbkd = null;
        this.zzbjy = null;
        this.mExtras = null;
        this.zzakd = null;
        this.zzbkb = null;
    }
    
    public String getAdvertiser() {
        return this.zzbkd;
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
    
    @Override
    public void zzb(final zzi zzbkb) {
        synchronized (this.zzakd) {
            this.zzbkb = zzbkb;
        }
    }
    
    public zzd zzlp() {
        return com.google.android.gms.dynamic.zze.zzac(this.zzbkb);
    }
    
    @Override
    public String zzlq() {
        return "1";
    }
    
    @Override
    public zza zzlr() {
        return this.zzbjy;
    }
    
    public zzdx zzlt() {
        return this.zzbkc;
    }
}
