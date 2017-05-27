// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.util.UUID;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;

public final class zzbd
{
    private static final char[] zzain;
    
    static {
        zzain = "0123456789abcdef".toCharArray();
    }
    
    public static String zza(final Throwable t) {
        final StringWriter stringWriter = new StringWriter();
        t.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
    
    public static String zzq(String zza) {
        if (zza != null && zza.matches("^[a-fA-F0-9]{8}-([a-fA-F0-9]{4}-){3}[a-fA-F0-9]{12}$")) {
            final UUID fromString = UUID.fromString(zza);
            final byte[] array = new byte[16];
            final ByteBuffer wrap = ByteBuffer.wrap(array);
            wrap.putLong(fromString.getMostSignificantBits());
            wrap.putLong(fromString.getLeastSignificantBits());
            zza = zzak.zza(array, true);
        }
        return zza;
    }
}
