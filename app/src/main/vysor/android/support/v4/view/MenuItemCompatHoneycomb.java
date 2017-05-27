// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.View;
import android.view.MenuItem;

class MenuItemCompatHoneycomb
{
    public static View getActionView(final MenuItem menuItem) {
        return menuItem.getActionView();
    }
    
    public static MenuItem setActionView(final MenuItem menuItem, final int actionView) {
        return menuItem.setActionView(actionView);
    }
    
    public static MenuItem setActionView(final MenuItem menuItem, final View actionView) {
        return menuItem.setActionView(actionView);
    }
    
    public static void setShowAsAction(final MenuItem menuItem, final int showAsAction) {
        menuItem.setShowAsAction(showAsAction);
    }
}
