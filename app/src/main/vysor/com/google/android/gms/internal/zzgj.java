// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.support.annotation.Nullable;

@zziy
public final class zzgj extends zzgs.zza
{
    private final Object zzakd;
    private zzgl.zza zzbsr;
    private zzgi zzbss;
    
    public zzgj() {
        this.zzakd = new Object();
    }
    
    public void onAdClicked() {
        synchronized (this.zzakd) {
            if (this.zzbss != null) {
                this.zzbss.zzef();
            }
        }
    }
    
    public void onAdClosed() {
        synchronized (this.zzakd) {
            if (this.zzbss != null) {
                this.zzbss.zzeg();
            }
        }
    }
    
    public void onAdFailedToLoad(final int n) {
        while (true) {
            while (true) {
                Label_0046: {
                    synchronized (this.zzakd) {
                        if (this.zzbsr != null) {
                            if (n != 3) {
                                break Label_0046;
                            }
                            final int n2 = 1;
                            this.zzbsr.zzaa(n2);
                            this.zzbsr = null;
                        }
                        return;
                    }
                }
                final int n2 = 2;
                continue;
            }
        }
    }
    
    public void onAdImpression() {
        synchronized (this.zzakd) {
            if (this.zzbss != null) {
                this.zzbss.zzek();
            }
        }
    }
    
    public void onAdLeftApplication() {
        synchronized (this.zzakd) {
            if (this.zzbss != null) {
                this.zzbss.zzeh();
            }
        }
    }
    
    public void onAdLoaded() {
        synchronized (this.zzakd) {
            if (this.zzbsr != null) {
                this.zzbsr.zzaa(0);
                this.zzbsr = null;
            }
            else if (this.zzbss != null) {
                this.zzbss.zzej();
            }
        }
    }
    
    public void onAdOpened() {
        synchronized (this.zzakd) {
            if (this.zzbss != null) {
                this.zzbss.zzei();
            }
        }
    }
    
    public void zza(@Nullable final zzgi zzbss) {
        synchronized (this.zzakd) {
            this.zzbss = zzbss;
        }
    }
    
    public void zza(final zzgl.zza zzbsr) {
        synchronized (this.zzakd) {
            this.zzbsr = zzbsr;
        }
    }
    
    public void zza(final zzgt zzgt) {
        synchronized (this.zzakd) {
            if (this.zzbsr != null) {
                this.zzbsr.zza(0, zzgt);
                this.zzbsr = null;
            }
            else if (this.zzbss != null) {
                this.zzbss.zzej();
            }
        }
    }
}
