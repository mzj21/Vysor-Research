// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.View;

@zziy
public class zzlo
{
    public void zza(final View view, final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener) {
        new zzlp(view, viewTreeObserver$OnGlobalLayoutListener).zzvj();
    }
    
    public void zza(final View view, final ViewTreeObserver$OnScrollChangedListener viewTreeObserver$OnScrollChangedListener) {
        new zzlq(view, viewTreeObserver$OnScrollChangedListener).zzvj();
    }
}
