// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.content.pm.ActivityInfo;
import android.content.Intent;
import android.app.Activity;

class NavUtilsJB
{
    public static Intent getParentActivityIntent(final Activity activity) {
        return activity.getParentActivityIntent();
    }
    
    public static String getParentActivityName(final ActivityInfo activityInfo) {
        return activityInfo.parentActivityName;
    }
    
    public static void navigateUpTo(final Activity activity, final Intent intent) {
        activity.navigateUpTo(intent);
    }
    
    public static boolean shouldUpRecreateTask(final Activity activity, final Intent intent) {
        return activity.shouldUpRecreateTask(intent);
    }
}
