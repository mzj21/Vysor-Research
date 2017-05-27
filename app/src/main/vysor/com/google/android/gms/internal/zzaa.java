// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import java.io.ByteArrayOutputStream;

public class zzaa extends ByteArrayOutputStream
{
    private final zzu zzbq;
    
    public zzaa(final zzu zzbq, final int n) {
        this.zzbq = zzbq;
        this.buf = this.zzbq.zzb(Math.max(n, 256));
    }
    
    private void zzd(final int n) {
        if (n + this.count > this.buf.length) {
            final byte[] zzb = this.zzbq.zzb(2 * (n + this.count));
            System.arraycopy(this.buf, 0, zzb, 0, this.count);
            this.zzbq.zza(this.buf);
            this.buf = zzb;
        }
    }
    
    @Override
    public void close() throws IOException {
        this.zzbq.zza(this.buf);
        this.buf = null;
        super.close();
    }
    
    public void finalize() {
        this.zzbq.zza(this.buf);
    }
    
    @Override
    public void write(final int n) {
        synchronized (this) {
            this.zzd(1);
            super.write(n);
        }
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) {
        synchronized (this) {
            this.zzd(n2);
            super.write(array, n, n2);
        }
    }
}
