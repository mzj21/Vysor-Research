// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ReadOnlyBufferException;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;

public final class zzard
{
    private final ByteBuffer bqu;
    
    private zzard(final ByteBuffer bqu) {
        (this.bqu = bqu).order(ByteOrder.LITTLE_ENDIAN);
    }
    
    private zzard(final byte[] array, final int n, final int n2) {
        this(ByteBuffer.wrap(array, n, n2));
    }
    
    private static int zza(final CharSequence charSequence, final int n) {
        final int length = charSequence.length();
        char c = '\0';
        for (int i = n; i < length; ++i) {
            final char char1 = charSequence.charAt(i);
            if (char1 < '\u0800') {
                c += (char)('\u007f' - char1 >>> 31);
            }
            else {
                c += '\u0002';
                if ('\ud800' <= char1 && char1 <= '\udfff') {
                    if (Character.codePointAt(charSequence, i) < 65536) {
                        throw new IllegalArgumentException(new StringBuilder(39).append("Unpaired surrogate at index ").append(i).toString());
                    }
                    ++i;
                }
            }
        }
        return c;
    }
    
    private static int zza(final CharSequence charSequence, final byte[] array, final int n, final int n2) {
        int length;
        int i;
        int n3;
        for (length = charSequence.length(), i = 0, n3 = n + n2; i < length && i + n < n3; ++i) {
            final char char1 = charSequence.charAt(i);
            if (char1 >= '\u0080') {
                break;
            }
            array[n + i] = (byte)char1;
        }
        int n4;
        if (i == length) {
            n4 = n + length;
        }
        else {
            int n5 = n + i;
            while (i < length) {
                final char char2 = charSequence.charAt(i);
                int n6 = 0;
                Label_0132: {
                    if (char2 < '\u0080' && n5 < n3) {
                        n6 = n5 + 1;
                        array[n5] = (byte)char2;
                    }
                    else if (char2 < '\u0800' && n5 <= n3 - 2) {
                        final int n7 = n5 + 1;
                        array[n5] = (byte)('\u03c0' | char2 >>> 6);
                        n6 = n7 + 1;
                        array[n7] = (byte)('\u0080' | (char2 & '?'));
                    }
                    else if ((char2 < '\ud800' || '\udfff' < char2) && n5 <= n3 - 3) {
                        final int n8 = n5 + 1;
                        array[n5] = (byte)('\u01e0' | char2 >>> 12);
                        final int n9 = n8 + 1;
                        array[n8] = (byte)('\u0080' | ('?' & char2 >>> 6));
                        n6 = n9 + 1;
                        array[n9] = (byte)('\u0080' | (char2 & '?'));
                    }
                    else {
                        if (n5 <= n3 - 4) {
                            if (i + 1 != charSequence.length()) {
                                ++i;
                                final char char3 = charSequence.charAt(i);
                                if (Character.isSurrogatePair(char2, char3)) {
                                    final int codePoint = Character.toCodePoint(char2, char3);
                                    final int n10 = n5 + 1;
                                    array[n5] = (byte)(0xF0 | codePoint >>> 18);
                                    final int n11 = n10 + 1;
                                    array[n10] = (byte)(0x80 | (0x3F & codePoint >>> 12));
                                    final int n12 = n11 + 1;
                                    array[n11] = (byte)(0x80 | (0x3F & codePoint >>> 6));
                                    n6 = n12 + 1;
                                    array[n12] = (byte)(0x80 | (codePoint & 0x3F));
                                    break Label_0132;
                                }
                            }
                            throw new IllegalArgumentException(new StringBuilder(39).append("Unpaired surrogate at index ").append(i - 1).toString());
                        }
                        throw new ArrayIndexOutOfBoundsException(new StringBuilder(37).append("Failed writing ").append(char2).append(" at index ").append(n5).toString());
                    }
                }
                ++i;
                n5 = n6;
            }
            n4 = n5;
        }
        return n4;
    }
    
    private static void zza(final CharSequence charSequence, final ByteBuffer byteBuffer) {
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        Label_0071: {
            if (!byteBuffer.hasArray()) {
                break Label_0071;
            }
            try {
                byteBuffer.position(zza(charSequence, byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining()) - byteBuffer.arrayOffset());
                return;
            }
            catch (ArrayIndexOutOfBoundsException ex2) {
                final BufferOverflowException ex = new BufferOverflowException();
                ex.initCause(ex2);
                throw ex;
            }
        }
        zzb(charSequence, byteBuffer);
    }
    
