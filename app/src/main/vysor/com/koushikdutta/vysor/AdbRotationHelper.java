// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.vysor;

public class AdbRotationHelper
{
    public static void forceRotation(final int n) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Runtime.getRuntime().exec("/system/bin/content insert --uri content://settings/system --bind name:s:accelerometer_rotation --bind value:i:0").waitFor();
                    Runtime.getRuntime().exec("/system/bin/content insert --uri content://settings/system --bind name:s:user_rotation --bind value:i:" + n).waitFor();
                }
                catch (Exception ex) {}
            }
        }.start();
    }
    
    public static void resetForcedRotation() {
        try {
            Runtime.getRuntime().exec("/system/bin/content insert --uri content://settings/system --bind name:s:accelerometer_rotation --bind value:i:1").waitFor();
        }
        catch (Exception ex) {}
    }
}
