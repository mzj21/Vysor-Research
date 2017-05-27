// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.ViewParent;
import android.os.SystemClock;
import android.support.v7.view.menu.ShowableListMenu;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.View;
import android.view.View$OnTouchListener;

public abstract class ForwardingListener implements View$OnTouchListener
{
    private int mActivePointerId;
    private Runnable mDisallowIntercept;
    private boolean mForwarding;
    private final int mLongPressTimeout;
    private final float mScaledTouchSlop;
    final View mSrc;
    private final int mTapTimeout;
    private final int[] mTmpLocation;
    private Runnable mTriggerLongPress;
    
    public ForwardingListener(final View mSrc) {
        this.mTmpLocation = new int[2];
        this.mSrc = mSrc;
        this.mScaledTouchSlop = ViewConfiguration.get(mSrc.getContext()).getScaledTouchSlop();
        this.mTapTimeout = ViewConfiguration.getTapTimeout();
        this.mLongPressTimeout = (this.mTapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
    }
    
    private void clearCallbacks() {
        if (this.mTriggerLongPress != null) {
            this.mSrc.removeCallbacks(this.mTriggerLongPress);
        }
        if (this.mDisallowIntercept != null) {
            this.mSrc.removeCallbacks(this.mDisallowIntercept);
        }
    }
    
    private boolean onTouchForwarded(final MotionEvent motionEvent) {
        boolean b = true;
        final View mSrc = this.mSrc;
        final ShowableListMenu popup = this.getPopup();
        boolean b2 = false;
        if (popup != null) {
            final boolean showing = popup.isShowing();
            b2 = false;
            if (showing) {
                final DropDownListView dropDownListView = (DropDownListView)popup.getListView();
                b2 = false;
                if (dropDownListView != null) {
                    final boolean shown = dropDownListView.isShown();
                    b2 = false;
                    if (shown) {
                        final MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
                        this.toGlobalMotionEvent(mSrc, obtainNoHistory);
                        this.toLocalMotionEvent((View)dropDownListView, obtainNoHistory);
                        final boolean onForwardedEvent = dropDownListView.onForwardedEvent(obtainNoHistory, this.mActivePointerId);
                        obtainNoHistory.recycle();
                        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
                        final boolean b3 = actionMasked != (b ? 1 : 0) && actionMasked != 3 && b;
                        if (!onForwardedEvent || !b3) {
                            b = false;
                        }
                        b2 = b;
                    }
                }
            }
        }
        return b2;
    }
    
    private boolean onTouchObserved(final MotionEvent motionEvent) {
        final View mSrc = this.mSrc;
        final boolean enabled = mSrc.isEnabled();
        boolean b = false;
        if (enabled) {
            switch (MotionEventCompat.getActionMasked(motionEvent)) {
                default: {
                    b = false;
                    break;
                }
                case 0: {
                    this.mActivePointerId = motionEvent.getPointerId(0);
                    if (this.mDisallowIntercept == null) {
                        this.mDisallowIntercept = new DisallowIntercept();
                    }
                    mSrc.postDelayed(this.mDisallowIntercept, (long)this.mTapTimeout);
                    if (this.mTriggerLongPress == null) {
                        this.mTriggerLongPress = new TriggerLongPress();
                    }
                    mSrc.postDelayed(this.mTriggerLongPress, (long)this.mLongPressTimeout);
                    b = false;
                    break;
                }
                case 2: {
                    final int pointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                    b = false;
                    if (pointerIndex < 0) {
                        break;
                    }
                    final boolean pointInView = pointInView(mSrc, motionEvent.getX(pointerIndex), motionEvent.getY(pointerIndex), this.mScaledTouchSlop);
                    b = false;
                    if (!pointInView) {
                        this.clearCallbacks();
                        mSrc.getParent().requestDisallowInterceptTouchEvent(true);
                        b = true;
                        break;
                    }
                    break;
                }
                case 1:
                case 3: {
                    this.clearCallbacks();
                    b = false;
                    break;
                }
            }
        }
        return b;
    }
    
    private static boolean pointInView(final View view, final float n, final float n2, final float n3) {
        return n >= -n3 && n2 >= -n3 && n < n3 + (view.getRight() - view.getLeft()) && n2 < n3 + (view.getBottom() - view.getTop());
    }
    
    private boolean toGlobalMotionEvent(final View view, final MotionEvent motionEvent) {
        final int[] mTmpLocation = this.mTmpLocation;
        view.getLocationOnScreen(mTmpLocation);
        motionEvent.offsetLocation((float)mTmpLocation[0], (float)mTmpLocation[1]);
        return true;
    }
    
    private boolean toLocalMotionEvent(final View view, final MotionEvent motionEvent) {
        final int[] mTmpLocation = this.mTmpLocation;
        view.getLocationOnScreen(mTmpLocation);
        motionEvent.offsetLocation((float)(-mTmpLocation[0]), (float)(-mTmpLocation[1]));
        return true;
    }
    
    public abstract ShowableListMenu getPopup();
    
    protected boolean onForwardingStarted() {
        final ShowableListMenu popup = this.getPopup();
        if (popup != null && !popup.isShowing()) {
            popup.show();
        }
        return true;
    }
    
    protected boolean onForwardingStopped() {
        final ShowableListMenu popup = this.getPopup();
        if (popup != null && popup.isShowing()) {
            popup.dismiss();
        }
        return true;
    }
    
    void onLongPress() {
        this.clearCallbacks();
        final View mSrc = this.mSrc;
        if (mSrc.isEnabled() && !mSrc.isLongClickable() && this.onForwardingStarted()) {
            mSrc.getParent().requestDisallowInterceptTouchEvent(true);
            final long uptimeMillis = SystemClock.uptimeMillis();
            final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            mSrc.onTouchEvent(obtain);
            obtain.recycle();
            this.mForwarding = true;
        }
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        final boolean mForwarding = this.mForwarding;
        boolean mForwarding2;
        if (mForwarding) {
            mForwarding2 = (this.onTouchForwarded(motionEvent) || !this.onForwardingStopped());
        }
        else {
            mForwarding2 = (this.onTouchObserved(motionEvent) && this.onForwardingStarted());
            if (mForwarding2) {
                final long uptimeMillis = SystemClock.uptimeMillis();
                final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                this.mSrc.onTouchEvent(obtain);
                obtain.recycle();
            }
        }
        if (!(this.mForwarding = mForwarding2)) {
            final boolean b = false;
            if (!mForwarding) {
                return b;
            }
        }
        return true;
    }
    
    private class DisallowIntercept implements Runnable
    {
        @Override
        public void run() {
            final ViewParent parent = ForwardingListener.this.mSrc.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
    }
    
    private class TriggerLongPress implements Runnable
    {
        @Override
        public void run() {
            ForwardingListener.this.onLongPress();
        }
    }
}
