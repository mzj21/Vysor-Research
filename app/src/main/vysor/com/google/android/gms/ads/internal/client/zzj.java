// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.internal.zziy;

@zziy
public final class zzj extends zzw.zza
{
    private final AppEventListener zzaxo;
    
    public zzj(final AppEventListener zzaxo) {
        this.zzaxo = zzaxo;
    }
    
    public void onAppEvent(final String s, final String s2) {
        this.zzaxo.onAppEvent(s, s2);
    }
}
