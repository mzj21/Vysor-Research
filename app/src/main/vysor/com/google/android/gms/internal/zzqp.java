// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.ref.WeakReference;
import android.os.Message;
import java.io.Writer;
import java.io.StringWriter;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.app.PendingIntent;
import android.util.Log;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.os.Handler;
import java.util.concurrent.atomic.AtomicReference;
import com.google.android.gms.common.api.PendingResult;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.zzc;
import com.google.android.gms.auth.api.signin.internal.zzk;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.ResultCallback;
import java.util.Iterator;
import android.os.Bundle;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import android.os.Looper;
import java.util.concurrent.locks.Lock;
import java.util.ArrayList;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import java.util.Queue;
import com.google.android.gms.common.internal.zzm;
import java.util.Map;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.GoogleApiAvailability;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;

public final class zzqp extends GoogleApiClient implements zzqy.zza
{
    private final Context mContext;
    private final int vN;
    private final GoogleApiAvailability vP;
    final Api.zza<? extends zzwz, zzxa> vQ;
    final zzh xB;
    final Map<Api<?>, Integer> xC;
    private final zzm xO;
    private zzqy xP;
    final Queue<zzqc.zza<?, ?>> xQ;
    private volatile boolean xR;
    private long xS;
    private long xT;
    private final zza xU;
    zzqv xV;
    final Map<Api.zzc<?>, Api.zze> xW;
    Set<Scope> xX;
    private final zzre xY;
    private final ArrayList<zzqf> xZ;
    private final Lock xf;
    private Integer ya;
    Set<zzrp> yb;
    final zzrq yc;
    private final zzm.zza yd;
    private final Looper zzajn;
    
