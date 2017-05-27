// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;
import android.support.annotation.Nullable;

@zziy
public class zzdl
{
    @Nullable
    public zzdk zza(@Nullable final zzdj zzdj) {
        if (zzdj == null) {
            throw new IllegalArgumentException("CSI configuration can't be null. ");
        }
        zzdk zzdk;
        if (!zzdj.zzkt()) {
            zzkn.v("CsiReporterFactory: CSI is not enabled. No CSI reporter created.");
            zzdk = null;
        }
        else {
            if (zzdj.getContext() == null) {
                throw new IllegalArgumentException("Context can't be null. Please set up context in CsiConfiguration.");
            }
            if (TextUtils.isEmpty((CharSequence)zzdj.zzhy())) {
                throw new IllegalArgumentException("AfmaVersion can't be null or empty. Please set up afmaVersion in CsiConfiguration.");
            }
            zzdk = new zzdk(zzdj.getContext(), zzdj.zzhy(), zzdj.zzku(), zzdj.zzkv());
        }
        return zzdk;
    }
}
