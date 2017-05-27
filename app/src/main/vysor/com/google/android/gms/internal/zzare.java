// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzare<M extends zzare<M>> extends zzark
{
    protected zzarg bqv;
    
    public M cP() throws CloneNotSupportedException {
        final zzare zzare = (zzare)super.cQ();
        zzari.zza(this, zzare);
        return (M)zzare;
    }
    
    public final <T> T zza(final zzarf<M, T> zzarf) {
        final zzarg bqv = this.bqv;
        T zzb = null;
        if (bqv != null) {
            final zzarh zzahq = this.bqv.zzahq(zzarn.zzahu(zzarf.tag));
            zzb = null;
            if (zzahq != null) {
                zzb = zzahq.zzb(zzarf);
            }
        }
        return zzb;
    }
    
    @Override
    public void zza(final zzard zzard) throws IOException {
        if (this.bqv != null) {
            for (int i = 0; i < this.bqv.size(); ++i) {
                this.bqv.zzahr(i).zza(zzard);
            }
        }
    }
    
    protected final boolean zza(final zzarc zzarc, final int n) throws IOException {
        final int position = zzarc.getPosition();
        boolean b;
        if (!zzarc.zzaha(n)) {
            b = false;
        }
        else {
            final int zzahu = zzarn.zzahu(n);
            final zzarm zzarm = new zzarm(n, zzarc.zzad(position, zzarc.getPosition() - position));
            zzarh zzahq = null;
            if (this.bqv == null) {
                this.bqv = new zzarg();
            }
            else {
                zzahq = this.bqv.zzahq(zzahu);
            }
            if (zzahq == null) {
                zzahq = new zzarh();
                this.bqv.zza(zzahu, zzahq);
            }
            zzahq.zza(zzarm);
            b = true;
        }
        return b;
    }
    
    @Override
    protected int zzx() {
        int i = 0;
        int n;
        if (this.bqv != null) {
            n = 0;
            while (i < this.bqv.size()) {
                n += this.bqv.zzahr(i).zzx();
                ++i;
            }
        }
        else {
            n = 0;
        }
        return n;
    }
}
