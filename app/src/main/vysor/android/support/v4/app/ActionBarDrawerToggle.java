// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.support.v4.view.ViewCompat;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.InsetDrawable;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.content.res.Configuration;
import android.support.v4.content.ContextCompat;
import android.content.Context;
import android.support.annotation.StringRes;
import android.support.annotation.DrawableRes;
import android.os.Build$VERSION;
import android.graphics.drawable.Drawable;
import android.app.Activity;
import android.support.v4.widget.DrawerLayout;

@Deprecated
public class ActionBarDrawerToggle implements DrawerListener
{
    private static final int ID_HOME = 16908332;
    private static final ActionBarDrawerToggleImpl IMPL;
    private static final float TOGGLE_DRAWABLE_OFFSET = 0.33333334f;
    final Activity mActivity;
    private final Delegate mActivityImpl;
    private final int mCloseDrawerContentDescRes;
    private Drawable mDrawerImage;
    private final int mDrawerImageResource;
    private boolean mDrawerIndicatorEnabled;
    private final DrawerLayout mDrawerLayout;
    private boolean mHasCustomUpIndicator;
    private Drawable mHomeAsUpIndicator;
    private final int mOpenDrawerContentDescRes;
    private Object mSetIndicatorInfo;
    private SlideDrawable mSlider;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 18) {
            IMPL = (ActionBarDrawerToggleImpl)new ActionBarDrawerToggleImplJellybeanMR2();
        }
        else if (sdk_INT >= 11) {
            IMPL = (ActionBarDrawerToggleImpl)new ActionBarDrawerToggleImplHC();
        }
        else {
            IMPL = (ActionBarDrawerToggleImpl)new ActionBarDrawerToggleImplBase();
        }
    }
    
    public ActionBarDrawerToggle(final Activity activity, final DrawerLayout drawerLayout, @DrawableRes final int n, @StringRes final int n2, @StringRes final int n3) {
        this(activity, drawerLayout, !assumeMaterial((Context)activity), n, n2, n3);
    }
    
    public ActionBarDrawerToggle(final Activity mActivity, final DrawerLayout mDrawerLayout, final boolean b, @DrawableRes final int mDrawerImageResource, @StringRes final int mOpenDrawerContentDescRes, @StringRes final int mCloseDrawerContentDescRes) {
        this.mDrawerIndicatorEnabled = true;
        this.mActivity = mActivity;
        if (mActivity instanceof DelegateProvider) {
            this.mActivityImpl = ((DelegateProvider)mActivity).getDrawerToggleDelegate();
        }
        else {
            this.mActivityImpl = null;
        }
        this.mDrawerLayout = mDrawerLayout;
        this.mDrawerImageResource = mDrawerImageResource;
        this.mOpenDrawerContentDescRes = mOpenDrawerContentDescRes;
        this.mCloseDrawerContentDescRes = mCloseDrawerContentDescRes;
        this.mHomeAsUpIndicator = this.getThemeUpIndicator();
        this.mDrawerImage = ContextCompat.getDrawable((Context)mActivity, mDrawerImageResource);
        this.mSlider = new SlideDrawable(this.mDrawerImage);
        final SlideDrawable mSlider = this.mSlider;
        float offset;
        if (b) {
            offset = 0.33333334f;
        }
        else {
            offset = 0.0f;
        }
        mSlider.setOffset(offset);
    }
    
    private static boolean assumeMaterial(final Context context) {
        return context.getApplicationInfo().targetSdkVersion >= 21 && Build$VERSION.SDK_INT >= 21;
    }
    
    Drawable getThemeUpIndicator() {
        Drawable drawable;
        if (this.mActivityImpl != null) {
            drawable = this.mActivityImpl.getThemeUpIndicator();
        }
        else {
            drawable = ActionBarDrawerToggle.IMPL.getThemeUpIndicator(this.mActivity);
        }
        return drawable;
    }
    
    public boolean isDrawerIndicatorEnabled() {
        return this.mDrawerIndicatorEnabled;
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        if (!this.mHasCustomUpIndicator) {
            this.mHomeAsUpIndicator = this.getThemeUpIndicator();
        }
        this.mDrawerImage = ContextCompat.getDrawable((Context)this.mActivity, this.mDrawerImageResource);
        this.syncState();
    }
    
    @Override
    public void onDrawerClosed(final View view) {
        this.mSlider.setPosition(0.0f);
        if (this.mDrawerIndicatorEnabled) {
            this.setActionBarDescription(this.mOpenDrawerContentDescRes);
        }
    }
    
    @Override
    public void onDrawerOpened(final View view) {
        this.mSlider.setPosition(1.0f);
        if (this.mDrawerIndicatorEnabled) {
            this.setActionBarDescription(this.mCloseDrawerContentDescRes);
        }
    }
    
    @Override
    public void onDrawerSlide(final View view, final float n) {
        final float position = this.mSlider.getPosition();
        float position2;
        if (n > 0.5f) {
            position2 = Math.max(position, 2.0f * Math.max(0.0f, n - 0.5f));
        }
        else {
            position2 = Math.min(position, n * 2.0f);
        }
        this.mSlider.setPosition(position2);
    }
    
    @Override
    public void onDrawerStateChanged(final int n) {
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        boolean b;
        if (menuItem != null && menuItem.getItemId() == 16908332 && this.mDrawerIndicatorEnabled) {
            if (this.mDrawerLayout.isDrawerVisible(8388611)) {
                this.mDrawerLayout.closeDrawer(8388611);
            }
            else {
                this.mDrawerLayout.openDrawer(8388611);
            }
            b = true;
        }
        else {
            b = false;
        }
        return b;
    }
    
    void setActionBarDescription(final int actionBarDescription) {
        if (this.mActivityImpl != null) {
            this.mActivityImpl.setActionBarDescription(actionBarDescription);
        }
        else {
            this.mSetIndicatorInfo = ActionBarDrawerToggle.IMPL.setActionBarDescription(this.mSetIndicatorInfo, this.mActivity, actionBarDescription);
        }
    }
    
    void setActionBarUpIndicator(final Drawable drawable, final int n) {
        if (this.mActivityImpl != null) {
            this.mActivityImpl.setActionBarUpIndicator(drawable, n);
        }
        else {
            this.mSetIndicatorInfo = ActionBarDrawerToggle.IMPL.setActionBarUpIndicator(this.mSetIndicatorInfo, this.mActivity, drawable, n);
        }
    }
    
    public void setDrawerIndicatorEnabled(final boolean mDrawerIndicatorEnabled) {
        if (mDrawerIndicatorEnabled != this.mDrawerIndicatorEnabled) {
            if (mDrawerIndicatorEnabled) {
                final SlideDrawable mSlider = this.mSlider;
                int n;
                if (this.mDrawerLayout.isDrawerOpen(8388611)) {
                    n = this.mCloseDrawerContentDescRes;
                }
                else {
                    n = this.mOpenDrawerContentDescRes;
                }
                this.setActionBarUpIndicator((Drawable)mSlider, n);
            }
            else {
                this.setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
            }
            this.mDrawerIndicatorEnabled = mDrawerIndicatorEnabled;
        }
    }
    
    public void setHomeAsUpIndicator(final int n) {
        Drawable drawable = null;
        if (n != 0) {
            drawable = ContextCompat.getDrawable((Context)this.mActivity, n);
        }
        this.setHomeAsUpIndicator(drawable);
    }
    
    public void setHomeAsUpIndicator(final Drawable mHomeAsUpIndicator) {
        if (mHomeAsUpIndicator == null) {
            this.mHomeAsUpIndicator = this.getThemeUpIndicator();
            this.mHasCustomUpIndicator = false;
        }
        else {
            this.mHomeAsUpIndicator = mHomeAsUpIndicator;
            this.mHasCustomUpIndicator = true;
        }
        if (!this.mDrawerIndicatorEnabled) {
            this.setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
        }
    }
    
    public void syncState() {
        if (this.mDrawerLayout.isDrawerOpen(8388611)) {
            this.mSlider.setPosition(1.0f);
        }
        else {
            this.mSlider.setPosition(0.0f);
        }
        if (this.mDrawerIndicatorEnabled) {
            final SlideDrawable mSlider = this.mSlider;
            int n;
            if (this.mDrawerLayout.isDrawerOpen(8388611)) {
                n = this.mCloseDrawerContentDescRes;
            }
            else {
                n = this.mOpenDrawerContentDescRes;
            }
            this.setActionBarUpIndicator((Drawable)mSlider, n);
        }
    }
    
    private interface ActionBarDrawerToggleImpl
    {
        Drawable getThemeUpIndicator(final Activity p0);
        
        Object setActionBarDescription(final Object p0, final Activity p1, final int p2);
        
        Object setActionBarUpIndicator(final Object p0, final Activity p1, final Drawable p2, final int p3);
    }
    
    private static class ActionBarDrawerToggleImplBase implements ActionBarDrawerToggleImpl
    {
        @Override
        public Drawable getThemeUpIndicator(final Activity activity) {
            return null;
        }
        
        @Override
        public Object setActionBarDescription(final Object o, final Activity activity, final int n) {
            return o;
        }
        
        @Override
        public Object setActionBarUpIndicator(final Object o, final Activity activity, final Drawable drawable, final int n) {
            return o;
        }
    }
    
    private static class ActionBarDrawerToggleImplHC implements ActionBarDrawerToggleImpl
    {
        @Override
        public Drawable getThemeUpIndicator(final Activity activity) {
            return ActionBarDrawerToggleHoneycomb.getThemeUpIndicator(activity);
        }
        
        @Override
        public Object setActionBarDescription(final Object o, final Activity activity, final int n) {
            return ActionBarDrawerToggleHoneycomb.setActionBarDescription(o, activity, n);
        }
        
        @Override
        public Object setActionBarUpIndicator(final Object o, final Activity activity, final Drawable drawable, final int n) {
            return ActionBarDrawerToggleHoneycomb.setActionBarUpIndicator(o, activity, drawable, n);
        }
    }
    
    private static class ActionBarDrawerToggleImplJellybeanMR2 implements ActionBarDrawerToggleImpl
    {
        @Override
        public Drawable getThemeUpIndicator(final Activity activity) {
            return ActionBarDrawerToggleJellybeanMR2.getThemeUpIndicator(activity);
        }
        
        @Override
        public Object setActionBarDescription(final Object o, final Activity activity, final int n) {
            return ActionBarDrawerToggleJellybeanMR2.setActionBarDescription(o, activity, n);
        }
        
        @Override
        public Object setActionBarUpIndicator(final Object o, final Activity activity, final Drawable drawable, final int n) {
            return ActionBarDrawerToggleJellybeanMR2.setActionBarUpIndicator(o, activity, drawable, n);
        }
    }
    
    public interface Delegate
    {
        @Nullable
        Drawable getThemeUpIndicator();
        
        void setActionBarDescription(@StringRes final int p0);
        
        void setActionBarUpIndicator(final Drawable p0, @StringRes final int p1);
    }
    
    public interface DelegateProvider
    {
        @Nullable
        Delegate getDrawerToggleDelegate();
    }
    
    private class SlideDrawable extends InsetDrawable implements Drawable$Callback
    {
        private final boolean mHasMirroring;
        private float mOffset;
        private float mPosition;
        private final Rect mTmpRect;
        
        SlideDrawable(final Drawable drawable) {
            super(drawable, 0);
            final int sdk_INT = Build$VERSION.SDK_INT;
            boolean mHasMirroring = false;
            if (sdk_INT > 18) {
                mHasMirroring = true;
            }
            this.mHasMirroring = mHasMirroring;
            this.mTmpRect = new Rect();
        }
        
        public void draw(final Canvas canvas) {
            int n = 1;
            this.copyBounds(this.mTmpRect);
            canvas.save();
            int n2;
            if (ViewCompat.getLayoutDirection(ActionBarDrawerToggle.this.mActivity.getWindow().getDecorView()) == n) {
                n2 = n;
            }
            else {
                n2 = 0;
            }
            if (n2 != 0) {
                n = -1;
            }
            final int width = this.mTmpRect.width();
            canvas.translate(-this.mOffset * width * this.mPosition * n, 0.0f);
            if (n2 != 0 && !this.mHasMirroring) {
                canvas.translate((float)width, 0.0f);
                canvas.scale(-1.0f, 1.0f);
            }
            super.draw(canvas);
            canvas.restore();
        }
        
        public float getPosition() {
            return this.mPosition;
        }
        
        public void setOffset(final float mOffset) {
            this.mOffset = mOffset;
            this.invalidateSelf();
        }
        
        public void setPosition(final float mPosition) {
            this.mPosition = mPosition;
            this.invalidateSelf();
        }
    }
}
