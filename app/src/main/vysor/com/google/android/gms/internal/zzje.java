// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.zzu;
import android.net.Uri$Builder;
import org.json.JSONException;
import com.google.android.gms.ads.internal.util.client.zzb;
import org.json.JSONObject;
import java.util.Arrays;
import android.net.Uri;
import android.text.TextUtils;
import java.util.Map;
import com.google.android.gms.ads.internal.safebrowsing.SafeBrowsingConfigParcel;
import com.google.android.gms.ads.internal.request.AutoClickProtectionConfigurationParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import java.util.List;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;

@zziy
public final class zzje
{
    private int mOrientation;
    private boolean zzazd;
    private String zzbjs;
    private final AdRequestInfoParcel zzbtk;
    private List<String> zzcea;
    private String zzckg;
    private String zzckh;
    private List<String> zzcki;
    private String zzckj;
    private String zzckk;
    private String zzckl;
    private List<String> zzckm;
    private long zzckn;
    private boolean zzcko;
    private final long zzckp;
    private long zzckq;
    private boolean zzckr;
    private boolean zzcks;
    private boolean zzckt;
    private boolean zzcku;
    private String zzckv;
    private boolean zzckw;
    private RewardItemParcel zzckx;
    private List<String> zzcky;
    private List<String> zzckz;
    private boolean zzcla;
    private AutoClickProtectionConfigurationParcel zzclb;
    private boolean zzclc;
    private String zzcld;
    private List<String> zzcle;
    private boolean zzclf;
    private String zzclg;
    private SafeBrowsingConfigParcel zzclh;
    
    public zzje(final AdRequestInfoParcel zzbtk) {
        this.zzckn = -1L;
        this.zzcko = false;
        this.zzckp = -1L;
        this.zzckq = -1L;
        this.mOrientation = -1;
        this.zzckr = false;
        this.zzcks = false;
        this.zzckt = false;
        this.zzcku = true;
        this.zzckv = "";
        this.zzckw = false;
        this.zzazd = false;
        this.zzcla = false;
        this.zzclc = false;
        this.zzbtk = zzbtk;
    }
    
    private void zzaa(final Map<String, List<String>> map) {
        final List<String> list = map.get("X-Afma-Content-Url-Opted-Out");
        if (list != null && !list.isEmpty()) {
            this.zzcku = Boolean.valueOf(list.get(0));
        }
    }
    
    private void zzab(final Map<String, List<String>> map) {
        final List<String> list = map.get("X-Afma-Gws-Query-Id");
        if (list != null && !list.isEmpty()) {
            this.zzckv = list.get(0);
        }
    }
    
    private void zzac(final Map<String, List<String>> map) {
        final String zzd = zzd(map, "X-Afma-Fluid");
        if (zzd != null && zzd.equals("mHeight")) {
            this.zzckw = true;
        }
    }
    
    private void zzad(final Map<String, List<String>> map) {
        this.zzazd = "native_express".equals(zzd(map, "X-Afma-Ad-Format"));
    }
    
    private void zzae(final Map<String, List<String>> map) {
        this.zzckx = RewardItemParcel.zzcp(zzd(map, "X-Afma-Rewards"));
    }
    
    private void zzaf(final Map<String, List<String>> map) {
        if (this.zzcky == null) {
            this.zzcky = zzf(map, "X-Afma-Reward-Video-Start-Urls");
        }
    }
    
    private void zzag(final Map<String, List<String>> map) {
        if (this.zzckz == null) {
            this.zzckz = zzf(map, "X-Afma-Reward-Video-Complete-Urls");
        }
    }
    
    private void zzah(final Map<String, List<String>> map) {
        this.zzcla |= this.zzg(map, "X-Afma-Use-Displayed-Impression");
    }
    
    private void zzai(final Map<String, List<String>> map) {
        this.zzclc |= this.zzg(map, "X-Afma-Auto-Collect-Location");
    }
    
    private void zzaj(final Map<String, List<String>> map) {
        final List<String> zzf = zzf(map, "X-Afma-Remote-Ping-Urls");
        if (zzf != null) {
            this.zzcle = zzf;
        }
    }
    
