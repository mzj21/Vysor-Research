// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view;

import android.content.res.XmlResourceParser;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R;
import android.view.SubMenu;
import android.view.View;
import android.support.v7.view.menu.MenuItemWrapperICS;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v4.view.MenuItemCompat;
import java.lang.reflect.Constructor;
import android.util.Log;
import android.support.v4.view.ActionProvider;
import android.view.MenuItem;
import java.lang.reflect.Method;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.InflateException;
import android.util.Xml;
import android.support.v4.internal.view.SupportMenu;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import android.view.Menu;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;
import android.content.ContextWrapper;
import android.app.Activity;
import android.content.Context;
import android.view.MenuInflater;

public class SupportMenuInflater extends MenuInflater
{
    static final Class<?>[] ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE;
    static final Class<?>[] ACTION_VIEW_CONSTRUCTOR_SIGNATURE;
    static final String LOG_TAG = "SupportMenuInflater";
    static final int NO_ID = 0;
    private static final String XML_GROUP = "group";
    private static final String XML_ITEM = "item";
    private static final String XML_MENU = "menu";
    final Object[] mActionProviderConstructorArguments;
    final Object[] mActionViewConstructorArguments;
    Context mContext;
    private Object mRealOwner;
    
    static {
        ACTION_VIEW_CONSTRUCTOR_SIGNATURE = new Class[] { Context.class };
        ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE = SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE;
    }
    
    public SupportMenuInflater(final Context mContext) {
        super(mContext);
        this.mContext = mContext;
        this.mActionViewConstructorArguments = new Object[] { mContext };
        this.mActionProviderConstructorArguments = this.mActionViewConstructorArguments;
    }
    
    private Object findRealOwner(Object realOwner) {
        if (!(realOwner instanceof Activity) && realOwner instanceof ContextWrapper) {
            realOwner = this.findRealOwner(((ContextWrapper)realOwner).getBaseContext());
        }
        return realOwner;
    }
    
    private void parseMenu(final XmlPullParser xmlPullParser, final AttributeSet set, final Menu menu) throws XmlPullParserException, IOException {
        final MenuState menuState = new MenuState(menu);
        int i = xmlPullParser.getEventType();
        int n = 0;
        Object o = null;
        String name3;
        while (true) {
            while (i != 2) {
                i = xmlPullParser.next();
                if (i == 1) {
                    int j = 0;
                    while (j == 0) {
                        switch (i) {
                            case 2: {
                                if (n != 0) {
                                    break;
                                }
                                final String name = xmlPullParser.getName();
                                if (name.equals("group")) {
                                    menuState.readGroup(set);
                                    break;
                                }
                                if (name.equals("item")) {
                                    menuState.readItem(set);
                                    break;
                                }
                                if (name.equals("menu")) {
                                    this.parseMenu(xmlPullParser, set, (Menu)menuState.addSubMenuItem());
                                    break;
                                }
                                n = 1;
                                o = name;
                                break;
                            }
                            case 3: {
                                final String name2 = xmlPullParser.getName();
                                if (n != 0 && name2.equals(o)) {
                                    n = 0;
                                    o = null;
                                    break;
                                }
                                if (name2.equals("group")) {
                                    menuState.resetGroup();
                                    break;
                                }
                                if (name2.equals("item")) {
                                    if (menuState.hasAddedItem()) {
                                        break;
                                    }
                                    if (menuState.itemActionProvider != null && menuState.itemActionProvider.hasSubMenu()) {
                                        menuState.addSubMenuItem();
                                        break;
                                    }
                                    menuState.addItem();
                                    break;
                                }
                                else {
                                    if (name2.equals("menu")) {
                                        j = 1;
                                        break;
                                    }
                                    break;
                                }
                                break;
                            }
                            case 1: {
                                throw new RuntimeException("Unexpected end of document");
                            }
                        }
                        i = xmlPullParser.next();
                    }
                    return;
                }
            }
            name3 = xmlPullParser.getName();
            if (name3.equals("menu")) {
                i = xmlPullParser.next();
                continue;
            }
            break;
        }
        throw new RuntimeException("Expecting menu, got " + name3);
    }
    
    Object getRealOwner() {
        if (this.mRealOwner == null) {
            this.mRealOwner = this.findRealOwner(this.mContext);
        }
        return this.mRealOwner;
    }
    
    public void inflate(final int n, final Menu menu) {
        if (!(menu instanceof SupportMenu)) {
            super.inflate(n, menu);
        }
        else {
            Object layout = null;
            try {
                layout = this.mContext.getResources().getLayout(n);
                this.parseMenu((XmlPullParser)layout, Xml.asAttributeSet((XmlPullParser)layout), menu);
            }
            catch (XmlPullParserException ex) {
                throw new InflateException("Error inflating menu XML", (Throwable)ex);
            }
            catch (IOException ex2) {
                throw new InflateException("Error inflating menu XML", (Throwable)ex2);
            }
            finally {
                if (layout != null) {
                    ((XmlResourceParser)layout).close();
                }
            }
        }
    }
    
