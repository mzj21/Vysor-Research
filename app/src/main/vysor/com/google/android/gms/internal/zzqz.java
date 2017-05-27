// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.common.internal.zzac;
import android.app.Activity;

public class zzqz
{
    private final Object yX;
    
    public zzqz(final Activity yx) {
        zzac.zzb(yx, "Activity must not be null");
        zzac.zzb(zzs.zzaxk() || yx instanceof FragmentActivity, (Object)"This Activity is not supported before platform version 11 (3.0 Honeycomb). Please use FragmentActivity instead.");
        this.yX = yx;
    }
    
    public boolean zzasn() {
        return this.yX instanceof FragmentActivity;
    }
    
    public Activity zzaso() {
        return (Activity)this.yX;
    }
    
    public FragmentActivity zzasp() {
        return (FragmentActivity)this.yX;
    }
}
