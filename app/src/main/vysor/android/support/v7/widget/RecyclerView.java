// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.widget.ScrollerCompat;
import android.graphics.PointF;
import android.support.v4.os.ParcelableCompat;
import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.os.Parcelable$Creator;
import android.support.v4.view.AbsSavedState;
import java.util.Collections;
import android.util.SparseIntArray;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.annotation.CallSuper;
import android.graphics.Matrix;
import android.view.ViewGroup$MarginLayoutParams;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import android.database.Observable;
import android.os.SystemClock;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.view.View$MeasureSpec;
import android.view.FocusFinder;
import android.view.ViewParent;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.util.SparseArray;
import android.support.v4.os.TraceCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.accessibility.AccessibilityEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.support.v7.recyclerview.R;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.view.ViewConfiguration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.view.VelocityTracker;
import android.graphics.RectF;
import android.graphics.Rect;
import android.support.v4.view.NestedScrollingChildHelper;
import java.util.List;
import java.util.ArrayList;
import android.support.annotation.VisibleForTesting;
import android.support.v4.widget.EdgeEffectCompat;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.NestedScrollingChild;
import android.view.ViewGroup;

public class RecyclerView extends ViewGroup implements NestedScrollingChild, ScrollingView
{
    static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC = false;
    private static final int[] CLIP_TO_PADDING_ATTR;
    static final boolean DEBUG = false;
    static final boolean DISPATCH_TEMP_DETACH = false;
    static final boolean FORCE_INVALIDATE_DISPLAY_LIST = false;
    public static final int HORIZONTAL = 0;
    private static final int INVALID_POINTER = -1;
    public static final int INVALID_TYPE = -1;
    private static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
    static final int MAX_SCROLL_DURATION = 2000;
    private static final int[] NESTED_SCROLLING_ATTRS;
    public static final long NO_ID = -1L;
    public static final int NO_POSITION = -1;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    static final String TAG = "RecyclerView";
    public static final int TOUCH_SLOP_DEFAULT = 0;
    public static final int TOUCH_SLOP_PAGING = 1;
    static final String TRACE_BIND_VIEW_TAG = "RV OnBindView";
    static final String TRACE_CREATE_VIEW_TAG = "RV CreateView";
    private static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG = "RV PartialInvalidate";
    private static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG = "RV FullInvalidate";
    private static final String TRACE_ON_LAYOUT_TAG = "RV OnLayout";
    static final String TRACE_SCROLL_TAG = "RV Scroll";
    public static final int VERTICAL = 1;
    static final Interpolator sQuinticInterpolator;
    RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
    private final AccessibilityManager mAccessibilityManager;
    private OnItemTouchListener mActiveOnItemTouchListener;
    Adapter mAdapter;
    AdapterHelper mAdapterHelper;
    boolean mAdapterUpdateDuringMeasure;
    private EdgeEffectCompat mBottomGlow;
    private ChildDrawingOrderCallback mChildDrawingOrderCallback;
    ChildHelper mChildHelper;
    boolean mClipToPadding;
    boolean mDataSetHasChangedAfterLayout;
    private int mDispatchScrollCounter;
    private int mEatRequestLayout;
    private int mEatenAccessibilityChangeFlags;
    @VisibleForTesting
    boolean mFirstLayoutComplete;
    boolean mHasFixedSize;
    private boolean mIgnoreMotionEventTillDown;
    private int mInitialTouchX;
    private int mInitialTouchY;
    boolean mIsAttached;
    ItemAnimator mItemAnimator;
    private ItemAnimatorListener mItemAnimatorListener;
    private Runnable mItemAnimatorRunner;
    final ArrayList<ItemDecoration> mItemDecorations;
    boolean mItemsAddedOrRemoved;
    boolean mItemsChanged;
    private int mLastTouchX;
    private int mLastTouchY;
    @VisibleForTesting
    LayoutManager mLayout;
    boolean mLayoutFrozen;
    private int mLayoutOrScrollCounter;
    boolean mLayoutRequestEaten;
    private EdgeEffectCompat mLeftGlow;
    private final int mMaxFlingVelocity;
    private final int mMinFlingVelocity;
    private final int[] mMinMaxLayoutPositions;
    private final int[] mNestedOffsets;
    private final RecyclerViewDataObserver mObserver;
    private List<OnChildAttachStateChangeListener> mOnChildAttachStateListeners;
    private OnFlingListener mOnFlingListener;
    private final ArrayList<OnItemTouchListener> mOnItemTouchListeners;
    private SavedState mPendingSavedState;
    private final boolean mPostUpdatesOnAnimation;
    boolean mPostedAnimatorRunner;
    private boolean mPreserveFocusAfterLayout;
    final Recycler mRecycler;
    RecyclerListener mRecyclerListener;
    private EdgeEffectCompat mRightGlow;
    private final int[] mScrollConsumed;
    private float mScrollFactor;
    private OnScrollListener mScrollListener;
    private List<OnScrollListener> mScrollListeners;
    private final int[] mScrollOffset;
    private int mScrollPointerId;
    private int mScrollState;
    private NestedScrollingChildHelper mScrollingChildHelper;
    final State mState;
    final Rect mTempRect;
    private final Rect mTempRect2;
    final RectF mTempRectF;
    private EdgeEffectCompat mTopGlow;
    private int mTouchSlop;
    final Runnable mUpdateChildViewsRunnable;
    private VelocityTracker mVelocityTracker;
    final ViewFlinger mViewFlinger;
    private final ViewInfoStore.ProcessCallback mViewInfoProcessCallback;
    final ViewInfoStore mViewInfoStore;
    
    static {
        NESTED_SCROLLING_ATTRS = new int[] { 16843830 };
        CLIP_TO_PADDING_ATTR = new int[] { 16842987 };
        LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = new Class[] { Context.class, AttributeSet.class, Integer.TYPE, Integer.TYPE };
        sQuinticInterpolator = (Interpolator)new Interpolator() {
            public float getInterpolation(final float n) {
                final float n2 = n - 1.0f;
                return 1.0f + n2 * (n2 * (n2 * (n2 * n2)));
            }
        };
    }
    
    public RecyclerView(final Context context) {
        this(context, null);
    }
    
    public RecyclerView(final Context context, @Nullable final AttributeSet set) {
        this(context, set, 0);
    }
    
