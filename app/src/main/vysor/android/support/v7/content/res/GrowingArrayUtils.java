// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.content.res;

import java.lang.reflect.Array;

final class GrowingArrayUtils
{
    public static int[] append(int[] array, final int n, final int n2) {
        assert n <= array.length;
        if (n + 1 > array.length) {
            final int[] array2 = new int[growSize(n)];
            System.arraycopy(array, 0, array2, 0, n);
            array = array2;
        }
        array[n] = n2;
        return array;
    }
    
    public static long[] append(long[] array, final int n, final long n2) {
        assert n <= array.length;
        if (n + 1 > array.length) {
            final long[] array2 = new long[growSize(n)];
            System.arraycopy(array, 0, array2, 0, n);
            array = array2;
        }
        array[n] = n2;
        return array;
    }
    
    public static <T> T[] append(T[] array, final int n, final T t) {
        assert n <= array.length;
        if (n + 1 > array.length) {
            final Object[] array2 = (Object[])Array.newInstance(array.getClass().getComponentType(), growSize(n));
            System.arraycopy(array, 0, array2, 0, n);
            array = (T[])array2;
        }
        array[n] = t;
        return array;
    }
    
    public static boolean[] append(boolean[] array, final int n, final boolean b) {
        assert n <= array.length;
        if (n + 1 > array.length) {
            final boolean[] array2 = new boolean[growSize(n)];
            System.arraycopy(array, 0, array2, 0, n);
            array = array2;
        }
        array[n] = b;
        return array;
    }
    
    public static int growSize(final int n) {
        int n2;
        if (n <= 4) {
            n2 = 8;
        }
        else {
            n2 = n * 2;
        }
        return n2;
    }
    
    public static int[] insert(int[] array, final int n, final int n2, final int n3) {
        assert n <= array.length;
        if (n + 1 <= array.length) {
            System.arraycopy(array, n2, array, n2 + 1, n - n2);
            array[n2] = n3;
        }
        else {
            final int[] array2 = new int[growSize(n)];
            System.arraycopy(array, 0, array2, 0, n2);
            array2[n2] = n3;
            System.arraycopy(array, n2, array2, n2 + 1, array.length - n2);
            array = array2;
        }
        return array;
    }
    
    public static long[] insert(long[] array, final int n, final int n2, final long n3) {
        assert n <= array.length;
        if (n + 1 <= array.length) {
            System.arraycopy(array, n2, array, n2 + 1, n - n2);
            array[n2] = n3;
        }
        else {
            final long[] array2 = new long[growSize(n)];
            System.arraycopy(array, 0, array2, 0, n2);
            array2[n2] = n3;
            System.arraycopy(array, n2, array2, n2 + 1, array.length - n2);
            array = array2;
        }
        return array;
    }
    
    public static <T> T[] insert(T[] array, final int n, final int n2, final T t) {
        assert n <= array.length;
        if (n + 1 <= array.length) {
            System.arraycopy(array, n2, array, n2 + 1, n - n2);
            array[n2] = t;
        }
        else {
            final Object[] array2 = (Object[])Array.newInstance(array.getClass().getComponentType(), growSize(n));
            System.arraycopy(array, 0, array2, 0, n2);
            array2[n2] = t;
            System.arraycopy(array, n2, array2, n2 + 1, array.length - n2);
            array = (T[])array2;
        }
        return array;
    }
    
    public static boolean[] insert(boolean[] array, final int n, final int n2, final boolean b) {
        assert n <= array.length;
        if (n + 1 <= array.length) {
            System.arraycopy(array, n2, array, n2 + 1, n - n2);
            array[n2] = b;
        }
        else {
            final boolean[] array2 = new boolean[growSize(n)];
            System.arraycopy(array, 0, array2, 0, n2);
            array2[n2] = b;
            System.arraycopy(array, n2, array2, n2 + 1, array.length - n2);
            array = array2;
        }
        return array;
    }
}
