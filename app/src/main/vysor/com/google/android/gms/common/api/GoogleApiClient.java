// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import com.google.android.gms.internal.zzqa;
import java.util.List;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.internal.zzqp;
import java.util.concurrent.locks.ReentrantLock;
import com.google.android.gms.internal.zzqf;
import java.util.Collection;
import com.google.android.gms.common.internal.zzai;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzwy;
import android.support.v4.util.ArrayMap;
import java.util.HashSet;
import java.util.ArrayList;
import com.google.android.gms.internal.zzxa;
import com.google.android.gms.internal.zzwz;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.internal.zzqz;
import com.google.android.gms.common.internal.zzh;
import android.view.View;
import android.accounts.Account;
import com.google.android.gms.internal.zzrd;
import com.google.android.gms.internal.zzqc;
import com.google.android.gms.internal.zzrl;
import com.google.android.gms.internal.zzrp;
import android.support.v4.app.FragmentActivity;
import android.os.Looper;
import android.content.Context;
import android.support.annotation.NonNull;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.ConnectionResult;
import java.util.Iterator;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import java.util.Map;
import java.util.Collections;
import java.util.WeakHashMap;
import java.util.Set;

public abstract class GoogleApiClient
{
    public static final int SIGN_IN_MODE_OPTIONAL = 2;
    public static final int SIGN_IN_MODE_REQUIRED = 1;
    private static final Set<GoogleApiClient> vE;
    
    static {
        vE = Collections.newSetFromMap(new WeakHashMap<GoogleApiClient, Boolean>());
    }
    
