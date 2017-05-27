// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.zzl;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzd;
import android.content.Context;

@zziy
public class zzfn
{
    private final Context mContext;
    private final zzd zzalo;
    private final zzgq zzals;
    private final VersionInfoParcel zzanh;
    
    zzfn(final Context mContext, final zzgq zzals, final VersionInfoParcel zzanh, final zzd zzalo) {
        this.mContext = mContext;
        this.zzals = zzals;
        this.zzanh = zzanh;
        this.zzalo = zzalo;
    }
    
    public Context getApplicationContext() {
        return this.mContext.getApplicationContext();
    }
    
    public zzl zzbf(final String s) {
        return new zzl(this.mContext, new AdSizeParcel(), s, this.zzals, this.zzanh, this.zzalo);
    }
    
    public zzl zzbg(final String s) {
        return new zzl(this.mContext.getApplicationContext(), new AdSizeParcel(), s, this.zzals, this.zzanh, this.zzalo);
    }
    
    public zzfn zzml() {
        return new zzfn(this.getApplicationContext(), this.zzals, this.zzanh, this.zzalo);
    }
}
