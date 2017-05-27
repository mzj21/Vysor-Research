// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.formats;

import com.google.android.gms.internal.zzdx;
import org.json.JSONObject;
import android.view.View;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Arrays;
import java.util.List;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzef;

@zziy
public class zzf extends zzef.zza implements zzi.zza
{
    private final Object zzakd;
    private final zza zzbjy;
    private zzi zzbkb;
    private final String zzbke;
    private final SimpleArrayMap<String, zzc> zzbkf;
    private final SimpleArrayMap<String, String> zzbkg;
    
    public zzf(final String zzbke, final SimpleArrayMap<String, zzc> zzbkf, final SimpleArrayMap<String, String> zzbkg, final zza zzbjy) {
        this.zzakd = new Object();
        this.zzbke = zzbke;
        this.zzbkf = zzbkf;
        this.zzbkg = zzbkg;
        this.zzbjy = zzbjy;
    }
    
    public List<String> getAvailableAssetNames() {
        final String[] array = new String[this.zzbkf.size() + this.zzbkg.size()];
        int n = 0;
        int n2 = 0;
        int i;
        while (true) {
            final int size = this.zzbkf.size();
            i = 0;
            if (n >= size) {
                break;
            }
            array[n2] = this.zzbkf.keyAt(n);
            final int n3 = n + 1;
            ++n2;
            n = n3;
        }
        while (i < this.zzbkg.size()) {
            array[n2] = this.zzbkg.keyAt(i);
            ++i;
            ++n2;
        }
        return Arrays.asList(array);
    }
    
    @Override
    public String getCustomTemplateId() {
        return this.zzbke;
    }
    
    public void performClick(final String s) {
        synchronized (this.zzakd) {
            if (this.zzbkb == null) {
                zzb.e("Attempt to call performClick before ad initialized.");
            }
            else {
                this.zzbkb.zza(null, s, null, null, null);
            }
        }
    }
    
    public void recordImpression() {
        synchronized (this.zzakd) {
            if (this.zzbkb == null) {
                zzb.e("Attempt to perform recordImpression before ad initialized.");
            }
            else {
                this.zzbkb.recordImpression();
            }
        }
    }
    
    public String zzaw(final String s) {
        return this.zzbkg.get(s);
    }
    
    public zzdx zzax(final String s) {
        return this.zzbkf.get(s);
    }
    
    @Override
    public void zzb(final zzi zzbkb) {
        synchronized (this.zzakd) {
            this.zzbkb = zzbkb;
        }
    }
    
    @Override
    public String zzlq() {
        return "3";
    }
    
    @Override
    public zza zzlr() {
        return this.zzbjy;
    }
}
