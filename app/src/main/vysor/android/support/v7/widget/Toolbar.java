// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.os.ParcelableCompat;
import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.os.Parcelable$Creator;
import android.support.v4.view.AbsSavedState;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.CollapsibleActionView;
import android.support.annotation.ColorInt;
import android.text.TextUtils$TruncateAt;
import android.view.ContextThemeWrapper;
import android.support.annotation.StyleRes;
import android.support.annotation.StringRes;
import android.support.v7.content.res.AppCompatResources;
import android.support.annotation.DrawableRes;
import android.os.Build$VERSION;
import android.support.v4.view.MenuItemCompat;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.text.Layout;
import android.support.annotation.MenuRes;
import android.view.Menu;
import android.support.v7.app.ActionBar;
import android.view.View$OnClickListener;
import android.support.v7.view.menu.MenuItemImpl;
import android.view.View$MeasureSpec;
import android.support.v7.view.SupportMenuInflater;
import android.view.MenuInflater;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import java.util.List;
import android.text.TextUtils;
import android.view.MenuItem;
import android.support.v7.appcompat.R;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;
import android.content.Context;
import android.support.v7.view.menu.MenuBuilder;
import android.widget.ImageView;
import java.util.ArrayList;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.widget.ImageButton;
import android.support.v7.view.menu.MenuPresenter;
import android.view.ViewGroup;

public class Toolbar extends ViewGroup
{
    private static final String TAG = "Toolbar";
    private MenuPresenter.Callback mActionMenuPresenterCallback;
    int mButtonGravity;
    ImageButton mCollapseButtonView;
    private CharSequence mCollapseDescription;
    private Drawable mCollapseIcon;
    private boolean mCollapsible;
    private int mContentInsetEndWithActions;
    private int mContentInsetStartWithNavigation;
    private RtlSpacingHelper mContentInsets;
    private boolean mEatingHover;
    private boolean mEatingTouch;
    View mExpandedActionView;
    private ExpandedActionViewMenuPresenter mExpandedMenuPresenter;
    private int mGravity;
    private final ArrayList<View> mHiddenViews;
    private ImageView mLogoView;
    private int mMaxButtonHeight;
    private MenuBuilder.Callback mMenuBuilderCallback;
    private ActionMenuView mMenuView;
    private final ActionMenuView.OnMenuItemClickListener mMenuViewItemClickListener;
    private ImageButton mNavButtonView;
    OnMenuItemClickListener mOnMenuItemClickListener;
    private ActionMenuPresenter mOuterActionMenuPresenter;
    private Context mPopupContext;
    private int mPopupTheme;
    private final Runnable mShowOverflowMenuRunnable;
    private CharSequence mSubtitleText;
    private int mSubtitleTextAppearance;
    private int mSubtitleTextColor;
    private TextView mSubtitleTextView;
    private final int[] mTempMargins;
    private final ArrayList<View> mTempViews;
    private int mTitleMarginBottom;
    private int mTitleMarginEnd;
    private int mTitleMarginStart;
    private int mTitleMarginTop;
    private CharSequence mTitleText;
    private int mTitleTextAppearance;
    private int mTitleTextColor;
    private TextView mTitleTextView;
    private ToolbarWidgetWrapper mWrapper;
    
    public Toolbar(final Context context) {
        this(context, null);
    }
    
    public Toolbar(final Context context, @Nullable final AttributeSet set) {
        this(context, set, R.attr.toolbarStyle);
    }
    
