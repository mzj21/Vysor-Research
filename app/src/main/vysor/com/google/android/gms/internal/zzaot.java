// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzaot<T>
{
    public abstract void zza(final zzaqa p0, final T p1) throws IOException;
    
    public abstract T zzb(final zzapy p0) throws IOException;
    
    public final zzaoh zzco(final T t) {
        try {
            final zzapp zzapp = new zzapp();
            this.zza(zzapp, t);
            return zzapp.br();
        }
        catch (IOException ex) {
            throw new zzaoi(ex);
        }
    }
}
