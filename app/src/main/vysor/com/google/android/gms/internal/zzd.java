// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public class zzd implements zzo
{
    private int zzn;
    private int zzo;
    private final int zzp;
    private final float zzq;
    
    public zzd() {
        this(2500, 1, 1.0f);
    }
    
    public zzd(final int zzn, final int zzp, final float zzq) {
        this.zzn = zzn;
        this.zzp = zzp;
        this.zzq = zzq;
    }
    
    @Override
    public void zza(final zzr zzr) throws zzr {
        ++this.zzo;
        this.zzn += (int)(this.zzn * this.zzq);
        if (!this.zze()) {
            throw zzr;
        }
    }
    
    @Override
    public int zzc() {
        return this.zzn;
    }
    
    @Override
    public int zzd() {
        return this.zzo;
    }
    
    protected boolean zze() {
        return this.zzo <= this.zzp;
    }
}
