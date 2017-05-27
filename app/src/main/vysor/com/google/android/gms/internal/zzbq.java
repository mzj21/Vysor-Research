// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public class zzbq extends zzbv
{
    private final StackTraceElement[] zzaiv;
    
    public zzbq(final zzbb zzbb, final String s, final String s2, final zzae.zza zza, final int n, final int n2, final StackTraceElement[] zzaiv) {
        super(zzbb, s, s2, zza, n, n2);
        this.zzaiv = zzaiv;
    }
    
    @Override
    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        if (this.zzaiv != null) {
            while (true) {
                final zzaz zzaz = new zzaz((String)this.zzaiz.invoke(null, this.zzaiv));
                while (true) {
                    Label_0115: {
                        synchronized (this.zzair) {
                            this.zzair.zzed = zzaz.zzahi;
                            if (zzaz.zzahj) {
                                final zzae.zza zzair = this.zzair;
                                if (!zzaz.zzahk) {
                                    break Label_0115;
                                }
                                final int n = 0;
                                zzair.zzen = n;
                            }
                        }
                        break;
                    }
                    final int n = 1;
                    continue;
                }
            }
        }
    }
}
