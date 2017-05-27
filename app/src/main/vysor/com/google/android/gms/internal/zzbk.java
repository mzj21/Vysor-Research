// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class zzbk extends zzbv
{
    public zzbk(final zzbb zzbb, final String s, final String s2, final zzae.zza zza, final int n, final int n2) {
        super(zzbb, s, s2, zza, n, n2);
    }
    
    private void zzcz() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zzair) {
            this.zzair.zzes = (String)this.zzaiz.invoke(null, this.zzafz.getApplicationContext());
        }
    }
    
    private void zzda() {
        final AdvertisingIdClient zzcv = this.zzafz.zzcv();
        if (zzcv == null) {
            this.zzr("E1");
        }
        else {
            try {
                final AdvertisingIdClient.Info info = zzcv.getInfo();
                final String zzq = zzbd.zzq(info.getId());
                if (zzq != null) {
                    synchronized (this.zzair) {
                        this.zzair.zzes = zzq;
                        this.zzair.zzeu = info.isLimitAdTrackingEnabled();
                        this.zzair.zzet = 5;
                    }
                }
            }
            catch (IOException ex) {
                this.zzr("E");
                return;
            }
            this.zzr("E");
        }
    }
    
    private void zzr(final String s) {
    }
    
    @Override
    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        if (this.zzafz.zzcm()) {
            this.zzda();
        }
        else {
            this.zzcz();
        }
    }
}