    private static class InflatedOnMenuItemClickListener implements MenuItem$OnMenuItemClickListener
    {
        private static final Class<?>[] PARAM_TYPES;
        private Method mMethod;
        private Object mRealOwner;
        
        static {
            PARAM_TYPES = new Class[] { MenuItem.class };
        }
        
        public InflatedOnMenuItemClickListener(final Object mRealOwner, final String s) {
            this.mRealOwner = mRealOwner;
            final Class<?> class1 = mRealOwner.getClass();
            try {
                this.mMethod = class1.getMethod(s, InflatedOnMenuItemClickListener.PARAM_TYPES);
            }
            catch (Exception ex2) {
                final InflateException ex = new InflateException("Couldn't resolve menu item onClick handler " + s + " in class " + class1.getName());
                ex.initCause((Throwable)ex2);
                throw ex;
            }
        }
        
        public boolean onMenuItemClick(final MenuItem menuItem) {
            boolean booleanValue = true;
            try {
                if (this.mMethod.getReturnType() == Boolean.TYPE) {
                    booleanValue = (boolean)this.mMethod.invoke(this.mRealOwner, menuItem);
                }
                else {
                    this.mMethod.invoke(this.mRealOwner, menuItem);
                }
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            return booleanValue;
        }
    }
    
    private class MenuState
    {
        private static final int defaultGroupId = 0;
        private static final int defaultItemCategory = 0;
        private static final int defaultItemCheckable = 0;
        private static final boolean defaultItemChecked = false;
        private static final boolean defaultItemEnabled = true;
        private static final int defaultItemId = 0;
        private static final int defaultItemOrder = 0;
        private static final boolean defaultItemVisible = true;
        private int groupCategory;
        private int groupCheckable;
        private boolean groupEnabled;
        private int groupId;
        private int groupOrder;
        private boolean groupVisible;
        ActionProvider itemActionProvider;
        private String itemActionProviderClassName;
        private String itemActionViewClassName;
        private int itemActionViewLayout;
        private boolean itemAdded;
        private char itemAlphabeticShortcut;
        private int itemCategoryOrder;
        private int itemCheckable;
        private boolean itemChecked;
        private boolean itemEnabled;
        private int itemIconResId;
        private int itemId;
        private String itemListenerMethodName;
        private char itemNumericShortcut;
        private int itemShowAsAction;
        private CharSequence itemTitle;
        private CharSequence itemTitleCondensed;
        private boolean itemVisible;
        private Menu menu;
        
        public MenuState(final Menu menu) {
            this.menu = menu;
            this.resetGroup();
        }
        
        private char getShortcut(final String s) {
            char char1 = '\0';
            if (s != null) {
                char1 = s.charAt(0);
            }
            return char1;
        }
        
        private <T> T newInstance(final String s, final Class<?>[] array, final Object[] array2) {
            try {
                final Constructor<?> constructor = SupportMenuInflater.this.mContext.getClassLoader().loadClass(s).getConstructor(array);
                constructor.setAccessible(true);
                final Object instance = constructor.newInstance(array2);
                return (T)instance;
            }
            catch (Exception ex) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + s, (Throwable)ex);
                final Object instance = null;
                return (T)instance;
            }
        }
        
        private void setItem(final MenuItem menuItem) {
            menuItem.setChecked(this.itemChecked).setVisible(this.itemVisible).setEnabled(this.itemEnabled).setCheckable(this.itemCheckable >= 1).setTitleCondensed(this.itemTitleCondensed).setIcon(this.itemIconResId).setAlphabeticShortcut(this.itemAlphabeticShortcut).setNumericShortcut(this.itemNumericShortcut);
            if (this.itemShowAsAction >= 0) {
                MenuItemCompat.setShowAsAction(menuItem, this.itemShowAsAction);
            }
            if (this.itemListenerMethodName != null) {
                if (SupportMenuInflater.this.mContext.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new InflatedOnMenuItemClickListener(SupportMenuInflater.this.getRealOwner(), this.itemListenerMethodName));
            }
            if (menuItem instanceof MenuItemImpl) {
                final MenuItemImpl menuItemImpl = (MenuItemImpl)menuItem;
            }
            if (this.itemCheckable >= 2) {
                if (menuItem instanceof MenuItemImpl) {
                    ((MenuItemImpl)menuItem).setExclusiveCheckable(true);
                }
                else if (menuItem instanceof MenuItemWrapperICS) {
                    ((MenuItemWrapperICS)menuItem).setExclusiveCheckable(true);
                }
            }
            final String itemActionViewClassName = this.itemActionViewClassName;
            boolean b = false;
            if (itemActionViewClassName != null) {
                MenuItemCompat.setActionView(menuItem, this.newInstance(this.itemActionViewClassName, SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE, SupportMenuInflater.this.mActionViewConstructorArguments));
                b = true;
            }
            if (this.itemActionViewLayout > 0) {
                if (!b) {
                    MenuItemCompat.setActionView(menuItem, this.itemActionViewLayout);
                }
                else {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                }
            }
            if (this.itemActionProvider != null) {
                MenuItemCompat.setActionProvider(menuItem, this.itemActionProvider);
            }
        }
        
