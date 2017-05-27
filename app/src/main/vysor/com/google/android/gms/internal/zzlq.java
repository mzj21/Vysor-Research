// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.ViewTreeObserver;
import android.view.View;
import java.lang.ref.WeakReference;
import android.view.ViewTreeObserver$OnScrollChangedListener;

@zziy
class zzlq extends zzlr implements ViewTreeObserver$OnScrollChangedListener
{
    private final WeakReference<ViewTreeObserver$OnScrollChangedListener> zzcup;
    
    public zzlq(final View view, final ViewTreeObserver$OnScrollChangedListener viewTreeObserver$OnScrollChangedListener) {
        super(view);
        this.zzcup = new WeakReference<ViewTreeObserver$OnScrollChangedListener>(viewTreeObserver$OnScrollChangedListener);
    }
    
    public void onScrollChanged() {
        final ViewTreeObserver$OnScrollChangedListener viewTreeObserver$OnScrollChangedListener = this.zzcup.get();
        if (viewTreeObserver$OnScrollChangedListener != null) {
            viewTreeObserver$OnScrollChangedListener.onScrollChanged();
        }
        else {
            this.detach();
        }
    }
    
    @Override
    protected void zza(final ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.addOnScrollChangedListener((ViewTreeObserver$OnScrollChangedListener)this);
    }
    
    @Override
    protected void zzb(final ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.removeOnScrollChangedListener((ViewTreeObserver$OnScrollChangedListener)this);
    }
}
