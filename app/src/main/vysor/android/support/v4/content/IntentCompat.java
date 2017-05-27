// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import android.content.Intent;
import android.content.ComponentName;
import android.os.Build$VERSION;

public final class IntentCompat
{
    public static final String ACTION_EXTERNAL_APPLICATIONS_AVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE";
    public static final String ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE";
    public static final String CATEGORY_LEANBACK_LAUNCHER = "android.intent.category.LEANBACK_LAUNCHER";
    public static final String EXTRA_CHANGED_PACKAGE_LIST = "android.intent.extra.changed_package_list";
    public static final String EXTRA_CHANGED_UID_LIST = "android.intent.extra.changed_uid_list";
    public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";
    public static final int FLAG_ACTIVITY_CLEAR_TASK = 32768;
    public static final int FLAG_ACTIVITY_TASK_ON_HOME = 16384;
    private static final IntentCompatImpl IMPL;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 15) {
            IMPL = (IntentCompatImpl)new IntentCompatImplIcsMr1();
        }
        else if (sdk_INT >= 11) {
            IMPL = (IntentCompatImpl)new IntentCompatImplHC();
        }
        else {
            IMPL = (IntentCompatImpl)new IntentCompatImplBase();
        }
    }
    
    public static Intent makeMainActivity(final ComponentName componentName) {
        return IntentCompat.IMPL.makeMainActivity(componentName);
    }
    
    public static Intent makeMainSelectorActivity(final String s, final String s2) {
        return IntentCompat.IMPL.makeMainSelectorActivity(s, s2);
    }
    
    public static Intent makeRestartActivityTask(final ComponentName componentName) {
        return IntentCompat.IMPL.makeRestartActivityTask(componentName);
    }
    
    interface IntentCompatImpl
    {
        Intent makeMainActivity(final ComponentName p0);
        
        Intent makeMainSelectorActivity(final String p0, final String p1);
        
        Intent makeRestartActivityTask(final ComponentName p0);
    }
    
    static class IntentCompatImplBase implements IntentCompatImpl
    {
        @Override
        public Intent makeMainActivity(final ComponentName component) {
            final Intent intent = new Intent("android.intent.action.MAIN");
            intent.setComponent(component);
            intent.addCategory("android.intent.category.LAUNCHER");
            return intent;
        }
        
        @Override
        public Intent makeMainSelectorActivity(final String s, final String s2) {
            final Intent intent = new Intent(s);
            intent.addCategory(s2);
            return intent;
        }
        
        @Override
        public Intent makeRestartActivityTask(final ComponentName componentName) {
            final Intent mainActivity = this.makeMainActivity(componentName);
            mainActivity.addFlags(268468224);
            return mainActivity;
        }
    }
    
    static class IntentCompatImplHC extends IntentCompatImplBase
    {
        @Override
        public Intent makeMainActivity(final ComponentName componentName) {
            return IntentCompatHoneycomb.makeMainActivity(componentName);
        }
        
        @Override
        public Intent makeRestartActivityTask(final ComponentName componentName) {
            return IntentCompatHoneycomb.makeRestartActivityTask(componentName);
        }
    }
    
    static class IntentCompatImplIcsMr1 extends IntentCompatImplHC
    {
        @Override
        public Intent makeMainSelectorActivity(final String s, final String s2) {
            return IntentCompatIcsMr1.makeMainSelectorActivity(s, s2);
        }
    }
}
