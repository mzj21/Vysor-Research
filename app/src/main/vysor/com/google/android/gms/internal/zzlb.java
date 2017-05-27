// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzu;
import android.content.Context;
import com.google.android.gms.ads.internal.util.client.zzc;

@zziy
public final class zzlb extends zzkm
{
    private final String zzae;
    private final zzc zzcsz;
    
    public zzlb(final Context context, final String s, final String s2) {
        this(s2, zzu.zzfz().zzg(context, s));
    }
    
    public zzlb(final String zzae, final String s) {
        this.zzcsz = new zzc(s);
        this.zzae = zzae;
    }
    
    @Override
    public void onStop() {
    }
    
    @Override
    public void zzfc() {
        this.zzcsz.zzcy(this.zzae);
    }
}
