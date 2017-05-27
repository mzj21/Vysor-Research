// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.view.ViewCompat;
import android.os.Build$VERSION;
import android.view.View$MeasureSpec;
import android.view.View;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R;
import android.support.v4.content.res.ConfigurationHelper;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.LinearLayout;

public class ButtonBarLayout extends LinearLayout
{
    private static final int ALLOW_STACKING_MIN_HEIGHT_DP = 320;
    private boolean mAllowStacking;
    private int mLastWidthSize;
    
    public ButtonBarLayout(final Context context, final AttributeSet set) {
        super(context, set);
        this.mLastWidthSize = -1;
        final boolean b = ConfigurationHelper.getScreenHeightDp(this.getResources()) >= 320;
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.ButtonBarLayout);
        this.mAllowStacking = obtainStyledAttributes.getBoolean(R.styleable.ButtonBarLayout_allowStacking, b);
        obtainStyledAttributes.recycle();
    }
    
    private boolean isStacked() {
        boolean b = true;
        if (this.getOrientation() != (b ? 1 : 0)) {
            b = false;
        }
        return b;
    }
    
    private void setStacked(final boolean b) {
        int orientation;
        if (b) {
            orientation = 1;
        }
        else {
            orientation = 0;
        }
        this.setOrientation(orientation);
        int gravity;
        if (b) {
            gravity = 5;
        }
        else {
            gravity = 80;
        }
        this.setGravity(gravity);
        final View viewById = this.findViewById(R.id.spacer);
        if (viewById != null) {
            int visibility;
            if (b) {
                visibility = 8;
            }
            else {
                visibility = 4;
            }
            viewById.setVisibility(visibility);
        }
        for (int i = -2 + this.getChildCount(); i >= 0; --i) {
            this.bringChildToFront(this.getChildAt(i));
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        final int size = View$MeasureSpec.getSize(n);
        if (this.mAllowStacking) {
            if (size > this.mLastWidthSize && this.isStacked()) {
                this.setStacked(false);
            }
            this.mLastWidthSize = size;
        }
        int measureSpec;
        int n3;
        if (!this.isStacked() && View$MeasureSpec.getMode(n) == 1073741824) {
            measureSpec = View$MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
            n3 = 1;
        }
        else {
            measureSpec = n;
            n3 = 0;
        }
        super.onMeasure(measureSpec, n2);
        if (this.mAllowStacking && !this.isStacked()) {
            int n5;
            if (Build$VERSION.SDK_INT >= 11) {
                final int n4 = 0xFF000000 & ViewCompat.getMeasuredWidthAndState((View)this);
                n5 = 0;
                if (n4 == 16777216) {
                    n5 = 1;
                }
            }
            else {
                int n6 = 0;
                for (int i = 0; i < this.getChildCount(); ++i) {
                    n6 += this.getChildAt(i).getMeasuredWidth();
                }
                final int n7 = n6 + this.getPaddingLeft() + this.getPaddingRight();
                n5 = 0;
                if (n7 > size) {
                    n5 = 1;
                }
            }
            if (n5 != 0) {
                this.setStacked(true);
                n3 = 1;
            }
        }
        if (n3 != 0) {
            super.onMeasure(n, n2);
        }
    }
    
    public void setAllowStacking(final boolean mAllowStacking) {
        if (this.mAllowStacking != mAllowStacking) {
            this.mAllowStacking = mAllowStacking;
            if (!this.mAllowStacking && this.getOrientation() == 1) {
                this.setStacked(false);
            }
            this.requestLayout();
        }
    }
}
