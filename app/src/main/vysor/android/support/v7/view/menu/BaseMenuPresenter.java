// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import java.util.ArrayList;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
import android.content.Context;

public abstract class BaseMenuPresenter implements MenuPresenter
{
    private Callback mCallback;
    protected Context mContext;
    private int mId;
    protected LayoutInflater mInflater;
    private int mItemLayoutRes;
    protected MenuBuilder mMenu;
    private int mMenuLayoutRes;
    protected MenuView mMenuView;
    protected Context mSystemContext;
    protected LayoutInflater mSystemInflater;
    
    public BaseMenuPresenter(final Context mSystemContext, final int mMenuLayoutRes, final int mItemLayoutRes) {
        this.mSystemContext = mSystemContext;
        this.mSystemInflater = LayoutInflater.from(mSystemContext);
        this.mMenuLayoutRes = mMenuLayoutRes;
        this.mItemLayoutRes = mItemLayoutRes;
    }
    
    protected void addItemView(final View view, final int n) {
        final ViewGroup viewGroup = (ViewGroup)view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup)this.mMenuView).addView(view, n);
    }
    
    public abstract void bindItemView(final MenuItemImpl p0, final MenuView.ItemView p1);
    
    @Override
    public boolean collapseItemActionView(final MenuBuilder menuBuilder, final MenuItemImpl menuItemImpl) {
        return false;
    }
    
    public MenuView.ItemView createItemView(final ViewGroup viewGroup) {
        return (MenuView.ItemView)this.mSystemInflater.inflate(this.mItemLayoutRes, viewGroup, false);
    }
    
    @Override
    public boolean expandItemActionView(final MenuBuilder menuBuilder, final MenuItemImpl menuItemImpl) {
        return false;
    }
    
    protected boolean filterLeftoverView(final ViewGroup viewGroup, final int n) {
        viewGroup.removeViewAt(n);
        return true;
    }
    
    @Override
    public boolean flagActionItems() {
        return false;
    }
    
    public Callback getCallback() {
        return this.mCallback;
    }
    
    @Override
    public int getId() {
        return this.mId;
    }
    
    public View getItemView(final MenuItemImpl menuItemImpl, final View view, final ViewGroup viewGroup) {
        MenuView.ItemView itemView;
        if (view instanceof MenuView.ItemView) {
            itemView = (MenuView.ItemView)view;
        }
        else {
            itemView = this.createItemView(viewGroup);
        }
        this.bindItemView(menuItemImpl, itemView);
        return (View)itemView;
    }
    
    @Override
    public MenuView getMenuView(final ViewGroup viewGroup) {
        if (this.mMenuView == null) {
            (this.mMenuView = (MenuView)this.mSystemInflater.inflate(this.mMenuLayoutRes, viewGroup, false)).initialize(this.mMenu);
            this.updateMenuView(true);
        }
        return this.mMenuView;
    }
    
    @Override
    public void initForMenu(final Context mContext, final MenuBuilder mMenu) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.mMenu = mMenu;
    }
    
    @Override
    public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
        if (this.mCallback != null) {
            this.mCallback.onCloseMenu(menuBuilder, b);
        }
    }
    
    @Override
    public boolean onSubMenuSelected(final SubMenuBuilder subMenuBuilder) {
        return this.mCallback != null && this.mCallback.onOpenSubMenu(subMenuBuilder);
    }
    
    @Override
    public void setCallback(final Callback mCallback) {
        this.mCallback = mCallback;
    }
    
    public void setId(final int mId) {
        this.mId = mId;
    }
    
    public boolean shouldIncludeItem(final int n, final MenuItemImpl menuItemImpl) {
        return true;
    }
    
    @Override
    public void updateMenuView(final boolean b) {
        final ViewGroup viewGroup = (ViewGroup)this.mMenuView;
        if (viewGroup != null) {
            final MenuBuilder mMenu = this.mMenu;
            int i = 0;
            if (mMenu != null) {
                this.mMenu.flagActionItems();
                final ArrayList<MenuItemImpl> visibleItems = this.mMenu.getVisibleItems();
                for (int size = visibleItems.size(), j = 0; j < size; ++j) {
                    final MenuItemImpl menuItemImpl = visibleItems.get(j);
                    if (this.shouldIncludeItem(i, menuItemImpl)) {
                        final View child = viewGroup.getChildAt(i);
                        MenuItemImpl itemData;
                        if (child instanceof MenuView.ItemView) {
                            itemData = ((MenuView.ItemView)child).getItemData();
                        }
                        else {
                            itemData = null;
                        }
                        final View itemView = this.getItemView(menuItemImpl, child, viewGroup);
                        if (menuItemImpl != itemData) {
                            itemView.setPressed(false);
                            ViewCompat.jumpDrawablesToCurrentState(itemView);
                        }
                        if (itemView != child) {
                            this.addItemView(itemView, i);
                        }
                        ++i;
                    }
                }
            }
            while (i < viewGroup.getChildCount()) {
                if (!this.filterLeftoverView(viewGroup, i)) {
                    ++i;
                }
            }
        }
    }
}
