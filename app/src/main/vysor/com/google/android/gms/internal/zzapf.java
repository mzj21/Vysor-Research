// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.AbstractSet;
import java.util.Map;
import java.util.Set;
import java.util.Comparator;
import java.io.Serializable;
import java.util.AbstractMap;

public final class zzapf<K, V> extends AbstractMap<K, V> implements Serializable
{
    private static final Comparator<Comparable> blR;
    Comparator<? super K> aWP;
    zzd<K, V> blS;
    final zzd<K, V> blT;
    private zza blU;
    private zzb blV;
    int modCount;
    int size;
    
    static {
        blR = new Comparator<Comparable>() {
            public int zza(final Comparable comparable, final Comparable comparable2) {
                return comparable.compareTo(comparable2);
            }
        };
    }
    
    public zzapf() {
        this((Comparator)zzapf.blR);
    }
    
    public zzapf(Comparator<? super K> blR) {
        this.size = 0;
        this.modCount = 0;
        this.blT = new zzd<K, V>();
        if (blR == null) {
            blR = zzapf.blR;
        }
        this.aWP = (Comparator<? super K>)blR;
    }
    
    private boolean equal(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    private void zza(final zzd<K, V> zzd) {
        final zzd<K, V> bmd = zzd.bmd;
        final zzd<K, V> bme = zzd.bme;
        final zzd<K, V> bmd2 = bme.bmd;
        final zzd<K, V> bme2 = bme.bme;
        zzd.bme = bmd2;
        if (bmd2 != null) {
            bmd2.bmc = zzd;
        }
        this.zza(zzd, bme);
        bme.bmd = zzd;
        zzd.bmc = bme;
        int height;
        if (bmd != null) {
            height = bmd.height;
        }
        else {
            height = 0;
        }
        int height2;
        if (bmd2 != null) {
            height2 = bmd2.height;
        }
        else {
            height2 = 0;
        }
        zzd.height = 1 + Math.max(height, height2);
        final int height3 = zzd.height;
        int height4 = 0;
        if (bme2 != null) {
            height4 = bme2.height;
        }
        bme.height = 1 + Math.max(height3, height4);
    }
    
    private void zza(final zzd<K, V> zzd, final zzd<K, V> blS) {
        final zzd<K, V> bmc = zzd.bmc;
        zzd.bmc = null;
        if (blS != null) {
            blS.bmc = bmc;
        }
        if (bmc != null) {
            if (bmc.bmd == zzd) {
                bmc.bmd = blS;
            }
            else {
                assert bmc.bme == zzd;
                bmc.bme = blS;
            }
        }
        else {
            this.blS = blS;
        }
    }
    
    private void zzb(final zzd<K, V> zzd) {
        final zzd<K, V> bmd = zzd.bmd;
        final zzd<K, V> bme = zzd.bme;
        final zzd<K, V> bmd2 = bmd.bmd;
        final zzd<K, V> bme2 = bmd.bme;
        zzd.bmd = bme2;
        if (bme2 != null) {
            bme2.bmc = zzd;
        }
        this.zza(zzd, bmd);
        bmd.bme = zzd;
        zzd.bmc = bmd;
        int height;
        if (bme != null) {
            height = bme.height;
        }
        else {
            height = 0;
        }
        int height2;
        if (bme2 != null) {
            height2 = bme2.height;
        }
        else {
            height2 = 0;
        }
        zzd.height = 1 + Math.max(height, height2);
        final int height3 = zzd.height;
        int height4 = 0;
        if (bmd2 != null) {
            height4 = bmd2.height;
        }
        bmd.height = 1 + Math.max(height3, height4);
    }
    
    private void zzb(zzd<K, V> bmc, final boolean b) {
        while (bmc != null) {
            final zzd<K, V> bmd = bmc.bmd;
            final zzd<K, V> bme = bmc.bme;
            int height;
            if (bmd != null) {
                height = bmd.height;
            }
            else {
                height = 0;
            }
            int height2;
            if (bme != null) {
                height2 = bme.height;
            }
            else {
                height2 = 0;
            }
            final int n = height - height2;
            if (n == -2) {
                final zzd<K, V> bmd2 = bme.bmd;
                final zzd<K, V> bme2 = bme.bme;
                int height3;
                if (bme2 != null) {
                    height3 = bme2.height;
                }
                else {
                    height3 = 0;
                }
                int height4;
                if (bmd2 != null) {
                    height4 = bmd2.height;
                }
                else {
                    height4 = 0;
                }
                final int n2 = height4 - height3;
                if (n2 == -1 || (n2 == 0 && !b)) {
                    this.zza(bmc);
                }
                else {
                    assert n2 == 1;
                    this.zzb(bme);
                    this.zza(bmc);
                }
                if (b) {
                    break;
                }
            }
            else if (n == 2) {
                final zzd<K, V> bmd3 = bmd.bmd;
                final zzd<K, V> bme3 = bmd.bme;
                int height5;
                if (bme3 != null) {
                    height5 = bme3.height;
                }
                else {
                    height5 = 0;
                }
                int height6;
                if (bmd3 != null) {
                    height6 = bmd3.height;
                }
                else {
                    height6 = 0;
                }
                final int n3 = height6 - height5;
                if (n3 == 1 || (n3 == 0 && !b)) {
                    this.zzb(bmc);
                }
                else {
                    assert n3 == -1;
                    this.zza(bmd);
                    this.zzb(bmc);
                }
                if (b) {
                    break;
                }
            }
            else if (n == 0) {
                bmc.height = height + 1;
                if (b) {
                    break;
                }
            }
            else {
                assert n == 1;
                bmc.height = 1 + Math.max(height, height2);
                if (!b) {
                    break;
                }
            }
            bmc = bmc.bmc;
        }
    }
    
    @Override
    public void clear() {
        this.blS = null;
        this.size = 0;
        ++this.modCount;
        final zzd<K, V> blT = this.blT;
        blT.bmf = blT;
        blT.blZ = blT;
    }
    
    @Override
    public boolean containsKey(final Object o) {
        return this.zzcr(o) != null;
    }
    
    @Override
    public Set<Entry<K, V>> entrySet() {
        zza blU = this.blU;
        if (blU == null) {
            blU = new zza();
            this.blU = blU;
        }
        return blU;
    }
    
    @Override
    public V get(final Object o) {
        final zzd<K, V> zzcr = this.zzcr(o);
        V value;
        if (zzcr != null) {
            value = zzcr.value;
        }
        else {
            value = null;
        }
        return value;
    }
    
    @Override
    public Set<K> keySet() {
        zzb blV = this.blV;
        if (blV == null) {
            blV = new zzb();
            this.blV = blV;
        }
        return blV;
    }
    
    @Override
    public V put(final K k, final V value) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        final zzd<K, V> zza = this.zza(k, true);
        final V value2 = zza.value;
        zza.value = value;
        return value2;
    }
    
