// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.util.Log;
import android.content.pm.ActivityInfo;
import android.support.annotation.Nullable;
import android.content.pm.PackageManager$NameNotFoundException;
import android.support.v4.content.IntentCompat;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.os.Build$VERSION;

public final class NavUtils
{
    private static final NavUtilsImpl IMPL;
    public static final String PARENT_ACTIVITY = "android.support.PARENT_ACTIVITY";
    private static final String TAG = "NavUtils";
    
    static {
        if (Build$VERSION.SDK_INT >= 16) {
            IMPL = (NavUtilsImpl)new NavUtilsImplJB();
        }
        else {
            IMPL = (NavUtilsImpl)new NavUtilsImplBase();
        }
    }
    
    public static Intent getParentActivityIntent(final Activity activity) {
        return NavUtils.IMPL.getParentActivityIntent(activity);
    }
    
    public static Intent getParentActivityIntent(final Context context, final ComponentName componentName) throws PackageManager$NameNotFoundException {
        final String parentActivityName = getParentActivityName(context, componentName);
        Intent intent;
        if (parentActivityName == null) {
            intent = null;
        }
        else {
            final ComponentName component = new ComponentName(componentName.getPackageName(), parentActivityName);
            if (getParentActivityName(context, component) == null) {
                intent = IntentCompat.makeMainActivity(component);
            }
            else {
                intent = new Intent().setComponent(component);
            }
        }
        return intent;
    }
    
    public static Intent getParentActivityIntent(final Context context, final Class<?> clazz) throws PackageManager$NameNotFoundException {
        final String parentActivityName = getParentActivityName(context, new ComponentName(context, (Class)clazz));
        Intent intent;
        if (parentActivityName == null) {
            intent = null;
        }
        else {
            final ComponentName component = new ComponentName(context, parentActivityName);
            if (getParentActivityName(context, component) == null) {
                intent = IntentCompat.makeMainActivity(component);
            }
            else {
                intent = new Intent().setComponent(component);
            }
        }
        return intent;
    }
    
    @Nullable
    public static String getParentActivityName(final Activity activity) {
        try {
            return getParentActivityName((Context)activity, activity.getComponentName());
        }
        catch (PackageManager$NameNotFoundException ex) {
            throw new IllegalArgumentException((Throwable)ex);
        }
    }
    
    @Nullable
    public static String getParentActivityName(final Context context, final ComponentName componentName) throws PackageManager$NameNotFoundException {
        return NavUtils.IMPL.getParentActivityName(context, context.getPackageManager().getActivityInfo(componentName, 128));
    }
    
    public static void navigateUpFromSameTask(final Activity activity) {
        final Intent parentActivityIntent = getParentActivityIntent(activity);
        if (parentActivityIntent == null) {
            throw new IllegalArgumentException("Activity " + activity.getClass().getSimpleName() + " does not have a parent activity name specified." + " (Did you forget to add the android.support.PARENT_ACTIVITY <meta-data> " + " element in your manifest?)");
        }
        navigateUpTo(activity, parentActivityIntent);
    }
    
    public static void navigateUpTo(final Activity activity, final Intent intent) {
        NavUtils.IMPL.navigateUpTo(activity, intent);
    }
    
    public static boolean shouldUpRecreateTask(final Activity activity, final Intent intent) {
        return NavUtils.IMPL.shouldUpRecreateTask(activity, intent);
    }
    
    interface NavUtilsImpl
    {
        Intent getParentActivityIntent(final Activity p0);
        
        String getParentActivityName(final Context p0, final ActivityInfo p1);
        
        void navigateUpTo(final Activity p0, final Intent p1);
        
        boolean shouldUpRecreateTask(final Activity p0, final Intent p1);
    }
    
    static class NavUtilsImplBase implements NavUtilsImpl
    {
        @Override
        public Intent getParentActivityIntent(final Activity activity) {
            final String parentActivityName = NavUtils.getParentActivityName(activity);
            Intent intent = null;
            if (parentActivityName != null) {
                final ComponentName component = new ComponentName((Context)activity, parentActivityName);
                try {
                    final String parentActivityName2 = NavUtils.getParentActivityName((Context)activity, component);
                    intent = null;
                    if (parentActivityName2 == null) {
                        intent = IntentCompat.makeMainActivity(component);
                    }
                    else {
                        intent = new Intent().setComponent(component);
                    }
                }
                catch (PackageManager$NameNotFoundException ex) {
                    Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName '" + parentActivityName + "' in manifest");
                }
            }
            return intent;
        }
        
        @Override
        public String getParentActivityName(final Context context, final ActivityInfo activityInfo) {
            String s;
            if (activityInfo.metaData == null) {
                s = null;
            }
            else {
                s = activityInfo.metaData.getString("android.support.PARENT_ACTIVITY");
                if (s == null) {
                    s = null;
                }
                else if (s.charAt(0) == '.') {
                    s = context.getPackageName() + s;
                }
            }
            return s;
        }
        
        @Override
        public void navigateUpTo(final Activity activity, final Intent intent) {
            intent.addFlags(67108864);
            activity.startActivity(intent);
            activity.finish();
        }
        
        @Override
        public boolean shouldUpRecreateTask(final Activity activity, final Intent intent) {
            final String action = activity.getIntent().getAction();
            return action != null && !action.equals("android.intent.action.MAIN");
        }
    }
    
    static class NavUtilsImplJB extends NavUtilsImplBase
    {
        @Override
        public Intent getParentActivityIntent(final Activity activity) {
            Intent intent = NavUtilsJB.getParentActivityIntent(activity);
            if (intent == null) {
                intent = this.superGetParentActivityIntent(activity);
            }
            return intent;
        }
        
        @Override
        public String getParentActivityName(final Context context, final ActivityInfo activityInfo) {
            String s = NavUtilsJB.getParentActivityName(activityInfo);
            if (s == null) {
                s = super.getParentActivityName(context, activityInfo);
            }
            return s;
        }
        
        @Override
        public void navigateUpTo(final Activity activity, final Intent intent) {
            NavUtilsJB.navigateUpTo(activity, intent);
        }
        
        @Override
        public boolean shouldUpRecreateTask(final Activity activity, final Intent intent) {
            return NavUtilsJB.shouldUpRecreateTask(activity, intent);
        }
        
        Intent superGetParentActivityIntent(final Activity activity) {
            return super.getParentActivityIntent(activity);
        }
    }
}
