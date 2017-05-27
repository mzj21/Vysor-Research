// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzkg;
import android.text.TextUtils;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzku;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.ads.internal.client.zzz;

@zziy
public class zzo extends zzz.zza
{
    private static final Object zzaok;
    @Nullable
    private static zzo zzaol;
    private final Context mContext;
    private final Object zzakd;
    private boolean zzaom;
    private boolean zzaon;
    private float zzaoo;
    private VersionInfoParcel zzaop;
    
    static {
        zzaok = new Object();
    }
    
    zzo(final Context mContext, final VersionInfoParcel zzaop) {
        this.zzakd = new Object();
        this.zzaoo = -1.0f;
        this.mContext = mContext;
        this.zzaop = zzaop;
        this.zzaom = false;
    }
    
    public static zzo zza(final Context context, final VersionInfoParcel versionInfoParcel) {
        synchronized (zzo.zzaok) {
            if (zzo.zzaol == null) {
                zzo.zzaol = new zzo(context.getApplicationContext(), versionInfoParcel);
            }
            return zzo.zzaol;
        }
    }
    
    @Nullable
    public static zzo zzfd() {
        synchronized (zzo.zzaok) {
            return zzo.zzaol;
        }
    }
    
    public void initialize() {
        synchronized (zzo.zzaok) {
            if (this.zzaom) {
                zzb.zzdf("Mobile ads is initialized already.");
            }
            else {
                this.zzaom = true;
                // monitorexit(zzo.zzaok)
                zzu.zzgd().zzb(this.mContext, this.zzaop);
                zzu.zzge().initialize(this.mContext);
            }
        }
    }
    
    public void setAppMuted(final boolean zzaon) {
        synchronized (this.zzakd) {
            this.zzaon = zzaon;
        }
    }
    
    public void setAppVolume(final float zzaoo) {
        synchronized (this.zzakd) {
            this.zzaoo = zzaoo;
        }
    }
    
    public void zzb(final zzd zzd, final String s) {
        final zzku zzc = this.zzc(zzd, s);
        if (zzc == null) {
            zzb.e("Context is null. Failed to open debug menu.");
        }
        else {
            zzc.showDialog();
        }
    }
    
    @Nullable
    protected zzku zzc(final zzd zzd, final String adUnitId) {
        zzku zzku;
        if (zzd == null) {
            zzku = null;
        }
        else {
            final Context context = zze.zzae(zzd);
            if (context == null) {
                zzku = null;
            }
            else {
                final zzku zzku2 = new zzku(context);
                zzku2.setAdUnitId(adUnitId);
                zzku = zzku2;
            }
        }
        return zzku;
    }
    
    public float zzfe() {
        synchronized (this.zzakd) {
            return this.zzaoo;
        }
    }
    
    public boolean zzff() {
        while (true) {
            synchronized (this.zzakd) {
                if (this.zzaoo >= 0.0f) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public boolean zzfg() {
        synchronized (this.zzakd) {
            return this.zzaon;
        }
    }
    
    public void zzw(final String s) {
        zzdi.initialize(this.mContext);
        if (!TextUtils.isEmpty((CharSequence)s) && zzdi.zzbgq.get()) {
            zzu.zzgv().zza(this.mContext, this.zzaop, true, null, s, null);
        }
    }
}