    public static int zzag(final int n, final int n2) {
        return zzahl(n) + zzahi(n2);
    }
    
    public static int zzah(final int n, final int n2) {
        return zzahl(n) + zzahj(n2);
    }
    
    public static int zzahi(final int n) {
        int zzahn;
        if (n >= 0) {
            zzahn = zzahn(n);
        }
        else {
            zzahn = 10;
        }
        return zzahn;
    }
    
    public static int zzahj(final int n) {
        return zzahn(zzahp(n));
    }
    
    public static int zzahl(final int n) {
        return zzahn(zzarn.zzaj(n, 0));
    }
    
    public static int zzahn(final int n) {
        int n2;
        if ((n & 0xFFFFFF80) == 0x0) {
            n2 = 1;
        }
        else if ((n & 0xFFFFC000) == 0x0) {
            n2 = 2;
        }
        else if ((0xFFE00000 & n) == 0x0) {
            n2 = 3;
        }
        else if ((0xF0000000 & n) == 0x0) {
            n2 = 4;
        }
        else {
            n2 = 5;
        }
        return n2;
    }
    
    public static int zzahp(final int n) {
        return n << 1 ^ n >> 31;
    }
    
    public static int zzb(final int n, final double n2) {
        return zzahl(n) + zzp(n2);
    }
    
    public static int zzb(final int n, final zzark zzark) {
        return 2 * zzahl(n) + zzd(zzark);
    }
    
    public static int zzb(final int n, final byte[] array) {
        return zzahl(n) + zzbg(array);
    }
    
    private static void zzb(final CharSequence charSequence, final ByteBuffer byteBuffer) {
        for (int length = charSequence.length(), i = 0; i < length; ++i) {
            final char char1 = charSequence.charAt(i);
            if (char1 < '\u0080') {
                byteBuffer.put((byte)char1);
            }
            else if (char1 < '\u0800') {
                byteBuffer.put((byte)('\u03c0' | char1 >>> 6));
                byteBuffer.put((byte)('\u0080' | (char1 & '?')));
            }
            else {
                if (char1 >= '\ud800' && '\udfff' >= char1) {
                    if (i + 1 != charSequence.length()) {
                        ++i;
                        final char char2 = charSequence.charAt(i);
                        if (Character.isSurrogatePair(char1, char2)) {
                            final int codePoint = Character.toCodePoint(char1, char2);
                            byteBuffer.put((byte)(0xF0 | codePoint >>> 18));
                            byteBuffer.put((byte)(0x80 | (0x3F & codePoint >>> 12)));
                            byteBuffer.put((byte)(0x80 | (0x3F & codePoint >>> 6)));
                            byteBuffer.put((byte)(0x80 | (codePoint & 0x3F)));
                            continue;
                        }
                    }
                    throw new IllegalArgumentException(new StringBuilder(39).append("Unpaired surrogate at index ").append(i - 1).toString());
                }
                byteBuffer.put((byte)('\u01e0' | char1 >>> 12));
                byteBuffer.put((byte)('\u0080' | ('?' & char1 >>> 6)));
                byteBuffer.put((byte)('\u0080' | (char1 & '?')));
            }
        }
    }
    
    public static zzard zzbe(final byte[] array) {
        return zzc(array, 0, array.length);
    }
    
    public static int zzbg(final byte[] array) {
        return zzahn(array.length) + array.length;
    }
    
    public static int zzc(final int n, final zzark zzark) {
        return zzahl(n) + zze(zzark);
    }
    
    public static zzard zzc(final byte[] array, final int n, final int n2) {
        return new zzard(array, n, n2);
    }
    
    public static int zzd(final int n, final float n2) {
        return zzahl(n) + zzl(n2);
    }
    
    public static int zzd(final zzark zzark) {
        return zzark.db();
    }
    
    private static int zzd(final CharSequence charSequence) {
        int length;
        char c;
        for (length = charSequence.length(), c = '\0'; c < length && charSequence.charAt(c) < '\u0080'; ++c) {}
        char c2 = c;
        int n = length;
        while (c2 < length) {
            final char char1 = charSequence.charAt(c2);
            if (char1 >= '\u0800') {
                n += zza(charSequence, c2);
                break;
            }
            final char c3 = (char)(n + ('\u007f' - char1 >>> 31));
            ++c2;
            n = c3;
        }
        if (n < length) {
            throw new IllegalArgumentException(new StringBuilder(54).append("UTF-8 length does not fit in int: ").append(4294967296L + n).toString());
        }
        return n;
    }
    
