// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

public final class zzapl implements zzaou
{
    private final zzapb bkM;
    
    public zzapl(final zzapb bkM) {
        this.bkM = bkM;
    }
    
    @Override
    public <T> zzaot<T> zza(final zzaob zzaob, final zzapx<T> zzapx) {
        final Type bz = zzapx.bz();
        final Class<? super T> by = zzapx.by();
        zza zza;
        if (!Collection.class.isAssignableFrom(by)) {
            zza = null;
        }
        else {
            final Type zza2 = zzapa.zza(bz, by);
            zza = new zza(zzaob, zza2, (zzaot<Object>)zzaob.zza(zzapx.zzl(zza2)), (zzapg<? extends Collection<Object>>)this.bkM.zzb((zzapx<? extends Collection<T>>)zzapx));
        }
        return (zzaot<T>)zza;
    }
    
    private static final class zza<E> extends zzaot<Collection<E>>
    {
        private final zzaot<E> bms;
        private final zzapg<? extends Collection<E>> bmt;
        
        public zza(final zzaob zzaob, final Type type, final zzaot<E> zzaot, final zzapg<? extends Collection<E>> bmt) {
            this.bms = new zzapv<E>(zzaob, zzaot, type);
            this.bmt = bmt;
        }
        
        @Override
        public void zza(final zzaqa zzaqa, final Collection<E> collection) throws IOException {
            if (collection == null) {
                zzaqa.bx();
            }
            else {
                zzaqa.bt();
                final Iterator<E> iterator = collection.iterator();
                while (iterator.hasNext()) {
                    this.bms.zza(zzaqa, iterator.next());
                }
                zzaqa.bu();
            }
        }
        
        public Collection<E> zzj(final zzapy zzapy) throws IOException {
            Collection<E> collection;
            if (zzapy.bn() == zzapz.bos) {
                zzapy.nextNull();
                collection = null;
            }
            else {
                collection = (Collection<E>)this.bmt.bg();
                zzapy.beginArray();
                while (zzapy.hasNext()) {
                    collection.add(this.bms.zzb(zzapy));
                }
                zzapy.endArray();
            }
            return collection;
        }
    }
}
