// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.annotation.StyleRes;
import android.support.v7.widget.Toolbar;
import android.support.annotation.LayoutRes;
import android.support.v4.app.ActivityCompat;
import android.support.annotation.CallSuper;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.content.res.Configuration;
import android.support.v4.app.NavUtils;
import android.content.Intent;
import android.content.Context;
import android.support.v7.widget.VectorEnabledTintResources;
import android.view.MenuInflater;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v4.view.KeyEventCompat;
import android.view.KeyEvent;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.content.res.Resources;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.app.FragmentActivity;

public class AppCompatActivity extends FragmentActivity implements SupportParentable, DelegateProvider, AppCompatCallback
{
    private AppCompatDelegate mDelegate;
    private boolean mEatKeyUpEvent;
    private Resources mResources;
    private int mThemeId;
    
    public AppCompatActivity() {
        this.mThemeId = 0;
    }
    
    public void addContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.getDelegate().addContentView(view, viewGroup$LayoutParams);
    }
    
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        boolean dispatchKeyEvent = true;
        if (!KeyEventCompat.isCtrlPressed(keyEvent) || keyEvent.getUnicodeChar(0xFFFF8FFF & keyEvent.getMetaState()) != 60) {
            return super.dispatchKeyEvent(keyEvent);
        }
        final int action = keyEvent.getAction();
        if (action == 0) {
            final ActionBar supportActionBar = this.getSupportActionBar();
            if (supportActionBar == null || !supportActionBar.isShowing() || !supportActionBar.requestFocus()) {
                return super.dispatchKeyEvent(keyEvent);
            }
            this.mEatKeyUpEvent = dispatchKeyEvent;
        }
        else {
            if (action != (dispatchKeyEvent ? 1 : 0) || !this.mEatKeyUpEvent) {
                return super.dispatchKeyEvent(keyEvent);
            }
            this.mEatKeyUpEvent = false;
        }
        return dispatchKeyEvent;
        dispatchKeyEvent = super.dispatchKeyEvent(keyEvent);
        return dispatchKeyEvent;
    }
    
    public View findViewById(@IdRes final int n) {
        return this.getDelegate().findViewById(n);
    }
    
    @NonNull
    public AppCompatDelegate getDelegate() {
        if (this.mDelegate == null) {
            this.mDelegate = AppCompatDelegate.create(this, this);
        }
        return this.mDelegate;
    }
    
    @Nullable
    @Override
    public Delegate getDrawerToggleDelegate() {
        return this.getDelegate().getDrawerToggleDelegate();
    }
    
    public MenuInflater getMenuInflater() {
        return this.getDelegate().getMenuInflater();
    }
    
    public Resources getResources() {
        if (this.mResources == null && VectorEnabledTintResources.shouldBeUsed()) {
            this.mResources = new VectorEnabledTintResources((Context)this, super.getResources());
        }
        Resources resources;
        if (this.mResources == null) {
            resources = super.getResources();
        }
        else {
            resources = this.mResources;
        }
        return resources;
    }
    
    @Nullable
    public ActionBar getSupportActionBar() {
        return this.getDelegate().getSupportActionBar();
    }
    
    @Nullable
    @Override
    public Intent getSupportParentActivityIntent() {
        return NavUtils.getParentActivityIntent(this);
    }
    
    public void invalidateOptionsMenu() {
        this.getDelegate().invalidateOptionsMenu();
    }
    
    @Override
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.getDelegate().onConfigurationChanged(configuration);
        if (this.mResources != null) {
            this.mResources.updateConfiguration(configuration, super.getResources().getDisplayMetrics());
        }
    }
    
    public void onContentChanged() {
        this.onSupportContentChanged();
    }
    
    @Override
    protected void onCreate(@Nullable final Bundle bundle) {
        final AppCompatDelegate delegate = this.getDelegate();
        delegate.installViewFactory();
        delegate.onCreate(bundle);
        if (delegate.applyDayNight() && this.mThemeId != 0) {
            if (Build$VERSION.SDK_INT >= 23) {
                this.onApplyThemeResource(this.getTheme(), this.mThemeId, false);
            }
            else {
                this.setTheme(this.mThemeId);
            }
        }
        super.onCreate(bundle);
    }
    
    public void onCreateSupportNavigateUpTaskStack(@NonNull final TaskStackBuilder taskStackBuilder) {
        taskStackBuilder.addParentStack(this);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.getDelegate().onDestroy();
    }
    
    @Override
    public final boolean onMenuItemSelected(final int n, final MenuItem menuItem) {
        boolean b;
        if (super.onMenuItemSelected(n, menuItem)) {
            b = true;
        }
        else {
            final ActionBar supportActionBar = this.getSupportActionBar();
            b = (menuItem.getItemId() == 16908332 && supportActionBar != null && (0x4 & supportActionBar.getDisplayOptions()) != 0x0 && this.onSupportNavigateUp());
        }
        return b;
    }
    
    public boolean onMenuOpened(final int n, final Menu menu) {
        return super.onMenuOpened(n, menu);
    }
    
    @Override
    public void onPanelClosed(final int n, final Menu menu) {
        super.onPanelClosed(n, menu);
    }
    
    protected void onPostCreate(@Nullable final Bundle bundle) {
        super.onPostCreate(bundle);
        this.getDelegate().onPostCreate(bundle);
    }
    
    @Override
    protected void onPostResume() {
        super.onPostResume();
        this.getDelegate().onPostResume();
    }
    
    public void onPrepareSupportNavigateUpTaskStack(@NonNull final TaskStackBuilder taskStackBuilder) {
    }
    
    @Override
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.getDelegate().onSaveInstanceState(bundle);
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        this.getDelegate().onStart();
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        this.getDelegate().onStop();
    }
    
    @CallSuper
    @Override
    public void onSupportActionModeFinished(@NonNull final ActionMode actionMode) {
    }
    
    @CallSuper
    @Override
    public void onSupportActionModeStarted(@NonNull final ActionMode actionMode) {
    }
    
    @Deprecated
    public void onSupportContentChanged() {
    }
    
    public boolean onSupportNavigateUp() {
        final Intent supportParentActivityIntent = this.getSupportParentActivityIntent();
        if (supportParentActivityIntent == null) {
            return false;
        }
        while (true) {
            Label_0053: {
                if (!this.supportShouldUpRecreateTask(supportParentActivityIntent)) {
                    break Label_0053;
                }
                final TaskStackBuilder create = TaskStackBuilder.create((Context)this);
                this.onCreateSupportNavigateUpTaskStack(create);
                this.onPrepareSupportNavigateUpTaskStack(create);
                create.startActivities();
                try {
                    ActivityCompat.finishAffinity(this);
                    return true;
                }
                catch (IllegalStateException ex) {
                    this.finish();
                    return true;
                }
            }
            this.supportNavigateUpTo(supportParentActivityIntent);
            continue;
        }
    }
    
    protected void onTitleChanged(final CharSequence title, final int n) {
        super.onTitleChanged(title, n);
        this.getDelegate().setTitle(title);
    }
    
    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(@NonNull final ActionMode.Callback callback) {
        return null;
    }
    
    public void setContentView(@LayoutRes final int contentView) {
        this.getDelegate().setContentView(contentView);
    }
    
    public void setContentView(final View contentView) {
        this.getDelegate().setContentView(contentView);
    }
    
    public void setContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.getDelegate().setContentView(view, viewGroup$LayoutParams);
    }
    
    public void setSupportActionBar(@Nullable final Toolbar supportActionBar) {
        this.getDelegate().setSupportActionBar(supportActionBar);
    }
    
    @Deprecated
    public void setSupportProgress(final int n) {
    }
    
    @Deprecated
    public void setSupportProgressBarIndeterminate(final boolean b) {
    }
    
    @Deprecated
    public void setSupportProgressBarIndeterminateVisibility(final boolean b) {
    }
    
    @Deprecated
    public void setSupportProgressBarVisibility(final boolean b) {
    }
    
    public void setTheme(@StyleRes final int n) {
        super.setTheme(n);
        this.mThemeId = n;
    }
    
    @Nullable
    public ActionMode startSupportActionMode(@NonNull final ActionMode.Callback callback) {
        return this.getDelegate().startSupportActionMode(callback);
    }
    
    @Override
    public void supportInvalidateOptionsMenu() {
        this.getDelegate().invalidateOptionsMenu();
    }
    
    public void supportNavigateUpTo(@NonNull final Intent intent) {
        NavUtils.navigateUpTo(this, intent);
    }
    
    public boolean supportRequestWindowFeature(final int n) {
        return this.getDelegate().requestWindowFeature(n);
    }
    
    public boolean supportShouldUpRecreateTask(@NonNull final Intent intent) {
        return NavUtils.shouldUpRecreateTask(this, intent);
    }
}
