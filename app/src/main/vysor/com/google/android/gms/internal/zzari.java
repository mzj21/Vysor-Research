// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Arrays;
import java.nio.charset.Charset;

public final class zzari
{
    protected static final Charset ISO_8859_1;
    protected static final Charset UTF_8;
    public static final Object bqD;
    
    static {
        UTF_8 = Charset.forName("UTF-8");
        ISO_8859_1 = Charset.forName("ISO-8859-1");
        bqD = new Object();
    }
    
    public static boolean equals(final float[] array, final float[] array2) {
        boolean equals;
        if (array == null || array.length == 0) {
            equals = (array2 == null || array2.length == 0);
        }
        else {
            equals = Arrays.equals(array, array2);
        }
        return equals;
    }
    
    public static boolean equals(final int[] array, final int[] array2) {
        boolean equals;
        if (array == null || array.length == 0) {
            equals = (array2 == null || array2.length == 0);
        }
        else {
            equals = Arrays.equals(array, array2);
        }
        return equals;
    }
    
    public static boolean equals(final long[] array, final long[] array2) {
        boolean equals;
        if (array == null || array.length == 0) {
            equals = (array2 == null || array2.length == 0);
        }
        else {
            equals = Arrays.equals(array, array2);
        }
        return equals;
    }
    
    public static boolean equals(final Object[] array, final Object[] array2) {
        int length;
        if (array == null) {
            length = 0;
        }
        else {
            length = array.length;
        }
        int length2;
        if (array2 == null) {
            length2 = 0;
        }
        else {
            length2 = array2.length;
        }
        int n = 0;
        int n2 = 0;
        boolean b3;
        while (true) {
            if (n2 < length && array[n2] == null) {
                ++n2;
            }
            else {
                int n3;
                for (n3 = n; n3 < length2 && array2[n3] == null; ++n3) {}
                boolean b;
                if (n2 >= length) {
                    b = true;
                }
                else {
                    b = false;
                }
                boolean b2;
                if (n3 >= length2) {
                    b2 = true;
                }
                else {
                    b2 = false;
                }
                if (b && b2) {
                    b3 = true;
                    break;
                }
                b3 = false;
                if (b != b2) {
                    break;
                }
                final boolean equals = array[n2].equals(array2[n3]);
                b3 = false;
                if (!equals) {
                    break;
                }
                final int n4 = n2 + 1;
                n = n3 + 1;
                n2 = n4;
            }
        }
        return b3;
    }
    
    public static int hashCode(final float[] array) {
        int hashCode;
        if (array == null || array.length == 0) {
            hashCode = 0;
        }
        else {
            hashCode = Arrays.hashCode(array);
        }
        return hashCode;
    }
    
    public static int hashCode(final int[] array) {
        int hashCode;
        if (array == null || array.length == 0) {
            hashCode = 0;
        }
        else {
            hashCode = Arrays.hashCode(array);
        }
        return hashCode;
    }
    
    public static int hashCode(final long[] array) {
        int hashCode;
        if (array == null || array.length == 0) {
            hashCode = 0;
        }
        else {
            hashCode = Arrays.hashCode(array);
        }
        return hashCode;
    }
    
    public static int hashCode(final Object[] array) {
        int n = 0;
        if (array == null) {
            final int length = 0;
        }
        else {
            final int length = array.length;
        }
        for (final Object o : array) {
            if (o != null) {
                n = n * 31 + o.hashCode();
            }
        }
        return n;
    }
    
    public static void zza(final zzare zzare, final zzare zzare2) {
        if (zzare.bqv != null) {
            zzare2.bqv = (zzarg)zzare.bqv.clone();
        }
    }
    
    public static boolean zza(final byte[][] array, final byte[][] array2) {
        int length;
        if (array == null) {
            length = 0;
        }
        else {
            length = array.length;
        }
        int length2;
        if (array2 == null) {
            length2 = 0;
        }
        else {
            length2 = array2.length;
        }
        int n = 0;
        int n2 = 0;
        boolean b3;
        while (true) {
            if (n2 < length && array[n2] == null) {
                ++n2;
            }
            else {
                int n3;
                for (n3 = n; n3 < length2 && array2[n3] == null; ++n3) {}
                boolean b;
                if (n2 >= length) {
                    b = true;
                }
                else {
                    b = false;
                }
                boolean b2;
                if (n3 >= length2) {
                    b2 = true;
                }
                else {
                    b2 = false;
                }
                if (b && b2) {
                    b3 = true;
                    break;
                }
                b3 = false;
                if (b != b2) {
                    break;
                }
                final boolean equals = Arrays.equals(array[n2], array2[n3]);
                b3 = false;
                if (!equals) {
                    break;
                }
                final int n4 = n2 + 1;
                n = n3 + 1;
                n2 = n4;
            }
        }
        return b3;
    }
    
    public static int zzb(final byte[][] array) {
        int n = 0;
        if (array == null) {
            final int length = 0;
        }
        else {
            final int length = array.length;
        }
        for (final byte[] array2 : array) {
            if (array2 != null) {
                n = n * 31 + Arrays.hashCode(array2);
            }
        }
        return n;
    }
}
