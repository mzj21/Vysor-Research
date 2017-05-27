// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.Field;

public final class zzaps implements zzaou
{
    private final zzapb bkM;
    private final zzapc bkV;
    private final zzaoa bkX;
    
    public zzaps(final zzapb bkM, final zzaoa bkX, final zzapc bkV) {
        this.bkM = bkM;
        this.bkX = bkX;
        this.bkV = bkV;
    }
    
    private zzaot<?> zza(final zzaob zzaob, final Field field, final zzapx<?> zzapx) {
        final zzaov zzaov = field.getAnnotation(zzaov.class);
        if (zzaov == null) {
            return zzaob.zza(zzapx);
        }
        zzaot<?> zzaot = zzapn.zza(this.bkM, zzaob, zzapx, zzaov);
        if (zzaot == null) {
            return zzaob.zza(zzapx);
        }
        return zzaot;
        zzaot = zzaob.zza(zzapx);
        return zzaot;
    }
    
    private zzb zza(final zzaob zzaob, final Field field, final String s, final zzapx<?> zzapx, final boolean b, final boolean b2) {
        return (zzb)new zzb(s, b, b2) {
            final zzaot<?> bmG = zzaps.this.zza(zzaob, field, zzapx);
            final /* synthetic */ boolean bmK = zzaph.zzk(zzapx.by());
            
            @Override
            void zza(final zzapy zzapy, final Object o) throws IOException, IllegalAccessException {
                final Object zzb = this.bmG.zzb(zzapy);
                if (zzb != null || !this.bmK) {
                    field.set(o, zzb);
                }
            }
            
            @Override
            void zza(final zzaqa zzaqa, final Object o) throws IOException, IllegalAccessException {
                new zzapv<Object>(zzaob, this.bmG, zzapx.bz()).zza(zzaqa, field.get(o));
            }
            
            public boolean zzct(final Object o) throws IOException, IllegalAccessException {
                final boolean bmN = this.bmN;
                boolean b = false;
                if (bmN) {
                    final Object value = field.get(o);
                    b = false;
                    if (value != o) {
                        b = true;
                    }
                }
                return b;
            }
        };
    }
    
    static List<String> zza(final zzaoa zzaoa, final Field field) {
        final zzaow zzaow = field.getAnnotation(zzaow.class);
        final LinkedList<String> list = new LinkedList<String>();
        if (zzaow == null) {
            list.add(zzaoa.zzc(field));
        }
        else {
            list.add(zzaow.value());
            final String[] be = zzaow.be();
            for (int length = be.length, i = 0; i < length; ++i) {
                list.add(be[i]);
            }
        }
        return list;
    }
    
    private Map<String, zzb> zza(final zzaob zzaob, zzapx<?> zzl, Class<?> by) {
        final LinkedHashMap<String, zzb> linkedHashMap = new LinkedHashMap<String, zzb>();
        LinkedHashMap<String, zzb> linkedHashMap2;
        if (by.isInterface()) {
            linkedHashMap2 = linkedHashMap;
        }
        else {
            final Type bz = zzl.bz();
            while (by != Object.class) {
                for (final Field field : by.getDeclaredFields()) {
                    boolean zza = this.zza(field, true);
                    final boolean zza2 = this.zza(field, false);
                    if (zza || zza2) {
                        field.setAccessible(true);
                        final Type zza3 = zzapa.zza(zzl.bz(), by, field.getGenericType());
                        final List<String> zzd = this.zzd(field);
                        zzb zzb = null;
                        zzb zzb2;
                        for (int j = 0; j < zzd.size(); ++j, zzb = zzb2) {
                            final String s = zzd.get(j);
                            if (j != 0) {
                                zza = false;
                            }
                            zzb2 = linkedHashMap.put(s, this.zza(zzaob, field, s, zzapx.zzl(zza3), zza, zza2));
                            if (zzb != null) {
                                zzb2 = zzb;
                            }
                        }
                        if (zzb != null) {
                            final String value = String.valueOf(bz);
                            final String name = zzb.name;
                            throw new IllegalArgumentException(new StringBuilder(37 + String.valueOf(value).length() + String.valueOf(name).length()).append(value).append(" declares multiple JSON fields named ").append(name).toString());
                        }
                    }
                }
                zzl = zzapx.zzl(zzapa.zza(zzl.bz(), by, by.getGenericSuperclass()));
                by = zzl.by();
            }
            linkedHashMap2 = linkedHashMap;
        }
        return linkedHashMap2;
    }
    
    static boolean zza(final Field field, final boolean b, final zzapc zzapc) {
        return !zzapc.zza(field.getType(), b) && !zzapc.zza(field, b);
    }
    
    private List<String> zzd(final Field field) {
        return zza(this.bkX, field);
    }
    
    @Override
    public <T> zzaot<T> zza(final zzaob zzaob, final zzapx<T> zzapx) {
        final Class<? super T> by = zzapx.by();
        final boolean assignable = Object.class.isAssignableFrom(by);
        zza zza = null;
        if (assignable) {
            zza = new zza<T>((zzapg)this.bkM.zzb(zzapx), (Map)this.zza(zzaob, zzapx, by));
        }
        return (zzaot<T>)zza;
    }
    
    public boolean zza(final Field field, final boolean b) {
        return zza(field, b, this.bkV);
    }
    
    public static final class zza<T> extends zzaot<T>
    {
        private final Map<String, zzb> bmM;
        private final zzapg<T> bmt;
        
        private zza(final zzapg<T> bmt, final Map<String, zzb> bmM) {
            this.bmt = bmt;
            this.bmM = bmM;
        }
        
        @Override
        public void zza(final zzaqa zzaqa, final T t) throws IOException {
            if (t == null) {
                zzaqa.bx();
            }
            else {
                zzaqa.bv();
                try {
                    for (final zzb zzb : this.bmM.values()) {
                        if (zzb.zzct(t)) {
                            zzaqa.zzus(zzb.name);
                            zzb.zza(zzaqa, t);
                        }
                    }
                }
                catch (IllegalAccessException ex) {
                    throw new AssertionError();
                }
                zzaqa.bw();
            }
        }
        
        @Override
        public T zzb(final zzapy zzapy) throws IOException {
            T t;
            if (zzapy.bn() == zzapz.bos) {
                zzapy.nextNull();
                t = null;
            }
            else {
                final T bg = this.bmt.bg();
                try {
                    zzapy.beginObject();
                    while (zzapy.hasNext()) {
                        final zzb zzb = this.bmM.get(zzapy.nextName());
                        if (zzb != null && zzb.bmO) {
                            goto Label_0095;
                        }
                        zzapy.skipValue();
                    }
                }
                catch (IllegalStateException ex) {
                    throw new zzaoq(ex);
                }
                catch (IllegalAccessException ex2) {
                    throw new AssertionError((Object)ex2);
                }
                zzapy.endObject();
                t = bg;
            }
            return t;
        }
    }
    
    abstract static class zzb
    {
        final boolean bmN;
        final boolean bmO;
        final String name;
        
        protected zzb(final String name, final boolean bmN, final boolean bmO) {
            this.name = name;
            this.bmN = bmN;
            this.bmO = bmO;
        }
        
        abstract void zza(final zzapy p0, final Object p1) throws IOException, IllegalAccessException;
        
        abstract void zza(final zzaqa p0, final Object p1) throws IOException, IllegalAccessException;
        
        abstract boolean zzct(final Object p0) throws IOException, IllegalAccessException;
    }
}
