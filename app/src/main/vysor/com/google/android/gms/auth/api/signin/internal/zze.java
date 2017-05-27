// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin.internal;

public class zze
{
    static int hy;
    private int hz;
    
    static {
        zze.hy = 31;
    }
    
    public zze() {
        this.hz = 1;
    }
    
    public int zzahv() {
        return this.hz;
    }
    
    public zze zzbd(final boolean b) {
        final int n = zze.hy * this.hz;
        int n2;
        if (b) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        this.hz = n2 + n;
        return this;
    }
    
    public zze zzq(final Object o) {
        final int n = zze.hy * this.hz;
        int hashCode;
        if (o == null) {
            hashCode = 0;
        }
        else {
            hashCode = o.hashCode();
        }
        this.hz = hashCode + n;
        return this;
    }
}
