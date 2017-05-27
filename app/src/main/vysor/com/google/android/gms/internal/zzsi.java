// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Context;

public class zzsi
{
    private static zzsi Fj;
    private zzsh Fi;
    
    static {
        zzsi.Fj = new zzsi();
    }
    
    public zzsi() {
        this.Fi = null;
    }
    
    public static zzsh zzcr(final Context context) {
        return zzsi.Fj.zzcq(context);
    }
    
    public zzsh zzcq(Context applicationContext) {
        synchronized (this) {
            if (this.Fi == null) {
                if (applicationContext.getApplicationContext() != null) {
                    applicationContext = applicationContext.getApplicationContext();
                }
                this.Fi = new zzsh(applicationContext);
            }
            return this.Fi;
        }
    }
}
