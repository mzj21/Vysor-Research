// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.ViewGroup$LayoutParams;
import android.view.View;
import com.google.android.gms.common.internal.zzac;
import android.view.ViewGroup;
import com.google.android.gms.ads.internal.overlay.zzk;
import android.content.Context;

@zziy
public class zzls
{
    private final Context mContext;
    private final zzlt zzbkr;
    private zzk zzcay;
    private final ViewGroup zzcur;
    
    public zzls(final Context context, final ViewGroup viewGroup, final zzlt zzlt) {
        this(context, viewGroup, zzlt, null);
    }
    
    zzls(final Context mContext, final ViewGroup zzcur, final zzlt zzbkr, final zzk zzcay) {
        this.mContext = mContext;
        this.zzcur = zzcur;
        this.zzbkr = zzbkr;
        this.zzcay = zzcay;
    }
    
    public void onDestroy() {
        zzac.zzhq("onDestroy must be called from the UI thread.");
        if (this.zzcay != null) {
            this.zzcay.destroy();
            this.zzcur.removeView((View)this.zzcay);
            this.zzcay = null;
        }
    }
    
    public void onPause() {
        zzac.zzhq("onPause must be called from the UI thread.");
        if (this.zzcay != null) {
            this.zzcay.pause();
        }
    }
    
    public void zza(final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        if (this.zzcay == null) {
            zzdm.zza(this.zzbkr.zzwa().zzkz(), this.zzbkr.zzvz(), "vpr2");
            this.zzcay = new zzk(this.mContext, this.zzbkr, n5, b, this.zzbkr.zzwa().zzkz());
            this.zzcur.addView((View)this.zzcay, 0, new ViewGroup$LayoutParams(-1, -1));
            this.zzcay.zzd(n, n2, n3, n4);
            this.zzbkr.zzvr().zzan(false);
        }
    }
    
    public void zze(final int n, final int n2, final int n3, final int n4) {
        zzac.zzhq("The underlay may only be modified from the UI thread.");
        if (this.zzcay != null) {
            this.zzcay.zzd(n, n2, n3, n4);
        }
    }
    
    public zzk zzvk() {
        zzac.zzhq("getAdVideoUnderlay must be called from the UI thread.");
        return this.zzcay;
    }
}
