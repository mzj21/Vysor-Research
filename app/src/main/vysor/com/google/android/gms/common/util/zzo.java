// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.util;

import java.io.ByteArrayOutputStream;
import android.os.ParcelFileDescriptor;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;

public final class zzo
{
    public static long zza(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        return zza(inputStream, outputStream, false);
    }
    
    public static long zza(final InputStream inputStream, final OutputStream outputStream, final boolean b) throws IOException {
        return zza(inputStream, outputStream, b, 1024);
    }
    
    public static long zza(final InputStream inputStream, final OutputStream outputStream, final boolean b, final int n) throws IOException {
        final byte[] array = new byte[n];
        long n2 = 0L;
        try {
            while (true) {
                final int read = inputStream.read(array, 0, n);
                if (read == -1) {
                    break;
                }
                n2 += read;
                outputStream.write(array, 0, read);
            }
        }
        finally {
            if (b) {
                zzb(inputStream);
                zzb(outputStream);
            }
        }
        if (b) {
            zzb(inputStream);
            zzb(outputStream);
        }
        return n2;
    }
    
    public static void zza(final ParcelFileDescriptor parcelFileDescriptor) {
        if (parcelFileDescriptor == null) {
            return;
        }
        try {
            parcelFileDescriptor.close();
        }
        catch (IOException ex) {}
    }
    
    public static byte[] zza(final InputStream inputStream, final boolean b) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        zza(inputStream, byteArrayOutputStream, b);
        return byteArrayOutputStream.toByteArray();
    }
    
    public static void zzb(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (IOException ex) {}
    }
    
    public static byte[] zzl(final InputStream inputStream) throws IOException {
        return zza(inputStream, true);
    }
}
