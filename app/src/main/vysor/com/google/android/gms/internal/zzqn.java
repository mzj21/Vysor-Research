// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.signin.internal.zzd;
import android.support.annotation.BinderThread;
import com.google.android.gms.signin.internal.zzb;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzac;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.lang.ref.WeakReference;
import com.google.android.gms.common.internal.zze;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.HashMap;
import java.util.Collection;
import com.google.android.gms.common.api.Scope;
import java.util.Collections;
import java.util.Iterator;
import android.app.PendingIntent;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import android.util.Log;
import com.google.android.gms.signin.internal.SignInResponse;
import java.util.HashSet;
import com.google.android.gms.common.internal.zzr;
import java.util.Set;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.zzc;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.Map;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.common.api.Api;
import android.content.Context;

public class zzqn implements zzqq
{
    private final Context mContext;
    private final Api.zza<? extends zzwz, zzxa> vQ;
    private boolean xA;
    private final zzh xB;
    private final Map<Api<?>, Integer> xC;
    private ArrayList<Future<?>> xD;
    private final Lock xf;
    private final zzqr xk;
    private final com.google.android.gms.common.zzc xn;
    private ConnectionResult xo;
    private int xp;
    private int xq;
    private int xr;
    private final Bundle xs;
    private final Set<Api.zzc> xt;
    private zzwz xu;
    private int xv;
    private boolean xw;
    private boolean xx;
    private zzr xy;
    private boolean xz;
    
    public zzqn(final zzqr xk, final zzh xb, final Map<Api<?>, Integer> xc, final com.google.android.gms.common.zzc xn, final Api.zza<? extends zzwz, zzxa> vq, final Lock xf, final Context mContext) {
        this.xq = 0;
        this.xs = new Bundle();
        this.xt = new HashSet<Api.zzc>();
        this.xD = new ArrayList<Future<?>>();
        this.xk = xk;
        this.xB = xb;
        this.xC = xc;
        this.xn = xn;
        this.vQ = vq;
        this.xf = xf;
        this.mContext = mContext;
    }
    
    private void zza(final SignInResponse signInResponse) {
        if (this.zzfr(0)) {
            final ConnectionResult zzave = signInResponse.zzave();
            if (zzave.isSuccess()) {
                final ResolveAccountResponse zzcdl = signInResponse.zzcdl();
                final ConnectionResult zzave2 = zzcdl.zzave();
                if (!zzave2.isSuccess()) {
                    final String value = String.valueOf(zzave2);
                    Log.wtf("GoogleApiClientConnecting", new StringBuilder(48 + String.valueOf(value).length()).append("Sign-in succeeded with resolve account failure: ").append(value).toString(), (Throwable)new Exception());
                    this.zzg(zzave2);
                }
                else {
                    this.xx = true;
                    this.xy = zzcdl.zzavd();
                    this.xz = zzcdl.zzavf();
                    this.xA = zzcdl.zzavg();
                    this.zzark();
                }
            }
            else if (this.zzf(zzave)) {
                this.zzarn();
                this.zzark();
            }
            else {
                this.zzg(zzave);
            }
        }
    }
    
    private boolean zza(final int n, final int n2, final ConnectionResult connectionResult) {
        Label_0023: {
            if (n2 != 1) {
                break Label_0023;
            }
            final boolean zze = this.zze(connectionResult);
            final boolean b = false;
            if (zze) {
                break Label_0023;
            }
            return b;
        }
        if (this.xo != null) {
            final int xp = this.xp;
            final boolean b = false;
            if (n >= xp) {
                return b;
            }
        }
        return true;
    }
    
    private boolean zzarj() {
        --this.xr;
        final int xr = this.xr;
        boolean b = false;
        if (xr <= 0) {
            if (this.xr < 0) {
                Log.w("GoogleApiClientConnecting", this.xk.wV.zzarv());
                Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", (Throwable)new Exception());
                this.zzg(new ConnectionResult(8, null));
                b = false;
            }
            else if (this.xo != null) {
                this.xk.yo = this.xp;
                this.zzg(this.xo);
                b = false;
            }
            else {
                b = true;
            }
        }
        return b;
    }
    
    private void zzark() {
        if (this.xr == 0 && (!this.xw || this.xx)) {
            this.zzarl();
        }
    }
    
