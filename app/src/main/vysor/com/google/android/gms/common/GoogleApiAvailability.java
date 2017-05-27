// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.support.annotation.NonNull;
import com.google.android.gms.internal.zzrb;
import com.google.android.gms.common.api.GoogleApiActivity;
import android.support.v4.app.FragmentActivity;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.google.android.gms.internal.zzqv;
import android.content.Intent;
import android.annotation.TargetApi;
import android.util.TypedValue;
import com.google.android.gms.common.util.zzs;
import android.app.AlertDialog;
import android.content.DialogInterface$OnClickListener;
import android.view.View;
import android.app.AlertDialog$Builder;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.util.Log;
import android.support.annotation.MainThread;
import com.google.android.gms.internal.zzrf;
import com.google.android.gms.tasks.Tasks;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.util.zzi;
import android.support.annotation.Nullable;
import android.app.PendingIntent;
import com.google.android.gms.common.internal.zzj;
import android.content.Context;
import android.content.DialogInterface$OnCancelListener;
import android.app.Dialog;
import android.app.Activity;

public class GoogleApiAvailability extends zzc
{
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final GoogleApiAvailability uM;
    
    static {
        uM = new GoogleApiAvailability();
        GOOGLE_PLAY_SERVICES_VERSION_CODE = zzc.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }
    
    public static GoogleApiAvailability getInstance() {
        return GoogleApiAvailability.uM;
    }
    
    public Dialog getErrorDialog(final Activity activity, final int n, final int n2) {
        return this.getErrorDialog(activity, n, n2, null);
    }
    
    public Dialog getErrorDialog(final Activity activity, final int n, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        return this.zza((Context)activity, n, zzj.zza(activity, this.zza((Context)activity, n, "d"), n2), dialogInterface$OnCancelListener);
    }
    
    @Nullable
    @Override
    public PendingIntent getErrorResolutionPendingIntent(final Context context, final int n, final int n2) {
        return super.getErrorResolutionPendingIntent(context, n, n2);
    }
    
    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(final Context context, final ConnectionResult connectionResult) {
        PendingIntent pendingIntent;
        if (connectionResult.hasResolution()) {
            pendingIntent = connectionResult.getResolution();
        }
        else {
            int errorCode = connectionResult.getErrorCode();
            if (zzi.zzcl(context) && errorCode == 2) {
                errorCode = 42;
            }
            pendingIntent = this.getErrorResolutionPendingIntent(context, errorCode, 0);
        }
        return pendingIntent;
    }
    
    @Override
    public final String getErrorString(final int n) {
        return super.getErrorString(n);
    }
    
    @Nullable
    @Override
    public String getOpenSourceSoftwareLicenseInfo(final Context context) {
        return super.getOpenSourceSoftwareLicenseInfo(context);
    }
    
    @Override
    public int isGooglePlayServicesAvailable(final Context context) {
        return super.isGooglePlayServicesAvailable(context);
    }
    
    @Override
    public final boolean isUserResolvableError(final int n) {
        return super.isUserResolvableError(n);
    }
    
    @MainThread
    public Task<Void> makeGooglePlayServicesAvailable(final Activity activity) {
        zzac.zzhq("makeGooglePlayServicesAvailable must be called from the main thread");
        final int googlePlayServicesAvailable = this.isGooglePlayServicesAvailable((Context)activity);
        Task<Void> task;
        if (googlePlayServicesAvailable == 0) {
            task = Tasks.forResult((Void)null);
        }
        else {
            final zzrf zzu = zzrf.zzu(activity);
            zzu.zzk(new ConnectionResult(googlePlayServicesAvailable, null));
            task = zzu.getTask();
        }
        return task;
    }
    
    public boolean showErrorDialogFragment(final Activity activity, final int n, final int n2) {
        return this.showErrorDialogFragment(activity, n, n2, null);
    }
    
    public boolean showErrorDialogFragment(final Activity activity, final int n, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        final Dialog errorDialog = this.getErrorDialog(activity, n, n2, dialogInterface$OnCancelListener);
        boolean b;
        if (errorDialog == null) {
            b = false;
        }
        else {
            this.zza(activity, errorDialog, "GooglePlayServicesErrorDialog", dialogInterface$OnCancelListener);
            b = true;
        }
        return b;
    }
    
    public void showErrorNotification(final Context context, final int n) {
        if (n == 6) {
            Log.e("GoogleApiAvailability", "showErrorNotification(context, errorCode) is called for RESOLUTION_REQUIRED when showErrorNotification(context, result) should be called");
        }
        if (this.isUserResolvableError(n)) {
            GooglePlayServicesUtil.showErrorNotification(n, context);
        }
    }
    
    public void showErrorNotification(final Context context, final ConnectionResult connectionResult) {
        final PendingIntent errorResolutionPendingIntent = this.getErrorResolutionPendingIntent(context, connectionResult);
        if (errorResolutionPendingIntent != null) {
            GooglePlayServicesUtil.zza(connectionResult.getErrorCode(), context, errorResolutionPendingIntent);
        }
    }
    