    @Override
    public V remove(final Object o) {
        final zzd<K, V> zzcs = this.zzcs(o);
        V value;
        if (zzcs != null) {
            value = zzcs.value;
        }
        else {
            value = null;
        }
        return value;
    }
    
    @Override
    public int size() {
        return this.size;
    }
    
    zzd<K, V> zza(final K k, final boolean b) {
        final Comparator<? super K> awp = this.aWP;
        Object blS = this.blS;
        Object o = null;
        int n = 0;
        Label_0112: {
            if (blS == null) {
                o = blS;
                n = 0;
                break Label_0112;
            }
            Comparable<K> comparable;
            if (awp == zzapf.blR) {
                comparable = (Comparable<K>)k;
            }
            else {
                comparable = null;
            }
            zzd<K, V> zzd;
            while (true) {
                int n2;
                if (comparable != null) {
                    n2 = comparable.compareTo(((zzd)blS).aXd);
                }
                else {
                    n2 = awp.compare(k, (K)((zzd)blS).aXd);
                }
                if (n2 == 0) {
                    zzd = (zzd<K, V>)blS;
                    break;
                }
                Entry<K, V> entry;
                if (n2 < 0) {
                    entry = ((zzd)blS).bmd;
                }
                else {
                    entry = ((zzd)blS).bme;
                }
                if (entry == null) {
                    final int n3 = n2;
                    o = blS;
                    n = n3;
                    break Label_0112;
                }
                blS = entry;
            }
            return zzd;
        }
        zzd<K, V> zzd = null;
        if (b) {
            final zzd<K, V> blT = this.blT;
            zzd bme;
            if (o == null) {
                if (awp == zzapf.blR && !(k instanceof Comparable)) {
                    throw new ClassCastException(String.valueOf(k.getClass().getName()).concat(" is not Comparable"));
                }
                bme = new zzd<K, V>((zzd<Object, Object>)o, k, (zzd<Object, Object>)blT, (zzd<Object, Object>)blT.bmf);
                this.blS = (zzd<K, V>)bme;
            }
            else {
                bme = new zzd<K, V>((zzd<K, V>)o, k, blT, blT.bmf);
                if (n < 0) {
                    ((zzd)o).bmd = bme;
                }
                else {
                    ((zzd)o).bme = bme;
                }
                this.zzb((zzd<K, V>)o, true);
            }
            ++this.size;
            ++this.modCount;
            zzd = (zzd<K, V>)bme;
            return zzd;
        }
        return zzd;
    }
    
