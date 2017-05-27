// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget.helper;

import android.support.v4.animation.AnimatorUpdateListenerCompat;
import android.support.v4.animation.AnimatorCompatHelper;
import android.support.v4.animation.AnimatorListenerCompat;
import android.view.GestureDetector$SimpleOnGestureListener;
import android.view.animation.Interpolator;
import android.util.Log;
import android.view.ViewParent;
import android.support.v4.animation.ValueAnimatorCompat;
import android.graphics.Canvas;
import android.content.res.Resources;
import android.support.v7.recyclerview.R;
import android.support.annotation.Nullable;
import android.view.ViewConfiguration;
import android.view.GestureDetector$OnGestureListener;
import android.support.v4.view.VelocityTrackerCompat;
import android.os.Build$VERSION;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.support.v4.view.ViewCompat;
import java.util.ArrayList;
import android.view.VelocityTracker;
import android.graphics.Rect;
import android.view.View;
import android.support.v4.view.GestureDetectorCompat;
import java.util.List;
import android.support.v7.widget.RecyclerView;

public class ItemTouchHelper extends ItemDecoration implements OnChildAttachStateChangeListener
{
    static final int ACTION_MODE_DRAG_MASK = 16711680;
    private static final int ACTION_MODE_IDLE_MASK = 255;
    static final int ACTION_MODE_SWIPE_MASK = 65280;
    public static final int ACTION_STATE_DRAG = 2;
    public static final int ACTION_STATE_IDLE = 0;
    public static final int ACTION_STATE_SWIPE = 1;
    static final int ACTIVE_POINTER_ID_NONE = -1;
    public static final int ANIMATION_TYPE_DRAG = 8;
    public static final int ANIMATION_TYPE_SWIPE_CANCEL = 4;
    public static final int ANIMATION_TYPE_SWIPE_SUCCESS = 2;
    static final boolean DEBUG = false;
    static final int DIRECTION_FLAG_COUNT = 8;
    public static final int DOWN = 2;
    public static final int END = 32;
    public static final int LEFT = 4;
    private static final int PIXELS_PER_SECOND = 1000;
    public static final int RIGHT = 8;
    public static final int START = 16;
    static final String TAG = "ItemTouchHelper";
    public static final int UP = 1;
    int mActionState;
    int mActivePointerId;
    Callback mCallback;
    private ChildDrawingOrderCallback mChildDrawingOrderCallback;
    private List<Integer> mDistances;
    private long mDragScrollStartTimeInMs;
    float mDx;
    float mDy;
    GestureDetectorCompat mGestureDetector;
    float mInitialTouchX;
    float mInitialTouchY;
    float mMaxSwipeVelocity;
    private final OnItemTouchListener mOnItemTouchListener;
    View mOverdrawChild;
    int mOverdrawChildPosition;
    final List<View> mPendingCleanup;
    List<RecoverAnimation> mRecoverAnimations;
    RecyclerView mRecyclerView;
    final Runnable mScrollRunnable;
    ViewHolder mSelected;
    int mSelectedFlags;
    float mSelectedStartX;
    float mSelectedStartY;
    private int mSlop;
    private List<ViewHolder> mSwapTargets;
    float mSwipeEscapeVelocity;
    private final float[] mTmpPosition;
    private Rect mTmpRect;
    VelocityTracker mVelocityTracker;
    
