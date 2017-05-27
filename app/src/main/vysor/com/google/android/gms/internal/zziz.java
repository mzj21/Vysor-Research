// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;
import org.json.JSONObject;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import java.util.List;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.location.Location;

@zziy
public class zziz
{
    @Nullable
    public Location zzawl;
    @Nullable
    public String zzcfw;
    @Nullable
    public Bundle zzcgb;
    @Nullable
    public List<String> zzcgk;
    @Nullable
    public Bundle zzciu;
    @Nullable
    public zzjl.zza zzciv;
    @Nullable
    public String zzciw;
    public AdRequestInfoParcel zzcix;
    public zzjh zzciy;
    public JSONObject zzciz;
    
    public zziz() {
        this.zzciz = new JSONObject();
        this.zzcgk = new ArrayList<String>();
    }
    
    public zziz zza(final zzjh zzciy) {
        this.zzciy = zzciy;
        return this;
    }
    
    public zziz zza(final zzjl.zza zzciv) {
        this.zzciv = zzciv;
        return this;
    }
    
    public zziz zzc(final Location zzawl) {
        this.zzawl = zzawl;
        return this;
    }
    
    public zziz zzci(final String zzcfw) {
        this.zzcfw = zzcfw;
        return this;
    }
    
    public zziz zzcj(final String zzciw) {
        this.zzciw = zzciw;
        return this;
    }
    
    public zziz zze(final Bundle zzciu) {
        this.zzciu = zzciu;
        return this;
    }
    
    public zziz zzf(final Bundle zzcgb) {
        this.zzcgb = zzcgb;
        return this;
    }
    
    public zziz zzf(final AdRequestInfoParcel zzcix) {
        this.zzcix = zzcix;
        return this;
    }
    
    public zziz zzj(final JSONObject zzciz) {
        this.zzciz = zzciz;
        return this;
    }
    
    public zziz zzk(final List<String> zzcgk) {
        if (zzcgk == null) {
            this.zzcgk.clear();
        }
        this.zzcgk = zzcgk;
        return this;
    }
}
