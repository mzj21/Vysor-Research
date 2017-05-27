// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.support.v7.view.SupportActionModeWrapper;
import android.view.ActionMode;
import android.view.ActionMode$Callback;
import android.view.Window$Callback;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.content.res.Configuration;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import android.content.ComponentName;
import android.app.Activity;
import android.view.Window;
import android.content.Context;

class AppCompatDelegateImplV14 extends AppCompatDelegateImplV11
{
    private static final String KEY_LOCAL_NIGHT_MODE = "appcompat:local_night_mode";
    private boolean mApplyDayNightCalled;
    private AutoNightModeManager mAutoNightModeManager;
    private boolean mHandleNativeActionModes;
    private int mLocalNightMode;
    
    AppCompatDelegateImplV14(final Context context, final Window window, final AppCompatCallback appCompatCallback) {
        super(context, window, appCompatCallback);
        this.mLocalNightMode = -100;
        this.mHandleNativeActionModes = true;
    }
    
    private void ensureAutoNightModeManager() {
        if (this.mAutoNightModeManager == null) {
            this.mAutoNightModeManager = new AutoNightModeManager(TwilightManager.getInstance(this.mContext));
        }
    }
    
    private int getNightMode() {
        int n;
        if (this.mLocalNightMode != -100) {
            n = this.mLocalNightMode;
        }
        else {
            n = AppCompatDelegate.getDefaultNightMode();
        }
        return n;
    }
    
    private boolean shouldRecreateOnNightModeChange() {
        boolean b = true;
        if (!this.mApplyDayNightCalled || !(this.mContext instanceof Activity)) {
            b = false;
            return b;
        }
        final PackageManager packageManager = this.mContext.getPackageManager();
        try {
            if ((packageManager.getActivityInfo(new ComponentName(this.mContext, (Class)this.mContext.getClass()), 0).configChanges & 0x200) != 0x0) {
                b = false;
            }
            return b;
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", (Throwable)ex);
            return b;
        }
        return b;
    }
    
    private boolean updateForNightMode(final int n) {
        final Resources resources = this.mContext.getResources();
        final Configuration configuration = resources.getConfiguration();
        final int n2 = 0x30 & configuration.uiMode;
        int n3;
        if (n == 2) {
            n3 = 32;
        }
        else {
            n3 = 16;
        }
        boolean b;
        if (n2 != n3) {
            if (this.shouldRecreateOnNightModeChange()) {
                ((Activity)this.mContext).recreate();
            }
            else {
                final Configuration configuration2 = new Configuration(configuration);
                configuration2.uiMode = (n3 | (0xFFFFFFCF & configuration2.uiMode));
                resources.updateConfiguration(configuration2, (DisplayMetrics)null);
            }
            b = true;
        }
        else {
            b = false;
        }
        return b;
    }
    
    @Override
    public boolean applyDayNight() {
        final int nightMode = this.getNightMode();
        final int mapNightMode = this.mapNightMode(nightMode);
        boolean updateForNightMode = false;
        if (mapNightMode != -1) {
            updateForNightMode = this.updateForNightMode(mapNightMode);
        }
        if (nightMode == 0) {
            this.ensureAutoNightModeManager();
            this.mAutoNightModeManager.setup();
        }
        this.mApplyDayNightCalled = true;
        return updateForNightMode;
    }
    
    @VisibleForTesting
    final AutoNightModeManager getAutoNightModeManager() {
        this.ensureAutoNightModeManager();
        return this.mAutoNightModeManager;
    }
    
    @Override
    public boolean isHandleNativeActionModesEnabled() {
        return this.mHandleNativeActionModes;
    }
    
