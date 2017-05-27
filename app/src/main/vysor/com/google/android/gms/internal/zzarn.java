// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public final class zzarn
{
    public static final int[] bqF;
    public static final long[] bqG;
    public static final float[] bqH;
    public static final double[] bqI;
    public static final boolean[] bqJ;
    public static final String[] bqK;
    public static final byte[][] bqL;
    public static final byte[] bqM;
    
    static {
        bqF = new int[0];
        bqG = new long[0];
        bqH = new float[0];
        bqI = new double[0];
        bqJ = new boolean[0];
        bqK = new String[0];
        bqL = new byte[0][];
        bqM = new byte[0];
    }
    
    static int zzaht(final int n) {
        return n & 0x7;
    }
    
    public static int zzahu(final int n) {
        return n >>> 3;
    }
    
    public static int zzaj(final int n, final int n2) {
        return n2 | n << 3;
    }
    
    public static boolean zzb(final zzarc zzarc, final int n) throws IOException {
        return zzarc.zzaha(n);
    }
    
    public static final int zzc(final zzarc zzarc, final int n) throws IOException {
        int n2 = 1;
        final int position = zzarc.getPosition();
        zzarc.zzaha(n);
        while (zzarc.cw() == n) {
            zzarc.zzaha(n);
            ++n2;
        }
        zzarc.zzahe(position);
        return n2;
    }
}
