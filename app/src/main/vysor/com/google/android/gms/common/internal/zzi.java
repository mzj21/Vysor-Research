// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.GooglePlayServicesUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.content.res.Resources;
import com.google.android.gms.R;
import android.util.Log;
import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.internal.zzsi;
import android.text.TextUtils;
import android.content.Context;
import android.support.v4.util.SimpleArrayMap;

public final class zzi
{
    private static final SimpleArrayMap<String, String> Cc;
    
    static {
        Cc = new SimpleArrayMap<String, String>();
    }
    
    public static String zzce(final Context context) {
        String s = context.getApplicationInfo().name;
        if (!TextUtils.isEmpty((CharSequence)s)) {
            return s;
        }
        s = context.getPackageName();
        context.getApplicationContext().getPackageManager();
        try {
            s = zzsi.zzcr(context).zzik(context.getPackageName()).toString();
            return s;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return s;
        }
    }
    
    @Nullable
    public static String zzg(final Context context, final int n) {
        final Resources resources = context.getResources();
        String s = null;
        switch (n) {
            default: {
                Log.e("GoogleApiAvailability", new StringBuilder(33).append("Unexpected error code ").append(n).toString());
                return s;
            }
            case 20: {
                Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
                s = zzu(context, "common_google_play_services_restricted_profile_title");
                return s;
            }
            case 17: {
                Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
                s = zzu(context, "common_google_play_services_sign_in_failed_title");
                return s;
            }
            case 16: {
                Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
                s = null;
                return s;
            }
            case 11: {
                Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
                s = null;
                return s;
            }
            case 5: {
                Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
                s = zzu(context, "common_google_play_services_invalid_account_title");
                return s;
            }
            case 10: {
                Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
                s = null;
                return s;
            }
            case 8: {
                Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
                s = null;
                return s;
            }
            case 7: {
                Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
                s = zzu(context, "common_google_play_services_network_error_title");
                return s;
            }
            case 9: {
                Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
                s = resources.getString(R.string.common_google_play_services_unsupported_title);
                return s;
            }
            case 2:
            case 42: {
                s = resources.getString(R.string.common_google_play_services_update_title);
                return s;
            }
            case 18: {
                s = resources.getString(R.string.common_google_play_services_updating_title);
                return s;
            }
            case 3: {
                s = resources.getString(R.string.common_google_play_services_enable_title);
                return s;
            }
            case 1: {
                s = resources.getString(R.string.common_google_play_services_install_title);
            }
            case 4:
            case 6: {
                return s;
            }
        }
    }
    
    private static String zzg(final Context context, final String s, final String s2) {
        final Resources resources = context.getResources();
        String s3 = zzu(context, s);
        if (s3 == null) {
            s3 = resources.getString(R.string.common_google_play_services_unknown_issue);
        }
        return String.format(resources.getConfiguration().locale, s3, s2);
    }
    
    @NonNull
    public static String zzh(final Context context, final int n) {
        String s;
        if (n == 6) {
            s = zzu(context, "common_google_play_services_resolution_required_title");
        }
        else {
            s = zzg(context, n);
        }
        if (s == null) {
            s = context.getResources().getString(R.string.common_google_play_services_notification_ticker);
        }
        return s;
    }
    
    @NonNull
    public static String zzi(final Context context, final int n) {
        final Resources resources = context.getResources();
        final String zzce = zzce(context);
        String s = null;
        switch (n) {
            default: {
                s = resources.getString(R.string.common_google_play_services_unknown_issue, new Object[] { zzce });
                break;
            }
            case 1: {
                if (com.google.android.gms.common.util.zzi.zzb(resources)) {
                    s = resources.getString(R.string.common_google_play_services_install_text_tablet, new Object[] { zzce });
                    break;
                }
                s = resources.getString(R.string.common_google_play_services_install_text_phone, new Object[] { zzce });
                break;
            }
            case 3: {
                s = resources.getString(R.string.common_google_play_services_enable_text, new Object[] { zzce });
                break;
            }
            case 18: {
                s = resources.getString(R.string.common_google_play_services_updating_text, new Object[] { zzce });
                break;
            }
            case 2: {
                s = resources.getString(R.string.common_google_play_services_update_text, new Object[] { zzce });
                break;
            }
            case 42: {
                s = resources.getString(R.string.common_google_play_services_wear_update_text);
                break;
            }
            case 9: {
                s = resources.getString(R.string.common_google_play_services_unsupported_text, new Object[] { zzce });
                break;
            }
            case 7: {
                s = zzg(context, "common_google_play_services_network_error_text", zzce);
                break;
            }
            case 5: {
                s = zzg(context, "common_google_play_services_invalid_account_text", zzce);
                break;
            }
            case 16: {
                s = zzg(context, "common_google_play_services_api_unavailable_text", zzce);
                break;
            }
            case 17: {
                s = zzg(context, "common_google_play_services_sign_in_failed_text", zzce);
                break;
            }
            case 20: {
                s = zzg(context, "common_google_play_services_restricted_profile_text", zzce);
                break;
            }
        }
        return s;
    }
    
    @NonNull
    public static String zzj(final Context context, final int n) {
        String s;
        if (n == 6) {
            s = zzg(context, "common_google_play_services_resolution_required_text", zzce(context));
        }
        else {
            s = zzi(context, n);
        }
        return s;
    }
    
    @NonNull
    public static String zzk(final Context context, final int n) {
        final Resources resources = context.getResources();
        String s = null;
        switch (n) {
            default: {
                s = resources.getString(17039370);
                break;
            }
            case 1: {
                s = resources.getString(R.string.common_google_play_services_install_button);
                break;
            }
            case 3: {
                s = resources.getString(R.string.common_google_play_services_enable_button);
                break;
            }
            case 2: {
                s = resources.getString(R.string.common_google_play_services_update_button);
                break;
            }
        }
        return s;
    }
    
    @Nullable
    private static String zzu(final Context context, final String s) {
        final Resources remoteResource;
        final int identifier;
        synchronized (zzi.Cc) {
            String string = zzi.Cc.get(s);
            if (string != null) {
                return string;
            }
            remoteResource = GooglePlayServicesUtil.getRemoteResource(context);
            if (remoteResource == null) {
                // monitorexit(zzi.Cc)
                string = null;
                return string;
            }
            identifier = remoteResource.getIdentifier(s, "string", "com.google.android.gms");
            if (identifier == 0) {
                final String value = String.valueOf(s);
                String concat;
                if (value.length() != 0) {
                    concat = "Missing resource: ".concat(value);
                }
                else {
                    concat = new String("Missing resource: ");
                }
                Log.w("GoogleApiAvailability", concat);
                // monitorexit(zzi.Cc)
                string = null;
                return string;
            }
        }
        String string = remoteResource.getString(identifier);
        if (TextUtils.isEmpty((CharSequence)string)) {
            final String value2 = String.valueOf(s);
            String concat2;
            if (value2.length() != 0) {
                concat2 = "Got empty resource: ".concat(value2);
            }
            else {
                concat2 = new String("Got empty resource: ");
            }
            Log.w("GoogleApiAvailability", concat2);
            // monitorexit(simpleArrayMap)
            string = null;
        }
        else {
            zzi.Cc.put(s, string);
        }
        // monitorexit(simpleArrayMap)
        return string;
    }
}
