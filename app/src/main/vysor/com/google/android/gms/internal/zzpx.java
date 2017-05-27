// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public class zzpx
{
    public static long zzd(final long n, final long n2) {
        long n3;
        if (n >= 0L) {
            n3 = n % n2;
        }
        else {
            n3 = (1L + Long.MAX_VALUE % n2 + (n & Long.MAX_VALUE) % n2) % n2;
        }
        return n3;
    }
}