    public static int zzda(final long n) {
        return zzdf(n);
    }
    
    public static int zzdb(final long n) {
        return zzdf(n);
    }
    
    public static int zzdc(final long n) {
        return 8;
    }
    
    public static int zzdd(final long n) {
        return zzdf(zzdh(n));
    }
    
    public static int zzdf(final long n) {
        int n2;
        if ((0xFFFFFFFFFFFFFF80L & n) == 0x0L) {
            n2 = 1;
        }
        else if ((0xFFFFFFFFFFFFC000L & n) == 0x0L) {
            n2 = 2;
        }
        else if ((0xFFFFFFFFFFE00000L & n) == 0x0L) {
            n2 = 3;
        }
        else if ((0xFFFFFFFFF0000000L & n) == 0x0L) {
            n2 = 4;
        }
        else if ((0xFFFFFFF800000000L & n) == 0x0L) {
            n2 = 5;
        }
        else if ((0xFFFFFC0000000000L & n) == 0x0L) {
            n2 = 6;
        }
        else if ((0xFFFE000000000000L & n) == 0x0L) {
            n2 = 7;
        }
        else if ((0xFF00000000000000L & n) == 0x0L) {
            n2 = 8;
        }
        else if ((Long.MIN_VALUE & n) == 0x0L) {
            n2 = 9;
        }
        else {
            n2 = 10;
        }
        return n2;
    }
    
    public static long zzdh(final long n) {
        return n << 1 ^ n >> 63;
    }
    
    public static int zzdl(final boolean b) {
        return 1;
    }
    
    public static int zze(final int n, final long n2) {
        return zzahl(n) + zzda(n2);
    }
    
    public static int zze(final zzark zzark) {
        final int db = zzark.db();
        return db + zzahn(db);
    }
    
    public static int zzf(final int n, final long n2) {
        return zzahl(n) + zzdb(n2);
    }
    
    public static int zzg(final int n, final long n2) {
        return zzahl(n) + zzdc(n2);
    }
    
    public static int zzh(final int n, final long n2) {
        return zzahl(n) + zzdd(n2);
    }
    
    public static int zzk(final int n, final boolean b) {
        return zzahl(n) + zzdl(b);
    }
    
    public static int zzl(final float n) {
        return 4;
    }
    
    public static int zzp(final double n) {
        return 8;
    }
    
    public static int zzs(final int n, final String s) {
        return zzahl(n) + zzuy(s);
    }
    
    public static int zzuy(final String s) {
        final int zzd = zzd(s);
        return zzd + zzahn(zzd);
    }
    
    public int cN() {
        return this.bqu.remaining();
    }
    
