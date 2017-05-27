// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public final class zzapc implements zzaou, Cloneable
{
    public static final zzapc blF;
    private double blG;
    private int blH;
    private boolean blI;
    private List<zzanx> blJ;
    private List<zzanx> blK;
    
    static {
        blF = new zzapc();
    }
    
    public zzapc() {
        this.blG = -1.0;
        this.blH = 136;
        this.blI = true;
        this.blJ = Collections.emptyList();
        this.blK = Collections.emptyList();
    }
    
    private boolean zza(final zzaox zzaox) {
        return zzaox == null || zzaox.bf() <= this.blG;
    }
    
    private boolean zza(final zzaox zzaox, final zzaoy zzaoy) {
        return this.zza(zzaox) && this.zza(zzaoy);
    }
    
    private boolean zza(final zzaoy zzaoy) {
        return zzaoy == null || zzaoy.bf() > this.blG;
    }
    
    private boolean zzm(final Class<?> clazz) {
        return !Enum.class.isAssignableFrom(clazz) && (clazz.isAnonymousClass() || clazz.isLocalClass());
    }
    
    private boolean zzn(final Class<?> clazz) {
        return clazz.isMemberClass() && !this.zzo(clazz);
    }
    
    private boolean zzo(final Class<?> clazz) {
        return (0x8 & clazz.getModifiers()) != 0x0;
    }
    
    protected zzapc bh() {
        try {
            return (zzapc)super.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new AssertionError();
        }
    }
    
    @Override
    public <T> zzaot<T> zza(final zzaob zzaob, final zzapx<T> zzapx) {
        final Class<? super T> by = zzapx.by();
        final boolean zza = this.zza(by, true);
        final boolean zza2 = this.zza(by, false);
        zzaot<T> zzaot;
        if (!zza && !zza2) {
            zzaot = null;
        }
        else {
            zzaot = new zzaot<T>() {
                private zzaot<T> bkU;
                
                private zzaot<T> bd() {
                    zzaot<T> bkU = this.bkU;
                    if (bkU == null) {
                        bkU = zzaob.zza(zzapc.this, zzapx);
                        this.bkU = bkU;
                    }
                    return bkU;
                }
                
                @Override
                public void zza(final zzaqa zzaqa, final T t) throws IOException {
                    if (zza) {
                        zzaqa.bx();
                    }
                    else {
                        this.bd().zza(zzaqa, t);
                    }
                }
                
                @Override
                public T zzb(final zzapy zzapy) throws IOException {
                    T zzb;
                    if (zza2) {
                        zzapy.skipValue();
                        zzb = null;
                    }
                    else {
                        zzb = this.bd().zzb(zzapy);
                    }
                    return zzb;
                }
            };
        }
        return zzaot;
    }
    
    public zzapc zza(final zzanx zzanx, final boolean b, final boolean b2) {
        final zzapc bh = this.bh();
        if (b) {
            (bh.blJ = new ArrayList<zzanx>(this.blJ)).add(zzanx);
        }
        if (b2) {
            (bh.blK = new ArrayList<zzanx>(this.blK)).add(zzanx);
        }
        return bh;
    }
    
    public boolean zza(final Class<?> clazz, final boolean b) {
        boolean b2;
        if (this.blG != -1.0 && !this.zza(clazz.getAnnotation(zzaox.class), clazz.getAnnotation(zzaoy.class))) {
            b2 = true;
        }
        else if (!this.blI && this.zzn(clazz)) {
            b2 = true;
        }
        else if (this.zzm(clazz)) {
            b2 = true;
        }
        else {
            List<zzanx> list;
            if (b) {
                list = this.blJ;
            }
            else {
                list = this.blK;
            }
            final Iterator<zzanx> iterator = list.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().zzh(clazz)) {
                    b2 = true;
                    return b2;
                }
            }
            b2 = false;
        }
        return b2;
    }
    
    public boolean zza(final Field field, final boolean b) {
        boolean b2;
        if ((this.blH & field.getModifiers()) != 0x0) {
            b2 = true;
        }
        else if (this.blG != -1.0 && !this.zza(field.getAnnotation(zzaox.class), field.getAnnotation(zzaoy.class))) {
            b2 = true;
        }
        else if (field.isSynthetic()) {
            b2 = true;
        }
        else if (!this.blI && this.zzn(field.getType())) {
            b2 = true;
        }
        else if (this.zzm(field.getType())) {
            b2 = true;
        }
        else {
            List<zzanx> list;
            if (b) {
                list = this.blJ;
            }
            else {
                list = this.blK;
            }
            if (!list.isEmpty()) {
                final zzany zzany = new zzany(field);
                final Iterator<zzanx> iterator = list.iterator();
                while (iterator.hasNext()) {
                    if (iterator.next().zza(zzany)) {
                        b2 = true;
                        return b2;
                    }
                }
            }
            b2 = false;
        }
        return b2;
    }
    
    public zzapc zzg(final int... array) {
        int i = 0;
        final zzapc bh = this.bh();
        bh.blH = 0;
        while (i < array.length) {
            bh.blH |= array[i];
            ++i;
        }
        return bh;
    }
}
