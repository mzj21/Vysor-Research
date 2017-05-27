// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.lang.reflect.Method;
import android.app.ActionBar;
import android.util.Log;
import android.os.Build$VERSION;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.app.Activity;

class ActionBarDrawerToggleHoneycomb
{
    private static final String TAG = "ActionBarDrawerToggleHoneycomb";
    private static final int[] THEME_ATTRS;
    
    static {
        THEME_ATTRS = new int[] { 16843531 };
    }
    
    public static Drawable getThemeUpIndicator(final Activity activity) {
        final TypedArray obtainStyledAttributes = activity.obtainStyledAttributes(ActionBarDrawerToggleHoneycomb.THEME_ATTRS);
        final Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        return drawable;
    }
    
    public static SetIndicatorInfo setActionBarDescription(SetIndicatorInfo setIndicatorInfo, final Activity activity, final int n) {
        if (setIndicatorInfo == null) {
            setIndicatorInfo = new SetIndicatorInfo(activity);
        }
        if (setIndicatorInfo.setHomeAsUpIndicator == null) {
            return setIndicatorInfo;
        }
        try {
            final ActionBar actionBar = activity.getActionBar();
            setIndicatorInfo.setHomeActionContentDescription.invoke(actionBar, n);
            if (Build$VERSION.SDK_INT <= 19) {
                actionBar.setSubtitle(actionBar.getSubtitle());
            }
            return setIndicatorInfo;
        }
        catch (Exception ex) {
            Log.w("ActionBarDrawerToggleHoneycomb", "Couldn't set content description via JB-MR2 API", (Throwable)ex);
            return setIndicatorInfo;
        }
    }
    
    public static SetIndicatorInfo setActionBarUpIndicator(final SetIndicatorInfo setIndicatorInfo, final Activity activity, final Drawable imageDrawable, final int n) {
        final SetIndicatorInfo setIndicatorInfo2 = new SetIndicatorInfo(activity);
        Label_0092: {
            if (setIndicatorInfo2.setHomeAsUpIndicator == null) {
                break Label_0092;
            }
            try {
                final ActionBar actionBar = activity.getActionBar();
                setIndicatorInfo2.setHomeAsUpIndicator.invoke(actionBar, imageDrawable);
                setIndicatorInfo2.setHomeActionContentDescription.invoke(actionBar, n);
                return setIndicatorInfo2;
            }
            catch (Exception ex) {
                Log.w("ActionBarDrawerToggleHoneycomb", "Couldn't set home-as-up indicator via JB-MR2 API", (Throwable)ex);
                return setIndicatorInfo2;
            }
        }
        if (setIndicatorInfo2.upIndicatorView != null) {
            setIndicatorInfo2.upIndicatorView.setImageDrawable(imageDrawable);
            return setIndicatorInfo2;
        }
        Log.w("ActionBarDrawerToggleHoneycomb", "Couldn't set home-as-up indicator");
        return setIndicatorInfo2;
    }
    
    static class SetIndicatorInfo
    {
        public Method setHomeActionContentDescription;
        public Method setHomeAsUpIndicator;
        public ImageView upIndicatorView;
        
        SetIndicatorInfo(final Activity activity) {
            try {
                this.setHomeAsUpIndicator = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", Drawable.class);
                this.setHomeActionContentDescription = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", Integer.TYPE);
            }
            catch (NoSuchMethodException ex) {
                final View viewById = activity.findViewById(16908332);
                if (viewById == null) {
                    return;
                }
                final ViewGroup viewGroup = (ViewGroup)viewById.getParent();
                if (viewGroup.getChildCount() != 2) {
                    return;
                }
                final View child = viewGroup.getChildAt(0);
                final View child2 = viewGroup.getChildAt(1);
                Object o;
                if (child.getId() == 16908332) {
                    o = child2;
                }
                else {
                    o = child;
                }
                if (o instanceof ImageView) {
                    this.upIndicatorView = (ImageView)o;
                }
            }
        }
    }
}
