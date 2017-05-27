// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.spdy;

import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.Arrays;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;
import com.koushikdutta.async.util.Charsets;
import android.util.Base64;
import java.io.Serializable;

final class ByteString implements Serializable
{
    public static final ByteString EMPTY;
    private static final char[] HEX_DIGITS;
    private static final long serialVersionUID = 1L;
    final byte[] data;
    private transient int hashCode;
    private transient String utf8;
    
    static {
        HEX_DIGITS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        EMPTY = of(new byte[0]);
    }
    
    ByteString(final byte[] data) {
        this.data = data;
    }
    
    public static ByteString decodeBase64(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("base64 == null");
        }
        final byte[] decode = Base64.decode(s, 0);
        ByteString byteString;
        if (decode != null) {
            byteString = new ByteString(decode);
        }
        else {
            byteString = null;
        }
        return byteString;
    }
    
    public static ByteString decodeHex(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("hex == null");
        }
        if (s.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: " + s);
        }
        final byte[] array = new byte[s.length() / 2];
        for (int i = 0; i < array.length; ++i) {
            array[i] = (byte)((decodeHexDigit(s.charAt(i * 2)) << 4) + decodeHexDigit(s.charAt(1 + i * 2)));
        }
        return of(array);
    }
    
    private static int decodeHexDigit(final char c) {
        char c2;
        if (c >= '0' && c <= '9') {
            c2 = (char)(c - '0');
        }
        else if (c >= 'a' && c <= 'f') {
            c2 = (char)('\n' + (c - 'a'));
        }
        else {
            if (c < 'A' || c > 'F') {
                throw new IllegalArgumentException("Unexpected hex digit: " + c);
            }
            c2 = (char)('\n' + (c - 'A'));
        }
        return c2;
    }
    
    public static ByteString encodeUtf8(final String utf8) {
        if (utf8 == null) {
            throw new IllegalArgumentException("s == null");
        }
        final ByteString byteString = new ByteString(utf8.getBytes(Charsets.UTF_8));
        byteString.utf8 = utf8;
        return byteString;
    }
    
    public static ByteString of(final byte... array) {
        if (array == null) {
            throw new IllegalArgumentException("data == null");
        }
        return new ByteString(array.clone());
    }
    
    public static ByteString of(final byte[] array, final int n, final int n2) {
        if (array == null) {
            throw new IllegalArgumentException("data == null");
        }
        Util.checkOffsetAndCount(array.length, n, n2);
        final byte[] array2 = new byte[n2];
        System.arraycopy(array, n, array2, 0, n2);
        return new ByteString(array2);
    }
    
    public static ByteString read(final InputStream inputStream, final int n) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (n < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + n);
        }
        final byte[] array = new byte[n];
        int read;
        for (int i = 0; i < n; i += read) {
            read = inputStream.read(array, i, n - i);
            if (read == -1) {
                throw new EOFException();
            }
        }
        return new ByteString(array);
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException {
        final ByteString read = read(objectInputStream, objectInputStream.readInt());
        try {
            final Field declaredField = ByteString.class.getDeclaredField("data");
            declaredField.setAccessible(true);
            declaredField.set(this, read.data);
        }
        catch (NoSuchFieldException ex) {
            throw new AssertionError();
        }
        catch (IllegalAccessException ex2) {
            throw new AssertionError();
        }
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.data.length);
        objectOutputStream.write(this.data);
    }
    
    public String base64() {
        return Base64.encodeToString(this.data, 0);
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof ByteString && Arrays.equals(((ByteString)o).data, this.data));
    }
    
    public byte getByte(final int n) {
        return this.data[n];
    }
    
    @Override
    public int hashCode() {
        int hashCode = this.hashCode;
        if (hashCode == 0) {
            hashCode = Arrays.hashCode(this.data);
            this.hashCode = hashCode;
        }
        return hashCode;
    }
    
    public String hex() {
        final char[] array = new char[2 * this.data.length];
        final byte[] data = this.data;
        final int length = data.length;
        int i = 0;
        int n = 0;
        while (i < length) {
            final byte b = data[i];
            final int n2 = n + 1;
            array[n] = ByteString.HEX_DIGITS[0xF & b >> 4];
            n = n2 + 1;
            array[n2] = ByteString.HEX_DIGITS[b & 0xF];
            ++i;
        }
        return new String(array);
    }
    
    public int size() {
        return this.data.length;
    }
    
    public ByteString toAsciiLowercase() {
        ByteString byteString = null;
        for (int i = 0; i < this.data.length; ++i) {
            final byte b = this.data[i];
            if (b >= 65 && b <= 90) {
                final byte[] array = this.data.clone();
                final int n = i + 1;
                array[i] = (byte)(b + 32);
                for (int j = n; j < array.length; ++j) {
                    final byte b2 = array[j];
                    if (b2 >= 65 && b2 <= 90) {
                        array[j] = (byte)(b2 + 32);
                    }
                }
                byteString = new ByteString(array);
                break;
            }
        }
        return byteString;
    }
    
    public ByteString toAsciiUppercase() {
        ByteString byteString = null;
        for (int i = 0; i < this.data.length; ++i) {
            final byte b = this.data[i];
            if (b >= 97 && b <= 122) {
                final byte[] array = this.data.clone();
                final int n = i + 1;
                array[i] = (byte)(b - 32);
                for (int j = n; j < array.length; ++j) {
                    final byte b2 = array[j];
                    if (b2 >= 97 && b2 <= 122) {
                        array[j] = (byte)(b2 - 32);
                    }
                }
                byteString = new ByteString(array);
                break;
            }
        }
        return byteString;
    }
    
    public byte[] toByteArray() {
        return this.data.clone();
    }
    
    @Override
    public String toString() {
        String s;
        if (this.data.length == 0) {
            s = "ByteString[size=0]";
        }
        else if (this.data.length <= 16) {
            s = String.format(Locale.ENGLISH, "ByteString[size=%s data=%s]", this.data.length, this.hex());
        }
        else {
            try {
                s = String.format(Locale.ENGLISH, "ByteString[size=%s md5=%s]", this.data.length, of(MessageDigest.getInstance("MD5").digest(this.data)).hex());
            }
            catch (NoSuchAlgorithmException ex) {
                throw new AssertionError();
            }
        }
        return s;
    }
    
    public String utf8() {
        String utf8 = this.utf8;
        if (utf8 == null) {
            utf8 = new String(this.data, Charsets.UTF_8);
            this.utf8 = utf8;
        }
        return utf8;
    }
    
    public void write(final OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        outputStream.write(this.data);
    }
}
