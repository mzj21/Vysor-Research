// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.math.BigInteger;

public final class zzaon extends zzaoh
{
    private static final Class<?>[] blf;
    private Object value;
    
    static {
        blf = new Class[] { Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class };
    }
    
    public zzaon(final Boolean value) {
        this.setValue(value);
    }
    
    public zzaon(final Number value) {
        this.setValue(value);
    }
    
    zzaon(final Object value) {
        this.setValue(value);
    }
    
    public zzaon(final String value) {
        this.setValue(value);
    }
    
    private static boolean zza(final zzaon zzaon) {
        boolean b;
        if (zzaon.value instanceof Number) {
            final Number n = (Number)zzaon.value;
            b = (n instanceof BigInteger || n instanceof Long || n instanceof Integer || n instanceof Short || n instanceof Byte);
        }
        else {
            b = false;
        }
        return b;
    }
    
    private static boolean zzcn(final Object o) {
        boolean b = true;
        if (!(o instanceof String)) {
            final Class<?> class1 = o.getClass();
            final Class<?>[] blf = zzaon.blf;
            for (int length = blf.length, i = 0; i < length; ++i) {
                if (blf[i].isAssignableFrom(class1)) {
                    return b;
                }
            }
            b = false;
        }
        return b;
    }
    
    @Override
    public Number aQ() {
        Number n;
        if (this.value instanceof String) {
            n = new zzape((String)this.value);
        }
        else {
            n = (Number)this.value;
        }
        return n;
    }
    
    @Override
    public String aR() {
        String s;
        if (this.bb()) {
            s = this.aQ().toString();
        }
        else if (this.ba()) {
            s = this.aZ().toString();
        }
        else {
            s = (String)this.value;
        }
        return s;
    }
    
    @Override
    Boolean aZ() {
        return (Boolean)this.value;
    }
    
    public boolean ba() {
        return this.value instanceof Boolean;
    }
    
    public boolean bb() {
        return this.value instanceof Number;
    }
    
    public boolean bc() {
        return this.value instanceof String;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean equals = true;
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                equals = false;
            }
            else {
                final zzaon zzaon = (zzaon)o;
                if (this.value == null) {
                    if (zzaon.value != null) {
                        equals = false;
                    }
                }
                else if (zza(this) && zza(zzaon)) {
                    if (this.aQ().longValue() != zzaon.aQ().longValue()) {
                        equals = false;
                    }
                }
                else if (this.value instanceof Number && zzaon.value instanceof Number) {
                    final double doubleValue = this.aQ().doubleValue();
                    final double doubleValue2 = zzaon.aQ().doubleValue();
                    boolean b = false;
                    Label_0169: {
                        if (doubleValue != doubleValue2) {
                            final boolean naN = Double.isNaN(doubleValue);
                            b = false;
                            if (!naN) {
                                break Label_0169;
                            }
                            final boolean naN2 = Double.isNaN(doubleValue2);
                            b = false;
                            if (!naN2) {
                                break Label_0169;
                            }
                        }
                        b = equals;
                    }
                    equals = b;
                }
                else {
                    equals = this.value.equals(zzaon.value);
                }
            }
        }
        return equals;
    }
    
    @Override
    public boolean getAsBoolean() {
        boolean b;
        if (this.ba()) {
            b = this.aZ();
        }
        else {
            b = Boolean.parseBoolean(this.aR());
        }
        return b;
    }
    
    @Override
    public double getAsDouble() {
        double n;
        if (this.bb()) {
            n = this.aQ().doubleValue();
        }
        else {
            n = Double.parseDouble(this.aR());
        }
        return n;
    }
    
    @Override
    public int getAsInt() {
        int n;
        if (this.bb()) {
            n = this.aQ().intValue();
        }
        else {
            n = Integer.parseInt(this.aR());
        }
        return n;
    }
    
    @Override
    public long getAsLong() {
        long n;
        if (this.bb()) {
            n = this.aQ().longValue();
        }
        else {
            n = Long.parseLong(this.aR());
        }
        return n;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.value == null) {
            hashCode = 31;
        }
        else if (zza(this)) {
            final long longValue = this.aQ().longValue();
            hashCode = (int)(longValue ^ longValue >>> 32);
        }
        else if (this.value instanceof Number) {
            final long doubleToLongBits = Double.doubleToLongBits(this.aQ().doubleValue());
            hashCode = (int)(doubleToLongBits ^ doubleToLongBits >>> 32);
        }
        else {
            hashCode = this.value.hashCode();
        }
        return hashCode;
    }
    
    void setValue(final Object value) {
        if (value instanceof Character) {
            this.value = String.valueOf((char)value);
        }
        else {
            zzaoz.zzbs(value instanceof Number || zzcn(value));
            this.value = value;
        }
    }
}
