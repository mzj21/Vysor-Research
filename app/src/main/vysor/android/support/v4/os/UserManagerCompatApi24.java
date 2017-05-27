// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.os;

import android.os.UserManager;
import android.content.Context;

public class UserManagerCompatApi24
{
    public static boolean isUserUnlocked(final Context context) {
        return ((UserManager)context.getSystemService((Class)UserManager.class)).isUserUnlocked();
    }
}
