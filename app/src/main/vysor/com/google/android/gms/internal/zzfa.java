// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONException;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.concurrent.Future;
import java.util.Map;
import org.json.JSONObject;
import java.util.HashMap;

@zziy
public class zzfa implements zzev
{
    final HashMap<String, zzlg<JSONObject>> zzbnk;
    
    public zzfa() {
        this.zzbnk = new HashMap<String, zzlg<JSONObject>>();
    }
    
    @Override
    public void zza(final zzlt zzlt, final Map<String, String> map) {
        this.zzi(map.get("request_id"), map.get("fetched_ad"));
    }
    
    public Future<JSONObject> zzaz(final String s) {
        final zzlg<JSONObject> zzlg = new zzlg<JSONObject>();
        this.zzbnk.put(s, zzlg);
        return zzlg;
    }
    
    public void zzba(final String s) {
        final zzlg<JSONObject> zzlg = this.zzbnk.get(s);
        if (zzlg == null) {
            zzb.e("Could not find the ad request for the corresponding ad response.");
        }
        else {
            if (!zzlg.isDone()) {
                zzlg.cancel(true);
            }
            this.zzbnk.remove(s);
        }
    }
    
    public void zzi(final String s, final String s2) {
        zzb.zzdd("Received ad from the cache.");
        final zzlg<JSONObject> zzlg = this.zzbnk.get(s);
        if (zzlg == null) {
            zzb.e("Could not find the ad request for the corresponding ad response.");
        }
        else {
            try {
                zzlg.zzh(new JSONObject(s2));
            }
            catch (JSONException ex) {
                zzb.zzb("Failed constructing JSON object from value passed from javascript", (Throwable)ex);
                zzlg.zzh(null);
            }
            finally {
                this.zzbnk.remove(s);
            }
        }
    }
}
