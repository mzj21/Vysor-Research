// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.net.Uri;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public final class zzqv extends BroadcastReceiver
{
    protected Context mContext;
    private final zza yO;
    
    public zzqv(final zza yo) {
        this.yO = yo;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final Uri data = intent.getData();
        Object schemeSpecificPart = null;
        if (data != null) {
            schemeSpecificPart = data.getSchemeSpecificPart();
        }
        if ("com.google.android.gms".equals(schemeSpecificPart)) {
            this.yO.zzaqp();
            this.unregister();
        }
    }
    
    public void setContext(final Context mContext) {
        this.mContext = mContext;
    }
    
    public void unregister() {
        synchronized (this) {
            if (this.mContext != null) {
                this.mContext.unregisterReceiver((BroadcastReceiver)this);
            }
            this.mContext = null;
        }
    }
    
    public abstract static class zza
    {
        public abstract void zzaqp();
    }
}
