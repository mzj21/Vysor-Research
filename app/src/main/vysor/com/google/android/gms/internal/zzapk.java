// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.lang.reflect.GenericArrayType;

public final class zzapk<E> extends zzaot<Object>
{
    public static final zzaou bmp;
    private final Class<E> bmq;
    private final zzaot<E> bmr;
    
    static {
        bmp = new zzaou() {
            @Override
            public <T> zzaot<T> zza(final zzaob zzaob, final zzapx<T> zzapx) {
                final Type bz = zzapx.bz();
                zzapk zzapk;
                if (!(bz instanceof GenericArrayType) && (!(bz instanceof Class) || !((Class)bz).isArray())) {
                    zzapk = null;
                }
                else {
                    final Type zzh = zzapa.zzh(bz);
                    zzapk = new zzapk(zzaob, (zzaot<Object>)zzaob.zza(zzapx.zzl(zzh)), (Class<Object>)zzapa.zzf(zzh));
                }
                return (zzaot<T>)zzapk;
            }
        };
    }
    
    public zzapk(final zzaob zzaob, final zzaot<E> zzaot, final Class<E> bmq) {
        this.bmr = new zzapv<E>(zzaob, zzaot, bmq);
        this.bmq = bmq;
    }
    
    @Override
    public void zza(final zzaqa zzaqa, final Object o) throws IOException {
        if (o == null) {
            zzaqa.bx();
        }
        else {
            zzaqa.bt();
            for (int i = 0; i < Array.getLength(o); ++i) {
                this.bmr.zza(zzaqa, (E)Array.get(o, i));
            }
            zzaqa.bu();
        }
    }
    
    @Override
    public Object zzb(final zzapy zzapy) throws IOException {
        Object o;
        if (zzapy.bn() == zzapz.bos) {
            zzapy.nextNull();
            o = null;
        }
        else {
            final ArrayList<E> list = new ArrayList<E>();
            zzapy.beginArray();
            while (zzapy.hasNext()) {
                list.add(this.bmr.zzb(zzapy));
            }
            zzapy.endArray();
            final Object instance = Array.newInstance(this.bmq, list.size());
            for (int i = 0; i < list.size(); ++i) {
                Array.set(instance, i, list.get(i));
            }
            o = instance;
        }
        return o;
    }
}