    public zzqp(final Context mContext, final Lock xf, final Looper zzajn, final zzh xb, final GoogleApiAvailability vp, final Api.zza<? extends zzwz, zzxa> vq, final Map<Api<?>, Integer> xc, final List<ConnectionCallbacks> list, final List<OnConnectionFailedListener> list2, final Map<Api.zzc<?>, Api.zze> xw, final int vn, final int n, final ArrayList<zzqf> xz) {
        this.xP = null;
        this.xQ = new LinkedList<zzqc.zza<?, ?>>();
        this.xS = 120000L;
        this.xT = 5000L;
        this.xX = new HashSet<Scope>();
        this.xY = new zzre();
        this.ya = null;
        this.yb = null;
        this.yd = new zzm.zza() {
            @Override
            public boolean isConnected() {
                return zzqp.this.isConnected();
            }
            
            @Override
            public Bundle zzaoe() {
                return null;
            }
        };
        this.mContext = mContext;
        this.xf = xf;
        this.xO = new zzm(zzajn, this.yd);
        this.zzajn = zzajn;
        this.xU = new zza(zzajn);
        this.vP = vp;
        this.vN = vn;
        if (this.vN >= 0) {
            this.ya = n;
        }
        this.xC = xc;
        this.xW = xw;
        this.xZ = xz;
        this.yc = new zzrq(this.xW);
        final Iterator<ConnectionCallbacks> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.xO.registerConnectionCallbacks(iterator.next());
        }
        final Iterator<OnConnectionFailedListener> iterator2 = list2.iterator();
        while (iterator2.hasNext()) {
            this.xO.registerConnectionFailedListener(iterator2.next());
        }
        this.xB = xb;
        this.vQ = vq;
    }
    
    private void resume() {
        this.xf.lock();
        try {
            if (this.isResuming()) {
                this.zzarq();
            }
        }
        finally {
            this.xf.unlock();
        }
    }
    
    public static int zza(final Iterable<Api.zze> iterable, final boolean b) {
        int n = 1;
        final Iterator<Api.zze> iterator = iterable.iterator();
        int n2 = 0;
        int n3 = 0;
        while (iterator.hasNext()) {
            final Api.zze zze = iterator.next();
            if (zze.zzahd()) {
                n3 = n;
            }
            int n4;
            if (zze.zzahs()) {
                n4 = n;
            }
            else {
                n4 = n2;
            }
            n2 = n4;
        }
        if (n3 != 0) {
            if (n2 != 0 && b) {
                n = 2;
            }
        }
        else {
            n = 3;
        }
        return n;
    }
    
    private void zza(final GoogleApiClient googleApiClient, final zzrm zzrm, final boolean b) {
        zzrx.Dh.zzg(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            public void zzp(@NonNull final Status status) {
                zzk.zzbd(zzqp.this.mContext).zzaie();
                if (status.isSuccess() && zzqp.this.isConnected()) {
                    zzqp.this.reconnect();
                }
                zzrm.zzc(status);
                if (b) {
                    googleApiClient.disconnect();
                }
            }
        });
    }
    
    private void zzarq() {
        this.xO.zzauu();
        this.xP.connect();
    }
    
    private void zzarr() {
        this.xf.lock();
        try {
            if (this.zzart()) {
                this.zzarq();
            }
        }
        finally {
            this.xf.unlock();
        }
    }
    
    private void zzb(@NonNull final zzqz zzqz) {
        if (this.vN >= 0) {
            zzqa.zza(zzqz).zzfq(this.vN);
            return;
        }
        throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
    }
    
    private void zzft(final int n) {
        if (this.ya == null) {
            this.ya = n;
        }
        else if (this.ya != n) {
            final String value = String.valueOf(zzfu(n));
            final String value2 = String.valueOf(zzfu(this.ya));
            throw new IllegalStateException(new StringBuilder(51 + String.valueOf(value).length() + String.valueOf(value2).length()).append("Cannot use sign-in mode: ").append(value).append(". Mode was already set to ").append(value2).toString());
        }
        if (this.xP == null) {
            final Iterator<Api.zze> iterator = this.xW.values().iterator();
            int n2 = 0;
            boolean b = false;
            while (iterator.hasNext()) {
                final Api.zze zze = iterator.next();
                if (zze.zzahd()) {
                    b = true;
                }
                int n3;
                if (zze.zzahs()) {
                    n3 = 1;
                }
                else {
                    n3 = n2;
                }
                n2 = n3;
            }
            switch (this.ya) {
                case 1: {
                    if (!b) {
                        throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
                    }
                    if (n2 != 0) {
                        throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
                    }
                    break;
                }
                case 2: {
                    if (b) {
                        this.xP = zzqh.zza(this.mContext, this, this.xf, this.zzajn, this.vP, this.xW, this.xB, this.xC, this.vQ, this.xZ);
                        return;
                    }
                    break;
                }
            }
            this.xP = new zzqr(this.mContext, this, this.xf, this.zzajn, this.vP, this.xW, this.xB, this.xC, this.vQ, this.xZ, this);
        }
    }
    
    static String zzfu(final int n) {
        String s = null;
        switch (n) {
            default: {
                s = "UNKNOWN";
                break;
            }
            case 3: {
                s = "SIGN_IN_MODE_NONE";
                break;
            }
            case 1: {
                s = "SIGN_IN_MODE_REQUIRED";
                break;
            }
            case 2: {
                s = "SIGN_IN_MODE_OPTIONAL";
                break;
            }
        }
        return s;
    }
    
    @Override
    public ConnectionResult blockingConnect() {
        boolean b = true;
        Label_0091: {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                break Label_0091;
            }
            boolean b2 = b;
        Label_0050_Outer:
            while (true) {
                zzac.zza(b2, (Object)"blockingConnect must not be called on the UI thread");
                this.xf.lock();
                while (true) {
                    Label_0143: {
                        try {
                            if (this.vN >= 0) {
                                if (this.ya == null) {
                                    b = false;
                                }
                                zzac.zza(b, (Object)"Sign-in mode should have been set explicitly by auto-manage.");
                            }
                            else {
                                if (this.ya != null) {
                                    break Label_0143;
                                }
                                this.ya = zza(this.xW.values(), false);
                            }
                            this.zzft(this.ya);
                            this.xO.zzauu();
                            return this.xP.blockingConnect();
                            b2 = false;
                            continue Label_0050_Outer;
                        }
                        finally {
                            this.xf.unlock();
                        }
                    }
                    if (this.ya == 2) {
                        break;
                    }
                    continue;
                }
            }
        }
        throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
    }
    
    @Override
    public ConnectionResult blockingConnect(final long n, @NonNull final TimeUnit timeUnit) {
        final Looper myLooper = Looper.myLooper();
        final Looper mainLooper = Looper.getMainLooper();
        boolean b = false;
        if (myLooper != mainLooper) {
            b = true;
        }
        zzac.zza(b, (Object)"blockingConnect must not be called on the UI thread");
        zzac.zzb(timeUnit, "TimeUnit must not be null");
        this.xf.lock();
        try {
            if (this.ya == null) {
                this.ya = zza(this.xW.values(), false);
            }
            else if (this.ya == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            this.zzft(this.ya);
            this.xO.zzauu();
            return this.xP.blockingConnect(n, timeUnit);
        }
        finally {
            this.xf.unlock();
        }
    }
    
    @Override
    public PendingResult<Status> clearDefaultAccountAndReconnect() {
        zzac.zza(this.isConnected(), (Object)"GoogleApiClient is not connected yet.");
        zzac.zza(this.ya != 2, (Object)"Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
        final zzrm zzrm = new zzrm(this);
        if (this.xW.containsKey(zzrx.fa)) {
            this.zza(this, zzrm, false);
        }
        else {
            final AtomicReference<GoogleApiClient> atomicReference = new AtomicReference<GoogleApiClient>();
            final GoogleApiClient build = new Builder(this.mContext).addApi(zzrx.API).addConnectionCallbacks(new ConnectionCallbacks() {
                @Override
                public void onConnected(final Bundle bundle) {
                    zzqp.this.zza(atomicReference.get(), zzrm, true);
                }
                
                @Override
                public void onConnectionSuspended(final int n) {
                }
            }).addOnConnectionFailedListener(new OnConnectionFailedListener() {
                @Override
                public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
                    zzrm.zzc(new Status(8));
                }
            }).setHandler(this.xU).build();
            atomicReference.set(build);
            build.connect();
        }
        return zzrm;
    }
    
    @Override
    public void connect() {
        while (true) {
            this.xf.lock();
            while (true) {
                Label_0099: {
                    try {
                        if (this.vN >= 0) {
                            final Integer ya = this.ya;
                            boolean b = false;
                            if (ya != null) {
                                b = true;
                            }
                            zzac.zza(b, (Object)"Sign-in mode should have been set explicitly by auto-manage.");
                        }
                        else {
                            if (this.ya != null) {
                                break Label_0099;
                            }
                            this.ya = zza(this.xW.values(), false);
                        }
                        this.connect(this.ya);
                        return;
                    }
                    finally {
                        this.xf.unlock();
                    }
                }
                if (this.ya == 2) {
                    break;
                }
                continue;
            }
        }
        throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
    }
    
    @Override
    public void connect(final int n) {
        boolean b = true;
        this.xf.lock();
        Label_0071: {
            if (n != 3 && n != (b ? 1 : 0)) {
                if (n != 2) {
                    break Label_0071;
                }
            }
            try {
                while (true) {
                    zzac.zzb(b, (Object)new StringBuilder(33).append("Illegal sign-in mode: ").append(n).toString());
                    this.zzft(n);
                    this.zzarq();
                    return;
                    b = false;
                    continue;
                }
            }
            finally {
                this.xf.unlock();
            }
        }
    }
    
    @Override
    public void disconnect() {
        this.xf.lock();
        try {
            this.yc.release();
            if (this.xP != null) {
                this.xP.disconnect();
            }
            this.xY.release();
            for (final zzqc.zza zza : this.xQ) {
                zza.zza((zzrq.zzb)null);
                zza.cancel();
            }
        }
        finally {
            this.xf.unlock();
        }
        this.xQ.clear();
        if (this.xP == null) {
            this.xf.unlock();
        }
        else {
            this.zzart();
            this.xO.zzaut();
            this.xf.unlock();
        }
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        printWriter.append(s).append("mContext=").println(this.mContext);
        printWriter.append(s).append("mResuming=").print(this.xR);
        printWriter.append(" mWorkQueue.size()=").print(this.xQ.size());
        this.yc.dump(printWriter);
        if (this.xP != null) {
            this.xP.dump(s, fileDescriptor, printWriter, array);
        }
    }
    
    @NonNull
    @Override
    public ConnectionResult getConnectionResult(@NonNull final Api<?> api) {
        this.xf.lock();
        try {
            if (!this.isConnected() && !this.isResuming()) {
                throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
            }
        }
        finally {
            this.xf.unlock();
        }
        if (this.xW.containsKey(api.zzapp())) {
            ConnectionResult connectionResult = this.xP.getConnectionResult(api);
            if (connectionResult == null) {
                if (this.isResuming()) {
                    connectionResult = ConnectionResult.uJ;
                    this.xf.unlock();
                }
                else {
                    Log.w("GoogleApiClientImpl", this.zzarv());
                    Log.wtf("GoogleApiClientImpl", String.valueOf(api.getName()).concat(" requested in getConnectionResult is not connected but is not present in the failed  connections map"), (Throwable)new Exception());
                    connectionResult = new ConnectionResult(8, null);
                    this.xf.unlock();
                }
            }
            else {
                this.xf.unlock();
            }
            return connectionResult;
        }
        throw new IllegalArgumentException(String.valueOf(api.getName()).concat(" was never registered with GoogleApiClient"));
    }
    
    @Override
    public Context getContext() {
        return this.mContext;
    }
    
    @Override
    public Looper getLooper() {
        return this.zzajn;
    }
    
    public int getSessionId() {
        return System.identityHashCode(this);
    }
    
    @Override
    public boolean hasConnectedApi(@NonNull final Api<?> api) {
        final Api.zze zze = this.xW.get(api.zzapp());
        return zze != null && zze.isConnected();
    }
    
    @Override
    public boolean isConnected() {
        return this.xP != null && this.xP.isConnected();
    }
    
    @Override
    public boolean isConnecting() {
        return this.xP != null && this.xP.isConnecting();
    }
    
    @Override
    public boolean isConnectionCallbacksRegistered(@NonNull final ConnectionCallbacks connectionCallbacks) {
        return this.xO.isConnectionCallbacksRegistered(connectionCallbacks);
    }
    
    @Override
    public boolean isConnectionFailedListenerRegistered(@NonNull final OnConnectionFailedListener onConnectionFailedListener) {
        return this.xO.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }
    
    boolean isResuming() {
        return this.xR;
    }
    
    @Override
    public void reconnect() {
        this.disconnect();
        this.connect();
    }
    
    @Override
    public void registerConnectionCallbacks(@NonNull final ConnectionCallbacks connectionCallbacks) {
        this.xO.registerConnectionCallbacks(connectionCallbacks);
    }
    
    @Override
    public void registerConnectionFailedListener(@NonNull final OnConnectionFailedListener onConnectionFailedListener) {
        this.xO.registerConnectionFailedListener(onConnectionFailedListener);
    }
    
    @Override
    public void stopAutoManage(@NonNull final FragmentActivity fragmentActivity) {
        this.zzb(new zzqz(fragmentActivity));
    }
    
    @Override
    public void unregisterConnectionCallbacks(@NonNull final ConnectionCallbacks connectionCallbacks) {
        this.xO.unregisterConnectionCallbacks(connectionCallbacks);
    }
    
    @Override
    public void unregisterConnectionFailedListener(@NonNull final OnConnectionFailedListener onConnectionFailedListener) {
        this.xO.unregisterConnectionFailedListener(onConnectionFailedListener);
    }
    
    @NonNull
    @Override
    public <C extends Api.zze> C zza(@NonNull final Api.zzc<C> zzc) {
        final Api.zze zze = this.xW.get(zzc);
        zzac.zzb(zze, "Appropriate Api was not requested.");
        return (C)zze;
    }
    
    @Override
    public void zza(final zzrp zzrp) {
        this.xf.lock();
        try {
            if (this.yb == null) {
                this.yb = new HashSet<zzrp>();
            }
            this.yb.add(zzrp);
        }
        finally {
            this.xf.unlock();
        }
    }
    
    @Override
    public boolean zza(@NonNull final Api<?> api) {
        return this.xW.containsKey(api.zzapp());
    }
    
    @Override
    public boolean zza(final zzrl zzrl) {
        return this.xP != null && this.xP.zza(zzrl);
    }
    
    @Override
    public void zzaqb() {
        if (this.xP != null) {
            this.xP.zzaqb();
        }
    }
    
    void zzars() {
        if (!this.isResuming()) {
            this.xR = true;
            if (this.xV == null) {
                this.xV = this.vP.zza(this.mContext.getApplicationContext(), new zzb(this));
            }
            this.xU.sendMessageDelayed(this.xU.obtainMessage(1), this.xS);
            this.xU.sendMessageDelayed(this.xU.obtainMessage(2), this.xT);
        }
    }
    
    boolean zzart() {
        final boolean resuming = this.isResuming();
        boolean b = false;
        if (resuming) {
            this.xR = false;
            this.xU.removeMessages(2);
            this.xU.removeMessages(1);
            if (this.xV != null) {
                this.xV.unregister();
                this.xV = null;
            }
            b = true;
        }
        return b;
    }
    
    boolean zzaru() {
        boolean b = false;
        this.xf.lock();
        try {
            if (this.yb != null) {
                final boolean empty = this.yb.isEmpty();
                b = false;
                if (!empty) {
                    b = true;
                }
                this.xf.unlock();
            }
            return b;
        }
        finally {
            this.xf.unlock();
        }
    }
    
    String zzarv() {
        final StringWriter stringWriter = new StringWriter();
        this.dump("", null, new PrintWriter(stringWriter), null);
        return stringWriter.toString();
    }
    
     <C extends Api.zze> C zzb(final Api.zzc<?> zzc) {
        final Api.zze zze = this.xW.get(zzc);
        zzac.zzb(zze, "Appropriate Api was not requested.");
        return (C)zze;
    }
    
    @Override
    public void zzb(final zzrp zzrp) {
        while (true) {
            this.xf.lock();
            Label_0088: {
                try {
                    if (this.yb == null) {
                        Log.wtf("GoogleApiClientImpl", "Attempted to remove pending transform when no transforms are registered.", (Throwable)new Exception());
                    }
                    else {
                        if (this.yb.remove(zzrp)) {
                            break Label_0088;
                        }
                        Log.wtf("GoogleApiClientImpl", "Failed to remove pending transform - this may lead to memory leaks!", (Throwable)new Exception());
                    }
                    return;
                }
                finally {
                    this.xf.unlock();
                }
            }
            if (!this.zzaru()) {
                this.xP.zzaqy();
            }
        }
    }
    
    @Override
    public <A extends Api.zzb, R extends Result, T extends zzqc.zza<R, A>> T zzc(@NonNull T zzc) {
        while (true) {
            while (true) {
                boolean b = false;
                Label_0009: {
                    if (zzc.zzapp() != null) {
                        b = true;
                        break Label_0009;
                    }
                    Label_0126: {
                        break Label_0126;
                        while (true) {
                            final boolean containsKey;
                            String name = null;
                            zzac.zzb(containsKey, (Object)new StringBuilder(65 + String.valueOf(name).length()).append("GoogleApiClient is not configured to use ").append(name).append(" required for this call.").toString());
                            this.xf.lock();
                            try {
                                if (this.xP == null) {
                                    this.xQ.add(zzc);
                                }
                                else {
                                    zzc = (T)this.xP.zzc((zzqc.zza<R, A>)zzc);
                                    this.xf.unlock();
                                }
                                return zzc;
                                b = false;
                                break;
                                name = "the API";
                            }
                            finally {
                                this.xf.unlock();
                            }
                        }
                    }
                }
                zzac.zzb(b, (Object)"This task can not be enqueued (it's probably a Batch or malformed)");
                final boolean containsKey = this.xW.containsKey(zzc.zzapp());
                if (((zzqc.zza)zzc).zzaqn() != null) {
                    final String name = ((zzqc.zza)zzc).zzaqn().getName();
                    continue;
                }
                break;
            }
            continue;
        }
    }
    
    @Override
    public void zzc(final int n, final boolean b) {
        if (n == 1 && !b) {
            this.zzars();
        }
        this.yc.zzasw();
        this.xO.zzgo(n);
        this.xO.zzaut();
        if (n == 2) {
            this.zzarq();
        }
    }
    
    @Override
    public <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zzd(@NonNull T zzd) {
        while (true) {
            Label_0129: {
                if (zzd.zzapp() == null) {
                    break Label_0129;
                }
                final boolean b = true;
                zzac.zzb(b, (Object)"This task can not be executed (it's probably a Batch or malformed)");
                final boolean containsKey = this.xW.containsKey(zzd.zzapp());
                String name;
                if (((zzqc.zza)zzd).zzaqn() != null) {
                    name = ((zzqc.zza)zzd).zzaqn().getName();
                }
                else {
                    name = "the API";
                }
                zzac.zzb(containsKey, (Object)new StringBuilder(65 + String.valueOf(name).length()).append("GoogleApiClient is not configured to use ").append(name).append(" required for this call.").toString());
                this.xf.lock();
                Label_0142: {
                    try {
                        if (this.xP == null) {
                            throw new IllegalStateException("GoogleApiClient is not connected yet.");
                        }
                        break Label_0142;
                    }
                    finally {
                        this.xf.unlock();
                    }
                    break Label_0129;
                }
                if (this.isResuming()) {
                    this.xQ.add(zzd);
                    while (!this.xQ.isEmpty()) {
                        final zzqc.zza zza = this.xQ.remove();
                        this.yc.zzb(zza);
                        zza.zzz(Status.wa);
                    }
                    this.xf.unlock();
                }
                else {
                    zzd = (T)this.xP.zzd((zzqc.zza<? extends Result, A>)zzd);
                    this.xf.unlock();
                }
                return zzd;
            }
            final boolean b = false;
            continue;
        }
    }
    
    @Override
    public void zzd(final ConnectionResult connectionResult) {
        if (!this.vP.zzd(this.mContext, connectionResult.getErrorCode())) {
            this.zzart();
        }
        if (!this.isResuming()) {
            this.xO.zzn(connectionResult);
            this.xO.zzaut();
        }
    }
    
    @Override
    public void zzn(final Bundle bundle) {
        while (!this.xQ.isEmpty()) {
            this.zzd(this.xQ.remove());
        }
        this.xO.zzp(bundle);
    }
    
    @Override
    public <L> zzrd<L> zzs(@NonNull final L l) {
        this.xf.lock();
        try {
            return this.xY.zzb(l, this.zzajn);
        }
        finally {
            this.xf.unlock();
        }
    }
    
    final class zza extends Handler
    {
        zza(final Looper looper) {
            super(looper);
        }
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {
                    Log.w("GoogleApiClientImpl", new StringBuilder(31).append("Unknown message id: ").append(message.what).toString());
                    break;
                }
                case 1: {
                    zzqp.this.zzarr();
                    break;
                }
                case 2: {
                    zzqp.this.resume();
                    break;
                }
            }
        }
    }
    
    static class zzb extends zzqv.zza
    {
        private WeakReference<zzqp> yi;
        
        zzb(final zzqp zzqp) {
            this.yi = new WeakReference<zzqp>(zzqp);
        }
        
        @Override
        public void zzaqp() {
            final zzqp zzqp = this.yi.get();
            if (zzqp != null) {
                zzqp.resume();
            }
        }
    }
}
