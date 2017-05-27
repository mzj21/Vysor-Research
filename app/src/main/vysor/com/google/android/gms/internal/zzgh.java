// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONArray;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.zzu;
import java.util.Collections;
import java.util.ArrayList;
import com.google.android.gms.ads.internal.util.client.zzb;
import org.json.JSONObject;
import java.util.List;

@zziy
public final class zzgh
{
    public final List<zzgg> zzbsb;
    public final long zzbsc;
    public final List<String> zzbsd;
    public final List<String> zzbse;
    public final List<String> zzbsf;
    public final List<String> zzbsg;
    public final boolean zzbsh;
    public final String zzbsi;
    public final long zzbsj;
    public final String zzbsk;
    public final int zzbsl;
    public final int zzbsm;
    public final long zzbsn;
    public final boolean zzbso;
    public int zzbsp;
    public int zzbsq;
    
    public zzgh(final String s) throws JSONException {
        final JSONObject jsonObject = new JSONObject(s);
        if (zzb.zzbf(2)) {
            final String value = String.valueOf(jsonObject.toString(2));
            String concat;
            if (value.length() != 0) {
                concat = "Mediation Response JSON: ".concat(value);
            }
            else {
                concat = new String("Mediation Response JSON: ");
            }
            zzkn.v(concat);
        }
        final JSONArray jsonArray = jsonObject.getJSONArray("ad_networks");
        final ArrayList list = new ArrayList<zzgg>(jsonArray.length());
        int i = 0;
        int zzbsp = -1;
        while (i < jsonArray.length()) {
            final zzgg zzgg = new zzgg(jsonArray.getJSONObject(i));
            list.add(zzgg);
            if (zzbsp < 0 && this.zza(zzgg)) {
                zzbsp = i;
            }
            ++i;
        }
        this.zzbsp = zzbsp;
        this.zzbsq = jsonArray.length();
        this.zzbsb = Collections.unmodifiableList((List<? extends zzgg>)list);
        this.zzbsi = jsonObject.getString("qdata");
        this.zzbsm = jsonObject.optInt("fs_model_type", -1);
        this.zzbsn = jsonObject.optLong("timeout_ms", -1L);
        final JSONObject optJSONObject = jsonObject.optJSONObject("settings");
        if (optJSONObject != null) {
            this.zzbsc = optJSONObject.optLong("ad_network_timeout_millis", -1L);
            this.zzbsd = zzu.zzgs().zza(optJSONObject, "click_urls");
            this.zzbse = zzu.zzgs().zza(optJSONObject, "imp_urls");
            this.zzbsf = zzu.zzgs().zza(optJSONObject, "nofill_urls");
            this.zzbsg = zzu.zzgs().zza(optJSONObject, "remote_ping_urls");
            this.zzbsh = optJSONObject.optBoolean("render_in_browser", false);
            final long optLong = optJSONObject.optLong("refresh", -1L);
            long zzbsj;
            if (optLong > 0L) {
                zzbsj = optLong * 1000L;
            }
            else {
                zzbsj = -1L;
            }
            this.zzbsj = zzbsj;
            final RewardItemParcel zza = RewardItemParcel.zza(optJSONObject.optJSONArray("rewards"));
            if (zza == null) {
                this.zzbsk = null;
                this.zzbsl = 0;
            }
            else {
                this.zzbsk = zza.type;
                this.zzbsl = zza.zzcny;
            }
            this.zzbso = optJSONObject.optBoolean("use_displayed_impression", false);
        }
        else {
            this.zzbsc = -1L;
            this.zzbsd = null;
            this.zzbse = null;
            this.zzbsf = null;
            this.zzbsg = null;
            this.zzbsj = -1L;
            this.zzbsk = null;
            this.zzbsl = 0;
            this.zzbso = false;
            this.zzbsh = false;
        }
    }
    
    public zzgh(final List<zzgg> zzbsb, final long zzbsc, final List<String> zzbsd, final List<String> zzbse, final List<String> zzbsf, final List<String> zzbsg, final boolean zzbsh, final String zzbsi, final long zzbsj, final int zzbsp, final int zzbsq, final String zzbsk, final int zzbsl, final int zzbsm, final long zzbsn, final boolean zzbso) {
        this.zzbsb = zzbsb;
        this.zzbsc = zzbsc;
        this.zzbsd = zzbsd;
        this.zzbse = zzbse;
        this.zzbsf = zzbsf;
        this.zzbsg = zzbsg;
        this.zzbsh = zzbsh;
        this.zzbsi = zzbsi;
        this.zzbsj = zzbsj;
        this.zzbsp = zzbsp;
        this.zzbsq = zzbsq;
        this.zzbsk = zzbsk;
        this.zzbsl = zzbsl;
        this.zzbsm = zzbsm;
        this.zzbsn = zzbsn;
        this.zzbso = zzbso;
    }
    
    private boolean zza(final zzgg zzgg) {
        final Iterator<String> iterator = zzgg.zzbrn.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals("com.google.ads.mediation.admob.AdMobAdapter")) {
                return true;
            }
        }
        return false;
    }
}
