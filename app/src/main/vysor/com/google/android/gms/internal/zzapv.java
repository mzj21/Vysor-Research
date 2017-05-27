// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.Type;

final class zzapv<T> extends zzaot<T>
{
    private final zzaot<T> bkU;
    private final zzaob bmQ;
    private final Type bmR;
    
    zzapv(final zzaob bmQ, final zzaot<T> bkU, final Type bmR) {
        this.bmQ = bmQ;
        this.bkU = bkU;
        this.bmR = bmR;
    }
    
    private Type zzb(Type class1, final Object o) {
        if (o != null && (class1 == Object.class || class1 instanceof TypeVariable || class1 instanceof Class)) {
            class1 = o.getClass();
        }
        return class1;
    }
    
    @Override
    public void zza(final zzaqa zzaqa, final T t) throws IOException {
        zzaot<?> zzaot = this.bkU;
        final Type zzb = this.zzb(this.bmR, t);
        if (zzb != this.bmR) {
            zzaot = this.bmQ.zza(zzapx.zzl(zzb));
            if (zzaot instanceof zzaps.zza && !(this.bkU instanceof zzaps.zza)) {
                zzaot = this.bkU;
            }
        }
        zzaot.zza(zzaqa, t);
    }
    
    @Override
    public T zzb(final zzapy zzapy) throws IOException {
        return this.bkU.zzb(zzapy);
    }
}
