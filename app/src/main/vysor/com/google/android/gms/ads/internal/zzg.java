// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import org.json.JSONObject;
import com.google.android.gms.internal.zzfz;
import com.google.android.gms.internal.zzlm;
import com.google.android.gms.internal.zzfy;
import com.google.android.gms.internal.zzkr;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.zzdi;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzkg;
import java.util.Map;
import com.google.android.gms.internal.zzlt;
import com.google.android.gms.internal.zzev;
import android.content.Context;
import com.google.android.gms.internal.zziy;

@zziy
public class zzg
{
    private Context mContext;
    private final Object zzakd;
    public final zzev zzamn;
    
    public zzg() {
        this.zzakd = new Object();
        this.zzamn = new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                zzlt.zzb("/appSettingsFetched", this);
                final Object zza = zzg.this.zzakd;
                // monitorenter(zza)
                if (map == null) {
                    return;
                }
                try {
                    if ("true".equalsIgnoreCase(map.get("isSuccessful"))) {
                        zzu.zzgd().zzd(zzg.this.mContext, map.get("appSettingsJson"));
                    }
                }
                finally {
                }
                // monitorexit(zza)
            }
        };
    }
    
    private static boolean zza(@Nullable final zzkg zzkg) {
        boolean b = true;
        if (zzkg != null) {
            b = (((zzu.zzgf().currentTimeMillis() - zzkg.zztf() > zzdi.zzbgs.get() && b) || !zzkg.zztg()) && b);
        }
        return b;
    }
    
    public void zza(final Context mContext, final VersionInfoParcel versionInfoParcel, final boolean b, @Nullable final zzkg zzkg, final String s, @Nullable final String s2) {
        if (zza(zzkg)) {
            if (mContext == null) {
                zzb.zzdf("Context not provided to fetch application settings");
            }
            else if (TextUtils.isEmpty((CharSequence)s) && TextUtils.isEmpty((CharSequence)s2)) {
                zzb.zzdf("App settings could not be fetched. Required parameters missing");
            }
            else {
                this.mContext = mContext;
                zzkr.zzcrf.post((Runnable)new Runnable() {
                    final /* synthetic */ zzfy zzamp = zzu.zzfz().zzc(mContext, versionInfoParcel);
                    
                    @Override
                    public void run() {
                        this.zzamp.zzmy().zza(new zzlm.zzc<zzfz>() {
                            public void zzb(final zzfz zzfz) {
                                zzfz.zza("/appSettingsFetched", zzg.this.zzamn);
                                try {
                                    final JSONObject jsonObject = new JSONObject();
                                    if (!TextUtils.isEmpty((CharSequence)s)) {
                                        jsonObject.put("app_id", (Object)s);
                                    }
                                    else if (!TextUtils.isEmpty((CharSequence)s2)) {
                                        jsonObject.put("ad_unit_id", (Object)s2);
                                    }
                                    jsonObject.put("is_init", b);
                                    jsonObject.put("pn", (Object)mContext.getPackageName());
                                    zzfz.zza("AFMA_fetchAppSettings", jsonObject);
                                }
                                catch (Exception ex) {
                                    zzfz.zzb("/appSettingsFetched", zzg.this.zzamn);
                                    com.google.android.gms.ads.internal.util.client.zzb.zzb("Error requesting application settings", ex);
                                }
                            }
                        }, new zzlm.zzb());
                    }
                });
            }
        }
    }
}
