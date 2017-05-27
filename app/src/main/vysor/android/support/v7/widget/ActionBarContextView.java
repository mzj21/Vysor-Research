// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.View$MeasureSpec;
import android.os.Build$VERSION;
import android.view.accessibility.AccessibilityEvent;
import android.view.MotionEvent;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.view.View$OnClickListener;
import android.support.v7.view.ActionMode;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;

public class ActionBarContextView extends AbsActionBarView
{
    private static final String TAG = "ActionBarContextView";
    private View mClose;
    private int mCloseItemLayout;
    private View mCustomView;
    private CharSequence mSubtitle;
    private int mSubtitleStyleRes;
    private TextView mSubtitleView;
    private CharSequence mTitle;
    private LinearLayout mTitleLayout;
    private boolean mTitleOptional;
    private int mTitleStyleRes;
    private TextView mTitleView;
    
    public ActionBarContextView(final Context context) {
        this(context, null);
    }
    
    public ActionBarContextView(final Context context, final AttributeSet set) {
        this(context, set, R.attr.actionModeStyle);
    }
    
    public ActionBarContextView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, set, R.styleable.ActionMode, n, 0);
        this.setBackgroundDrawable(obtainStyledAttributes.getDrawable(R.styleable.ActionMode_background));
        this.mTitleStyleRes = obtainStyledAttributes.getResourceId(R.styleable.ActionMode_titleTextStyle, 0);
        this.mSubtitleStyleRes = obtainStyledAttributes.getResourceId(R.styleable.ActionMode_subtitleTextStyle, 0);
        this.mContentHeight = obtainStyledAttributes.getLayoutDimension(R.styleable.ActionMode_height, 0);
        this.mCloseItemLayout = obtainStyledAttributes.getResourceId(R.styleable.ActionMode_closeItemLayout, R.layout.abc_action_mode_close_item_material);
        obtainStyledAttributes.recycle();
    }
    
    private void initTitle() {
        int visibility = 8;
        if (this.mTitleLayout == null) {
            LayoutInflater.from(this.getContext()).inflate(R.layout.abc_action_bar_title_item, (ViewGroup)this);
            this.mTitleLayout = (LinearLayout)this.getChildAt(-1 + this.getChildCount());
            this.mTitleView = (TextView)this.mTitleLayout.findViewById(R.id.action_bar_title);
            this.mSubtitleView = (TextView)this.mTitleLayout.findViewById(R.id.action_bar_subtitle);
            if (this.mTitleStyleRes != 0) {
                this.mTitleView.setTextAppearance(this.getContext(), this.mTitleStyleRes);
            }
            if (this.mSubtitleStyleRes != 0) {
                this.mSubtitleView.setTextAppearance(this.getContext(), this.mSubtitleStyleRes);
            }
        }
        this.mTitleView.setText(this.mTitle);
        this.mSubtitleView.setText(this.mSubtitle);
        boolean b;
        if (!TextUtils.isEmpty(this.mTitle)) {
            b = true;
        }
        else {
            b = false;
        }
        boolean b2;
        if (!TextUtils.isEmpty(this.mSubtitle)) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        final TextView mSubtitleView = this.mSubtitleView;
        int visibility2;
        if (b2) {
            visibility2 = 0;
        }
        else {
            visibility2 = visibility;
        }
        mSubtitleView.setVisibility(visibility2);
        final LinearLayout mTitleLayout = this.mTitleLayout;
        if (b || b2) {
            visibility = 0;
        }
        mTitleLayout.setVisibility(visibility);
        if (this.mTitleLayout.getParent() == null) {
            this.addView((View)this.mTitleLayout);
        }
    }
    
    public void closeMode() {
        if (this.mClose == null) {
            this.killMode();
        }
    }
    
    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return (ViewGroup$LayoutParams)new ViewGroup$MarginLayoutParams(-1, -2);
    }
    
    public ViewGroup$LayoutParams generateLayoutParams(final AttributeSet set) {
        return (ViewGroup$LayoutParams)new ViewGroup$MarginLayoutParams(this.getContext(), set);
    }
    
    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }
    
    public CharSequence getTitle() {
        return this.mTitle;
    }
    
    @Override
    public boolean hideOverflowMenu() {
        return this.mActionMenuPresenter != null && this.mActionMenuPresenter.hideOverflowMenu();
    }
    
    public void initForMode(final ActionMode actionMode) {
        if (this.mClose == null) {
            this.addView(this.mClose = LayoutInflater.from(this.getContext()).inflate(this.mCloseItemLayout, (ViewGroup)this, false));
        }
        else if (this.mClose.getParent() == null) {
            this.addView(this.mClose);
        }
        this.mClose.findViewById(R.id.action_mode_close_button).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                actionMode.finish();
            }
        });
        final MenuBuilder menuBuilder = (MenuBuilder)actionMode.getMenu();
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.dismissPopupMenus();
        }
        (this.mActionMenuPresenter = new ActionMenuPresenter(this.getContext())).setReserveOverflow(true);
        final ViewGroup$LayoutParams viewGroup$LayoutParams = new ViewGroup$LayoutParams(-2, -1);
        menuBuilder.addMenuPresenter(this.mActionMenuPresenter, this.mPopupContext);
        (this.mMenuView = (ActionMenuView)this.mActionMenuPresenter.getMenuView(this)).setBackgroundDrawable((Drawable)null);
        this.addView((View)this.mMenuView, viewGroup$LayoutParams);
    }
    
    @Override
    public boolean isOverflowMenuShowing() {
        return this.mActionMenuPresenter != null && this.mActionMenuPresenter.isOverflowMenuShowing();
    }
    
    public boolean isTitleOptional() {
        return this.mTitleOptional;
    }
    
    public void killMode() {
        this.removeAllViews();
        this.mCustomView = null;
        this.mMenuView = null;
    }
    
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.hideOverflowMenu();
            this.mActionMenuPresenter.hideSubMenus();
        }
    }
    
    public void onInitializeAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        if (Build$VERSION.SDK_INT >= 14) {
            if (accessibilityEvent.getEventType() == 32) {
                accessibilityEvent.setSource((View)this);
                accessibilityEvent.setClassName((CharSequence)this.getClass().getName());
                accessibilityEvent.setPackageName((CharSequence)this.getContext().getPackageName());
                accessibilityEvent.setContentDescription(this.mTitle);
            }
            else {
                super.onInitializeAccessibilityEvent(accessibilityEvent);
            }
        }
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        final boolean layoutRtl = ViewUtils.isLayoutRtl((View)this);
        int n5;
        if (layoutRtl) {
            n5 = n3 - n - this.getPaddingRight();
        }
        else {
            n5 = this.getPaddingLeft();
        }
        final int paddingTop = this.getPaddingTop();
        final int n6 = n4 - n2 - this.getPaddingTop() - this.getPaddingBottom();
        if (this.mClose != null && this.mClose.getVisibility() != 8) {
            final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)this.mClose.getLayoutParams();
            int n7;
            if (layoutRtl) {
                n7 = viewGroup$MarginLayoutParams.rightMargin;
            }
            else {
                n7 = viewGroup$MarginLayoutParams.leftMargin;
            }
            int n8;
            if (layoutRtl) {
                n8 = viewGroup$MarginLayoutParams.leftMargin;
            }
            else {
                n8 = viewGroup$MarginLayoutParams.rightMargin;
            }
            final int next = AbsActionBarView.next(n5, n7, layoutRtl);
            n5 = AbsActionBarView.next(next + this.positionChild(this.mClose, next, paddingTop, n6, layoutRtl), n8, layoutRtl);
        }
        if (this.mTitleLayout != null && this.mCustomView == null && this.mTitleLayout.getVisibility() != 8) {
            n5 += this.positionChild((View)this.mTitleLayout, n5, paddingTop, n6, layoutRtl);
        }
        if (this.mCustomView != null) {
            final int n9 = n5 + this.positionChild(this.mCustomView, n5, paddingTop, n6, layoutRtl);
        }
        int paddingLeft;
        if (layoutRtl) {
            paddingLeft = this.getPaddingLeft();
        }
        else {
            paddingLeft = n3 - n - this.getPaddingRight();
        }
        if (this.mMenuView != null) {
            final int n10 = paddingLeft + this.positionChild((View)this.mMenuView, paddingLeft, paddingTop, n6, !layoutRtl);
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        if (View$MeasureSpec.getMode(n) != 1073741824) {
            throw new IllegalStateException(this.getClass().getSimpleName() + " can only be used " + "with android:layout_width=\"match_parent\" (or fill_parent)");
        }
        if (View$MeasureSpec.getMode(n2) == 0) {
            throw new IllegalStateException(this.getClass().getSimpleName() + " can only be used " + "with android:layout_height=\"wrap_content\"");
        }
        final int size = View$MeasureSpec.getSize(n);
        int n3;
        if (this.mContentHeight > 0) {
            n3 = this.mContentHeight;
        }
        else {
            n3 = View$MeasureSpec.getSize(n2);
        }
        final int n4 = this.getPaddingTop() + this.getPaddingBottom();
        int n5 = size - this.getPaddingLeft() - this.getPaddingRight();
        final int n6 = n3 - n4;
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(n6, Integer.MIN_VALUE);
        if (this.mClose != null) {
            final int measureChildView = this.measureChildView(this.mClose, n5, measureSpec, 0);
            final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)this.mClose.getLayoutParams();
            n5 = measureChildView - (viewGroup$MarginLayoutParams.leftMargin + viewGroup$MarginLayoutParams.rightMargin);
        }
        if (this.mMenuView != null && this.mMenuView.getParent() == this) {
            n5 = this.measureChildView((View)this.mMenuView, n5, measureSpec, 0);
        }
        if (this.mTitleLayout != null && this.mCustomView == null) {
            if (this.mTitleOptional) {
                this.mTitleLayout.measure(View$MeasureSpec.makeMeasureSpec(0, 0), measureSpec);
                final int measuredWidth = this.mTitleLayout.getMeasuredWidth();
                boolean b;
                if (measuredWidth <= n5) {
                    b = true;
                }
                else {
                    b = false;
                }
                if (b) {
                    n5 -= measuredWidth;
                }
                final LinearLayout mTitleLayout = this.mTitleLayout;
                int visibility;
                if (b) {
                    visibility = 0;
                }
                else {
                    visibility = 8;
                }
                mTitleLayout.setVisibility(visibility);
            }
            else {
                n5 = this.measureChildView((View)this.mTitleLayout, n5, measureSpec, 0);
            }
        }
        if (this.mCustomView != null) {
            final ViewGroup$LayoutParams layoutParams = this.mCustomView.getLayoutParams();
            int n7;
            if (layoutParams.width != -2) {
                n7 = 1073741824;
            }
            else {
                n7 = Integer.MIN_VALUE;
            }
            int min;
            if (layoutParams.width >= 0) {
                min = Math.min(layoutParams.width, n5);
            }
            else {
                min = n5;
            }
            int n8;
            if (layoutParams.height != -2) {
                n8 = 1073741824;
            }
            else {
                n8 = Integer.MIN_VALUE;
            }
            int min2;
            if (layoutParams.height >= 0) {
                min2 = Math.min(layoutParams.height, n6);
            }
            else {
                min2 = n6;
            }
            this.mCustomView.measure(View$MeasureSpec.makeMeasureSpec(min, n7), View$MeasureSpec.makeMeasureSpec(min2, n8));
        }
        if (this.mContentHeight <= 0) {
            int n9 = 0;
            for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
                final int n10 = n4 + this.getChildAt(i).getMeasuredHeight();
                if (n10 > n9) {
                    n9 = n10;
                }
            }
            this.setMeasuredDimension(size, n9);
        }
        else {
            this.setMeasuredDimension(size, n3);
        }
    }
    
    @Override
    public void setContentHeight(final int mContentHeight) {
        this.mContentHeight = mContentHeight;
    }
    
    public void setCustomView(final View mCustomView) {
        if (this.mCustomView != null) {
            this.removeView(this.mCustomView);
        }
        this.mCustomView = mCustomView;
        if (mCustomView != null && this.mTitleLayout != null) {
            this.removeView((View)this.mTitleLayout);
            this.mTitleLayout = null;
        }
        if (mCustomView != null) {
            this.addView(mCustomView);
        }
        this.requestLayout();
    }
    
    public void setSubtitle(final CharSequence mSubtitle) {
        this.mSubtitle = mSubtitle;
        this.initTitle();
    }
    
    public void setTitle(final CharSequence mTitle) {
        this.mTitle = mTitle;
        this.initTitle();
    }
    
    public void setTitleOptional(final boolean mTitleOptional) {
        if (mTitleOptional != this.mTitleOptional) {
            this.requestLayout();
        }
        this.mTitleOptional = mTitleOptional;
    }
    
    public boolean shouldDelayChildPressedState() {
        return false;
    }
    
    @Override
    public boolean showOverflowMenu() {
        return this.mActionMenuPresenter != null && this.mActionMenuPresenter.showOverflowMenu();
    }
}
