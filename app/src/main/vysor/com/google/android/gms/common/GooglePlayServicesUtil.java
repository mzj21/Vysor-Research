// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.util.Log;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;
import android.content.Intent;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.os.Build$VERSION;
import android.app.Notification$Style;
import android.app.Notification$BigTextStyle;
import com.google.android.gms.R;
import android.app.Notification$Builder;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.common.internal.zzj;
import android.support.v4.app.Fragment;
import android.content.res.Resources;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface$OnCancelListener;
import android.app.Dialog;
import android.app.Activity;

public final class GooglePlayServicesUtil extends zze
{
    public static final String GMS_ERROR_DIALOG = "GooglePlayServicesErrorDialog";
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 0;
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    
    @Deprecated
    public static Dialog getErrorDialog(final int n, final Activity activity, final int n2) {
        return getErrorDialog(n, activity, n2, null);
    }
    
    @Deprecated
    public static Dialog getErrorDialog(int n, final Activity activity, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        if (zzd((Context)activity, n)) {
            n = 18;
        }
        return GoogleApiAvailability.getInstance().getErrorDialog(activity, n, n2, dialogInterface$OnCancelListener);
    }
    
    @Deprecated
    public static PendingIntent getErrorPendingIntent(final int n, final Context context, final int n2) {
        return zze.getErrorPendingIntent(n, context, n2);
    }
    
    @Deprecated
    public static String getErrorString(final int n) {
        return zze.getErrorString(n);
    }
    
    @Deprecated
    public static String getOpenSourceSoftwareLicenseInfo(final Context context) {
        return zze.getOpenSourceSoftwareLicenseInfo(context);
    }
    
    public static Context getRemoteContext(final Context context) {
        return zze.getRemoteContext(context);
    }
    
    public static Resources getRemoteResource(final Context context) {
        return zze.getRemoteResource(context);
    }
    
    @Deprecated
    public static int isGooglePlayServicesAvailable(final Context context) {
        return zze.isGooglePlayServicesAvailable(context);
    }
    
    @Deprecated
    public static boolean isUserRecoverableError(final int n) {
        return zze.isUserRecoverableError(n);
    }
    
    @Deprecated
    public static boolean showErrorDialogFragment(final int n, final Activity activity, final int n2) {
        return showErrorDialogFragment(n, activity, n2, null);
    }
    
    @Deprecated
    public static boolean showErrorDialogFragment(final int n, final Activity activity, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        return showErrorDialogFragment(n, activity, null, n2, dialogInterface$OnCancelListener);
    }
    
    public static boolean showErrorDialogFragment(int n, final Activity activity, final Fragment fragment, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        if (zzd((Context)activity, n)) {
            n = 18;
        }
        final GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        boolean showErrorDialogFragment;
        if (fragment == null) {
            showErrorDialogFragment = instance.showErrorDialogFragment(activity, n, n2, dialogInterface$OnCancelListener);
        }
        else {
            final Dialog zza = instance.zza((Context)activity, n, zzj.zza(fragment, GoogleApiAvailability.getInstance().zza((Context)activity, n, "d"), n2), dialogInterface$OnCancelListener);
            if (zza == null) {
                showErrorDialogFragment = false;
            }
            else {
                instance.zza(activity, zza, "GooglePlayServicesErrorDialog", dialogInterface$OnCancelListener);
                showErrorDialogFragment = true;
            }
        }
        return showErrorDialogFragment;
    }
    
    @Deprecated
    public static void showErrorNotification(int n, final Context context) {
        if (zzi.zzcl(context) && n == 2) {
            n = 42;
        }
        if (zzd(context, n) || zze(context, n)) {
            zzbs(context);
        }
        else {
            zza(n, context);
        }
    }
    
    private static void zza(final int n, final Context context) {
        zza(n, context, (String)null);
    }
    
    static void zza(final int n, final Context context, final PendingIntent pendingIntent) {
        zza(n, context, null, pendingIntent);
    }
    
    private static void zza(final int n, final Context context, final String s) {
        zza(n, context, s, GoogleApiAvailability.getInstance().zza(context, n, 0, "n"));
    }
    
