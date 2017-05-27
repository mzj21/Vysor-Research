// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.Window;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout;
import android.app.Activity;
import android.content.Context;
import android.widget.PopupWindow;
import android.annotation.TargetApi;

@zziy
@TargetApi(19)
public class zzio extends zzim
{
    private Object zzcde;
    private PopupWindow zzcdf;
    private boolean zzcdg;
    
    zzio(final Context context, final zzke.zza zza, final zzlt zzlt, final zzil.zza zza2) {
        super(context, zza, zzlt, zza2);
        this.zzcde = new Object();
        this.zzcdg = false;
    }
    
    private void zzrc() {
        synchronized (this.zzcde) {
            this.zzcdg = true;
            if (this.mContext instanceof Activity && ((Activity)this.mContext).isDestroyed()) {
                this.zzcdf = null;
            }
            if (this.zzcdf != null) {
                if (this.zzcdf.isShowing()) {
                    this.zzcdf.dismiss();
                }
                this.zzcdf = null;
            }
        }
    }
    
    @Override
    public void cancel() {
        this.zzrc();
        super.cancel();
    }
    
    @Override
    protected void zzal(final int n) {
        this.zzrc();
        super.zzal(n);
    }
    
    @Override
    protected void zzrb() {
        while (true) {
            Label_0177: {
                if (!(this.mContext instanceof Activity)) {
                    break Label_0177;
                }
                final Window window = ((Activity)this.mContext).getWindow();
                if (window != null && window.getDecorView() != null && !((Activity)this.mContext).isDestroyed()) {
                    final FrameLayout frameLayout = new FrameLayout(this.mContext);
                    frameLayout.setLayoutParams(new ViewGroup$LayoutParams(-1, -1));
                    frameLayout.addView(this.zzbkr.getView(), -1, -1);
                    synchronized (this.zzcde) {
                        if (this.zzcdg) {
                            return;
                        }
                    }
                    (this.zzcdf = new PopupWindow((View)frameLayout, 1, 1, false)).setOutsideTouchable(true);
                    this.zzcdf.setClippingEnabled(false);
                    com.google.android.gms.ads.internal.util.client.zzb.zzdd("Displaying the 1x1 popup off the screen.");
                    while (true) {
                        try {
                            this.zzcdf.showAtLocation(window.getDecorView(), 0, -1, -1);
                            // monitorexit(o)
                            return;
                        }
                        catch (Exception ex) {
                            this.zzcdf = null;
                            continue;
                        }
                        break;
                    }
                    break Label_0177;
                }
                return;
            }
            final Window window = null;
            continue;
        }
    }
}