    private void zzarl() {
        final ArrayList<Api.zze> list = new ArrayList<Api.zze>();
        this.xq = 1;
        this.xr = this.xk.xW.size();
        for (final Api.zzc zzc : this.xk.xW.keySet()) {
            if (this.xk.yl.containsKey(zzc)) {
                if (!this.zzarj()) {
                    continue;
                }
                this.zzarm();
            }
            else {
                list.add(this.xk.xW.get(zzc));
            }
        }
        if (!list.isEmpty()) {
            this.xD.add(zzqs.zzarz().submit(new zzc(list)));
        }
    }
    
    private void zzarm() {
        this.xk.zzarx();
        zzqs.zzarz().execute(new Runnable() {
            @Override
            public void run() {
                zzqn.this.xn.zzbq(zzqn.this.mContext);
            }
        });
        if (this.xu != null) {
            if (this.xz) {
                this.xu.zza(this.xy, this.xA);
            }
            this.zzbq(false);
        }
        final Iterator<Api.zzc<?>> iterator = this.xk.yl.keySet().iterator();
        while (iterator.hasNext()) {
            this.xk.xW.get((Api.zzc)iterator.next()).disconnect();
        }
        Bundle xs;
        if (this.xs.isEmpty()) {
            xs = null;
        }
        else {
            xs = this.xs;
        }
        this.xk.yp.zzn(xs);
    }
    
    private void zzarn() {
        this.xw = false;
        this.xk.wV.xX = Collections.emptySet();
        for (final Api.zzc zzc : this.xt) {
            if (!this.xk.yl.containsKey(zzc)) {
                this.xk.yl.put(zzc, new ConnectionResult(17, null));
            }
        }
    }
    
    private void zzaro() {
        final Iterator<Future<?>> iterator = this.xD.iterator();
        while (iterator.hasNext()) {
            iterator.next().cancel(true);
        }
        this.xD.clear();
    }
    
    private Set<Scope> zzarp() {
        Set<Scope> emptySet;
        if (this.xB == null) {
            emptySet = Collections.emptySet();
        }
        else {
            final HashSet<Scope> set = new HashSet<Scope>(this.xB.zzaug());
            final Map<Api<?>, zzh.zza> zzaui = this.xB.zzaui();
            for (final Api<?> api : zzaui.keySet()) {
                if (!this.xk.yl.containsKey(api.zzapp())) {
                    set.addAll((Collection<?>)((zzh.zza)zzaui.get(api)).hm);
                }
            }
            emptySet = set;
        }
        return emptySet;
    }
    
    private void zzb(final ConnectionResult xo, final Api<?> api, final int n) {
        if (n != 2) {
            final int priority = api.zzapm().getPriority();
            if (this.zza(priority, n, xo)) {
                this.xo = xo;
                this.xp = priority;
            }
        }
        this.xk.yl.put(api.zzapp(), xo);
    }
    
    private void zzbq(final boolean b) {
        if (this.xu != null) {
            if (((Api.zze)this.xu).isConnected() && b) {
                this.xu.zzcda();
            }
            ((Api.zze)this.xu).disconnect();
            this.xy = null;
        }
    }
    
    private boolean zze(final ConnectionResult connectionResult) {
        boolean b = true;
        if (!connectionResult.hasResolution() && this.xn.zzfl(connectionResult.getErrorCode()) == null) {
            b = false;
        }
        return b;
    }
    
    private boolean zzf(final ConnectionResult connectionResult) {
        boolean b = true;
        if (this.xv != 2 && (this.xv != (b ? 1 : 0) || connectionResult.hasResolution())) {
            b = false;
        }
        return b;
    }
    
    private boolean zzfr(final int n) {
        boolean b;
        if (this.xq != n) {
            Log.w("GoogleApiClientConnecting", this.xk.wV.zzarv());
            final String value = String.valueOf(this);
            Log.w("GoogleApiClientConnecting", new StringBuilder(23 + String.valueOf(value).length()).append("Unexpected callback in ").append(value).toString());
            Log.w("GoogleApiClientConnecting", new StringBuilder(33).append("mRemainingConnections=").append(this.xr).toString());
            final String value2 = String.valueOf(this.zzfs(this.xq));
            final String value3 = String.valueOf(this.zzfs(n));
            Log.wtf("GoogleApiClientConnecting", new StringBuilder(70 + String.valueOf(value2).length() + String.valueOf(value3).length()).append("GoogleApiClient connecting is in step ").append(value2).append(" but received callback for step ").append(value3).toString(), (Throwable)new Exception());
            this.zzg(new ConnectionResult(8, null));
            b = false;
        }
        else {
            b = true;
        }
        return b;
    }
    
