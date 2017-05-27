// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.Arrays;
import java.util.Collection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class zzarg implements Cloneable
{
    private static final zzarh bqx;
    private zzarh[] bqA;
    private boolean bqy;
    private int[] bqz;
    private int mSize;
    
    static {
        bqx = new zzarh();
    }
    
    zzarg() {
        this(10);
    }
    
    zzarg(final int n) {
        this.bqy = false;
        final int idealIntArraySize = this.idealIntArraySize(n);
        this.bqz = new int[idealIntArraySize];
        this.bqA = new zzarh[idealIntArraySize];
        this.mSize = 0;
    }
    
    private int idealByteArraySize(int n) {
        for (int i = 4; i < 32; ++i) {
            if (n <= -12 + (1 << i)) {
                n = -12 + (1 << i);
                break;
            }
        }
        return n;
    }
    
    private int idealIntArraySize(final int n) {
        return this.idealByteArraySize(n * 4) / 4;
    }
    
    private boolean zza(final int[] array, final int[] array2, final int n) {
        for (int i = 0; i < n; ++i) {
            final int n2 = array[i];
            final int n3 = array2[i];
            final boolean b = false;
            if (n2 != n3) {
                return b;
            }
        }
        return true;
    }
    
    private boolean zza(final zzarh[] array, final zzarh[] array2, final int n) {
        for (int i = 0; i < n; ++i) {
            final boolean equals = array[i].equals(array2[i]);
            final boolean b = false;
            if (!equals) {
                return b;
            }
        }
        return true;
    }
    
    private int zzahs(final int n) {
        int i = 0;
        int n2 = -1 + this.mSize;
        while (i <= n2) {
            final int n3 = i + n2 >>> 1;
            final int n4 = this.bqz[n3];
            if (n4 < n) {
                i = n3 + 1;
            }
            else {
                if (n4 <= n) {
                    return n3;
                }
                n2 = n3 - 1;
            }
        }
        return ~i;
    }
    
    public final zzarg cR() {
        final int size = this.size();
        final zzarg zzarg = new zzarg(size);
        System.arraycopy(this.bqz, 0, zzarg.bqz, 0, size);
        for (int i = 0; i < size; ++i) {
            if (this.bqA[i] != null) {
                zzarg.bqA[i] = (zzarh)this.bqA[i].clone();
            }
        }
        zzarg.mSize = size;
        return zzarg;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        if (o != this) {
            if (!(o instanceof zzarg)) {
                b = false;
            }
            else {
                final zzarg zzarg = (zzarg)o;
                if (this.size() != zzarg.size()) {
                    b = false;
                }
                else if (!this.zza(this.bqz, zzarg.bqz, this.mSize) || !this.zza(this.bqA, zzarg.bqA, this.mSize)) {
                    b = false;
                }
            }
        }
        return b;
    }
    
    @Override
    public int hashCode() {
        int n = 17;
        for (int i = 0; i < this.mSize; ++i) {
            n = 31 * (n * 31 + this.bqz[i]) + this.bqA[i].hashCode();
        }
        return n;
    }
    
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    int size() {
        return this.mSize;
    }
    
    void zza(final int n, final zzarh zzarh) {
        final int zzahs = this.zzahs(n);
        if (zzahs >= 0) {
            this.bqA[zzahs] = zzarh;
        }
        else {
            final int n2 = ~zzahs;
            if (n2 < this.mSize && this.bqA[n2] == zzarg.bqx) {
                this.bqz[n2] = n;
                this.bqA[n2] = zzarh;
            }
            else {
                if (this.mSize >= this.bqz.length) {
                    final int idealIntArraySize = this.idealIntArraySize(1 + this.mSize);
                    final int[] bqz = new int[idealIntArraySize];
                    final zzarh[] bqA = new zzarh[idealIntArraySize];
                    System.arraycopy(this.bqz, 0, bqz, 0, this.bqz.length);
                    System.arraycopy(this.bqA, 0, bqA, 0, this.bqA.length);
                    this.bqz = bqz;
                    this.bqA = bqA;
                }
                if (this.mSize - n2 != 0) {
                    System.arraycopy(this.bqz, n2, this.bqz, n2 + 1, this.mSize - n2);
                    System.arraycopy(this.bqA, n2, this.bqA, n2 + 1, this.mSize - n2);
                }
                this.bqz[n2] = n;
                this.bqA[n2] = zzarh;
                ++this.mSize;
            }
        }
    }
    
    zzarh zzahq(final int n) {
        final int zzahs = this.zzahs(n);
        zzarh zzarh;
        if (zzahs < 0 || this.bqA[zzahs] == zzarg.bqx) {
            zzarh = null;
        }
        else {
            zzarh = this.bqA[zzahs];
        }
        return zzarh;
    }
    
    zzarh zzahr(final int n) {
        return this.bqA[n];
    }
}
