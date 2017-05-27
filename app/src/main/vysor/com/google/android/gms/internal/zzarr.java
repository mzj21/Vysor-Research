// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.support.customtabs.CustomTabsClient;
import android.content.ComponentName;
import java.lang.ref.WeakReference;
import android.support.customtabs.CustomTabsServiceConnection;

public class zzarr extends CustomTabsServiceConnection
{
    private WeakReference<zzars> brx;
    
    public zzarr(final zzars zzars) {
        this.brx = new WeakReference<zzars>(zzars);
    }
    
    public void onCustomTabsServiceConnected(final ComponentName componentName, final CustomTabsClient customTabsClient) {
        final zzars zzars = this.brx.get();
        if (zzars != null) {
            zzars.zza(customTabsClient);
        }
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        final zzars zzars = this.brx.get();
        if (zzars != null) {
            zzars.zzlg();
        }
    }
}
