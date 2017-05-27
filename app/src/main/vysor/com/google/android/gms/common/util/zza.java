// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.util;

import android.support.v4.util.SimpleArrayMap;
import java.util.Iterator;
import java.util.Collection;
import android.support.v4.util.ArrayMap;
import java.util.AbstractSet;

public class zza<E> extends AbstractSet<E>
{
    private final ArrayMap<E, E> EJ;
    
    public zza() {
        this.EJ = new ArrayMap<E, E>();
    }
    
    public zza(final int n) {
        this.EJ = new ArrayMap<E, E>(n);
    }
    
    public zza(final Collection<E> collection) {
        this(collection.size());
        this.addAll((Collection<? extends E>)collection);
    }
    
    @Override
    public boolean add(final E e) {
        boolean b;
        if (this.EJ.containsKey(e)) {
            b = false;
        }
        else {
            this.EJ.put(e, e);
            b = true;
        }
        return b;
    }
    
    @Override
    public boolean addAll(final Collection<? extends E> collection) {
        boolean b;
        if (collection instanceof zza) {
            b = this.zza((zza<? extends E>)collection);
        }
        else {
            b = super.addAll(collection);
        }
        return b;
    }
    
    @Override
    public void clear() {
        this.EJ.clear();
    }
    
    @Override
    public boolean contains(final Object o) {
        return this.EJ.containsKey(o);
    }
    
    @Override
    public Iterator<E> iterator() {
        return this.EJ.keySet().iterator();
    }
    
    @Override
    public boolean remove(final Object o) {
        boolean b;
        if (!this.EJ.containsKey(o)) {
            b = false;
        }
        else {
            this.EJ.remove(o);
            b = true;
        }
        return b;
    }
    
    @Override
    public int size() {
        return this.EJ.size();
    }
    
    public boolean zza(final zza<? extends E> zza) {
        final int size = this.size();
        this.EJ.putAll((SimpleArrayMap<?, ?>)zza.EJ);
        return this.size() > size;
    }
}
