// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;

public class zzaw
{
    private static Cipher zzahe;
    private static final Object zzahf;
    private static final Object zzahg;
    private final SecureRandom zzahd;
    
    static {
        zzaw.zzahe = null;
        zzahf = new Object();
        zzahg = new Object();
    }
    
    public zzaw(final SecureRandom zzahd) {
        this.zzahd = zzahd;
    }
    
    private Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
        synchronized (zzaw.zzahg) {
            if (zzaw.zzahe == null) {
                zzaw.zzahe = Cipher.getInstance("AES/CBC/PKCS5Padding");
            }
            return zzaw.zzahe;
        }
    }
    
    static void zzh(final byte[] array) {
        for (int i = 0; i < array.length; ++i) {
            array[i] ^= 0x44;
        }
    }
    
    public byte[] zzc(final byte[] array, final String s) throws zza {
        if (array.length != 16) {
            throw new zza();
        }
        try {
            if (zzak.zza(s, false).length <= 16) {
                throw new zza();
            }
            goto Label_0053;
        }
        catch (NoSuchAlgorithmException ex) {
            throw new zza(ex);
        }
        catch (InvalidKeyException ex2) {
            throw new zza(ex2);
        }
        catch (IllegalBlockSizeException ex3) {
            throw new zza(ex3);
        }
        catch (NoSuchPaddingException ex4) {
            throw new zza(ex4);
        }
        catch (BadPaddingException ex5) {
            throw new zza(ex5);
        }
        catch (InvalidAlgorithmParameterException ex6) {
            throw new zza(ex6);
        }
        catch (IllegalArgumentException ex7) {
            throw new zza(ex7);
        }
        try {
            final SecretKeySpec secretKeySpec;
            final byte[] array2;
            this.getCipher().init(2, secretKeySpec, new IvParameterSpec(array2));
            final byte[] array3;
            return this.getCipher().doFinal(array3);
        }
        finally {}
    }
    
    public String zzd(final byte[] array, final byte[] array2) throws zza {
        if (array.length != 16) {
            throw new zza();
        }
        try {
            final SecretKeySpec secretKeySpec = new SecretKeySpec(array, "AES");
            synchronized (zzaw.zzahf) {
                this.getCipher().init(1, secretKeySpec, this.zzahd);
                final byte[] doFinal = this.getCipher().doFinal(array2);
                final byte[] iv = this.getCipher().getIV();
                // monitorexit(zzaw.zzahf)
                final int n = doFinal.length + iv.length;
                final ByteBuffer allocate = ByteBuffer.allocate(n);
                allocate.put(iv).put(doFinal);
                allocate.flip();
                final byte[] array3 = new byte[n];
                allocate.get(array3);
                return zzak.zza(array3, false);
            }
        }
        catch (NoSuchAlgorithmException ex) {
            throw new zza(ex);
        }
        catch (InvalidKeyException ex2) {
            throw new zza(ex2);
        }
        catch (IllegalBlockSizeException ex3) {
            throw new zza(ex3);
        }
        catch (NoSuchPaddingException ex4) {
            throw new zza(ex4);
        }
        catch (BadPaddingException ex5) {
            throw new zza(ex5);
        }
    }
    
    public byte[] zzn(final String s) throws zza {
        byte[] zza;
        try {
            zza = zzak.zza(s, false);
            if (zza.length != 32) {
                throw new zza();
            }
        }
        catch (IllegalArgumentException ex) {
            throw new zza(ex);
        }
        final ByteBuffer wrap = ByteBuffer.wrap(zza, 4, 16);
        final byte[] array = new byte[16];
        wrap.get(array);
        zzh(array);
        return array;
    }
    
    public class zza extends Exception
    {
        public zza() {
        }
        
        public zza(final Throwable t) {
            super(t);
        }
    }
}
