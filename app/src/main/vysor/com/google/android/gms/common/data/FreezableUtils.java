// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import java.util.Iterator;
import java.util.ArrayList;

public final class FreezableUtils
{
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(final ArrayList<E> list) {
        final ArrayList list2 = new ArrayList(list.size());
        for (int size = list.size(), i = 0; i < size; ++i) {
            list2.add(list.get(i).freeze());
        }
        return (ArrayList<T>)list2;
    }
    
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(final E[] array) {
        final ArrayList<T> list = new ArrayList<T>(array.length);
        for (int i = 0; i < array.length; ++i) {
            list.add(array[i].freeze());
        }
        return list;
    }
    
    public static <T, E extends Freezable<T>> ArrayList<T> freezeIterable(final Iterable<E> iterable) {
        final ArrayList<Object> list = (ArrayList<Object>)new ArrayList<T>();
        final Iterator<E> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().freeze());
        }
        return (ArrayList<T>)list;
    }
}
