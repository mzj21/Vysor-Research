// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.content.pm.ApplicationInfo;
import com.google.android.gms.common.util.zzs;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzdq;
import com.google.android.gms.internal.zzlt;
import android.content.Context;
import com.google.android.gms.internal.zziy;

@zziy
public abstract class zzj
{
    @Nullable
    public abstract zzi zza(final Context p0, final zzlt p1, final int p2, final boolean p3, final zzdq p4);
    
    protected boolean zzh(final zzlt zzlt) {
        return zzlt.zzdt().zzaxj;
    }
    
    protected boolean zzq(final Context context) {
        final ApplicationInfo applicationInfo = context.getApplicationInfo();
        return zzs.zzaxn() && (applicationInfo == null || applicationInfo.targetSdkVersion >= 11);
    }
}
