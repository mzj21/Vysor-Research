// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzr;
import java.util.Collections;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.internal.zze;
import android.app.PendingIntent;
import com.google.android.gms.common.internal.zzai;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Process;
import java.util.concurrent.atomic.AtomicBoolean;
import java.lang.ref.PhantomReference;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import android.os.Message;
import android.util.Log;
import java.util.Iterator;
import com.google.android.gms.common.api.Api;
import android.support.annotation.WorkerThread;
import com.google.android.gms.tasks.TaskCompletionSource;
import android.util.Pair;
import android.os.HandlerThread;
import com.google.android.gms.common.util.zza;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import android.util.SparseArray;
import com.google.android.gms.common.api.zzd;
import java.lang.ref.ReferenceQueue;
import java.util.Set;
import com.google.android.gms.common.GoogleApiAvailability;
import android.os.Handler;
import android.content.Context;
import android.os.Handler$Callback;

public class zzqt implements Handler$Callback
{
    private static zzqt yu;
    private static final Object zzaok;
    private final Context mContext;
    private final Handler mHandler;
    private final GoogleApiAvailability vP;
    private long xS;
    private long xT;
    private final Set<zzpz<?>> yA;
    private final ReferenceQueue<com.google.android.gms.common.api.zzd<?>> yB;
    private final SparseArray<zza> yC;
    private zzb yD;
    private long yt;
    private int yv;
    private final AtomicInteger yw;
    private final SparseArray<zzc<?>> yx;
    private final Map<zzpz<?>, zzc<?>> yy;
    private zzqi yz;
    
    static {
        zzaok = new Object();
    }
    
    private zzqt(final Context context) {
        this(context, GoogleApiAvailability.getInstance());
    }
    
