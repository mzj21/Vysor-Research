// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.support.v4.os.ParcelableCompat;
import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.os.Parcelable$Creator;
import android.support.v4.view.AbsSavedState;
import android.content.res.TypedArray;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.os.Parcelable;
import android.view.View$MeasureSpec;
import android.view.KeyEvent;
import android.support.v4.view.MotionEventCompat;
import android.view.ViewGroup$MarginLayoutParams;
import android.support.v4.view.GravityCompat;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.os.SystemClock;
import android.view.ViewGroup$LayoutParams;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewGroupCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.graphics.drawable.Drawable;
import android.graphics.Paint;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

public class DrawerLayout extends ViewGroup implements DrawerLayoutImpl
{
    private static final boolean ALLOW_EDGE_LOCK = false;
    static final boolean CAN_HIDE_DESCENDANTS = false;
    private static final boolean CHILDREN_DISALLOW_INTERCEPT = true;
    private static final int DEFAULT_SCRIM_COLOR = -1728053248;
    private static final int DRAWER_ELEVATION = 10;
    static final DrawerLayoutCompatImpl IMPL;
    static final int[] LAYOUT_ATTRS;
    public static final int LOCK_MODE_LOCKED_CLOSED = 1;
    public static final int LOCK_MODE_LOCKED_OPEN = 2;
    public static final int LOCK_MODE_UNDEFINED = 3;
    public static final int LOCK_MODE_UNLOCKED = 0;
    private static final int MIN_DRAWER_MARGIN = 64;
    private static final int MIN_FLING_VELOCITY = 400;
    private static final int PEEK_DELAY = 160;
    private static final boolean SET_DRAWER_SHADOW_FROM_ELEVATION = false;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "DrawerLayout";
    private static final float TOUCH_SLOP_SENSITIVITY = 1.0f;
    private final ChildAccessibilityDelegate mChildAccessibilityDelegate;
    private boolean mChildrenCanceledTouch;
    private boolean mDisallowInterceptRequested;
    private boolean mDrawStatusBarBackground;
    private float mDrawerElevation;
    private int mDrawerState;
    private boolean mFirstLayout;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private Object mLastInsets;
    private final ViewDragCallback mLeftCallback;
    private final ViewDragHelper mLeftDragger;
    @Nullable
    private DrawerListener mListener;
    private List<DrawerListener> mListeners;
    private int mLockModeEnd;
    private int mLockModeLeft;
    private int mLockModeRight;
    private int mLockModeStart;
    private int mMinDrawerMargin;
    private final ArrayList<View> mNonDrawerViews;
    private final ViewDragCallback mRightCallback;
    private final ViewDragHelper mRightDragger;
    private int mScrimColor;
    private float mScrimOpacity;
    private Paint mScrimPaint;
    private Drawable mShadowEnd;
    private Drawable mShadowLeft;
    private Drawable mShadowLeftResolved;
    private Drawable mShadowRight;
    private Drawable mShadowRightResolved;
    private Drawable mShadowStart;
    private Drawable mStatusBarBackground;
    private CharSequence mTitleLeft;
    private CharSequence mTitleRight;
    
    static {
        int set_DRAWER_SHADOW_FROM_ELEVATION = 1;
        final int[] layout_ATTRS = new int[set_DRAWER_SHADOW_FROM_ELEVATION];
        layout_ATTRS[0] = 16842931;
        LAYOUT_ATTRS = layout_ATTRS;
        boolean can_HIDE_DESCENDANTS;
        if (Build$VERSION.SDK_INT >= 19) {
            can_HIDE_DESCENDANTS = (set_DRAWER_SHADOW_FROM_ELEVATION != 0);
        }
        else {
            can_HIDE_DESCENDANTS = false;
        }
        if (Build$VERSION.SDK_INT < 21) {
            set_DRAWER_SHADOW_FROM_ELEVATION = 0;
        }
        if (Build$VERSION.SDK_INT >= 21) {
            IMPL = (DrawerLayoutCompatImpl)new DrawerLayoutCompatImplApi21();
        }
        else {
            IMPL = (DrawerLayoutCompatImpl)new DrawerLayoutCompatImplBase();
        }
    }
    
    public DrawerLayout(final Context context) {
        this(context, null);
    }
    
    public DrawerLayout(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public DrawerLayout(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mChildAccessibilityDelegate = new ChildAccessibilityDelegate();
        this.mScrimColor = -1728053248;
        this.mScrimPaint = new Paint();
        this.mFirstLayout = true;
        this.mLockModeLeft = 3;
        this.mLockModeRight = 3;
        this.mLockModeStart = 3;
        this.mLockModeEnd = 3;
        this.mShadowStart = null;
        this.mShadowEnd = null;
        this.mShadowLeft = null;
        this.mShadowRight = null;
        this.setDescendantFocusability(262144);
        final float density = this.getResources().getDisplayMetrics().density;
        this.mMinDrawerMargin = (int)(0.5f + 64.0f * density);
        final float n2 = 400.0f * density;
        this.mLeftCallback = new ViewDragCallback(3);
        this.mRightCallback = new ViewDragCallback(5);
        (this.mLeftDragger = ViewDragHelper.create(this, 1.0f, (ViewDragHelper.Callback)this.mLeftCallback)).setEdgeTrackingEnabled(1);
        this.mLeftDragger.setMinVelocity(n2);
        this.mLeftCallback.setDragger(this.mLeftDragger);
        (this.mRightDragger = ViewDragHelper.create(this, 1.0f, (ViewDragHelper.Callback)this.mRightCallback)).setEdgeTrackingEnabled(2);
        this.mRightDragger.setMinVelocity(n2);
        this.mRightCallback.setDragger(this.mRightDragger);
        this.setFocusableInTouchMode(true);
        ViewCompat.setImportantForAccessibility((View)this, 1);
        ViewCompat.setAccessibilityDelegate((View)this, new AccessibilityDelegate());
        ViewGroupCompat.setMotionEventSplittingEnabled(this, false);
        if (ViewCompat.getFitsSystemWindows((View)this)) {
            DrawerLayout.IMPL.configureApplyInsets((View)this);
            this.mStatusBarBackground = DrawerLayout.IMPL.getDefaultStatusBarBackground(context);
        }
        this.mDrawerElevation = 10.0f * density;
        this.mNonDrawerViews = new ArrayList<View>();
    }
    
    static String gravityToString(final int n) {
        String hexString;
        if ((n & 0x3) == 0x3) {
            hexString = "LEFT";
        }
        else if ((n & 0x5) == 0x5) {
            hexString = "RIGHT";
        }
        else {
            hexString = Integer.toHexString(n);
        }
        return hexString;
    }
    
    private static boolean hasOpaqueBackground(final View view) {
        final Drawable background = view.getBackground();
        boolean b = false;
        if (background != null) {
            final int opacity = background.getOpacity();
            b = false;
            if (opacity == -1) {
                b = true;
            }
        }
        return b;
    }
    
    private boolean hasPeekingDrawer() {
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            if (((LayoutParams)this.getChildAt(i).getLayoutParams()).isPeeking) {
                return true;
            }
        }
        return false;
    }
    
    private boolean hasVisibleDrawer() {
        return this.findVisibleDrawer() != null;
    }
    
    static boolean includeChildForAccessibility(final View view) {
        return ViewCompat.getImportantForAccessibility(view) != 4 && ViewCompat.getImportantForAccessibility(view) != 2;
    }
    
