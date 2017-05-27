// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.PendingIntent;
import android.graphics.Rect;
import android.graphics.Bitmap;
import android.util.Pair;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.app.ActivityOptions;

class ActivityOptionsCompat24
{
    private final ActivityOptions mActivityOptions;
    
    private ActivityOptionsCompat24(final ActivityOptions mActivityOptions) {
        this.mActivityOptions = mActivityOptions;
    }
    
    public static ActivityOptionsCompat24 makeBasic() {
        return new ActivityOptionsCompat24(ActivityOptions.makeBasic());
    }
    
    public static ActivityOptionsCompat24 makeClipRevealAnimation(final View view, final int n, final int n2, final int n3, final int n4) {
        return new ActivityOptionsCompat24(ActivityOptions.makeClipRevealAnimation(view, n, n2, n3, n4));
    }
    
    public static ActivityOptionsCompat24 makeCustomAnimation(final Context context, final int n, final int n2) {
        return new ActivityOptionsCompat24(ActivityOptions.makeCustomAnimation(context, n, n2));
    }
    
    public static ActivityOptionsCompat24 makeScaleUpAnimation(final View view, final int n, final int n2, final int n3, final int n4) {
        return new ActivityOptionsCompat24(ActivityOptions.makeScaleUpAnimation(view, n, n2, n3, n4));
    }
    
    public static ActivityOptionsCompat24 makeSceneTransitionAnimation(final Activity activity, final View view, final String s) {
        return new ActivityOptionsCompat24(ActivityOptions.makeSceneTransitionAnimation(activity, view, s));
    }
    
    public static ActivityOptionsCompat24 makeSceneTransitionAnimation(final Activity activity, final View[] array, final String[] array2) {
        Pair[] array3 = null;
        if (array != null) {
            array3 = new Pair[array.length];
            for (int i = 0; i < array3.length; ++i) {
                array3[i] = Pair.create((Object)array[i], (Object)array2[i]);
            }
        }
        return new ActivityOptionsCompat24(ActivityOptions.makeSceneTransitionAnimation(activity, array3));
    }
    
    public static ActivityOptionsCompat24 makeTaskLaunchBehind() {
        return new ActivityOptionsCompat24(ActivityOptions.makeTaskLaunchBehind());
    }
    
    public static ActivityOptionsCompat24 makeThumbnailScaleUpAnimation(final View view, final Bitmap bitmap, final int n, final int n2) {
        return new ActivityOptionsCompat24(ActivityOptions.makeThumbnailScaleUpAnimation(view, bitmap, n, n2));
    }
    
    public Rect getLaunchBounds() {
        return this.mActivityOptions.getLaunchBounds();
    }
    
    public void requestUsageTimeReport(final PendingIntent pendingIntent) {
        this.mActivityOptions.requestUsageTimeReport(pendingIntent);
    }
    
    public ActivityOptionsCompat24 setLaunchBounds(@Nullable final Rect launchBounds) {
        return new ActivityOptionsCompat24(this.mActivityOptions.setLaunchBounds(launchBounds));
    }
    
    public Bundle toBundle() {
        return this.mActivityOptions.toBundle();
    }
    
    public void update(final ActivityOptionsCompat24 activityOptionsCompat24) {
        this.mActivityOptions.update(activityOptionsCompat24.mActivityOptions);
    }
}
