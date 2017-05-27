// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.MenuItem$OnActionExpandListener;
import android.view.MenuItem;

class MenuItemCompatIcs
{
    public static boolean collapseActionView(final MenuItem menuItem) {
        return menuItem.collapseActionView();
    }
    
    public static boolean expandActionView(final MenuItem menuItem) {
        return menuItem.expandActionView();
    }
    
    public static boolean isActionViewExpanded(final MenuItem menuItem) {
        return menuItem.isActionViewExpanded();
    }
    
    public static MenuItem setOnActionExpandListener(final MenuItem menuItem, final SupportActionExpandProxy supportActionExpandProxy) {
        return menuItem.setOnActionExpandListener((MenuItem$OnActionExpandListener)new OnActionExpandListenerWrapper(supportActionExpandProxy));
    }
    
    static class OnActionExpandListenerWrapper implements MenuItem$OnActionExpandListener
    {
        private SupportActionExpandProxy mWrapped;
        
        public OnActionExpandListenerWrapper(final SupportActionExpandProxy mWrapped) {
            this.mWrapped = mWrapped;
        }
        
        public boolean onMenuItemActionCollapse(final MenuItem menuItem) {
            return this.mWrapped.onMenuItemActionCollapse(menuItem);
        }
        
        public boolean onMenuItemActionExpand(final MenuItem menuItem) {
            return this.mWrapped.onMenuItemActionExpand(menuItem);
        }
    }
    
    interface SupportActionExpandProxy
    {
        boolean onMenuItemActionCollapse(final MenuItem p0);
        
        boolean onMenuItemActionExpand(final MenuItem p0);
    }
}
