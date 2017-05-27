// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.util;

public final class zzm
{
    public static String zza(final byte[] array, final int n, final int n2, final boolean b) {
        String string;
        if (array == null || array.length == 0 || n < 0 || n2 <= 0 || n + n2 > array.length) {
            string = null;
        }
        else {
            int n3 = 57;
            if (b) {
                n3 = 75;
            }
            final StringBuilder sb = new StringBuilder(n3 * ((-1 + (n2 + 16)) / 16));
            int n4 = n;
            int i = n2;
            int n5 = 0;
            int n6 = 0;
            while (i > 0) {
                if (n6 == 0) {
                    if (n2 < 65536) {
                        sb.append(String.format("%04X:", n4));
                        n5 = n4;
                    }
                    else {
                        sb.append(String.format("%08X:", n4));
                        n5 = n4;
                    }
                }
                else if (n6 == 8) {
                    sb.append(" -");
                }
                sb.append(String.format(" %02X", 0xFF & array[n4]));
                final int n7 = i - 1;
                final int n8 = n6 + 1;
                if (b && (n8 == 16 || n7 == 0)) {
                    final int n9 = 16 - n8;
                    if (n9 > 0) {
                        for (int j = 0; j < n9; ++j) {
                            sb.append("   ");
                        }
                    }
                    if (n9 >= 8) {
                        sb.append("  ");
                    }
                    sb.append("  ");
                    for (int k = 0; k < n8; ++k) {
                        char c = (char)array[n5 + k];
                        if (c < ' ' || c > '~') {
                            c = '.';
                        }
                        sb.append(c);
                    }
                }
                int n10;
                if (n8 == 16 || n7 == 0) {
                    sb.append('\n');
                    n10 = 0;
                }
                else {
                    n10 = n8;
                }
                ++n4;
                n6 = n10;
                i = n7;
            }
            string = sb.toString();
        }
        return string;
    }
}
