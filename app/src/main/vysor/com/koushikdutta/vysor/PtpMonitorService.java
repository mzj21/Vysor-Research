// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.vysor;

import android.os.SystemProperties;

public class PtpMonitorService extends MonitorService
{
    @Override
    protected boolean canContinue() {
        final String value = SystemProperties.get("persist.sys.usb.config");
        return value == null || !value.contains("mtp");
    }
}
