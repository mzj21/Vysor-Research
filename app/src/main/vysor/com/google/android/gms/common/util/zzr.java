// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.util;

public class zzr
{
    public static int zza(final byte[] array, int i, final int n, final int n2) {
        final int n3 = i + (n & 0xFFFFFFFC);
        int n4 = n2;
        while (i < n3) {
            final int n5 = -862048943 * ((0xFF & array[i]) | (0xFF & array[i + 1]) << 8 | (0xFF & array[i + 2]) << 16 | array[i + 3] << 24);
            final int n6 = n4 ^ 461845907 * (n5 << 15 | n5 >>> 17);
            n4 = -430675100 + 5 * (n6 << 13 | n6 >>> 19);
            i += 4;
        }
        final int n7 = n & 0x3;
        int n8 = 0;
        int n9 = 0;
        switch (n7) {
            default: {
                n9 = n4;
                break;
            }
            case 3: {
                n8 = (0xFF & array[n3 + 2]) << 16;
            }
            case 2: {
                n8 |= (0xFF & array[n3 + 1]) << 8;
            }
            case 1: {
                final int n10 = -862048943 * (n8 | (0xFF & array[n3]));
                n9 = (n4 ^ 461845907 * (n10 << 15 | n10 >>> 17));
                break;
            }
        }
        final int n11 = n9 ^ n;
        final int n12 = -2048144789 * (n11 ^ n11 >>> 16);
        final int n13 = -1028477387 * (n12 ^ n12 >>> 13);
        return n13 ^ n13 >>> 16;
    }
}
