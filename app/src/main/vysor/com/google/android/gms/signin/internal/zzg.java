// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.internal.zzac;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import android.accounts.Account;
import com.google.android.gms.auth.api.signin.internal.zzk;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import android.os.Parcelable;
import com.google.android.gms.internal.zzxa;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.internal.zzh;
import android.os.Bundle;
import com.google.android.gms.internal.zzwz;
import com.google.android.gms.common.internal.zzl;

public class zzg extends zzl<zze> implements zzwz
{
    private Integer Ca;
    private final boolean aAk;
    private final Bundle aAl;
    private final zzh xB;
    
    public zzg(final Context context, final Looper looper, final boolean aAk, final zzh xb, final Bundle aAl, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 44, xb, connectionCallbacks, onConnectionFailedListener);
        this.aAk = aAk;
        this.xB = xb;
        this.aAl = aAl;
        this.Ca = xb.zzaun();
    }
    
    public zzg(final Context context, final Looper looper, final boolean b, final zzh zzh, final zzxa zzxa, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, b, zzh, zza(zzh), connectionCallbacks, onConnectionFailedListener);
    }
    
    public static Bundle zza(final zzh zzh) {
        final zzxa zzaum = zzh.zzaum();
        final Integer zzaun = zzh.zzaun();
        final Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", (Parcelable)zzh.getAccount());
        if (zzaun != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", (int)zzaun);
        }
        if (zzaum != null) {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", zzaum.zzcdb());
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", zzaum.zzahk());
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", zzaum.zzahn());
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", zzaum.zzahm());
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", zzaum.zzaho());
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", zzaum.zzcdc());
            if (zzaum.zzcdd() != null) {
                bundle.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", (long)zzaum.zzcdd());
            }
            if (zzaum.zzcde() != null) {
                bundle.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", (long)zzaum.zzcde());
            }
        }
        return bundle;
    }
    
    private ResolveAccountRequest zzcdj() {
        final Account zzatv = this.xB.zzatv();
        final boolean equals = "<<default account>>".equals(zzatv.name);
        GoogleSignInAccount zzaic = null;
        if (equals) {
            zzaic = com.google.android.gms.auth.api.signin.internal.zzk.zzbd(this.getContext()).zzaic();
        }
        return new ResolveAccountRequest(zzatv, this.Ca, zzaic);
    }
    
    @Override
    public void connect() {
        this.zza((zzf)new zzi(this));
    }
    
    @Override
    public void zza(final zzr zzr, final boolean b) {
        try {
            this.zzatx().zza(zzr, this.Ca, b);
        }
        catch (RemoteException ex) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }
    
    @Override
    public void zza(final zzd zzd) {
        zzac.zzb(zzd, "Expecting a valid ISignInCallbacks");
        try {
            this.zzatx().zza(new SignInRequest(this.zzcdj()), zzd);
        }
        catch (RemoteException ex) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                zzd.zzb(new SignInResponse(8));
            }
            catch (RemoteException ex2) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", (Throwable)ex);
            }
        }
    }
    
    @Override
    protected Bundle zzagl() {
        if (!this.getContext().getPackageName().equals(this.xB.zzauj())) {
            this.aAl.putString("com.google.android.gms.signin.internal.realClientPackageName", this.xB.zzauj());
        }
        return this.aAl;
    }
    
    @Override
    public boolean zzahd() {
        return this.aAk;
    }
    
    @Override
    public void zzcda() {
        try {
            this.zzatx().zzaaf(this.Ca);
        }
        catch (RemoteException ex) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }
    
    @Override
    protected String zzix() {
        return "com.google.android.gms.signin.service.START";
    }
    
    @Override
    protected String zziy() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }
    
    protected zze zzlc(final IBinder binder) {
        return zze.zza.zzlb(binder);
    }
}
