// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.view.MenuItem$OnActionExpandListener;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.content.ActivityNotFoundException;
import android.util.Log;
import android.os.Build$VERSION;
import android.view.SubMenu;
import android.view.ViewDebug$CapturedViewProperty;
import android.support.v7.content.res.AppCompatResources;
import android.view.MenuItem;
import android.support.v4.view.MenuItemCompat;
import android.view.ContextMenu$ContextMenuInfo;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.View;
import android.support.v4.view.ActionProvider;
import android.support.v4.internal.view.SupportMenuItem;

public final class MenuItemImpl implements SupportMenuItem
{
    private static final int CHECKABLE = 1;
    private static final int CHECKED = 2;
    private static final int ENABLED = 16;
    private static final int EXCLUSIVE = 4;
    private static final int HIDDEN = 8;
    private static final int IS_ACTION = 32;
    static final int NO_ICON = 0;
    private static final int SHOW_AS_ACTION_MASK = 3;
    private static final String TAG = "MenuItemImpl";
    private static String sDeleteShortcutLabel;
    private static String sEnterShortcutLabel;
    private static String sPrependShortcutLabel;
    private static String sSpaceShortcutLabel;
    private ActionProvider mActionProvider;
    private View mActionView;
    private final int mCategoryOrder;
    private MenuItem$OnMenuItemClickListener mClickListener;
    private int mFlags;
    private final int mGroup;
    private Drawable mIconDrawable;
    private int mIconResId;
    private final int mId;
    private Intent mIntent;
    private boolean mIsActionViewExpanded;
    private Runnable mItemCallback;
    MenuBuilder mMenu;
    private ContextMenu$ContextMenuInfo mMenuInfo;
    private MenuItemCompat.OnActionExpandListener mOnActionExpandListener;
    private final int mOrdering;
    private char mShortcutAlphabeticChar;
    private char mShortcutNumericChar;
    private int mShowAsAction;
    private SubMenuBuilder mSubMenu;
    private CharSequence mTitle;
    private CharSequence mTitleCondensed;
    
    MenuItemImpl(final MenuBuilder mMenu, final int mGroup, final int mId, final int mCategoryOrder, final int mOrdering, final CharSequence mTitle, final int mShowAsAction) {
        this.mIconResId = 0;
        this.mFlags = 16;
        this.mShowAsAction = 0;
        this.mIsActionViewExpanded = false;
        this.mMenu = mMenu;
        this.mId = mId;
        this.mGroup = mGroup;
        this.mCategoryOrder = mCategoryOrder;
        this.mOrdering = mOrdering;
        this.mTitle = mTitle;
        this.mShowAsAction = mShowAsAction;
    }
    
    public void actionFormatChanged() {
        this.mMenu.onItemActionRequestChanged(this);
    }
    
    @Override
    public boolean collapseActionView() {
        final int n = 0x8 & this.mShowAsAction;
        boolean collapseItemActionView = false;
        if (n != 0) {
            if (this.mActionView == null) {
                collapseItemActionView = true;
            }
            else {
                if (this.mOnActionExpandListener != null) {
                    final boolean onMenuItemActionCollapse = this.mOnActionExpandListener.onMenuItemActionCollapse((MenuItem)this);
                    collapseItemActionView = false;
                    if (!onMenuItemActionCollapse) {
                        return collapseItemActionView;
                    }
                }
                collapseItemActionView = this.mMenu.collapseItemActionView(this);
            }
        }
        return collapseItemActionView;
    }
    
    @Override
    public boolean expandActionView() {
        final boolean hasCollapsibleActionView = this.hasCollapsibleActionView();
        boolean expandItemActionView = false;
        if (hasCollapsibleActionView) {
            if (this.mOnActionExpandListener != null) {
                final boolean onMenuItemActionExpand = this.mOnActionExpandListener.onMenuItemActionExpand((MenuItem)this);
                expandItemActionView = false;
                if (!onMenuItemActionExpand) {
                    return expandItemActionView;
                }
            }
            expandItemActionView = this.mMenu.expandItemActionView(this);
        }
        return expandItemActionView;
    }
    
