// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONException;
import org.json.JSONObject;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.HashMap;
import java.util.Map;

@zziy
public class zzfd implements zzev
{
    private final Object zzakd;
    private final Map<String, zza> zzbnp;
    
    public zzfd() {
        this.zzakd = new Object();
        this.zzbnp = new HashMap<String, zza>();
    }
    
    @Override
    public void zza(final zzlt zzlt, final Map<String, String> map) {
        final String s = map.get("id");
        final String s2 = map.get("fail");
        final String s3 = map.get("fail_reason");
        final String s4 = map.get("result");
        final zza zza;
        synchronized (this.zzakd) {
            zza = this.zzbnp.remove(s);
            if (zza == null) {
                final String value = String.valueOf(s);
                String concat;
                if (value.length() != 0) {
                    concat = "Received result for unexpected method invocation: ".concat(value);
                }
                else {
                    concat = new String("Received result for unexpected method invocation: ");
                }
                zzb.zzdf(concat);
                return;
            }
        }
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            zza.zzbb(s3);
        }
        // monitorexit(o)
        else if (s4 == null) {
            zza.zzbb("No result.");
        }
        // monitorexit(o)
        else {
            while (true) {
                try {
                    zza.zzd(new JSONObject(s4));
                }
                // monitorexit(o)
                catch (JSONException ex) {
                    zza.zzbb(ex.getMessage());
                    continue;
                }
                break;
            }
        }
    }
    
    public interface zza
    {
        void zzbb(final String p0);
        
        void zzd(final JSONObject p0);
    }
}
