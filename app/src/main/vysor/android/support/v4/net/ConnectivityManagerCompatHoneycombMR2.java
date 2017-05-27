// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.net;

import android.net.NetworkInfo;
import android.net.ConnectivityManager;

class ConnectivityManagerCompatHoneycombMR2
{
    public static boolean isActiveNetworkMetered(final ConnectivityManager connectivityManager) {
        boolean b = true;
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            switch (activeNetworkInfo.getType()) {
                case 1:
                case 7:
                case 9: {
                    b = false;
                }
            }
        }
        return b;
    }
}
