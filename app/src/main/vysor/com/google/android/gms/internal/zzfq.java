// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import android.location.Location;
import com.google.android.gms.ads.internal.client.SearchAdRequestParcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import android.os.Parcelable$Creator;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.os.Parcel;
import java.util.Iterator;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import android.os.Bundle;
import com.google.android.gms.ads.internal.zzu;
import java.util.regex.Pattern;
import java.io.UnsupportedEncodingException;
import android.util.Base64;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.HashMap;
import android.support.annotation.Nullable;
import java.util.LinkedList;
import java.util.Map;

@zziy
public class zzfq
{
    private final Map<zzfr, zzfs> zzbpe;
    private final LinkedList<zzfr> zzbpf;
    @Nullable
    private zzfn zzbpg;
    
    public zzfq() {
        this.zzbpe = new HashMap<zzfr, zzfs>();
        this.zzbpf = new LinkedList<zzfr>();
    }
    
    private static void zza(final String s, final zzfr zzfr) {
        if (zzb.zzbf(2)) {
            zzkn.v(String.format(s, zzfr));
        }
    }
    
    private String[] zzbh(final String s) {
        String[] split;
        try {
            split = s.split("\u0000");
            for (int i = 0; i < split.length; ++i) {
                split[i] = new String(Base64.decode(split[i], 0), "UTF-8");
            }
        }
        catch (UnsupportedEncodingException ex) {
            split = new String[0];
        }
        return split;
    }
    
    private boolean zzbi(final String s) {
        try {
            return Pattern.matches(zzdi.zzbdp.get(), s);
        }
        catch (RuntimeException ex) {
            zzu.zzgd().zza(ex, "InterstitialAdPool.isExcludedAdUnit");
            return false;
        }
    }
    
    private static void zzc(final Bundle bundle, final String s) {
        final String[] split = s.split("/", 2);
        if (split.length != 0) {
            final String s2 = split[0];
            if (split.length == 1) {
                bundle.remove(s2);
            }
            else {
                final Bundle bundle2 = bundle.getBundle(s2);
                if (bundle2 != null) {
                    zzc(bundle2, split[1]);
                }
            }
        }
    }
    
    @Nullable
    static Bundle zzk(final AdRequestParcel adRequestParcel) {
        final Bundle zzawn = adRequestParcel.zzawn;
        Bundle bundle;
        if (zzawn == null) {
            bundle = null;
        }
        else {
            bundle = zzawn.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        }
        return bundle;
    }
    
    static AdRequestParcel zzl(final AdRequestParcel adRequestParcel) {
        final AdRequestParcel zzo = zzo(adRequestParcel);
        Bundle zzk = zzk(zzo);
        if (zzk == null) {
            zzk = new Bundle();
            zzo.zzawn.putBundle("com.google.ads.mediation.admob.AdMobAdapter", zzk);
        }
        zzk.putBoolean("_skipMediation", true);
        return zzo;
    }
    
    static boolean zzm(final AdRequestParcel adRequestParcel) {
        final Bundle zzawn = adRequestParcel.zzawn;
        boolean b = false;
        if (zzawn != null) {
            final Bundle bundle = zzawn.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
            b = false;
            if (bundle != null) {
                final boolean containsKey = bundle.containsKey("_skipMediation");
                b = false;
                if (containsKey) {
                    b = true;
                }
            }
        }
        return b;
    }
    
    private String zzmn() {
        String string;
        try {
            final StringBuilder sb = new StringBuilder();
            final Iterator<zzfr> iterator = (Iterator<zzfr>)this.zzbpf.iterator();
            while (iterator.hasNext()) {
                sb.append(Base64.encodeToString(iterator.next().toString().getBytes("UTF-8"), 0));
                if (iterator.hasNext()) {
                    sb.append("\u0000");
                }
            }
            return sb.toString();
        }
        catch (UnsupportedEncodingException ex) {
            string = "";
        }
        return string;
        StringBuilder sb = null;
        string = sb.toString();
        return string;
    }
    
    private static AdRequestParcel zzn(final AdRequestParcel adRequestParcel) {
        final AdRequestParcel zzo = zzo(adRequestParcel);
        final String[] split = zzdi.zzbdl.get().split(",");
        for (int length = split.length, i = 0; i < length; ++i) {
            zzc(zzo.zzawn, split[i]);
        }
        return zzo;
    }
    
    static AdRequestParcel zzo(final AdRequestParcel adRequestParcel) {
        final Parcel obtain = Parcel.obtain();
        adRequestParcel.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        final AdRequestParcel adRequestParcel2 = (AdRequestParcel)AdRequestParcel.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        AdRequestParcel.zzj(adRequestParcel2);
        return adRequestParcel2;
    }
    
    void flush() {
        while (this.zzbpf.size() > 0) {
            final zzfr zzfr = this.zzbpf.remove();
            final zzfs zzfs = this.zzbpe.get(zzfr);
            zza("Flushing interstitial queue for %s.", zzfr);
            while (zzfs.size() > 0) {
                zzfs.zzp(null).zzbpl.zzfa();
            }
            this.zzbpe.remove(zzfr);
        }
    }
    
