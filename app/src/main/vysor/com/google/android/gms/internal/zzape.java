// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.math.BigDecimal;

public final class zzape extends Number
{
    private final String value;
    
    public zzape(final String value) {
        this.value = value;
    }
    
    @Override
    public double doubleValue() {
        return Double.parseDouble(this.value);
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b;
        if (this == o) {
            b = true;
        }
        else {
            final boolean b2 = o instanceof zzape;
            b = false;
            if (b2) {
                final zzape zzape = (zzape)o;
                if (this.value != zzape.value) {
                    final boolean equals = this.value.equals(zzape.value);
                    b = false;
                    if (!equals) {
                        return b;
                    }
                }
                b = true;
            }
        }
        return b;
    }
    
    @Override
    public float floatValue() {
        return Float.parseFloat(this.value);
    }
    
    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
    
    @Override
    public int intValue() {
        try {
            return Integer.parseInt(this.value);
        }
        catch (NumberFormatException ex) {
            try {
                final int n = (int)Long.parseLong(this.value);
            }
            catch (NumberFormatException ex2) {
                final int n = new BigDecimal(this.value).intValue();
            }
        }
    }
    
    @Override
    public long longValue() {
        try {
            return Long.parseLong(this.value);
        }
        catch (NumberFormatException ex) {
            return new BigDecimal(this.value).longValue();
        }
    }
    
    @Override
    public String toString() {
        return this.value;
    }
}
