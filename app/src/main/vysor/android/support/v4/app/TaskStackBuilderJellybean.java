// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.PendingIntent;
import android.os.Bundle;
import android.content.Intent;
import android.content.Context;

class TaskStackBuilderJellybean
{
    public static PendingIntent getActivitiesPendingIntent(final Context context, final int n, final Intent[] array, final int n2, final Bundle bundle) {
        return PendingIntent.getActivities(context, n, array, n2, bundle);
    }
}
