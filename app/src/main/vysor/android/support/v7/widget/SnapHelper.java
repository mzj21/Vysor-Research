// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.animation.DecelerateInterpolator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.animation.Interpolator;
import android.view.View;
import android.util.DisplayMetrics;
import android.content.Context;
import android.widget.Scroller;

public abstract class SnapHelper extends OnFlingListener
{
    static final float MILLISECONDS_PER_INCH = 100.0f;
    private Scroller mGravityScroller;
    RecyclerView mRecyclerView;
    private final OnScrollListener mScrollListener;
    
    public SnapHelper() {
        this.mScrollListener = new OnScrollListener() {
            boolean mScrolled = false;
            
            @Override
            public void onScrollStateChanged(final RecyclerView recyclerView, final int n) {
                super.onScrollStateChanged(recyclerView, n);
                if (n == 0 && this.mScrolled) {
                    this.mScrolled = false;
                    SnapHelper.this.snapToTargetExistingView();
                }
            }
            
            @Override
            public void onScrolled(final RecyclerView recyclerView, final int n, final int n2) {
                if (n != 0 || n2 != 0) {
                    this.mScrolled = true;
                }
            }
        };
    }
    
    @Nullable
    private LinearSmoothScroller createSnapScroller(final LayoutManager layoutManager) {
        LinearSmoothScroller linearSmoothScroller;
        if (!(layoutManager instanceof ScrollVectorProvider)) {
            linearSmoothScroller = null;
        }
        else {
            linearSmoothScroller = new LinearSmoothScroller(this.mRecyclerView.getContext()) {
                @Override
                protected float calculateSpeedPerPixel(final DisplayMetrics displayMetrics) {
                    return 100.0f / displayMetrics.densityDpi;
                }
                
                @Override
                protected void onTargetFound(final View view, final State state, final Action action) {
                    final int[] calculateDistanceToFinalSnap = SnapHelper.this.calculateDistanceToFinalSnap(SnapHelper.this.mRecyclerView.getLayoutManager(), view);
                    final int n = calculateDistanceToFinalSnap[0];
                    final int n2 = calculateDistanceToFinalSnap[1];
                    final int calculateTimeForDeceleration = this.calculateTimeForDeceleration(Math.max(Math.abs(n), Math.abs(n2)));
                    if (calculateTimeForDeceleration > 0) {
                        action.update(n, n2, calculateTimeForDeceleration, (Interpolator)this.mDecelerateInterpolator);
                    }
                }
            };
        }
        return linearSmoothScroller;
    }
    
    private void destroyCallbacks() {
        this.mRecyclerView.removeOnScrollListener(this.mScrollListener);
        this.mRecyclerView.setOnFlingListener(null);
    }
    
    private void setupCallbacks() throws IllegalStateException {
        if (this.mRecyclerView.getOnFlingListener() != null) {
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        }
        this.mRecyclerView.addOnScrollListener(this.mScrollListener);
        this.mRecyclerView.setOnFlingListener((RecyclerView.OnFlingListener)this);
    }
    
    private boolean snapFromFling(@NonNull final LayoutManager layoutManager, final int n, final int n2) {
        final boolean b = layoutManager instanceof ScrollVectorProvider;
        boolean b2 = false;
        if (b) {
            final LinearSmoothScroller snapScroller = this.createSnapScroller(layoutManager);
            b2 = false;
            if (snapScroller != null) {
                final int targetSnapPosition = this.findTargetSnapPosition(layoutManager, n, n2);
                b2 = false;
                if (targetSnapPosition != -1) {
                    ((RecyclerView.SmoothScroller)snapScroller).setTargetPosition(targetSnapPosition);
                    layoutManager.startSmoothScroll(snapScroller);
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    public void attachToRecyclerView(@Nullable final RecyclerView mRecyclerView) throws IllegalStateException {
        if (this.mRecyclerView != mRecyclerView) {
            if (this.mRecyclerView != null) {
                this.destroyCallbacks();
            }
            this.mRecyclerView = mRecyclerView;
            if (this.mRecyclerView != null) {
                this.setupCallbacks();
                this.mGravityScroller = new Scroller(this.mRecyclerView.getContext(), (Interpolator)new DecelerateInterpolator());
                this.snapToTargetExistingView();
            }
        }
    }
    
    @Nullable
    public abstract int[] calculateDistanceToFinalSnap(@NonNull final LayoutManager p0, @NonNull final View p1);
    
    public int[] calculateScrollDistance(final int n, final int n2) {
        final int[] array = new int[2];
        this.mGravityScroller.fling(0, 0, n, n2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        array[0] = this.mGravityScroller.getFinalX();
        array[1] = this.mGravityScroller.getFinalY();
        return array;
    }
    
    @Nullable
    public abstract View findSnapView(final LayoutManager p0);
    
    public abstract int findTargetSnapPosition(final LayoutManager p0, final int p1, final int p2);
    
    @Override
    public boolean onFling(final int n, final int n2) {
        final RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        boolean b = false;
        if (layoutManager != null) {
            final RecyclerView.Adapter adapter = this.mRecyclerView.getAdapter();
            b = false;
            if (adapter != null) {
                final int minFlingVelocity = this.mRecyclerView.getMinFlingVelocity();
                if (Math.abs(n2) <= minFlingVelocity) {
                    final int abs = Math.abs(n);
                    b = false;
                    if (abs <= minFlingVelocity) {
                        return b;
                    }
                }
                final boolean snapFromFling = this.snapFromFling(layoutManager, n, n2);
                b = false;
                if (snapFromFling) {
                    b = true;
                }
            }
        }
        return b;
    }
    
    void snapToTargetExistingView() {
        if (this.mRecyclerView != null) {
            final RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
            if (layoutManager != null) {
                final View snapView = this.findSnapView(layoutManager);
                if (snapView != null) {
                    final int[] calculateDistanceToFinalSnap = this.calculateDistanceToFinalSnap(layoutManager, snapView);
                    if (calculateDistanceToFinalSnap[0] != 0 || calculateDistanceToFinalSnap[1] != 0) {
                        this.mRecyclerView.smoothScrollBy(calculateDistanceToFinalSnap[0], calculateDistanceToFinalSnap[1]);
                    }
                }
            }
        }
    }
}
