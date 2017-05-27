// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.animation.Interpolator;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.DisplayMetrics;
import android.view.View;
import android.content.Context;
import android.graphics.PointF;
import android.view.animation.LinearInterpolator;
import android.view.animation.DecelerateInterpolator;

public class LinearSmoothScroller extends SmoothScroller
{
    private static final boolean DEBUG = false;
    private static final float MILLISECONDS_PER_INCH = 25.0f;
    public static final int SNAP_TO_ANY = 0;
    public static final int SNAP_TO_END = 1;
    public static final int SNAP_TO_START = -1;
    private static final String TAG = "LinearSmoothScroller";
    private static final float TARGET_SEEK_EXTRA_SCROLL_RATIO = 1.2f;
    private static final int TARGET_SEEK_SCROLL_DISTANCE_PX = 10000;
    private final float MILLISECONDS_PER_PX;
    protected final DecelerateInterpolator mDecelerateInterpolator;
    protected int mInterimTargetDx;
    protected int mInterimTargetDy;
    protected final LinearInterpolator mLinearInterpolator;
    protected PointF mTargetVector;
    
    public LinearSmoothScroller(final Context context) {
        this.mLinearInterpolator = new LinearInterpolator();
        this.mDecelerateInterpolator = new DecelerateInterpolator();
        this.mInterimTargetDx = 0;
        this.mInterimTargetDy = 0;
        this.MILLISECONDS_PER_PX = this.calculateSpeedPerPixel(context.getResources().getDisplayMetrics());
    }
    
    private int clampApplyScroll(final int n, final int n2) {
        int n3 = n - n2;
        if (n * n3 <= 0) {
            n3 = 0;
        }
        return n3;
    }
    
    public int calculateDtToFit(final int n, final int n2, final int n3, final int n4, final int n5) {
        int n6 = 0;
        switch (n5) {
            default: {
                throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
            }
            case -1: {
                n6 = n3 - n;
                break;
            }
            case 1: {
                n6 = n4 - n2;
                break;
            }
            case 0: {
                n6 = n3 - n;
                if (n6 > 0) {
                    break;
                }
                final int n7 = n4 - n2;
                if (n7 < 0) {
                    n6 = n7;
                    break;
                }
                n6 = 0;
                break;
            }
        }
        return n6;
    }
    
    public int calculateDxToMakeVisible(final View view, final int n) {
        final LayoutManager layoutManager = ((RecyclerView.SmoothScroller)this).getLayoutManager();
        int calculateDtToFit;
        if (layoutManager == null || !layoutManager.canScrollHorizontally()) {
            calculateDtToFit = 0;
        }
        else {
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            calculateDtToFit = this.calculateDtToFit(layoutManager.getDecoratedLeft(view) - layoutParams.leftMargin, layoutManager.getDecoratedRight(view) + layoutParams.rightMargin, layoutManager.getPaddingLeft(), layoutManager.getWidth() - layoutManager.getPaddingRight(), n);
        }
        return calculateDtToFit;
    }
    
    public int calculateDyToMakeVisible(final View view, final int n) {
        final LayoutManager layoutManager = ((RecyclerView.SmoothScroller)this).getLayoutManager();
        int calculateDtToFit;
        if (layoutManager == null || !layoutManager.canScrollVertically()) {
            calculateDtToFit = 0;
        }
        else {
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            calculateDtToFit = this.calculateDtToFit(layoutManager.getDecoratedTop(view) - layoutParams.topMargin, layoutManager.getDecoratedBottom(view) + layoutParams.bottomMargin, layoutManager.getPaddingTop(), layoutManager.getHeight() - layoutManager.getPaddingBottom(), n);
        }
        return calculateDtToFit;
    }
    
    protected float calculateSpeedPerPixel(final DisplayMetrics displayMetrics) {
        return 25.0f / displayMetrics.densityDpi;
    }
    
    protected int calculateTimeForDeceleration(final int n) {
        return (int)Math.ceil(this.calculateTimeForScrolling(n) / 0.3356);
    }
    
    protected int calculateTimeForScrolling(final int n) {
        return (int)Math.ceil(Math.abs(n) * this.MILLISECONDS_PER_PX);
    }
    
