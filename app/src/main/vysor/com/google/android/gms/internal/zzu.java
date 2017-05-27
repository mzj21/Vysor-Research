// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collections;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Comparator;

public class zzu
{
    protected static final Comparator<byte[]> zzbv;
    private List<byte[]> zzbr;
    private List<byte[]> zzbs;
    private int zzbt;
    private final int zzbu;
    
    static {
        zzbv = new Comparator<byte[]>() {
            public int zza(final byte[] array, final byte[] array2) {
                return array.length - array2.length;
            }
        };
    }
    
    public zzu(final int zzbu) {
        this.zzbr = new LinkedList<byte[]>();
        this.zzbs = new ArrayList<byte[]>(64);
        this.zzbt = 0;
        this.zzbu = zzbu;
    }
    
    private void zzw() {
        synchronized (this) {
            while (this.zzbt > this.zzbu) {
                final byte[] array = this.zzbr.remove(0);
                this.zzbs.remove(array);
                this.zzbt -= array.length;
            }
        }
    }
    // monitorexit(this)
    
    public void zza(final byte[] array) {
        // monitorenter(this)
        if (array == null) {
            return;
        }
        try {
            if (array.length <= this.zzbu) {
                this.zzbr.add(array);
                int binarySearch = Collections.binarySearch(this.zzbs, array, zzu.zzbv);
                if (binarySearch < 0) {
                    binarySearch = -1 + -binarySearch;
                }
                this.zzbs.add(binarySearch, array);
                this.zzbt += array.length;
                this.zzw();
            }
        }
        finally {
        }
        // monitorexit(this)
    }
    
    public byte[] zzb(final int n) {
        // monitorenter(this)
        int i = 0;
        try {
            while (i < this.zzbs.size()) {
                final byte[] array = this.zzbs.get(i);
                if (array.length >= n) {
                    this.zzbt -= array.length;
                    this.zzbs.remove(i);
                    this.zzbr.remove(array);
                    return array;
                }
                ++i;
            }
            return new byte[n];
        }
        finally {
        }
        // monitorexit(this)
    }
}
