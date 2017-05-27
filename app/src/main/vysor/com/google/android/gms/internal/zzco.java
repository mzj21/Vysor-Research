// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONObject;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.content.Context;

@zziy
public class zzco extends zzcj
{
    private final zzfz zzato;
    
    public zzco(final Context context, final AdSizeParcel adSizeParcel, final zzke zzke, final VersionInfoParcel versionInfoParcel, final zzcq zzcq, final zzfz zzato) {
        super(context, adSizeParcel, zzke, versionInfoParcel, zzcq);
        this.zzc(this.zzato = zzato);
        this.zzhj();
        this.zzk(3);
        final String value = String.valueOf(this.zzasj.zzia());
        String concat;
        if (value.length() != 0) {
            concat = "Tracking ad unit: ".concat(value);
        }
        else {
            concat = new String("Tracking ad unit: ");
        }
        com.google.android.gms.ads.internal.util.client.zzb.zzdd(concat);
    }
    
    @Override
    protected void destroy() {
        synchronized (this.zzakd) {
            super.destroy();
            this.zzd(this.zzato);
        }
    }
    
    @Override
    protected void zzb(final JSONObject jsonObject) {
        this.zzato.zza("AFMA_updateActiveView", jsonObject);
    }
    
    @Override
    public void zzhl() {
        this.destroy();
    }
    
    @Override
    protected boolean zzhr() {
        return true;
    }
}
