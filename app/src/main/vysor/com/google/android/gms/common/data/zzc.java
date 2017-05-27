// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import android.net.Uri;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzac;

public abstract class zzc
{
    protected final DataHolder xi;
    protected int zK;
    private int zL;
    
    public zzc(final DataHolder dataHolder, final int n) {
        this.xi = zzac.zzy(dataHolder);
        this.zzfz(n);
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = o instanceof zzc;
        boolean b2 = false;
        if (b) {
            final zzc zzc = (zzc)o;
            final boolean equal = zzab.equal(zzc.zK, this.zK);
            b2 = false;
            if (equal) {
                final boolean equal2 = zzab.equal(zzc.zL, this.zL);
                b2 = false;
                if (equal2) {
                    final DataHolder xi = zzc.xi;
                    final DataHolder xi2 = this.xi;
                    b2 = false;
                    if (xi == xi2) {
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
    
    protected boolean getBoolean(final String s) {
        return this.xi.zze(s, this.zK, this.zL);
    }
    
    protected byte[] getByteArray(final String s) {
        return this.xi.zzg(s, this.zK, this.zL);
    }
    
    protected float getFloat(final String s) {
        return this.xi.zzf(s, this.zK, this.zL);
    }
    
    protected int getInteger(final String s) {
        return this.xi.zzc(s, this.zK, this.zL);
    }
    
    protected long getLong(final String s) {
        return this.xi.zzb(s, this.zK, this.zL);
    }
    
    protected String getString(final String s) {
        return this.xi.zzd(s, this.zK, this.zL);
    }
    
    @Override
    public int hashCode() {
        return zzab.hashCode(this.zK, this.zL, this.xi);
    }
    
    public boolean isDataValid() {
        return !this.xi.isClosed();
    }
    
    protected void zza(final String s, final CharArrayBuffer charArrayBuffer) {
        this.xi.zza(s, this.zK, this.zL, charArrayBuffer);
    }
    
    protected int zzatc() {
        return this.zK;
    }
    
    protected void zzfz(final int zk) {
        zzac.zzbr(zk >= 0 && zk < this.xi.getCount());
        this.zK = zk;
        this.zL = this.xi.zzgb(this.zK);
    }
    
    public boolean zzhm(final String s) {
        return this.xi.zzhm(s);
    }
    
    protected Uri zzhn(final String s) {
        return this.xi.zzh(s, this.zK, this.zL);
    }
    
    protected boolean zzho(final String s) {
        return this.xi.zzi(s, this.zK, this.zL);
    }
}
