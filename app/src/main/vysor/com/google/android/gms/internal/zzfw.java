// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Future;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.content.Context;

@zziy
public class zzfw
{
    private zzfv zza(final Context context, final VersionInfoParcel versionInfoParcel, final zza<zzfv> zza, final zzau zzau, final zzd zzd) {
        final zzfx zzbqb = new zzfx(context, versionInfoParcel, zzau, zzd);
        (zza.zzbqb = zzbqb).zza((zzfv.zza)new zzfv.zza() {
            @Override
            public void zzmx() {
                zza.zzh(zza.zzbqb);
            }
        });
        return zzbqb;
    }
    
    public Future<zzfv> zza(final Context context, final VersionInfoParcel versionInfoParcel, final String s, final zzau zzau, final zzd zzd) {
        final zza zza = (zza)new zza<zzfv>();
        zzkr.zzcrf.post((Runnable)new Runnable() {
            @Override
            public void run() {
                zzfw.this.zza(context, versionInfoParcel, zza, zzau, zzd).zzbl(s);
            }
        });
        return (Future<zzfv>)zza;
    }
    
    private static class zza<JavascriptEngine> extends zzlg<JavascriptEngine>
    {
        JavascriptEngine zzbqb;
    }
}
