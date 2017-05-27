// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.request;

import android.webkit.CookieManager;
import org.json.JSONException;
import com.google.android.gms.internal.zzkq;
import com.google.android.gms.internal.zzln;
import com.google.android.gms.internal.zzdi;
import android.text.TextUtils;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.ads.internal.zzu;
import android.support.annotation.NonNull;
import com.google.android.gms.internal.zzlm;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import org.json.JSONObject;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.internal.zzkt;
import com.google.android.gms.internal.zzgh;
import com.google.android.gms.internal.zzau;
import android.content.Context;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzkm;

@zziy
public class zzb extends zzkm implements zzc.zza
{
    private final Context mContext;
    private final zzau zzbkp;
    zzgh zzbsv;
    private AdRequestInfoParcel zzbtk;
    AdResponseParcel zzccl;
    private Runnable zzccm;
    private final Object zzccn;
    private final com.google.android.gms.ads.internal.request.zza.zza zzcfh;
    private final AdRequestInfoParcel.zza zzcfi;
    zzkt zzcfj;
    
    public zzb(final Context mContext, final AdRequestInfoParcel.zza zzcfi, final zzau zzbkp, final com.google.android.gms.ads.internal.request.zza.zza zzcfh) {
        this.zzccn = new Object();
        this.zzcfh = zzcfh;
        this.mContext = mContext;
        this.zzcfi = zzcfi;
        this.zzbkp = zzbkp;
    }
    
    private void zzd(final int n, final String s) {
        if (n == 3 || n == -1) {
            com.google.android.gms.ads.internal.util.client.zzb.zzde(s);
        }
        else {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf(s);
        }
        if (this.zzccl == null) {
            this.zzccl = new AdResponseParcel(n);
        }
        else {
            this.zzccl = new AdResponseParcel(n, this.zzccl.zzbsj);
        }
        AdRequestInfoParcel zzbtk;
        if (this.zzbtk != null) {
            zzbtk = this.zzbtk;
        }
        else {
            zzbtk = new AdRequestInfoParcel(this.zzcfi, null, -1L);
        }
        this.zzcfh.zza(new zzke.zza(zzbtk, this.zzccl, this.zzbsv, null, n, -1L, this.zzccl.zzchg, null));
    }
    
    @Override
    public void onStop() {
        synchronized (this.zzccn) {
            if (this.zzcfj != null) {
                this.zzcfj.cancel();
            }
        }
    }
    
    zzkt zza(final VersionInfoParcel versionInfoParcel, final zzlm<AdRequestInfoParcel> zzlm) {
        return zzc.zza(this.mContext, versionInfoParcel, zzlm, (zzc.zza)this);
    }
    
