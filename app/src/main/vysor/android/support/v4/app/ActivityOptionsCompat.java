// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Bundle;
import android.app.PendingIntent;
import android.support.annotation.Nullable;
import android.graphics.Rect;
import android.graphics.Bitmap;
import android.support.v4.util.Pair;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.os.Build$VERSION;

public class ActivityOptionsCompat
{
    public static final String EXTRA_USAGE_TIME_REPORT = "android.activity.usage_time";
    public static final String EXTRA_USAGE_TIME_REPORT_PACKAGES = "android.usage_time_packages";
    
    public static ActivityOptionsCompat makeBasic() {
        ActivityOptionsCompat activityOptionsCompat;
        if (Build$VERSION.SDK_INT >= 24) {
            activityOptionsCompat = new ActivityOptionsImpl24(ActivityOptionsCompat24.makeBasic());
        }
        else if (Build$VERSION.SDK_INT >= 23) {
            activityOptionsCompat = new ActivityOptionsImpl23(ActivityOptionsCompat23.makeBasic());
        }
        else {
            activityOptionsCompat = new ActivityOptionsCompat();
        }
        return activityOptionsCompat;
    }
    
    public static ActivityOptionsCompat makeClipRevealAnimation(final View view, final int n, final int n2, final int n3, final int n4) {
        ActivityOptionsCompat activityOptionsCompat;
        if (Build$VERSION.SDK_INT >= 24) {
            activityOptionsCompat = new ActivityOptionsImpl24(ActivityOptionsCompat24.makeClipRevealAnimation(view, n, n2, n3, n4));
        }
        else if (Build$VERSION.SDK_INT >= 23) {
            activityOptionsCompat = new ActivityOptionsImpl23(ActivityOptionsCompat23.makeClipRevealAnimation(view, n, n2, n3, n4));
        }
        else {
            activityOptionsCompat = new ActivityOptionsCompat();
        }
        return activityOptionsCompat;
    }
    
