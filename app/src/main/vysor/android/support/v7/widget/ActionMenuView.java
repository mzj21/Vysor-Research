// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.ViewDebug$ExportedProperty;
import android.view.ContextThemeWrapper;
import android.support.annotation.StyleRes;
import android.content.res.Configuration;
import android.view.MenuItem;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.annotation.Nullable;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.accessibility.AccessibilityEvent;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.View$MeasureSpec;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.MenuBuilder;

public class ActionMenuView extends LinearLayoutCompat implements ItemInvoker, MenuView
{
    static final int GENERATED_ITEM_PADDING = 4;
    static final int MIN_CELL_SIZE = 56;
    private static final String TAG = "ActionMenuView";
    private MenuPresenter.Callback mActionMenuPresenterCallback;
    private boolean mFormatItems;
    private int mFormatItemsWidth;
    private int mGeneratedItemPadding;
    private MenuBuilder mMenu;
    Callback mMenuBuilderCallback;
    private int mMinCellSize;
    OnMenuItemClickListener mOnMenuItemClickListener;
    private Context mPopupContext;
    private int mPopupTheme;
    private ActionMenuPresenter mPresenter;
    private boolean mReserveOverflow;
    
    public ActionMenuView(final Context context) {
        this(context, null);
    }
    
    public ActionMenuView(final Context mPopupContext, final AttributeSet set) {
        super(mPopupContext, set);
        this.setBaselineAligned(false);
        final float density = mPopupContext.getResources().getDisplayMetrics().density;
        this.mMinCellSize = (int)(56.0f * density);
        this.mGeneratedItemPadding = (int)(4.0f * density);
        this.mPopupContext = mPopupContext;
        this.mPopupTheme = 0;
    }
    
