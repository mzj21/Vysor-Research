// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.util;

import android.content.Context;
import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.content.res.Resources;

public final class zzi
{
    private static Boolean EL;
    private static Boolean EM;
    private static Boolean EN;
    private static Boolean EO;
    
    public static boolean zzb(final Resources resources) {
        boolean booleanValue = false;
        if (resources != null) {
            if (zzi.EL == null) {
                boolean b;
                if ((0xF & resources.getConfiguration().screenLayout) > 3) {
                    b = true;
                }
                else {
                    b = false;
                }
                boolean b2 = false;
                Label_0055: {
                    if (!zzs.zzaxk() || !b) {
                        final boolean zzc = zzc(resources);
                        b2 = false;
                        if (!zzc) {
                            break Label_0055;
                        }
                    }
                    b2 = true;
                }
                zzi.EL = b2;
            }
            booleanValue = zzi.EL;
        }
        return booleanValue;
    }
    
    @TargetApi(13)
    private static boolean zzc(final Resources resources) {
        if (zzi.EM == null) {
            final Configuration configuration = resources.getConfiguration();
            zzi.EM = (zzs.zzaxm() && (0xF & configuration.screenLayout) <= 3 && configuration.smallestScreenWidthDp >= 600);
        }
        return zzi.EM;
    }
    
    @TargetApi(20)
    public static boolean zzcl(final Context context) {
        if (zzi.EN == null) {
            zzi.EN = (zzs.zzaxs() && context.getPackageManager().hasSystemFeature("android.hardware.type.watch"));
        }
        return zzi.EN;
    }
    
    @TargetApi(21)
    public static boolean zzcm(final Context context) {
        if (zzi.EO == null) {
            zzi.EO = (zzs.zzaxu() && context.getPackageManager().hasSystemFeature("cn.google"));
        }
        return zzi.EO;
    }
}
