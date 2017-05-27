// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.clearcut;

import android.content.Context;

public class zza
{
    private static int tF;
    public static final zza tG;
    
    static {
        zza.tF = -1;
        tG = new zza();
    }
    
    public int zzbl(final Context context) {
        if (zza.tF < 0) {
            zza.tF = context.getSharedPreferences("bootCount", 0).getInt("bootCount", 1);
        }
        return zza.tF;
    }
}
