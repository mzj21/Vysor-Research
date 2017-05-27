// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import android.content.Context;

@zziy
public class zzgo implements zzgf
{
    private final Context mContext;
    private final Object zzakd;
    private final zzdq zzalg;
    private final zzgq zzals;
    private final boolean zzatk;
    private final boolean zzazd;
    private final zzgh zzbsv;
    private final AdRequestInfoParcel zzbtk;
    private final long zzbtl;
    private final long zzbtm;
    private boolean zzbto;
    private List<zzgl> zzbtq;
    private zzgk zzbtu;
    
    public zzgo(final Context mContext, final AdRequestInfoParcel zzbtk, final zzgq zzals, final zzgh zzbsv, final boolean zzatk, final boolean zzazd, final long zzbtl, final long zzbtm, final zzdq zzalg) {
        this.zzakd = new Object();
        this.zzbto = false;
        this.zzbtq = new ArrayList<zzgl>();
        this.mContext = mContext;
        this.zzbtk = zzbtk;
        this.zzals = zzals;
        this.zzbsv = zzbsv;
        this.zzatk = zzatk;
        this.zzazd = zzazd;
        this.zzbtl = zzbtl;
        this.zzbtm = zzbtm;
        this.zzalg = zzalg;
    }
    
    @Override
    public void cancel() {
        synchronized (this.zzakd) {
            this.zzbto = true;
            if (this.zzbtu != null) {
                this.zzbtu.cancel();
            }
        }
    }
    
    @Override
    public zzgl zzd(final List<zzgg> list) {
        zzb.zzdd("Starting mediation.");
        final ArrayList<String> list2 = new ArrayList<String>();
        final zzdo zzla = this.zzalg.zzla();
        final Iterator<zzgg> iterator = list.iterator();
        zzgl zza = null;
        zzgg zzgg;
        String value;
        String concat;
        Iterator<String> iterator2;
        String s = null;
        zzdo zzla2 = null;
        Label_0078_Outer:Label_0095_Outer:
        while (true) {
            if (!iterator.hasNext()) {
                if (!list2.isEmpty()) {
                    this.zzalg.zzh("mediation_networks_fail", TextUtils.join((CharSequence)",", (Iterable)list2));
                }
                zza = new zzgl(1);
                return zza;
            }
            zzgg = iterator.next();
            value = String.valueOf(zzgg.zzbrm);
            while (true) {
                Label_0158: {
                    if (value.length() == 0) {
                        break Label_0158;
                    }
                    concat = "Trying mediation network: ".concat(value);
                    while (true) {
                        zzb.zzde(concat);
                        iterator2 = zzgg.zzbrn.iterator();
                        if (!iterator2.hasNext()) {
                            continue Label_0078_Outer;
                        }
                        s = iterator2.next();
                        zzla2 = this.zzalg.zzla();
                        synchronized (this.zzakd) {
                            if (this.zzbto) {
                                zza = new zzgl(-1);
                            }
                            else {
                                this.zzbtu = new zzgk(this.mContext, s, this.zzals, this.zzbsv, zzgg, this.zzbtk.zzcfu, this.zzbtk.zzaqz, this.zzbtk.zzaqv, this.zzatk, this.zzazd, this.zzbtk.zzarn, this.zzbtk.zzarr);
                                // monitorexit(this.zzakd)
                                zza = this.zzbtu.zza(this.zzbtl, this.zzbtm);
                                this.zzbtq.add(zza);
                                if (zza.zzbtd != 0) {
                                    break Label_0158;
                                }
                                zzb.zzdd("Adapter succeeded.");
                                this.zzalg.zzh("mediation_network_succeed", s);
                                if (!list2.isEmpty()) {
                                    this.zzalg.zzh("mediation_networks_fail", TextUtils.join((CharSequence)",", (Iterable)list2));
                                }
                                this.zzalg.zza(zzla2, "mls");
                                this.zzalg.zza(zzla, "ttm");
                            }
                            return zza;
                            concat = new String("Trying mediation network: ");
                            continue Label_0095_Outer;
                        }
                        break;
                    }
                }
                list2.add(s);
                this.zzalg.zza(zzla2, "mlf");
                if (zza.zzbtf != null) {
                    zzkr.zzcrf.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            try {
                                zza.zzbtf.destroy();
                            }
                            catch (RemoteException ex) {
                                zzb.zzd("Could not destroy mediation adapter.", (Throwable)ex);
                            }
                        }
                    });
                }
                continue;
            }
        }
    }
    
    @Override
    public List<zzgl> zzne() {
        return this.zzbtq;
    }
}
