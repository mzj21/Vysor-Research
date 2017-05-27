// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.ConnectionResult;
import java.util.Iterator;
import com.google.android.gms.common.api.zzd;
import com.google.android.gms.common.util.zza;

public class zzqi extends zzqd
{
    @Override
    public void onStop() {
        super.onStop();
        final Iterator<zzd> iterator = null.iterator();
        while (iterator.hasNext()) {
            iterator.next().release();
        }
        null.clear();
        null.zza(this);
    }
    
    @Override
    protected void zza(final ConnectionResult connectionResult, final int n) {
        null.zza(connectionResult, n);
    }
    
    @Override
    protected void zzaqk() {
        null.zzaqk();
    }
}
