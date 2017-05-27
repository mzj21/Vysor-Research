// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public class zzr extends Exception
{
    private long zzab;
    public final zzi zzbi;
    
    public zzr() {
        this.zzbi = null;
    }
    
    public zzr(final zzi zzbi) {
        this.zzbi = zzbi;
    }
    
    public zzr(final Throwable t) {
        super(t);
        this.zzbi = null;
    }
    
    void zza(final long zzab) {
        this.zzab = zzab;
    }
}
