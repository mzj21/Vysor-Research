// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.view.View;
import android.support.annotation.Nullable;

public class LinearSnapHelper extends SnapHelper
{
    private static final float INVALID_DISTANCE = 1.0f;
    @Nullable
    private OrientationHelper mHorizontalHelper;
    @Nullable
    private OrientationHelper mVerticalHelper;
    
    private float computeDistancePerChild(final LayoutManager layoutManager, final OrientationHelper orientationHelper) {
        View view = null;
        View view2 = null;
        int n = Integer.MAX_VALUE;
        int n2 = Integer.MIN_VALUE;
        final int childCount = layoutManager.getChildCount();
        float n3;
        if (childCount == 0) {
            n3 = 1.0f;
        }
        else {
            for (int i = 0; i < childCount; ++i) {
                final View child = layoutManager.getChildAt(i);
                final int position = layoutManager.getPosition(child);
                if (position != -1) {
                    if (position < n) {
                        n = position;
                        view = child;
                    }
                    if (position > n2) {
                        n2 = position;
                        view2 = child;
                    }
                }
            }
            if (view == null || view2 == null) {
                n3 = 1.0f;
            }
            else {
                final int n4 = Math.max(orientationHelper.getDecoratedEnd(view), orientationHelper.getDecoratedEnd(view2)) - Math.min(orientationHelper.getDecoratedStart(view), orientationHelper.getDecoratedStart(view2));
                if (n4 == 0) {
                    n3 = 1.0f;
                }
                else {
                    n3 = 1.0f * n4 / (1 + (n2 - n));
                }
            }
        }
        return n3;
    }
    
    private int distanceToCenter(@NonNull final LayoutManager layoutManager, @NonNull final View view, final OrientationHelper orientationHelper) {
        final int n = orientationHelper.getDecoratedStart(view) + orientationHelper.getDecoratedMeasurement(view) / 2;
        int n2;
        if (layoutManager.getClipToPadding()) {
            n2 = orientationHelper.getStartAfterPadding() + orientationHelper.getTotalSpace() / 2;
        }
        else {
            n2 = orientationHelper.getEnd() / 2;
        }
        return n - n2;
    }
    
    private int estimateNextPositionDiffForFling(final LayoutManager layoutManager, final OrientationHelper orientationHelper, final int n, final int n2) {
        final int[] calculateScrollDistance = this.calculateScrollDistance(n, n2);
        final float computeDistancePerChild = this.computeDistancePerChild(layoutManager, orientationHelper);
        final float n3 = fcmpg(computeDistancePerChild, 0.0f);
        int n4 = 0;
        if (n3 > 0) {
            int n5;
            if (Math.abs(calculateScrollDistance[0]) > Math.abs(calculateScrollDistance[1])) {
                n5 = calculateScrollDistance[0];
            }
            else {
                n5 = calculateScrollDistance[1];
            }
            n4 = (int)Math.floor(n5 / computeDistancePerChild);
        }
        return n4;
    }
    
    @Nullable
    private View findCenterView(final LayoutManager layoutManager, final OrientationHelper orientationHelper) {
        final int childCount = layoutManager.getChildCount();
        View view;
        if (childCount == 0) {
            view = null;
        }
        else {
            view = null;
            int n;
            if (layoutManager.getClipToPadding()) {
                n = orientationHelper.getStartAfterPadding() + orientationHelper.getTotalSpace() / 2;
            }
            else {
                n = orientationHelper.getEnd() / 2;
            }
            int n2 = Integer.MAX_VALUE;
            for (int i = 0; i < childCount; ++i) {
                final View child = layoutManager.getChildAt(i);
                final int abs = Math.abs(orientationHelper.getDecoratedStart(child) + orientationHelper.getDecoratedMeasurement(child) / 2 - n);
                if (abs < n2) {
                    n2 = abs;
                    view = child;
                }
            }
        }
        return view;
    }
    
    @NonNull
    private OrientationHelper getHorizontalHelper(@NonNull final LayoutManager layoutManager) {
        if (this.mHorizontalHelper == null || this.mHorizontalHelper.mLayoutManager != layoutManager) {
            this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return this.mHorizontalHelper;
    }
    
    @NonNull
    private OrientationHelper getVerticalHelper(@NonNull final LayoutManager layoutManager) {
        if (this.mVerticalHelper == null || this.mVerticalHelper.mLayoutManager != layoutManager) {
            this.mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return this.mVerticalHelper;
    }
    
    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull final LayoutManager layoutManager, @NonNull final View view) {
        final int[] array = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            array[0] = this.distanceToCenter(layoutManager, view, this.getHorizontalHelper(layoutManager));
        }
        else {
            array[0] = 0;
        }
        if (layoutManager.canScrollVertically()) {
            array[1] = this.distanceToCenter(layoutManager, view, this.getVerticalHelper(layoutManager));
        }
        else {
            array[1] = 0;
        }
        return array;
    }
    
    @Override
    public View findSnapView(final LayoutManager layoutManager) {
        View view;
        if (layoutManager.canScrollVertically()) {
            view = this.findCenterView(layoutManager, this.getVerticalHelper(layoutManager));
        }
        else if (layoutManager.canScrollHorizontally()) {
            view = this.findCenterView(layoutManager, this.getHorizontalHelper(layoutManager));
        }
        else {
            view = null;
        }
        return view;
    }
    
    @Override
    public int findTargetSnapPosition(final LayoutManager layoutManager, final int n, final int n2) {
        int n3 = -1;
        if (layoutManager instanceof ScrollVectorProvider) {
            final int itemCount = layoutManager.getItemCount();
            if (itemCount != 0) {
                final View snapView = this.findSnapView(layoutManager);
                if (snapView != null) {
                    final int position = layoutManager.getPosition(snapView);
                    if (position != n3) {
                        final PointF computeScrollVectorForPosition = ((ScrollVectorProvider)layoutManager).computeScrollVectorForPosition(itemCount - 1);
                        if (computeScrollVectorForPosition != null) {
                            int estimateNextPositionDiffForFling;
                            if (layoutManager.canScrollHorizontally()) {
                                estimateNextPositionDiffForFling = this.estimateNextPositionDiffForFling(layoutManager, this.getHorizontalHelper(layoutManager), n, 0);
                                if (computeScrollVectorForPosition.x < 0.0f) {
                                    estimateNextPositionDiffForFling = -estimateNextPositionDiffForFling;
                                }
                            }
                            else {
                                estimateNextPositionDiffForFling = 0;
                            }
                            int estimateNextPositionDiffForFling2;
                            if (layoutManager.canScrollVertically()) {
                                estimateNextPositionDiffForFling2 = this.estimateNextPositionDiffForFling(layoutManager, this.getVerticalHelper(layoutManager), 0, n2);
                                if (computeScrollVectorForPosition.y < 0.0f) {
                                    estimateNextPositionDiffForFling2 = -estimateNextPositionDiffForFling2;
                                }
                            }
                            else {
                                estimateNextPositionDiffForFling2 = 0;
                            }
                            int n4;
                            if (layoutManager.canScrollVertically()) {
                                n4 = estimateNextPositionDiffForFling2;
                            }
                            else {
                                n4 = estimateNextPositionDiffForFling;
                            }
                            if (n4 != 0) {
                                n3 = position + n4;
                                if (n3 < 0) {
                                    n3 = 0;
                                }
                                if (n3 >= itemCount) {
                                    n3 = itemCount - 1;
                                }
                            }
                        }
                    }
                }
            }
        }
        return n3;
    }
}
