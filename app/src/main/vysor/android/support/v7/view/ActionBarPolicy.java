// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view;

import android.support.v4.view.ViewConfigurationCompat;
import android.view.ViewConfiguration;
import android.os.Build$VERSION;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.support.v7.appcompat.R;
import android.content.res.Resources;
import android.support.v4.content.res.ConfigurationHelper;
import android.content.Context;

public class ActionBarPolicy
{
    private Context mContext;
    
    private ActionBarPolicy(final Context mContext) {
        this.mContext = mContext;
    }
    
    public static ActionBarPolicy get(final Context context) {
        return new ActionBarPolicy(context);
    }
    
    public boolean enableHomeButtonByDefault() {
        return this.mContext.getApplicationInfo().targetSdkVersion < 14;
    }
    
    public int getEmbeddedMenuWidthLimit() {
        return this.mContext.getResources().getDisplayMetrics().widthPixels / 2;
    }
    
    public int getMaxActionButtons() {
        final Resources resources = this.mContext.getResources();
        final int screenWidthDp = ConfigurationHelper.getScreenWidthDp(resources);
        final int screenHeightDp = ConfigurationHelper.getScreenHeightDp(resources);
        int n;
        if (ConfigurationHelper.getSmallestScreenWidthDp(resources) > 600 || screenWidthDp > 600 || (screenWidthDp > 960 && screenHeightDp > 720) || (screenWidthDp > 720 && screenHeightDp > 960)) {
            n = 5;
        }
        else if (screenWidthDp >= 500 || (screenWidthDp > 640 && screenHeightDp > 480) || (screenWidthDp > 480 && screenHeightDp > 640)) {
            n = 4;
        }
        else if (screenWidthDp >= 360) {
            n = 3;
        }
        else {
            n = 2;
        }
        return n;
    }
    
    public int getStackedTabMaxWidth() {
        return this.mContext.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_stacked_tab_max_width);
    }
    
    public int getTabContainerHeight() {
        final TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes((AttributeSet)null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        int n = obtainStyledAttributes.getLayoutDimension(R.styleable.ActionBar_height, 0);
        final Resources resources = this.mContext.getResources();
        if (!this.hasEmbeddedTabs()) {
            n = Math.min(n, resources.getDimensionPixelSize(R.dimen.abc_action_bar_stacked_max_height));
        }
        obtainStyledAttributes.recycle();
        return n;
    }
    
    public boolean hasEmbeddedTabs() {
        return this.mContext.getResources().getBoolean(R.bool.abc_action_bar_embed_tabs);
    }
    
    public boolean showsOverflowMenuButton() {
        boolean b = true;
        if (Build$VERSION.SDK_INT < 19 && ViewConfigurationCompat.hasPermanentMenuKey(ViewConfiguration.get(this.mContext))) {
            b = false;
        }
        return b;
    }
}