    public void cO() {
        if (this.cN() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }
    
    public void zza(final int n, final double n2) throws IOException {
        this.zzai(n, 1);
        this.zzo(n2);
    }
    
    public void zza(final int n, final long n2) throws IOException {
        this.zzai(n, 0);
        this.zzcw(n2);
    }
    
    public void zza(final int n, final zzark zzark) throws IOException {
        this.zzai(n, 2);
        this.zzc(zzark);
    }
    
    public void zza(final int n, final byte[] array) throws IOException {
        this.zzai(n, 2);
        this.zzbf(array);
    }
    
    public void zzae(final int n, final int n2) throws IOException {
        this.zzai(n, 0);
        this.zzahg(n2);
    }
    
    public void zzaf(final int n, final int n2) throws IOException {
        this.zzai(n, 0);
        this.zzahh(n2);
    }
    
    public void zzahg(final int n) throws IOException {
        if (n >= 0) {
            this.zzahm(n);
        }
        else {
            this.zzde(n);
        }
    }
    
    public void zzahh(final int n) throws IOException {
        this.zzahm(zzahp(n));
    }
    
    public void zzahk(final int n) throws IOException {
        this.zzc((byte)n);
    }
    
    public void zzahm(int n) throws IOException {
        while ((n & 0xFFFFFF80) != 0x0) {
            this.zzahk(0x80 | (n & 0x7F));
            n >>>= 7;
        }
        this.zzahk(n);
    }
    
    public void zzaho(final int n) throws IOException {
        if (this.bqu.remaining() < 4) {
            throw new zza(this.bqu.position(), this.bqu.limit());
        }
        this.bqu.putInt(n);
    }
    
    public void zzai(final int n, final int n2) throws IOException {
        this.zzahm(zzarn.zzaj(n, n2));
    }
    
    public void zzb(final int n, final long n2) throws IOException {
        this.zzai(n, 0);
        this.zzcx(n2);
    }
    
    public void zzb(final zzark zzark) throws IOException {
        zzark.zza(this);
    }
    
    public void zzbf(final byte[] array) throws IOException {
        this.zzahm(array.length);
        this.zzbh(array);
    }
    
    public void zzbh(final byte[] array) throws IOException {
        this.zzd(array, 0, array.length);
    }
    
    public void zzc(final byte b) throws IOException {
        if (!this.bqu.hasRemaining()) {
            throw new zza(this.bqu.position(), this.bqu.limit());
        }
        this.bqu.put(b);
    }
    
    public void zzc(final int n, final float n2) throws IOException {
        this.zzai(n, 5);
        this.zzk(n2);
    }
    
    public void zzc(final int n, final long n2) throws IOException {
        this.zzai(n, 1);
        this.zzcy(n2);
    }
    
    public void zzc(final zzark zzark) throws IOException {
        this.zzahm(zzark.da());
        zzark.zza(this);
    }
    
    public void zzcw(final long n) throws IOException {
        this.zzde(n);
    }
    
    public void zzcx(final long n) throws IOException {
        this.zzde(n);
    }
    
    public void zzcy(final long n) throws IOException {
        this.zzdg(n);
    }
    
    public void zzcz(final long n) throws IOException {
        this.zzde(zzdh(n));
    }
    
    public void zzd(final int n, final long n2) throws IOException {
        this.zzai(n, 0);
        this.zzcz(n2);
    }
    
    public void zzd(final byte[] array, final int n, final int n2) throws IOException {
        if (this.bqu.remaining() >= n2) {
            this.bqu.put(array, n, n2);
            return;
        }
        throw new zza(this.bqu.position(), this.bqu.limit());
    }
    
    public void zzde(long n) throws IOException {
        while ((0xFFFFFFFFFFFFFF80L & n) != 0x0L) {
            this.zzahk(0x80 | (0x7F & (int)n));
            n >>>= 7;
        }
        this.zzahk((int)n);
    }
    
    public void zzdg(final long n) throws IOException {
        if (this.bqu.remaining() < 8) {
            throw new zza(this.bqu.position(), this.bqu.limit());
        }
        this.bqu.putLong(n);
    }
    
    public void zzdk(final boolean b) throws IOException {
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        this.zzahk(n);
    }
    
    public void zzj(final int n, final boolean b) throws IOException {
        this.zzai(n, 0);
        this.zzdk(b);
    }
    
    public void zzk(final float n) throws IOException {
        this.zzaho(Float.floatToIntBits(n));
    }
    
    public void zzo(final double n) throws IOException {
        this.zzdg(Double.doubleToLongBits(n));
    }
    
    public void zzr(final int n, final String s) throws IOException {
        this.zzai(n, 2);
        this.zzux(s);
    }
    
    public void zzux(final String s) throws IOException {
        Label_0160: {
            int zzahn;
            int position;
            try {
                zzahn = zzahn(s.length());
                if (zzahn != zzahn(3 * s.length())) {
                    break Label_0160;
                }
                position = this.bqu.position();
                if (this.bqu.remaining() < zzahn) {
                    throw new zza(zzahn + position, this.bqu.limit());
                }
            }
            catch (BufferOverflowException ex) {
                final zza zza = new zza(this.bqu.position(), this.bqu.limit());
                zza.initCause(ex);
                throw zza;
            }
            this.bqu.position(position + zzahn);
            zza(s, this.bqu);
            final int position2 = this.bqu.position();
            this.bqu.position(position);
            this.zzahm(position2 - position - zzahn);
            this.bqu.position(position2);
            return;
        }
        this.zzahm(zzd(s));
        zza(s, this.bqu);
    }
    
    public static class zza extends IOException
    {
        zza(final int n, final int n2) {
            super(new StringBuilder(108).append("CodedOutputStream was writing to a flat byte array and ran out of space (pos ").append(n).append(" limit ").append(n2).append(").").toString());
        }
    }
}
