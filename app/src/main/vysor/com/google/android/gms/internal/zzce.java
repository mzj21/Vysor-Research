// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.dynamic.zze;
import android.net.Uri;
import com.google.android.gms.dynamic.zzd;
import android.content.Context;

public final class zzce extends zzcg.zza
{
    private final zzat zzaki;
    private final zzau zzakj;
    private final zzar zzakk;
    private boolean zzakl;
    
    public zzce(final String s, final Context context, final boolean b) {
        this.zzakl = false;
        this.zzaki = zzat.zza(s, context, b);
        this.zzakj = new zzau(this.zzaki);
        zzar zzd;
        if (b) {
            zzd = null;
        }
        else {
            zzd = zzar.zzd(context);
        }
        this.zzakk = zzd;
    }
    
    private zzd zza(final zzd zzd, final zzd zzd2, final boolean b) {
        zzd zzac;
        try {
            final Uri uri = zze.zzae(zzd);
            final Context context = zze.zzae(zzd2);
            Uri uri2;
            if (b) {
                uri2 = this.zzakj.zza(uri, context);
            }
            else {
                uri2 = this.zzakj.zzb(uri, context);
            }
            zzac = zze.zzac(uri2);
        }
        catch (zzav zzav) {
            zzac = null;
        }
        return zzac;
    }
    
    public zzd zza(final zzd zzd, final zzd zzd2) {
        return this.zza(zzd, zzd2, true);
    }
    
    public String zza(final zzd zzd, final String s) {
        return this.zzaki.zzb(zze.zzae(zzd), s);
    }
    
    public boolean zza(final zzd zzd) {
        return this.zzakj.zza(zze.zzae(zzd));
    }
    
    public zzd zzb(final zzd zzd, final zzd zzd2) {
        return this.zza(zzd, zzd2, false);
    }
    
    public void zzb(final String s, final String s2) {
        this.zzakj.zzb(s, s2);
    }
    
    public boolean zzb(final zzd zzd) {
        return this.zzakj.zzc(zze.zzae(zzd));
    }
    
    public boolean zzb(final String s, final boolean b) {
        boolean zzakl = true;
        if (this.zzakk == null) {
            zzakl = false;
        }
        else {
            this.zzakk.zza(new AdvertisingIdClient.Info(s, b));
            this.zzakl = zzakl;
        }
        return zzakl;
    }
    
    public String zzc(final zzd zzd) {
        final Context context = zze.zzae(zzd);
        final String zzb = this.zzaki.zzb(context);
        String zza;
        if (this.zzakk != null && this.zzakl) {
            zza = this.zzakk.zza(zzb, this.zzakk.zzb(context));
            this.zzakl = false;
        }
        else {
            zza = zzb;
        }
        return zza;
    }
    
    public void zzd(final zzd zzd) {
        this.zzakj.zza(zze.zzae(zzd));
    }
    
    public String zzdk() {
        return "ms";
    }
    
    public void zzm(final String s) {
        this.zzakj.zzm(s);
    }
}
