// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Bundle;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.util.Pair;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.app.ActivityOptions;

class ActivityOptionsCompat23
{
    private final ActivityOptions mActivityOptions;
    
    private ActivityOptionsCompat23(final ActivityOptions mActivityOptions) {
        this.mActivityOptions = mActivityOptions;
    }
    
    public static ActivityOptionsCompat23 makeBasic() {
        return new ActivityOptionsCompat23(ActivityOptions.makeBasic());
    }
    
    public static ActivityOptionsCompat23 makeClipRevealAnimation(final View view, final int n, final int n2, final int n3, final int n4) {
        return new ActivityOptionsCompat23(ActivityOptions.makeClipRevealAnimation(view, n, n2, n3, n4));
    }
    
    public static ActivityOptionsCompat23 makeCustomAnimation(final Context context, final int n, final int n2) {
        return new ActivityOptionsCompat23(ActivityOptions.makeCustomAnimation(context, n, n2));
    }
    
    public static ActivityOptionsCompat23 makeScaleUpAnimation(final View view, final int n, final int n2, final int n3, final int n4) {
        return new ActivityOptionsCompat23(ActivityOptions.makeScaleUpAnimation(view, n, n2, n3, n4));
    }
    
    public static ActivityOptionsCompat23 makeSceneTransitionAnimation(final Activity activity, final View view, final String s) {
        return new ActivityOptionsCompat23(ActivityOptions.makeSceneTransitionAnimation(activity, view, s));
    }
    
    public static ActivityOptionsCompat23 makeSceneTransitionAnimation(final Activity activity, final View[] array, final String[] array2) {
        Pair[] array3 = null;
        if (array != null) {
            array3 = new Pair[array.length];
            for (int i = 0; i < array3.length; ++i) {
                array3[i] = Pair.create((Object)array[i], (Object)array2[i]);
            }
        }
        return new ActivityOptionsCompat23(ActivityOptions.makeSceneTransitionAnimation(activity, array3));
    }
    
    public static ActivityOptionsCompat23 makeTaskLaunchBehind() {
        return new ActivityOptionsCompat23(ActivityOptions.makeTaskLaunchBehind());
    }
    
    public static ActivityOptionsCompat23 makeThumbnailScaleUpAnimation(final View view, final Bitmap bitmap, final int n, final int n2) {
        return new ActivityOptionsCompat23(ActivityOptions.makeThumbnailScaleUpAnimation(view, bitmap, n, n2));
    }
    
    public void requestUsageTimeReport(final PendingIntent pendingIntent) {
        this.mActivityOptions.requestUsageTimeReport(pendingIntent);
    }
    
    public Bundle toBundle() {
        return this.mActivityOptions.toBundle();
    }
    
    public void update(final ActivityOptionsCompat23 activityOptionsCompat23) {
        this.mActivityOptions.update(activityOptionsCompat23.mActivityOptions);
    }
}
