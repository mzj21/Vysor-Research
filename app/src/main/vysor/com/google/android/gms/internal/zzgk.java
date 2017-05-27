// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.ads.mediation.AdUrlAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import android.text.TextUtils;
import com.google.android.gms.ads.formats.NativeAdOptions;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.SystemClock;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import java.util.List;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import android.content.Context;

@zziy
public class zzgk implements zza
{
    private final Context mContext;
    private final Object zzakd;
    private final zzgq zzals;
    private final NativeAdOptionsParcel zzand;
    private final List<String> zzane;
    private final VersionInfoParcel zzanh;
    private AdRequestParcel zzaow;
    private final AdSizeParcel zzapc;
    private final boolean zzatk;
    private final boolean zzazd;
    private final String zzbst;
    private final long zzbsu;
    private final zzgh zzbsv;
    private final zzgg zzbsw;
    private zzgr zzbsx;
    private int zzbsy;
    private zzgt zzbsz;
    
    public zzgk(final Context mContext, final String zzbst, final zzgq zzals, final zzgh zzbsv, final zzgg zzbsw, final AdRequestParcel zzaow, final AdSizeParcel zzapc, final VersionInfoParcel zzanh, final boolean zzatk, final boolean zzazd, final NativeAdOptionsParcel zzand, final List<String> zzane) {
        this.zzakd = new Object();
        this.zzbsy = -2;
        this.mContext = mContext;
        this.zzals = zzals;
        this.zzbsw = zzbsw;
        if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(zzbst)) {
            this.zzbst = this.zznf();
        }
        else {
            this.zzbst = zzbst;
        }
        this.zzbsv = zzbsv;
        long zzbsc;
        if (zzbsv.zzbsc != -1L) {
            zzbsc = zzbsv.zzbsc;
        }
        else {
            zzbsc = 10000L;
        }
        this.zzbsu = zzbsc;
        this.zzaow = zzaow;
        this.zzapc = zzapc;
        this.zzanh = zzanh;
        this.zzatk = zzatk;
        this.zzazd = zzazd;
        this.zzand = zzand;
        this.zzane = zzane;
    }
    
    private long zza(final long n, final long n2, final long n3, final long n4) {
        while (this.zzbsy == -2) {
            this.zzb(n, n2, n3, n4);
        }
        return zzu.zzgf().elapsedRealtime() - n;
    }
    
    private void zza(final zzgj zzgj) {
        final String zzbn = this.zzbn(this.zzbsw.zzbrt);
        Label_0103: {
            try {
                if (this.zzanh.zzctt >= 4100000) {
                    break Label_0103;
                }
                if (this.zzapc.zzaxj) {
                    this.zzbsx.zza(zze.zzac(this.mContext), this.zzaow, zzbn, zzgj);
                }
                else {
                    this.zzbsx.zza(zze.zzac(this.mContext), this.zzapc, this.zzaow, zzbn, zzgj);
                }
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not request ad from mediation adapter.", (Throwable)ex);
                this.zzaa(5);
            }
            return;
        }
        if (this.zzatk) {
            this.zzbsx.zza(zze.zzac(this.mContext), this.zzaow, zzbn, this.zzbsw.zzbrl, zzgj, this.zzand, this.zzane);
        }
        else if (this.zzapc.zzaxj) {
            this.zzbsx.zza(zze.zzac(this.mContext), this.zzaow, zzbn, this.zzbsw.zzbrl, zzgj);
        }
        else if (this.zzazd) {
            if (this.zzbsw.zzbrw != null) {
                this.zzbsx.zza(zze.zzac(this.mContext), this.zzaow, zzbn, this.zzbsw.zzbrl, zzgj, new NativeAdOptionsParcel(zzbo(this.zzbsw.zzbsa)), this.zzbsw.zzbrz);
            }
            else {
                this.zzbsx.zza(zze.zzac(this.mContext), this.zzapc, this.zzaow, zzbn, this.zzbsw.zzbrl, zzgj);
            }
        }
        else {
            this.zzbsx.zza(zze.zzac(this.mContext), this.zzapc, this.zzaow, zzbn, this.zzbsw.zzbrl, zzgj);
        }
    }
    
    private boolean zzab(final int n) {
        while (true) {
            while (true) {
                Label_0104: {
                    try {
                        Bundle bundle;
                        if (this.zzatk) {
                            bundle = this.zzbsx.zznp();
                        }
                        else if (this.zzapc.zzaxj) {
                            bundle = this.zzbsx.getInterstitialAdapterInfo();
                        }
                        else {
                            bundle = this.zzbsx.zzno();
                        }
                        boolean b = false;
                        if (bundle != null) {
                            if ((n & bundle.getInt("capabilities", 0)) != n) {
                                break Label_0104;
                            }
                            final boolean b2 = true;
                            b = b2;
                        }
                        return b;
                    }
                    catch (RemoteException ex) {
                        zzb.zzdf("Could not get adapter info. Returning false");
                        return false;
                    }
                }
                final boolean b2 = false;
                continue;
            }
        }
    }
    
    private static zzgt zzac(final int n) {
        return new zzgt.zza() {
            public int zznk() throws RemoteException {
                return n;
            }
        };
    }
    
    private void zzb(final long n, final long n2, final long n3, final long n4) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        final long n5 = n2 - (elapsedRealtime - n);
        final long n6 = n4 - (elapsedRealtime - n3);
        if (n5 <= 0L || n6 <= 0L) {
            zzb.zzde("Timed out waiting for adapter.");
            this.zzbsy = 3;
        }
        else {
            try {
                this.zzakd.wait(Math.min(n5, n6));
            }
            catch (InterruptedException ex) {
                this.zzbsy = -1;
            }
        }
    }
    
    private String zzbn(String string) {
        if (string != null && this.zzni() && !this.zzab(2)) {
            try {
                final JSONObject jsonObject = new JSONObject(string);
                jsonObject.remove("cpm_floor_cents");
                string = jsonObject.toString();
            }
            catch (JSONException ex) {
                zzb.zzdf("Could not remove field. Returning the original value");
            }
        }
        return string;
    }
    
    private static NativeAdOptions zzbo(final String s) {
        final NativeAdOptions.Builder builder = new NativeAdOptions.Builder();
        NativeAdOptions nativeAdOptions;
        if (s == null) {
            nativeAdOptions = builder.build();
        }
        else {
            while (true) {
                try {
                    final JSONObject jsonObject = new JSONObject(s);
                    builder.setRequestMultipleImages(jsonObject.optBoolean("multiple_images", false));
                    builder.setReturnUrlsForImageAssets(jsonObject.optBoolean("only_urls", false));
                    builder.setImageOrientation(zzbp(jsonObject.optString("native_image_orientation", "any")));
                    nativeAdOptions = builder.build();
                }
                catch (JSONException ex) {
                    zzb.zzd("Exception occurred when creating native ad options", (Throwable)ex);
                    continue;
                }
                break;
            }
        }
        return nativeAdOptions;
    }
    
    private static int zzbp(final String s) {
        int n;
        if ("landscape".equals(s)) {
            n = 2;
        }
        else if ("portrait".equals(s)) {
            n = 1;
        }
        else {
            n = 0;
        }
        return n;
    }
    
    private String zznf() {
        try {
            if (!TextUtils.isEmpty((CharSequence)this.zzbsw.zzbrp)) {
                if (this.zzals.zzbr(this.zzbsw.zzbrp)) {
                    return "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter";
                }
                return "com.google.ads.mediation.customevent.CustomEventAdapter";
            }
        }
        catch (RemoteException ex) {
            zzb.zzdf("Fail to determine the custom event's version, assuming the old one.");
        }
        return "com.google.ads.mediation.customevent.CustomEventAdapter";
    }
    
    private zzgt zzng() {
        zzgt zzgt;
        if (this.zzbsy != 0 || !this.zzni()) {
            zzgt = null;
        }
        else {
            try {
                if (this.zzab(4) && this.zzbsz != null && this.zzbsz.zznk() != 0) {
                    zzgt = this.zzbsz;
                    return zzgt;
                }
            }
            catch (RemoteException ex) {
                zzb.zzdf("Could not get cpm value from MediationResponseMetadata");
            }
            zzgt = zzac(this.zznj());
        }
        return zzgt;
    }
    
    private zzgr zznh() {
        final String value = String.valueOf(this.zzbst);
        String concat;
        if (value.length() != 0) {
            concat = "Instantiating mediation adapter: ".concat(value);
        }
        else {
            concat = new String("Instantiating mediation adapter: ");
        }
        zzb.zzde(concat);
        Label_0168: {
            if (this.zzatk) {
                break Label_0168;
            }
            zzgr zzgr;
            if (zzdi.zzbei.get() && "com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzbst)) {
                zzgr = this.zza(new AdMobAdapter());
            }
            else if (zzdi.zzbej.get() && "com.google.ads.mediation.AdUrlAdapter".equals(this.zzbst)) {
                zzgr = this.zza(new AdUrlAdapter());
            }
            else {
                if (!"com.google.ads.mediation.admob.AdMobCustomTabsAdapter".equals(this.zzbst)) {
                    break Label_0168;
                }
                zzgr = new zzgx(new zzhf());
            }
            return zzgr;
            try {
                zzgr = this.zzals.zzbq(this.zzbst);
                return zzgr;
            }
            catch (RemoteException ex) {
                final String value2 = String.valueOf(this.zzbst);
                String concat2;
                if (value2.length() != 0) {
                    concat2 = "Could not instantiate mediation adapter: ".concat(value2);
                }
                else {
                    concat2 = new String("Could not instantiate mediation adapter: ");
                }
                zzb.zza(concat2, (Throwable)ex);
                zzgr = null;
                return zzgr;
            }
        }
    }
    
    private boolean zzni() {
        return this.zzbsv.zzbsm != -1;
    }
    
    private int zznj() {
        int n;
        if (this.zzbsw.zzbrt == null) {
            n = 0;
        }
        else {
            JSONObject jsonObject = null;
            Label_0066: {
                try {
                    jsonObject = new JSONObject(this.zzbsw.zzbrt);
                    if (!"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzbst)) {
                        break Label_0066;
                    }
                    n = jsonObject.optInt("cpm_cents", 0);
                }
                catch (JSONException ex) {
                    zzb.zzdf("Could not convert to json. Returning 0");
                    n = 0;
                }
                return n;
            }
            if (this.zzab(2)) {
                n = jsonObject.optInt("cpm_floor_cents", 0);
            }
            else {
                n = 0;
            }
            if (n == 0) {
                n = jsonObject.optInt("penalized_average_cpm_cents", 0);
            }
        }
        return n;
    }
    
    public void cancel() {
        synchronized (this.zzakd) {
            while (true) {
                try {
                    if (this.zzbsx != null) {
                        this.zzbsx.destroy();
                    }
                    this.zzbsy = -1;
                    this.zzakd.notify();
                }
                catch (RemoteException ex) {
                    zzb.zzd("Could not destroy mediation adapter.", (Throwable)ex);
                    continue;
                }
                break;
            }
        }
    }
    
    public zzgl zza(final long n, final long n2) {
        synchronized (this.zzakd) {
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            final zzgj zzgj = new zzgj();
            zzkr.zzcrf.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    synchronized (zzgk.this.zzakd) {
                        if (zzgk.this.zzbsy != -2) {
                            return;
                        }
                        zzgk.this.zzbsx = zzgk.this.zznh();
                        if (zzgk.this.zzbsx == null) {
                            zzgk.this.zzaa(4);
                            return;
                        }
                    }
                    if (zzgk.this.zzni() && !zzgk.this.zzab(1)) {
                        final String zzf = zzgk.this.zzbst;
                        zzb.zzdf(new StringBuilder(56 + String.valueOf(zzf).length()).append("Ignoring adapter ").append(zzf).append(" as delayed impression is not supported").toString());
                        zzgk.this.zzaa(2);
                    }
                    // monitorexit(o)
                    else {
                        zzgj.zza(zzgk.this);
                        zzgk.this.zza(zzgj);
                    }
                    // monitorexit(o)
                }
            });
            return new zzgl(this.zzbsw, this.zzbsx, this.zzbst, zzgj, this.zzbsy, this.zzng(), this.zza(elapsedRealtime, this.zzbsu, n, n2));
        }
    }
    
    protected zzgr zza(final MediationAdapter mediationAdapter) {
        return new zzgx(mediationAdapter);
    }
    
    @Override
    public void zza(final int zzbsy, final zzgt zzbsz) {
        synchronized (this.zzakd) {
            this.zzbsy = zzbsy;
            this.zzbsz = zzbsz;
            this.zzakd.notify();
        }
    }
    
    @Override
    public void zzaa(final int zzbsy) {
        synchronized (this.zzakd) {
            this.zzbsy = zzbsy;
            this.zzakd.notify();
        }
    }
}
