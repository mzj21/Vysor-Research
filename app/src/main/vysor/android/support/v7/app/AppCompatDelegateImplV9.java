// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.View$MeasureSpec;
import android.view.accessibility.AccessibilityEvent;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.ActionMenuPresenter;
import android.view.View$OnClickListener;
import android.support.v7.widget.TintTypedArray;
import android.widget.LinearLayout;
import android.support.v7.widget.AbsActionBarView;
import android.support.v4.os.ParcelableCompat;
import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.ListMenuPresenter;
import android.support.v7.content.res.AppCompatResources;
import android.view.MotionEvent;
import android.view.ViewGroup$MarginLayoutParams;
import android.support.v7.view.StandaloneActionMode;
import android.support.v7.widget.ViewStubCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.AppCompatDrawableManager;
import android.content.res.Configuration;
import android.support.v4.view.LayoutInflaterCompat;
import android.app.Dialog;
import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.annotation.IdRes;
import android.os.Bundle;
import android.support.v7.widget.VectorEnabledTintResources;
import android.support.annotation.NonNull;
import android.view.LayoutInflater$Factory;
import android.util.AttributeSet;
import android.util.AndroidRuntimeException;
import android.view.KeyCharacterMap;
import android.view.ViewParent;
import android.view.Window$Callback;
import android.view.WindowManager$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.view.WindowManager;
import android.view.Menu;
import android.util.Log;
import android.media.AudioManager;
import android.support.v4.view.ViewConfigurationCompat;
import android.view.ViewConfiguration;
import android.view.KeyEvent;
import android.content.res.Resources$Theme;
import android.support.v7.view.menu.MenuPresenter;
import android.text.TextUtils;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.support.v7.widget.ViewUtils;
import android.support.v7.widget.FitWindowsViewGroup;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.os.Build$VERSION;
import android.support.v7.view.ContextThemeWrapper;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R;
import android.support.v7.widget.ContentFrameLayout;
import android.view.Window;
import android.content.Context;
import android.widget.TextView;
import android.graphics.Rect;
import android.view.ViewGroup;
import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.DecorContentParent;
import android.support.v7.widget.ActionBarContextView;
import android.widget.PopupWindow;
import android.support.v7.view.ActionMode;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v4.view.LayoutInflaterFactory;

class AppCompatDelegateImplV9 extends AppCompatDelegateImplBase implements LayoutInflaterFactory, Callback
{
    private ActionMenuPresenterCallback mActionMenuPresenterCallback;
    ActionMode mActionMode;
    PopupWindow mActionModePopup;
    ActionBarContextView mActionModeView;
    private AppCompatViewInflater mAppCompatViewInflater;
    private boolean mClosingActionMenu;
    private DecorContentParent mDecorContentParent;
    private boolean mEnableDefaultActionBarUp;
    ViewPropertyAnimatorCompat mFadeAnim;
    private boolean mFeatureIndeterminateProgress;
    private boolean mFeatureProgress;
    int mInvalidatePanelMenuFeatures;
    boolean mInvalidatePanelMenuPosted;
    private final Runnable mInvalidatePanelMenuRunnable;
    private boolean mLongPressBackDown;
    private PanelMenuPresenterCallback mPanelMenuPresenterCallback;
    private PanelFeatureState[] mPanels;
    private PanelFeatureState mPreparedPanel;
    Runnable mShowActionModePopup;
    private View mStatusGuard;
    private ViewGroup mSubDecor;
    private boolean mSubDecorInstalled;
    private Rect mTempRect1;
    private Rect mTempRect2;
    private TextView mTitleView;
    
    AppCompatDelegateImplV9(final Context context, final Window window, final AppCompatCallback appCompatCallback) {
        super(context, window, appCompatCallback);
        this.mFadeAnim = null;
        this.mInvalidatePanelMenuRunnable = new Runnable() {
            @Override
            public void run() {
                if ((0x1 & AppCompatDelegateImplV9.this.mInvalidatePanelMenuFeatures) != 0x0) {
                    AppCompatDelegateImplV9.this.doInvalidatePanelMenu(0);
                }
                if ((0x1000 & AppCompatDelegateImplV9.this.mInvalidatePanelMenuFeatures) != 0x0) {
                    AppCompatDelegateImplV9.this.doInvalidatePanelMenu(108);
                }
                AppCompatDelegateImplV9.this.mInvalidatePanelMenuPosted = false;
                AppCompatDelegateImplV9.this.mInvalidatePanelMenuFeatures = 0;
            }
        };
    }
    
