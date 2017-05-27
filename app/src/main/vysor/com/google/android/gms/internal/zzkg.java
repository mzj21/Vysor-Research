// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.util.client.zzb;
import org.json.JSONObject;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@zziy
public class zzkg
{
    private final long zzcpb;
    private final List<String> zzcpc;
    private final Map<String, zzb> zzcpd;
    private String zzcpe;
    private String zzcpf;
    private boolean zzcpg;
    
    public zzkg(final String zzcpf, final long zzcpb) {
        this.zzcpc = new ArrayList<String>();
        this.zzcpd = new HashMap<String, zzb>();
        this.zzcpg = false;
        this.zzcpf = zzcpf;
        this.zzcpb = zzcpb;
        this.zzcs(zzcpf);
    }
    
    private void zzcs(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            JSONObject jsonObject = null;
            Label_0060: {
                try {
                    jsonObject = new JSONObject(s);
                    if (jsonObject.optInt("status", -1) == 1) {
                        break Label_0060;
                    }
                    this.zzcpg = false;
                    com.google.android.gms.ads.internal.util.client.zzb.zzdf("App settings could not be fetched successfully.");
                }
                catch (JSONException ex) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Exception occurred while processing app setting json", (Throwable)ex);
                    zzu.zzgd().zza((Throwable)ex, "AppSettings.parseAppSettingsJson");
                }
                return;
            }
            this.zzcpg = true;
            this.zzcpe = jsonObject.optString("app_id");
            final JSONArray optJSONArray = jsonObject.optJSONArray("ad_unit_id_settings");
            int i = 0;
            if (optJSONArray != null) {
                while (i < optJSONArray.length()) {
                    this.zzl(optJSONArray.getJSONObject(i));
                    ++i;
                }
            }
        }
    }
    
    private void zzl(final JSONObject jsonObject) throws JSONException {
        final String optString = jsonObject.optString("format");
        final String optString2 = jsonObject.optString("ad_unit_id");
        if (!TextUtils.isEmpty((CharSequence)optString) && !TextUtils.isEmpty((CharSequence)optString2)) {
            if ("interstitial".equalsIgnoreCase(optString)) {
                this.zzcpc.add(optString2);
            }
            else if ("rewarded".equalsIgnoreCase(optString)) {
                final JSONObject optJSONObject = jsonObject.optJSONObject("mediation_config");
                if (optJSONObject != null) {
                    final JSONArray optJSONArray = optJSONObject.optJSONArray("ad_networks");
                    if (optJSONArray != null) {
                        for (int i = 0; i < optJSONArray.length(); ++i) {
                            final JSONObject jsonObject2 = optJSONArray.getJSONObject(i);
                            final JSONArray optJSONArray2 = jsonObject2.optJSONArray("adapters");
                            if (optJSONArray2 == null) {
                                break;
                            }
                            final ArrayList<String> list = new ArrayList<String>();
                            for (int j = 0; j < optJSONArray2.length(); ++j) {
                                list.add(optJSONArray2.getString(j));
                            }
                            final JSONObject optJSONObject2 = jsonObject2.optJSONObject("data");
                            if (optJSONObject2 == null) {
                                break;
                            }
                            final Bundle bundle = new Bundle();
                            final Iterator keys = optJSONObject2.keys();
                            while (keys.hasNext()) {
                                final String s = keys.next();
                                bundle.putString(s, optJSONObject2.getString(s));
                            }
                            final zza zza = new zza(list, bundle);
                            zzb zzb;
                            if (this.zzcpd.containsKey(optString2)) {
                                zzb = this.zzcpd.get(optString2);
                            }
                            else {
                                zzb = new zzb();
                            }
                            zzb.zza(zza);
                            this.zzcpd.put(optString2, zzb);
                        }
                    }
                }
            }
        }
    }
    
    public long zztf() {
        return this.zzcpb;
    }
    
    public boolean zztg() {
        return this.zzcpg;
    }
    
    public String zzth() {
        return this.zzcpf;
    }
    
    public String zzti() {
        return this.zzcpe;
    }
    
    class zza
    {
        private final List<String> zzcph;
        private final Bundle zzcpi;
        
        public zza(final List<String> zzcph, final Bundle zzcpi) {
            this.zzcph = zzcph;
            this.zzcpi = zzcpi;
        }
    }
    
    class zzb
    {
        final List<zza> zzcpk;
        
        zzb() {
            this.zzcpk = new ArrayList<zza>();
        }
        
        public void zza(final zza zza) {
            this.zzcpk.add(zza);
        }
    }
}
