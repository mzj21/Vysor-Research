// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.widget.PopupWindow$OnDismissListener;
import android.widget.AdapterView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.widget.HeaderViewListAdapter;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.view.View$MeasureSpec;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.graphics.Rect;
import android.widget.AdapterView$OnItemClickListener;

abstract class MenuPopup implements MenuPresenter, ShowableListMenu, AdapterView$OnItemClickListener
{
    private Rect mEpicenterBounds;
    
    protected static int measureIndividualMenuWidth(final ListAdapter listAdapter, ViewGroup viewGroup, final Context context, int n) {
        int n2 = 0;
        View view = null;
        int n3 = 0;
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(0, 0);
        final int measureSpec2 = View$MeasureSpec.makeMeasureSpec(0, 0);
        for (int count = listAdapter.getCount(), i = 0; i < count; ++i) {
            final int itemViewType = listAdapter.getItemViewType(i);
            if (itemViewType != n3) {
                n3 = itemViewType;
                view = null;
            }
            if (viewGroup == null) {
                viewGroup = (ViewGroup)new FrameLayout(context);
            }
            view = listAdapter.getView(i, view, viewGroup);
            view.measure(measureSpec, measureSpec2);
            final int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= n) {
                return n;
            }
            if (measuredWidth > n2) {
                n2 = measuredWidth;
            }
        }
        n = n2;
        return n;
    }
    
    protected static boolean shouldPreserveIconSpacing(final MenuBuilder menuBuilder) {
        final int size = menuBuilder.size();
        int n = 0;
        boolean b;
        while (true) {
            b = false;
            if (n >= size) {
                break;
            }
            final MenuItem item = menuBuilder.getItem(n);
            if (item.isVisible() && item.getIcon() != null) {
                b = true;
                break;
            }
            ++n;
        }
        return b;
    }
    
    protected static MenuAdapter toMenuAdapter(final ListAdapter listAdapter) {
        MenuAdapter menuAdapter;
        if (listAdapter instanceof HeaderViewListAdapter) {
            menuAdapter = (MenuAdapter)((HeaderViewListAdapter)listAdapter).getWrappedAdapter();
        }
        else {
            menuAdapter = (MenuAdapter)listAdapter;
        }
        return menuAdapter;
    }
    
    public abstract void addMenu(final MenuBuilder p0);
    
    protected boolean closeMenuOnSubMenuOpened() {
        return true;
    }
    
    @Override
    public boolean collapseItemActionView(final MenuBuilder menuBuilder, final MenuItemImpl menuItemImpl) {
        return false;
    }
    
    @Override
    public boolean expandItemActionView(final MenuBuilder menuBuilder, final MenuItemImpl menuItemImpl) {
        return false;
    }
    
    public Rect getEpicenterBounds() {
        return this.mEpicenterBounds;
    }
    
    @Override
    public int getId() {
        return 0;
    }
    
    @Override
    public MenuView getMenuView(final ViewGroup viewGroup) {
        throw new UnsupportedOperationException("MenuPopups manage their own views");
    }
    
    @Override
    public void initForMenu(@NonNull final Context context, @Nullable final MenuBuilder menuBuilder) {
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        final ListAdapter listAdapter = (ListAdapter)adapterView.getAdapter();
        final MenuBuilder mAdapterMenu = toMenuAdapter(listAdapter).mAdapterMenu;
        final MenuItem menuItem = (MenuItem)listAdapter.getItem(n);
        int n3;
        if (this.closeMenuOnSubMenuOpened()) {
            n3 = 0;
        }
        else {
            n3 = 4;
        }
        mAdapterMenu.performItemAction(menuItem, this, n3);
    }
    
    public abstract void setAnchorView(final View p0);
    
    public void setEpicenterBounds(final Rect mEpicenterBounds) {
        this.mEpicenterBounds = mEpicenterBounds;
    }
    
    public abstract void setForceShowIcon(final boolean p0);
    
    public abstract void setGravity(final int p0);
    
    public abstract void setHorizontalOffset(final int p0);
    
    public abstract void setOnDismissListener(final PopupWindow$OnDismissListener p0);
    
    public abstract void setShowTitle(final boolean p0);
    
    public abstract void setVerticalOffset(final int p0);
}
