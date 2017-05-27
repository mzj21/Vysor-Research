// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.support.annotation.MainThread;
import android.app.Dialog;
import com.google.android.gms.common.api.GoogleApiActivity;
import android.os.Parcelable;
import android.os.Bundle;
import android.content.DialogInterface;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import android.os.Handler;
import com.google.android.gms.common.GoogleApiAvailability;
import android.content.DialogInterface$OnCancelListener;

public abstract class zzqd extends zzra implements DialogInterface$OnCancelListener
{
    protected boolean mStarted;
    protected final GoogleApiAvailability vP;
    private int wA;
    private final Handler wB;
    protected boolean wy;
    private ConnectionResult wz;
    
    protected zzqd(final zzrb zzrb) {
        this(zzrb, GoogleApiAvailability.getInstance());
    }
    
    zzqd(final zzrb zzrb, final GoogleApiAvailability vp) {
        super(zzrb);
        this.wA = -1;
        this.wB = new Handler(Looper.getMainLooper());
        this.vP = vp;
    }
    
    @Override
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        int n3 = 1;
        Label_0031: {
            switch (n) {
                case 2: {
                    final int googlePlayServicesAvailable = this.vP.isGooglePlayServicesAvailable((Context)this.getActivity());
                    if (googlePlayServicesAvailable != 0) {
                        n3 = 0;
                    }
                    if (this.wz.getErrorCode() == 18 && googlePlayServicesAvailable == 18) {
                        return;
                    }
                    break Label_0031;
                }
                case 1: {
                    if (n2 == -1) {
                        break Label_0031;
                    }
                    if (n2 == 0) {
                        int intExtra;
                        if (intent != null) {
                            intExtra = intent.getIntExtra("<<ResolutionFailureErrorDetail>>", 13);
                        }
                        else {
                            intExtra = 13;
                        }
                        this.wz = new ConnectionResult(intExtra, null);
                        break;
                    }
                    break;
                }
            }
            n3 = 0;
        }
        if (n3 != 0) {
            this.zzaqo();
        }
        else {
            this.zza(this.wz, this.wA);
        }
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        this.zza(new ConnectionResult(13, null), this.wA);
        this.zzaqo();
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.wy = bundle.getBoolean("resolving_error", false);
            if (this.wy) {
                this.wA = bundle.getInt("failed_client_id", -1);
                this.wz = new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent)bundle.getParcelable("failed_resolution"));
            }
        }
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("resolving_error", this.wy);
        if (this.wy) {
            bundle.putInt("failed_client_id", this.wA);
            bundle.putInt("failed_status", this.wz.getErrorCode());
            bundle.putParcelable("failed_resolution", (Parcelable)this.wz.getResolution());
        }
    }
    
    @Override
    public void onStart() {
        super.onStart();
        this.mStarted = true;
    }
    
    @Override
    public void onStop() {
        super.onStop();
        this.mStarted = false;
    }
    
    protected abstract void zza(final ConnectionResult p0, final int p1);
    
    protected abstract void zzaqk();
    
    protected void zzaqo() {
        this.wA = -1;
        this.wy = false;
        this.wz = null;
        this.zzaqk();
    }
    
    public void zzb(final ConnectionResult wz, final int wa) {
        if (!this.wy) {
            this.wy = true;
            this.wA = wa;
            this.wz = wz;
            this.wB.post((Runnable)new zza());
        }
    }
    
    private class zza implements Runnable
    {
        @MainThread
        @Override
        public void run() {
            if (zzqd.this.mStarted) {
                if (zzqd.this.wz.hasResolution()) {
                    zzqd.this.yY.startActivityForResult(GoogleApiActivity.zzb((Context)zzqd.this.getActivity(), zzqd.this.wz.getResolution(), zzqd.this.wA, false), 1);
                }
                else if (zzqd.this.vP.isUserResolvableError(zzqd.this.wz.getErrorCode())) {
                    zzqd.this.vP.zza(zzqd.this.getActivity(), zzqd.this.yY, zzqd.this.wz.getErrorCode(), 2, (DialogInterface$OnCancelListener)zzqd.this);
                }
                else if (zzqd.this.wz.getErrorCode() == 18) {
                    zzqd.this.vP.zza(zzqd.this.getActivity().getApplicationContext(), new zzqv.zza() {
                        final /* synthetic */ Dialog wD = zzqd.this.vP.zza(zzqd.this.getActivity(), (DialogInterface$OnCancelListener)zzqd.this);
                        
                        @Override
                        public void zzaqp() {
                            zzqd.this.zzaqo();
                            if (this.wD.isShowing()) {
                                this.wD.dismiss();
                            }
                        }
                    });
                }
                else {
                    zzqd.this.zza(zzqd.this.wz, zzqd.this.wA);
                }
            }
        }
    }
}
