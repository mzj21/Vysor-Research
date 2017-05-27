// 
// Decompiled by Procyon v0.5.30
// 

package android.os;

public class SystemProperties {
    public static final int PROP_NAME_MAX = 31;
    public static final int PROP_VALUE_MAX = 91;

    public static String get(final String s) {
        if (s.length() > 31) {
            throw new IllegalArgumentException("key.length > 31");
        }
        return native_get(s);
    }

    public static String get(final String s, final String s2) {
        if (s.length() > 31) {
            throw new IllegalArgumentException("key.length > 31");
        }
        return native_get(s, s2);
    }

    public static boolean getBoolean(final String s, final boolean b) {
        if (s.length() > 31) {
            throw new IllegalArgumentException("key.length > 31");
        }
        return native_get_boolean(s, b);
    }

    public static int getInt(final String s, final int n) {
        if (s.length() > 31) {
            throw new IllegalArgumentException("key.length > 31");
        }
        return native_get_int(s, n);
    }

    public static long getLong(final String s, final long n) {
        if (s.length() > 31) {
            throw new IllegalArgumentException("key.length > 31");
        }
        return native_get_long(s, n);
    }

    private static native String native_get(final String p0);

    private static native String native_get(final String p0, final String p1);

    private static native boolean native_get_boolean(final String p0, final boolean p1);

    private static native int native_get_int(final String p0, final int p1);

    private static native long native_get_long(final String p0, final long p1);

    private static native void native_set(final String p0, final String p1);

    public static void set(final String s, final String s2) {
        if (s.length() > 31) {
            throw new IllegalArgumentException("key.length > 31");
        }
        if (s2 != null && s2.length() > 91) {
            throw new IllegalArgumentException("val.length > 91");
        }
        native_set(s, s2);
    }
}
