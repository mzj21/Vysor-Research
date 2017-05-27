// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.util;

import java.io.PrintWriter;

public final class TimeUtils
{
    public static final int HUNDRED_DAY_FIELD_LEN = 19;
    private static final int SECONDS_PER_DAY = 86400;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_MINUTE = 60;
    private static char[] sFormatStr;
    private static final Object sFormatSync;
    
    static {
        sFormatSync = new Object();
        TimeUtils.sFormatStr = new char[24];
    }
    
    private static int accumField(final int n, final int n2, final boolean b, final int n3) {
        int n4;
        if (n > 99 || (b && n3 >= 3)) {
            n4 = n2 + 3;
        }
        else if (n > 9 || (b && n3 >= 2)) {
            n4 = n2 + 2;
        }
        else if (b || n > 0) {
            n4 = n2 + 1;
        }
        else {
            n4 = 0;
        }
        return n4;
    }
    
    public static void formatDuration(final long n, final long n2, final PrintWriter printWriter) {
        if (n == 0L) {
            printWriter.print("--");
        }
        else {
            formatDuration(n - n2, printWriter, 0);
        }
    }
    
    public static void formatDuration(final long n, final PrintWriter printWriter) {
        formatDuration(n, printWriter, 0);
    }
    
    public static void formatDuration(final long n, final PrintWriter printWriter, final int n2) {
        synchronized (TimeUtils.sFormatSync) {
            printWriter.print(new String(TimeUtils.sFormatStr, 0, formatDurationLocked(n, n2)));
        }
    }
    
    public static void formatDuration(final long n, final StringBuilder sb) {
        synchronized (TimeUtils.sFormatSync) {
            sb.append(TimeUtils.sFormatStr, 0, formatDurationLocked(n, 0));
        }
    }
    
    private static int formatDurationLocked(long n, final int n2) {
        if (TimeUtils.sFormatStr.length < n2) {
            TimeUtils.sFormatStr = new char[n2];
        }
        final char[] sFormatStr = TimeUtils.sFormatStr;
        int n3;
        if (n == 0L) {
            while (n2 - 1 < 0) {
                sFormatStr[0] = ' ';
            }
            sFormatStr[0] = '0';
            n3 = 1;
        }
        else {
            char c;
            if (n > 0L) {
                c = '+';
            }
            else {
                c = '-';
                n = -n;
            }
            final int n4 = (int)(n % 1000L);
            final int n6;
            int n5 = n6 = (int)Math.floor(n / 1000L);
            int n7 = 0;
            if (n6 > 86400) {
                n7 = n5 / 86400;
                n5 -= 86400 * n7;
            }
            final int n8 = n5;
            int n9 = 0;
            if (n8 > 3600) {
                n9 = n5 / 3600;
                n5 -= n9 * 3600;
            }
            final int n10 = n5;
            int n11 = 0;
            if (n10 > 60) {
                n11 = n5 / 60;
                n5 -= n11 * 60;
            }
            int n12 = 0;
            if (n2 != 0) {
                final int accumField = accumField(n7, 1, false, 0);
                final int n13 = accumField + accumField(n9, 1, accumField > 0, 2);
                final int n14 = n13 + accumField(n11, 1, n13 > 0, 2);
                final int n15 = n14 + accumField(n5, 1, n14 > 0, 2);
                int n16;
                if (n15 > 0) {
                    n16 = 3;
                }
                else {
                    n16 = 0;
                }
                for (int i = n15 + (1 + accumField(n4, 2, true, n16)); i < n2; ++i) {
                    sFormatStr[n12] = ' ';
                    ++n12;
                }
            }
            sFormatStr[n12] = c;
            final int n17 = n12 + 1;
            boolean b;
            if (n2 != 0) {
                b = true;
            }
            else {
                b = false;
            }
            final int printField = printField(sFormatStr, n7, 'd', n17, false, 0);
            final boolean b2 = printField != n17;
            int n18;
            if (b) {
                n18 = 2;
            }
            else {
                n18 = 0;
            }
            final int printField2 = printField(sFormatStr, n9, 'h', printField, b2, n18);
            final boolean b3 = printField2 != n17;
            int n19;
            if (b) {
                n19 = 2;
            }
            else {
                n19 = 0;
            }
            final int printField3 = printField(sFormatStr, n11, 'm', printField2, b3, n19);
            final boolean b4 = printField3 != n17;
            int n20;
            if (b) {
                n20 = 2;
            }
            else {
                n20 = 0;
            }
            final int printField4 = printField(sFormatStr, n5, 's', printField3, b4, n20);
            int n21;
            if (b && printField4 != n17) {
                n21 = 3;
            }
            else {
                n21 = 0;
            }
            final int printField5 = printField(sFormatStr, n4, 'm', printField4, true, n21);
            sFormatStr[printField5] = 's';
            n3 = printField5 + 1;
        }
        return n3;
    }
    
    private static int printField(final char[] array, int n, final char c, int n2, final boolean b, final int n3) {
        if (b || n > 0) {
            final int n4 = n2;
            if ((b && n3 >= 3) || n > 99) {
                final int n5 = n / 100;
                array[n2] = (char)(n5 + 48);
                ++n2;
                n -= n5 * 100;
            }
            if ((b && n3 >= 2) || n > 9 || n4 != n2) {
                final int n6 = n / 10;
                array[n2] = (char)(n6 + 48);
                ++n2;
                n -= n6 * 10;
            }
            array[n2] = (char)(n + 48);
            final int n7 = n2 + 1;
            array[n7] = c;
            n2 = n7 + 1;
        }
        return n2;
    }
}
