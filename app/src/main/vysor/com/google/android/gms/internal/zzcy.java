// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.security.MessageDigest;

@zziy
public class zzcy extends zzcv
{
    private MessageDigest zzavn;
    
    byte[] zza(final String[] array) {
        int i = 0;
        byte[] zzn;
        if (array.length == 1) {
            zzn = zzcx.zzn(zzcx.zzae(array[0]));
        }
        else if (array.length < 5) {
            final byte[] array2 = new byte[2 * array.length];
            for (int j = 0; j < array.length; ++j) {
                final byte[] zzq = this.zzq(zzcx.zzae(array[j]));
                array2[j * 2] = zzq[0];
                array2[1 + j * 2] = zzq[1];
            }
            zzn = array2;
        }
        else {
            zzn = new byte[array.length];
            while (i < array.length) {
                zzn[i] = this.zzp(zzcx.zzae(array[i]));
                ++i;
            }
        }
        return zzn;
    }
    
    public byte[] zzac(final String s) {
        byte[] array;
        while (true) {
            int length = 4;
            final byte[] zza = this.zza(s.split(" "));
            this.zzavn = this.zzir();
            while (true) {
                final byte[] digest;
                synchronized (this.zzakd) {
                    if (this.zzavn == null) {
                        array = new byte[0];
                        break;
                    }
                    this.zzavn.reset();
                    this.zzavn.update(zza);
                    digest = this.zzavn.digest();
                    if (digest.length > length) {
                        array = new byte[length];
                        System.arraycopy(digest, 0, array, 0, array.length);
                        break;
                    }
                }
                length = digest.length;
                continue;
            }
        }
        return array;
    }
    
    byte zzp(final int n) {
        return (byte)((n & 0xFF) ^ (0xFF00 & n) >> 8 ^ (0xFF0000 & n) >> 16 ^ (0xFF000000 & n) >> 24);
    }
    
    byte[] zzq(final int n) {
        final int n2 = (0xFFFF & n) ^ (0xFFFF0000 & n) >> 16;
        return new byte[] { (byte)n2, (byte)(n2 >> 8) };
    }
}