    public android.view.ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }
    
    @Override
    public View getActionView() {
        View view;
        if (this.mActionView != null) {
            view = this.mActionView;
        }
        else if (this.mActionProvider != null) {
            this.mActionView = this.mActionProvider.onCreateActionView((MenuItem)this);
            view = this.mActionView;
        }
        else {
            view = null;
        }
        return view;
    }
    
    public char getAlphabeticShortcut() {
        return this.mShortcutAlphabeticChar;
    }
    
    Runnable getCallback() {
        return this.mItemCallback;
    }
    
    public int getGroupId() {
        return this.mGroup;
    }
    
    public Drawable getIcon() {
        Drawable mIconDrawable;
        if (this.mIconDrawable != null) {
            mIconDrawable = this.mIconDrawable;
        }
        else if (this.mIconResId != 0) {
            mIconDrawable = AppCompatResources.getDrawable(this.mMenu.getContext(), this.mIconResId);
            this.mIconResId = 0;
            this.mIconDrawable = mIconDrawable;
        }
        else {
            mIconDrawable = null;
        }
        return mIconDrawable;
    }
    
    public Intent getIntent() {
        return this.mIntent;
    }
    
    @ViewDebug$CapturedViewProperty
    public int getItemId() {
        return this.mId;
    }
    
    public ContextMenu$ContextMenuInfo getMenuInfo() {
        return this.mMenuInfo;
    }
    
    public char getNumericShortcut() {
        return this.mShortcutNumericChar;
    }
    
    public int getOrder() {
        return this.mCategoryOrder;
    }
    
    public int getOrdering() {
        return this.mOrdering;
    }
    
    char getShortcut() {
        char c;
        if (this.mMenu.isQwertyMode()) {
            c = this.mShortcutAlphabeticChar;
        }
        else {
            c = this.mShortcutNumericChar;
        }
        return c;
    }
    
    String getShortcutLabel() {
        final char shortcut = this.getShortcut();
        String string;
        if (shortcut == '\0') {
            string = "";
        }
        else {
            final StringBuilder sb = new StringBuilder(MenuItemImpl.sPrependShortcutLabel);
            switch (shortcut) {
                default: {
                    sb.append(shortcut);
                    break;
                }
                case 10: {
                    sb.append(MenuItemImpl.sEnterShortcutLabel);
                    break;
                }
                case 8: {
                    sb.append(MenuItemImpl.sDeleteShortcutLabel);
                    break;
                }
                case 32: {
                    sb.append(MenuItemImpl.sSpaceShortcutLabel);
                    break;
                }
            }
            string = sb.toString();
        }
        return string;
    }
    
    public SubMenu getSubMenu() {
        return (SubMenu)this.mSubMenu;
    }
    
    @Override
    public ActionProvider getSupportActionProvider() {
        return this.mActionProvider;
    }
    
    @ViewDebug$CapturedViewProperty
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
        if (Build$VERSION.SDK_INT < 18 && charSequence != null && !(charSequence instanceof String)) {
            charSequence = charSequence.toString();
        }
        return charSequence;
    }
    
    CharSequence getTitleForItemView(final MenuView.ItemView itemView) {
        CharSequence charSequence;
        if (itemView != null && itemView.prefersCondensedTitle()) {
            charSequence = this.getTitleCondensed();
        }
        else {
            charSequence = this.getTitle();
        }
        return charSequence;
    }
    
    public boolean hasCollapsibleActionView() {
        final int n = 0x8 & this.mShowAsAction;
        boolean b = false;
        if (n != 0) {
            if (this.mActionView == null && this.mActionProvider != null) {
                this.mActionView = this.mActionProvider.onCreateActionView((MenuItem)this);
            }
            final View mActionView = this.mActionView;
            b = false;
            if (mActionView != null) {
                b = true;
            }
        }
        return b;
    }
    
    public boolean hasSubMenu() {
        return this.mSubMenu != null;
    }
    
    public boolean invoke() {
        boolean b = true;
        if ((this.mClickListener == null || !this.mClickListener.onMenuItemClick((MenuItem)this)) && !this.mMenu.dispatchMenuItemSelected(this.mMenu.getRootMenu(), (MenuItem)this)) {
            if (this.mItemCallback != null) {
                this.mItemCallback.run();
            }
            else {
                if (this.mIntent != null) {
                    try {
                        this.mMenu.getContext().startActivity(this.mIntent);
                        return b;
                    }
                    catch (ActivityNotFoundException ex) {
                        Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", (Throwable)ex);
                    }
                }
                if (this.mActionProvider == null || !this.mActionProvider.onPerformDefaultAction()) {
                    b = false;
                }
            }
        }
        return b;
    }
    
    public boolean isActionButton() {
        return (0x20 & this.mFlags) == 0x20;
    }
    
    @Override
    public boolean isActionViewExpanded() {
        return this.mIsActionViewExpanded;
    }
    
    public boolean isCheckable() {
        boolean b = true;
        if ((0x1 & this.mFlags) != (b ? 1 : 0)) {
            b = false;
        }
        return b;
    }
    
    public boolean isChecked() {
        return (0x2 & this.mFlags) == 0x2;
    }
    
    public boolean isEnabled() {
        return (0x10 & this.mFlags) != 0x0;
    }
    
    public boolean isExclusiveCheckable() {
        return (0x4 & this.mFlags) != 0x0;
    }
    
    public boolean isVisible() {
        boolean b = true;
        if (this.mActionProvider != null && this.mActionProvider.overridesItemVisibility()) {
            if ((0x8 & this.mFlags) != 0x0 || !this.mActionProvider.isVisible()) {
                b = false;
            }
        }
        else if ((0x8 & this.mFlags) != 0x0) {
            b = false;
        }
        return b;
    }
    
    public boolean requestsActionButton() {
        boolean b = true;
        if ((0x1 & this.mShowAsAction) != (b ? 1 : 0)) {
            b = false;
        }
        return b;
    }
    
    public boolean requiresActionButton() {
        return (0x2 & this.mShowAsAction) == 0x2;
    }
    
    public MenuItem setActionProvider(final android.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }
    
    public SupportMenuItem setActionView(final int n) {
        final Context context = this.mMenu.getContext();
        this.setActionView(LayoutInflater.from(context).inflate(n, (ViewGroup)new LinearLayout(context), false));
        return this;
    }
    
    public SupportMenuItem setActionView(final View mActionView) {
        this.mActionView = mActionView;
        this.mActionProvider = null;
        if (mActionView != null && mActionView.getId() == -1 && this.mId > 0) {
            mActionView.setId(this.mId);
        }
        this.mMenu.onItemActionRequestChanged(this);
        return this;
    }
    
    public void setActionViewExpanded(final boolean mIsActionViewExpanded) {
        this.mIsActionViewExpanded = mIsActionViewExpanded;
        this.mMenu.onItemsChanged(false);
    }
    
    public MenuItem setAlphabeticShortcut(final char c) {
        if (this.mShortcutAlphabeticChar != c) {
            this.mShortcutAlphabeticChar = Character.toLowerCase(c);
            this.mMenu.onItemsChanged(false);
        }
        return (MenuItem)this;
    }
    
    public MenuItem setCallback(final Runnable mItemCallback) {
        this.mItemCallback = mItemCallback;
        return (MenuItem)this;
    }
    
    public MenuItem setCheckable(final boolean b) {
        final int mFlags = this.mFlags;
        final int n = 0xFFFFFFFE & this.mFlags;
        boolean b2;
        if (b) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        this.mFlags = ((b2 ? 1 : 0) | n);
        if (mFlags != this.mFlags) {
            this.mMenu.onItemsChanged(false);
        }
        return (MenuItem)this;
    }
    
    public MenuItem setChecked(final boolean checkedInt) {
        if ((0x4 & this.mFlags) != 0x0) {
            this.mMenu.setExclusiveItemChecked((MenuItem)this);
        }
        else {
            this.setCheckedInt(checkedInt);
        }
        return (MenuItem)this;
    }
    
    void setCheckedInt(final boolean b) {
        final int mFlags = this.mFlags;
        final int n = 0xFFFFFFFD & this.mFlags;
        int n2;
        if (b) {
            n2 = 2;
        }
        else {
            n2 = 0;
        }
        this.mFlags = (n2 | n);
        if (mFlags != this.mFlags) {
            this.mMenu.onItemsChanged(false);
        }
    }
    
    public MenuItem setEnabled(final boolean b) {
        if (b) {
            this.mFlags |= 0x10;
        }
        else {
            this.mFlags &= 0xFFFFFFEF;
        }
        this.mMenu.onItemsChanged(false);
        return (MenuItem)this;
    }
    
    public void setExclusiveCheckable(final boolean b) {
        final int n = 0xFFFFFFFB & this.mFlags;
        int n2;
        if (b) {
            n2 = 4;
        }
        else {
            n2 = 0;
        }
        this.mFlags = (n2 | n);
    }
    
    public MenuItem setIcon(final int mIconResId) {
        this.mIconDrawable = null;
        this.mIconResId = mIconResId;
        this.mMenu.onItemsChanged(false);
        return (MenuItem)this;
    }
    
    public MenuItem setIcon(final Drawable mIconDrawable) {
        this.mIconResId = 0;
        this.mIconDrawable = mIconDrawable;
        this.mMenu.onItemsChanged(false);
        return (MenuItem)this;
    }
    
    public MenuItem setIntent(final Intent mIntent) {
        this.mIntent = mIntent;
        return (MenuItem)this;
    }
    
    public void setIsActionButton(final boolean b) {
        if (b) {
            this.mFlags |= 0x20;
        }
        else {
            this.mFlags &= 0xFFFFFFDF;
        }
    }
    
    void setMenuInfo(final ContextMenu$ContextMenuInfo mMenuInfo) {
        this.mMenuInfo = mMenuInfo;
    }
    
    public MenuItem setNumericShortcut(final char mShortcutNumericChar) {
        if (this.mShortcutNumericChar != mShortcutNumericChar) {
            this.mShortcutNumericChar = mShortcutNumericChar;
            this.mMenu.onItemsChanged(false);
        }
        return (MenuItem)this;
    }
    
    public MenuItem setOnActionExpandListener(final MenuItem$OnActionExpandListener menuItem$OnActionExpandListener) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setOnActionExpandListener()");
    }
    
    public MenuItem setOnMenuItemClickListener(final MenuItem$OnMenuItemClickListener mClickListener) {
        this.mClickListener = mClickListener;
        return (MenuItem)this;
    }
    
    public MenuItem setShortcut(final char mShortcutNumericChar, final char c) {
        this.mShortcutNumericChar = mShortcutNumericChar;
        this.mShortcutAlphabeticChar = Character.toLowerCase(c);
        this.mMenu.onItemsChanged(false);
        return (MenuItem)this;
    }
    
    @Override
    public void setShowAsAction(final int mShowAsAction) {
        switch (mShowAsAction & 0x3) {
            default: {
                throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
            }
            case 0:
            case 1:
            case 2: {
                this.mShowAsAction = mShowAsAction;
                this.mMenu.onItemActionRequestChanged(this);
            }
        }
    }
    
    public SupportMenuItem setShowAsActionFlags(final int showAsAction) {
        this.setShowAsAction(showAsAction);
        return this;
    }
    
    public void setSubMenu(final SubMenuBuilder mSubMenu) {
        (this.mSubMenu = mSubMenu).setHeaderTitle(this.getTitle());
    }
    
    @Override
    public SupportMenuItem setSupportActionProvider(final ActionProvider mActionProvider) {
        if (this.mActionProvider != null) {
            this.mActionProvider.reset();
        }
        this.mActionView = null;
        this.mActionProvider = mActionProvider;
        this.mMenu.onItemsChanged(true);
        if (this.mActionProvider != null) {
            this.mActionProvider.setVisibilityListener((ActionProvider.VisibilityListener)new ActionProvider.VisibilityListener() {
                @Override
                public void onActionProviderVisibilityChanged(final boolean b) {
                    MenuItemImpl.this.mMenu.onItemVisibleChanged(MenuItemImpl.this);
                }
            });
        }
        return this;
    }
    
    @Override
    public SupportMenuItem setSupportOnActionExpandListener(final MenuItemCompat.OnActionExpandListener mOnActionExpandListener) {
        this.mOnActionExpandListener = mOnActionExpandListener;
        return this;
    }
    
    public MenuItem setTitle(final int n) {
        return this.setTitle(this.mMenu.getContext().getString(n));
    }
    
    public MenuItem setTitle(final CharSequence charSequence) {
        this.mTitle = charSequence;
        this.mMenu.onItemsChanged(false);
        if (this.mSubMenu != null) {
            this.mSubMenu.setHeaderTitle(charSequence);
        }
        return (MenuItem)this;
    }
    
    public MenuItem setTitleCondensed(final CharSequence mTitleCondensed) {
        this.mTitleCondensed = mTitleCondensed;
        if (mTitleCondensed == null) {
            final CharSequence mTitle = this.mTitle;
        }
        this.mMenu.onItemsChanged(false);
        return (MenuItem)this;
    }
    
    public MenuItem setVisible(final boolean visibleInt) {
        if (this.setVisibleInt(visibleInt)) {
            this.mMenu.onItemVisibleChanged(this);
        }
        return (MenuItem)this;
    }
    
    boolean setVisibleInt(final boolean b) {
        final int mFlags = this.mFlags;
        final int n = 0xFFFFFFF7 & this.mFlags;
        int n2;
        if (b) {
            n2 = 0;
        }
        else {
            n2 = 8;
        }
        this.mFlags = (n2 | n);
        final int mFlags2 = this.mFlags;
        boolean b2 = false;
        if (mFlags != mFlags2) {
            b2 = true;
        }
        return b2;
    }
    
    public boolean shouldShowIcon() {
        return this.mMenu.getOptionalIconsVisible();
    }
    
    boolean shouldShowShortcut() {
        return this.mMenu.isShortcutsVisible() && this.getShortcut() != '\0';
    }
    
    public boolean showsTextAsAction() {
        return (0x4 & this.mShowAsAction) == 0x4;
    }
    
    @Override
    public String toString() {
        String string;
        if (this.mTitle != null) {
            string = this.mTitle.toString();
        }
        else {
            string = null;
        }
        return string;
    }
}
