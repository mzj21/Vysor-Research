// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public abstract class DowngradeableSafeParcel extends AbstractSafeParcelable implements ReflectedParcelable
{
    private static final Object Ce;
    private static ClassLoader Cf;
    private static Integer Cg;
    private boolean Ch;
    
    static {
        Ce = new Object();
        DowngradeableSafeParcel.Cf = null;
        DowngradeableSafeParcel.Cg = null;
    }
    
    public DowngradeableSafeParcel() {
        this.Ch = false;
    }
    
    protected static ClassLoader zzaup() {
        synchronized (DowngradeableSafeParcel.Ce) {
            // monitorexit(DowngradeableSafeParcel.Ce)
            return null;
        }
    }
    
    protected static Integer zzauq() {
        synchronized (DowngradeableSafeParcel.Ce) {
            // monitorexit(DowngradeableSafeParcel.Ce)
            return null;
        }
    }
    
    private static boolean zzd(final Class<?> clazz) {
        try {
            return "SAFE_PARCELABLE_NULL_STRING".equals(clazz.getField("NULL").get(null));
        }
        catch (IllegalAccessException ex) {
            return false;
        }
        catch (NoSuchFieldException ex2) {
            return false;
        }
    }
    
    protected static boolean zzhs(final String s) {
        final ClassLoader zzaup = zzaup();
        boolean zzd;
        if (zzaup == null) {
            zzd = true;
        }
        else {
            try {
                zzd = zzd(zzaup.loadClass(s));
            }
            catch (Exception ex) {
                zzd = false;
            }
        }
        return zzd;
    }
    
    protected boolean zzaur() {
        return false;
    }
}
