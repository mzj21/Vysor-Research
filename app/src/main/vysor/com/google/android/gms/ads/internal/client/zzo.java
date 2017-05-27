// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.internal.zziy;
import com.google.android.gms.ads.AdListener;

@zziy
public class zzo extends AdListener
{
    private final Object lock;
    private AdListener zzayi;
    
    public zzo() {
        this.lock = new Object();
    }
    
    @Override
    public void onAdClosed() {
        synchronized (this.lock) {
            if (this.zzayi != null) {
                this.zzayi.onAdClosed();
            }
        }
    }
    
    @Override
    public void onAdFailedToLoad(final int n) {
        synchronized (this.lock) {
            if (this.zzayi != null) {
                this.zzayi.onAdFailedToLoad(n);
            }
        }
    }
    
    @Override
    public void onAdLeftApplication() {
        synchronized (this.lock) {
            if (this.zzayi != null) {
                this.zzayi.onAdLeftApplication();
            }
        }
    }
    
    @Override
    public void onAdLoaded() {
        synchronized (this.lock) {
            if (this.zzayi != null) {
                this.zzayi.onAdLoaded();
            }
        }
    }
    
    @Override
    public void onAdOpened() {
        synchronized (this.lock) {
            if (this.zzayi != null) {
                this.zzayi.onAdOpened();
            }
        }
    }
    
    public void zza(final AdListener zzayi) {
        synchronized (this.lock) {
            this.zzayi = zzayi;
        }
    }
}
