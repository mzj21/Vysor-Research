// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.RemoteException;

public abstract class zzuw<T>
{
    private final int zzbae;
    private final String zzbaf;
    private final T zzbag;
    
    private zzuw(final int zzbae, final String zzbaf, final T zzbag) {
        this.zzbae = zzbae;
        this.zzbaf = zzbaf;
        this.zzbag = zzbag;
        zzva.zzbhm().zza(this);
    }
    
    public static zza zzb(final int n, final String s, final Boolean b) {
        return new zza(n, s, b);
    }
    
    public static zzb zzb(final int n, final String s, final int n2) {
        return new zzb(n, s, Integer.valueOf(n2));
    }
    
    public static zzc zzb(final int n, final String s, final long n2) {
        return new zzc(n, s, Long.valueOf(n2));
    }
    
    public static zzd zzc(final int n, final String s, final String s2) {
        return new zzd(n, s, s2);
    }
    
    public T get() {
        return zzva.zzbhn().zzb(this);
    }
    
    public String getKey() {
        return this.zzbaf;
    }
    
    public int getSource() {
        return this.zzbae;
    }
    
    protected abstract T zza(final zzuz p0);
    
    public T zzkq() {
        return this.zzbag;
    }
    
    public static class zza extends zzuw<Boolean>
    {
        public zza(final int n, final String s, final Boolean b) {
            super(n, s, b, null);
        }
        
        public Boolean zzb(final zzuz zzuz) {
            try {
                return zzuz.getBooleanFlagValue(this.getKey(), this.zzkq(), this.getSource());
            }
            catch (RemoteException ex) {
                return this.zzkq();
            }
        }
    }
    
    public static class zzb extends zzuw<Integer>
    {
        public zzb(final int n, final String s, final Integer n2) {
            super(n, s, n2, null);
        }
        
        public Integer zzc(final zzuz zzuz) {
            try {
                return zzuz.getIntFlagValue(this.getKey(), this.zzkq(), this.getSource());
            }
            catch (RemoteException ex) {
                return this.zzkq();
            }
        }
    }
    
    public static class zzc extends zzuw<Long>
    {
        public zzc(final int n, final String s, final Long n2) {
            super(n, s, n2, null);
        }
        
        public Long zzd(final zzuz zzuz) {
            try {
                return zzuz.getLongFlagValue(this.getKey(), this.zzkq(), this.getSource());
            }
            catch (RemoteException ex) {
                return this.zzkq();
            }
        }
    }
    
    public static class zzd extends zzuw<String>
    {
        public zzd(final int n, final String s, final String s2) {
            super(n, s, s2, null);
        }
        
        public String zze(final zzuz zzuz) {
            try {
                return zzuz.getStringFlagValue(this.getKey(), this.zzkq(), this.getSource());
            }
            catch (RemoteException ex) {
                return this.zzkq();
            }
        }
    }
}