    public Dialog zza(final Activity activity, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        final ProgressBar view = new ProgressBar((Context)activity, (AttributeSet)null, 16842874);
        view.setIndeterminate(true);
        view.setVisibility(0);
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)activity);
        alertDialog$Builder.setView((View)view);
        alertDialog$Builder.setMessage((CharSequence)com.google.android.gms.common.internal.zzi.zzi((Context)activity, 18));
        alertDialog$Builder.setTitle((CharSequence)com.google.android.gms.common.internal.zzi.zzg((Context)activity, 18));
        alertDialog$Builder.setPositiveButton((CharSequence)"", (DialogInterface$OnClickListener)null);
        final AlertDialog create = alertDialog$Builder.create();
        this.zza(activity, (Dialog)create, "GooglePlayServicesUpdatingDialog", dialogInterface$OnCancelListener);
        return (Dialog)create;
    }
    
    @TargetApi(14)
    Dialog zza(final Context context, int n, final zzj zzj, final DialogInterface$OnCancelListener onCancelListener) {
        Object create = null;
        if (n != 0) {
            if (zzi.zzcl(context) && n == 2) {
                n = 42;
            }
            final boolean zzaxn = zzs.zzaxn();
            AlertDialog$Builder alertDialog$Builder = null;
            if (zzaxn) {
                final TypedValue typedValue = new TypedValue();
                context.getTheme().resolveAttribute(16843529, typedValue, true);
                final boolean equals = "Theme.Dialog.Alert".equals(context.getResources().getResourceEntryName(typedValue.resourceId));
                alertDialog$Builder = null;
                if (equals) {
                    alertDialog$Builder = new AlertDialog$Builder(context, 5);
                }
            }
            if (alertDialog$Builder == null) {
                alertDialog$Builder = new AlertDialog$Builder(context);
            }
            alertDialog$Builder.setMessage((CharSequence)com.google.android.gms.common.internal.zzi.zzi(context, n));
            if (onCancelListener != null) {
                alertDialog$Builder.setOnCancelListener(onCancelListener);
            }
            final String zzk = com.google.android.gms.common.internal.zzi.zzk(context, n);
            if (zzk != null) {
                alertDialog$Builder.setPositiveButton((CharSequence)zzk, (DialogInterface$OnClickListener)zzj);
            }
            final String zzg = com.google.android.gms.common.internal.zzi.zzg(context, n);
            if (zzg != null) {
                alertDialog$Builder.setTitle((CharSequence)zzg);
            }
            create = alertDialog$Builder.create();
        }
        return (Dialog)create;
    }
    
    @Nullable
    @Override
    public PendingIntent zza(final Context context, final int n, final int n2, @Nullable final String s) {
        return super.zza(context, n, n2, s);
    }
    
    @Nullable
    @Override
    public Intent zza(final Context context, final int n, @Nullable final String s) {
        return super.zza(context, n, s);
    }
    
    @Nullable
    public zzqv zza(final Context context, final zzqv.zza zza) {
        final IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        zzqv zzqv = new zzqv(zza);
        context.registerReceiver((BroadcastReceiver)zzqv, intentFilter);
        zzqv.setContext(context);
        if (!this.zzs(context, "com.google.android.gms")) {
            zza.zzaqp();
            zzqv.unregister();
            zzqv = null;
        }
        return zzqv;
    }
    
    @TargetApi(11)
    void zza(final Activity activity, final Dialog dialog, final String s, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        while (true) {
            while (true) {
                try {
                    final int n = (activity instanceof FragmentActivity) ? 1 : 0;
                    if (n != 0) {
                        SupportErrorDialogFragment.newInstance(dialog, dialogInterface$OnCancelListener).show(((FragmentActivity)activity).getSupportFragmentManager(), s);
                        return;
                    }
                }
                catch (NoClassDefFoundError noClassDefFoundError) {
                    final int n = 0;
                    continue;
                }
                break;
            }
            if (zzs.zzaxk()) {
                ErrorDialogFragment.newInstance(dialog, dialogInterface$OnCancelListener).show(activity.getFragmentManager(), s);
                return;
            }
            break;
        }
        throw new RuntimeException("This Activity does not support Fragments.");
    }
    
    public void zza(final Context context, final ConnectionResult connectionResult, final int n) {
        final PendingIntent errorResolutionPendingIntent = this.getErrorResolutionPendingIntent(context, connectionResult);
        if (errorResolutionPendingIntent != null) {
            GooglePlayServicesUtil.zza(connectionResult.getErrorCode(), context, GoogleApiActivity.zza(context, errorResolutionPendingIntent, n));
        }
    }
    
    public boolean zza(final Activity activity, @NonNull final zzrb zzrb, final int n, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        final Dialog zza = this.zza((Context)activity, n, zzj.zza(zzrb, this.zza((Context)activity, n, "d"), n2), dialogInterface$OnCancelListener);
        boolean b;
        if (zza == null) {
            b = false;
        }
        else {
            this.zza(activity, zza, "GooglePlayServicesErrorDialog", dialogInterface$OnCancelListener);
            b = true;
        }
        return b;
    }
    
    @Override
    public int zzbo(final Context context) {
        return super.zzbo(context);
    }
    
    @Override
    public boolean zzd(final Context context, final int n) {
        return super.zzd(context, n);
    }
    
    @Deprecated
    @Nullable
    @Override
    public Intent zzfl(final int n) {
        return super.zzfl(n);
    }
}
