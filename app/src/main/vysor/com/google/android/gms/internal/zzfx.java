// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONObject;
import com.google.android.gms.ads.internal.safebrowsing.zzc;
import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.overlay.zzg;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.zzs;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.zzd;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.content.Context;

@zziy
public class zzfx implements zzfv
{
    private final zzlt zzbkr;
    
    public zzfx(final Context context, final VersionInfoParcel versionInfoParcel, @Nullable final zzau zzau, final zzd zzd) {
        this.zzbkr = zzu.zzga().zza(context, new AdSizeParcel(), false, false, zzau, versionInfoParcel, null, null, zzd);
        this.zzbkr.getWebView().setWillNotDraw(true);
    }
    
    private void runOnUiThread(final Runnable runnable) {
        if (zzm.zzjr().zzvf()) {
            runnable.run();
        }
        else {
            zzkr.zzcrf.post(runnable);
        }
    }
    
    @Override
    public void destroy() {
        this.zzbkr.destroy();
    }
    
    @Override
    public void zza(final com.google.android.gms.ads.internal.client.zza zza, final zzg zzg, final zzer zzer, final zzp zzp, final boolean b, final zzex zzex, final zzez zzez, final zze zze, final zzhn zzhn) {
        this.zzbkr.zzvr().zza(zza, zzg, zzer, zzp, b, zzex, zzez, new zze(this.zzbkr.getContext(), false), zzhn, null);
    }
    
    @Override
    public void zza(final zza zza) {
        this.zzbkr.zzvr().zza((zzlu.zza)new zzlu.zza() {
            @Override
            public void zza(final zzlt zzlt, final boolean b) {
                zza.zzmx();
            }
        });
    }
    
    @Override
    public void zza(final String s, final zzev zzev) {
        this.zzbkr.zzvr().zza(s, zzev);
    }
    
    @Override
    public void zza(final String s, final JSONObject jsonObject) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                zzfx.this.zzbkr.zza(s, jsonObject);
            }
        });
    }
    
    @Override
    public void zzb(final String s, final zzev zzev) {
        this.zzbkr.zzvr().zzb(s, zzev);
    }
    
    @Override
    public void zzb(final String s, final JSONObject jsonObject) {
        this.zzbkr.zzb(s, jsonObject);
    }
    
    @Override
    public void zzbk(final String s) {
        this.runOnUiThread(new Runnable() {
            final /* synthetic */ String zzbqg = String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head><body></body></html>", s);
            
            @Override
            public void run() {
                zzfx.this.zzbkr.loadData(this.zzbqg, "text/html", "UTF-8");
            }
        });
    }
    
    @Override
    public void zzbl(final String s) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                zzfx.this.zzbkr.loadUrl(s);
            }
        });
    }
    
    @Override
    public void zzbm(final String s) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                zzfx.this.zzbkr.loadData(s, "text/html", "UTF-8");
            }
        });
    }
    
    @Override
    public void zzj(final String s, final String s2) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                zzfx.this.zzbkr.zzj(s, s2);
            }
        });
    }
    
    @Override
    public zzga zzmw() {
        return new zzgb(this);
    }
}
