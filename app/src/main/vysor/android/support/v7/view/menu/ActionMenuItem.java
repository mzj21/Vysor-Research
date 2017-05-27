// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.support.v4.view.MenuItemCompat;
import android.view.MenuItem$OnActionExpandListener;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.ContextMenu$ContextMenuInfo;
import android.view.View;
import android.view.ActionProvider;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.view.MenuItem$OnMenuItemClickListener;
import android.support.v4.internal.view.SupportMenuItem;

public class ActionMenuItem implements SupportMenuItem
{
    private static final int CHECKABLE = 1;
    private static final int CHECKED = 2;
    private static final int ENABLED = 16;
    private static final int EXCLUSIVE = 4;
    private static final int HIDDEN = 8;
    private static final int NO_ICON;
    private final int mCategoryOrder;
    private MenuItem$OnMenuItemClickListener mClickListener;
    private Context mContext;
    private int mFlags;
    private final int mGroup;
    private Drawable mIconDrawable;
    private int mIconResId;
    private final int mId;
    private Intent mIntent;
    private final int mOrdering;
    private char mShortcutAlphabeticChar;
    private char mShortcutNumericChar;
    private CharSequence mTitle;
    private CharSequence mTitleCondensed;
    
    public ActionMenuItem(final Context mContext, final int mGroup, final int mId, final int mCategoryOrder, final int mOrdering, final CharSequence mTitle) {
        this.mIconResId = 0;
        this.mFlags = 16;
        this.mContext = mContext;
        this.mId = mId;
        this.mGroup = mGroup;
        this.mCategoryOrder = mCategoryOrder;
        this.mOrdering = mOrdering;
        this.mTitle = mTitle;
    }
    
    @Override
    public boolean collapseActionView() {
        return false;
    }
    
    @Override
    public boolean expandActionView() {
        return false;
    }
    
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public View getActionView() {
        return null;
    }
    
    public char getAlphabeticShortcut() {
        return this.mShortcutAlphabeticChar;
    }
    
    public int getGroupId() {
        return this.mGroup;
    }
    
    public Drawable getIcon() {
        return this.mIconDrawable;
    }
    
    public Intent getIntent() {
        return this.mIntent;
    }
    
    public int getItemId() {
        return this.mId;
    }
    
    public ContextMenu$ContextMenuInfo getMenuInfo() {
        return null;
    }
    
    public char getNumericShortcut() {
        return this.mShortcutNumericChar;
    }
    
    public int getOrder() {
        return this.mOrdering;
    }
    
    public SubMenu getSubMenu() {
        return null;
    }
    
    @Override
    public android.support.v4.view.ActionProvider getSupportActionProvider() {
        return null;
    }
    
    public CharSequence getTitle() {
        return this.mTitle;
    }
    
    public CharSequence getTitleCondensed() {
        CharSequence charSequence;
        if (this.mTitleCondensed != null) {
            charSequence = this.mTitleCondensed;
        }
        else {
            charSequence = this.mTitle;
        }
        return charSequence;
    }
    
    public boolean hasSubMenu() {
        return false;
    }
    
    public boolean invoke() {
        boolean b = true;
        if (this.mClickListener == null || !this.mClickListener.onMenuItemClick((MenuItem)this)) {
            if (this.mIntent != null) {
                this.mContext.startActivity(this.mIntent);
            }
            else {
                b = false;
            }
        }
        return b;
    }
    
    @Override
    public boolean isActionViewExpanded() {
        return false;
    }
    
    public boolean isCheckable() {
        return (0x1 & this.mFlags) != 0x0;
    }
    
    public boolean isChecked() {
        return (0x2 & this.mFlags) != 0x0;
    }
    
    public boolean isEnabled() {
        return (0x10 & this.mFlags) != 0x0;
    }
    
    public boolean isVisible() {
        return (0x8 & this.mFlags) == 0x0;
    }
    
    public MenuItem setActionProvider(final ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }
    
    public SupportMenuItem setActionView(final int n) {
        throw new UnsupportedOperationException();
    }
    
