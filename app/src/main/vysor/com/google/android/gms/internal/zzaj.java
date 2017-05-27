// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import android.util.Base64;
import android.text.TextUtils;
import java.util.HashMap;

public abstract class zzaj<K, V>
{
    private static final String TAG;
    
    static {
        TAG = zzaj.class.getSimpleName();
    }
    
    protected static <K, V> HashMap<K, V> zzl(final String s) {
        try {
            if (!TextUtils.isEmpty((CharSequence)s)) {
                return (HashMap<K, V>)new ObjectInputStream(new ByteArrayInputStream(Base64.decode(s.getBytes(), 0))).readObject();
            }
            goto Label_0049;
        }
        catch (ClassNotFoundException ex) {}
        catch (IOException ex2) {
            goto Label_0040;
        }
    }
    
    @Override
    public String toString() {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this.zzar());
            objectOutputStream.close();
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    protected abstract HashMap<K, V> zzar();
    
    protected abstract void zzk(final String p0);
}
