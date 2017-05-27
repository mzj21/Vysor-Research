// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.internal.view;

import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ActionProvider;
import android.view.View;
import android.view.MenuItem;

public interface SupportMenuItem extends MenuItem
{
    public static final int SHOW_AS_ACTION_ALWAYS = 2;
    public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;
    public static final int SHOW_AS_ACTION_IF_ROOM = 1;
    public static final int SHOW_AS_ACTION_NEVER = 0;
    public static final int SHOW_AS_ACTION_WITH_TEXT = 4;
    
    boolean collapseActionView();
    
    boolean expandActionView();
    
    View getActionView();
    
    ActionProvider getSupportActionProvider();
    
    boolean isActionViewExpanded();
    
    MenuItem setActionView(final int p0);
    
    MenuItem setActionView(final View p0);
    
    void setShowAsAction(final int p0);
    
    MenuItem setShowAsActionFlags(final int p0);
    
    SupportMenuItem setSupportActionProvider(final ActionProvider p0);
    
    SupportMenuItem setSupportOnActionExpandListener(final MenuItemCompat.OnActionExpandListener p0);
}
