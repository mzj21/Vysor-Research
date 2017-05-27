// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import java.util.Random;
import com.google.android.gms.internal.zziy;

@zziy
public class zzn extends zzy.zza
{
    private Object zzakd;
    private final Random zzayg;
    private long zzayh;
    
    public zzn() {
        this.zzakd = new Object();
        this.zzayg = new Random();
        this.zzjt();
    }
    
    public long getValue() {
        return this.zzayh;
    }
    
    public void zzjt() {
        final Object zzakd = this.zzakd;
        // monitorenter(zzakd)
        int n = 3;
        long zzayh = 0L;
        while (true) {
            Label_0046: {
                if (--n <= 0) {
                    break Label_0046;
                }
                try {
                    zzayh = 2147483648L + this.zzayg.nextInt();
                    if (zzayh != this.zzayh && zzayh != 0L) {
                        this.zzayh = zzayh;
                        return;
                    }
                    continue;
                }
                finally {
                }
                // monitorexit(zzakd)
            }
        }
    }
}
