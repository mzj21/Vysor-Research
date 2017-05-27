// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.common.util.zzh;
import android.os.Build$VERSION;
import com.google.android.gms.internal.zzlo;
import com.google.android.gms.internal.zzfi;
import com.google.android.gms.internal.zzla;
import com.google.android.gms.internal.zzgm;
import com.google.android.gms.ads.internal.overlay.zzr;
import com.google.android.gms.ads.internal.overlay.zzq;
import com.google.android.gms.internal.zzkz;
import com.google.android.gms.internal.zzgc;
import com.google.android.gms.internal.zzfq;
import com.google.android.gms.ads.internal.purchase.zzi;
import com.google.android.gms.internal.zzll;
import com.google.android.gms.internal.zzdh;
import com.google.android.gms.internal.zzdg;
import com.google.android.gms.internal.zzdf;
import com.google.android.gms.internal.zzji;
import com.google.android.gms.internal.zzkv;
import com.google.android.gms.internal.zzdl;
import com.google.android.gms.internal.zzkh;
import com.google.android.gms.internal.zzct;
import com.google.android.gms.internal.zzks;
import com.google.android.gms.internal.zzlv;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzil;
import com.google.android.gms.ads.internal.overlay.zze;
import com.google.android.gms.ads.internal.overlay.zza;
import com.google.android.gms.internal.zziy;

@zziy
public class zzu
{
    private static final Object zzaok;
    private static zzu zzapn;
    private final zza zzapo;
    private final com.google.android.gms.ads.internal.request.zza zzapp;
    private final zze zzapq;
    private final zzil zzapr;
    private final zzkr zzaps;
    private final zzlv zzapt;
    private final zzks zzapu;
    private final zzct zzapv;
    private final zzkh zzapw;
    private final com.google.android.gms.ads.internal.cache.zza zzapx;
    private final com.google.android.gms.common.util.zze zzapy;
    private final zzg zzapz;
    private final zzdl zzaqa;
    private final zzkv zzaqb;
    private final zzji zzaqc;
    private final zzdf zzaqd;
    private final zzdg zzaqe;
    private final zzdh zzaqf;
    private final zzll zzaqg;
    private final zzi zzaqh;
    private final zzfq zzaqi;
    private final zzgc zzaqj;
    private final zzkz zzaqk;
    private final zzq zzaql;
    private final zzr zzaqm;
    private final zzgm zzaqn;
    private final zzla zzaqo;
    private final zzp zzaqp;
    private final zzfi zzaqq;
    private final zzlo zzaqr;
    
    static {
        zzaok = new Object();
        zza(new zzu());
    }
    
    protected zzu() {
        this.zzapo = new zza();
        this.zzapp = new com.google.android.gms.ads.internal.request.zza();
        this.zzapq = new zze();
        this.zzapr = new zzil();
        this.zzaps = new zzkr();
        this.zzapt = new zzlv();
        this.zzapu = zzks.zzbe(Build$VERSION.SDK_INT);
        this.zzapv = new zzct();
        this.zzapw = new zzkh(this.zzaps);
        this.zzapx = new com.google.android.gms.ads.internal.cache.zza();
        this.zzapy = new zzh();
        this.zzapz = new zzg();
        this.zzaqa = new zzdl();
        this.zzaqb = new zzkv();
        this.zzaqc = new zzji();
        this.zzaqd = new zzdf();
        this.zzaqe = new zzdg();
        this.zzaqf = new zzdh();
        this.zzaqg = new zzll();
        this.zzaqh = new zzi();
        this.zzaqi = new zzfq();
        this.zzaqj = new zzgc();
        this.zzaqk = new zzkz();
        this.zzaql = new zzq();
        this.zzaqm = new zzr();
        this.zzaqn = new zzgm();
        this.zzaqo = new zzla();
        this.zzaqp = new zzp();
        this.zzaqq = new zzfi();
        this.zzaqr = new zzlo();
    }
    
    protected static void zza(final zzu zzapn) {
        synchronized (zzu.zzaok) {
            zzu.zzapn = zzapn;
        }
    }
    
    private static zzu zzfu() {
        synchronized (zzu.zzaok) {
            return zzu.zzapn;
        }
    }
    
    public static com.google.android.gms.ads.internal.request.zza zzfv() {
        return zzfu().zzapp;
    }
    
    public static zza zzfw() {
        return zzfu().zzapo;
    }
    
    public static zze zzfx() {
        return zzfu().zzapq;
    }
    
    public static zzil zzfy() {
        return zzfu().zzapr;
    }
    
    public static zzkr zzfz() {
        return zzfu().zzaps;
    }
    
    public static zzlv zzga() {
        return zzfu().zzapt;
    }
    
    public static zzks zzgb() {
        return zzfu().zzapu;
    }
    
    public static zzct zzgc() {
        return zzfu().zzapv;
    }
    
    public static zzkh zzgd() {
        return zzfu().zzapw;
    }
    
    public static com.google.android.gms.ads.internal.cache.zza zzge() {
        return zzfu().zzapx;
    }
    
    public static com.google.android.gms.common.util.zze zzgf() {
        return zzfu().zzapy;
    }
    
    public static zzdl zzgg() {
        return zzfu().zzaqa;
    }
    
    public static zzkv zzgh() {
        return zzfu().zzaqb;
    }
    
    public static zzji zzgi() {
        return zzfu().zzaqc;
    }
    
    public static zzdg zzgj() {
        return zzfu().zzaqe;
    }
    
    public static zzdf zzgk() {
        return zzfu().zzaqd;
    }
    
    public static zzdh zzgl() {
        return zzfu().zzaqf;
    }
    
    public static zzll zzgm() {
        return zzfu().zzaqg;
    }
    
    public static zzi zzgn() {
        return zzfu().zzaqh;
    }
    
    public static zzfq zzgo() {
        return zzfu().zzaqi;
    }
    
    public static zzkz zzgp() {
        return zzfu().zzaqk;
    }
    
    public static zzq zzgq() {
        return zzfu().zzaql;
    }
    
    public static zzr zzgr() {
        return zzfu().zzaqm;
    }
    
    public static zzgm zzgs() {
        return zzfu().zzaqn;
    }
    
    public static zzp zzgt() {
        return zzfu().zzaqp;
    }
    
    public static zzla zzgu() {
        return zzfu().zzaqo;
    }
    
    public static zzg zzgv() {
        return zzfu().zzapz;
    }
    
    public static zzfi zzgw() {
        return zzfu().zzaqq;
    }
    
    public static zzlo zzgx() {
        return zzfu().zzaqr;
    }
}
