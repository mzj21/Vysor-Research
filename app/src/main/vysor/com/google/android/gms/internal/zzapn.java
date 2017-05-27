// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class zzapn implements zzaou
{
    private final zzapb bkM;
    
    public zzapn(final zzapb bkM) {
        this.bkM = bkM;
    }
    
    static zzaot<?> zza(final zzapb zzapb, final zzaob zzaob, final zzapx<?> zzapx, final zzaov zzaov) {
        final Class<?> value = zzaov.value();
        zzaot<?> zza;
        if (zzaot.class.isAssignableFrom(value)) {
            zza = zzapb.zzb((zzapx<zzaot<?>>)zzapx.zzr(value)).bg();
        }
        else {
            if (!zzaou.class.isAssignableFrom(value)) {
                throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter or TypeAdapterFactory reference.");
            }
            zza = zzapb.zzb((zzapx<zzaou>)zzapx.zzr(value)).bg().zza(zzaob, zzapx);
        }
        return zza;
    }
    
    @Override
    public <T> zzaot<T> zza(final zzaob zzaob, final zzapx<T> zzapx) {
        final zzaov zzaov = zzapx.by().getAnnotation(zzaov.class);
        zzaot<?> zza;
        if (zzaov == null) {
            zza = null;
        }
        else {
            zza = zza(this.bkM, zzaob, zzapx, zzaov);
        }
        return (zzaot<T>)zza;
    }
}
