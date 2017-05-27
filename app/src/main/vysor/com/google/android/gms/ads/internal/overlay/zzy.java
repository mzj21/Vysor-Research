// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zziy;

@zziy
class zzy implements Runnable
{
    private boolean mCancelled;
    private zzk zzcay;
    
    zzy(final zzk zzcay) {
        this.mCancelled = false;
        this.zzcay = zzcay;
    }
    
    public void cancel() {
        this.mCancelled = true;
        zzkr.zzcrf.removeCallbacks((Runnable)this);
    }
    
    @Override
    public void run() {
        if (!this.mCancelled) {
            this.zzcay.zzpo();
            this.zzqh();
        }
    }
    
    public void zzqh() {
        zzkr.zzcrf.postDelayed((Runnable)this, 250L);
    }
}
