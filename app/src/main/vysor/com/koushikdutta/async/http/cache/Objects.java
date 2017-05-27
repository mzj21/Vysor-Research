// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.cache;

final class Objects
{
    public static boolean equal(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    public static int hashCode(final Object o) {
        int hashCode;
        if (o == null) {
            hashCode = 0;
        }
        else {
            hashCode = o.hashCode();
        }
        return hashCode;
    }
}