    private void zzak(final Map<String, List<String>> map) {
        final String zzd = zzd(map, "X-Afma-Auto-Protection-Configuration");
        if (zzd == null || TextUtils.isEmpty((CharSequence)zzd)) {
            final Uri$Builder buildUpon = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204").buildUpon();
            buildUpon.appendQueryParameter("id", "gmob-apps-blocked-navigation");
            if (!TextUtils.isEmpty((CharSequence)this.zzckk)) {
                buildUpon.appendQueryParameter("debugDialog", this.zzckk);
            }
            final boolean booleanValue = zzdi.zzbaw.get();
            final String[] array = { null };
            final String value = String.valueOf(buildUpon.toString());
            final String value2 = String.valueOf("navigationURL");
            array[0] = new StringBuilder(18 + String.valueOf(value).length() + String.valueOf(value2).length()).append(value).append("&").append(value2).append("={NAVIGATION_URL}").toString();
            this.zzclb = new AutoClickProtectionConfigurationParcel(booleanValue, Arrays.asList(array));
        }
        else {
            try {
                this.zzclb = AutoClickProtectionConfigurationParcel.zzi(new JSONObject(zzd));
            }
            catch (JSONException ex) {
                zzb.zzd("Error parsing configuration JSON", (Throwable)ex);
                this.zzclb = new AutoClickProtectionConfigurationParcel();
            }
        }
    }
    
    private void zzal(final Map<String, List<String>> map) {
        this.zzcld = zzd(map, "Set-Cookie");
    }
    
    private void zzam(final Map<String, List<String>> map) {
        final String zzd = zzd(map, "X-Afma-Safe-Browsing");
        if (!TextUtils.isEmpty((CharSequence)zzd)) {
            try {
                this.zzclh = SafeBrowsingConfigParcel.zzk(new JSONObject(zzd));
            }
            catch (JSONException ex) {
                zzb.zzd("Error parsing safe browsing header", (Throwable)ex);
            }
        }
    }
    
    static String zzd(final Map<String, List<String>> map, final String s) {
        final List<String> list = map.get(s);
        String s2;
        if (list != null && !list.isEmpty()) {
            s2 = list.get(0);
        }
        else {
            s2 = null;
        }
        return s2;
    }
    
    static long zze(final Map<String, List<String>> map, final String s) {
        final List<String> list = map.get(s);
        if (list == null || list.isEmpty()) {
            return -1L;
        }
        final String s2 = list.get(0);
        try {
            return (long)(Float.parseFloat(s2) * 1000.0f);
        }
        catch (NumberFormatException ex) {
            zzb.zzdf(new StringBuilder(36 + String.valueOf(s).length() + String.valueOf(s2).length()).append("Could not parse float from ").append(s).append(" header: ").append(s2).toString());
        }
        return -1L;
    }
    
    static List<String> zzf(final Map<String, List<String>> map, final String s) {
        final List<String> list = map.get(s);
        if (list == null || list.isEmpty()) {
            return null;
        }
        final String s2 = list.get(0);
        if (s2 == null) {
            return null;
        }
        return Arrays.asList(s2.trim().split("\\s+"));
        list2 = null;
        return list2;
    }
    
    private boolean zzg(final Map<String, List<String>> map, final String s) {
        final List<String> list = map.get(s);
        return list != null && !list.isEmpty() && Boolean.valueOf(list.get(0));
    }
    
    private void zzk(final Map<String, List<String>> map) {
        this.zzckg = zzd(map, "X-Afma-Ad-Size");
    }
    
    private void zzl(final Map<String, List<String>> map) {
        this.zzclg = zzd(map, "X-Afma-Ad-Slot-Size");
    }
    
    private void zzm(final Map<String, List<String>> map) {
        final List<String> zzf = zzf(map, "X-Afma-Click-Tracking-Urls");
        if (zzf != null) {
            this.zzcki = zzf;
        }
    }
    
    private void zzn(final Map<String, List<String>> map) {
        this.zzckj = zzd(map, "X-Afma-Debug-Signals");
    }
    
    private void zzo(final Map<String, List<String>> map) {
        final List<String> list = map.get("X-Afma-Debug-Dialog");
        if (list != null && !list.isEmpty()) {
            this.zzckk = list.get(0);
        }
    }
    