    private void applyFixedSizeWindow() {
        final ContentFrameLayout contentFrameLayout = (ContentFrameLayout)this.mSubDecor.findViewById(16908290);
        final View decorView = this.mWindow.getDecorView();
        contentFrameLayout.setDecorPadding(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        final TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.AppCompatTheme);
        obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowFixedWidthMajor)) {
            obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout.getFixedWidthMajor());
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowFixedWidthMinor)) {
            obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout.getFixedWidthMinor());
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowFixedHeightMajor)) {
            obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout.getFixedHeightMajor());
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowFixedHeightMinor)) {
            obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout.getFixedHeightMinor());
        }
        obtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }
    
    private ViewGroup createSubDecor() {
        final TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.AppCompatTheme);
        if (!obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowActionBar)) {
            obtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowNoTitle, false)) {
            this.requestWindowFeature(1);
        }
        else if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowActionBar, false)) {
            this.requestWindowFeature(108);
        }
        if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowActionBarOverlay, false)) {
            this.requestWindowFeature(109);
        }
        if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowActionModeOverlay, false)) {
            this.requestWindowFeature(10);
        }
        this.mIsFloating = obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_android_windowIsFloating, false);
        obtainStyledAttributes.recycle();
        this.mWindow.getDecorView();
        final LayoutInflater from = LayoutInflater.from(this.mContext);
        Object contentView;
        if (!this.mWindowNoTitle) {
            if (this.mIsFloating) {
                contentView = from.inflate(R.layout.abc_dialog_title_material, (ViewGroup)null);
                this.mOverlayActionBar = false;
                this.mHasActionBar = false;
            }
            else {
                final boolean mHasActionBar = this.mHasActionBar;
                contentView = null;
                if (mHasActionBar) {
                    final TypedValue typedValue = new TypedValue();
                    this.mContext.getTheme().resolveAttribute(R.attr.actionBarTheme, typedValue, true);
                    Object mContext;
                    if (typedValue.resourceId != 0) {
                        mContext = new ContextThemeWrapper(this.mContext, typedValue.resourceId);
                    }
                    else {
                        mContext = this.mContext;
                    }
                    contentView = LayoutInflater.from((Context)mContext).inflate(R.layout.abc_screen_toolbar, (ViewGroup)null);
                    (this.mDecorContentParent = (DecorContentParent)((ViewGroup)contentView).findViewById(R.id.decor_content_parent)).setWindowCallback(this.getWindowCallback());
                    if (this.mOverlayActionBar) {
                        this.mDecorContentParent.initFeature(109);
                    }
                    if (this.mFeatureProgress) {
                        this.mDecorContentParent.initFeature(2);
                    }
                    if (this.mFeatureIndeterminateProgress) {
                        this.mDecorContentParent.initFeature(5);
                    }
                }
            }
        }
        else {
            if (this.mOverlayActionMode) {
                contentView = from.inflate(R.layout.abc_screen_simple_overlay_action_mode, (ViewGroup)null);
            }
            else {
                contentView = from.inflate(R.layout.abc_screen_simple, (ViewGroup)null);
            }
            if (Build$VERSION.SDK_INT >= 21) {
                ViewCompat.setOnApplyWindowInsetsListener((View)contentView, new OnApplyWindowInsetsListener() {
                    @Override
                    public WindowInsetsCompat onApplyWindowInsets(final View view, WindowInsetsCompat replaceSystemWindowInsets) {
                        final int systemWindowInsetTop = replaceSystemWindowInsets.getSystemWindowInsetTop();
                        final int updateStatusGuard = AppCompatDelegateImplV9.this.updateStatusGuard(systemWindowInsetTop);
                        if (systemWindowInsetTop != updateStatusGuard) {
                            replaceSystemWindowInsets = replaceSystemWindowInsets.replaceSystemWindowInsets(replaceSystemWindowInsets.getSystemWindowInsetLeft(), updateStatusGuard, replaceSystemWindowInsets.getSystemWindowInsetRight(), replaceSystemWindowInsets.getSystemWindowInsetBottom());
                        }
                        return ViewCompat.onApplyWindowInsets(view, replaceSystemWindowInsets);
                    }
                });
            }
            else {
                ((FitWindowsViewGroup)contentView).setOnFitSystemWindowsListener((FitWindowsViewGroup.OnFitSystemWindowsListener)new FitWindowsViewGroup.OnFitSystemWindowsListener() {
                    @Override
                    public void onFitSystemWindows(final Rect rect) {
                        rect.top = AppCompatDelegateImplV9.this.updateStatusGuard(rect.top);
                    }
                });
            }
        }
        if (contentView == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.mHasActionBar + ", windowActionBarOverlay: " + this.mOverlayActionBar + ", android:windowIsFloating: " + this.mIsFloating + ", windowActionModeOverlay: " + this.mOverlayActionMode + ", windowNoTitle: " + this.mWindowNoTitle + " }");
        }
        if (this.mDecorContentParent == null) {
            this.mTitleView = (TextView)((ViewGroup)contentView).findViewById(R.id.title);
        }
        ViewUtils.makeOptionalFitsSystemWindows((View)contentView);
        final ContentFrameLayout contentFrameLayout = (ContentFrameLayout)((ViewGroup)contentView).findViewById(R.id.action_bar_activity_content);
        final ViewGroup viewGroup = (ViewGroup)this.mWindow.findViewById(16908290);
        if (viewGroup != null) {
            while (viewGroup.getChildCount() > 0) {
                final View child = viewGroup.getChildAt(0);
                viewGroup.removeViewAt(0);
                contentFrameLayout.addView(child);
            }
            viewGroup.setId(-1);
            contentFrameLayout.setId(16908290);
            if (viewGroup instanceof FrameLayout) {
                ((FrameLayout)viewGroup).setForeground((Drawable)null);
            }
        }
        this.mWindow.setContentView((View)contentView);
        contentFrameLayout.setAttachListener((ContentFrameLayout.OnAttachListener)new ContentFrameLayout.OnAttachListener() {
            @Override
            public void onAttachedFromWindow() {
            }
            
            @Override
            public void onDetachedFromWindow() {
                AppCompatDelegateImplV9.this.dismissPopups();
            }
        });
        return (ViewGroup)contentView;
    }
    
    private void ensureSubDecor() {
        if (!this.mSubDecorInstalled) {
            this.mSubDecor = this.createSubDecor();
            final CharSequence title = this.getTitle();
            if (!TextUtils.isEmpty(title)) {
                this.onTitleChanged(title);
            }
            this.applyFixedSizeWindow();
            this.onSubDecorInstalled(this.mSubDecor);
            this.mSubDecorInstalled = true;
            final PanelFeatureState panelState = this.getPanelState(0, false);
            if (!this.isDestroyed() && (panelState == null || panelState.menu == null)) {
                this.invalidatePanelMenu(108);
            }
        }
    }
    
    private boolean initializePanelContent(final PanelFeatureState panelFeatureState) {
        boolean b = true;
        if (panelFeatureState.createdPanelView != null) {
            panelFeatureState.shownPanelView = panelFeatureState.createdPanelView;
        }
        else if (panelFeatureState.menu == null) {
            b = false;
        }
        else {
            if (this.mPanelMenuPresenterCallback == null) {
                this.mPanelMenuPresenterCallback = new PanelMenuPresenterCallback();
            }
            panelFeatureState.shownPanelView = (View)panelFeatureState.getListMenuView(this.mPanelMenuPresenterCallback);
            if (panelFeatureState.shownPanelView == null) {
                b = false;
            }
        }
        return b;
    }
    
    private boolean initializePanelDecor(final PanelFeatureState panelFeatureState) {
        panelFeatureState.setStyle(this.getActionBarThemedContext());
        panelFeatureState.decorView = (ViewGroup)new ListMenuDecorView(panelFeatureState.listPresenterContext);
        panelFeatureState.gravity = 81;
        return true;
    }
    
    private boolean initializePanelMenu(final PanelFeatureState panelFeatureState) {
        Context mContext = this.mContext;
        if ((panelFeatureState.featureId == 0 || panelFeatureState.featureId == 108) && this.mDecorContentParent != null) {
            final TypedValue typedValue = new TypedValue();
            final Resources$Theme theme = mContext.getTheme();
            theme.resolveAttribute(R.attr.actionBarTheme, typedValue, true);
            Resources$Theme to;
            if (typedValue.resourceId != 0) {
                to = mContext.getResources().newTheme();
                to.setTo(theme);
                to.applyStyle(typedValue.resourceId, true);
                to.resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
            }
            else {
                theme.resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
                to = null;
            }
            if (typedValue.resourceId != 0) {
                if (to == null) {
                    to = mContext.getResources().newTheme();
                    to.setTo(theme);
                }
                to.applyStyle(typedValue.resourceId, true);
            }
            if (to != null) {
                final ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(mContext, 0);
                ((Context)contextThemeWrapper).getTheme().setTo(to);
                mContext = (Context)contextThemeWrapper;
            }
        }
        final MenuBuilder menu = new MenuBuilder(mContext);
        menu.setCallback((MenuBuilder.Callback)this);
        panelFeatureState.setMenu(menu);
        return true;
    }
    
    private void invalidatePanelMenu(final int n) {
        this.mInvalidatePanelMenuFeatures |= 1 << n;
        if (!this.mInvalidatePanelMenuPosted) {
            ViewCompat.postOnAnimation(this.mWindow.getDecorView(), this.mInvalidatePanelMenuRunnable);
            this.mInvalidatePanelMenuPosted = true;
        }
    }
    
    private boolean onKeyDownPanel(final int n, final KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        final PanelFeatureState panelState = this.getPanelState(n, true);
        if (panelState.isOpen) {
            return false;
        }
        return this.preparePanel(panelState, keyEvent);
        preparePanel = false;
        return preparePanel;
    }
    
    private boolean onKeyUpPanel(final int n, final KeyEvent keyEvent) {
        boolean b;
        if (this.mActionMode != null) {
            b = false;
        }
        else {
            final PanelFeatureState panelState = this.getPanelState(n, true);
            if (n == 0 && this.mDecorContentParent != null && this.mDecorContentParent.canShowOverflowMenu() && !ViewConfigurationCompat.hasPermanentMenuKey(ViewConfiguration.get(this.mContext))) {
                if (!this.mDecorContentParent.isOverflowMenuShowing()) {
                    final boolean destroyed = this.isDestroyed();
                    b = false;
                    if (!destroyed) {
                        final boolean preparePanel = this.preparePanel(panelState, keyEvent);
                        b = false;
                        if (preparePanel) {
                            b = this.mDecorContentParent.showOverflowMenu();
                        }
                    }
                }
                else {
                    b = this.mDecorContentParent.hideOverflowMenu();
                }
            }
            else if (panelState.isOpen || panelState.isHandled) {
                b = panelState.isOpen;
                this.closePanel(panelState, true);
            }
            else {
                final boolean isPrepared = panelState.isPrepared;
                b = false;
                if (isPrepared) {
                    boolean preparePanel2 = true;
                    if (panelState.refreshMenuContent) {
                        panelState.isPrepared = false;
                        preparePanel2 = this.preparePanel(panelState, keyEvent);
                    }
                    b = false;
                    if (preparePanel2) {
                        this.openPanel(panelState, keyEvent);
                        b = true;
                    }
                }
            }
            if (b) {
                final AudioManager audioManager = (AudioManager)this.mContext.getSystemService("audio");
                if (audioManager != null) {
                    audioManager.playSoundEffect(0);
                }
                else {
                    Log.w("AppCompatDelegate", "Couldn't get audio manager");
                }
            }
        }
        return b;
    }
    
    private void openPanel(final PanelFeatureState panelFeatureState, final KeyEvent keyEvent) {
        if (!panelFeatureState.isOpen && !this.isDestroyed()) {
            if (panelFeatureState.featureId == 0) {
                final Context mContext = this.mContext;
                boolean b;
                if ((0xF & mContext.getResources().getConfiguration().screenLayout) == 0x4) {
                    b = true;
                }
                else {
                    b = false;
                }
                boolean b2;
                if (mContext.getApplicationInfo().targetSdkVersion >= 11) {
                    b2 = true;
                }
                else {
                    b2 = false;
                }
                if (b && b2) {
                    return;
                }
            }
            final Window$Callback windowCallback = this.getWindowCallback();
            if (windowCallback != null && !windowCallback.onMenuOpened(panelFeatureState.featureId, (Menu)panelFeatureState.menu)) {
                this.closePanel(panelFeatureState, true);
            }
            else {
                final WindowManager windowManager = (WindowManager)this.mContext.getSystemService("window");
                if (windowManager != null && this.preparePanel(panelFeatureState, keyEvent)) {
                    int n = -2;
                    if (panelFeatureState.decorView == null || panelFeatureState.refreshDecorView) {
                        if (panelFeatureState.decorView == null) {
                            if (!this.initializePanelDecor(panelFeatureState) || panelFeatureState.decorView == null) {
                                return;
                            }
                        }
                        else if (panelFeatureState.refreshDecorView && panelFeatureState.decorView.getChildCount() > 0) {
                            panelFeatureState.decorView.removeAllViews();
                        }
                        if (!this.initializePanelContent(panelFeatureState) || !panelFeatureState.hasPanelItems()) {
                            return;
                        }
                        ViewGroup$LayoutParams layoutParams = panelFeatureState.shownPanelView.getLayoutParams();
                        if (layoutParams == null) {
                            layoutParams = new ViewGroup$LayoutParams(-2, -2);
                        }
                        panelFeatureState.decorView.setBackgroundResource(panelFeatureState.background);
                        final ViewParent parent = panelFeatureState.shownPanelView.getParent();
                        if (parent != null && parent instanceof ViewGroup) {
                            ((ViewGroup)parent).removeView(panelFeatureState.shownPanelView);
                        }
                        panelFeatureState.decorView.addView(panelFeatureState.shownPanelView, layoutParams);
                        if (!panelFeatureState.shownPanelView.hasFocus()) {
                            panelFeatureState.shownPanelView.requestFocus();
                        }
                    }
                    else if (panelFeatureState.createdPanelView != null) {
                        final ViewGroup$LayoutParams layoutParams2 = panelFeatureState.createdPanelView.getLayoutParams();
                        if (layoutParams2 != null && layoutParams2.width == -1) {
                            n = -1;
                        }
                    }
                    panelFeatureState.isHandled = false;
                    final WindowManager$LayoutParams windowManager$LayoutParams = new WindowManager$LayoutParams(n, -2, panelFeatureState.x, panelFeatureState.y, 1002, 8519680, -3);
                    windowManager$LayoutParams.gravity = panelFeatureState.gravity;
                    windowManager$LayoutParams.windowAnimations = panelFeatureState.windowAnimations;
                    windowManager.addView((View)panelFeatureState.decorView, (ViewGroup$LayoutParams)windowManager$LayoutParams);
                    panelFeatureState.isOpen = true;
                }
            }
        }
    }
    
    private boolean performPanelShortcut(final PanelFeatureState panelFeatureState, final int n, final KeyEvent keyEvent, final int n2) {
        boolean performShortcut = false;
        if (keyEvent.isSystem()) {
            performShortcut = false;
        }
        else {
            Label_0063: {
                if (!panelFeatureState.isPrepared) {
                    final boolean preparePanel = this.preparePanel(panelFeatureState, keyEvent);
                    performShortcut = false;
                    if (!preparePanel) {
                        break Label_0063;
                    }
                }
                final MenuBuilder menu = panelFeatureState.menu;
                performShortcut = false;
                if (menu != null) {
                    performShortcut = panelFeatureState.menu.performShortcut(n, keyEvent, n2);
                }
            }
            if (performShortcut && (n2 & 0x1) == 0x0 && this.mDecorContentParent == null) {
                this.closePanel(panelFeatureState, true);
            }
        }
        return performShortcut;
    }
    
    private boolean preparePanel(final PanelFeatureState mPreparedPanel, final KeyEvent keyEvent) {
        final boolean destroyed = this.isDestroyed();
        boolean b = false;
        if (!destroyed) {
            if (mPreparedPanel.isPrepared) {
                b = true;
            }
            else {
                if (this.mPreparedPanel != null && this.mPreparedPanel != mPreparedPanel) {
                    this.closePanel(this.mPreparedPanel, false);
                }
                final Window$Callback windowCallback = this.getWindowCallback();
                if (windowCallback != null) {
                    mPreparedPanel.createdPanelView = windowCallback.onCreatePanelView(mPreparedPanel.featureId);
                }
                boolean b2;
                if (mPreparedPanel.featureId == 0 || mPreparedPanel.featureId == 108) {
                    b2 = true;
                }
                else {
                    b2 = false;
                }
                if (b2 && this.mDecorContentParent != null) {
                    this.mDecorContentParent.setMenuPrepared();
                }
                if (mPreparedPanel.createdPanelView == null && (!b2 || !(this.peekSupportActionBar() instanceof ToolbarActionBar))) {
                    if (mPreparedPanel.menu == null || mPreparedPanel.refreshMenuContent) {
                        if (mPreparedPanel.menu == null) {
                            final boolean initializePanelMenu = this.initializePanelMenu(mPreparedPanel);
                            b = false;
                            if (!initializePanelMenu) {
                                return b;
                            }
                            final MenuBuilder menu = mPreparedPanel.menu;
                            b = false;
                            if (menu == null) {
                                return b;
                            }
                        }
                        if (b2 && this.mDecorContentParent != null) {
                            if (this.mActionMenuPresenterCallback == null) {
                                this.mActionMenuPresenterCallback = new ActionMenuPresenterCallback();
                            }
                            this.mDecorContentParent.setMenu((Menu)mPreparedPanel.menu, this.mActionMenuPresenterCallback);
                        }
                        mPreparedPanel.menu.stopDispatchingItemsChanged();
                        if (!windowCallback.onCreatePanelMenu(mPreparedPanel.featureId, (Menu)mPreparedPanel.menu)) {
                            mPreparedPanel.setMenu(null);
                            b = false;
                            if (!b2) {
                                return b;
                            }
                            final DecorContentParent mDecorContentParent = this.mDecorContentParent;
                            b = false;
                            if (mDecorContentParent != null) {
                                this.mDecorContentParent.setMenu(null, this.mActionMenuPresenterCallback);
                                b = false;
                                return b;
                            }
                            return b;
                        }
                        else {
                            mPreparedPanel.refreshMenuContent = false;
                        }
                    }
                    mPreparedPanel.menu.stopDispatchingItemsChanged();
                    if (mPreparedPanel.frozenActionViewState != null) {
                        mPreparedPanel.menu.restoreActionViewStates(mPreparedPanel.frozenActionViewState);
                        mPreparedPanel.frozenActionViewState = null;
                    }
                    if (!windowCallback.onPreparePanel(0, mPreparedPanel.createdPanelView, (Menu)mPreparedPanel.menu)) {
                        if (b2 && this.mDecorContentParent != null) {
                            this.mDecorContentParent.setMenu(null, this.mActionMenuPresenterCallback);
                        }
                        mPreparedPanel.menu.startDispatchingItemsChanged();
                        b = false;
                        return b;
                    }
                    int deviceId;
                    if (keyEvent != null) {
                        deviceId = keyEvent.getDeviceId();
                    }
                    else {
                        deviceId = -1;
                    }
                    mPreparedPanel.qwertyMode = (KeyCharacterMap.load(deviceId).getKeyboardType() != 1);
                    mPreparedPanel.menu.setQwertyMode(mPreparedPanel.qwertyMode);
                    mPreparedPanel.menu.startDispatchingItemsChanged();
                }
                mPreparedPanel.isPrepared = true;
                mPreparedPanel.isHandled = false;
                this.mPreparedPanel = mPreparedPanel;
                b = true;
            }
        }
        return b;
    }
    
    private void reopenMenu(final MenuBuilder menuBuilder, final boolean b) {
        if (this.mDecorContentParent != null && this.mDecorContentParent.canShowOverflowMenu() && (!ViewConfigurationCompat.hasPermanentMenuKey(ViewConfiguration.get(this.mContext)) || this.mDecorContentParent.isOverflowMenuShowPending())) {
            final Window$Callback windowCallback = this.getWindowCallback();
            if (!this.mDecorContentParent.isOverflowMenuShowing() || !b) {
                if (windowCallback != null && !this.isDestroyed()) {
                    if (this.mInvalidatePanelMenuPosted && (0x1 & this.mInvalidatePanelMenuFeatures) != 0x0) {
                        this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
                        this.mInvalidatePanelMenuRunnable.run();
                    }
                    final PanelFeatureState panelState = this.getPanelState(0, true);
                    if (panelState.menu != null && !panelState.refreshMenuContent && windowCallback.onPreparePanel(0, panelState.createdPanelView, (Menu)panelState.menu)) {
                        windowCallback.onMenuOpened(108, (Menu)panelState.menu);
                        this.mDecorContentParent.showOverflowMenu();
                    }
                }
            }
            else {
                this.mDecorContentParent.hideOverflowMenu();
                if (!this.isDestroyed()) {
                    windowCallback.onPanelClosed(108, (Menu)this.getPanelState(0, true).menu);
                }
            }
        }
        else {
            final PanelFeatureState panelState2 = this.getPanelState(0, true);
            panelState2.refreshDecorView = true;
            this.closePanel(panelState2, false);
            this.openPanel(panelState2, null);
        }
    }
    
    private int sanitizeWindowFeatureId(int n) {
        if (n == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            n = 108;
        }
        else if (n == 9) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            n = 109;
        }
        return n;
    }
    
    private boolean shouldInheritContext(ViewParent parent) {
        boolean b;
        if (parent == null) {
            b = false;
        }
        else {
            final View decorView = this.mWindow.getDecorView();
            while (parent != null) {
                if (parent == decorView || !(parent instanceof View) || ViewCompat.isAttachedToWindow((View)parent)) {
                    b = false;
                    return b;
                }
                parent = parent.getParent();
            }
            b = true;
        }
        return b;
    }
    
    private void throwFeatureRequestIfSubDecorInstalled() {
        if (this.mSubDecorInstalled) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }
    
    @Override
    public void addContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.ensureSubDecor();
        ((ViewGroup)this.mSubDecor.findViewById(16908290)).addView(view, viewGroup$LayoutParams);
        this.mOriginalWindowCallback.onContentChanged();
    }
    
    View callActivityOnCreateView(final View view, final String s, final Context context, final AttributeSet set) {
        if (!(this.mOriginalWindowCallback instanceof LayoutInflater$Factory)) {
            return null;
        }
        View onCreateView = ((LayoutInflater$Factory)this.mOriginalWindowCallback).onCreateView(s, context, set);
        if (onCreateView == null) {
            return null;
        }
        return onCreateView;
        onCreateView = null;
        return onCreateView;
    }
    
    void callOnPanelClosed(final int n, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && n >= 0 && n < this.mPanels.length) {
                panelFeatureState = this.mPanels[n];
            }
            if (panelFeatureState != null) {
                menu = (Menu)panelFeatureState.menu;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.isOpen) && !this.isDestroyed()) {
            this.mOriginalWindowCallback.onPanelClosed(n, menu);
        }
    }
    
    void checkCloseActionMenu(final MenuBuilder menuBuilder) {
        if (!this.mClosingActionMenu) {
            this.mClosingActionMenu = true;
            this.mDecorContentParent.dismissPopups();
            final Window$Callback windowCallback = this.getWindowCallback();
            if (windowCallback != null && !this.isDestroyed()) {
                windowCallback.onPanelClosed(108, (Menu)menuBuilder);
            }
            this.mClosingActionMenu = false;
        }
    }
    
    void closePanel(final int n) {
        this.closePanel(this.getPanelState(n, true), true);
    }
    
    void closePanel(final PanelFeatureState panelFeatureState, final boolean b) {
        if (b && panelFeatureState.featureId == 0 && this.mDecorContentParent != null && this.mDecorContentParent.isOverflowMenuShowing()) {
            this.checkCloseActionMenu(panelFeatureState.menu);
        }
        else {
            final WindowManager windowManager = (WindowManager)this.mContext.getSystemService("window");
            if (windowManager != null && panelFeatureState.isOpen && panelFeatureState.decorView != null) {
                windowManager.removeView((View)panelFeatureState.decorView);
                if (b) {
                    this.callOnPanelClosed(panelFeatureState.featureId, panelFeatureState, null);
                }
            }
            panelFeatureState.isPrepared = false;
            panelFeatureState.isHandled = false;
            panelFeatureState.isOpen = false;
            panelFeatureState.shownPanelView = null;
            panelFeatureState.refreshDecorView = true;
            if (this.mPreparedPanel == panelFeatureState) {
                this.mPreparedPanel = null;
            }
        }
    }
    
    @Override
    public View createView(final View view, final String s, @NonNull final Context context, @NonNull final AttributeSet set) {
        final boolean b = Build$VERSION.SDK_INT < 21;
        if (this.mAppCompatViewInflater == null) {
            this.mAppCompatViewInflater = new AppCompatViewInflater();
        }
        return this.mAppCompatViewInflater.createView(view, s, context, set, b && this.shouldInheritContext((ViewParent)view), b, true, VectorEnabledTintResources.shouldBeUsed());
    }
    
    void dismissPopups() {
        if (this.mDecorContentParent != null) {
            this.mDecorContentParent.dismissPopups();
        }
        Label_0060: {
            if (this.mActionModePopup == null) {
                break Label_0060;
            }
            this.mWindow.getDecorView().removeCallbacks(this.mShowActionModePopup);
            while (true) {
                if (!this.mActionModePopup.isShowing()) {
                    break Label_0055;
                }
                try {
                    this.mActionModePopup.dismiss();
                    this.mActionModePopup = null;
                    this.endOnGoingFadeAnimation();
                    final PanelFeatureState panelState = this.getPanelState(0, false);
                    if (panelState != null && panelState.menu != null) {
                        panelState.menu.close();
                    }
                }
                catch (IllegalArgumentException ex) {
                    continue;
                }
                break;
            }
        }
    }
    
    @Override
    boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        boolean b = true;
        if (keyEvent.getKeyCode() != 82 || !this.mOriginalWindowCallback.dispatchKeyEvent(keyEvent)) {
            final int keyCode = keyEvent.getKeyCode();
            if (keyEvent.getAction() == 0 && b) {
                b = this.onKeyDown(keyCode, keyEvent);
            }
            else {
                b = this.onKeyUp(keyCode, keyEvent);
            }
        }
        return b;
    }
    
    void doInvalidatePanelMenu(final int n) {
        final PanelFeatureState panelState = this.getPanelState(n, true);
        if (panelState.menu != null) {
            final Bundle frozenActionViewState = new Bundle();
            panelState.menu.saveActionViewStates(frozenActionViewState);
            if (frozenActionViewState.size() > 0) {
                panelState.frozenActionViewState = frozenActionViewState;
            }
            panelState.menu.stopDispatchingItemsChanged();
            panelState.menu.clear();
        }
        panelState.refreshMenuContent = true;
        panelState.refreshDecorView = true;
        if ((n == 108 || n == 0) && this.mDecorContentParent != null) {
            final PanelFeatureState panelState2 = this.getPanelState(0, false);
            if (panelState2 != null) {
                panelState2.isPrepared = false;
                this.preparePanel(panelState2, null);
            }
        }
    }
    
    void endOnGoingFadeAnimation() {
        if (this.mFadeAnim != null) {
            this.mFadeAnim.cancel();
        }
    }
    
    PanelFeatureState findMenuPanel(final Menu menu) {
        final PanelFeatureState[] mPanels = this.mPanels;
        if (mPanels != null) {
            final int length = mPanels.length;
        }
        else {
            final int length = 0;
        }
        for (final PanelFeatureState panelFeatureState : mPanels) {
            if (panelFeatureState != null && panelFeatureState.menu == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }
    
    @Nullable
    @Override
    public View findViewById(@IdRes final int n) {
        this.ensureSubDecor();
        return this.mWindow.findViewById(n);
    }
    
    protected PanelFeatureState getPanelState(final int n, final boolean b) {
        PanelFeatureState[] mPanels = this.mPanels;
        if (mPanels == null || mPanels.length <= n) {
            final PanelFeatureState[] mPanels2 = new PanelFeatureState[n + 1];
            if (mPanels != null) {
                System.arraycopy(mPanels, 0, mPanels2, 0, mPanels.length);
            }
            mPanels = mPanels2;
            this.mPanels = mPanels2;
        }
        PanelFeatureState panelFeatureState = mPanels[n];
        if (panelFeatureState == null) {
            panelFeatureState = new PanelFeatureState(n);
            mPanels[n] = panelFeatureState;
        }
        return panelFeatureState;
    }
    
    ViewGroup getSubDecor() {
        return this.mSubDecor;
    }
    
    @Override
    public boolean hasWindowFeature(final int n) {
        final int sanitizeWindowFeatureId = this.sanitizeWindowFeatureId(n);
        boolean b = false;
        switch (sanitizeWindowFeatureId) {
            default: {
                b = this.mWindow.hasFeature(sanitizeWindowFeatureId);
                break;
            }
            case 108: {
                b = this.mHasActionBar;
                break;
            }
            case 109: {
                b = this.mOverlayActionBar;
                break;
            }
            case 10: {
                b = this.mOverlayActionMode;
                break;
            }
            case 2: {
                b = this.mFeatureProgress;
                break;
            }
            case 5: {
                b = this.mFeatureIndeterminateProgress;
                break;
            }
            case 1: {
                b = this.mWindowNoTitle;
                break;
            }
        }
        return b;
    }
    
    public void initWindowDecorActionBar() {
        this.ensureSubDecor();
        if (this.mHasActionBar && this.mActionBar == null) {
            if (this.mOriginalWindowCallback instanceof Activity) {
                this.mActionBar = new WindowDecorActionBar((Activity)this.mOriginalWindowCallback, this.mOverlayActionBar);
            }
            else if (this.mOriginalWindowCallback instanceof Dialog) {
                this.mActionBar = new WindowDecorActionBar((Dialog)this.mOriginalWindowCallback);
            }
            if (this.mActionBar != null) {
                this.mActionBar.setDefaultDisplayHomeAsUpEnabled(this.mEnableDefaultActionBarUp);
            }
        }
    }
    
    @Override
    public void installViewFactory() {
        final LayoutInflater from = LayoutInflater.from(this.mContext);
        if (from.getFactory() == null) {
            LayoutInflaterCompat.setFactory(from, this);
        }
        else if (!(LayoutInflaterCompat.getFactory(from) instanceof AppCompatDelegateImplV9)) {
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }
    
    @Override
    public void invalidateOptionsMenu() {
        final ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar == null || !supportActionBar.invalidateOptionsMenu()) {
            this.invalidatePanelMenu(0);
        }
    }
    
    boolean onBackPressed() {
        boolean b = true;
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        else {
            final ActionBar supportActionBar = this.getSupportActionBar();
            if (supportActionBar == null || !supportActionBar.collapseActionView()) {
                b = false;
            }
        }
        return b;
    }
    
    @Override
    public void onConfigurationChanged(final Configuration configuration) {
        if (this.mHasActionBar && this.mSubDecorInstalled) {
            final ActionBar supportActionBar = this.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.onConfigurationChanged(configuration);
            }
        }
        AppCompatDrawableManager.get().onConfigurationChanged(this.mContext);
        this.applyDayNight();
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        if (this.mOriginalWindowCallback instanceof Activity && NavUtils.getParentActivityName((Activity)this.mOriginalWindowCallback) != null) {
            final ActionBar peekSupportActionBar = this.peekSupportActionBar();
            if (peekSupportActionBar == null) {
                this.mEnableDefaultActionBarUp = true;
            }
            else {
                peekSupportActionBar.setDefaultDisplayHomeAsUpEnabled(true);
            }
        }
    }
    
    @Override
    public final View onCreateView(final View view, final String s, final Context context, final AttributeSet set) {
        View view2 = this.callActivityOnCreateView(view, s, context, set);
        if (view2 == null) {
            view2 = this.createView(view, s, context, set);
        }
        return view2;
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mActionBar != null) {
            this.mActionBar.onDestroy();
        }
    }
    
    boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        boolean mLongPressBackDown = true;
        switch (n) {
            case 82: {
                this.onKeyDownPanel(0, keyEvent);
                return mLongPressBackDown;
            }
            case 4: {
                if ((0x80 & keyEvent.getFlags()) == 0x0) {
                    mLongPressBackDown = false;
                }
                this.mLongPressBackDown = mLongPressBackDown;
                break;
            }
        }
        if (Build$VERSION.SDK_INT < 11) {
            this.onKeyShortcut(n, keyEvent);
        }
        mLongPressBackDown = false;
        return mLongPressBackDown;
    }
    
    @Override
    boolean onKeyShortcut(final int n, final KeyEvent keyEvent) {
        int isHandled = 1;
        final ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar == null || !supportActionBar.onKeyShortcut(n, keyEvent)) {
            if (this.mPreparedPanel != null && this.performPanelShortcut(this.mPreparedPanel, keyEvent.getKeyCode(), keyEvent, isHandled)) {
                if (this.mPreparedPanel != null) {
                    this.mPreparedPanel.isHandled = (isHandled != 0);
                }
            }
            else {
                if (this.mPreparedPanel == null) {
                    final PanelFeatureState panelState = this.getPanelState(0, isHandled != 0);
                    this.preparePanel(panelState, keyEvent);
                    final boolean performPanelShortcut = this.performPanelShortcut(panelState, keyEvent.getKeyCode(), keyEvent, isHandled);
                    panelState.isPrepared = false;
                    if (performPanelShortcut) {
                        return isHandled != 0;
                    }
                }
                isHandled = 0;
            }
        }
        return isHandled != 0;
    }
    
    boolean onKeyUp(final int n, final KeyEvent keyEvent) {
        boolean b = true;
        switch (n) {
            case 82: {
                this.onKeyUpPanel(0, keyEvent);
                return b;
            }
            case 4: {
                final boolean mLongPressBackDown = this.mLongPressBackDown;
                this.mLongPressBackDown = false;
                final PanelFeatureState panelState = this.getPanelState(0, false);
                if (panelState != null && panelState.isOpen) {
                    if (!mLongPressBackDown) {
                        this.closePanel(panelState, b);
                        return b;
                    }
                    return b;
                }
                else {
                    if (this.onBackPressed()) {
                        return b;
                    }
                    break;
                }
                break;
            }
        }
        b = false;
        return b;
    }
    
    @Override
    public boolean onMenuItemSelected(final MenuBuilder menuBuilder, final MenuItem menuItem) {
        final Window$Callback windowCallback = this.getWindowCallback();
        if (windowCallback == null || this.isDestroyed()) {
            return false;
        }
        final PanelFeatureState menuPanel = this.findMenuPanel((Menu)menuBuilder.getRootMenu());
        if (menuPanel == null) {
            return false;
        }
        return windowCallback.onMenuItemSelected(menuPanel.featureId, menuItem);
        onMenuItemSelected = false;
        return onMenuItemSelected;
    }
    
    @Override
    public void onMenuModeChange(final MenuBuilder menuBuilder) {
        this.reopenMenu(menuBuilder, true);
    }
    
    @Override
    boolean onMenuOpened(final int n, final Menu menu) {
        boolean b = true;
        if (n == 108) {
            final ActionBar supportActionBar = this.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.dispatchMenuVisibilityChanged(b);
            }
        }
        else {
            b = false;
        }
        return b;
    }
    
    @Override
    void onPanelClosed(final int n, final Menu menu) {
        if (n == 108) {
            final ActionBar supportActionBar = this.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.dispatchMenuVisibilityChanged(false);
            }
        }
        else if (n == 0) {
            final PanelFeatureState panelState = this.getPanelState(n, true);
            if (panelState.isOpen) {
                this.closePanel(panelState, false);
            }
        }
    }
    
    @Override
    public void onPostCreate(final Bundle bundle) {
        this.ensureSubDecor();
    }
    
    @Override
    public void onPostResume() {
        final ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(true);
        }
    }
    
    @Override
    public void onStop() {
        final ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(false);
        }
    }
    
    void onSubDecorInstalled(final ViewGroup viewGroup) {
    }
    
    @Override
    void onTitleChanged(final CharSequence text) {
        if (this.mDecorContentParent != null) {
            this.mDecorContentParent.setWindowTitle(text);
        }
        else if (this.peekSupportActionBar() != null) {
            this.peekSupportActionBar().setWindowTitle(text);
        }
        else if (this.mTitleView != null) {
            this.mTitleView.setText(text);
        }
    }
    
    @Override
    public boolean requestWindowFeature(final int n) {
        final int sanitizeWindowFeatureId = this.sanitizeWindowFeatureId(n);
        Label_0023: {
            if (!this.mWindowNoTitle) {
                break Label_0023;
            }
            final boolean requestFeature = false;
            if (sanitizeWindowFeatureId != 108) {
                break Label_0023;
            }
            return requestFeature;
        }
        if (this.mHasActionBar && sanitizeWindowFeatureId == 1) {
            this.mHasActionBar = false;
        }
        switch (sanitizeWindowFeatureId) {
            default: {
                return this.mWindow.requestFeature(sanitizeWindowFeatureId);
            }
            case 108: {
                this.throwFeatureRequestIfSubDecorInstalled();
                this.mHasActionBar = true;
                return true;
            }
            case 109: {
                this.throwFeatureRequestIfSubDecorInstalled();
                this.mOverlayActionBar = true;
                return true;
            }
            case 10: {
                this.throwFeatureRequestIfSubDecorInstalled();
                this.mOverlayActionMode = true;
                return true;
            }
            case 2: {
                this.throwFeatureRequestIfSubDecorInstalled();
                this.mFeatureProgress = true;
                return true;
            }
            case 5: {
                this.throwFeatureRequestIfSubDecorInstalled();
                this.mFeatureIndeterminateProgress = true;
                return true;
            }
            case 1: {
                this.throwFeatureRequestIfSubDecorInstalled();
                this.mWindowNoTitle = true;
                return true;
            }
        }
    }
    
    @Override
    public void setContentView(final int n) {
        this.ensureSubDecor();
        final ViewGroup viewGroup = (ViewGroup)this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.mContext).inflate(n, viewGroup);
        this.mOriginalWindowCallback.onContentChanged();
    }
    
    @Override
    public void setContentView(final View view) {
        this.ensureSubDecor();
        final ViewGroup viewGroup = (ViewGroup)this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.mOriginalWindowCallback.onContentChanged();
    }
    
    @Override
    public void setContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.ensureSubDecor();
        final ViewGroup viewGroup = (ViewGroup)this.mSubDecor.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, viewGroup$LayoutParams);
        this.mOriginalWindowCallback.onContentChanged();
    }
    
    @Override
    public void setSupportActionBar(final Toolbar toolbar) {
        if (this.mOriginalWindowCallback instanceof Activity) {
            final ActionBar supportActionBar = this.getSupportActionBar();
            if (supportActionBar instanceof WindowDecorActionBar) {
                throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
            }
            this.mMenuInflater = null;
            if (supportActionBar != null) {
                supportActionBar.onDestroy();
            }
            if (toolbar != null) {
                final ToolbarActionBar mActionBar = new ToolbarActionBar(toolbar, ((Activity)this.mContext).getTitle(), this.mAppCompatWindowCallback);
                this.mActionBar = mActionBar;
                this.mWindow.setCallback(mActionBar.getWrappedWindowCallback());
            }
            else {
                this.mActionBar = null;
                this.mWindow.setCallback(this.mAppCompatWindowCallback);
            }
            this.invalidateOptionsMenu();
        }
    }
    
    final boolean shouldAnimateActionModeView() {
        return this.mSubDecorInstalled && this.mSubDecor != null && ViewCompat.isLaidOut((View)this.mSubDecor);
    }
    
    @Override
    public ActionMode startSupportActionMode(@NonNull final ActionMode.Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        final ActionModeCallbackWrapperV9 actionModeCallbackWrapperV9 = new ActionModeCallbackWrapperV9(callback);
        final ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar != null) {
            this.mActionMode = supportActionBar.startActionMode(actionModeCallbackWrapperV9);
            if (this.mActionMode != null && this.mAppCompatCallback != null) {
                this.mAppCompatCallback.onSupportActionModeStarted(this.mActionMode);
            }
        }
        if (this.mActionMode == null) {
            this.mActionMode = this.startSupportActionModeFromWindow(actionModeCallbackWrapperV9);
        }
        return this.mActionMode;
    }
    
    @Override
    ActionMode startSupportActionModeFromWindow(@NonNull ActionMode.Callback callback) {
        this.endOnGoingFadeAnimation();
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        if (!(callback instanceof ActionModeCallbackWrapperV9)) {
            callback = new ActionModeCallbackWrapperV9(callback);
        }
        final AppCompatCallback mAppCompatCallback = this.mAppCompatCallback;
        ActionMode onWindowStartingSupportActionMode = null;
        while (true) {
            if (mAppCompatCallback == null) {
                break Label_0074;
            }
            final boolean destroyed = this.isDestroyed();
            onWindowStartingSupportActionMode = null;
            if (destroyed) {
                break Label_0074;
            }
            try {
                onWindowStartingSupportActionMode = this.mAppCompatCallback.onWindowStartingSupportActionMode(callback);
                if (onWindowStartingSupportActionMode != null) {
                    this.mActionMode = onWindowStartingSupportActionMode;
                }
                else {
                    if (this.mActionModeView == null) {
                        if (this.mIsFloating) {
                            final TypedValue typedValue = new TypedValue();
                            final Resources$Theme theme = this.mContext.getTheme();
                            theme.resolveAttribute(R.attr.actionBarTheme, typedValue, true);
                            Object mContext;
                            if (typedValue.resourceId != 0) {
                                final Resources$Theme theme2 = this.mContext.getResources().newTheme();
                                theme2.setTo(theme);
                                theme2.applyStyle(typedValue.resourceId, true);
                                mContext = new ContextThemeWrapper(this.mContext, 0);
                                ((Context)mContext).getTheme().setTo(theme2);
                            }
                            else {
                                mContext = this.mContext;
                            }
                            this.mActionModeView = new ActionBarContextView((Context)mContext);
                            PopupWindowCompat.setWindowLayoutType(this.mActionModePopup = new PopupWindow((Context)mContext, (AttributeSet)null, R.attr.actionModePopupWindowStyle), 2);
                            this.mActionModePopup.setContentView((View)this.mActionModeView);
                            this.mActionModePopup.setWidth(-1);
                            ((Context)mContext).getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
                            this.mActionModeView.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, ((Context)mContext).getResources().getDisplayMetrics()));
                            this.mActionModePopup.setHeight(-2);
                            this.mShowActionModePopup = new Runnable() {
                                @Override
                                public void run() {
                                    AppCompatDelegateImplV9.this.mActionModePopup.showAtLocation((View)AppCompatDelegateImplV9.this.mActionModeView, 55, 0, 0);
                                    AppCompatDelegateImplV9.this.endOnGoingFadeAnimation();
                                    if (AppCompatDelegateImplV9.this.shouldAnimateActionModeView()) {
                                        ViewCompat.setAlpha((View)AppCompatDelegateImplV9.this.mActionModeView, 0.0f);
                                        (AppCompatDelegateImplV9.this.mFadeAnim = ViewCompat.animate((View)AppCompatDelegateImplV9.this.mActionModeView).alpha(1.0f)).setListener(new ViewPropertyAnimatorListenerAdapter() {
                                            @Override
                                            public void onAnimationEnd(final View view) {
                                                ViewCompat.setAlpha((View)AppCompatDelegateImplV9.this.mActionModeView, 1.0f);
                                                AppCompatDelegateImplV9.this.mFadeAnim.setListener(null);
                                                AppCompatDelegateImplV9.this.mFadeAnim = null;
                                            }
                                            
                                            @Override
                                            public void onAnimationStart(final View view) {
                                                AppCompatDelegateImplV9.this.mActionModeView.setVisibility(0);
                                            }
                                        });
                                    }
                                    else {
                                        ViewCompat.setAlpha((View)AppCompatDelegateImplV9.this.mActionModeView, 1.0f);
                                        AppCompatDelegateImplV9.this.mActionModeView.setVisibility(0);
                                    }
                                }
                            };
                        }
                        else {
                            final ViewStubCompat viewStubCompat = (ViewStubCompat)this.mSubDecor.findViewById(R.id.action_mode_bar_stub);
                            if (viewStubCompat != null) {
                                viewStubCompat.setLayoutInflater(LayoutInflater.from(this.getActionBarThemedContext()));
                                this.mActionModeView = (ActionBarContextView)viewStubCompat.inflate();
                            }
                        }
                    }
                    if (this.mActionModeView != null) {
                        this.endOnGoingFadeAnimation();
                        this.mActionModeView.killMode();
                        final StandaloneActionMode mActionMode = new StandaloneActionMode(this.mActionModeView.getContext(), this.mActionModeView, callback, this.mActionModePopup == null);
                        if (callback.onCreateActionMode(mActionMode, mActionMode.getMenu())) {
                            mActionMode.invalidate();
                            this.mActionModeView.initForMode(mActionMode);
                            this.mActionMode = mActionMode;
                            if (this.shouldAnimateActionModeView()) {
                                ViewCompat.setAlpha((View)this.mActionModeView, 0.0f);
                                (this.mFadeAnim = ViewCompat.animate((View)this.mActionModeView).alpha(1.0f)).setListener(new ViewPropertyAnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(final View view) {
                                        ViewCompat.setAlpha((View)AppCompatDelegateImplV9.this.mActionModeView, 1.0f);
                                        AppCompatDelegateImplV9.this.mFadeAnim.setListener(null);
                                        AppCompatDelegateImplV9.this.mFadeAnim = null;
                                    }
                                    
                                    @Override
                                    public void onAnimationStart(final View view) {
                                        AppCompatDelegateImplV9.this.mActionModeView.setVisibility(0);
                                        AppCompatDelegateImplV9.this.mActionModeView.sendAccessibilityEvent(32);
                                        if (AppCompatDelegateImplV9.this.mActionModeView.getParent() != null) {
                                            ViewCompat.requestApplyInsets((View)AppCompatDelegateImplV9.this.mActionModeView.getParent());
                                        }
                                    }
                                });
                            }
                            else {
                                ViewCompat.setAlpha((View)this.mActionModeView, 1.0f);
                                this.mActionModeView.setVisibility(0);
                                this.mActionModeView.sendAccessibilityEvent(32);
                                if (this.mActionModeView.getParent() != null) {
                                    ViewCompat.requestApplyInsets((View)this.mActionModeView.getParent());
                                }
                            }
                            if (this.mActionModePopup != null) {
                                this.mWindow.getDecorView().post(this.mShowActionModePopup);
                            }
                        }
                        else {
                            this.mActionMode = null;
                        }
                    }
                }
                if (this.mActionMode != null && this.mAppCompatCallback != null) {
                    this.mAppCompatCallback.onSupportActionModeStarted(this.mActionMode);
                }
                return this.mActionMode;
            }
            catch (AbstractMethodError abstractMethodError) {
                onWindowStartingSupportActionMode = null;
                continue;
            }
            break;
        }
    }
    
    int updateStatusGuard(int n) {
        final ActionBarContextView mActionModeView = this.mActionModeView;
        int n2 = 0;
        if (mActionModeView != null) {
            final boolean b = this.mActionModeView.getLayoutParams() instanceof ViewGroup$MarginLayoutParams;
            n2 = 0;
            if (b) {
                final ViewGroup$MarginLayoutParams layoutParams = (ViewGroup$MarginLayoutParams)this.mActionModeView.getLayoutParams();
                int n4;
                if (this.mActionModeView.isShown()) {
                    if (this.mTempRect1 == null) {
                        this.mTempRect1 = new Rect();
                        this.mTempRect2 = new Rect();
                    }
                    final Rect mTempRect1 = this.mTempRect1;
                    final Rect mTempRect2 = this.mTempRect2;
                    mTempRect1.set(0, n, 0, 0);
                    ViewUtils.computeFitSystemWindows((View)this.mSubDecor, mTempRect1, mTempRect2);
                    int n3;
                    if (mTempRect2.top == 0) {
                        n3 = n;
                    }
                    else {
                        n3 = 0;
                    }
                    final int topMargin = layoutParams.topMargin;
                    n4 = 0;
                    if (topMargin != n3) {
                        n4 = 1;
                        layoutParams.topMargin = n;
                        if (this.mStatusGuard == null) {
                            (this.mStatusGuard = new View(this.mContext)).setBackgroundColor(this.mContext.getResources().getColor(R.color.abc_input_method_navigation_guard));
                            this.mSubDecor.addView(this.mStatusGuard, -1, new ViewGroup$LayoutParams(-1, n));
                        }
                        else {
                            final ViewGroup$LayoutParams layoutParams2 = this.mStatusGuard.getLayoutParams();
                            if (layoutParams2.height != n) {
                                layoutParams2.height = n;
                                this.mStatusGuard.setLayoutParams(layoutParams2);
                            }
                        }
                    }
                    if (this.mStatusGuard != null) {
                        n2 = 1;
                    }
                    else {
                        n2 = 0;
                    }
                    if (!this.mOverlayActionMode && n2 != 0) {
                        n = 0;
                    }
                }
                else {
                    final int topMargin2 = layoutParams.topMargin;
                    n4 = 0;
                    n2 = 0;
                    if (topMargin2 != 0) {
                        n4 = 1;
                        layoutParams.topMargin = 0;
                        n2 = 0;
                    }
                }
                if (n4 != 0) {
                    this.mActionModeView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
                }
            }
        }
        if (this.mStatusGuard != null) {
            final View mStatusGuard = this.mStatusGuard;
            int visibility = 0;
            if (n2 == 0) {
                visibility = 8;
            }
            mStatusGuard.setVisibility(visibility);
        }
        return n;
    }
    
    private final class ActionMenuPresenterCallback implements MenuPresenter.Callback
    {
        @Override
        public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
            AppCompatDelegateImplV9.this.checkCloseActionMenu(menuBuilder);
        }
        
        @Override
        public boolean onOpenSubMenu(final MenuBuilder menuBuilder) {
            final Window$Callback windowCallback = AppCompatDelegateImplV9.this.getWindowCallback();
            if (windowCallback != null) {
                windowCallback.onMenuOpened(108, (Menu)menuBuilder);
            }
            return true;
        }
    }
    
    class ActionModeCallbackWrapperV9 implements ActionMode.Callback
    {
        private ActionMode.Callback mWrapped;
        
        public ActionModeCallbackWrapperV9(final ActionMode.Callback mWrapped) {
            this.mWrapped = mWrapped;
        }
        
        @Override
        public boolean onActionItemClicked(final ActionMode actionMode, final MenuItem menuItem) {
            return this.mWrapped.onActionItemClicked(actionMode, menuItem);
        }
        
        @Override
        public boolean onCreateActionMode(final ActionMode actionMode, final Menu menu) {
            return this.mWrapped.onCreateActionMode(actionMode, menu);
        }
        
        @Override
        public void onDestroyActionMode(final ActionMode actionMode) {
            this.mWrapped.onDestroyActionMode(actionMode);
            if (AppCompatDelegateImplV9.this.mActionModePopup != null) {
                AppCompatDelegateImplV9.this.mWindow.getDecorView().removeCallbacks(AppCompatDelegateImplV9.this.mShowActionModePopup);
            }
            if (AppCompatDelegateImplV9.this.mActionModeView != null) {
                AppCompatDelegateImplV9.this.endOnGoingFadeAnimation();
                (AppCompatDelegateImplV9.this.mFadeAnim = ViewCompat.animate((View)AppCompatDelegateImplV9.this.mActionModeView).alpha(0.0f)).setListener(new ViewPropertyAnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(final View view) {
                        AppCompatDelegateImplV9.this.mActionModeView.setVisibility(8);
                        if (AppCompatDelegateImplV9.this.mActionModePopup != null) {
                            AppCompatDelegateImplV9.this.mActionModePopup.dismiss();
                        }
                        else if (AppCompatDelegateImplV9.this.mActionModeView.getParent() instanceof View) {
                            ViewCompat.requestApplyInsets((View)AppCompatDelegateImplV9.this.mActionModeView.getParent());
                        }
                        AppCompatDelegateImplV9.this.mActionModeView.removeAllViews();
                        AppCompatDelegateImplV9.this.mFadeAnim.setListener(null);
                        AppCompatDelegateImplV9.this.mFadeAnim = null;
                    }
                });
            }
            if (AppCompatDelegateImplV9.this.mAppCompatCallback != null) {
                AppCompatDelegateImplV9.this.mAppCompatCallback.onSupportActionModeFinished(AppCompatDelegateImplV9.this.mActionMode);
            }
            AppCompatDelegateImplV9.this.mActionMode = null;
        }
        
        @Override
        public boolean onPrepareActionMode(final ActionMode actionMode, final Menu menu) {
            return this.mWrapped.onPrepareActionMode(actionMode, menu);
        }
    }
    
    private class ListMenuDecorView extends ContentFrameLayout
    {
        public ListMenuDecorView(final Context context) {
            super(context);
        }
        
        private boolean isOutOfBounds(final int n, final int n2) {
            return n < -5 || n2 < -5 || n > 5 + this.getWidth() || n2 > 5 + this.getHeight();
        }
        
        public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
            return AppCompatDelegateImplV9.this.dispatchKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }
        
        public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
            boolean onInterceptTouchEvent;
            if (motionEvent.getAction() == 0 && this.isOutOfBounds((int)motionEvent.getX(), (int)motionEvent.getY())) {
                AppCompatDelegateImplV9.this.closePanel(0);
                onInterceptTouchEvent = true;
            }
            else {
                onInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
            }
            return onInterceptTouchEvent;
        }
        
        public void setBackgroundResource(final int n) {
            this.setBackgroundDrawable(AppCompatResources.getDrawable(this.getContext(), n));
        }
    }
    
    protected static final class PanelFeatureState
    {
        int background;
        View createdPanelView;
        ViewGroup decorView;
        int featureId;
        Bundle frozenActionViewState;
        Bundle frozenMenuState;
        int gravity;
        boolean isHandled;
        boolean isOpen;
        boolean isPrepared;
        ListMenuPresenter listMenuPresenter;
        Context listPresenterContext;
        MenuBuilder menu;
        public boolean qwertyMode;
        boolean refreshDecorView;
        boolean refreshMenuContent;
        View shownPanelView;
        boolean wasLastOpen;
        int windowAnimations;
        int x;
        int y;
        
        PanelFeatureState(final int featureId) {
            this.featureId = featureId;
            this.refreshDecorView = false;
        }
        
        void applyFrozenState() {
            if (this.menu != null && this.frozenMenuState != null) {
                this.menu.restorePresenterStates(this.frozenMenuState);
                this.frozenMenuState = null;
            }
        }
        
        public void clearMenuPresenters() {
            if (this.menu != null) {
                this.menu.removeMenuPresenter(this.listMenuPresenter);
            }
            this.listMenuPresenter = null;
        }
        
        MenuView getListMenuView(final MenuPresenter.Callback callback) {
            MenuView menuView;
            if (this.menu == null) {
                menuView = null;
            }
            else {
                if (this.listMenuPresenter == null) {
                    (this.listMenuPresenter = new ListMenuPresenter(this.listPresenterContext, R.layout.abc_list_menu_item_layout)).setCallback(callback);
                    this.menu.addMenuPresenter(this.listMenuPresenter);
                }
                menuView = this.listMenuPresenter.getMenuView(this.decorView);
            }
            return menuView;
        }
        
        public boolean hasPanelItems() {
            boolean b = true;
            if (this.shownPanelView == null) {
                b = false;
            }
            else if (this.createdPanelView == null && this.listMenuPresenter.getAdapter().getCount() <= 0) {
                b = false;
            }
            return b;
        }
        
        void onRestoreInstanceState(final Parcelable parcelable) {
            final SavedState savedState = (SavedState)parcelable;
            this.featureId = savedState.featureId;
            this.wasLastOpen = savedState.isOpen;
            this.frozenMenuState = savedState.menuState;
            this.shownPanelView = null;
            this.decorView = null;
        }
        
        Parcelable onSaveInstanceState() {
            final SavedState savedState = new SavedState();
            savedState.featureId = this.featureId;
            savedState.isOpen = this.isOpen;
            if (this.menu != null) {
                savedState.menuState = new Bundle();
                this.menu.savePresenterStates(savedState.menuState);
            }
            return (Parcelable)savedState;
        }
        
        void setMenu(final MenuBuilder menu) {
            if (menu != this.menu) {
                if (this.menu != null) {
                    this.menu.removeMenuPresenter(this.listMenuPresenter);
                }
                this.menu = menu;
                if (menu != null && this.listMenuPresenter != null) {
                    menu.addMenuPresenter(this.listMenuPresenter);
                }
            }
        }
        
        void setStyle(final Context context) {
            final TypedValue typedValue = new TypedValue();
            final Resources$Theme theme = context.getResources().newTheme();
            theme.setTo(context.getTheme());
            theme.resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                theme.applyStyle(typedValue.resourceId, true);
            }
            theme.resolveAttribute(R.attr.panelMenuListTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                theme.applyStyle(typedValue.resourceId, true);
            }
            else {
                theme.applyStyle(R.style.Theme_AppCompat_CompactMenu, true);
            }
            final ContextThemeWrapper listPresenterContext = new ContextThemeWrapper(context, 0);
            ((Context)listPresenterContext).getTheme().setTo(theme);
            this.listPresenterContext = (Context)listPresenterContext;
            final TypedArray obtainStyledAttributes = ((Context)listPresenterContext).obtainStyledAttributes(R.styleable.AppCompatTheme);
            this.background = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTheme_panelBackground, 0);
            this.windowAnimations = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            obtainStyledAttributes.recycle();
        }
        
        private static class SavedState implements Parcelable
        {
            public static final Parcelable$Creator<SavedState> CREATOR;
            int featureId;
            boolean isOpen;
            Bundle menuState;
            
            static {
                CREATOR = ParcelableCompat.newCreator((ParcelableCompatCreatorCallbacks<SavedState>)new ParcelableCompatCreatorCallbacks<SavedState>() {
                    @Override
                    public SavedState createFromParcel(final Parcel parcel, final ClassLoader classLoader) {
                        return SavedState.readFromParcel(parcel, classLoader);
                    }
                    
                    @Override
                    public SavedState[] newArray(final int n) {
                        return new SavedState[n];
                    }
                });
            }
            
            static SavedState readFromParcel(final Parcel parcel, final ClassLoader classLoader) {
                boolean isOpen = true;
                final SavedState savedState = new SavedState();
                savedState.featureId = parcel.readInt();
                if (parcel.readInt() != (isOpen ? 1 : 0)) {
                    isOpen = false;
                }
                savedState.isOpen = isOpen;
                if (savedState.isOpen) {
                    savedState.menuState = parcel.readBundle(classLoader);
                }
                return savedState;
            }
            
            public int describeContents() {
                return 0;
            }
            
            public void writeToParcel(final Parcel parcel, final int n) {
                parcel.writeInt(this.featureId);
                int n2;
                if (this.isOpen) {
                    n2 = 1;
                }
                else {
                    n2 = 0;
                }
                parcel.writeInt(n2);
                if (this.isOpen) {
                    parcel.writeBundle(this.menuState);
                }
            }
        }
    }
    
    private final class PanelMenuPresenterCallback implements MenuPresenter.Callback
    {
        @Override
        public void onCloseMenu(MenuBuilder menuBuilder, final boolean b) {
            final Object rootMenu = menuBuilder.getRootMenu();
            boolean b2;
            if (rootMenu != menuBuilder) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            final AppCompatDelegateImplV9 this$0 = AppCompatDelegateImplV9.this;
            if (b2) {
                menuBuilder = (MenuBuilder)rootMenu;
            }
            final PanelFeatureState menuPanel = this$0.findMenuPanel((Menu)menuBuilder);
            if (menuPanel != null) {
                if (b2) {
                    AppCompatDelegateImplV9.this.callOnPanelClosed(menuPanel.featureId, menuPanel, (Menu)rootMenu);
                    AppCompatDelegateImplV9.this.closePanel(menuPanel, true);
                }
                else {
                    AppCompatDelegateImplV9.this.closePanel(menuPanel, b);
                }
            }
        }
        
        @Override
        public boolean onOpenSubMenu(final MenuBuilder menuBuilder) {
            if (menuBuilder == null && AppCompatDelegateImplV9.this.mHasActionBar) {
                final Window$Callback windowCallback = AppCompatDelegateImplV9.this.getWindowCallback();
                if (windowCallback != null && !AppCompatDelegateImplV9.this.isDestroyed()) {
                    windowCallback.onMenuOpened(108, (Menu)menuBuilder);
                }
            }
            return true;
        }
    }
}
