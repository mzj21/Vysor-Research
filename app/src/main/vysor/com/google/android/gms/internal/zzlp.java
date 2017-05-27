// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzu;
import android.view.ViewTreeObserver;
import android.view.View;
import java.lang.ref.WeakReference;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

@zziy
class zzlp extends zzlr implements ViewTreeObserver$OnGlobalLayoutListener
{
    private final WeakReference<ViewTreeObserver$OnGlobalLayoutListener> zzcup;
    
    public zzlp(final View view, final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener) {
        super(view);
        this.zzcup = new WeakReference<ViewTreeObserver$OnGlobalLayoutListener>(viewTreeObserver$OnGlobalLayoutListener);
    }
    
    public void onGlobalLayout() {
        final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener = this.zzcup.get();
        if (viewTreeObserver$OnGlobalLayoutListener != null) {
            viewTreeObserver$OnGlobalLayoutListener.onGlobalLayout();
        }
        else {
            this.detach();
        }
    }
    
    @Override
    protected void zza(final ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
    }
    
    @Override
    protected void zzb(final ViewTreeObserver viewTreeObserver) {
        zzu.zzgb().zza(viewTreeObserver, (ViewTreeObserver$OnGlobalLayoutListener)this);
    }
}
