// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Parcel;
import android.content.IntentSender$SendIntentException;
import android.content.Intent;
import android.app.Activity;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzab;
import android.app.PendingIntent;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class Status extends AbstractSafeParcelable implements Result, ReflectedParcelable
{
    public static final Parcelable$Creator<Status> CREATOR;
    public static final Status vY;
    public static final Status vZ;
    public static final Status wa;
    public static final Status wb;
    public static final Status wc;
    public static final Status wd;
    public static final Status we;
    private final PendingIntent mPendingIntent;
    private final int mVersionCode;
    private final int rR;
    private final String uK;
    
    static {
        vY = new Status(0);
        vZ = new Status(14);
        wa = new Status(8);
        wb = new Status(15);
        wc = new Status(16);
        wd = new Status(17);
        we = new Status(18);
        CREATOR = (Parcelable$Creator)new zzh();
    }
    
    public Status(final int n) {
        this(n, null);
    }
    
    Status(final int mVersionCode, final int rr, final String uk, final PendingIntent mPendingIntent) {
        this.mVersionCode = mVersionCode;
        this.rR = rr;
        this.uK = uk;
        this.mPendingIntent = mPendingIntent;
    }
    
    public Status(final int n, final String s) {
        this(1, n, s, null);
    }
    
    public Status(final int n, final String s, final PendingIntent pendingIntent) {
        this(1, n, s, pendingIntent);
    }
    
    private String zzaqi() {
        String s;
        if (this.uK != null) {
            s = this.uK;
        }
        else {
            s = CommonStatusCodes.getStatusCodeString(this.rR);
        }
        return s;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = o instanceof Status;
        boolean b2 = false;
        if (b) {
            final Status status = (Status)o;
            final int mVersionCode = this.mVersionCode;
            final int mVersionCode2 = status.mVersionCode;
            b2 = false;
            if (mVersionCode == mVersionCode2) {
                final int rr = this.rR;
                final int rr2 = status.rR;
                b2 = false;
                if (rr == rr2) {
                    final boolean equal = zzab.equal(this.uK, status.uK);
                    b2 = false;
                    if (equal) {
                        final boolean equal2 = zzab.equal(this.mPendingIntent, status.mPendingIntent);
                        b2 = false;
                        if (equal2) {
                            b2 = true;
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    public PendingIntent getResolution() {
        return this.mPendingIntent;
    }
    
    @Override
    public Status getStatus() {
        return this;
    }
    
    public int getStatusCode() {
        return this.rR;
    }
    
    @Nullable
    public String getStatusMessage() {
        return this.uK;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    public boolean hasResolution() {
        return this.mPendingIntent != null;
    }
    
    @Override
    public int hashCode() {
        return zzab.hashCode(this.mVersionCode, this.rR, this.uK, this.mPendingIntent);
    }
    
    public boolean isCanceled() {
        return this.rR == 16;
    }
    
    public boolean isInterrupted() {
        return this.rR == 14;
    }
    
    public boolean isSuccess() {
        return this.rR <= 0;
    }
    
    public void startResolutionForResult(final Activity activity, final int n) throws IntentSender$SendIntentException {
        if (this.hasResolution()) {
            activity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), n, (Intent)null, 0, 0, 0);
        }
    }
    
    @Override
    public String toString() {
        return zzab.zzx(this).zzg("statusCode", this.zzaqi()).zzg("resolution", this.mPendingIntent).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzh.zza(this, parcel, n);
    }
    
    PendingIntent zzaqh() {
        return this.mPendingIntent;
    }
}
