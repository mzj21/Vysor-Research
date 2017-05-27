// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.ads.internal.reward.client.zza;

@zziy
public class zzjp extends zza.zza
{
    private final String zzcln;
    private final int zzcms;
    
    public zzjp(final String zzcln, final int zzcms) {
        this.zzcln = zzcln;
        this.zzcms = zzcms;
    }
    
    public boolean equals(final Object o) {
        boolean b = false;
        if (o != null) {
            final boolean b2 = o instanceof zzjp;
            b = false;
            if (b2) {
                final zzjp zzjp = (zzjp)o;
                final boolean equal = zzab.equal(this.getType(), zzjp.getType());
                b = false;
                if (equal) {
                    final boolean equal2 = zzab.equal(this.getAmount(), zzjp.getAmount());
                    b = false;
                    if (equal2) {
                        b = true;
                    }
                }
            }
        }
        return b;
    }
    
    public int getAmount() {
        return this.zzcms;
    }
    
    public String getType() {
        return this.zzcln;
    }
}
