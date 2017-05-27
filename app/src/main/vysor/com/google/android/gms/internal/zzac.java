// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.pm.PackageManager$NameNotFoundException;
import org.apache.http.client.HttpClient;
import android.net.http.AndroidHttpClient;
import android.os.Build$VERSION;
import java.io.File;
import android.content.Context;

public class zzac
{
    public static zzl zza(final Context context) {
        return zza(context, null);
    }
    
    public static zzl zza(final Context context, zzy zzy) {
        final File file = new File(context.getCacheDir(), "volley");
        String string = "volley/0";
        while (true) {
            try {
                final String packageName = context.getPackageName();
                string = new StringBuilder(12 + String.valueOf(packageName).length()).append(packageName).append("/").append(context.getPackageManager().getPackageInfo(packageName, 0).versionCode).toString();
                if (zzy == null) {
                    if (Build$VERSION.SDK_INT >= 9) {
                        zzy = new zzz();
                    }
                    else {
                        zzy = new zzw((HttpClient)AndroidHttpClient.newInstance(string));
                    }
                }
                final zzl zzl = new zzl(new zzv(file), new zzt(zzy));
                zzl.start();
                return zzl;
            }
            catch (PackageManager$NameNotFoundException ex) {
                continue;
            }
            break;
        }
    }
}
