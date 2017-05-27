// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.View$MeasureSpec;
import android.support.v7.widget.ViewUtils;
import android.view.accessibility.AccessibilityEvent;
import android.view.MotionEvent;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.widget.ActionMenuPresenter;
import android.view.View$OnClickListener;
import android.view.ViewGroup$MarginLayoutParams;
import android.text.TextUtils;
import android.support.v7.widget.TintTypedArray;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.AbsActionBarView;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.menu.MenuPopupHelper;
import android.view.MenuItem;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.view.SupportMenuInflater;
import android.view.MenuInflater;
import android.view.Menu;
import java.lang.ref.WeakReference;
import android.support.v7.view.menu.MenuBuilder;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.SpinnerAdapter;
import android.view.ViewGroup$LayoutParams;
import android.view.LayoutInflater;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup;
import android.content.res.Configuration;
import android.view.ContextThemeWrapper;
import android.util.TypedValue;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.support.v7.view.ActionBarPolicy;
import android.support.v7.appcompat.R;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.os.Build$VERSION;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v7.widget.ScrollingTabContainerView;
import java.util.ArrayList;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.app.Dialog;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DecorToolbar;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.support.v7.widget.ActionBarContextView;
import android.content.Context;
import android.view.View;
import android.support.v7.widget.ActionBarContainer;
import android.app.Activity;
import android.view.animation.Interpolator;
import android.support.v7.widget.ActionBarOverlayLayout;

public class WindowDecorActionBar extends ActionBar implements ActionBarVisibilityCallback
{
    private static final boolean ALLOW_SHOW_HIDE_ANIMATIONS = false;
    private static final long FADE_IN_DURATION_MS = 200L;
    private static final long FADE_OUT_DURATION_MS = 100L;
    private static final int INVALID_POSITION = -1;
    private static final String TAG = "WindowDecorActionBar";
    private static final Interpolator sHideInterpolator;
    private static final Interpolator sShowInterpolator;
    ActionModeImpl mActionMode;
    private Activity mActivity;
    ActionBarContainer mContainerView;
    boolean mContentAnimations;
    View mContentView;
    Context mContext;
    ActionBarContextView mContextView;
    private int mCurWindowVisibility;
    ViewPropertyAnimatorCompatSet mCurrentShowAnim;
    DecorToolbar mDecorToolbar;
    ActionMode mDeferredDestroyActionMode;
    ActionMode.Callback mDeferredModeDestroyCallback;
    private Dialog mDialog;
    private boolean mDisplayHomeAsUpSet;
    private boolean mHasEmbeddedTabs;
    boolean mHiddenByApp;
    boolean mHiddenBySystem;
    final ViewPropertyAnimatorListener mHideListener;
    boolean mHideOnContentScroll;
    private boolean mLastMenuVisibility;
    private ArrayList<OnMenuVisibilityListener> mMenuVisibilityListeners;
    private boolean mNowShowing;
    ActionBarOverlayLayout mOverlayLayout;
    private int mSavedTabPosition;
    private TabImpl mSelectedTab;
    private boolean mShowHideAnimationEnabled;
    final ViewPropertyAnimatorListener mShowListener;
    private boolean mShowingForMode;
    ScrollingTabContainerView mTabScrollView;
    private ArrayList<TabImpl> mTabs;
    private Context mThemedContext;
    final ViewPropertyAnimatorUpdateListener mUpdateListener;
    
    static {
        boolean allow_SHOW_HIDE_ANIMATIONS = true;
        sHideInterpolator = (Interpolator)new AccelerateInterpolator();
        sShowInterpolator = (Interpolator)new DecelerateInterpolator();
        if (Build$VERSION.SDK_INT < 14) {
            allow_SHOW_HIDE_ANIMATIONS = false;
        }
    }
    