    void restore() {
        if (this.zzbpg != null) {
            final SharedPreferences sharedPreferences = this.zzbpg.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0);
            this.flush();
            HashMap<Object, zzfr> hashMap;
            try {
                hashMap = new HashMap<Object, zzfr>();
                for (final Map.Entry<String, V> entry : sharedPreferences.getAll().entrySet()) {
                    if (!entry.getKey().equals("PoolKeys")) {
                        final zzfu zzbj = zzfu.zzbj((String)entry.getValue());
                        final zzfr zzfr = new zzfr(zzbj.zzaow, zzbj.zzang, zzbj.zzbpj);
                        if (this.zzbpe.containsKey(zzfr)) {
                            continue;
                        }
                        this.zzbpe.put(zzfr, new zzfs(zzbj.zzaow, zzbj.zzang, zzbj.zzbpj));
                        hashMap.put(zzfr.toString(), zzfr);
                        zza("Restored interstitial queue for %s.", zzfr);
                    }
                }
            }
            catch (Throwable t) {
                zzu.zzgd().zza(t, "InterstitialAdPool.restore");
                zzb.zzd("Malformed preferences value for InterstitialAdPool.", t);
                this.zzbpe.clear();
                this.zzbpf.clear();
                return;
            }
            final String[] zzbh = this.zzbh(sharedPreferences.getString("PoolKeys", ""));
            for (int length = zzbh.length, i = 0; i < length; ++i) {
                final zzfr zzfr2 = hashMap.get(zzbh[i]);
                if (this.zzbpe.containsKey(zzfr2)) {
                    this.zzbpf.add(zzfr2);
                }
            }
        }
    }
    
    void save() {
        if (this.zzbpg != null) {
            final SharedPreferences$Editor edit = this.zzbpg.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0).edit();
            edit.clear();
            for (final Map.Entry<zzfr, zzfs> entry : this.zzbpe.entrySet()) {
                final zzfr zzfr = entry.getKey();
                final zzfs zzfs = entry.getValue();
                if (zzfs.zzms()) {
                    edit.putString(zzfr.toString(), new zzfu(zzfs).zzmv());
                    zza("Saved interstitial queue for %s.", zzfr);
                }
            }
            edit.putString("PoolKeys", this.zzmn());
            edit.apply();
        }
    }
    
    @Nullable
    zzfs.zza zza(final AdRequestParcel adRequestParcel, final String s) {
        zzfs.zza zza;
        if (this.zzbi(s)) {
            zza = null;
        }
        else {
            final int zzcmd = new zzjh.zza(this.zzbpg.getApplicationContext()).zzsk().zzcmd;
            final AdRequestParcel zzn = zzn(adRequestParcel);
            final zzfr zzfr = new zzfr(zzn, s, zzcmd);
            final zzfs zzfs = this.zzbpe.get(zzfr);
            zzfs zzfs3;
            if (zzfs == null) {
                zza("Interstitial pool created at %s.", zzfr);
                final zzfs zzfs2 = new zzfs(zzn, s, zzcmd);
                this.zzbpe.put(zzfr, zzfs2);
                zzfs3 = zzfs2;
            }
            else {
                zzfs3 = zzfs;
            }
            this.zzbpf.remove(zzfr);
            this.zzbpf.add(zzfr);
            zzfs3.zzmr();
            while (this.zzbpf.size() > zzdi.zzbdm.get()) {
                final zzfr zzfr2 = this.zzbpf.remove();
                final zzfs zzfs4 = this.zzbpe.get(zzfr2);
                zza("Evicting interstitial queue for %s.", zzfr2);
                while (zzfs4.size() > 0) {
                    zzfs4.zzp(null).zzbpl.zzfa();
                }
                this.zzbpe.remove(zzfr2);
            }
            while (zzfs3.size() > 0) {
                final zzfs.zza zzp = zzfs3.zzp(zzn);
                if (!zzp.zzbpp || zzu.zzgf().currentTimeMillis() - zzp.zzbpo <= 1000L * zzdi.zzbdo.get()) {
                    String s2;
                    if (zzp.zzbpm != null) {
                        s2 = " (inline) ";
                    }
                    else {
                        s2 = " ";
                    }
                    zza(new StringBuilder(34 + String.valueOf(s2).length()).append("Pooled interstitial").append(s2).append("returned at %s.").toString(), zzfr);
                    zza = zzp;
                    return zza;
                }
                zza("Expired interstitial at %s.", zzfr);
            }
            zza = null;
        }
        return zza;
    }
    
    void zza(final zzfn zzfn) {
        if (this.zzbpg == null) {
            this.zzbpg = zzfn.zzml();
            this.restore();
        }
    }
    
    void zzb(final AdRequestParcel adRequestParcel, final String s) {
        if (this.zzbpg != null) {
            final int zzcmd = new zzjh.zza(this.zzbpg.getApplicationContext()).zzsk().zzcmd;
            final AdRequestParcel zzn = zzn(adRequestParcel);
            final zzfr zzfr = new zzfr(zzn, s, zzcmd);
            zzfs zzfs = this.zzbpe.get(zzfr);
            if (zzfs == null) {
                zza("Interstitial pool created at %s.", zzfr);
                zzfs = new zzfs(zzn, s, zzcmd);
                this.zzbpe.put(zzfr, zzfs);
            }
            zzfs.zza(this.zzbpg, adRequestParcel);
            zzfs.zzmr();
            zza("Inline entry added to the queue at %s.", zzfr);
        }
    }
    
    void zzmm() {
        if (this.zzbpg != null) {
            for (final Map.Entry<zzfr, zzfs> entry : this.zzbpe.entrySet()) {
                final zzfr zzfr = entry.getKey();
                final zzfs zzfs = entry.getValue();
                if (zzb.zzbf(2)) {
                    final int size = zzfs.size();
                    final int zzmp = zzfs.zzmp();
                    if (zzmp < size) {
                        zzkn.v(String.format("Loading %s/%s pooled interstitials for %s.", size - zzmp, size, zzfr));
                    }
                }
                zzfs.zzmq();
                while (zzfs.size() < zzdi.zzbdn.get()) {
                    zza("Pooling and loading one new interstitial for %s.", zzfr);
                    zzfs.zzb(this.zzbpg);
                }
            }
            this.save();
        }
    }
}
