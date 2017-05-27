// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzu;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.app.Activity;
import android.view.View;

@zziy
public final class zzlf
{
    private final View mView;
    private Activity zzctd;
    private boolean zzcte;
    private boolean zzctf;
    private boolean zzctg;
    private ViewTreeObserver$OnGlobalLayoutListener zzcth;
    private ViewTreeObserver$OnScrollChangedListener zzcti;
    
    public zzlf(final Activity zzctd, final View mView, final ViewTreeObserver$OnGlobalLayoutListener zzcth, final ViewTreeObserver$OnScrollChangedListener zzcti) {
        this.zzctd = zzctd;
        this.mView = mView;
        this.zzcth = zzcth;
        this.zzcti = zzcti;
    }
    
    private void zzvc() {
        if (!this.zzcte) {
            if (this.zzcth != null) {
                if (this.zzctd != null) {
                    zzu.zzfz().zza(this.zzctd, this.zzcth);
                }
                zzu.zzgx().zza(this.mView, this.zzcth);
            }
            if (this.zzcti != null) {
                if (this.zzctd != null) {
                    zzu.zzfz().zza(this.zzctd, this.zzcti);
                }
                zzu.zzgx().zza(this.mView, this.zzcti);
            }
            this.zzcte = true;
        }
    }
    
    private void zzvd() {
        if (this.zzctd != null && this.zzcte) {
            if (this.zzcth != null && this.zzctd != null) {
                zzu.zzgb().zzb(this.zzctd, this.zzcth);
            }
            if (this.zzcti != null && this.zzctd != null) {
                zzu.zzfz().zzb(this.zzctd, this.zzcti);
            }
            this.zzcte = false;
        }
    }
    
    public void onAttachedToWindow() {
        this.zzctf = true;
        if (this.zzctg) {
            this.zzvc();
        }
    }
    
    public void onDetachedFromWindow() {
        this.zzctf = false;
        this.zzvd();
    }
    
    public void zzl(final Activity zzctd) {
        this.zzctd = zzctd;
    }
    
    public void zzva() {
        this.zzctg = true;
        if (this.zzctf) {
            this.zzvc();
        }
    }
    
    public void zzvb() {
        this.zzctg = false;
        this.zzvd();
    }
}
