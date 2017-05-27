// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Future;
import java.util.Collection;
import android.annotation.TargetApi;
import android.security.NetworkSecurityPolicy;
import com.google.android.gms.ads.internal.zzu;
import android.os.Looper;
import com.google.android.gms.common.util.zzs;
import java.util.Iterator;
import java.util.ArrayList;
import android.os.Bundle;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.content.res.Resources;
import java.util.HashMap;
import java.util.HashSet;
import java.math.BigInteger;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.content.Context;

@zziy
public class zzkh implements zzct.zzb, zzkp.zzb
{
    private Context mContext;
    private final Object zzakd;
    private zzcm zzaln;
    private VersionInfoParcel zzanh;
    private boolean zzaom;
    private zzcs zzaur;
    private String zzbnw;
    private boolean zzckt;
    private boolean zzcku;
    private boolean zzclc;
    private final String zzcpl;
    private final zzki zzcpm;
    private BigInteger zzcpn;
    private final HashSet<zzkf> zzcpo;
    private final HashMap<String, zzkk> zzcpp;
    private boolean zzcpq;
    private int zzcpr;
    private zzdk zzcps;
    private zzcu zzcpt;
    private String zzcpu;
    private Boolean zzcpv;
    private boolean zzcpw;
    private boolean zzcpx;
    private boolean zzcpy;
    private String zzcpz;
    private long zzcqa;
    private long zzcqb;
    private int zzcqc;
    
    public zzkh(final zzkr zzkr) {
        this.zzakd = new Object();
        this.zzcpn = BigInteger.ONE;
        this.zzcpo = new HashSet<zzkf>();
        this.zzcpp = new HashMap<String, zzkk>();
        this.zzcpq = false;
        this.zzckt = true;
        this.zzcpr = 0;
        this.zzaom = false;
        this.zzcps = null;
        this.zzcku = true;
        this.zzcpt = null;
        this.zzaur = null;
        this.zzcpv = null;
        this.zzcpw = false;
        this.zzcpx = false;
        this.zzclc = false;
        this.zzcpy = false;
        this.zzcpz = "";
        this.zzcqa = 0L;
        this.zzcqb = 0L;
        this.zzcqc = -1;
        this.zzcpl = zzkr.zzui();
        this.zzcpm = new zzki(this.zzcpl);
    }
    
    public Resources getResources() {
        Resources resources;
        if (this.zzanh.zzctu) {
            resources = this.mContext.getResources();
        }
        else {
            try {
                final zzsu zza = zzsu.zza(this.mContext, zzsu.Oy, "com.google.android.gms.ads.dynamite");
                resources = null;
                if (zza != null) {
                    resources = zza.zzbdy().getResources();
                }
            }
            catch (zzsu.zza zza2) {
                zzb.zzd("Cannot load resource from dynamite apk or local jar", zza2);
                resources = null;
            }
        }
        return resources;
    }
    
    public String getSessionId() {
        return this.zzcpl;
    }
    
    public Bundle zza(final Context context, final zzkj zzkj, final String s) {
        final Bundle bundle;
        final Bundle bundle2;
        synchronized (this.zzakd) {
            bundle = new Bundle();
            bundle.putBundle("app", this.zzcpm.zze(context, s));
            bundle2 = new Bundle();
            for (final String s2 : this.zzcpp.keySet()) {
                bundle2.putBundle(s2, this.zzcpp.get(s2).toBundle());
            }
        }
        bundle.putBundle("slots", bundle2);
        final ArrayList<Bundle> list = new ArrayList<Bundle>();
        final Iterator<zzkf> iterator2 = this.zzcpo.iterator();
        while (iterator2.hasNext()) {
            list.add(iterator2.next().toBundle());
        }
        bundle.putParcelableArrayList("ads", (ArrayList)list);
        zzkj.zza(this.zzcpo);
        this.zzcpo.clear();
        // monitorexit(o)
        return bundle;
    }
    
    public void zza(final zzkf zzkf) {
        synchronized (this.zzakd) {
            this.zzcpo.add(zzkf);
        }
    }
    
    public void zza(final String s, final zzkk zzkk) {
        synchronized (this.zzakd) {
            this.zzcpp.put(s, zzkk);
        }
    }
    
    public void zza(final Thread thread) {
        zzix.zza(this.mContext, thread, this.zzanh);
    }
    
    public void zza(final Throwable t, final String s) {
        new zzix(this.mContext, this.zzanh, null, null).zza(t, s);
    }
    