    @Nullable
    public PointF computeScrollVectorForPosition(final int n) {
        final LayoutManager layoutManager = ((RecyclerView.SmoothScroller)this).getLayoutManager();
        PointF computeScrollVectorForPosition;
        if (layoutManager instanceof ScrollVectorProvider) {
            computeScrollVectorForPosition = ((ScrollVectorProvider)layoutManager).computeScrollVectorForPosition(n);
        }
        else {
            Log.w("LinearSmoothScroller", "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + ScrollVectorProvider.class.getCanonicalName());
            computeScrollVectorForPosition = null;
        }
        return computeScrollVectorForPosition;
    }
    
    protected int getHorizontalSnapPreference() {
        int n;
        if (this.mTargetVector == null || this.mTargetVector.x == 0.0f) {
            n = 0;
        }
        else if (this.mTargetVector.x > 0.0f) {
            n = 1;
        }
        else {
            n = -1;
        }
        return n;
    }
    
    protected int getVerticalSnapPreference() {
        int n;
        if (this.mTargetVector == null || this.mTargetVector.y == 0.0f) {
            n = 0;
        }
        else if (this.mTargetVector.y > 0.0f) {
            n = 1;
        }
        else {
            n = -1;
        }
        return n;
    }
    
    @Override
    protected void onSeekTargetStep(final int n, final int n2, final State state, final Action action) {
        if (((RecyclerView.SmoothScroller)this).getChildCount() == 0) {
            ((RecyclerView.SmoothScroller)this).stop();
        }
        else {
            this.mInterimTargetDx = this.clampApplyScroll(this.mInterimTargetDx, n);
            this.mInterimTargetDy = this.clampApplyScroll(this.mInterimTargetDy, n2);
            if (this.mInterimTargetDx == 0 && this.mInterimTargetDy == 0) {
                this.updateActionForInterimTarget(action);
            }
        }
    }
    
    @Override
    protected void onStart() {
    }
    
    @Override
    protected void onStop() {
        this.mInterimTargetDy = 0;
        this.mInterimTargetDx = 0;
        this.mTargetVector = null;
    }
    
    @Override
    protected void onTargetFound(final View view, final State state, final Action action) {
        final int calculateDxToMakeVisible = this.calculateDxToMakeVisible(view, this.getHorizontalSnapPreference());
        final int calculateDyToMakeVisible = this.calculateDyToMakeVisible(view, this.getVerticalSnapPreference());
        final int calculateTimeForDeceleration = this.calculateTimeForDeceleration((int)Math.sqrt(calculateDxToMakeVisible * calculateDxToMakeVisible + calculateDyToMakeVisible * calculateDyToMakeVisible));
        if (calculateTimeForDeceleration > 0) {
            action.update(-calculateDxToMakeVisible, -calculateDyToMakeVisible, calculateTimeForDeceleration, (Interpolator)this.mDecelerateInterpolator);
        }
    }
    
    protected void updateActionForInterimTarget(final Action action) {
        final PointF computeScrollVectorForPosition = this.computeScrollVectorForPosition(((RecyclerView.SmoothScroller)this).getTargetPosition());
        if (computeScrollVectorForPosition == null || (computeScrollVectorForPosition.x == 0.0f && computeScrollVectorForPosition.y == 0.0f)) {
            action.jumpTo(((RecyclerView.SmoothScroller)this).getTargetPosition());
            ((RecyclerView.SmoothScroller)this).stop();
        }
        else {
            ((RecyclerView.SmoothScroller)this).normalize(computeScrollVectorForPosition);
            this.mTargetVector = computeScrollVectorForPosition;
            this.mInterimTargetDx = (int)(10000.0f * computeScrollVectorForPosition.x);
            this.mInterimTargetDy = (int)(10000.0f * computeScrollVectorForPosition.y);
            action.update((int)(1.2f * this.mInterimTargetDx), (int)(1.2f * this.mInterimTargetDy), (int)(1.2f * this.calculateTimeForScrolling(10000)), (Interpolator)this.mLinearInterpolator);
        }
    }
}
