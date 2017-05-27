// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.ThreadLocalRandom;
import java.io.IOException;
import java.util.Random;
import com.google.android.gms.clearcut.zzb;

public class zzao
{
    protected static volatile zzb zzaga;
    private static volatile Random zzagc;
    private static final Object zzagd;
    private zzbb zzafz;
    protected boolean zzagb;
    
    static {
        zzao.zzaga = null;
        zzao.zzagc = null;
        zzagd = new Object();
    }
    
    public zzao(final zzbb zzafz) {
        this.zzagb = false;
        this.zzafz = zzafz;
        zzdi.initialize(zzafz.getContext());
        this.zzagb = zzdi.zzbem.get();
        if (this.zzagb && zzao.zzaga == null) {
            synchronized (zzao.zzagd) {
                if (zzao.zzaga == null) {
                    zzao.zzaga = new zzb(zzafz.getContext(), "ADSHIELD", null);
                }
            }
        }
    }
    
    private static Random zzav() {
        Label_0030: {
            if (zzao.zzagc != null) {
                break Label_0030;
            }
            synchronized (zzao.zzagd) {
                if (zzao.zzagc == null) {
                    zzao.zzagc = new Random();
                }
                return zzao.zzagc;
            }
        }
    }
    
    public void zza(final int n, final int n2, final long n3) throws IOException {
        try {
            if (this.zzagb && zzao.zzaga != null && this.zzafz.zzcn()) {
                final zzad.zza zza = new zzad.zza();
                zza.zzck = this.zzafz.getContext().getPackageName();
                zza.zzcl = n3;
                final zzb.zza zzl = zzao.zzaga.zzl(zzark.zzf(zza));
                zzl.zzfi(n2);
                zzl.zzfh(n);
                zzl.zze(this.zzafz.zzcl());
            }
        }
        catch (Exception ex) {}
    }
    
    public int zzau() {
        try {
            return ThreadLocalRandom.current().nextInt();
        }
        catch (NoClassDefFoundError noClassDefFoundError) {
            return zzav().nextInt();
        }
        catch (RuntimeException ex) {
            return zzav().nextInt();
        }
    }
}