    public SupportMenuItem setActionView(final View view) {
        throw new UnsupportedOperationException();
    }
    
    public MenuItem setAlphabeticShortcut(final char mShortcutAlphabeticChar) {
        this.mShortcutAlphabeticChar = mShortcutAlphabeticChar;
        return (MenuItem)this;
    }
    
    public MenuItem setCheckable(final boolean b) {
        final int n = 0xFFFFFFFE & this.mFlags;
        boolean b2;
        if (b) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        this.mFlags = ((b2 ? 1 : 0) | n);
        return (MenuItem)this;
    }
    
    public MenuItem setChecked(final boolean b) {
        final int n = 0xFFFFFFFD & this.mFlags;
        int n2;
        if (b) {
            n2 = 2;
        }
        else {
            n2 = 0;
        }
        this.mFlags = (n2 | n);
        return (MenuItem)this;
    }
    
    public MenuItem setEnabled(final boolean b) {
        final int n = 0xFFFFFFEF & this.mFlags;
        int n2;
        if (b) {
            n2 = 16;
        }
        else {
            n2 = 0;
        }
        this.mFlags = (n2 | n);
        return (MenuItem)this;
    }
    
    public ActionMenuItem setExclusiveCheckable(final boolean b) {
        final int n = 0xFFFFFFFB & this.mFlags;
        int n2;
        if (b) {
            n2 = 4;
        }
        else {
            n2 = 0;
        }
        this.mFlags = (n2 | n);
        return this;
    }
    
    public MenuItem setIcon(final int mIconResId) {
        this.mIconResId = mIconResId;
        this.mIconDrawable = ContextCompat.getDrawable(this.mContext, mIconResId);
        return (MenuItem)this;
    }
    
    public MenuItem setIcon(final Drawable mIconDrawable) {
        this.mIconDrawable = mIconDrawable;
        this.mIconResId = 0;
        return (MenuItem)this;
    }
    
    public MenuItem setIntent(final Intent mIntent) {
        this.mIntent = mIntent;
        return (MenuItem)this;
    }
    
    public MenuItem setNumericShortcut(final char mShortcutNumericChar) {
        this.mShortcutNumericChar = mShortcutNumericChar;
        return (MenuItem)this;
    }
    
    public MenuItem setOnActionExpandListener(final MenuItem$OnActionExpandListener menuItem$OnActionExpandListener) {
        throw new UnsupportedOperationException();
    }
    
    public MenuItem setOnMenuItemClickListener(final MenuItem$OnMenuItemClickListener mClickListener) {
        this.mClickListener = mClickListener;
        return (MenuItem)this;
    }
    
    public MenuItem setShortcut(final char mShortcutNumericChar, final char mShortcutAlphabeticChar) {
        this.mShortcutNumericChar = mShortcutNumericChar;
        this.mShortcutAlphabeticChar = mShortcutAlphabeticChar;
        return (MenuItem)this;
    }
    
    @Override
    public void setShowAsAction(final int n) {
    }
    
    public SupportMenuItem setShowAsActionFlags(final int showAsAction) {
        this.setShowAsAction(showAsAction);
        return this;
    }
    
    @Override
    public SupportMenuItem setSupportActionProvider(final android.support.v4.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public SupportMenuItem setSupportOnActionExpandListener(final MenuItemCompat.OnActionExpandListener onActionExpandListener) {
        return this;
    }
    
    public MenuItem setTitle(final int n) {
        this.mTitle = this.mContext.getResources().getString(n);
        return (MenuItem)this;
    }
    
    public MenuItem setTitle(final CharSequence mTitle) {
        this.mTitle = mTitle;
        return (MenuItem)this;
    }
    
    public MenuItem setTitleCondensed(final CharSequence mTitleCondensed) {
        this.mTitleCondensed = mTitleCondensed;
        return (MenuItem)this;
    }
    
    public MenuItem setVisible(final boolean b) {
        final int n = 0x8 & this.mFlags;
        int n2;
        if (b) {
            n2 = 0;
        }
        else {
            n2 = 8;
        }
        this.mFlags = (n2 | n);
        return (MenuItem)this;
    }
}
