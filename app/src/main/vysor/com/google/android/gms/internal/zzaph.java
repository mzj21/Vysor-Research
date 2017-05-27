// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzaph
{
    private static final Map<Class<?>, Class<?>> bmg;
    private static final Map<Class<?>, Class<?>> bmh;
    
    static {
        final HashMap<Class<?>, Class<?>> hashMap = new HashMap<Class<?>, Class<?>>(16);
        final HashMap<Class<?>, Class<?>> hashMap2 = new HashMap<Class<?>, Class<?>>(16);
        zza(hashMap, hashMap2, Boolean.TYPE, Boolean.class);
        zza(hashMap, hashMap2, Byte.TYPE, Byte.class);
        zza(hashMap, hashMap2, Character.TYPE, Character.class);
        zza(hashMap, hashMap2, Double.TYPE, Double.class);
        zza(hashMap, hashMap2, Float.TYPE, Float.class);
        zza(hashMap, hashMap2, Integer.TYPE, Integer.class);
        zza(hashMap, hashMap2, Long.TYPE, Long.class);
        zza(hashMap, hashMap2, Short.TYPE, Short.class);
        zza(hashMap, hashMap2, Void.TYPE, Void.class);
        bmg = Collections.unmodifiableMap((Map<?, ?>)hashMap);
        bmh = Collections.unmodifiableMap((Map<?, ?>)hashMap2);
    }
    
    private static void zza(final Map<Class<?>, Class<?>> map, final Map<Class<?>, Class<?>> map2, final Class<?> clazz, final Class<?> clazz2) {
        map.put(clazz, clazz2);
        map2.put(clazz2, clazz);
    }
    
    public static boolean zzk(final Type type) {
        return zzaph.bmg.containsKey(type);
    }
    
    public static <T> Class<T> zzp(Class<T> clazz) {
        final Class<?> clazz2 = zzaph.bmg.get(zzaoz.zzy(clazz));
        if (clazz2 != null) {
            clazz = (Class<T>)clazz2;
        }
        return clazz;
    }
}