    void zza(final zzd<K, V> zzd, final boolean b) {
        if (b) {
            zzd.bmf.blZ = zzd.blZ;
            zzd.blZ.bmf = zzd.bmf;
        }
        final zzd<K, V> bmd = zzd.bmd;
        final zzd<K, V> bme = zzd.bme;
        final zzd<K, V> bmc = zzd.bmc;
        if (bmd != null && bme != null) {
            zzd<K, V> zzd2;
            if (bmd.height > bme.height) {
                zzd2 = bmd.bk();
            }
            else {
                zzd2 = bme.bj();
            }
            this.zza(zzd2, false);
            final zzd<K, V> bmd2 = zzd.bmd;
            int height;
            if (bmd2 != null) {
                height = bmd2.height;
                zzd2.bmd = bmd2;
                bmd2.bmc = zzd2;
                zzd.bmd = null;
            }
            else {
                height = 0;
            }
            final zzd<K, V> bme2 = zzd.bme;
            int height2 = 0;
            if (bme2 != null) {
                height2 = bme2.height;
                zzd2.bme = bme2;
                bme2.bmc = zzd2;
                zzd.bme = null;
            }
            zzd2.height = 1 + Math.max(height, height2);
            this.zza(zzd, zzd2);
        }
        else {
            if (bmd != null) {
                this.zza(zzd, bmd);
                zzd.bmd = null;
            }
            else if (bme != null) {
                this.zza(zzd, bme);
                zzd.bme = null;
            }
            else {
                this.zza(zzd, null);
            }
            this.zzb(bmc, false);
            --this.size;
            ++this.modCount;
        }
    }
    
