// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;

class TaskStackBuilderHoneycomb
{
    public static PendingIntent getActivitiesPendingIntent(final Context context, final int n, final Intent[] array, final int n2) {
        return PendingIntent.getActivities(context, n, array, n2);
    }
}
