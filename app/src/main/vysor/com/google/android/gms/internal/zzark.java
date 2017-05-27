// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzark
{
    protected volatile int bqE;
    
    public zzark() {
        this.bqE = -1;
    }
    
    public static final <T extends zzark> T zza(final T t, final byte[] array) throws zzarj {
        return zzb(t, array, 0, array.length);
    }
    
    public static final void zza(final zzark zzark, final byte[] array, final int n, final int n2) {
        try {
            final zzard zzc = zzard.zzc(array, n, n2);
            zzark.zza(zzc);
            zzc.cO();
        }
        catch (IOException ex) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", ex);
        }
    }
    
    public static final <T extends zzark> T zzb(final T t, final byte[] array, final int n, final int n2) throws zzarj {
        try {
            final zzarc zzb = zzarc.zzb(array, n, n2);
            t.zzb(zzb);
            zzb.zzagz(0);
            return t;
        }
        catch (zzarj zzarj) {
            throw zzarj;
        }
        catch (IOException ex) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }
    
    public static final byte[] zzf(final zzark zzark) {
        final byte[] array = new byte[zzark.db()];
        zza(zzark, array, 0, array.length);
        return array;
    }
    
    public zzark cQ() throws CloneNotSupportedException {
        return (zzark)super.clone();
    }
    
    public int da() {
        if (this.bqE < 0) {
            this.db();
        }
        return this.bqE;
    }
    
    public int db() {
        return this.bqE = this.zzx();
    }
    
    @Override
    public String toString() {
        return zzarl.zzg(this);
    }
    
    public void zza(final zzard zzard) throws IOException {
    }
    
    public abstract zzark zzb(final zzarc p0) throws IOException;
    
    protected int zzx() {
        return 0;
    }
}