    private boolean mirror(final Drawable drawable, final int n) {
        boolean b;
        if (drawable == null || !DrawableCompat.isAutoMirrored(drawable)) {
            b = false;
        }
        else {
            DrawableCompat.setLayoutDirection(drawable, n);
            b = true;
        }
        return b;
    }
    
    private Drawable resolveLeftShadow() {
        final int layoutDirection = ViewCompat.getLayoutDirection((View)this);
        Drawable drawable;
        if (layoutDirection == 0) {
            if (this.mShadowStart == null) {
                return this.mShadowLeft;
            }
            this.mirror(this.mShadowStart, layoutDirection);
            drawable = this.mShadowStart;
        }
        else {
            if (this.mShadowEnd == null) {
                return this.mShadowLeft;
            }
            this.mirror(this.mShadowEnd, layoutDirection);
            drawable = this.mShadowEnd;
        }
        return drawable;
        drawable = this.mShadowLeft;
        return drawable;
    }
    
    private Drawable resolveRightShadow() {
        final int layoutDirection = ViewCompat.getLayoutDirection((View)this);
        Drawable drawable;
        if (layoutDirection == 0) {
            if (this.mShadowEnd == null) {
                return this.mShadowRight;
            }
            this.mirror(this.mShadowEnd, layoutDirection);
            drawable = this.mShadowEnd;
        }
        else {
            if (this.mShadowStart == null) {
                return this.mShadowRight;
            }
            this.mirror(this.mShadowStart, layoutDirection);
            drawable = this.mShadowStart;
        }
        return drawable;
        drawable = this.mShadowRight;
        return drawable;
    }
    
    private void resolveShadowDrawables() {
        if (!DrawerLayout.SET_DRAWER_SHADOW_FROM_ELEVATION) {
            this.mShadowLeftResolved = this.resolveLeftShadow();
            this.mShadowRightResolved = this.resolveRightShadow();
        }
    }
    
