// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.nio.ByteOrder;
import java.nio.ByteBuffer;

public class zzpt
{
    private static long zza(final byte[] array, final int n, final int n2) {
        long n3 = 0L;
        for (int min = Math.min(n2, 8), i = 0; i < min; ++i) {
            n3 |= (0xFFL & array[n + i]) << i * 8;
        }
        return n3;
    }
    
    private static long zza(final byte[] array, final long n) {
        final int n2 = 0xFFFFFFF8 & array.length;
        final int n3 = 0x7 & array.length;
        long n4 = n ^ -4132994306676758123L * array.length;
        long n5;
        for (int i = 0; i < n2; i += 8, n4 = n5) {
            n5 = -4132994306676758123L * (n4 ^ -4132994306676758123L * zzah(-4132994306676758123L * zzb(array, i)));
        }
        if (n3 != 0) {
            n4 = -4132994306676758123L * (n4 ^ zza(array, n2, n3));
        }
        return zzah(-4132994306676758123L * zzah(n4));
    }
    
    private static void zza(final byte[] array, final int n, final long n2, final long n3, final long[] array2) {
        final long zzb = zzb(array, n);
        final long zzb2 = zzb(array, n + 8);
        final long zzb3 = zzb(array, n + 16);
        final long zzb4 = zzb(array, n + 24);
        final long n4 = zzb + n2;
        final long rotateRight = Long.rotateRight(zzb4 + (n3 + n4), 51);
        final long n5 = zzb3 + (zzb2 + n4);
        final long n6 = rotateRight + Long.rotateRight(n5, 23);
        array2[0] = n5 + zzb4;
        array2[1] = n4 + n6;
    }
    
    private static long zzah(final long n) {
        return n ^ n >>> 47;
    }
    
    private static long zzb(final byte[] array, final int n) {
        final ByteBuffer wrap = ByteBuffer.wrap(array, n, 8);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        return wrap.getLong();
    }
    
    private static long zzc(final long n, final long n2) {
        final long n3 = -4132994306676758123L * (n2 ^ n);
        final long n4 = -4132994306676758123L * (n ^ (n3 ^ n3 >>> 47));
        return -4132994306676758123L * (n4 ^ n4 >>> 47);
    }
    
    public static long zzm(final byte[] array) {
        long zzb = -6505348102511208375L;
        long n;
        if (array.length <= 32) {
            n = zza(array, -1397348546323613475L);
        }
        else if (array.length <= 64) {
            n = zzn(array);
        }
        else {
            n = zzo(array);
        }
        long zzb2;
        if (array.length >= 8) {
            zzb2 = zzb(array, 0);
        }
        else {
            zzb2 = zzb;
        }
        if (array.length >= 9) {
            zzb = zzb(array, -8 + array.length);
        }
        long zzc = zzc(n + zzb, zzb2);
        if (zzc == 0L || zzc == 1L) {
            zzc -= 2L;
        }
        return zzc;
    }
    
    private static long zzn(final byte[] array) {
        final int length = array.length;
        final long zzb = zzb(array, 24);
        final long n = zzb(array, 0) + -6505348102511208375L * (length + zzb(array, length - 16));
        final long rotateRight = Long.rotateRight(n + zzb, 52);
        final long rotateRight2 = Long.rotateRight(n, 37);
        final long n2 = n + zzb(array, 8);
        final long n3 = rotateRight2 + Long.rotateRight(n2, 7);
        final long n4 = n2 + zzb(array, 16);
        final long n5 = zzb + n4;
        final long n6 = n3 + (rotateRight + Long.rotateRight(n4, 31));
        final long n7 = zzb(array, 16) + zzb(array, length - 32);
        final long zzb2 = zzb(array, length - 8);
        final long rotateRight3 = Long.rotateRight(n7 + zzb2, 52);
        final long rotateRight4 = Long.rotateRight(n7, 37);
        final long n8 = n7 + zzb(array, length - 24);
        final long n9 = rotateRight4 + Long.rotateRight(n8, 7);
        final long n10 = n8 + zzb(array, length - 16);
        return -4288712594273399085L * zzah(n6 + -6505348102511208375L * zzah(-4288712594273399085L * (n5 + (n9 + (rotateRight3 + Long.rotateRight(n10, 31)))) + -6505348102511208375L * (n10 + zzb2 + n6)));
    }
    
    private static long zzo(final byte[] array) {
        final int length = array.length;
        final long zzb = zzb(array, 0);
        final long n = 0x8D58AC26AFE12E47L ^ zzb(array, length - 16);
        final long n2 = 0xA5B85C5E198ED849L ^ zzb(array, length - 56);
        final long[] array2 = new long[2];
        final long[] array3 = new long[2];
        zza(array, length - 64, length, n, array2);
        zza(array, length - 32, -8261664234251669945L * length, -6505348102511208375L, array3);
        long n3 = n2 + -8261664234251669945L * zzah(array2[1]);
        final long n4 = -8261664234251669945L * Long.rotateRight(n3 + zzb, 39);
        long n5 = -8261664234251669945L * Long.rotateRight(n, 33);
        final int n6 = 0xFFFFFFC0 & length - 1;
        int n7 = 0;
        long n8 = n4;
        int n9 = n6;
        long n12;
        long rotateRight;
        while (true) {
            final long n10 = -8261664234251669945L * Long.rotateRight(n8 + n5 + array2[0] + zzb(array, n7 + 16), 37);
            final long n11 = -8261664234251669945L * Long.rotateRight(n5 + array2[1] + zzb(array, n7 + 48), 42);
            n12 = (n10 ^ array3[1]);
            n5 = (n11 ^ array2[0]);
            rotateRight = Long.rotateRight(n3 ^ array3[0], 33);
            zza(array, n7, -8261664234251669945L * array2[1], n12 + array3[0], array2);
            zza(array, n7 + 32, rotateRight + array3[1], n5, array3);
            n7 += 64;
            final int n13 = n9 - 64;
            if (n13 == 0) {
                break;
            }
            n3 = n12;
            n8 = rotateRight;
            n9 = n13;
        }
        return zzc(n12 + (zzc(array2[0], array3[0]) + -8261664234251669945L * zzah(n5)), rotateRight + zzc(array2[1], array3[1]));
    }
}
