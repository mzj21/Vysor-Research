// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;

@zziy
public final class zzeq implements zzev
{
    private final zzer zzbma;
    
    public zzeq(final zzer zzbma) {
        this.zzbma = zzbma;
    }
    
    @Override
    public void zza(final zzlt zzlt, final Map<String, String> map) {
        final String s = map.get("name");
        if (s == null) {
            zzb.zzdf("App event with no name parameter.");
        }
        else {
            this.zzbma.onAppEvent(s, map.get("info"));
        }
    }
}
