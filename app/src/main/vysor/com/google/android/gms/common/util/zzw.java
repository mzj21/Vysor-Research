// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.util;

import com.google.android.gms.common.internal.zzg;
import java.util.regex.Pattern;

public class zzw
{
    private static final Pattern EZ;
    
    static {
        EZ = Pattern.compile("\\$\\{(.*?)\\}");
    }
    
    public static boolean zzij(final String s) {
        return s == null || zzg.BB.zzb(s);
    }
}