    protected AdSizeParcel zzb(final AdRequestInfoParcel adRequestInfoParcel) throws zza {
        if (this.zzccl.zzaxm) {
            for (final AdSizeParcel adSizeParcel : adRequestInfoParcel.zzaqz.zzaxk) {
                if (adSizeParcel.zzaxm) {
                    return new AdSizeParcel(adSizeParcel, adRequestInfoParcel.zzaqz.zzaxk);
                }
            }
        }
        Label_0076: {
            break Label_0076;
        }
        if (this.zzccl.zzchf == null) {
            throw new zza("The ad response must specify one of the supported ad sizes.", 0);
        }
        final String[] split = this.zzccl.zzchf.split("x");
        if (split.length != 2) {
            final String value = String.valueOf(this.zzccl.zzchf);
            String concat;
            if (value.length() != 0) {
                concat = "Invalid ad size format from the ad response: ".concat(value);
            }
            else {
                concat = new String("Invalid ad size format from the ad response: ");
            }
            throw new zza(concat, 0);
        }
        while (true) {
            AdSizeParcel adSizeParcel2;
            int int1;
            int int2;
            AdSizeParcel[] zzaxk2;
            int length2;
            int n = 0;
            AdSizeParcel adSizeParcel3 = null;
            float density;
            int width;
            int height;
            String value2;
            String concat2;
            Label_0253_Outer:Label_0275_Outer:
            while (true) {
            Label_0393:
                while (true) {
                Label_0383:
                    while (true) {
                        try {
                            int1 = Integer.parseInt(split[0]);
                            int2 = Integer.parseInt(split[1]);
                            zzaxk2 = adRequestInfoParcel.zzaqz.zzaxk;
                            length2 = zzaxk2.length;
                            n = 0;
                            if (n >= length2) {
                                break;
                            }
                            adSizeParcel3 = zzaxk2[n];
                            density = this.mContext.getResources().getDisplayMetrics().density;
                            if (adSizeParcel3.width == -1) {
                                width = (int)(adSizeParcel3.widthPixels / density);
                                if (adSizeParcel3.height != -2) {
                                    break Label_0383;
                                }
                                height = (int)(adSizeParcel3.heightPixels / density);
                                if (int1 == width && int2 == height && !adSizeParcel3.zzaxm) {
                                    adSizeParcel2 = new AdSizeParcel(adSizeParcel3, adRequestInfoParcel.zzaqz.zzaxk);
                                    return adSizeParcel2;
                                }
                                break Label_0393;
                            }
                        }
                        catch (NumberFormatException ex) {
                            value2 = String.valueOf(this.zzccl.zzchf);
                            if (value2.length() != 0) {
                                concat2 = "Invalid ad size number from the ad response: ".concat(value2);
                            }
                            else {
                                concat2 = new String("Invalid ad size number from the ad response: ");
                            }
                            throw new zza(concat2, 0);
                        }
                        width = adSizeParcel3.width;
                        continue Label_0275_Outer;
                    }
                    height = adSizeParcel3.height;
                    continue;
                }
                ++n;
                continue Label_0253_Outer;
            }
        }
        final String value3 = String.valueOf(this.zzccl.zzchf);
        String concat3;
        if (value3.length() != 0) {
            concat3 = "The ad size from the ad response was not one of the requested sizes: ".concat(value3);
        }
        else {
            concat3 = new String("The ad size from the ad response was not one of the requested sizes: ");
        }
        throw new zza(concat3, 0);
    }
    
