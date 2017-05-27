// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Bundle;
import android.graphics.Bitmap;
import android.util.Pair;
import android.app.Activity;
import android.view.View;
import android.content.Context;
import android.app.ActivityOptions;

class ActivityOptionsCompat21
{
    private final ActivityOptions mActivityOptions;
    
    private ActivityOptionsCompat21(final ActivityOptions mActivityOptions) {
        this.mActivityOptions = mActivityOptions;
    }
    
    public static ActivityOptionsCompat21 makeCustomAnimation(final Context context, final int n, final int n2) {
        return new ActivityOptionsCompat21(ActivityOptions.makeCustomAnimation(context, n, n2));
    }
    
    public static ActivityOptionsCompat21 makeScaleUpAnimation(final View view, final int n, final int n2, final int n3, final int n4) {
        return new ActivityOptionsCompat21(ActivityOptions.makeScaleUpAnimation(view, n, n2, n3, n4));
    }
    
    public static ActivityOptionsCompat21 makeSceneTransitionAnimation(final Activity activity, final View view, final String s) {
        return new ActivityOptionsCompat21(ActivityOptions.makeSceneTransitionAnimation(activity, view, s));
    }
    
    public static ActivityOptionsCompat21 makeSceneTransitionAnimation(final Activity activity, final View[] array, final String[] array2) {
        Pair[] array3 = null;
        if (array != null) {
            array3 = new Pair[array.length];
            for (int i = 0; i < array3.length; ++i) {
                array3[i] = Pair.create((Object)array[i], (Object)array2[i]);
            }
        }
        return new ActivityOptionsCompat21(ActivityOptions.makeSceneTransitionAnimation(activity, array3));
    }
    
    public static ActivityOptionsCompat21 makeTaskLaunchBehind() {
        return new ActivityOptionsCompat21(ActivityOptions.makeTaskLaunchBehind());
    }
    
    public static ActivityOptionsCompat21 makeThumbnailScaleUpAnimation(final View view, final Bitmap bitmap, final int n, final int n2) {
        return new ActivityOptionsCompat21(ActivityOptions.makeThumbnailScaleUpAnimation(view, bitmap, n, n2));
    }
    
    public Bundle toBundle() {
        return this.mActivityOptions.toBundle();
    }
    
    public void update(final ActivityOptionsCompat21 activityOptionsCompat21) {
        this.mActivityOptions.update(activityOptionsCompat21.mActivityOptions);
    }
}
