// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.View;
import java.util.Iterator;
import com.google.android.gms.ads.internal.client.zzab;
import java.util.List;
import android.os.Bundle;
import com.google.android.gms.ads.internal.formats.zza;
import com.google.android.gms.ads.internal.formats.zzc;
import java.util.ArrayList;
import org.json.JSONException;
import com.google.android.gms.ads.internal.formats.zzi;
import org.json.JSONObject;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.ads.internal.formats.zzd;

@zziy
public class zziu implements zza<zzd>
{
    private final boolean zzcfd;
    private final boolean zzcfe;
    
    public zziu(final boolean zzcfd, final boolean zzcfe) {
        this.zzcfd = zzcfd;
        this.zzcfe = zzcfe;
    }
    
    private zzlt zzb(final zzlj<zzlt> zzlj) {
        try {
            return zzlj.get(zzdi.zzbgb.get(), TimeUnit.SECONDS);
        }
        catch (InterruptedException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("InterruptedException occurred while waiting for video to load", ex);
            Thread.currentThread().interrupt();
        }
        catch (ExecutionException ex2) {}
        catch (CancellationException ex3) {
            goto Label_0049;
        }
        catch (TimeoutException ex3) {
            goto Label_0049;
        }
    }
    
    public zzd zzb(final zzis zzis, final JSONObject jsonObject) throws JSONException, InterruptedException, ExecutionException {
        final List<zzlj<zzc>> zza = zzis.zza(jsonObject, "images", true, this.zzcfd, this.zzcfe);
        final zzlj<zzc> zza2 = zzis.zza(jsonObject, "app_icon", true, this.zzcfd);
        final zzlj<zzlt> zzc = zzis.zzc(jsonObject, "video");
        final zzlj<com.google.android.gms.ads.internal.formats.zza> zzg = zzis.zzg(jsonObject);
        final ArrayList<zzc> list = new ArrayList<zzc>();
        final Iterator<zzlj<zzc>> iterator = zza.iterator();
        while (iterator.hasNext()) {
            list.add((zzc)iterator.next().get());
        }
        final zzlt zzb = this.zzb(zzc);
        final String string = jsonObject.getString("headline");
        final String string2 = jsonObject.getString("body");
        final zzdx zzdx = zza2.get();
        final String string3 = jsonObject.getString("call_to_action");
        final double optDouble = jsonObject.optDouble("rating", -1.0);
        final String optString = jsonObject.optString("store");
        final String optString2 = jsonObject.optString("price");
        final com.google.android.gms.ads.internal.formats.zza zza3 = zzg.get();
        final Bundle bundle = new Bundle();
        zzly zzwb;
        if (zzb != null) {
            zzwb = zzb.zzwb();
        }
        else {
            zzwb = null;
        }
        View view;
        if (zzb != null) {
            view = zzb.getView();
        }
        else {
            view = null;
        }
        return new zzd(string, list, string2, zzdx, string3, optDouble, optString, optString2, zza3, bundle, zzwb, view);
    }
}
