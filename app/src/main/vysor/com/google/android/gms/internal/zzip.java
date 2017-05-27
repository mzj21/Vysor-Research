// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ListIterator;
import android.os.Bundle;
import java.util.Collection;
import java.util.ArrayList;
import com.google.android.gms.ads.internal.request.AutoClickProtectionConfigurationParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import org.json.JSONObject;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.formats.zzi;
import com.google.ads.mediation.admob.AdMobAdapter;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.ads.internal.zzn;
import java.util.concurrent.CountDownLatch;
import java.util.Iterator;
import android.text.TextUtils;
import java.util.List;
import android.content.Context;

@zziy
public class zzip extends zzik
{
    private final zzdq zzalg;
    private zzgq zzals;
    private final zzlt zzbkr;
    private zzgh zzbsv;
    zzgf zzcdh;
    protected zzgl zzcdi;
    private boolean zzcdj;
    
    zzip(final Context context, final zzke.zza zza, final zzgq zzals, final zzil.zza zza2, final zzdq zzalg, final zzlt zzbkr) {
        super(context, zza, zza2);
        this.zzals = zzals;
        this.zzbsv = zza.zzcof;
        this.zzalg = zzalg;
        this.zzbkr = zzbkr;
    }
    
    private static String zza(final zzgl zzgl) {
        final String zzbro = zzgl.zzbte.zzbro;
        return new StringBuilder(33 + String.valueOf(zzbro).length()).append(zzbro).append(".").append(zzao(zzgl.zzbtd)).append(".").append(zzgl.zzbtj).toString();
    }
    
    private static int zzao(final int n) {
        int n2 = 0;
        switch (n) {
            default: {
                n2 = 6;
                break;
            }
            case 0: {
                n2 = 0;
                break;
            }
            case 1: {
                n2 = 1;
                break;
            }
            case 3: {
                n2 = 2;
                break;
            }
            case 4: {
                n2 = 3;
                break;
            }
            case -1: {
                n2 = 4;
                break;
            }
            case 5: {
                n2 = 5;
                break;
            }
        }
        return n2;
    }
    
    private static String zzg(final List<zzgl> list) {
        String s;
        if (list == null) {
            s = "".toString();
        }
        else {
            final Iterator<zzgl> iterator = list.iterator();
            String string = "";
            while (iterator.hasNext()) {
                final zzgl zzgl = iterator.next();
                if (zzgl != null && zzgl.zzbte != null && !TextUtils.isEmpty((CharSequence)zzgl.zzbte.zzbro)) {
                    final String value = String.valueOf(string);
                    final String value2 = String.valueOf(zza(zzgl));
                    string = new StringBuilder(1 + String.valueOf(value).length() + String.valueOf(value2).length()).append(value).append(value2).append("_").toString();
                }
            }
            s = string.substring(0, Math.max(0, -1 + string.length()));
        }
        return s;
    }
    
    private void zzrd() throws zza {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        zzkr.zzcrf.post((Runnable)new Runnable() {
            @Override
            public void run() {
                synchronized (zzip.this.zzccn) {
                    zzip.this.zzcdj = zzn.zza(zzip.this.zzbkr, zzip.this.zzcdi, countDownLatch);
                }
            }
        });
        try {
            countDownLatch.await(10L, TimeUnit.SECONDS);
            synchronized (this.zzccn) {
                if (!this.zzcdj) {
                    throw new zza("View could not be prepared", 0);
                }
            }
        }
        catch (InterruptedException ex) {
            final String value = String.valueOf(ex);
            throw new zza(new StringBuilder(38 + String.valueOf(value).length()).append("Interrupted while waiting for latch : ").append(value).toString(), 0);
        }
        if (this.zzbkr.isDestroyed()) {
            throw new zza("Assets not loaded, web view is destroyed", 0);
        }
    }
    // monitorexit(o)
    
    @Override
    public void onStop() {
        synchronized (this.zzccn) {
            super.onStop();
            if (this.zzcdh != null) {
                this.zzcdh.cancel();
            }
        }
    }
    
