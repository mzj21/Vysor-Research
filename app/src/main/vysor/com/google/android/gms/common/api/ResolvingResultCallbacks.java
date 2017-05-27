// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.content.IntentSender$SendIntentException;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import android.support.annotation.NonNull;
import android.app.Activity;

public abstract class ResolvingResultCallbacks<R extends Result> extends ResultCallbacks<R>
{
    private final Activity mActivity;
    private final int vV;
    
    protected ResolvingResultCallbacks(@NonNull final Activity activity, final int vv) {
        this.mActivity = zzac.zzb(activity, "Activity must not be null");
        this.vV = vv;
    }
    
    @Override
    public final void onFailure(@NonNull final Status status) {
        Label_0046: {
            if (!status.hasResolution()) {
                break Label_0046;
            }
            try {
                status.startResolutionForResult(this.mActivity, this.vV);
                return;
            }
            catch (IntentSender$SendIntentException ex) {
                Log.e("ResolvingResultCallback", "Failed to start resolution", (Throwable)ex);
                this.onUnresolvableFailure(new Status(8));
                return;
            }
        }
        this.onUnresolvableFailure(status);
    }
    
    @Override
    public abstract void onSuccess(@NonNull final R p0);
    
    public abstract void onUnresolvableFailure(@NonNull final Status p0);
}
