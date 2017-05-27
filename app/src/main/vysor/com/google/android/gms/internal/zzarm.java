// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

final class zzarm
{
    final byte[] avk;
    final int tag;
    
    zzarm(final int tag, final byte[] avk) {
        this.tag = tag;
        this.avk = avk;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        if (o != this) {
            if (!(o instanceof zzarm)) {
                b = false;
            }
            else {
                final zzarm zzarm = (zzarm)o;
                if (this.tag != zzarm.tag || !Arrays.equals(this.avk, zzarm.avk)) {
                    b = false;
                }
            }
        }
        return b;
    }
    
    @Override
    public int hashCode() {
        return 31 * (527 + this.tag) + Arrays.hashCode(this.avk);
    }
    
    void zza(final zzard zzard) throws IOException {
        zzard.zzahm(this.tag);
        zzard.zzbh(this.avk);
    }
    
    int zzx() {
        return 0 + zzard.zzahn(this.tag) + this.avk.length;
    }
}
