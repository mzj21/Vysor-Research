// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.ads.internal.zzu;

@zziy
public class zzkk
{
    private final Object zzakd;
    private final zzkh zzapw;
    private final String zzcos;
    private int zzcqi;
    private int zzcqj;
    
    zzkk(final zzkh zzapw, final String zzcos) {
        this.zzakd = new Object();
        this.zzapw = zzapw;
        this.zzcos = zzcos;
    }
    
    public zzkk(final String s) {
        this(zzu.zzgd(), s);
    }
    
    public Bundle toBundle() {
        synchronized (this.zzakd) {
            final Bundle bundle = new Bundle();
            bundle.putInt("pmnli", this.zzcqi);
            bundle.putInt("pmnll", this.zzcqj);
            return bundle;
        }
    }
    
    public void zzh(final int zzcqi, final int zzcqj) {
        synchronized (this.zzakd) {
            this.zzcqi = zzcqi;
            this.zzcqj = zzcqj;
            this.zzapw.zza(this.zzcos, this);
        }
    }
}
