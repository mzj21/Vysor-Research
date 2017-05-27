// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.os;

import android.content.Context;

public class UserManagerCompat
{
    @Deprecated
    public static boolean isUserRunningAndLocked(final Context context) {
        return !isUserUnlocked(context);
    }
    
    @Deprecated
    public static boolean isUserRunningAndUnlocked(final Context context) {
        return isUserUnlocked(context);
    }
    
    public static boolean isUserUnlocked(final Context context) {
        return !BuildCompat.isAtLeastN() || UserManagerCompatApi24.isUserUnlocked(context);
    }
}
