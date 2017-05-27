// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.IntentFilter;
import android.util.Log;
import java.util.Iterator;
import java.util.List;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.content.pm.ResolveInfo;
import java.util.ArrayList;
import android.content.Intent;
import android.net.Uri;
import android.content.Context;

public class zzarq
{
    private static String brw;
    
    public static String zzfc(final Context context) {
        String s;
        if (zzarq.brw != null) {
            s = zzarq.brw;
        }
        else {
            final PackageManager packageManager = context.getPackageManager();
            final Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
            final ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
            String packageName;
            if (resolveActivity != null) {
                packageName = resolveActivity.activityInfo.packageName;
            }
            else {
                packageName = null;
            }
            final List queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
            final ArrayList<String> list = new ArrayList<String>();
            for (final ResolveInfo resolveInfo : queryIntentActivities) {
                final Intent intent2 = new Intent();
                intent2.setAction("android.support.customtabs.action.CustomTabsService");
                intent2.setPackage(resolveInfo.activityInfo.packageName);
                if (packageManager.resolveService(intent2, 0) != null) {
                    list.add(resolveInfo.activityInfo.packageName);
                }
            }
            if (list.isEmpty()) {
                zzarq.brw = null;
            }
            else if (list.size() == 1) {
                zzarq.brw = (String)list.get(0);
            }
            else if (!TextUtils.isEmpty((CharSequence)packageName) && !zzp(context, intent) && list.contains(packageName)) {
                zzarq.brw = packageName;
            }
            else if (list.contains("com.android.chrome")) {
                zzarq.brw = "com.android.chrome";
            }
            else if (list.contains("com.chrome.beta")) {
                zzarq.brw = "com.chrome.beta";
            }
            else if (list.contains("com.chrome.dev")) {
                zzarq.brw = "com.chrome.dev";
            }
            else if (list.contains("com.google.android.apps.chrome")) {
                zzarq.brw = "com.google.android.apps.chrome";
            }
            s = zzarq.brw;
        }
        return s;
    }
    
    private static boolean zzp(final Context context, final Intent intent) {
        try {
            final List queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 64);
            if (queryIntentActivities != null && queryIntentActivities.size() != 0) {
                for (final ResolveInfo resolveInfo : queryIntentActivities) {
                    final IntentFilter filter = resolveInfo.filter;
                    if (filter != null && filter.countDataAuthorities() != 0 && filter.countDataPaths() != 0 && resolveInfo.activityInfo != null) {
                        return true;
                    }
                }
                return false;
                return true;
            }
            return false;
        }
        catch (RuntimeException ex) {
            Log.e("CustomTabsHelper", "Runtime exception while getting specialized handlers");
        }
        return false;
        b = false;
        return b;
    }
}
