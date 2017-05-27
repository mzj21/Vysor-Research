// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.accessibility.AccessibilityEvent;
import android.graphics.PointF;
import android.util.Log;
import java.util.List;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.widget.helper.ItemTouchHelper;

public class LinearLayoutManager extends LayoutManager implements ScrollVectorProvider, ViewDropHandler
{
    static final boolean DEBUG = false;
    public static final int HORIZONTAL = 0;
    public static final int INVALID_OFFSET = Integer.MIN_VALUE;
    private static final float MAX_SCROLL_FACTOR = 0.33333334f;
    private static final String TAG = "LinearLayoutManager";
    public static final int VERTICAL = 1;
    final AnchorInfo mAnchorInfo;
    private boolean mLastStackFromEnd;
    private final LayoutChunkResult mLayoutChunkResult;
    private LayoutState mLayoutState;
    int mOrientation;
    OrientationHelper mOrientationHelper;
    SavedState mPendingSavedState;
    int mPendingScrollPosition;
    int mPendingScrollPositionOffset;
    private boolean mRecycleChildrenOnDetach;
    private boolean mReverseLayout;
    boolean mShouldReverseLayout;
    private boolean mSmoothScrollbarEnabled;
    private boolean mStackFromEnd;
    
    public LinearLayoutManager(final Context context) {
        this(context, 1, false);
    }
    
    public LinearLayoutManager(final Context context, final int orientation, final boolean reverseLayout) {
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo = new AnchorInfo();
        this.mLayoutChunkResult = new LayoutChunkResult();
        this.setOrientation(orientation);
        this.setReverseLayout(reverseLayout);
        ((RecyclerView.LayoutManager)this).setAutoMeasureEnabled(true);
    }
    
    public LinearLayoutManager(final Context context, final AttributeSet set, final int n, final int n2) {
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo = new AnchorInfo();
        this.mLayoutChunkResult = new LayoutChunkResult();
        final Properties properties = RecyclerView.LayoutManager.getProperties(context, set, n, n2);
        this.setOrientation(properties.orientation);
        this.setReverseLayout(properties.reverseLayout);
        this.setStackFromEnd(properties.stackFromEnd);
        ((RecyclerView.LayoutManager)this).setAutoMeasureEnabled(true);
    }
    
