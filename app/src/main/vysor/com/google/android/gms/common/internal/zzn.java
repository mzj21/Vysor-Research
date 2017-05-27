// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.content.ServiceConnection;
import android.content.ComponentName;
import android.content.Context;

public abstract class zzn
{
    private static zzn CA;
    private static final Object Cz;
    
    static {
        Cz = new Object();
    }
    
    public static zzn zzcf(final Context context) {
        synchronized (zzn.Cz) {
            if (zzn.CA == null) {
                zzn.CA = new zzo(context.getApplicationContext());
            }
            return zzn.CA;
        }
    }
    
    public abstract boolean zza(final ComponentName p0, final ServiceConnection p1, final String p2);
    
    public abstract boolean zza(final String p0, final String p1, final ServiceConnection p2, final String p3);
    
    public abstract void zzb(final ComponentName p0, final ServiceConnection p1, final String p2);
    
    public abstract void zzb(final String p0, final String p1, final ServiceConnection p2, final String p3);
}
