// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

public final class Size
{
    private final int zzajw;
    private final int zzajx;
    
    public Size(final int zzajw, final int zzajx) {
        this.zzajw = zzajw;
        this.zzajx = zzajx;
    }
    
    public static Size parseSize(final String s) throws NumberFormatException {
        if (s == null) {
            throw new IllegalArgumentException("string must not be null");
        }
        int n = s.indexOf(42);
        if (n < 0) {
            n = s.indexOf(120);
        }
        if (n < 0) {
            throw zzhp(s);
        }
        try {
            return new Size(Integer.parseInt(s.substring(0, n)), Integer.parseInt(s.substring(n + 1)));
        }
        catch (NumberFormatException ex) {
            throw zzhp(s);
        }
    }
    
    private static NumberFormatException zzhp(final String s) {
        throw new NumberFormatException(new StringBuilder(16 + String.valueOf(s).length()).append("Invalid Size: \"").append(s).append("\"").toString());
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        boolean b2 = false;
        if (o != null) {
            if (this == o) {
                b2 = b;
            }
            else {
                final boolean b3 = o instanceof Size;
                b2 = false;
                if (b3) {
                    final Size size = (Size)o;
                    if (this.zzajw != size.zzajw || this.zzajx != size.zzajx) {
                        b = false;
                    }
                    b2 = b;
                }
            }
        }
        return b2;
    }
    
    public int getHeight() {
        return this.zzajx;
    }
    
    public int getWidth() {
        return this.zzajw;
    }
    
    @Override
    public int hashCode() {
        return this.zzajx ^ (this.zzajw << 16 | this.zzajw >>> 16);
    }
    
    @Override
    public String toString() {
        return new StringBuilder(23).append(this.zzajw).append("x").append(this.zzajx).toString();
    }
}