    public ItemTouchHelper(final Callback mCallback) {
        this.mPendingCleanup = new ArrayList<View>();
        this.mTmpPosition = new float[2];
        this.mSelected = null;
        this.mActivePointerId = -1;
        this.mActionState = 0;
        this.mRecoverAnimations = new ArrayList<RecoverAnimation>();
        this.mScrollRunnable = new Runnable() {
            @Override
            public void run() {
                if (ItemTouchHelper.this.mSelected != null && ItemTouchHelper.this.scrollIfNecessary()) {
                    if (ItemTouchHelper.this.mSelected != null) {
                        ItemTouchHelper.this.moveIfNecessary(ItemTouchHelper.this.mSelected);
                    }
                    ItemTouchHelper.this.mRecyclerView.removeCallbacks(ItemTouchHelper.this.mScrollRunnable);
                    ViewCompat.postOnAnimation((View)ItemTouchHelper.this.mRecyclerView, this);
                }
            }
        };
        this.mChildDrawingOrderCallback = null;
        this.mOverdrawChild = null;
        this.mOverdrawChildPosition = -1;
        this.mOnItemTouchListener = new OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(final RecyclerView recyclerView, final MotionEvent motionEvent) {
                boolean b = true;
                ItemTouchHelper.this.mGestureDetector.onTouchEvent(motionEvent);
                final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
                if (actionMasked == 0) {
                    ItemTouchHelper.this.mActivePointerId = motionEvent.getPointerId(0);
                    ItemTouchHelper.this.mInitialTouchX = motionEvent.getX();
                    ItemTouchHelper.this.mInitialTouchY = motionEvent.getY();
                    ItemTouchHelper.this.obtainVelocityTracker();
                    if (ItemTouchHelper.this.mSelected == null) {
                        final RecoverAnimation animation = ItemTouchHelper.this.findAnimation(motionEvent);
                        if (animation != null) {
                            final ItemTouchHelper this$0 = ItemTouchHelper.this;
                            this$0.mInitialTouchX -= animation.mX;
                            final ItemTouchHelper this$2 = ItemTouchHelper.this;
                            this$2.mInitialTouchY -= animation.mY;
                            ItemTouchHelper.this.endRecoverAnimation(animation.mViewHolder, b);
                            if (ItemTouchHelper.this.mPendingCleanup.remove(animation.mViewHolder.itemView)) {
                                ItemTouchHelper.this.mCallback.clearView(ItemTouchHelper.this.mRecyclerView, animation.mViewHolder);
                            }
                            ItemTouchHelper.this.select(animation.mViewHolder, animation.mActionState);
                            ItemTouchHelper.this.updateDxDy(motionEvent, ItemTouchHelper.this.mSelectedFlags, 0);
                        }
                    }
                }
                else if (actionMasked == 3 || actionMasked == (b ? 1 : 0)) {
                    ItemTouchHelper.this.mActivePointerId = -1;
                    ItemTouchHelper.this.select(null, 0);
                }
                else if (ItemTouchHelper.this.mActivePointerId != -1) {
                    final int pointerIndex = motionEvent.findPointerIndex(ItemTouchHelper.this.mActivePointerId);
                    if (pointerIndex >= 0) {
                        ItemTouchHelper.this.checkSelectForSwipe(actionMasked, motionEvent, pointerIndex);
                    }
                }
                if (ItemTouchHelper.this.mVelocityTracker != null) {
                    ItemTouchHelper.this.mVelocityTracker.addMovement(motionEvent);
                }
                if (ItemTouchHelper.this.mSelected == null) {
                    b = false;
                }
                return b;
            }
            
            @Override
            public void onRequestDisallowInterceptTouchEvent(final boolean b) {
                if (b) {
                    ItemTouchHelper.this.select(null, 0);
                }
            }
            
            @Override
            public void onTouchEvent(final RecyclerView recyclerView, final MotionEvent motionEvent) {
                ItemTouchHelper.this.mGestureDetector.onTouchEvent(motionEvent);
                if (ItemTouchHelper.this.mVelocityTracker != null) {
                    ItemTouchHelper.this.mVelocityTracker.addMovement(motionEvent);
                }
                if (ItemTouchHelper.this.mActivePointerId != -1) {
                    final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
                    final int pointerIndex = motionEvent.findPointerIndex(ItemTouchHelper.this.mActivePointerId);
                    if (pointerIndex >= 0) {
                        ItemTouchHelper.this.checkSelectForSwipe(actionMasked, motionEvent, pointerIndex);
                    }
                    final ViewHolder mSelected = ItemTouchHelper.this.mSelected;
                    if (mSelected != null) {
                        switch (actionMasked) {
                            case 3: {
                                if (ItemTouchHelper.this.mVelocityTracker != null) {
                                    ItemTouchHelper.this.mVelocityTracker.clear();
                                }
                            }
                            case 1: {
                                ItemTouchHelper.this.select(null, 0);
                                ItemTouchHelper.this.mActivePointerId = -1;
                                break;
                            }
                            case 2: {
                                if (pointerIndex >= 0) {
                                    ItemTouchHelper.this.updateDxDy(motionEvent, ItemTouchHelper.this.mSelectedFlags, pointerIndex);
                                    ItemTouchHelper.this.moveIfNecessary(mSelected);
                                    ItemTouchHelper.this.mRecyclerView.removeCallbacks(ItemTouchHelper.this.mScrollRunnable);
                                    ItemTouchHelper.this.mScrollRunnable.run();
                                    ItemTouchHelper.this.mRecyclerView.invalidate();
                                    break;
                                }
                                break;
                            }
                            case 6: {
                                final int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                                if (motionEvent.getPointerId(actionIndex) == ItemTouchHelper.this.mActivePointerId) {
                                    int n = 0;
                                    if (actionIndex == 0) {
                                        n = 1;
                                    }
                                    ItemTouchHelper.this.mActivePointerId = motionEvent.getPointerId(n);
                                    ItemTouchHelper.this.updateDxDy(motionEvent, ItemTouchHelper.this.mSelectedFlags, actionIndex);
                                    break;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        };
        this.mCallback = mCallback;
    }
    
    private void addChildDrawingOrderCallback() {
        if (Build$VERSION.SDK_INT < 21) {
            if (this.mChildDrawingOrderCallback == null) {
                this.mChildDrawingOrderCallback = new ChildDrawingOrderCallback() {
                    @Override
                    public int onGetChildDrawingOrder(final int n, int n2) {
                        if (ItemTouchHelper.this.mOverdrawChild != null) {
                            int mOverdrawChildPosition = ItemTouchHelper.this.mOverdrawChildPosition;
                            if (mOverdrawChildPosition == -1) {
                                mOverdrawChildPosition = ItemTouchHelper.this.mRecyclerView.indexOfChild(ItemTouchHelper.this.mOverdrawChild);
                                ItemTouchHelper.this.mOverdrawChildPosition = mOverdrawChildPosition;
                            }
                            if (n2 == n - 1) {
                                n2 = mOverdrawChildPosition;
                            }
                            else if (n2 >= mOverdrawChildPosition) {
                                ++n2;
                            }
                        }
                        return n2;
                    }
                };
            }
            this.mRecyclerView.setChildDrawingOrderCallback(this.mChildDrawingOrderCallback);
        }
    }
    
    private int checkHorizontalSwipe(final ViewHolder viewHolder, final int n) {
        if ((n & 0xC) == 0x0) {
            return 0;
        }
        int n2;
        if (this.mDx > 0.0f) {
            n2 = 8;
        }
        else {
            n2 = 4;
        }
        if (this.mVelocityTracker != null && this.mActivePointerId > -1) {
            this.mVelocityTracker.computeCurrentVelocity(1000, this.mCallback.getSwipeVelocityThreshold(this.mMaxSwipeVelocity));
            final float xVelocity = VelocityTrackerCompat.getXVelocity(this.mVelocityTracker, this.mActivePointerId);
            final float yVelocity = VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, this.mActivePointerId);
            int n3;
            if (xVelocity > 0.0f) {
                n3 = 8;
            }
            else {
                n3 = 4;
            }
            final float abs = Math.abs(xVelocity);
            if ((n3 & n) != 0x0 && n2 == n3 && abs >= this.mCallback.getSwipeEscapeVelocity(this.mSwipeEscapeVelocity) && abs > Math.abs(yVelocity)) {
                return n3;
            }
        }
        final float n4 = this.mRecyclerView.getWidth() * this.mCallback.getSwipeThreshold(viewHolder);
        if ((n & n2) == 0x0 || Math.abs(this.mDx) <= n4) {
            return 0;
        }
        return n2;
        n3 = 0;
        return n3;
    }
    
    private int checkVerticalSwipe(final ViewHolder viewHolder, final int n) {
        if ((n & 0x3) == 0x0) {
            return 0;
        }
        int n2;
        if (this.mDy > 0.0f) {
            n2 = 2;
        }
        else {
            n2 = 1;
        }
        if (this.mVelocityTracker != null && this.mActivePointerId > -1) {
            this.mVelocityTracker.computeCurrentVelocity(1000, this.mCallback.getSwipeVelocityThreshold(this.mMaxSwipeVelocity));
            final float xVelocity = VelocityTrackerCompat.getXVelocity(this.mVelocityTracker, this.mActivePointerId);
            final float yVelocity = VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, this.mActivePointerId);
            int n3;
            if (yVelocity > 0.0f) {
                n3 = 2;
            }
            else {
                n3 = 1;
            }
            final float abs = Math.abs(yVelocity);
            if ((n3 & n) != 0x0 && n3 == n2 && abs >= this.mCallback.getSwipeEscapeVelocity(this.mSwipeEscapeVelocity) && abs > Math.abs(xVelocity)) {
                return n3;
            }
        }
        final float n4 = this.mRecyclerView.getHeight() * this.mCallback.getSwipeThreshold(viewHolder);
        if ((n & n2) == 0x0 || Math.abs(this.mDy) <= n4) {
            return 0;
        }
        return n2;
        n3 = 0;
        return n3;
    }
    
    private void destroyCallbacks() {
        this.mRecyclerView.removeItemDecoration((RecyclerView.ItemDecoration)this);
        this.mRecyclerView.removeOnItemTouchListener(this.mOnItemTouchListener);
        this.mRecyclerView.removeOnChildAttachStateChangeListener((RecyclerView.OnChildAttachStateChangeListener)this);
        for (int i = -1 + this.mRecoverAnimations.size(); i >= 0; --i) {
            this.mCallback.clearView(this.mRecyclerView, this.mRecoverAnimations.get(0).mViewHolder);
        }
        this.mRecoverAnimations.clear();
        this.mOverdrawChild = null;
        this.mOverdrawChildPosition = -1;
        this.releaseVelocityTracker();
    }
    
    private List<ViewHolder> findSwapTargets(final ViewHolder viewHolder) {
        if (this.mSwapTargets == null) {
            this.mSwapTargets = new ArrayList<ViewHolder>();
            this.mDistances = new ArrayList<Integer>();
        }
        else {
            this.mSwapTargets.clear();
            this.mDistances.clear();
        }
        final int boundingBoxMargin = this.mCallback.getBoundingBoxMargin();
        final int n = Math.round(this.mSelectedStartX + this.mDx) - boundingBoxMargin;
        final int n2 = Math.round(this.mSelectedStartY + this.mDy) - boundingBoxMargin;
        final int n3 = n + viewHolder.itemView.getWidth() + boundingBoxMargin * 2;
        final int n4 = n2 + viewHolder.itemView.getHeight() + boundingBoxMargin * 2;
        final int n5 = (n + n3) / 2;
        final int n6 = (n2 + n4) / 2;
        final RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        for (int childCount = layoutManager.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = layoutManager.getChildAt(i);
            if (child != viewHolder.itemView && child.getBottom() >= n2 && child.getTop() <= n4 && child.getRight() >= n && child.getLeft() <= n3) {
                final RecyclerView.ViewHolder childViewHolder = this.mRecyclerView.getChildViewHolder(child);
                if (this.mCallback.canDropOver(this.mRecyclerView, this.mSelected, childViewHolder)) {
                    final int abs = Math.abs(n5 - (child.getLeft() + child.getRight()) / 2);
                    final int abs2 = Math.abs(n6 - (child.getTop() + child.getBottom()) / 2);
                    final int n7 = abs * abs + abs2 * abs2;
                    int n8 = 0;
                    for (int size = this.mSwapTargets.size(), n9 = 0; n9 < size && n7 > this.mDistances.get(n9); ++n9) {
                        ++n8;
                    }
                    this.mSwapTargets.add(n8, childViewHolder);
                    this.mDistances.add(n8, n7);
                }
            }
        }
        return this.mSwapTargets;
    }
    
    private ViewHolder findSwipedView(final MotionEvent motionEvent) {
        final RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        final int mActivePointerId = this.mActivePointerId;
        Object childViewHolder = null;
        if (mActivePointerId != -1) {
            final int pointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
            final float n = motionEvent.getX(pointerIndex) - this.mInitialTouchX;
            final float n2 = motionEvent.getY(pointerIndex) - this.mInitialTouchY;
            final float abs = Math.abs(n);
            final float abs2 = Math.abs(n2);
            if (abs < this.mSlop) {
                final float n3 = fcmpg(abs2, (float)this.mSlop);
                childViewHolder = null;
                if (n3 < 0) {
                    return (ViewHolder)childViewHolder;
                }
            }
            if (abs > abs2) {
                final boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
                childViewHolder = null;
                if (canScrollHorizontally) {
                    return (ViewHolder)childViewHolder;
                }
            }
            if (abs2 > abs) {
                final boolean canScrollVertically = layoutManager.canScrollVertically();
                childViewHolder = null;
                if (canScrollVertically) {
                    return (ViewHolder)childViewHolder;
                }
            }
            final View childView = this.findChildView(motionEvent);
            childViewHolder = null;
            if (childView != null) {
                childViewHolder = this.mRecyclerView.getChildViewHolder(childView);
            }
        }
        return (ViewHolder)childViewHolder;
    }
    
    private void getSelectedDxDy(final float[] array) {
        if ((0xC & this.mSelectedFlags) != 0x0) {
            array[0] = this.mSelectedStartX + this.mDx - this.mSelected.itemView.getLeft();
        }
        else {
            array[0] = ViewCompat.getTranslationX(this.mSelected.itemView);
        }
        if ((0x3 & this.mSelectedFlags) != 0x0) {
            array[1] = this.mSelectedStartY + this.mDy - this.mSelected.itemView.getTop();
        }
        else {
            array[1] = ViewCompat.getTranslationY(this.mSelected.itemView);
        }
    }
    
    private static boolean hitTest(final View view, final float n, final float n2, final float n3, final float n4) {
        return n >= n3 && n <= n3 + view.getWidth() && n2 >= n4 && n2 <= n4 + view.getHeight();
    }
    
    private void initGestureDetector() {
        if (this.mGestureDetector == null) {
            this.mGestureDetector = new GestureDetectorCompat(this.mRecyclerView.getContext(), (GestureDetector$OnGestureListener)new ItemTouchHelperGestureListener());
        }
    }
    
    private void releaseVelocityTracker() {
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }
    
    private void setupCallbacks() {
        this.mSlop = ViewConfiguration.get(this.mRecyclerView.getContext()).getScaledTouchSlop();
        this.mRecyclerView.addItemDecoration((RecyclerView.ItemDecoration)this);
        this.mRecyclerView.addOnItemTouchListener(this.mOnItemTouchListener);
        this.mRecyclerView.addOnChildAttachStateChangeListener((RecyclerView.OnChildAttachStateChangeListener)this);
        this.initGestureDetector();
    }
    
    private int swipeIfNecessary(final ViewHolder viewHolder) {
        int n;
        if (this.mActionState == 2) {
            n = 0;
        }
        else {
            final int movementFlags = this.mCallback.getMovementFlags(this.mRecyclerView, viewHolder);
            final int n2 = (0xFF00 & this.mCallback.convertToAbsoluteDirection(movementFlags, ViewCompat.getLayoutDirection((View)this.mRecyclerView))) >> 8;
            if (n2 == 0) {
                n = 0;
            }
            else {
                final int n3 = (movementFlags & 0xFF00) >> 8;
                if (Math.abs(this.mDx) > Math.abs(this.mDy)) {
                    n = this.checkHorizontalSwipe(viewHolder, n2);
                    if (n > 0) {
                        if ((n3 & n) == 0x0) {
                            n = Callback.convertToRelativeDirection(n, ViewCompat.getLayoutDirection((View)this.mRecyclerView));
                            return n;
                        }
                        return n;
                    }
                    else {
                        n = this.checkVerticalSwipe(viewHolder, n2);
                        if (n > 0) {
                            return n;
                        }
                    }
                }
                else {
                    n = this.checkVerticalSwipe(viewHolder, n2);
                    if (n > 0) {
                        return n;
                    }
                    n = this.checkHorizontalSwipe(viewHolder, n2);
                    if (n > 0) {
                        if ((n3 & n) == 0x0) {
                            n = Callback.convertToRelativeDirection(n, ViewCompat.getLayoutDirection((View)this.mRecyclerView));
                            return n;
                        }
                        return n;
                    }
                }
                n = 0;
            }
        }
        return n;
    }
    
    public void attachToRecyclerView(@Nullable final RecyclerView mRecyclerView) {
        if (this.mRecyclerView != mRecyclerView) {
            if (this.mRecyclerView != null) {
                this.destroyCallbacks();
            }
            this.mRecyclerView = mRecyclerView;
            if (this.mRecyclerView != null) {
                final Resources resources = mRecyclerView.getResources();
                this.mSwipeEscapeVelocity = resources.getDimension(R.dimen.item_touch_helper_swipe_escape_velocity);
                this.mMaxSwipeVelocity = resources.getDimension(R.dimen.item_touch_helper_swipe_escape_max_velocity);
                this.setupCallbacks();
            }
        }
    }
    
    boolean checkSelectForSwipe(final int n, final MotionEvent motionEvent, final int n2) {
        boolean b;
        if (this.mSelected != null || n != 2 || this.mActionState == 2 || !this.mCallback.isItemViewSwipeEnabled()) {
            b = false;
        }
        else if (this.mRecyclerView.getScrollState() == 1) {
            b = false;
        }
        else {
            final ViewHolder swipedView = this.findSwipedView(motionEvent);
            if (swipedView == null) {
                b = false;
            }
            else {
                final int n3 = (0xFF00 & this.mCallback.getAbsoluteMovementFlags(this.mRecyclerView, swipedView)) >> 8;
                if (n3 == 0) {
                    b = false;
                }
                else {
                    final float x = motionEvent.getX(n2);
                    final float y = motionEvent.getY(n2);
                    final float n4 = x - this.mInitialTouchX;
                    final float n5 = y - this.mInitialTouchY;
                    final float abs = Math.abs(n4);
                    final float abs2 = Math.abs(n5);
                    if (abs < this.mSlop && abs2 < this.mSlop) {
                        b = false;
                    }
                    else {
                        if (abs > abs2) {
                            if (n4 < 0.0f && (n3 & 0x4) == 0x0) {
                                b = false;
                                return b;
                            }
                            if (n4 > 0.0f && (n3 & 0x8) == 0x0) {
                                b = false;
                                return b;
                            }
                        }
                        else {
                            if (n5 < 0.0f && (n3 & 0x1) == 0x0) {
                                b = false;
                                return b;
                            }
                            if (n5 > 0.0f && (n3 & 0x2) == 0x0) {
                                b = false;
                                return b;
                            }
                        }
                        this.mDy = 0.0f;
                        this.mDx = 0.0f;
                        this.mActivePointerId = motionEvent.getPointerId(0);
                        this.select(swipedView, 1);
                        b = true;
                    }
                }
            }
        }
        return b;
    }
    
    int endRecoverAnimation(final ViewHolder viewHolder, final boolean b) {
        for (int i = -1 + this.mRecoverAnimations.size(); i >= 0; --i) {
            final RecoverAnimation recoverAnimation = this.mRecoverAnimations.get(i);
            if (recoverAnimation.mViewHolder == viewHolder) {
                recoverAnimation.mOverridden |= b;
                if (!recoverAnimation.mEnded) {
                    recoverAnimation.cancel();
                }
                this.mRecoverAnimations.remove(i);
                return recoverAnimation.mAnimationType;
            }
        }
        return 0;
    }
    
    RecoverAnimation findAnimation(final MotionEvent motionEvent) {
        RecoverAnimation recoverAnimation;
        if (this.mRecoverAnimations.isEmpty()) {
            recoverAnimation = null;
        }
        else {
            final View childView = this.findChildView(motionEvent);
            for (int i = -1 + this.mRecoverAnimations.size(); i >= 0; --i) {
                recoverAnimation = this.mRecoverAnimations.get(i);
                if (recoverAnimation.mViewHolder.itemView == childView) {
                    return recoverAnimation;
                }
            }
            recoverAnimation = null;
        }
        return recoverAnimation;
    }
    
    View findChildView(final MotionEvent motionEvent) {
        final float x = motionEvent.getX();
        final float y = motionEvent.getY();
        Label_0057: {
            if (this.mSelected == null) {
                break Label_0057;
            }
            final View view = this.mSelected.itemView;
            if (!hitTest(view, x, y, this.mSelectedStartX + this.mDx, this.mSelectedStartY + this.mDy)) {
                break Label_0057;
            }
            return view;
        }
        for (int i = -1 + this.mRecoverAnimations.size(); i >= 0; --i) {
            final RecoverAnimation recoverAnimation = this.mRecoverAnimations.get(i);
            final View itemView = recoverAnimation.mViewHolder.itemView;
            if (hitTest(itemView, x, y, recoverAnimation.mX, recoverAnimation.mY)) {
                return itemView;
            }
        }
        return this.mRecyclerView.findChildViewUnder(x, y);
    }
    
    @Override
    public void getItemOffsets(final Rect rect, final View view, final RecyclerView recyclerView, final State state) {
        rect.setEmpty();
    }
    
    boolean hasRunningRecoverAnim() {
        for (int size = this.mRecoverAnimations.size(), i = 0; i < size; ++i) {
            if (!this.mRecoverAnimations.get(i).mEnded) {
                return true;
            }
        }
        return false;
    }
    
    void moveIfNecessary(final ViewHolder viewHolder) {
        if (!this.mRecyclerView.isLayoutRequested() && this.mActionState == 2) {
            final float moveThreshold = this.mCallback.getMoveThreshold(viewHolder);
            final int n = (int)(this.mSelectedStartX + this.mDx);
            final int n2 = (int)(this.mSelectedStartY + this.mDy);
            if (Math.abs(n2 - viewHolder.itemView.getTop()) >= moveThreshold * viewHolder.itemView.getHeight() || Math.abs(n - viewHolder.itemView.getLeft()) >= moveThreshold * viewHolder.itemView.getWidth()) {
                final List<ViewHolder> swapTargets = this.findSwapTargets(viewHolder);
                if (swapTargets.size() != 0) {
                    final ViewHolder chooseDropTarget = this.mCallback.chooseDropTarget(viewHolder, swapTargets, n, n2);
                    if (chooseDropTarget == null) {
                        this.mSwapTargets.clear();
                        this.mDistances.clear();
                    }
                    else {
                        final int adapterPosition = chooseDropTarget.getAdapterPosition();
                        final int adapterPosition2 = viewHolder.getAdapterPosition();
                        if (this.mCallback.onMove(this.mRecyclerView, viewHolder, chooseDropTarget)) {
                            this.mCallback.onMoved(this.mRecyclerView, viewHolder, adapterPosition2, chooseDropTarget, adapterPosition, n, n2);
                        }
                    }
                }
            }
        }
    }
    
    void obtainVelocityTracker() {
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
        }
        this.mVelocityTracker = VelocityTracker.obtain();
    }
    
    @Override
    public void onChildViewAttachedToWindow(final View view) {
    }
    
    @Override
    public void onChildViewDetachedFromWindow(final View view) {
        this.removeChildDrawingOrderCallbackIfNecessary(view);
        final RecyclerView.ViewHolder childViewHolder = this.mRecyclerView.getChildViewHolder(view);
        if (childViewHolder != null) {
            if (this.mSelected != null && childViewHolder == this.mSelected) {
                this.select(null, 0);
            }
            else {
                this.endRecoverAnimation(childViewHolder, false);
                if (this.mPendingCleanup.remove(childViewHolder.itemView)) {
                    this.mCallback.clearView(this.mRecyclerView, childViewHolder);
                }
            }
        }
    }
    
    @Override
    public void onDraw(final Canvas canvas, final RecyclerView recyclerView, final State state) {
        this.mOverdrawChildPosition = -1;
        final ViewHolder mSelected = this.mSelected;
        float n = 0.0f;
        float n2 = 0.0f;
        if (mSelected != null) {
            this.getSelectedDxDy(this.mTmpPosition);
            n = this.mTmpPosition[0];
            n2 = this.mTmpPosition[1];
        }
        this.mCallback.onDraw(canvas, recyclerView, this.mSelected, this.mRecoverAnimations, this.mActionState, n, n2);
    }
    
    @Override
    public void onDrawOver(final Canvas canvas, final RecyclerView recyclerView, final State state) {
        final ViewHolder mSelected = this.mSelected;
        float n = 0.0f;
        float n2 = 0.0f;
        if (mSelected != null) {
            this.getSelectedDxDy(this.mTmpPosition);
            n = this.mTmpPosition[0];
            n2 = this.mTmpPosition[1];
        }
        this.mCallback.onDrawOver(canvas, recyclerView, this.mSelected, this.mRecoverAnimations, this.mActionState, n, n2);
    }
    
    void postDispatchSwipe(final RecoverAnimation recoverAnimation, final int n) {
        this.mRecyclerView.post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (ItemTouchHelper.this.mRecyclerView != null && ItemTouchHelper.this.mRecyclerView.isAttachedToWindow() && !recoverAnimation.mOverridden && recoverAnimation.mViewHolder.getAdapterPosition() != -1) {
                    final RecyclerView.ItemAnimator itemAnimator = ItemTouchHelper.this.mRecyclerView.getItemAnimator();
                    if ((itemAnimator == null || !itemAnimator.isRunning(null)) && !ItemTouchHelper.this.hasRunningRecoverAnim()) {
                        ItemTouchHelper.this.mCallback.onSwiped(recoverAnimation.mViewHolder, n);
                    }
                    else {
                        ItemTouchHelper.this.mRecyclerView.post((Runnable)this);
                    }
                }
            }
        });
    }
    
    void removeChildDrawingOrderCallbackIfNecessary(final View view) {
        if (view == this.mOverdrawChild) {
            this.mOverdrawChild = null;
            if (this.mChildDrawingOrderCallback != null) {
                this.mRecyclerView.setChildDrawingOrderCallback(null);
            }
        }
    }
    
    boolean scrollIfNecessary() {
        boolean b;
        if (this.mSelected == null) {
            this.mDragScrollStartTimeInMs = Long.MIN_VALUE;
            b = false;
        }
        else {
            final long currentTimeMillis = System.currentTimeMillis();
            long n;
            if (this.mDragScrollStartTimeInMs == Long.MIN_VALUE) {
                n = 0L;
            }
            else {
                n = currentTimeMillis - this.mDragScrollStartTimeInMs;
            }
            final RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
            if (this.mTmpRect == null) {
                this.mTmpRect = new Rect();
            }
            layoutManager.calculateItemDecorationsForChild(this.mSelected.itemView, this.mTmpRect);
            final boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
            int interpolateOutOfBoundsScroll = 0;
            if (canScrollHorizontally) {
                final int n2 = (int)(this.mSelectedStartX + this.mDx);
                final int n3 = n2 - this.mTmpRect.left - this.mRecyclerView.getPaddingLeft();
                if (this.mDx < 0.0f && n3 < 0) {
                    interpolateOutOfBoundsScroll = n3;
                }
                else {
                    final float n4 = fcmpl(this.mDx, 0.0f);
                    interpolateOutOfBoundsScroll = 0;
                    if (n4 > 0) {
                        final int n5 = n2 + this.mSelected.itemView.getWidth() + this.mTmpRect.right - (this.mRecyclerView.getWidth() - this.mRecyclerView.getPaddingRight());
                        interpolateOutOfBoundsScroll = 0;
                        if (n5 > 0) {
                            interpolateOutOfBoundsScroll = n5;
                        }
                    }
                }
            }
            final boolean canScrollVertically = layoutManager.canScrollVertically();
            int interpolateOutOfBoundsScroll2 = 0;
            if (canScrollVertically) {
                final int n6 = (int)(this.mSelectedStartY + this.mDy);
                final int n7 = n6 - this.mTmpRect.top - this.mRecyclerView.getPaddingTop();
                if (this.mDy < 0.0f && n7 < 0) {
                    interpolateOutOfBoundsScroll2 = n7;
                }
                else {
                    final float n8 = fcmpl(this.mDy, 0.0f);
                    interpolateOutOfBoundsScroll2 = 0;
                    if (n8 > 0) {
                        final int n9 = n6 + this.mSelected.itemView.getHeight() + this.mTmpRect.bottom - (this.mRecyclerView.getHeight() - this.mRecyclerView.getPaddingBottom());
                        interpolateOutOfBoundsScroll2 = 0;
                        if (n9 > 0) {
                            interpolateOutOfBoundsScroll2 = n9;
                        }
                    }
                }
            }
            if (interpolateOutOfBoundsScroll != 0) {
                interpolateOutOfBoundsScroll = this.mCallback.interpolateOutOfBoundsScroll(this.mRecyclerView, this.mSelected.itemView.getWidth(), interpolateOutOfBoundsScroll, this.mRecyclerView.getWidth(), n);
            }
            if (interpolateOutOfBoundsScroll2 != 0) {
                interpolateOutOfBoundsScroll2 = this.mCallback.interpolateOutOfBoundsScroll(this.mRecyclerView, this.mSelected.itemView.getHeight(), interpolateOutOfBoundsScroll2, this.mRecyclerView.getHeight(), n);
            }
            if (interpolateOutOfBoundsScroll != 0 || interpolateOutOfBoundsScroll2 != 0) {
                if (this.mDragScrollStartTimeInMs == Long.MIN_VALUE) {
                    this.mDragScrollStartTimeInMs = currentTimeMillis;
                }
                this.mRecyclerView.scrollBy(interpolateOutOfBoundsScroll, interpolateOutOfBoundsScroll2);
                b = true;
            }
            else {
                this.mDragScrollStartTimeInMs = Long.MIN_VALUE;
                b = false;
            }
        }
        return b;
    }
    
    void select(final ViewHolder mSelected, final int mActionState) {
        if (mSelected != this.mSelected || mActionState != this.mActionState) {
            this.mDragScrollStartTimeInMs = Long.MIN_VALUE;
            final int mActionState2 = this.mActionState;
            this.endRecoverAnimation(mSelected, true);
            if ((this.mActionState = mActionState) == 2) {
                this.mOverdrawChild = mSelected.itemView;
                this.addChildDrawingOrderCallback();
            }
            final int n = -1 + (1 << 8 + mActionState * 8);
            final ViewHolder mSelected2 = this.mSelected;
            int n2 = 0;
            if (mSelected2 != null) {
                final ViewHolder mSelected3 = this.mSelected;
                if (mSelected3.itemView.getParent() != null) {
                    int swipeIfNecessary;
                    if (mActionState2 == 2) {
                        swipeIfNecessary = 0;
                    }
                    else {
                        swipeIfNecessary = this.swipeIfNecessary(mSelected3);
                    }
                    this.releaseVelocityTracker();
                    float n3 = 0.0f;
                    float n4 = 0.0f;
                    switch (swipeIfNecessary) {
                        default: {
                            n3 = 0.0f;
                            n4 = 0.0f;
                            break;
                        }
                        case 4:
                        case 8:
                        case 16:
                        case 32: {
                            n3 = Math.signum(this.mDx) * this.mRecyclerView.getWidth();
                            n4 = 0.0f;
                            break;
                        }
                        case 1:
                        case 2: {
                            n4 = Math.signum(this.mDy) * this.mRecyclerView.getHeight();
                            n3 = 0.0f;
                            break;
                        }
                    }
                    int n5;
                    if (mActionState2 == 2) {
                        n5 = 8;
                    }
                    else if (swipeIfNecessary > 0) {
                        n5 = 2;
                    }
                    else {
                        n5 = 4;
                    }
                    this.getSelectedDxDy(this.mTmpPosition);
                    final float n6 = this.mTmpPosition[0];
                    final float n7 = this.mTmpPosition[1];
                    final RecoverAnimation recoverAnimation = new RecoverAnimation(mSelected3, n5, mActionState2, n6, n7, n3, n4) {
                        @Override
                        public void onAnimationEnd(final ValueAnimatorCompat valueAnimatorCompat) {
                            super.onAnimationEnd(valueAnimatorCompat);
                            if (!this.mOverridden) {
                                if (swipeIfNecessary <= 0) {
                                    ItemTouchHelper.this.mCallback.clearView(ItemTouchHelper.this.mRecyclerView, mSelected3);
                                }
                                else {
                                    ItemTouchHelper.this.mPendingCleanup.add(mSelected3.itemView);
                                    this.mIsPendingCleanup = true;
                                    if (swipeIfNecessary > 0) {
                                        ItemTouchHelper.this.postDispatchSwipe((RecoverAnimation)this, swipeIfNecessary);
                                    }
                                }
                                if (ItemTouchHelper.this.mOverdrawChild == mSelected3.itemView) {
                                    ItemTouchHelper.this.removeChildDrawingOrderCallbackIfNecessary(mSelected3.itemView);
                                }
                            }
                        }
                    };
                    ((RecoverAnimation)recoverAnimation).setDuration(this.mCallback.getAnimationDuration(this.mRecyclerView, n5, n3 - n6, n4 - n7));
                    this.mRecoverAnimations.add((RecoverAnimation)recoverAnimation);
                    ((RecoverAnimation)recoverAnimation).start();
                    n2 = 1;
                }
                else {
                    this.removeChildDrawingOrderCallbackIfNecessary(mSelected3.itemView);
                    this.mCallback.clearView(this.mRecyclerView, mSelected3);
                    n2 = 0;
                }
                this.mSelected = null;
            }
            if (mSelected != null) {
                this.mSelectedFlags = (n & this.mCallback.getAbsoluteMovementFlags(this.mRecyclerView, mSelected)) >> 8 * this.mActionState;
                this.mSelectedStartX = mSelected.itemView.getLeft();
                this.mSelectedStartY = mSelected.itemView.getTop();
                this.mSelected = mSelected;
                if (mActionState == 2) {
                    this.mSelected.itemView.performHapticFeedback(0);
                }
            }
            final ViewParent parent = this.mRecyclerView.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(this.mSelected != null);
            }
            if (n2 == 0) {
                this.mRecyclerView.getLayoutManager().requestSimpleAnimationsInNextLayout();
            }
            this.mCallback.onSelectedChanged(this.mSelected, this.mActionState);
            this.mRecyclerView.invalidate();
        }
    }
    
    public void startDrag(final ViewHolder viewHolder) {
        if (!this.mCallback.hasDragFlag(this.mRecyclerView, viewHolder)) {
            Log.e("ItemTouchHelper", "Start drag has been called but swiping is not enabled");
        }
        else if (viewHolder.itemView.getParent() != this.mRecyclerView) {
            Log.e("ItemTouchHelper", "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
        }
        else {
            this.obtainVelocityTracker();
            this.mDy = 0.0f;
            this.mDx = 0.0f;
            this.select(viewHolder, 2);
        }
    }
    
    public void startSwipe(final ViewHolder viewHolder) {
        if (!this.mCallback.hasSwipeFlag(this.mRecyclerView, viewHolder)) {
            Log.e("ItemTouchHelper", "Start swipe has been called but dragging is not enabled");
        }
        else if (viewHolder.itemView.getParent() != this.mRecyclerView) {
            Log.e("ItemTouchHelper", "Start swipe has been called with a view holder which is not a child of the RecyclerView controlled by this ItemTouchHelper.");
        }
        else {
            this.obtainVelocityTracker();
            this.mDy = 0.0f;
            this.mDx = 0.0f;
            this.select(viewHolder, 1);
        }
    }
    
    void updateDxDy(final MotionEvent motionEvent, final int n, final int n2) {
        final float x = motionEvent.getX(n2);
        final float y = motionEvent.getY(n2);
        this.mDx = x - this.mInitialTouchX;
        this.mDy = y - this.mInitialTouchY;
        if ((n & 0x4) == 0x0) {
            this.mDx = Math.max(0.0f, this.mDx);
        }
        if ((n & 0x8) == 0x0) {
            this.mDx = Math.min(0.0f, this.mDx);
        }
        if ((n & 0x1) == 0x0) {
            this.mDy = Math.max(0.0f, this.mDy);
        }
        if ((n & 0x2) == 0x0) {
            this.mDy = Math.min(0.0f, this.mDy);
        }
    }
    
    public abstract static class Callback
    {
        private static final int ABS_HORIZONTAL_DIR_FLAGS = 789516;
        public static final int DEFAULT_DRAG_ANIMATION_DURATION = 200;
        public static final int DEFAULT_SWIPE_ANIMATION_DURATION = 250;
        private static final long DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS = 2000L;
        static final int RELATIVE_DIR_FLAGS = 3158064;
        private static final Interpolator sDragScrollInterpolator;
        private static final Interpolator sDragViewScrollCapInterpolator;
        private static final ItemTouchUIUtil sUICallback;
        private int mCachedMaxScrollSpeed;
        
        static {
            sDragScrollInterpolator = (Interpolator)new Interpolator() {
                public float getInterpolation(final float n) {
                    return n * (n * (n * (n * n)));
                }
            };
            sDragViewScrollCapInterpolator = (Interpolator)new Interpolator() {
                public float getInterpolation(final float n) {
                    final float n2 = n - 1.0f;
                    return 1.0f + n2 * (n2 * (n2 * (n2 * n2)));
                }
            };
            if (Build$VERSION.SDK_INT >= 21) {
                sUICallback = new ItemTouchUIUtilImpl.Lollipop();
            }
            else if (Build$VERSION.SDK_INT >= 11) {
                sUICallback = new ItemTouchUIUtilImpl.Honeycomb();
            }
            else {
                sUICallback = new ItemTouchUIUtilImpl.Gingerbread();
            }
        }
        
        public Callback() {
            this.mCachedMaxScrollSpeed = -1;
        }
        
        public static int convertToRelativeDirection(int n, final int n2) {
            final int n3 = n & 0xC0C0C;
            if (n3 != 0) {
                final int n4 = n & ~n3;
                if (n2 == 0) {
                    n = (n4 | n3 << 2);
                }
                else {
                    n = (n4 | (0xFFF3F3F3 & n3 << 1) | (0xC0C0C & n3 << 1) << 2);
                }
            }
            return n;
        }
        
        public static ItemTouchUIUtil getDefaultUIUtil() {
            return Callback.sUICallback;
        }
        
        private int getMaxDragScroll(final RecyclerView recyclerView) {
            if (this.mCachedMaxScrollSpeed == -1) {
                this.mCachedMaxScrollSpeed = recyclerView.getResources().getDimensionPixelSize(R.dimen.item_touch_helper_max_drag_scroll_per_frame);
            }
            return this.mCachedMaxScrollSpeed;
        }
        
        public static int makeFlag(final int n, final int n2) {
            return n2 << n * 8;
        }
        
        public static int makeMovementFlags(final int n, final int n2) {
            return makeFlag(0, n2 | n) | makeFlag(1, n2) | makeFlag(2, n);
        }
        
        public boolean canDropOver(final RecyclerView recyclerView, final ViewHolder viewHolder, final ViewHolder viewHolder2) {
            return true;
        }
        
        public ViewHolder chooseDropTarget(final ViewHolder viewHolder, final List<ViewHolder> list, final int n, final int n2) {
            final int n3 = n + viewHolder.itemView.getWidth();
            final int n4 = n2 + viewHolder.itemView.getHeight();
            ViewHolder viewHolder2 = null;
            int n5 = -1;
            final int n6 = n - viewHolder.itemView.getLeft();
            final int n7 = n2 - viewHolder.itemView.getTop();
            for (int size = list.size(), i = 0; i < size; ++i) {
                final ViewHolder viewHolder3 = list.get(i);
                if (n6 > 0) {
                    final int n8 = viewHolder3.itemView.getRight() - n3;
                    if (n8 < 0 && viewHolder3.itemView.getRight() > viewHolder.itemView.getRight()) {
                        final int abs = Math.abs(n8);
                        if (abs > n5) {
                            n5 = abs;
                            viewHolder2 = viewHolder3;
                        }
                    }
                }
                if (n6 < 0) {
                    final int n9 = viewHolder3.itemView.getLeft() - n;
                    if (n9 > 0 && viewHolder3.itemView.getLeft() < viewHolder.itemView.getLeft()) {
                        final int abs2 = Math.abs(n9);
                        if (abs2 > n5) {
                            n5 = abs2;
                            viewHolder2 = viewHolder3;
                        }
                    }
                }
                if (n7 < 0) {
                    final int n10 = viewHolder3.itemView.getTop() - n2;
                    if (n10 > 0 && viewHolder3.itemView.getTop() < viewHolder.itemView.getTop()) {
                        final int abs3 = Math.abs(n10);
                        if (abs3 > n5) {
                            n5 = abs3;
                            viewHolder2 = viewHolder3;
                        }
                    }
                }
                if (n7 > 0) {
                    final int n11 = viewHolder3.itemView.getBottom() - n4;
                    if (n11 < 0 && viewHolder3.itemView.getBottom() > viewHolder.itemView.getBottom()) {
                        final int abs4 = Math.abs(n11);
                        if (abs4 > n5) {
                            n5 = abs4;
                            viewHolder2 = viewHolder3;
                        }
                    }
                }
            }
            return viewHolder2;
        }
        
        public void clearView(final RecyclerView recyclerView, final ViewHolder viewHolder) {
            Callback.sUICallback.clearView(viewHolder.itemView);
        }
        
        public int convertToAbsoluteDirection(final int n, final int n2) {
            final int n3 = n & 0x303030;
            int n4;
            if (n3 == 0) {
                n4 = n;
            }
            else {
                final int n5 = n & ~n3;
                if (n2 == 0) {
                    n4 = (n5 | n3 >> 2);
                }
                else {
                    n4 = (n5 | (0xFFCFCFCF & n3 >> 1) | (0x303030 & n3 >> 1) >> 2);
                }
            }
            return n4;
        }
        
        final int getAbsoluteMovementFlags(final RecyclerView recyclerView, final ViewHolder viewHolder) {
            return this.convertToAbsoluteDirection(this.getMovementFlags(recyclerView, viewHolder), ViewCompat.getLayoutDirection((View)recyclerView));
        }
        
        public long getAnimationDuration(final RecyclerView recyclerView, final int n, final float n2, final float n3) {
            final RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
            long n4;
            if (itemAnimator == null) {
                if (n == 8) {
                    n4 = 200L;
                }
                else {
                    n4 = 250L;
                }
            }
            else if (n == 8) {
                n4 = itemAnimator.getMoveDuration();
            }
            else {
                n4 = itemAnimator.getRemoveDuration();
            }
            return n4;
        }
        
        public int getBoundingBoxMargin() {
            return 0;
        }
        
        public float getMoveThreshold(final ViewHolder viewHolder) {
            return 0.5f;
        }
        
        public abstract int getMovementFlags(final RecyclerView p0, final ViewHolder p1);
        
        public float getSwipeEscapeVelocity(final float n) {
            return n;
        }
        
        public float getSwipeThreshold(final ViewHolder viewHolder) {
            return 0.5f;
        }
        
        public float getSwipeVelocityThreshold(final float n) {
            return n;
        }
        
        boolean hasDragFlag(final RecyclerView recyclerView, final ViewHolder viewHolder) {
            return (0xFF0000 & this.getAbsoluteMovementFlags(recyclerView, viewHolder)) != 0x0;
        }
        
        boolean hasSwipeFlag(final RecyclerView recyclerView, final ViewHolder viewHolder) {
            return (0xFF00 & this.getAbsoluteMovementFlags(recyclerView, viewHolder)) != 0x0;
        }
        
        public int interpolateOutOfBoundsScroll(final RecyclerView recyclerView, final int n, final int n2, final int n3, final long n4) {
            final int n5 = (int)((int)Math.signum(n2) * this.getMaxDragScroll(recyclerView) * Callback.sDragViewScrollCapInterpolator.getInterpolation(Math.min(1.0f, 1.0f * Math.abs(n2) / n)));
            float n6;
            if (n4 > 2000L) {
                n6 = 1.0f;
            }
            else {
                n6 = n4 / 2000.0f;
            }
            final int n7 = (int)(n5 * Callback.sDragScrollInterpolator.getInterpolation(n6));
            int n8;
            if (n7 == 0) {
                if (n2 > 0) {
                    n8 = 1;
                }
                else {
                    n8 = -1;
                }
            }
            else {
                n8 = n7;
            }
            return n8;
        }
        
        public boolean isItemViewSwipeEnabled() {
            return true;
        }
        
        public boolean isLongPressDragEnabled() {
            return true;
        }
        
        public void onChildDraw(final Canvas canvas, final RecyclerView recyclerView, final ViewHolder viewHolder, final float n, final float n2, final int n3, final boolean b) {
            Callback.sUICallback.onDraw(canvas, recyclerView, viewHolder.itemView, n, n2, n3, b);
        }
        
        public void onChildDrawOver(final Canvas canvas, final RecyclerView recyclerView, final ViewHolder viewHolder, final float n, final float n2, final int n3, final boolean b) {
            Callback.sUICallback.onDrawOver(canvas, recyclerView, viewHolder.itemView, n, n2, n3, b);
        }
        
        void onDraw(final Canvas canvas, final RecyclerView recyclerView, final ViewHolder viewHolder, final List<RecoverAnimation> list, final int n, final float n2, final float n3) {
            for (int size = list.size(), i = 0; i < size; ++i) {
                final RecoverAnimation recoverAnimation = list.get(i);
                recoverAnimation.update();
                final int save = canvas.save();
                this.onChildDraw(canvas, recyclerView, recoverAnimation.mViewHolder, recoverAnimation.mX, recoverAnimation.mY, recoverAnimation.mActionState, false);
                canvas.restoreToCount(save);
            }
            if (viewHolder != null) {
                final int save2 = canvas.save();
                this.onChildDraw(canvas, recyclerView, viewHolder, n2, n3, n, true);
                canvas.restoreToCount(save2);
            }
        }
        
        void onDrawOver(final Canvas canvas, final RecyclerView recyclerView, final ViewHolder viewHolder, final List<RecoverAnimation> list, final int n, final float n2, final float n3) {
            final int size = list.size();
            for (int i = 0; i < size; ++i) {
                final RecoverAnimation recoverAnimation = list.get(i);
                final int save = canvas.save();
                this.onChildDrawOver(canvas, recyclerView, recoverAnimation.mViewHolder, recoverAnimation.mX, recoverAnimation.mY, recoverAnimation.mActionState, false);
                canvas.restoreToCount(save);
            }
            if (viewHolder != null) {
                final int save2 = canvas.save();
                this.onChildDrawOver(canvas, recyclerView, viewHolder, n2, n3, n, true);
                canvas.restoreToCount(save2);
            }
            boolean b = false;
            for (int j = size - 1; j >= 0; --j) {
                final RecoverAnimation recoverAnimation2 = list.get(j);
                if (recoverAnimation2.mEnded && !recoverAnimation2.mIsPendingCleanup) {
                    list.remove(j);
                }
                else if (!recoverAnimation2.mEnded) {
                    b = true;
                }
            }
            if (b) {
                recyclerView.invalidate();
            }
        }
        
        public abstract boolean onMove(final RecyclerView p0, final ViewHolder p1, final ViewHolder p2);
        
        public void onMoved(final RecyclerView recyclerView, final ViewHolder viewHolder, final int n, final ViewHolder viewHolder2, final int n2, final int n3, final int n4) {
            final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof ViewDropHandler) {
                ((ViewDropHandler)layoutManager).prepareForDrop(viewHolder.itemView, viewHolder2.itemView, n3, n4);
            }
            else {
                if (layoutManager.canScrollHorizontally()) {
                    if (layoutManager.getDecoratedLeft(viewHolder2.itemView) <= recyclerView.getPaddingLeft()) {
                        recyclerView.scrollToPosition(n2);
                    }
                    if (layoutManager.getDecoratedRight(viewHolder2.itemView) >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                        recyclerView.scrollToPosition(n2);
                    }
                }
                if (layoutManager.canScrollVertically()) {
                    if (layoutManager.getDecoratedTop(viewHolder2.itemView) <= recyclerView.getPaddingTop()) {
                        recyclerView.scrollToPosition(n2);
                    }
                    if (layoutManager.getDecoratedBottom(viewHolder2.itemView) >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                        recyclerView.scrollToPosition(n2);
                    }
                }
            }
        }
        
        public void onSelectedChanged(final ViewHolder viewHolder, final int n) {
            if (viewHolder != null) {
                Callback.sUICallback.onSelected(viewHolder.itemView);
            }
        }
        
        public abstract void onSwiped(final ViewHolder p0, final int p1);
    }
    
    private class ItemTouchHelperGestureListener extends GestureDetector$SimpleOnGestureListener
    {
        public boolean onDown(final MotionEvent motionEvent) {
            return true;
        }
        
        public void onLongPress(final MotionEvent motionEvent) {
            final View childView = ItemTouchHelper.this.findChildView(motionEvent);
            if (childView != null) {
                final RecyclerView.ViewHolder childViewHolder = ItemTouchHelper.this.mRecyclerView.getChildViewHolder(childView);
                if (childViewHolder != null && ItemTouchHelper.this.mCallback.hasDragFlag(ItemTouchHelper.this.mRecyclerView, childViewHolder) && motionEvent.getPointerId(0) == ItemTouchHelper.this.mActivePointerId) {
                    final int pointerIndex = motionEvent.findPointerIndex(ItemTouchHelper.this.mActivePointerId);
                    final float x = motionEvent.getX(pointerIndex);
                    final float y = motionEvent.getY(pointerIndex);
                    ItemTouchHelper.this.mInitialTouchX = x;
                    ItemTouchHelper.this.mInitialTouchY = y;
                    final ItemTouchHelper this$0 = ItemTouchHelper.this;
                    ItemTouchHelper.this.mDy = 0.0f;
                    this$0.mDx = 0.0f;
                    if (ItemTouchHelper.this.mCallback.isLongPressDragEnabled()) {
                        ItemTouchHelper.this.select(childViewHolder, 2);
                    }
                }
            }
        }
    }
    
    private class RecoverAnimation implements AnimatorListenerCompat
    {
        final int mActionState;
        final int mAnimationType;
        boolean mEnded;
        private float mFraction;
        public boolean mIsPendingCleanup;
        boolean mOverridden;
        final float mStartDx;
        final float mStartDy;
        final float mTargetX;
        final float mTargetY;
        private final ValueAnimatorCompat mValueAnimator;
        final ViewHolder mViewHolder;
        float mX;
        float mY;
        
        public RecoverAnimation(final ViewHolder mViewHolder, final int mAnimationType, final int mActionState, final float mStartDx, final float mStartDy, final float mTargetX, final float mTargetY) {
            this.mOverridden = false;
            this.mEnded = false;
            this.mActionState = mActionState;
            this.mAnimationType = mAnimationType;
            this.mViewHolder = mViewHolder;
            this.mStartDx = mStartDx;
            this.mStartDy = mStartDy;
            this.mTargetX = mTargetX;
            this.mTargetY = mTargetY;
            (this.mValueAnimator = AnimatorCompatHelper.emptyValueAnimator()).addUpdateListener(new AnimatorUpdateListenerCompat() {
                @Override
                public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
                    RecoverAnimation.this.setFraction(valueAnimatorCompat.getAnimatedFraction());
                }
            });
            this.mValueAnimator.setTarget(mViewHolder.itemView);
            this.mValueAnimator.addListener(this);
            this.setFraction(0.0f);
        }
        
        public void cancel() {
            this.mValueAnimator.cancel();
        }
        
        @Override
        public void onAnimationCancel(final ValueAnimatorCompat valueAnimatorCompat) {
            this.setFraction(1.0f);
        }
        
        @Override
        public void onAnimationEnd(final ValueAnimatorCompat valueAnimatorCompat) {
            if (!this.mEnded) {
                this.mViewHolder.setIsRecyclable(true);
            }
            this.mEnded = true;
        }
        
        @Override
        public void onAnimationRepeat(final ValueAnimatorCompat valueAnimatorCompat) {
        }
        
        @Override
        public void onAnimationStart(final ValueAnimatorCompat valueAnimatorCompat) {
        }
        
        public void setDuration(final long duration) {
            this.mValueAnimator.setDuration(duration);
        }
        
        public void setFraction(final float mFraction) {
            this.mFraction = mFraction;
        }
        
        public void start() {
            this.mViewHolder.setIsRecyclable(false);
            this.mValueAnimator.start();
        }
        
        public void update() {
            if (this.mStartDx == this.mTargetX) {
                this.mX = ViewCompat.getTranslationX(this.mViewHolder.itemView);
            }
            else {
                this.mX = this.mStartDx + this.mFraction * (this.mTargetX - this.mStartDx);
            }
            if (this.mStartDy == this.mTargetY) {
                this.mY = ViewCompat.getTranslationY(this.mViewHolder.itemView);
            }
            else {
                this.mY = this.mStartDy + this.mFraction * (this.mTargetY - this.mStartDy);
            }
        }
    }
    
    public abstract static class SimpleCallback extends Callback
    {
        private int mDefaultDragDirs;
        private int mDefaultSwipeDirs;
        
        public SimpleCallback(final int mDefaultDragDirs, final int mDefaultSwipeDirs) {
            this.mDefaultSwipeDirs = mDefaultSwipeDirs;
            this.mDefaultDragDirs = mDefaultDragDirs;
        }
        
        public int getDragDirs(final RecyclerView recyclerView, final ViewHolder viewHolder) {
            return this.mDefaultDragDirs;
        }
        
        @Override
        public int getMovementFlags(final RecyclerView recyclerView, final ViewHolder viewHolder) {
            return Callback.makeMovementFlags(this.getDragDirs(recyclerView, viewHolder), this.getSwipeDirs(recyclerView, viewHolder));
        }
        
        public int getSwipeDirs(final RecyclerView recyclerView, final ViewHolder viewHolder) {
            return this.mDefaultSwipeDirs;
        }
        
        public void setDefaultDragDirs(final int mDefaultDragDirs) {
            this.mDefaultDragDirs = mDefaultDragDirs;
        }
        
        public void setDefaultSwipeDirs(final int mDefaultSwipeDirs) {
            this.mDefaultSwipeDirs = mDefaultSwipeDirs;
        }
    }
    
    public interface ViewDropHandler
    {
        void prepareForDrop(final View p0, final View p1, final int p2, final int p3);
    }
}
