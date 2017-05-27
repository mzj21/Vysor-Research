// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.content.Intent;

public interface zzrb
{
    void startActivityForResult(final Intent p0, final int p1);
    
     <T extends zzra> T zza(final String p0, final Class<T> p1);
    
    void zza(final String p0, @NonNull final zzra p1);
    
    Activity zzasq();
}
