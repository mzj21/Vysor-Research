// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.Arrays;
import android.view.View$MeasureSpec;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.util.Log;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import android.util.SparseIntArray;
import android.graphics.Rect;

public class GridLayoutManager extends LinearLayoutManager
{
    private static final boolean DEBUG = false;
    public static final int DEFAULT_SPAN_COUNT = -1;
    private static final String TAG = "GridLayoutManager";
    int[] mCachedBorders;
    final Rect mDecorInsets;
    boolean mPendingSpanCountChange;
    final SparseIntArray mPreLayoutSpanIndexCache;
    final SparseIntArray mPreLayoutSpanSizeCache;
    View[] mSet;
    int mSpanCount;
    SpanSizeLookup mSpanSizeLookup;
    
    public GridLayoutManager(final Context context, final int spanCount) {
        super(context);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = (SpanSizeLookup)new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        this.setSpanCount(spanCount);
    }
    
    public GridLayoutManager(final Context context, final int spanCount, final int n, final boolean b) {
        super(context, n, b);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = (SpanSizeLookup)new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        this.setSpanCount(spanCount);
    }
    
    public GridLayoutManager(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = (SpanSizeLookup)new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        this.setSpanCount(RecyclerView.LayoutManager.getProperties(context, set, n, n2).spanCount);
    }
    
    private void assignSpans(final Recycler recycler, final State state, final int n, final int n2, final boolean b) {
        int n3;
        int n4;
        int n5;
        if (b) {
            n3 = 0;
            n4 = n;
            n5 = 1;
        }
        else {
            n3 = n - 1;
            n4 = -1;
            n5 = -1;
        }
        int mSpanIndex = 0;
        for (int i = n3; i != n4; i += n5) {
            final View view = this.mSet[i];
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            layoutParams.mSpanSize = this.getSpanSize(recycler, state, ((RecyclerView.LayoutManager)this).getPosition(view));
            layoutParams.mSpanIndex = mSpanIndex;
            mSpanIndex += layoutParams.mSpanSize;
        }
    }
    
    private void cachePreLayoutSpanMapping() {
        for (int childCount = ((RecyclerView.LayoutManager)this).getChildCount(), i = 0; i < childCount; ++i) {
            final LayoutParams layoutParams = (LayoutParams)((RecyclerView.LayoutManager)this).getChildAt(i).getLayoutParams();
            final int viewLayoutPosition = ((RecyclerView.LayoutParams)layoutParams).getViewLayoutPosition();
            this.mPreLayoutSpanSizeCache.put(viewLayoutPosition, layoutParams.getSpanSize());
            this.mPreLayoutSpanIndexCache.put(viewLayoutPosition, layoutParams.getSpanIndex());
        }
    }
    
    private void calculateItemBorders(final int n) {
        this.mCachedBorders = calculateItemBorders(this.mCachedBorders, this.mSpanCount, n);
    }
    
    static int[] calculateItemBorders(int[] array, final int n, final int n2) {
        if (array == null || array.length != n + 1 || array[-1 + array.length] != n2) {
            array = new int[n + 1];
        }
        array[0] = 0;
        final int n3 = n2 / n;
        final int n4 = n2 % n;
        int n5 = 0;
        int n6 = 0;
        for (int i = 1; i <= n; ++i) {
            int n7 = n3;
            n6 += n4;
            if (n6 > 0 && n - n6 < n4) {
                ++n7;
                n6 -= n;
            }
            n5 += n7;
            array[i] = n5;
        }
        return array;
    }
    
    private void clearPreLayoutSpanMappingCache() {
        this.mPreLayoutSpanSizeCache.clear();
        this.mPreLayoutSpanIndexCache.clear();
    }
    
