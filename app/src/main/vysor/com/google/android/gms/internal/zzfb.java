// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.util.zzf;
import com.google.android.gms.ads.internal.zze;
import java.util.Map;

@zziy
public class zzfb implements zzev
{
    static final Map<String, Integer> zzbnn;
    private final zze zzbnl;
    private final zzhh zzbnm;
    
    static {
        zzbnn = zzf.zza("resize", 1, "playVideo", 2, "storePicture", 3, "createCalendarEvent", 4, "setOrientationProperties", 5, "closeResizedAd", 6);
    }
    
    public zzfb(final zze zzbnl, final zzhh zzbnm) {
        this.zzbnl = zzbnl;
        this.zzbnm = zzbnm;
    }
    
    @Override
    public void zza(final zzlt zzlt, final Map<String, String> map) {
        final int intValue = zzfb.zzbnn.get(map.get("a"));
        if (intValue != 5 && this.zzbnl != null && !this.zzbnl.zzer()) {
            this.zzbnl.zzv(null);
        }
        else {
            switch (intValue) {
                default: {
                    zzb.zzde("Unknown MRAID command called.");
                    break;
                }
                case 1: {
                    this.zzbnm.execute(map);
                    break;
                }
                case 4: {
                    new zzhg(zzlt, map).execute();
                    break;
                }
                case 3: {
                    new zzhj(zzlt, map).execute();
                    break;
                }
                case 5: {
                    new zzhi(zzlt, map).execute();
                    break;
                }
                case 6: {
                    this.zzbnm.zzt(true);
                    break;
                }
            }
        }
    }
}