    int mapNightMode(int applyableNightMode) {
        switch (applyableNightMode) {
            case 0: {
                this.ensureAutoNightModeManager();
                applyableNightMode = this.mAutoNightModeManager.getApplyableNightMode();
                break;
            }
            case -100: {
                applyableNightMode = -1;
                break;
            }
        }
        return applyableNightMode;
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null && this.mLocalNightMode == -100) {
            this.mLocalNightMode = bundle.getInt("appcompat:local_night_mode", -100);
        }
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mAutoNightModeManager != null) {
            this.mAutoNightModeManager.cleanup();
        }
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mLocalNightMode != -100) {
            bundle.putInt("appcompat:local_night_mode", this.mLocalNightMode);
        }
    }
    
    @Override
    public void onStart() {
        super.onStart();
        this.applyDayNight();
    }
    
    @Override
    public void onStop() {
        super.onStop();
        if (this.mAutoNightModeManager != null) {
            this.mAutoNightModeManager.cleanup();
        }
    }
    
    @Override
    public void setHandleNativeActionModesEnabled(final boolean mHandleNativeActionModes) {
        this.mHandleNativeActionModes = mHandleNativeActionModes;
    }
    
    @Override
    public void setLocalNightMode(final int mLocalNightMode) {
        switch (mLocalNightMode) {
            default: {
                Log.i("AppCompatDelegate", "setLocalNightMode() called with an unknown mode");
                break;
            }
            case -1:
            case 0:
            case 1:
            case 2: {
                if (this.mLocalNightMode == mLocalNightMode) {
                    break;
                }
                this.mLocalNightMode = mLocalNightMode;
                if (this.mApplyDayNightCalled) {
                    this.applyDayNight();
                    break;
                }
                break;
            }
        }
    }
    
    @Override
    Window$Callback wrapWindowCallback(final Window$Callback window$Callback) {
        return (Window$Callback)new AppCompatWindowCallbackV14(window$Callback);
    }
    
    class AppCompatWindowCallbackV14 extends AppCompatWindowCallbackBase
    {
        AppCompatWindowCallbackV14(final Window$Callback window$Callback) {
            super(window$Callback);
        }
        
        @Override
        public ActionMode onWindowStartingActionMode(final ActionMode$Callback actionMode$Callback) {
            ActionMode actionMode;
            if (AppCompatDelegateImplV14.this.isHandleNativeActionModesEnabled()) {
                actionMode = this.startAsSupportActionMode(actionMode$Callback);
            }
            else {
                actionMode = super.onWindowStartingActionMode(actionMode$Callback);
            }
            return actionMode;
        }
        
        final ActionMode startAsSupportActionMode(final ActionMode$Callback actionMode$Callback) {
            final SupportActionModeWrapper.CallbackWrapper callbackWrapper = new SupportActionModeWrapper.CallbackWrapper(AppCompatDelegateImplV14.this.mContext, actionMode$Callback);
            final android.support.v7.view.ActionMode startSupportActionMode = AppCompatDelegateImplV14.this.startSupportActionMode(callbackWrapper);
            ActionMode actionModeWrapper;
            if (startSupportActionMode != null) {
                actionModeWrapper = callbackWrapper.getActionModeWrapper(startSupportActionMode);
            }
            else {
                actionModeWrapper = null;
            }
            return actionModeWrapper;
        }
    }
    
    @VisibleForTesting
    final class AutoNightModeManager
    {
        private BroadcastReceiver mAutoTimeChangeReceiver;
        private IntentFilter mAutoTimeChangeReceiverFilter;
        private boolean mIsNight;
        private TwilightManager mTwilightManager;
        
        AutoNightModeManager(@NonNull final TwilightManager mTwilightManager) {
            this.mTwilightManager = mTwilightManager;
            this.mIsNight = mTwilightManager.isNight();
        }
        
        final void cleanup() {
            if (this.mAutoTimeChangeReceiver != null) {
                AppCompatDelegateImplV14.this.mContext.unregisterReceiver(this.mAutoTimeChangeReceiver);
                this.mAutoTimeChangeReceiver = null;
            }
        }
        
        final void dispatchTimeChanged() {
            final boolean night = this.mTwilightManager.isNight();
            if (night != this.mIsNight) {
                this.mIsNight = night;
                AppCompatDelegateImplV14.this.applyDayNight();
            }
        }
        
        final int getApplyableNightMode() {
            int n;
            if (this.mIsNight) {
                n = 2;
            }
            else {
                n = 1;
            }
            return n;
        }
        
        final void setup() {
            this.cleanup();
            if (this.mAutoTimeChangeReceiver == null) {
                this.mAutoTimeChangeReceiver = new BroadcastReceiver() {
                    public void onReceive(final Context context, final Intent intent) {
                        AutoNightModeManager.this.dispatchTimeChanged();
                    }
                };
            }
            if (this.mAutoTimeChangeReceiverFilter == null) {
                (this.mAutoTimeChangeReceiverFilter = new IntentFilter()).addAction("android.intent.action.TIME_SET");
                this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
                this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_TICK");
            }
            AppCompatDelegateImplV14.this.mContext.registerReceiver(this.mAutoTimeChangeReceiver, this.mAutoTimeChangeReceiverFilter);
        }
    }
}
