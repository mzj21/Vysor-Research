// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.vysor;

import android.view.MotionEvent;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v4.view.ViewPager;

public class NonSwipeableViewPager extends ViewPager
{
    private boolean allowSwiping;
    
    public NonSwipeableViewPager(final Context context) {
        super(context);
    }
    
    public NonSwipeableViewPager(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public void allowSwiping(final boolean allowSwiping) {
        this.allowSwiping = allowSwiping;
    }
    
    @Override
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        return this.allowSwiping && super.onInterceptHoverEvent(motionEvent);
    }
    
    @Override
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return this.allowSwiping && super.onTouchEvent(motionEvent);
    }
}