    static int measureChildForCells(final View view, final int n, final int n2, final int n3, final int n4) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(n3) - n4, View$MeasureSpec.getMode(n3));
        ActionMenuItemView actionMenuItemView;
        if (view instanceof ActionMenuItemView) {
            actionMenuItemView = (ActionMenuItemView)view;
        }
        else {
            actionMenuItemView = null;
        }
        boolean b;
        if (actionMenuItemView != null && actionMenuItemView.hasText()) {
            b = true;
        }
        else {
            b = false;
        }
        int cellsUsed = 0;
        Label_0124: {
            if (n2 > 0) {
                if (b) {
                    cellsUsed = 0;
                    if (n2 < 2) {
                        break Label_0124;
                    }
                }
                view.measure(View$MeasureSpec.makeMeasureSpec(n * n2, Integer.MIN_VALUE), measureSpec);
                final int measuredWidth = view.getMeasuredWidth();
                cellsUsed = measuredWidth / n;
                if (measuredWidth % n != 0) {
                    ++cellsUsed;
                }
                if (b && cellsUsed < 2) {
                    cellsUsed = 2;
                }
            }
        }
        layoutParams.expandable = (!layoutParams.isOverflowButton && b);
        layoutParams.cellsUsed = cellsUsed;
        view.measure(View$MeasureSpec.makeMeasureSpec(cellsUsed * n, 1073741824), measureSpec);
        return cellsUsed;
    }
    
    private void onMeasureExactFormat(final int n, final int n2) {
        final int mode = View$MeasureSpec.getMode(n2);
        final int size = View$MeasureSpec.getSize(n);
        int size2 = View$MeasureSpec.getSize(n2);
        final int n3 = this.getPaddingLeft() + this.getPaddingRight();
        final int n4 = this.getPaddingTop() + this.getPaddingBottom();
        final int childMeasureSpec = getChildMeasureSpec(n2, n4, -2);
        final int n5 = size - n3;
        final int n6 = n5 / this.mMinCellSize;
        final int n7 = n5 % this.mMinCellSize;
        if (n6 == 0) {
            this.setMeasuredDimension(n5, 0);
        }
        else {
            final int n8 = this.mMinCellSize + n7 / n6;
            int n9 = n6;
            int max = 0;
            int max2 = 0;
            int n10 = 0;
            int n11 = 0;
            boolean b = false;
            long n12 = 0L;
            final int childCount = this.getChildCount();
            for (int i = 0; i < childCount; ++i) {
                final View child = this.getChildAt(i);
                if (child.getVisibility() != 8) {
                    final boolean b2 = child instanceof ActionMenuItemView;
                    ++n11;
                    if (b2) {
                        child.setPadding(this.mGeneratedItemPadding, 0, this.mGeneratedItemPadding, 0);
                    }
                    final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
                    layoutParams.expanded = false;
                    layoutParams.extraPixels = 0;
                    layoutParams.cellsUsed = 0;
                    layoutParams.expandable = false;
                    layoutParams.leftMargin = 0;
                    layoutParams.rightMargin = 0;
                    layoutParams.preventEdgeOffset = (b2 && ((ActionMenuItemView)child).hasText());
                    int n13;
                    if (layoutParams.isOverflowButton) {
                        n13 = 1;
                    }
                    else {
                        n13 = n9;
                    }
                    final int measureChildForCells = measureChildForCells(child, n8, n13, childMeasureSpec, n4);
                    max2 = Math.max(max2, measureChildForCells);
                    if (layoutParams.expandable) {
                        ++n10;
                    }
                    if (layoutParams.isOverflowButton) {
                        b = true;
                    }
                    n9 -= measureChildForCells;
                    max = Math.max(max, child.getMeasuredHeight());
                    if (measureChildForCells == 1) {
                        n12 |= 1 << i;
                    }
                }
            }
            final boolean b3 = b && n11 == 2;
            boolean b4 = false;
            while (n10 > 0 && n9 > 0) {
                int cellsUsed = Integer.MAX_VALUE;
                long n14 = 0L;
                int n15 = 0;
                for (int j = 0; j < childCount; ++j) {
                    final LayoutParams layoutParams2 = (LayoutParams)this.getChildAt(j).getLayoutParams();
                    if (layoutParams2.expandable) {
                        if (layoutParams2.cellsUsed < cellsUsed) {
                            cellsUsed = layoutParams2.cellsUsed;
                            n14 = 1 << j;
                            n15 = 1;
                        }
                        else if (layoutParams2.cellsUsed == cellsUsed) {
                            n14 |= 1 << j;
                            ++n15;
                        }
                    }
                }
                n12 |= n14;
                if (n15 > n9) {
                    break;
                }
                final int n16 = cellsUsed + 1;
                for (int k = 0; k < childCount; ++k) {
                    final View child2 = this.getChildAt(k);
                    final LayoutParams layoutParams3 = (LayoutParams)child2.getLayoutParams();
                    if ((n14 & 1 << k) == 0x0L) {
                        if (layoutParams3.cellsUsed == n16) {
                            n12 |= 1 << k;
                        }
                    }
                    else {
                        if (b3 && layoutParams3.preventEdgeOffset && n9 == 1) {
                            child2.setPadding(n8 + this.mGeneratedItemPadding, 0, this.mGeneratedItemPadding, 0);
                        }
                        ++layoutParams3.cellsUsed;
                        layoutParams3.expanded = true;
                        --n9;
                    }
                }
                b4 = true;
            }
            boolean b5;
            if (!b && n11 == 1) {
                b5 = true;
            }
            else {
                b5 = false;
            }
            if (n9 > 0 && n12 != 0L && (n9 < n11 - 1 || b5 || max2 > 1)) {
                float n17 = Long.bitCount(n12);
                if (!b5) {
                    if ((0x1L & n12) != 0x0L && !((LayoutParams)this.getChildAt(0).getLayoutParams()).preventEdgeOffset) {
                        n17 -= 0.5f;
                    }
                    if ((n12 & 1 << childCount - 1) != 0x0L && !((LayoutParams)this.getChildAt(childCount - 1).getLayoutParams()).preventEdgeOffset) {
                        n17 -= 0.5f;
                    }
                }
                int n18;
                if (n17 > 0.0f) {
                    n18 = (int)(n9 * n8 / n17);
                }
                else {
                    n18 = 0;
                }
                for (int l = 0; l < childCount; ++l) {
                    if ((n12 & 1 << l) != 0x0L) {
                        final View child3 = this.getChildAt(l);
                        final LayoutParams layoutParams4 = (LayoutParams)child3.getLayoutParams();
                        if (child3 instanceof ActionMenuItemView) {
                            layoutParams4.extraPixels = n18;
                            layoutParams4.expanded = true;
                            if (l == 0 && !layoutParams4.preventEdgeOffset) {
                                layoutParams4.leftMargin = -n18 / 2;
                            }
                            b4 = true;
                        }
                        else if (layoutParams4.isOverflowButton) {
                            layoutParams4.extraPixels = n18;
                            layoutParams4.expanded = true;
                            layoutParams4.rightMargin = -n18 / 2;
                            b4 = true;
                        }
                        else {
                            if (l != 0) {
                                layoutParams4.leftMargin = n18 / 2;
                            }
                            if (l != childCount - 1) {
                                layoutParams4.rightMargin = n18 / 2;
                            }
                        }
                    }
                }
            }
            if (b4) {
                for (int n19 = 0; n19 < childCount; ++n19) {
                    final View child4 = this.getChildAt(n19);
                    final LayoutParams layoutParams5 = (LayoutParams)child4.getLayoutParams();
                    if (layoutParams5.expanded) {
                        child4.measure(View$MeasureSpec.makeMeasureSpec(n8 * layoutParams5.cellsUsed + layoutParams5.extraPixels, 1073741824), childMeasureSpec);
                    }
                }
            }
            if (mode != 1073741824) {
                size2 = max;
            }
            this.setMeasuredDimension(n5, size2);
        }
    }
    
    @Override
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams != null && viewGroup$LayoutParams instanceof LayoutParams;
    }
    
    public void dismissPopupMenus() {
        if (this.mPresenter != null) {
            this.mPresenter.dismissPopupMenus();
        }
    }
    
    public boolean dispatchPopulateAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        return false;
    }
    
    protected LayoutParams generateDefaultLayoutParams() {
        final LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 16;
        return layoutParams;
    }
    
    public LayoutParams generateLayoutParams(final AttributeSet set) {
        return new LayoutParams(this.getContext(), set);
    }
    
    protected LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        LayoutParams generateDefaultLayoutParams;
        if (viewGroup$LayoutParams != null) {
            if (viewGroup$LayoutParams instanceof LayoutParams) {
                generateDefaultLayoutParams = new LayoutParams((LayoutParams)viewGroup$LayoutParams);
            }
            else {
                generateDefaultLayoutParams = new LayoutParams(viewGroup$LayoutParams);
            }
            if (generateDefaultLayoutParams.gravity <= 0) {
                generateDefaultLayoutParams.gravity = 16;
            }
        }
        else {
            generateDefaultLayoutParams = this.generateDefaultLayoutParams();
        }
        return generateDefaultLayoutParams;
    }
    
    public LayoutParams generateOverflowButtonLayoutParams() {
        final LayoutParams generateDefaultLayoutParams = this.generateDefaultLayoutParams();
        generateDefaultLayoutParams.isOverflowButton = true;
        return generateDefaultLayoutParams;
    }
    
    public Menu getMenu() {
        if (this.mMenu == null) {
            final Context context = this.getContext();
            (this.mMenu = new MenuBuilder(context)).setCallback((MenuBuilder.Callback)new MenuBuilderCallback());
            (this.mPresenter = new ActionMenuPresenter(context)).setReserveOverflow(true);
            final ActionMenuPresenter mPresenter = this.mPresenter;
            MenuPresenter.Callback mActionMenuPresenterCallback;
            if (this.mActionMenuPresenterCallback != null) {
                mActionMenuPresenterCallback = this.mActionMenuPresenterCallback;
            }
            else {
                mActionMenuPresenterCallback = new ActionMenuPresenterCallback();
            }
            mPresenter.setCallback(mActionMenuPresenterCallback);
            this.mMenu.addMenuPresenter(this.mPresenter, this.mPopupContext);
            this.mPresenter.setMenuView(this);
        }
        return (Menu)this.mMenu;
    }
    
    @Nullable
    public Drawable getOverflowIcon() {
        this.getMenu();
        return this.mPresenter.getOverflowIcon();
    }
    
    public int getPopupTheme() {
        return this.mPopupTheme;
    }
    
    @Override
    public int getWindowAnimations() {
        return 0;
    }
    
    protected boolean hasSupportDividerBeforeChildAt(final int n) {
        boolean b;
        if (n == 0) {
            b = false;
        }
        else {
            final View child = this.getChildAt(n - 1);
            final View child2 = this.getChildAt(n);
            final int childCount = this.getChildCount();
            b = false;
            if (n < childCount) {
                final boolean b2 = child instanceof ActionMenuChildView;
                b = false;
                if (b2) {
                    b = (false | ((ActionMenuChildView)child).needsDividerAfter());
                }
            }
            if (n > 0 && child2 instanceof ActionMenuChildView) {
                b |= ((ActionMenuChildView)child2).needsDividerBefore();
            }
        }
        return b;
    }
    
    public boolean hideOverflowMenu() {
        return this.mPresenter != null && this.mPresenter.hideOverflowMenu();
    }
    
    @Override
    public void initialize(final MenuBuilder mMenu) {
        this.mMenu = mMenu;
    }
    
    @Override
    public boolean invokeItem(final MenuItemImpl menuItemImpl) {
        return this.mMenu.performItemAction((MenuItem)menuItemImpl, 0);
    }
    
    public boolean isOverflowMenuShowPending() {
        return this.mPresenter != null && this.mPresenter.isOverflowMenuShowPending();
    }
    
    public boolean isOverflowMenuShowing() {
        return this.mPresenter != null && this.mPresenter.isOverflowMenuShowing();
    }
    
    public boolean isOverflowReserved() {
        return this.mReserveOverflow;
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mPresenter != null) {
            this.mPresenter.updateMenuView(false);
            if (this.mPresenter.isOverflowMenuShowing()) {
                this.mPresenter.hideOverflowMenu();
                this.mPresenter.showOverflowMenu();
            }
        }
    }
    
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.dismissPopupMenus();
    }
    
    @Override
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        if (!this.mFormatItems) {
            super.onLayout(b, n, n2, n3, n4);
        }
        else {
            final int childCount = this.getChildCount();
            final int n5 = (n4 - n2) / 2;
            final int dividerWidth = this.getDividerWidth();
            int n6 = 0;
            int n7 = 0;
            int n8 = n3 - n - this.getPaddingRight() - this.getPaddingLeft();
            boolean b2 = false;
            final boolean layoutRtl = ViewUtils.isLayoutRtl((View)this);
            for (int i = 0; i < childCount; ++i) {
                final View child = this.getChildAt(i);
                if (child.getVisibility() != 8) {
                    final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
                    if (layoutParams.isOverflowButton) {
                        int measuredWidth = child.getMeasuredWidth();
                        if (this.hasSupportDividerBeforeChildAt(i)) {
                            measuredWidth += dividerWidth;
                        }
                        final int measuredHeight = child.getMeasuredHeight();
                        int n9;
                        int n10;
                        if (layoutRtl) {
                            n9 = this.getPaddingLeft() + layoutParams.leftMargin;
                            n10 = n9 + measuredWidth;
                        }
                        else {
                            n10 = this.getWidth() - this.getPaddingRight() - layoutParams.rightMargin;
                            n9 = n10 - measuredWidth;
                        }
                        final int n11 = n5 - measuredHeight / 2;
                        child.layout(n9, n11, n10, n11 + measuredHeight);
                        n8 -= measuredWidth;
                        b2 = true;
                    }
                    else {
                        final int n12 = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                        n6 += n12;
                        n8 -= n12;
                        if (this.hasSupportDividerBeforeChildAt(i)) {
                            n6 += dividerWidth;
                        }
                        ++n7;
                    }
                }
            }
            if (childCount == 1 && !b2) {
                final View child2 = this.getChildAt(0);
                final int measuredWidth2 = child2.getMeasuredWidth();
                final int measuredHeight2 = child2.getMeasuredHeight();
                final int n13 = (n3 - n) / 2 - measuredWidth2 / 2;
                final int n14 = n5 - measuredHeight2 / 2;
                child2.layout(n13, n14, n13 + measuredWidth2, n14 + measuredHeight2);
            }
            else {
                int n15;
                if (b2) {
                    n15 = 0;
                }
                else {
                    n15 = 1;
                }
                final int n16 = n7 - n15;
                int n17;
                if (n16 > 0) {
                    n17 = n8 / n16;
                }
                else {
                    n17 = 0;
                }
                final int max = Math.max(0, n17);
                if (layoutRtl) {
                    int n18 = this.getWidth() - this.getPaddingRight();
                    for (int j = 0; j < childCount; ++j) {
                        final View child3 = this.getChildAt(j);
                        final LayoutParams layoutParams2 = (LayoutParams)child3.getLayoutParams();
                        if (child3.getVisibility() != 8 && !layoutParams2.isOverflowButton) {
                            final int n19 = n18 - layoutParams2.rightMargin;
                            final int measuredWidth3 = child3.getMeasuredWidth();
                            final int measuredHeight3 = child3.getMeasuredHeight();
                            final int n20 = n5 - measuredHeight3 / 2;
                            child3.layout(n19 - measuredWidth3, n20, n19, n20 + measuredHeight3);
                            n18 = n19 - (max + (measuredWidth3 + layoutParams2.leftMargin));
                        }
                    }
                }
                else {
                    int paddingLeft = this.getPaddingLeft();
                    for (int k = 0; k < childCount; ++k) {
                        final View child4 = this.getChildAt(k);
                        final LayoutParams layoutParams3 = (LayoutParams)child4.getLayoutParams();
                        if (child4.getVisibility() != 8 && !layoutParams3.isOverflowButton) {
                            final int n21 = paddingLeft + layoutParams3.leftMargin;
                            final int measuredWidth4 = child4.getMeasuredWidth();
                            final int measuredHeight4 = child4.getMeasuredHeight();
                            final int n22 = n5 - measuredHeight4 / 2;
                            child4.layout(n21, n22, n21 + measuredWidth4, n22 + measuredHeight4);
                            paddingLeft = n21 + (max + (measuredWidth4 + layoutParams3.rightMargin));
                        }
                    }
                }
            }
        }
    }
    
    @Override
    protected void onMeasure(final int n, final int n2) {
        final boolean mFormatItems = this.mFormatItems;
        this.mFormatItems = (View$MeasureSpec.getMode(n) == 1073741824);
        if (mFormatItems != this.mFormatItems) {
            this.mFormatItemsWidth = 0;
        }
        final int size = View$MeasureSpec.getSize(n);
        if (this.mFormatItems && this.mMenu != null && size != this.mFormatItemsWidth) {
            this.mFormatItemsWidth = size;
            this.mMenu.onItemsChanged(true);
        }
        final int childCount = this.getChildCount();
        if (this.mFormatItems && childCount > 0) {
            this.onMeasureExactFormat(n, n2);
        }
        else {
            for (int i = 0; i < childCount; ++i) {
                final LayoutParams layoutParams = (LayoutParams)this.getChildAt(i).getLayoutParams();
                layoutParams.rightMargin = 0;
                layoutParams.leftMargin = 0;
            }
            super.onMeasure(n, n2);
        }
    }
    
    public MenuBuilder peekMenu() {
        return this.mMenu;
    }
    
    public void setExpandedActionViewsExclusive(final boolean expandedActionViewsExclusive) {
        this.mPresenter.setExpandedActionViewsExclusive(expandedActionViewsExclusive);
    }
    
    public void setMenuCallbacks(final MenuPresenter.Callback mActionMenuPresenterCallback, final Callback mMenuBuilderCallback) {
        this.mActionMenuPresenterCallback = mActionMenuPresenterCallback;
        this.mMenuBuilderCallback = mMenuBuilderCallback;
    }
    
    public void setOnMenuItemClickListener(final OnMenuItemClickListener mOnMenuItemClickListener) {
        this.mOnMenuItemClickListener = mOnMenuItemClickListener;
    }
    
    public void setOverflowIcon(@Nullable final Drawable overflowIcon) {
        this.getMenu();
        this.mPresenter.setOverflowIcon(overflowIcon);
    }
    
    public void setOverflowReserved(final boolean mReserveOverflow) {
        this.mReserveOverflow = mReserveOverflow;
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
    
    public void setPresenter(final ActionMenuPresenter mPresenter) {
        (this.mPresenter = mPresenter).setMenuView(this);
    }
    
    public boolean showOverflowMenu() {
        return this.mPresenter != null && this.mPresenter.showOverflowMenu();
    }
    
    public interface ActionMenuChildView
    {
        boolean needsDividerAfter();
        
        boolean needsDividerBefore();
    }
    
    private class ActionMenuPresenterCallback implements MenuPresenter.Callback
    {
        @Override
        public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
        }
        
        @Override
        public boolean onOpenSubMenu(final MenuBuilder menuBuilder) {
            return false;
        }
    }
    
    public static class LayoutParams extends LinearLayoutCompat.LayoutParams
    {
        @ViewDebug$ExportedProperty
        public int cellsUsed;
        @ViewDebug$ExportedProperty
        public boolean expandable;
        boolean expanded;
        @ViewDebug$ExportedProperty
        public int extraPixels;
        @ViewDebug$ExportedProperty
        public boolean isOverflowButton;
        @ViewDebug$ExportedProperty
        public boolean preventEdgeOffset;
        
        public LayoutParams(final int n, final int n2) {
            super(n, n2);
            this.isOverflowButton = false;
        }
        
        LayoutParams(final int n, final int n2, final boolean isOverflowButton) {
            super(n, n2);
            this.isOverflowButton = isOverflowButton;
        }
        
        public LayoutParams(final Context context, final AttributeSet set) {
            super(context, set);
        }
        
        public LayoutParams(final LayoutParams layoutParams) {
            super((ViewGroup$LayoutParams)layoutParams);
            this.isOverflowButton = layoutParams.isOverflowButton;
        }
        
        public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
        }
    }
    
    private class MenuBuilderCallback implements Callback
    {
        @Override
        public boolean onMenuItemSelected(final MenuBuilder menuBuilder, final MenuItem menuItem) {
            return ActionMenuView.this.mOnMenuItemClickListener != null && ActionMenuView.this.mOnMenuItemClickListener.onMenuItemClick(menuItem);
        }
        
        @Override
        public void onMenuModeChange(final MenuBuilder menuBuilder) {
            if (ActionMenuView.this.mMenuBuilderCallback != null) {
                ActionMenuView.this.mMenuBuilderCallback.onMenuModeChange(menuBuilder);
            }
        }
    }
    
    public interface OnMenuItemClickListener
    {
        boolean onMenuItemClick(final MenuItem p0);
    }
}
