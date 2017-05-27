// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.util;

import java.util.HashSet;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Set;
import java.util.Collections;
import android.support.v4.util.ArrayMap;
import java.util.Map;

public final class zzf
{
    public static <K, V> Map<K, V> zza(final K k, final V v, final K i, final V v2, final K j, final V v3, final K l, final V v4, final K m, final V v5, final K k2, final V v6) {
        final ArrayMap<K, V> arrayMap = new ArrayMap<K, V>(6);
        arrayMap.put(k, v);
        arrayMap.put(i, v2);
        arrayMap.put(j, v3);
        arrayMap.put(l, v4);
        arrayMap.put(m, v5);
        arrayMap.put(k2, v6);
        return Collections.unmodifiableMap((Map<? extends K, ? extends V>)arrayMap);
    }
    
    public static <T> Set<T> zza(final T t, final T t2, final T t3) {
        final zza<T> zza = new zza<T>(3);
        zza.add(t);
        zza.add(t2);
        zza.add(t3);
        return Collections.unmodifiableSet((Set<? extends T>)zza);
    }
    
    public static <T> Set<T> zza(final T t, final T t2, final T t3, final T t4) {
        final zza<T> zza = new zza<T>(4);
        zza.add(t);
        zza.add(t2);
        zza.add(t3);
        zza.add(t4);
        return Collections.unmodifiableSet((Set<? extends T>)zza);
    }
    
    private static <K, V> void zza(final K[] array, final V[] array2) {
        if (array.length != array2.length) {
            throw new IllegalArgumentException(new StringBuilder(66).append("Key and values array lengths not equal: ").append(array.length).append(" != ").append(array2.length).toString());
        }
    }
    
    public static <T> Set<T> zzaa(final T t) {
        return Collections.singleton(t);
    }
    
    public static <T> Set<T> zzaxh() {
        return Collections.emptySet();
    }
    
    public static <K, V> Map<K, V> zzaxi() {
        return Collections.emptyMap();
    }
    
    public static <K, V> Map<K, V> zzb(final K[] array, final V[] array2) {
        int i = 0;
        zza(array, array2);
        final int length = array.length;
        Map<K, V> map = null;
        switch (length) {
            default: {
                Object o;
                if (length <= 32) {
                    o = new ArrayMap<K, V>(length);
                }
                else {
                    o = new HashMap<K, V>(length, 1.0f);
                    i = 0;
                }
                while (i < length) {
                    ((Map<K, V>)o).put(array[i], array2[i]);
                    ++i;
                }
                map = Collections.unmodifiableMap((Map<? extends K, ? extends V>)o);
                break;
            }
            case 0: {
                map = zzaxi();
                break;
            }
            case 1: {
                map = zze(array[0], array2[0]);
                break;
            }
        }
        return map;
    }
    
    public static <T> List<T> zzc(final T t, final T t2) {
        final ArrayList<T> list = new ArrayList<T>(2);
        list.add(t);
        list.add(t2);
        return Collections.unmodifiableList((List<? extends T>)list);
    }
    
    public static <T> Set<T> zzc(final T... array) {
        Set<T> set = null;
        switch (array.length) {
            default: {
                Object o;
                if (array.length <= 32) {
                    o = new zza<T>((Collection<Object>)Arrays.asList(array));
                }
                else {
                    o = new HashSet<T>(Arrays.asList(array));
                }
                set = Collections.unmodifiableSet((Set<? extends T>)o);
                break;
            }
            case 0: {
                set = zzaxh();
                break;
            }
            case 1: {
                set = zzaa(array[0]);
                break;
            }
            case 2: {
                set = zzd(array[0], array[1]);
                break;
            }
            case 3: {
                set = zza(array[0], array[1], array[2]);
                break;
            }
            case 4: {
                set = zza(array[0], array[1], array[2], array[3]);
                break;
            }
        }
        return set;
    }
    
    public static <T> Set<T> zzd(final T t, final T t2) {
        final zza<T> zza = new zza<T>(2);
        zza.add(t);
        zza.add(t2);
        return Collections.unmodifiableSet((Set<? extends T>)zza);
    }
    
    public static <K, V> Map<K, V> zze(final K k, final V v) {
        return Collections.singletonMap(k, v);
    }
    
    public static <T> List<T> zzz(final T t) {
        return Collections.singletonList(t);
    }
}
