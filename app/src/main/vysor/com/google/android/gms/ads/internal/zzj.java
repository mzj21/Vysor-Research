// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.internal.zzkr;
import java.util.ArrayList;
import java.lang.ref.WeakReference;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.client.zzy;
import java.util.List;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.internal.zzej;
import com.google.android.gms.internal.zzek;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.internal.zzei;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzeh;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.internal.zzgq;
import android.content.Context;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.ads.internal.client.zzr;

@zziy
public class zzj extends zzr.zza
{
    private final Context mContext;
    private final Object zzakd;
    private final zzd zzalo;
    private final zzgq zzals;
    private final zzq zzamy;
    @Nullable
    private final zzeh zzamz;
    @Nullable
    private final zzei zzana;
    private final SimpleArrayMap<String, zzek> zzanb;
    private final SimpleArrayMap<String, zzej> zzanc;
    private final NativeAdOptionsParcel zzand;
    private final List<String> zzane;
    private final zzy zzanf;
    private final String zzang;
    private final VersionInfoParcel zzanh;
    @Nullable
    private WeakReference<com.google.android.gms.ads.internal.zzq> zzani;
    
    zzj(final Context mContext, final String zzang, final zzgq zzals, final VersionInfoParcel zzanh, final zzq zzamy, final zzeh zzamz, final zzei zzana, final SimpleArrayMap<String, zzek> zzanb, final SimpleArrayMap<String, zzej> zzanc, final NativeAdOptionsParcel zzand, final zzy zzanf, final zzd zzalo) {
        this.zzakd = new Object();
        this.mContext = mContext;
        this.zzang = zzang;
        this.zzals = zzals;
        this.zzanh = zzanh;
        this.zzamy = zzamy;
        this.zzana = zzana;
        this.zzamz = zzamz;
        this.zzanb = zzanb;
        this.zzanc = zzanc;
        this.zzand = zzand;
        this.zzane = this.zzew();
        this.zzanf = zzanf;
        this.zzalo = zzalo;
    }
    
    private List<String> zzew() {
        final ArrayList<String> list = new ArrayList<String>();
        if (this.zzana != null) {
            list.add("1");
        }
        if (this.zzamz != null) {
            list.add("2");
        }
        if (this.zzanb.size() > 0) {
            list.add("3");
        }
        return list;
    }
    
    @Nullable
    public String getMediationAdapterClassName() {
        String mediationAdapterClassName;
        while (true) {
            while (true) {
                Label_0056: {
                    synchronized (this.zzakd) {
                        if (this.zzani != null) {
                            final com.google.android.gms.ads.internal.zzq zzq = this.zzani.get();
                            if (zzq == null) {
                                break Label_0056;
                            }
                            mediationAdapterClassName = zzq.getMediationAdapterClassName();
                        }
                        else {
                            // monitorexit(this.zzakd)
                            mediationAdapterClassName = null;
                        }
                    }
                    break;
                }
                mediationAdapterClassName = null;
                continue;
            }
        }
        return mediationAdapterClassName;
    }
    
    public boolean isLoading() {
        boolean loading;
        while (true) {
            while (true) {
                Label_0056: {
                    synchronized (this.zzakd) {
                        if (this.zzani != null) {
                            final com.google.android.gms.ads.internal.zzq zzq = this.zzani.get();
                            if (zzq == null) {
                                break Label_0056;
                            }
                            loading = zzq.isLoading();
                        }
                        else {
                            // monitorexit(this.zzakd)
                            loading = false;
                        }
                    }
                    break;
                }
                loading = false;
                continue;
            }
        }
        return loading;
    }
    
    protected void runOnUiThread(final Runnable runnable) {
        zzkr.zzcrf.post(runnable);
    }
    
    protected com.google.android.gms.ads.internal.zzq zzex() {
        return new com.google.android.gms.ads.internal.zzq(this.mContext, this.zzalo, AdSizeParcel.zzk(this.mContext), this.zzang, this.zzals, this.zzanh);
    }
    
    public void zzf(final AdRequestParcel adRequestParcel) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                synchronized (zzj.this.zzakd) {
                    final com.google.android.gms.ads.internal.zzq zzex = zzj.this.zzex();
                    zzj.this.zzani = (WeakReference<com.google.android.gms.ads.internal.zzq>)new WeakReference(zzex);
                    zzex.zzb(zzj.this.zzamz);
                    zzex.zzb(zzj.this.zzana);
                    zzex.zza(zzj.this.zzanb);
                    zzex.zza(zzj.this.zzamy);
                    zzex.zzb(zzj.this.zzanc);
                    zzex.zzb(zzj.this.zzew());
                    zzex.zzb(zzj.this.zzand);
                    zzex.zza(zzj.this.zzanf);
                    zzex.zzb(adRequestParcel);
                }
            }
        });
    }
}