    private void updateChildrenImportantForAccessibility(final View view, final boolean b) {
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if ((!b && !this.isDrawerView(child)) || (b && child == view)) {
                ViewCompat.setImportantForAccessibility(child, 1);
            }
            else {
                ViewCompat.setImportantForAccessibility(child, 4);
            }
        }
    }
    
    public void addDrawerListener(@NonNull final DrawerListener drawerListener) {
        if (drawerListener != null) {
            if (this.mListeners == null) {
                this.mListeners = new ArrayList<DrawerListener>();
            }
            this.mListeners.add(drawerListener);
        }
    }
    
    public void addFocusables(final ArrayList<View> list, final int n, final int n2) {
        if (this.getDescendantFocusability() != 393216) {
            final int childCount = this.getChildCount();
            boolean b = false;
            for (int i = 0; i < childCount; ++i) {
                final View child = this.getChildAt(i);
                if (this.isDrawerView(child)) {
                    if (this.isDrawerOpen(child)) {
                        b = true;
                        child.addFocusables((ArrayList)list, n, n2);
                    }
                }
                else {
                    this.mNonDrawerViews.add(child);
                }
            }
            if (!b) {
                for (int size = this.mNonDrawerViews.size(), j = 0; j < size; ++j) {
                    final View view = this.mNonDrawerViews.get(j);
                    if (view.getVisibility() == 0) {
                        view.addFocusables((ArrayList)list, n, n2);
                    }
                }
            }
            this.mNonDrawerViews.clear();
        }
    }
    
    public void addView(final View view, final int n, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super.addView(view, n, viewGroup$LayoutParams);
        if (this.findOpenDrawer() != null || this.isDrawerView(view)) {
            ViewCompat.setImportantForAccessibility(view, 4);
        }
        else {
            ViewCompat.setImportantForAccessibility(view, 1);
        }
        if (!DrawerLayout.CAN_HIDE_DESCENDANTS) {
            ViewCompat.setAccessibilityDelegate(view, this.mChildAccessibilityDelegate);
        }
    }
    
    void cancelChildViewTouch() {
        if (!this.mChildrenCanceledTouch) {
            final long uptimeMillis = SystemClock.uptimeMillis();
            final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
                this.getChildAt(i).dispatchTouchEvent(obtain);
            }
            obtain.recycle();
            this.mChildrenCanceledTouch = true;
        }
    }
    
    boolean checkDrawerViewAbsoluteGravity(final View view, final int n) {
        return (n & this.getDrawerViewAbsoluteGravity(view)) == n;
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof LayoutParams && super.checkLayoutParams(viewGroup$LayoutParams);
    }
    
    public void closeDrawer(final int n) {
        this.closeDrawer(n, true);
    }
    
    public void closeDrawer(final int n, final boolean b) {
        final View drawerWithGravity = this.findDrawerWithGravity(n);
        if (drawerWithGravity == null) {
            throw new IllegalArgumentException("No drawer view found with gravity " + gravityToString(n));
        }
        this.closeDrawer(drawerWithGravity, b);
    }
    
    public void closeDrawer(final View view) {
        this.closeDrawer(view, true);
    }
    
    public void closeDrawer(final View view, final boolean b) {
        if (!this.isDrawerView(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (this.mFirstLayout) {
            layoutParams.onScreen = 0.0f;
            layoutParams.openState = 0;
        }
        else if (b) {
            layoutParams.openState |= 0x4;
            if (this.checkDrawerViewAbsoluteGravity(view, 3)) {
                this.mLeftDragger.smoothSlideViewTo(view, -view.getWidth(), view.getTop());
            }
            else {
                this.mRightDragger.smoothSlideViewTo(view, this.getWidth(), view.getTop());
            }
        }
        else {
            this.moveDrawerToOffset(view, 0.0f);
            this.updateDrawerState(layoutParams.gravity, 0, view);
            view.setVisibility(4);
        }
        this.invalidate();
    }
    
    public void closeDrawers() {
        this.closeDrawers(false);
    }
    
    void closeDrawers(final boolean b) {
        boolean b2 = false;
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            if (this.isDrawerView(child) && (!b || layoutParams.isPeeking)) {
                final int width = child.getWidth();
                if (this.checkDrawerViewAbsoluteGravity(child, 3)) {
                    b2 |= this.mLeftDragger.smoothSlideViewTo(child, -width, child.getTop());
                }
                else {
                    b2 |= this.mRightDragger.smoothSlideViewTo(child, this.getWidth(), child.getTop());
                }
                layoutParams.isPeeking = false;
            }
        }
        this.mLeftCallback.removeCallbacks();
        this.mRightCallback.removeCallbacks();
        if (b2) {
            this.invalidate();
        }
    }
    
    public void computeScroll() {
        final int childCount = this.getChildCount();
        float max = 0.0f;
        for (int i = 0; i < childCount; ++i) {
            max = Math.max(max, ((LayoutParams)this.getChildAt(i).getLayoutParams()).onScreen);
        }
        this.mScrimOpacity = max;
        if (this.mLeftDragger.continueSettling(true) | this.mRightDragger.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation((View)this);
        }
    }
    
    void dispatchOnDrawerClosed(final View view) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if ((0x1 & layoutParams.openState) == 0x1) {
            layoutParams.openState = 0;
            if (this.mListeners != null) {
                for (int i = -1 + this.mListeners.size(); i >= 0; --i) {
                    this.mListeners.get(i).onDrawerClosed(view);
                }
            }
            this.updateChildrenImportantForAccessibility(view, false);
            if (this.hasWindowFocus()) {
                final View rootView = this.getRootView();
                if (rootView != null) {
                    rootView.sendAccessibilityEvent(32);
                }
            }
        }
    }
    
    void dispatchOnDrawerOpened(final View view) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if ((0x1 & layoutParams.openState) == 0x0) {
            layoutParams.openState = 1;
            if (this.mListeners != null) {
                for (int i = -1 + this.mListeners.size(); i >= 0; --i) {
                    this.mListeners.get(i).onDrawerOpened(view);
                }
            }
            this.updateChildrenImportantForAccessibility(view, true);
            if (this.hasWindowFocus()) {
                this.sendAccessibilityEvent(32);
            }
            view.requestFocus();
        }
    }
    
    void dispatchOnDrawerSlide(final View view, final float n) {
        if (this.mListeners != null) {
            for (int i = -1 + this.mListeners.size(); i >= 0; --i) {
                this.mListeners.get(i).onDrawerSlide(view, n);
            }
        }
    }
    
    protected boolean drawChild(final Canvas canvas, final View view, final long n) {
        final int height = this.getHeight();
        final boolean contentView = this.isContentView(view);
        int width = this.getWidth();
        final int save = canvas.save();
        int n2 = 0;
        if (contentView) {
            for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
                final View child = this.getChildAt(i);
                if (child != view && child.getVisibility() == 0 && hasOpaqueBackground(child) && this.isDrawerView(child) && child.getHeight() >= height) {
                    if (this.checkDrawerViewAbsoluteGravity(child, 3)) {
                        final int right = child.getRight();
                        if (right > n2) {
                            n2 = right;
                        }
                    }
                    else {
                        final int left = child.getLeft();
                        if (left < width) {
                            width = left;
                        }
                    }
                }
            }
            canvas.clipRect(n2, 0, width, this.getHeight());
        }
        final boolean drawChild = super.drawChild(canvas, view, n);
        canvas.restoreToCount(save);
        if (this.mScrimOpacity > 0.0f && contentView) {
            this.mScrimPaint.setColor((int)(((0xFF000000 & this.mScrimColor) >>> 24) * this.mScrimOpacity) << 24 | (0xFFFFFF & this.mScrimColor));
            canvas.drawRect((float)n2, 0.0f, (float)width, (float)this.getHeight(), this.mScrimPaint);
        }
        else if (this.mShadowLeftResolved != null && this.checkDrawerViewAbsoluteGravity(view, 3)) {
            final int intrinsicWidth = this.mShadowLeftResolved.getIntrinsicWidth();
            final int right2 = view.getRight();
            final float max = Math.max(0.0f, Math.min(right2 / this.mLeftDragger.getEdgeSize(), 1.0f));
            this.mShadowLeftResolved.setBounds(right2, view.getTop(), right2 + intrinsicWidth, view.getBottom());
            this.mShadowLeftResolved.setAlpha((int)(255.0f * max));
            this.mShadowLeftResolved.draw(canvas);
        }
        else if (this.mShadowRightResolved != null && this.checkDrawerViewAbsoluteGravity(view, 5)) {
            final int intrinsicWidth2 = this.mShadowRightResolved.getIntrinsicWidth();
            final int left2 = view.getLeft();
            final float max2 = Math.max(0.0f, Math.min((this.getWidth() - left2) / this.mRightDragger.getEdgeSize(), 1.0f));
            this.mShadowRightResolved.setBounds(left2 - intrinsicWidth2, view.getTop(), left2, view.getBottom());
            this.mShadowRightResolved.setAlpha((int)(255.0f * max2));
            this.mShadowRightResolved.draw(canvas);
        }
        return drawChild;
    }
    
    View findDrawerWithGravity(final int n) {
        final int n2 = 0x7 & GravityCompat.getAbsoluteGravity(n, ViewCompat.getLayoutDirection((View)this));
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if ((0x7 & this.getDrawerViewAbsoluteGravity(child)) == n2) {
                return child;
            }
        }
        return null;
    }
    
    View findOpenDrawer() {
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if ((0x1 & ((LayoutParams)child.getLayoutParams()).openState) == 0x1) {
                return child;
            }
        }
        return null;
    }
    
    View findVisibleDrawer() {
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (this.isDrawerView(child) && this.isDrawerVisible(child)) {
                return child;
            }
        }
        return null;
    }
    
    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return (ViewGroup$LayoutParams)new LayoutParams(-1, -1);
    }
    
    public ViewGroup$LayoutParams generateLayoutParams(final AttributeSet set) {
        return (ViewGroup$LayoutParams)new LayoutParams(this.getContext(), set);
    }
    
    protected ViewGroup$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
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
        return (ViewGroup$LayoutParams)layoutParams;
    }
    
    public float getDrawerElevation() {
        float mDrawerElevation;
        if (DrawerLayout.SET_DRAWER_SHADOW_FROM_ELEVATION) {
            mDrawerElevation = this.mDrawerElevation;
        }
        else {
            mDrawerElevation = 0.0f;
        }
        return mDrawerElevation;
    }
    
    public int getDrawerLockMode(final int n) {
        final int layoutDirection = ViewCompat.getLayoutDirection((View)this);
        switch (n) {
            case 3: {
                if (this.mLockModeLeft != 3) {
                    return this.mLockModeLeft;
                }
                int n2;
                if (layoutDirection == 0) {
                    n2 = this.mLockModeStart;
                }
                else {
                    n2 = this.mLockModeEnd;
                }
                if (n2 != 3) {
                    return n2;
                }
                break;
            }
            case 5: {
                if (this.mLockModeRight != 3) {
                    return this.mLockModeRight;
                }
                int n3;
                if (layoutDirection == 0) {
                    n3 = this.mLockModeEnd;
                }
                else {
                    n3 = this.mLockModeStart;
                }
                if (n3 != 3) {
                    return n3;
                }
                break;
            }
            case 8388611: {
                if (this.mLockModeStart != 3) {
                    return this.mLockModeStart;
                }
                int n4;
                if (layoutDirection == 0) {
                    n4 = this.mLockModeLeft;
                }
                else {
                    n4 = this.mLockModeRight;
                }
                if (n4 != 3) {
                    return n4;
                }
                break;
            }
            case 8388613: {
                if (this.mLockModeEnd != 3) {
                    return this.mLockModeEnd;
                }
                int n5;
                if (layoutDirection == 0) {
                    n5 = this.mLockModeRight;
                }
                else {
                    n5 = this.mLockModeLeft;
                }
                if (n5 != 3) {
                    return n5;
                }
                break;
            }
        }
        return 0;
    }
    
    public int getDrawerLockMode(final View view) {
        if (!this.isDrawerView(view)) {
            throw new IllegalArgumentException("View " + view + " is not a drawer");
        }
        return this.getDrawerLockMode(((LayoutParams)view.getLayoutParams()).gravity);
    }
    
    @Nullable
    public CharSequence getDrawerTitle(final int n) {
        final int absoluteGravity = GravityCompat.getAbsoluteGravity(n, ViewCompat.getLayoutDirection((View)this));
        CharSequence charSequence;
        if (absoluteGravity == 3) {
            charSequence = this.mTitleLeft;
        }
        else if (absoluteGravity == 5) {
            charSequence = this.mTitleRight;
        }
        else {
            charSequence = null;
        }
        return charSequence;
    }
    
    int getDrawerViewAbsoluteGravity(final View view) {
        return GravityCompat.getAbsoluteGravity(((LayoutParams)view.getLayoutParams()).gravity, ViewCompat.getLayoutDirection((View)this));
    }
    
    float getDrawerViewOffset(final View view) {
        return ((LayoutParams)view.getLayoutParams()).onScreen;
    }
    
    public Drawable getStatusBarBackgroundDrawable() {
        return this.mStatusBarBackground;
    }
    
    boolean isContentView(final View view) {
        return ((LayoutParams)view.getLayoutParams()).gravity == 0;
    }
    
    public boolean isDrawerOpen(final int n) {
        final View drawerWithGravity = this.findDrawerWithGravity(n);
        return drawerWithGravity != null && this.isDrawerOpen(drawerWithGravity);
    }
    
    public boolean isDrawerOpen(final View view) {
        boolean b = true;
        if (!this.isDrawerView(view)) {
            throw new IllegalArgumentException("View " + view + " is not a drawer");
        }
        if ((0x1 & ((LayoutParams)view.getLayoutParams()).openState) != (b ? 1 : 0)) {
            b = false;
        }
        return b;
    }
    
    boolean isDrawerView(final View view) {
        final int absoluteGravity = GravityCompat.getAbsoluteGravity(((LayoutParams)view.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(view));
        return (absoluteGravity & 0x3) != 0x0 || (absoluteGravity & 0x5) != 0x0;
    }
    
    public boolean isDrawerVisible(final int n) {
        final View drawerWithGravity = this.findDrawerWithGravity(n);
        return drawerWithGravity != null && this.isDrawerVisible(drawerWithGravity);
    }
    
    public boolean isDrawerVisible(final View view) {
        if (!this.isDrawerView(view)) {
            throw new IllegalArgumentException("View " + view + " is not a drawer");
        }
        return ((LayoutParams)view.getLayoutParams()).onScreen > 0.0f;
    }
    
    void moveDrawerToOffset(final View view, final float n) {
        final float drawerViewOffset = this.getDrawerViewOffset(view);
        final int width = view.getWidth();
        int n2 = (int)(n * width) - (int)(drawerViewOffset * width);
        if (!this.checkDrawerViewAbsoluteGravity(view, 3)) {
            n2 = -n2;
        }
        view.offsetLeftAndRight(n2);
        this.setDrawerViewOffset(view, n);
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFirstLayout = true;
    }
    
    public void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (this.mDrawStatusBarBackground && this.mStatusBarBackground != null) {
            final int topInset = DrawerLayout.IMPL.getTopInset(this.mLastInsets);
            if (topInset > 0) {
                this.mStatusBarBackground.setBounds(0, 0, this.getWidth(), topInset);
                this.mStatusBarBackground.draw(canvas);
            }
        }
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        final boolean b = this.mLeftDragger.shouldInterceptTouchEvent(motionEvent) | this.mRightDragger.shouldInterceptTouchEvent(motionEvent);
        boolean b2 = false;
        switch (actionMasked) {
            case 0: {
                final float x = motionEvent.getX();
                final float y = motionEvent.getY();
                this.mInitialMotionX = x;
                this.mInitialMotionY = y;
                final float n = fcmpl(this.mScrimOpacity, 0.0f);
                b2 = false;
                if (n > 0) {
                    final View topChildUnder = this.mLeftDragger.findTopChildUnder((int)x, (int)y);
                    b2 = false;
                    if (topChildUnder != null) {
                        final boolean contentView = this.isContentView(topChildUnder);
                        b2 = false;
                        if (contentView) {
                            b2 = true;
                        }
                    }
                }
                this.mDisallowInterceptRequested = false;
                this.mChildrenCanceledTouch = false;
                break;
            }
            case 2: {
                final boolean checkTouchSlop = this.mLeftDragger.checkTouchSlop(3);
                b2 = false;
                if (checkTouchSlop) {
                    this.mLeftCallback.removeCallbacks();
                    this.mRightCallback.removeCallbacks();
                    b2 = false;
                    break;
                }
                break;
            }
            case 1:
            case 3: {
                this.closeDrawers(true);
                this.mDisallowInterceptRequested = false;
                this.mChildrenCanceledTouch = false;
                b2 = false;
                break;
            }
        }
        if (!b && !b2 && !this.hasPeekingDrawer()) {
            final boolean mChildrenCanceledTouch = this.mChildrenCanceledTouch;
            final boolean b3 = false;
            if (!mChildrenCanceledTouch) {
                return b3;
            }
        }
        return true;
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        boolean onKeyDown;
        if (n == 4 && this.hasVisibleDrawer()) {
            keyEvent.startTracking();
            onKeyDown = true;
        }
        else {
            onKeyDown = super.onKeyDown(n, keyEvent);
        }
        return onKeyDown;
    }
    
    public boolean onKeyUp(final int n, final KeyEvent keyEvent) {
        boolean onKeyUp;
        if (n == 4) {
            final View visibleDrawer = this.findVisibleDrawer();
            if (visibleDrawer != null && this.getDrawerLockMode(visibleDrawer) == 0) {
                this.closeDrawers();
            }
            onKeyUp = (visibleDrawer != null);
        }
        else {
            onKeyUp = super.onKeyUp(n, keyEvent);
        }
        return onKeyUp;
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        this.mInLayout = true;
        final int n5 = n3 - n;
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (child.getVisibility() != 8) {
                final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
                if (this.isContentView(child)) {
                    child.layout(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + child.getMeasuredWidth(), layoutParams.topMargin + child.getMeasuredHeight());
                }
                else {
                    final int measuredWidth = child.getMeasuredWidth();
                    final int measuredHeight = child.getMeasuredHeight();
                    int n6;
                    float n7;
                    if (this.checkDrawerViewAbsoluteGravity(child, 3)) {
                        n6 = -measuredWidth + (int)(measuredWidth * layoutParams.onScreen);
                        n7 = (measuredWidth + n6) / measuredWidth;
                    }
                    else {
                        n6 = n5 - (int)(measuredWidth * layoutParams.onScreen);
                        n7 = (n5 - n6) / measuredWidth;
                    }
                    int n8;
                    if (n7 != layoutParams.onScreen) {
                        n8 = 1;
                    }
                    else {
                        n8 = 0;
                    }
                    switch (0x70 & layoutParams.gravity) {
                        default: {
                            child.layout(n6, layoutParams.topMargin, n6 + measuredWidth, measuredHeight + layoutParams.topMargin);
                            break;
                        }
                        case 80: {
                            final int n9 = n4 - n2;
                            child.layout(n6, n9 - layoutParams.bottomMargin - child.getMeasuredHeight(), n6 + measuredWidth, n9 - layoutParams.bottomMargin);
                            break;
                        }
                        case 16: {
                            final int n10 = n4 - n2;
                            int topMargin = (n10 - measuredHeight) / 2;
                            if (topMargin < layoutParams.topMargin) {
                                topMargin = layoutParams.topMargin;
                            }
                            else if (topMargin + measuredHeight > n10 - layoutParams.bottomMargin) {
                                topMargin = n10 - layoutParams.bottomMargin - measuredHeight;
                            }
                            child.layout(n6, topMargin, n6 + measuredWidth, topMargin + measuredHeight);
                            break;
                        }
                    }
                    if (n8 != 0) {
                        this.setDrawerViewOffset(child, n7);
                    }
                    int visibility;
                    if (layoutParams.onScreen > 0.0f) {
                        visibility = 0;
                    }
                    else {
                        visibility = 4;
                    }
                    if (child.getVisibility() != visibility) {
                        child.setVisibility(visibility);
                    }
                }
            }
        }
        this.mInLayout = false;
        this.mFirstLayout = false;
    }
    
    protected void onMeasure(final int n, final int n2) {
        final int mode = View$MeasureSpec.getMode(n);
        final int mode2 = View$MeasureSpec.getMode(n2);
        int size = View$MeasureSpec.getSize(n);
        int size2 = View$MeasureSpec.getSize(n2);
        if (mode != 1073741824 || mode2 != 1073741824) {
            if (!this.isInEditMode()) {
                throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
            }
            if (mode != Integer.MIN_VALUE && mode == 0) {
                size = 300;
            }
            if (mode2 != Integer.MIN_VALUE && mode2 == 0) {
                size2 = 300;
            }
        }
        this.setMeasuredDimension(size, size2);
        final boolean b = this.mLastInsets != null && ViewCompat.getFitsSystemWindows((View)this);
        final int layoutDirection = ViewCompat.getLayoutDirection((View)this);
        int n3 = 0;
        int n4 = 0;
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (child.getVisibility() != 8) {
                final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
                if (b) {
                    final int absoluteGravity = GravityCompat.getAbsoluteGravity(layoutParams.gravity, layoutDirection);
                    if (ViewCompat.getFitsSystemWindows(child)) {
                        DrawerLayout.IMPL.dispatchChildInsets(child, this.mLastInsets, absoluteGravity);
                    }
                    else {
                        DrawerLayout.IMPL.applyMarginInsets(layoutParams, this.mLastInsets, absoluteGravity);
                    }
                }
                if (this.isContentView(child)) {
                    child.measure(View$MeasureSpec.makeMeasureSpec(size - layoutParams.leftMargin - layoutParams.rightMargin, 1073741824), View$MeasureSpec.makeMeasureSpec(size2 - layoutParams.topMargin - layoutParams.bottomMargin, 1073741824));
                }
                else {
                    if (!this.isDrawerView(child)) {
                        throw new IllegalStateException("Child " + child + " at index " + i + " does not have a valid layout_gravity - must be Gravity.LEFT, " + "Gravity.RIGHT or Gravity.NO_GRAVITY");
                    }
                    if (DrawerLayout.SET_DRAWER_SHADOW_FROM_ELEVATION && ViewCompat.getElevation(child) != this.mDrawerElevation) {
                        ViewCompat.setElevation(child, this.mDrawerElevation);
                    }
                    final int n5 = 0x7 & this.getDrawerViewAbsoluteGravity(child);
                    boolean b2;
                    if (n5 == 3) {
                        b2 = true;
                    }
                    else {
                        b2 = false;
                    }
                    if ((b2 && n3 != 0) || (!b2 && n4 != 0)) {
                        throw new IllegalStateException("Child drawer has absolute gravity " + gravityToString(n5) + " but this " + "DrawerLayout" + " already has a " + "drawer view along that edge");
                    }
                    if (b2) {
                        n3 = 1;
                    }
                    else {
                        n4 = 1;
                    }
                    child.measure(getChildMeasureSpec(n, this.mMinDrawerMargin + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), getChildMeasureSpec(n2, layoutParams.topMargin + layoutParams.bottomMargin, layoutParams.height));
                }
            }
        }
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
        }
        else {
            final SavedState savedState = (SavedState)parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            if (savedState.openDrawerGravity != 0) {
                final View drawerWithGravity = this.findDrawerWithGravity(savedState.openDrawerGravity);
                if (drawerWithGravity != null) {
                    this.openDrawer(drawerWithGravity);
                }
            }
            if (savedState.lockModeLeft != 3) {
                this.setDrawerLockMode(savedState.lockModeLeft, 3);
            }
            if (savedState.lockModeRight != 3) {
                this.setDrawerLockMode(savedState.lockModeRight, 5);
            }
            if (savedState.lockModeStart != 3) {
                this.setDrawerLockMode(savedState.lockModeStart, 8388611);
            }
            if (savedState.lockModeEnd != 3) {
                this.setDrawerLockMode(savedState.lockModeEnd, 8388613);
            }
        }
    }
    
    public void onRtlPropertiesChanged(final int n) {
        this.resolveShadowDrawables();
    }
    
    protected Parcelable onSaveInstanceState() {
        final SavedState savedState = new SavedState(super.onSaveInstanceState());
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final LayoutParams layoutParams = (LayoutParams)this.getChildAt(i).getLayoutParams();
            boolean b;
            if (layoutParams.openState == 1) {
                b = true;
            }
            else {
                b = false;
            }
            boolean b2;
            if (layoutParams.openState == 2) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            if (b || b2) {
                savedState.openDrawerGravity = layoutParams.gravity;
                break;
            }
        }
        savedState.lockModeLeft = this.mLockModeLeft;
        savedState.lockModeRight = this.mLockModeRight;
        savedState.lockModeStart = this.mLockModeStart;
        savedState.lockModeEnd = this.mLockModeEnd;
        return (Parcelable)savedState;
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        this.mLeftDragger.processTouchEvent(motionEvent);
        this.mRightDragger.processTouchEvent(motionEvent);
        switch (0xFF & motionEvent.getAction()) {
            case 0: {
                final float x = motionEvent.getX();
                final float y = motionEvent.getY();
                this.mInitialMotionX = x;
                this.mInitialMotionY = y;
                this.mDisallowInterceptRequested = false;
                this.mChildrenCanceledTouch = false;
                break;
            }
            case 1: {
                final float x2 = motionEvent.getX();
                final float y2 = motionEvent.getY();
                boolean b = true;
                final View topChildUnder = this.mLeftDragger.findTopChildUnder((int)x2, (int)y2);
                if (topChildUnder != null && this.isContentView(topChildUnder)) {
                    final float n = x2 - this.mInitialMotionX;
                    final float n2 = y2 - this.mInitialMotionY;
                    final int touchSlop = this.mLeftDragger.getTouchSlop();
                    if (n * n + n2 * n2 < touchSlop * touchSlop) {
                        final View openDrawer = this.findOpenDrawer();
                        if (openDrawer != null) {
                            b = (this.getDrawerLockMode(openDrawer) == 2);
                        }
                    }
                }
                this.closeDrawers(b);
                this.mDisallowInterceptRequested = false;
                break;
            }
            case 3: {
                this.closeDrawers(true);
                this.mDisallowInterceptRequested = false;
                this.mChildrenCanceledTouch = false;
                break;
            }
        }
        return true;
    }
    
    public void openDrawer(final int n) {
        this.openDrawer(n, true);
    }
    
    public void openDrawer(final int n, final boolean b) {
        final View drawerWithGravity = this.findDrawerWithGravity(n);
        if (drawerWithGravity == null) {
            throw new IllegalArgumentException("No drawer view found with gravity " + gravityToString(n));
        }
        this.openDrawer(drawerWithGravity, b);
    }
    
    public void openDrawer(final View view) {
        this.openDrawer(view, true);
    }
    
    public void openDrawer(final View view, final boolean b) {
        if (!this.isDrawerView(view)) {
            throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
        }
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (this.mFirstLayout) {
            layoutParams.onScreen = 1.0f;
            layoutParams.openState = 1;
            this.updateChildrenImportantForAccessibility(view, true);
        }
        else if (b) {
            layoutParams.openState |= 0x2;
            if (this.checkDrawerViewAbsoluteGravity(view, 3)) {
                this.mLeftDragger.smoothSlideViewTo(view, 0, view.getTop());
            }
            else {
                this.mRightDragger.smoothSlideViewTo(view, this.getWidth() - view.getWidth(), view.getTop());
            }
        }
        else {
            this.moveDrawerToOffset(view, 1.0f);
            this.updateDrawerState(layoutParams.gravity, 0, view);
            view.setVisibility(0);
        }
        this.invalidate();
    }
    
    public void removeDrawerListener(@NonNull final DrawerListener drawerListener) {
        if (drawerListener != null && this.mListeners != null) {
            this.mListeners.remove(drawerListener);
        }
    }
    
    public void requestDisallowInterceptTouchEvent(final boolean mDisallowInterceptRequested) {
        super.requestDisallowInterceptTouchEvent(mDisallowInterceptRequested);
        this.mDisallowInterceptRequested = mDisallowInterceptRequested;
        if (mDisallowInterceptRequested) {
            this.closeDrawers(true);
        }
    }
    
    public void requestLayout() {
        if (!this.mInLayout) {
            super.requestLayout();
        }
    }
    
    public void setChildInsets(final Object mLastInsets, final boolean mDrawStatusBarBackground) {
        this.mLastInsets = mLastInsets;
        this.mDrawStatusBarBackground = mDrawStatusBarBackground;
        this.setWillNotDraw(!mDrawStatusBarBackground && this.getBackground() == null);
        this.requestLayout();
    }
    
    public void setDrawerElevation(final float mDrawerElevation) {
        this.mDrawerElevation = mDrawerElevation;
        for (int i = 0; i < this.getChildCount(); ++i) {
            final View child = this.getChildAt(i);
            if (this.isDrawerView(child)) {
                ViewCompat.setElevation(child, this.mDrawerElevation);
            }
        }
    }
    
    @Deprecated
    public void setDrawerListener(final DrawerListener mListener) {
        if (this.mListener != null) {
            this.removeDrawerListener(this.mListener);
        }
        if (mListener != null) {
            this.addDrawerListener(mListener);
        }
        this.mListener = mListener;
    }
    
    public void setDrawerLockMode(final int n) {
        this.setDrawerLockMode(n, 3);
        this.setDrawerLockMode(n, 5);
    }
    
    public void setDrawerLockMode(final int n, final int n2) {
        final int absoluteGravity = GravityCompat.getAbsoluteGravity(n2, ViewCompat.getLayoutDirection((View)this));
        switch (n2) {
            case 3: {
                this.mLockModeLeft = n;
                break;
            }
            case 5: {
                this.mLockModeRight = n;
                break;
            }
            case 8388611: {
                this.mLockModeStart = n;
                break;
            }
            case 8388613: {
                this.mLockModeEnd = n;
                break;
            }
        }
        if (n != 0) {
            ViewDragHelper viewDragHelper;
            if (absoluteGravity == 3) {
                viewDragHelper = this.mLeftDragger;
            }
            else {
                viewDragHelper = this.mRightDragger;
            }
            viewDragHelper.cancel();
        }
        switch (n) {
            case 2: {
                final View drawerWithGravity = this.findDrawerWithGravity(absoluteGravity);
                if (drawerWithGravity != null) {
                    this.openDrawer(drawerWithGravity);
                    break;
                }
                break;
            }
            case 1: {
                final View drawerWithGravity2 = this.findDrawerWithGravity(absoluteGravity);
                if (drawerWithGravity2 != null) {
                    this.closeDrawer(drawerWithGravity2);
                    break;
                }
                break;
            }
        }
    }
    
    public void setDrawerLockMode(final int n, final View view) {
        if (!this.isDrawerView(view)) {
            throw new IllegalArgumentException("View " + view + " is not a " + "drawer with appropriate layout_gravity");
        }
        this.setDrawerLockMode(n, ((LayoutParams)view.getLayoutParams()).gravity);
    }
    
    public void setDrawerShadow(@DrawableRes final int n, final int n2) {
        this.setDrawerShadow(this.getResources().getDrawable(n), n2);
    }
    
    public void setDrawerShadow(final Drawable drawable, final int n) {
        if (!DrawerLayout.SET_DRAWER_SHADOW_FROM_ELEVATION) {
            if ((n & 0x800003) == 0x800003) {
                this.mShadowStart = drawable;
            }
            else if ((n & 0x800005) == 0x800005) {
                this.mShadowEnd = drawable;
            }
            else if ((n & 0x3) == 0x3) {
                this.mShadowLeft = drawable;
            }
            else {
                if ((n & 0x5) != 0x5) {
                    return;
                }
                this.mShadowRight = drawable;
            }
            this.resolveShadowDrawables();
            this.invalidate();
        }
    }
    
    public void setDrawerTitle(final int n, final CharSequence charSequence) {
        final int absoluteGravity = GravityCompat.getAbsoluteGravity(n, ViewCompat.getLayoutDirection((View)this));
        if (absoluteGravity == 3) {
            this.mTitleLeft = charSequence;
        }
        else if (absoluteGravity == 5) {
            this.mTitleRight = charSequence;
        }
    }
    
    void setDrawerViewOffset(final View view, final float onScreen) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (onScreen != layoutParams.onScreen) {
            this.dispatchOnDrawerSlide(view, layoutParams.onScreen = onScreen);
        }
    }
    
    public void setScrimColor(@ColorInt final int mScrimColor) {
        this.mScrimColor = mScrimColor;
        this.invalidate();
    }
    
    public void setStatusBarBackground(final int n) {
        Drawable drawable;
        if (n != 0) {
            drawable = ContextCompat.getDrawable(this.getContext(), n);
        }
        else {
            drawable = null;
        }
        this.mStatusBarBackground = drawable;
        this.invalidate();
    }
    
    public void setStatusBarBackground(final Drawable mStatusBarBackground) {
        this.mStatusBarBackground = mStatusBarBackground;
        this.invalidate();
    }
    
    public void setStatusBarBackgroundColor(@ColorInt final int n) {
        this.mStatusBarBackground = (Drawable)new ColorDrawable(n);
        this.invalidate();
    }
    
    void updateDrawerState(final int n, final int n2, final View view) {
        final int viewDragState = this.mLeftDragger.getViewDragState();
        final int viewDragState2 = this.mRightDragger.getViewDragState();
        int mDrawerState;
        if (viewDragState == 1 || viewDragState2 == 1) {
            mDrawerState = 1;
        }
        else if (viewDragState == 2 || viewDragState2 == 2) {
            mDrawerState = 2;
        }
        else {
            mDrawerState = 0;
        }
        if (view != null && n2 == 0) {
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            if (layoutParams.onScreen == 0.0f) {
                this.dispatchOnDrawerClosed(view);
            }
            else if (layoutParams.onScreen == 1.0f) {
                this.dispatchOnDrawerOpened(view);
            }
        }
        if (mDrawerState != this.mDrawerState) {
            this.mDrawerState = mDrawerState;
            if (this.mListeners != null) {
                for (int i = -1 + this.mListeners.size(); i >= 0; --i) {
                    this.mListeners.get(i).onDrawerStateChanged(mDrawerState);
                }
            }
        }
    }
    
    class AccessibilityDelegate extends AccessibilityDelegateCompat
    {
        private final Rect mTmpRect;
        
        AccessibilityDelegate() {
            this.mTmpRect = new Rect();
        }
        
        private void addChildrenForAccessibility(final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, final ViewGroup viewGroup) {
            for (int childCount = viewGroup.getChildCount(), i = 0; i < childCount; ++i) {
                final View child = viewGroup.getChildAt(i);
                if (DrawerLayout.includeChildForAccessibility(child)) {
                    accessibilityNodeInfoCompat.addChild(child);
                }
            }
        }
        
        private void copyNodeInfoNoChildren(final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2) {
            final Rect mTmpRect = this.mTmpRect;
            accessibilityNodeInfoCompat2.getBoundsInParent(mTmpRect);
            accessibilityNodeInfoCompat.setBoundsInParent(mTmpRect);
            accessibilityNodeInfoCompat2.getBoundsInScreen(mTmpRect);
            accessibilityNodeInfoCompat.setBoundsInScreen(mTmpRect);
            accessibilityNodeInfoCompat.setVisibleToUser(accessibilityNodeInfoCompat2.isVisibleToUser());
            accessibilityNodeInfoCompat.setPackageName(accessibilityNodeInfoCompat2.getPackageName());
            accessibilityNodeInfoCompat.setClassName(accessibilityNodeInfoCompat2.getClassName());
            accessibilityNodeInfoCompat.setContentDescription(accessibilityNodeInfoCompat2.getContentDescription());
            accessibilityNodeInfoCompat.setEnabled(accessibilityNodeInfoCompat2.isEnabled());
            accessibilityNodeInfoCompat.setClickable(accessibilityNodeInfoCompat2.isClickable());
            accessibilityNodeInfoCompat.setFocusable(accessibilityNodeInfoCompat2.isFocusable());
            accessibilityNodeInfoCompat.setFocused(accessibilityNodeInfoCompat2.isFocused());
            accessibilityNodeInfoCompat.setAccessibilityFocused(accessibilityNodeInfoCompat2.isAccessibilityFocused());
            accessibilityNodeInfoCompat.setSelected(accessibilityNodeInfoCompat2.isSelected());
            accessibilityNodeInfoCompat.setLongClickable(accessibilityNodeInfoCompat2.isLongClickable());
            accessibilityNodeInfoCompat.addAction(accessibilityNodeInfoCompat2.getActions());
        }
        
        @Override
        public boolean dispatchPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
            boolean dispatchPopulateAccessibilityEvent;
            if (accessibilityEvent.getEventType() == 32) {
                final List text = accessibilityEvent.getText();
                final View visibleDrawer = DrawerLayout.this.findVisibleDrawer();
                if (visibleDrawer != null) {
                    final CharSequence drawerTitle = DrawerLayout.this.getDrawerTitle(DrawerLayout.this.getDrawerViewAbsoluteGravity(visibleDrawer));
                    if (drawerTitle != null) {
                        text.add(drawerTitle);
                    }
                }
                dispatchPopulateAccessibilityEvent = true;
            }
            else {
                dispatchPopulateAccessibilityEvent = super.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
            }
            return dispatchPopulateAccessibilityEvent;
        }
        
        @Override
        public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName((CharSequence)DrawerLayout.class.getName());
        }
        
        @Override
        public void onInitializeAccessibilityNodeInfo(final View source, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (DrawerLayout.CAN_HIDE_DESCENDANTS) {
                super.onInitializeAccessibilityNodeInfo(source, accessibilityNodeInfoCompat);
            }
            else {
                final AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain(accessibilityNodeInfoCompat);
                super.onInitializeAccessibilityNodeInfo(source, obtain);
                accessibilityNodeInfoCompat.setSource(source);
                final ViewParent parentForAccessibility = ViewCompat.getParentForAccessibility(source);
                if (parentForAccessibility instanceof View) {
                    accessibilityNodeInfoCompat.setParent((View)parentForAccessibility);
                }
                this.copyNodeInfoNoChildren(accessibilityNodeInfoCompat, obtain);
                obtain.recycle();
                this.addChildrenForAccessibility(accessibilityNodeInfoCompat, (ViewGroup)source);
            }
            accessibilityNodeInfoCompat.setClassName(DrawerLayout.class.getName());
            accessibilityNodeInfoCompat.setFocusable(false);
            accessibilityNodeInfoCompat.setFocused(false);
            accessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_FOCUS);
            accessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLEAR_FOCUS);
        }
        
        @Override
        public boolean onRequestSendAccessibilityEvent(final ViewGroup viewGroup, final View view, final AccessibilityEvent accessibilityEvent) {
            return (DrawerLayout.CAN_HIDE_DESCENDANTS || DrawerLayout.includeChildForAccessibility(view)) && super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }
    }
    
    final class ChildAccessibilityDelegate extends AccessibilityDelegateCompat
    {
        @Override
        public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            if (!DrawerLayout.includeChildForAccessibility(view)) {
                accessibilityNodeInfoCompat.setParent(null);
            }
        }
    }
    
    interface DrawerLayoutCompatImpl
    {
        void applyMarginInsets(final ViewGroup$MarginLayoutParams p0, final Object p1, final int p2);
        
        void configureApplyInsets(final View p0);
        
        void dispatchChildInsets(final View p0, final Object p1, final int p2);
        
        Drawable getDefaultStatusBarBackground(final Context p0);
        
        int getTopInset(final Object p0);
    }
    
    static class DrawerLayoutCompatImplApi21 implements DrawerLayoutCompatImpl
    {
        @Override
        public void applyMarginInsets(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final Object o, final int n) {
            DrawerLayoutCompatApi21.applyMarginInsets(viewGroup$MarginLayoutParams, o, n);
        }
        
        @Override
        public void configureApplyInsets(final View view) {
            DrawerLayoutCompatApi21.configureApplyInsets(view);
        }
        
        @Override
        public void dispatchChildInsets(final View view, final Object o, final int n) {
            DrawerLayoutCompatApi21.dispatchChildInsets(view, o, n);
        }
        
        @Override
        public Drawable getDefaultStatusBarBackground(final Context context) {
            return DrawerLayoutCompatApi21.getDefaultStatusBarBackground(context);
        }
        
        @Override
        public int getTopInset(final Object o) {
            return DrawerLayoutCompatApi21.getTopInset(o);
        }
    }
    
    static class DrawerLayoutCompatImplBase implements DrawerLayoutCompatImpl
    {
        @Override
        public void applyMarginInsets(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final Object o, final int n) {
        }
        
        @Override
        public void configureApplyInsets(final View view) {
        }
        
        @Override
        public void dispatchChildInsets(final View view, final Object o, final int n) {
        }
        
        @Override
        public Drawable getDefaultStatusBarBackground(final Context context) {
            return null;
        }
        
        @Override
        public int getTopInset(final Object o) {
            return 0;
        }
    }
    
    public interface DrawerListener
    {
        void onDrawerClosed(final View p0);
        
        void onDrawerOpened(final View p0);
        
        void onDrawerSlide(final View p0, final float p1);
        
        void onDrawerStateChanged(final int p0);
    }
    
    public static class LayoutParams extends ViewGroup$MarginLayoutParams
    {
        private static final int FLAG_IS_CLOSING = 4;
        private static final int FLAG_IS_OPENED = 1;
        private static final int FLAG_IS_OPENING = 2;
        public int gravity;
        boolean isPeeking;
        float onScreen;
        int openState;
        
        public LayoutParams(final int n, final int n2) {
            super(n, n2);
            this.gravity = 0;
        }
        
        public LayoutParams(final int n, final int n2, final int gravity) {
            this(n, n2);
            this.gravity = gravity;
        }
        
        public LayoutParams(final Context context, final AttributeSet set) {
            super(context, set);
            this.gravity = 0;
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, DrawerLayout.LAYOUT_ATTRS);
            this.gravity = obtainStyledAttributes.getInt(0, 0);
            obtainStyledAttributes.recycle();
        }
        
        public LayoutParams(final LayoutParams layoutParams) {
            super((ViewGroup$MarginLayoutParams)layoutParams);
            this.gravity = 0;
            this.gravity = layoutParams.gravity;
        }
        
        public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
            this.gravity = 0;
        }
        
        public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            super(viewGroup$MarginLayoutParams);
            this.gravity = 0;
        }
    }
    
    protected static class SavedState extends AbsSavedState
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        int lockModeEnd;
        int lockModeLeft;
        int lockModeRight;
        int lockModeStart;
        int openDrawerGravity;
        
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
        
        public SavedState(final Parcel parcel, final ClassLoader classLoader) {
            super(parcel, classLoader);
            this.openDrawerGravity = 0;
            this.openDrawerGravity = parcel.readInt();
            this.lockModeLeft = parcel.readInt();
            this.lockModeRight = parcel.readInt();
            this.lockModeStart = parcel.readInt();
            this.lockModeEnd = parcel.readInt();
        }
        
        public SavedState(final Parcelable parcelable) {
            super(parcelable);
            this.openDrawerGravity = 0;
        }
        
        @Override
        public void writeToParcel(final Parcel parcel, final int n) {
            super.writeToParcel(parcel, n);
            parcel.writeInt(this.openDrawerGravity);
            parcel.writeInt(this.lockModeLeft);
            parcel.writeInt(this.lockModeRight);
            parcel.writeInt(this.lockModeStart);
            parcel.writeInt(this.lockModeEnd);
        }
    }
    
    public abstract static class SimpleDrawerListener implements DrawerListener
    {
        @Override
        public void onDrawerClosed(final View view) {
        }
        
        @Override
        public void onDrawerOpened(final View view) {
        }
        
        @Override
        public void onDrawerSlide(final View view, final float n) {
        }
        
        @Override
        public void onDrawerStateChanged(final int n) {
        }
    }
    
    private class ViewDragCallback extends Callback
    {
        private final int mAbsGravity;
        private ViewDragHelper mDragger;
        private final Runnable mPeekRunnable;
        
        ViewDragCallback(final int mAbsGravity) {
            this.mPeekRunnable = new Runnable() {
                @Override
                public void run() {
                    ViewDragCallback.this.peekDrawer();
                }
            };
            this.mAbsGravity = mAbsGravity;
        }
        
        private void closeOtherDrawer() {
            int n = 3;
            if (this.mAbsGravity == n) {
                n = 5;
            }
            final View drawerWithGravity = DrawerLayout.this.findDrawerWithGravity(n);
            if (drawerWithGravity != null) {
                DrawerLayout.this.closeDrawer(drawerWithGravity);
            }
        }
        
        @Override
        public int clampViewPositionHorizontal(final View view, final int n, final int n2) {
            int n3;
            if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, 3)) {
                n3 = Math.max(-view.getWidth(), Math.min(n, 0));
            }
            else {
                final int width = DrawerLayout.this.getWidth();
                n3 = Math.max(width - view.getWidth(), Math.min(n, width));
            }
            return n3;
        }
        
        @Override
        public int clampViewPositionVertical(final View view, final int n, final int n2) {
            return view.getTop();
        }
        
        @Override
        public int getViewHorizontalDragRange(final View view) {
            int width;
            if (DrawerLayout.this.isDrawerView(view)) {
                width = view.getWidth();
            }
            else {
                width = 0;
            }
            return width;
        }
        
        @Override
        public void onEdgeDragStarted(final int n, final int n2) {
            View view;
            if ((n & 0x1) == 0x1) {
                view = DrawerLayout.this.findDrawerWithGravity(3);
            }
            else {
                view = DrawerLayout.this.findDrawerWithGravity(5);
            }
            if (view != null && DrawerLayout.this.getDrawerLockMode(view) == 0) {
                this.mDragger.captureChildView(view, n2);
            }
        }
        
        @Override
        public boolean onEdgeLock(final int n) {
            return false;
        }
        
        @Override
        public void onEdgeTouched(final int n, final int n2) {
            DrawerLayout.this.postDelayed(this.mPeekRunnable, 160L);
        }
        
        @Override
        public void onViewCaptured(final View view, final int n) {
            ((LayoutParams)view.getLayoutParams()).isPeeking = false;
            this.closeOtherDrawer();
        }
        
        @Override
        public void onViewDragStateChanged(final int n) {
            DrawerLayout.this.updateDrawerState(this.mAbsGravity, n, this.mDragger.getCapturedView());
        }
        
        @Override
        public void onViewPositionChanged(final View view, final int n, final int n2, final int n3, final int n4) {
            final int width = view.getWidth();
            float n5;
            if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, 3)) {
                n5 = (width + n) / width;
            }
            else {
                n5 = (DrawerLayout.this.getWidth() - n) / width;
            }
            DrawerLayout.this.setDrawerViewOffset(view, n5);
            int visibility;
            if (n5 == 0.0f) {
                visibility = 4;
            }
            else {
                visibility = 0;
            }
            view.setVisibility(visibility);
            DrawerLayout.this.invalidate();
        }
        
        @Override
        public void onViewReleased(final View view, final float n, final float n2) {
            final float drawerViewOffset = DrawerLayout.this.getDrawerViewOffset(view);
            final int width = view.getWidth();
            int n3;
            if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, 3)) {
                if (n > 0.0f || (n == 0.0f && drawerViewOffset > 0.5f)) {
                    n3 = 0;
                }
                else {
                    n3 = -width;
                }
            }
            else {
                final int width2 = DrawerLayout.this.getWidth();
                if (n < 0.0f || (n == 0.0f && drawerViewOffset > 0.5f)) {
                    n3 = width2 - width;
                }
                else {
                    n3 = width2;
                }
            }
            this.mDragger.settleCapturedViewAt(n3, view.getTop());
            DrawerLayout.this.invalidate();
        }
        
        void peekDrawer() {
            final int edgeSize = this.mDragger.getEdgeSize();
            boolean b;
            if (this.mAbsGravity == 3) {
                b = true;
            }
            else {
                b = false;
            }
            View view;
            int n2;
            if (b) {
                view = DrawerLayout.this.findDrawerWithGravity(3);
                int n = 0;
                if (view != null) {
                    n = -view.getWidth();
                }
                n2 = n + edgeSize;
            }
            else {
                view = DrawerLayout.this.findDrawerWithGravity(5);
                n2 = DrawerLayout.this.getWidth() - edgeSize;
            }
            if (view != null && ((b && view.getLeft() < n2) || (!b && view.getLeft() > n2)) && DrawerLayout.this.getDrawerLockMode(view) == 0) {
                final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
                this.mDragger.smoothSlideViewTo(view, n2, view.getTop());
                layoutParams.isPeeking = true;
                DrawerLayout.this.invalidate();
                this.closeOtherDrawer();
                DrawerLayout.this.cancelChildViewTouch();
            }
        }
        
        public void removeCallbacks() {
            DrawerLayout.this.removeCallbacks(this.mPeekRunnable);
        }
        
        public void setDragger(final ViewDragHelper mDragger) {
            this.mDragger = mDragger;
        }
        
        @Override
        public boolean tryCaptureView(final View view, final int n) {
            return DrawerLayout.this.isDrawerView(view) && DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, this.mAbsGravity) && DrawerLayout.this.getDrawerLockMode(view) == 0;
        }
    }
}
