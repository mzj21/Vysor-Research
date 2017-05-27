// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.Iterator;

public class zzz
{
    private final String separator;
    
    private zzz(final String separator) {
        this.separator = separator;
    }
    
    public static zzz zzhy(final String s) {
        return new zzz(s);
    }
    
    public final String zza(final Iterable<?> iterable) {
        return this.zza(new StringBuilder(), iterable).toString();
    }
    
    public final StringBuilder zza(final StringBuilder sb, final Iterable<?> iterable) {
        final Iterator<?> iterator = iterable.iterator();
        if (iterator.hasNext()) {
            sb.append(this.zzw(iterator.next()));
            while (iterator.hasNext()) {
                sb.append(this.separator);
                sb.append(this.zzw(iterator.next()));
            }
        }
        return sb;
    }
    
    CharSequence zzw(final Object o) {
        CharSequence string;
        if (o instanceof CharSequence) {
            string = (CharSequence)o;
        }
        else {
            string = o.toString();
        }
        return string;
    }
}
