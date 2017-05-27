// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONArray;
import com.google.android.gms.ads.internal.formats.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.formats.zzi;
import java.util.concurrent.ExecutionException;
import org.json.JSONException;
import com.google.android.gms.ads.internal.formats.zzc;
import java.util.concurrent.Future;
import android.support.v4.util.SimpleArrayMap;
import org.json.JSONObject;
import com.google.android.gms.ads.internal.formats.zzf;

@zziy
public class zziw implements zza<zzf>
{
    private final boolean zzcfd;
    
    public zziw(final boolean zzcfd) {
        this.zzcfd = zzcfd;
    }
    
    private void zza(final zzis zzis, final JSONObject jsonObject, final SimpleArrayMap<String, Future<zzc>> simpleArrayMap) throws JSONException {
        simpleArrayMap.put(jsonObject.getString("name"), zzis.zza(jsonObject, "image_value", this.zzcfd));
    }
    
    private void zza(final JSONObject jsonObject, final SimpleArrayMap<String, String> simpleArrayMap) throws JSONException {
        simpleArrayMap.put(jsonObject.getString("name"), jsonObject.getString("string_value"));
    }
    
    private <K, V> SimpleArrayMap<K, V> zzc(final SimpleArrayMap<K, Future<V>> simpleArrayMap) throws InterruptedException, ExecutionException {
        final SimpleArrayMap<K, V> simpleArrayMap2 = new SimpleArrayMap<K, V>();
        for (int i = 0; i < simpleArrayMap.size(); ++i) {
            simpleArrayMap2.put(simpleArrayMap.keyAt(i), simpleArrayMap.valueAt(i).get());
        }
        return simpleArrayMap2;
    }
    
    public zzf zzd(final zzis zzis, final JSONObject jsonObject) throws JSONException, InterruptedException, ExecutionException {
        final SimpleArrayMap<String, Future<zzc>> simpleArrayMap = new SimpleArrayMap<String, Future<zzc>>();
        final SimpleArrayMap<String, String> simpleArrayMap2 = new SimpleArrayMap<String, String>();
        final zzlj<com.google.android.gms.ads.internal.formats.zza> zzg = zzis.zzg(jsonObject);
        final JSONArray jsonArray = jsonObject.getJSONArray("custom_assets");
        for (int i = 0; i < jsonArray.length(); ++i) {
            final JSONObject jsonObject2 = jsonArray.getJSONObject(i);
            final String string = jsonObject2.getString("type");
            if ("string".equals(string)) {
                this.zza(jsonObject2, simpleArrayMap2);
            }
            else if ("image".equals(string)) {
                this.zza(zzis, jsonObject2, simpleArrayMap);
            }
            else {
                final String value = String.valueOf(string);
                String concat;
                if (value.length() != 0) {
                    concat = "Unknown custom asset type: ".concat(value);
                }
                else {
                    concat = new String("Unknown custom asset type: ");
                }
                com.google.android.gms.ads.internal.util.client.zzb.zzdf(concat);
            }
        }
        return new zzf(jsonObject.getString("custom_template_id"), this.zzc(simpleArrayMap), simpleArrayMap2, (com.google.android.gms.ads.internal.formats.zza)zzg.get());
    }
}