    public zzcu zzaa(final Context context) {
        zzcu zzcpt;
        if (!zzdi.zzbcd.get() || !zzs.zzaxn() || this.zztj()) {
            zzcpt = null;
        }
        else {
            synchronized (this.zzakd) {
                if (Looper.getMainLooper() == null || context == null) {
                    // monitorexit(this.zzakd)
                    zzcpt = null;
                }
                else {
                    if (this.zzaur == null) {
                        this.zzaur = new zzcs();
                    }
                    if (this.zzcpt == null) {
                        this.zzcpt = new zzcu(this.zzaur, new zzix(this.mContext, this.zzanh, null, null));
                    }
                    this.zzcpt.zzim();
                    zzcpt = this.zzcpt;
                }
            }
        }
        return zzcpt;
    }
    
    public void zzaf(final boolean zzcku) {
        synchronized (this.zzakd) {
            if (this.zzcku != zzcku) {
                zzkp.zze(this.mContext, zzcku);
            }
            this.zzcku = zzcku;
            final zzcu zzaa = this.zzaa(this.mContext);
            if (zzaa != null && !zzaa.isAlive()) {
                zzb.zzde("start fetching content...");
                zzaa.zzim();
            }
        }
    }
    
    public void zzag(final boolean zzcpy) {
        this.zzcpy = zzcpy;
    }
    
    public void zzah(final boolean zzcpw) {
        synchronized (this.zzakd) {
            this.zzcpw = zzcpw;
        }
    }
    
    @TargetApi(23)
    public void zzb(final Context context, final VersionInfoParcel zzanh) {
        synchronized (this.zzakd) {
            if (!this.zzaom) {
                this.mContext = context.getApplicationContext();
                this.zzanh = zzanh;
                zzu.zzgc().zza((zzct.zzb)this);
                zzkp.zza(context, (zzkp.zzb)this);
                zzkp.zzb(context, (zzkp.zzb)this);
                zzkp.zzc(context, (zzkp.zzb)this);
                zzkp.zzd(context, (zzkp.zzb)this);
                zzkp.zze(context, (zzkp.zzb)this);
                zzkp.zzf(context, (zzkp.zzb)this);
                zzkp.zzg(context, (zzkp.zzb)this);
                this.zza(Thread.currentThread());
                this.zzbnw = zzu.zzfz().zzg(context, zzanh.zzcs);
                if (zzs.zzaxv() && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted()) {
                    this.zzcpx = true;
                }
                this.zzaln = new zzcm(context.getApplicationContext(), this.zzanh, zzu.zzfz().zzc(context, zzanh));
                this.zztz();
                zzu.zzgn().zzt(this.mContext);
                this.zzaom = true;
            }
        }
    }
    
    public void zzb(final Boolean zzcpv) {
        synchronized (this.zzakd) {
            this.zzcpv = zzcpv;
        }
    }
    
    public void zzb(final HashSet<zzkf> set) {
        synchronized (this.zzakd) {
            this.zzcpo.addAll((Collection<?>)set);
        }
    }
    
    public Future zzbc(final int zzcqc) {
        synchronized (this.zzakd) {
            this.zzcqc = zzcqc;
            return zzkp.zza(this.mContext, zzcqc);
        }
    }
    
    public Future zzc(final Context context, final boolean zzckt) {
        Future zzc;
        synchronized (this.zzakd) {
            if (zzckt != this.zzckt) {
                this.zzckt = zzckt;
                zzc = zzkp.zzc(context, zzckt);
            }
            else {
                // monitorexit(this.zzakd)
                zzc = null;
            }
        }
        return zzc;
    }
    
    public Future zzct(final String zzcpu) {
        final Object zzakd = this.zzakd;
        // monitorenter(zzakd)
        while (true) {
            if (zzcpu != null) {
                Future zzf;
                try {
                    if (!zzcpu.equals(this.zzcpu)) {
                        this.zzcpu = zzcpu;
                        zzf = zzkp.zzf(this.mContext, zzcpu);
                    }
                    else {
                        // monitorexit(zzakd)
                        zzf = null;
                    }
                }
                finally {
                }
                // monitorexit(zzakd)
                return zzf;
            }
            continue;
        }
    }
    
    public Future zzd(final Context context, final String zzcpz) {
        this.zzcqa = zzu.zzgf().currentTimeMillis();
        final Object zzakd = this.zzakd;
        // monitorenter(zzakd)
        while (true) {
            if (zzcpz != null) {
                Future zza;
                try {
                    if (!zzcpz.equals(this.zzcpz)) {
                        this.zzcpz = zzcpz;
                        zza = zzkp.zza(context, zzcpz, this.zzcqa);
                    }
                    else {
                        // monitorexit(zzakd)
                        zza = null;
                    }
                }
                finally {
                }
                // monitorexit(zzakd)
                return zza;
            }
            continue;
        }
    }
    
