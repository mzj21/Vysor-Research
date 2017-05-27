// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.util;

import android.util.Log;
import android.annotation.TargetApi;
import java.io.File;
import android.content.Context;

public class zzx
{
    @TargetApi(21)
    public static File getNoBackupFilesDir(final Context context) {
        File file;
        if (zzs.zzaxu()) {
            file = context.getNoBackupFilesDir();
        }
        else {
            file = zze(new File(context.getApplicationInfo().dataDir, "no_backup"));
        }
        return file;
    }
    
    private static File zze(File file) {
        synchronized (zzx.class) {
            if (!file.exists() && !file.mkdirs() && !file.exists()) {
                final String value = String.valueOf(file.getPath());
                String concat;
                if (value.length() != 0) {
                    concat = "Unable to create no-backup dir ".concat(value);
                }
                else {
                    concat = new String("Unable to create no-backup dir ");
                }
                Log.w("SupportV4Utils", concat);
                file = null;
            }
            return file;
        }
    }
}
