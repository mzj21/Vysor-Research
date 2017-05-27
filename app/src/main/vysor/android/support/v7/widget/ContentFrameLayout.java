// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.View$MeasureSpec;
import android.util.AttributeSet;
import android.content.Context;
import android.util.TypedValue;
import android.graphics.Rect;
import android.widget.FrameLayout;

public class ContentFrameLayout extends FrameLayout
{
    private OnAttachListener mAttachListener;
    private final Rect mDecorPadding;
    private TypedValue mFixedHeightMajor;
    private TypedValue mFixedHeightMinor;
    private TypedValue mFixedWidthMajor;
    private TypedValue mFixedWidthMinor;
    private TypedValue mMinWidthMajor;
    private TypedValue mMinWidthMinor;
    
    public ContentFrameLayout(final Context context) {
        this(context, null);
    }
    
    public ContentFrameLayout(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public ContentFrameLayout(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mDecorPadding = new Rect();
    }
    
    public void dispatchFitSystemWindows(final Rect rect) {
        this.fitSystemWindows(rect);
    }
    
    public TypedValue getFixedHeightMajor() {
        if (this.mFixedHeightMajor == null) {
            this.mFixedHeightMajor = new TypedValue();
        }
        return this.mFixedHeightMajor;
    }
    
    public TypedValue getFixedHeightMinor() {
        if (this.mFixedHeightMinor == null) {
            this.mFixedHeightMinor = new TypedValue();
        }
        return this.mFixedHeightMinor;
    }
    
    public TypedValue getFixedWidthMajor() {
        if (this.mFixedWidthMajor == null) {
            this.mFixedWidthMajor = new TypedValue();
        }
        return this.mFixedWidthMajor;
    }
    
    public TypedValue getFixedWidthMinor() {
        if (this.mFixedWidthMinor == null) {
            this.mFixedWidthMinor = new TypedValue();
        }
        return this.mFixedWidthMinor;
    }
    
    public TypedValue getMinWidthMajor() {
        if (this.mMinWidthMajor == null) {
            this.mMinWidthMajor = new TypedValue();
        }
        return this.mMinWidthMajor;
    }
    
    public TypedValue getMinWidthMinor() {
        if (this.mMinWidthMinor == null) {
            this.mMinWidthMinor = new TypedValue();
        }
        return this.mMinWidthMinor;
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mAttachListener != null) {
            this.mAttachListener.onAttachedFromWindow();
        }
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mAttachListener != null) {
            this.mAttachListener.onDetachedFromWindow();
        }
    }
    
    protected void onMeasure(int measureSpec, int measureSpec2) {
        final DisplayMetrics displayMetrics = this.getContext().getResources().getDisplayMetrics();
        boolean b;
        if (displayMetrics.widthPixels < displayMetrics.heightPixels) {
            b = true;
        }
        else {
            b = false;
        }
        final int mode = View$MeasureSpec.getMode(measureSpec);
        final int mode2 = View$MeasureSpec.getMode(measureSpec2);
        boolean b2 = false;
        if (mode == Integer.MIN_VALUE) {
            TypedValue typedValue;
            if (b) {
                typedValue = this.mFixedWidthMinor;
            }
            else {
                typedValue = this.mFixedWidthMajor;
            }
            b2 = false;
            if (typedValue != null) {
                final int type = typedValue.type;
                b2 = false;
                if (type != 0) {
                    int n;
                    if (typedValue.type == 5) {
                        n = (int)typedValue.getDimension(displayMetrics);
                    }
                    else {
                        final int type2 = typedValue.type;
                        n = 0;
                        if (type2 == 6) {
                            n = (int)typedValue.getFraction((float)displayMetrics.widthPixels, (float)displayMetrics.widthPixels);
                        }
                    }
                    b2 = false;
                    if (n > 0) {
                        measureSpec = View$MeasureSpec.makeMeasureSpec(Math.min(n - (this.mDecorPadding.left + this.mDecorPadding.right), View$MeasureSpec.getSize(measureSpec)), 1073741824);
                        b2 = true;
                    }
                }
            }
        }
        if (mode2 == Integer.MIN_VALUE) {
            TypedValue typedValue2;
            if (b) {
                typedValue2 = this.mFixedHeightMajor;
            }
            else {
                typedValue2 = this.mFixedHeightMinor;
            }
            if (typedValue2 != null && typedValue2.type != 0) {
                int n2;
                if (typedValue2.type == 5) {
                    n2 = (int)typedValue2.getDimension(displayMetrics);
                }
                else {
                    final int type3 = typedValue2.type;
                    n2 = 0;
                    if (type3 == 6) {
                        n2 = (int)typedValue2.getFraction((float)displayMetrics.heightPixels, (float)displayMetrics.heightPixels);
                    }
                }
                if (n2 > 0) {
                    measureSpec2 = View$MeasureSpec.makeMeasureSpec(Math.min(n2 - (this.mDecorPadding.top + this.mDecorPadding.bottom), View$MeasureSpec.getSize(measureSpec2)), 1073741824);
                }
            }
        }
        super.onMeasure(measureSpec, measureSpec2);
        final int measuredWidth = this.getMeasuredWidth();
        int n3 = View$MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
        boolean b3 = false;
        if (!b2) {
            b3 = false;
            if (mode == Integer.MIN_VALUE) {
                TypedValue typedValue3;
                if (b) {
                    typedValue3 = this.mMinWidthMinor;
                }
                else {
                    typedValue3 = this.mMinWidthMajor;
                }
                b3 = false;
                if (typedValue3 != null) {
                    final int type4 = typedValue3.type;
                    b3 = false;
                    if (type4 != 0) {
                        int n4;
                        if (typedValue3.type == 5) {
                            n4 = (int)typedValue3.getDimension(displayMetrics);
                        }
                        else {
                            final int type5 = typedValue3.type;
                            n4 = 0;
                            if (type5 == 6) {
                                n4 = (int)typedValue3.getFraction((float)displayMetrics.widthPixels, (float)displayMetrics.widthPixels);
                            }
                        }
                        if (n4 > 0) {
                            n4 -= this.mDecorPadding.left + this.mDecorPadding.right;
                        }
                        b3 = false;
                        if (measuredWidth < n4) {
                            n3 = View$MeasureSpec.makeMeasureSpec(n4, 1073741824);
                            b3 = true;
                        }
                    }
                }
            }
        }
        if (b3) {
            super.onMeasure(n3, measureSpec2);
        }
    }
    
    public void setAttachListener(final OnAttachListener mAttachListener) {
        this.mAttachListener = mAttachListener;
    }
    
    public void setDecorPadding(final int n, final int n2, final int n3, final int n4) {
        this.mDecorPadding.set(n, n2, n3, n4);
        if (ViewCompat.isLaidOut((View)this)) {
            this.requestLayout();
        }
    }
    
    public interface OnAttachListener
    {
        void onAttachedFromWindow();
        
        void onDetachedFromWindow();
    }
}
