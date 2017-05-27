// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.content.ActivityNotFoundException;
import android.util.Log;
import android.content.DialogInterface;
import android.annotation.TargetApi;
import com.google.android.gms.internal.zzrb;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.app.Activity;
import android.content.DialogInterface$OnClickListener;

public abstract class zzj implements DialogInterface$OnClickListener
{
    public static zzj zza(final Activity activity, final Intent intent, final int n) {
        return new zzj() {
            @Override
            public void zzauo() {
                if (intent != null) {
                    activity.startActivityForResult(intent, n);
                }
            }
        };
    }
    
    public static zzj zza(@NonNull final Fragment fragment, final Intent intent, final int n) {
        return new zzj() {
            @Override
            public void zzauo() {
                if (intent != null) {
                    fragment.startActivityForResult(intent, n);
                }
            }
        };
    }
    
    public static zzj zza(@NonNull final zzrb zzrb, final Intent intent, final int n) {
        return new zzj() {
            @TargetApi(11)
            @Override
            public void zzauo() {
                if (intent != null) {
                    zzrb.startActivityForResult(intent, n);
                }
            }
        };
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        try {
            this.zzauo();
            dialogInterface.dismiss();
        }
        catch (ActivityNotFoundException ex) {
            Log.e("DialogRedirect", "Can't redirect to app settings for Google Play services", (Throwable)ex);
        }
    }
    
    public abstract void zzauo();
}