    public Future zzd(final Context context, final boolean zzclc) {
        Future zzf;
        synchronized (this.zzakd) {
            if (zzclc != this.zzclc) {
                this.zzclc = zzclc;
                zzf = zzkp.zzf(context, zzclc);
            }
            else {
                // monitorexit(this.zzakd)
                zzf = null;
            }
        }
        return zzf;
    }
    
    @Override
    public void zzh(final Bundle bundle) {
        synchronized (this.zzakd) {
            this.zzckt = bundle.getBoolean("use_https", this.zzckt);
            this.zzcpr = bundle.getInt("webview_cache_version", this.zzcpr);
            if (bundle.containsKey("content_url_opted_out")) {
                this.zzaf(bundle.getBoolean("content_url_opted_out"));
            }
            if (bundle.containsKey("content_url_hashes")) {
                this.zzcpu = bundle.getString("content_url_hashes");
            }
            this.zzclc = bundle.getBoolean("auto_collect_location", this.zzclc);
            String zzcpz;
            if (bundle.containsKey("app_settings_json")) {
                zzcpz = bundle.getString("app_settings_json");
            }
            else {
                zzcpz = this.zzcpz;
            }
            this.zzcpz = zzcpz;
            this.zzcqa = bundle.getLong("app_settings_last_update_ms", this.zzcqa);
            this.zzcqb = bundle.getLong("app_last_background_time_ms", this.zzcqb);
            this.zzcqc = bundle.getInt("request_in_session_count", this.zzcqc);
        }
    }
    
    @Override
    public void zzk(final boolean b) {
        if (b) {
            if (zzu.zzgf().currentTimeMillis() - this.zzcqb > zzdi.zzbdd.get()) {
                this.zzcpm.zzbd(-1);
            }
            else {
                this.zzcpm.zzbd(this.zzcqc);
            }
        }
        else {
            this.zzo(zzu.zzgf().currentTimeMillis());
            this.zzbc(this.zzcpm.zztu());
        }
    }
    
    public Future zzo(final long zzcqb) {
        Future zza;
        synchronized (this.zzakd) {
            if (this.zzcqb < zzcqb) {
                this.zzcqb = zzcqb;
                zza = zzkp.zza(this.mContext, zzcqb);
            }
            else {
                // monitorexit(this.zzakd)
                zza = null;
            }
        }
        return zza;
    }
    
    public boolean zztj() {
        synchronized (this.zzakd) {
            return this.zzcku;
        }
    }
    
    public String zztk() {
        synchronized (this.zzakd) {
            final String string = this.zzcpn.toString();
            this.zzcpn = this.zzcpn.add(BigInteger.ONE);
            return string;
        }
    }
    
    public zzki zztl() {
        synchronized (this.zzakd) {
            return this.zzcpm;
        }
    }
    
    public zzdk zztm() {
        synchronized (this.zzakd) {
            return this.zzcps;
        }
    }
    
    public boolean zztn() {
        synchronized (this.zzakd) {
            final boolean zzcpq = this.zzcpq;
            this.zzcpq = true;
            return zzcpq;
        }
    }
    
    public boolean zzto() {
        while (true) {
            synchronized (this.zzakd) {
                if (this.zzckt) {
                    return true;
                }
                if (this.zzcpx) {
                    return true;
                }
                return false;
            }
            return true;
            b = false;
            return b;
        }
    }
    
    public String zztp() {
        synchronized (this.zzakd) {
            return this.zzbnw;
        }
    }
    
    public String zztq() {
        synchronized (this.zzakd) {
            return this.zzcpu;
        }
    }
    
    public Boolean zztr() {
        synchronized (this.zzakd) {
            return this.zzcpv;
        }
    }
    
    public boolean zzts() {
        synchronized (this.zzakd) {
            return this.zzclc;
        }
    }
    
    public long zztt() {
        synchronized (this.zzakd) {
            return this.zzcqb;
        }
    }
    
    public int zztu() {
        synchronized (this.zzakd) {
            return this.zzcqc;
        }
    }
    
    public boolean zztv() {
        return this.zzcpy;
    }
    
    public zzkg zztw() {
        synchronized (this.zzakd) {
            return new zzkg(this.zzcpz, this.zzcqa);
        }
    }
    
    public zzcm zztx() {
        return this.zzaln;
    }
    
    public boolean zzty() {
        synchronized (this.zzakd) {
            return this.zzcpw;
        }
    }
    
    void zztz() {
        final zzdj zzdj = new zzdj(this.mContext, this.zzanh.zzcs);
        try {
            this.zzcps = zzu.zzgg().zza(zzdj);
        }
        catch (IllegalArgumentException ex) {
            zzb.zzd("Cannot initialize CSI reporter.", ex);
        }
    }
}
