// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzu;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import android.support.annotation.Nullable;
import java.util.Map;
import java.util.List;

@zziy
public class zzdq
{
    private final Object zzakd;
    boolean zzbhy;
    private final List<zzdo> zzbip;
    private final Map<String, String> zzbiq;
    private String zzbir;
    private zzdo zzbis;
    @Nullable
    private zzdq zzbit;
    
    public zzdq(final boolean zzbhy, final String s, final String s2) {
        this.zzbip = new LinkedList<zzdo>();
        this.zzbiq = new LinkedHashMap<String, String>();
        this.zzakd = new Object();
        this.zzbhy = zzbhy;
        this.zzbiq.put("action", s);
        this.zzbiq.put("ad_format", s2);
    }
    
    public boolean zza(final zzdo zzdo, final long n, final String... array) {
        synchronized (this.zzakd) {
            for (int length = array.length, i = 0; i < length; ++i) {
                this.zzbip.add(new zzdo(n, array[i], zzdo));
            }
            return true;
        }
    }
    
    public boolean zza(@Nullable final zzdo zzdo, final String... array) {
        return this.zzbhy && zzdo != null && this.zza(zzdo, zzu.zzgf().elapsedRealtime(), array);
    }
    
    public void zzav(final String zzbir) {
        if (this.zzbhy) {
            synchronized (this.zzakd) {
                this.zzbir = zzbir;
            }
        }
    }
    
    @Nullable
    public zzdo zzc(final long n) {
        final boolean zzbhy = this.zzbhy;
        zzdo zzdo = null;
        if (zzbhy) {
            zzdo = new zzdo(n, null, null);
        }
        return zzdo;
    }
    
    public void zzc(@Nullable final zzdq zzbit) {
        synchronized (this.zzakd) {
            this.zzbit = zzbit;
        }
    }
    
    public void zzh(final String s, final String s2) {
        if (this.zzbhy && !TextUtils.isEmpty((CharSequence)s2)) {
            final zzdk zztm = zzu.zzgd().zztm();
            if (zztm != null) {
                synchronized (this.zzakd) {
                    zztm.zzat(s).zza(this.zzbiq, s, s2);
                }
            }
        }
    }
    
    public zzdo zzla() {
        return this.zzc(zzu.zzgf().elapsedRealtime());
    }
    
    public void zzlb() {
        synchronized (this.zzakd) {
            this.zzbis = this.zzla();
        }
    }
    
    public String zzlc() {
        final StringBuilder sb = new StringBuilder();
        synchronized (this.zzakd) {
            for (final zzdo zzdo : this.zzbip) {
                final long time = zzdo.getTime();
                final String zzkx = zzdo.zzkx();
                final zzdo zzky = zzdo.zzky();
                if (zzky != null && time > 0L) {
                    sb.append(zzkx).append('.').append(time - zzky.getTime()).append(',');
                }
            }
        }
        this.zzbip.clear();
        if (!TextUtils.isEmpty((CharSequence)this.zzbir)) {
            sb.append(this.zzbir);
        }
        else if (sb.length() > 0) {
            sb.setLength(-1 + sb.length());
        }
        // monitorexit(o)
        return sb.toString();
    }
    
    public zzdo zzld() {
        synchronized (this.zzakd) {
            return this.zzbis;
        }
    }
    
    Map<String, String> zzm() {
        Map<String, String> map;
        synchronized (this.zzakd) {
            final zzdk zztm = zzu.zzgd().zztm();
            if (zztm == null || this.zzbit == null) {
                map = this.zzbiq;
            }
            else {
                map = zztm.zza(this.zzbiq, this.zzbit.zzm());
            }
        }
        return map;
    }
}
