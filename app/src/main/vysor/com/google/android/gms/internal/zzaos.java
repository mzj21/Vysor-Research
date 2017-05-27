// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

final class zzaos<T> extends zzaot<T>
{
    private zzaot<T> bkU;
    private final zzaop<T> blj;
    private final zzaog<T> blk;
    private final zzaob bll;
    private final zzapx<T> blm;
    private final zzaou bln;
    
    private zzaos(final zzaop<T> blj, final zzaog<T> blk, final zzaob bll, final zzapx<T> blm, final zzaou bln) {
        this.blj = blj;
        this.blk = blk;
        this.bll = bll;
        this.blm = blm;
        this.bln = bln;
    }
    
    private zzaot<T> bd() {
        zzaot<T> bkU = this.bkU;
        if (bkU == null) {
            bkU = this.bll.zza(this.bln, this.blm);
            this.bkU = bkU;
        }
        return bkU;
    }
    
    public static zzaou zza(final zzapx<?> zzapx, final Object o) {
        return new zza(o, (zzapx)zzapx, false, (Class)null);
    }
    
    public static zzaou zzb(final zzapx<?> zzapx, final Object o) {
        return new zza(o, (zzapx)zzapx, zzapx.bz() == zzapx.by(), (Class)null);
    }
    
    @Override
    public void zza(final zzaqa zzaqa, final T t) throws IOException {
        if (this.blj == null) {
            this.bd().zza(zzaqa, t);
        }
        else if (t == null) {
            zzaqa.bx();
        }
        else {
            zzapi.zzb(this.blj.zza(t, this.blm.bz(), this.bll.bkS), zzaqa);
        }
    }
    
    @Override
    public T zzb(final zzapy zzapy) throws IOException {
        T t;
        if (this.blk == null) {
            t = this.bd().zzb(zzapy);
        }
        else {
            final zzaoh zzh = zzapi.zzh(zzapy);
            if (zzh.aV()) {
                t = null;
            }
            else {
                try {
                    t = this.blk.zzb(zzh, this.blm.bz(), this.bll.bkR);
                }
                catch (zzaol zzaol) {
                    throw zzaol;
                }
                catch (Exception ex) {
                    throw new zzaol(ex);
                }
            }
        }
        return t;
    }
    
    private static class zza implements zzaou
    {
        private final zzaop<?> blj;
        private final zzaog<?> blk;
        private final zzapx<?> blo;
        private final boolean blp;
        private final Class<?> blq;
        
        private zza(final Object o, final zzapx<?> blo, final boolean blp, final Class<?> blq) {
            zzaop<?> blj;
            if (o instanceof zzaop) {
                blj = (zzaop<?>)o;
            }
            else {
                blj = null;
            }
            this.blj = blj;
            zzaog<?> blk;
            if (o instanceof zzaog) {
                blk = (zzaog<?>)o;
            }
            else {
                blk = null;
            }
            this.blk = blk;
            zzaoz.zzbs(this.blj != null || this.blk != null);
            this.blo = blo;
            this.blp = blp;
            this.blq = blq;
        }
        
        @Override
        public <T> zzaot<T> zza(final zzaob zzaob, final zzapx<T> zzapx) {
            int assignable;
            if (this.blo != null) {
                if (this.blo.equals(zzapx) || (this.blp && this.blo.bz() == zzapx.by())) {
                    assignable = 1;
                }
                else {
                    assignable = 0;
                }
            }
            else {
                assignable = (this.blq.isAssignableFrom(zzapx.by()) ? 1 : 0);
            }
            zzaot<T> zzaot;
            if (assignable != 0) {
                zzaot = new zzaos<T>(this.blj, this.blk, zzaob, zzapx, this, null);
            }
            else {
                zzaot = null;
            }
            return zzaot;
        }
    }
}
