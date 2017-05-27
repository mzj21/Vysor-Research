// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import android.os.Handler;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.lang.ref.WeakReference;
import com.google.android.gms.internal.zzkr;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.internal.zziy;

@zziy
public class zzr
{
    private final zza zzaov;
    @Nullable
    private AdRequestParcel zzaow;
    private boolean zzaox;
    private boolean zzaoy;
    private long zzaoz;
    private final Runnable zzw;
    
    public zzr(final com.google.android.gms.ads.internal.zza zza) {
        this(zza, new zza(zzkr.zzcrf));
    }
    
    zzr(final com.google.android.gms.ads.internal.zza zza, final zza zzaov) {
        this.zzaox = false;
        this.zzaoy = false;
        this.zzaoz = 0L;
        this.zzaov = zzaov;
        this.zzw = new Runnable() {
            final /* synthetic */ WeakReference zzapa = new WeakReference((T)zza);
            
            @Override
            public void run() {
                zzr.this.zzaox = false;
                final com.google.android.gms.ads.internal.zza zza = (com.google.android.gms.ads.internal.zza)this.zzapa.get();
                if (zza != null) {
                    zza.zzd(zzr.this.zzaow);
                }
            }
        };
    }
    
    public void cancel() {
        this.zzaox = false;
        this.zzaov.removeCallbacks(this.zzw);
    }
    
    public void pause() {
        this.zzaoy = true;
        if (this.zzaox) {
            this.zzaov.removeCallbacks(this.zzw);
        }
    }
    
    public void resume() {
        this.zzaoy = false;
        if (this.zzaox) {
            this.zzaox = false;
            this.zza(this.zzaow, this.zzaoz);
        }
    }
    
    public void zza(final AdRequestParcel zzaow, final long zzaoz) {
        if (this.zzaox) {
            zzb.zzdf("An ad refresh is already scheduled.");
        }
        else {
            this.zzaow = zzaow;
            this.zzaox = true;
            this.zzaoz = zzaoz;
            if (!this.zzaoy) {
                zzb.zzde(new StringBuilder(65).append("Scheduling ad refresh ").append(zzaoz).append(" milliseconds from now.").toString());
                this.zzaov.postDelayed(this.zzw, zzaoz);
            }
        }
    }
    
    public boolean zzfl() {
        return this.zzaox;
    }
    
    public void zzg(final AdRequestParcel zzaow) {
        this.zzaow = zzaow;
    }
    
    public void zzh(final AdRequestParcel adRequestParcel) {
        this.zza(adRequestParcel, 60000L);
    }
    
    public static class zza
    {
        private final Handler mHandler;
        
        public zza(final Handler mHandler) {
            this.mHandler = mHandler;
        }
        
        public boolean postDelayed(final Runnable runnable, final long n) {
            return this.mHandler.postDelayed(runnable, n);
        }
        
        public void removeCallbacks(final Runnable runnable) {
            this.mHandler.removeCallbacks(runnable);
        }
    }
}
