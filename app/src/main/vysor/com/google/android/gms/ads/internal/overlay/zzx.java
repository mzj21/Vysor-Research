// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Color;
import java.util.Iterator;
import android.os.Bundle;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzdm;
import android.view.TextureView;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.text.TextUtils;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzkx;
import com.google.android.gms.internal.zzdq;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzdo;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.content.Context;
import com.google.android.gms.internal.zziy;

@zziy
public class zzx
{
    private final Context mContext;
    private final VersionInfoParcel zzaop;
    private final String zzcaj;
    @Nullable
    private final zzdo zzcak;
    @Nullable
    private final zzdq zzcal;
    private final zzkx zzcam;
    private final long[] zzcan;
    private final String[] zzcao;
    private boolean zzcap;
    private boolean zzcaq;
    private boolean zzcar;
    private boolean zzcas;
    private boolean zzcat;
    private zzi zzcau;
    private boolean zzcav;
    private boolean zzcaw;
    private long zzcax;
    
    public zzx(final Context mContext, final VersionInfoParcel zzaop, final String zzcaj, @Nullable final zzdq zzcal, @Nullable final zzdo zzcak) {
        this.zzcam = new zzkx.zzb().zza("min_1", Double.MIN_VALUE, 1.0).zza("1_5", 1.0, 5.0).zza("5_10", 5.0, 10.0).zza("10_20", 10.0, 20.0).zza("20_30", 20.0, 30.0).zza("30_max", 30.0, Double.MAX_VALUE).zzuw();
        this.zzcap = false;
        this.zzcaq = false;
        this.zzcar = false;
        this.zzcas = false;
        this.zzcax = -1L;
        this.mContext = mContext;
        this.zzaop = zzaop;
        this.zzcaj = zzcaj;
        this.zzcal = zzcal;
        this.zzcak = zzcak;
        final String s = zzdi.zzbbm.get();
        if (s == null) {
            this.zzcao = new String[0];
            this.zzcan = new long[0];
        }
        else {
            final String[] split = TextUtils.split(s, ",");
            this.zzcao = new String[split.length];
            this.zzcan = new long[split.length];
            int i = 0;
            while (i < split.length) {
                while (true) {
                    try {
                        this.zzcan[i] = Long.parseLong(split[i]);
                        ++i;
                    }
                    catch (NumberFormatException ex) {
                        zzb.zzd("Unable to parse frame hash target time number.", ex);
                        this.zzcan[i] = -1L;
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    private void zzc(final zzi zzi) {
        final long longValue = zzdi.zzbbn.get();
        final long n = zzi.getCurrentPosition();
        for (int i = 0; i < this.zzcao.length; ++i) {
            if (this.zzcao[i] == null && longValue > Math.abs(n - this.zzcan[i])) {
                this.zzcao[i] = this.zza((TextureView)zzi);
                break;
            }
        }
    }
    
    private void zzqe() {
        if (this.zzcar && !this.zzcas) {
            zzdm.zza(this.zzcal, this.zzcak, "vff2");
            this.zzcas = true;
        }
        final long nanoTime = zzu.zzgf().nanoTime();
        if (this.zzcat && this.zzcaw && this.zzcax != -1L) {
            this.zzcam.zza(TimeUnit.SECONDS.toNanos(1L) / (nanoTime - this.zzcax));
        }
        this.zzcaw = this.zzcat;
        this.zzcax = nanoTime;
    }
    
    public void onStop() {
        if (zzdi.zzbbl.get() && !this.zzcav) {
            final Bundle bundle = new Bundle();
            bundle.putString("type", "native-player-metrics");
            bundle.putString("request", this.zzcaj);
            bundle.putString("player", this.zzcau.zzog());
            for (final zzkx.zza zza : this.zzcam.getBuckets()) {
                final String value = String.valueOf("fps_c_");
                final String value2 = String.valueOf(zza.name);
                String concat;
                if (value2.length() != 0) {
                    concat = value.concat(value2);
                }
                else {
                    concat = new String(value);
                }
                bundle.putString(concat, Integer.toString(zza.count));
                final String value3 = String.valueOf("fps_p_");
                final String value4 = String.valueOf(zza.name);
                String concat2;
                if (value4.length() != 0) {
                    concat2 = value3.concat(value4);
                }
                else {
                    concat2 = new String(value3);
                }
                bundle.putString(concat2, Double.toString(zza.zzcsg));
            }
            for (int i = 0; i < this.zzcan.length; ++i) {
                final String s = this.zzcao[i];
                if (s != null) {
                    final String value5 = String.valueOf("fh_");
                    final String value6 = String.valueOf((Object)this.zzcan[i]);
                    bundle.putString(new StringBuilder(0 + String.valueOf(value5).length() + String.valueOf(value6).length()).append(value5).append(value6).toString(), s);
                }
            }
            zzu.zzfz().zza(this.mContext, this.zzaop.zzcs, "gmob-apps", bundle, true);
            this.zzcav = true;
        }
    }
    
    @TargetApi(14)
    String zza(final TextureView textureView) {
        final Bitmap bitmap = textureView.getBitmap(8, 8);
        long n = 0L;
        long n2 = 63L;
        long n3;
        long n4;
        for (int i = 0; i < 8; ++i, n2 = n4, n = n3) {
            n3 = n;
            n4 = n2;
            int n6;
            for (int j = 0; j < 8; j = n6) {
                final int pixel = bitmap.getPixel(j, i);
                long n5;
                if (Color.blue(pixel) + Color.red(pixel) + Color.green(pixel) > 128) {
                    n5 = 1L;
                }
                else {
                    n5 = 0L;
                }
                n3 |= n5 << (int)n4;
                n6 = j + 1;
                --n4;
            }
        }
        return String.format("%016X", n);
    }
    
    public void zza(final zzi zzcau) {
        zzdm.zza(this.zzcal, this.zzcak, "vpc2");
        this.zzcap = true;
        if (this.zzcal != null) {
            this.zzcal.zzh("vpn", zzcau.zzog());
        }
        this.zzcau = zzcau;
    }
    
    public void zzb(final zzi zzi) {
        this.zzqe();
        this.zzc(zzi);
    }
    
    public void zzpj() {
        if (this.zzcap && !this.zzcaq) {
            zzdm.zza(this.zzcal, this.zzcak, "vfr2");
            this.zzcaq = true;
        }
    }
    
    public void zzqf() {
        this.zzcat = true;
        if (this.zzcaq && !this.zzcar) {
            zzdm.zza(this.zzcal, this.zzcak, "vfp2");
            this.zzcar = true;
        }
    }
    
    public void zzqg() {
        this.zzcat = false;
    }
}
