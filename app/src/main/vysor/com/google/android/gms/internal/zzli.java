// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

@zziy
public class zzli
{
    public static <A, B> zzlj<B> zza(final zzlj<A> zzlj, final zza<A, B> zza) {
        final zzlg<B> zzlg = new zzlg<B>();
        zzlj.zzc(new Runnable() {
            @Override
            public void run() {
                try {
                    zzlg.zzh(zza.apply(zzlj.get()));
                }
                catch (ExecutionException ex) {}
                catch (InterruptedException ex2) {
                    goto Label_0028;
                }
                catch (CancellationException ex3) {
                    goto Label_0028;
                }
            }
        });
        return zzlg;
    }
    
    public static <V> zzlj<List<V>> zzo(final List<zzlj<V>> list) {
        final zzlg<List<V>> zzlg = new zzlg<List<V>>();
        final int size = list.size();
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        final Iterator<zzlj<V>> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next().zzc(new Runnable() {
                @Override
                public void run() {
                    if (atomicInteger.incrementAndGet() < size) {
                        return;
                    }
                    try {
                        zzlg.zzh(zzp((List<zzlj<Object>>)list));
                    }
                    catch (InterruptedException ex) {}
                    catch (ExecutionException ex2) {
                        goto Label_0030;
                    }
                }
            });
        }
        return zzlg;
    }
    
    private static <V> List<V> zzp(final List<zzlj<V>> list) throws ExecutionException, InterruptedException {
        final ArrayList<Object> list2 = (ArrayList<Object>)new ArrayList<V>();
        final Iterator<zzlj<V>> iterator = list.iterator();
        while (iterator.hasNext()) {
            final Object value = iterator.next().get();
            if (value != null) {
                list2.add(value);
            }
        }
        return (List<V>)list2;
    }
    
    public interface zza<D, R>
    {
        R apply(final D p0);
    }
}