    private int computeScrollExtent(final State state) {
        final int childCount = ((RecyclerView.LayoutManager)this).getChildCount();
        int computeScrollExtent = 0;
        if (childCount != 0) {
            this.ensureLayoutState();
            final OrientationHelper mOrientationHelper = this.mOrientationHelper;
            final View firstVisibleChildClosestToStart = this.findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true);
            final boolean mSmoothScrollbarEnabled = this.mSmoothScrollbarEnabled;
            boolean b = false;
            if (!mSmoothScrollbarEnabled) {
                b = true;
            }
            computeScrollExtent = ScrollbarHelper.computeScrollExtent(state, mOrientationHelper, firstVisibleChildClosestToStart, this.findFirstVisibleChildClosestToEnd(b, true), this, this.mSmoothScrollbarEnabled);
        }
        return computeScrollExtent;
    }
    
    private int computeScrollOffset(final State state) {
        final int childCount = ((RecyclerView.LayoutManager)this).getChildCount();
        int computeScrollOffset = 0;
        if (childCount != 0) {
            this.ensureLayoutState();
            final OrientationHelper mOrientationHelper = this.mOrientationHelper;
            final View firstVisibleChildClosestToStart = this.findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true);
            final boolean mSmoothScrollbarEnabled = this.mSmoothScrollbarEnabled;
            boolean b = false;
            if (!mSmoothScrollbarEnabled) {
                b = true;
            }
            computeScrollOffset = ScrollbarHelper.computeScrollOffset(state, mOrientationHelper, firstVisibleChildClosestToStart, this.findFirstVisibleChildClosestToEnd(b, true), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
        }
        return computeScrollOffset;
    }
    
    private int computeScrollRange(final State state) {
        final int childCount = ((RecyclerView.LayoutManager)this).getChildCount();
        int computeScrollRange = 0;
        if (childCount != 0) {
            this.ensureLayoutState();
            final OrientationHelper mOrientationHelper = this.mOrientationHelper;
            final View firstVisibleChildClosestToStart = this.findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true);
            final boolean mSmoothScrollbarEnabled = this.mSmoothScrollbarEnabled;
            boolean b = false;
            if (!mSmoothScrollbarEnabled) {
                b = true;
            }
            computeScrollRange = ScrollbarHelper.computeScrollRange(state, mOrientationHelper, firstVisibleChildClosestToStart, this.findFirstVisibleChildClosestToEnd(b, true), this, this.mSmoothScrollbarEnabled);
        }
        return computeScrollRange;
    }
    
    private View findFirstReferenceChild(final Recycler recycler, final State state) {
        return this.findReferenceChild(recycler, state, 0, ((RecyclerView.LayoutManager)this).getChildCount(), state.getItemCount());
    }
    
    private View findFirstVisibleChildClosestToEnd(final boolean b, final boolean b2) {
        View view;
        if (this.mShouldReverseLayout) {
            view = this.findOneVisibleChild(0, ((RecyclerView.LayoutManager)this).getChildCount(), b, b2);
        }
        else {
            view = this.findOneVisibleChild(-1 + ((RecyclerView.LayoutManager)this).getChildCount(), -1, b, b2);
        }
        return view;
    }
    
    private View findFirstVisibleChildClosestToStart(final boolean b, final boolean b2) {
        View view;
        if (this.mShouldReverseLayout) {
            view = this.findOneVisibleChild(-1 + ((RecyclerView.LayoutManager)this).getChildCount(), -1, b, b2);
        }
        else {
            view = this.findOneVisibleChild(0, ((RecyclerView.LayoutManager)this).getChildCount(), b, b2);
        }
        return view;
    }
    
    private View findLastReferenceChild(final Recycler recycler, final State state) {
        return this.findReferenceChild(recycler, state, -1 + ((RecyclerView.LayoutManager)this).getChildCount(), -1, state.getItemCount());
    }
    
    private View findReferenceChildClosestToEnd(final Recycler recycler, final State state) {
        View view;
        if (this.mShouldReverseLayout) {
            view = this.findFirstReferenceChild(recycler, state);
        }
        else {
            view = this.findLastReferenceChild(recycler, state);
        }
        return view;
    }
    
    private View findReferenceChildClosestToStart(final Recycler recycler, final State state) {
        View view;
        if (this.mShouldReverseLayout) {
            view = this.findLastReferenceChild(recycler, state);
        }
        else {
            view = this.findFirstReferenceChild(recycler, state);
        }
        return view;
    }
    
    private int fixLayoutEndGap(final int n, final Recycler recycler, final State state, final boolean b) {
        final int n2 = this.mOrientationHelper.getEndAfterPadding() - n;
        int n6;
        if (n2 > 0) {
            final int n3 = -this.scrollBy(-n2, recycler, state);
            final int n4 = n + n3;
            if (b) {
                final int n5 = this.mOrientationHelper.getEndAfterPadding() - n4;
                if (n5 > 0) {
                    this.mOrientationHelper.offsetChildren(n5);
                    n6 = n5 + n3;
                    return n6;
                }
            }
            n6 = n3;
        }
        else {
            n6 = 0;
        }
        return n6;
    }
    
    private int fixLayoutStartGap(final int n, final Recycler recycler, final State state, final boolean b) {
        final int n2 = n - this.mOrientationHelper.getStartAfterPadding();
        int n6;
        if (n2 > 0) {
            final int n3 = -this.scrollBy(n2, recycler, state);
            final int n4 = n + n3;
            if (b) {
                final int n5 = n4 - this.mOrientationHelper.getStartAfterPadding();
                if (n5 > 0) {
                    this.mOrientationHelper.offsetChildren(-n5);
                    n6 = n3 - n5;
                    return n6;
                }
            }
            n6 = n3;
        }
        else {
            n6 = 0;
        }
        return n6;
    }
    
    private View getChildClosestToEnd() {
        int n;
        if (this.mShouldReverseLayout) {
            n = 0;
        }
        else {
            n = -1 + ((RecyclerView.LayoutManager)this).getChildCount();
        }
        return ((RecyclerView.LayoutManager)this).getChildAt(n);
    }
    
    private View getChildClosestToStart() {
        int n;
        if (this.mShouldReverseLayout) {
            n = -1 + ((RecyclerView.LayoutManager)this).getChildCount();
        }
        else {
            n = 0;
        }
        return ((RecyclerView.LayoutManager)this).getChildAt(n);
    }
    
    private void layoutForPredictiveAnimations(final Recycler recycler, final State state, final int n, final int n2) {
        if (state.willRunPredictiveAnimations() && ((RecyclerView.LayoutManager)this).getChildCount() != 0 && !state.isPreLayout() && this.supportsPredictiveItemAnimations()) {
            int mExtra = 0;
            int mExtra2 = 0;
            final List<ViewHolder> scrapList = recycler.getScrapList();
            final int size = scrapList.size();
            final int position = ((RecyclerView.LayoutManager)this).getPosition(((RecyclerView.LayoutManager)this).getChildAt(0));
            for (int i = 0; i < size; ++i) {
                final ViewHolder viewHolder = scrapList.get(i);
                if (!viewHolder.isRemoved()) {
                    int n3;
                    if (viewHolder.getLayoutPosition() < position != this.mShouldReverseLayout) {
                        n3 = -1;
                    }
                    else {
                        n3 = 1;
                    }
                    if (n3 == -1) {
                        mExtra += this.mOrientationHelper.getDecoratedMeasurement(viewHolder.itemView);
                    }
                    else {
                        mExtra2 += this.mOrientationHelper.getDecoratedMeasurement(viewHolder.itemView);
                    }
                }
            }
            this.mLayoutState.mScrapList = scrapList;
            if (mExtra > 0) {
                this.updateLayoutStateToFillStart(((RecyclerView.LayoutManager)this).getPosition(this.getChildClosestToStart()), n);
                this.mLayoutState.mExtra = mExtra;
                this.mLayoutState.mAvailable = 0;
                this.mLayoutState.assignPositionFromScrapList();
                this.fill(recycler, this.mLayoutState, state, false);
            }
            if (mExtra2 > 0) {
                this.updateLayoutStateToFillEnd(((RecyclerView.LayoutManager)this).getPosition(this.getChildClosestToEnd()), n2);
                this.mLayoutState.mExtra = mExtra2;
                this.mLayoutState.mAvailable = 0;
                this.mLayoutState.assignPositionFromScrapList();
                this.fill(recycler, this.mLayoutState, state, false);
            }
            this.mLayoutState.mScrapList = null;
        }
    }
    
    private void logChildren() {
        Log.d("LinearLayoutManager", "internal representation of views on the screen");
        for (int i = 0; i < ((RecyclerView.LayoutManager)this).getChildCount(); ++i) {
            final View child = ((RecyclerView.LayoutManager)this).getChildAt(i);
            Log.d("LinearLayoutManager", "item " + ((RecyclerView.LayoutManager)this).getPosition(child) + ", coord:" + this.mOrientationHelper.getDecoratedStart(child));
        }
        Log.d("LinearLayoutManager", "==============");
    }
    
    private void recycleByLayoutState(final Recycler recycler, final LayoutState layoutState) {
        if (layoutState.mRecycle && !layoutState.mInfinite) {
            if (layoutState.mLayoutDirection == -1) {
                this.recycleViewsFromEnd(recycler, layoutState.mScrollingOffset);
            }
            else {
                this.recycleViewsFromStart(recycler, layoutState.mScrollingOffset);
            }
        }
    }
    
    private void recycleChildren(final Recycler recycler, final int n, final int n2) {
        if (n != n2) {
            if (n2 > n) {
                for (int i = n2 - 1; i >= n; --i) {
                    ((RecyclerView.LayoutManager)this).removeAndRecycleViewAt(i, recycler);
                }
            }
            else {
                for (int j = n; j > n2; --j) {
                    ((RecyclerView.LayoutManager)this).removeAndRecycleViewAt(j, recycler);
                }
            }
        }
    }
    
    private void recycleViewsFromEnd(final Recycler recycler, final int n) {
        final int childCount = ((RecyclerView.LayoutManager)this).getChildCount();
        if (n >= 0) {
            final int n2 = this.mOrientationHelper.getEnd() - n;
            if (this.mShouldReverseLayout) {
                for (int i = 0; i < childCount; ++i) {
                    final View child = ((RecyclerView.LayoutManager)this).getChildAt(i);
                    if (this.mOrientationHelper.getDecoratedStart(child) < n2 || this.mOrientationHelper.getTransformedStartWithDecoration(child) < n2) {
                        this.recycleChildren(recycler, 0, i);
                        break;
                    }
                }
            }
            else {
                for (int j = childCount - 1; j >= 0; --j) {
                    final View child2 = ((RecyclerView.LayoutManager)this).getChildAt(j);
                    if (this.mOrientationHelper.getDecoratedStart(child2) < n2 || this.mOrientationHelper.getTransformedStartWithDecoration(child2) < n2) {
                        this.recycleChildren(recycler, childCount - 1, j);
                        break;
                    }
                }
            }
        }
    }
    
    private void recycleViewsFromStart(final Recycler recycler, final int n) {
        if (n >= 0) {
            final int childCount = ((RecyclerView.LayoutManager)this).getChildCount();
            if (this.mShouldReverseLayout) {
                for (int i = childCount - 1; i >= 0; --i) {
                    final View child = ((RecyclerView.LayoutManager)this).getChildAt(i);
                    if (this.mOrientationHelper.getDecoratedEnd(child) > n || this.mOrientationHelper.getTransformedEndWithDecoration(child) > n) {
                        this.recycleChildren(recycler, childCount - 1, i);
                        break;
                    }
                }
            }
            else {
                for (int j = 0; j < childCount; ++j) {
                    final View child2 = ((RecyclerView.LayoutManager)this).getChildAt(j);
                    if (this.mOrientationHelper.getDecoratedEnd(child2) > n || this.mOrientationHelper.getTransformedEndWithDecoration(child2) > n) {
                        this.recycleChildren(recycler, 0, j);
                        break;
                    }
                }
            }
        }
    }
    
    private void resolveShouldLayoutReverse() {
        boolean mShouldReverseLayout = true;
        if (this.mOrientation == (mShouldReverseLayout ? 1 : 0) || !this.isLayoutRTL()) {
            this.mShouldReverseLayout = this.mReverseLayout;
        }
        else {
            if (this.mReverseLayout) {
                mShouldReverseLayout = false;
            }
            this.mShouldReverseLayout = mShouldReverseLayout;
        }
    }
    
    private boolean updateAnchorFromChildren(final Recycler recycler, final State state, final AnchorInfo anchorInfo) {
        final int childCount = ((RecyclerView.LayoutManager)this).getChildCount();
        boolean b = false;
        if (childCount != 0) {
            final View focusedChild = ((RecyclerView.LayoutManager)this).getFocusedChild();
            if (focusedChild != null && anchorInfo.isViewValidAsAnchor(focusedChild, state)) {
                anchorInfo.assignFromViewAndKeepVisibleRect(focusedChild);
                b = true;
            }
            else {
                final boolean mLastStackFromEnd = this.mLastStackFromEnd;
                final boolean mStackFromEnd = this.mStackFromEnd;
                b = false;
                if (mLastStackFromEnd == mStackFromEnd) {
                    View view;
                    if (anchorInfo.mLayoutFromEnd) {
                        view = this.findReferenceChildClosestToEnd(recycler, state);
                    }
                    else {
                        view = this.findReferenceChildClosestToStart(recycler, state);
                    }
                    b = false;
                    if (view != null) {
                        anchorInfo.assignFromView(view);
                        if (!state.isPreLayout() && this.supportsPredictiveItemAnimations()) {
                            int n;
                            if (this.mOrientationHelper.getDecoratedStart(view) >= this.mOrientationHelper.getEndAfterPadding() || this.mOrientationHelper.getDecoratedEnd(view) < this.mOrientationHelper.getStartAfterPadding()) {
                                n = 1;
                            }
                            else {
                                n = 0;
                            }
                            if (n != 0) {
                                int mCoordinate;
                                if (anchorInfo.mLayoutFromEnd) {
                                    mCoordinate = this.mOrientationHelper.getEndAfterPadding();
                                }
                                else {
                                    mCoordinate = this.mOrientationHelper.getStartAfterPadding();
                                }
                                anchorInfo.mCoordinate = mCoordinate;
                            }
                        }
                        b = true;
                    }
                }
            }
        }
        return b;
    }
    
    private boolean updateAnchorFromPendingData(final State state, final AnchorInfo anchorInfo) {
        boolean mLayoutFromEnd = true;
        if (state.isPreLayout() || this.mPendingScrollPosition == -1) {
            mLayoutFromEnd = false;
        }
        else if (this.mPendingScrollPosition < 0 || this.mPendingScrollPosition >= state.getItemCount()) {
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            mLayoutFromEnd = false;
        }
        else {
            anchorInfo.mPosition = this.mPendingScrollPosition;
            if (this.mPendingSavedState != null && this.mPendingSavedState.hasValidAnchor()) {
                anchorInfo.mLayoutFromEnd = this.mPendingSavedState.mAnchorLayoutFromEnd;
                if (anchorInfo.mLayoutFromEnd) {
                    anchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - this.mPendingSavedState.mAnchorOffset;
                }
                else {
                    anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingSavedState.mAnchorOffset;
                }
            }
            else if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
                final View viewByPosition = this.findViewByPosition(this.mPendingScrollPosition);
                if (viewByPosition != null) {
                    if (this.mOrientationHelper.getDecoratedMeasurement(viewByPosition) > this.mOrientationHelper.getTotalSpace()) {
                        anchorInfo.assignCoordinateFromPadding();
                    }
                    else if (this.mOrientationHelper.getDecoratedStart(viewByPosition) - this.mOrientationHelper.getStartAfterPadding() < 0) {
                        anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding();
                        anchorInfo.mLayoutFromEnd = false;
                    }
                    else if (this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(viewByPosition) < 0) {
                        anchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding();
                        anchorInfo.mLayoutFromEnd = mLayoutFromEnd;
                    }
                    else {
                        int decoratedStart;
                        if (anchorInfo.mLayoutFromEnd) {
                            decoratedStart = this.mOrientationHelper.getDecoratedEnd(viewByPosition) + this.mOrientationHelper.getTotalSpaceChange();
                        }
                        else {
                            decoratedStart = this.mOrientationHelper.getDecoratedStart(viewByPosition);
                        }
                        anchorInfo.mCoordinate = decoratedStart;
                    }
                }
                else {
                    if (((RecyclerView.LayoutManager)this).getChildCount() > 0) {
                        final boolean b = this.mPendingScrollPosition < ((RecyclerView.LayoutManager)this).getPosition(((RecyclerView.LayoutManager)this).getChildAt(0)) && mLayoutFromEnd;
                        final boolean mShouldReverseLayout = this.mShouldReverseLayout;
                        boolean mLayoutFromEnd2 = false;
                        if (b == mShouldReverseLayout) {
                            mLayoutFromEnd2 = mLayoutFromEnd;
                        }
                        anchorInfo.mLayoutFromEnd = mLayoutFromEnd2;
                    }
                    anchorInfo.assignCoordinateFromPadding();
                }
            }
            else {
                anchorInfo.mLayoutFromEnd = this.mShouldReverseLayout;
                if (this.mShouldReverseLayout) {
                    anchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - this.mPendingScrollPositionOffset;
                }
                else {
                    anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingScrollPositionOffset;
                }
            }
        }
        return mLayoutFromEnd;
    }
    
    private void updateAnchorInfoForLayout(final Recycler recycler, final State state, final AnchorInfo anchorInfo) {
        if (!this.updateAnchorFromPendingData(state, anchorInfo) && !this.updateAnchorFromChildren(recycler, state, anchorInfo)) {
            anchorInfo.assignCoordinateFromPadding();
            int mPosition;
            if (this.mStackFromEnd) {
                mPosition = -1 + state.getItemCount();
            }
            else {
                mPosition = 0;
            }
            anchorInfo.mPosition = mPosition;
        }
    }
    
    private void updateLayoutState(final int mLayoutDirection, final int mAvailable, final boolean b, final State state) {
        int mItemDirection = -1;
        int mItemDirection2 = 1;
        this.mLayoutState.mInfinite = this.resolveIsInfinite();
        this.mLayoutState.mExtra = this.getExtraLayoutSpace(state);
        this.mLayoutState.mLayoutDirection = mLayoutDirection;
        int mScrollingOffset;
        if (mLayoutDirection == mItemDirection2) {
            final LayoutState mLayoutState = this.mLayoutState;
            mLayoutState.mExtra += this.mOrientationHelper.getEndPadding();
            final View childClosestToEnd = this.getChildClosestToEnd();
            final LayoutState mLayoutState2 = this.mLayoutState;
            if (!this.mShouldReverseLayout) {
                mItemDirection = mItemDirection2;
            }
            mLayoutState2.mItemDirection = mItemDirection;
            this.mLayoutState.mCurrentPosition = ((RecyclerView.LayoutManager)this).getPosition(childClosestToEnd) + this.mLayoutState.mItemDirection;
            this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedEnd(childClosestToEnd);
            mScrollingOffset = this.mOrientationHelper.getDecoratedEnd(childClosestToEnd) - this.mOrientationHelper.getEndAfterPadding();
        }
        else {
            final View childClosestToStart = this.getChildClosestToStart();
            final LayoutState mLayoutState3 = this.mLayoutState;
            mLayoutState3.mExtra += this.mOrientationHelper.getStartAfterPadding();
            final LayoutState mLayoutState4 = this.mLayoutState;
            if (!this.mShouldReverseLayout) {
                mItemDirection2 = mItemDirection;
            }
            mLayoutState4.mItemDirection = mItemDirection2;
            this.mLayoutState.mCurrentPosition = ((RecyclerView.LayoutManager)this).getPosition(childClosestToStart) + this.mLayoutState.mItemDirection;
            this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart);
            mScrollingOffset = -this.mOrientationHelper.getDecoratedStart(childClosestToStart) + this.mOrientationHelper.getStartAfterPadding();
        }
        this.mLayoutState.mAvailable = mAvailable;
        if (b) {
            final LayoutState mLayoutState5 = this.mLayoutState;
            mLayoutState5.mAvailable -= mScrollingOffset;
        }
        this.mLayoutState.mScrollingOffset = mScrollingOffset;
    }
    
    private void updateLayoutStateToFillEnd(final int mCurrentPosition, final int mOffset) {
        this.mLayoutState.mAvailable = this.mOrientationHelper.getEndAfterPadding() - mOffset;
        final LayoutState mLayoutState = this.mLayoutState;
        int mItemDirection;
        if (this.mShouldReverseLayout) {
            mItemDirection = -1;
        }
        else {
            mItemDirection = 1;
        }
        mLayoutState.mItemDirection = mItemDirection;
        this.mLayoutState.mCurrentPosition = mCurrentPosition;
        this.mLayoutState.mLayoutDirection = 1;
        this.mLayoutState.mOffset = mOffset;
        this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
    }
    
    private void updateLayoutStateToFillEnd(final AnchorInfo anchorInfo) {
        this.updateLayoutStateToFillEnd(anchorInfo.mPosition, anchorInfo.mCoordinate);
    }
    
    private void updateLayoutStateToFillStart(final int mCurrentPosition, final int mOffset) {
        this.mLayoutState.mAvailable = mOffset - this.mOrientationHelper.getStartAfterPadding();
        this.mLayoutState.mCurrentPosition = mCurrentPosition;
        final LayoutState mLayoutState = this.mLayoutState;
        int mItemDirection;
        if (this.mShouldReverseLayout) {
            mItemDirection = 1;
        }
        else {
            mItemDirection = -1;
        }
        mLayoutState.mItemDirection = mItemDirection;
        this.mLayoutState.mLayoutDirection = -1;
        this.mLayoutState.mOffset = mOffset;
        this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
    }
    
    private void updateLayoutStateToFillStart(final AnchorInfo anchorInfo) {
        this.updateLayoutStateToFillStart(anchorInfo.mPosition, anchorInfo.mCoordinate);
    }
    
    @Override
    public void assertNotInLayoutOrScroll(final String s) {
        if (this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(s);
        }
    }
    
    @Override
    public boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }
    
    @Override
    public boolean canScrollVertically() {
        boolean b = true;
        if (this.mOrientation != (b ? 1 : 0)) {
            b = false;
        }
        return b;
    }
    
    @Override
    public int computeHorizontalScrollExtent(final State state) {
        return this.computeScrollExtent(state);
    }
    
    @Override
    public int computeHorizontalScrollOffset(final State state) {
        return this.computeScrollOffset(state);
    }
    
    @Override
    public int computeHorizontalScrollRange(final State state) {
        return this.computeScrollRange(state);
    }
    
    @Override
    public PointF computeScrollVectorForPosition(final int n) {
        PointF pointF;
        if (((RecyclerView.LayoutManager)this).getChildCount() == 0) {
            pointF = null;
        }
        else {
            final int position = ((RecyclerView.LayoutManager)this).getPosition(((RecyclerView.LayoutManager)this).getChildAt(0));
            boolean b = false;
            if (n < position) {
                b = true;
            }
            int n2;
            if (b != this.mShouldReverseLayout) {
                n2 = -1;
            }
            else {
                n2 = 1;
            }
            if (this.mOrientation == 0) {
                pointF = new PointF((float)n2, 0.0f);
            }
            else {
                pointF = new PointF(0.0f, (float)n2);
            }
        }
        return pointF;
    }
    
    @Override
    public int computeVerticalScrollExtent(final State state) {
        return this.computeScrollExtent(state);
    }
    
    @Override
    public int computeVerticalScrollOffset(final State state) {
        return this.computeScrollOffset(state);
    }
    
    @Override
    public int computeVerticalScrollRange(final State state) {
        return this.computeScrollRange(state);
    }
    
    int convertFocusDirectionToLayoutDirection(final int n) {
        int n2 = -1;
        int n3 = Integer.MIN_VALUE;
        int n4 = 1;
        switch (n) {
            default: {
                n2 = n3;
                break;
            }
            case 1: {
                if (this.mOrientation != n4 && this.isLayoutRTL()) {
                    n2 = n4;
                    break;
                }
                break;
            }
            case 2: {
                if (this.mOrientation == n4) {
                    n2 = n4;
                    break;
                }
                if (!this.isLayoutRTL()) {
                    n2 = n4;
                    break;
                }
                break;
            }
            case 33: {
                if (this.mOrientation != n4) {
                    n2 = n3;
                    break;
                }
                break;
            }
            case 130: {
                if (this.mOrientation == n4) {
                    n3 = n4;
                }
                n2 = n3;
                break;
            }
            case 17: {
                if (this.mOrientation != 0) {
                    n2 = n3;
                    break;
                }
                break;
            }
            case 66: {
                if (this.mOrientation != 0) {
                    n4 = n3;
                }
                n2 = n4;
                break;
            }
        }
        return n2;
    }
    
    LayoutState createLayoutState() {
        return new LayoutState();
    }
    
    void ensureLayoutState() {
        if (this.mLayoutState == null) {
            this.mLayoutState = this.createLayoutState();
        }
        if (this.mOrientationHelper == null) {
            this.mOrientationHelper = OrientationHelper.createOrientationHelper(this, this.mOrientation);
        }
    }
    
    int fill(final Recycler recycler, final LayoutState layoutState, final State state, final boolean b) {
        final int mAvailable = layoutState.mAvailable;
        if (layoutState.mScrollingOffset != Integer.MIN_VALUE) {
            if (layoutState.mAvailable < 0) {
                layoutState.mScrollingOffset += layoutState.mAvailable;
            }
            this.recycleByLayoutState(recycler, layoutState);
        }
        int n = layoutState.mAvailable + layoutState.mExtra;
        final LayoutChunkResult mLayoutChunkResult = this.mLayoutChunkResult;
        while ((layoutState.mInfinite || n > 0) && layoutState.hasMore(state)) {
            mLayoutChunkResult.resetInternal();
            this.layoutChunk(recycler, state, layoutState, mLayoutChunkResult);
            if (mLayoutChunkResult.mFinished) {
                break;
            }
            layoutState.mOffset += mLayoutChunkResult.mConsumed * layoutState.mLayoutDirection;
            if (!mLayoutChunkResult.mIgnoreConsumed || this.mLayoutState.mScrapList != null || !state.isPreLayout()) {
                layoutState.mAvailable -= mLayoutChunkResult.mConsumed;
                n -= mLayoutChunkResult.mConsumed;
            }
            if (layoutState.mScrollingOffset != Integer.MIN_VALUE) {
                layoutState.mScrollingOffset += mLayoutChunkResult.mConsumed;
                if (layoutState.mAvailable < 0) {
                    layoutState.mScrollingOffset += layoutState.mAvailable;
                }
                this.recycleByLayoutState(recycler, layoutState);
            }
            if (b && mLayoutChunkResult.mFocusable) {
                break;
            }
        }
        return mAvailable - layoutState.mAvailable;
    }
    
    public int findFirstCompletelyVisibleItemPosition() {
        final View oneVisibleChild = this.findOneVisibleChild(0, ((RecyclerView.LayoutManager)this).getChildCount(), true, false);
        int position;
        if (oneVisibleChild == null) {
            position = -1;
        }
        else {
            position = ((RecyclerView.LayoutManager)this).getPosition(oneVisibleChild);
        }
        return position;
    }
    
    public int findFirstVisibleItemPosition() {
        final View oneVisibleChild = this.findOneVisibleChild(0, ((RecyclerView.LayoutManager)this).getChildCount(), false, true);
        int position;
        if (oneVisibleChild == null) {
            position = -1;
        }
        else {
            position = ((RecyclerView.LayoutManager)this).getPosition(oneVisibleChild);
        }
        return position;
    }
    
    public int findLastCompletelyVisibleItemPosition() {
        int position = -1;
        final View oneVisibleChild = this.findOneVisibleChild(-1 + ((RecyclerView.LayoutManager)this).getChildCount(), position, true, false);
        if (oneVisibleChild != null) {
            position = ((RecyclerView.LayoutManager)this).getPosition(oneVisibleChild);
        }
        return position;
    }
    
    public int findLastVisibleItemPosition() {
        int position = -1;
        final View oneVisibleChild = this.findOneVisibleChild(-1 + ((RecyclerView.LayoutManager)this).getChildCount(), position, false, true);
        if (oneVisibleChild != null) {
            position = ((RecyclerView.LayoutManager)this).getPosition(oneVisibleChild);
        }
        return position;
    }
    
    View findOneVisibleChild(final int n, final int n2, final boolean b, final boolean b2) {
        this.ensureLayoutState();
        final int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        final int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        int n3;
        if (n2 > n) {
            n3 = 1;
        }
        else {
            n3 = -1;
        }
        View view = null;
        for (int i = n; i != n2; i += n3) {
            final View child = ((RecyclerView.LayoutManager)this).getChildAt(i);
            final int decoratedStart = this.mOrientationHelper.getDecoratedStart(child);
            final int decoratedEnd = this.mOrientationHelper.getDecoratedEnd(child);
            if (decoratedStart < endAfterPadding && decoratedEnd > startAfterPadding) {
                if (!b || (decoratedStart >= startAfterPadding && decoratedEnd <= endAfterPadding)) {
                    return child;
                }
                if (b2 && view == null) {
                    view = child;
                }
            }
        }
        return view;
    }
    
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
            if (position >= 0 && position < n3) {
                if (((LayoutParams)child.getLayoutParams()).isItemRemoved()) {
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
    public View findViewByPosition(final int n) {
        final int childCount = ((RecyclerView.LayoutManager)this).getChildCount();
        View view;
        if (childCount == 0) {
            view = null;
        }
        else {
            final int n2 = n - ((RecyclerView.LayoutManager)this).getPosition(((RecyclerView.LayoutManager)this).getChildAt(0));
            if (n2 >= 0 && n2 < childCount) {
                view = ((RecyclerView.LayoutManager)this).getChildAt(n2);
                if (((RecyclerView.LayoutManager)this).getPosition(view) == n) {
                    return view;
                }
            }
            view = super.findViewByPosition(n);
        }
        return view;
    }
    
    @Override
    public LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }
    
    protected int getExtraLayoutSpace(final State state) {
        int totalSpace;
        if (state.hasTargetScrollPosition()) {
            totalSpace = this.mOrientationHelper.getTotalSpace();
        }
        else {
            totalSpace = 0;
        }
        return totalSpace;
    }
    
    public int getOrientation() {
        return this.mOrientation;
    }
    
    public boolean getRecycleChildrenOnDetach() {
        return this.mRecycleChildrenOnDetach;
    }
    
    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }
    
    public boolean getStackFromEnd() {
        return this.mStackFromEnd;
    }
    
    protected boolean isLayoutRTL() {
        boolean b = true;
        if (((RecyclerView.LayoutManager)this).getLayoutDirection() != (b ? 1 : 0)) {
            b = false;
        }
        return b;
    }
    
    public boolean isSmoothScrollbarEnabled() {
        return this.mSmoothScrollbarEnabled;
    }
    
    void layoutChunk(final Recycler recycler, final State state, final LayoutState layoutState, final LayoutChunkResult layoutChunkResult) {
        final View next = layoutState.next(recycler);
        if (next == null) {
            layoutChunkResult.mFinished = true;
        }
        else {
            final LayoutParams layoutParams = (LayoutParams)next.getLayoutParams();
            if (layoutState.mScrapList == null) {
                if (this.mShouldReverseLayout == (layoutState.mLayoutDirection == -1)) {
                    ((RecyclerView.LayoutManager)this).addView(next);
                }
                else {
                    ((RecyclerView.LayoutManager)this).addView(next, 0);
                }
            }
            else if (this.mShouldReverseLayout == (layoutState.mLayoutDirection == -1)) {
                ((RecyclerView.LayoutManager)this).addDisappearingView(next);
            }
            else {
                ((RecyclerView.LayoutManager)this).addDisappearingView(next, 0);
            }
            ((RecyclerView.LayoutManager)this).measureChildWithMargins(next, 0, 0);
            layoutChunkResult.mConsumed = this.mOrientationHelper.getDecoratedMeasurement(next);
            int mOffset;
            int n;
            int mOffset2;
            int n2;
            if (this.mOrientation == 1) {
                if (this.isLayoutRTL()) {
                    mOffset = ((RecyclerView.LayoutManager)this).getWidth() - ((RecyclerView.LayoutManager)this).getPaddingRight();
                    n = mOffset - this.mOrientationHelper.getDecoratedMeasurementInOther(next);
                }
                else {
                    n = ((RecyclerView.LayoutManager)this).getPaddingLeft();
                    mOffset = n + this.mOrientationHelper.getDecoratedMeasurementInOther(next);
                }
                if (layoutState.mLayoutDirection == -1) {
                    mOffset2 = layoutState.mOffset;
                    n2 = layoutState.mOffset - layoutChunkResult.mConsumed;
                }
                else {
                    n2 = layoutState.mOffset;
                    mOffset2 = layoutState.mOffset + layoutChunkResult.mConsumed;
                }
            }
            else {
                n2 = ((RecyclerView.LayoutManager)this).getPaddingTop();
                mOffset2 = n2 + this.mOrientationHelper.getDecoratedMeasurementInOther(next);
                if (layoutState.mLayoutDirection == -1) {
                    mOffset = layoutState.mOffset;
                    n = layoutState.mOffset - layoutChunkResult.mConsumed;
                }
                else {
                    n = layoutState.mOffset;
                    mOffset = layoutState.mOffset + layoutChunkResult.mConsumed;
                }
            }
            ((RecyclerView.LayoutManager)this).layoutDecoratedWithMargins(next, n, n2, mOffset, mOffset2);
            if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
                layoutChunkResult.mIgnoreConsumed = true;
            }
            layoutChunkResult.mFocusable = next.isFocusable();
        }
    }
    
    void onAnchorReady(final Recycler recycler, final State state, final AnchorInfo anchorInfo, final int n) {
    }
    
    @Override
    public void onDetachedFromWindow(final RecyclerView recyclerView, final Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        if (this.mRecycleChildrenOnDetach) {
            ((RecyclerView.LayoutManager)this).removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }
    
    @Override
    public View onFocusSearchFailed(final View view, final int n, final Recycler recycler, final State state) {
        this.resolveShouldLayoutReverse();
        View view2;
        if (((RecyclerView.LayoutManager)this).getChildCount() == 0) {
            view2 = null;
        }
        else {
            final int convertFocusDirectionToLayoutDirection = this.convertFocusDirectionToLayoutDirection(n);
            if (convertFocusDirectionToLayoutDirection == Integer.MIN_VALUE) {
                view2 = null;
            }
            else {
                this.ensureLayoutState();
                View view3;
                if (convertFocusDirectionToLayoutDirection == -1) {
                    view3 = this.findReferenceChildClosestToStart(recycler, state);
                }
                else {
                    view3 = this.findReferenceChildClosestToEnd(recycler, state);
                }
                if (view3 == null) {
                    view2 = null;
                }
                else {
                    this.ensureLayoutState();
                    this.updateLayoutState(convertFocusDirectionToLayoutDirection, (int)(0.33333334f * this.mOrientationHelper.getTotalSpace()), false, state);
                    this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
                    this.mLayoutState.mRecycle = false;
                    this.fill(recycler, this.mLayoutState, state, true);
                    if (convertFocusDirectionToLayoutDirection == -1) {
                        view2 = this.getChildClosestToStart();
                    }
                    else {
                        view2 = this.getChildClosestToEnd();
                    }
                    if (view2 == view3 || !view2.isFocusable()) {
                        view2 = null;
                    }
                }
            }
        }
        return view2;
    }
    
    @Override
    public void onInitializeAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (((RecyclerView.LayoutManager)this).getChildCount() > 0) {
            final AccessibilityRecordCompat record = AccessibilityEventCompat.asRecord(accessibilityEvent);
            record.setFromIndex(this.findFirstVisibleItemPosition());
            record.setToIndex(this.findLastVisibleItemPosition());
        }
    }
    
    @Override
    public void onLayoutChildren(final Recycler recycler, final State state) {
        if ((this.mPendingSavedState != null || this.mPendingScrollPosition != -1) && state.getItemCount() == 0) {
            ((RecyclerView.LayoutManager)this).removeAndRecycleAllViews(recycler);
        }
        else {
            if (this.mPendingSavedState != null && this.mPendingSavedState.hasValidAnchor()) {
                this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
            }
            this.ensureLayoutState();
            this.mLayoutState.mRecycle = false;
            this.resolveShouldLayoutReverse();
            if (!this.mAnchorInfo.mValid || this.mPendingScrollPosition != -1 || this.mPendingSavedState != null) {
                this.mAnchorInfo.reset();
                this.mAnchorInfo.mLayoutFromEnd = (this.mShouldReverseLayout ^ this.mStackFromEnd);
                this.updateAnchorInfoForLayout(recycler, state, this.mAnchorInfo);
                this.mAnchorInfo.mValid = true;
            }
            final int extraLayoutSpace = this.getExtraLayoutSpace(state);
            int n;
            int n2;
            if (this.mLayoutState.mLastScrollDelta >= 0) {
                n = extraLayoutSpace;
                n2 = 0;
            }
            else {
                n2 = extraLayoutSpace;
                n = 0;
            }
            int n3 = n2 + this.mOrientationHelper.getStartAfterPadding();
            int n4 = n + this.mOrientationHelper.getEndPadding();
            if (state.isPreLayout() && this.mPendingScrollPosition != -1 && this.mPendingScrollPositionOffset != Integer.MIN_VALUE) {
                final View viewByPosition = this.findViewByPosition(this.mPendingScrollPosition);
                if (viewByPosition != null) {
                    int n5;
                    if (this.mShouldReverseLayout) {
                        n5 = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(viewByPosition) - this.mPendingScrollPositionOffset;
                    }
                    else {
                        n5 = this.mPendingScrollPositionOffset - (this.mOrientationHelper.getDecoratedStart(viewByPosition) - this.mOrientationHelper.getStartAfterPadding());
                    }
                    if (n5 > 0) {
                        n3 += n5;
                    }
                    else {
                        n4 -= n5;
                    }
                }
            }
            int n6;
            if (this.mAnchorInfo.mLayoutFromEnd) {
                if (this.mShouldReverseLayout) {
                    n6 = 1;
                }
                else {
                    n6 = -1;
                }
            }
            else if (this.mShouldReverseLayout) {
                n6 = -1;
            }
            else {
                n6 = 1;
            }
            this.onAnchorReady(recycler, state, this.mAnchorInfo, n6);
            ((RecyclerView.LayoutManager)this).detachAndScrapAttachedViews(recycler);
            this.mLayoutState.mInfinite = this.resolveIsInfinite();
            this.mLayoutState.mIsPreLayout = state.isPreLayout();
            int n7;
            int n8;
            if (this.mAnchorInfo.mLayoutFromEnd) {
                this.updateLayoutStateToFillStart(this.mAnchorInfo);
                this.mLayoutState.mExtra = n3;
                this.fill(recycler, this.mLayoutState, state, false);
                n7 = this.mLayoutState.mOffset;
                final int mCurrentPosition = this.mLayoutState.mCurrentPosition;
                if (this.mLayoutState.mAvailable > 0) {
                    n4 += this.mLayoutState.mAvailable;
                }
                this.updateLayoutStateToFillEnd(this.mAnchorInfo);
                this.mLayoutState.mExtra = n4;
                final LayoutState mLayoutState = this.mLayoutState;
                mLayoutState.mCurrentPosition += this.mLayoutState.mItemDirection;
                this.fill(recycler, this.mLayoutState, state, false);
                n8 = this.mLayoutState.mOffset;
                if (this.mLayoutState.mAvailable > 0) {
                    final int mAvailable = this.mLayoutState.mAvailable;
                    this.updateLayoutStateToFillStart(mCurrentPosition, n7);
                    this.mLayoutState.mExtra = mAvailable;
                    this.fill(recycler, this.mLayoutState, state, false);
                    n7 = this.mLayoutState.mOffset;
                }
            }
            else {
                this.updateLayoutStateToFillEnd(this.mAnchorInfo);
                this.mLayoutState.mExtra = n4;
                this.fill(recycler, this.mLayoutState, state, false);
                n8 = this.mLayoutState.mOffset;
                final int mCurrentPosition2 = this.mLayoutState.mCurrentPosition;
                if (this.mLayoutState.mAvailable > 0) {
                    n3 += this.mLayoutState.mAvailable;
                }
                this.updateLayoutStateToFillStart(this.mAnchorInfo);
                this.mLayoutState.mExtra = n3;
                final LayoutState mLayoutState2 = this.mLayoutState;
                mLayoutState2.mCurrentPosition += this.mLayoutState.mItemDirection;
                this.fill(recycler, this.mLayoutState, state, false);
                n7 = this.mLayoutState.mOffset;
                if (this.mLayoutState.mAvailable > 0) {
                    final int mAvailable2 = this.mLayoutState.mAvailable;
                    this.updateLayoutStateToFillEnd(mCurrentPosition2, n8);
                    this.mLayoutState.mExtra = mAvailable2;
                    this.fill(recycler, this.mLayoutState, state, false);
                    n8 = this.mLayoutState.mOffset;
                }
            }
            if (((RecyclerView.LayoutManager)this).getChildCount() > 0) {
                if (this.mShouldReverseLayout ^ this.mStackFromEnd) {
                    final int fixLayoutEndGap = this.fixLayoutEndGap(n8, recycler, state, true);
                    final int n9 = n7 + fixLayoutEndGap;
                    final int n10 = n8 + fixLayoutEndGap;
                    final int fixLayoutStartGap = this.fixLayoutStartGap(n9, recycler, state, false);
                    n7 = n9 + fixLayoutStartGap;
                    n8 = n10 + fixLayoutStartGap;
                }
                else {
                    final int fixLayoutStartGap2 = this.fixLayoutStartGap(n7, recycler, state, true);
                    final int n11 = n7 + fixLayoutStartGap2;
                    final int n12 = n8 + fixLayoutStartGap2;
                    final int fixLayoutEndGap2 = this.fixLayoutEndGap(n12, recycler, state, false);
                    n7 = n11 + fixLayoutEndGap2;
                    n8 = n12 + fixLayoutEndGap2;
                }
            }
            this.layoutForPredictiveAnimations(recycler, state, n7, n8);
            if (!state.isPreLayout()) {
                this.mOrientationHelper.onLayoutComplete();
            }
            else {
                this.mAnchorInfo.reset();
            }
            this.mLastStackFromEnd = this.mStackFromEnd;
        }
    }
    
    @Override
    public void onLayoutCompleted(final State state) {
        super.onLayoutCompleted(state);
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mAnchorInfo.reset();
    }
    
    @Override
    public void onRestoreInstanceState(final Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.mPendingSavedState = (SavedState)parcelable;
            ((RecyclerView.LayoutManager)this).requestLayout();
        }
    }
    
    @Override
    public Parcelable onSaveInstanceState() {
        SavedState savedState;
        if (this.mPendingSavedState != null) {
            savedState = new SavedState(this.mPendingSavedState);
        }
        else {
            savedState = new SavedState();
            if (((RecyclerView.LayoutManager)this).getChildCount() > 0) {
                this.ensureLayoutState();
                final boolean mAnchorLayoutFromEnd = this.mLastStackFromEnd ^ this.mShouldReverseLayout;
                savedState.mAnchorLayoutFromEnd = mAnchorLayoutFromEnd;
                if (mAnchorLayoutFromEnd) {
                    final View childClosestToEnd = this.getChildClosestToEnd();
                    savedState.mAnchorOffset = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(childClosestToEnd);
                    savedState.mAnchorPosition = ((RecyclerView.LayoutManager)this).getPosition(childClosestToEnd);
                }
                else {
                    final View childClosestToStart = this.getChildClosestToStart();
                    savedState.mAnchorPosition = ((RecyclerView.LayoutManager)this).getPosition(childClosestToStart);
                    savedState.mAnchorOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart) - this.mOrientationHelper.getStartAfterPadding();
                }
            }
            else {
                savedState.invalidateAnchor();
            }
        }
        return (Parcelable)savedState;
    }
    
    @Override
    public void prepareForDrop(final View view, final View view2, final int n, final int n2) {
        this.assertNotInLayoutOrScroll("Cannot drop a view during a scroll or layout calculation");
        this.ensureLayoutState();
        this.resolveShouldLayoutReverse();
        final int position = ((RecyclerView.LayoutManager)this).getPosition(view);
        final int position2 = ((RecyclerView.LayoutManager)this).getPosition(view2);
        int n3;
        if (position < position2) {
            n3 = 1;
        }
        else {
            n3 = -1;
        }
        if (this.mShouldReverseLayout) {
            if (n3 == 1) {
                this.scrollToPositionWithOffset(position2, this.mOrientationHelper.getEndAfterPadding() - (this.mOrientationHelper.getDecoratedStart(view2) + this.mOrientationHelper.getDecoratedMeasurement(view)));
            }
            else {
                this.scrollToPositionWithOffset(position2, this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(view2));
            }
        }
        else if (n3 == -1) {
            this.scrollToPositionWithOffset(position2, this.mOrientationHelper.getDecoratedStart(view2));
        }
        else {
            this.scrollToPositionWithOffset(position2, this.mOrientationHelper.getDecoratedEnd(view2) - this.mOrientationHelper.getDecoratedMeasurement(view));
        }
    }
    
    boolean resolveIsInfinite() {
        return this.mOrientationHelper.getMode() == 0 && this.mOrientationHelper.getEnd() == 0;
    }
    
    int scrollBy(final int n, final Recycler recycler, final State state) {
        final int childCount = ((RecyclerView.LayoutManager)this).getChildCount();
        int mLastScrollDelta = 0;
        if (childCount != 0) {
            mLastScrollDelta = 0;
            if (n != 0) {
                this.mLayoutState.mRecycle = true;
                this.ensureLayoutState();
                int n2;
                if (n > 0) {
                    n2 = 1;
                }
                else {
                    n2 = -1;
                }
                final int abs = Math.abs(n);
                this.updateLayoutState(n2, abs, true, state);
                final int n3 = this.mLayoutState.mScrollingOffset + this.fill(recycler, this.mLayoutState, state, false);
                mLastScrollDelta = 0;
                if (n3 >= 0) {
                    if (abs > n3) {
                        mLastScrollDelta = n2 * n3;
                    }
                    else {
                        mLastScrollDelta = n;
                    }
                    this.mOrientationHelper.offsetChildren(-mLastScrollDelta);
                    this.mLayoutState.mLastScrollDelta = mLastScrollDelta;
                }
            }
        }
        return mLastScrollDelta;
    }
    
    @Override
    public int scrollHorizontallyBy(final int n, final Recycler recycler, final State state) {
        int scrollBy;
        if (this.mOrientation == 1) {
            scrollBy = 0;
        }
        else {
            scrollBy = this.scrollBy(n, recycler, state);
        }
        return scrollBy;
    }
    
    @Override
    public void scrollToPosition(final int mPendingScrollPosition) {
        this.mPendingScrollPosition = mPendingScrollPosition;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        if (this.mPendingSavedState != null) {
            this.mPendingSavedState.invalidateAnchor();
        }
        ((RecyclerView.LayoutManager)this).requestLayout();
    }
    
    public void scrollToPositionWithOffset(final int mPendingScrollPosition, final int mPendingScrollPositionOffset) {
        this.mPendingScrollPosition = mPendingScrollPosition;
        this.mPendingScrollPositionOffset = mPendingScrollPositionOffset;
        if (this.mPendingSavedState != null) {
            this.mPendingSavedState.invalidateAnchor();
        }
        ((RecyclerView.LayoutManager)this).requestLayout();
    }
    
    @Override
    public int scrollVerticallyBy(final int n, final Recycler recycler, final State state) {
        int scrollBy;
        if (this.mOrientation == 0) {
            scrollBy = 0;
        }
        else {
            scrollBy = this.scrollBy(n, recycler, state);
        }
        return scrollBy;
    }
    
    public void setOrientation(final int mOrientation) {
        if (mOrientation != 0 && mOrientation != 1) {
            throw new IllegalArgumentException("invalid orientation:" + mOrientation);
        }
        this.assertNotInLayoutOrScroll(null);
        if (mOrientation != this.mOrientation) {
            this.mOrientation = mOrientation;
            this.mOrientationHelper = null;
            ((RecyclerView.LayoutManager)this).requestLayout();
        }
    }
    
    public void setRecycleChildrenOnDetach(final boolean mRecycleChildrenOnDetach) {
        this.mRecycleChildrenOnDetach = mRecycleChildrenOnDetach;
    }
    
    public void setReverseLayout(final boolean mReverseLayout) {
        this.assertNotInLayoutOrScroll(null);
        if (mReverseLayout != this.mReverseLayout) {
            this.mReverseLayout = mReverseLayout;
            ((RecyclerView.LayoutManager)this).requestLayout();
        }
    }
    
    public void setSmoothScrollbarEnabled(final boolean mSmoothScrollbarEnabled) {
        this.mSmoothScrollbarEnabled = mSmoothScrollbarEnabled;
    }
    
    public void setStackFromEnd(final boolean mStackFromEnd) {
        this.assertNotInLayoutOrScroll(null);
        if (this.mStackFromEnd != mStackFromEnd) {
            this.mStackFromEnd = mStackFromEnd;
            ((RecyclerView.LayoutManager)this).requestLayout();
        }
    }
    
    @Override
    boolean shouldMeasureTwice() {
        return ((RecyclerView.LayoutManager)this).getHeightMode() != 1073741824 && ((RecyclerView.LayoutManager)this).getWidthMode() != 1073741824 && ((RecyclerView.LayoutManager)this).hasFlexibleChildInBothOrientations();
    }
    
    @Override
    public void smoothScrollToPosition(final RecyclerView recyclerView, final State state, final int targetPosition) {
        final LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        ((RecyclerView.SmoothScroller)linearSmoothScroller).setTargetPosition(targetPosition);
        ((RecyclerView.LayoutManager)this).startSmoothScroll(linearSmoothScroller);
    }
    
    @Override
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && this.mLastStackFromEnd == this.mStackFromEnd;
    }
    
    void validateChildOrder() {
        int n = 1;
        Log.d("LinearLayoutManager", "validating child count " + ((RecyclerView.LayoutManager)this).getChildCount());
        if (((RecyclerView.LayoutManager)this).getChildCount() >= n) {
            final int position = ((RecyclerView.LayoutManager)this).getPosition(((RecyclerView.LayoutManager)this).getChildAt(0));
            final int decoratedStart = this.mOrientationHelper.getDecoratedStart(((RecyclerView.LayoutManager)this).getChildAt(0));
            if (this.mShouldReverseLayout) {
                for (int i = 1; i < ((RecyclerView.LayoutManager)this).getChildCount(); ++i) {
                    final View child = ((RecyclerView.LayoutManager)this).getChildAt(i);
                    final int position2 = ((RecyclerView.LayoutManager)this).getPosition(child);
                    final int decoratedStart2 = this.mOrientationHelper.getDecoratedStart(child);
                    if (position2 < position) {
                        this.logChildren();
                        final StringBuilder append = new StringBuilder().append("detected invalid position. loc invalid? ");
                        if (decoratedStart2 >= decoratedStart) {
                            n = 0;
                        }
                        throw new RuntimeException(append.append(n != 0).toString());
                    }
                    if (decoratedStart2 > decoratedStart) {
                        this.logChildren();
                        throw new RuntimeException("detected invalid location");
                    }
                }
            }
            else {
                for (int j = 1; j < ((RecyclerView.LayoutManager)this).getChildCount(); ++j) {
                    final View child2 = ((RecyclerView.LayoutManager)this).getChildAt(j);
                    final int position3 = ((RecyclerView.LayoutManager)this).getPosition(child2);
                    final int decoratedStart3 = this.mOrientationHelper.getDecoratedStart(child2);
                    if (position3 < position) {
                        this.logChildren();
                        final StringBuilder append2 = new StringBuilder().append("detected invalid position. loc invalid? ");
                        if (decoratedStart3 >= decoratedStart) {
                            n = 0;
                        }
                        throw new RuntimeException(append2.append(n != 0).toString());
                    }
                    if (decoratedStart3 < decoratedStart) {
                        this.logChildren();
                        throw new RuntimeException("detected invalid location");
                    }
                }
            }
        }
    }
    
    class AnchorInfo
    {
        int mCoordinate;
        boolean mLayoutFromEnd;
        int mPosition;
        boolean mValid;
        
        AnchorInfo() {
            this.reset();
        }
        
        void assignCoordinateFromPadding() {
            int mCoordinate;
            if (this.mLayoutFromEnd) {
                mCoordinate = LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding();
            }
            else {
                mCoordinate = LinearLayoutManager.this.mOrientationHelper.getStartAfterPadding();
            }
            this.mCoordinate = mCoordinate;
        }
        
        public void assignFromView(final View view) {
            if (this.mLayoutFromEnd) {
                this.mCoordinate = LinearLayoutManager.this.mOrientationHelper.getDecoratedEnd(view) + LinearLayoutManager.this.mOrientationHelper.getTotalSpaceChange();
            }
            else {
                this.mCoordinate = LinearLayoutManager.this.mOrientationHelper.getDecoratedStart(view);
            }
            this.mPosition = ((RecyclerView.LayoutManager)LinearLayoutManager.this).getPosition(view);
        }
        
        public void assignFromViewAndKeepVisibleRect(final View view) {
            final int totalSpaceChange = LinearLayoutManager.this.mOrientationHelper.getTotalSpaceChange();
            if (totalSpaceChange >= 0) {
                this.assignFromView(view);
            }
            else {
                this.mPosition = ((RecyclerView.LayoutManager)LinearLayoutManager.this).getPosition(view);
                if (this.mLayoutFromEnd) {
                    final int n = LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding() - totalSpaceChange - LinearLayoutManager.this.mOrientationHelper.getDecoratedEnd(view);
                    this.mCoordinate = LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding() - n;
                    if (n > 0) {
                        final int n2 = this.mCoordinate - LinearLayoutManager.this.mOrientationHelper.getDecoratedMeasurement(view);
                        final int startAfterPadding = LinearLayoutManager.this.mOrientationHelper.getStartAfterPadding();
                        final int n3 = n2 - (startAfterPadding + Math.min(LinearLayoutManager.this.mOrientationHelper.getDecoratedStart(view) - startAfterPadding, 0));
                        if (n3 < 0) {
                            this.mCoordinate += Math.min(n, -n3);
                        }
                    }
                }
                else {
                    final int decoratedStart = LinearLayoutManager.this.mOrientationHelper.getDecoratedStart(view);
                    final int n4 = decoratedStart - LinearLayoutManager.this.mOrientationHelper.getStartAfterPadding();
                    this.mCoordinate = decoratedStart;
                    if (n4 > 0) {
                        final int n5 = LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding() - Math.min(0, LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding() - totalSpaceChange - LinearLayoutManager.this.mOrientationHelper.getDecoratedEnd(view)) - (decoratedStart + LinearLayoutManager.this.mOrientationHelper.getDecoratedMeasurement(view));
                        if (n5 < 0) {
                            this.mCoordinate -= Math.min(n4, -n5);
                        }
                    }
                }
            }
        }
        
        boolean isViewValidAsAnchor(final View view, final State state) {
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            return !layoutParams.isItemRemoved() && layoutParams.getViewLayoutPosition() >= 0 && layoutParams.getViewLayoutPosition() < state.getItemCount();
        }
        
        void reset() {
            this.mPosition = -1;
            this.mCoordinate = Integer.MIN_VALUE;
            this.mLayoutFromEnd = false;
            this.mValid = false;
        }
        
        @Override
        public String toString() {
            return "AnchorInfo{mPosition=" + this.mPosition + ", mCoordinate=" + this.mCoordinate + ", mLayoutFromEnd=" + this.mLayoutFromEnd + ", mValid=" + this.mValid + '}';
        }
    }
    
    protected static class LayoutChunkResult
    {
        public int mConsumed;
        public boolean mFinished;
        public boolean mFocusable;
        public boolean mIgnoreConsumed;
        
        void resetInternal() {
            this.mConsumed = 0;
            this.mFinished = false;
            this.mIgnoreConsumed = false;
            this.mFocusable = false;
        }
    }
    
    static class LayoutState
    {
        static final int INVALID_LAYOUT = Integer.MIN_VALUE;
        static final int ITEM_DIRECTION_HEAD = -1;
        static final int ITEM_DIRECTION_TAIL = 1;
        static final int LAYOUT_END = 1;
        static final int LAYOUT_START = -1;
        static final int SCROLLING_OFFSET_NaN = Integer.MIN_VALUE;
        static final String TAG = "LLM#LayoutState";
        int mAvailable;
        int mCurrentPosition;
        int mExtra;
        boolean mInfinite;
        boolean mIsPreLayout;
        int mItemDirection;
        int mLastScrollDelta;
        int mLayoutDirection;
        int mOffset;
        boolean mRecycle;
        List<ViewHolder> mScrapList;
        int mScrollingOffset;
        
        LayoutState() {
            this.mRecycle = true;
            this.mExtra = 0;
            this.mIsPreLayout = false;
            this.mScrapList = null;
        }
        
        private View nextViewFromScrapList() {
            for (int size = this.mScrapList.size(), i = 0; i < size; ++i) {
                final View itemView = this.mScrapList.get(i).itemView;
                final LayoutParams layoutParams = (LayoutParams)itemView.getLayoutParams();
                if (!layoutParams.isItemRemoved() && this.mCurrentPosition == layoutParams.getViewLayoutPosition()) {
                    this.assignPositionFromScrapList(itemView);
                    return itemView;
                }
            }
            return null;
        }
        
        public void assignPositionFromScrapList() {
            this.assignPositionFromScrapList(null);
        }
        
        public void assignPositionFromScrapList(final View view) {
            final View nextViewInLimitedList = this.nextViewInLimitedList(view);
            if (nextViewInLimitedList == null) {
                this.mCurrentPosition = -1;
            }
            else {
                this.mCurrentPosition = ((LayoutParams)nextViewInLimitedList.getLayoutParams()).getViewLayoutPosition();
            }
        }
        
        boolean hasMore(final State state) {
            return this.mCurrentPosition >= 0 && this.mCurrentPosition < state.getItemCount();
        }
        
        void log() {
            Log.d("LLM#LayoutState", "avail:" + this.mAvailable + ", ind:" + this.mCurrentPosition + ", dir:" + this.mItemDirection + ", offset:" + this.mOffset + ", layoutDir:" + this.mLayoutDirection);
        }
        
        View next(final Recycler recycler) {
            View view;
            if (this.mScrapList != null) {
                view = this.nextViewFromScrapList();
            }
            else {
                view = recycler.getViewForPosition(this.mCurrentPosition);
                this.mCurrentPosition += this.mItemDirection;
            }
            return view;
        }
        
        public View nextViewInLimitedList(final View view) {
            final int size = this.mScrapList.size();
            View view2 = null;
            int n = Integer.MAX_VALUE;
            for (int i = 0; i < size; ++i) {
                final View itemView = this.mScrapList.get(i).itemView;
                final LayoutParams layoutParams = (LayoutParams)itemView.getLayoutParams();
                if (itemView != view && !layoutParams.isItemRemoved()) {
                    final int n2 = (layoutParams.getViewLayoutPosition() - this.mCurrentPosition) * this.mItemDirection;
                    if (n2 >= 0 && n2 < n) {
                        view2 = itemView;
                        if ((n = n2) == 0) {
                            break;
                        }
                    }
                }
            }
            return view2;
        }
    }
    
    public static class SavedState implements Parcelable
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        boolean mAnchorLayoutFromEnd;
        int mAnchorOffset;
        int mAnchorPosition;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<SavedState>() {
                public SavedState createFromParcel(final Parcel parcel) {
                    return new SavedState(parcel);
                }
                
                public SavedState[] newArray(final int n) {
                    return new SavedState[n];
                }
            };
        }
        
        public SavedState() {
        }
        
        SavedState(final Parcel parcel) {
            boolean mAnchorLayoutFromEnd = true;
            this.mAnchorPosition = parcel.readInt();
            this.mAnchorOffset = parcel.readInt();
            if (parcel.readInt() != (mAnchorLayoutFromEnd ? 1 : 0)) {
                mAnchorLayoutFromEnd = false;
            }
            this.mAnchorLayoutFromEnd = mAnchorLayoutFromEnd;
        }
        
        public SavedState(final SavedState savedState) {
            this.mAnchorPosition = savedState.mAnchorPosition;
            this.mAnchorOffset = savedState.mAnchorOffset;
            this.mAnchorLayoutFromEnd = savedState.mAnchorLayoutFromEnd;
        }
        
        public int describeContents() {
            return 0;
        }
        
        boolean hasValidAnchor() {
            return this.mAnchorPosition >= 0;
        }
        
        void invalidateAnchor() {
            this.mAnchorPosition = -1;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            parcel.writeInt(this.mAnchorPosition);
            parcel.writeInt(this.mAnchorOffset);
            int n2;
            if (this.mAnchorLayoutFromEnd) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            parcel.writeInt(n2);
        }
    }
}