    private String zzfs(final int n) {
        String s = null;
        switch (n) {
            default: {
                s = "UNKNOWN";
                break;
            }
            case 0: {
                s = "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
                break;
            }
            case 1: {
                s = "STEP_GETTING_REMOTE_SERVICE";
                break;
            }
        }
        return s;
    }
    
    private void zzg(final ConnectionResult connectionResult) {
        this.zzaro();
        this.zzbq(!connectionResult.hasResolution());
        this.xk.zzi(connectionResult);
        this.xk.yp.zzd(connectionResult);
    }
    
    @Override
    public void begin() {
        this.xk.yl.clear();
        this.xw = false;
        this.xo = null;
        this.xq = 0;
        this.xv = 2;
        this.xx = false;
        this.xz = false;
        final HashMap<Api.zze, zza> hashMap = new HashMap<Api.zze, zza>();
        final Iterator<Api<?>> iterator = this.xC.keySet().iterator();
        boolean b = false;
        while (iterator.hasNext()) {
            final Api<?> api = iterator.next();
            final Api.zze zze = this.xk.xW.get(api.zzapp());
            final int intValue = this.xC.get(api);
            boolean b2;
            if (api.zzapm().getPriority() == 1) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            final boolean b3 = b2 | b;
            if (zze.zzahd()) {
                this.xw = true;
                if (intValue < this.xv) {
                    this.xv = intValue;
                }
                if (intValue != 0) {
                    this.xt.add((Api.zzc)api.zzapp());
                }
            }
            hashMap.put(zze, new zza(this, api, intValue));
            b = b3;
        }
        if (b) {
            this.xw = false;
        }
        if (this.xw) {
            this.xB.zzc(this.xk.wV.getSessionId());
            final zze zze2 = new zze();
            this.xu = (zzwz)this.vQ.zza(this.mContext, this.xk.wV.getLooper(), this.xB, this.xB.zzaum(), zze2, zze2);
        }
        this.xr = this.xk.xW.size();
        this.xD.add(zzqs.zzarz().submit(new zzb(hashMap)));
    }
    
    @Override
    public void connect() {
    }
    
    @Override
    public boolean disconnect() {
        this.zzaro();
        this.zzbq(true);
        this.xk.zzi(null);
        return true;
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        if (this.zzfr(1)) {
            if (bundle != null) {
                this.xs.putAll(bundle);
            }
            if (this.zzarj()) {
                this.zzarm();
            }
        }
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
        this.zzg(new ConnectionResult(8, null));
    }
    
    @Override
    public void zza(final ConnectionResult connectionResult, final Api<?> api, final int n) {
        if (this.zzfr(1)) {
            this.zzb(connectionResult, api, n);
            if (this.zzarj()) {
                this.zzarm();
            }
        }
    }
    
    @Override
    public <A extends Api.zzb, R extends Result, T extends zzqc.zza<R, A>> T zzc(final T t) {
        this.xk.wV.xQ.add(t);
        return t;
    }
    
