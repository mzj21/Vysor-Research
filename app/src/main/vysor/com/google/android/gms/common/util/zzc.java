// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.util;

import android.util.Base64;

public final class zzc
{
    public static String zzp(final byte[] array) {
        String encodeToString;
        if (array == null) {
            encodeToString = null;
        }
        else {
            encodeToString = Base64.encodeToString(array, 0);
        }
        return encodeToString;
    }
    
    public static String zzq(final byte[] array) {
        String encodeToString;
        if (array == null) {
            encodeToString = null;
        }
        else {
            encodeToString = Base64.encodeToString(array, 10);
        }
        return encodeToString;
    }
    
    public static String zzr(final byte[] array) {
        String encodeToString;
        if (array == null) {
            encodeToString = null;
        }
        else {
            encodeToString = Base64.encodeToString(array, 11);
        }
        return encodeToString;
    }
}