    @Override
    protected zzke zzam(final int n) {
        final AdRequestInfoParcel zzcix = this.zzcck.zzcix;
        final AdRequestParcel zzcfu = zzcix.zzcfu;
        final zzlt zzbkr = this.zzbkr;
        final List<String> zzbsd = this.zzccl.zzbsd;
        final List<String> zzbse = this.zzccl.zzbse;
        final List<String> zzche = this.zzccl.zzche;
        final int orientation = this.zzccl.orientation;
        final long zzbsj = this.zzccl.zzbsj;
        final String zzcfx = zzcix.zzcfx;
        final boolean zzchc = this.zzccl.zzchc;
        zzgg zzbte;
        if (this.zzcdi != null) {
            zzbte = this.zzcdi.zzbte;
        }
        else {
            zzbte = null;
        }
        zzgr zzbtf;
        if (this.zzcdi != null) {
            zzbtf = this.zzcdi.zzbtf;
        }
        else {
            zzbtf = null;
        }
        String s;
        if (this.zzcdi != null) {
            s = this.zzcdi.zzbtg;
        }
        else {
            s = AdMobAdapter.class.getName();
        }
        final zzgh zzbsv = this.zzbsv;
        zzgj zzbth;
        if (this.zzcdi != null) {
            zzbth = this.zzcdi.zzbth;
        }
        else {
            zzbth = null;
        }
        final long zzchd = this.zzccl.zzchd;
        final AdSizeParcel zzaqz = this.zzcck.zzaqz;
        final long zzchb = this.zzccl.zzchb;
        final long zzcoj = this.zzcck.zzcoj;
        final long zzchg = this.zzccl.zzchg;
        final String zzchh = this.zzccl.zzchh;
        final JSONObject zzcod = this.zzcck.zzcod;
        final RewardItemParcel zzchr = this.zzccl.zzchr;
        final List<String> zzchs = this.zzccl.zzchs;
        final List<String> zzcht = this.zzccl.zzcht;
        final boolean b = this.zzbsv != null && this.zzbsv.zzbso;
        final AutoClickProtectionConfigurationParcel zzchv = this.zzccl.zzchv;
        String zzg;
        if (this.zzcdh != null) {
            zzg = zzg(this.zzcdh.zzne());
        }
        else {
            zzg = null;
        }
        return new zzke(zzcfu, zzbkr, zzbsd, n, zzbse, zzche, orientation, zzbsj, zzcfx, zzchc, zzbte, zzbtf, s, zzbsv, zzbth, zzchd, zzaqz, zzchb, zzcoj, zzchg, zzchh, zzcod, null, zzchr, zzchs, zzcht, b, zzchv, zzg, this.zzccl.zzbsg, this.zzccl.zzchy);
    }
    
    @Override
    protected void zzh(final long n) throws zza {
        ArrayList<zzgg> list;
        while (true) {
            while (true) {
                Label_0269: {
                    synchronized (this.zzccn) {
                        this.zzcdh = this.zzi(n);
                        // monitorexit(this.zzccn)
                        list = new ArrayList<zzgg>(this.zzbsv.zzbsb);
                        final Bundle zzawn = this.zzcck.zzcix.zzcfu.zzawn;
                        if (zzawn == null) {
                            break Label_0269;
                        }
                        final Bundle bundle = zzawn.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
                        if (bundle == null) {
                            break Label_0269;
                        }
                        final int boolean1 = bundle.getBoolean("_skipMediation") ? 1 : 0;
                        if (boolean1 != 0) {
                            final ListIterator<Object> listIterator = list.listIterator();
                            while (listIterator.hasNext()) {
                                if (!listIterator.next().zzbrn.contains("com.google.ads.mediation.admob.AdMobAdapter")) {
                                    listIterator.remove();
                                }
                            }
                        }
                    }
                    break;
                }
                final int boolean1 = 0;
                continue;
            }
        }
        this.zzcdi = this.zzcdh.zzd(list);
        switch (this.zzcdi.zzbtd) {
            default: {
                throw new zza(new StringBuilder(40).append("Unexpected mediation result: ").append(this.zzcdi.zzbtd).toString(), 0);
            }
            case 1: {
                throw new zza("No fill from any mediation ad networks.", 3);
            }
            case 0: {
                if (this.zzcdi.zzbte != null && this.zzcdi.zzbte.zzbrw != null) {
                    this.zzrd();
                }
            }
        }
    }
    
    zzgf zzi(final long n) {
        zzgf zzgf;
        if (this.zzbsv.zzbsm != -1) {
            zzgf = new zzgn(this.mContext, this.zzcck.zzcix, this.zzals, this.zzbsv, this.zzccl.zzaxl, this.zzccl.zzaxn, n, zzdi.zzbel.get(), 2);
        }
        else {
            zzgf = new zzgo(this.mContext, this.zzcck.zzcix, this.zzals, this.zzbsv, this.zzccl.zzaxl, this.zzccl.zzaxn, n, zzdi.zzbel.get(), this.zzalg);
        }
        return zzgf;
    }
}
