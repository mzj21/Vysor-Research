// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.content.Context;

@zziy
public class zzim extends zzih implements zza
{
    zzim(final Context context, final zzke.zza zza, final zzlt zzlt, final zzil.zza zza2) {
        super(context, zza, zzlt, zza2);
    }
    
    @Override
    protected void zzqu() {
        if (this.zzccl.errorCode == -2) {
            this.zzbkr.zzvr().zza((zzlu.zza)this);
            this.zzrb();
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Loading HTML in WebView.");
            this.zzbkr.loadDataWithBaseURL(zzu.zzfz().zzcv(this.zzccl.zzbyj), this.zzccl.body, "text/html", "UTF-8", null);
        }
    }
    
    protected void zzrb() {
    }
}
