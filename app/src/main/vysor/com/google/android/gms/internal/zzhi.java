// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;

@zziy
public class zzhi
{
    private final zzlt zzbkr;
    private final boolean zzbvj;
    private final String zzbvk;
    
    public zzhi(final zzlt zzbkr, final Map<String, String> map) {
        this.zzbkr = zzbkr;
        this.zzbvk = map.get("forceOrientation");
        if (map.containsKey("allowOrientationChange")) {
            this.zzbvj = Boolean.parseBoolean(map.get("allowOrientationChange"));
        }
        else {
            this.zzbvj = true;
        }
    }
    
    public void execute() {
        if (this.zzbkr == null) {
            zzb.zzdf("AdWebView is null");
        }
        else {
            int requestedOrientation;
            if ("portrait".equalsIgnoreCase(this.zzbvk)) {
                requestedOrientation = zzu.zzgb().zzun();
            }
            else if ("landscape".equalsIgnoreCase(this.zzbvk)) {
                requestedOrientation = zzu.zzgb().zzum();
            }
            else if (this.zzbvj) {
                requestedOrientation = -1;
            }
            else {
                requestedOrientation = zzu.zzgb().zzuo();
            }
            this.zzbkr.setRequestedOrientation(requestedOrientation);
        }
    }
}