    zzd<K, V> zzc(final Entry<?, ?> entry) {
        zzd<K, V> zzcr = this.zzcr(entry.getKey());
        int n;
        if (zzcr != null && this.equal(zzcr.value, entry.getValue())) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n == 0) {
            zzcr = null;
        }
        return zzcr;
    }
    
    zzd<K, V> zzcr(final Object o) {
        Entry<K, V> zza = null;
        if (o == null) {
            return (zzd<K, V>)zza;
        }
        try {
            zza = (Entry<K, V>)this.zza(o, false);
            return (zzd<K, V>)zza;
        }
        catch (ClassCastException ex) {
            zza = null;
            return (zzd<K, V>)zza;
        }
    }
    
    zzd<K, V> zzcs(final Object o) {
        final zzd<K, V> zzcr = this.zzcr(o);
        if (zzcr != null) {
            this.zza(zzcr, true);
        }
        return zzcr;
    }
    
    class zza extends AbstractSet<Entry<K, V>>
    {
        @Override
        public void clear() {
            zzapf.this.clear();
        }
        
        @Override
        public boolean contains(final Object o) {
            return o instanceof Entry && zzapf.this.zzc((Entry<?, ?>)o) != null;
        }
        
        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new zzc<Entry<K, V>>() {
                @Override
                public Entry<K, V> next() {
                    return ((zzc)this).bi();
                }
            };
        }
        
        @Override
        public boolean remove(final Object o) {
            final boolean b = o instanceof Entry;
            boolean b2 = false;
            if (b) {
                final zzd<K, V> zzc = zzapf.this.zzc((Entry<?, ?>)o);
                b2 = false;
                if (zzc != null) {
                    zzapf.this.zza(zzc, true);
                    b2 = true;
                }
            }
            return b2;
        }
        
        @Override
        public int size() {
            return zzapf.this.size;
        }
    }
    
    final class zzb extends AbstractSet<K>
    {
        @Override
        public void clear() {
            zzapf.this.clear();
        }
        
        @Override
        public boolean contains(final Object o) {
            return zzapf.this.containsKey(o);
        }
        
        @Override
        public Iterator<K> iterator() {
            return new zzc<K>() {
                @Override
                public K next() {
                    return ((zzc)this).bi().aXd;
                }
            };
        }
        
        @Override
        public boolean remove(final Object o) {
            return zzapf.this.zzcs(o) != null;
        }
        
        @Override
        public int size() {
            return zzapf.this.size;
        }
    }
    
    private abstract class zzc<T> implements Iterator<T>
    {
        zzd<K, V> blZ;
        zzd<K, V> bma;
        int bmb;
        
        private zzc() {
            this.blZ = zzapf.this.blT.blZ;
            this.bma = null;
            this.bmb = zzapf.this.modCount;
        }
        
        final zzd<K, V> bi() {
            final zzd<K, V> blZ = this.blZ;
            if (blZ == zzapf.this.blT) {
                throw new NoSuchElementException();
            }
            if (zzapf.this.modCount != this.bmb) {
                throw new ConcurrentModificationException();
            }
            this.blZ = blZ.blZ;
            return this.bma = blZ;
        }
        
        @Override
        public final boolean hasNext() {
            return this.blZ != zzapf.this.blT;
        }
        
        @Override
        public final void remove() {
            if (this.bma == null) {
                throw new IllegalStateException();
            }
            zzapf.this.zza(this.bma, true);
            this.bma = null;
            this.bmb = zzapf.this.modCount;
        }
    }
    
    static final class zzd<K, V> implements Entry<K, V>
    {
        final K aXd;
        zzd<K, V> blZ;
        zzd<K, V> bmc;
        zzd<K, V> bmd;
        zzd<K, V> bme;
        zzd<K, V> bmf;
        int height;
        V value;
        
        zzd() {
            this.aXd = null;
            this.bmf = this;
            this.blZ = this;
        }
        
        zzd(final zzd<K, V> bmc, final K aXd, final zzd<K, V> blZ, final zzd<K, V> bmf) {
            this.bmc = bmc;
            this.aXd = aXd;
            this.height = 1;
            this.blZ = blZ;
            this.bmf = bmf;
            bmf.blZ = this;
            blZ.bmf = this;
        }
        
        public zzd<K, V> bj() {
            zzd<K, V> bmd2;
            for (zzd<K, V> bmd = this.bmd; bmd != null; bmd = bmd2) {
                bmd2 = bmd.bmd;
                this = bmd;
            }
            return this;
        }
        
        public zzd<K, V> bk() {
            zzd<K, V> bme2;
            for (zzd<K, V> bme = this.bme; bme != null; bme = bme2) {
                bme2 = bme.bme;
                this = bme;
            }
            return this;
        }
        
        @Override
        public boolean equals(final Object o) {
            final boolean b = o instanceof Entry;
            boolean b2 = false;
            if (b) {
                final Entry entry = (Entry)o;
                if (this.aXd == null) {
                    final K key = (K)entry.getKey();
                    b2 = false;
                    if (key != null) {
                        return b2;
                    }
                }
                else {
                    final boolean equals = this.aXd.equals(entry.getKey());
                    b2 = false;
                    if (!equals) {
                        return b2;
                    }
                }
                if (this.value == null) {
                    final Object value = entry.getValue();
                    b2 = false;
                    if (value != null) {
                        return b2;
                    }
                }
                else {
                    final boolean equals2 = this.value.equals(entry.getValue());
                    b2 = false;
                    if (!equals2) {
                        return b2;
                    }
                }
                b2 = true;
            }
            return b2;
        }
        
        @Override
        public K getKey() {
            return this.aXd;
        }
        
        @Override
        public V getValue() {
            return this.value;
        }
        
        @Override
        public int hashCode() {
            int hashCode;
            if (this.aXd == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.aXd.hashCode();
            }
            final V value = this.value;
            int hashCode2 = 0;
            if (value != null) {
                hashCode2 = this.value.hashCode();
            }
            return hashCode ^ hashCode2;
        }
        
        @Override
        public V setValue(final V value) {
            final V value2 = this.value;
            this.value = value;
            return value2;
        }
        
        @Override
        public String toString() {
            final String value = String.valueOf(this.aXd);
            final String value2 = String.valueOf(this.value);
            return new StringBuilder(1 + String.valueOf(value).length() + String.valueOf(value2).length()).append(value).append("=").append(value2).toString();
        }
    }
}
