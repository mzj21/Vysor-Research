// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;

public class zzi
{
    public final byte[] data;
    public final int statusCode;
    public final boolean zzaa;
    public final long zzab;
    public final Map<String, String> zzz;
    
    public zzi(final int statusCode, final byte[] data, final Map<String, String> zzz, final boolean zzaa, final long zzab) {
        this.statusCode = statusCode;
        this.data = data;
        this.zzz = zzz;
        this.zzaa = zzaa;
        this.zzab = zzab;
    }
    
    public zzi(final byte[] array, final Map<String, String> map) {
        this(200, array, map, false, 0L);
    }
}