    public static void dumpAll(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        synchronized (GoogleApiClient.vE) {
            final String concat = String.valueOf(s).concat("  ");
            final Iterator<GoogleApiClient> iterator = GoogleApiClient.vE.iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final GoogleApiClient googleApiClient = iterator.next();
                final PrintWriter append = printWriter.append(s).append("GoogleApiClient#");
                final int n2 = n + 1;
                append.println(n);
                googleApiClient.dump(concat, fileDescriptor, printWriter, array);
                n = n2;
            }
        }
    }
    
    public static Set<GoogleApiClient> zzaqa() {
        synchronized (GoogleApiClient.vE) {
            return GoogleApiClient.vE;
        }
    }
    
    public abstract ConnectionResult blockingConnect();
    
    public abstract ConnectionResult blockingConnect(final long p0, @NonNull final TimeUnit p1);
    
    public abstract PendingResult<Status> clearDefaultAccountAndReconnect();
    
    public abstract void connect();
    
    public void connect(final int n) {
        throw new UnsupportedOperationException();
    }
    
    public abstract void disconnect();
    
    public abstract void dump(final String p0, final FileDescriptor p1, final PrintWriter p2, final String[] p3);
    
    @NonNull
    public abstract ConnectionResult getConnectionResult(@NonNull final Api<?> p0);
    
    public Context getContext() {
        throw new UnsupportedOperationException();
    }
    
    public Looper getLooper() {
        throw new UnsupportedOperationException();
    }
    
    public abstract boolean hasConnectedApi(@NonNull final Api<?> p0);
    
    public abstract boolean isConnected();
    
    public abstract boolean isConnecting();
    
    public abstract boolean isConnectionCallbacksRegistered(@NonNull final ConnectionCallbacks p0);
    
    public abstract boolean isConnectionFailedListenerRegistered(@NonNull final OnConnectionFailedListener p0);
    
    public abstract void reconnect();
    
    public abstract void registerConnectionCallbacks(@NonNull final ConnectionCallbacks p0);
    
    public abstract void registerConnectionFailedListener(@NonNull final OnConnectionFailedListener p0);
    
    public abstract void stopAutoManage(@NonNull final FragmentActivity p0);
    
    public abstract void unregisterConnectionCallbacks(@NonNull final ConnectionCallbacks p0);
    
    public abstract void unregisterConnectionFailedListener(@NonNull final OnConnectionFailedListener p0);
    
    @NonNull
    public <C extends Api.zze> C zza(@NonNull final Api.zzc<C> zzc) {
        throw new UnsupportedOperationException();
    }
    
    public void zza(final zzrp zzrp) {
        throw new UnsupportedOperationException();
    }
    
    public boolean zza(@NonNull final Api<?> api) {
        throw new UnsupportedOperationException();
    }
    
    public boolean zza(final zzrl zzrl) {
        throw new UnsupportedOperationException();
    }
    
    public void zzaqb() {
        throw new UnsupportedOperationException();
    }
    
    public void zzb(final zzrp zzrp) {
        throw new UnsupportedOperationException();
    }
    
    public <A extends Api.zzb, R extends Result, T extends zzqc.zza<R, A>> T zzc(@NonNull final T t) {
        throw new UnsupportedOperationException();
    }
    
    public <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zzd(@NonNull final T t) {
        throw new UnsupportedOperationException();
    }
    
    public <L> zzrd<L> zzs(@NonNull final L l) {
        throw new UnsupportedOperationException();
    }
    
    public static final class Builder
    {
        private Account ec;
        private String fo;
        private final Context mContext;
        private final Set<Scope> vF;
        private final Set<Scope> vG;
        private int vH;
        private View vI;
        private String vJ;
        private final Map<Api<?>, zzh.zza> vK;
        private final Map<Api<?>, Api.ApiOptions> vL;
        private zzqz vM;
        private int vN;
        private OnConnectionFailedListener vO;
        private GoogleApiAvailability vP;
        private Api.zza<? extends zzwz, zzxa> vQ;
        private final ArrayList<ConnectionCallbacks> vR;
        private final ArrayList<OnConnectionFailedListener> vS;
        private Looper zzajn;
        
        public Builder(@NonNull final Context mContext) {
            this.vF = new HashSet<Scope>();
            this.vG = new HashSet<Scope>();
            this.vK = new ArrayMap<Api<?>, zzh.zza>();
            this.vL = new ArrayMap<Api<?>, Api.ApiOptions>();
            this.vN = -1;
            this.vP = GoogleApiAvailability.getInstance();
            this.vQ = zzwy.fb;
            this.vR = new ArrayList<ConnectionCallbacks>();
            this.vS = new ArrayList<OnConnectionFailedListener>();
            this.mContext = mContext;
            this.zzajn = mContext.getMainLooper();
            this.fo = mContext.getPackageName();
            this.vJ = mContext.getClass().getName();
        }
        
        public Builder(@NonNull final Context context, @NonNull final ConnectionCallbacks connectionCallbacks, @NonNull final OnConnectionFailedListener onConnectionFailedListener) {
            this(context);
            zzac.zzb(connectionCallbacks, "Must provide a connected listener");
            this.vR.add(connectionCallbacks);
            zzac.zzb(onConnectionFailedListener, "Must provide a connection failed listener");
            this.vS.add(onConnectionFailedListener);
        }
        
        private static <C extends Api.zze, O> C zza(final Api.zza<C, O> zza, final Object o, final Context context, final Looper looper, final zzh zzh, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener) {
            return zza.zza(context, looper, zzh, (O)o, connectionCallbacks, onConnectionFailedListener);
        }
        
        private Builder zza(@NonNull final zzqz vm, final int vn, @Nullable final OnConnectionFailedListener vo) {
            zzac.zzb(vn >= 0, (Object)"clientId must be non-negative");
            this.vN = vn;
            this.vO = vo;
            this.vM = vm;
            return this;
        }
        
        private static <C extends Api.zzg, O> zzai zza(final Api.zzh<C, O> zzh, final Object o, final Context context, final Looper looper, final zzh zzh2, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener) {
            return new zzai(context, looper, zzh.zzapt(), connectionCallbacks, onConnectionFailedListener, zzh2, zzh.zzr((O)o));
        }
        
        private <O extends Api.ApiOptions> void zza(final Api<O> api, final O o, final int n, final Scope... array) {
            boolean b = true;
            int i = 0;
            if (n != (b ? 1 : 0)) {
                if (n != 2) {
                    throw new IllegalArgumentException(new StringBuilder(90).append("Invalid resolution mode: '").append(n).append("', use a constant from GoogleApiClient.ResolutionMode").toString());
                }
                b = false;
            }
            final HashSet<Scope> set = new HashSet<Scope>(api.zzapm().zzp(o));
            while (i < array.length) {
                set.add(array[i]);
                ++i;
            }
            this.vK.put(api, new zzh.zza(set, b));
        }
        
        private GoogleApiClient zzaqe() {
            final zzh zzaqd = this.zzaqd();
            Api<?> api = null;
            final Map<Api<?>, zzh.zza> zzaui = zzaqd.zzaui();
            final ArrayMap<Api<?>, Integer> arrayMap = new ArrayMap<Api<?>, Integer>();
            final ArrayMap<Api.zzc<?>, Api.zze> arrayMap2 = new ArrayMap<Api.zzc<?>, Api.zze>();
            final ArrayList<zzqf> list = new ArrayList<zzqf>();
            final Iterator<Api<?>> iterator = this.vL.keySet().iterator();
            Api api2 = null;
            while (iterator.hasNext()) {
                Api<?> api3 = iterator.next();
                final Api.ApiOptions value = this.vL.get(api3);
                final zzh.zza value2 = zzaui.get(api3);
                int n = 0;
                if (value2 != null) {
                    if (((zzh.zza)zzaui.get(api3)).Cb) {
                        n = 1;
                    }
                    else {
                        n = 2;
                    }
                }
                arrayMap.put(api3, n);
                final zzqf zzqf = new zzqf(api3, n);
                list.add(zzqf);
                zzai zzai;
                Api<?> api5;
                if (api3.zzapq()) {
                    final Api.zzh<?, ?> zzapo = api3.zzapo();
                    Api<?> api4;
                    if (zzapo.getPriority() == 1) {
                        api4 = api3;
                    }
                    else {
                        api4 = (Api<?>)api2;
                    }
                    zzai = zza(zzapo, value, this.mContext, this.zzajn, zzaqd, zzqf, zzqf);
                    api5 = api4;
                }
                else {
                    final Api.zza<?, ?> zzapn = api3.zzapn();
                    Api<?> api6;
                    if (zzapn.getPriority() == 1) {
                        api6 = api3;
                    }
                    else {
                        api6 = (Api<?>)api2;
                    }
                    zzai = zza(zzapn, value, this.mContext, this.zzajn, zzaqd, zzqf, zzqf);
                    api5 = api6;
                }
                arrayMap2.put(api3.zzapp(), zzai);
                if (((Api.zze)zzai).zzahs()) {
                    if (api != null) {
                        final String value3 = String.valueOf(api3.getName());
                        final String value4 = String.valueOf(api.getName());
                        throw new IllegalStateException(new StringBuilder(21 + String.valueOf(value3).length() + String.valueOf(value4).length()).append(value3).append(" cannot be used with ").append(value4).toString());
                    }
                }
                else {
                    api3 = api;
                }
                api2 = api5;
                api = api3;
            }
            if (api != null) {
                if (api2 != null) {
                    final String value5 = String.valueOf(api.getName());
                    final String value6 = String.valueOf(api2.getName());
                    throw new IllegalStateException(new StringBuilder(21 + String.valueOf(value5).length() + String.valueOf(value6).length()).append(value5).append(" cannot be used with ").append(value6).toString());
                }
                zzac.zza(this.ec == null, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", api.getName());
                zzac.zza(this.vF.equals(this.vG), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", api.getName());
            }
            return new zzqp(this.mContext, new ReentrantLock(), this.zzajn, zzaqd, this.vP, this.vQ, arrayMap, this.vR, this.vS, arrayMap2, this.vN, zzqp.zza((Iterable<Api.zze>)arrayMap2.values(), true), list);
        }
        
        private void zzf(final GoogleApiClient googleApiClient) {
            zzqa.zza(this.vM).zza(this.vN, googleApiClient, this.vO);
        }
        
        public Builder addApi(@NonNull final Api<? extends Api.ApiOptions.NotRequiredOptions> api) {
            zzac.zzb(api, "Api must not be null");
            this.vL.put(api, null);
            final List<Scope> zzp = api.zzapm().zzp((Api.ApiOptions.NotRequiredOptions)null);
            this.vG.addAll(zzp);
            this.vF.addAll(zzp);
            return this;
        }
        
        public <O extends Api.ApiOptions.HasOptions> Builder addApi(@NonNull final Api<O> api, @NonNull final O o) {
            zzac.zzb(api, "Api must not be null");
            zzac.zzb(o, "Null options are not permitted for this Api");
            this.vL.put(api, (Api.ApiOptions)o);
            final List<Scope> zzp = api.zzapm().zzp(o);
            this.vG.addAll(zzp);
            this.vF.addAll(zzp);
            return this;
        }
        
        public <O extends Api.ApiOptions.HasOptions> Builder addApiIfAvailable(@NonNull final Api<O> api, @NonNull final O o, final Scope... array) {
            zzac.zzb(api, "Api must not be null");
            zzac.zzb(o, "Null options are not permitted for this Api");
            this.vL.put(api, (Api.ApiOptions)o);
            this.zza(api, o, 1, array);
            return this;
        }
        
        public Builder addApiIfAvailable(@NonNull final Api<? extends Api.ApiOptions.NotRequiredOptions> api, final Scope... array) {
            zzac.zzb(api, "Api must not be null");
            this.vL.put(api, null);
            this.zza((Api<Api.ApiOptions>)api, null, 1, array);
            return this;
        }
        
        public Builder addConnectionCallbacks(@NonNull final ConnectionCallbacks connectionCallbacks) {
            zzac.zzb(connectionCallbacks, "Listener must not be null");
            this.vR.add(connectionCallbacks);
            return this;
        }
        
        public Builder addOnConnectionFailedListener(@NonNull final OnConnectionFailedListener onConnectionFailedListener) {
            zzac.zzb(onConnectionFailedListener, "Listener must not be null");
            this.vS.add(onConnectionFailedListener);
            return this;
        }
        
        public Builder addScope(@NonNull final Scope scope) {
            zzac.zzb(scope, "Scope must not be null");
            this.vF.add(scope);
            return this;
        }
        
        public GoogleApiClient build() {
            Label_0058: {
                if (this.vL.isEmpty()) {
                    break Label_0058;
                }
                boolean b = true;
                while (true) {
                    zzac.zzb(b, (Object)"must call addApi() to add at least one API");
                    final GoogleApiClient zzaqe = this.zzaqe();
                    synchronized (GoogleApiClient.vE) {
                        GoogleApiClient.vE.add(zzaqe);
                        // monitorexit(GoogleApiClient.zzaqc())
                        if (this.vN >= 0) {
                            this.zzf(zzaqe);
                        }
                        return zzaqe;
                        b = false;
                    }
                }
            }
        }
        
        public Builder enableAutoManage(@NonNull final FragmentActivity fragmentActivity, final int n, @Nullable final OnConnectionFailedListener onConnectionFailedListener) {
            return this.zza(new zzqz(fragmentActivity), n, onConnectionFailedListener);
        }
        
        public Builder enableAutoManage(@NonNull final FragmentActivity fragmentActivity, @Nullable final OnConnectionFailedListener onConnectionFailedListener) {
            return this.enableAutoManage(fragmentActivity, 0, onConnectionFailedListener);
        }
        
        public Builder setAccountName(final String s) {
            Account ec;
            if (s == null) {
                ec = null;
            }
            else {
                ec = new Account(s, "com.google");
            }
            this.ec = ec;
            return this;
        }
        
        public Builder setGravityForPopups(final int vh) {
            this.vH = vh;
            return this;
        }
        
        public Builder setHandler(@NonNull final Handler handler) {
            zzac.zzb(handler, "Handler must not be null");
            this.zzajn = handler.getLooper();
            return this;
        }
        
        public Builder setViewForPopups(@NonNull final View vi) {
            zzac.zzb(vi, "View must not be null");
            this.vI = vi;
            return this;
        }
        
        public Builder useDefaultAccount() {
            return this.setAccountName("<<default account>>");
        }
        
        public zzh zzaqd() {
            zzxa aAa = zzxa.aAa;
            if (this.vL.containsKey(zzwy.API)) {
                aAa = (zzxa)this.vL.get(zzwy.API);
            }
            return new zzh(this.ec, this.vF, this.vK, this.vH, this.vI, this.fo, this.vJ, aAa);
        }
    }
    
    public interface ConnectionCallbacks
    {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;
        
        void onConnected(@Nullable final Bundle p0);
        
        void onConnectionSuspended(final int p0);
    }
    
    public interface OnConnectionFailedListener
    {
        void onConnectionFailed(@NonNull final ConnectionResult p0);
    }
}
