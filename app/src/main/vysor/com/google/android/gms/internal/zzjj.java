// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.content.Context;

@zziy
public abstract class zzjj
{
    public abstract void zza(final Context p0, final zzjd p1, final VersionInfoParcel p2);
    
    protected void zze(final zzjd zzjd) {
        zzjd.zzsf();
        if (zzjd.zzsd() != null) {
            zzjd.zzsd().release();
        }
    }
}