    public RecyclerView(final Context context, @Nullable final AttributeSet set, final int n) {
        super(context, set, n);
        this.mObserver = new RecyclerViewDataObserver();
        this.mRecycler = new Recycler();
        this.mViewInfoStore = new ViewInfoStore();
        this.mUpdateChildViewsRunnable = new Runnable() {
            @Override
            public void run() {
                if (RecyclerView.this.mFirstLayoutComplete && !RecyclerView.this.isLayoutRequested()) {
                    if (!RecyclerView.this.mIsAttached) {
                        RecyclerView.this.requestLayout();
                    }
                    else if (RecyclerView.this.mLayoutFrozen) {
                        RecyclerView.this.mLayoutRequestEaten = true;
                    }
                    else {
                        RecyclerView.this.consumePendingUpdateOperations();
                    }
                }
            }
        };
        this.mTempRect = new Rect();
        this.mTempRect2 = new Rect();
        this.mTempRectF = new RectF();
        this.mItemDecorations = new ArrayList<ItemDecoration>();
        this.mOnItemTouchListeners = new ArrayList<OnItemTouchListener>();
        this.mEatRequestLayout = 0;
        this.mDataSetHasChangedAfterLayout = false;
        this.mLayoutOrScrollCounter = 0;
        this.mDispatchScrollCounter = 0;
        this.mItemAnimator = (ItemAnimator)new DefaultItemAnimator();
        this.mScrollState = 0;
        this.mScrollPointerId = -1;
        this.mScrollFactor = Float.MIN_VALUE;
        this.mPreserveFocusAfterLayout = true;
        this.mViewFlinger = new ViewFlinger();
        this.mState = new State();
        this.mItemsAddedOrRemoved = false;
        this.mItemsChanged = false;
        this.mItemAnimatorListener = (ItemAnimatorListener)new ItemAnimatorRestoreListener();
        this.mPostedAnimatorRunner = false;
        this.mMinMaxLayoutPositions = new int[2];
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        this.mNestedOffsets = new int[2];
        this.mItemAnimatorRunner = new Runnable() {
            @Override
            public void run() {
                if (RecyclerView.this.mItemAnimator != null) {
                    RecyclerView.this.mItemAnimator.runPendingAnimations();
                }
                RecyclerView.this.mPostedAnimatorRunner = false;
            }
        };
        this.mViewInfoProcessCallback = new ViewInfoStore.ProcessCallback() {
            @Override
            public void processAppeared(final ViewHolder viewHolder, final ItemHolderInfo itemHolderInfo, final ItemHolderInfo itemHolderInfo2) {
                RecyclerView.this.animateAppearance(viewHolder, itemHolderInfo, itemHolderInfo2);
            }
            
            @Override
            public void processDisappeared(final ViewHolder viewHolder, @NonNull final ItemHolderInfo itemHolderInfo, @Nullable final ItemHolderInfo itemHolderInfo2) {
                RecyclerView.this.mRecycler.unscrapView(viewHolder);
                RecyclerView.this.animateDisappearance(viewHolder, itemHolderInfo, itemHolderInfo2);
            }
            
            @Override
            public void processPersistent(final ViewHolder viewHolder, @NonNull final ItemHolderInfo itemHolderInfo, @NonNull final ItemHolderInfo itemHolderInfo2) {
                viewHolder.setIsRecyclable(false);
                if (RecyclerView.this.mDataSetHasChangedAfterLayout) {
                    if (RecyclerView.this.mItemAnimator.animateChange(viewHolder, viewHolder, itemHolderInfo, itemHolderInfo2)) {
                        RecyclerView.this.postAnimationRunner();
                    }
                }
                else if (RecyclerView.this.mItemAnimator.animatePersistence(viewHolder, itemHolderInfo, itemHolderInfo2)) {
                    RecyclerView.this.postAnimationRunner();
                }
            }
            
            @Override
            public void unused(final ViewHolder viewHolder) {
                RecyclerView.this.mLayout.removeAndRecycleView(viewHolder.itemView, RecyclerView.this.mRecycler);
            }
        };
        if (set != null) {
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, RecyclerView.CLIP_TO_PADDING_ATTR, n, 0);
            this.mClipToPadding = obtainStyledAttributes.getBoolean(0, true);
            obtainStyledAttributes.recycle();
        }
        else {
            this.mClipToPadding = true;
        }
        this.setScrollContainer(true);
        this.setFocusableInTouchMode(true);
        this.mPostUpdatesOnAnimation = (Build$VERSION.SDK_INT >= 16);
        final ViewConfiguration value = ViewConfiguration.get(context);
        this.mTouchSlop = value.getScaledTouchSlop();
        this.mMinFlingVelocity = value.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = value.getScaledMaximumFlingVelocity();
        this.setWillNotDraw(this.getOverScrollMode() == 2);
        this.mItemAnimator.setListener(this.mItemAnimatorListener);
        this.initAdapterManager();
        this.initChildrenHelper();
        if (ViewCompat.getImportantForAccessibility((View)this) == 0) {
            ViewCompat.setImportantForAccessibility((View)this, 1);
        }
        this.mAccessibilityManager = (AccessibilityManager)this.getContext().getSystemService("accessibility");
        this.setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
        boolean boolean1 = true;
        if (set != null) {
            final TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(set, R.styleable.RecyclerView, n, 0);
            final String string = obtainStyledAttributes2.getString(R.styleable.RecyclerView_layoutManager);
            if (obtainStyledAttributes2.getInt(R.styleable.RecyclerView_android_descendantFocusability, -1) == -1) {
                this.setDescendantFocusability(262144);
            }
            obtainStyledAttributes2.recycle();
            this.createLayoutManager(context, string, set, n, 0);
            if (Build$VERSION.SDK_INT >= 21) {
                final TypedArray obtainStyledAttributes3 = context.obtainStyledAttributes(set, RecyclerView.NESTED_SCROLLING_ATTRS, n, 0);
                boolean1 = obtainStyledAttributes3.getBoolean(0, true);
                obtainStyledAttributes3.recycle();
            }
        }
        else {
            this.setDescendantFocusability(262144);
        }
        this.setNestedScrollingEnabled(boolean1);
    }
    
    static /* synthetic */ void access$000(final RecyclerView recyclerView, final View view, final int n, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        recyclerView.attachViewToParent(view, n, viewGroup$LayoutParams);
    }
    
    static /* synthetic */ void access$100(final RecyclerView recyclerView, final int n) {
        recyclerView.detachViewFromParent(n);
    }
    
    static /* synthetic */ void access$1000(final RecyclerView recyclerView, final int n, final int n2) {
        recyclerView.setMeasuredDimension(n, n2);
    }
    
    static /* synthetic */ boolean access$500(final RecyclerView recyclerView) {
        return recyclerView.awakenScrollBars();
    }
    
    private void addAnimatingView(final ViewHolder viewHolder) {
        final View itemView = viewHolder.itemView;
        final boolean b = itemView.getParent() == this;
        this.mRecycler.unscrapView(this.getChildViewHolder(itemView));
        if (viewHolder.isTmpDetached()) {
            this.mChildHelper.attachViewToParent(itemView, -1, itemView.getLayoutParams(), true);
        }
        else if (!b) {
            this.mChildHelper.addView(itemView, true);
        }
        else {
            this.mChildHelper.hide(itemView);
        }
    }
    
    private void animateChange(@NonNull final ViewHolder mShadowingHolder, @NonNull final ViewHolder mShadowedHolder, @NonNull final ItemHolderInfo itemHolderInfo, @NonNull final ItemHolderInfo itemHolderInfo2, final boolean b, final boolean b2) {
        mShadowingHolder.setIsRecyclable(false);
        if (b) {
            this.addAnimatingView(mShadowingHolder);
        }
        if (mShadowingHolder != mShadowedHolder) {
            if (b2) {
                this.addAnimatingView(mShadowedHolder);
            }
            mShadowingHolder.mShadowedHolder = mShadowedHolder;
            this.addAnimatingView(mShadowingHolder);
            this.mRecycler.unscrapView(mShadowingHolder);
            mShadowedHolder.setIsRecyclable(false);
            mShadowedHolder.mShadowingHolder = mShadowingHolder;
        }
        if (this.mItemAnimator.animateChange(mShadowingHolder, mShadowedHolder, itemHolderInfo, itemHolderInfo2)) {
            this.postAnimationRunner();
        }
    }
    
    private void cancelTouch() {
        this.resetTouch();
        this.setScrollState(0);
    }
    
    private void createLayoutManager(final Context context, final String s, final AttributeSet set, final int n, final int n2) {
        if (s != null) {
            final String trim = s.trim();
            if (trim.length() != 0) {
                final String fullClassName = this.getFullClassName(context, trim);
                try {
                    Label_0129: {
                        if (!this.isInEditMode()) {
                            break Label_0129;
                        }
                        ClassLoader classLoader = this.getClass().getClassLoader();
                        while (true) {
                            final Class<? extends LayoutManager> subclass = classLoader.loadClass(fullClassName).asSubclass(LayoutManager.class);
                            try {
                                final Constructor<? extends LayoutManager> constructor = subclass.getConstructor(RecyclerView.LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
                                final Object[] array = { context, set, n, n2 };
                                constructor.setAccessible(true);
                                this.setLayoutManager((LayoutManager)constructor.newInstance(array));
                                break;
                                classLoader = context.getClassLoader();
                            }
                            catch (NoSuchMethodException ex2) {
                                try {
                                    final Constructor<? extends LayoutManager> constructor = subclass.getConstructor((Class<?>[])new Class[0]);
                                    final Object[] array = null;
                                }
                                catch (NoSuchMethodException ex) {
                                    ex.initCause(ex2);
                                    throw new IllegalStateException(set.getPositionDescription() + ": Error creating LayoutManager " + fullClassName, ex);
                                }
                            }
                        }
                    }
                }
                catch (ClassNotFoundException ex3) {
                    throw new IllegalStateException(set.getPositionDescription() + ": Unable to find LayoutManager " + fullClassName, ex3);
                }
                catch (InvocationTargetException ex4) {
                    throw new IllegalStateException(set.getPositionDescription() + ": Could not instantiate the LayoutManager: " + fullClassName, ex4);
                }
                catch (InstantiationException ex5) {
                    throw new IllegalStateException(set.getPositionDescription() + ": Could not instantiate the LayoutManager: " + fullClassName, ex5);
                }
                catch (IllegalAccessException ex6) {
                    throw new IllegalStateException(set.getPositionDescription() + ": Cannot access non-public constructor " + fullClassName, ex6);
                }
                catch (ClassCastException ex7) {
                    throw new IllegalStateException(set.getPositionDescription() + ": Class is not a LayoutManager " + fullClassName, ex7);
                }
            }
        }
    }
    
    private boolean didChildRangeChange(final int n, final int n2) {
        this.findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        if (this.mMinMaxLayoutPositions[0] == n) {
            final int n3 = this.mMinMaxLayoutPositions[1];
            final boolean b = false;
            if (n3 == n2) {
                return b;
            }
        }
        return true;
    }
    
    private void dispatchContentChangedIfNecessary() {
        final int mEatenAccessibilityChangeFlags = this.mEatenAccessibilityChangeFlags;
        this.mEatenAccessibilityChangeFlags = 0;
        if (mEatenAccessibilityChangeFlags != 0 && this.isAccessibilityEnabled()) {
            final AccessibilityEvent obtain = AccessibilityEvent.obtain();
            obtain.setEventType(2048);
            AccessibilityEventCompat.setContentChangeTypes(obtain, mEatenAccessibilityChangeFlags);
            this.sendAccessibilityEventUnchecked(obtain);
        }
    }
    
    private void dispatchLayoutStep1() {
        this.mState.assertLayoutStep(1);
        this.mState.mIsMeasuring = false;
        this.eatRequestLayout();
        this.mViewInfoStore.clear();
        this.onEnterLayoutOrScroll();
        this.saveFocusInfo();
        this.processAdapterUpdatesAndSetAnimationFlags();
        this.mState.mTrackOldChangeHolders = (this.mState.mRunSimpleAnimations && this.mItemsChanged);
        this.mItemsChanged = false;
        this.mItemsAddedOrRemoved = false;
        this.mState.mInPreLayout = this.mState.mRunPredictiveAnimations;
        this.mState.mItemCount = this.mAdapter.getItemCount();
        this.findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        if (this.mState.mRunSimpleAnimations) {
            for (int childCount = this.mChildHelper.getChildCount(), i = 0; i < childCount; ++i) {
                final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
                if (!childViewHolderInt.shouldIgnore() && (!childViewHolderInt.isInvalid() || this.mAdapter.hasStableIds())) {
                    this.mViewInfoStore.addToPreLayout(childViewHolderInt, this.mItemAnimator.recordPreLayoutInformation(this.mState, childViewHolderInt, ItemAnimator.buildAdapterChangeFlagsForAnimations(childViewHolderInt), childViewHolderInt.getUnmodifiedPayloads()));
                    if (this.mState.mTrackOldChangeHolders && childViewHolderInt.isUpdated() && !childViewHolderInt.isRemoved() && !childViewHolderInt.shouldIgnore() && !childViewHolderInt.isInvalid()) {
                        this.mViewInfoStore.addToOldChangeHolders(this.getChangedHolderKey(childViewHolderInt), childViewHolderInt);
                    }
                }
            }
        }
        if (this.mState.mRunPredictiveAnimations) {
            this.saveOldPositions();
            final boolean mStructureChanged = this.mState.mStructureChanged;
            this.mState.mStructureChanged = false;
            this.mLayout.onLayoutChildren(this.mRecycler, this.mState);
            this.mState.mStructureChanged = mStructureChanged;
            for (int j = 0; j < this.mChildHelper.getChildCount(); ++j) {
                final ViewHolder childViewHolderInt2 = getChildViewHolderInt(this.mChildHelper.getChildAt(j));
                if (!childViewHolderInt2.shouldIgnore() && !this.mViewInfoStore.isInPreLayout(childViewHolderInt2)) {
                    int buildAdapterChangeFlagsForAnimations = ItemAnimator.buildAdapterChangeFlagsForAnimations(childViewHolderInt2);
                    final boolean hasAnyOfTheFlags = childViewHolderInt2.hasAnyOfTheFlags(8192);
                    if (!hasAnyOfTheFlags) {
                        buildAdapterChangeFlagsForAnimations |= 0x1000;
                    }
                    final ItemHolderInfo recordPreLayoutInformation = this.mItemAnimator.recordPreLayoutInformation(this.mState, childViewHolderInt2, buildAdapterChangeFlagsForAnimations, childViewHolderInt2.getUnmodifiedPayloads());
                    if (hasAnyOfTheFlags) {
                        this.recordAnimationInfoIfBouncedHiddenView(childViewHolderInt2, recordPreLayoutInformation);
                    }
                    else {
                        this.mViewInfoStore.addToAppearedInPreLayoutHolders(childViewHolderInt2, recordPreLayoutInformation);
                    }
                }
            }
            this.clearOldPositions();
        }
        else {
            this.clearOldPositions();
        }
        this.onExitLayoutOrScroll();
        this.resumeRequestLayout(false);
        this.mState.mLayoutStep = 2;
    }
    
    private void dispatchLayoutStep2() {
        this.eatRequestLayout();
        this.onEnterLayoutOrScroll();
        this.mState.assertLayoutStep(6);
        this.mAdapterHelper.consumeUpdatesInOnePass();
        this.mState.mItemCount = this.mAdapter.getItemCount();
        this.mState.mDeletedInvisibleItemCountSincePreviousLayout = 0;
        this.mState.mInPreLayout = false;
        this.mLayout.onLayoutChildren(this.mRecycler, this.mState);
        this.mState.mStructureChanged = false;
        this.mPendingSavedState = null;
        this.mState.mRunSimpleAnimations = (this.mState.mRunSimpleAnimations && this.mItemAnimator != null);
        this.mState.mLayoutStep = 4;
        this.onExitLayoutOrScroll();
        this.resumeRequestLayout(false);
    }
    
    private void dispatchLayoutStep3() {
        this.mState.assertLayoutStep(4);
        this.eatRequestLayout();
        this.onEnterLayoutOrScroll();
        this.mState.mLayoutStep = 1;
        if (this.mState.mRunSimpleAnimations) {
            for (int i = -1 + this.mChildHelper.getChildCount(); i >= 0; --i) {
                final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
                if (!childViewHolderInt.shouldIgnore()) {
                    final long changedHolderKey = this.getChangedHolderKey(childViewHolderInt);
                    final ItemHolderInfo recordPostLayoutInformation = this.mItemAnimator.recordPostLayoutInformation(this.mState, childViewHolderInt);
                    final ViewHolder fromOldChangeHolders = this.mViewInfoStore.getFromOldChangeHolders(changedHolderKey);
                    if (fromOldChangeHolders != null && !fromOldChangeHolders.shouldIgnore()) {
                        final boolean disappearing = this.mViewInfoStore.isDisappearing(fromOldChangeHolders);
                        final boolean disappearing2 = this.mViewInfoStore.isDisappearing(childViewHolderInt);
                        if (disappearing && fromOldChangeHolders == childViewHolderInt) {
                            this.mViewInfoStore.addToPostLayout(childViewHolderInt, recordPostLayoutInformation);
                        }
                        else {
                            final ItemHolderInfo popFromPreLayout = this.mViewInfoStore.popFromPreLayout(fromOldChangeHolders);
                            this.mViewInfoStore.addToPostLayout(childViewHolderInt, recordPostLayoutInformation);
                            final ItemHolderInfo popFromPostLayout = this.mViewInfoStore.popFromPostLayout(childViewHolderInt);
                            if (popFromPreLayout == null) {
                                this.handleMissingPreInfoForChangeError(changedHolderKey, childViewHolderInt, fromOldChangeHolders);
                            }
                            else {
                                this.animateChange(fromOldChangeHolders, childViewHolderInt, popFromPreLayout, popFromPostLayout, disappearing, disappearing2);
                            }
                        }
                    }
                    else {
                        this.mViewInfoStore.addToPostLayout(childViewHolderInt, recordPostLayoutInformation);
                    }
                }
            }
            this.mViewInfoStore.process(this.mViewInfoProcessCallback);
        }
        this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
        this.mState.mPreviousLayoutItemCount = this.mState.mItemCount;
        this.mDataSetHasChangedAfterLayout = false;
        this.mState.mRunSimpleAnimations = false;
        this.mState.mRunPredictiveAnimations = false;
        this.mLayout.mRequestedSimpleAnimations = false;
        if (this.mRecycler.mChangedScrap != null) {
            this.mRecycler.mChangedScrap.clear();
        }
        this.mLayout.onLayoutCompleted(this.mState);
        this.onExitLayoutOrScroll();
        this.resumeRequestLayout(false);
        this.mViewInfoStore.clear();
        if (this.didChildRangeChange(this.mMinMaxLayoutPositions[0], this.mMinMaxLayoutPositions[1])) {
            this.dispatchOnScrolled(0, 0);
        }
        this.recoverFocusFromState();
        this.resetFocusInfo();
    }
    
    private boolean dispatchOnItemTouch(final MotionEvent motionEvent) {
        boolean b = true;
        final int action = motionEvent.getAction();
        if (this.mActiveOnItemTouchListener != null) {
            if (action == 0) {
                this.mActiveOnItemTouchListener = null;
            }
            else {
                this.mActiveOnItemTouchListener.onTouchEvent(this, motionEvent);
                if (action == 3 || action == (b ? 1 : 0)) {
                    this.mActiveOnItemTouchListener = null;
                    return b;
                }
                return b;
            }
        }
        if (action != 0) {
            for (int size = this.mOnItemTouchListeners.size(), i = 0; i < size; ++i) {
                final OnItemTouchListener mActiveOnItemTouchListener = this.mOnItemTouchListeners.get(i);
                if (mActiveOnItemTouchListener.onInterceptTouchEvent(this, motionEvent)) {
                    this.mActiveOnItemTouchListener = mActiveOnItemTouchListener;
                    return b;
                }
            }
        }
        b = false;
        return b;
    }
    
    private boolean dispatchOnItemTouchIntercept(final MotionEvent motionEvent) {
        final int action = motionEvent.getAction();
        if (action == 3 || action == 0) {
            this.mActiveOnItemTouchListener = null;
        }
        for (int size = this.mOnItemTouchListeners.size(), i = 0; i < size; ++i) {
            final OnItemTouchListener mActiveOnItemTouchListener = this.mOnItemTouchListeners.get(i);
            if (mActiveOnItemTouchListener.onInterceptTouchEvent(this, motionEvent) && action != 3) {
                this.mActiveOnItemTouchListener = mActiveOnItemTouchListener;
                return true;
            }
        }
        return false;
    }
    
    private void findMinMaxChildLayoutPositions(final int[] array) {
        final int childCount = this.mChildHelper.getChildCount();
        if (childCount == 0) {
            array[1] = (array[0] = -1);
        }
        else {
            int n = Integer.MAX_VALUE;
            int n2 = Integer.MIN_VALUE;
            for (int i = 0; i < childCount; ++i) {
                final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
                if (!childViewHolderInt.shouldIgnore()) {
                    final int layoutPosition = childViewHolderInt.getLayoutPosition();
                    if (layoutPosition < n) {
                        n = layoutPosition;
                    }
                    if (layoutPosition > n2) {
                        n2 = layoutPosition;
                    }
                }
            }
            array[0] = n;
            array[1] = n2;
        }
    }
    
    static ViewHolder getChildViewHolderInt(final View view) {
        Object mViewHolder;
        if (view == null) {
            mViewHolder = null;
        }
        else {
            mViewHolder = ((LayoutParams)view.getLayoutParams()).mViewHolder;
        }
        return (ViewHolder)mViewHolder;
    }
    
    private int getDeepestFocusedViewWithId(View focusedChild) {
        int n = focusedChild.getId();
        while (!focusedChild.isFocused() && focusedChild instanceof ViewGroup && focusedChild.hasFocus()) {
            focusedChild = ((ViewGroup)focusedChild).getFocusedChild();
            if (focusedChild.getId() != -1) {
                n = focusedChild.getId();
            }
        }
        return n;
    }
    
    private String getFullClassName(final Context context, String s) {
        if (s.charAt(0) == '.') {
            s = context.getPackageName() + s;
        }
        else if (!s.contains(".")) {
            s = RecyclerView.class.getPackage().getName() + '.' + s;
        }
        return s;
    }
    
    private float getScrollFactor() {
        if (this.mScrollFactor == Float.MIN_VALUE) {
            final TypedValue typedValue = new TypedValue();
            if (!this.getContext().getTheme().resolveAttribute(16842829, typedValue, true)) {
                return 0.0f;
            }
            this.mScrollFactor = typedValue.getDimension(this.getContext().getResources().getDisplayMetrics());
        }
        return this.mScrollFactor;
    }
    
    private NestedScrollingChildHelper getScrollingChildHelper() {
        if (this.mScrollingChildHelper == null) {
            this.mScrollingChildHelper = new NestedScrollingChildHelper((View)this);
        }
        return this.mScrollingChildHelper;
    }
    
    private void handleMissingPreInfoForChangeError(final long n, final ViewHolder viewHolder, final ViewHolder viewHolder2) {
        final int childCount = this.mChildHelper.getChildCount();
        int i = 0;
        while (i < childCount) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
            if (childViewHolderInt != viewHolder && this.getChangedHolderKey(childViewHolderInt) == n) {
                if (this.mAdapter != null && this.mAdapter.hasStableIds()) {
                    throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + childViewHolderInt + " \n View Holder 2:" + viewHolder);
                }
                throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + childViewHolderInt + " \n View Holder 2:" + viewHolder);
            }
            else {
                ++i;
            }
        }
        Log.e("RecyclerView", "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + viewHolder2 + " cannot be found but it is necessary for " + viewHolder);
    }
    
    private boolean hasUpdatedView() {
        for (int childCount = this.mChildHelper.getChildCount(), i = 0; i < childCount; ++i) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.isUpdated()) {
                return true;
            }
        }
        return false;
    }
    
    private void initChildrenHelper() {
        this.mChildHelper = new ChildHelper((ChildHelper.Callback)new ChildHelper.Callback() {
            @Override
            public void addView(final View view, final int n) {
                RecyclerView.this.addView(view, n);
                RecyclerView.this.dispatchChildAttached(view);
            }
            
            @Override
            public void attachViewToParent(final View view, final int n, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
                final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                if (childViewHolderInt != null) {
                    if (!childViewHolderInt.isTmpDetached() && !childViewHolderInt.shouldIgnore()) {
                        throw new IllegalArgumentException("Called attach on a child which is not detached: " + childViewHolderInt);
                    }
                    childViewHolderInt.clearTmpDetachFlag();
                }
                RecyclerView.access$000(RecyclerView.this, view, n, viewGroup$LayoutParams);
            }
            
            @Override
            public void detachViewFromParent(final int n) {
                final View child = this.getChildAt(n);
                if (child != null) {
                    final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(child);
                    if (childViewHolderInt != null) {
                        if (childViewHolderInt.isTmpDetached() && !childViewHolderInt.shouldIgnore()) {
                            throw new IllegalArgumentException("called detach on an already detached child " + childViewHolderInt);
                        }
                        childViewHolderInt.addFlags(256);
                    }
                }
                RecyclerView.access$100(RecyclerView.this, n);
            }
            
            @Override
            public View getChildAt(final int n) {
                return RecyclerView.this.getChildAt(n);
            }
            
            @Override
            public int getChildCount() {
                return RecyclerView.this.getChildCount();
            }
            
            @Override
            public ViewHolder getChildViewHolder(final View view) {
                return RecyclerView.getChildViewHolderInt(view);
            }
            
            @Override
            public int indexOfChild(final View view) {
                return RecyclerView.this.indexOfChild(view);
            }
            
            @Override
            public void onEnteredHiddenState(final View view) {
                final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                if (childViewHolderInt != null) {
                    childViewHolderInt.onEnteredHiddenState();
                }
            }
            
            @Override
            public void onLeftHiddenState(final View view) {
                final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                if (childViewHolderInt != null) {
                    childViewHolderInt.onLeftHiddenState();
                }
            }
            
            @Override
            public void removeAllViews() {
                for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
                    RecyclerView.this.dispatchChildDetached(this.getChildAt(i));
                }
                RecyclerView.this.removeAllViews();
            }
            
            @Override
            public void removeViewAt(final int n) {
                final View child = RecyclerView.this.getChildAt(n);
                if (child != null) {
                    RecyclerView.this.dispatchChildDetached(child);
                }
                RecyclerView.this.removeViewAt(n);
            }
        });
    }
    
    private boolean isPreferredNextFocus(final View view, final View view2, final int n) {
        boolean b = true;
        if (view2 == null || view2 == this) {
            b = false;
        }
        else if (view != null) {
            if (n == 2 || n == (b ? 1 : 0)) {
                final boolean b2 = this.mLayout.getLayoutDirection() == (b ? 1 : 0) && b;
                boolean b3 = false;
                if (n == 2) {
                    b3 = b;
                }
                int n2;
                if (b3 ^ b2) {
                    n2 = 66;
                }
                else {
                    n2 = 17;
                }
                if (!this.isPreferredNextFocusAbsolute(view, view2, n2)) {
                    if (n == 2) {
                        b = this.isPreferredNextFocusAbsolute(view, view2, 130);
                    }
                    else {
                        b = this.isPreferredNextFocusAbsolute(view, view2, 33);
                    }
                }
            }
            else {
                b = this.isPreferredNextFocusAbsolute(view, view2, n);
            }
        }
        return b;
    }
    
    private boolean isPreferredNextFocusAbsolute(final View view, final View view2, final int n) {
        boolean b = true;
        this.mTempRect.set(0, 0, view.getWidth(), view.getHeight());
        this.mTempRect2.set(0, 0, view2.getWidth(), view2.getHeight());
        this.offsetDescendantRectToMyCoords(view, this.mTempRect);
        this.offsetDescendantRectToMyCoords(view2, this.mTempRect2);
        switch (n) {
            default: {
                throw new IllegalArgumentException("direction must be absolute. received:" + n);
            }
            case 17: {
                if ((this.mTempRect.right > this.mTempRect2.right || this.mTempRect.left >= this.mTempRect2.right) && this.mTempRect.left > this.mTempRect2.left) {
                    break;
                }
                b = false;
                break;
            }
            case 66: {
                if ((this.mTempRect.left >= this.mTempRect2.left && this.mTempRect.right > this.mTempRect2.left) || this.mTempRect.right >= this.mTempRect2.right) {
                    b = false;
                    break;
                }
                break;
            }
            case 33: {
                if ((this.mTempRect.bottom <= this.mTempRect2.bottom && this.mTempRect.top < this.mTempRect2.bottom) || this.mTempRect.top <= this.mTempRect2.top) {
                    b = false;
                    break;
                }
                break;
            }
            case 130: {
                if ((this.mTempRect.top >= this.mTempRect2.top && this.mTempRect.bottom > this.mTempRect2.top) || this.mTempRect.bottom >= this.mTempRect2.bottom) {
                    b = false;
                    break;
                }
                break;
            }
        }
        return b;
    }
    
    private void onPointerUp(final MotionEvent motionEvent) {
        final int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (motionEvent.getPointerId(actionIndex) == this.mScrollPointerId) {
            int n;
            if (actionIndex == 0) {
                n = 1;
            }
            else {
                n = 0;
            }
            this.mScrollPointerId = motionEvent.getPointerId(n);
            final int n2 = (int)(0.5f + motionEvent.getX(n));
            this.mLastTouchX = n2;
            this.mInitialTouchX = n2;
            final int n3 = (int)(0.5f + motionEvent.getY(n));
            this.mLastTouchY = n3;
            this.mInitialTouchY = n3;
        }
    }
    
    private boolean predictiveItemAnimationsEnabled() {
        return this.mItemAnimator != null && this.mLayout.supportsPredictiveItemAnimations();
    }
    
    private void processAdapterUpdatesAndSetAnimationFlags() {
        boolean mRunPredictiveAnimations = true;
        if (this.mDataSetHasChangedAfterLayout) {
            this.mAdapterHelper.reset();
            this.markKnownViewsInvalid();
            this.mLayout.onItemsChanged(this);
        }
        if (this.predictiveItemAnimationsEnabled()) {
            this.mAdapterHelper.preProcess();
        }
        else {
            this.mAdapterHelper.consumeUpdatesInOnePass();
        }
        final boolean b = (this.mItemsAddedOrRemoved || this.mItemsChanged) && mRunPredictiveAnimations;
        this.mState.mRunSimpleAnimations = (this.mFirstLayoutComplete && this.mItemAnimator != null && (this.mDataSetHasChangedAfterLayout || b || this.mLayout.mRequestedSimpleAnimations) && (!this.mDataSetHasChangedAfterLayout || this.mAdapter.hasStableIds()) && mRunPredictiveAnimations);
        final State mState = this.mState;
        if (!this.mState.mRunSimpleAnimations || !b || this.mDataSetHasChangedAfterLayout || !this.predictiveItemAnimationsEnabled()) {
            mRunPredictiveAnimations = false;
        }
        mState.mRunPredictiveAnimations = mRunPredictiveAnimations;
    }
    
    private void pullGlows(final float n, final float n2, final float n3, final float n4) {
        boolean b;
        if (n2 < 0.0f) {
            this.ensureLeftGlow();
            final boolean onPull = this.mLeftGlow.onPull(-n2 / this.getWidth(), 1.0f - n3 / this.getHeight());
            b = false;
            if (onPull) {
                b = true;
            }
        }
        else {
            final float n5 = fcmpl(n2, 0.0f);
            b = false;
            if (n5 > 0) {
                this.ensureRightGlow();
                final boolean onPull2 = this.mRightGlow.onPull(n2 / this.getWidth(), n3 / this.getHeight());
                b = false;
                if (onPull2) {
                    b = true;
                }
            }
        }
        if (n4 < 0.0f) {
            this.ensureTopGlow();
            if (this.mTopGlow.onPull(-n4 / this.getHeight(), n / this.getWidth())) {
                b = true;
            }
        }
        else if (n4 > 0.0f) {
            this.ensureBottomGlow();
            if (this.mBottomGlow.onPull(n4 / this.getHeight(), 1.0f - n / this.getWidth())) {
                b = true;
            }
        }
        if (b || n2 != 0.0f || n4 != 0.0f) {
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    private void recoverFocusFromState() {
        if (this.mPreserveFocusAfterLayout && this.mAdapter != null && this.hasFocus()) {
            if (!this.isFocused()) {
                final View focusedChild = this.getFocusedChild();
                if (focusedChild == null || !this.mChildHelper.isHidden(focusedChild)) {
                    return;
                }
            }
            final int mFocusedItemPosition = this.mState.mFocusedItemPosition;
            ViewHolder viewHolder = null;
            if (mFocusedItemPosition != -1) {
                viewHolder = this.findViewHolderForAdapterPosition(this.mState.mFocusedItemPosition);
            }
            if (viewHolder == null && this.mState.mFocusedItemId != -1L && this.mAdapter.hasStableIds()) {
                viewHolder = this.findViewHolderForItemId(this.mState.mFocusedItemId);
            }
            if (viewHolder != null && !viewHolder.itemView.hasFocus() && viewHolder.itemView.hasFocusable()) {
                View itemView = viewHolder.itemView;
                if (this.mState.mFocusedSubChildId != -1L) {
                    final View viewById = viewHolder.itemView.findViewById(this.mState.mFocusedSubChildId);
                    if (viewById != null && viewById.isFocusable()) {
                        itemView = viewById;
                    }
                }
                itemView.requestFocus();
            }
        }
    }
    
    private void releaseGlows() {
        final EdgeEffectCompat mLeftGlow = this.mLeftGlow;
        boolean onRelease = false;
        if (mLeftGlow != null) {
            onRelease = this.mLeftGlow.onRelease();
        }
        if (this.mTopGlow != null) {
            onRelease |= this.mTopGlow.onRelease();
        }
        if (this.mRightGlow != null) {
            onRelease |= this.mRightGlow.onRelease();
        }
        if (this.mBottomGlow != null) {
            onRelease |= this.mBottomGlow.onRelease();
        }
        if (onRelease) {
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    private void resetFocusInfo() {
        this.mState.mFocusedItemId = -1L;
        this.mState.mFocusedItemPosition = -1;
        this.mState.mFocusedSubChildId = -1;
    }
    
    private void resetTouch() {
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.clear();
        }
        this.stopNestedScroll();
        this.releaseGlows();
    }
    
    private void saveFocusInfo() {
        final boolean mPreserveFocusAfterLayout = this.mPreserveFocusAfterLayout;
        View focusedChild = null;
        if (mPreserveFocusAfterLayout) {
            final boolean hasFocus = this.hasFocus();
            focusedChild = null;
            if (hasFocus) {
                final Adapter mAdapter = this.mAdapter;
                focusedChild = null;
                if (mAdapter != null) {
                    focusedChild = this.getFocusedChild();
                }
            }
        }
        ViewHolder containingViewHolder;
        if (focusedChild == null) {
            containingViewHolder = null;
        }
        else {
            containingViewHolder = this.findContainingViewHolder(focusedChild);
        }
        if (containingViewHolder == null) {
            this.resetFocusInfo();
        }
        else {
            final State mState = this.mState;
            long itemId;
            if (this.mAdapter.hasStableIds()) {
                itemId = containingViewHolder.getItemId();
            }
            else {
                itemId = -1L;
            }
            mState.mFocusedItemId = itemId;
            final State mState2 = this.mState;
            int adapterPosition;
            if (this.mDataSetHasChangedAfterLayout) {
                adapterPosition = -1;
            }
            else {
                adapterPosition = containingViewHolder.getAdapterPosition();
            }
            mState2.mFocusedItemPosition = adapterPosition;
            this.mState.mFocusedSubChildId = this.getDeepestFocusedViewWithId(containingViewHolder.itemView);
        }
    }
    
    private void setAdapterInternal(final Adapter mAdapter, final boolean b, final boolean b2) {
        if (this.mAdapter != null) {
            this.mAdapter.unregisterAdapterDataObserver(this.mObserver);
            this.mAdapter.onDetachedFromRecyclerView(this);
        }
        if (!b || b2) {
            if (this.mItemAnimator != null) {
                this.mItemAnimator.endAnimations();
            }
            if (this.mLayout != null) {
                this.mLayout.removeAndRecycleAllViews(this.mRecycler);
                this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
            }
            this.mRecycler.clear();
        }
        this.mAdapterHelper.reset();
        final Adapter mAdapter2 = this.mAdapter;
        if ((this.mAdapter = mAdapter) != null) {
            mAdapter.registerAdapterDataObserver(this.mObserver);
            mAdapter.onAttachedToRecyclerView(this);
        }
        if (this.mLayout != null) {
            this.mLayout.onAdapterChanged(mAdapter2, this.mAdapter);
        }
        this.mRecycler.onAdapterChanged(mAdapter2, this.mAdapter, b);
        this.mState.mStructureChanged = true;
        this.markKnownViewsInvalid();
    }
    
    private void stopScrollersInternal() {
        this.mViewFlinger.stop();
        if (this.mLayout != null) {
            this.mLayout.stopSmoothScroller();
        }
    }
    
    void absorbGlows(final int n, final int n2) {
        if (n < 0) {
            this.ensureLeftGlow();
            this.mLeftGlow.onAbsorb(-n);
        }
        else if (n > 0) {
            this.ensureRightGlow();
            this.mRightGlow.onAbsorb(n);
        }
        if (n2 < 0) {
            this.ensureTopGlow();
            this.mTopGlow.onAbsorb(-n2);
        }
        else if (n2 > 0) {
            this.ensureBottomGlow();
            this.mBottomGlow.onAbsorb(n2);
        }
        if (n != 0 || n2 != 0) {
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    public void addFocusables(final ArrayList<View> list, final int n, final int n2) {
        if (this.mLayout == null || !this.mLayout.onAddFocusables(this, list, n, n2)) {
            super.addFocusables((ArrayList)list, n, n2);
        }
    }
    
    public void addItemDecoration(final ItemDecoration itemDecoration) {
        this.addItemDecoration(itemDecoration, -1);
    }
    
    public void addItemDecoration(final ItemDecoration itemDecoration, final int n) {
        if (this.mLayout != null) {
            this.mLayout.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
        }
        if (this.mItemDecorations.isEmpty()) {
            this.setWillNotDraw(false);
        }
        if (n < 0) {
            this.mItemDecorations.add(itemDecoration);
        }
        else {
            this.mItemDecorations.add(n, itemDecoration);
        }
        this.markItemDecorInsetsDirty();
        this.requestLayout();
    }
    
    public void addOnChildAttachStateChangeListener(final OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        if (this.mOnChildAttachStateListeners == null) {
            this.mOnChildAttachStateListeners = new ArrayList<OnChildAttachStateChangeListener>();
        }
        this.mOnChildAttachStateListeners.add(onChildAttachStateChangeListener);
    }
    
    public void addOnItemTouchListener(final OnItemTouchListener onItemTouchListener) {
        this.mOnItemTouchListeners.add(onItemTouchListener);
    }
    
    public void addOnScrollListener(final OnScrollListener onScrollListener) {
        if (this.mScrollListeners == null) {
            this.mScrollListeners = new ArrayList<OnScrollListener>();
        }
        this.mScrollListeners.add(onScrollListener);
    }
    
    void animateAppearance(@NonNull final ViewHolder viewHolder, @Nullable final ItemHolderInfo itemHolderInfo, @NonNull final ItemHolderInfo itemHolderInfo2) {
        viewHolder.setIsRecyclable(false);
        if (this.mItemAnimator.animateAppearance(viewHolder, itemHolderInfo, itemHolderInfo2)) {
            this.postAnimationRunner();
        }
    }
    
    void animateDisappearance(@NonNull final ViewHolder viewHolder, @NonNull final ItemHolderInfo itemHolderInfo, @Nullable final ItemHolderInfo itemHolderInfo2) {
        this.addAnimatingView(viewHolder);
        viewHolder.setIsRecyclable(false);
        if (this.mItemAnimator.animateDisappearance(viewHolder, itemHolderInfo, itemHolderInfo2)) {
            this.postAnimationRunner();
        }
    }
    
    void assertInLayoutOrScroll(final String s) {
        if (this.isComputingLayout()) {
            return;
        }
        if (s == null) {
            throw new IllegalStateException("Cannot call this method unless RecyclerView is computing a layout or scrolling");
        }
        throw new IllegalStateException(s);
    }
    
    void assertNotInLayoutOrScroll(final String s) {
        if (!this.isComputingLayout()) {
            if (this.mDispatchScrollCounter > 0) {
                Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks might be run during a measure & layout pass where you cannot change the RecyclerView data. Any method call that might change the structure of the RecyclerView or the adapter contents should be postponed to the next frame.", (Throwable)new IllegalStateException(""));
            }
            return;
        }
        if (s == null) {
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling");
        }
        throw new IllegalStateException(s);
    }
    
    boolean canReuseUpdatedViewHolder(final ViewHolder viewHolder) {
        return this.mItemAnimator == null || this.mItemAnimator.canReuseUpdatedViewHolder(viewHolder, viewHolder.getUnmodifiedPayloads());
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof LayoutParams && this.mLayout.checkLayoutParams((LayoutParams)viewGroup$LayoutParams);
    }
    
    void clearOldPositions() {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.clearOldPosition();
            }
        }
        this.mRecycler.clearOldPositions();
    }
    
    public void clearOnChildAttachStateChangeListeners() {
        if (this.mOnChildAttachStateListeners != null) {
            this.mOnChildAttachStateListeners.clear();
        }
    }
    
    public void clearOnScrollListeners() {
        if (this.mScrollListeners != null) {
            this.mScrollListeners.clear();
        }
    }
    
    public int computeHorizontalScrollExtent() {
        final LayoutManager mLayout = this.mLayout;
        int computeHorizontalScrollExtent = 0;
        if (mLayout != null) {
            final boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
            computeHorizontalScrollExtent = 0;
            if (canScrollHorizontally) {
                computeHorizontalScrollExtent = this.mLayout.computeHorizontalScrollExtent(this.mState);
            }
        }
        return computeHorizontalScrollExtent;
    }
    
    public int computeHorizontalScrollOffset() {
        final LayoutManager mLayout = this.mLayout;
        int computeHorizontalScrollOffset = 0;
        if (mLayout != null) {
            final boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
            computeHorizontalScrollOffset = 0;
            if (canScrollHorizontally) {
                computeHorizontalScrollOffset = this.mLayout.computeHorizontalScrollOffset(this.mState);
            }
        }
        return computeHorizontalScrollOffset;
    }
    
    public int computeHorizontalScrollRange() {
        final LayoutManager mLayout = this.mLayout;
        int computeHorizontalScrollRange = 0;
        if (mLayout != null) {
            final boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
            computeHorizontalScrollRange = 0;
            if (canScrollHorizontally) {
                computeHorizontalScrollRange = this.mLayout.computeHorizontalScrollRange(this.mState);
            }
        }
        return computeHorizontalScrollRange;
    }
    
    public int computeVerticalScrollExtent() {
        final LayoutManager mLayout = this.mLayout;
        int computeVerticalScrollExtent = 0;
        if (mLayout != null) {
            final boolean canScrollVertically = this.mLayout.canScrollVertically();
            computeVerticalScrollExtent = 0;
            if (canScrollVertically) {
                computeVerticalScrollExtent = this.mLayout.computeVerticalScrollExtent(this.mState);
            }
        }
        return computeVerticalScrollExtent;
    }
    
    public int computeVerticalScrollOffset() {
        final LayoutManager mLayout = this.mLayout;
        int computeVerticalScrollOffset = 0;
        if (mLayout != null) {
            final boolean canScrollVertically = this.mLayout.canScrollVertically();
            computeVerticalScrollOffset = 0;
            if (canScrollVertically) {
                computeVerticalScrollOffset = this.mLayout.computeVerticalScrollOffset(this.mState);
            }
        }
        return computeVerticalScrollOffset;
    }
    
    public int computeVerticalScrollRange() {
        final LayoutManager mLayout = this.mLayout;
        int computeVerticalScrollRange = 0;
        if (mLayout != null) {
            final boolean canScrollVertically = this.mLayout.canScrollVertically();
            computeVerticalScrollRange = 0;
            if (canScrollVertically) {
                computeVerticalScrollRange = this.mLayout.computeVerticalScrollRange(this.mState);
            }
        }
        return computeVerticalScrollRange;
    }
    
    void considerReleasingGlowsOnScroll(final int n, final int n2) {
        final EdgeEffectCompat mLeftGlow = this.mLeftGlow;
        boolean onRelease = false;
        if (mLeftGlow != null) {
            final boolean finished = this.mLeftGlow.isFinished();
            onRelease = false;
            if (!finished) {
                onRelease = false;
                if (n > 0) {
                    onRelease = this.mLeftGlow.onRelease();
                }
            }
        }
        if (this.mRightGlow != null && !this.mRightGlow.isFinished() && n < 0) {
            onRelease |= this.mRightGlow.onRelease();
        }
        if (this.mTopGlow != null && !this.mTopGlow.isFinished() && n2 > 0) {
            onRelease |= this.mTopGlow.onRelease();
        }
        if (this.mBottomGlow != null && !this.mBottomGlow.isFinished() && n2 < 0) {
            onRelease |= this.mBottomGlow.onRelease();
        }
        if (onRelease) {
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    void consumePendingUpdateOperations() {
        if (!this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout) {
            TraceCompat.beginSection("RV FullInvalidate");
            this.dispatchLayout();
            TraceCompat.endSection();
        }
        else if (this.mAdapterHelper.hasPendingUpdates()) {
            if (this.mAdapterHelper.hasAnyUpdateTypes(4) && !this.mAdapterHelper.hasAnyUpdateTypes(11)) {
                TraceCompat.beginSection("RV PartialInvalidate");
                this.eatRequestLayout();
                this.mAdapterHelper.preProcess();
                if (!this.mLayoutRequestEaten) {
                    if (this.hasUpdatedView()) {
                        this.dispatchLayout();
                    }
                    else {
                        this.mAdapterHelper.consumePostponedUpdates();
                    }
                }
                this.resumeRequestLayout(true);
                TraceCompat.endSection();
            }
            else if (this.mAdapterHelper.hasPendingUpdates()) {
                TraceCompat.beginSection("RV FullInvalidate");
                this.dispatchLayout();
                TraceCompat.endSection();
            }
        }
    }
    
    void defaultOnMeasure(final int n, final int n2) {
        this.setMeasuredDimension(LayoutManager.chooseSize(n, this.getPaddingLeft() + this.getPaddingRight(), ViewCompat.getMinimumWidth((View)this)), LayoutManager.chooseSize(n2, this.getPaddingTop() + this.getPaddingBottom(), ViewCompat.getMinimumHeight((View)this)));
    }
    
    void dispatchChildAttached(final View view) {
        final ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        this.onChildAttachedToWindow(view);
        if (this.mAdapter != null && childViewHolderInt != null) {
            this.mAdapter.onViewAttachedToWindow(childViewHolderInt);
        }
        if (this.mOnChildAttachStateListeners != null) {
            for (int i = -1 + this.mOnChildAttachStateListeners.size(); i >= 0; --i) {
                this.mOnChildAttachStateListeners.get(i).onChildViewAttachedToWindow(view);
            }
        }
    }
    
    void dispatchChildDetached(final View view) {
        final ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        this.onChildDetachedFromWindow(view);
        if (this.mAdapter != null && childViewHolderInt != null) {
            this.mAdapter.onViewDetachedFromWindow(childViewHolderInt);
        }
        if (this.mOnChildAttachStateListeners != null) {
            for (int i = -1 + this.mOnChildAttachStateListeners.size(); i >= 0; --i) {
                this.mOnChildAttachStateListeners.get(i).onChildViewDetachedFromWindow(view);
            }
        }
    }
    
    void dispatchLayout() {
        if (this.mAdapter == null) {
            Log.e("RecyclerView", "No adapter attached; skipping layout");
        }
        else if (this.mLayout == null) {
            Log.e("RecyclerView", "No layout manager attached; skipping layout");
        }
        else {
            this.mState.mIsMeasuring = false;
            if (this.mState.mLayoutStep == 1) {
                this.dispatchLayoutStep1();
                this.mLayout.setExactMeasureSpecsFrom(this);
                this.dispatchLayoutStep2();
            }
            else if (this.mAdapterHelper.hasUpdates() || this.mLayout.getWidth() != this.getWidth() || this.mLayout.getHeight() != this.getHeight()) {
                this.mLayout.setExactMeasureSpecsFrom(this);
                this.dispatchLayoutStep2();
            }
            else {
                this.mLayout.setExactMeasureSpecsFrom(this);
            }
            this.dispatchLayoutStep3();
        }
    }
    
    public boolean dispatchNestedFling(final float n, final float n2, final boolean b) {
        return this.getScrollingChildHelper().dispatchNestedFling(n, n2, b);
    }
    
    public boolean dispatchNestedPreFling(final float n, final float n2) {
        return this.getScrollingChildHelper().dispatchNestedPreFling(n, n2);
    }
    
    public boolean dispatchNestedPreScroll(final int n, final int n2, final int[] array, final int[] array2) {
        return this.getScrollingChildHelper().dispatchNestedPreScroll(n, n2, array, array2);
    }
    
    public boolean dispatchNestedScroll(final int n, final int n2, final int n3, final int n4, final int[] array) {
        return this.getScrollingChildHelper().dispatchNestedScroll(n, n2, n3, n4, array);
    }
    
    void dispatchOnScrollStateChanged(final int n) {
        if (this.mLayout != null) {
            this.mLayout.onScrollStateChanged(n);
        }
        this.onScrollStateChanged(n);
        if (this.mScrollListener != null) {
            this.mScrollListener.onScrollStateChanged(this, n);
        }
        if (this.mScrollListeners != null) {
            for (int i = -1 + this.mScrollListeners.size(); i >= 0; --i) {
                this.mScrollListeners.get(i).onScrollStateChanged(this, n);
            }
        }
    }
    
    void dispatchOnScrolled(final int n, final int n2) {
        ++this.mDispatchScrollCounter;
        final int scrollX = this.getScrollX();
        final int scrollY = this.getScrollY();
        this.onScrollChanged(scrollX, scrollY, scrollX, scrollY);
        this.onScrolled(n, n2);
        if (this.mScrollListener != null) {
            this.mScrollListener.onScrolled(this, n, n2);
        }
        if (this.mScrollListeners != null) {
            for (int i = -1 + this.mScrollListeners.size(); i >= 0; --i) {
                this.mScrollListeners.get(i).onScrolled(this, n, n2);
            }
        }
        --this.mDispatchScrollCounter;
    }
    
    protected void dispatchRestoreInstanceState(final SparseArray<Parcelable> sparseArray) {
        this.dispatchThawSelfOnly((SparseArray)sparseArray);
    }
    
    protected void dispatchSaveInstanceState(final SparseArray<Parcelable> sparseArray) {
        this.dispatchFreezeSelfOnly((SparseArray)sparseArray);
    }
    
    public void draw(final Canvas canvas) {
        int n = 1;
        super.draw(canvas);
        for (int size = this.mItemDecorations.size(), i = 0; i < size; ++i) {
            this.mItemDecorations.get(i).onDrawOver(canvas, this, this.mState);
        }
        final EdgeEffectCompat mLeftGlow = this.mLeftGlow;
        int n2 = 0;
        if (mLeftGlow != null) {
            final boolean finished = this.mLeftGlow.isFinished();
            n2 = 0;
            if (!finished) {
                final int save = canvas.save();
                int paddingBottom;
                if (this.mClipToPadding) {
                    paddingBottom = this.getPaddingBottom();
                }
                else {
                    paddingBottom = 0;
                }
                canvas.rotate(270.0f);
                canvas.translate((float)(paddingBottom + -this.getHeight()), 0.0f);
                if (this.mLeftGlow != null && this.mLeftGlow.draw(canvas)) {
                    n2 = n;
                }
                else {
                    n2 = 0;
                }
                canvas.restoreToCount(save);
            }
        }
        if (this.mTopGlow != null && !this.mTopGlow.isFinished()) {
            final int save2 = canvas.save();
            if (this.mClipToPadding) {
                canvas.translate((float)this.getPaddingLeft(), (float)this.getPaddingTop());
            }
            int n3;
            if (this.mTopGlow != null && this.mTopGlow.draw(canvas)) {
                n3 = n;
            }
            else {
                n3 = 0;
            }
            n2 |= n3;
            canvas.restoreToCount(save2);
        }
        if (this.mRightGlow != null && !this.mRightGlow.isFinished()) {
            final int save3 = canvas.save();
            final int width = this.getWidth();
            int paddingTop;
            if (this.mClipToPadding) {
                paddingTop = this.getPaddingTop();
            }
            else {
                paddingTop = 0;
            }
            canvas.rotate(90.0f);
            canvas.translate((float)(-paddingTop), (float)(-width));
            int n4;
            if (this.mRightGlow != null && this.mRightGlow.draw(canvas)) {
                n4 = n;
            }
            else {
                n4 = 0;
            }
            n2 |= n4;
            canvas.restoreToCount(save3);
        }
        if (this.mBottomGlow != null && !this.mBottomGlow.isFinished()) {
            final int save4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.mClipToPadding) {
                canvas.translate((float)(-this.getWidth() + this.getPaddingRight()), (float)(-this.getHeight() + this.getPaddingBottom()));
            }
            else {
                canvas.translate((float)(-this.getWidth()), (float)(-this.getHeight()));
            }
            if (this.mBottomGlow == null || !this.mBottomGlow.draw(canvas)) {
                n = 0;
            }
            n2 |= n;
            canvas.restoreToCount(save4);
        }
        if (n2 == 0 && this.mItemAnimator != null && this.mItemDecorations.size() > 0 && this.mItemAnimator.isRunning()) {
            n2 = 1;
        }
        if (n2 != 0) {
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    public boolean drawChild(final Canvas canvas, final View view, final long n) {
        return super.drawChild(canvas, view, n);
    }
    
    void eatRequestLayout() {
        ++this.mEatRequestLayout;
        if (this.mEatRequestLayout == 1 && !this.mLayoutFrozen) {
            this.mLayoutRequestEaten = false;
        }
    }
    
    void ensureBottomGlow() {
        if (this.mBottomGlow == null) {
            this.mBottomGlow = new EdgeEffectCompat(this.getContext());
            if (this.mClipToPadding) {
                this.mBottomGlow.setSize(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight(), this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom());
            }
            else {
                this.mBottomGlow.setSize(this.getMeasuredWidth(), this.getMeasuredHeight());
            }
        }
    }
    
    void ensureLeftGlow() {
        if (this.mLeftGlow == null) {
            this.mLeftGlow = new EdgeEffectCompat(this.getContext());
            if (this.mClipToPadding) {
                this.mLeftGlow.setSize(this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom(), this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight());
            }
            else {
                this.mLeftGlow.setSize(this.getMeasuredHeight(), this.getMeasuredWidth());
            }
        }
    }
    
    void ensureRightGlow() {
        if (this.mRightGlow == null) {
            this.mRightGlow = new EdgeEffectCompat(this.getContext());
            if (this.mClipToPadding) {
                this.mRightGlow.setSize(this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom(), this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight());
            }
            else {
                this.mRightGlow.setSize(this.getMeasuredHeight(), this.getMeasuredWidth());
            }
        }
    }
    
    void ensureTopGlow() {
        if (this.mTopGlow == null) {
            this.mTopGlow = new EdgeEffectCompat(this.getContext());
            if (this.mClipToPadding) {
                this.mTopGlow.setSize(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight(), this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom());
            }
            else {
                this.mTopGlow.setSize(this.getMeasuredWidth(), this.getMeasuredHeight());
            }
        }
    }
    
    public View findChildViewUnder(final float n, final float n2) {
        for (int i = -1 + this.mChildHelper.getChildCount(); i >= 0; --i) {
            final View child = this.mChildHelper.getChildAt(i);
            final float translationX = ViewCompat.getTranslationX(child);
            final float translationY = ViewCompat.getTranslationY(child);
            if (n >= translationX + child.getLeft() && n <= translationX + child.getRight() && n2 >= translationY + child.getTop() && n2 <= translationY + child.getBottom()) {
                return child;
            }
        }
        return null;
    }
    
    @Nullable
    public View findContainingItemView(View view) {
        ViewParent viewParent;
        for (viewParent = view.getParent(); viewParent != null && viewParent != this && viewParent instanceof View; viewParent = view.getParent()) {
            view = (View)viewParent;
        }
        if (viewParent != this) {
            view = null;
        }
        return view;
    }
    
    @Nullable
    public ViewHolder findContainingViewHolder(final View view) {
        final View containingItemView = this.findContainingItemView(view);
        ViewHolder childViewHolder;
        if (containingItemView == null) {
            childViewHolder = null;
        }
        else {
            childViewHolder = this.getChildViewHolder(containingItemView);
        }
        return childViewHolder;
    }
    
    public ViewHolder findViewHolderForAdapterPosition(final int n) {
        ViewHolder childViewHolderInt;
        if (this.mDataSetHasChangedAfterLayout) {
            childViewHolderInt = null;
        }
        else {
            final int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
            ViewHolder viewHolder = null;
            for (int i = 0; i < unfilteredChildCount; ++i) {
                childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
                if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && this.getAdapterPositionFor(childViewHolderInt) == n) {
                    if (!this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                        return childViewHolderInt;
                    }
                    viewHolder = childViewHolderInt;
                }
            }
            childViewHolderInt = viewHolder;
        }
        return childViewHolderInt;
    }
    
    public ViewHolder findViewHolderForItemId(final long n) {
        ViewHolder childViewHolderInt;
        if (this.mAdapter == null || !this.mAdapter.hasStableIds()) {
            childViewHolderInt = null;
        }
        else {
            final int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
            ViewHolder viewHolder = null;
            for (int i = 0; i < unfilteredChildCount; ++i) {
                childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
                if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && childViewHolderInt.getItemId() == n) {
                    if (!this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                        return childViewHolderInt;
                    }
                    viewHolder = childViewHolderInt;
                }
            }
            childViewHolderInt = viewHolder;
        }
        return childViewHolderInt;
    }
    
    public ViewHolder findViewHolderForLayoutPosition(final int n) {
        return this.findViewHolderForPosition(n, false);
    }
    
    @Deprecated
    public ViewHolder findViewHolderForPosition(final int n) {
        return this.findViewHolderForPosition(n, false);
    }
    
    ViewHolder findViewHolderForPosition(final int n, final boolean b) {
        final int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        ViewHolder viewHolder = null;
        for (int i = 0; i < unfilteredChildCount; ++i) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved()) {
                if (b) {
                    if (childViewHolderInt.mPosition != n) {
                        continue;
                    }
                }
                else if (childViewHolderInt.getLayoutPosition() != n) {
                    continue;
                }
                if (!this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                    return childViewHolderInt;
                }
                viewHolder = childViewHolderInt;
            }
        }
        return viewHolder;
    }
    
    public boolean fling(int n, int n2) {
        boolean b = false;
        if (this.mLayout == null) {
            Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        }
        else {
            final boolean mLayoutFrozen = this.mLayoutFrozen;
            b = false;
            if (!mLayoutFrozen) {
                final boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
                final boolean canScrollVertically = this.mLayout.canScrollVertically();
                if (!canScrollHorizontally || Math.abs(n) < this.mMinFlingVelocity) {
                    n = 0;
                }
                if (!canScrollVertically || Math.abs(n2) < this.mMinFlingVelocity) {
                    n2 = 0;
                }
                if (n == 0) {
                    b = false;
                    if (n2 == 0) {
                        return b;
                    }
                }
                final boolean dispatchNestedPreFling = this.dispatchNestedPreFling(n, n2);
                b = false;
                if (!dispatchNestedPreFling) {
                    final boolean b2 = canScrollHorizontally || canScrollVertically;
                    this.dispatchNestedFling(n, n2, b2);
                    if (this.mOnFlingListener != null && this.mOnFlingListener.onFling(n, n2)) {
                        b = true;
                    }
                    else {
                        b = false;
                        if (b2) {
                            this.mViewFlinger.fling(Math.max(-this.mMaxFlingVelocity, Math.min(n, this.mMaxFlingVelocity)), Math.max(-this.mMaxFlingVelocity, Math.min(n2, this.mMaxFlingVelocity)));
                            b = true;
                        }
                    }
                }
            }
        }
        return b;
    }
    
    public View focusSearch(final View view, final int n) {
        final View onInterceptFocusSearch = this.mLayout.onInterceptFocusSearch(view, n);
        View focusSearch;
        if (onInterceptFocusSearch != null) {
            focusSearch = onInterceptFocusSearch;
        }
        else {
            boolean b;
            if (this.mAdapter != null && this.mLayout != null && !this.isComputingLayout() && !this.mLayoutFrozen) {
                b = true;
            }
            else {
                b = false;
            }
            final FocusFinder instance = FocusFinder.getInstance();
            View view2;
            if (b && (n == 2 || n == 1)) {
                final boolean canScrollVertically = this.mLayout.canScrollVertically();
                int n2 = 0;
                if (canScrollVertically) {
                    int n3;
                    if (n == 2) {
                        n3 = 130;
                    }
                    else {
                        n3 = 33;
                    }
                    if (instance.findNextFocus((ViewGroup)this, view, n3) == null) {
                        n2 = 1;
                    }
                    else {
                        n2 = 0;
                    }
                }
                if (n2 == 0 && this.mLayout.canScrollHorizontally()) {
                    boolean b2;
                    if (this.mLayout.getLayoutDirection() == 1) {
                        b2 = true;
                    }
                    else {
                        b2 = false;
                    }
                    boolean b3;
                    if (n == 2) {
                        b3 = true;
                    }
                    else {
                        b3 = false;
                    }
                    int n4;
                    if (b3 ^ b2) {
                        n4 = 66;
                    }
                    else {
                        n4 = 17;
                    }
                    if (instance.findNextFocus((ViewGroup)this, view, n4) == null) {
                        n2 = 1;
                    }
                    else {
                        n2 = 0;
                    }
                }
                if (n2 != 0) {
                    this.consumePendingUpdateOperations();
                    if (this.findContainingItemView(view) == null) {
                        focusSearch = null;
                        return focusSearch;
                    }
                    this.eatRequestLayout();
                    this.mLayout.onFocusSearchFailed(view, n, this.mRecycler, this.mState);
                    this.resumeRequestLayout(false);
                }
                view2 = instance.findNextFocus((ViewGroup)this, view, n);
            }
            else {
                view2 = instance.findNextFocus((ViewGroup)this, view, n);
                if (view2 == null && b) {
                    this.consumePendingUpdateOperations();
                    if (this.findContainingItemView(view) == null) {
                        focusSearch = null;
                        return focusSearch;
                    }
                    this.eatRequestLayout();
                    view2 = this.mLayout.onFocusSearchFailed(view, n, this.mRecycler, this.mState);
                    this.resumeRequestLayout(false);
                }
            }
            if (this.isPreferredNextFocus(view, view2, n)) {
                focusSearch = view2;
            }
            else {
                focusSearch = super.focusSearch(view, n);
            }
        }
        return focusSearch;
    }
    
    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        if (this.mLayout == null) {
            throw new IllegalStateException("RecyclerView has no LayoutManager");
        }
        return (ViewGroup$LayoutParams)this.mLayout.generateDefaultLayoutParams();
    }
    
    public ViewGroup$LayoutParams generateLayoutParams(final AttributeSet set) {
        if (this.mLayout == null) {
            throw new IllegalStateException("RecyclerView has no LayoutManager");
        }
        return (ViewGroup$LayoutParams)this.mLayout.generateLayoutParams(this.getContext(), set);
    }
    
    protected ViewGroup$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (this.mLayout == null) {
            throw new IllegalStateException("RecyclerView has no LayoutManager");
        }
        return (ViewGroup$LayoutParams)this.mLayout.generateLayoutParams(viewGroup$LayoutParams);
    }
    
    public Adapter getAdapter() {
        return this.mAdapter;
    }
    
    int getAdapterPositionFor(final ViewHolder viewHolder) {
        int applyPendingUpdatesToPosition;
        if (viewHolder.hasAnyOfTheFlags(524) || !viewHolder.isBound()) {
            applyPendingUpdatesToPosition = -1;
        }
        else {
            applyPendingUpdatesToPosition = this.mAdapterHelper.applyPendingUpdatesToPosition(viewHolder.mPosition);
        }
        return applyPendingUpdatesToPosition;
    }
    
    public int getBaseline() {
        int n;
        if (this.mLayout != null) {
            n = this.mLayout.getBaseline();
        }
        else {
            n = super.getBaseline();
        }
        return n;
    }
    
    long getChangedHolderKey(final ViewHolder viewHolder) {
        long itemId;
        if (this.mAdapter.hasStableIds()) {
            itemId = viewHolder.getItemId();
        }
        else {
            itemId = viewHolder.mPosition;
        }
        return itemId;
    }
    
    public int getChildAdapterPosition(final View view) {
        final ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        int adapterPosition;
        if (childViewHolderInt != null) {
            adapterPosition = childViewHolderInt.getAdapterPosition();
        }
        else {
            adapterPosition = -1;
        }
        return adapterPosition;
    }
    
    protected int getChildDrawingOrder(final int n, final int n2) {
        int n3;
        if (this.mChildDrawingOrderCallback == null) {
            n3 = super.getChildDrawingOrder(n, n2);
        }
        else {
            n3 = this.mChildDrawingOrderCallback.onGetChildDrawingOrder(n, n2);
        }
        return n3;
    }
    
    public long getChildItemId(final View view) {
        long itemId = -1L;
        if (this.mAdapter != null && this.mAdapter.hasStableIds()) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(view);
            if (childViewHolderInt != null) {
                itemId = childViewHolderInt.getItemId();
            }
        }
        return itemId;
    }
    
    public int getChildLayoutPosition(final View view) {
        final ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        int layoutPosition;
        if (childViewHolderInt != null) {
            layoutPosition = childViewHolderInt.getLayoutPosition();
        }
        else {
            layoutPosition = -1;
        }
        return layoutPosition;
    }
    
    @Deprecated
    public int getChildPosition(final View view) {
        return this.getChildAdapterPosition(view);
    }
    
    public ViewHolder getChildViewHolder(final View view) {
        final ViewParent parent = view.getParent();
        if (parent != null && parent != this) {
            throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
        }
        return getChildViewHolderInt(view);
    }
    
    public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate() {
        return this.mAccessibilityDelegate;
    }
    
    public ItemAnimator getItemAnimator() {
        return this.mItemAnimator;
    }
    
    Rect getItemDecorInsetsForChild(final View view) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        Rect rect;
        if (!layoutParams.mInsetsDirty) {
            rect = layoutParams.mDecorInsets;
        }
        else if (this.mState.isPreLayout() && (layoutParams.isItemChanged() || layoutParams.isViewInvalid())) {
            rect = layoutParams.mDecorInsets;
        }
        else {
            rect = layoutParams.mDecorInsets;
            rect.set(0, 0, 0, 0);
            for (int size = this.mItemDecorations.size(), i = 0; i < size; ++i) {
                this.mTempRect.set(0, 0, 0, 0);
                this.mItemDecorations.get(i).getItemOffsets(this.mTempRect, view, this, this.mState);
                rect.left += this.mTempRect.left;
                rect.top += this.mTempRect.top;
                rect.right += this.mTempRect.right;
                rect.bottom += this.mTempRect.bottom;
            }
            layoutParams.mInsetsDirty = false;
        }
        return rect;
    }
    
    public LayoutManager getLayoutManager() {
        return this.mLayout;
    }
    
    public int getMaxFlingVelocity() {
        return this.mMaxFlingVelocity;
    }
    
    public int getMinFlingVelocity() {
        return this.mMinFlingVelocity;
    }
    
    @Nullable
    public OnFlingListener getOnFlingListener() {
        return this.mOnFlingListener;
    }
    
    public boolean getPreserveFocusAfterLayout() {
        return this.mPreserveFocusAfterLayout;
    }
    
    public RecycledViewPool getRecycledViewPool() {
        return this.mRecycler.getRecycledViewPool();
    }
    
    public int getScrollState() {
        return this.mScrollState;
    }
    
    public boolean hasFixedSize() {
        return this.mHasFixedSize;
    }
    
    public boolean hasNestedScrollingParent() {
        return this.getScrollingChildHelper().hasNestedScrollingParent();
    }
    
    public boolean hasPendingAdapterUpdates() {
        return !this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout || this.mAdapterHelper.hasPendingUpdates();
    }
    
    void initAdapterManager() {
        this.mAdapterHelper = new AdapterHelper((AdapterHelper.Callback)new AdapterHelper.Callback() {
            void dispatchUpdate(final UpdateOp updateOp) {
                switch (updateOp.cmd) {
                    case 1: {
                        RecyclerView.this.mLayout.onItemsAdded(RecyclerView.this, updateOp.positionStart, updateOp.itemCount);
                        break;
                    }
                    case 2: {
                        RecyclerView.this.mLayout.onItemsRemoved(RecyclerView.this, updateOp.positionStart, updateOp.itemCount);
                        break;
                    }
                    case 4: {
                        RecyclerView.this.mLayout.onItemsUpdated(RecyclerView.this, updateOp.positionStart, updateOp.itemCount, updateOp.payload);
                        break;
                    }
                    case 8: {
                        RecyclerView.this.mLayout.onItemsMoved(RecyclerView.this, updateOp.positionStart, updateOp.itemCount, 1);
                        break;
                    }
                }
            }
            
            @Override
            public ViewHolder findViewHolder(final int n) {
                Object viewHolderForPosition = RecyclerView.this.findViewHolderForPosition(n, true);
                if (viewHolderForPosition == null) {
                    viewHolderForPosition = null;
                }
                else if (RecyclerView.this.mChildHelper.isHidden(((ViewHolder)viewHolderForPosition).itemView)) {
                    viewHolderForPosition = null;
                }
                return (ViewHolder)viewHolderForPosition;
            }
            
            @Override
            public void markViewHoldersUpdated(final int n, final int n2, final Object o) {
                RecyclerView.this.viewRangeUpdate(n, n2, o);
                RecyclerView.this.mItemsChanged = true;
            }
            
            @Override
            public void offsetPositionsForAdd(final int n, final int n2) {
                RecyclerView.this.offsetPositionRecordsForInsert(n, n2);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            }
            
            @Override
            public void offsetPositionsForMove(final int n, final int n2) {
                RecyclerView.this.offsetPositionRecordsForMove(n, n2);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            }
            
            @Override
            public void offsetPositionsForRemovingInvisible(final int n, final int n2) {
                RecyclerView.this.offsetPositionRecordsForRemove(n, n2, true);
                RecyclerView.this.mItemsAddedOrRemoved = true;
                final State mState = RecyclerView.this.mState;
                mState.mDeletedInvisibleItemCountSincePreviousLayout += n2;
            }
            
            @Override
            public void offsetPositionsForRemovingLaidOutOrNewView(final int n, final int n2) {
                RecyclerView.this.offsetPositionRecordsForRemove(n, n2, false);
                RecyclerView.this.mItemsAddedOrRemoved = true;
            }
            
            @Override
            public void onDispatchFirstPass(final UpdateOp updateOp) {
                this.dispatchUpdate(updateOp);
            }
            
            @Override
            public void onDispatchSecondPass(final UpdateOp updateOp) {
                this.dispatchUpdate(updateOp);
            }
        });
    }
    
    void invalidateGlows() {
        this.mBottomGlow = null;
        this.mTopGlow = null;
        this.mRightGlow = null;
        this.mLeftGlow = null;
    }
    
    public void invalidateItemDecorations() {
        if (this.mItemDecorations.size() != 0) {
            if (this.mLayout != null) {
                this.mLayout.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout");
            }
            this.markItemDecorInsetsDirty();
            this.requestLayout();
        }
    }
    
    boolean isAccessibilityEnabled() {
        return this.mAccessibilityManager != null && this.mAccessibilityManager.isEnabled();
    }
    
    public boolean isAnimating() {
        return this.mItemAnimator != null && this.mItemAnimator.isRunning();
    }
    
    public boolean isAttachedToWindow() {
        return this.mIsAttached;
    }
    
    public boolean isComputingLayout() {
        return this.mLayoutOrScrollCounter > 0;
    }
    
    public boolean isLayoutFrozen() {
        return this.mLayoutFrozen;
    }
    
    public boolean isNestedScrollingEnabled() {
        return this.getScrollingChildHelper().isNestedScrollingEnabled();
    }
    
    void jumpToPositionForSmoothScroller(final int n) {
        if (this.mLayout != null) {
            this.mLayout.scrollToPosition(n);
            this.awakenScrollBars();
        }
    }
    
    void markItemDecorInsetsDirty() {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            ((LayoutParams)this.mChildHelper.getUnfilteredChildAt(i).getLayoutParams()).mInsetsDirty = true;
        }
        this.mRecycler.markItemDecorInsetsDirty();
    }
    
    void markKnownViewsInvalid() {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.addFlags(6);
            }
        }
        this.markItemDecorInsetsDirty();
        this.mRecycler.markKnownViewsInvalid();
    }
    
    public void offsetChildrenHorizontal(final int n) {
        for (int childCount = this.mChildHelper.getChildCount(), i = 0; i < childCount; ++i) {
            this.mChildHelper.getChildAt(i).offsetLeftAndRight(n);
        }
    }
    
    public void offsetChildrenVertical(final int n) {
        for (int childCount = this.mChildHelper.getChildCount(), i = 0; i < childCount; ++i) {
            this.mChildHelper.getChildAt(i).offsetTopAndBottom(n);
        }
    }
    
    void offsetPositionRecordsForInsert(final int n, final int n2) {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.mPosition >= n) {
                childViewHolderInt.offsetPosition(n2, false);
                this.mState.mStructureChanged = true;
            }
        }
        this.mRecycler.offsetPositionRecordsForInsert(n, n2);
        this.requestLayout();
    }
    
    void offsetPositionRecordsForMove(final int n, final int n2) {
        final int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        int n3;
        int n4;
        int n5;
        if (n < n2) {
            n3 = n;
            n4 = n2;
            n5 = -1;
        }
        else {
            n3 = n2;
            n4 = n;
            n5 = 1;
        }
        for (int i = 0; i < unfilteredChildCount; ++i) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && childViewHolderInt.mPosition >= n3 && childViewHolderInt.mPosition <= n4) {
                if (childViewHolderInt.mPosition == n) {
                    childViewHolderInt.offsetPosition(n2 - n, false);
                }
                else {
                    childViewHolderInt.offsetPosition(n5, false);
                }
                this.mState.mStructureChanged = true;
            }
        }
        this.mRecycler.offsetPositionRecordsForMove(n, n2);
        this.requestLayout();
    }
    
    void offsetPositionRecordsForRemove(final int n, final int n2, final boolean b) {
        final int n3 = n + n2;
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                if (childViewHolderInt.mPosition >= n3) {
                    childViewHolderInt.offsetPosition(-n2, b);
                    this.mState.mStructureChanged = true;
                }
                else if (childViewHolderInt.mPosition >= n) {
                    childViewHolderInt.flagRemovedAndOffsetPosition(n - 1, -n2, b);
                    this.mState.mStructureChanged = true;
                }
            }
        }
        this.mRecycler.offsetPositionRecordsForRemove(n, n2, b);
        this.requestLayout();
    }
    
    protected void onAttachedToWindow() {
        boolean b = true;
        super.onAttachedToWindow();
        this.mLayoutOrScrollCounter = 0;
        this.mIsAttached = b;
        if (!this.mFirstLayoutComplete || this.isLayoutRequested()) {
            b = false;
        }
        this.mFirstLayoutComplete = b;
        if (this.mLayout != null) {
            this.mLayout.dispatchAttachedToWindow(this);
        }
        this.mPostedAnimatorRunner = false;
    }
    
    public void onChildAttachedToWindow(final View view) {
    }
    
    public void onChildDetachedFromWindow(final View view) {
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mItemAnimator != null) {
            this.mItemAnimator.endAnimations();
        }
        this.stopScroll();
        this.mIsAttached = false;
        if (this.mLayout != null) {
            this.mLayout.dispatchDetachedFromWindow(this, this.mRecycler);
        }
        this.removeCallbacks(this.mItemAnimatorRunner);
        this.mViewInfoStore.onDetach();
    }
    
    public void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        for (int size = this.mItemDecorations.size(), i = 0; i < size; ++i) {
            this.mItemDecorations.get(i).onDraw(canvas, this, this.mState);
        }
    }
    
    void onEnterLayoutOrScroll() {
        ++this.mLayoutOrScrollCounter;
    }
    
    void onExitLayoutOrScroll() {
        --this.mLayoutOrScrollCounter;
        if (this.mLayoutOrScrollCounter < 1) {
            this.mLayoutOrScrollCounter = 0;
            this.dispatchContentChangedIfNecessary();
        }
    }
    
    public boolean onGenericMotionEvent(final MotionEvent motionEvent) {
        if (this.mLayout != null && !this.mLayoutFrozen && (0x2 & motionEvent.getSource()) != 0x0 && motionEvent.getAction() == 8) {
            float n;
            if (this.mLayout.canScrollVertically()) {
                n = -MotionEventCompat.getAxisValue(motionEvent, 9);
            }
            else {
                n = 0.0f;
            }
            float axisValue;
            if (this.mLayout.canScrollHorizontally()) {
                axisValue = MotionEventCompat.getAxisValue(motionEvent, 10);
            }
            else {
                axisValue = 0.0f;
            }
            if (n != 0.0f || axisValue != 0.0f) {
                final float scrollFactor = this.getScrollFactor();
                this.scrollByInternal((int)(axisValue * scrollFactor), (int)(n * scrollFactor), motionEvent);
            }
        }
        return false;
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        boolean b;
        if (this.mLayoutFrozen) {
            b = false;
        }
        else if (this.dispatchOnItemTouchIntercept(motionEvent)) {
            this.cancelTouch();
            b = true;
        }
        else if (this.mLayout == null) {
            b = false;
        }
        else {
            final boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
            final boolean canScrollVertically = this.mLayout.canScrollVertically();
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            this.mVelocityTracker.addMovement(motionEvent);
            final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
            final int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
            switch (actionMasked) {
                case 0: {
                    if (this.mIgnoreMotionEventTillDown) {
                        this.mIgnoreMotionEventTillDown = false;
                    }
                    this.mScrollPointerId = motionEvent.getPointerId(0);
                    final int n = (int)(0.5f + motionEvent.getX());
                    this.mLastTouchX = n;
                    this.mInitialTouchX = n;
                    final int n2 = (int)(0.5f + motionEvent.getY());
                    this.mLastTouchY = n2;
                    this.mInitialTouchY = n2;
                    if (this.mScrollState == 2) {
                        this.getParent().requestDisallowInterceptTouchEvent(true);
                        this.setScrollState(1);
                    }
                    this.mNestedOffsets[this.mNestedOffsets[1] = 0] = 0;
                    int n3 = 0;
                    if (canScrollHorizontally) {
                        n3 = ((false | true) ? 1 : 0);
                    }
                    if (canScrollVertically) {
                        n3 |= 0x2;
                    }
                    this.startNestedScroll(n3);
                    break;
                }
                case 5: {
                    this.mScrollPointerId = motionEvent.getPointerId(actionIndex);
                    final int n4 = (int)(0.5f + motionEvent.getX(actionIndex));
                    this.mLastTouchX = n4;
                    this.mInitialTouchX = n4;
                    final int n5 = (int)(0.5f + motionEvent.getY(actionIndex));
                    this.mLastTouchY = n5;
                    this.mInitialTouchY = n5;
                    break;
                }
                case 2: {
                    final int pointerIndex = motionEvent.findPointerIndex(this.mScrollPointerId);
                    if (pointerIndex < 0) {
                        Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                        b = false;
                        return b;
                    }
                    final int n6 = (int)(0.5f + motionEvent.getX(pointerIndex));
                    final int n7 = (int)(0.5f + motionEvent.getY(pointerIndex));
                    if (this.mScrollState == 1) {
                        break;
                    }
                    final int n8 = n6 - this.mInitialTouchX;
                    final int n9 = n7 - this.mInitialTouchY;
                    boolean b2 = false;
                    if (canScrollHorizontally) {
                        final int abs = Math.abs(n8);
                        final int mTouchSlop = this.mTouchSlop;
                        b2 = false;
                        if (abs > mTouchSlop) {
                            final int mInitialTouchX = this.mInitialTouchX;
                            final int mTouchSlop2 = this.mTouchSlop;
                            int n10;
                            if (n8 < 0) {
                                n10 = -1;
                            }
                            else {
                                n10 = 1;
                            }
                            this.mLastTouchX = mInitialTouchX + n10 * mTouchSlop2;
                            b2 = true;
                        }
                    }
                    if (canScrollVertically && Math.abs(n9) > this.mTouchSlop) {
                        final int mInitialTouchY = this.mInitialTouchY;
                        final int mTouchSlop3 = this.mTouchSlop;
                        int n11;
                        if (n9 < 0) {
                            n11 = -1;
                        }
                        else {
                            n11 = 1;
                        }
                        this.mLastTouchY = mInitialTouchY + n11 * mTouchSlop3;
                        b2 = true;
                    }
                    if (b2) {
                        this.setScrollState(1);
                        break;
                    }
                    break;
                }
                case 6: {
                    this.onPointerUp(motionEvent);
                    break;
                }
                case 1: {
                    this.mVelocityTracker.clear();
                    this.stopNestedScroll();
                    break;
                }
                case 3: {
                    this.cancelTouch();
                    break;
                }
            }
            b = (this.mScrollState == 1);
        }
        return b;
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        TraceCompat.beginSection("RV OnLayout");
        this.dispatchLayout();
        TraceCompat.endSection();
        this.mFirstLayoutComplete = true;
    }
    
    protected void onMeasure(final int n, final int n2) {
        if (this.mLayout == null) {
            this.defaultOnMeasure(n, n2);
        }
        else if (this.mLayout.mAutoMeasure) {
            final int mode = View$MeasureSpec.getMode(n);
            final int mode2 = View$MeasureSpec.getMode(n2);
            boolean b = false;
            if (mode == 1073741824) {
                b = false;
                if (mode2 == 1073741824) {
                    b = true;
                }
            }
            this.mLayout.onMeasure(this.mRecycler, this.mState, n, n2);
            if (!b && this.mAdapter != null) {
                if (this.mState.mLayoutStep == 1) {
                    this.dispatchLayoutStep1();
                }
                this.mLayout.setMeasureSpecs(n, n2);
                this.mState.mIsMeasuring = true;
                this.dispatchLayoutStep2();
                this.mLayout.setMeasuredDimensionFromChildren(n, n2);
                if (this.mLayout.shouldMeasureTwice()) {
                    this.mLayout.setMeasureSpecs(View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 1073741824), View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 1073741824));
                    this.mState.mIsMeasuring = true;
                    this.dispatchLayoutStep2();
                    this.mLayout.setMeasuredDimensionFromChildren(n, n2);
                }
            }
        }
        else if (this.mHasFixedSize) {
            this.mLayout.onMeasure(this.mRecycler, this.mState, n, n2);
        }
        else {
            if (this.mAdapterUpdateDuringMeasure) {
                this.eatRequestLayout();
                this.processAdapterUpdatesAndSetAnimationFlags();
                if (this.mState.mRunPredictiveAnimations) {
                    this.mState.mInPreLayout = true;
                }
                else {
                    this.mAdapterHelper.consumeUpdatesInOnePass();
                    this.mState.mInPreLayout = false;
                }
                this.resumeRequestLayout(this.mAdapterUpdateDuringMeasure = false);
            }
            if (this.mAdapter != null) {
                this.mState.mItemCount = this.mAdapter.getItemCount();
            }
            else {
                this.mState.mItemCount = 0;
            }
            this.eatRequestLayout();
            this.mLayout.onMeasure(this.mRecycler, this.mState, n, n2);
            this.resumeRequestLayout(false);
            this.mState.mInPreLayout = false;
        }
    }
    
    protected boolean onRequestFocusInDescendants(final int n, final Rect rect) {
        return !this.isComputingLayout() && super.onRequestFocusInDescendants(n, rect);
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
        }
        else {
            this.mPendingSavedState = (SavedState)parcelable;
            super.onRestoreInstanceState(this.mPendingSavedState.getSuperState());
            if (this.mLayout != null && this.mPendingSavedState.mLayoutState != null) {
                this.mLayout.onRestoreInstanceState(this.mPendingSavedState.mLayoutState);
            }
        }
    }
    
    protected Parcelable onSaveInstanceState() {
        final SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.mPendingSavedState != null) {
            savedState.copyFrom(this.mPendingSavedState);
        }
        else if (this.mLayout != null) {
            savedState.mLayoutState = this.mLayout.onSaveInstanceState();
        }
        else {
            savedState.mLayoutState = null;
        }
        return (Parcelable)savedState;
    }
    
    public void onScrollStateChanged(final int n) {
    }
    
    public void onScrolled(final int n, final int n2) {
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (n != n3 || n2 != n4) {
            this.invalidateGlows();
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        boolean b;
        if (this.mLayoutFrozen || this.mIgnoreMotionEventTillDown) {
            b = false;
        }
        else if (this.dispatchOnItemTouch(motionEvent)) {
            this.cancelTouch();
            b = true;
        }
        else if (this.mLayout == null) {
            b = false;
        }
        else {
            final boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
            final boolean canScrollVertically = this.mLayout.canScrollVertically();
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            final MotionEvent obtain = MotionEvent.obtain(motionEvent);
            final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
            final int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
            if (actionMasked == 0) {
                this.mNestedOffsets[this.mNestedOffsets[1] = 0] = 0;
            }
            obtain.offsetLocation((float)this.mNestedOffsets[0], (float)this.mNestedOffsets[1]);
            int n = 0;
            switch (actionMasked) {
                case 0: {
                    this.mScrollPointerId = motionEvent.getPointerId(0);
                    final int n2 = (int)(0.5f + motionEvent.getX());
                    this.mLastTouchX = n2;
                    this.mInitialTouchX = n2;
                    final int n3 = (int)(0.5f + motionEvent.getY());
                    this.mLastTouchY = n3;
                    this.mInitialTouchY = n3;
                    int n4 = 0;
                    if (canScrollHorizontally) {
                        n4 = ((false | true) ? 1 : 0);
                    }
                    if (canScrollVertically) {
                        n4 |= 0x2;
                    }
                    this.startNestedScroll(n4);
                    n = 0;
                    break;
                }
                case 5: {
                    this.mScrollPointerId = motionEvent.getPointerId(actionIndex);
                    final int n5 = (int)(0.5f + motionEvent.getX(actionIndex));
                    this.mLastTouchX = n5;
                    this.mInitialTouchX = n5;
                    final int n6 = (int)(0.5f + motionEvent.getY(actionIndex));
                    this.mLastTouchY = n6;
                    this.mInitialTouchY = n6;
                    n = 0;
                    break;
                }
                case 2: {
                    final int pointerIndex = motionEvent.findPointerIndex(this.mScrollPointerId);
                    if (pointerIndex < 0) {
                        Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                        b = false;
                        return b;
                    }
                    final int n7 = (int)(0.5f + motionEvent.getX(pointerIndex));
                    final int n8 = (int)(0.5f + motionEvent.getY(pointerIndex));
                    int n9 = this.mLastTouchX - n7;
                    int n10 = this.mLastTouchY - n8;
                    if (this.dispatchNestedPreScroll(n9, n10, this.mScrollConsumed, this.mScrollOffset)) {
                        n9 -= this.mScrollConsumed[0];
                        n10 -= this.mScrollConsumed[1];
                        obtain.offsetLocation((float)this.mScrollOffset[0], (float)this.mScrollOffset[1]);
                        final int[] mNestedOffsets = this.mNestedOffsets;
                        mNestedOffsets[0] += this.mScrollOffset[0];
                        final int[] mNestedOffsets2 = this.mNestedOffsets;
                        mNestedOffsets2[1] += this.mScrollOffset[1];
                    }
                    if (this.mScrollState != 1) {
                        boolean b2 = false;
                        if (canScrollHorizontally) {
                            final int abs = Math.abs(n9);
                            final int mTouchSlop = this.mTouchSlop;
                            b2 = false;
                            if (abs > mTouchSlop) {
                                if (n9 > 0) {
                                    n9 -= this.mTouchSlop;
                                }
                                else {
                                    n9 += this.mTouchSlop;
                                }
                                b2 = true;
                            }
                        }
                        if (canScrollVertically && Math.abs(n10) > this.mTouchSlop) {
                            if (n10 > 0) {
                                n10 -= this.mTouchSlop;
                            }
                            else {
                                n10 += this.mTouchSlop;
                            }
                            b2 = true;
                        }
                        if (b2) {
                            this.setScrollState(1);
                        }
                    }
                    final int mScrollState = this.mScrollState;
                    n = 0;
                    if (mScrollState != 1) {
                        break;
                    }
                    this.mLastTouchX = n7 - this.mScrollOffset[0];
                    this.mLastTouchY = n8 - this.mScrollOffset[1];
                    if (!canScrollHorizontally) {
                        n9 = 0;
                    }
                    if (!canScrollVertically) {
                        n10 = 0;
                    }
                    final boolean scrollByInternal = this.scrollByInternal(n9, n10, obtain);
                    n = 0;
                    if (scrollByInternal) {
                        this.getParent().requestDisallowInterceptTouchEvent(true);
                        n = 0;
                        break;
                    }
                    break;
                }
                case 6: {
                    this.onPointerUp(motionEvent);
                    n = 0;
                    break;
                }
                case 1: {
                    this.mVelocityTracker.addMovement(obtain);
                    n = 1;
                    this.mVelocityTracker.computeCurrentVelocity(1000, (float)this.mMaxFlingVelocity);
                    float n11;
                    if (canScrollHorizontally) {
                        n11 = -VelocityTrackerCompat.getXVelocity(this.mVelocityTracker, this.mScrollPointerId);
                    }
                    else {
                        n11 = 0.0f;
                    }
                    float n12;
                    if (canScrollVertically) {
                        n12 = -VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, this.mScrollPointerId);
                    }
                    else {
                        n12 = 0.0f;
                    }
                    if ((n11 == 0.0f && n12 == 0.0f) || !this.fling((int)n11, (int)n12)) {
                        this.setScrollState(0);
                    }
                    this.resetTouch();
                    break;
                }
                case 3: {
                    this.cancelTouch();
                    n = 0;
                    break;
                }
            }
            if (n == 0) {
                this.mVelocityTracker.addMovement(obtain);
            }
            obtain.recycle();
            b = true;
        }
        return b;
    }
    
    void postAnimationRunner() {
        if (!this.mPostedAnimatorRunner && this.mIsAttached) {
            ViewCompat.postOnAnimation((View)this, this.mItemAnimatorRunner);
            this.mPostedAnimatorRunner = true;
        }
    }
    
    void recordAnimationInfoIfBouncedHiddenView(final ViewHolder viewHolder, final ItemHolderInfo itemHolderInfo) {
        viewHolder.setFlags(0, 8192);
        if (this.mState.mTrackOldChangeHolders && viewHolder.isUpdated() && !viewHolder.isRemoved() && !viewHolder.shouldIgnore()) {
            this.mViewInfoStore.addToOldChangeHolders(this.getChangedHolderKey(viewHolder), viewHolder);
        }
        this.mViewInfoStore.addToPreLayout(viewHolder, itemHolderInfo);
    }
    
    boolean removeAnimatingView(final View view) {
        this.eatRequestLayout();
        final boolean removeViewIfHidden = this.mChildHelper.removeViewIfHidden(view);
        if (removeViewIfHidden) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(view);
            this.mRecycler.unscrapView(childViewHolderInt);
            this.mRecycler.recycleViewHolderInternal(childViewHolderInt);
        }
        this.resumeRequestLayout(!removeViewIfHidden);
        return removeViewIfHidden;
    }
    
    protected void removeDetachedView(final View view, final boolean b) {
        final ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            if (childViewHolderInt.isTmpDetached()) {
                childViewHolderInt.clearTmpDetachFlag();
            }
            else if (!childViewHolderInt.shouldIgnore()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + childViewHolderInt);
            }
        }
        this.dispatchChildDetached(view);
        super.removeDetachedView(view, b);
    }
    
    public void removeItemDecoration(final ItemDecoration itemDecoration) {
        if (this.mLayout != null) {
            this.mLayout.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
        }
        this.mItemDecorations.remove(itemDecoration);
        if (this.mItemDecorations.isEmpty()) {
            this.setWillNotDraw(this.getOverScrollMode() == 2);
        }
        this.markItemDecorInsetsDirty();
        this.requestLayout();
    }
    
    public void removeOnChildAttachStateChangeListener(final OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        if (this.mOnChildAttachStateListeners != null) {
            this.mOnChildAttachStateListeners.remove(onChildAttachStateChangeListener);
        }
    }
    
    public void removeOnItemTouchListener(final OnItemTouchListener onItemTouchListener) {
        this.mOnItemTouchListeners.remove(onItemTouchListener);
        if (this.mActiveOnItemTouchListener == onItemTouchListener) {
            this.mActiveOnItemTouchListener = null;
        }
    }
    
    public void removeOnScrollListener(final OnScrollListener onScrollListener) {
        if (this.mScrollListeners != null) {
            this.mScrollListeners.remove(onScrollListener);
        }
    }
    
    void repositionShadowingViews() {
        for (int childCount = this.mChildHelper.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.mChildHelper.getChildAt(i);
            final ViewHolder childViewHolder = this.getChildViewHolder(child);
            if (childViewHolder != null && childViewHolder.mShadowingHolder != null) {
                final View itemView = childViewHolder.mShadowingHolder.itemView;
                final int left = child.getLeft();
                final int top = child.getTop();
                if (left != itemView.getLeft() || top != itemView.getTop()) {
                    itemView.layout(left, top, left + itemView.getWidth(), top + itemView.getHeight());
                }
            }
        }
    }
    
    public void requestChildFocus(final View view, final View view2) {
        if (!this.mLayout.onRequestChildFocus(this, this.mState, view, view2) && view2 != null) {
            this.mTempRect.set(0, 0, view2.getWidth(), view2.getHeight());
            final ViewGroup$LayoutParams layoutParams = view2.getLayoutParams();
            if (layoutParams instanceof LayoutParams) {
                final LayoutParams layoutParams2 = (LayoutParams)layoutParams;
                if (!layoutParams2.mInsetsDirty) {
                    final Rect mDecorInsets = layoutParams2.mDecorInsets;
                    final Rect mTempRect = this.mTempRect;
                    mTempRect.left -= mDecorInsets.left;
                    final Rect mTempRect2 = this.mTempRect;
                    mTempRect2.right += mDecorInsets.right;
                    final Rect mTempRect3 = this.mTempRect;
                    mTempRect3.top -= mDecorInsets.top;
                    final Rect mTempRect4 = this.mTempRect;
                    mTempRect4.bottom += mDecorInsets.bottom;
                }
            }
            this.offsetDescendantRectToMyCoords(view2, this.mTempRect);
            this.offsetRectIntoDescendantCoords(view, this.mTempRect);
            final Rect mTempRect5 = this.mTempRect;
            final boolean mFirstLayoutComplete = this.mFirstLayoutComplete;
            boolean b = false;
            if (!mFirstLayoutComplete) {
                b = true;
            }
            this.requestChildRectangleOnScreen(view, mTempRect5, b);
        }
        super.requestChildFocus(view, view2);
    }
    
    public boolean requestChildRectangleOnScreen(final View view, final Rect rect, final boolean b) {
        return this.mLayout.requestChildRectangleOnScreen(this, view, rect, b);
    }
    
    public void requestDisallowInterceptTouchEvent(final boolean b) {
        for (int size = this.mOnItemTouchListeners.size(), i = 0; i < size; ++i) {
            this.mOnItemTouchListeners.get(i).onRequestDisallowInterceptTouchEvent(b);
        }
        super.requestDisallowInterceptTouchEvent(b);
    }
    
    public void requestLayout() {
        if (this.mEatRequestLayout == 0 && !this.mLayoutFrozen) {
            super.requestLayout();
        }
        else {
            this.mLayoutRequestEaten = true;
        }
    }
    
    void resumeRequestLayout(final boolean b) {
        if (this.mEatRequestLayout < 1) {
            this.mEatRequestLayout = 1;
        }
        if (!b) {
            this.mLayoutRequestEaten = false;
        }
        if (this.mEatRequestLayout == 1) {
            if (b && this.mLayoutRequestEaten && !this.mLayoutFrozen && this.mLayout != null && this.mAdapter != null) {
                this.dispatchLayout();
            }
            if (!this.mLayoutFrozen) {
                this.mLayoutRequestEaten = false;
            }
        }
        --this.mEatRequestLayout;
    }
    
    void saveOldPositions() {
        for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
            final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.saveOldPosition();
            }
        }
    }
    
    public void scrollBy(int n, int n2) {
        if (this.mLayout == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        }
        else if (!this.mLayoutFrozen) {
            final boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
            final boolean canScrollVertically = this.mLayout.canScrollVertically();
            if (canScrollHorizontally || canScrollVertically) {
                if (!canScrollHorizontally) {
                    n = 0;
                }
                if (!canScrollVertically) {
                    n2 = 0;
                }
                this.scrollByInternal(n, n2, null);
            }
        }
    }
    
    boolean scrollByInternal(final int n, final int n2, final MotionEvent motionEvent) {
        this.consumePendingUpdateOperations();
        final Adapter mAdapter = this.mAdapter;
        int scrollHorizontallyBy = 0;
        int scrollVerticallyBy = 0;
        int n3 = 0;
        int n4 = 0;
        if (mAdapter != null) {
            this.eatRequestLayout();
            this.onEnterLayoutOrScroll();
            TraceCompat.beginSection("RV Scroll");
            scrollHorizontallyBy = 0;
            n3 = 0;
            if (n != 0) {
                scrollHorizontallyBy = this.mLayout.scrollHorizontallyBy(n, this.mRecycler, this.mState);
                n3 = n - scrollHorizontallyBy;
            }
            scrollVerticallyBy = 0;
            n4 = 0;
            if (n2 != 0) {
                scrollVerticallyBy = this.mLayout.scrollVerticallyBy(n2, this.mRecycler, this.mState);
                n4 = n2 - scrollVerticallyBy;
            }
            TraceCompat.endSection();
            this.repositionShadowingViews();
            this.onExitLayoutOrScroll();
            this.resumeRequestLayout(false);
        }
        if (!this.mItemDecorations.isEmpty()) {
            this.invalidate();
        }
        if (this.dispatchNestedScroll(scrollHorizontallyBy, scrollVerticallyBy, n3, n4, this.mScrollOffset)) {
            this.mLastTouchX -= this.mScrollOffset[0];
            this.mLastTouchY -= this.mScrollOffset[1];
            if (motionEvent != null) {
                motionEvent.offsetLocation((float)this.mScrollOffset[0], (float)this.mScrollOffset[1]);
            }
            final int[] mNestedOffsets = this.mNestedOffsets;
            mNestedOffsets[0] += this.mScrollOffset[0];
            final int[] mNestedOffsets2 = this.mNestedOffsets;
            mNestedOffsets2[1] += this.mScrollOffset[1];
        }
        else if (this.getOverScrollMode() != 2) {
            if (motionEvent != null) {
                this.pullGlows(motionEvent.getX(), n3, motionEvent.getY(), n4);
            }
            this.considerReleasingGlowsOnScroll(n, n2);
        }
        if (scrollHorizontallyBy != 0 || scrollVerticallyBy != 0) {
            this.dispatchOnScrolled(scrollHorizontallyBy, scrollVerticallyBy);
        }
        if (!this.awakenScrollBars()) {
            this.invalidate();
        }
        return scrollHorizontallyBy != 0 || scrollVerticallyBy != 0;
    }
    
    public void scrollTo(final int n, final int n2) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }
    
    public void scrollToPosition(final int n) {
        if (!this.mLayoutFrozen) {
            this.stopScroll();
            if (this.mLayout == null) {
                Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
            }
            else {
                this.mLayout.scrollToPosition(n);
                this.awakenScrollBars();
            }
        }
    }
    
    public void sendAccessibilityEventUnchecked(final AccessibilityEvent accessibilityEvent) {
        if (!this.shouldDeferAccessibilityEvent(accessibilityEvent)) {
            super.sendAccessibilityEventUnchecked(accessibilityEvent);
        }
    }
    
    public void setAccessibilityDelegateCompat(final RecyclerViewAccessibilityDelegate mAccessibilityDelegate) {
        ViewCompat.setAccessibilityDelegate((View)this, this.mAccessibilityDelegate = mAccessibilityDelegate);
    }
    
    public void setAdapter(final Adapter adapter) {
        this.setLayoutFrozen(false);
        this.setAdapterInternal(adapter, false, true);
        this.requestLayout();
    }
    
    public void setChildDrawingOrderCallback(final ChildDrawingOrderCallback mChildDrawingOrderCallback) {
        if (mChildDrawingOrderCallback != this.mChildDrawingOrderCallback) {
            this.mChildDrawingOrderCallback = mChildDrawingOrderCallback;
            this.setChildrenDrawingOrderEnabled(this.mChildDrawingOrderCallback != null);
        }
    }
    
    public void setClipToPadding(final boolean mClipToPadding) {
        if (mClipToPadding != this.mClipToPadding) {
            this.invalidateGlows();
        }
        super.setClipToPadding(this.mClipToPadding = mClipToPadding);
        if (this.mFirstLayoutComplete) {
            this.requestLayout();
        }
    }
    
    void setDataSetChangedAfterLayout() {
        if (!this.mDataSetHasChangedAfterLayout) {
            this.mDataSetHasChangedAfterLayout = true;
            for (int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount(), i = 0; i < unfilteredChildCount; ++i) {
                final ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
                if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                    childViewHolderInt.addFlags(512);
                }
            }
            this.mRecycler.setAdapterPositionsAsUnknown();
        }
    }
    
    public void setHasFixedSize(final boolean mHasFixedSize) {
        this.mHasFixedSize = mHasFixedSize;
    }
    
    public void setItemAnimator(final ItemAnimator mItemAnimator) {
        if (this.mItemAnimator != null) {
            this.mItemAnimator.endAnimations();
            this.mItemAnimator.setListener(null);
        }
        this.mItemAnimator = mItemAnimator;
        if (this.mItemAnimator != null) {
            this.mItemAnimator.setListener(this.mItemAnimatorListener);
        }
    }
    
    public void setItemViewCacheSize(final int viewCacheSize) {
        this.mRecycler.setViewCacheSize(viewCacheSize);
    }
    
    public void setLayoutFrozen(final boolean b) {
        if (b != this.mLayoutFrozen) {
            this.assertNotInLayoutOrScroll("Do not setLayoutFrozen in layout or scroll");
            if (!b) {
                this.mLayoutFrozen = false;
                if (this.mLayoutRequestEaten && this.mLayout != null && this.mAdapter != null) {
                    this.requestLayout();
                }
                this.mLayoutRequestEaten = false;
            }
            else {
                final long uptimeMillis = SystemClock.uptimeMillis();
                this.onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0));
                this.mLayoutFrozen = true;
                this.mIgnoreMotionEventTillDown = true;
                this.stopScroll();
            }
        }
    }
    
    public void setLayoutManager(final LayoutManager mLayout) {
        if (mLayout != this.mLayout) {
            this.stopScroll();
            if (this.mLayout != null) {
                if (this.mItemAnimator != null) {
                    this.mItemAnimator.endAnimations();
                }
                this.mLayout.removeAndRecycleAllViews(this.mRecycler);
                this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
                this.mRecycler.clear();
                if (this.mIsAttached) {
                    this.mLayout.dispatchDetachedFromWindow(this, this.mRecycler);
                }
                this.mLayout.setRecyclerView(null);
                this.mLayout = null;
            }
            else {
                this.mRecycler.clear();
            }
            this.mChildHelper.removeAllViewsUnfiltered();
            this.mLayout = mLayout;
            if (mLayout != null) {
                if (mLayout.mRecyclerView != null) {
                    throw new IllegalArgumentException("LayoutManager " + mLayout + " is already attached to a RecyclerView: " + mLayout.mRecyclerView);
                }
                this.mLayout.setRecyclerView(this);
                if (this.mIsAttached) {
                    this.mLayout.dispatchAttachedToWindow(this);
                }
            }
            this.requestLayout();
        }
    }
    
    public void setNestedScrollingEnabled(final boolean nestedScrollingEnabled) {
        this.getScrollingChildHelper().setNestedScrollingEnabled(nestedScrollingEnabled);
    }
    
    public void setOnFlingListener(@Nullable final OnFlingListener mOnFlingListener) {
        this.mOnFlingListener = mOnFlingListener;
    }
    
    @Deprecated
    public void setOnScrollListener(final OnScrollListener mScrollListener) {
        this.mScrollListener = mScrollListener;
    }
    
    public void setPreserveFocusAfterLayout(final boolean mPreserveFocusAfterLayout) {
        this.mPreserveFocusAfterLayout = mPreserveFocusAfterLayout;
    }
    
    public void setRecycledViewPool(final RecycledViewPool recycledViewPool) {
        this.mRecycler.setRecycledViewPool(recycledViewPool);
    }
    
    public void setRecyclerListener(final RecyclerListener mRecyclerListener) {
        this.mRecyclerListener = mRecyclerListener;
    }
    
    void setScrollState(final int mScrollState) {
        if (mScrollState != this.mScrollState) {
            if ((this.mScrollState = mScrollState) != 2) {
                this.stopScrollersInternal();
            }
            this.dispatchOnScrollStateChanged(mScrollState);
        }
    }
    
    public void setScrollingTouchSlop(final int n) {
        final ViewConfiguration value = ViewConfiguration.get(this.getContext());
        switch (n) {
            default: {
                Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + n + "; using default value");
            }
            case 0: {
                this.mTouchSlop = value.getScaledTouchSlop();
                break;
            }
            case 1: {
                this.mTouchSlop = value.getScaledPagingTouchSlop();
                break;
            }
        }
    }
    
    public void setViewCacheExtension(final ViewCacheExtension viewCacheExtension) {
        this.mRecycler.setViewCacheExtension(viewCacheExtension);
    }
    
    boolean shouldDeferAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        boolean b;
        if (this.isComputingLayout()) {
            int contentChangeTypes = 0;
            if (accessibilityEvent != null) {
                contentChangeTypes = AccessibilityEventCompat.getContentChangeTypes(accessibilityEvent);
            }
            if (contentChangeTypes == 0) {
                contentChangeTypes = 0;
            }
            this.mEatenAccessibilityChangeFlags |= contentChangeTypes;
            b = true;
        }
        else {
            b = false;
        }
        return b;
    }
    
    public void smoothScrollBy(int n, int n2) {
        if (this.mLayout == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        }
        else if (!this.mLayoutFrozen) {
            if (!this.mLayout.canScrollHorizontally()) {
                n = 0;
            }
            if (!this.mLayout.canScrollVertically()) {
                n2 = 0;
            }
            if (n != 0 || n2 != 0) {
                this.mViewFlinger.smoothScrollBy(n, n2);
            }
        }
    }
    
    public void smoothScrollToPosition(final int n) {
        if (!this.mLayoutFrozen) {
            if (this.mLayout == null) {
                Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            }
            else {
                this.mLayout.smoothScrollToPosition(this, this.mState, n);
            }
        }
    }
    
    public boolean startNestedScroll(final int n) {
        return this.getScrollingChildHelper().startNestedScroll(n);
    }
    
    public void stopNestedScroll() {
        this.getScrollingChildHelper().stopNestedScroll();
    }
    
    public void stopScroll() {
        this.setScrollState(0);
        this.stopScrollersInternal();
    }
    
    public void swapAdapter(final Adapter adapter, final boolean b) {
        this.setLayoutFrozen(false);
        this.setAdapterInternal(adapter, true, b);
        this.setDataSetChangedAfterLayout();
        this.requestLayout();
    }
    
    void viewRangeUpdate(final int n, final int n2, final Object o) {
        final int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        final int n3 = n + n2;
        for (int i = 0; i < unfilteredChildCount; ++i) {
            final View unfilteredChild = this.mChildHelper.getUnfilteredChildAt(i);
            final ViewHolder childViewHolderInt = getChildViewHolderInt(unfilteredChild);
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.mPosition >= n && childViewHolderInt.mPosition < n3) {
                childViewHolderInt.addFlags(2);
                childViewHolderInt.addChangePayload(o);
                ((LayoutParams)unfilteredChild.getLayoutParams()).mInsetsDirty = true;
            }
        }
        this.mRecycler.viewRangeUpdate(n, n2);
    }
    
    public abstract static class Adapter<VH extends ViewHolder>
    {
        private boolean mHasStableIds;
        private final AdapterDataObservable mObservable;
        
        public Adapter() {
            this.mObservable = new AdapterDataObservable();
            this.mHasStableIds = false;
        }
        
        public final void bindViewHolder(final VH vh, final int mPosition) {
            vh.mPosition = mPosition;
            if (this.hasStableIds()) {
                vh.mItemId = this.getItemId(mPosition);
            }
            ((ViewHolder)vh).setFlags(1, 519);
            TraceCompat.beginSection("RV OnBindView");
            this.onBindViewHolder(vh, mPosition, ((ViewHolder)vh).getUnmodifiedPayloads());
            ((ViewHolder)vh).clearPayload();
            final ViewGroup$LayoutParams layoutParams = vh.itemView.getLayoutParams();
            if (layoutParams instanceof LayoutParams) {
                ((LayoutParams)layoutParams).mInsetsDirty = true;
            }
            TraceCompat.endSection();
        }
        
        public final VH createViewHolder(final ViewGroup viewGroup, final int mItemViewType) {
            TraceCompat.beginSection("RV CreateView");
            final ViewHolder onCreateViewHolder = this.onCreateViewHolder(viewGroup, mItemViewType);
            onCreateViewHolder.mItemViewType = mItemViewType;
            TraceCompat.endSection();
            return (VH)onCreateViewHolder;
        }
        
        public abstract int getItemCount();
        
        public long getItemId(final int n) {
            return -1L;
        }
        
        public int getItemViewType(final int n) {
            return 0;
        }
        
        public final boolean hasObservers() {
            return this.mObservable.hasObservers();
        }
        
        public final boolean hasStableIds() {
            return this.mHasStableIds;
        }
        
        public final void notifyDataSetChanged() {
            this.mObservable.notifyChanged();
        }
        
        public final void notifyItemChanged(final int n) {
            this.mObservable.notifyItemRangeChanged(n, 1);
        }
        
        public final void notifyItemChanged(final int n, final Object o) {
            this.mObservable.notifyItemRangeChanged(n, 1, o);
        }
        
        public final void notifyItemInserted(final int n) {
            this.mObservable.notifyItemRangeInserted(n, 1);
        }
        
        public final void notifyItemMoved(final int n, final int n2) {
            this.mObservable.notifyItemMoved(n, n2);
        }
        
        public final void notifyItemRangeChanged(final int n, final int n2) {
            this.mObservable.notifyItemRangeChanged(n, n2);
        }
        
        public final void notifyItemRangeChanged(final int n, final int n2, final Object o) {
            this.mObservable.notifyItemRangeChanged(n, n2, o);
        }
        
        public final void notifyItemRangeInserted(final int n, final int n2) {
            this.mObservable.notifyItemRangeInserted(n, n2);
        }
        
        public final void notifyItemRangeRemoved(final int n, final int n2) {
            this.mObservable.notifyItemRangeRemoved(n, n2);
        }
        
        public final void notifyItemRemoved(final int n) {
            this.mObservable.notifyItemRangeRemoved(n, 1);
        }
        
        public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        }
        
        public abstract void onBindViewHolder(final VH p0, final int p1);
        
        public void onBindViewHolder(final VH vh, final int n, final List<Object> list) {
            this.onBindViewHolder(vh, n);
        }
        
        public abstract VH onCreateViewHolder(final ViewGroup p0, final int p1);
        
        public void onDetachedFromRecyclerView(final RecyclerView recyclerView) {
        }
        
        public boolean onFailedToRecycleView(final VH vh) {
            return false;
        }
        
        public void onViewAttachedToWindow(final VH vh) {
        }
        
        public void onViewDetachedFromWindow(final VH vh) {
        }
        
        public void onViewRecycled(final VH vh) {
        }
        
        public void registerAdapterDataObserver(final AdapterDataObserver adapterDataObserver) {
            this.mObservable.registerObserver((Object)adapterDataObserver);
        }
        
        public void setHasStableIds(final boolean mHasStableIds) {
            if (this.hasObservers()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            this.mHasStableIds = mHasStableIds;
        }
        
        public void unregisterAdapterDataObserver(final AdapterDataObserver adapterDataObserver) {
            this.mObservable.unregisterObserver((Object)adapterDataObserver);
        }
    }
    
    static class AdapterDataObservable extends Observable<AdapterDataObserver>
    {
        public boolean hasObservers() {
            return !this.mObservers.isEmpty();
        }
        
        public void notifyChanged() {
            for (int i = -1 + this.mObservers.size(); i >= 0; --i) {
                ((AdapterDataObserver)this.mObservers.get(i)).onChanged();
            }
        }
        
        public void notifyItemMoved(final int n, final int n2) {
            for (int i = -1 + this.mObservers.size(); i >= 0; --i) {
                ((AdapterDataObserver)this.mObservers.get(i)).onItemRangeMoved(n, n2, 1);
            }
        }
        
        public void notifyItemRangeChanged(final int n, final int n2) {
            this.notifyItemRangeChanged(n, n2, null);
        }
        
        public void notifyItemRangeChanged(final int n, final int n2, final Object o) {
            for (int i = -1 + this.mObservers.size(); i >= 0; --i) {
                ((AdapterDataObserver)this.mObservers.get(i)).onItemRangeChanged(n, n2, o);
            }
        }
        
        public void notifyItemRangeInserted(final int n, final int n2) {
            for (int i = -1 + this.mObservers.size(); i >= 0; --i) {
                ((AdapterDataObserver)this.mObservers.get(i)).onItemRangeInserted(n, n2);
            }
        }
        
        public void notifyItemRangeRemoved(final int n, final int n2) {
            for (int i = -1 + this.mObservers.size(); i >= 0; --i) {
                ((AdapterDataObserver)this.mObservers.get(i)).onItemRangeRemoved(n, n2);
            }
        }
    }
    
    public abstract static class AdapterDataObserver
    {
        public void onChanged() {
        }
        
        public void onItemRangeChanged(final int n, final int n2) {
        }
        
        public void onItemRangeChanged(final int n, final int n2, final Object o) {
            this.onItemRangeChanged(n, n2);
        }
        
        public void onItemRangeInserted(final int n, final int n2) {
        }
        
        public void onItemRangeMoved(final int n, final int n2, final int n3) {
        }
        
        public void onItemRangeRemoved(final int n, final int n2) {
        }
    }
    
    public interface ChildDrawingOrderCallback
    {
        int onGetChildDrawingOrder(final int p0, final int p1);
    }
    
    public abstract static class ItemAnimator
    {
        public static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        public static final int FLAG_CHANGED = 2;
        public static final int FLAG_INVALIDATED = 4;
        public static final int FLAG_MOVED = 2048;
        public static final int FLAG_REMOVED = 8;
        private long mAddDuration;
        private long mChangeDuration;
        private ArrayList<ItemAnimatorFinishedListener> mFinishedListeners;
        private ItemAnimatorListener mListener;
        private long mMoveDuration;
        private long mRemoveDuration;
        
        public ItemAnimator() {
            this.mListener = null;
            this.mFinishedListeners = new ArrayList<ItemAnimatorFinishedListener>();
            this.mAddDuration = 120L;
            this.mRemoveDuration = 120L;
            this.mMoveDuration = 250L;
            this.mChangeDuration = 250L;
        }
        
        static int buildAdapterChangeFlagsForAnimations(final ViewHolder viewHolder) {
            int n = 0xE & viewHolder.mFlags;
            int n2;
            if (viewHolder.isInvalid()) {
                n2 = 4;
            }
            else {
                if ((n & 0x4) == 0x0) {
                    final int oldPosition = viewHolder.getOldPosition();
                    final int adapterPosition = viewHolder.getAdapterPosition();
                    if (oldPosition != -1 && adapterPosition != -1 && oldPosition != adapterPosition) {
                        n |= 0x800;
                    }
                }
                n2 = n;
            }
            return n2;
        }
        
        public abstract boolean animateAppearance(@NonNull final ViewHolder p0, @Nullable final ItemHolderInfo p1, @NonNull final ItemHolderInfo p2);
        
        public abstract boolean animateChange(@NonNull final ViewHolder p0, @NonNull final ViewHolder p1, @NonNull final ItemHolderInfo p2, @NonNull final ItemHolderInfo p3);
        
        public abstract boolean animateDisappearance(@NonNull final ViewHolder p0, @NonNull final ItemHolderInfo p1, @Nullable final ItemHolderInfo p2);
        
        public abstract boolean animatePersistence(@NonNull final ViewHolder p0, @NonNull final ItemHolderInfo p1, @NonNull final ItemHolderInfo p2);
        
        public boolean canReuseUpdatedViewHolder(@NonNull final ViewHolder viewHolder) {
            return true;
        }
        
        public boolean canReuseUpdatedViewHolder(@NonNull final ViewHolder viewHolder, @NonNull final List<Object> list) {
            return this.canReuseUpdatedViewHolder(viewHolder);
        }
        
        public final void dispatchAnimationFinished(final ViewHolder viewHolder) {
            this.onAnimationFinished(viewHolder);
            if (this.mListener != null) {
                this.mListener.onAnimationFinished(viewHolder);
            }
        }
        
        public final void dispatchAnimationStarted(final ViewHolder viewHolder) {
            this.onAnimationStarted(viewHolder);
        }
        
        public final void dispatchAnimationsFinished() {
            for (int size = this.mFinishedListeners.size(), i = 0; i < size; ++i) {
                this.mFinishedListeners.get(i).onAnimationsFinished();
            }
            this.mFinishedListeners.clear();
        }
        
        public abstract void endAnimation(final ViewHolder p0);
        
        public abstract void endAnimations();
        
        public long getAddDuration() {
            return this.mAddDuration;
        }
        
        public long getChangeDuration() {
            return this.mChangeDuration;
        }
        
        public long getMoveDuration() {
            return this.mMoveDuration;
        }
        
        public long getRemoveDuration() {
            return this.mRemoveDuration;
        }
        
        public abstract boolean isRunning();
        
        public final boolean isRunning(final ItemAnimatorFinishedListener itemAnimatorFinishedListener) {
            final boolean running = this.isRunning();
            if (itemAnimatorFinishedListener != null) {
                if (!running) {
                    itemAnimatorFinishedListener.onAnimationsFinished();
                }
                else {
                    this.mFinishedListeners.add(itemAnimatorFinishedListener);
                }
            }
            return running;
        }
        
        public ItemHolderInfo obtainHolderInfo() {
            return new ItemHolderInfo();
        }
        
        public void onAnimationFinished(final ViewHolder viewHolder) {
        }
        
        public void onAnimationStarted(final ViewHolder viewHolder) {
        }
        
        @NonNull
        public ItemHolderInfo recordPostLayoutInformation(@NonNull final State state, @NonNull final ViewHolder from) {
            return this.obtainHolderInfo().setFrom(from);
        }
        
        @NonNull
        public ItemHolderInfo recordPreLayoutInformation(@NonNull final State state, @NonNull final ViewHolder from, final int n, @NonNull final List<Object> list) {
            return this.obtainHolderInfo().setFrom(from);
        }
        
        public abstract void runPendingAnimations();
        
        public void setAddDuration(final long mAddDuration) {
            this.mAddDuration = mAddDuration;
        }
        
        public void setChangeDuration(final long mChangeDuration) {
            this.mChangeDuration = mChangeDuration;
        }
        
        void setListener(final ItemAnimatorListener mListener) {
            this.mListener = mListener;
        }
        
        public void setMoveDuration(final long mMoveDuration) {
            this.mMoveDuration = mMoveDuration;
        }
        
        public void setRemoveDuration(final long mRemoveDuration) {
            this.mRemoveDuration = mRemoveDuration;
        }
        
        @Retention(RetentionPolicy.SOURCE)
        public @interface AdapterChanges {
        }
        
        public interface ItemAnimatorFinishedListener
        {
            void onAnimationsFinished();
        }
        
        interface ItemAnimatorListener
        {
            void onAnimationFinished(final ViewHolder p0);
        }
        
        public static class ItemHolderInfo
        {
            public int bottom;
            public int changeFlags;
            public int left;
            public int right;
            public int top;
            
            public ItemHolderInfo setFrom(final ViewHolder viewHolder) {
                return this.setFrom(viewHolder, 0);
            }
            
            public ItemHolderInfo setFrom(final ViewHolder viewHolder, final int n) {
                final View itemView = viewHolder.itemView;
                this.left = itemView.getLeft();
                this.top = itemView.getTop();
                this.right = itemView.getRight();
                this.bottom = itemView.getBottom();
                return this;
            }
        }
    }
    
    private class ItemAnimatorRestoreListener implements ItemAnimatorListener
    {
        @Override
        public void onAnimationFinished(final ViewHolder viewHolder) {
            viewHolder.setIsRecyclable(true);
            if (viewHolder.mShadowedHolder != null && viewHolder.mShadowingHolder == null) {
                viewHolder.mShadowedHolder = null;
            }
            viewHolder.mShadowingHolder = null;
            if (!viewHolder.shouldBeKeptAsChild() && !RecyclerView.this.removeAnimatingView(viewHolder.itemView) && viewHolder.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(viewHolder.itemView, false);
            }
        }
    }
    
    public abstract static class ItemDecoration
    {
        @Deprecated
        public void getItemOffsets(final Rect rect, final int n, final RecyclerView recyclerView) {
            rect.set(0, 0, 0, 0);
        }
        
        public void getItemOffsets(final Rect rect, final View view, final RecyclerView recyclerView, final State state) {
            this.getItemOffsets(rect, ((LayoutParams)view.getLayoutParams()).getViewLayoutPosition(), recyclerView);
        }
        
        @Deprecated
        public void onDraw(final Canvas canvas, final RecyclerView recyclerView) {
        }
        
        public void onDraw(final Canvas canvas, final RecyclerView recyclerView, final State state) {
            this.onDraw(canvas, recyclerView);
        }
        
        @Deprecated
        public void onDrawOver(final Canvas canvas, final RecyclerView recyclerView) {
        }
        
        public void onDrawOver(final Canvas canvas, final RecyclerView recyclerView, final State state) {
            this.onDrawOver(canvas, recyclerView);
        }
    }
    
    public abstract static class LayoutManager
    {
        boolean mAutoMeasure;
        ChildHelper mChildHelper;
        private int mHeight;
        private int mHeightMode;
        boolean mIsAttachedToWindow;
        private boolean mMeasurementCacheEnabled;
        RecyclerView mRecyclerView;
        boolean mRequestedSimpleAnimations;
        @Nullable
        SmoothScroller mSmoothScroller;
        private int mWidth;
        private int mWidthMode;
        
        public LayoutManager() {
            this.mRequestedSimpleAnimations = false;
            this.mIsAttachedToWindow = false;
            this.mAutoMeasure = false;
            this.mMeasurementCacheEnabled = true;
        }
        
        private void addViewInt(final View view, int childCount, final boolean b) {
            final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (b || childViewHolderInt.isRemoved()) {
                this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(childViewHolderInt);
            }
            else {
                this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(childViewHolderInt);
            }
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            if (childViewHolderInt.wasReturnedFromScrap() || childViewHolderInt.isScrap()) {
                if (childViewHolderInt.isScrap()) {
                    childViewHolderInt.unScrap();
                }
                else {
                    childViewHolderInt.clearReturnedFromScrapFlag();
                }
                this.mChildHelper.attachViewToParent(view, childCount, view.getLayoutParams(), false);
            }
            else if (view.getParent() == this.mRecyclerView) {
                final int indexOfChild = this.mChildHelper.indexOfChild(view);
                if (childCount == -1) {
                    childCount = this.mChildHelper.getChildCount();
                }
                if (indexOfChild == -1) {
                    throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.mRecyclerView.indexOfChild(view));
                }
                if (indexOfChild != childCount) {
                    this.mRecyclerView.mLayout.moveView(indexOfChild, childCount);
                }
            }
            else {
                this.mChildHelper.addView(view, childCount, false);
                layoutParams.mInsetsDirty = true;
                if (this.mSmoothScroller != null && this.mSmoothScroller.isRunning()) {
                    this.mSmoothScroller.onChildAttachedToWindow(view);
                }
            }
            if (layoutParams.mPendingInvalidate) {
                childViewHolderInt.itemView.invalidate();
                layoutParams.mPendingInvalidate = false;
            }
        }
        
        public static int chooseSize(final int n, final int n2, final int n3) {
            final int mode = View$MeasureSpec.getMode(n);
            int n4 = View$MeasureSpec.getSize(n);
            switch (mode) {
                default: {
                    n4 = Math.max(n2, n3);
                    return n4;
                }
                case Integer.MIN_VALUE: {
                    n4 = Math.min(n4, Math.max(n2, n3));
                }
                case 1073741824: {
                    return n4;
                }
            }
        }
        
        private void detachViewInternal(final int n, final View view) {
            this.mChildHelper.detachViewFromParent(n);
        }
        
        public static int getChildMeasureSpec(final int n, final int n2, final int n3, final int n4, final boolean b) {
            final int max = Math.max(0, n - n3);
            int n5 = 0;
            int n6 = 0;
            if (n4 >= 0) {
                n5 = n4;
                n6 = 1073741824;
            }
            else if (b) {
                if (n4 == -1) {
                    switch (n2) {
                        default: {
                            n6 = 0;
                            n5 = 0;
                            break;
                        }
                        case Integer.MIN_VALUE:
                        case 1073741824: {
                            n5 = max;
                            n6 = n2;
                            break;
                        }
                        case 0: {
                            n6 = 0;
                            n5 = 0;
                            break;
                        }
                    }
                }
                else {
                    n6 = 0;
                    n5 = 0;
                    if (n4 == -2) {
                        n6 = 0;
                        n5 = 0;
                    }
                }
            }
            else if (n4 == -1) {
                n5 = max;
                n6 = n2;
            }
            else {
                n6 = 0;
                n5 = 0;
                if (n4 == -2) {
                    n5 = max;
                    if (n2 == Integer.MIN_VALUE || n2 == 1073741824) {
                        n6 = Integer.MIN_VALUE;
                    }
                    else {
                        n6 = 0;
                    }
                }
            }
            return View$MeasureSpec.makeMeasureSpec(n5, n6);
        }
        
        @Deprecated
        public static int getChildMeasureSpec(final int n, final int n2, final int n3, final boolean b) {
            final int max = Math.max(0, n - n2);
            int n4;
            int n5;
            if (b) {
                if (n3 >= 0) {
                    n4 = n3;
                    n5 = 1073741824;
                }
                else {
                    n5 = 0;
                    n4 = 0;
                }
            }
            else if (n3 >= 0) {
                n4 = n3;
                n5 = 1073741824;
            }
            else if (n3 == -1) {
                n4 = max;
                n5 = 1073741824;
            }
            else {
                n5 = 0;
                n4 = 0;
                if (n3 == -2) {
                    n4 = max;
                    n5 = Integer.MIN_VALUE;
                }
            }
            return View$MeasureSpec.makeMeasureSpec(n4, n5);
        }
        
        public static Properties getProperties(final Context context, final AttributeSet set, final int n, final int n2) {
            final Properties properties = new Properties();
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.RecyclerView, n, n2);
            properties.orientation = obtainStyledAttributes.getInt(R.styleable.RecyclerView_android_orientation, 1);
            properties.spanCount = obtainStyledAttributes.getInt(R.styleable.RecyclerView_spanCount, 1);
            properties.reverseLayout = obtainStyledAttributes.getBoolean(R.styleable.RecyclerView_reverseLayout, false);
            properties.stackFromEnd = obtainStyledAttributes.getBoolean(R.styleable.RecyclerView_stackFromEnd, false);
            obtainStyledAttributes.recycle();
            return properties;
        }
        
        private static boolean isMeasurementUpToDate(final int n, final int n2, final int n3) {
            boolean b = true;
            final int mode = View$MeasureSpec.getMode(n2);
            final int size = View$MeasureSpec.getSize(n2);
            if (n3 > 0 && n != n3) {
                b = false;
            }
            else {
                switch (mode) {
                    case 0: {
                        break;
                    }
                    default: {
                        b = false;
                        break;
                    }
                    case Integer.MIN_VALUE: {
                        if (size < n) {
                            b = false;
                            break;
                        }
                        break;
                    }
                    case 1073741824: {
                        if (size != n) {
                            b = false;
                            break;
                        }
                        break;
                    }
                }
            }
            return b;
        }
        
        private void onSmoothScrollerStopped(final SmoothScroller smoothScroller) {
            if (this.mSmoothScroller == smoothScroller) {
                this.mSmoothScroller = null;
            }
        }
        
        private void scrapOrRecycleView(final Recycler recycler, final int n, final View view) {
            final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (!childViewHolderInt.shouldIgnore()) {
                if (childViewHolderInt.isInvalid() && !childViewHolderInt.isRemoved() && !this.mRecyclerView.mAdapter.hasStableIds()) {
                    this.removeViewAt(n);
                    recycler.recycleViewHolderInternal(childViewHolderInt);
                }
                else {
                    this.detachViewAt(n);
                    recycler.scrapView(view);
                    this.mRecyclerView.mViewInfoStore.onViewDetached(childViewHolderInt);
                }
            }
        }
        
        public void addDisappearingView(final View view) {
            this.addDisappearingView(view, -1);
        }
        
        public void addDisappearingView(final View view, final int n) {
            this.addViewInt(view, n, true);
        }
        
        public void addView(final View view) {
            this.addView(view, -1);
        }
        
        public void addView(final View view, final int n) {
            this.addViewInt(view, n, false);
        }
        
        public void assertInLayoutOrScroll(final String s) {
            if (this.mRecyclerView != null) {
                this.mRecyclerView.assertInLayoutOrScroll(s);
            }
        }
        
        public void assertNotInLayoutOrScroll(final String s) {
            if (this.mRecyclerView != null) {
                this.mRecyclerView.assertNotInLayoutOrScroll(s);
            }
        }
        
        public void attachView(final View view) {
            this.attachView(view, -1);
        }
        
        public void attachView(final View view, final int n) {
            this.attachView(view, n, (LayoutParams)view.getLayoutParams());
        }
        
        public void attachView(final View view, final int n, final LayoutParams layoutParams) {
            final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.isRemoved()) {
                this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(childViewHolderInt);
            }
            else {
                this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(childViewHolderInt);
            }
            this.mChildHelper.attachViewToParent(view, n, (ViewGroup$LayoutParams)layoutParams, childViewHolderInt.isRemoved());
        }
        
        public void calculateItemDecorationsForChild(final View view, final Rect rect) {
            if (this.mRecyclerView == null) {
                rect.set(0, 0, 0, 0);
            }
            else {
                rect.set(this.mRecyclerView.getItemDecorInsetsForChild(view));
            }
        }
        
        public boolean canScrollHorizontally() {
            return false;
        }
        
        public boolean canScrollVertically() {
            return false;
        }
        
        public boolean checkLayoutParams(final LayoutParams layoutParams) {
            return layoutParams != null;
        }
        
        public int computeHorizontalScrollExtent(final State state) {
            return 0;
        }
        
        public int computeHorizontalScrollOffset(final State state) {
            return 0;
        }
        
        public int computeHorizontalScrollRange(final State state) {
            return 0;
        }
        
        public int computeVerticalScrollExtent(final State state) {
            return 0;
        }
        
        public int computeVerticalScrollOffset(final State state) {
            return 0;
        }
        
        public int computeVerticalScrollRange(final State state) {
            return 0;
        }
        
        public void detachAndScrapAttachedViews(final Recycler recycler) {
            for (int i = -1 + this.getChildCount(); i >= 0; --i) {
                this.scrapOrRecycleView(recycler, i, this.getChildAt(i));
            }
        }
        
        public void detachAndScrapView(final View view, final Recycler recycler) {
            this.scrapOrRecycleView(recycler, this.mChildHelper.indexOfChild(view), view);
        }
        
        public void detachAndScrapViewAt(final int n, final Recycler recycler) {
            this.scrapOrRecycleView(recycler, n, this.getChildAt(n));
        }
        
        public void detachView(final View view) {
            final int indexOfChild = this.mChildHelper.indexOfChild(view);
            if (indexOfChild >= 0) {
                this.detachViewInternal(indexOfChild, view);
            }
        }
        
        public void detachViewAt(final int n) {
            this.detachViewInternal(n, this.getChildAt(n));
        }
        
        void dispatchAttachedToWindow(final RecyclerView recyclerView) {
            this.mIsAttachedToWindow = true;
            this.onAttachedToWindow(recyclerView);
        }
        
        void dispatchDetachedFromWindow(final RecyclerView recyclerView, final Recycler recycler) {
            this.mIsAttachedToWindow = false;
            this.onDetachedFromWindow(recyclerView, recycler);
        }
        
        public void endAnimation(final View view) {
            if (this.mRecyclerView.mItemAnimator != null) {
                this.mRecyclerView.mItemAnimator.endAnimation(RecyclerView.getChildViewHolderInt(view));
            }
        }
        
        @Nullable
        public View findContainingItemView(final View view) {
            View containingItemView;
            if (this.mRecyclerView == null) {
                containingItemView = null;
            }
            else {
                containingItemView = this.mRecyclerView.findContainingItemView(view);
                if (containingItemView == null) {
                    containingItemView = null;
                }
                else if (this.mChildHelper.isHidden(containingItemView)) {
                    containingItemView = null;
                }
            }
            return containingItemView;
        }
        
        public View findViewByPosition(final int n) {
            for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
                final View child = this.getChildAt(i);
                final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(child);
                if (childViewHolderInt != null && childViewHolderInt.getLayoutPosition() == n && !childViewHolderInt.shouldIgnore() && (this.mRecyclerView.mState.isPreLayout() || !childViewHolderInt.isRemoved())) {
                    return child;
                }
            }
            return null;
        }
        
        public abstract LayoutParams generateDefaultLayoutParams();
        
        public LayoutParams generateLayoutParams(final Context context, final AttributeSet set) {
            return new LayoutParams(context, set);
        }
        
        public LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            LayoutParams layoutParams;
            if (viewGroup$LayoutParams instanceof LayoutParams) {
                layoutParams = new LayoutParams((LayoutParams)viewGroup$LayoutParams);
            }
            else if (viewGroup$LayoutParams instanceof ViewGroup$MarginLayoutParams) {
                layoutParams = new LayoutParams((ViewGroup$MarginLayoutParams)viewGroup$LayoutParams);
            }
            else {
                layoutParams = new LayoutParams(viewGroup$LayoutParams);
            }
            return layoutParams;
        }
        
        public int getBaseline() {
            return -1;
        }
        
        public int getBottomDecorationHeight(final View view) {
            return ((LayoutParams)view.getLayoutParams()).mDecorInsets.bottom;
        }
        
        public View getChildAt(final int n) {
            View child;
            if (this.mChildHelper != null) {
                child = this.mChildHelper.getChildAt(n);
            }
            else {
                child = null;
            }
            return child;
        }
        
        public int getChildCount() {
            int childCount;
            if (this.mChildHelper != null) {
                childCount = this.mChildHelper.getChildCount();
            }
            else {
                childCount = 0;
            }
            return childCount;
        }
        
        public boolean getClipToPadding() {
            return this.mRecyclerView != null && this.mRecyclerView.mClipToPadding;
        }
        
        public int getColumnCountForAccessibility(final Recycler recycler, final State state) {
            int itemCount = 1;
            if (this.mRecyclerView != null && this.mRecyclerView.mAdapter != null && this.canScrollHorizontally()) {
                itemCount = this.mRecyclerView.mAdapter.getItemCount();
            }
            return itemCount;
        }
        
        public int getDecoratedBottom(final View view) {
            return view.getBottom() + this.getBottomDecorationHeight(view);
        }
        
        public void getDecoratedBoundsWithMargins(final View view, final Rect rect) {
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            final Rect mDecorInsets = layoutParams.mDecorInsets;
            rect.set(view.getLeft() - mDecorInsets.left - layoutParams.leftMargin, view.getTop() - mDecorInsets.top - layoutParams.topMargin, view.getRight() + mDecorInsets.right + layoutParams.rightMargin, view.getBottom() + mDecorInsets.bottom + layoutParams.bottomMargin);
        }
        
        public int getDecoratedLeft(final View view) {
            return view.getLeft() - this.getLeftDecorationWidth(view);
        }
        
        public int getDecoratedMeasuredHeight(final View view) {
            final Rect mDecorInsets = ((LayoutParams)view.getLayoutParams()).mDecorInsets;
            return view.getMeasuredHeight() + mDecorInsets.top + mDecorInsets.bottom;
        }
        
        public int getDecoratedMeasuredWidth(final View view) {
            final Rect mDecorInsets = ((LayoutParams)view.getLayoutParams()).mDecorInsets;
            return view.getMeasuredWidth() + mDecorInsets.left + mDecorInsets.right;
        }
        
        public int getDecoratedRight(final View view) {
            return view.getRight() + this.getRightDecorationWidth(view);
        }
        
        public int getDecoratedTop(final View view) {
            return view.getTop() - this.getTopDecorationHeight(view);
        }
        
        public View getFocusedChild() {
            View focusedChild;
            if (this.mRecyclerView == null) {
                focusedChild = null;
            }
            else {
                focusedChild = this.mRecyclerView.getFocusedChild();
                if (focusedChild == null || this.mChildHelper.isHidden(focusedChild)) {
                    focusedChild = null;
                }
            }
            return focusedChild;
        }
        
        public int getHeight() {
            return this.mHeight;
        }
        
        public int getHeightMode() {
            return this.mHeightMode;
        }
        
        public int getItemCount() {
            Adapter adapter;
            if (this.mRecyclerView != null) {
                adapter = this.mRecyclerView.getAdapter();
            }
            else {
                adapter = null;
            }
            int itemCount;
            if (adapter != null) {
                itemCount = adapter.getItemCount();
            }
            else {
                itemCount = 0;
            }
            return itemCount;
        }
        
        public int getItemViewType(final View view) {
            return RecyclerView.getChildViewHolderInt(view).getItemViewType();
        }
        
        public int getLayoutDirection() {
            return ViewCompat.getLayoutDirection((View)this.mRecyclerView);
        }
        
        public int getLeftDecorationWidth(final View view) {
            return ((LayoutParams)view.getLayoutParams()).mDecorInsets.left;
        }
        
        public int getMinimumHeight() {
            return ViewCompat.getMinimumHeight((View)this.mRecyclerView);
        }
        
        public int getMinimumWidth() {
            return ViewCompat.getMinimumWidth((View)this.mRecyclerView);
        }
        
        public int getPaddingBottom() {
            int paddingBottom;
            if (this.mRecyclerView != null) {
                paddingBottom = this.mRecyclerView.getPaddingBottom();
            }
            else {
                paddingBottom = 0;
            }
            return paddingBottom;
        }
        
        public int getPaddingEnd() {
            int paddingEnd;
            if (this.mRecyclerView != null) {
                paddingEnd = ViewCompat.getPaddingEnd((View)this.mRecyclerView);
            }
            else {
                paddingEnd = 0;
            }
            return paddingEnd;
        }
        
        public int getPaddingLeft() {
            int paddingLeft;
            if (this.mRecyclerView != null) {
                paddingLeft = this.mRecyclerView.getPaddingLeft();
            }
            else {
                paddingLeft = 0;
            }
            return paddingLeft;
        }
        
        public int getPaddingRight() {
            int paddingRight;
            if (this.mRecyclerView != null) {
                paddingRight = this.mRecyclerView.getPaddingRight();
            }
            else {
                paddingRight = 0;
            }
            return paddingRight;
        }
        
        public int getPaddingStart() {
            int paddingStart;
            if (this.mRecyclerView != null) {
                paddingStart = ViewCompat.getPaddingStart((View)this.mRecyclerView);
            }
            else {
                paddingStart = 0;
            }
            return paddingStart;
        }
        
        public int getPaddingTop() {
            int paddingTop;
            if (this.mRecyclerView != null) {
                paddingTop = this.mRecyclerView.getPaddingTop();
            }
            else {
                paddingTop = 0;
            }
            return paddingTop;
        }
        
        public int getPosition(final View view) {
            return ((LayoutParams)view.getLayoutParams()).getViewLayoutPosition();
        }
        
        public int getRightDecorationWidth(final View view) {
            return ((LayoutParams)view.getLayoutParams()).mDecorInsets.right;
        }
        
        public int getRowCountForAccessibility(final Recycler recycler, final State state) {
            int itemCount = 1;
            if (this.mRecyclerView != null && this.mRecyclerView.mAdapter != null && this.canScrollVertically()) {
                itemCount = this.mRecyclerView.mAdapter.getItemCount();
            }
            return itemCount;
        }
        
        public int getSelectionModeForAccessibility(final Recycler recycler, final State state) {
            return 0;
        }
        
        public int getTopDecorationHeight(final View view) {
            return ((LayoutParams)view.getLayoutParams()).mDecorInsets.top;
        }
        
        public void getTransformedBoundingBox(final View view, final boolean b, final Rect rect) {
            if (b) {
                final Rect mDecorInsets = ((LayoutParams)view.getLayoutParams()).mDecorInsets;
                rect.set(-mDecorInsets.left, -mDecorInsets.top, view.getWidth() + mDecorInsets.right, view.getHeight() + mDecorInsets.bottom);
            }
            else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (this.mRecyclerView != null) {
                final Matrix matrix = ViewCompat.getMatrix(view);
                if (matrix != null && !matrix.isIdentity()) {
                    final RectF mTempRectF = this.mRecyclerView.mTempRectF;
                    mTempRectF.set(rect);
                    matrix.mapRect(mTempRectF);
                    rect.set((int)Math.floor(mTempRectF.left), (int)Math.floor(mTempRectF.top), (int)Math.ceil(mTempRectF.right), (int)Math.ceil(mTempRectF.bottom));
                }
            }
            rect.offset(view.getLeft(), view.getTop());
        }
        
        public int getWidth() {
            return this.mWidth;
        }
        
        public int getWidthMode() {
            return this.mWidthMode;
        }
        
        boolean hasFlexibleChildInBothOrientations() {
            for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
                final ViewGroup$LayoutParams layoutParams = this.getChildAt(i).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
            return false;
        }
        
        public boolean hasFocus() {
            return this.mRecyclerView != null && this.mRecyclerView.hasFocus();
        }
        
        public void ignoreView(final View view) {
            if (view.getParent() != this.mRecyclerView || this.mRecyclerView.indexOfChild(view) == -1) {
                throw new IllegalArgumentException("View should be fully attached to be ignored");
            }
            final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            childViewHolderInt.addFlags(128);
            this.mRecyclerView.mViewInfoStore.removeViewHolder(childViewHolderInt);
        }
        
        public boolean isAttachedToWindow() {
            return this.mIsAttachedToWindow;
        }
        
        public boolean isAutoMeasureEnabled() {
            return this.mAutoMeasure;
        }
        
        public boolean isFocused() {
            return this.mRecyclerView != null && this.mRecyclerView.isFocused();
        }
        
        public boolean isLayoutHierarchical(final Recycler recycler, final State state) {
            return false;
        }
        
        public boolean isMeasurementCacheEnabled() {
            return this.mMeasurementCacheEnabled;
        }
        
        public boolean isSmoothScrolling() {
            return this.mSmoothScroller != null && this.mSmoothScroller.isRunning();
        }
        
        public void layoutDecorated(final View view, final int n, final int n2, final int n3, final int n4) {
            final Rect mDecorInsets = ((LayoutParams)view.getLayoutParams()).mDecorInsets;
            view.layout(n + mDecorInsets.left, n2 + mDecorInsets.top, n3 - mDecorInsets.right, n4 - mDecorInsets.bottom);
        }
        
        public void layoutDecoratedWithMargins(final View view, final int n, final int n2, final int n3, final int n4) {
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            final Rect mDecorInsets = layoutParams.mDecorInsets;
            view.layout(n + mDecorInsets.left + layoutParams.leftMargin, n2 + mDecorInsets.top + layoutParams.topMargin, n3 - mDecorInsets.right - layoutParams.rightMargin, n4 - mDecorInsets.bottom - layoutParams.bottomMargin);
        }
        
        public void measureChild(final View view, final int n, final int n2) {
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            final Rect itemDecorInsetsForChild = this.mRecyclerView.getItemDecorInsetsForChild(view);
            final int n3 = n + (itemDecorInsetsForChild.left + itemDecorInsetsForChild.right);
            final int n4 = n2 + (itemDecorInsetsForChild.top + itemDecorInsetsForChild.bottom);
            final int childMeasureSpec = getChildMeasureSpec(this.getWidth(), this.getWidthMode(), n3 + (this.getPaddingLeft() + this.getPaddingRight()), layoutParams.width, this.canScrollHorizontally());
            final int childMeasureSpec2 = getChildMeasureSpec(this.getHeight(), this.getHeightMode(), n4 + (this.getPaddingTop() + this.getPaddingBottom()), layoutParams.height, this.canScrollVertically());
            if (this.shouldMeasureChild(view, childMeasureSpec, childMeasureSpec2, layoutParams)) {
                view.measure(childMeasureSpec, childMeasureSpec2);
            }
        }
        
        public void measureChildWithMargins(final View view, final int n, final int n2) {
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            final Rect itemDecorInsetsForChild = this.mRecyclerView.getItemDecorInsetsForChild(view);
            final int n3 = n + (itemDecorInsetsForChild.left + itemDecorInsetsForChild.right);
            final int n4 = n2 + (itemDecorInsetsForChild.top + itemDecorInsetsForChild.bottom);
            final int childMeasureSpec = getChildMeasureSpec(this.getWidth(), this.getWidthMode(), n3 + (this.getPaddingLeft() + this.getPaddingRight() + layoutParams.leftMargin + layoutParams.rightMargin), layoutParams.width, this.canScrollHorizontally());
            final int childMeasureSpec2 = getChildMeasureSpec(this.getHeight(), this.getHeightMode(), n4 + (this.getPaddingTop() + this.getPaddingBottom() + layoutParams.topMargin + layoutParams.bottomMargin), layoutParams.height, this.canScrollVertically());
            if (this.shouldMeasureChild(view, childMeasureSpec, childMeasureSpec2, layoutParams)) {
                view.measure(childMeasureSpec, childMeasureSpec2);
            }
        }
        
        public void moveView(final int n, final int n2) {
            final View child = this.getChildAt(n);
            if (child == null) {
                throw new IllegalArgumentException("Cannot move a child from non-existing index:" + n);
            }
            this.detachViewAt(n);
            this.attachView(child, n2);
        }
        
        public void offsetChildrenHorizontal(final int n) {
            if (this.mRecyclerView != null) {
                this.mRecyclerView.offsetChildrenHorizontal(n);
            }
        }
        
        public void offsetChildrenVertical(final int n) {
            if (this.mRecyclerView != null) {
                this.mRecyclerView.offsetChildrenVertical(n);
            }
        }
        
        public void onAdapterChanged(final Adapter adapter, final Adapter adapter2) {
        }
        
        public boolean onAddFocusables(final RecyclerView recyclerView, final ArrayList<View> list, final int n, final int n2) {
            return false;
        }
        
        @CallSuper
        public void onAttachedToWindow(final RecyclerView recyclerView) {
        }
        
        @Deprecated
        public void onDetachedFromWindow(final RecyclerView recyclerView) {
        }
        
        @CallSuper
        public void onDetachedFromWindow(final RecyclerView recyclerView, final Recycler recycler) {
            this.onDetachedFromWindow(recyclerView);
        }
        
        @Nullable
        public View onFocusSearchFailed(final View view, final int n, final Recycler recycler, final State state) {
            return null;
        }
        
        public void onInitializeAccessibilityEvent(final Recycler recycler, final State state, final AccessibilityEvent accessibilityEvent) {
            int scrollable = 1;
            final AccessibilityRecordCompat record = AccessibilityEventCompat.asRecord(accessibilityEvent);
            if (this.mRecyclerView != null && record != null) {
                if (!ViewCompat.canScrollVertically((View)this.mRecyclerView, scrollable) && !ViewCompat.canScrollVertically((View)this.mRecyclerView, -1) && !ViewCompat.canScrollHorizontally((View)this.mRecyclerView, -1) && !ViewCompat.canScrollHorizontally((View)this.mRecyclerView, scrollable)) {
                    scrollable = 0;
                }
                record.setScrollable(scrollable != 0);
                if (this.mRecyclerView.mAdapter != null) {
                    record.setItemCount(this.mRecyclerView.mAdapter.getItemCount());
                }
            }
        }
        
        public void onInitializeAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
            this.onInitializeAccessibilityEvent(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, accessibilityEvent);
        }
        
        void onInitializeAccessibilityNodeInfo(final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            this.onInitializeAccessibilityNodeInfo(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, accessibilityNodeInfoCompat);
        }
        
        public void onInitializeAccessibilityNodeInfo(final Recycler recycler, final State state, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (ViewCompat.canScrollVertically((View)this.mRecyclerView, -1) || ViewCompat.canScrollHorizontally((View)this.mRecyclerView, -1)) {
                accessibilityNodeInfoCompat.addAction(8192);
                accessibilityNodeInfoCompat.setScrollable(true);
            }
            if (ViewCompat.canScrollVertically((View)this.mRecyclerView, 1) || ViewCompat.canScrollHorizontally((View)this.mRecyclerView, 1)) {
                accessibilityNodeInfoCompat.addAction(4096);
                accessibilityNodeInfoCompat.setScrollable(true);
            }
            accessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(this.getRowCountForAccessibility(recycler, state), this.getColumnCountForAccessibility(recycler, state), this.isLayoutHierarchical(recycler, state), this.getSelectionModeForAccessibility(recycler, state)));
        }
        
        public void onInitializeAccessibilityNodeInfoForItem(final Recycler recycler, final State state, final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int position;
            if (this.canScrollVertically()) {
                position = this.getPosition(view);
            }
            else {
                position = 0;
            }
            int position2;
            if (this.canScrollHorizontally()) {
                position2 = this.getPosition(view);
            }
            else {
                position2 = 0;
            }
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(position, 1, position2, 1, false, false));
        }
        
        void onInitializeAccessibilityNodeInfoForItem(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && !this.mChildHelper.isHidden(childViewHolderInt.itemView)) {
                this.onInitializeAccessibilityNodeInfoForItem(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, view, accessibilityNodeInfoCompat);
            }
        }
        
        public View onInterceptFocusSearch(final View view, final int n) {
            return null;
        }
        
        public void onItemsAdded(final RecyclerView recyclerView, final int n, final int n2) {
        }
        
        public void onItemsChanged(final RecyclerView recyclerView) {
        }
        
        public void onItemsMoved(final RecyclerView recyclerView, final int n, final int n2, final int n3) {
        }
        
        public void onItemsRemoved(final RecyclerView recyclerView, final int n, final int n2) {
        }
        
        public void onItemsUpdated(final RecyclerView recyclerView, final int n, final int n2) {
        }
        
        public void onItemsUpdated(final RecyclerView recyclerView, final int n, final int n2, final Object o) {
            this.onItemsUpdated(recyclerView, n, n2);
        }
        
        public void onLayoutChildren(final Recycler recycler, final State state) {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }
        
        public void onLayoutCompleted(final State state) {
        }
        
        public void onMeasure(final Recycler recycler, final State state, final int n, final int n2) {
            this.mRecyclerView.defaultOnMeasure(n, n2);
        }
        
        public boolean onRequestChildFocus(final RecyclerView recyclerView, final State state, final View view, final View view2) {
            return this.onRequestChildFocus(recyclerView, view, view2);
        }
        
        @Deprecated
        public boolean onRequestChildFocus(final RecyclerView recyclerView, final View view, final View view2) {
            return this.isSmoothScrolling() || recyclerView.isComputingLayout();
        }
        
        public void onRestoreInstanceState(final Parcelable parcelable) {
        }
        
        public Parcelable onSaveInstanceState() {
            return null;
        }
        
        public void onScrollStateChanged(final int n) {
        }
        
        boolean performAccessibilityAction(final int n, final Bundle bundle) {
            return this.performAccessibilityAction(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, n, bundle);
        }
        
        public boolean performAccessibilityAction(final Recycler recycler, final State state, final int n, final Bundle bundle) {
            final RecyclerView mRecyclerView = this.mRecyclerView;
            boolean b = false;
            if (mRecyclerView != null) {
                int n2 = 0;
                int n3 = 0;
                switch (n) {
                    case 8192: {
                        final boolean canScrollVertically = ViewCompat.canScrollVertically((View)this.mRecyclerView, -1);
                        n3 = 0;
                        if (canScrollVertically) {
                            n3 = -(this.getHeight() - this.getPaddingTop() - this.getPaddingBottom());
                        }
                        final boolean canScrollHorizontally = ViewCompat.canScrollHorizontally((View)this.mRecyclerView, -1);
                        n2 = 0;
                        if (canScrollHorizontally) {
                            n2 = -(this.getWidth() - this.getPaddingLeft() - this.getPaddingRight());
                            break;
                        }
                        break;
                    }
                    case 4096: {
                        final boolean canScrollVertically2 = ViewCompat.canScrollVertically((View)this.mRecyclerView, 1);
                        n3 = 0;
                        if (canScrollVertically2) {
                            n3 = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
                        }
                        final boolean canScrollHorizontally2 = ViewCompat.canScrollHorizontally((View)this.mRecyclerView, 1);
                        n2 = 0;
                        if (canScrollHorizontally2) {
                            n2 = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
                            break;
                        }
                        break;
                    }
                }
                if (n3 == 0) {
                    b = false;
                    if (n2 == 0) {
                        return b;
                    }
                }
                this.mRecyclerView.scrollBy(n2, n3);
                b = true;
            }
            return b;
        }
        
        public boolean performAccessibilityActionForItem(final Recycler recycler, final State state, final View view, final int n, final Bundle bundle) {
            return false;
        }
        
        boolean performAccessibilityActionForItem(final View view, final int n, final Bundle bundle) {
            return this.performAccessibilityActionForItem(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, view, n, bundle);
        }
        
        public void postOnAnimation(final Runnable runnable) {
            if (this.mRecyclerView != null) {
                ViewCompat.postOnAnimation((View)this.mRecyclerView, runnable);
            }
        }
        
        public void removeAllViews() {
            for (int i = -1 + this.getChildCount(); i >= 0; --i) {
                this.mChildHelper.removeViewAt(i);
            }
        }
        
        public void removeAndRecycleAllViews(final Recycler recycler) {
            for (int i = -1 + this.getChildCount(); i >= 0; --i) {
                if (!RecyclerView.getChildViewHolderInt(this.getChildAt(i)).shouldIgnore()) {
                    this.removeAndRecycleViewAt(i, recycler);
                }
            }
        }
        
        void removeAndRecycleScrapInt(final Recycler recycler) {
            final int scrapCount = recycler.getScrapCount();
            for (int i = scrapCount - 1; i >= 0; --i) {
                final View scrapView = recycler.getScrapViewAt(i);
                final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(scrapView);
                if (!childViewHolderInt.shouldIgnore()) {
                    childViewHolderInt.setIsRecyclable(false);
                    if (childViewHolderInt.isTmpDetached()) {
                        this.mRecyclerView.removeDetachedView(scrapView, false);
                    }
                    if (this.mRecyclerView.mItemAnimator != null) {
                        this.mRecyclerView.mItemAnimator.endAnimation(childViewHolderInt);
                    }
                    childViewHolderInt.setIsRecyclable(true);
                    recycler.quickRecycleScrapView(scrapView);
                }
            }
            recycler.clearScrap();
            if (scrapCount > 0) {
                this.mRecyclerView.invalidate();
            }
        }
        
        public void removeAndRecycleView(final View view, final Recycler recycler) {
            this.removeView(view);
            recycler.recycleView(view);
        }
        
        public void removeAndRecycleViewAt(final int n, final Recycler recycler) {
            final View child = this.getChildAt(n);
            this.removeViewAt(n);
            recycler.recycleView(child);
        }
        
        public boolean removeCallbacks(final Runnable runnable) {
            return this.mRecyclerView != null && this.mRecyclerView.removeCallbacks(runnable);
        }
        
        public void removeDetachedView(final View view) {
            this.mRecyclerView.removeDetachedView(view, false);
        }
        
        public void removeView(final View view) {
            this.mChildHelper.removeView(view);
        }
        
        public void removeViewAt(final int n) {
            if (this.getChildAt(n) != null) {
                this.mChildHelper.removeViewAt(n);
            }
        }
        
        public boolean requestChildRectangleOnScreen(final RecyclerView recyclerView, final View view, final Rect rect, final boolean b) {
            final int paddingLeft = this.getPaddingLeft();
            final int paddingTop = this.getPaddingTop();
            final int n = this.getWidth() - this.getPaddingRight();
            final int n2 = this.getHeight() - this.getPaddingBottom();
            final int n3 = view.getLeft() + rect.left - view.getScrollX();
            final int n4 = view.getTop() + rect.top - view.getScrollY();
            final int n5 = n3 + rect.width();
            final int n6 = n4 + rect.height();
            final int min = Math.min(0, n3 - paddingLeft);
            final int min2 = Math.min(0, n4 - paddingTop);
            final int max = Math.max(0, n5 - n);
            final int max2 = Math.max(0, n6 - n2);
            int n7;
            if (this.getLayoutDirection() == 1) {
                if (max != 0) {
                    n7 = max;
                }
                else {
                    n7 = Math.max(min, n5 - n);
                }
            }
            else if (min != 0) {
                n7 = min;
            }
            else {
                n7 = Math.min(n3 - paddingLeft, max);
            }
            int min3;
            if (min2 != 0) {
                min3 = min2;
            }
            else {
                min3 = Math.min(n4 - paddingTop, max2);
            }
            boolean b2;
            if (n7 != 0 || min3 != 0) {
                if (b) {
                    recyclerView.scrollBy(n7, min3);
                }
                else {
                    recyclerView.smoothScrollBy(n7, min3);
                }
                b2 = true;
            }
            else {
                b2 = false;
            }
            return b2;
        }
        
        public void requestLayout() {
            if (this.mRecyclerView != null) {
                this.mRecyclerView.requestLayout();
            }
        }
        
        public void requestSimpleAnimationsInNextLayout() {
            this.mRequestedSimpleAnimations = true;
        }
        
        public int scrollHorizontallyBy(final int n, final Recycler recycler, final State state) {
            return 0;
        }
        
        public void scrollToPosition(final int n) {
        }
        
        public int scrollVerticallyBy(final int n, final Recycler recycler, final State state) {
            return 0;
        }
        
        public void setAutoMeasureEnabled(final boolean mAutoMeasure) {
            this.mAutoMeasure = mAutoMeasure;
        }
        
        void setExactMeasureSpecsFrom(final RecyclerView recyclerView) {
            this.setMeasureSpecs(View$MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View$MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
        }
        
        void setMeasureSpecs(final int n, final int n2) {
            this.mWidth = View$MeasureSpec.getSize(n);
            this.mWidthMode = View$MeasureSpec.getMode(n);
            if (this.mWidthMode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.mWidth = 0;
            }
            this.mHeight = View$MeasureSpec.getSize(n2);
            this.mHeightMode = View$MeasureSpec.getMode(n2);
            if (this.mHeightMode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.mHeight = 0;
            }
        }
        
        public void setMeasuredDimension(final int n, final int n2) {
            RecyclerView.access$1000(this.mRecyclerView, n, n2);
        }
        
        public void setMeasuredDimension(final Rect rect, final int n, final int n2) {
            this.setMeasuredDimension(chooseSize(n, rect.width() + this.getPaddingLeft() + this.getPaddingRight(), this.getMinimumWidth()), chooseSize(n2, rect.height() + this.getPaddingTop() + this.getPaddingBottom(), this.getMinimumHeight()));
        }
        
        void setMeasuredDimensionFromChildren(final int n, final int n2) {
            final int childCount = this.getChildCount();
            if (childCount == 0) {
                this.mRecyclerView.defaultOnMeasure(n, n2);
            }
            else {
                int left = Integer.MAX_VALUE;
                int top = Integer.MAX_VALUE;
                int right = Integer.MIN_VALUE;
                int bottom = Integer.MIN_VALUE;
                for (int i = 0; i < childCount; ++i) {
                    final View child = this.getChildAt(i);
                    final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
                    final Rect mTempRect = this.mRecyclerView.mTempRect;
                    this.getDecoratedBoundsWithMargins(child, mTempRect);
                    if (mTempRect.left < left) {
                        left = mTempRect.left;
                    }
                    if (mTempRect.right > right) {
                        right = mTempRect.right;
                    }
                    if (mTempRect.top < top) {
                        top = mTempRect.top;
                    }
                    if (mTempRect.bottom > bottom) {
                        bottom = mTempRect.bottom;
                    }
                }
                this.mRecyclerView.mTempRect.set(left, top, right, bottom);
                this.setMeasuredDimension(this.mRecyclerView.mTempRect, n, n2);
            }
        }
        
        public void setMeasurementCacheEnabled(final boolean mMeasurementCacheEnabled) {
            this.mMeasurementCacheEnabled = mMeasurementCacheEnabled;
        }
        
        void setRecyclerView(final RecyclerView mRecyclerView) {
            if (mRecyclerView == null) {
                this.mRecyclerView = null;
                this.mChildHelper = null;
                this.mWidth = 0;
                this.mHeight = 0;
            }
            else {
                this.mRecyclerView = mRecyclerView;
                this.mChildHelper = mRecyclerView.mChildHelper;
                this.mWidth = mRecyclerView.getWidth();
                this.mHeight = mRecyclerView.getHeight();
            }
            this.mWidthMode = 1073741824;
            this.mHeightMode = 1073741824;
        }
        
        boolean shouldMeasureChild(final View view, final int n, final int n2, final LayoutParams layoutParams) {
            return view.isLayoutRequested() || !this.mMeasurementCacheEnabled || !isMeasurementUpToDate(view.getWidth(), n, layoutParams.width) || !isMeasurementUpToDate(view.getHeight(), n2, layoutParams.height);
        }
        
        boolean shouldMeasureTwice() {
            return false;
        }
        
        boolean shouldReMeasureChild(final View view, final int n, final int n2, final LayoutParams layoutParams) {
            return !this.mMeasurementCacheEnabled || !isMeasurementUpToDate(view.getMeasuredWidth(), n, layoutParams.width) || !isMeasurementUpToDate(view.getMeasuredHeight(), n2, layoutParams.height);
        }
        
        public void smoothScrollToPosition(final RecyclerView recyclerView, final State state, final int n) {
            Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
        }
        
        public void startSmoothScroll(final SmoothScroller mSmoothScroller) {
            if (this.mSmoothScroller != null && mSmoothScroller != this.mSmoothScroller && this.mSmoothScroller.isRunning()) {
                this.mSmoothScroller.stop();
            }
            (this.mSmoothScroller = mSmoothScroller).start(this.mRecyclerView, this);
        }
        
        public void stopIgnoringView(final View view) {
            final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            childViewHolderInt.stopIgnoring();
            childViewHolderInt.resetInternal();
            childViewHolderInt.addFlags(4);
        }
        
        void stopSmoothScroller() {
            if (this.mSmoothScroller != null) {
                this.mSmoothScroller.stop();
            }
        }
        
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
        
        public static class Properties
        {
            public int orientation;
            public boolean reverseLayout;
            public int spanCount;
            public boolean stackFromEnd;
        }
    }
    
    public static class LayoutParams extends ViewGroup$MarginLayoutParams
    {
        final Rect mDecorInsets;
        boolean mInsetsDirty;
        boolean mPendingInvalidate;
        ViewHolder mViewHolder;
        
        public LayoutParams(final int n, final int n2) {
            super(n, n2);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }
        
        public LayoutParams(final Context context, final AttributeSet set) {
            super(context, set);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }
        
        public LayoutParams(final LayoutParams layoutParams) {
            super((ViewGroup$LayoutParams)layoutParams);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }
        
        public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }
        
        public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            super(viewGroup$MarginLayoutParams);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }
        
        public int getViewAdapterPosition() {
            return this.mViewHolder.getAdapterPosition();
        }
        
        public int getViewLayoutPosition() {
            return this.mViewHolder.getLayoutPosition();
        }
        
        @Deprecated
        public int getViewPosition() {
            return this.mViewHolder.getPosition();
        }
        
        public boolean isItemChanged() {
            return this.mViewHolder.isUpdated();
        }
        
        public boolean isItemRemoved() {
            return this.mViewHolder.isRemoved();
        }
        
        public boolean isViewInvalid() {
            return this.mViewHolder.isInvalid();
        }
        
        public boolean viewNeedsUpdate() {
            return this.mViewHolder.needsUpdate();
        }
    }
    
    public interface OnChildAttachStateChangeListener
    {
        void onChildViewAttachedToWindow(final View p0);
        
        void onChildViewDetachedFromWindow(final View p0);
    }
    
    public abstract static class OnFlingListener
    {
        public abstract boolean onFling(final int p0, final int p1);
    }
    
    public interface OnItemTouchListener
    {
        boolean onInterceptTouchEvent(final RecyclerView p0, final MotionEvent p1);
        
        void onRequestDisallowInterceptTouchEvent(final boolean p0);
        
        void onTouchEvent(final RecyclerView p0, final MotionEvent p1);
    }
    
    public abstract static class OnScrollListener
    {
        public void onScrollStateChanged(final RecyclerView recyclerView, final int n) {
        }
        
        public void onScrolled(final RecyclerView recyclerView, final int n, final int n2) {
        }
    }
    
    public static class RecycledViewPool
    {
        private static final int DEFAULT_MAX_SCRAP = 5;
        private int mAttachCount;
        private SparseIntArray mMaxScrap;
        private SparseArray<ArrayList<ViewHolder>> mScrap;
        
        public RecycledViewPool() {
            this.mScrap = (SparseArray<ArrayList<ViewHolder>>)new SparseArray();
            this.mMaxScrap = new SparseIntArray();
            this.mAttachCount = 0;
        }
        
        private ArrayList<ViewHolder> getScrapHeapForType(final int n) {
            ArrayList<ViewHolder> list = (ArrayList<ViewHolder>)this.mScrap.get(n);
            if (list == null) {
                list = new ArrayList<ViewHolder>();
                this.mScrap.put(n, (Object)list);
                if (this.mMaxScrap.indexOfKey(n) < 0) {
                    this.mMaxScrap.put(n, 5);
                }
            }
            return list;
        }
        
        void attach(final Adapter adapter) {
            ++this.mAttachCount;
        }
        
        public void clear() {
            this.mScrap.clear();
        }
        
        void detach() {
            --this.mAttachCount;
        }
        
        public ViewHolder getRecycledView(final int n) {
            final ArrayList list = (ArrayList)this.mScrap.get(n);
            ViewHolder viewHolder;
            if (list != null && !list.isEmpty()) {
                final int n2 = -1 + list.size();
                viewHolder = list.get(n2);
                list.remove(n2);
            }
            else {
                viewHolder = null;
            }
            return viewHolder;
        }
        
        void onAdapterChanged(final Adapter adapter, final Adapter adapter2, final boolean b) {
            if (adapter != null) {
                this.detach();
            }
            if (!b && this.mAttachCount == 0) {
                this.clear();
            }
            if (adapter2 != null) {
                this.attach(adapter2);
            }
        }
        
        public void putRecycledView(final ViewHolder viewHolder) {
            final int itemViewType = viewHolder.getItemViewType();
            final ArrayList<ViewHolder> scrapHeapForType = this.getScrapHeapForType(itemViewType);
            if (this.mMaxScrap.get(itemViewType) > scrapHeapForType.size()) {
                viewHolder.resetInternal();
                scrapHeapForType.add(viewHolder);
            }
        }
        
        public void setMaxRecycledViews(final int n, final int n2) {
            this.mMaxScrap.put(n, n2);
            final ArrayList list = (ArrayList)this.mScrap.get(n);
            if (list != null) {
                while (list.size() > n2) {
                    list.remove(-1 + list.size());
                }
            }
        }
        
        int size() {
            int n = 0;
            for (int i = 0; i < this.mScrap.size(); ++i) {
                final ArrayList list = (ArrayList)this.mScrap.valueAt(i);
                if (list != null) {
                    n += list.size();
                }
            }
            return n;
        }
    }
    
    public final class Recycler
    {
        private static final int DEFAULT_CACHE_SIZE = 2;
        final ArrayList<ViewHolder> mAttachedScrap;
        final ArrayList<ViewHolder> mCachedViews;
        ArrayList<ViewHolder> mChangedScrap;
        private RecycledViewPool mRecyclerPool;
        private final List<ViewHolder> mUnmodifiableAttachedScrap;
        private ViewCacheExtension mViewCacheExtension;
        private int mViewCacheMax;
        
        public Recycler() {
            this.mAttachedScrap = new ArrayList<ViewHolder>();
            this.mChangedScrap = null;
            this.mCachedViews = new ArrayList<ViewHolder>();
            this.mUnmodifiableAttachedScrap = Collections.unmodifiableList((List<? extends ViewHolder>)this.mAttachedScrap);
            this.mViewCacheMax = 2;
        }
        
        private void attachAccessibilityDelegate(final View view) {
            if (RecyclerView.this.isAccessibilityEnabled()) {
                if (ViewCompat.getImportantForAccessibility(view) == 0) {
                    ViewCompat.setImportantForAccessibility(view, 1);
                }
                if (!ViewCompat.hasAccessibilityDelegate(view)) {
                    ViewCompat.setAccessibilityDelegate(view, RecyclerView.this.mAccessibilityDelegate.getItemDelegate());
                }
            }
        }
        
        private void invalidateDisplayListInt(final ViewHolder viewHolder) {
            if (viewHolder.itemView instanceof ViewGroup) {
                this.invalidateDisplayListInt((ViewGroup)viewHolder.itemView, false);
            }
        }
        
        private void invalidateDisplayListInt(final ViewGroup viewGroup, final boolean b) {
            for (int i = -1 + viewGroup.getChildCount(); i >= 0; --i) {
                final View child = viewGroup.getChildAt(i);
                if (child instanceof ViewGroup) {
                    this.invalidateDisplayListInt((ViewGroup)child, true);
                }
            }
            if (b) {
                if (viewGroup.getVisibility() == 4) {
                    viewGroup.setVisibility(0);
                    viewGroup.setVisibility(4);
                }
                else {
                    final int visibility = viewGroup.getVisibility();
                    viewGroup.setVisibility(4);
                    viewGroup.setVisibility(visibility);
                }
            }
        }
        
        void addViewHolderToRecycledViewPool(final ViewHolder viewHolder) {
            ViewCompat.setAccessibilityDelegate(viewHolder.itemView, null);
            this.dispatchViewRecycled(viewHolder);
            viewHolder.mOwnerRecyclerView = null;
            this.getRecycledViewPool().putRecycledView(viewHolder);
        }
        
        public void bindViewToPosition(final View view, final int mPreLayoutPosition) {
            boolean b = true;
            final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt == null) {
                throw new IllegalArgumentException("The view does not have a ViewHolder. You cannot pass arbitrary views to this method, they should be created by the Adapter");
            }
            final int positionOffset = RecyclerView.this.mAdapterHelper.findPositionOffset(mPreLayoutPosition);
            if (positionOffset < 0 || positionOffset >= RecyclerView.this.mAdapter.getItemCount()) {
                throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + mPreLayoutPosition + "(offset:" + positionOffset + ")." + "state:" + RecyclerView.this.mState.getItemCount());
            }
            childViewHolderInt.mOwnerRecyclerView = RecyclerView.this;
            RecyclerView.this.mAdapter.bindViewHolder(childViewHolderInt, positionOffset);
            this.attachAccessibilityDelegate(view);
            if (RecyclerView.this.mState.isPreLayout()) {
                childViewHolderInt.mPreLayoutPosition = mPreLayoutPosition;
            }
            final ViewGroup$LayoutParams layoutParams = childViewHolderInt.itemView.getLayoutParams();
            LayoutParams layoutParams2;
            if (layoutParams == null) {
                layoutParams2 = (LayoutParams)RecyclerView.this.generateDefaultLayoutParams();
                childViewHolderInt.itemView.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
            }
            else if (!RecyclerView.this.checkLayoutParams(layoutParams)) {
                layoutParams2 = (LayoutParams)RecyclerView.this.generateLayoutParams(layoutParams);
                childViewHolderInt.itemView.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
            }
            else {
                layoutParams2 = (LayoutParams)layoutParams;
            }
            layoutParams2.mInsetsDirty = b;
            layoutParams2.mViewHolder = childViewHolderInt;
            if (childViewHolderInt.itemView.getParent() != null) {
                b = false;
            }
            layoutParams2.mPendingInvalidate = b;
        }
        
        public void clear() {
            this.mAttachedScrap.clear();
            this.recycleAndClearCachedViews();
        }
        
        void clearOldPositions() {
            for (int size = this.mCachedViews.size(), i = 0; i < size; ++i) {
                this.mCachedViews.get(i).clearOldPosition();
            }
            for (int size2 = this.mAttachedScrap.size(), j = 0; j < size2; ++j) {
                this.mAttachedScrap.get(j).clearOldPosition();
            }
            if (this.mChangedScrap != null) {
                for (int size3 = this.mChangedScrap.size(), k = 0; k < size3; ++k) {
                    this.mChangedScrap.get(k).clearOldPosition();
                }
            }
        }
        
        void clearScrap() {
            this.mAttachedScrap.clear();
            if (this.mChangedScrap != null) {
                this.mChangedScrap.clear();
            }
        }
        
        public int convertPreLayoutPositionToPostLayout(int positionOffset) {
            if (positionOffset < 0 || positionOffset >= RecyclerView.this.mState.getItemCount()) {
                throw new IndexOutOfBoundsException("invalid position " + positionOffset + ". State " + "item count is " + RecyclerView.this.mState.getItemCount());
            }
            if (RecyclerView.this.mState.isPreLayout()) {
                positionOffset = RecyclerView.this.mAdapterHelper.findPositionOffset(positionOffset);
            }
            return positionOffset;
        }
        
        void dispatchViewRecycled(final ViewHolder viewHolder) {
            if (RecyclerView.this.mRecyclerListener != null) {
                RecyclerView.this.mRecyclerListener.onViewRecycled(viewHolder);
            }
            if (RecyclerView.this.mAdapter != null) {
                RecyclerView.this.mAdapter.onViewRecycled(viewHolder);
            }
            if (RecyclerView.this.mState != null) {
                RecyclerView.this.mViewInfoStore.removeViewHolder(viewHolder);
            }
        }
        
        ViewHolder getChangedScrapViewForPosition(final int n) {
            if (this.mChangedScrap != null) {
                final int size = this.mChangedScrap.size();
                if (size != 0) {
                    for (int i = 0; i < size; ++i) {
                        final Object o = this.mChangedScrap.get(i);
                        if (!((ViewHolder)o).wasReturnedFromScrap() && ((ViewHolder)o).getLayoutPosition() == n) {
                            ((ViewHolder)o).addFlags(32);
                            return (ViewHolder)o;
                        }
                    }
                    if (RecyclerView.this.mAdapter.hasStableIds()) {
                        final int positionOffset = RecyclerView.this.mAdapterHelper.findPositionOffset(n);
                        if (positionOffset > 0 && positionOffset < RecyclerView.this.mAdapter.getItemCount()) {
                            final long itemId = RecyclerView.this.mAdapter.getItemId(positionOffset);
                            for (int j = 0; j < size; ++j) {
                                final Object o = this.mChangedScrap.get(j);
                                if (!((ViewHolder)o).wasReturnedFromScrap() && ((ViewHolder)o).getItemId() == itemId) {
                                    ((ViewHolder)o).addFlags(32);
                                    return (ViewHolder)o;
                                }
                            }
                        }
                    }
                    final Object o = null;
                    return (ViewHolder)o;
                }
            }
            final Object o = null;
            return (ViewHolder)o;
        }
        
        RecycledViewPool getRecycledViewPool() {
            if (this.mRecyclerPool == null) {
                this.mRecyclerPool = new RecycledViewPool();
            }
            return this.mRecyclerPool;
        }
        
        int getScrapCount() {
            return this.mAttachedScrap.size();
        }
        
        public List<ViewHolder> getScrapList() {
            return this.mUnmodifiableAttachedScrap;
        }
        
        View getScrapViewAt(final int n) {
            return this.mAttachedScrap.get(n).itemView;
        }
        
        ViewHolder getScrapViewForId(final long n, final int n2, final boolean b) {
            for (int i = -1 + this.mAttachedScrap.size(); i >= 0; --i) {
                final ViewHolder viewHolder = this.mAttachedScrap.get(i);
                if (viewHolder.getItemId() == n && !viewHolder.wasReturnedFromScrap()) {
                    if (n2 == viewHolder.getItemViewType()) {
                        viewHolder.addFlags(32);
                        if (viewHolder.isRemoved() && !RecyclerView.this.mState.isPreLayout()) {
                            viewHolder.setFlags(2, 14);
                        }
                        return viewHolder;
                    }
                    if (!b) {
                        this.mAttachedScrap.remove(i);
                        RecyclerView.this.removeDetachedView(viewHolder.itemView, false);
                        this.quickRecycleScrapView(viewHolder.itemView);
                    }
                }
            }
            for (int j = -1 + this.mCachedViews.size(); j >= 0; --j) {
                final ViewHolder viewHolder = this.mCachedViews.get(j);
                if (viewHolder.getItemId() == n) {
                    if (n2 == viewHolder.getItemViewType()) {
                        if (!b) {
                            this.mCachedViews.remove(j);
                            return viewHolder;
                        }
                        return viewHolder;
                    }
                    else if (!b) {
                        this.recycleCachedViewAt(j);
                    }
                }
            }
            return null;
        }
        
        ViewHolder getScrapViewForPosition(final int n, final int n2, final boolean b) {
            final int size = this.mAttachedScrap.size();
            int i = 0;
            while (i < size) {
                final ViewHolder viewHolder = this.mAttachedScrap.get(i);
                if (!viewHolder.wasReturnedFromScrap() && viewHolder.getLayoutPosition() == n && !viewHolder.isInvalid() && (RecyclerView.this.mState.mInPreLayout || !viewHolder.isRemoved())) {
                    if (n2 != -1 && viewHolder.getItemViewType() != n2) {
                        Log.e("RecyclerView", "Scrap view for position " + n + " isn't dirty but has" + " wrong view type! (found " + viewHolder.getItemViewType() + " but expected " + n2 + ")");
                        break;
                    }
                    viewHolder.addFlags(32);
                    return viewHolder;
                }
                else {
                    ++i;
                }
            }
            if (!b) {
                final View hiddenNonRemovedView = RecyclerView.this.mChildHelper.findHiddenNonRemovedView(n, n2);
                if (hiddenNonRemovedView != null) {
                    final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(hiddenNonRemovedView);
                    RecyclerView.this.mChildHelper.unhide(hiddenNonRemovedView);
                    final int indexOfChild = RecyclerView.this.mChildHelper.indexOfChild(hiddenNonRemovedView);
                    if (indexOfChild == -1) {
                        throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + childViewHolderInt);
                    }
                    RecyclerView.this.mChildHelper.detachViewFromParent(indexOfChild);
                    this.scrapView(hiddenNonRemovedView);
                    childViewHolderInt.addFlags(8224);
                    return childViewHolderInt;
                }
            }
            final int size2 = this.mCachedViews.size();
            int j = 0;
            while (j < size2) {
                final ViewHolder viewHolder = this.mCachedViews.get(j);
                if (!viewHolder.isInvalid() && viewHolder.getLayoutPosition() == n) {
                    if (!b) {
                        this.mCachedViews.remove(j);
                        return viewHolder;
                    }
                    return viewHolder;
                }
                else {
                    ++j;
                }
            }
            return null;
        }
        
        public View getViewForPosition(final int n) {
            return this.getViewForPosition(n, false);
        }
        
        View getViewForPosition(final int n, final boolean b) {
            if (n < 0 || n >= RecyclerView.this.mState.getItemCount()) {
                throw new IndexOutOfBoundsException("Invalid item position " + n + "(" + n + "). Item count:" + RecyclerView.this.mState.getItemCount());
            }
            final boolean preLayout = RecyclerView.this.mState.isPreLayout();
            boolean b2 = false;
            Object mViewHolder = null;
            if (preLayout) {
                mViewHolder = this.getChangedScrapViewForPosition(n);
                b2 = (mViewHolder != null);
            }
            if (mViewHolder == null) {
                mViewHolder = this.getScrapViewForPosition(n, -1, b);
                if (mViewHolder != null) {
                    if (!this.validateViewHolderForOffsetPosition((ViewHolder)mViewHolder)) {
                        if (!b) {
                            ((ViewHolder)mViewHolder).addFlags(4);
                            if (((ViewHolder)mViewHolder).isScrap()) {
                                RecyclerView.this.removeDetachedView(((ViewHolder)mViewHolder).itemView, false);
                                ((ViewHolder)mViewHolder).unScrap();
                            }
                            else if (((ViewHolder)mViewHolder).wasReturnedFromScrap()) {
                                ((ViewHolder)mViewHolder).clearReturnedFromScrapFlag();
                            }
                            this.recycleViewHolderInternal((ViewHolder)mViewHolder);
                        }
                        mViewHolder = null;
                    }
                    else {
                        b2 = true;
                    }
                }
            }
            if (mViewHolder == null) {
                final int positionOffset = RecyclerView.this.mAdapterHelper.findPositionOffset(n);
                if (positionOffset < 0 || positionOffset >= RecyclerView.this.mAdapter.getItemCount()) {
                    throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + n + "(offset:" + positionOffset + ")." + "state:" + RecyclerView.this.mState.getItemCount());
                }
                final int itemViewType = RecyclerView.this.mAdapter.getItemViewType(positionOffset);
                if (RecyclerView.this.mAdapter.hasStableIds()) {
                    mViewHolder = this.getScrapViewForId(RecyclerView.this.mAdapter.getItemId(positionOffset), itemViewType, b);
                    if (mViewHolder != null) {
                        ((ViewHolder)mViewHolder).mPosition = positionOffset;
                        b2 = true;
                    }
                }
                if (mViewHolder == null && this.mViewCacheExtension != null) {
                    final View viewForPositionAndType = this.mViewCacheExtension.getViewForPositionAndType(this, n, itemViewType);
                    if (viewForPositionAndType != null) {
                        mViewHolder = RecyclerView.this.getChildViewHolder(viewForPositionAndType);
                        if (mViewHolder == null) {
                            throw new IllegalArgumentException("getViewForPositionAndType returned a view which does not have a ViewHolder");
                        }
                        if (((ViewHolder)mViewHolder).shouldIgnore()) {
                            throw new IllegalArgumentException("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view.");
                        }
                    }
                }
                if (mViewHolder == null) {
                    mViewHolder = this.getRecycledViewPool().getRecycledView(itemViewType);
                    if (mViewHolder != null) {
                        ((ViewHolder)mViewHolder).resetInternal();
                        if (RecyclerView.FORCE_INVALIDATE_DISPLAY_LIST) {
                            this.invalidateDisplayListInt((ViewHolder)mViewHolder);
                        }
                    }
                }
                if (mViewHolder == null) {
                    mViewHolder = RecyclerView.this.mAdapter.createViewHolder(RecyclerView.this, itemViewType);
                }
            }
            if (b2 && !RecyclerView.this.mState.isPreLayout() && ((ViewHolder)mViewHolder).hasAnyOfTheFlags(8192)) {
                ((ViewHolder)mViewHolder).setFlags(0, 8192);
                if (RecyclerView.this.mState.mRunSimpleAnimations) {
                    RecyclerView.this.recordAnimationInfoIfBouncedHiddenView((ViewHolder)mViewHolder, RecyclerView.this.mItemAnimator.recordPreLayoutInformation(RecyclerView.this.mState, (ViewHolder)mViewHolder, 0x1000 | ItemAnimator.buildAdapterChangeFlagsForAnimations((ViewHolder)mViewHolder), ((ViewHolder)mViewHolder).getUnmodifiedPayloads()));
                }
            }
            int n2 = 0;
            Label_0641: {
                if (RecyclerView.this.mState.isPreLayout() && ((ViewHolder)mViewHolder).isBound()) {
                    ((ViewHolder)mViewHolder).mPreLayoutPosition = n;
                }
                else {
                    if (((ViewHolder)mViewHolder).isBound() && !((ViewHolder)mViewHolder).needsUpdate()) {
                        final boolean invalid = ((ViewHolder)mViewHolder).isInvalid();
                        n2 = 0;
                        if (!invalid) {
                            break Label_0641;
                        }
                    }
                    final int positionOffset2 = RecyclerView.this.mAdapterHelper.findPositionOffset(n);
                    ((ViewHolder)mViewHolder).mOwnerRecyclerView = RecyclerView.this;
                    RecyclerView.this.mAdapter.bindViewHolder((ViewHolder)mViewHolder, positionOffset2);
                    this.attachAccessibilityDelegate(((ViewHolder)mViewHolder).itemView);
                    n2 = 1;
                    if (RecyclerView.this.mState.isPreLayout()) {
                        ((ViewHolder)mViewHolder).mPreLayoutPosition = n;
                    }
                }
            }
            final ViewGroup$LayoutParams layoutParams = ((ViewHolder)mViewHolder).itemView.getLayoutParams();
            LayoutParams layoutParams2;
            if (layoutParams == null) {
                layoutParams2 = (LayoutParams)RecyclerView.this.generateDefaultLayoutParams();
                ((ViewHolder)mViewHolder).itemView.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
            }
            else if (!RecyclerView.this.checkLayoutParams(layoutParams)) {
                layoutParams2 = (LayoutParams)RecyclerView.this.generateLayoutParams(layoutParams);
                ((ViewHolder)mViewHolder).itemView.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
            }
            else {
                layoutParams2 = (LayoutParams)layoutParams;
            }
            layoutParams2.mViewHolder = (ViewHolder)mViewHolder;
            layoutParams2.mPendingInvalidate = (b2 && n2 != 0);
            return ((ViewHolder)mViewHolder).itemView;
        }
        
        void markItemDecorInsetsDirty() {
            for (int size = this.mCachedViews.size(), i = 0; i < size; ++i) {
                final LayoutParams layoutParams = (LayoutParams)this.mCachedViews.get(i).itemView.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.mInsetsDirty = true;
                }
            }
        }
        
        void markKnownViewsInvalid() {
            if (RecyclerView.this.mAdapter != null && RecyclerView.this.mAdapter.hasStableIds()) {
                for (int size = this.mCachedViews.size(), i = 0; i < size; ++i) {
                    final ViewHolder viewHolder = this.mCachedViews.get(i);
                    if (viewHolder != null) {
                        viewHolder.addFlags(6);
                        viewHolder.addChangePayload(null);
                    }
                }
            }
            else {
                this.recycleAndClearCachedViews();
            }
        }
        
        void offsetPositionRecordsForInsert(final int n, final int n2) {
            for (int size = this.mCachedViews.size(), i = 0; i < size; ++i) {
                final ViewHolder viewHolder = this.mCachedViews.get(i);
                if (viewHolder != null && viewHolder.mPosition >= n) {
                    viewHolder.offsetPosition(n2, true);
                }
            }
        }
        
        void offsetPositionRecordsForMove(final int n, final int n2) {
            int n3;
            int n4;
            int n5;
            if (n < n2) {
                n3 = n;
                n4 = n2;
                n5 = -1;
            }
            else {
                n3 = n2;
                n4 = n;
                n5 = 1;
            }
            for (int size = this.mCachedViews.size(), i = 0; i < size; ++i) {
                final ViewHolder viewHolder = this.mCachedViews.get(i);
                if (viewHolder != null && viewHolder.mPosition >= n3 && viewHolder.mPosition <= n4) {
                    if (viewHolder.mPosition == n) {
                        viewHolder.offsetPosition(n2 - n, false);
                    }
                    else {
                        viewHolder.offsetPosition(n5, false);
                    }
                }
            }
        }
        
        void offsetPositionRecordsForRemove(final int n, final int n2, final boolean b) {
            final int n3 = n + n2;
            for (int i = -1 + this.mCachedViews.size(); i >= 0; --i) {
                final ViewHolder viewHolder = this.mCachedViews.get(i);
                if (viewHolder != null) {
                    if (viewHolder.mPosition >= n3) {
                        viewHolder.offsetPosition(-n2, b);
                    }
                    else if (viewHolder.mPosition >= n) {
                        viewHolder.addFlags(8);
                        this.recycleCachedViewAt(i);
                    }
                }
            }
        }
        
        void onAdapterChanged(final Adapter adapter, final Adapter adapter2, final boolean b) {
            this.clear();
            this.getRecycledViewPool().onAdapterChanged(adapter, adapter2, b);
        }
        
        void quickRecycleScrapView(final View view) {
            final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            childViewHolderInt.mScrapContainer = null;
            childViewHolderInt.mInChangeScrap = false;
            childViewHolderInt.clearReturnedFromScrapFlag();
            this.recycleViewHolderInternal(childViewHolderInt);
        }
        
        void recycleAndClearCachedViews() {
            for (int i = -1 + this.mCachedViews.size(); i >= 0; --i) {
                this.recycleCachedViewAt(i);
            }
            this.mCachedViews.clear();
        }
        
        void recycleCachedViewAt(final int n) {
            this.addViewHolderToRecycledViewPool(this.mCachedViews.get(n));
            this.mCachedViews.remove(n);
        }
        
        public void recycleView(final View view) {
            final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(view, false);
            }
            if (childViewHolderInt.isScrap()) {
                childViewHolderInt.unScrap();
            }
            else if (childViewHolderInt.wasReturnedFromScrap()) {
                childViewHolderInt.clearReturnedFromScrapFlag();
            }
            this.recycleViewHolderInternal(childViewHolderInt);
        }
        
        void recycleViewHolderInternal(final ViewHolder viewHolder) {
            boolean b = true;
            if (viewHolder.isScrap() || viewHolder.itemView.getParent() != null) {
                final StringBuilder append = new StringBuilder().append("Scrapped or attached views may not be recycled. isScrap:").append(viewHolder.isScrap()).append(" isAttached:");
                if (viewHolder.itemView.getParent() == null) {
                    b = false;
                }
                throw new IllegalArgumentException(append.append(b).toString());
            }
            if (viewHolder.isTmpDetached()) {
                throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + viewHolder);
            }
            if (viewHolder.shouldIgnore()) {
                throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.");
            }
            final boolean access$700 = viewHolder.doesTransientStatePreventRecycling();
            boolean b2 = false;
            boolean b3 = false;
            Label_0281: {
                if (RecyclerView.this.mAdapter == null || !access$700 || !RecyclerView.this.mAdapter.onFailedToRecycleView(viewHolder) || !b) {
                    final boolean recyclable = viewHolder.isRecyclable();
                    b2 = false;
                    b3 = false;
                    if (!recyclable) {
                        break Label_0281;
                    }
                }
                final boolean hasAnyOfTheFlags = viewHolder.hasAnyOfTheFlags(14);
                b2 = false;
                if (!hasAnyOfTheFlags) {
                    int size = this.mCachedViews.size();
                    if (size >= this.mViewCacheMax && size > 0) {
                        this.recycleCachedViewAt(0);
                        --size;
                    }
                    final int mViewCacheMax = this.mViewCacheMax;
                    b2 = false;
                    if (size < mViewCacheMax) {
                        this.mCachedViews.add(viewHolder);
                        b2 = true;
                    }
                }
                b3 = false;
                if (!b2) {
                    this.addViewHolderToRecycledViewPool(viewHolder);
                    b3 = true;
                }
            }
            RecyclerView.this.mViewInfoStore.removeViewHolder(viewHolder);
            if (!b2 && !b3 && access$700) {
                viewHolder.mOwnerRecyclerView = null;
            }
        }
        
        void recycleViewInternal(final View view) {
            this.recycleViewHolderInternal(RecyclerView.getChildViewHolderInt(view));
        }
        
        void scrapView(final View view) {
            final ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.hasAnyOfTheFlags(12) || !childViewHolderInt.isUpdated() || RecyclerView.this.canReuseUpdatedViewHolder(childViewHolderInt)) {
                if (childViewHolderInt.isInvalid() && !childViewHolderInt.isRemoved() && !RecyclerView.this.mAdapter.hasStableIds()) {
                    throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.");
                }
                childViewHolderInt.setScrapContainer(this, false);
                this.mAttachedScrap.add(childViewHolderInt);
            }
            else {
                if (this.mChangedScrap == null) {
                    this.mChangedScrap = new ArrayList<ViewHolder>();
                }
                childViewHolderInt.setScrapContainer(this, true);
                this.mChangedScrap.add(childViewHolderInt);
            }
        }
        
        void setAdapterPositionsAsUnknown() {
            for (int size = this.mCachedViews.size(), i = 0; i < size; ++i) {
                final ViewHolder viewHolder = this.mCachedViews.get(i);
                if (viewHolder != null) {
                    viewHolder.addFlags(512);
                }
            }
        }
        
        void setRecycledViewPool(final RecycledViewPool mRecyclerPool) {
            if (this.mRecyclerPool != null) {
                this.mRecyclerPool.detach();
            }
            if ((this.mRecyclerPool = mRecyclerPool) != null) {
                this.mRecyclerPool.attach(RecyclerView.this.getAdapter());
            }
        }
        
        void setViewCacheExtension(final ViewCacheExtension mViewCacheExtension) {
            this.mViewCacheExtension = mViewCacheExtension;
        }
        
        public void setViewCacheSize(final int mViewCacheMax) {
            this.mViewCacheMax = mViewCacheMax;
            for (int n = -1 + this.mCachedViews.size(); n >= 0 && this.mCachedViews.size() > mViewCacheMax; --n) {
                this.recycleCachedViewAt(n);
            }
        }
        
        void unscrapView(final ViewHolder viewHolder) {
            if (viewHolder.mInChangeScrap) {
                this.mChangedScrap.remove(viewHolder);
            }
            else {
                this.mAttachedScrap.remove(viewHolder);
            }
            viewHolder.mScrapContainer = null;
            viewHolder.mInChangeScrap = false;
            viewHolder.clearReturnedFromScrapFlag();
        }
        
        boolean validateViewHolderForOffsetPosition(final ViewHolder viewHolder) {
            boolean preLayout = true;
            if (viewHolder.isRemoved()) {
                preLayout = RecyclerView.this.mState.isPreLayout();
            }
            else {
                if (viewHolder.mPosition < 0 || viewHolder.mPosition >= RecyclerView.this.mAdapter.getItemCount()) {
                    throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + viewHolder);
                }
                if (!RecyclerView.this.mState.isPreLayout() && RecyclerView.this.mAdapter.getItemViewType(viewHolder.mPosition) != viewHolder.getItemViewType()) {
                    preLayout = false;
                }
                else if (RecyclerView.this.mAdapter.hasStableIds() && viewHolder.getItemId() != RecyclerView.this.mAdapter.getItemId(viewHolder.mPosition)) {
                    preLayout = false;
                }
            }
            return preLayout;
        }
        
        void viewRangeUpdate(final int n, final int n2) {
            final int n3 = n + n2;
            for (int i = -1 + this.mCachedViews.size(); i >= 0; --i) {
                final ViewHolder viewHolder = this.mCachedViews.get(i);
                if (viewHolder != null) {
                    final int layoutPosition = viewHolder.getLayoutPosition();
                    if (layoutPosition >= n && layoutPosition < n3) {
                        viewHolder.addFlags(2);
                        this.recycleCachedViewAt(i);
                    }
                }
            }
        }
    }
    
    public interface RecyclerListener
    {
        void onViewRecycled(final ViewHolder p0);
    }
    
    private class RecyclerViewDataObserver extends AdapterDataObserver
    {
        @Override
        public void onChanged() {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if (RecyclerView.this.mAdapter.hasStableIds()) {
                RecyclerView.this.mState.mStructureChanged = true;
                RecyclerView.this.setDataSetChangedAfterLayout();
            }
            else {
                RecyclerView.this.mState.mStructureChanged = true;
                RecyclerView.this.setDataSetChangedAfterLayout();
            }
            if (!RecyclerView.this.mAdapterHelper.hasPendingUpdates()) {
                RecyclerView.this.requestLayout();
            }
        }
        
        @Override
        public void onItemRangeChanged(final int n, final int n2, final Object o) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if (RecyclerView.this.mAdapterHelper.onItemRangeChanged(n, n2, o)) {
                this.triggerUpdateProcessor();
            }
        }
        
        @Override
        public void onItemRangeInserted(final int n, final int n2) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if (RecyclerView.this.mAdapterHelper.onItemRangeInserted(n, n2)) {
                this.triggerUpdateProcessor();
            }
        }
        
        @Override
        public void onItemRangeMoved(final int n, final int n2, final int n3) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if (RecyclerView.this.mAdapterHelper.onItemRangeMoved(n, n2, n3)) {
                this.triggerUpdateProcessor();
            }
        }
        
        @Override
        public void onItemRangeRemoved(final int n, final int n2) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if (RecyclerView.this.mAdapterHelper.onItemRangeRemoved(n, n2)) {
                this.triggerUpdateProcessor();
            }
        }
        
        void triggerUpdateProcessor() {
            if (RecyclerView.this.mPostUpdatesOnAnimation && RecyclerView.this.mHasFixedSize && RecyclerView.this.mIsAttached) {
                ViewCompat.postOnAnimation((View)RecyclerView.this, RecyclerView.this.mUpdateChildViewsRunnable);
            }
            else {
                RecyclerView.this.mAdapterUpdateDuringMeasure = true;
                RecyclerView.this.requestLayout();
            }
        }
    }
    
    public static class SavedState extends AbsSavedState
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        Parcelable mLayoutState;
        
        static {
            CREATOR = ParcelableCompat.newCreator((ParcelableCompatCreatorCallbacks<SavedState>)new ParcelableCompatCreatorCallbacks<SavedState>() {
                @Override
                public SavedState createFromParcel(final Parcel parcel, final ClassLoader classLoader) {
                    return new SavedState(parcel, classLoader);
                }
                
                @Override
                public SavedState[] newArray(final int n) {
                    return new SavedState[n];
                }
            });
        }
        
        SavedState(final Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            if (classLoader == null) {
                classLoader = LayoutManager.class.getClassLoader();
            }
            this.mLayoutState = parcel.readParcelable(classLoader);
        }
        
        SavedState(final Parcelable parcelable) {
            super(parcelable);
        }
        
        void copyFrom(final SavedState savedState) {
            this.mLayoutState = savedState.mLayoutState;
        }
        
        @Override
        public void writeToParcel(final Parcel parcel, final int n) {
            super.writeToParcel(parcel, n);
            parcel.writeParcelable(this.mLayoutState, 0);
        }
    }
    
    public static class SimpleOnItemTouchListener implements OnItemTouchListener
    {
        @Override
        public boolean onInterceptTouchEvent(final RecyclerView recyclerView, final MotionEvent motionEvent) {
            return false;
        }
        
        @Override
        public void onRequestDisallowInterceptTouchEvent(final boolean b) {
        }
        
        @Override
        public void onTouchEvent(final RecyclerView recyclerView, final MotionEvent motionEvent) {
        }
    }
    
    public abstract static class SmoothScroller
    {
        private LayoutManager mLayoutManager;
        private boolean mPendingInitialRun;
        private RecyclerView mRecyclerView;
        private final Action mRecyclingAction;
        private boolean mRunning;
        private int mTargetPosition;
        private View mTargetView;
        
        public SmoothScroller() {
            this.mTargetPosition = -1;
            this.mRecyclingAction = new Action(0, 0);
        }
        
        private void onAnimation(final int n, final int n2) {
            final RecyclerView mRecyclerView = this.mRecyclerView;
            if (!this.mRunning || this.mTargetPosition == -1 || mRecyclerView == null) {
                this.stop();
            }
            this.mPendingInitialRun = false;
            if (this.mTargetView != null) {
                if (this.getChildPosition(this.mTargetView) == this.mTargetPosition) {
                    this.onTargetFound(this.mTargetView, mRecyclerView.mState, this.mRecyclingAction);
                    this.mRecyclingAction.runIfNecessary(mRecyclerView);
                    this.stop();
                }
                else {
                    Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                    this.mTargetView = null;
                }
            }
            if (this.mRunning) {
                this.onSeekTargetStep(n, n2, mRecyclerView.mState, this.mRecyclingAction);
                final boolean hasJumpTarget = this.mRecyclingAction.hasJumpTarget();
                this.mRecyclingAction.runIfNecessary(mRecyclerView);
                if (hasJumpTarget) {
                    if (this.mRunning) {
                        this.mPendingInitialRun = true;
                        mRecyclerView.mViewFlinger.postOnAnimation();
                    }
                    else {
                        this.stop();
                    }
                }
            }
        }
        
        public View findViewByPosition(final int n) {
            return this.mRecyclerView.mLayout.findViewByPosition(n);
        }
        
        public int getChildCount() {
            return this.mRecyclerView.mLayout.getChildCount();
        }
        
        public int getChildPosition(final View view) {
            return this.mRecyclerView.getChildLayoutPosition(view);
        }
        
        @Nullable
        public LayoutManager getLayoutManager() {
            return this.mLayoutManager;
        }
        
        public int getTargetPosition() {
            return this.mTargetPosition;
        }
        
        @Deprecated
        public void instantScrollToPosition(final int n) {
            this.mRecyclerView.scrollToPosition(n);
        }
        
        public boolean isPendingInitialRun() {
            return this.mPendingInitialRun;
        }
        
        public boolean isRunning() {
            return this.mRunning;
        }
        
        protected void normalize(final PointF pointF) {
            final double sqrt = Math.sqrt(pointF.x * pointF.x + pointF.y * pointF.y);
            pointF.x /= (float)sqrt;
            pointF.y /= (float)sqrt;
        }
        
        protected void onChildAttachedToWindow(final View mTargetView) {
            if (this.getChildPosition(mTargetView) == this.getTargetPosition()) {
                this.mTargetView = mTargetView;
            }
        }
        
        protected abstract void onSeekTargetStep(final int p0, final int p1, final State p2, final Action p3);
        
        protected abstract void onStart();
        
        protected abstract void onStop();
        
        protected abstract void onTargetFound(final View p0, final State p1, final Action p2);
        
        public void setTargetPosition(final int mTargetPosition) {
            this.mTargetPosition = mTargetPosition;
        }
        
        void start(final RecyclerView mRecyclerView, final LayoutManager mLayoutManager) {
            this.mRecyclerView = mRecyclerView;
            this.mLayoutManager = mLayoutManager;
            if (this.mTargetPosition == -1) {
                throw new IllegalArgumentException("Invalid target position");
            }
            this.mRecyclerView.mState.mTargetPosition = this.mTargetPosition;
            this.mRunning = true;
            this.mPendingInitialRun = true;
            this.mTargetView = this.findViewByPosition(this.getTargetPosition());
            this.onStart();
            this.mRecyclerView.mViewFlinger.postOnAnimation();
        }
        
        protected final void stop() {
            if (this.mRunning) {
                this.onStop();
                this.mRecyclerView.mState.mTargetPosition = -1;
                this.mTargetView = null;
                this.mTargetPosition = -1;
                this.mPendingInitialRun = false;
                this.mRunning = false;
                this.mLayoutManager.onSmoothScrollerStopped(this);
                this.mLayoutManager = null;
                this.mRecyclerView = null;
            }
        }
        
        public static class Action
        {
            public static final int UNDEFINED_DURATION = Integer.MIN_VALUE;
            private boolean changed;
            private int consecutiveUpdates;
            private int mDuration;
            private int mDx;
            private int mDy;
            private Interpolator mInterpolator;
            private int mJumpToPosition;
            
            public Action(final int n, final int n2) {
                this(n, n2, Integer.MIN_VALUE, null);
            }
            
            public Action(final int n, final int n2, final int n3) {
                this(n, n2, n3, null);
            }
            
            public Action(final int mDx, final int mDy, final int mDuration, final Interpolator mInterpolator) {
                this.mJumpToPosition = -1;
                this.changed = false;
                this.consecutiveUpdates = 0;
                this.mDx = mDx;
                this.mDy = mDy;
                this.mDuration = mDuration;
                this.mInterpolator = mInterpolator;
            }
            
            private void validate() {
                if (this.mInterpolator != null && this.mDuration < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                }
                if (this.mDuration < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            }
            
            public int getDuration() {
                return this.mDuration;
            }
            
            public int getDx() {
                return this.mDx;
            }
            
            public int getDy() {
                return this.mDy;
            }
            
            public Interpolator getInterpolator() {
                return this.mInterpolator;
            }
            
            boolean hasJumpTarget() {
                return this.mJumpToPosition >= 0;
            }
            
            public void jumpTo(final int mJumpToPosition) {
                this.mJumpToPosition = mJumpToPosition;
            }
            
            void runIfNecessary(final RecyclerView recyclerView) {
                if (this.mJumpToPosition >= 0) {
                    final int mJumpToPosition = this.mJumpToPosition;
                    this.mJumpToPosition = -1;
                    recyclerView.jumpToPositionForSmoothScroller(mJumpToPosition);
                    this.changed = false;
                }
                else if (this.changed) {
                    this.validate();
                    if (this.mInterpolator == null) {
                        if (this.mDuration == Integer.MIN_VALUE) {
                            recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy);
                        }
                        else {
                            recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, this.mDuration);
                        }
                    }
                    else {
                        recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, this.mDuration, this.mInterpolator);
                    }
                    ++this.consecutiveUpdates;
                    if (this.consecutiveUpdates > 10) {
                        Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                    }
                    this.changed = false;
                }
                else {
                    this.consecutiveUpdates = 0;
                }
            }
            
            public void setDuration(final int mDuration) {
                this.changed = true;
                this.mDuration = mDuration;
            }
            
            public void setDx(final int mDx) {
                this.changed = true;
                this.mDx = mDx;
            }
            
            public void setDy(final int mDy) {
                this.changed = true;
                this.mDy = mDy;
            }
            
            public void setInterpolator(final Interpolator mInterpolator) {
                this.changed = true;
                this.mInterpolator = mInterpolator;
            }
            
            public void update(final int mDx, final int mDy, final int mDuration, final Interpolator mInterpolator) {
                this.mDx = mDx;
                this.mDy = mDy;
                this.mDuration = mDuration;
                this.mInterpolator = mInterpolator;
                this.changed = true;
            }
        }
        
        public interface ScrollVectorProvider
        {
            PointF computeScrollVectorForPosition(final int p0);
        }
    }
    
    public static class State
    {
        static final int STEP_ANIMATIONS = 4;
        static final int STEP_LAYOUT = 2;
        static final int STEP_START = 1;
        private SparseArray<Object> mData;
        int mDeletedInvisibleItemCountSincePreviousLayout;
        long mFocusedItemId;
        int mFocusedItemPosition;
        int mFocusedSubChildId;
        boolean mInPreLayout;
        boolean mIsMeasuring;
        int mItemCount;
        int mLayoutStep;
        int mPreviousLayoutItemCount;
        boolean mRunPredictiveAnimations;
        boolean mRunSimpleAnimations;
        boolean mStructureChanged;
        private int mTargetPosition;
        boolean mTrackOldChangeHolders;
        
        public State() {
            this.mTargetPosition = -1;
            this.mLayoutStep = 1;
            this.mItemCount = 0;
            this.mPreviousLayoutItemCount = 0;
            this.mDeletedInvisibleItemCountSincePreviousLayout = 0;
            this.mStructureChanged = false;
            this.mInPreLayout = false;
            this.mRunSimpleAnimations = false;
            this.mRunPredictiveAnimations = false;
            this.mTrackOldChangeHolders = false;
            this.mIsMeasuring = false;
        }
        
        void assertLayoutStep(final int n) {
            if ((n & this.mLayoutStep) == 0x0) {
                throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(n) + " but it is " + Integer.toBinaryString(this.mLayoutStep));
            }
        }
        
        public boolean didStructureChange() {
            return this.mStructureChanged;
        }
        
        public <T> T get(final int n) {
            Object value;
            if (this.mData == null) {
                value = null;
            }
            else {
                value = this.mData.get(n);
            }
            return (T)value;
        }
        
        public int getItemCount() {
            int mItemCount;
            if (this.mInPreLayout) {
                mItemCount = this.mPreviousLayoutItemCount - this.mDeletedInvisibleItemCountSincePreviousLayout;
            }
            else {
                mItemCount = this.mItemCount;
            }
            return mItemCount;
        }
        
        public int getTargetScrollPosition() {
            return this.mTargetPosition;
        }
        
        public boolean hasTargetScrollPosition() {
            return this.mTargetPosition != -1;
        }
        
        public boolean isMeasuring() {
            return this.mIsMeasuring;
        }
        
        public boolean isPreLayout() {
            return this.mInPreLayout;
        }
        
        public void put(final int n, final Object o) {
            if (this.mData == null) {
                this.mData = (SparseArray<Object>)new SparseArray();
            }
            this.mData.put(n, o);
        }
        
        public void remove(final int n) {
            if (this.mData != null) {
                this.mData.remove(n);
            }
        }
        
        State reset() {
            this.mTargetPosition = -1;
            if (this.mData != null) {
                this.mData.clear();
            }
            this.mItemCount = 0;
            this.mStructureChanged = false;
            this.mIsMeasuring = false;
            return this;
        }
        
        @Override
        public String toString() {
            return "State{mTargetPosition=" + this.mTargetPosition + ", mData=" + this.mData + ", mItemCount=" + this.mItemCount + ", mPreviousLayoutItemCount=" + this.mPreviousLayoutItemCount + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.mDeletedInvisibleItemCountSincePreviousLayout + ", mStructureChanged=" + this.mStructureChanged + ", mInPreLayout=" + this.mInPreLayout + ", mRunSimpleAnimations=" + this.mRunSimpleAnimations + ", mRunPredictiveAnimations=" + this.mRunPredictiveAnimations + '}';
        }
        
        public boolean willRunPredictiveAnimations() {
            return this.mRunPredictiveAnimations;
        }
        
        public boolean willRunSimpleAnimations() {
            return this.mRunSimpleAnimations;
        }
    }
    
    public abstract static class ViewCacheExtension
    {
        public abstract View getViewForPositionAndType(final Recycler p0, final int p1, final int p2);
    }
    
    private class ViewFlinger implements Runnable
    {
        private boolean mEatRunOnAnimationRequest;
        private Interpolator mInterpolator;
        private int mLastFlingX;
        private int mLastFlingY;
        private boolean mReSchedulePostAnimationCallback;
        private ScrollerCompat mScroller;
        
        public ViewFlinger() {
            this.mInterpolator = RecyclerView.sQuinticInterpolator;
            this.mEatRunOnAnimationRequest = false;
            this.mReSchedulePostAnimationCallback = false;
            this.mScroller = ScrollerCompat.create(RecyclerView.this.getContext(), RecyclerView.sQuinticInterpolator);
        }
        
        private int computeScrollDuration(final int n, final int n2, final int n3, final int n4) {
            int abs = Math.abs(n);
            final int abs2 = Math.abs(n2);
            boolean b;
            if (abs > abs2) {
                b = true;
            }
            else {
                b = false;
            }
            final int n5 = (int)Math.sqrt(n3 * n3 + n4 * n4);
            final int n6 = (int)Math.sqrt(n * n + n2 * n2);
            int n7;
            if (b) {
                n7 = RecyclerView.this.getWidth();
            }
            else {
                n7 = RecyclerView.this.getHeight();
            }
            final int n8 = n7 / 2;
            final float n9 = n8 + n8 * this.distanceInfluenceForSnapDuration(Math.min(1.0f, 1.0f * n6 / n7));
            int n10;
            if (n5 > 0) {
                n10 = 4 * Math.round(1000.0f * Math.abs(n9 / n5));
            }
            else {
                if (!b) {
                    abs = abs2;
                }
                n10 = (int)(300.0f * (1.0f + abs / n7));
            }
            return Math.min(n10, 2000);
        }
        
        private void disableRunOnAnimationRequests() {
            this.mReSchedulePostAnimationCallback = false;
            this.mEatRunOnAnimationRequest = true;
        }
        
        private float distanceInfluenceForSnapDuration(final float n) {
            return (float)Math.sin((float)(0.4712389167638204 * (n - 0.5f)));
        }
        
        private void enableRunOnAnimationRequests() {
            this.mEatRunOnAnimationRequest = false;
            if (this.mReSchedulePostAnimationCallback) {
                this.postOnAnimation();
            }
        }
        
        public void fling(final int n, final int n2) {
            RecyclerView.this.setScrollState(2);
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            this.mScroller.fling(0, 0, n, n2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            this.postOnAnimation();
        }
        
        void postOnAnimation() {
            if (this.mEatRunOnAnimationRequest) {
                this.mReSchedulePostAnimationCallback = true;
            }
            else {
                RecyclerView.this.removeCallbacks((Runnable)this);
                ViewCompat.postOnAnimation((View)RecyclerView.this, this);
            }
        }
        
        @Override
        public void run() {
            if (RecyclerView.this.mLayout == null) {
                this.stop();
            }
            else {
                this.disableRunOnAnimationRequests();
                RecyclerView.this.consumePendingUpdateOperations();
                final ScrollerCompat mScroller = this.mScroller;
                final SmoothScroller mSmoothScroller = RecyclerView.this.mLayout.mSmoothScroller;
                if (mScroller.computeScrollOffset()) {
                    final int currX = mScroller.getCurrX();
                    final int currY = mScroller.getCurrY();
                    final int n = currX - this.mLastFlingX;
                    final int n2 = currY - this.mLastFlingY;
                    this.mLastFlingX = currX;
                    this.mLastFlingY = currY;
                    final Adapter mAdapter = RecyclerView.this.mAdapter;
                    int scrollHorizontallyBy = 0;
                    int n3 = 0;
                    int n4 = 0;
                    int scrollVerticallyBy = 0;
                    if (mAdapter != null) {
                        RecyclerView.this.eatRequestLayout();
                        RecyclerView.this.onEnterLayoutOrScroll();
                        TraceCompat.beginSection("RV Scroll");
                        scrollHorizontallyBy = 0;
                        n3 = 0;
                        if (n != 0) {
                            scrollHorizontallyBy = RecyclerView.this.mLayout.scrollHorizontallyBy(n, RecyclerView.this.mRecycler, RecyclerView.this.mState);
                            n3 = n - scrollHorizontallyBy;
                        }
                        n4 = 0;
                        scrollVerticallyBy = 0;
                        if (n2 != 0) {
                            scrollVerticallyBy = RecyclerView.this.mLayout.scrollVerticallyBy(n2, RecyclerView.this.mRecycler, RecyclerView.this.mState);
                            n4 = n2 - scrollVerticallyBy;
                        }
                        TraceCompat.endSection();
                        RecyclerView.this.repositionShadowingViews();
                        RecyclerView.this.onExitLayoutOrScroll();
                        RecyclerView.this.resumeRequestLayout(false);
                        if (mSmoothScroller != null && !mSmoothScroller.isPendingInitialRun() && mSmoothScroller.isRunning()) {
                            final int itemCount = RecyclerView.this.mState.getItemCount();
                            if (itemCount == 0) {
                                mSmoothScroller.stop();
                            }
                            else if (mSmoothScroller.getTargetPosition() >= itemCount) {
                                mSmoothScroller.setTargetPosition(itemCount - 1);
                                mSmoothScroller.onAnimation(n - n3, n2 - n4);
                            }
                            else {
                                mSmoothScroller.onAnimation(n - n3, n2 - n4);
                            }
                        }
                    }
                    if (!RecyclerView.this.mItemDecorations.isEmpty()) {
                        RecyclerView.this.invalidate();
                    }
                    if (RecyclerView.this.getOverScrollMode() != 2) {
                        RecyclerView.this.considerReleasingGlowsOnScroll(n, n2);
                    }
                    if (n3 != 0 || n4 != 0) {
                        final int n5 = (int)mScroller.getCurrVelocity();
                        int n6 = 0;
                        if (n3 != currX) {
                            if (n3 < 0) {
                                n6 = -n5;
                            }
                            else if (n3 > 0) {
                                n6 = n5;
                            }
                            else {
                                n6 = 0;
                            }
                        }
                        int n7 = 0;
                        if (n4 != currY) {
                            if (n4 < 0) {
                                n7 = -n5;
                            }
                            else if (n4 > 0) {
                                n7 = n5;
                            }
                            else {
                                n7 = 0;
                            }
                        }
                        if (RecyclerView.this.getOverScrollMode() != 2) {
                            RecyclerView.this.absorbGlows(n6, n7);
                        }
                        if ((n6 != 0 || n3 == currX || mScroller.getFinalX() == 0) && (n7 != 0 || n4 == currY || mScroller.getFinalY() == 0)) {
                            mScroller.abortAnimation();
                        }
                    }
                    if (scrollHorizontallyBy != 0 || scrollVerticallyBy != 0) {
                        RecyclerView.this.dispatchOnScrolled(scrollHorizontallyBy, scrollVerticallyBy);
                    }
                    if (!RecyclerView.access$500(RecyclerView.this)) {
                        RecyclerView.this.invalidate();
                    }
                    boolean b;
                    if (n2 != 0 && RecyclerView.this.mLayout.canScrollVertically() && scrollVerticallyBy == n2) {
                        b = true;
                    }
                    else {
                        b = false;
                    }
                    boolean b2;
                    if (n != 0 && RecyclerView.this.mLayout.canScrollHorizontally() && scrollHorizontallyBy == n) {
                        b2 = true;
                    }
                    else {
                        b2 = false;
                    }
                    boolean b3;
                    if ((n == 0 && n2 == 0) || b2 || b) {
                        b3 = true;
                    }
                    else {
                        b3 = false;
                    }
                    if (mScroller.isFinished() || !b3) {
                        RecyclerView.this.setScrollState(0);
                    }
                    else {
                        this.postOnAnimation();
                    }
                }
                if (mSmoothScroller != null) {
                    if (mSmoothScroller.isPendingInitialRun()) {
                        mSmoothScroller.onAnimation(0, 0);
                    }
                    if (!this.mReSchedulePostAnimationCallback) {
                        mSmoothScroller.stop();
                    }
                }
                this.enableRunOnAnimationRequests();
            }
        }
        
        public void smoothScrollBy(final int n, final int n2) {
            this.smoothScrollBy(n, n2, 0, 0);
        }
        
        public void smoothScrollBy(final int n, final int n2, final int n3) {
            this.smoothScrollBy(n, n2, n3, RecyclerView.sQuinticInterpolator);
        }
        
        public void smoothScrollBy(final int n, final int n2, final int n3, final int n4) {
            this.smoothScrollBy(n, n2, this.computeScrollDuration(n, n2, n3, n4));
        }
        
        public void smoothScrollBy(final int n, final int n2, final int n3, final Interpolator mInterpolator) {
            if (this.mInterpolator != mInterpolator) {
                this.mInterpolator = mInterpolator;
                this.mScroller = ScrollerCompat.create(RecyclerView.this.getContext(), mInterpolator);
            }
            RecyclerView.this.setScrollState(2);
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            this.mScroller.startScroll(0, 0, n, n2, n3);
            this.postOnAnimation();
        }
        
        public void stop() {
            RecyclerView.this.removeCallbacks((Runnable)this);
            this.mScroller.abortAnimation();
        }
    }
    
    public abstract static class ViewHolder
    {
        static final int FLAG_ADAPTER_FULLUPDATE = 1024;
        static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
        static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
        static final int FLAG_BOUND = 1;
        static final int FLAG_IGNORE = 128;
        static final int FLAG_INVALID = 4;
        static final int FLAG_MOVED = 2048;
        static final int FLAG_NOT_RECYCLABLE = 16;
        static final int FLAG_REMOVED = 8;
        static final int FLAG_RETURNED_FROM_SCRAP = 32;
        static final int FLAG_TMP_DETACHED = 256;
        static final int FLAG_UPDATE = 2;
        private static final List<Object> FULLUPDATE_PAYLOADS;
        public final View itemView;
        private int mFlags;
        private boolean mInChangeScrap;
        private int mIsRecyclableCount;
        long mItemId;
        int mItemViewType;
        int mOldPosition;
        RecyclerView mOwnerRecyclerView;
        List<Object> mPayloads;
        int mPosition;
        int mPreLayoutPosition;
        private Recycler mScrapContainer;
        ViewHolder mShadowedHolder;
        ViewHolder mShadowingHolder;
        List<Object> mUnmodifiedPayloads;
        private int mWasImportantForAccessibilityBeforeHidden;
        
        static {
            FULLUPDATE_PAYLOADS = Collections.EMPTY_LIST;
        }
        
        public ViewHolder(final View itemView) {
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1L;
            this.mItemViewType = -1;
            this.mPreLayoutPosition = -1;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            this.mPayloads = null;
            this.mUnmodifiedPayloads = null;
            this.mIsRecyclableCount = 0;
            this.mScrapContainer = null;
            this.mInChangeScrap = false;
            this.mWasImportantForAccessibilityBeforeHidden = 0;
            if (itemView == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.itemView = itemView;
        }
        
        private void createPayloadsIfNeeded() {
            if (this.mPayloads == null) {
                this.mPayloads = new ArrayList<Object>();
                this.mUnmodifiedPayloads = Collections.unmodifiableList((List<?>)this.mPayloads);
            }
        }
        
        private boolean doesTransientStatePreventRecycling() {
            return (0x10 & this.mFlags) == 0x0 && ViewCompat.hasTransientState(this.itemView);
        }
        
        private void onEnteredHiddenState() {
            this.mWasImportantForAccessibilityBeforeHidden = ViewCompat.getImportantForAccessibility(this.itemView);
            ViewCompat.setImportantForAccessibility(this.itemView, 4);
        }
        
        private void onLeftHiddenState() {
            ViewCompat.setImportantForAccessibility(this.itemView, this.mWasImportantForAccessibilityBeforeHidden);
            this.mWasImportantForAccessibilityBeforeHidden = 0;
        }
        
        private boolean shouldBeKeptAsChild() {
            return (0x10 & this.mFlags) != 0x0;
        }
        
        void addChangePayload(final Object o) {
            if (o == null) {
                this.addFlags(1024);
            }
            else if ((0x400 & this.mFlags) == 0x0) {
                this.createPayloadsIfNeeded();
                this.mPayloads.add(o);
            }
        }
        
        void addFlags(final int n) {
            this.mFlags |= n;
        }
        
        void clearOldPosition() {
            this.mOldPosition = -1;
            this.mPreLayoutPosition = -1;
        }
        
        void clearPayload() {
            if (this.mPayloads != null) {
                this.mPayloads.clear();
            }
            this.mFlags &= 0xFFFFFBFF;
        }
        
        void clearReturnedFromScrapFlag() {
            this.mFlags &= 0xFFFFFFDF;
        }
        
        void clearTmpDetachFlag() {
            this.mFlags &= 0xFFFFFEFF;
        }
        
        void flagRemovedAndOffsetPosition(final int mPosition, final int n, final boolean b) {
            this.addFlags(8);
            this.offsetPosition(n, b);
            this.mPosition = mPosition;
        }
        
        public final int getAdapterPosition() {
            int adapterPosition;
            if (this.mOwnerRecyclerView == null) {
                adapterPosition = -1;
            }
            else {
                adapterPosition = this.mOwnerRecyclerView.getAdapterPositionFor(this);
            }
            return adapterPosition;
        }
        
        public final long getItemId() {
            return this.mItemId;
        }
        
        public final int getItemViewType() {
            return this.mItemViewType;
        }
        
        public final int getLayoutPosition() {
            int n;
            if (this.mPreLayoutPosition == -1) {
                n = this.mPosition;
            }
            else {
                n = this.mPreLayoutPosition;
            }
            return n;
        }
        
        public final int getOldPosition() {
            return this.mOldPosition;
        }
        
        @Deprecated
        public final int getPosition() {
            int n;
            if (this.mPreLayoutPosition == -1) {
                n = this.mPosition;
            }
            else {
                n = this.mPreLayoutPosition;
            }
            return n;
        }
        
        List<Object> getUnmodifiedPayloads() {
            List<Object> list;
            if ((0x400 & this.mFlags) == 0x0) {
                if (this.mPayloads == null || this.mPayloads.size() == 0) {
                    list = ViewHolder.FULLUPDATE_PAYLOADS;
                }
                else {
                    list = this.mUnmodifiedPayloads;
                }
            }
            else {
                list = ViewHolder.FULLUPDATE_PAYLOADS;
            }
            return list;
        }
        
        boolean hasAnyOfTheFlags(final int n) {
            return (n & this.mFlags) != 0x0;
        }
        
        boolean isAdapterPositionUnknown() {
            return (0x200 & this.mFlags) != 0x0 || this.isInvalid();
        }
        
        boolean isBound() {
            return (0x1 & this.mFlags) != 0x0;
        }
        
        boolean isInvalid() {
            return (0x4 & this.mFlags) != 0x0;
        }
        
        public final boolean isRecyclable() {
            return (0x10 & this.mFlags) == 0x0 && !ViewCompat.hasTransientState(this.itemView);
        }
        
        boolean isRemoved() {
            return (0x8 & this.mFlags) != 0x0;
        }
        
        boolean isScrap() {
            return this.mScrapContainer != null;
        }
        
        boolean isTmpDetached() {
            return (0x100 & this.mFlags) != 0x0;
        }
        
        boolean isUpdated() {
            return (0x2 & this.mFlags) != 0x0;
        }
        
        boolean needsUpdate() {
            return (0x2 & this.mFlags) != 0x0;
        }
        
        void offsetPosition(final int n, final boolean b) {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
            if (this.mPreLayoutPosition == -1) {
                this.mPreLayoutPosition = this.mPosition;
            }
            if (b) {
                this.mPreLayoutPosition += n;
            }
            this.mPosition += n;
            if (this.itemView.getLayoutParams() != null) {
                ((LayoutParams)this.itemView.getLayoutParams()).mInsetsDirty = true;
            }
        }
        
        void resetInternal() {
            this.mFlags = 0;
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1L;
            this.mPreLayoutPosition = -1;
            this.mIsRecyclableCount = 0;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            this.clearPayload();
            this.mWasImportantForAccessibilityBeforeHidden = 0;
        }
        
        void saveOldPosition() {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
        }
        
        void setFlags(final int n, final int n2) {
            this.mFlags = ((this.mFlags & ~n2) | (n & n2));
        }
        
        public final void setIsRecyclable(final boolean b) {
            int mIsRecyclableCount;
            if (b) {
                mIsRecyclableCount = -1 + this.mIsRecyclableCount;
            }
            else {
                mIsRecyclableCount = 1 + this.mIsRecyclableCount;
            }
            this.mIsRecyclableCount = mIsRecyclableCount;
            if (this.mIsRecyclableCount < 0) {
                this.mIsRecyclableCount = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
            }
            else if (!b && this.mIsRecyclableCount == 1) {
                this.mFlags |= 0x10;
            }
            else if (b && this.mIsRecyclableCount == 0) {
                this.mFlags &= 0xFFFFFFEF;
            }
        }
        
        void setScrapContainer(final Recycler mScrapContainer, final boolean mInChangeScrap) {
            this.mScrapContainer = mScrapContainer;
            this.mInChangeScrap = mInChangeScrap;
        }
        
        boolean shouldIgnore() {
            return (0x80 & this.mFlags) != 0x0;
        }
        
        void stopIgnoring() {
            this.mFlags &= 0xFFFFFF7F;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("ViewHolder{" + Integer.toHexString(this.hashCode()) + " position=" + this.mPosition + " id=" + this.mItemId + ", oldPos=" + this.mOldPosition + ", pLpos:" + this.mPreLayoutPosition);
            if (this.isScrap()) {
                final StringBuilder append = sb.append(" scrap ");
                String s;
                if (this.mInChangeScrap) {
                    s = "[changeScrap]";
                }
                else {
                    s = "[attachedScrap]";
                }
                append.append(s);
            }
            if (this.isInvalid()) {
                sb.append(" invalid");
            }
            if (!this.isBound()) {
                sb.append(" unbound");
            }
            if (this.needsUpdate()) {
                sb.append(" update");
            }
            if (this.isRemoved()) {
                sb.append(" removed");
            }
            if (this.shouldIgnore()) {
                sb.append(" ignored");
            }
            if (this.isTmpDetached()) {
                sb.append(" tmpDetached");
            }
            if (!this.isRecyclable()) {
                sb.append(" not recyclable(" + this.mIsRecyclableCount + ")");
            }
            if (this.isAdapterPositionUnknown()) {
                sb.append(" undefined adapter position");
            }
            if (this.itemView.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }
        
        void unScrap() {
            this.mScrapContainer.unscrapView(this);
        }
        
        boolean wasReturnedFromScrap() {
            return (0x20 & this.mFlags) != 0x0;
        }
    }
}
