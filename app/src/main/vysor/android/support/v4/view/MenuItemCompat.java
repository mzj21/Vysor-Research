// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.View;
import android.util.Log;
import android.support.v4.internal.view.SupportMenuItem;
import android.view.MenuItem;
import android.os.Build$VERSION;

public final class MenuItemCompat
{
    static final MenuVersionImpl IMPL;
    public static final int SHOW_AS_ACTION_ALWAYS = 2;
    public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;
    public static final int SHOW_AS_ACTION_IF_ROOM = 1;
    public static final int SHOW_AS_ACTION_NEVER = 0;
    public static final int SHOW_AS_ACTION_WITH_TEXT = 4;
    private static final String TAG = "MenuItemCompat";
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 14) {
            IMPL = (MenuVersionImpl)new IcsMenuVersionImpl();
        }
        else if (sdk_INT >= 11) {
            IMPL = (MenuVersionImpl)new HoneycombMenuVersionImpl();
        }
        else {
            IMPL = (MenuVersionImpl)new BaseMenuVersionImpl();
        }
    }
    
    public static boolean collapseActionView(final MenuItem menuItem) {
        boolean b;
        if (menuItem instanceof SupportMenuItem) {
            b = ((SupportMenuItem)menuItem).collapseActionView();
        }
        else {
            b = MenuItemCompat.IMPL.collapseActionView(menuItem);
        }
        return b;
    }
    
    public static boolean expandActionView(final MenuItem menuItem) {
        boolean b;
        if (menuItem instanceof SupportMenuItem) {
            b = ((SupportMenuItem)menuItem).expandActionView();
        }
        else {
            b = MenuItemCompat.IMPL.expandActionView(menuItem);
        }
        return b;
    }
    
    public static ActionProvider getActionProvider(final MenuItem menuItem) {
        ActionProvider supportActionProvider;
        if (menuItem instanceof SupportMenuItem) {
            supportActionProvider = ((SupportMenuItem)menuItem).getSupportActionProvider();
        }
        else {
            Log.w("MenuItemCompat", "getActionProvider: item does not implement SupportMenuItem; returning null");
            supportActionProvider = null;
        }
        return supportActionProvider;
    }
    
    public static View getActionView(final MenuItem menuItem) {
        View view;
        if (menuItem instanceof SupportMenuItem) {
            view = ((SupportMenuItem)menuItem).getActionView();
        }
        else {
            view = MenuItemCompat.IMPL.getActionView(menuItem);
        }
        return view;
    }
    
    public static boolean isActionViewExpanded(final MenuItem menuItem) {
        boolean b;
        if (menuItem instanceof SupportMenuItem) {
            b = ((SupportMenuItem)menuItem).isActionViewExpanded();
        }
        else {
            b = MenuItemCompat.IMPL.isActionViewExpanded(menuItem);
        }
        return b;
    }
    
    public static MenuItem setActionProvider(MenuItem setSupportActionProvider, final ActionProvider supportActionProvider) {
        if (setSupportActionProvider instanceof SupportMenuItem) {
            setSupportActionProvider = (MenuItem)((SupportMenuItem)setSupportActionProvider).setSupportActionProvider(supportActionProvider);
        }
        else {
            Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        }
        return setSupportActionProvider;
    }
    
    public static MenuItem setActionView(final MenuItem menuItem, final int actionView) {
        MenuItem menuItem2;
        if (menuItem instanceof SupportMenuItem) {
            menuItem2 = ((SupportMenuItem)menuItem).setActionView(actionView);
        }
        else {
            menuItem2 = MenuItemCompat.IMPL.setActionView(menuItem, actionView);
        }
        return menuItem2;
    }
    
    public static MenuItem setActionView(final MenuItem menuItem, final View actionView) {
        MenuItem menuItem2;
        if (menuItem instanceof SupportMenuItem) {
            menuItem2 = ((SupportMenuItem)menuItem).setActionView(actionView);
        }
        else {
            menuItem2 = MenuItemCompat.IMPL.setActionView(menuItem, actionView);
        }
        return menuItem2;
    }
    
    public static MenuItem setOnActionExpandListener(final MenuItem menuItem, final OnActionExpandListener supportOnActionExpandListener) {
        Object o;
        if (menuItem instanceof SupportMenuItem) {
            o = ((SupportMenuItem)menuItem).setSupportOnActionExpandListener(supportOnActionExpandListener);
        }
        else {
            o = MenuItemCompat.IMPL.setOnActionExpandListener(menuItem, supportOnActionExpandListener);
        }
        return (MenuItem)o;
    }
    
    public static void setShowAsAction(final MenuItem menuItem, final int showAsAction) {
        if (menuItem instanceof SupportMenuItem) {
            ((SupportMenuItem)menuItem).setShowAsAction(showAsAction);
        }
        else {
            MenuItemCompat.IMPL.setShowAsAction(menuItem, showAsAction);
        }
    }
    
    static class BaseMenuVersionImpl implements MenuVersionImpl
    {
        @Override
        public boolean collapseActionView(final MenuItem menuItem) {
            return false;
        }
        
        @Override
        public boolean expandActionView(final MenuItem menuItem) {
            return false;
        }
        
        @Override
        public View getActionView(final MenuItem menuItem) {
            return null;
        }
        
        @Override
        public boolean isActionViewExpanded(final MenuItem menuItem) {
            return false;
        }
        
        @Override
        public MenuItem setActionView(final MenuItem menuItem, final int n) {
            return menuItem;
        }
        
        @Override
        public MenuItem setActionView(final MenuItem menuItem, final View view) {
            return menuItem;
        }
        
        @Override
        public MenuItem setOnActionExpandListener(final MenuItem menuItem, final OnActionExpandListener onActionExpandListener) {
            return menuItem;
        }
        
        @Override
        public void setShowAsAction(final MenuItem menuItem, final int n) {
        }
    }
    
    static class HoneycombMenuVersionImpl implements MenuVersionImpl
    {
        @Override
        public boolean collapseActionView(final MenuItem menuItem) {
            return false;
        }
        
        @Override
        public boolean expandActionView(final MenuItem menuItem) {
            return false;
        }
        
        @Override
        public View getActionView(final MenuItem menuItem) {
            return MenuItemCompatHoneycomb.getActionView(menuItem);
        }
        
        @Override
        public boolean isActionViewExpanded(final MenuItem menuItem) {
            return false;
        }
        
        @Override
        public MenuItem setActionView(final MenuItem menuItem, final int n) {
            return MenuItemCompatHoneycomb.setActionView(menuItem, n);
        }
        
        @Override
        public MenuItem setActionView(final MenuItem menuItem, final View view) {
            return MenuItemCompatHoneycomb.setActionView(menuItem, view);
        }
        
        @Override
        public MenuItem setOnActionExpandListener(final MenuItem menuItem, final OnActionExpandListener onActionExpandListener) {
            return menuItem;
        }
        
        @Override
        public void setShowAsAction(final MenuItem menuItem, final int n) {
            MenuItemCompatHoneycomb.setShowAsAction(menuItem, n);
        }
    }
    
    static class IcsMenuVersionImpl extends HoneycombMenuVersionImpl
    {
        @Override
        public boolean collapseActionView(final MenuItem menuItem) {
            return MenuItemCompatIcs.collapseActionView(menuItem);
        }
        
        @Override
        public boolean expandActionView(final MenuItem menuItem) {
            return MenuItemCompatIcs.expandActionView(menuItem);
        }
        
        @Override
        public boolean isActionViewExpanded(final MenuItem menuItem) {
            return MenuItemCompatIcs.isActionViewExpanded(menuItem);
        }
        
        @Override
        public MenuItem setOnActionExpandListener(final MenuItem menuItem, final OnActionExpandListener onActionExpandListener) {
            MenuItem menuItem2;
            if (onActionExpandListener == null) {
                menuItem2 = MenuItemCompatIcs.setOnActionExpandListener(menuItem, null);
            }
            else {
                menuItem2 = MenuItemCompatIcs.setOnActionExpandListener(menuItem, (MenuItemCompatIcs.SupportActionExpandProxy)new MenuItemCompatIcs.SupportActionExpandProxy() {
                    @Override
                    public boolean onMenuItemActionCollapse(final MenuItem menuItem) {
                        return onActionExpandListener.onMenuItemActionCollapse(menuItem);
                    }
                    
                    @Override
                    public boolean onMenuItemActionExpand(final MenuItem menuItem) {
                        return onActionExpandListener.onMenuItemActionExpand(menuItem);
                    }
                });
            }
            return menuItem2;
        }
    }
    
    interface MenuVersionImpl
    {
        boolean collapseActionView(final MenuItem p0);
        
        boolean expandActionView(final MenuItem p0);
        
        View getActionView(final MenuItem p0);
        
        boolean isActionViewExpanded(final MenuItem p0);
        
        MenuItem setActionView(final MenuItem p0, final int p1);
        
        MenuItem setActionView(final MenuItem p0, final View p1);
        
        MenuItem setOnActionExpandListener(final MenuItem p0, final OnActionExpandListener p1);
        
        void setShowAsAction(final MenuItem p0, final int p1);
    }
    
    public interface OnActionExpandListener
    {
        boolean onMenuItemActionCollapse(final MenuItem p0);
        
        boolean onMenuItemActionExpand(final MenuItem p0);
    }
}
