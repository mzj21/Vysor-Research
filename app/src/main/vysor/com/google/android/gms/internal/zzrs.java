// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Binder;

public abstract class zzrs<T>
{
    private static String READ_PERMISSION;
    private static zza zB;
    private static int zC;
    private static final Object zzaok;
    private T zD;
    protected final String zzbaf;
    protected final T zzbag;
    
    static {
        zzaok = new Object();
        zzrs.zB = null;
        zzrs.zC = 0;
        zzrs.READ_PERMISSION = "com.google.android.providers.gsf.permission.READ_GSERVICES";
    }
    
    protected zzrs(final String zzbaf, final T zzbag) {
        this.zD = null;
        this.zzbaf = zzbaf;
        this.zzbag = zzbag;
    }
    
    public static zzrs<Float> zza(final String s, final Float n) {
        return new zzrs<Float>(s, n) {
            protected Float zzhk(final String s) {
                return zzrs.zzasy().zzb(this.zzbaf, (Float)this.zzbag);
            }
        };
    }
    
    public static zzrs<Integer> zza(final String s, final Integer n) {
        return new zzrs<Integer>(s, n) {
            protected Integer zzhj(final String s) {
                return zzrs.zzasy().zzb(this.zzbaf, (Integer)this.zzbag);
            }
        };
    }
    
    public static zzrs<Long> zza(final String s, final Long n) {
        return new zzrs<Long>(s, n) {
            protected Long zzhi(final String s) {
                return zzrs.zzasy().getLong(this.zzbaf, (Long)this.zzbag);
            }
        };
    }
    
    public static zzrs<String> zzab(final String s, final String s2) {
        return new zzrs<String>(s, s2) {
            protected String zzhl(final String s) {
                return zzrs.zzasy().getString(this.zzbaf, (String)this.zzbag);
            }
        };
    }
    
    static /* synthetic */ zza zzasy() {
        return null;
    }
    
    public static zzrs<Boolean> zzm(final String s, final boolean b) {
        return new zzrs<Boolean>(s, Boolean.valueOf(b)) {
            protected Boolean zzhh(final String s) {
                return zzrs.zzasy().zza(this.zzbaf, (Boolean)this.zzbag);
            }
        };
    }
    
    public final T get() {
        try {
            return this.zzhg(this.zzbaf);
        }
        catch (SecurityException ex) {
            final long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                final T t = this.zzhg(this.zzbaf);
            }
            finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
    }
    
    protected abstract T zzhg(final String p0);
    
    private interface zza
    {
        Long getLong(final String p0, final Long p1);
        
        String getString(final String p0, final String p1);
        
        Boolean zza(final String p0, final Boolean p1);
        
        Float zzb(final String p0, final Float p1);
        
        Integer zzb(final String p0, final Integer p1);
    }
}
