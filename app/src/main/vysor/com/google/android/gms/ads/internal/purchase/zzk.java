// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.purchase;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import android.content.Intent;
import com.google.android.gms.internal.zziy;

@zziy
public class zzk
{
    private final String zzazb;
    
    public zzk(final String zzazb) {
        this.zzazb = zzazb;
    }
    
    public boolean zza(final String s, final int n, final Intent intent) {
        boolean b = false;
        if (s != null) {
            b = false;
            if (intent != null) {
                final String zze = zzu.zzgn().zze(intent);
                final String zzf = zzu.zzgn().zzf(intent);
                b = false;
                if (zze != null) {
                    b = false;
                    if (zzf != null) {
                        if (!s.equals(zzu.zzgn().zzcc(zze))) {
                            zzb.zzdf("Developer payload not match.");
                            b = false;
                        }
                        else if (this.zzazb != null && !zzl.zzc(this.zzazb, zze, zzf)) {
                            zzb.zzdf("Fail to verify signature.");
                            b = false;
                        }
                        else {
                            b = true;
                        }
                    }
                }
            }
        }
        return b;
    }
    
    public String zzqs() {
        return zzu.zzfz().zzui();
    }
}
