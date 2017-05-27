// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

@zziy
public class zzjt
{
    public final int errorCode;
    public final String zzbro;
    public final long zzbtj;
    public final String zzcnk;
    
    private zzjt(final zza zza) {
        this.zzcnk = zza.zzbst;
        this.zzbro = zza.zzcnl;
        this.errorCode = zza.zzcdb;
        this.zzbtj = zza.zzcnm;
    }
    
    public static final class zza
    {
        private String zzbst;
        private int zzcdb;
        private String zzcnl;
        private long zzcnm;
        
        public zza zzaz(final int zzcdb) {
            this.zzcdb = zzcdb;
            return this;
        }
        
        public zza zzcn(final String zzbst) {
            this.zzbst = zzbst;
            return this;
        }
        
        public zza zzco(final String zzcnl) {
            this.zzcnl = zzcnl;
            return this;
        }
        
        public zza zzl(final long zzcnm) {
            this.zzcnm = zzcnm;
            return this;
        }
        
        public zzjt zzss() {
            return new zzjt(this, null);
        }
    }
}
