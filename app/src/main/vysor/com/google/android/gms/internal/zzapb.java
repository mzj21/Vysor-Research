// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.SortedMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.LinkedHashSet;
import java.util.Set;
import java.lang.reflect.ParameterizedType;
import java.util.EnumSet;
import java.util.TreeSet;
import java.util.SortedSet;
import java.util.Collection;
import java.lang.reflect.Type;
import java.util.Map;

public final class zzapb
{
    private final Map<Type, zzaod<?>> bkY;
    
    public zzapb(final Map<Type, zzaod<?>> bkY) {
        this.bkY = bkY;
    }
    
    private <T> zzapg<T> zzc(final Type type, final Class<? super T> clazz) {
        zzapg<T> zzapg;
        if (Collection.class.isAssignableFrom(clazz)) {
            if (SortedSet.class.isAssignableFrom(clazz)) {
                zzapg = new zzapg<T>() {
                    @Override
                    public T bg() {
                        return (T)new TreeSet();
                    }
                };
            }
            else if (EnumSet.class.isAssignableFrom(clazz)) {
                zzapg = new zzapg<T>() {
                    @Override
                    public T bg() {
                        if (!(type instanceof ParameterizedType)) {
                            final String value = String.valueOf(type.toString());
                            String concat;
                            if (value.length() != 0) {
                                concat = "Invalid EnumSet type: ".concat(value);
                            }
                            else {
                                concat = new String("Invalid EnumSet type: ");
                            }
                            throw new zzaoi(concat);
                        }
                        final Type type = ((ParameterizedType)type).getActualTypeArguments()[0];
                        if (type instanceof Class) {
                            return (T)EnumSet.noneOf((Class<Enum>)type);
                        }
                        final String value2 = String.valueOf(type.toString());
                        String concat2;
                        if (value2.length() != 0) {
                            concat2 = "Invalid EnumSet type: ".concat(value2);
                        }
                        else {
                            concat2 = new String("Invalid EnumSet type: ");
                        }
                        throw new zzaoi(concat2);
                    }
                };
            }
            else if (Set.class.isAssignableFrom(clazz)) {
                zzapg = new zzapg<T>() {
                    @Override
                    public T bg() {
                        return (T)new LinkedHashSet();
                    }
                };
            }
            else if (Queue.class.isAssignableFrom(clazz)) {
                zzapg = new zzapg<T>() {
                    @Override
                    public T bg() {
                        return (T)new LinkedList();
                    }
                };
            }
            else {
                zzapg = new zzapg<T>() {
                    @Override
                    public T bg() {
                        return (T)new ArrayList();
                    }
                };
            }
        }
        else if (Map.class.isAssignableFrom(clazz)) {
            if (SortedMap.class.isAssignableFrom(clazz)) {
                zzapg = new zzapg<T>() {
                    @Override
                    public T bg() {
                        return (T)new TreeMap();
                    }
                };
            }
            else if (type instanceof ParameterizedType && !String.class.isAssignableFrom(zzapx.zzl(((ParameterizedType)type).getActualTypeArguments()[0]).by())) {
                zzapg = new zzapg<T>() {
                    @Override
                    public T bg() {
                        return (T)new LinkedHashMap();
                    }
                };
            }
            else {
                zzapg = new zzapg<T>() {
                    @Override
                    public T bg() {
                        return (T)new zzapf();
                    }
                };
            }
        }
        else {
            zzapg = null;
        }
        return zzapg;
    }
    
    private <T> zzapg<T> zzd(final Type type, final Class<? super T> clazz) {
        return new zzapg<T>() {
            private final zzapj blB = zzapj.bl();
            
            @Override
            public T bg() {
                try {
                    return this.blB.zzf(clazz);
                }
                catch (Exception ex) {
                    final String value = String.valueOf(type);
                    throw new RuntimeException(new StringBuilder(116 + String.valueOf(value).length()).append("Unable to invoke no-args constructor for ").append(value).append(". ").append("Register an InstanceCreator with Gson for this type may fix this problem.").toString(), ex);
                }
            }
        };
    }
    
    private <T> zzapg<T> zzl(final Class<? super T> clazz) {
        try {
            final Constructor<? super T> declaredConstructor = clazz.getDeclaredConstructor((Class<?>[])new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new zzapg<T>() {
                @Override
                public T bg() {
                    try {
                        return declaredConstructor.newInstance((Object[])null);
                    }
                    catch (InstantiationException ex) {
                        final String value = String.valueOf(declaredConstructor);
                        throw new RuntimeException(new StringBuilder(30 + String.valueOf(value).length()).append("Failed to invoke ").append(value).append(" with no args").toString(), ex);
                    }
                    catch (InvocationTargetException ex2) {
                        final String value2 = String.valueOf(declaredConstructor);
                        throw new RuntimeException(new StringBuilder(30 + String.valueOf(value2).length()).append("Failed to invoke ").append(value2).append(" with no args").toString(), ex2.getTargetException());
                    }
                    catch (IllegalAccessException ex3) {
                        throw new AssertionError((Object)ex3);
                    }
                }
            };
        }
        catch (NoSuchMethodException ex) {
            return null;
        }
    }
    
    @Override
    public String toString() {
        return this.bkY.toString();
    }
    
    public <T> zzapg<T> zzb(final zzapx<T> zzapx) {
        final Type bz = zzapx.bz();
        final Class<? super T> by = zzapx.by();
        final zzaod<?> zzaod = this.bkY.get(bz);
        Object o;
        if (zzaod != null) {
            o = new zzapg<T>() {
                @Override
                public T bg() {
                    return zzaod.zza(bz);
                }
            };
        }
        else {
            final zzaod<?> zzaod2 = this.bkY.get(by);
            if (zzaod2 != null) {
                o = new zzapg<T>() {
                    @Override
                    public T bg() {
                        return zzaod2.zza(bz);
                    }
                };
            }
            else {
                o = this.zzl((Class<? super Object>)by);
                if (o == null) {
                    o = this.zzc(bz, (Class<? super Object>)by);
                    if (o == null) {
                        o = this.zzd(bz, (Class<? super Object>)by);
                    }
                }
            }
        }
        return (zzapg<T>)o;
    }
}
