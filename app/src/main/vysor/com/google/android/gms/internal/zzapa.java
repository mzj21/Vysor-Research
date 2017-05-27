// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.Modifier;
import java.lang.reflect.Array;
import java.io.Serializable;
import java.util.Map;
import java.util.Properties;
import java.util.Arrays;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.util.NoSuchElementException;
import java.lang.reflect.Type;

public final class zzapa
{
    static final Type[] blr;
    
    static {
        blr = new Type[0];
    }
    
    static boolean equal(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    private static int zza(final Object[] array, final Object o) {
        for (int i = 0; i < array.length; ++i) {
            if (o.equals(array[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }
    
    private static Class<?> zza(final TypeVariable<?> typeVariable) {
        final Object genericDeclaration = typeVariable.getGenericDeclaration();
        Class<?> clazz;
        if (genericDeclaration instanceof Class) {
            clazz = (Class<?>)genericDeclaration;
        }
        else {
            clazz = null;
        }
        return clazz;
    }
    
    public static ParameterizedType zza(final Type type, final Type type2, final Type... array) {
        return new zzb(type, type2, array);
    }
    
    public static Type zza(final Type type, final Class<?> clazz) {
        Type zzb = zzb(type, clazz, Collection.class);
        if (zzb instanceof WildcardType) {
            zzb = ((WildcardType)zzb).getUpperBounds()[0];
        }
        Type type2;
        if (zzb instanceof ParameterizedType) {
            type2 = ((ParameterizedType)zzb).getActualTypeArguments()[0];
        }
        else {
            type2 = Object.class;
        }
        return type2;
    }
    
    static Type zza(Type type, Class<?> clazz, final Class<?> clazz2) {
        if (clazz2 != clazz) {
            if (clazz2.isInterface()) {
                final Class<?>[] interfaces = clazz.getInterfaces();
                for (int i = 0; i < interfaces.length; ++i) {
                    if (interfaces[i] == clazz2) {
                        type = clazz.getGenericInterfaces()[i];
                        return type;
                    }
                    if (clazz2.isAssignableFrom(interfaces[i])) {
                        type = zza(clazz.getGenericInterfaces()[i], interfaces[i], clazz2);
                        return type;
                    }
                }
            }
            if (!clazz.isInterface()) {
                while (clazz != Object.class) {
                    final Class<? super Object> superclass = clazz.getSuperclass();
                    if (superclass == clazz2) {
                        type = clazz.getGenericSuperclass();
                        return type;
                    }
                    if (clazz2.isAssignableFrom(superclass)) {
                        type = zza(clazz.getGenericSuperclass(), superclass, clazz2);
                        return type;
                    }
                    clazz = (Class<Object>)superclass;
                }
            }
            type = clazz2;
        }
        return type;
    }
    
    public static Type zza(final Type type, final Class<?> clazz, final Type type2) {
        Type type3;
        Type zza;
        for (type3 = type2; type3 instanceof TypeVariable; type3 = zza) {
            final TypeVariable<?> typeVariable = (TypeVariable<?>)type3;
            zza = zza(type, clazz, typeVariable);
            if (zza == typeVariable) {
                type3 = zza;
                return type3;
            }
        }
        if (type3 instanceof Class && ((Class<?>)type3).isArray()) {
            type3 = type3;
            final Class<?> componentType = ((Class)type3).getComponentType();
            final Type zza2 = zza(type, clazz, (Type)componentType);
            if (componentType != zza2) {
                type3 = zzb(zza2);
                return type3;
            }
            return type3;
        }
        else if (type3 instanceof GenericArrayType) {
            type3 = type3;
            final Type genericComponentType = ((GenericArrayType)type3).getGenericComponentType();
            final Type zza3 = zza(type, clazz, genericComponentType);
            if (genericComponentType != zza3) {
                type3 = zzb(zza3);
                return type3;
            }
            return type3;
        }
        else if (type3 instanceof ParameterizedType) {
            type3 = type3;
            final Type ownerType = ((ParameterizedType)type3).getOwnerType();
            final Type zza4 = zza(type, clazz, ownerType);
            int n;
            if (zza4 != ownerType) {
                n = 1;
            }
            else {
                n = 0;
            }
            final Type[] actualTypeArguments = ((ParameterizedType)type3).getActualTypeArguments();
            final int length = actualTypeArguments.length;
            int n2 = n;
            Type[] array = actualTypeArguments;
            for (int i = 0; i < length; ++i) {
                final Type zza5 = zza(type, clazz, array[i]);
                if (zza5 != array[i]) {
                    if (n2 == 0) {
                        array = array.clone();
                        n2 = 1;
                    }
                    array[i] = zza5;
                }
            }
            if (n2 != 0) {
                type3 = zza(zza4, ((ParameterizedType)type3).getRawType(), array);
                return type3;
            }
            return type3;
        }
        else {
            if (!(type3 instanceof WildcardType)) {
                return type3;
            }
            type3 = type3;
            final Type[] lowerBounds = ((WildcardType)type3).getLowerBounds();
            final Type[] upperBounds = ((WildcardType)type3).getUpperBounds();
            if (lowerBounds.length == 1) {
                final Type zza6 = zza(type, clazz, lowerBounds[0]);
                if (zza6 != lowerBounds[0]) {
                    type3 = zzd(zza6);
                    return type3;
                }
                return type3;
            }
            else {
                if (upperBounds.length != 1) {
                    return type3;
                }
                final Type zza7 = zza(type, clazz, upperBounds[0]);
                if (zza7 != upperBounds[0]) {
                    type3 = zzc(zza7);
                    return type3;
                }
                return type3;
            }
        }
    }
    
    static Type zza(final Type type, final Class<?> clazz, TypeVariable<?> type2) {
        final Class<?> zza = zza((TypeVariable<?>)type2);
        if (zza != null) {
            final Type zza2 = zza(type, clazz, zza);
            if (zza2 instanceof ParameterizedType) {
                type2 = ((ParameterizedType)zza2).getActualTypeArguments()[zza(zza.getTypeParameters(), type2)];
            }
        }
        return type2;
    }
    
    public static boolean zza(final Type type, final Type type2) {
        boolean b = true;
        boolean b2;
        if (type == type2) {
            b2 = b;
        }
        else if (type instanceof Class) {
            b2 = type.equals(type2);
        }
        else if (type instanceof ParameterizedType) {
            final boolean b3 = type2 instanceof ParameterizedType;
            b2 = false;
            if (b3) {
                final ParameterizedType parameterizedType = (ParameterizedType)type;
                final ParameterizedType parameterizedType2 = (ParameterizedType)type2;
                if (!equal(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) || !parameterizedType.getRawType().equals(parameterizedType2.getRawType()) || !Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments())) {
                    b = false;
                }
                b2 = b;
            }
        }
        else if (type instanceof GenericArrayType) {
            final boolean b4 = type2 instanceof GenericArrayType;
            b2 = false;
            if (b4) {
                b2 = zza(((GenericArrayType)type).getGenericComponentType(), ((GenericArrayType)type2).getGenericComponentType());
            }
        }
        else if (type instanceof WildcardType) {
            final boolean b5 = type2 instanceof WildcardType;
            b2 = false;
            if (b5) {
                final WildcardType wildcardType = (WildcardType)type;
                final WildcardType wildcardType2 = (WildcardType)type2;
                if (!Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) || !Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds())) {
                    b = false;
                }
                b2 = b;
            }
        }
        else {
            final boolean b6 = type instanceof TypeVariable;
            b2 = false;
            if (b6) {
                final boolean b7 = type2 instanceof TypeVariable;
                b2 = false;
                if (b7) {
                    final TypeVariable typeVariable = (TypeVariable)type;
                    final TypeVariable typeVariable2 = (TypeVariable)type2;
                    if (typeVariable.getGenericDeclaration() != typeVariable2.getGenericDeclaration() || !typeVariable.getName().equals(typeVariable2.getName())) {
                        b = false;
                    }
                    b2 = b;
                }
            }
        }
        return b2;
    }
    
    public static GenericArrayType zzb(final Type type) {
        return new zza(type);
    }
    
    static Type zzb(final Type type, final Class<?> clazz, final Class<?> clazz2) {
        zzaoz.zzbs(clazz2.isAssignableFrom(clazz));
        return zza(type, clazz, zza(type, clazz, clazz2));
    }
    
    public static Type[] zzb(final Type type, final Class<?> clazz) {
        Type[] actualTypeArguments;
        if (type == Properties.class) {
            actualTypeArguments = new Type[] { String.class, String.class };
        }
        else {
            final Type zzb = zzb(type, clazz, Map.class);
            if (zzb instanceof ParameterizedType) {
                actualTypeArguments = ((ParameterizedType)zzb).getActualTypeArguments();
            }
            else {
                actualTypeArguments = new Type[] { Object.class, Object.class };
            }
        }
        return actualTypeArguments;
    }
    
    public static WildcardType zzc(final Type type) {
        return new zzc(new Type[] { type }, zzapa.blr);
    }
    
    private static int zzcp(final Object o) {
        int hashCode;
        if (o != null) {
            hashCode = o.hashCode();
        }
        else {
            hashCode = 0;
        }
        return hashCode;
    }
    
    public static WildcardType zzd(final Type type) {
        return new zzc(new Type[] { Object.class }, new Type[] { type });
    }
    
    public static Type zze(final Type type) {
        Type type2;
        if (type instanceof Class) {
            final Class clazz = (Class)type;
            Serializable s;
            if (clazz.isArray()) {
                s = new zza(zze(clazz.getComponentType()));
            }
            else {
                s = clazz;
            }
            type2 = (Type)s;
        }
        else if (type instanceof ParameterizedType) {
            final ParameterizedType parameterizedType = (ParameterizedType)type;
            type2 = new zzb(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        }
        else if (type instanceof GenericArrayType) {
            type2 = new zza(((GenericArrayType)type).getGenericComponentType());
        }
        else if (type instanceof WildcardType) {
            final WildcardType wildcardType = (WildcardType)type;
            type2 = new zzc(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
        }
        else {
            type2 = type;
        }
        return type2;
    }
    
    public static Class<?> zzf(final Type type) {
        Class<?> clazz;
        if (type instanceof Class) {
            clazz = (Class<?>)type;
        }
        else if (type instanceof ParameterizedType) {
            final Type rawType = ((ParameterizedType)type).getRawType();
            zzaoz.zzbs(rawType instanceof Class);
            clazz = (Class<?>)rawType;
        }
        else if (type instanceof GenericArrayType) {
            clazz = Array.newInstance(zzf(((GenericArrayType)type).getGenericComponentType()), 0).getClass();
        }
        else if (type instanceof TypeVariable) {
            clazz = Object.class;
        }
        else {
            if (!(type instanceof WildcardType)) {
                String name;
                if (type == null) {
                    name = "null";
                }
                else {
                    name = type.getClass().getName();
                }
                final String value = String.valueOf("Expected a Class, ParameterizedType, or GenericArrayType, but <");
                final String value2 = String.valueOf(type);
                throw new IllegalArgumentException(new StringBuilder(13 + String.valueOf(value).length() + String.valueOf(value2).length() + String.valueOf(name).length()).append(value).append(value2).append("> is of type ").append(name).toString());
            }
            clazz = zzf(((WildcardType)type).getUpperBounds()[0]);
        }
        return clazz;
    }
    
    public static String zzg(final Type type) {
        String s;
        if (type instanceof Class) {
            s = ((Class)type).getName();
        }
        else {
            s = type.toString();
        }
        return s;
    }
    
    public static Type zzh(final Type type) {
        Type type2;
        if (type instanceof GenericArrayType) {
            type2 = ((GenericArrayType)type).getGenericComponentType();
        }
        else {
            type2 = ((Class)type).getComponentType();
        }
        return type2;
    }
    
    private static void zzi(final Type type) {
        zzaoz.zzbs(!(type instanceof Class) || !((Class)type).isPrimitive());
    }
    
    private static final class zza implements Serializable, GenericArrayType
    {
        private final Type bls;
        
        public zza(final Type type) {
            this.bls = zzapa.zze(type);
        }
        
        @Override
        public boolean equals(final Object o) {
            return o instanceof GenericArrayType && zzapa.zza(this, (Type)o);
        }
        
        @Override
        public Type getGenericComponentType() {
            return this.bls;
        }
        
        @Override
        public int hashCode() {
            return this.bls.hashCode();
        }
        
        @Override
        public String toString() {
            return String.valueOf(zzapa.zzg(this.bls)).concat("[]");
        }
    }
    
    private static final class zzb implements Serializable, ParameterizedType
    {
        private final Type blt;
        private final Type blu;
        private final Type[] blv;
        
        public zzb(final Type type, final Type type2, final Type... array) {
            int i = 0;
            if (type2 instanceof Class) {
                final Class clazz = (Class)type2;
                boolean b;
                if (Modifier.isStatic(clazz.getModifiers()) || clazz.getEnclosingClass() == null) {
                    b = true;
                }
                else {
                    b = false;
                }
                zzaoz.zzbs(type != null || b);
            }
            Type zze;
            if (type == null) {
                zze = null;
            }
            else {
                zze = zzapa.zze(type);
            }
            this.blt = zze;
            this.blu = zzapa.zze(type2);
            this.blv = array.clone();
            while (i < this.blv.length) {
                zzaoz.zzy(this.blv[i]);
                zzi(this.blv[i]);
                this.blv[i] = zzapa.zze(this.blv[i]);
                ++i;
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            return o instanceof ParameterizedType && zzapa.zza(this, (Type)o);
        }
        
        @Override
        public Type[] getActualTypeArguments() {
            return this.blv.clone();
        }
        
        @Override
        public Type getOwnerType() {
            return this.blt;
        }
        
        @Override
        public Type getRawType() {
            return this.blu;
        }
        
        @Override
        public int hashCode() {
            return Arrays.hashCode(this.blv) ^ this.blu.hashCode() ^ zzcp(this.blt);
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder(30 * (1 + this.blv.length));
            sb.append(zzapa.zzg(this.blu));
            String s;
            if (this.blv.length == 0) {
                s = sb.toString();
            }
            else {
                sb.append("<").append(zzapa.zzg(this.blv[0]));
                for (int i = 1; i < this.blv.length; ++i) {
                    sb.append(", ").append(zzapa.zzg(this.blv[i]));
                }
                s = sb.append(">").toString();
            }
            return s;
        }
    }
    
    private static final class zzc implements Serializable, WildcardType
    {
        private final Type blw;
        private final Type blx;
        
        public zzc(final Type[] array, final Type[] array2) {
            int n = 1;
            boolean b;
            if (array2.length <= n) {
                b = (n != 0);
            }
            else {
                b = false;
            }
            zzaoz.zzbs(b);
            boolean b2;
            if (array.length == n) {
                b2 = (n != 0);
            }
            else {
                b2 = false;
            }
            zzaoz.zzbs(b2);
            if (array2.length == n) {
                zzaoz.zzy(array2[0]);
                zzi(array2[0]);
                if (array[0] != Object.class) {
                    n = 0;
                }
                zzaoz.zzbs(n != 0);
                this.blx = zzapa.zze(array2[0]);
                this.blw = Object.class;
            }
            else {
                zzaoz.zzy(array[0]);
                zzi(array[0]);
                this.blx = null;
                this.blw = zzapa.zze(array[0]);
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            return o instanceof WildcardType && zzapa.zza(this, (Type)o);
        }
        
        @Override
        public Type[] getLowerBounds() {
            Type[] blr;
            if (this.blx != null) {
                blr = new Type[] { this.blx };
            }
            else {
                blr = zzapa.blr;
            }
            return blr;
        }
        
        @Override
        public Type[] getUpperBounds() {
            return new Type[] { this.blw };
        }
        
        @Override
        public int hashCode() {
            int n;
            if (this.blx != null) {
                n = 31 + this.blx.hashCode();
            }
            else {
                n = 1;
            }
            return n ^ 31 + this.blw.hashCode();
        }
        
        @Override
        public String toString() {
            String s;
            if (this.blx != null) {
                final String value = String.valueOf(zzapa.zzg(this.blx));
                if (value.length() != 0) {
                    s = "? super ".concat(value);
                }
                else {
                    s = new String("? super ");
                }
            }
            else if (this.blw == Object.class) {
                s = "?";
            }
            else {
                final String value2 = String.valueOf(zzapa.zzg(this.blw));
                if (value2.length() != 0) {
                    s = "? extends ".concat(value2);
                }
                else {
                    s = new String("? extends ");
                }
            }
            return s;
        }
    }
}
