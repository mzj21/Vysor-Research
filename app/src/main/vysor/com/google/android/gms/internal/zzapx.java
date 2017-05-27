// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class zzapx<T>
{
    final Type bmR;
    final Class<? super T> bnV;
    final int bnW;
    
    protected zzapx() {
        this.bmR = zzq(this.getClass());
        this.bnV = (Class<? super T>)zzapa.zzf(this.bmR);
        this.bnW = this.bmR.hashCode();
    }
    
    zzapx(final Type type) {
        this.bmR = zzapa.zze(zzaoz.zzy(type));
        this.bnV = (Class<? super T>)zzapa.zzf(this.bmR);
        this.bnW = this.bmR.hashCode();
    }
    
    public static zzapx<?> zzl(final Type type) {
        return new zzapx<Object>(type);
    }
    
    static Type zzq(final Class<?> clazz) {
        final Type genericSuperclass = clazz.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        return zzapa.zze(((ParameterizedType)genericSuperclass).getActualTypeArguments()[0]);
    }
    
    public static <T> zzapx<T> zzr(final Class<T> clazz) {
        return new zzapx<T>(clazz);
    }
    
    public final Class<? super T> by() {
        return this.bnV;
    }
    
    public final Type bz() {
        return this.bmR;
    }
    
    @Override
    public final boolean equals(final Object o) {
        return o instanceof zzapx && zzapa.zza(this.bmR, ((zzapx)o).bmR);
    }
    
    @Override
    public final int hashCode() {
        return this.bnW;
    }
    
    @Override
    public final String toString() {
        return zzapa.zzg(this.bmR);
    }
}
