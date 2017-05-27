// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import java.io.Writer;

public final class zzapp extends zzaqa
{
    private static final Writer bmx;
    private static final zzaon bmy;
    private zzaoh bmA;
    private final List<zzaoh> bmw;
    private String bmz;
    
    static {
        bmx = new Writer() {
            @Override
            public void close() throws IOException {
                throw new AssertionError();
            }
            
            @Override
            public void flush() throws IOException {
                throw new AssertionError();
            }
            
            @Override
            public void write(final char[] array, final int n, final int n2) {
                throw new AssertionError();
            }
        };
        bmy = new zzaon("closed");
    }
    
    public zzapp() {
        super(zzapp.bmx);
        this.bmw = new ArrayList<zzaoh>();
        this.bmA = zzaoj.bld;
    }
    
    private zzaoh bs() {
        return this.bmw.get(-1 + this.bmw.size());
    }
    
    private void zzd(final zzaoh bmA) {
        if (this.bmz != null) {
            if (!bmA.aV() || this.bK()) {
                ((zzaok)this.bs()).zza(this.bmz, bmA);
            }
            this.bmz = null;
        }
        else if (this.bmw.isEmpty()) {
            this.bmA = bmA;
        }
        else {
            final zzaoh bs = this.bs();
            if (!(bs instanceof zzaoe)) {
                throw new IllegalStateException();
            }
            ((zzaoe)bs).zzc(bmA);
        }
    }
    
    public zzaoh br() {
        if (!this.bmw.isEmpty()) {
            final String value = String.valueOf(this.bmw);
            throw new IllegalStateException(new StringBuilder(34 + String.valueOf(value).length()).append("Expected one JSON element but was ").append(value).toString());
        }
        return this.bmA;
    }
    
    @Override
    public zzaqa bt() throws IOException {
        final zzaoe zzaoe = new zzaoe();
        this.zzd(zzaoe);
        this.bmw.add(zzaoe);
        return this;
    }
    
    @Override
    public zzaqa bu() throws IOException {
        if (this.bmw.isEmpty() || this.bmz != null) {
            throw new IllegalStateException();
        }
        if (this.bs() instanceof zzaoe) {
            this.bmw.remove(-1 + this.bmw.size());
            return this;
        }
        throw new IllegalStateException();
    }
    
    @Override
    public zzaqa bv() throws IOException {
        final zzaok zzaok = new zzaok();
        this.zzd(zzaok);
        this.bmw.add(zzaok);
        return this;
    }
    
    @Override
    public zzaqa bw() throws IOException {
        if (this.bmw.isEmpty() || this.bmz != null) {
            throw new IllegalStateException();
        }
        if (this.bs() instanceof zzaok) {
            this.bmw.remove(-1 + this.bmw.size());
            return this;
        }
        throw new IllegalStateException();
    }
    
    @Override
    public zzaqa bx() throws IOException {
        this.zzd(zzaoj.bld);
        return this;
    }
    
    @Override
    public void close() throws IOException {
        if (!this.bmw.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.bmw.add(zzapp.bmy);
    }
    
    @Override
    public void flush() throws IOException {
    }
    
    @Override
    public zzaqa zza(final Number n) throws IOException {
        final zzaqa bx;
        if (n == null) {
            bx = this.bx();
        }
        else {
            if (!this.isLenient()) {
                final double doubleValue = n.doubleValue();
                if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                    final String value = String.valueOf(n);
                    throw new IllegalArgumentException(new StringBuilder(33 + String.valueOf(value).length()).append("JSON forbids NaN and infinities: ").append(value).toString());
                }
            }
            this.zzd(new zzaon(n));
        }
        return bx;
    }
    
    @Override
    public zzaqa zzcu(final long n) throws IOException {
        this.zzd(new zzaon(n));
        return this;
    }
    
    @Override
    public zzaqa zzdf(final boolean b) throws IOException {
        this.zzd(new zzaon(Boolean.valueOf(b)));
        return this;
    }
    
    @Override
    public zzaqa zzus(final String bmz) throws IOException {
        if (this.bmw.isEmpty() || this.bmz != null) {
            throw new IllegalStateException();
        }
        if (this.bs() instanceof zzaok) {
            this.bmz = bmz;
            return this;
        }
        throw new IllegalStateException();
    }
    
    @Override
    public zzaqa zzut(final String s) throws IOException {
        final zzaqa bx;
        if (s == null) {
            bx = this.bx();
        }
        else {
            this.zzd(new zzaon(s));
        }
        return bx;
    }
}
