// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.util;

import android.os.Build$VERSION;

public final class zzs
{
    public static boolean zzaxk() {
        return zzhk(11);
    }
    
    public static boolean zzaxl() {
        return zzhk(12);
    }
    
    public static boolean zzaxm() {
        return zzhk(13);
    }
    
    public static boolean zzaxn() {
        return zzhk(14);
    }
    
    public static boolean zzaxo() {
        return zzhk(16);
    }
    
    public static boolean zzaxp() {
        return zzhk(17);
    }
    
    public static boolean zzaxq() {
        return zzhk(18);
    }
    
    public static boolean zzaxr() {
        return zzhk(19);
    }
    
    public static boolean zzaxs() {
        return zzhk(20);
    }
    
    @Deprecated
    public static boolean zzaxt() {
        return zzaxu();
    }
    
    public static boolean zzaxu() {
        return zzhk(21);
    }
    
    public static boolean zzaxv() {
        return zzhk(23);
    }
    
    public static boolean zzaxw() {
        return zzhk(24);
    }
    
    private static boolean zzhk(final int n) {
        return Build$VERSION.SDK_INT >= n;
    }
}
