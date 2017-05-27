// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.os.Parcel;
import android.content.IntentSender$SendIntentException;
import android.content.Intent;
import android.app.Activity;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzab;
import android.app.PendingIntent;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class ConnectionResult extends AbstractSafeParcelable
{
    public static final int API_UNAVAILABLE = 16;
    public static final int CANCELED = 13;
    public static final Parcelable$Creator<ConnectionResult> CREATOR;
    public static final int DEVELOPER_ERROR = 10;
    @Deprecated
    public static final int DRIVE_EXTERNAL_STORAGE_REQUIRED = 1500;
    public static final int INTERNAL_ERROR = 8;
    public static final int INTERRUPTED = 15;
    public static final int INVALID_ACCOUNT = 5;
    public static final int LICENSE_CHECK_FAILED = 11;
    public static final int NETWORK_ERROR = 7;
    public static final int RESOLUTION_REQUIRED = 6;
    public static final int RESTRICTED_PROFILE = 20;
    public static final int SERVICE_DISABLED = 3;
    public static final int SERVICE_INVALID = 9;
    public static final int SERVICE_MISSING = 1;
    public static final int SERVICE_MISSING_PERMISSION = 19;
    public static final int SERVICE_UPDATING = 18;
    public static final int SERVICE_VERSION_UPDATE_REQUIRED = 2;
    public static final int SIGN_IN_FAILED = 17;
    public static final int SIGN_IN_REQUIRED = 4;
    public static final int SUCCESS = 0;
    public static final int TIMEOUT = 14;
    public static final ConnectionResult uJ;
    private final PendingIntent mPendingIntent;
    final int mVersionCode;
    private final int rR;
    private final String uK;
    
    static {
        uJ = new ConnectionResult(0);
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    public ConnectionResult(final int n) {
        this(n, null, null);
    }
    
    ConnectionResult(final int mVersionCode, final int rr, final PendingIntent mPendingIntent, final String uk) {
        this.mVersionCode = mVersionCode;
        this.rR = rr;
        this.mPendingIntent = mPendingIntent;
        this.uK = uk;
    }
    
    public ConnectionResult(final int n, final PendingIntent pendingIntent) {
        this(n, pendingIntent, null);
    }
    
    public ConnectionResult(final int n, final PendingIntent pendingIntent, final String s) {
        this(1, n, pendingIntent, s);
    }
    
    static String getStatusString(final int n) {
        String string = null;
        switch (n) {
            default: {
                string = new StringBuilder(31).append("UNKNOWN_ERROR_CODE(").append(n).append(")").toString();
                break;
            }
            case 0: {
                string = "SUCCESS";
                break;
            }
            case 1: {
                string = "SERVICE_MISSING";
                break;
            }
            case 2: {
                string = "SERVICE_VERSION_UPDATE_REQUIRED";
                break;
            }
            case 3: {
                string = "SERVICE_DISABLED";
                break;
            }
            case 4: {
                string = "SIGN_IN_REQUIRED";
                break;
            }
            case 5: {
                string = "INVALID_ACCOUNT";
                break;
            }
            case 6: {
                string = "RESOLUTION_REQUIRED";
                break;
            }
            case 7: {
                string = "NETWORK_ERROR";
                break;
            }
            case 8: {
                string = "INTERNAL_ERROR";
                break;
            }
            case 9: {
                string = "SERVICE_INVALID";
                break;
            }
            case 10: {
                string = "DEVELOPER_ERROR";
                break;
            }
            case 11: {
                string = "LICENSE_CHECK_FAILED";
                break;
            }
            case 13: {
                string = "CANCELED";
                break;
            }
            case 14: {
                string = "TIMEOUT";
                break;
            }
            case 15: {
                string = "INTERRUPTED";
                break;
            }
            case 16: {
                string = "API_UNAVAILABLE";
                break;
            }
            case 17: {
                string = "SIGN_IN_FAILED";
                break;
            }
            case 18: {
                string = "SERVICE_UPDATING";
                break;
            }
            case 19: {
                string = "SERVICE_MISSING_PERMISSION";
                break;
            }
            case 20: {
                string = "RESTRICTED_PROFILE";
                break;
            }
            case 21: {
                string = "API_VERSION_UPDATE_REQUIRED";
                break;
            }
            case 42: {
                string = "UPDATE_ANDROID_WEAR";
                break;
            }
            case 1500: {
                string = "DRIVE_EXTERNAL_STORAGE_REQUIRED";
                break;
            }
            case 99: {
                string = "UNFINISHED";
                break;
            }
            case -1: {
                string = "UNKNOWN";
                break;
            }
        }
        return string;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        if (o != this) {
            if (!(o instanceof ConnectionResult)) {
                b = false;
            }
            else {
                final ConnectionResult connectionResult = (ConnectionResult)o;
                if (this.rR != connectionResult.rR || !zzab.equal(this.mPendingIntent, connectionResult.mPendingIntent) || !zzab.equal(this.uK, connectionResult.uK)) {
                    b = false;
                }
            }
        }
        return b;
    }
    
    public int getErrorCode() {
        return this.rR;
    }
    
    @Nullable
    public String getErrorMessage() {
        return this.uK;
    }
    
    @Nullable
    public PendingIntent getResolution() {
        return this.mPendingIntent;
    }
    
    public boolean hasResolution() {
        return this.rR != 0 && this.mPendingIntent != null;
    }
    
    @Override
    public int hashCode() {
        return zzab.hashCode(this.rR, this.mPendingIntent, this.uK);
    }
    
    public boolean isSuccess() {
        return this.rR == 0;
    }
    
    public void startResolutionForResult(final Activity activity, final int n) throws IntentSender$SendIntentException {
        if (this.hasResolution()) {
            activity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), n, (Intent)null, 0, 0, 0);
        }
    }
    
    @Override
    public String toString() {
        return zzab.zzx(this).zzg("statusCode", getStatusString(this.rR)).zzg("resolution", this.mPendingIntent).zzg("message", this.uK).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
}
