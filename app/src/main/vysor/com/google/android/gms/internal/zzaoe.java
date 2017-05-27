// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public final class zzaoe extends zzaoh implements Iterable<zzaoh>
{
    private final List<zzaoh> aLw;
    
    public zzaoe() {
        this.aLw = new ArrayList<zzaoh>();
    }
    
    @Override
    public Number aQ() {
        if (this.aLw.size() == 1) {
            return this.aLw.get(0).aQ();
        }
        throw new IllegalStateException();
    }
    
    @Override
    public String aR() {
        if (this.aLw.size() == 1) {
            return this.aLw.get(0).aR();
        }
        throw new IllegalStateException();
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof zzaoe && ((zzaoe)o).aLw.equals(this.aLw));
    }
    
    @Override
    public boolean getAsBoolean() {
        if (this.aLw.size() == 1) {
            return this.aLw.get(0).getAsBoolean();
        }
        throw new IllegalStateException();
    }
    
    @Override
    public double getAsDouble() {
        if (this.aLw.size() == 1) {
            return this.aLw.get(0).getAsDouble();
        }
        throw new IllegalStateException();
    }
    
    @Override
    public int getAsInt() {
        if (this.aLw.size() == 1) {
            return this.aLw.get(0).getAsInt();
        }
        throw new IllegalStateException();
    }
    
    @Override
    public long getAsLong() {
        if (this.aLw.size() == 1) {
            return this.aLw.get(0).getAsLong();
        }
        throw new IllegalStateException();
    }
    
    @Override
    public int hashCode() {
        return this.aLw.hashCode();
    }
    
    @Override
    public Iterator<zzaoh> iterator() {
        return this.aLw.iterator();
    }
    
    public int size() {
        return this.aLw.size();
    }
    
    public zzaoh zzagv(final int n) {
        return this.aLw.get(n);
    }
    
    public void zzc(zzaoh bld) {
        if (bld == null) {
            bld = zzaoj.bld;
        }
        this.aLw.add(bld);
    }
}
