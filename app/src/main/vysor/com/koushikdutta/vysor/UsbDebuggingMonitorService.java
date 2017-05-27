// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.vysor;

import android.provider.Settings$Secure;

public class UsbDebuggingMonitorService extends MonitorService
{
    @Override
    protected boolean canContinue() {
        boolean b = true;
        if (Settings$Secure.getInt(this.getContentResolver(), "adb_enabled", 0) != (b ? 1 : 0)) {
            b = false;
        }
        return b;
    }
}
