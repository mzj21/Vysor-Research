// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebChromeClient$CustomViewCallback;
import android.view.View;
import android.annotation.TargetApi;

@zziy
@TargetApi(14)
public final class zzmc extends zzma
{
    public zzmc(final zzlt zzlt) {
        super(zzlt);
    }
    
    public void onShowCustomView(final View view, final int n, final WebChromeClient$CustomViewCallback webChromeClient$CustomViewCallback) {
        this.zza(view, n, webChromeClient$CustomViewCallback);
    }
}