    private void ensureAnchorIsInCorrectSpan(final Recycler recycler, final State state, final AnchorInfo anchorInfo, final int n) {
        int n2 = 1;
        if (n != n2) {
            n2 = 0;
        }
        int n3 = this.getSpanIndex(recycler, state, anchorInfo.mPosition);
        if (n2 != 0) {
            while (n3 > 0 && anchorInfo.mPosition > 0) {
                --anchorInfo.mPosition;
                n3 = this.getSpanIndex(recycler, state, anchorInfo.mPosition);
            }
        }
        else {
            final int n4 = -1 + state.getItemCount();
            int i = anchorInfo.mPosition;
            int n5 = n3;
            while (i < n4) {
                final int spanIndex = this.getSpanIndex(recycler, state, i + 1);
                if (spanIndex <= n5) {
                    break;
                }
                ++i;
                n5 = spanIndex;
            }
            anchorInfo.mPosition = i;
        }
    }
    
    private void ensureViewSet() {
        if (this.mSet == null || this.mSet.length != this.mSpanCount) {
            this.mSet = new View[this.mSpanCount];
        }
    }
    
    private int getSpanGroupIndex(final Recycler recycler, final State state, final int n) {
        int n2;
        if (!state.isPreLayout()) {
            n2 = this.mSpanSizeLookup.getSpanGroupIndex(n, this.mSpanCount);
        }
        else {
            final int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(n);
            if (convertPreLayoutPositionToPostLayout == -1) {
                Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + n);
                n2 = 0;
            }
            else {
                n2 = this.mSpanSizeLookup.getSpanGroupIndex(convertPreLayoutPositionToPostLayout, this.mSpanCount);
            }
        }
        return n2;
    }
    
    private int getSpanIndex(final Recycler recycler, final State state, final int n) {
        int n2;
        if (!state.isPreLayout()) {
            n2 = this.mSpanSizeLookup.getCachedSpanIndex(n, this.mSpanCount);
        }
        else {
            n2 = this.mPreLayoutSpanIndexCache.get(n, -1);
            if (n2 == -1) {
                final int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(n);
                if (convertPreLayoutPositionToPostLayout == -1) {
                    Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + n);
                    n2 = 0;
                }
                else {
                    n2 = this.mSpanSizeLookup.getCachedSpanIndex(convertPreLayoutPositionToPostLayout, this.mSpanCount);
                }
            }
        }
        return n2;
    }
    
    private int getSpanSize(final Recycler recycler, final State state, final int n) {
        int n2;
        if (!state.isPreLayout()) {
            n2 = this.mSpanSizeLookup.getSpanSize(n);
        }
        else {
            n2 = this.mPreLayoutSpanSizeCache.get(n, -1);
            if (n2 == -1) {
                final int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(n);
                if (convertPreLayoutPositionToPostLayout == -1) {
                    Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + n);
                    n2 = 1;
                }
                else {
                    n2 = this.mSpanSizeLookup.getSpanSize(convertPreLayoutPositionToPostLayout);
                }
            }
        }
        return n2;
    }
    
    private void guessMeasurement(final float n, final int n2) {
        this.calculateItemBorders(Math.max(Math.round(n * this.mSpanCount), n2));
    }
    
    private void measureChild(final View view, final int n, final boolean b) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        final Rect mDecorInsets = layoutParams.mDecorInsets;
        final int n2 = mDecorInsets.top + mDecorInsets.bottom + layoutParams.topMargin + layoutParams.bottomMargin;
        final int n3 = mDecorInsets.left + mDecorInsets.right + layoutParams.leftMargin + layoutParams.rightMargin;
        final int spaceForSpanRange = this.getSpaceForSpanRange(layoutParams.mSpanIndex, layoutParams.mSpanSize);
        int n4;
        int n5;
        if (this.mOrientation == 1) {
            n4 = RecyclerView.LayoutManager.getChildMeasureSpec(spaceForSpanRange, n, n3, layoutParams.width, false);
            n5 = RecyclerView.LayoutManager.getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), ((RecyclerView.LayoutManager)this).getHeightMode(), n2, layoutParams.height, true);
        }
        else {
            n5 = RecyclerView.LayoutManager.getChildMeasureSpec(spaceForSpanRange, n, n2, layoutParams.height, false);
            n4 = RecyclerView.LayoutManager.getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), ((RecyclerView.LayoutManager)this).getWidthMode(), n3, layoutParams.width, true);
        }
        this.measureChildWithDecorationsAndMargin(view, n4, n5, b);
    }
    
    private void measureChildWithDecorationsAndMargin(final View view, final int n, final int n2, final boolean b) {
        final RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
        boolean b2;
        if (b) {
            b2 = ((RecyclerView.LayoutManager)this).shouldReMeasureChild(view, n, n2, layoutParams);
        }
        else {
            b2 = ((RecyclerView.LayoutManager)this).shouldMeasureChild(view, n, n2, layoutParams);
        }
        if (b2) {
            view.measure(n, n2);
        }
    }
    
    private void updateMeasurements() {
        int n;
        if (this.getOrientation() == 1) {
            n = ((RecyclerView.LayoutManager)this).getWidth() - ((RecyclerView.LayoutManager)this).getPaddingRight() - ((RecyclerView.LayoutManager)this).getPaddingLeft();
        }
        else {
            n = ((RecyclerView.LayoutManager)this).getHeight() - ((RecyclerView.LayoutManager)this).getPaddingBottom() - ((RecyclerView.LayoutManager)this).getPaddingTop();
        }
        this.calculateItemBorders(n);
    }
    
    @Override
    public boolean checkLayoutParams(final RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }
    
    @Override
    View findReferenceChild(final Recycler recycler, final State state, final int n, final int n2, final int n3) {
        this.ensureLayoutState();
        View view = null;
        View view2 = null;
        final int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        final int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        int n4;
        if (n2 > n) {
            n4 = 1;
        }
        else {
            n4 = -1;
        }
        for (int i = n; i != n2; i += n4) {
            final View child = ((RecyclerView.LayoutManager)this).getChildAt(i);
            final int position = ((RecyclerView.LayoutManager)this).getPosition(child);
            if (position >= 0 && position < n3 && this.getSpanIndex(recycler, state, position) == 0) {
                if (((RecyclerView.LayoutParams)child.getLayoutParams()).isItemRemoved()) {
                    if (view == null) {
                        view = child;
                    }
                }
                else {
                    if (this.mOrientationHelper.getDecoratedStart(child) < endAfterPadding && this.mOrientationHelper.getDecoratedEnd(child) >= startAfterPadding) {
                        return child;
                    }
                    if (view2 == null) {
                        view2 = child;
                    }
                }
            }
        }
        if (view2 == null) {
            view2 = view;
        }
        return view2;
    }
    
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams;
        if (this.mOrientation == 0) {
            layoutParams = new LayoutParams(-2, -1);
        }
        else {
            layoutParams = new LayoutParams(-1, -2);
        }
        return layoutParams;
    }
    
    @Override
    public RecyclerView.LayoutParams generateLayoutParams(final Context context, final AttributeSet set) {
        return new LayoutParams(context, set);
    }
    
    @Override
    public RecyclerView.LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        LayoutParams layoutParams;
        if (viewGroup$LayoutParams instanceof ViewGroup$MarginLayoutParams) {
            layoutParams = new LayoutParams((ViewGroup$MarginLayoutParams)viewGroup$LayoutParams);
        }
        else {
            layoutParams = new LayoutParams(viewGroup$LayoutParams);
        }
        return layoutParams;
    }
    
    @Override
    public int getColumnCountForAccessibility(final Recycler recycler, final State state) {
        int mSpanCount;
        if (this.mOrientation == 1) {
            mSpanCount = this.mSpanCount;
        }
        else if (state.getItemCount() < 1) {
            mSpanCount = 0;
        }
        else {
            mSpanCount = 1 + this.getSpanGroupIndex(recycler, state, -1 + state.getItemCount());
        }
        return mSpanCount;
    }
    
    @Override
    public int getRowCountForAccessibility(final Recycler recycler, final State state) {
        int mSpanCount;
        if (this.mOrientation == 0) {
            mSpanCount = this.mSpanCount;
        }
        else if (state.getItemCount() < 1) {
            mSpanCount = 0;
        }
        else {
            mSpanCount = 1 + this.getSpanGroupIndex(recycler, state, -1 + state.getItemCount());
        }
        return mSpanCount;
    }
    
    int getSpaceForSpanRange(final int n, final int n2) {
        int n3;
        if (this.mOrientation == 1 && this.isLayoutRTL()) {
            n3 = this.mCachedBorders[this.mSpanCount - n] - this.mCachedBorders[this.mSpanCount - n - n2];
        }
        else {
            n3 = this.mCachedBorders[n + n2] - this.mCachedBorders[n];
        }
        return n3;
    }
    
    public int getSpanCount() {
        return this.mSpanCount;
    }
    
    public SpanSizeLookup getSpanSizeLookup() {
        return this.mSpanSizeLookup;
    }
    
    @Override
    void layoutChunk(final Recycler recycler, final State state, final LayoutState layoutState, final LayoutChunkResult layoutChunkResult) {
        final int modeInOther = this.mOrientationHelper.getModeInOther();
        boolean b;
        if (modeInOther != 1073741824) {
            b = true;
        }
        else {
            b = false;
        }
        int n;
        if (((RecyclerView.LayoutManager)this).getChildCount() > 0) {
            n = this.mCachedBorders[this.mSpanCount];
        }
        else {
            n = 0;
        }
        if (b) {
            this.updateMeasurements();
        }
        final boolean b2 = layoutState.mItemDirection == 1;
        int mSpanCount = this.mSpanCount;
        int n2 = 0;
        int n3 = 0;
        if (!b2) {
            mSpanCount = this.getSpanIndex(recycler, state, layoutState.mCurrentPosition) + this.getSpanSize(recycler, state, layoutState.mCurrentPosition);
        }
        while (n2 < this.mSpanCount && layoutState.hasMore(state) && mSpanCount > 0) {
            final int mCurrentPosition = layoutState.mCurrentPosition;
            final int spanSize = this.getSpanSize(recycler, state, mCurrentPosition);
            if (spanSize > this.mSpanCount) {
                throw new IllegalArgumentException("Item at position " + mCurrentPosition + " requires " + spanSize + " spans but GridLayoutManager has only " + this.mSpanCount + " spans.");
            }
            mSpanCount -= spanSize;
            if (mSpanCount < 0) {
                break;
            }
            final View next = layoutState.next(recycler);
            if (next == null) {
                break;
            }
            n3 += spanSize;
            this.mSet[n2] = next;
            ++n2;
        }
        if (n2 == 0) {
            layoutChunkResult.mFinished = true;
        }
        else {
            int mConsumed = 0;
            float n4 = 0.0f;
            this.assignSpans(recycler, state, n2, n3, b2);
            for (int i = 0; i < n2; ++i) {
                final View view = this.mSet[i];
                if (layoutState.mScrapList == null) {
                    if (b2) {
                        ((RecyclerView.LayoutManager)this).addView(view);
                    }
                    else {
                        ((RecyclerView.LayoutManager)this).addView(view, 0);
                    }
                }
                else if (b2) {
                    ((RecyclerView.LayoutManager)this).addDisappearingView(view);
                }
                else {
                    ((RecyclerView.LayoutManager)this).addDisappearingView(view, 0);
                }
                ((RecyclerView.LayoutManager)this).calculateItemDecorationsForChild(view, this.mDecorInsets);
                this.measureChild(view, modeInOther, false);
                final int decoratedMeasurement = this.mOrientationHelper.getDecoratedMeasurement(view);
                if (decoratedMeasurement > mConsumed) {
                    mConsumed = decoratedMeasurement;
                }
                final float n5 = 1.0f * this.mOrientationHelper.getDecoratedMeasurementInOther(view) / ((LayoutParams)view.getLayoutParams()).mSpanSize;
                if (n5 > n4) {
                    n4 = n5;
                }
            }
            if (b) {
                this.guessMeasurement(n4, n);
                mConsumed = 0;
                for (int j = 0; j < n2; ++j) {
                    final View view2 = this.mSet[j];
                    this.measureChild(view2, 1073741824, true);
                    final int decoratedMeasurement2 = this.mOrientationHelper.getDecoratedMeasurement(view2);
                    if (decoratedMeasurement2 > mConsumed) {
                        mConsumed = decoratedMeasurement2;
                    }
                }
            }
            for (int k = 0; k < n2; ++k) {
                final View view3 = this.mSet[k];
                if (this.mOrientationHelper.getDecoratedMeasurement(view3) != mConsumed) {
                    final LayoutParams layoutParams = (LayoutParams)view3.getLayoutParams();
                    final Rect mDecorInsets = layoutParams.mDecorInsets;
                    final int n6 = mDecorInsets.top + mDecorInsets.bottom + layoutParams.topMargin + layoutParams.bottomMargin;
                    final int n7 = mDecorInsets.left + mDecorInsets.right + layoutParams.leftMargin + layoutParams.rightMargin;
                    final int spaceForSpanRange = this.getSpaceForSpanRange(layoutParams.mSpanIndex, layoutParams.mSpanSize);
                    int n8;
                    int n9;
                    if (this.mOrientation == 1) {
                        n8 = RecyclerView.LayoutManager.getChildMeasureSpec(spaceForSpanRange, 1073741824, n7, layoutParams.width, false);
                        n9 = View$MeasureSpec.makeMeasureSpec(mConsumed - n6, 1073741824);
                    }
                    else {
                        n8 = View$MeasureSpec.makeMeasureSpec(mConsumed - n7, 1073741824);
                        n9 = RecyclerView.LayoutManager.getChildMeasureSpec(spaceForSpanRange, 1073741824, n6, layoutParams.height, false);
                    }
                    this.measureChildWithDecorationsAndMargin(view3, n8, n9, true);
                }
            }
            layoutChunkResult.mConsumed = mConsumed;
            int mOffset = 0;
            int mOffset2 = 0;
            int mOffset3;
            int mOffset4;
            if (this.mOrientation == 1) {
                if (layoutState.mLayoutDirection == -1) {
                    mOffset3 = layoutState.mOffset;
                    mOffset4 = mOffset3 - mConsumed;
                }
                else {
                    mOffset4 = layoutState.mOffset;
                    mOffset3 = mOffset4 + mConsumed;
                    mOffset = 0;
                    mOffset2 = 0;
                }
            }
            else if (layoutState.mLayoutDirection == -1) {
                mOffset2 = layoutState.mOffset;
                mOffset = mOffset2 - mConsumed;
                mOffset4 = 0;
                mOffset3 = 0;
            }
            else {
                mOffset = layoutState.mOffset;
                mOffset2 = mOffset + mConsumed;
                mOffset4 = 0;
                mOffset3 = 0;
            }
            for (int l = 0; l < n2; ++l) {
                final View view4 = this.mSet[l];
                final LayoutParams layoutParams2 = (LayoutParams)view4.getLayoutParams();
                if (this.mOrientation == 1) {
                    if (this.isLayoutRTL()) {
                        mOffset2 = ((RecyclerView.LayoutManager)this).getPaddingLeft() + this.mCachedBorders[this.mSpanCount - layoutParams2.mSpanIndex];
                        mOffset = mOffset2 - this.mOrientationHelper.getDecoratedMeasurementInOther(view4);
                    }
                    else {
                        mOffset = ((RecyclerView.LayoutManager)this).getPaddingLeft() + this.mCachedBorders[layoutParams2.mSpanIndex];
                        mOffset2 = mOffset + this.mOrientationHelper.getDecoratedMeasurementInOther(view4);
                    }
                }
                else {
                    mOffset4 = ((RecyclerView.LayoutManager)this).getPaddingTop() + this.mCachedBorders[layoutParams2.mSpanIndex];
                    mOffset3 = mOffset4 + this.mOrientationHelper.getDecoratedMeasurementInOther(view4);
                }
                ((RecyclerView.LayoutManager)this).layoutDecoratedWithMargins(view4, mOffset, mOffset4, mOffset2, mOffset3);
                if (((RecyclerView.LayoutParams)layoutParams2).isItemRemoved() || ((RecyclerView.LayoutParams)layoutParams2).isItemChanged()) {
                    layoutChunkResult.mIgnoreConsumed = true;
                }
                layoutChunkResult.mFocusable |= view4.isFocusable();
            }
            Arrays.fill(this.mSet, null);
        }
    }
    
    @Override
    void onAnchorReady(final Recycler recycler, final State state, final AnchorInfo anchorInfo, final int n) {
        super.onAnchorReady(recycler, state, anchorInfo, n);
        this.updateMeasurements();
        if (state.getItemCount() > 0 && !state.isPreLayout()) {
            this.ensureAnchorIsInCorrectSpan(recycler, state, anchorInfo, n);
        }
        this.ensureViewSet();
    }
    
    @Override
    public View onFocusSearchFailed(final View view, final int n, final Recycler recycler, final State state) {
        final View containingItemView = ((RecyclerView.LayoutManager)this).findContainingItemView(view);
        View child;
        if (containingItemView == null) {
            child = null;
        }
        else {
            final LayoutParams layoutParams = (LayoutParams)containingItemView.getLayoutParams();
            final int mSpanIndex = layoutParams.mSpanIndex;
            final int n2 = layoutParams.mSpanIndex + layoutParams.mSpanSize;
            if (super.onFocusSearchFailed(view, n, recycler, state) == null) {
                child = null;
            }
            else {
                int n3;
                if ((this.convertFocusDirectionToLayoutDirection(n) == 1 && true) != this.mShouldReverseLayout) {
                    n3 = 1;
                }
                else {
                    n3 = 0;
                }
                int n4;
                int n5;
                int childCount;
                if (n3 != 0) {
                    n4 = -1 + ((RecyclerView.LayoutManager)this).getChildCount();
                    n5 = -1;
                    childCount = -1;
                }
                else {
                    n5 = 1;
                    childCount = ((RecyclerView.LayoutManager)this).getChildCount();
                    n4 = 0;
                }
                final boolean b = this.mOrientation == 1 && this.isLayoutRTL();
                View view2 = null;
                int mSpanIndex2 = -1;
                int n6 = 0;
                for (int i = n4; i != childCount; i += n5) {
                    child = ((RecyclerView.LayoutManager)this).getChildAt(i);
                    if (child == containingItemView) {
                        break;
                    }
                    if (child.isFocusable()) {
                        final LayoutParams layoutParams2 = (LayoutParams)child.getLayoutParams();
                        final int mSpanIndex3 = layoutParams2.mSpanIndex;
                        final int n7 = layoutParams2.mSpanIndex + layoutParams2.mSpanSize;
                        if (mSpanIndex3 == mSpanIndex && n7 == n2) {
                            return child;
                        }
                        int n8;
                        if (view2 == null) {
                            n8 = 1;
                        }
                        else {
                            final int n9 = Math.min(n7, n2) - Math.max(mSpanIndex3, mSpanIndex);
                            if (n9 > n6) {
                                n8 = 1;
                            }
                            else {
                                final int n10 = n6;
                                n8 = 0;
                                if (n9 == n10) {
                                    final boolean b2 = mSpanIndex3 > mSpanIndex2 && true;
                                    final boolean b3 = b;
                                    final boolean b4 = b2;
                                    n8 = 0;
                                    if (b3 == b4) {
                                        n8 = 1;
                                    }
                                }
                            }
                        }
                        if (n8 != 0) {
                            view2 = child;
                            mSpanIndex2 = layoutParams2.mSpanIndex;
                            n6 = Math.min(n7, n2) - Math.max(mSpanIndex3, mSpanIndex);
                        }
                    }
                }
                child = view2;
            }
        }
        return child;
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfoForItem(final Recycler recycler, final State state, final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        final ViewGroup$LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
        }
        else {
            final LayoutParams layoutParams2 = (LayoutParams)layoutParams;
            final int spanGroupIndex = this.getSpanGroupIndex(recycler, state, ((RecyclerView.LayoutParams)layoutParams2).getViewLayoutPosition());
            if (this.mOrientation == 0) {
                accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(layoutParams2.getSpanIndex(), layoutParams2.getSpanSize(), spanGroupIndex, 1, this.mSpanCount > 1 && layoutParams2.getSpanSize() == this.mSpanCount, false));
            }
            else {
                accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(spanGroupIndex, 1, layoutParams2.getSpanIndex(), layoutParams2.getSpanSize(), this.mSpanCount > 1 && layoutParams2.getSpanSize() == this.mSpanCount, false));
            }
        }
    }
    
    @Override
    public void onItemsAdded(final RecyclerView recyclerView, final int n, final int n2) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onItemsChanged(final RecyclerView recyclerView) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onItemsMoved(final RecyclerView recyclerView, final int n, final int n2, final int n3) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onItemsRemoved(final RecyclerView recyclerView, final int n, final int n2) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onItemsUpdated(final RecyclerView recyclerView, final int n, final int n2, final Object o) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onLayoutChildren(final Recycler recycler, final State state) {
        if (state.isPreLayout()) {
            this.cachePreLayoutSpanMapping();
        }
        super.onLayoutChildren(recycler, state);
        this.clearPreLayoutSpanMappingCache();
    }
    
    @Override
    public void onLayoutCompleted(final State state) {
        super.onLayoutCompleted(state);
        this.mPendingSpanCountChange = false;
    }
    
    @Override
    public int scrollHorizontallyBy(final int n, final Recycler recycler, final State state) {
        this.updateMeasurements();
        this.ensureViewSet();
        return super.scrollHorizontallyBy(n, recycler, state);
    }
    
    @Override
    public int scrollVerticallyBy(final int n, final Recycler recycler, final State state) {
        this.updateMeasurements();
        this.ensureViewSet();
        return super.scrollVerticallyBy(n, recycler, state);
    }
    
    @Override
    public void setMeasuredDimension(final Rect rect, final int n, final int n2) {
        if (this.mCachedBorders == null) {
            super.setMeasuredDimension(rect, n, n2);
        }
        final int n3 = ((RecyclerView.LayoutManager)this).getPaddingLeft() + ((RecyclerView.LayoutManager)this).getPaddingRight();
        final int n4 = ((RecyclerView.LayoutManager)this).getPaddingTop() + ((RecyclerView.LayoutManager)this).getPaddingBottom();
        int n5;
        int n6;
        if (this.mOrientation == 1) {
            n5 = RecyclerView.LayoutManager.chooseSize(n2, n4 + rect.height(), ((RecyclerView.LayoutManager)this).getMinimumHeight());
            n6 = RecyclerView.LayoutManager.chooseSize(n, n3 + this.mCachedBorders[-1 + this.mCachedBorders.length], ((RecyclerView.LayoutManager)this).getMinimumWidth());
        }
        else {
            n6 = RecyclerView.LayoutManager.chooseSize(n, n3 + rect.width(), ((RecyclerView.LayoutManager)this).getMinimumWidth());
            n5 = RecyclerView.LayoutManager.chooseSize(n2, n4 + this.mCachedBorders[-1 + this.mCachedBorders.length], ((RecyclerView.LayoutManager)this).getMinimumHeight());
        }
        ((RecyclerView.LayoutManager)this).setMeasuredDimension(n6, n5);
    }
    
    public void setSpanCount(final int mSpanCount) {
        if (mSpanCount != this.mSpanCount) {
            this.mPendingSpanCountChange = true;
            if (mSpanCount < 1) {
                throw new IllegalArgumentException("Span count should be at least 1. Provided " + mSpanCount);
            }
            this.mSpanCount = mSpanCount;
            this.mSpanSizeLookup.invalidateSpanIndexCache();
            ((RecyclerView.LayoutManager)this).requestLayout();
        }
    }
    
    public void setSpanSizeLookup(final SpanSizeLookup mSpanSizeLookup) {
        this.mSpanSizeLookup = mSpanSizeLookup;
    }
    
    @Override
    public void setStackFromEnd(final boolean b) {
        if (b) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.setStackFromEnd(false);
    }
    
    @Override
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && !this.mPendingSpanCountChange;
    }
    
    public static final class DefaultSpanSizeLookup extends SpanSizeLookup
    {
        @Override
        public int getSpanIndex(final int n, final int n2) {
            return n % n2;
        }
        
        @Override
        public int getSpanSize(final int n) {
            return 1;
        }
    }
    
    public static class LayoutParams extends RecyclerView.LayoutParams
    {
        public static final int INVALID_SPAN_ID = -1;
        int mSpanIndex;
        int mSpanSize;
        
        public LayoutParams(final int n, final int n2) {
            super(n, n2);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }
        
        public LayoutParams(final Context context, final AttributeSet set) {
            super(context, set);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }
        
        public LayoutParams(final RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }
        
        public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }
        
        public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            super(viewGroup$MarginLayoutParams);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }
        
        public int getSpanIndex() {
            return this.mSpanIndex;
        }
        
        public int getSpanSize() {
            return this.mSpanSize;
        }
    }
    
    public abstract static class SpanSizeLookup
    {
        private boolean mCacheSpanIndices;
        final SparseIntArray mSpanIndexCache;
        
        public SpanSizeLookup() {
            this.mSpanIndexCache = new SparseIntArray();
            this.mCacheSpanIndices = false;
        }
        
        int findReferenceIndexFromCache(final int n) {
            int i = 0;
            int n2 = -1 + this.mSpanIndexCache.size();
            while (i <= n2) {
                final int n3 = i + n2 >>> 1;
                if (this.mSpanIndexCache.keyAt(n3) < n) {
                    i = n3 + 1;
                }
                else {
                    n2 = n3 - 1;
                }
            }
            final int n4 = i - 1;
            int key;
            if (n4 >= 0 && n4 < this.mSpanIndexCache.size()) {
                key = this.mSpanIndexCache.keyAt(n4);
            }
            else {
                key = -1;
            }
            return key;
        }
        
        int getCachedSpanIndex(final int n, final int n2) {
            int n3;
            if (!this.mCacheSpanIndices) {
                n3 = this.getSpanIndex(n, n2);
            }
            else {
                n3 = this.mSpanIndexCache.get(n, -1);
                if (n3 == -1) {
                    final int spanIndex = this.getSpanIndex(n, n2);
                    this.mSpanIndexCache.put(n, spanIndex);
                    n3 = spanIndex;
                }
            }
            return n3;
        }
        
        public int getSpanGroupIndex(final int n, final int n2) {
            int n3 = 0;
            int n4 = 0;
            final int spanSize = this.getSpanSize(n);
            for (int i = 0; i < n; ++i) {
                final int spanSize2 = this.getSpanSize(i);
                n3 += spanSize2;
                if (n3 == n2) {
                    n3 = 0;
                    ++n4;
                }
                else if (n3 > n2) {
                    n3 = spanSize2;
                    ++n4;
                }
            }
            if (n3 + spanSize > n2) {
                ++n4;
            }
            return n4;
        }
        
        public int getSpanIndex(final int n, final int n2) {
            final int spanSize = this.getSpanSize(n);
            int n3;
            if (spanSize == n2) {
                n3 = 0;
            }
            else {
                final boolean mCacheSpanIndices = this.mCacheSpanIndices;
                n3 = 0;
                int n4 = 0;
                if (mCacheSpanIndices) {
                    final int size = this.mSpanIndexCache.size();
                    n3 = 0;
                    n4 = 0;
                    if (size > 0) {
                        final int referenceIndexFromCache = this.findReferenceIndexFromCache(n);
                        n3 = 0;
                        n4 = 0;
                        if (referenceIndexFromCache >= 0) {
                            n3 = this.mSpanIndexCache.get(referenceIndexFromCache) + this.getSpanSize(referenceIndexFromCache);
                            n4 = referenceIndexFromCache + 1;
                        }
                    }
                }
                for (int i = n4; i < n; ++i) {
                    final int spanSize2 = this.getSpanSize(i);
                    n3 += spanSize2;
                    if (n3 == n2) {
                        n3 = 0;
                    }
                    else if (n3 > n2) {
                        n3 = spanSize2;
                    }
                }
                if (n3 + spanSize > n2) {
                    n3 = 0;
                }
            }
            return n3;
        }
        
        public abstract int getSpanSize(final int p0);
        
        public void invalidateSpanIndexCache() {
            this.mSpanIndexCache.clear();
        }
        
        public boolean isSpanIndexCacheEnabled() {
            return this.mCacheSpanIndices;
        }
        
        public void setSpanIndexCacheEnabled(final boolean mCacheSpanIndices) {
            this.mCacheSpanIndices = mCacheSpanIndices;
        }
    }
}
