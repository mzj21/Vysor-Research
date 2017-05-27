// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import android.text.TextUtils;
import java.util.Map;

@zziy
class zzfg implements zzev
{
    private int zzg(final Map<String, String> map) throws NullPointerException, NumberFormatException {
        int int1 = Integer.parseInt(map.get("playbackState"));
        if (int1 < 0 || 3 < int1) {
            int1 = 0;
        }
        return int1;
    }
    
    @Override
    public void zza(final zzlt zzlt, final Map<String, String> map) {
        if (zzdi.zzbef.get()) {
            final zzly zzwb = zzlt.zzwb();
            while (true) {
                Label_0243: {
                    if (zzwb != null) {
                        break Label_0243;
                    }
                    try {
                        final zzly zzly = new zzly(zzlt, Float.parseFloat(map.get("duration")));
                        zzlt.zza(zzly);
                        final zzly zzly2 = zzly;
                        final boolean equals = "1".equals(map.get("muted"));
                        final float float1 = Float.parseFloat(map.get("currentTime"));
                        final int zzg = this.zzg(map);
                        final String s = map.get("aspectRatio");
                        if (TextUtils.isEmpty((CharSequence)s)) {
                            final float n = 0.0f;
                            if (zzb.zzbf(3)) {
                                zzb.zzdd(new StringBuilder(79 + String.valueOf(s).length()).append("Video Meta GMSG: isMuted : ").append(equals).append(" , playbackState : ").append(zzg).append(" , aspectRatio : ").append(s).toString());
                            }
                            zzly2.zza(float1, zzg, equals, n);
                            return;
                        }
                        goto Label_0224;
                    }
                    catch (NullPointerException ex) {}
                    catch (NumberFormatException ex2) {
                        goto Label_0204;
                    }
                }
                final zzly zzly2 = zzwb;
                continue;
            }
        }
    }
}