        public void addItem() {
            this.itemAdded = true;
            this.setItem(this.menu.add(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle));
        }
        
        public SubMenu addSubMenuItem() {
            this.itemAdded = true;
            final SubMenu addSubMenu = this.menu.addSubMenu(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle);
            this.setItem(addSubMenu.getItem());
            return addSubMenu;
        }
        
        public boolean hasAddedItem() {
            return this.itemAdded;
        }
        
        public void readGroup(final AttributeSet set) {
            final TypedArray obtainStyledAttributes = SupportMenuInflater.this.mContext.obtainStyledAttributes(set, R.styleable.MenuGroup);
            this.groupId = obtainStyledAttributes.getResourceId(R.styleable.MenuGroup_android_id, 0);
            this.groupCategory = obtainStyledAttributes.getInt(R.styleable.MenuGroup_android_menuCategory, 0);
            this.groupOrder = obtainStyledAttributes.getInt(R.styleable.MenuGroup_android_orderInCategory, 0);
            this.groupCheckable = obtainStyledAttributes.getInt(R.styleable.MenuGroup_android_checkableBehavior, 0);
            this.groupVisible = obtainStyledAttributes.getBoolean(R.styleable.MenuGroup_android_visible, true);
            this.groupEnabled = obtainStyledAttributes.getBoolean(R.styleable.MenuGroup_android_enabled, true);
            obtainStyledAttributes.recycle();
        }
        
        public void readItem(final AttributeSet set) {
            final TypedArray obtainStyledAttributes = SupportMenuInflater.this.mContext.obtainStyledAttributes(set, R.styleable.MenuItem);
            this.itemId = obtainStyledAttributes.getResourceId(R.styleable.MenuItem_android_id, 0);
            this.itemCategoryOrder = ((0xFFFF0000 & obtainStyledAttributes.getInt(R.styleable.MenuItem_android_menuCategory, this.groupCategory)) | (0xFFFF & obtainStyledAttributes.getInt(R.styleable.MenuItem_android_orderInCategory, this.groupOrder)));
            this.itemTitle = obtainStyledAttributes.getText(R.styleable.MenuItem_android_title);
            this.itemTitleCondensed = obtainStyledAttributes.getText(R.styleable.MenuItem_android_titleCondensed);
            this.itemIconResId = obtainStyledAttributes.getResourceId(R.styleable.MenuItem_android_icon, 0);
            this.itemAlphabeticShortcut = this.getShortcut(obtainStyledAttributes.getString(R.styleable.MenuItem_android_alphabeticShortcut));
            this.itemNumericShortcut = this.getShortcut(obtainStyledAttributes.getString(R.styleable.MenuItem_android_numericShortcut));
            if (obtainStyledAttributes.hasValue(R.styleable.MenuItem_android_checkable)) {
                int itemCheckable;
                if (obtainStyledAttributes.getBoolean(R.styleable.MenuItem_android_checkable, false)) {
                    itemCheckable = 1;
                }
                else {
                    itemCheckable = 0;
                }
                this.itemCheckable = itemCheckable;
            }
            else {
                this.itemCheckable = this.groupCheckable;
            }
            this.itemChecked = obtainStyledAttributes.getBoolean(R.styleable.MenuItem_android_checked, false);
            this.itemVisible = obtainStyledAttributes.getBoolean(R.styleable.MenuItem_android_visible, this.groupVisible);
            this.itemEnabled = obtainStyledAttributes.getBoolean(R.styleable.MenuItem_android_enabled, this.groupEnabled);
            this.itemShowAsAction = obtainStyledAttributes.getInt(R.styleable.MenuItem_showAsAction, -1);
            this.itemListenerMethodName = obtainStyledAttributes.getString(R.styleable.MenuItem_android_onClick);
            this.itemActionViewLayout = obtainStyledAttributes.getResourceId(R.styleable.MenuItem_actionLayout, 0);
            this.itemActionViewClassName = obtainStyledAttributes.getString(R.styleable.MenuItem_actionViewClass);
            this.itemActionProviderClassName = obtainStyledAttributes.getString(R.styleable.MenuItem_actionProviderClass);
            boolean b;
            if (this.itemActionProviderClassName != null) {
                b = true;
            }
            else {
                b = false;
            }
            if (b && this.itemActionViewLayout == 0 && this.itemActionViewClassName == null) {
                this.itemActionProvider = this.newInstance(this.itemActionProviderClassName, SupportMenuInflater.ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE, SupportMenuInflater.this.mActionProviderConstructorArguments);
            }
            else {
                if (b) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.itemActionProvider = null;
            }
            obtainStyledAttributes.recycle();
            this.itemAdded = false;
        }
        
        public void resetGroup() {
            this.groupId = 0;
            this.groupCategory = 0;
            this.groupOrder = 0;
            this.groupCheckable = 0;
            this.groupVisible = true;
            this.groupEnabled = true;
        }
    }
}
