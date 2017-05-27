// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Future;
import com.google.android.gms.common.internal.zzc;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import java.util.Map;

@zziy
public class zzfk implements zzev
{
    @Override
    public void zza(final zzlt zzlt, final Map<String, String> map) {
        final zzfi zzgw = zzu.zzgw();
        if (map.containsKey("abort")) {
            if (!zzgw.zze(zzlt)) {
                zzb.zzdf("Precache abort but no preload task running.");
            }
        }
        else {
            final String s = map.get("src");
            if (s == null) {
                zzb.zzdf("Precache video action is missing the src parameter.");
            }
            else {
                int int1 = 0;
                String s2 = null;
            Label_0099_Outer:
                while (true) {
                    while (true) {
                        while (true) {
                            try {
                                int1 = Integer.parseInt(map.get("player"));
                                if (map.containsKey("mimetype")) {
                                    s2 = map.get("mimetype");
                                    if (zzgw.zzf(zzlt)) {
                                        zzb.zzdf("Precache task already running.");
                                        return;
                                    }
                                    break;
                                }
                            }
                            catch (NumberFormatException ex) {
                                int1 = 0;
                                continue Label_0099_Outer;
                            }
                            break;
                        }
                        s2 = "";
                        continue;
                    }
                }
                zzc.zzu(zzlt.zzdp());
                final Future future = (Future)new zzfh(zzlt, zzlt.zzdp().zzamc.zza(zzlt, int1, s2), s).zzqw();
            }
        }
    }
}
