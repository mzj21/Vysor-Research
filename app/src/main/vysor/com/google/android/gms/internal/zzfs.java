// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.zzl;
import android.support.annotation.Nullable;
import java.util.Iterator;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import java.util.LinkedList;

@zziy
class zzfs
{
    private final String zzang;
    private final LinkedList<zza> zzbph;
    private AdRequestParcel zzbpi;
    private final int zzbpj;
    private boolean zzbpk;
    
    zzfs(final AdRequestParcel zzbpi, final String zzang, final int zzbpj) {
        zzac.zzy(zzbpi);
        zzac.zzy(zzang);
        this.zzbph = new LinkedList<zza>();
        this.zzbpi = zzbpi;
        this.zzang = zzang;
        this.zzbpj = zzbpj;
    }
    
    String getAdUnitId() {
        return this.zzang;
    }
    
    int getNetworkType() {
        return this.zzbpj;
    }
    
    int size() {
        return this.zzbph.size();
    }
    
    void zza(final zzfn zzfn, final AdRequestParcel adRequestParcel) {
        this.zzbph.add(new zza(zzfn, adRequestParcel));
    }
    
    void zzb(final zzfn zzfn) {
        final zza zza = new zza(zzfn);
        this.zzbph.add(zza);
        zza.zzmt();
    }
    
    AdRequestParcel zzmo() {
        return this.zzbpi;
    }
    
    int zzmp() {
        final Iterator<zza> iterator = (Iterator<zza>)this.zzbph.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            int n2;
            if (iterator.next().zzbpp) {
                n2 = n + 1;
            }
            else {
                n2 = n;
            }
            n = n2;
        }
        return n;
    }
    
    void zzmq() {
        final Iterator<zza> iterator = this.zzbph.iterator();
        while (iterator.hasNext()) {
            iterator.next().zzmt();
        }
    }
    
    void zzmr() {
        this.zzbpk = true;
    }
    
    boolean zzms() {
        return this.zzbpk;
    }
    
    zza zzp(@Nullable final AdRequestParcel zzbpi) {
        if (zzbpi != null) {
            this.zzbpi = zzbpi;
        }
        return this.zzbph.remove();
    }
    
    class zza
    {
        zzl zzbpl;
        @Nullable
        AdRequestParcel zzbpm;
        zzfo zzbpn;
        long zzbpo;
        boolean zzbpp;
        boolean zzbpq;
        
        zza(final zzfn zzfn) {
            this.zzbpl = zzfn.zzbg(zzfs.this.zzang);
            (this.zzbpn = new zzfo()).zzc(this.zzbpl);
        }
        
        zza(final zzfs zzfs, final zzfn zzfn, final AdRequestParcel zzbpm) {
            this(zzfs, zzfn);
            this.zzbpm = zzbpm;
        }
        
        void zzmt() {
            if (!this.zzbpp) {
                AdRequestParcel adRequestParcel;
                if (this.zzbpm != null) {
                    adRequestParcel = this.zzbpm;
                }
                else {
                    adRequestParcel = zzfs.this.zzbpi;
                }
                this.zzbpq = this.zzbpl.zzb(zzfq.zzl(adRequestParcel));
                this.zzbpp = true;
                this.zzbpo = zzu.zzgf().currentTimeMillis();
            }
        }
    }
}
