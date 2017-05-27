// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.os.Build$VERSION;
import android.view.animation.Interpolator;
import android.content.Context;
import android.widget.OverScroller;

public final class ScrollerCompat
{
    private final boolean mIsIcsOrNewer;
    OverScroller mScroller;
    
    ScrollerCompat(final boolean mIsIcsOrNewer, final Context context, final Interpolator interpolator) {
        this.mIsIcsOrNewer = mIsIcsOrNewer;
        OverScroller mScroller;
        if (interpolator != null) {
            mScroller = new OverScroller(context, interpolator);
        }
        else {
            mScroller = new OverScroller(context);
        }
        this.mScroller = mScroller;
    }
    
    public static ScrollerCompat create(final Context context) {
        return create(context, null);
    }
    
    public static ScrollerCompat create(final Context context, final Interpolator interpolator) {
        return new ScrollerCompat(Build$VERSION.SDK_INT >= 14, context, interpolator);
    }
    
    public void abortAnimation() {
        this.mScroller.abortAnimation();
    }
    
    public boolean computeScrollOffset() {
        return this.mScroller.computeScrollOffset();
    }
    
    public void fling(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this.mScroller.fling(n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void fling(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
        this.mScroller.fling(n, n2, n3, n4, n5, n6, n7, n8, n9, n10);
    }
    
    public float getCurrVelocity() {
        float currVelocity;
        if (this.mIsIcsOrNewer) {
            currVelocity = ScrollerCompatIcs.getCurrVelocity(this.mScroller);
        }
        else {
            currVelocity = 0.0f;
        }
        return currVelocity;
    }
    
    public int getCurrX() {
        return this.mScroller.getCurrX();
    }
    
    public int getCurrY() {
        return this.mScroller.getCurrY();
    }
    
    public int getFinalX() {
        return this.mScroller.getFinalX();
    }
    
    public int getFinalY() {
        return this.mScroller.getFinalY();
    }
    
    public boolean isFinished() {
        return this.mScroller.isFinished();
    }
    
    public boolean isOverScrolled() {
        return this.mScroller.isOverScrolled();
    }
    
    public void notifyHorizontalEdgeReached(final int n, final int n2, final int n3) {
        this.mScroller.notifyHorizontalEdgeReached(n, n2, n3);
    }
    
    public void notifyVerticalEdgeReached(final int n, final int n2, final int n3) {
        this.mScroller.notifyVerticalEdgeReached(n, n2, n3);
    }
    
    public boolean springBack(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        return this.mScroller.springBack(n, n2, n3, n4, n5, n6);
    }
    
    public void startScroll(final int n, final int n2, final int n3, final int n4) {
        this.mScroller.startScroll(n, n2, n3, n4);
    }
    
    public void startScroll(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.mScroller.startScroll(n, n2, n3, n4, n5);
    }
}