    public WindowDecorActionBar(final Activity mActivity, final boolean b) {
        this.mTabs = new ArrayList<TabImpl>();
        this.mSavedTabPosition = -1;
        this.mMenuVisibilityListeners = new ArrayList<OnMenuVisibilityListener>();
        this.mCurWindowVisibility = 0;
        this.mContentAnimations = true;
        this.mNowShowing = true;
        this.mHideListener = new ViewPropertyAnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(final View view) {
                if (WindowDecorActionBar.this.mContentAnimations && WindowDecorActionBar.this.mContentView != null) {
                    ViewCompat.setTranslationY(WindowDecorActionBar.this.mContentView, 0.0f);
                    ViewCompat.setTranslationY((View)WindowDecorActionBar.this.mContainerView, 0.0f);
                }
                WindowDecorActionBar.this.mContainerView.setVisibility(8);
                WindowDecorActionBar.this.mContainerView.setTransitioning(false);
                WindowDecorActionBar.this.mCurrentShowAnim = null;
                WindowDecorActionBar.this.completeDeferredDestroyActionMode();
                if (WindowDecorActionBar.this.mOverlayLayout != null) {
                    ViewCompat.requestApplyInsets((View)WindowDecorActionBar.this.mOverlayLayout);
                }
            }
        };
        this.mShowListener = new ViewPropertyAnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(final View view) {
                WindowDecorActionBar.this.mCurrentShowAnim = null;
                WindowDecorActionBar.this.mContainerView.requestLayout();
            }
        };
        this.mUpdateListener = new ViewPropertyAnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(final View view) {
                ((View)WindowDecorActionBar.this.mContainerView.getParent()).invalidate();
            }
        };
        this.mActivity = mActivity;
        final View decorView = mActivity.getWindow().getDecorView();
        this.init(decorView);
        if (!b) {
            this.mContentView = decorView.findViewById(16908290);
        }
    }
    
    public WindowDecorActionBar(final Dialog mDialog) {
        this.mTabs = new ArrayList<TabImpl>();
        this.mSavedTabPosition = -1;
        this.mMenuVisibilityListeners = new ArrayList<OnMenuVisibilityListener>();
        this.mCurWindowVisibility = 0;
        this.mContentAnimations = true;
        this.mNowShowing = true;
        this.mHideListener = new ViewPropertyAnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(final View view) {
                if (WindowDecorActionBar.this.mContentAnimations && WindowDecorActionBar.this.mContentView != null) {
                    ViewCompat.setTranslationY(WindowDecorActionBar.this.mContentView, 0.0f);
                    ViewCompat.setTranslationY((View)WindowDecorActionBar.this.mContainerView, 0.0f);
                }
                WindowDecorActionBar.this.mContainerView.setVisibility(8);
                WindowDecorActionBar.this.mContainerView.setTransitioning(false);
                WindowDecorActionBar.this.mCurrentShowAnim = null;
                WindowDecorActionBar.this.completeDeferredDestroyActionMode();
                if (WindowDecorActionBar.this.mOverlayLayout != null) {
                    ViewCompat.requestApplyInsets((View)WindowDecorActionBar.this.mOverlayLayout);
                }
            }
        };
        this.mShowListener = new ViewPropertyAnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(final View view) {
                WindowDecorActionBar.this.mCurrentShowAnim = null;
                WindowDecorActionBar.this.mContainerView.requestLayout();
            }
        };
        this.mUpdateListener = new ViewPropertyAnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(final View view) {
                ((View)WindowDecorActionBar.this.mContainerView.getParent()).invalidate();
            }
        };
        this.mDialog = mDialog;
        this.init(mDialog.getWindow().getDecorView());
    }
    
    public WindowDecorActionBar(final View view) {
        this.mTabs = new ArrayList<TabImpl>();
        this.mSavedTabPosition = -1;
        this.mMenuVisibilityListeners = new ArrayList<OnMenuVisibilityListener>();
        this.mCurWindowVisibility = 0;
        this.mContentAnimations = true;
        this.mNowShowing = true;
        this.mHideListener = new ViewPropertyAnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(final View view) {
                if (WindowDecorActionBar.this.mContentAnimations && WindowDecorActionBar.this.mContentView != null) {
                    ViewCompat.setTranslationY(WindowDecorActionBar.this.mContentView, 0.0f);
                    ViewCompat.setTranslationY((View)WindowDecorActionBar.this.mContainerView, 0.0f);
                }
                WindowDecorActionBar.this.mContainerView.setVisibility(8);
                WindowDecorActionBar.this.mContainerView.setTransitioning(false);
                WindowDecorActionBar.this.mCurrentShowAnim = null;
                WindowDecorActionBar.this.completeDeferredDestroyActionMode();
                if (WindowDecorActionBar.this.mOverlayLayout != null) {
                    ViewCompat.requestApplyInsets((View)WindowDecorActionBar.this.mOverlayLayout);
                }
            }
        };
        this.mShowListener = new ViewPropertyAnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(final View view) {
                WindowDecorActionBar.this.mCurrentShowAnim = null;
                WindowDecorActionBar.this.mContainerView.requestLayout();
            }
        };
        this.mUpdateListener = new ViewPropertyAnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(final View view) {
                ((View)WindowDecorActionBar.this.mContainerView.getParent()).invalidate();
            }
        };
        assert view.isInEditMode();
        this.init(view);
    }
    
    static boolean checkShowingFlags(final boolean b, final boolean b2, final boolean b3) {
        boolean b4 = true;
        if (!b3 && (b || b2)) {
            b4 = false;
        }
        return b4;
    }
    
    private void cleanupTabs() {
        if (this.mSelectedTab != null) {
            this.selectTab(null);
        }
        this.mTabs.clear();
        if (this.mTabScrollView != null) {
            this.mTabScrollView.removeAllTabs();
        }
        this.mSavedTabPosition = -1;
    }
    
    private void configureTab(final Tab tab, final int position) {
        final TabImpl tabImpl = (TabImpl)tab;
        if (tabImpl.getCallback() == null) {
            throw new IllegalStateException("Action Bar Tab must have a Callback");
        }
        tabImpl.setPosition(position);
        this.mTabs.add(position, tabImpl);
        for (int size = this.mTabs.size(), i = position + 1; i < size; ++i) {
            this.mTabs.get(i).setPosition(i);
        }
    }
    
    private void ensureTabsExist() {
        if (this.mTabScrollView == null) {
            final ScrollingTabContainerView mTabScrollView = new ScrollingTabContainerView(this.mContext);
            if (this.mHasEmbeddedTabs) {
                mTabScrollView.setVisibility(0);
                this.mDecorToolbar.setEmbeddedTabView(mTabScrollView);
            }
            else {
                if (this.getNavigationMode() == 2) {
                    mTabScrollView.setVisibility(0);
                    if (this.mOverlayLayout != null) {
                        ViewCompat.requestApplyInsets((View)this.mOverlayLayout);
                    }
                }
                else {
                    mTabScrollView.setVisibility(8);
                }
                this.mContainerView.setTabContainer(mTabScrollView);
            }
            this.mTabScrollView = mTabScrollView;
        }
    }
    
    private DecorToolbar getDecorToolbar(final View view) {
        DecorToolbar wrapper;
        if (view instanceof DecorToolbar) {
            wrapper = (DecorToolbar)view;
        }
        else {
            if (!(view instanceof Toolbar)) {
                String simpleName;
                if ("Can't make a decor toolbar out of " + view != null) {
                    simpleName = view.getClass().getSimpleName();
                }
                else {
                    simpleName = "null";
                }
                throw new IllegalStateException(simpleName);
            }
            wrapper = ((Toolbar)view).getWrapper();
        }
        return wrapper;
    }
    
    private void hideForActionMode() {
        if (this.mShowingForMode) {
            this.mShowingForMode = false;
            if (this.mOverlayLayout != null) {
                this.mOverlayLayout.setShowingForActionMode(false);
            }
            this.updateVisibility(false);
        }
    }
    
    private void init(final View view) {
        this.mOverlayLayout = (ActionBarOverlayLayout)view.findViewById(R.id.decor_content_parent);
        if (this.mOverlayLayout != null) {
            this.mOverlayLayout.setActionBarVisibilityCallback((ActionBarOverlayLayout.ActionBarVisibilityCallback)this);
        }
        this.mDecorToolbar = this.getDecorToolbar(view.findViewById(R.id.action_bar));
        this.mContextView = (ActionBarContextView)view.findViewById(R.id.action_context_bar);
        this.mContainerView = (ActionBarContainer)view.findViewById(R.id.action_bar_container);
        if (this.mDecorToolbar == null || this.mContextView == null || this.mContainerView == null) {
            throw new IllegalStateException(this.getClass().getSimpleName() + " can only be used " + "with a compatible window decor layout");
        }
        this.mContext = this.mDecorToolbar.getContext();
        boolean b;
        if ((0x4 & this.mDecorToolbar.getDisplayOptions()) != 0x0) {
            b = true;
        }
        else {
            b = false;
        }
        if (b) {
            this.mDisplayHomeAsUpSet = true;
        }
        final ActionBarPolicy value = ActionBarPolicy.get(this.mContext);
        this.setHomeButtonEnabled(value.enableHomeButtonByDefault() || b);
        this.setHasEmbeddedTabs(value.hasEmbeddedTabs());
        final TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes((AttributeSet)null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        if (obtainStyledAttributes.getBoolean(R.styleable.ActionBar_hideOnContentScroll, false)) {
            this.setHideOnContentScrollEnabled(true);
        }
        final int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            this.setElevation(dimensionPixelSize);
        }
        obtainStyledAttributes.recycle();
    }
    
    private void setHasEmbeddedTabs(final boolean mHasEmbeddedTabs) {
        boolean hasNonEmbeddedTabs = true;
        if (!(this.mHasEmbeddedTabs = mHasEmbeddedTabs)) {
            this.mDecorToolbar.setEmbeddedTabView(null);
            this.mContainerView.setTabContainer(this.mTabScrollView);
        }
        else {
            this.mContainerView.setTabContainer(null);
            this.mDecorToolbar.setEmbeddedTabView(this.mTabScrollView);
        }
        final boolean b = this.getNavigationMode() == 2 && hasNonEmbeddedTabs;
        if (this.mTabScrollView != null) {
            if (b) {
                this.mTabScrollView.setVisibility(0);
                if (this.mOverlayLayout != null) {
                    ViewCompat.requestApplyInsets((View)this.mOverlayLayout);
                }
            }
            else {
                this.mTabScrollView.setVisibility(8);
            }
        }
        this.mDecorToolbar.setCollapsible(!this.mHasEmbeddedTabs && b && hasNonEmbeddedTabs);
        final ActionBarOverlayLayout mOverlayLayout = this.mOverlayLayout;
        if (this.mHasEmbeddedTabs || !b) {
            hasNonEmbeddedTabs = false;
        }
        mOverlayLayout.setHasNonEmbeddedTabs(hasNonEmbeddedTabs);
    }
    
    private boolean shouldAnimateContextView() {
        return ViewCompat.isLaidOut((View)this.mContainerView);
    }
    
    private void showForActionMode() {
        if (!this.mShowingForMode) {
            this.mShowingForMode = true;
            if (this.mOverlayLayout != null) {
                this.mOverlayLayout.setShowingForActionMode(true);
            }
            this.updateVisibility(false);
        }
    }
    
    private void updateVisibility(final boolean b) {
        if (checkShowingFlags(this.mHiddenByApp, this.mHiddenBySystem, this.mShowingForMode)) {
            if (!this.mNowShowing) {
                this.mNowShowing = true;
                this.doShow(b);
            }
        }
        else if (this.mNowShowing) {
            this.mNowShowing = false;
            this.doHide(b);
        }
    }
    
    @Override
    public void addOnMenuVisibilityListener(final OnMenuVisibilityListener onMenuVisibilityListener) {
        this.mMenuVisibilityListeners.add(onMenuVisibilityListener);
    }
    
    @Override
    public void addTab(final Tab tab) {
        this.addTab(tab, this.mTabs.isEmpty());
    }
    
    @Override
    public void addTab(final Tab tab, final int n) {
        this.addTab(tab, n, this.mTabs.isEmpty());
    }
    
    @Override
    public void addTab(final Tab tab, final int n, final boolean b) {
        this.ensureTabsExist();
        this.mTabScrollView.addTab(tab, n, b);
        this.configureTab(tab, n);
        if (b) {
            this.selectTab(tab);
        }
    }
    
    @Override
    public void addTab(final Tab tab, final boolean b) {
        this.ensureTabsExist();
        this.mTabScrollView.addTab(tab, b);
        this.configureTab(tab, this.mTabs.size());
        if (b) {
            this.selectTab(tab);
        }
    }
    
    public void animateToMode(final boolean b) {
        if (b) {
            this.showForActionMode();
        }
        else {
            this.hideForActionMode();
        }
        if (this.shouldAnimateContextView()) {
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat;
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2;
            if (b) {
                viewPropertyAnimatorCompat = this.mDecorToolbar.setupAnimatorToVisibility(4, 100L);
                viewPropertyAnimatorCompat2 = this.mContextView.setupAnimatorToVisibility(0, 200L);
            }
            else {
                viewPropertyAnimatorCompat2 = this.mDecorToolbar.setupAnimatorToVisibility(0, 200L);
                viewPropertyAnimatorCompat = this.mContextView.setupAnimatorToVisibility(8, 100L);
            }
            final ViewPropertyAnimatorCompatSet set = new ViewPropertyAnimatorCompatSet();
            set.playSequentially(viewPropertyAnimatorCompat, viewPropertyAnimatorCompat2);
            set.start();
        }
        else if (b) {
            this.mDecorToolbar.setVisibility(4);
            this.mContextView.setVisibility(0);
        }
        else {
            this.mDecorToolbar.setVisibility(0);
            this.mContextView.setVisibility(8);
        }
    }
    
    @Override
    public boolean collapseActionView() {
        boolean b;
        if (this.mDecorToolbar != null && this.mDecorToolbar.hasExpandedActionView()) {
            this.mDecorToolbar.collapseActionView();
            b = true;
        }
        else {
            b = false;
        }
        return b;
    }
    
    void completeDeferredDestroyActionMode() {
        if (this.mDeferredModeDestroyCallback != null) {
            this.mDeferredModeDestroyCallback.onDestroyActionMode(this.mDeferredDestroyActionMode);
            this.mDeferredDestroyActionMode = null;
            this.mDeferredModeDestroyCallback = null;
        }
    }
    
    @Override
    public void dispatchMenuVisibilityChanged(final boolean mLastMenuVisibility) {
        if (mLastMenuVisibility != this.mLastMenuVisibility) {
            this.mLastMenuVisibility = mLastMenuVisibility;
            for (int size = this.mMenuVisibilityListeners.size(), i = 0; i < size; ++i) {
                this.mMenuVisibilityListeners.get(i).onMenuVisibilityChanged(mLastMenuVisibility);
            }
        }
    }
    
    public void doHide(final boolean b) {
        if (this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.cancel();
        }
        if (this.mCurWindowVisibility == 0 && WindowDecorActionBar.ALLOW_SHOW_HIDE_ANIMATIONS && (this.mShowHideAnimationEnabled || b)) {
            ViewCompat.setAlpha((View)this.mContainerView, 1.0f);
            this.mContainerView.setTransitioning(true);
            final ViewPropertyAnimatorCompatSet mCurrentShowAnim = new ViewPropertyAnimatorCompatSet();
            float n = -this.mContainerView.getHeight();
            if (b) {
                final int[] array = { 0, 0 };
                this.mContainerView.getLocationInWindow(array);
                n -= array[1];
            }
            final ViewPropertyAnimatorCompat translationY = ViewCompat.animate((View)this.mContainerView).translationY(n);
            translationY.setUpdateListener(this.mUpdateListener);
            mCurrentShowAnim.play(translationY);
            if (this.mContentAnimations && this.mContentView != null) {
                mCurrentShowAnim.play(ViewCompat.animate(this.mContentView).translationY(n));
            }
            mCurrentShowAnim.setInterpolator(WindowDecorActionBar.sHideInterpolator);
            mCurrentShowAnim.setDuration(250L);
            mCurrentShowAnim.setListener(this.mHideListener);
            (this.mCurrentShowAnim = mCurrentShowAnim).start();
        }
        else {
            this.mHideListener.onAnimationEnd(null);
        }
    }
    
    public void doShow(final boolean b) {
        if (this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.cancel();
        }
        this.mContainerView.setVisibility(0);
        if (this.mCurWindowVisibility == 0 && WindowDecorActionBar.ALLOW_SHOW_HIDE_ANIMATIONS && (this.mShowHideAnimationEnabled || b)) {
            ViewCompat.setTranslationY((View)this.mContainerView, 0.0f);
            float n = -this.mContainerView.getHeight();
            if (b) {
                final int[] array = { 0, 0 };
                this.mContainerView.getLocationInWindow(array);
                n -= array[1];
            }
            ViewCompat.setTranslationY((View)this.mContainerView, n);
            final ViewPropertyAnimatorCompatSet mCurrentShowAnim = new ViewPropertyAnimatorCompatSet();
            final ViewPropertyAnimatorCompat translationY = ViewCompat.animate((View)this.mContainerView).translationY(0.0f);
            translationY.setUpdateListener(this.mUpdateListener);
            mCurrentShowAnim.play(translationY);
            if (this.mContentAnimations && this.mContentView != null) {
                ViewCompat.setTranslationY(this.mContentView, n);
                mCurrentShowAnim.play(ViewCompat.animate(this.mContentView).translationY(0.0f));
            }
            mCurrentShowAnim.setInterpolator(WindowDecorActionBar.sShowInterpolator);
            mCurrentShowAnim.setDuration(250L);
            mCurrentShowAnim.setListener(this.mShowListener);
            (this.mCurrentShowAnim = mCurrentShowAnim).start();
        }
        else {
            ViewCompat.setAlpha((View)this.mContainerView, 1.0f);
            ViewCompat.setTranslationY((View)this.mContainerView, 0.0f);
            if (this.mContentAnimations && this.mContentView != null) {
                ViewCompat.setTranslationY(this.mContentView, 0.0f);
            }
            this.mShowListener.onAnimationEnd(null);
        }
        if (this.mOverlayLayout != null) {
            ViewCompat.requestApplyInsets((View)this.mOverlayLayout);
        }
    }
    
    @Override
    public void enableContentAnimations(final boolean mContentAnimations) {
        this.mContentAnimations = mContentAnimations;
    }
    
    @Override
    public View getCustomView() {
        return this.mDecorToolbar.getCustomView();
    }
    
    @Override
    public int getDisplayOptions() {
        return this.mDecorToolbar.getDisplayOptions();
    }
    
    @Override
    public float getElevation() {
        return ViewCompat.getElevation((View)this.mContainerView);
    }
    
    @Override
    public int getHeight() {
        return this.mContainerView.getHeight();
    }
    
    @Override
    public int getHideOffset() {
        return this.mOverlayLayout.getActionBarHideOffset();
    }
    
    @Override
    public int getNavigationItemCount() {
        int n = 0;
        switch (this.mDecorToolbar.getNavigationMode()) {
            default: {
                n = 0;
                break;
            }
            case 2: {
                n = this.mTabs.size();
                break;
            }
            case 1: {
                n = this.mDecorToolbar.getDropdownItemCount();
                break;
            }
        }
        return n;
    }
    
    @Override
    public int getNavigationMode() {
        return this.mDecorToolbar.getNavigationMode();
    }
    
    @Override
    public int getSelectedNavigationIndex() {
        int n = -1;
        switch (this.mDecorToolbar.getNavigationMode()) {
            case 2: {
                if (this.mSelectedTab != null) {
                    n = this.mSelectedTab.getPosition();
                    break;
                }
                break;
            }
            case 1: {
                n = this.mDecorToolbar.getDropdownSelectedPosition();
                break;
            }
        }
        return n;
    }
    
    @Override
    public Tab getSelectedTab() {
        return this.mSelectedTab;
    }
    
    @Override
    public CharSequence getSubtitle() {
        return this.mDecorToolbar.getSubtitle();
    }
    
    @Override
    public Tab getTabAt(final int n) {
        return this.mTabs.get(n);
    }
    
    @Override
    public int getTabCount() {
        return this.mTabs.size();
    }
    
    @Override
    public Context getThemedContext() {
        if (this.mThemedContext == null) {
            final TypedValue typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
            final int resourceId = typedValue.resourceId;
            if (resourceId != 0) {
                this.mThemedContext = (Context)new ContextThemeWrapper(this.mContext, resourceId);
            }
            else {
                this.mThemedContext = this.mContext;
            }
        }
        return this.mThemedContext;
    }
    
    @Override
    public CharSequence getTitle() {
        return this.mDecorToolbar.getTitle();
    }
    
    public boolean hasIcon() {
        return this.mDecorToolbar.hasIcon();
    }
    
    public boolean hasLogo() {
        return this.mDecorToolbar.hasLogo();
    }
    
    @Override
    public void hide() {
        if (!this.mHiddenByApp) {
            this.mHiddenByApp = true;
            this.updateVisibility(false);
        }
    }
    
    @Override
    public void hideForSystem() {
        if (!this.mHiddenBySystem) {
            this.updateVisibility(this.mHiddenBySystem = true);
        }
    }
    
    @Override
    public boolean isHideOnContentScrollEnabled() {
        return this.mOverlayLayout.isHideOnContentScrollEnabled();
    }
    
    @Override
    public boolean isShowing() {
        final int height = this.getHeight();
        return this.mNowShowing && (height == 0 || this.getHideOffset() < height);
    }
    
    @Override
    public boolean isTitleTruncated() {
        return this.mDecorToolbar != null && this.mDecorToolbar.isTitleTruncated();
    }
    
    @Override
    public Tab newTab() {
        return new TabImpl();
    }
    
    @Override
    public void onConfigurationChanged(final Configuration configuration) {
        this.setHasEmbeddedTabs(ActionBarPolicy.get(this.mContext).hasEmbeddedTabs());
    }
    
    @Override
    public void onContentScrollStarted() {
        if (this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.cancel();
            this.mCurrentShowAnim = null;
        }
    }
    
    @Override
    public void onContentScrollStopped() {
    }
    
    @Override
    public void onWindowVisibilityChanged(final int mCurWindowVisibility) {
        this.mCurWindowVisibility = mCurWindowVisibility;
    }
    
    @Override
    public void removeAllTabs() {
        this.cleanupTabs();
    }
    
    @Override
    public void removeOnMenuVisibilityListener(final OnMenuVisibilityListener onMenuVisibilityListener) {
        this.mMenuVisibilityListeners.remove(onMenuVisibilityListener);
    }
    
    @Override
    public void removeTab(final Tab tab) {
        this.removeTabAt(tab.getPosition());
    }
    
    @Override
    public void removeTabAt(final int n) {
        if (this.mTabScrollView != null) {
            int n2;
            if (this.mSelectedTab != null) {
                n2 = this.mSelectedTab.getPosition();
            }
            else {
                n2 = this.mSavedTabPosition;
            }
            this.mTabScrollView.removeTabAt(n);
            final TabImpl tabImpl = this.mTabs.remove(n);
            if (tabImpl != null) {
                tabImpl.setPosition(-1);
            }
            for (int size = this.mTabs.size(), i = n; i < size; ++i) {
                this.mTabs.get(i).setPosition(i);
            }
            if (n2 == n) {
                Tab tab;
                if (this.mTabs.isEmpty()) {
                    tab = null;
                }
                else {
                    tab = this.mTabs.get(Math.max(0, n - 1));
                }
                this.selectTab(tab);
            }
        }
    }
    
    public boolean requestFocus() {
        final ViewGroup viewGroup = this.mDecorToolbar.getViewGroup();
        boolean b;
        if (viewGroup != null && !viewGroup.hasFocus()) {
            viewGroup.requestFocus();
            b = true;
        }
        else {
            b = false;
        }
        return b;
    }
    
    @Override
    public void selectTab(final Tab tab) {
        int position = -1;
        if (this.getNavigationMode() != 2) {
            int position2;
            if (tab != null) {
                position2 = tab.getPosition();
            }
            else {
                position2 = position;
            }
            this.mSavedTabPosition = position2;
        }
        else {
            FragmentTransaction disallowAddToBackStack;
            if (this.mActivity instanceof FragmentActivity && !this.mDecorToolbar.getViewGroup().isInEditMode()) {
                disallowAddToBackStack = ((FragmentActivity)this.mActivity).getSupportFragmentManager().beginTransaction().disallowAddToBackStack();
            }
            else {
                disallowAddToBackStack = null;
            }
            if (this.mSelectedTab == tab) {
                if (this.mSelectedTab != null) {
                    this.mSelectedTab.getCallback().onTabReselected(this.mSelectedTab, disallowAddToBackStack);
                    this.mTabScrollView.animateToTab(tab.getPosition());
                }
            }
            else {
                final ScrollingTabContainerView mTabScrollView = this.mTabScrollView;
                if (tab != null) {
                    position = tab.getPosition();
                }
                mTabScrollView.setTabSelected(position);
                if (this.mSelectedTab != null) {
                    this.mSelectedTab.getCallback().onTabUnselected(this.mSelectedTab, disallowAddToBackStack);
                }
                this.mSelectedTab = (TabImpl)tab;
                if (this.mSelectedTab != null) {
                    this.mSelectedTab.getCallback().onTabSelected(this.mSelectedTab, disallowAddToBackStack);
                }
            }
            if (disallowAddToBackStack != null && !disallowAddToBackStack.isEmpty()) {
                disallowAddToBackStack.commit();
            }
        }
    }
    
    @Override
    public void setBackgroundDrawable(final Drawable primaryBackground) {
        this.mContainerView.setPrimaryBackground(primaryBackground);
    }
    
    @Override
    public void setCustomView(final int n) {
        this.setCustomView(LayoutInflater.from(this.getThemedContext()).inflate(n, this.mDecorToolbar.getViewGroup(), false));
    }
    
    @Override
    public void setCustomView(final View customView) {
        this.mDecorToolbar.setCustomView(customView);
    }
    
    @Override
    public void setCustomView(final View customView, final ActionBar.LayoutParams layoutParams) {
        customView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.mDecorToolbar.setCustomView(customView);
    }
    
    @Override
    public void setDefaultDisplayHomeAsUpEnabled(final boolean displayHomeAsUpEnabled) {
        if (!this.mDisplayHomeAsUpSet) {
            this.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled);
        }
    }
    
    @Override
    public void setDisplayHomeAsUpEnabled(final boolean b) {
        int n;
        if (b) {
            n = 4;
        }
        else {
            n = 0;
        }
        this.setDisplayOptions(n, 4);
    }
    
    @Override
    public void setDisplayOptions(final int displayOptions) {
        if ((displayOptions & 0x4) != 0x0) {
            this.mDisplayHomeAsUpSet = true;
        }
        this.mDecorToolbar.setDisplayOptions(displayOptions);
    }
    
    @Override
    public void setDisplayOptions(final int n, final int n2) {
        final int displayOptions = this.mDecorToolbar.getDisplayOptions();
        if ((n2 & 0x4) != 0x0) {
            this.mDisplayHomeAsUpSet = true;
        }
        this.mDecorToolbar.setDisplayOptions((n & n2) | (displayOptions & ~n2));
    }
    
    @Override
    public void setDisplayShowCustomEnabled(final boolean b) {
        int n;
        if (b) {
            n = 16;
        }
        else {
            n = 0;
        }
        this.setDisplayOptions(n, 16);
    }
    
    @Override
    public void setDisplayShowHomeEnabled(final boolean b) {
        int n;
        if (b) {
            n = 2;
        }
        else {
            n = 0;
        }
        this.setDisplayOptions(n, 2);
    }
    
    @Override
    public void setDisplayShowTitleEnabled(final boolean b) {
        int n;
        if (b) {
            n = 8;
        }
        else {
            n = 0;
        }
        this.setDisplayOptions(n, 8);
    }
    
    @Override
    public void setDisplayUseLogoEnabled(final boolean b) {
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        this.setDisplayOptions(n, 1);
    }
    
    @Override
    public void setElevation(final float n) {
        ViewCompat.setElevation((View)this.mContainerView, n);
    }
    
    @Override
    public void setHideOffset(final int actionBarHideOffset) {
        if (actionBarHideOffset != 0 && !this.mOverlayLayout.isInOverlayMode()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to set a non-zero hide offset");
        }
        this.mOverlayLayout.setActionBarHideOffset(actionBarHideOffset);
    }
    
    @Override
    public void setHideOnContentScrollEnabled(final boolean b) {
        if (b && !this.mOverlayLayout.isInOverlayMode()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.mHideOnContentScroll = b;
        this.mOverlayLayout.setHideOnContentScrollEnabled(b);
    }
    
    @Override
    public void setHomeActionContentDescription(final int navigationContentDescription) {
        this.mDecorToolbar.setNavigationContentDescription(navigationContentDescription);
    }
    
    @Override
    public void setHomeActionContentDescription(final CharSequence navigationContentDescription) {
        this.mDecorToolbar.setNavigationContentDescription(navigationContentDescription);
    }
    
    @Override
    public void setHomeAsUpIndicator(final int navigationIcon) {
        this.mDecorToolbar.setNavigationIcon(navigationIcon);
    }
    
    @Override
    public void setHomeAsUpIndicator(final Drawable navigationIcon) {
        this.mDecorToolbar.setNavigationIcon(navigationIcon);
    }
    
    @Override
    public void setHomeButtonEnabled(final boolean homeButtonEnabled) {
        this.mDecorToolbar.setHomeButtonEnabled(homeButtonEnabled);
    }
    
    @Override
    public void setIcon(final int icon) {
        this.mDecorToolbar.setIcon(icon);
    }
    
    @Override
    public void setIcon(final Drawable icon) {
        this.mDecorToolbar.setIcon(icon);
    }
    
    @Override
    public void setListNavigationCallbacks(final SpinnerAdapter spinnerAdapter, final OnNavigationListener onNavigationListener) {
        this.mDecorToolbar.setDropdownParams(spinnerAdapter, (AdapterView$OnItemSelectedListener)new NavItemSelectedListener(onNavigationListener));
    }
    
    @Override
    public void setLogo(final int logo) {
        this.mDecorToolbar.setLogo(logo);
    }
    
    @Override
    public void setLogo(final Drawable logo) {
        this.mDecorToolbar.setLogo(logo);
    }
    
    @Override
    public void setNavigationMode(final int navigationMode) {
        boolean hasNonEmbeddedTabs = true;
        final int navigationMode2 = this.mDecorToolbar.getNavigationMode();
        switch (navigationMode2) {
            case 2: {
                this.mSavedTabPosition = this.getSelectedNavigationIndex();
                this.selectTab(null);
                this.mTabScrollView.setVisibility(8);
                break;
            }
        }
        if (navigationMode2 != navigationMode && !this.mHasEmbeddedTabs && this.mOverlayLayout != null) {
            ViewCompat.requestApplyInsets((View)this.mOverlayLayout);
        }
        this.mDecorToolbar.setNavigationMode(navigationMode);
        switch (navigationMode) {
            case 2: {
                this.ensureTabsExist();
                this.mTabScrollView.setVisibility(0);
                if (this.mSavedTabPosition != -1) {
                    this.setSelectedNavigationItem(this.mSavedTabPosition);
                    this.mSavedTabPosition = -1;
                    break;
                }
                break;
            }
        }
        this.mDecorToolbar.setCollapsible(navigationMode == 2 && !this.mHasEmbeddedTabs && hasNonEmbeddedTabs);
        final ActionBarOverlayLayout mOverlayLayout = this.mOverlayLayout;
        if (navigationMode != 2 || this.mHasEmbeddedTabs) {
            hasNonEmbeddedTabs = false;
        }
        mOverlayLayout.setHasNonEmbeddedTabs(hasNonEmbeddedTabs);
    }
    
    @Override
    public void setSelectedNavigationItem(final int dropdownSelectedPosition) {
        switch (this.mDecorToolbar.getNavigationMode()) {
            default: {
                throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
            }
            case 2: {
                this.selectTab(this.mTabs.get(dropdownSelectedPosition));
                break;
            }
            case 1: {
                this.mDecorToolbar.setDropdownSelectedPosition(dropdownSelectedPosition);
                break;
            }
        }
    }
    
    @Override
    public void setShowHideAnimationEnabled(final boolean mShowHideAnimationEnabled) {
        this.mShowHideAnimationEnabled = mShowHideAnimationEnabled;
        if (!mShowHideAnimationEnabled && this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.cancel();
        }
    }
    
    @Override
    public void setSplitBackgroundDrawable(final Drawable drawable) {
    }
    
    @Override
    public void setStackedBackgroundDrawable(final Drawable stackedBackground) {
        this.mContainerView.setStackedBackground(stackedBackground);
    }
    
    @Override
    public void setSubtitle(final int n) {
        this.setSubtitle(this.mContext.getString(n));
    }
    
    @Override
    public void setSubtitle(final CharSequence subtitle) {
        this.mDecorToolbar.setSubtitle(subtitle);
    }
    
    @Override
    public void setTitle(final int n) {
        this.setTitle(this.mContext.getString(n));
    }
    
    @Override
    public void setTitle(final CharSequence title) {
        this.mDecorToolbar.setTitle(title);
    }
    
    @Override
    public void setWindowTitle(final CharSequence windowTitle) {
        this.mDecorToolbar.setWindowTitle(windowTitle);
    }
    
    @Override
    public void show() {
        if (this.mHiddenByApp) {
            this.updateVisibility(this.mHiddenByApp = false);
        }
    }
    
    @Override
    public void showForSystem() {
        if (this.mHiddenBySystem) {
            this.mHiddenBySystem = false;
            this.updateVisibility(true);
        }
    }
    
    @Override
    public ActionMode startActionMode(final ActionMode.Callback callback) {
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        this.mOverlayLayout.setHideOnContentScrollEnabled(false);
        this.mContextView.killMode();
        ActionModeImpl mActionMode = new ActionModeImpl(this.mContextView.getContext(), callback);
        if (mActionMode.dispatchOnCreate()) {
            (this.mActionMode = mActionMode).invalidate();
            this.mContextView.initForMode(mActionMode);
            this.animateToMode(true);
            this.mContextView.sendAccessibilityEvent(32);
        }
        else {
            mActionMode = null;
        }
        return mActionMode;
    }
    
    public class ActionModeImpl extends ActionMode implements MenuBuilder.Callback
    {
        private final Context mActionModeContext;
        private ActionMode.Callback mCallback;
        private WeakReference<View> mCustomView;
        private final MenuBuilder mMenu;
        
        public ActionModeImpl(final Context mActionModeContext, final ActionMode.Callback mCallback) {
            this.mActionModeContext = mActionModeContext;
            this.mCallback = mCallback;
            (this.mMenu = new MenuBuilder(mActionModeContext).setDefaultShowAsAction(1)).setCallback((MenuBuilder.Callback)this);
        }
        
        public boolean dispatchOnCreate() {
            this.mMenu.stopDispatchingItemsChanged();
            try {
                return this.mCallback.onCreateActionMode(this, (Menu)this.mMenu);
            }
            finally {
                this.mMenu.startDispatchingItemsChanged();
            }
        }
        
        @Override
        public void finish() {
            if (WindowDecorActionBar.this.mActionMode == this) {
                if (!WindowDecorActionBar.checkShowingFlags(WindowDecorActionBar.this.mHiddenByApp, WindowDecorActionBar.this.mHiddenBySystem, false)) {
                    WindowDecorActionBar.this.mDeferredDestroyActionMode = this;
                    WindowDecorActionBar.this.mDeferredModeDestroyCallback = this.mCallback;
                }
                else {
                    this.mCallback.onDestroyActionMode(this);
                }
                this.mCallback = null;
                WindowDecorActionBar.this.animateToMode(false);
                WindowDecorActionBar.this.mContextView.closeMode();
                WindowDecorActionBar.this.mDecorToolbar.getViewGroup().sendAccessibilityEvent(32);
                WindowDecorActionBar.this.mOverlayLayout.setHideOnContentScrollEnabled(WindowDecorActionBar.this.mHideOnContentScroll);
                WindowDecorActionBar.this.mActionMode = null;
            }
        }
        
        @Override
        public View getCustomView() {
            View view;
            if (this.mCustomView != null) {
                view = this.mCustomView.get();
            }
            else {
                view = null;
            }
            return view;
        }
        
        @Override
        public Menu getMenu() {
            return (Menu)this.mMenu;
        }
        
        @Override
        public MenuInflater getMenuInflater() {
            return new SupportMenuInflater(this.mActionModeContext);
        }
        
        @Override
        public CharSequence getSubtitle() {
            return WindowDecorActionBar.this.mContextView.getSubtitle();
        }
        
        @Override
        public CharSequence getTitle() {
            return WindowDecorActionBar.this.mContextView.getTitle();
        }
        
        @Override
        public void invalidate() {
            if (WindowDecorActionBar.this.mActionMode == this) {
                this.mMenu.stopDispatchingItemsChanged();
                try {
                    this.mCallback.onPrepareActionMode(this, (Menu)this.mMenu);
                }
                finally {
                    this.mMenu.startDispatchingItemsChanged();
                }
            }
        }
        
        @Override
        public boolean isTitleOptional() {
            return WindowDecorActionBar.this.mContextView.isTitleOptional();
        }
        
        public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
        }
        
        public void onCloseSubMenu(final SubMenuBuilder subMenuBuilder) {
        }
        
        @Override
        public boolean onMenuItemSelected(final MenuBuilder menuBuilder, final MenuItem menuItem) {
            return this.mCallback != null && this.mCallback.onActionItemClicked(this, menuItem);
        }
        
        @Override
        public void onMenuModeChange(final MenuBuilder menuBuilder) {
            if (this.mCallback != null) {
                this.invalidate();
                WindowDecorActionBar.this.mContextView.showOverflowMenu();
            }
        }
        
        public boolean onSubMenuSelected(final SubMenuBuilder subMenuBuilder) {
            boolean b = true;
            if (this.mCallback == null) {
                b = false;
            }
            else if (subMenuBuilder.hasVisibleItems()) {
                new MenuPopupHelper(WindowDecorActionBar.this.getThemedContext(), subMenuBuilder).show();
            }
            return b;
        }
        
        @Override
        public void setCustomView(final View customView) {
            WindowDecorActionBar.this.mContextView.setCustomView(customView);
            this.mCustomView = new WeakReference<View>(customView);
        }
        
        @Override
        public void setSubtitle(final int n) {
            this.setSubtitle(WindowDecorActionBar.this.mContext.getResources().getString(n));
        }
        
        @Override
        public void setSubtitle(final CharSequence subtitle) {
            WindowDecorActionBar.this.mContextView.setSubtitle(subtitle);
        }
        
        @Override
        public void setTitle(final int n) {
            this.setTitle(WindowDecorActionBar.this.mContext.getResources().getString(n));
        }
        
        @Override
        public void setTitle(final CharSequence title) {
            WindowDecorActionBar.this.mContextView.setTitle(title);
        }
        
        @Override
        public void setTitleOptionalHint(final boolean b) {
            super.setTitleOptionalHint(b);
            WindowDecorActionBar.this.mContextView.setTitleOptional(b);
        }
    }
    
    public class TabImpl extends Tab
    {
        private TabListener mCallback;
        private CharSequence mContentDesc;
        private View mCustomView;
        private Drawable mIcon;
        private int mPosition;
        private Object mTag;
        private CharSequence mText;
        
        public TabImpl() {
            this.mPosition = -1;
        }
        
        public TabListener getCallback() {
            return this.mCallback;
        }
        
        @Override
        public CharSequence getContentDescription() {
            return this.mContentDesc;
        }
        
        @Override
        public View getCustomView() {
            return this.mCustomView;
        }
        
        @Override
        public Drawable getIcon() {
            return this.mIcon;
        }
        
        @Override
        public int getPosition() {
            return this.mPosition;
        }
        
        @Override
        public Object getTag() {
            return this.mTag;
        }
        
        @Override
        public CharSequence getText() {
            return this.mText;
        }
        
        @Override
        public void select() {
            WindowDecorActionBar.this.selectTab(this);
        }
        
        @Override
        public Tab setContentDescription(final int n) {
            return this.setContentDescription(WindowDecorActionBar.this.mContext.getResources().getText(n));
        }
        
        @Override
        public Tab setContentDescription(final CharSequence mContentDesc) {
            this.mContentDesc = mContentDesc;
            if (this.mPosition >= 0) {
                WindowDecorActionBar.this.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }
        
        @Override
        public Tab setCustomView(final int n) {
            return this.setCustomView(LayoutInflater.from(WindowDecorActionBar.this.getThemedContext()).inflate(n, (ViewGroup)null));
        }
        
        @Override
        public Tab setCustomView(final View mCustomView) {
            this.mCustomView = mCustomView;
            if (this.mPosition >= 0) {
                WindowDecorActionBar.this.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }
        
        @Override
        public Tab setIcon(final int n) {
            return this.setIcon(AppCompatResources.getDrawable(WindowDecorActionBar.this.mContext, n));
        }
        
        @Override
        public Tab setIcon(final Drawable mIcon) {
            this.mIcon = mIcon;
            if (this.mPosition >= 0) {
                WindowDecorActionBar.this.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }
        
        public void setPosition(final int mPosition) {
            this.mPosition = mPosition;
        }
        
        @Override
        public Tab setTabListener(final TabListener mCallback) {
            this.mCallback = mCallback;
            return this;
        }
        
        @Override
        public Tab setTag(final Object mTag) {
            this.mTag = mTag;
            return this;
        }
        
        @Override
        public Tab setText(final int n) {
            return this.setText(WindowDecorActionBar.this.mContext.getResources().getText(n));
        }
        
        @Override
        public Tab setText(final CharSequence mText) {
            this.mText = mText;
            if (this.mPosition >= 0) {
                WindowDecorActionBar.this.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }
    }
}
