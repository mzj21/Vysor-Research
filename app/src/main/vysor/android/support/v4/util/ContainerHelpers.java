// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.util;

class ContainerHelpers
{
    static final int[] EMPTY_INTS;
    static final long[] EMPTY_LONGS;
    static final Object[] EMPTY_OBJECTS;
    
    static {
        EMPTY_INTS = new int[0];
        EMPTY_LONGS = new long[0];
        EMPTY_OBJECTS = new Object[0];
    }
    
    static int binarySearch(final int[] array, final int n, final int n2) {
        int i = 0;
        int n3 = n - 1;
        while (i <= n3) {
            final int n4 = i + n3 >>> 1;
            final int n5 = array[n4];
            if (n5 < n2) {
                i = n4 + 1;
            }
            else {
                if (n5 <= n2) {
                    return n4;
                }
                n3 = n4 - 1;
            }
        }
        return ~i;
    }
    
    static int binarySearch(final long[] array, final int n, final long n2) {
        int i = 0;
        int n3 = n - 1;
        while (i <= n3) {
            final int n4 = i + n3 >>> 1;
            final long n5 = array[n4];
            if (n5 < n2) {
                i = n4 + 1;
            }
            else {
                if (n5 <= n2) {
                    return n4;
                }
                n3 = n4 - 1;
            }
        }
        return ~i;
    }
    
    public static boolean equal(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    public static int idealByteArraySize(int n) {
        for (int i = 4; i < 32; ++i) {
            if (n <= -12 + (1 << i)) {
                n = -12 + (1 << i);
                break;
            }
        }
        return n;
    }
    
    public static int idealIntArraySize(final int n) {
        return idealByteArraySize(n * 4) / 4;
    }
    
    public static int idealLongArraySize(final int n) {
        return idealByteArraySize(n * 8) / 8;
    }
}
