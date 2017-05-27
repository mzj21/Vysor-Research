// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class zzva
{
    private static zzva Uw;
    private final zzux Ux;
    private final zzuy Uy;
    
    static {
        zza(new zzva());
    }
    
    private zzva() {
        this.Ux = new zzux();
        this.Uy = new zzuy();
    }
    
    protected static void zza(final zzva uw) {
        synchronized (zzva.class) {
            zzva.Uw = uw;
        }
    }
    
    private static zzva zzbhl() {
        synchronized (zzva.class) {
            return zzva.Uw;
        }
    }
    
    public static zzux zzbhm() {
        return zzbhl().Ux;
    }
    
    public static zzuy zzbhn() {
        return zzbhl().Uy;
    }
}