    @Override
    public <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zzd(final T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
    
    private static class zza implements zze.zzf
    {
        private final Api<?> tv;
        private final int wT;
        private final WeakReference<zzqn> xF;
        
        public zza(final zzqn zzqn, final Api<?> tv, final int wt) {
            this.xF = new WeakReference<zzqn>(zzqn);
            this.tv = tv;
            this.wT = wt;
        }
        
        @Override
        public void zzh(@NonNull final ConnectionResult connectionResult) {
            final zzqn zzqn = this.xF.get();
            if (zzqn != null) {
                final Looper myLooper = Looper.myLooper();
                final Looper looper = zzqn.xk.wV.getLooper();
                boolean b = false;
                if (myLooper == looper) {
                    b = true;
                }
                zzac.zza(b, (Object)"onReportServiceBinding must be called on the GoogleApiClient handler thread");
                zzqn.xf.lock();
                try {
                    if (zzqn.zzfr(0)) {
                        if (!connectionResult.isSuccess()) {
                            zzqn.zzb(connectionResult, this.tv, this.wT);
                        }
                        if (zzqn.zzarj()) {
                            zzqn.zzark();
                        }
                    }
                }
                finally {
                    zzqn.xf.unlock();
                }
            }
        }
    }
    
    private class zzb extends zzf
    {
        private final Map<Api.zze, zza> xG;
        
        public zzb(final Map<Api.zze, zza> xg) {
            this.xG = xg;
        }
        
        @WorkerThread
        public void zzari() {
            int n = 1;
            final Iterator<Api.zze> iterator = this.xG.keySet().iterator();
            int n2 = n;
            int n3 = 0;
            while (true) {
                while (iterator.hasNext()) {
                    final Api.zze zze = iterator.next();
                    int n5;
                    int n6;
                    if (zze.zzapr()) {
                        if (this.xG.get(zze).wT == 0) {
                            final int n4 = n;
                            int googlePlayServicesAvailable = 0;
                            if (n != 0) {
                                googlePlayServicesAvailable = zzqn.this.xn.isGooglePlayServicesAvailable(zzqn.this.mContext);
                            }
                            if (googlePlayServicesAvailable != 0 && (n4 != 0 || n2 != 0)) {
                                zzqn.this.xk.zza((zzqr.zza)new zzqr.zza(zzqn.this) {
                                    final /* synthetic */ ConnectionResult xH = new ConnectionResult(googlePlayServicesAvailable, null);
                                    
                                    public void zzari() {
                                        zzqn.this.zzg(this.xH);
                                    }
                                });
                            }
                            else {
                                if (zzqn.this.xw) {
                                    zzqn.this.xu.connect();
                                }
                                for (final Api.zze zze2 : this.xG.keySet()) {
                                    final zza zza = this.xG.get(zze2);
                                    if (zze2.zzapr() && googlePlayServicesAvailable != 0) {
                                        zzqn.this.xk.zza((zzqr.zza)new zzqr.zza(zzqn.this) {
                                            public void zzari() {
                                                ((com.google.android.gms.common.internal.zze.zzf)zza).zzh(new ConnectionResult(16, null));
                                            }
                                        });
                                    }
                                    else {
                                        zze2.zza(zza);
                                    }
                                }
                            }
                            return;
                        }
                        n5 = n2;
                        n6 = n;
                    }
                    else {
                        n5 = 0;
                        n6 = n3;
                    }
                    n3 = n6;
                    n2 = n5;
                }
                n = n3;
                final int n4 = 0;
                continue;
            }
        }
    }
    
    private class zzc extends zzf
    {
        private final ArrayList<Api.zze> xK;
        
        public zzc(final ArrayList<Api.zze> xk) {
            this.xK = xk;
        }
        
        @WorkerThread
        public void zzari() {
            zzqn.this.xk.wV.xX = zzqn.this.zzarp();
            final Iterator<Api.zze> iterator = this.xK.iterator();
            while (iterator.hasNext()) {
                iterator.next().zza(zzqn.this.xy, zzqn.this.xk.wV.xX);
            }
        }
    }
    
    private static class zzd extends zzb
    {
        private final WeakReference<zzqn> xF;
        
        zzd(final zzqn zzqn) {
            this.xF = new WeakReference<zzqn>(zzqn);
        }
        
        @BinderThread
        @Override
        public void zzb(final SignInResponse signInResponse) {
            final zzqn zzqn = this.xF.get();
            if (zzqn != null) {
                zzqn.xk.zza((zzqr.zza)new zzqr.zza(zzqn) {
                    public void zzari() {
                        zzqn.zza(signInResponse);
                    }
                });
            }
        }
    }
    
    private class zze implements ConnectionCallbacks, OnConnectionFailedListener
    {
        @Override
        public void onConnected(final Bundle bundle) {
            zzqn.this.xu.zza(new zzd(zzqn.this));
        }
        
        @Override
        public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
            zzqn.this.xf.lock();
            try {
                if (zzqn.this.zzf(connectionResult)) {
                    zzqn.this.zzarn();
                    zzqn.this.zzark();
                }
                else {
                    zzqn.this.zzg(connectionResult);
                }
            }
            finally {
                zzqn.this.xf.unlock();
            }
        }
        
        @Override
        public void onConnectionSuspended(final int n) {
        }
    }
    
    private abstract class zzf implements Runnable
    {
        @WorkerThread
        @Override
        public void run() {
            zzqn.this.xf.lock();
            try {
                if (!Thread.interrupted()) {
                    this.zzari();
                    zzqn.this.xf.unlock();
                }
            }
            catch (RuntimeException ex) {
                zzqn.this.xk.zza(ex);
                zzqn.this.xf.unlock();
            }
            finally {
                zzqn.this.xf.unlock();
            }
        }
        
        @WorkerThread
        protected abstract void zzari();
    }
}
