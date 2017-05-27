// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.vysor;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

public class PowerMonitorService extends MonitorService
{
    @Override
    protected boolean canContinue() {
        boolean b = true;
        final int intExtra = this.getBaseContext().registerReceiver((BroadcastReceiver)null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("plugged", -1);
        if (intExtra != 2 && intExtra != (b ? 1 : 0)) {
            b = false;
        }
        return b;
    }
}
