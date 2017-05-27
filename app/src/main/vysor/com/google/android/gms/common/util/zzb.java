// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import com.google.android.gms.common.internal.zzab;

public final class zzb
{
    public static <T> int zza(final T[] array, final T t) {
        int i = 0;
        int length;
        if (array != null) {
            length = array.length;
        }
        else {
            length = 0;
            i = 0;
        }
        while (i < length) {
            if (zzab.equal(array[i], t)) {
                return i;
            }
            ++i;
        }
        return -1;
    }
    
    public static void zza(final StringBuilder sb, final double[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(Double.toString(array[i]));
        }
    }
    
    public static void zza(final StringBuilder sb, final float[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(Float.toString(array[i]));
        }
    }
    
    public static void zza(final StringBuilder sb, final int[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(Integer.toString(array[i]));
        }
    }
    
    public static void zza(final StringBuilder sb, final long[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(Long.toString(array[i]));
        }
    }
    
    public static <T> void zza(final StringBuilder sb, final T[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(array[i].toString());
        }
    }
    
    public static void zza(final StringBuilder sb, final String[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append("\"").append(array[i]).append("\"");
        }
    }
    
    public static void zza(final StringBuilder sb, final boolean[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(Boolean.toString(array[i]));
        }
    }
    
    public static byte[] zza(final byte[]... array) {
        byte[] array2;
        if (array.length == 0) {
            array2 = new byte[0];
        }
        else {
            int i = 0;
            int n = 0;
            while (i < array.length) {
                n += array[i].length;
                ++i;
            }
            final byte[] copy = Arrays.copyOf(array[0], n);
            int length = array[0].length;
            for (int j = 1; j < array.length; ++j) {
                final byte[] array3 = array[j];
                System.arraycopy(array3, 0, copy, length, array3.length);
                length += array3.length;
            }
            array2 = copy;
        }
        return array2;
    }
    
    public static Integer[] zza(final int[] array) {
        Integer[] array2;
        if (array == null) {
            array2 = null;
        }
        else {
            final int length = array.length;
            array2 = new Integer[length];
            for (int i = 0; i < length; ++i) {
                array2[i] = array[i];
            }
        }
        return array2;
    }
    
    public static <T> ArrayList<T> zzaxg() {
        return new ArrayList<T>();
    }
    
    public static <T> ArrayList<T> zzb(final T[] array) {
        final int length = array.length;
        final ArrayList list = new ArrayList<T>(length);
        for (int i = 0; i < length; ++i) {
            list.add(array[i]);
        }
        return (ArrayList<T>)list;
    }
    
    public static <T> boolean zzb(final T[] array, final T t) {
        return zza(array, t) >= 0;
    }
}
