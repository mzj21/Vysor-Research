// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;
import java.util.Iterator;
import java.util.Arrays;
import java.util.Vector;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CountDownLatch;
import java.security.MessageDigest;

final class zzam
{
    static boolean zzyf;
    private static MessageDigest zzyg;
    private static final Object zzyh;
    private static final Object zzyi;
    static CountDownLatch zzyj;
    
    static {
        zzam.zzyf = false;
        zzam.zzyg = null;
        zzyh = new Object();
        zzyi = new Object();
        zzam.zzyj = new CountDownLatch(1);
    }
    
    private static int zza(final boolean b) {
        int n;
        if (b) {
            n = 239;
        }
        else {
            n = 255;
        }
        return n;
    }
    
    static String zza(final zzae.zza zza, final String s, final boolean b) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return zza(zzark.zzf(zza), s, b);
    }
    
    static String zza(final String s, final String s2, final boolean b) {
        final byte[] zzb = zzb(s, s2, b);
        String s3;
        if (zzb != null) {
            s3 = zzak.zza(zzb, true);
        }
        else {
            s3 = Integer.toString(7);
        }
        return s3;
    }
    
    static String zza(final byte[] array, final String s, final boolean b) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] array2;
        if (b) {
            array2 = zzb(array, s);
        }
        else {
            array2 = zza(array, s);
        }
        return zzak.zza(array2, true);
    }
    
    static Vector<byte[]> zza(final byte[] array, final int n) {
        Vector<byte[]> vector = null;
        if (array != null) {
            final int length = array.length;
            vector = null;
            if (length > 0) {
                final int n2 = (-1 + (n + array.length)) / n;
                final Vector<byte[]> vector2 = new Vector<byte[]>();
                int n3 = 0;
            Label_0094_Outer:
                while (true) {
                    while (true) {
                        if (n3 < n2) {
                            final int n4 = n3 * n;
                            try {
                                int length2;
                                if (array.length - n4 > n) {
                                    length2 = n4 + n;
                                }
                                else {
                                    length2 = array.length;
                                }
                                vector2.add(Arrays.copyOfRange(array, n4, length2));
                                ++n3;
                                continue Label_0094_Outer;
                                vector = vector2;
                            }
                            catch (IndexOutOfBoundsException ex) {
                                vector = null;
                            }
                            break;
                        }
                        continue;
                    }
                }
            }
        }
        return vector;
    }
    
    static void zza(String substring, final byte[] array) throws UnsupportedEncodingException {
        if (substring.length() > 32) {
            substring = substring.substring(0, 32);
        }
        new zzaqc(substring.getBytes("UTF-8")).zzax(array);
    }
    
    static byte[] zza(final byte[] array, final String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final Vector<byte[]> zza = zza(array, 255);
        byte[] array2;
        if (zza == null || zza.size() == 0) {
            array2 = zzb(zzark.zzf(zzb(4096L)), s);
        }
        else {
            final zzae.zzf zzf = new zzae.zzf();
            zzf.zzfw = new byte[zza.size()][];
            final Iterator<byte[]> iterator = zza.iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final byte[] zzb = zzb(iterator.next(), s, false);
                final byte[][] zzfw = zzf.zzfw;
                final int n2 = n + 1;
                zzfw[n] = zzb;
                n = n2;
            }
            zzf.zzfr = zzg(array);
            array2 = zzark.zzf(zzf);
        }
        return array2;
    }
    
    static void zzas() {
        synchronized (zzam.zzyi) {
            if (!zzam.zzyf) {
                zzam.zzyf = true;
                new Thread(new zza()).start();
            }
        }
    }
    
    static MessageDigest zzat() {
        zzas();
        while (true) {
            try {
                final int await = zzam.zzyj.await(2L, TimeUnit.SECONDS) ? 1 : 0;
                MessageDigest zzyg = null;
                if (await != 0) {
                    final MessageDigest zzyg2 = zzam.zzyg;
                    zzyg = null;
                    if (zzyg2 != null) {
                        zzyg = zzam.zzyg;
                    }
                }
                return zzyg;
            }
            catch (InterruptedException ex) {
                final int await = 0;
                continue;
            }
            break;
        }
    }
    
    static zzae.zza zzb(final long n) {
        final zzae.zza zza = new zzae.zza();
        zza.zzdl = n;
        return zza;
    }
    
    static byte[] zzb(final String s, final String s2, final boolean b) {
        final zzae.zzc zzc = new zzae.zzc();
        byte[] zzf = null;
        try {
            byte[] zzfp;
            if (s.length() < 3) {
                zzfp = s.getBytes("ISO-8859-1");
            }
            else {
                zzfp = zzak.zza(s, true);
            }
            zzc.zzfp = zzfp;
            byte[] zzfq;
            if (b) {
                if (s2.length() < 3) {
                    zzfq = s2.getBytes("ISO-8859-1");
                }
                else {
                    zzfq = zzak.zza(s2, true);
                }
            }
            else if (s2 == null || s2.length() == 0) {
                zzfq = Integer.toString(5).getBytes("ISO-8859-1");
            }
            else {
                zzfq = zzak.zza(zza(s2.getBytes("ISO-8859-1"), null, zzdi.zzbep.get()), true);
            }
            zzc.zzfq = zzfq;
            zzf = zzark.zzf(zzc);
        }
        catch (NoSuchAlgorithmException ex) {}
        catch (UnsupportedEncodingException ex2) {
            goto Label_0147;
        }
        return zzf;
    }
    
    static byte[] zzb(final byte[] array, final String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return zzb(array, s, true);
    }
    
    private static byte[] zzb(byte[] zzf, final String s, final boolean b) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final int zza = zza(b);
        if (zzf.length > zza) {
            zzf = zzark.zzf(zzb(4096L));
        }
        byte[] array2;
        if (zzf.length < zza) {
            final byte[] array = new byte[zza - zzf.length];
            new SecureRandom().nextBytes(array);
            array2 = ByteBuffer.allocate(zza + 1).put((byte)zzf.length).put(zzf).put(array).array();
        }
        else {
            array2 = ByteBuffer.allocate(zza + 1).put((byte)zzf.length).put(zzf).array();
        }
        if (b) {
            array2 = ByteBuffer.allocate(256).put(zzg(array2)).put(array2).array();
        }
        final byte[] array3 = new byte[256];
        new zzan().zzb(array2, array3);
        if (s != null && s.length() > 0) {
            zza(s, array3);
        }
        return array3;
    }
    
    public static byte[] zzg(final byte[] array) throws NoSuchAlgorithmException {
        final MessageDigest zzat;
        synchronized (zzam.zzyh) {
            zzat = zzat();
            if (zzat == null) {
                throw new NoSuchAlgorithmException("Cannot compute hash");
            }
        }
        zzat.reset();
        zzat.update(array);
        // monitorexit(o)
        return zzam.zzyg.digest();
    }
    
    private static final class zza implements Runnable
    {
        @Override
        public void run() {
            try {
                zzam.zzyg = MessageDigest.getInstance("MD5");
            }
            catch (NoSuchAlgorithmException ex) {
                zzam.zzyj.countDown();
            }
            finally {
                zzam.zzyj.countDown();
            }
        }
    }
}