    public Toolbar(final Context context, @Nullable final AttributeSet set, final int n) {
        super(context, set, n);
        this.mGravity = 8388627;
        this.mTempViews = new ArrayList<View>();
        this.mHiddenViews = new ArrayList<View>();
        this.mTempMargins = new int[2];
        this.mMenuViewItemClickListener = new ActionMenuView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(final MenuItem menuItem) {
                return Toolbar.this.mOnMenuItemClickListener != null && Toolbar.this.mOnMenuItemClickListener.onMenuItemClick(menuItem);
            }
        };
        this.mShowOverflowMenuRunnable = new Runnable() {
            @Override
            public void run() {
                Toolbar.this.showOverflowMenu();
            }
        };
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(this.getContext(), set, R.styleable.Toolbar, n, 0);
        this.mTitleTextAppearance = obtainStyledAttributes.getResourceId(R.styleable.Toolbar_titleTextAppearance, 0);
        this.mSubtitleTextAppearance = obtainStyledAttributes.getResourceId(R.styleable.Toolbar_subtitleTextAppearance, 0);
        this.mGravity = obtainStyledAttributes.getInteger(R.styleable.Toolbar_android_gravity, this.mGravity);
        this.mButtonGravity = obtainStyledAttributes.getInteger(R.styleable.Toolbar_buttonGravity, 48);
        int n2 = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.Toolbar_titleMargin, 0);
        if (obtainStyledAttributes.hasValue(R.styleable.Toolbar_titleMargins)) {
            n2 = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.Toolbar_titleMargins, n2);
        }
        this.mTitleMarginBottom = n2;
        this.mTitleMarginTop = n2;
        this.mTitleMarginEnd = n2;
        this.mTitleMarginStart = n2;
        final int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginStart, -1);
        if (dimensionPixelOffset >= 0) {
            this.mTitleMarginStart = dimensionPixelOffset;
        }
        final int dimensionPixelOffset2 = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginEnd, -1);
        if (dimensionPixelOffset2 >= 0) {
            this.mTitleMarginEnd = dimensionPixelOffset2;
        }
        final int dimensionPixelOffset3 = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginTop, -1);
        if (dimensionPixelOffset3 >= 0) {
            this.mTitleMarginTop = dimensionPixelOffset3;
        }
        final int dimensionPixelOffset4 = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginBottom, -1);
        if (dimensionPixelOffset4 >= 0) {
            this.mTitleMarginBottom = dimensionPixelOffset4;
        }
        this.mMaxButtonHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Toolbar_maxButtonHeight, -1);
        final int dimensionPixelOffset5 = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        final int dimensionPixelOffset6 = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        final int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Toolbar_contentInsetLeft, 0);
        final int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Toolbar_contentInsetRight, 0);
        this.ensureContentInsets();
        this.mContentInsets.setAbsolute(dimensionPixelSize, dimensionPixelSize2);
        if (dimensionPixelOffset5 != Integer.MIN_VALUE || dimensionPixelOffset6 != Integer.MIN_VALUE) {
            this.mContentInsets.setRelative(dimensionPixelOffset5, dimensionPixelOffset6);
        }
        this.mContentInsetStartWithNavigation = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.mContentInsetEndWithActions = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.mCollapseIcon = obtainStyledAttributes.getDrawable(R.styleable.Toolbar_collapseIcon);
        this.mCollapseDescription = obtainStyledAttributes.getText(R.styleable.Toolbar_collapseContentDescription);
        final CharSequence text = obtainStyledAttributes.getText(R.styleable.Toolbar_title);
        if (!TextUtils.isEmpty(text)) {
            this.setTitle(text);
        }
        final CharSequence text2 = obtainStyledAttributes.getText(R.styleable.Toolbar_subtitle);
        if (!TextUtils.isEmpty(text2)) {
            this.setSubtitle(text2);
        }
        this.mPopupContext = this.getContext();
        this.setPopupTheme(obtainStyledAttributes.getResourceId(R.styleable.Toolbar_popupTheme, 0));
        final Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.Toolbar_navigationIcon);
        if (drawable != null) {
            this.setNavigationIcon(drawable);
        }
        final CharSequence text3 = obtainStyledAttributes.getText(R.styleable.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(text3)) {
            this.setNavigationContentDescription(text3);
        }
        final Drawable drawable2 = obtainStyledAttributes.getDrawable(R.styleable.Toolbar_logo);
        if (drawable2 != null) {
            this.setLogo(drawable2);
        }
        final CharSequence text4 = obtainStyledAttributes.getText(R.styleable.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(text4)) {
            this.setLogoDescription(text4);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.Toolbar_titleTextColor)) {
            this.setTitleTextColor(obtainStyledAttributes.getColor(R.styleable.Toolbar_titleTextColor, -1));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.Toolbar_subtitleTextColor)) {
            this.setSubtitleTextColor(obtainStyledAttributes.getColor(R.styleable.Toolbar_subtitleTextColor, -1));
        }
        obtainStyledAttributes.recycle();
    }
    
    private void addCustomViewsWithGravity(final List<View> list, final int n) {
        int n2 = 1;
        if (ViewCompat.getLayoutDirection((View)this) != n2) {
            n2 = 0;
        }
        final int childCount = this.getChildCount();
        final int absoluteGravity = GravityCompat.getAbsoluteGravity(n, ViewCompat.getLayoutDirection((View)this));
        list.clear();
        if (n2 != 0) {
            for (int i = childCount - 1; i >= 0; --i) {
                final View child = this.getChildAt(i);
                final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
                if (layoutParams.mViewType == 0 && this.shouldLayout(child) && this.getChildHorizontalGravity(layoutParams.gravity) == absoluteGravity) {
                    list.add(child);
                }
            }
        }
        else {
            for (int j = 0; j < childCount; ++j) {
                final View child2 = this.getChildAt(j);
                final LayoutParams layoutParams2 = (LayoutParams)child2.getLayoutParams();
                if (layoutParams2.mViewType == 0 && this.shouldLayout(child2) && this.getChildHorizontalGravity(layoutParams2.gravity) == absoluteGravity) {
                    list.add(child2);
                }
            }
        }
    }
    
    private void addSystemView(final View view, final boolean b) {
        final ViewGroup$LayoutParams layoutParams = view.getLayoutParams();
        LayoutParams layoutParams2;
        if (layoutParams == null) {
            layoutParams2 = this.generateDefaultLayoutParams();
        }
        else if (!this.checkLayoutParams(layoutParams)) {
            layoutParams2 = this.generateLayoutParams(layoutParams);
        }
        else {
            layoutParams2 = (LayoutParams)layoutParams;
        }
        layoutParams2.mViewType = 1;
        if (b && this.mExpandedActionView != null) {
            view.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
            this.mHiddenViews.add(view);
        }
        else {
            this.addView(view, (ViewGroup$LayoutParams)layoutParams2);
        }
    }
    
    private void ensureContentInsets() {
        if (this.mContentInsets == null) {
            this.mContentInsets = new RtlSpacingHelper();
        }
    }
    
    private void ensureLogoView() {
        if (this.mLogoView == null) {
            this.mLogoView = new AppCompatImageView(this.getContext());
        }
    }
    
    private void ensureMenu() {
        this.ensureMenuView();
        if (this.mMenuView.peekMenu() == null) {
            final MenuBuilder menuBuilder = (MenuBuilder)this.mMenuView.getMenu();
            if (this.mExpandedMenuPresenter == null) {
                this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter();
            }
            this.mMenuView.setExpandedActionViewsExclusive(true);
            menuBuilder.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
        }
    }
    
    private void ensureMenuView() {
        if (this.mMenuView == null) {
            (this.mMenuView = new ActionMenuView(this.getContext())).setPopupTheme(this.mPopupTheme);
            this.mMenuView.setOnMenuItemClickListener(this.mMenuViewItemClickListener);
            this.mMenuView.setMenuCallbacks(this.mActionMenuPresenterCallback, this.mMenuBuilderCallback);
            final LayoutParams generateDefaultLayoutParams = this.generateDefaultLayoutParams();
            generateDefaultLayoutParams.gravity = (0x800005 | (0x70 & this.mButtonGravity));
            this.mMenuView.setLayoutParams((ViewGroup$LayoutParams)generateDefaultLayoutParams);
            this.addSystemView((View)this.mMenuView, false);
        }
    }
    
    private void ensureNavButtonView() {
        if (this.mNavButtonView == null) {
            this.mNavButtonView = new AppCompatImageButton(this.getContext(), null, R.attr.toolbarNavigationButtonStyle);
            final LayoutParams generateDefaultLayoutParams = this.generateDefaultLayoutParams();
            generateDefaultLayoutParams.gravity = (0x800003 | (0x70 & this.mButtonGravity));
            this.mNavButtonView.setLayoutParams((ViewGroup$LayoutParams)generateDefaultLayoutParams);
        }
    }
    
    private int getChildHorizontalGravity(final int n) {
        final int layoutDirection = ViewCompat.getLayoutDirection((View)this);
        int n2 = 0x7 & GravityCompat.getAbsoluteGravity(n, layoutDirection);
        switch (n2) {
            default: {
                int n3;
                if (layoutDirection == 1) {
                    n3 = 5;
                }
                else {
                    n3 = 3;
                }
                n2 = n3;
                return n2;
            }
            case 1:
            case 3:
            case 5: {
                return n2;
            }
        }
    }
    
    private int getChildTop(final View view, final int n) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        final int measuredHeight = view.getMeasuredHeight();
        int n2;
        if (n > 0) {
            n2 = (measuredHeight - n) / 2;
        }
        else {
            n2 = 0;
        }
        int n5 = 0;
        switch (this.getChildVerticalGravity(layoutParams.gravity)) {
            default: {
                final int paddingTop = this.getPaddingTop();
                final int paddingBottom = this.getPaddingBottom();
                final int height = this.getHeight();
                int n3 = (height - paddingTop - paddingBottom - measuredHeight) / 2;
                if (n3 < layoutParams.topMargin) {
                    n3 = layoutParams.topMargin;
                }
                else {
                    final int n4 = height - paddingBottom - measuredHeight - n3 - paddingTop;
                    if (n4 < layoutParams.bottomMargin) {
                        n3 = Math.max(0, n3 - (layoutParams.bottomMargin - n4));
                    }
                }
                n5 = paddingTop + n3;
                break;
            }
            case 48: {
                n5 = this.getPaddingTop() - n2;
                break;
            }
            case 80: {
                n5 = this.getHeight() - this.getPaddingBottom() - measuredHeight - layoutParams.bottomMargin - n2;
                break;
            }
        }
        return n5;
    }
    
    private int getChildVerticalGravity(final int n) {
        int n2 = n & 0x70;
        switch (n2) {
            default: {
                n2 = (0x70 & this.mGravity);
                return n2;
            }
            case 16:
            case 48:
            case 80: {
                return n2;
            }
        }
    }
    
    private int getHorizontalMargins(final View view) {
        final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)view.getLayoutParams();
        return MarginLayoutParamsCompat.getMarginStart(viewGroup$MarginLayoutParams) + MarginLayoutParamsCompat.getMarginEnd(viewGroup$MarginLayoutParams);
    }
    
    private MenuInflater getMenuInflater() {
        return new SupportMenuInflater(this.getContext());
    }
    
    private int getVerticalMargins(final View view) {
        final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)view.getLayoutParams();
        return viewGroup$MarginLayoutParams.topMargin + viewGroup$MarginLayoutParams.bottomMargin;
    }
    
    private int getViewListMeasuredWidth(final List<View> list, final int[] array) {
        int max = array[0];
        int max2 = array[1];
        int n = 0;
        for (int size = list.size(), i = 0; i < size; ++i) {
            final View view = list.get(i);
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            final int n2 = layoutParams.leftMargin - max;
            final int n3 = layoutParams.rightMargin - max2;
            final int max3 = Math.max(0, n2);
            final int max4 = Math.max(0, n3);
            max = Math.max(0, -n2);
            max2 = Math.max(0, -n3);
            n += max4 + (max3 + view.getMeasuredWidth());
        }
        return n;
    }
    
    private boolean isChildOrHidden(final View view) {
        return view.getParent() == this || this.mHiddenViews.contains(view);
    }
    
    private static boolean isCustomView(final View view) {
        return ((LayoutParams)view.getLayoutParams()).mViewType == 0;
    }
    
    private int layoutChildLeft(final View view, final int n, final int[] array, final int n2) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        final int n3 = layoutParams.leftMargin - array[0];
        final int n4 = n + Math.max(0, n3);
        array[0] = Math.max(0, -n3);
        final int childTop = this.getChildTop(view, n2);
        final int measuredWidth = view.getMeasuredWidth();
        view.layout(n4, childTop, n4 + measuredWidth, childTop + view.getMeasuredHeight());
        return n4 + (measuredWidth + layoutParams.rightMargin);
    }
    
    private int layoutChildRight(final View view, final int n, final int[] array, final int n2) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        final int n3 = layoutParams.rightMargin - array[1];
        final int n4 = n - Math.max(0, n3);
        array[1] = Math.max(0, -n3);
        final int childTop = this.getChildTop(view, n2);
        final int measuredWidth = view.getMeasuredWidth();
        view.layout(n4 - measuredWidth, childTop, n4, childTop + view.getMeasuredHeight());
        return n4 - (measuredWidth + layoutParams.leftMargin);
    }
    
    private int measureChildCollapseMargins(final View view, final int n, final int n2, final int n3, final int n4, final int[] array) {
        final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)view.getLayoutParams();
        final int n5 = viewGroup$MarginLayoutParams.leftMargin - array[0];
        final int n6 = viewGroup$MarginLayoutParams.rightMargin - array[1];
        final int n7 = Math.max(0, n5) + Math.max(0, n6);
        array[0] = Math.max(0, -n5);
        array[1] = Math.max(0, -n6);
        view.measure(getChildMeasureSpec(n, n2 + (n7 + (this.getPaddingLeft() + this.getPaddingRight())), viewGroup$MarginLayoutParams.width), getChildMeasureSpec(n3, n4 + (this.getPaddingTop() + this.getPaddingBottom() + viewGroup$MarginLayoutParams.topMargin + viewGroup$MarginLayoutParams.bottomMargin), viewGroup$MarginLayoutParams.height));
        return n7 + view.getMeasuredWidth();
    }
    
    private void measureChildConstrained(final View view, final int n, final int n2, final int n3, final int n4, final int n5) {
        final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)view.getLayoutParams();
        final int childMeasureSpec = getChildMeasureSpec(n, n2 + (this.getPaddingLeft() + this.getPaddingRight() + viewGroup$MarginLayoutParams.leftMargin + viewGroup$MarginLayoutParams.rightMargin), viewGroup$MarginLayoutParams.width);
        int n6 = getChildMeasureSpec(n3, n4 + (this.getPaddingTop() + this.getPaddingBottom() + viewGroup$MarginLayoutParams.topMargin + viewGroup$MarginLayoutParams.bottomMargin), viewGroup$MarginLayoutParams.height);
        final int mode = View$MeasureSpec.getMode(n6);
        if (mode != 1073741824 && n5 >= 0) {
            int min;
            if (mode != 0) {
                min = Math.min(View$MeasureSpec.getSize(n6), n5);
            }
            else {
                min = n5;
            }
            n6 = View$MeasureSpec.makeMeasureSpec(min, 1073741824);
        }
        view.measure(childMeasureSpec, n6);
    }
    
    private void postShowOverflowMenu() {
        this.removeCallbacks(this.mShowOverflowMenuRunnable);
        this.post(this.mShowOverflowMenuRunnable);
    }
    
    private boolean shouldCollapse() {
        final boolean mCollapsible = this.mCollapsible;
        boolean b = false;
        if (mCollapsible) {
            for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
                final View child = this.getChildAt(i);
                if (this.shouldLayout(child) && child.getMeasuredWidth() > 0) {
                    final int measuredHeight = child.getMeasuredHeight();
                    b = false;
                    if (measuredHeight > 0) {
                        return b;
                    }
                }
            }
            b = true;
        }
        return b;
    }
    
    private boolean shouldLayout(final View view) {
        return view != null && view.getParent() == this && view.getVisibility() != 8;
    }
    
    void addChildrenForExpandedActionView() {
        for (int i = -1 + this.mHiddenViews.size(); i >= 0; --i) {
            this.addView((View)this.mHiddenViews.get(i));
        }
        this.mHiddenViews.clear();
    }
    
    public boolean canShowOverflowMenu() {
        return this.getVisibility() == 0 && this.mMenuView != null && this.mMenuView.isOverflowReserved();
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return super.checkLayoutParams(viewGroup$LayoutParams) && viewGroup$LayoutParams instanceof LayoutParams;
    }
    
    public void collapseActionView() {
        MenuItemImpl mCurrentExpandedItem;
        if (this.mExpandedMenuPresenter == null) {
            mCurrentExpandedItem = null;
        }
        else {
            mCurrentExpandedItem = this.mExpandedMenuPresenter.mCurrentExpandedItem;
        }
        if (mCurrentExpandedItem != null) {
            mCurrentExpandedItem.collapseActionView();
        }
    }
    
    public void dismissPopupMenus() {
        if (this.mMenuView != null) {
            this.mMenuView.dismissPopupMenus();
        }
    }
    
    void ensureCollapseButtonView() {
        if (this.mCollapseButtonView == null) {
            (this.mCollapseButtonView = new AppCompatImageButton(this.getContext(), null, R.attr.toolbarNavigationButtonStyle)).setImageDrawable(this.mCollapseIcon);
            this.mCollapseButtonView.setContentDescription(this.mCollapseDescription);
            final LayoutParams generateDefaultLayoutParams = this.generateDefaultLayoutParams();
            generateDefaultLayoutParams.gravity = (0x800003 | (0x70 & this.mButtonGravity));
            generateDefaultLayoutParams.mViewType = 2;
            this.mCollapseButtonView.setLayoutParams((ViewGroup$LayoutParams)generateDefaultLayoutParams);
            this.mCollapseButtonView.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    Toolbar.this.collapseActionView();
                }
            });
        }
    }
    
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }
    
    public LayoutParams generateLayoutParams(final AttributeSet set) {
        return new LayoutParams(this.getContext(), set);
    }
    
    protected LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        LayoutParams layoutParams;
        if (viewGroup$LayoutParams instanceof LayoutParams) {
            layoutParams = new LayoutParams((LayoutParams)viewGroup$LayoutParams);
        }
        else if (viewGroup$LayoutParams instanceof ActionBar.LayoutParams) {
            layoutParams = new LayoutParams((ActionBar.LayoutParams)viewGroup$LayoutParams);
        }
        else if (viewGroup$LayoutParams instanceof ViewGroup$MarginLayoutParams) {
            layoutParams = new LayoutParams((ViewGroup$MarginLayoutParams)viewGroup$LayoutParams);
        }
        else {
            layoutParams = new LayoutParams(viewGroup$LayoutParams);
        }
        return layoutParams;
    }
    
    public int getContentInsetEnd() {
        int end;
        if (this.mContentInsets != null) {
            end = this.mContentInsets.getEnd();
        }
        else {
            end = 0;
        }
        return end;
    }
    
    public int getContentInsetEndWithActions() {
        int n;
        if (this.mContentInsetEndWithActions != Integer.MIN_VALUE) {
            n = this.mContentInsetEndWithActions;
        }
        else {
            n = this.getContentInsetEnd();
        }
        return n;
    }
    
    public int getContentInsetLeft() {
        int left;
        if (this.mContentInsets != null) {
            left = this.mContentInsets.getLeft();
        }
        else {
            left = 0;
        }
        return left;
    }
    
    public int getContentInsetRight() {
        int right;
        if (this.mContentInsets != null) {
            right = this.mContentInsets.getRight();
        }
        else {
            right = 0;
        }
        return right;
    }
    
    public int getContentInsetStart() {
        int start;
        if (this.mContentInsets != null) {
            start = this.mContentInsets.getStart();
        }
        else {
            start = 0;
        }
        return start;
    }
    
    public int getContentInsetStartWithNavigation() {
        int n;
        if (this.mContentInsetStartWithNavigation != Integer.MIN_VALUE) {
            n = this.mContentInsetStartWithNavigation;
        }
        else {
            n = this.getContentInsetStart();
        }
        return n;
    }
    
    public int getCurrentContentInsetEnd() {
        final ActionMenuView mMenuView = this.mMenuView;
        int n = 0;
        if (mMenuView != null) {
            final MenuBuilder peekMenu = this.mMenuView.peekMenu();
            if (peekMenu != null && peekMenu.hasVisibleItems()) {
                n = 1;
            }
            else {
                n = 0;
            }
        }
        int n2;
        if (n != 0) {
            n2 = Math.max(this.getContentInsetEnd(), Math.max(this.mContentInsetEndWithActions, 0));
        }
        else {
            n2 = this.getContentInsetEnd();
        }
        return n2;
    }
    
    public int getCurrentContentInsetLeft() {
        int n;
        if (ViewCompat.getLayoutDirection((View)this) == 1) {
            n = this.getCurrentContentInsetEnd();
        }
        else {
            n = this.getCurrentContentInsetStart();
        }
        return n;
    }
    
    public int getCurrentContentInsetRight() {
        int n;
        if (ViewCompat.getLayoutDirection((View)this) == 1) {
            n = this.getCurrentContentInsetStart();
        }
        else {
            n = this.getCurrentContentInsetEnd();
        }
        return n;
    }
    
    public int getCurrentContentInsetStart() {
        int n;
        if (this.getNavigationIcon() != null) {
            n = Math.max(this.getContentInsetStart(), Math.max(this.mContentInsetStartWithNavigation, 0));
        }
        else {
            n = this.getContentInsetStart();
        }
        return n;
    }
    
    public Drawable getLogo() {
        Drawable drawable;
        if (this.mLogoView != null) {
            drawable = this.mLogoView.getDrawable();
        }
        else {
            drawable = null;
        }
        return drawable;
    }
    
    public CharSequence getLogoDescription() {
        CharSequence contentDescription;
        if (this.mLogoView != null) {
            contentDescription = this.mLogoView.getContentDescription();
        }
        else {
            contentDescription = null;
        }
        return contentDescription;
    }
    
    public Menu getMenu() {
        this.ensureMenu();
        return this.mMenuView.getMenu();
    }
    
    @Nullable
    public CharSequence getNavigationContentDescription() {
        CharSequence contentDescription;
        if (this.mNavButtonView != null) {
            contentDescription = this.mNavButtonView.getContentDescription();
        }
        else {
            contentDescription = null;
        }
        return contentDescription;
    }
    
    @Nullable
    public Drawable getNavigationIcon() {
        Drawable drawable;
        if (this.mNavButtonView != null) {
            drawable = this.mNavButtonView.getDrawable();
        }
        else {
            drawable = null;
        }
        return drawable;
    }
    
    @Nullable
    public Drawable getOverflowIcon() {
        this.ensureMenu();
        return this.mMenuView.getOverflowIcon();
    }
    
    public int getPopupTheme() {
        return this.mPopupTheme;
    }
    
    public CharSequence getSubtitle() {
        return this.mSubtitleText;
    }
    
    public CharSequence getTitle() {
        return this.mTitleText;
    }
    
    public int getTitleMarginBottom() {
        return this.mTitleMarginBottom;
    }
    
    public int getTitleMarginEnd() {
        return this.mTitleMarginEnd;
    }
    
    public int getTitleMarginStart() {
        return this.mTitleMarginStart;
    }
    
    public int getTitleMarginTop() {
        return this.mTitleMarginTop;
    }
    
    public DecorToolbar getWrapper() {
        if (this.mWrapper == null) {
            this.mWrapper = new ToolbarWidgetWrapper(this, true);
        }
        return this.mWrapper;
    }
    
    public boolean hasExpandedActionView() {
        return this.mExpandedMenuPresenter != null && this.mExpandedMenuPresenter.mCurrentExpandedItem != null;
    }
    
    public boolean hideOverflowMenu() {
        return this.mMenuView != null && this.mMenuView.hideOverflowMenu();
    }
    
    public void inflateMenu(@MenuRes final int n) {
        this.getMenuInflater().inflate(n, this.getMenu());
    }
    
    public boolean isOverflowMenuShowPending() {
        return this.mMenuView != null && this.mMenuView.isOverflowMenuShowPending();
    }
    
    public boolean isOverflowMenuShowing() {
        return this.mMenuView != null && this.mMenuView.isOverflowMenuShowing();
    }
    
    public boolean isTitleTruncated() {
        final TextView mTitleTextView = this.mTitleTextView;
        boolean b = false;
        if (mTitleTextView != null) {
            final Layout layout = this.mTitleTextView.getLayout();
            b = false;
            if (layout != null) {
                final int lineCount = layout.getLineCount();
                int n = 0;
                while (true) {
                    b = false;
                    if (n >= lineCount) {
                        return b;
                    }
                    if (layout.getEllipsisCount(n) > 0) {
                        break;
                    }
                    ++n;
                }
                b = true;
            }
        }
        return b;
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.removeCallbacks(this.mShowOverflowMenuRunnable);
    }
    
    public boolean onHoverEvent(final MotionEvent motionEvent) {
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 9) {
            this.mEatingHover = false;
        }
        if (!this.mEatingHover) {
            final boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.mEatingHover = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.mEatingHover = false;
        }
        return true;
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        boolean b2;
        if (ViewCompat.getLayoutDirection((View)this) == 1) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        final int width = this.getWidth();
        final int height = this.getHeight();
        final int paddingLeft = this.getPaddingLeft();
        final int paddingRight = this.getPaddingRight();
        final int paddingTop = this.getPaddingTop();
        final int paddingBottom = this.getPaddingBottom();
        int n5 = paddingLeft;
        int n6 = width - paddingRight;
        final int[] mTempMargins = this.mTempMargins;
        mTempMargins[mTempMargins[1] = 0] = 0;
        final int minimumHeight = ViewCompat.getMinimumHeight((View)this);
        int min;
        if (minimumHeight >= 0) {
            min = Math.min(minimumHeight, n4 - n2);
        }
        else {
            min = 0;
        }
        if (this.shouldLayout((View)this.mNavButtonView)) {
            if (b2) {
                n6 = this.layoutChildRight((View)this.mNavButtonView, n6, mTempMargins, min);
            }
            else {
                n5 = this.layoutChildLeft((View)this.mNavButtonView, n5, mTempMargins, min);
            }
        }
        if (this.shouldLayout((View)this.mCollapseButtonView)) {
            if (b2) {
                n6 = this.layoutChildRight((View)this.mCollapseButtonView, n6, mTempMargins, min);
            }
            else {
                n5 = this.layoutChildLeft((View)this.mCollapseButtonView, n5, mTempMargins, min);
            }
        }
        if (this.shouldLayout((View)this.mMenuView)) {
            if (b2) {
                n5 = this.layoutChildLeft((View)this.mMenuView, n5, mTempMargins, min);
            }
            else {
                n6 = this.layoutChildRight((View)this.mMenuView, n6, mTempMargins, min);
            }
        }
        final int currentContentInsetLeft = this.getCurrentContentInsetLeft();
        final int currentContentInsetRight = this.getCurrentContentInsetRight();
        mTempMargins[0] = Math.max(0, currentContentInsetLeft - n5);
        mTempMargins[1] = Math.max(0, currentContentInsetRight - (width - paddingRight - n6));
        int n7 = Math.max(n5, currentContentInsetLeft);
        int n8 = Math.min(n6, width - paddingRight - currentContentInsetRight);
        if (this.shouldLayout(this.mExpandedActionView)) {
            if (b2) {
                n8 = this.layoutChildRight(this.mExpandedActionView, n8, mTempMargins, min);
            }
            else {
                n7 = this.layoutChildLeft(this.mExpandedActionView, n7, mTempMargins, min);
            }
        }
        if (this.shouldLayout((View)this.mLogoView)) {
            if (b2) {
                n8 = this.layoutChildRight((View)this.mLogoView, n8, mTempMargins, min);
            }
            else {
                n7 = this.layoutChildLeft((View)this.mLogoView, n7, mTempMargins, min);
            }
        }
        final boolean shouldLayout = this.shouldLayout((View)this.mTitleTextView);
        final boolean shouldLayout2 = this.shouldLayout((View)this.mSubtitleTextView);
        int n9 = 0;
        if (shouldLayout) {
            final LayoutParams layoutParams = (LayoutParams)this.mTitleTextView.getLayoutParams();
            n9 = 0 + (layoutParams.topMargin + this.mTitleTextView.getMeasuredHeight() + layoutParams.bottomMargin);
        }
        if (shouldLayout2) {
            final LayoutParams layoutParams2 = (LayoutParams)this.mSubtitleTextView.getLayoutParams();
            n9 += layoutParams2.topMargin + this.mSubtitleTextView.getMeasuredHeight() + layoutParams2.bottomMargin;
        }
        if (shouldLayout || shouldLayout2) {
            TextView textView;
            if (shouldLayout) {
                textView = this.mTitleTextView;
            }
            else {
                textView = this.mSubtitleTextView;
            }
            TextView textView2;
            if (shouldLayout2) {
                textView2 = this.mSubtitleTextView;
            }
            else {
                textView2 = this.mTitleTextView;
            }
            final LayoutParams layoutParams3 = (LayoutParams)((View)textView).getLayoutParams();
            final LayoutParams layoutParams4 = (LayoutParams)((View)textView2).getLayoutParams();
            boolean b3;
            if ((shouldLayout && this.mTitleTextView.getMeasuredWidth() > 0) || (shouldLayout2 && this.mSubtitleTextView.getMeasuredWidth() > 0)) {
                b3 = true;
            }
            else {
                b3 = false;
            }
            int n11 = 0;
            switch (0x70 & this.mGravity) {
                default: {
                    int max = (height - paddingTop - paddingBottom - n9) / 2;
                    if (max < layoutParams3.topMargin + this.mTitleMarginTop) {
                        max = layoutParams3.topMargin + this.mTitleMarginTop;
                    }
                    else {
                        final int n10 = height - paddingBottom - n9 - max - paddingTop;
                        if (n10 < layoutParams3.bottomMargin + this.mTitleMarginBottom) {
                            max = Math.max(0, max - (layoutParams4.bottomMargin + this.mTitleMarginBottom - n10));
                        }
                    }
                    n11 = paddingTop + max;
                    break;
                }
                case 48: {
                    n11 = this.getPaddingTop() + layoutParams3.topMargin + this.mTitleMarginTop;
                    break;
                }
                case 80: {
                    n11 = height - paddingBottom - layoutParams4.bottomMargin - this.mTitleMarginBottom - n9;
                    break;
                }
            }
            if (b2) {
                int mTitleMarginStart;
                if (b3) {
                    mTitleMarginStart = this.mTitleMarginStart;
                }
                else {
                    mTitleMarginStart = 0;
                }
                final int n12 = mTitleMarginStart - mTempMargins[1];
                n8 -= Math.max(0, n12);
                mTempMargins[1] = Math.max(0, -n12);
                int n13 = n8;
                int n14 = n8;
                if (shouldLayout) {
                    final LayoutParams layoutParams5 = (LayoutParams)this.mTitleTextView.getLayoutParams();
                    final int n15 = n13 - this.mTitleTextView.getMeasuredWidth();
                    final int n16 = n11 + this.mTitleTextView.getMeasuredHeight();
                    this.mTitleTextView.layout(n15, n11, n13, n16);
                    n13 = n15 - this.mTitleMarginEnd;
                    n11 = n16 + layoutParams5.bottomMargin;
                }
                if (shouldLayout2) {
                    final LayoutParams layoutParams6 = (LayoutParams)this.mSubtitleTextView.getLayoutParams();
                    final int n17 = n11 + layoutParams6.topMargin;
                    final int n18 = n14 - this.mSubtitleTextView.getMeasuredWidth();
                    final int n19 = n17 + this.mSubtitleTextView.getMeasuredHeight();
                    this.mSubtitleTextView.layout(n18, n17, n14, n19);
                    n14 -= this.mTitleMarginEnd;
                    final int n20 = n19 + layoutParams6.bottomMargin;
                }
                if (b3) {
                    n8 = Math.min(n13, n14);
                }
            }
            else {
                int mTitleMarginStart2;
                if (b3) {
                    mTitleMarginStart2 = this.mTitleMarginStart;
                }
                else {
                    mTitleMarginStart2 = 0;
                }
                final int n21 = mTitleMarginStart2 - mTempMargins[0];
                n7 += Math.max(0, n21);
                mTempMargins[0] = Math.max(0, -n21);
                int n22 = n7;
                int n23 = n7;
                if (shouldLayout) {
                    final LayoutParams layoutParams7 = (LayoutParams)this.mTitleTextView.getLayoutParams();
                    final int n24 = n22 + this.mTitleTextView.getMeasuredWidth();
                    final int n25 = n11 + this.mTitleTextView.getMeasuredHeight();
                    this.mTitleTextView.layout(n22, n11, n24, n25);
                    n22 = n24 + this.mTitleMarginEnd;
                    n11 = n25 + layoutParams7.bottomMargin;
                }
                if (shouldLayout2) {
                    final LayoutParams layoutParams8 = (LayoutParams)this.mSubtitleTextView.getLayoutParams();
                    final int n26 = n11 + layoutParams8.topMargin;
                    final int n27 = n23 + this.mSubtitleTextView.getMeasuredWidth();
                    final int n28 = n26 + this.mSubtitleTextView.getMeasuredHeight();
                    this.mSubtitleTextView.layout(n23, n26, n27, n28);
                    n23 = n27 + this.mTitleMarginEnd;
                    final int n29 = n28 + layoutParams8.bottomMargin;
                }
                if (b3) {
                    n7 = Math.max(n22, n23);
                }
            }
        }
        this.addCustomViewsWithGravity(this.mTempViews, 3);
        for (int size = this.mTempViews.size(), i = 0; i < size; ++i) {
            n7 = this.layoutChildLeft(this.mTempViews.get(i), n7, mTempMargins, min);
        }
        this.addCustomViewsWithGravity(this.mTempViews, 5);
        for (int size2 = this.mTempViews.size(), j = 0; j < size2; ++j) {
            n8 = this.layoutChildRight(this.mTempViews.get(j), n8, mTempMargins, min);
        }
        this.addCustomViewsWithGravity(this.mTempViews, 1);
        final int viewListMeasuredWidth = this.getViewListMeasuredWidth(this.mTempViews, mTempMargins);
        int layoutChildLeft = paddingLeft + (width - paddingLeft - paddingRight) / 2 - viewListMeasuredWidth / 2;
        final int n30 = layoutChildLeft + viewListMeasuredWidth;
        if (layoutChildLeft < n7) {
            layoutChildLeft = n7;
        }
        else if (n30 > n8) {
            layoutChildLeft -= n30 - n8;
        }
        for (int size3 = this.mTempViews.size(), k = 0; k < size3; ++k) {
            layoutChildLeft = this.layoutChildLeft(this.mTempViews.get(k), layoutChildLeft, mTempMargins, min);
        }
        this.mTempViews.clear();
    }
    
    protected void onMeasure(final int n, final int n2) {
        final int[] mTempMargins = this.mTempMargins;
        int n3;
        int n4;
        if (ViewUtils.isLayoutRtl((View)this)) {
            n3 = 1;
            n4 = 0;
        }
        else {
            n4 = 1;
            n3 = 0;
        }
        final boolean shouldLayout = this.shouldLayout((View)this.mNavButtonView);
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        if (shouldLayout) {
            this.measureChildConstrained((View)this.mNavButtonView, n, 0, n2, 0, this.mMaxButtonHeight);
            n7 = this.mNavButtonView.getMeasuredWidth() + this.getHorizontalMargins((View)this.mNavButtonView);
            n6 = Math.max(0, this.mNavButtonView.getMeasuredHeight() + this.getVerticalMargins((View)this.mNavButtonView));
            n5 = ViewUtils.combineMeasuredStates(0, ViewCompat.getMeasuredState((View)this.mNavButtonView));
        }
        if (this.shouldLayout((View)this.mCollapseButtonView)) {
            this.measureChildConstrained((View)this.mCollapseButtonView, n, 0, n2, 0, this.mMaxButtonHeight);
            n7 = this.mCollapseButtonView.getMeasuredWidth() + this.getHorizontalMargins((View)this.mCollapseButtonView);
            n6 = Math.max(n6, this.mCollapseButtonView.getMeasuredHeight() + this.getVerticalMargins((View)this.mCollapseButtonView));
            n5 = ViewUtils.combineMeasuredStates(n5, ViewCompat.getMeasuredState((View)this.mCollapseButtonView));
        }
        final int currentContentInsetStart = this.getCurrentContentInsetStart();
        final int n8 = 0 + Math.max(currentContentInsetStart, n7);
        mTempMargins[n3] = Math.max(0, currentContentInsetStart - n7);
        final boolean shouldLayout2 = this.shouldLayout((View)this.mMenuView);
        int n9 = 0;
        if (shouldLayout2) {
            this.measureChildConstrained((View)this.mMenuView, n, n8, n2, 0, this.mMaxButtonHeight);
            n9 = this.mMenuView.getMeasuredWidth() + this.getHorizontalMargins((View)this.mMenuView);
            n6 = Math.max(n6, this.mMenuView.getMeasuredHeight() + this.getVerticalMargins((View)this.mMenuView));
            n5 = ViewUtils.combineMeasuredStates(n5, ViewCompat.getMeasuredState((View)this.mMenuView));
        }
        final int currentContentInsetEnd = this.getCurrentContentInsetEnd();
        int n10 = n8 + Math.max(currentContentInsetEnd, n9);
        mTempMargins[n4] = Math.max(0, currentContentInsetEnd - n9);
        if (this.shouldLayout(this.mExpandedActionView)) {
            n10 += this.measureChildCollapseMargins(this.mExpandedActionView, n, n10, n2, 0, mTempMargins);
            n6 = Math.max(n6, this.mExpandedActionView.getMeasuredHeight() + this.getVerticalMargins(this.mExpandedActionView));
            n5 = ViewUtils.combineMeasuredStates(n5, ViewCompat.getMeasuredState(this.mExpandedActionView));
        }
        if (this.shouldLayout((View)this.mLogoView)) {
            n10 += this.measureChildCollapseMargins((View)this.mLogoView, n, n10, n2, 0, mTempMargins);
            n6 = Math.max(n6, this.mLogoView.getMeasuredHeight() + this.getVerticalMargins((View)this.mLogoView));
            n5 = ViewUtils.combineMeasuredStates(n5, ViewCompat.getMeasuredState((View)this.mLogoView));
        }
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.getChildAt(i);
            if (((LayoutParams)child.getLayoutParams()).mViewType == 0 && this.shouldLayout(child)) {
                n10 += this.measureChildCollapseMargins(child, n, n10, n2, 0, mTempMargins);
                n6 = Math.max(n6, child.getMeasuredHeight() + this.getVerticalMargins(child));
                n5 = ViewUtils.combineMeasuredStates(n5, ViewCompat.getMeasuredState(child));
            }
        }
        final int n11 = this.mTitleMarginTop + this.mTitleMarginBottom;
        final int n12 = this.mTitleMarginStart + this.mTitleMarginEnd;
        final boolean shouldLayout3 = this.shouldLayout((View)this.mTitleTextView);
        int n13 = 0;
        int max = 0;
        if (shouldLayout3) {
            this.measureChildCollapseMargins((View)this.mTitleTextView, n, n10 + n12, n2, n11, mTempMargins);
            max = this.mTitleTextView.getMeasuredWidth() + this.getHorizontalMargins((View)this.mTitleTextView);
            n13 = this.mTitleTextView.getMeasuredHeight() + this.getVerticalMargins((View)this.mTitleTextView);
            n5 = ViewUtils.combineMeasuredStates(n5, ViewCompat.getMeasuredState((View)this.mTitleTextView));
        }
        if (this.shouldLayout((View)this.mSubtitleTextView)) {
            max = Math.max(max, this.measureChildCollapseMargins((View)this.mSubtitleTextView, n, n10 + n12, n2, n13 + n11, mTempMargins));
            n13 += this.mSubtitleTextView.getMeasuredHeight() + this.getVerticalMargins((View)this.mSubtitleTextView);
            n5 = ViewUtils.combineMeasuredStates(n5, ViewCompat.getMeasuredState((View)this.mSubtitleTextView));
        }
        final int n14 = n10 + max;
        final int max2 = Math.max(n6, n13);
        final int n15 = n14 + (this.getPaddingLeft() + this.getPaddingRight());
        final int n16 = max2 + (this.getPaddingTop() + this.getPaddingBottom());
        final int resolveSizeAndState = ViewCompat.resolveSizeAndState(Math.max(n15, this.getSuggestedMinimumWidth()), n, 0xFF000000 & n5);
        int resolveSizeAndState2 = ViewCompat.resolveSizeAndState(Math.max(n16, this.getSuggestedMinimumHeight()), n2, n5 << 16);
        if (this.shouldCollapse()) {
            resolveSizeAndState2 = 0;
        }
        this.setMeasuredDimension(resolveSizeAndState, resolveSizeAndState2);
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
        }
        else {
            final SavedState savedState = (SavedState)parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            Object peekMenu;
            if (this.mMenuView != null) {
                peekMenu = this.mMenuView.peekMenu();
            }
            else {
                peekMenu = null;
            }
            if (savedState.expandedMenuItemId != 0 && this.mExpandedMenuPresenter != null && peekMenu != null) {
                final MenuItem item = ((Menu)peekMenu).findItem(savedState.expandedMenuItemId);
                if (item != null) {
                    MenuItemCompat.expandActionView(item);
                }
            }
            if (savedState.isOverflowOpen) {
                this.postShowOverflowMenu();
            }
        }
    }
    
    public void onRtlPropertiesChanged(final int n) {
        boolean direction = true;
        if (Build$VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(n);
        }
        this.ensureContentInsets();
        final RtlSpacingHelper mContentInsets = this.mContentInsets;
        if (n != (direction ? 1 : 0)) {
            direction = false;
        }
        mContentInsets.setDirection(direction);
    }
    
    protected Parcelable onSaveInstanceState() {
        final SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.mExpandedMenuPresenter != null && this.mExpandedMenuPresenter.mCurrentExpandedItem != null) {
            savedState.expandedMenuItemId = this.mExpandedMenuPresenter.mCurrentExpandedItem.getItemId();
        }
        savedState.isOverflowOpen = this.isOverflowMenuShowing();
        return (Parcelable)savedState;
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 0) {
            this.mEatingTouch = false;
        }
        if (!this.mEatingTouch) {
            final boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.mEatingTouch = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.mEatingTouch = false;
        }
        return true;
    }
    
    void removeChildrenForExpandedActionView() {
        for (int i = -1 + this.getChildCount(); i >= 0; --i) {
            final View child = this.getChildAt(i);
            if (((LayoutParams)child.getLayoutParams()).mViewType != 2 && child != this.mMenuView) {
                this.removeViewAt(i);
                this.mHiddenViews.add(child);
            }
        }
    }
    
    public void setCollapsible(final boolean mCollapsible) {
        this.mCollapsible = mCollapsible;
        this.requestLayout();
    }
    
    public void setContentInsetEndWithActions(int mContentInsetEndWithActions) {
        if (mContentInsetEndWithActions < 0) {
            mContentInsetEndWithActions = Integer.MIN_VALUE;
        }
        if (mContentInsetEndWithActions != this.mContentInsetEndWithActions) {
            this.mContentInsetEndWithActions = mContentInsetEndWithActions;
            if (this.getNavigationIcon() != null) {
                this.requestLayout();
            }
        }
    }
    
    public void setContentInsetStartWithNavigation(int mContentInsetStartWithNavigation) {
        if (mContentInsetStartWithNavigation < 0) {
            mContentInsetStartWithNavigation = Integer.MIN_VALUE;
        }
        if (mContentInsetStartWithNavigation != this.mContentInsetStartWithNavigation) {
            this.mContentInsetStartWithNavigation = mContentInsetStartWithNavigation;
            if (this.getNavigationIcon() != null) {
                this.requestLayout();
            }
        }
    }
    
    public void setContentInsetsAbsolute(final int n, final int n2) {
        this.ensureContentInsets();
        this.mContentInsets.setAbsolute(n, n2);
    }
    
    public void setContentInsetsRelative(final int n, final int n2) {
        this.ensureContentInsets();
        this.mContentInsets.setRelative(n, n2);
    }
    
    public void setLogo(@DrawableRes final int n) {
        this.setLogo(AppCompatResources.getDrawable(this.getContext(), n));
    }
    
    public void setLogo(final Drawable imageDrawable) {
        if (imageDrawable != null) {
            this.ensureLogoView();
            if (!this.isChildOrHidden((View)this.mLogoView)) {
                this.addSystemView((View)this.mLogoView, true);
            }
        }
        else if (this.mLogoView != null && this.isChildOrHidden((View)this.mLogoView)) {
            this.removeView((View)this.mLogoView);
            this.mHiddenViews.remove(this.mLogoView);
        }
        if (this.mLogoView != null) {
            this.mLogoView.setImageDrawable(imageDrawable);
        }
    }
    
    public void setLogoDescription(@StringRes final int n) {
        this.setLogoDescription(this.getContext().getText(n));
    }
    
    public void setLogoDescription(final CharSequence contentDescription) {
        if (!TextUtils.isEmpty(contentDescription)) {
            this.ensureLogoView();
        }
        if (this.mLogoView != null) {
            this.mLogoView.setContentDescription(contentDescription);
        }
    }
    
    public void setMenu(final MenuBuilder menuBuilder, final ActionMenuPresenter actionMenuPresenter) {
        if (menuBuilder != null || this.mMenuView != null) {
            this.ensureMenuView();
            final MenuBuilder peekMenu = this.mMenuView.peekMenu();
            if (peekMenu != menuBuilder) {
                if (peekMenu != null) {
                    peekMenu.removeMenuPresenter(this.mOuterActionMenuPresenter);
                    peekMenu.removeMenuPresenter(this.mExpandedMenuPresenter);
                }
                if (this.mExpandedMenuPresenter == null) {
                    this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter();
                }
                actionMenuPresenter.setExpandedActionViewsExclusive(true);
                if (menuBuilder != null) {
                    menuBuilder.addMenuPresenter(actionMenuPresenter, this.mPopupContext);
                    menuBuilder.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
                }
                else {
                    actionMenuPresenter.initForMenu(this.mPopupContext, null);
                    this.mExpandedMenuPresenter.initForMenu(this.mPopupContext, null);
                    actionMenuPresenter.updateMenuView(true);
                    this.mExpandedMenuPresenter.updateMenuView(true);
                }
                this.mMenuView.setPopupTheme(this.mPopupTheme);
                this.mMenuView.setPresenter(actionMenuPresenter);
                this.mOuterActionMenuPresenter = actionMenuPresenter;
            }
        }
    }
    
    public void setMenuCallbacks(final MenuPresenter.Callback mActionMenuPresenterCallback, final MenuBuilder.Callback mMenuBuilderCallback) {
        this.mActionMenuPresenterCallback = mActionMenuPresenterCallback;
        this.mMenuBuilderCallback = mMenuBuilderCallback;
        if (this.mMenuView != null) {
            this.mMenuView.setMenuCallbacks(mActionMenuPresenterCallback, mMenuBuilderCallback);
        }
    }
    
    public void setNavigationContentDescription(@StringRes final int n) {
        CharSequence text;
        if (n != 0) {
            text = this.getContext().getText(n);
        }
        else {
            text = null;
        }
        this.setNavigationContentDescription(text);
    }
    
    public void setNavigationContentDescription(@Nullable final CharSequence contentDescription) {
        if (!TextUtils.isEmpty(contentDescription)) {
            this.ensureNavButtonView();
        }
        if (this.mNavButtonView != null) {
            this.mNavButtonView.setContentDescription(contentDescription);
        }
    }
    
    public void setNavigationIcon(@DrawableRes final int n) {
        this.setNavigationIcon(AppCompatResources.getDrawable(this.getContext(), n));
    }
    
    public void setNavigationIcon(@Nullable final Drawable imageDrawable) {
        if (imageDrawable != null) {
            this.ensureNavButtonView();
            if (!this.isChildOrHidden((View)this.mNavButtonView)) {
                this.addSystemView((View)this.mNavButtonView, true);
            }
        }
        else if (this.mNavButtonView != null && this.isChildOrHidden((View)this.mNavButtonView)) {
            this.removeView((View)this.mNavButtonView);
            this.mHiddenViews.remove(this.mNavButtonView);
        }
        if (this.mNavButtonView != null) {
            this.mNavButtonView.setImageDrawable(imageDrawable);
        }
    }
    
    public void setNavigationOnClickListener(final View$OnClickListener onClickListener) {
        this.ensureNavButtonView();
        this.mNavButtonView.setOnClickListener(onClickListener);
    }
    
    public void setOnMenuItemClickListener(final OnMenuItemClickListener mOnMenuItemClickListener) {
        this.mOnMenuItemClickListener = mOnMenuItemClickListener;
    }
    
    public void setOverflowIcon(@Nullable final Drawable overflowIcon) {
        this.ensureMenu();
        this.mMenuView.setOverflowIcon(overflowIcon);
    }
    
    public void setPopupTheme(@StyleRes final int mPopupTheme) {
        if (this.mPopupTheme != mPopupTheme) {
            if ((this.mPopupTheme = mPopupTheme) == 0) {
                this.mPopupContext = this.getContext();
            }
            else {
                this.mPopupContext = (Context)new ContextThemeWrapper(this.getContext(), mPopupTheme);
            }
        }
    }
    
    public void setSubtitle(@StringRes final int n) {
        this.setSubtitle(this.getContext().getText(n));
    }
    
    public void setSubtitle(final CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.mSubtitleTextView == null) {
                final Context context = this.getContext();
                (this.mSubtitleTextView = new AppCompatTextView(context)).setSingleLine();
                this.mSubtitleTextView.setEllipsize(TextUtils$TruncateAt.END);
                if (this.mSubtitleTextAppearance != 0) {
                    this.mSubtitleTextView.setTextAppearance(context, this.mSubtitleTextAppearance);
                }
                if (this.mSubtitleTextColor != 0) {
                    this.mSubtitleTextView.setTextColor(this.mSubtitleTextColor);
                }
            }
            if (!this.isChildOrHidden((View)this.mSubtitleTextView)) {
                this.addSystemView((View)this.mSubtitleTextView, true);
            }
        }
        else if (this.mSubtitleTextView != null && this.isChildOrHidden((View)this.mSubtitleTextView)) {
            this.removeView((View)this.mSubtitleTextView);
            this.mHiddenViews.remove(this.mSubtitleTextView);
        }
        if (this.mSubtitleTextView != null) {
            this.mSubtitleTextView.setText(charSequence);
        }
        this.mSubtitleText = charSequence;
    }
    
    public void setSubtitleTextAppearance(final Context context, @StyleRes final int mSubtitleTextAppearance) {
        this.mSubtitleTextAppearance = mSubtitleTextAppearance;
        if (this.mSubtitleTextView != null) {
            this.mSubtitleTextView.setTextAppearance(context, mSubtitleTextAppearance);
        }
    }
    
    public void setSubtitleTextColor(@ColorInt final int n) {
        this.mSubtitleTextColor = n;
        if (this.mSubtitleTextView != null) {
            this.mSubtitleTextView.setTextColor(n);
        }
    }
    
    public void setTitle(@StringRes final int n) {
        this.setTitle(this.getContext().getText(n));
    }
    
    public void setTitle(final CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.mTitleTextView == null) {
                final Context context = this.getContext();
                (this.mTitleTextView = new AppCompatTextView(context)).setSingleLine();
                this.mTitleTextView.setEllipsize(TextUtils$TruncateAt.END);
                if (this.mTitleTextAppearance != 0) {
                    this.mTitleTextView.setTextAppearance(context, this.mTitleTextAppearance);
                }
                if (this.mTitleTextColor != 0) {
                    this.mTitleTextView.setTextColor(this.mTitleTextColor);
                }
            }
            if (!this.isChildOrHidden((View)this.mTitleTextView)) {
                this.addSystemView((View)this.mTitleTextView, true);
            }
        }
        else if (this.mTitleTextView != null && this.isChildOrHidden((View)this.mTitleTextView)) {
            this.removeView((View)this.mTitleTextView);
            this.mHiddenViews.remove(this.mTitleTextView);
        }
        if (this.mTitleTextView != null) {
            this.mTitleTextView.setText(charSequence);
        }
        this.mTitleText = charSequence;
    }
    
    public void setTitleMargin(final int mTitleMarginStart, final int mTitleMarginTop, final int mTitleMarginEnd, final int mTitleMarginBottom) {
        this.mTitleMarginStart = mTitleMarginStart;
        this.mTitleMarginTop = mTitleMarginTop;
        this.mTitleMarginEnd = mTitleMarginEnd;
        this.mTitleMarginBottom = mTitleMarginBottom;
        this.requestLayout();
    }
    
    public void setTitleMarginBottom(final int mTitleMarginBottom) {
        this.mTitleMarginBottom = mTitleMarginBottom;
        this.requestLayout();
    }
    
    public void setTitleMarginEnd(final int mTitleMarginEnd) {
        this.mTitleMarginEnd = mTitleMarginEnd;
        this.requestLayout();
    }
    
    public void setTitleMarginStart(final int mTitleMarginStart) {
        this.mTitleMarginStart = mTitleMarginStart;
        this.requestLayout();
    }
    
    public void setTitleMarginTop(final int mTitleMarginTop) {
        this.mTitleMarginTop = mTitleMarginTop;
        this.requestLayout();
    }
    
    public void setTitleTextAppearance(final Context context, @StyleRes final int mTitleTextAppearance) {
        this.mTitleTextAppearance = mTitleTextAppearance;
        if (this.mTitleTextView != null) {
            this.mTitleTextView.setTextAppearance(context, mTitleTextAppearance);
        }
    }
    
    public void setTitleTextColor(@ColorInt final int n) {
        this.mTitleTextColor = n;
        if (this.mTitleTextView != null) {
            this.mTitleTextView.setTextColor(n);
        }
    }
    
    public boolean showOverflowMenu() {
        return this.mMenuView != null && this.mMenuView.showOverflowMenu();
    }
    
    private class ExpandedActionViewMenuPresenter implements MenuPresenter
    {
        MenuItemImpl mCurrentExpandedItem;
        MenuBuilder mMenu;
        
        @Override
        public boolean collapseItemActionView(final MenuBuilder menuBuilder, final MenuItemImpl menuItemImpl) {
            if (Toolbar.this.mExpandedActionView instanceof CollapsibleActionView) {
                ((CollapsibleActionView)Toolbar.this.mExpandedActionView).onActionViewCollapsed();
            }
            Toolbar.this.removeView(Toolbar.this.mExpandedActionView);
            Toolbar.this.removeView((View)Toolbar.this.mCollapseButtonView);
            Toolbar.this.mExpandedActionView = null;
            Toolbar.this.addChildrenForExpandedActionView();
            this.mCurrentExpandedItem = null;
            Toolbar.this.requestLayout();
            menuItemImpl.setActionViewExpanded(false);
            return true;
        }
        
        @Override
        public boolean expandItemActionView(final MenuBuilder menuBuilder, final MenuItemImpl mCurrentExpandedItem) {
            Toolbar.this.ensureCollapseButtonView();
            if (Toolbar.this.mCollapseButtonView.getParent() != Toolbar.this) {
                Toolbar.this.addView((View)Toolbar.this.mCollapseButtonView);
            }
            Toolbar.this.mExpandedActionView = mCurrentExpandedItem.getActionView();
            this.mCurrentExpandedItem = mCurrentExpandedItem;
            if (Toolbar.this.mExpandedActionView.getParent() != Toolbar.this) {
                final LayoutParams generateDefaultLayoutParams = Toolbar.this.generateDefaultLayoutParams();
                generateDefaultLayoutParams.gravity = (0x800003 | (0x70 & Toolbar.this.mButtonGravity));
                generateDefaultLayoutParams.mViewType = 2;
                Toolbar.this.mExpandedActionView.setLayoutParams((ViewGroup$LayoutParams)generateDefaultLayoutParams);
                Toolbar.this.addView(Toolbar.this.mExpandedActionView);
            }
            Toolbar.this.removeChildrenForExpandedActionView();
            Toolbar.this.requestLayout();
            mCurrentExpandedItem.setActionViewExpanded(true);
            if (Toolbar.this.mExpandedActionView instanceof CollapsibleActionView) {
                ((CollapsibleActionView)Toolbar.this.mExpandedActionView).onActionViewExpanded();
            }
            return true;
        }
        
        @Override
        public boolean flagActionItems() {
            return false;
        }
        
        @Override
        public int getId() {
            return 0;
        }
        
        @Override
        public MenuView getMenuView(final ViewGroup viewGroup) {
            return null;
        }
        
        @Override
        public void initForMenu(final Context context, final MenuBuilder mMenu) {
            if (this.mMenu != null && this.mCurrentExpandedItem != null) {
                this.mMenu.collapseItemActionView(this.mCurrentExpandedItem);
            }
            this.mMenu = mMenu;
        }
        
        @Override
        public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
        }
        
        @Override
        public void onRestoreInstanceState(final Parcelable parcelable) {
        }
        
        @Override
        public Parcelable onSaveInstanceState() {
            return null;
        }
        
        @Override
        public boolean onSubMenuSelected(final SubMenuBuilder subMenuBuilder) {
            return false;
        }
        
        @Override
        public void setCallback(final Callback callback) {
        }
        
        @Override
        public void updateMenuView(final boolean b) {
            if (this.mCurrentExpandedItem != null) {
                final MenuBuilder mMenu = this.mMenu;
                boolean b2 = false;
                if (mMenu != null) {
                    final int size = this.mMenu.size();
                    int n = 0;
                    while (true) {
                        b2 = false;
                        if (n >= size) {
                            break;
                        }
                        if (this.mMenu.getItem(n) == this.mCurrentExpandedItem) {
                            b2 = true;
                            break;
                        }
                        ++n;
                    }
                }
                if (!b2) {
                    this.collapseItemActionView(this.mMenu, this.mCurrentExpandedItem);
                }
            }
        }
    }
    
    public static class LayoutParams extends ActionBar.LayoutParams
    {
        static final int CUSTOM = 0;
        static final int EXPANDED = 2;
        static final int SYSTEM = 1;
        int mViewType;
        
        public LayoutParams(final int n) {
            this(-2, -1, n);
        }
        
        public LayoutParams(final int n, final int n2) {
            super(n, n2);
            this.mViewType = 0;
            this.gravity = 8388627;
        }
        
        public LayoutParams(final int n, final int n2, final int gravity) {
            super(n, n2);
            this.mViewType = 0;
            this.gravity = gravity;
        }
        
        public LayoutParams(@NonNull final Context context, final AttributeSet set) {
            super(context, set);
            this.mViewType = 0;
        }
        
        public LayoutParams(final ActionBar.LayoutParams layoutParams) {
            super(layoutParams);
            this.mViewType = 0;
        }
        
        public LayoutParams(final LayoutParams layoutParams) {
            super((ActionBar.LayoutParams)layoutParams);
            this.mViewType = 0;
            this.mViewType = layoutParams.mViewType;
        }
        
        public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
            this.mViewType = 0;
        }
        
        public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            super((ViewGroup$LayoutParams)viewGroup$MarginLayoutParams);
            this.mViewType = 0;
            this.copyMarginsFromCompat(viewGroup$MarginLayoutParams);
        }
        
        void copyMarginsFromCompat(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            this.leftMargin = viewGroup$MarginLayoutParams.leftMargin;
            this.topMargin = viewGroup$MarginLayoutParams.topMargin;
            this.rightMargin = viewGroup$MarginLayoutParams.rightMargin;
            this.bottomMargin = viewGroup$MarginLayoutParams.bottomMargin;
        }
    }
    
    public interface OnMenuItemClickListener
    {
        boolean onMenuItemClick(final MenuItem p0);
    }
    
    public static class SavedState extends AbsSavedState
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        int expandedMenuItemId;
        boolean isOverflowOpen;
        
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
        
        public SavedState(final Parcel parcel) {
            this(parcel, null);
        }
        
        public SavedState(final Parcel parcel, final ClassLoader classLoader) {
            super(parcel, classLoader);
            this.expandedMenuItemId = parcel.readInt();
            this.isOverflowOpen = (parcel.readInt() != 0);
        }
        
        public SavedState(final Parcelable parcelable) {
            super(parcelable);
        }
        
        @Override
        public void writeToParcel(final Parcel parcel, final int n) {
            super.writeToParcel(parcel, n);
            parcel.writeInt(this.expandedMenuItemId);
            int n2;
            if (this.isOverflowOpen) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            parcel.writeInt(n2);
        }
    }
}
