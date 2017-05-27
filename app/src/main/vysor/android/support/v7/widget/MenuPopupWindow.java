// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View;
import android.support.v7.view.menu.ListMenuItemView;
import android.view.KeyEvent;
import android.widget.ListAdapter;
import android.support.v7.view.menu.MenuAdapter;
import android.widget.HeaderViewListAdapter;
import android.view.MotionEvent;
import android.content.res.Configuration;
import android.transition.Transition;
import android.os.Build$VERSION;
import android.view.MenuItem;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuBuilder;
import android.util.AttributeSet;
import android.content.Context;
import android.util.Log;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

public class MenuPopupWindow extends ListPopupWindow implements MenuItemHoverListener
{
    private static final String TAG = "MenuPopupWindow";
    private static Method sSetTouchModalMethod;
    private MenuItemHoverListener mHoverListener;
    
    static {
        try {
            MenuPopupWindow.sSetTouchModalMethod = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
        }
        catch (NoSuchMethodException ex) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }
    
    public MenuPopupWindow(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
    }
    
    @Override
    DropDownListView createDropDownListView(final Context context, final boolean b) {
        final MenuDropDownListView menuDropDownListView = new MenuDropDownListView(context, b);
        menuDropDownListView.setHoverListener(this);
        return menuDropDownListView;
    }
    
    @Override
    public void onItemHoverEnter(@NonNull final MenuBuilder menuBuilder, @NonNull final MenuItem menuItem) {
        if (this.mHoverListener != null) {
            this.mHoverListener.onItemHoverEnter(menuBuilder, menuItem);
        }
    }
    
    @Override
    public void onItemHoverExit(@NonNull final MenuBuilder menuBuilder, @NonNull final MenuItem menuItem) {
        if (this.mHoverListener != null) {
            this.mHoverListener.onItemHoverExit(menuBuilder, menuItem);
        }
    }
    
    public void setEnterTransition(final Object o) {
        if (Build$VERSION.SDK_INT >= 23) {
            this.mPopup.setEnterTransition((Transition)o);
        }
    }
    
    public void setExitTransition(final Object o) {
        if (Build$VERSION.SDK_INT >= 23) {
            this.mPopup.setExitTransition((Transition)o);
        }
    }
    
    public void setHoverListener(final MenuItemHoverListener mHoverListener) {
        this.mHoverListener = mHoverListener;
    }
    
    public void setTouchModal(final boolean b) {
        if (MenuPopupWindow.sSetTouchModalMethod == null) {
            return;
        }
        try {
            MenuPopupWindow.sSetTouchModalMethod.invoke(this.mPopup, b);
        }
        catch (Exception ex) {
            Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
        }
    }
    
    public static class MenuDropDownListView extends DropDownListView
    {
        final int mAdvanceKey;
        private MenuItemHoverListener mHoverListener;
        private MenuItem mHoveredMenuItem;
        final int mRetreatKey;
        
        public MenuDropDownListView(final Context context, final boolean b) {
            super(context, b);
            final Configuration configuration = context.getResources().getConfiguration();
            if (Build$VERSION.SDK_INT >= 17 && 1 == configuration.getLayoutDirection()) {
                this.mAdvanceKey = 21;
                this.mRetreatKey = 22;
            }
            else {
                this.mAdvanceKey = 22;
                this.mRetreatKey = 21;
            }
        }
        
        public void clearSelection() {
            this.setSelection(-1);
        }
        
        public boolean onHoverEvent(final MotionEvent motionEvent) {
            if (this.mHoverListener != null) {
                final ListAdapter adapter = this.getAdapter();
                int headersCount;
                MenuAdapter menuAdapter;
                if (adapter instanceof HeaderViewListAdapter) {
                    final HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter)adapter;
                    headersCount = headerViewListAdapter.getHeadersCount();
                    menuAdapter = (MenuAdapter)headerViewListAdapter.getWrappedAdapter();
                }
                else {
                    menuAdapter = (MenuAdapter)adapter;
                    headersCount = 0;
                }
                final int action = motionEvent.getAction();
                Object item = null;
                if (action != 10) {
                    final int pointToPosition = this.pointToPosition((int)motionEvent.getX(), (int)motionEvent.getY());
                    item = null;
                    if (pointToPosition != -1) {
                        final int n = pointToPosition - headersCount;
                        item = null;
                        if (n >= 0) {
                            final int count = menuAdapter.getCount();
                            item = null;
                            if (n < count) {
                                item = menuAdapter.getItem(n);
                            }
                        }
                    }
                }
                final MenuItem mHoveredMenuItem = this.mHoveredMenuItem;
                if (mHoveredMenuItem != item) {
                    final MenuBuilder adapterMenu = menuAdapter.getAdapterMenu();
                    if (mHoveredMenuItem != null) {
                        this.mHoverListener.onItemHoverExit(adapterMenu, mHoveredMenuItem);
                    }
                    if ((this.mHoveredMenuItem = (MenuItem)item) != null) {
                        this.mHoverListener.onItemHoverEnter(adapterMenu, (MenuItem)item);
                    }
                }
            }
            return super.onHoverEvent(motionEvent);
        }
        
        public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
            final ListMenuItemView listMenuItemView = (ListMenuItemView)this.getSelectedView();
            boolean onKeyDown;
            if (listMenuItemView != null && n == this.mAdvanceKey) {
                if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                    this.performItemClick((View)listMenuItemView, this.getSelectedItemPosition(), this.getSelectedItemId());
                }
                onKeyDown = true;
            }
            else if (listMenuItemView != null && n == this.mRetreatKey) {
                this.setSelection(-1);
                ((MenuAdapter)this.getAdapter()).getAdapterMenu().close(false);
                onKeyDown = true;
            }
            else {
                onKeyDown = super.onKeyDown(n, keyEvent);
            }
            return onKeyDown;
        }
        
        public void setHoverListener(final MenuItemHoverListener mHoverListener) {
            this.mHoverListener = mHoverListener;
        }
    }
}
