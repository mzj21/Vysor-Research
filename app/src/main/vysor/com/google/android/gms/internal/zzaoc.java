// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Collection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.List;

public final class zzaoc
{
    private final List<zzaou> bkL;
    private zzapc bkV;
    private zzaor bkW;
    private zzaoa bkX;
    private final Map<Type, zzaod<?>> bkY;
    private final List<zzaou> bkZ;
    private int bla;
    private int blb;
    private boolean blc;
    
    public zzaoc() {
        this.bkV = zzapc.blF;
        this.bkW = zzaor.blg;
        this.bkX = zzanz.bkD;
        this.bkY = new HashMap<Type, zzaod<?>>();
        this.bkL = new ArrayList<zzaou>();
        this.bkZ = new ArrayList<zzaou>();
        this.bla = 2;
        this.blb = 2;
        this.blc = true;
    }
    
    private void zza(final String s, final int n, final int n2, final List<zzaou> list) {
        zzanw zzanw;
        if (s != null && !"".equals(s.trim())) {
            zzanw = new zzanw(s);
        }
        else {
            if (n == 2 || n2 == 2) {
                return;
            }
            zzanw = new zzanw(n, n2);
        }
        list.add(zzaos.zza(zzapx.zzr((Class<?>)Date.class), zzanw));
        list.add(zzaos.zza(zzapx.zzr((Class<?>)Timestamp.class), zzanw));
        list.add(zzaos.zza(zzapx.zzr((Class<?>)java.sql.Date.class), zzanw));
    }
    
    public zzaoc aO() {
        this.blc = false;
        return this;
    }
    
    public zzaob aP() {
        final ArrayList<Object> list = new ArrayList<Object>();
        list.addAll(this.bkL);
        Collections.reverse(list);
        list.addAll(this.bkZ);
        this.zza(null, this.bla, this.blb, (List<zzaou>)list);
        return new zzaob(this.bkV, this.bkX, this.bkY, false, false, false, this.blc, false, false, this.bkW, (List<zzaou>)list);
    }
    
    public zzaoc zza(final Type type, final Object o) {
        zzaoz.zzbs(o instanceof zzaop || o instanceof zzaog || o instanceof zzaod || o instanceof zzaot);
        if (o instanceof zzaod) {
            this.bkY.put(type, (zzaod<?>)o);
        }
        if (o instanceof zzaop || o instanceof zzaog) {
            this.bkL.add(zzaos.zzb(zzapx.zzl(type), o));
        }
        if (o instanceof zzaot) {
            this.bkL.add(zzapw.zza(zzapx.zzl(type), (zzaot<?>)o));
        }
        return this;
    }
    
    public zzaoc zza(final zzanx... array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            this.bkV = this.bkV.zza(array[i], true, true);
        }
        return this;
    }
    
    public zzaoc zzf(final int... array) {
        this.bkV = this.bkV.zzg(array);
        return this;
    }
}
