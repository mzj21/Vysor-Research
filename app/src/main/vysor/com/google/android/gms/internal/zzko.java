// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzu;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;

@zziy
public class zzko extends Handler
{
    public zzko(final Looper looper) {
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        try {
            super.handleMessage(message);
        }
        catch (Exception ex) {
            zzu.zzgd().zza(ex, "AdMobHandler.handleMessage");
        }
    }
}
