// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import java.util.ArrayList;

public abstract class zzf<T> extends AbstractDataBuffer<T>
{
    private boolean Ab;
    private ArrayList<Integer> Ac;
    
    protected zzf(final DataHolder dataHolder) {
        super(dataHolder);
        this.Ab = false;
    }
    
    private void zzati() {
        while (true) {
            Label_0204: {
                while (true) {
                    String zzd;
                    int n;
                    String zzd2;
                    synchronized (this) {
                        if (this.Ab) {
                            break;
                        }
                        final int count = this.xi.getCount();
                        this.Ac = new ArrayList<Integer>();
                        if (count <= 0) {
                            break Label_0204;
                        }
                        this.Ac.add(0);
                        final String zzath = this.zzath();
                        zzd = this.xi.zzd(zzath, 0, this.xi.zzgb(0));
                        n = 1;
                        if (n >= count) {
                            break Label_0204;
                        }
                        final int zzgb = this.xi.zzgb(n);
                        zzd2 = this.xi.zzd(zzath, n, zzgb);
                        if (zzd2 == null) {
                            throw new NullPointerException(new StringBuilder(78 + String.valueOf(zzath).length()).append("Missing value for markerColumn: ").append(zzath).append(", at row: ").append(n).append(", for window: ").append(zzgb).toString());
                        }
                    }
                    if (!zzd2.equals(zzd)) {
                        this.Ac.add(n);
                    }
                    else {
                        zzd2 = zzd;
                    }
                    ++n;
                    zzd = zzd2;
                    continue;
                }
            }
            this.Ab = true;
            break;
        }
    }
    // monitorexit(this)
    
    @Override
    public final T get(final int n) {
        this.zzati();
        return this.zzl(this.zzgf(n), this.zzgg(n));
    }
    
    @Override
    public int getCount() {
        this.zzati();
        return this.Ac.size();
    }
    
    protected abstract String zzath();
    
    protected String zzatj() {
        return null;
    }
    
    int zzgf(final int n) {
        if (n < 0 || n >= this.Ac.size()) {
            throw new IllegalArgumentException(new StringBuilder(53).append("Position ").append(n).append(" is out of bounds for this buffer").toString());
        }
        return this.Ac.get(n);
    }
    
    protected int zzgg(final int n) {
        int n2;
        if (n < 0 || n == this.Ac.size()) {
            n2 = 0;
        }
        else {
            if (n == -1 + this.Ac.size()) {
                n2 = this.xi.getCount() - this.Ac.get(n);
            }
            else {
                n2 = this.Ac.get(n + 1) - this.Ac.get(n);
            }
            if (n2 == 1) {
                final int zzgf = this.zzgf(n);
                final int zzgb = this.xi.zzgb(zzgf);
                final String zzatj = this.zzatj();
                if (zzatj != null && this.xi.zzd(zzatj, zzgf, zzgb) == null) {
                    n2 = 0;
                }
            }
        }
        return n2;
    }
    
    protected abstract T zzl(final int p0, final int p1);
}