    @TargetApi(20)
    private static void zza(final int n, final Context context, final String s, final PendingIntent pendingIntent) {
        final Resources resources = context.getResources();
        final String zzh = com.google.android.gms.common.internal.zzi.zzh(context, n);
        final String zzj = com.google.android.gms.common.internal.zzi.zzj(context, n);
        Notification notification;
        if (zzi.zzcl(context)) {
            zzac.zzbr(zzs.zzaxo());
            notification = new Notification$Builder(context).setSmallIcon(R.drawable.common_ic_googleplayservices).setPriority(2).setAutoCancel(true).setStyle((Notification$Style)new Notification$BigTextStyle().bigText((CharSequence)new StringBuilder(1 + String.valueOf(zzh).length() + String.valueOf(zzj).length()).append(zzh).append(" ").append(zzj).toString())).addAction(R.drawable.common_full_open_on_phone, (CharSequence)resources.getString(R.string.common_open_on_phone), pendingIntent).build();
        }
        else {
            final String string = resources.getString(R.string.common_google_play_services_notification_ticker);
            if (zzs.zzaxk()) {
                final Notification$Builder setAutoCancel = new Notification$Builder(context).setSmallIcon(17301642).setContentTitle((CharSequence)zzh).setContentText((CharSequence)zzj).setContentIntent(pendingIntent).setTicker((CharSequence)string).setAutoCancel(true);
                if (zzs.zzaxs()) {
                    setAutoCancel.setLocalOnly(true);
                }
                Notification notification2;
                if (zzs.zzaxo()) {
                    setAutoCancel.setStyle((Notification$Style)new Notification$BigTextStyle().bigText((CharSequence)zzj));
                    notification2 = setAutoCancel.build();
                }
                else {
                    notification2 = setAutoCancel.getNotification();
                }
                if (Build$VERSION.SDK_INT == 19) {
                    notification2.extras.putBoolean("android.support.localOnly", true);
                }
                notification = notification2;
            }
            else {
                notification = new NotificationCompat.Builder(context).setSmallIcon(17301642).setTicker(string).setWhen(System.currentTimeMillis()).setAutoCancel(true).setContentIntent(pendingIntent).setContentTitle(zzh).setContentText(zzj).build();
            }
        }
        int n2;
        if (zze.zzfn(n)) {
            GooglePlayServicesUtil.vd.set(false);
            n2 = 10436;
        }
        else {
            n2 = 39789;
        }
        final NotificationManager notificationManager = (NotificationManager)context.getSystemService("notification");
        if (s != null) {
            notificationManager.notify(s, n2, notification);
        }
        else {
            notificationManager.notify(n2, notification);
        }
    }
    
    private static void zzbs(final Context context) {
        final zza zza = new zza(context);
        zza.sendMessageDelayed(zza.obtainMessage(1), 120000L);
    }
    
    @Deprecated
    public static boolean zzd(final Context context, final int n) {
        return zze.zzd(context, n);
    }
    
    @Deprecated
    public static boolean zze(final Context context, final int n) {
        return zze.zze(context, n);
    }
    
    @Deprecated
    public static Intent zzfm(final int n) {
        return zze.zzfm(n);
    }
    
    private static class zza extends Handler
    {
        private final Context zzask;
        
        zza(final Context context) {
            Looper looper;
            if (Looper.myLooper() == null) {
                looper = Looper.getMainLooper();
            }
            else {
                looper = Looper.myLooper();
            }
            super(looper);
            this.zzask = context.getApplicationContext();
        }
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {
                    Log.w("GooglePlayServicesUtil", new StringBuilder(50).append("Don't know how to handle this message: ").append(message.what).toString());
                    break;
                }
                case 1: {
                    final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.zzask);
                    if (GooglePlayServicesUtil.isUserRecoverableError(googlePlayServicesAvailable)) {
                        zza(googlePlayServicesAvailable, this.zzask);
                        break;
                    }
                    break;
                }
            }
        }
    }
}
