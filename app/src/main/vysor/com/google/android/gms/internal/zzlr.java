// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.ViewTreeObserver;
import android.view.View;
import java.lang.ref.WeakReference;

@zziy
abstract class zzlr
{
    private final WeakReference<View> zzcuq;
    
    public zzlr(final View view) {
        this.zzcuq = new WeakReference<View>(view);
    }
    
    public final void detach() {
        final ViewTreeObserver viewTreeObserver = this.getViewTreeObserver();
        if (viewTreeObserver != null) {
            this.zzb(viewTreeObserver);
        }
    }
    
    protected ViewTreeObserver getViewTreeObserver() {
        final View view = this.zzcuq.get();
        ViewTreeObserver viewTreeObserver;
        if (view == null) {
            viewTreeObserver = null;
        }
        else {
            viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver == null || !viewTreeObserver.isAlive()) {
                viewTreeObserver = null;
            }
        }
        return viewTreeObserver;
    }
    
    protected abstract void zza(final ViewTreeObserver p0);
    
    protected abstract void zzb(final ViewTreeObserver p0);
    
    public final void zzvj() {
        final ViewTreeObserver viewTreeObserver = this.getViewTreeObserver();
        if (viewTreeObserver != null) {
            this.zza(viewTreeObserver);
        }
    }
}
