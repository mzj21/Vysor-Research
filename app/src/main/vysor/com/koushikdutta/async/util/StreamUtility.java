// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.util;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.Channels;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.Closeable;

public class StreamUtility
{
    public static void closeQuietly(final Closeable... array) {
        if (array != null) {
            final int length = array.length;
            int i = 0;
            while (i < length) {
                final Closeable closeable = array[i];
                while (true) {
                    if (closeable == null) {
                        break Label_0029;
                    }
                    try {
                        closeable.close();
                        ++i;
                    }
                    catch (IOException ex) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    public static void copyStream(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        fastChannelCopy(Channels.newChannel(inputStream), Channels.newChannel(outputStream));
    }
    
    public static void eat(final InputStream inputStream) throws IOException {
        while (inputStream.read(new byte[1024]) != -1) {}
    }
    
    public static void fastChannelCopy(final ReadableByteChannel readableByteChannel, final WritableByteChannel writableByteChannel) throws IOException {
        final ByteBuffer allocateDirect = ByteBuffer.allocateDirect(16384);
        while (readableByteChannel.read(allocateDirect) != -1) {
            allocateDirect.flip();
            writableByteChannel.write(allocateDirect);
            allocateDirect.compact();
        }
        allocateDirect.flip();
        while (allocateDirect.hasRemaining()) {
            writableByteChannel.write(allocateDirect);
        }
    }
    
    public static String readFile(final File file) throws IOException {
        final byte[] array = new byte[(int)file.length()];
        Closeable closeable = null;
        DataInputStream dataInputStream;
        try {
            final DataInputStream dataInputStream2;
            dataInputStream = (dataInputStream2 = new DataInputStream(new FileInputStream(file)));
            final byte[] array2 = array;
            dataInputStream2.readFully(array2);
            final int n = 1;
            final Closeable[] array3 = new Closeable[n];
            final int n2 = 0;
            final DataInputStream dataInputStream3 = dataInputStream;
            array3[n2] = dataInputStream3;
            closeQuietly(array3);
            final byte[] array4 = array;
            return new String(array4);
        }
        finally {
            final Object o2;
            final Object o = o2;
        }
        while (true) {
            try {
                final DataInputStream dataInputStream2 = dataInputStream;
                final byte[] array2 = array;
                dataInputStream2.readFully(array2);
                final int n = 1;
                final Closeable[] array3 = new Closeable[n];
                final int n2 = 0;
                final DataInputStream dataInputStream3 = dataInputStream;
                array3[n2] = dataInputStream3;
                closeQuietly(array3);
                final byte[] array4 = array;
                return new String(array4);
                closeQuietly(closeable);
                throw;
            }
            finally {
                closeable = dataInputStream;
                continue;
            }
            break;
        }
    }
    
    public static String readFile(final String s) throws IOException {
        return readFile(new File(s));
    }
    
    public static String readFileSilent(final String s) {
        try {
            return readFile(new File(s));
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    public static String readToEnd(final InputStream inputStream) throws IOException {
        return new String(readToEndAsArray(inputStream));
    }
    
    public static byte[] readToEndAsArray(final InputStream inputStream) throws IOException {
        final DataInputStream dataInputStream = new DataInputStream(inputStream);
        final byte[] array = new byte[1024];
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            final int read = dataInputStream.read(array);
            if (read == -1) {
                break;
            }
            byteArrayOutputStream.write(array, 0, read);
        }
        dataInputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    public static void writeFile(final File file, final String s) throws IOException {
        file.getParentFile().mkdirs();
        final DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
        dataOutputStream.write(s.getBytes());
        dataOutputStream.close();
    }
    
    public static void writeFile(final String s, final String s2) throws IOException {
        writeFile(new File(s), s2);
    }
}
