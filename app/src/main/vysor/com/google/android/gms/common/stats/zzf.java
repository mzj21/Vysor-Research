// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import java.util.List;
import android.text.TextUtils;
import android.os.Process;
import android.os.PowerManager$WakeLock;

public class zzf
{
    public static String zza(final PowerManager$WakeLock powerManager$WakeLock, String s) {
        final String value = String.valueOf(String.valueOf(Process.myPid() << 32 | System.identityHashCode(powerManager$WakeLock)));
        if (TextUtils.isEmpty((CharSequence)s)) {
            s = "";
        }
        final String value2 = String.valueOf(s);
        String concat;
        if (value2.length() != 0) {
            concat = value.concat(value2);
        }
        else {
            concat = new String(value);
        }
        return concat;
    }
    
    static String zzih(String s) {
        if ("com.google.android.gms".equals(s)) {
            s = null;
        }
        return s;
    }
    
    static List<String> zzz(List<String> list) {
        if (list != null && list.size() == 1 && "com.google.android.gms".equals(list.get(0))) {
            list = null;
        }
        return list;
    }
}
