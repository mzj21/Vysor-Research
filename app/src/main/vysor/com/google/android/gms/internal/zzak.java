// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.Base64;

public final class zzak
{
    public static String zza(final byte[] array, final boolean b) {
        int n;
        if (b) {
            n = 11;
        }
        else {
            n = 2;
        }
        return Base64.encodeToString(array, n);
    }
    
    public static byte[] zza(final String s, final boolean b) throws IllegalArgumentException {
        int n;
        if (b) {
            n = 11;
        }
        else {
            n = 2;
        }
        final byte[] decode = Base64.decode(s, n);
        if (decode.length == 0 && s.length() > 0) {
            final String value = String.valueOf(s);
            String concat;
            if (value.length() != 0) {
                concat = "Unable to decode ".concat(value);
            }
            else {
                concat = new String("Unable to decode ");
            }
            throw new IllegalArgumentException(concat);
        }
        return decode;
    }
}