    @Override
    public void zzb(@NonNull final AdResponseParcel zzccl) {
        while (true) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Received ad response.");
            this.zzccl = zzccl;
            final long elapsedRealtime = zzu.zzgf().elapsedRealtime();
            Label_0160: {
                synchronized (this.zzccn) {
                    this.zzcfj = null;
                    // monitorexit(this.zzccn)
                    zzu.zzgd().zzd(this.mContext, this.zzccl.zzcgt);
                    try {
                        if (this.zzccl.errorCode != -2 && this.zzccl.errorCode != -3) {
                            throw new zza(new StringBuilder(66).append("There was a problem getting an ad response. ErrorCode: ").append(this.zzccl.errorCode).toString(), this.zzccl.errorCode);
                        }
                        break Label_0160;
                    }
                    catch (zza zza) {
                        this.zzd(zza.getErrorCode(), zza.getMessage());
                        zzkr.zzcrf.removeCallbacks(this.zzccm);
                    }
                    return;
                }
            }
            this.zzru();
            AdSizeParcel zzb;
            if (this.zzbtk.zzaqz.zzaxk != null) {
                zzb = this.zzb(this.zzbtk);
            }
            else {
                zzb = null;
            }
            zzu.zzgd().zzaf(this.zzccl.zzchm);
            while (true) {
                Label_0302: {
                    if (TextUtils.isEmpty((CharSequence)this.zzccl.zzchk)) {
                        break Label_0302;
                    }
                    try {
                        final JSONObject jsonObject = new JSONObject(this.zzccl.zzchk);
                        this.zzcfh.zza(new zzke.zza(this.zzbtk, this.zzccl, this.zzbsv, zzb, -2, elapsedRealtime, this.zzccl.zzchg, jsonObject));
                        zzkr.zzcrf.removeCallbacks(this.zzccm);
                        return;
                    }
                    catch (Exception ex) {
                        com.google.android.gms.ads.internal.util.client.zzb.zzb("Error parsing the JSON for Active View.", ex);
                    }
                }
                final JSONObject jsonObject = null;
                continue;
            }
        }
    }
    
    @Override
    public void zzfc() {
        com.google.android.gms.ads.internal.util.client.zzb.zzdd("AdLoaderBackgroundTask started.");
        this.zzccm = new Runnable() {
            @Override
            public void run() {
                synchronized (zzb.this.zzccn) {
                    if (zzb.this.zzcfj != null) {
                        zzb.this.onStop();
                        zzb.this.zzd(2, "Timed out waiting for ad response.");
                    }
                }
            }
        };
        zzkr.zzcrf.postDelayed(this.zzccm, (long)zzdi.zzbek.get());
        final zzln<AdRequestInfoParcel> zzln = new zzln<AdRequestInfoParcel>();
        final long elapsedRealtime = zzu.zzgf().elapsedRealtime();
        zzkq.zza(new Runnable() {
            @Override
            public void run() {
                synchronized (zzb.this.zzccn) {
                    zzb.this.zzcfj = zzb.this.zza(zzb.this.zzcfi.zzaqv, zzln);
                    if (zzb.this.zzcfj == null) {
                        zzb.this.zzd(0, "Could not start the ad request service.");
                        zzkr.zzcrf.removeCallbacks(zzb.this.zzccm);
                    }
                }
            }
        });
        zzln.zzg(this.zzbtk = new AdRequestInfoParcel(this.zzcfi, this.zzbkp.zzaw().zzb(this.mContext), elapsedRealtime));
    }
    
    protected void zzru() throws zza {
        if (this.zzccl.errorCode != -3) {
            if (TextUtils.isEmpty((CharSequence)this.zzccl.body)) {
                throw new zza("No fill from ad server.", 3);
            }
            zzu.zzgd().zzc(this.mContext, this.zzccl.zzcgc);
            while (true) {
                Label_0227: {
                    if (!this.zzccl.zzchc) {
                        break Label_0227;
                    }
                    try {
                        this.zzbsv = new zzgh(this.zzccl.body);
                        zzu.zzgd().zzag(this.zzbsv.zzbsh);
                        if (TextUtils.isEmpty((CharSequence)this.zzccl.zzcgu) || !zzdi.zzbhh.get()) {
                            return;
                        }
                        com.google.android.gms.ads.internal.util.client.zzb.zzdd("Received cookie from server. Setting webview cookie in CookieManager.");
                        final CookieManager zzao = zzu.zzgb().zzao(this.mContext);
                        if (zzao != null) {
                            zzao.setCookie("googleads.g.doubleclick.net", this.zzccl.zzcgu);
                        }
                        return;
                    }
                    catch (JSONException ex) {
                        com.google.android.gms.ads.internal.util.client.zzb.zzb("Could not parse mediation config.", (Throwable)ex);
                        final String value = String.valueOf(this.zzccl.body);
                        String concat;
                        if (value.length() != 0) {
                            concat = "Could not parse mediation config: ".concat(value);
                        }
                        else {
                            concat = new String("Could not parse mediation config: ");
                        }
                        throw new zza(concat, 0);
                    }
                }
                zzu.zzgd().zzag(this.zzccl.zzbsh);
                continue;
            }
        }
    }
    
    @zziy
    static final class zza extends Exception
    {
        private final int zzcdb;
        
        public zza(final String s, final int zzcdb) {
            super(s);
            this.zzcdb = zzcdb;
        }
        
        public int getErrorCode() {
            return this.zzcdb;
        }
    }
}
