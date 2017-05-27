// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class zzarf<M extends zzare<M>, T>
{
    protected final Class<T> bhd;
    protected final boolean bqw;
    public final int tag;
    protected final int type;
    
    private zzarf(final int type, final Class<T> bhd, final int tag, final boolean bqw) {
        this.type = type;
        this.bhd = bhd;
        this.tag = tag;
        this.bqw = bqw;
    }
    
    public static <M extends zzare<M>, T extends zzark> zzarf<M, T> zza(final int n, final Class<T> clazz, final long n2) {
        return new zzarf<M, T>(n, clazz, (int)n2, false);
    }
    
    private T zzaz(final List<zzarm> list) {
        int i = 0;
        final ArrayList<Object> list2 = new ArrayList<Object>();
        for (int j = 0; j < list.size(); ++j) {
            final zzarm zzarm = list.get(j);
            if (zzarm.avk.length != 0) {
                this.zza(zzarm, list2);
            }
        }
        final int size = list2.size();
        Object cast;
        if (size == 0) {
            cast = null;
        }
        else {
            cast = this.bhd.cast(Array.newInstance(this.bhd.getComponentType(), size));
            while (i < size) {
                Array.set(cast, i, list2.get(i));
                ++i;
            }
        }
        return (T)cast;
    }
    
    private T zzba(final List<zzarm> list) {
        T cast;
        if (list.isEmpty()) {
            cast = null;
        }
        else {
            cast = this.bhd.cast(this.zzck(zzarc.zzbd(list.get(-1 + list.size()).avk)));
        }
        return cast;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        if (this != o) {
            if (!(o instanceof zzarf)) {
                b = false;
            }
            else {
                final zzarf zzarf = (zzarf)o;
                if (this.type != zzarf.type || this.bhd != zzarf.bhd || this.tag != zzarf.tag || this.bqw != zzarf.bqw) {
                    b = false;
                }
            }
        }
        return b;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (31 * (31 * (1147 + this.type) + this.bhd.hashCode()) + this.tag);
        int n2;
        if (this.bqw) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        return n2 + n;
    }
    
    protected void zza(final zzarm zzarm, final List<Object> list) {
        list.add(this.zzck(zzarc.zzbd(zzarm.avk)));
    }
    
    void zza(final Object o, final zzard zzard) throws IOException {
        if (this.bqw) {
            this.zzc(o, zzard);
        }
        else {
            this.zzb(o, zzard);
        }
    }
    
    final T zzay(final List<zzarm> list) {
        T t;
        if (list == null) {
            t = null;
        }
        else if (this.bqw) {
            t = this.zzaz(list);
        }
        else {
            t = this.zzba(list);
        }
        return t;
    }
    
    protected void zzb(final Object o, final zzard zzard) {
        Label_0113: {
            try {
                zzard.zzahm(this.tag);
                switch (this.type) {
                    default: {
                        throw new IllegalArgumentException(new StringBuilder(24).append("Unknown type ").append(this.type).toString());
                    }
                    case 10: {
                        break;
                    }
                    case 11: {
                        break Label_0113;
                    }
                }
            }
            catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
            final zzark zzark = (zzark)o;
            final int zzahu = zzarn.zzahu(this.tag);
            zzard.zzb(zzark);
            zzard.zzai(zzahu, 4);
            return;
        }
        zzard.zzc((zzark)o);
    }
    
    protected void zzc(final Object o, final zzard zzard) {
        for (int length = Array.getLength(o), i = 0; i < length; ++i) {
            final Object value = Array.get(o, i);
            if (value != null) {
                this.zzb(value, zzard);
            }
        }
    }
    
    protected Object zzck(final zzarc zzarc) {
        if (this.bqw) {
            final Class<?> componentType = this.bhd.getComponentType();
            try {
                switch (this.type) {
                    default: {
                        throw new IllegalArgumentException(new StringBuilder(24).append("Unknown type ").append(this.type).toString());
                    }
                    case 10: {
                        goto Label_0133;
                        goto Label_0133;
                    }
                    case 11: {
                        goto Label_0158;
                        goto Label_0158;
                    }
                }
            }
            catch (InstantiationException ex) {
                final String value = String.valueOf(componentType);
                throw new IllegalArgumentException(new StringBuilder(33 + String.valueOf(value).length()).append("Error creating instance of class ").append(value).toString(), ex);
            }
            catch (IllegalAccessException ex2) {
                final String value2 = String.valueOf(componentType);
                throw new IllegalArgumentException(new StringBuilder(33 + String.valueOf(value2).length()).append("Error creating instance of class ").append(value2).toString(), ex2);
            }
            catch (IOException ex3) {
                throw new IllegalArgumentException("Error reading extension field", ex3);
            }
            return;
        }
        goto Label_0125;
    }
    
    int zzcu(final Object o) {
        int n;
        if (this.bqw) {
            n = this.zzcv(o);
        }
        else {
            n = this.zzcw(o);
        }
        return n;
    }
    
    protected int zzcv(final Object o) {
        int n = 0;
        for (int length = Array.getLength(o), i = 0; i < length; ++i) {
            if (Array.get(o, i) != null) {
                n += this.zzcw(Array.get(o, i));
            }
        }
        return n;
    }
    
    protected int zzcw(final Object o) {
        final int zzahu = zzarn.zzahu(this.tag);
        int n = 0;
        switch (this.type) {
            default: {
                throw new IllegalArgumentException(new StringBuilder(24).append("Unknown type ").append(this.type).toString());
            }
            case 10: {
                n = zzard.zzb(zzahu, (zzark)o);
                break;
            }
            case 11: {
                n = zzard.zzc(zzahu, (zzark)o);
                break;
            }
        }
        return n;
    }
}
