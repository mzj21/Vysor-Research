// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import java.io.Reader;

public final class zzapo extends zzapy
{
    private static final Reader bmu;
    private static final Object bmv;
    private final List<Object> bmw;
    
    static {
        bmu = new Reader() {
            @Override
            public void close() throws IOException {
                throw new AssertionError();
            }
            
            @Override
            public int read(final char[] array, final int n, final int n2) throws IOException {
                throw new AssertionError();
            }
        };
        bmv = new Object();
    }
    
    public zzapo(final zzaoh zzaoh) {
        super(zzapo.bmu);
        (this.bmw = new ArrayList<Object>()).add(zzaoh);
    }
    
    private Object bo() {
        return this.bmw.get(-1 + this.bmw.size());
    }
    
    private Object bp() {
        return this.bmw.remove(-1 + this.bmw.size());
    }
    
    private void zza(final zzapz zzapz) throws IOException {
        if (this.bn() != zzapz) {
            final String value = String.valueOf(zzapz);
            final String value2 = String.valueOf(this.bn());
            throw new IllegalStateException(new StringBuilder(18 + String.valueOf(value).length() + String.valueOf(value2).length()).append("Expected ").append(value).append(" but was ").append(value2).toString());
        }
    }
    
    @Override
    public void beginArray() throws IOException {
        this.zza(zzapz.bok);
        this.bmw.add(((zzaoe)this.bo()).iterator());
    }
    
    @Override
    public void beginObject() throws IOException {
        this.zza(zzapz.bom);
        this.bmw.add(((zzaok)this.bo()).entrySet().iterator());
    }
    
    @Override
    public zzapz bn() throws IOException {
        zzapz zzapz;
        if (this.bmw.isEmpty()) {
            zzapz = com.google.android.gms.internal.zzapz.bot;
        }
        else {
            final Object bo = this.bo();
            if (bo instanceof Iterator) {
                final boolean b = this.bmw.get(-2 + this.bmw.size()) instanceof zzaok;
                final Iterator<Object> iterator = (Iterator<Object>)bo;
                if (iterator.hasNext()) {
                    if (b) {
                        zzapz = com.google.android.gms.internal.zzapz.boo;
                    }
                    else {
                        this.bmw.add(iterator.next());
                        zzapz = this.bn();
                    }
                }
                else if (b) {
                    zzapz = com.google.android.gms.internal.zzapz.bon;
                }
                else {
                    zzapz = com.google.android.gms.internal.zzapz.bol;
                }
            }
            else if (bo instanceof zzaok) {
                zzapz = com.google.android.gms.internal.zzapz.bom;
            }
            else if (bo instanceof zzaoe) {
                zzapz = com.google.android.gms.internal.zzapz.bok;
            }
            else if (bo instanceof zzaon) {
                final zzaon zzaon = (zzaon)bo;
                if (zzaon.bc()) {
                    zzapz = com.google.android.gms.internal.zzapz.bop;
                }
                else if (zzaon.ba()) {
                    zzapz = com.google.android.gms.internal.zzapz.bor;
                }
                else {
                    if (!zzaon.bb()) {
                        throw new AssertionError();
                    }
                    zzapz = com.google.android.gms.internal.zzapz.boq;
                }
            }
            else if (bo instanceof zzaoj) {
                zzapz = com.google.android.gms.internal.zzapz.bos;
            }
            else {
                if (bo == zzapo.bmv) {
                    throw new IllegalStateException("JsonReader is closed");
                }
                throw new AssertionError();
            }
        }
        return zzapz;
    }
    
    public void bq() throws IOException {
        this.zza(zzapz.boo);
        final Map.Entry<K, Object> entry = ((Iterator)this.bo()).next();
        this.bmw.add(entry.getValue());
        this.bmw.add(new zzaon((String)entry.getKey()));
    }
    
    @Override
    public void close() throws IOException {
        this.bmw.clear();
        this.bmw.add(zzapo.bmv);
    }
    
    @Override
    public void endArray() throws IOException {
        this.zza(zzapz.bol);
        this.bp();
        this.bp();
    }
    
    @Override
    public void endObject() throws IOException {
        this.zza(zzapz.bon);
        this.bp();
        this.bp();
    }
    
    @Override
    public boolean hasNext() throws IOException {
        final zzapz bn = this.bn();
        return bn != zzapz.bon && bn != zzapz.bol;
    }
    
    @Override
    public boolean nextBoolean() throws IOException {
        this.zza(zzapz.bor);
        return ((zzaon)this.bp()).getAsBoolean();
    }
    
    @Override
    public double nextDouble() throws IOException {
        final zzapz bn = this.bn();
        if (bn != zzapz.boq && bn != zzapz.bop) {
            final String value = String.valueOf(zzapz.boq);
            final String value2 = String.valueOf(bn);
            throw new IllegalStateException(new StringBuilder(18 + String.valueOf(value).length() + String.valueOf(value2).length()).append("Expected ").append(value).append(" but was ").append(value2).toString());
        }
        final double asDouble = ((zzaon)this.bo()).getAsDouble();
        if (!this.isLenient() && (Double.isNaN(asDouble) || Double.isInfinite(asDouble))) {
            throw new NumberFormatException(new StringBuilder(57).append("JSON forbids NaN and infinities: ").append(asDouble).toString());
        }
        this.bp();
        return asDouble;
    }
    
    @Override
    public int nextInt() throws IOException {
        final zzapz bn = this.bn();
        if (bn != zzapz.boq && bn != zzapz.bop) {
            final String value = String.valueOf(zzapz.boq);
            final String value2 = String.valueOf(bn);
            throw new IllegalStateException(new StringBuilder(18 + String.valueOf(value).length() + String.valueOf(value2).length()).append("Expected ").append(value).append(" but was ").append(value2).toString());
        }
        final int asInt = ((zzaon)this.bo()).getAsInt();
        this.bp();
        return asInt;
    }
    
    @Override
    public long nextLong() throws IOException {
        final zzapz bn = this.bn();
        if (bn != zzapz.boq && bn != zzapz.bop) {
            final String value = String.valueOf(zzapz.boq);
            final String value2 = String.valueOf(bn);
            throw new IllegalStateException(new StringBuilder(18 + String.valueOf(value).length() + String.valueOf(value2).length()).append("Expected ").append(value).append(" but was ").append(value2).toString());
        }
        final long asLong = ((zzaon)this.bo()).getAsLong();
        this.bp();
        return asLong;
    }
    
    @Override
    public String nextName() throws IOException {
        this.zza(zzapz.boo);
        final Map.Entry<K, Object> entry = ((Iterator)this.bo()).next();
        this.bmw.add(entry.getValue());
        return (String)entry.getKey();
    }
    
    @Override
    public void nextNull() throws IOException {
        this.zza(zzapz.bos);
        this.bp();
    }
    
    @Override
    public String nextString() throws IOException {
        final zzapz bn = this.bn();
        if (bn != zzapz.bop && bn != zzapz.boq) {
            final String value = String.valueOf(zzapz.bop);
            final String value2 = String.valueOf(bn);
            throw new IllegalStateException(new StringBuilder(18 + String.valueOf(value).length() + String.valueOf(value2).length()).append("Expected ").append(value).append(" but was ").append(value2).toString());
        }
        return ((zzaon)this.bp()).aR();
    }
    
    @Override
    public void skipValue() throws IOException {
        if (this.bn() == zzapz.boo) {
            this.nextName();
        }
        else {
            this.bp();
        }
    }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