    private zzqt(final Context mContext, final GoogleApiAvailability vp) {
        this.xT = 5000L;
        this.xS = 120000L;
        this.yt = 10000L;
        this.yv = -1;
        this.yw = new AtomicInteger(1);
        this.yx = (SparseArray<zzc<?>>)new SparseArray();
        this.yy = new ConcurrentHashMap<zzpz<?>, zzc<?>>(5, 0.75f, 1);
        this.yz = null;
        this.yA = new com.google.android.gms.common.util.zza<zzpz<?>>();
        this.yB = new ReferenceQueue<com.google.android.gms.common.api.zzd<?>>();
        this.yC = (SparseArray<zza>)new SparseArray();
        this.mContext = mContext;
        final HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper(), (Handler$Callback)this);
        this.vP = vp;
    }
    
    private int zza(final com.google.android.gms.common.api.zzd<?> zzd) {
        final int andIncrement = this.yw.getAndIncrement();
        this.mHandler.sendMessage(this.mHandler.obtainMessage(6, andIncrement, 0, (Object)zzd));
        return andIncrement;
    }
    
    public static Pair<zzqt, Integer> zza(final Context context, final com.google.android.gms.common.api.zzd<?> zzd) {
        synchronized (zzqt.zzaok) {
            if (zzqt.yu == null) {
                zzqt.yu = new zzqt(context.getApplicationContext());
            }
            return (Pair<zzqt, Integer>)Pair.create((Object)zzqt.yu, (Object)zzqt.yu.zza(zzd));
        }
    }
    
    @WorkerThread
    private void zza(final int n, final zzrd.zzb<?> zzb, final TaskCompletionSource<Void> taskCompletionSource) {
        ((zzc)this.yx.get(n)).zzb(n, zzb, taskCompletionSource);
    }
    
    @WorkerThread
    private void zza(final int n, final zzri zzri, final TaskCompletionSource<Void> taskCompletionSource) {
        ((zzc)this.yx.get(n)).zzb(n, zzri, taskCompletionSource);
    }
    
    @WorkerThread
    private void zza(final com.google.android.gms.common.api.zzd<?> zzd, final int n) {
        final zzpz<Object> zzapx = zzd.zzapx();
        if (!this.yy.containsKey(zzapx)) {
            this.yy.put(zzapx, new zzc<Object>((com.google.android.gms.common.api.zzd<Object>)zzd));
        }
        final zzc<?> zzc = this.yy.get(zzapx);
        zzc.zzfw(n);
        this.yx.put(n, (Object)zzc);
        ((zzc<Api.ApiOptions>)zzc).connect();
        this.yC.put(n, (Object)new zza(zzd, n, this.yB));
        if (this.yD == null || !this.yD.yF.get()) {
            (this.yD = new zzb(this.yB, this.yC)).start();
        }
    }
    
    @WorkerThread
    private void zza(final zzpy zzpy) {
        ((zzc)this.yx.get(zzpy.wf)).zzb(zzpy);
    }
    
    public static zzqt zzasa() {
        synchronized (zzqt.zzaok) {
            return zzqt.yu;
        }
    }
    
    @WorkerThread
    private void zzasb() {
        for (final zzc<Api.ApiOptions> zzc : this.yy.values()) {
            zzc.zzasf();
            zzc.connect();
        }
    }
    
    static /* synthetic */ zzqi zzd(final zzqt zzqt) {
        return null;
    }
    
    @WorkerThread
    private void zze(final int n, final boolean b) {
        final zzc zzc = (zzc)this.yx.get(n);
        if (zzc != null) {
            if (!b) {
                this.yx.delete(n);
            }
            zzc.zzf(n, b);
        }
        else {
            Log.wtf("GoogleApiManager", new StringBuilder(52).append("onRelease received for unknown instance: ").append(n).toString(), (Throwable)new Exception());
        }
    }
    
    @WorkerThread
    private void zzfv(final int n) {
        final zzc zzc = (zzc)this.yx.get(n);
        if (zzc != null) {
            this.yx.delete(n);
            zzc.zzfx(n);
        }
        else {
            Log.wtf("GoogleApiManager", new StringBuilder(64).append("onCleanupLeakInternal received for unknown instance: ").append(n).toString(), (Throwable)new Exception());
        }
    }
    
    @WorkerThread
    public boolean handleMessage(final Message message) {
        boolean b = false;
        switch (message.what) {
            default: {
                Log.w("GoogleApiManager", new StringBuilder(31).append("Unknown message id: ").append(message.what).toString());
                break;
            }
            case 1: {
                this.zza((zzqb)message.obj);
                return true;
            }
            case 6: {
                this.zza((com.google.android.gms.common.api.zzd<?>)message.obj, message.arg1);
                return true;
            }
            case 3: {
                this.zzasb();
                return true;
            }
            case 2: {
                this.zzfv(message.arg1);
                return true;
            }
            case 8: {
                final int arg1 = message.arg1;
                final int arg2 = message.arg2;
                boolean b2 = false;
                if (arg2 == 1) {
                    b2 = true;
                }
                this.zze(arg1, b2);
                return true;
            }
            case 4: {
                this.zza((zzpy)message.obj);
                return true;
            }
            case 5: {
                if (this.yx.get(message.arg1) != null) {
                    ((zzc<Api.ApiOptions>)this.yx.get(message.arg1)).zzab(new Status(17, "Error resolution was canceled by the user."));
                    return true;
                }
                return true;
            }
            case 9: {
                if (this.yy.containsKey(message.obj)) {
                    ((zzc<Api.ApiOptions>)this.yy.get(message.obj)).resume();
                    return true;
                }
                return true;
            }
            case 7: {
                final Pair pair = (Pair)message.obj;
                this.zza(message.arg1, (zzri)pair.first, (TaskCompletionSource<Void>)pair.second);
                return true;
            }
            case 10: {
                if (this.yy.containsKey(message.obj)) {
                    ((zzc<Api.ApiOptions>)this.yy.get(message.obj)).zzarr();
                    return true;
                }
                return true;
            }
            case 11: {
                if (this.yy.containsKey(message.obj)) {
                    ((zzc<Api.ApiOptions>)this.yy.get(message.obj)).zzasj();
                    return true;
                }
                return true;
            }
            case 12: {
                final Pair pair2 = (Pair)message.obj;
                this.zza(message.arg1, (zzrd.zzb<?>)pair2.first, (TaskCompletionSource<Void>)pair2.second);
                return true;
            }
        }
        return b;
        b = true;
        return b;
    }
    
    public void zza(final ConnectionResult connectionResult, final int n) {
        if (!this.zzc(connectionResult, n)) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(5, n, 0));
        }
    }
    
    public <O extends Api.ApiOptions> void zza(final com.google.android.gms.common.api.zzd<O> zzd, final int n, final zzqc.zza<? extends Result, Api.zzb> zza) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, (Object)new zzpy.zzb(zzd.getInstanceId(), n, zza)));
    }
    
    public <O extends Api.ApiOptions, TResult> void zza(final com.google.android.gms.common.api.zzd<O> zzd, final int n, final zzro<Api.zzb, TResult> zzro, final TaskCompletionSource<TResult> taskCompletionSource) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, (Object)new zzpy.zzd(zzd.getInstanceId(), n, (zzro<Api.zzb, Object>)zzro, (TaskCompletionSource<Object>)taskCompletionSource)));
    }
    
    @WorkerThread
    public void zza(final zzqb zzqb) {
        for (final zzpz<?> zzpz : zzqb.zzaqm()) {
            final zzc<?> zzc = this.yy.get(zzpz);
            if (zzc == null) {
                zzqb.cancel();
                break;
            }
            if (zzc.isConnected()) {
                zzqb.zza(zzpz, ConnectionResult.uJ);
            }
            else if (zzc.zzasg() != null) {
                zzqb.zza(zzpz, zzc.zzasg());
            }
            else {
                zzc.zzb(zzqb);
            }
        }
    }
    
    public void zza(final zzqi zzqi) {
        final Object zzaok = zzqt.zzaok;
        // monitorenter(zzaok)
        if (zzqi != null) {
            return;
        }
        try {
            this.yz = null;
            this.yA.clear();
        }
        finally {
        }
        // monitorexit(zzaok)
    }
    
    public void zzaqk() {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3));
    }
    
    boolean zzc(final ConnectionResult connectionResult, final int n) {
        boolean b;
        if (connectionResult.hasResolution() || this.vP.isUserResolvableError(connectionResult.getErrorCode())) {
            this.vP.zza(this.mContext, connectionResult, n);
            b = true;
        }
        else {
            b = false;
        }
        return b;
    }
    
    public void zzd(final int n, final boolean b) {
        final Handler mHandler = this.mHandler;
        final Handler mHandler2 = this.mHandler;
        int n2;
        if (b) {
            n2 = 1;
        }
        else {
            n2 = 2;
        }
        mHandler.sendMessage(mHandler2.obtainMessage(8, n, n2));
    }
    
    private final class zza extends PhantomReference<com.google.android.gms.common.api.zzd<?>>
    {
        private final int wf;
        
        public zza(final com.google.android.gms.common.api.zzd zzd, final int wf, final ReferenceQueue<com.google.android.gms.common.api.zzd<?>> referenceQueue) {
            super(zzd, (ReferenceQueue<? super com.google.android.gms.common.api.zzd>)referenceQueue);
            this.wf = wf;
        }
        
        public void zzasd() {
            zzqt.this.mHandler.sendMessage(zzqt.this.mHandler.obtainMessage(2, this.wf, 2));
        }
    }
    
    private static final class zzb extends Thread
    {
        private final ReferenceQueue<com.google.android.gms.common.api.zzd<?>> yB;
        private final SparseArray<zza> yC;
        private final AtomicBoolean yF;
        
        public zzb(final ReferenceQueue<com.google.android.gms.common.api.zzd<?>> yb, final SparseArray<zza> yc) {
            super("GoogleApiCleanup");
            this.yF = new AtomicBoolean();
            this.yB = yb;
            this.yC = yc;
        }
        
        @Override
        public void run() {
            this.yF.set(true);
            Process.setThreadPriority(10);
            Label_0062: {
                try {
                    while (this.yF.get()) {
                        final zza zza = (zza)this.yB.remove();
                        this.yC.remove(zza.wf);
                        zza.zzasd();
                    }
                    break Label_0062;
                }
                catch (InterruptedException ex) {
                    return;
                    this.yF.set(false);
                }
                finally {
                    this.yF.set(false);
                }
            }
        }
    }
    
    private class zzc<O extends Api.ApiOptions> implements ConnectionCallbacks, OnConnectionFailedListener, zzqg
    {
        private final Api.zze vC;
        private final zzpz<O> vx;
        private final SparseArray<Map<zzrd.zzb<?>, zzri>> wg;
        private boolean xR;
        private final Queue<zzpy> yG;
        private final Api.zzb yH;
        private final SparseArray<zzrq> yI;
        private final Set<zzqb> yJ;
        private ConnectionResult yK;
        
        public zzc(final com.google.android.gms.common.api.zzd<O> zzd) {
            this.yG = new LinkedList<zzpy>();
            this.yI = (SparseArray<zzrq>)new SparseArray();
            this.yJ = new HashSet<zzqb>();
            this.wg = (SparseArray<Map<zzrd.zzb<?>, zzri>>)new SparseArray();
            this.yK = null;
            this.vC = zzd.zza(zzqt.this.mHandler.getLooper(), this, this);
            if (this.vC instanceof zzai) {
                this.yH = ((zzai)this.vC).zzavk();
            }
            else {
                this.yH = this.vC;
            }
            this.vx = zzd.zzapx();
        }
        
        @WorkerThread
        private void connect() {
            if (!this.vC.isConnected() && !this.vC.isConnecting()) {
                if (this.vC.zzapr() && zzqt.this.yv != 0) {
                    zzqt.this.yv = zzqt.this.vP.isGooglePlayServicesAvailable(zzqt.this.mContext);
                    if (zzqt.this.yv != 0) {
                        this.onConnectionFailed(new ConnectionResult(zzqt.this.yv, null));
                        return;
                    }
                }
                this.vC.zza(new zzd(this.vC, this.vx));
            }
        }
        
        @WorkerThread
        private void resume() {
            if (this.xR) {
                this.connect();
            }
        }
        
        @WorkerThread
        private void zzab(final Status status) {
            final Iterator<zzpy> iterator = this.yG.iterator();
            while (iterator.hasNext()) {
                iterator.next().zzx(status);
            }
            this.yG.clear();
        }
        
        @WorkerThread
        private void zzarr() {
            if (this.xR) {
                this.zzash();
                Status status;
                if (zzqt.this.vP.isGooglePlayServicesAvailable(zzqt.this.mContext) == 18) {
                    status = new Status(8, "Connection timed out while waiting for Google Play services update to complete.");
                }
                else {
                    status = new Status(8, "API failed to connect while resuming due to an unknown error.");
                }
                this.zzab(status);
                this.vC.disconnect();
            }
        }
        
        @WorkerThread
        private void zzash() {
            if (this.xR) {
                zzqt.this.mHandler.removeMessages(10, (Object)this.vx);
                zzqt.this.mHandler.removeMessages(9, (Object)this.vx);
                this.xR = false;
            }
        }
        
        private void zzasi() {
            zzqt.this.mHandler.removeMessages(11, (Object)this.vx);
            zzqt.this.mHandler.sendMessageDelayed(zzqt.this.mHandler.obtainMessage(11, (Object)this.vx), zzqt.this.yt);
        }
        
        private void zzasj() {
            if (this.vC.isConnected() && this.wg.size() == 0) {
                for (int i = 0; i < this.yI.size(); ++i) {
                    if (((zzrq)this.yI.get(this.yI.keyAt(i))).zzasx()) {
                        this.zzasi();
                        return;
                    }
                }
                this.vC.disconnect();
            }
        }
        
        @WorkerThread
        private void zzc(final zzpy zzpy) {
            zzpy.zza(this.yI);
            try {
                zzpy.zzb(this.yH);
            }
            catch (DeadObjectException ex) {
                this.vC.disconnect();
                this.onConnectionSuspended(1);
            }
        }
        
        @WorkerThread
        private void zzj(final ConnectionResult connectionResult) {
            final Iterator<zzqb> iterator = this.yJ.iterator();
            while (iterator.hasNext()) {
                iterator.next().zza(this.vx, connectionResult);
            }
            this.yJ.clear();
        }
        
        boolean isConnected() {
            return this.vC.isConnected();
        }
        
        @WorkerThread
        @Override
        public void onConnected(@Nullable final Bundle bundle) {
            this.zzasf();
            this.zzj(ConnectionResult.uJ);
            this.zzash();
            for (int i = 0; i < this.wg.size(); ++i) {
                for (final zzri zzri : ((Map)this.wg.get(this.wg.keyAt(i))).values()) {
                    try {
                        zzri.wj.zza(this.yH, new TaskCompletionSource<Void>());
                    }
                    catch (DeadObjectException ex) {
                        this.vC.disconnect();
                        this.onConnectionSuspended(1);
                    }
                }
            }
            this.zzase();
            this.zzasi();
        }
        
        @WorkerThread
        @Override
        public void onConnectionFailed(@NonNull final ConnectionResult yk) {
            this.zzasf();
            zzqt.this.yv = -1;
            this.zzj(yk);
            final int key = this.yI.keyAt(0);
            if (this.yG.isEmpty()) {
                this.yK = yk;
            }
            else {
                synchronized (zzqt.zzaok) {
                    if (zzqt.zzd(zzqt.this) != null && zzqt.this.yA.contains(this.vx)) {
                        zzqt.zzd(zzqt.this).zzb(yk, key);
                        return;
                    }
                }
                // monitorexit(o)
                if (!zzqt.this.zzc(yk, key)) {
                    if (yk.getErrorCode() == 18) {
                        this.xR = true;
                    }
                    if (this.xR) {
                        zzqt.this.mHandler.sendMessageDelayed(Message.obtain(zzqt.this.mHandler, 9, (Object)this.vx), zzqt.this.xT);
                    }
                    else {
                        final String value = String.valueOf(this.vx.zzaqj());
                        this.zzab(new Status(17, new StringBuilder(38 + String.valueOf(value).length()).append("API: ").append(value).append(" is not available on this device.").toString()));
                    }
                }
            }
        }
        
        @WorkerThread
        @Override
        public void onConnectionSuspended(final int n) {
            this.zzasf();
            this.xR = true;
            zzqt.this.mHandler.sendMessageDelayed(Message.obtain(zzqt.this.mHandler, 9, (Object)this.vx), zzqt.this.xT);
            zzqt.this.mHandler.sendMessageDelayed(Message.obtain(zzqt.this.mHandler, 10, (Object)this.vx), zzqt.this.xS);
            zzqt.this.yv = -1;
        }
        
        @Override
        public void zza(final ConnectionResult connectionResult, final Api<?> api, final int n) {
            this.onConnectionFailed(connectionResult);
        }
        
        @WorkerThread
        public void zzase() {
            while (this.vC.isConnected() && !this.yG.isEmpty()) {
                this.zzc(this.yG.remove());
            }
        }
        
        @WorkerThread
        public void zzasf() {
            this.yK = null;
        }
        
        ConnectionResult zzasg() {
            return this.yK;
        }
        
        @WorkerThread
        public void zzb(final int n, @NonNull final zzrd.zzb<?> zzb, @NonNull final TaskCompletionSource<Void> taskCompletionSource) {
            final Map map = (Map)this.wg.get(n);
            if (map != null && map.get(zzb) != null) {
                this.zzb(new zzpy.zze(n, map.get(zzb).wk, taskCompletionSource, this.wg));
            }
            else {
                taskCompletionSource.setException(new com.google.android.gms.common.api.zza(Status.wa));
                Log.wtf("GoogleApiManager", "Received call to unregister a listener without a matching registration call.", (Throwable)new Exception());
            }
        }
        
        @WorkerThread
        public void zzb(final int n, @NonNull final zzri zzri, @NonNull final TaskCompletionSource<Void> taskCompletionSource) {
            this.zzb(new zzpy.zzc(n, zzri, taskCompletionSource, this.wg));
        }
        
        @WorkerThread
        public void zzb(final zzpy zzpy) {
            if (this.vC.isConnected()) {
                this.zzc(zzpy);
                this.zzasi();
            }
            else {
                this.yG.add(zzpy);
                if (this.yK != null && this.yK.hasResolution()) {
                    this.onConnectionFailed(this.yK);
                }
                else {
                    this.connect();
                }
            }
        }
        
        @WorkerThread
        public void zzb(final zzqb zzqb) {
            this.yJ.add(zzqb);
        }
        
        @WorkerThread
        public void zzf(final int n, final boolean b) {
            final Iterator<zzpy> iterator = (Iterator<zzpy>)this.yG.iterator();
            while (iterator.hasNext()) {
                final zzpy zzpy = iterator.next();
                if (zzpy.wf == n && zzpy.lN != 1 && zzpy.cancel()) {
                    iterator.remove();
                }
            }
            ((zzrq)this.yI.get(n)).release();
            this.wg.delete(n);
            if (!b) {
                this.yI.remove(n);
                zzqt.this.yC.remove(n);
                if (this.yI.size() == 0 && this.yG.isEmpty()) {
                    this.zzash();
                    this.vC.disconnect();
                    zzqt.this.yy.remove(this.vx);
                    synchronized (zzqt.zzaok) {
                        zzqt.this.yA.remove(this.vx);
                    }
                }
            }
        }
        
        @WorkerThread
        public void zzfw(final int n) {
            this.yI.put(n, (Object)new zzrq(this.vC));
        }
        
        @WorkerThread
        public void zzfx(final int n) {
            ((zzrq)this.yI.get(n)).zza((zzrq.zzc)new zzrq.zzc() {
                @Override
                public void zzask() {
                    if (zzqt.zzc.this.yG.isEmpty()) {
                        zzqt.zzc.this.zzf(n, false);
                    }
                }
            });
        }
    }
    
    private class zzd implements zzf
    {
        private final Api.zze vC;
        private final zzpz<?> vx;
        
        public zzd(final Api.zze vc, final zzpz<?> vx) {
            this.vC = vc;
            this.vx = vx;
        }
        
        @WorkerThread
        @Override
        public void zzh(@NonNull final ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                this.vC.zza(null, Collections.emptySet());
            }
            else {
                zzqt.this.yy.get(this.vx).onConnectionFailed(connectionResult);
            }
        }
    }
}
