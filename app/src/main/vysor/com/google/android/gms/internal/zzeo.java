// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

@zziy
public class zzeo extends zzej.zza
{
    private final NativeCustomTemplateAd.OnCustomClickListener zzbly;
    
    public zzeo(final NativeCustomTemplateAd.OnCustomClickListener zzbly) {
        this.zzbly = zzbly;
    }
    
    public void zza(final zzef zzef, final String s) {
        this.zzbly.onCustomClick(new zzeg(zzef), s);
    }
}