    public static ActivityOptionsCompat makeCustomAnimation(final Context context, final int n, final int n2) {
        ActivityOptionsCompat activityOptionsCompat;
        if (Build$VERSION.SDK_INT >= 24) {
            activityOptionsCompat = new ActivityOptionsImpl24(ActivityOptionsCompat24.makeCustomAnimation(context, n, n2));
        }
        else if (Build$VERSION.SDK_INT >= 23) {
            activityOptionsCompat = new ActivityOptionsImpl23(ActivityOptionsCompat23.makeCustomAnimation(context, n, n2));
        }
        else if (Build$VERSION.SDK_INT >= 21) {
            activityOptionsCompat = new ActivityOptionsImpl21(ActivityOptionsCompat21.makeCustomAnimation(context, n, n2));
        }
        else if (Build$VERSION.SDK_INT >= 16) {
            activityOptionsCompat = new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeCustomAnimation(context, n, n2));
        }
        else {
            activityOptionsCompat = new ActivityOptionsCompat();
        }
        return activityOptionsCompat;
    }
    
    public static ActivityOptionsCompat makeScaleUpAnimation(final View view, final int n, final int n2, final int n3, final int n4) {
        ActivityOptionsCompat activityOptionsCompat;
        if (Build$VERSION.SDK_INT >= 24) {
            activityOptionsCompat = new ActivityOptionsImpl24(ActivityOptionsCompat24.makeScaleUpAnimation(view, n, n2, n3, n4));
        }
        else if (Build$VERSION.SDK_INT >= 23) {
            activityOptionsCompat = new ActivityOptionsImpl23(ActivityOptionsCompat23.makeScaleUpAnimation(view, n, n2, n3, n4));
        }
        else if (Build$VERSION.SDK_INT >= 21) {
            activityOptionsCompat = new ActivityOptionsImpl21(ActivityOptionsCompat21.makeScaleUpAnimation(view, n, n2, n3, n4));
        }
        else if (Build$VERSION.SDK_INT >= 16) {
            activityOptionsCompat = new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeScaleUpAnimation(view, n, n2, n3, n4));
        }
        else {
            activityOptionsCompat = new ActivityOptionsCompat();
        }
        return activityOptionsCompat;
    }
    
    public static ActivityOptionsCompat makeSceneTransitionAnimation(final Activity activity, final View view, final String s) {
        ActivityOptionsCompat activityOptionsCompat;
        if (Build$VERSION.SDK_INT >= 24) {
            activityOptionsCompat = new ActivityOptionsImpl24(ActivityOptionsCompat24.makeSceneTransitionAnimation(activity, view, s));
        }
        else if (Build$VERSION.SDK_INT >= 23) {
            activityOptionsCompat = new ActivityOptionsImpl23(ActivityOptionsCompat23.makeSceneTransitionAnimation(activity, view, s));
        }
        else if (Build$VERSION.SDK_INT >= 21) {
            activityOptionsCompat = new ActivityOptionsImpl21(ActivityOptionsCompat21.makeSceneTransitionAnimation(activity, view, s));
        }
        else {
            activityOptionsCompat = new ActivityOptionsCompat();
        }
        return activityOptionsCompat;
    }
    
    public static ActivityOptionsCompat makeSceneTransitionAnimation(final Activity activity, final Pair<View, String>... array) {
        ActivityOptionsCompat activityOptionsCompat;
        if (Build$VERSION.SDK_INT >= 21) {
            String[] array2 = null;
            View[] array3 = null;
            if (array != null) {
                array3 = new View[array.length];
                array2 = new String[array.length];
                for (int i = 0; i < array.length; ++i) {
                    array3[i] = array[i].first;
                    array2[i] = array[i].second;
                }
            }
            if (Build$VERSION.SDK_INT >= 24) {
                activityOptionsCompat = new ActivityOptionsImpl24(ActivityOptionsCompat24.makeSceneTransitionAnimation(activity, array3, array2));
            }
            else if (Build$VERSION.SDK_INT >= 23) {
                activityOptionsCompat = new ActivityOptionsImpl23(ActivityOptionsCompat23.makeSceneTransitionAnimation(activity, array3, array2));
            }
            else {
                activityOptionsCompat = new ActivityOptionsImpl21(ActivityOptionsCompat21.makeSceneTransitionAnimation(activity, array3, array2));
            }
        }
        else {
            activityOptionsCompat = new ActivityOptionsCompat();
        }
        return activityOptionsCompat;
    }
    
    public static ActivityOptionsCompat makeTaskLaunchBehind() {
        ActivityOptionsCompat activityOptionsCompat;
        if (Build$VERSION.SDK_INT >= 24) {
            activityOptionsCompat = new ActivityOptionsImpl24(ActivityOptionsCompat24.makeTaskLaunchBehind());
        }
        else if (Build$VERSION.SDK_INT >= 23) {
            activityOptionsCompat = new ActivityOptionsImpl23(ActivityOptionsCompat23.makeTaskLaunchBehind());
        }
        else if (Build$VERSION.SDK_INT >= 21) {
            activityOptionsCompat = new ActivityOptionsImpl21(ActivityOptionsCompat21.makeTaskLaunchBehind());
        }
        else {
            activityOptionsCompat = new ActivityOptionsCompat();
        }
        return activityOptionsCompat;
    }
    
    public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(final View view, final Bitmap bitmap, final int n, final int n2) {
        ActivityOptionsCompat activityOptionsCompat;
        if (Build$VERSION.SDK_INT >= 24) {
            activityOptionsCompat = new ActivityOptionsImpl24(ActivityOptionsCompat24.makeThumbnailScaleUpAnimation(view, bitmap, n, n2));
        }
        else if (Build$VERSION.SDK_INT >= 23) {
            activityOptionsCompat = new ActivityOptionsImpl23(ActivityOptionsCompat23.makeThumbnailScaleUpAnimation(view, bitmap, n, n2));
        }
        else if (Build$VERSION.SDK_INT >= 21) {
            activityOptionsCompat = new ActivityOptionsImpl21(ActivityOptionsCompat21.makeThumbnailScaleUpAnimation(view, bitmap, n, n2));
        }
        else if (Build$VERSION.SDK_INT >= 16) {
            activityOptionsCompat = new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeThumbnailScaleUpAnimation(view, bitmap, n, n2));
        }
        else {
            activityOptionsCompat = new ActivityOptionsCompat();
        }
        return activityOptionsCompat;
    }
    
    @Nullable
    public Rect getLaunchBounds() {
        return null;
    }
    
    public void requestUsageTimeReport(final PendingIntent pendingIntent) {
    }
    
    public ActivityOptionsCompat setLaunchBounds(@Nullable final Rect rect) {
        return null;
    }
    
    public Bundle toBundle() {
        return null;
    }
    
    public void update(final ActivityOptionsCompat activityOptionsCompat) {
    }
    
    private static class ActivityOptionsImpl21 extends ActivityOptionsCompat
    {
        private final ActivityOptionsCompat21 mImpl;
        
        ActivityOptionsImpl21(final ActivityOptionsCompat21 mImpl) {
            this.mImpl = mImpl;
        }
        
        @Override
        public Bundle toBundle() {
            return this.mImpl.toBundle();
        }
        
        @Override
        public void update(final ActivityOptionsCompat activityOptionsCompat) {
            if (activityOptionsCompat instanceof ActivityOptionsImpl21) {
                this.mImpl.update(((ActivityOptionsImpl21)activityOptionsCompat).mImpl);
            }
        }
    }
    
    private static class ActivityOptionsImpl23 extends ActivityOptionsCompat
    {
        private final ActivityOptionsCompat23 mImpl;
        
        ActivityOptionsImpl23(final ActivityOptionsCompat23 mImpl) {
            this.mImpl = mImpl;
        }
        
        @Override
        public void requestUsageTimeReport(final PendingIntent pendingIntent) {
            this.mImpl.requestUsageTimeReport(pendingIntent);
        }
        
        @Override
        public Bundle toBundle() {
            return this.mImpl.toBundle();
        }
        
        @Override
        public void update(final ActivityOptionsCompat activityOptionsCompat) {
            if (activityOptionsCompat instanceof ActivityOptionsImpl23) {
                this.mImpl.update(((ActivityOptionsImpl23)activityOptionsCompat).mImpl);
            }
        }
    }
    
    private static class ActivityOptionsImpl24 extends ActivityOptionsCompat
    {
        private final ActivityOptionsCompat24 mImpl;
        
        ActivityOptionsImpl24(final ActivityOptionsCompat24 mImpl) {
            this.mImpl = mImpl;
        }
        
        @Override
        public Rect getLaunchBounds() {
            return this.mImpl.getLaunchBounds();
        }
        
        @Override
        public void requestUsageTimeReport(final PendingIntent pendingIntent) {
            this.mImpl.requestUsageTimeReport(pendingIntent);
        }
        
        @Override
        public ActivityOptionsCompat setLaunchBounds(@Nullable final Rect launchBounds) {
            return new ActivityOptionsImpl24(this.mImpl.setLaunchBounds(launchBounds));
        }
        
        @Override
        public Bundle toBundle() {
            return this.mImpl.toBundle();
        }
        
        @Override
        public void update(final ActivityOptionsCompat activityOptionsCompat) {
            if (activityOptionsCompat instanceof ActivityOptionsImpl24) {
                this.mImpl.update(((ActivityOptionsImpl24)activityOptionsCompat).mImpl);
            }
        }
    }
    
    private static class ActivityOptionsImplJB extends ActivityOptionsCompat
    {
        private final ActivityOptionsCompatJB mImpl;
        
        ActivityOptionsImplJB(final ActivityOptionsCompatJB mImpl) {
            this.mImpl = mImpl;
        }
        
        @Override
        public Bundle toBundle() {
            return this.mImpl.toBundle();
        }
        
        @Override
        public void update(final ActivityOptionsCompat activityOptionsCompat) {
            if (activityOptionsCompat instanceof ActivityOptionsImplJB) {
                this.mImpl.update(((ActivityOptionsImplJB)activityOptionsCompat).mImpl);
            }
        }
    }
}
