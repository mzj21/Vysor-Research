// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Map;
import java.lang.reflect.Type;

public final class zzapq implements zzaou
{
    private final zzapb bkM;
    private final boolean bmB;
    
    public zzapq(final zzapb bkM, final boolean bmB) {
        this.bkM = bkM;
        this.bmB = bmB;
    }
    
    private zzaot<?> zza(final zzaob zzaob, final Type type) {
        zzaot<?> zzaot;
        if (type == Boolean.TYPE || type == Boolean.class) {
            zzaot = zzapw.bmX;
        }
        else {
            zzaot = zzaob.zza(zzapx.zzl(type));
        }
        return zzaot;
    }
    
    @Override
    public <T> zzaot<T> zza(final zzaob zzaob, final zzapx<T> zzapx) {
        final Type bz = zzapx.bz();
        zza zza;
        if (!Map.class.isAssignableFrom(zzapx.by())) {
            zza = null;
        }
        else {
            final Type[] zzb = zzapa.zzb(bz, zzapa.zzf(bz));
            zza = new zza(zzaob, zzb[0], (zzaot<Object>)this.zza(zzaob, zzb[0]), zzb[1], (zzaot<Object>)zzaob.zza(zzapx.zzl(zzb[1])), (zzapg<? extends Map<Object, Object>>)this.bkM.zzb((zzapx<? extends Map<K, V>>)zzapx));
        }
        return (zzaot<T>)zza;
    }
    
    private final class zza<K, V> extends zzaot<Map<K, V>>
    {
        private final zzaot<K> bmC;
        private final zzaot<V> bmD;
        private final zzapg<? extends Map<K, V>> bmt;
        
        public zza(final zzaob zzaob, final Type type, final zzaot<K> zzaot, final Type type2, final zzaot<V> zzaot2, final zzapg<? extends Map<K, V>> bmt) {
            this.bmC = new zzapv<K>(zzaob, zzaot, type);
            this.bmD = new zzapv<V>(zzaob, zzaot2, type2);
            this.bmt = bmt;
        }
        
        private String zze(final zzaoh zzaoh) {
            String s;
            if (zzaoh.aU()) {
                final zzaon ay = zzaoh.aY();
                if (ay.bb()) {
                    s = String.valueOf(ay.aQ());
                }
                else if (ay.ba()) {
                    s = Boolean.toString(ay.getAsBoolean());
                }
                else {
                    if (!ay.bc()) {
                        throw new AssertionError();
                    }
                    s = ay.aR();
                }
            }
            else {
                if (!zzaoh.aV()) {
                    throw new AssertionError();
                }
                s = "null";
            }
            return s;
        }
        
        @Override
        public void zza(final zzaqa zzaqa, final Map<K, V> map) throws IOException {
            int i = 0;
            if (map == null) {
                zzaqa.bx();
            }
            else if (!zzapq.this.bmB) {
                zzaqa.bv();
                for (final Map.Entry<K, V> entry : map.entrySet()) {
                    zzaqa.zzus(String.valueOf(entry.getKey()));
                    this.bmD.zza(zzaqa, entry.getValue());
                }
                zzaqa.bw();
            }
            else {
                final ArrayList<zzaoh> list = new ArrayList<zzaoh>(map.size());
                final ArrayList<Object> list2 = new ArrayList<Object>(map.size());
                final Iterator<Map.Entry<K, V>> iterator2 = map.entrySet().iterator();
                int n = false ? 1 : 0;
                while (iterator2.hasNext()) {
                    final Map.Entry<K, V> entry2 = iterator2.next();
                    final zzaoh zzco = this.bmC.zzco(entry2.getKey());
                    list.add(zzco);
                    list2.add(entry2.getValue());
                    boolean b;
                    if (zzco.aS() || zzco.aT()) {
                        b = true;
                    }
                    else {
                        b = false;
                    }
                    n |= (b ? 1 : 0);
                }
                if (n != 0) {
                    zzaqa.bt();
                    while (i < list.size()) {
                        zzaqa.bt();
                        zzapi.zzb((zzaoh)list.get(i), zzaqa);
                        this.bmD.zza(zzaqa, (V)list2.get(i));
                        zzaqa.bu();
                        ++i;
                    }
                    zzaqa.bu();
                }
                else {
                    zzaqa.bv();
                    while (i < list.size()) {
                        zzaqa.zzus(this.zze((zzaoh)list.get(i)));
                        this.bmD.zza(zzaqa, (V)list2.get(i));
                        ++i;
                    }
                    zzaqa.bw();
                }
            }
        }
        
        public Map<K, V> zzl(final zzapy zzapy) throws IOException {
            final zzapz bn = zzapy.bn();
            Map<K, V> map;
            if (bn == zzapz.bos) {
                zzapy.nextNull();
                map = null;
            }
            else {
                map = (Map<K, V>)this.bmt.bg();
                if (bn == zzapz.bok) {
                    zzapy.beginArray();
                    while (zzapy.hasNext()) {
                        zzapy.beginArray();
                        final K zzb = this.bmC.zzb(zzapy);
                        if (map.put(zzb, this.bmD.zzb(zzapy)) != null) {
                            final String value = String.valueOf(zzb);
                            throw new zzaoq(new StringBuilder(15 + String.valueOf(value).length()).append("duplicate key: ").append(value).toString());
                        }
                        zzapy.endArray();
                    }
                    zzapy.endArray();
                }
                else {
                    zzapy.beginObject();
                    while (zzapy.hasNext()) {
                        zzapd.blQ.zzi(zzapy);
                        final K zzb2 = this.bmC.zzb(zzapy);
                        if (map.put(zzb2, this.bmD.zzb(zzapy)) != null) {
                            final String value2 = String.valueOf(zzb2);
                            throw new zzaoq(new StringBuilder(15 + String.valueOf(value2).length()).append("duplicate key: ").append(value2).toString());
                        }
                    }
                    zzapy.endObject();
                }
            }
            return map;
        }
    }
}
