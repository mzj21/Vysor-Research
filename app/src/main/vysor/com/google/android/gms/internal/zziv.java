// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.List;
import android.os.Bundle;
import com.google.android.gms.ads.internal.formats.zza;
import com.google.android.gms.ads.internal.formats.zzc;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import org.json.JSONException;
import com.google.android.gms.ads.internal.formats.zzi;
import org.json.JSONObject;
import com.google.android.gms.ads.internal.formats.zze;

@zziy
public class zziv implements zza<zze>
{
    private final boolean zzcfd;
    private final boolean zzcfe;
    
    public zziv(final boolean zzcfd, final boolean zzcfe) {
        this.zzcfd = zzcfd;
        this.zzcfe = zzcfe;
    }
    
    public zze zzc(final zzis zzis, final JSONObject jsonObject) throws JSONException, InterruptedException, ExecutionException {
        final List<zzlj<zzc>> zza = zzis.zza(jsonObject, "images", true, this.zzcfd, this.zzcfe);
        final zzlj<zzc> zza2 = zzis.zza(jsonObject, "secondary_image", false, this.zzcfd);
        final zzlj<com.google.android.gms.ads.internal.formats.zza> zzg = zzis.zzg(jsonObject);
        final ArrayList<zzc> list = new ArrayList<zzc>();
        final Iterator<zzlj<zzc>> iterator = zza.iterator();
        while (iterator.hasNext()) {
            list.add((zzc)iterator.next().get());
        }
        return new zze(jsonObject.getString("headline"), list, jsonObject.getString("body"), zza2.get(), jsonObject.getString("call_to_action"), jsonObject.getString("advertiser"), zzg.get(), new Bundle());
    }
}
