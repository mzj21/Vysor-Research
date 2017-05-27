// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzab;
import java.util.ArrayList;
import java.util.List;

@zziy
public class zzkx
{
    private final String[] zzcrz;
    private final double[] zzcsa;
    private final double[] zzcsb;
    private final int[] zzcsc;
    private int zzcsd;
    
    private zzkx(final zzb zzb) {
        final int size = zzb.zzcsi.size();
        this.zzcrz = zzb.zzcsh.toArray(new String[size]);
        this.zzcsa = this.zzn(zzb.zzcsi);
        this.zzcsb = this.zzn(zzb.zzcsj);
        this.zzcsc = new int[size];
        this.zzcsd = 0;
    }
    
    private double[] zzn(final List<Double> list) {
        final double[] array = new double[list.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = list.get(i);
        }
        return array;
    }
    
    public List<zza> getBuckets() {
        final ArrayList<zza> list = new ArrayList<zza>(this.zzcrz.length);
        for (int i = 0; i < this.zzcrz.length; ++i) {
            list.add(new zza(this.zzcrz[i], this.zzcsb[i], this.zzcsa[i], this.zzcsc[i] / this.zzcsd, this.zzcsc[i]));
        }
        return list;
    }
    
    public void zza(final double n) {
        ++this.zzcsd;
        for (int i = 0; i < this.zzcsb.length; ++i) {
            if (this.zzcsb[i] <= n && n < this.zzcsa[i]) {
                final int[] zzcsc = this.zzcsc;
                ++zzcsc[i];
            }
            if (n < this.zzcsb[i]) {
                break;
            }
        }
    }
    
    public static class zza
    {
        public final int count;
        public final String name;
        public final double zzcse;
        public final double zzcsf;
        public final double zzcsg;
        
        public zza(final String name, final double zzcsf, final double zzcse, final double zzcsg, final int count) {
            this.name = name;
            this.zzcsf = zzcsf;
            this.zzcse = zzcse;
            this.zzcsg = zzcsg;
            this.count = count;
        }
        
        @Override
        public boolean equals(final Object o) {
            final boolean b = o instanceof zza;
            boolean b2 = false;
            if (b) {
                final zza zza = (zza)o;
                final boolean equal = zzab.equal(this.name, zza.name);
                b2 = false;
                if (equal) {
                    final double n = dcmpl(this.zzcse, zza.zzcse);
                    b2 = false;
                    if (n == 0) {
                        final double n2 = dcmpl(this.zzcsf, zza.zzcsf);
                        b2 = false;
                        if (n2 == 0) {
                            final int count = this.count;
                            final int count2 = zza.count;
                            b2 = false;
                            if (count == count2) {
                                final int compare = Double.compare(this.zzcsg, zza.zzcsg);
                                b2 = false;
                                if (compare == 0) {
                                    b2 = true;
                                }
                            }
                        }
                    }
                }
            }
            return b2;
        }
        
        @Override
        public int hashCode() {
            return zzab.hashCode(this.name, this.zzcse, this.zzcsf, this.zzcsg, this.count);
        }
        
        @Override
        public String toString() {
            return zzab.zzx(this).zzg("name", this.name).zzg("minBound", this.zzcsf).zzg("maxBound", this.zzcse).zzg("percent", this.zzcsg).zzg("count", this.count).toString();
        }
    }
    
    public static class zzb
    {
        private final List<String> zzcsh;
        private final List<Double> zzcsi;
        private final List<Double> zzcsj;
        
        public zzb() {
            this.zzcsh = new ArrayList<String>();
            this.zzcsi = new ArrayList<Double>();
            this.zzcsj = new ArrayList<Double>();
        }
        
        public zzb zza(final String s, final double n, final double n2) {
            int i;
            for (i = 0; i < this.zzcsh.size(); ++i) {
                final double doubleValue = this.zzcsj.get(i);
                final double doubleValue2 = this.zzcsi.get(i);
                if (n < doubleValue || (doubleValue == n && n2 < doubleValue2)) {
                    break;
                }
            }
            this.zzcsh.add(i, s);
            this.zzcsj.add(i, n);
            this.zzcsi.add(i, n2);
            return this;
        }
        
        public zzkx zzuw() {
            return new zzkx(this, null);
        }
    }
}
