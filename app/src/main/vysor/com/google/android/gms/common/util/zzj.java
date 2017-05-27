// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.util;

import android.os.SystemClock;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.annotation.TargetApi;
import android.os.PowerManager;
import android.content.IntentFilter;

public final class zzj
{
    private static IntentFilter EP;
    private static long EQ;
    private static float ER;
    
    static {
        zzj.EP = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        zzj.ER = Float.NaN;
    }
    
    @TargetApi(20)
    public static boolean zzb(final PowerManager powerManager) {
        boolean b;
        if (zzs.zzaxs()) {
            b = powerManager.isInteractive();
        }
        else {
            b = powerManager.isScreenOn();
        }
        return b;
    }
    
    @TargetApi(20)
    public static int zzcn(final Context context) {
        int n = 1;
        int n2;
        if (context == null || context.getApplicationContext() == null) {
            n2 = -1;
        }
        else {
            final Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver)null, zzj.EP);
            int intExtra;
            if (registerReceiver == null) {
                intExtra = 0;
            }
            else {
                intExtra = registerReceiver.getIntExtra("plugged", 0);
            }
            int n3;
            if ((intExtra & 0x7) != 0x0) {
                n3 = n;
            }
            else {
                n3 = 0;
            }
            final PowerManager powerManager = (PowerManager)context.getSystemService("power");
            if (powerManager == null) {
                n2 = -1;
            }
            else {
                int n4;
                if (zzb(powerManager)) {
                    n4 = n;
                }
                else {
                    n4 = 0;
                }
                final int n5 = n4 << 1;
                if (n3 == 0) {
                    n = 0;
                }
                n2 = (n5 | n);
            }
        }
        return n2;
    }
    
    public static float zzco(final Context context) {
        synchronized (zzj.class) {
            float n;
            if (SystemClock.elapsedRealtime() - zzj.EQ < 60000L && !Float.isNaN(zzj.ER)) {
                n = zzj.ER;
            }
            else {
                final Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver)null, zzj.EP);
                if (registerReceiver != null) {
                    zzj.ER = registerReceiver.getIntExtra("level", -1) / registerReceiver.getIntExtra("scale", -1);
                }
                zzj.EQ = SystemClock.elapsedRealtime();
                n = zzj.ER;
            }
            return n;
        }
    }
}