    private void zzp(final Map<String, List<String>> map) {
        final List<String> zzf = zzf(map, "X-Afma-Tracking-Urls");
        if (zzf != null) {
            this.zzckm = zzf;
        }
    }
    
    private void zzq(final Map<String, List<String>> map) {
        final long zze = zze(map, "X-Afma-Interstitial-Timeout");
        if (zze != -1L) {
            this.zzckn = zze;
        }
    }
    
    private void zzr(final Map<String, List<String>> map) {
        this.zzckl = zzd(map, "X-Afma-ActiveView");
    }
    
    private void zzs(final Map<String, List<String>> map) {
        this.zzcks = "native".equals(zzd(map, "X-Afma-Ad-Format"));
    }
    
    private void zzt(final Map<String, List<String>> map) {
        this.zzckr |= this.zzg(map, "X-Afma-Custom-Rendering-Allowed");
    }
    
    private void zzu(final Map<String, List<String>> map) {
        this.zzcko |= this.zzg(map, "X-Afma-Mediation");
    }
    
    private void zzv(final Map<String, List<String>> map) {
        this.zzclf |= this.zzg(map, "X-Afma-Render-In-Browser");
    }
    
    private void zzw(final Map<String, List<String>> map) {
        final List<String> zzf = zzf(map, "X-Afma-Manual-Tracking-Urls");
        if (zzf != null) {
            this.zzcea = zzf;
        }
    }
    
    private void zzx(final Map<String, List<String>> map) {
        final long zze = zze(map, "X-Afma-Refresh-Rate");
        if (zze != -1L) {
            this.zzckq = zze;
        }
    }
    
    private void zzy(final Map<String, List<String>> map) {
        final List<String> list = map.get("X-Afma-Orientation");
        if (list != null && !list.isEmpty()) {
            final String s = list.get(0);
            if ("portrait".equalsIgnoreCase(s)) {
                this.mOrientation = zzu.zzgb().zzun();
            }
            else if ("landscape".equalsIgnoreCase(s)) {
                this.mOrientation = zzu.zzgb().zzum();
            }
        }
    }
    
    private void zzz(final Map<String, List<String>> map) {
        final List<String> list = map.get("X-Afma-Use-HTTPS");
        if (list != null && !list.isEmpty()) {
            this.zzckt = Boolean.valueOf(list.get(0));
        }
    }
    
    public void zzb(final String zzckh, final Map<String, List<String>> map, final String zzbjs) {
        this.zzckh = zzckh;
        this.zzbjs = zzbjs;
        this.zzj(map);
    }
    
    public AdResponseParcel zzj(final long n) {
        return new AdResponseParcel(this.zzbtk, this.zzckh, this.zzbjs, this.zzcki, this.zzckm, this.zzckn, this.zzcko, -1L, this.zzcea, this.zzckq, this.mOrientation, this.zzckg, n, this.zzckk, this.zzckl, this.zzckr, this.zzcks, this.zzckt, this.zzcku, false, this.zzckv, this.zzckw, this.zzazd, this.zzckx, this.zzcky, this.zzckz, this.zzcla, this.zzclb, this.zzclc, this.zzcld, this.zzcle, this.zzclf, this.zzclg, this.zzclh, this.zzckj);
    }
    
    public void zzj(final Map<String, List<String>> map) {
        this.zzk(map);
        this.zzl(map);
        this.zzm(map);
        this.zzn(map);
        this.zzo(map);
        this.zzp(map);
        this.zzq(map);
        this.zzu(map);
        this.zzw(map);
        this.zzx(map);
        this.zzy(map);
        this.zzr(map);
        this.zzz(map);
        this.zzt(map);
        this.zzs(map);
        this.zzaa(map);
        this.zzab(map);
        this.zzac(map);
        this.zzad(map);
        this.zzae(map);
        this.zzaf(map);
        this.zzag(map);
        this.zzah(map);
        this.zzai(map);
        this.zzal(map);
        this.zzak(map);
        this.zzaj(map);
        this.zzam(map);
        this.zzv(map);
    }
}
