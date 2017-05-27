// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.Pair;
import android.os.Message;
import android.os.Handler;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.api.ResultTransform;
import java.util.concurrent.TimeUnit;
import android.util.Log;
import java.util.Iterator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.internal.zzac;
import android.os.Looper;
import java.util.concurrent.CountDownLatch;
import com.google.android.gms.common.internal.zzs;
import java.util.concurrent.atomic.AtomicReference;
import com.google.android.gms.common.api.ResultCallback;
import java.util.ArrayList;
import com.google.android.gms.common.api.GoogleApiClient;
import java.lang.ref.WeakReference;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;

public abstract class zzqe<R extends Result> extends PendingResult<R>
{
    static final ThreadLocal<Boolean> wF;
    private R vU;
    private final Object wG;
    protected final zza<R> wH;
    protected final WeakReference<GoogleApiClient> wI;
    private final ArrayList<PendingResult.zza> wJ;
    private ResultCallback<? super R> wK;
    private final AtomicReference<zzrq.zzb> wL;
    private zzb wM;
    private volatile boolean wN;
    private boolean wO;
    private zzs wP;
    private volatile zzrp<R> wQ;
    private boolean wR;
    private boolean zzak;
    private final CountDownLatch zzamx;
    
    static {
        wF = new ThreadLocal<Boolean>() {
            protected Boolean zzaqv() {
                return false;
            }
        };
    }
    
    zzqe() {
        this.wG = new Object();
        this.zzamx = new CountDownLatch(1);
        this.wJ = new ArrayList<PendingResult.zza>();
        this.wL = new AtomicReference<zzrq.zzb>();
        this.wR = false;
        this.wH = new zza<R>(Looper.getMainLooper());
        this.wI = new WeakReference<GoogleApiClient>(null);
    }
    
    protected zzqe(final Looper looper) {
        this.wG = new Object();
        this.zzamx = new CountDownLatch(1);
        this.wJ = new ArrayList<PendingResult.zza>();
        this.wL = new AtomicReference<zzrq.zzb>();
        this.wR = false;
        this.wH = new zza<R>(looper);
        this.wI = new WeakReference<GoogleApiClient>(null);
    }
    
    protected zzqe(final GoogleApiClient googleApiClient) {
        this.wG = new Object();
        this.zzamx = new CountDownLatch(1);
        this.wJ = new ArrayList<PendingResult.zza>();
        this.wL = new AtomicReference<zzrq.zzb>();
        this.wR = false;
        Looper looper;
        if (googleApiClient != null) {
            looper = googleApiClient.getLooper();
        }
        else {
            looper = Looper.getMainLooper();
        }
        this.wH = new zza<R>(looper);
        this.wI = new WeakReference<GoogleApiClient>(googleApiClient);
    }
    
    private R get() {
        boolean b = true;
        synchronized (this.wG) {
            if (this.wN) {
                b = false;
            }
            zzac.zza(b, (Object)"Result has already been consumed.");
            zzac.zza(this.isReady(), (Object)"Result is not ready.");
            final Result vu = this.vU;
            this.vU = null;
            this.wK = null;
            this.wN = true;
            // monitorexit(this.wG)
            this.zzaqr();
            return (R)vu;
        }
    }
    
    private void zzaqr() {
        final zzrq.zzb zzb = this.wL.getAndSet(null);
        if (zzb != null) {
            zzb.zzc(this);
        }
    }
    
    private void zzd(final R vu) {
        this.vU = vu;
        this.wP = null;
        this.zzamx.countDown();
        final Status status = this.vU.getStatus();
        if (this.zzak) {
            this.wK = null;
        }
        else if (this.wK == null) {
            if (this.vU instanceof Releasable) {
                this.wM = new zzb();
            }
        }
        else {
            this.wH.zzaqw();
            this.wH.zza(this.wK, this.get());
        }
        final Iterator<PendingResult.zza> iterator = this.wJ.iterator();
        while (iterator.hasNext()) {
            iterator.next().zzv(status);
        }
        this.wJ.clear();
    }
    
    public static void zze(final Result result) {
        if (!(result instanceof Releasable)) {
            return;
        }
        try {
            ((Releasable)result).release();
        }
        catch (RuntimeException ex) {
            final String value = String.valueOf(result);
            Log.w("BasePendingResult", new StringBuilder(18 + String.valueOf(value).length()).append("Unable to release ").append(value).toString(), (Throwable)ex);
        }
    }
    
    @Override
    public final R await() {
        boolean b = true;
        while (true) {
        Label_0073_Outer:
            while (true) {
                boolean b3 = false;
                Label_0028: {
                    while (true) {
                        boolean b2 = false;
                        Label_0013: {
                            if (Looper.myLooper() != Looper.getMainLooper()) {
                                b2 = b;
                                break Label_0013;
                            }
                            Label_0068: {
                                break Label_0068;
                                while (true) {
                                    zzac.zza(b, (Object)"Cannot await if then() has been called.");
                                    while (true) {
                                        try {
                                            this.zzamx.await();
                                            zzac.zza(this.isReady(), (Object)"Result is not ready.");
                                            return this.get();
                                            b3 = false;
                                            break Label_0028;
                                            b2 = false;
                                            break;
                                            b = false;
                                        }
                                        catch (InterruptedException ex) {
                                            this.zzaa(Status.vZ);
                                            continue Label_0073_Outer;
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        zzac.zza(b2, (Object)"await must not be called on the UI thread");
                        if (this.wN) {
                            continue;
                        }
                        break;
                    }
                    b3 = b;
                }
                zzac.zza(b3, (Object)"Result has already been consumed");
                if (this.wQ == null) {
                    continue Label_0073_Outer;
                }
                break;
            }
            continue;
        }
    }
    
    @Override
    public final R await(final long n, final TimeUnit timeUnit) {
        boolean b = true;
        while (true) {
        Label_0101_Outer:
            while (true) {
                boolean b3 = false;
                Label_0041: {
                    while (true) {
                        boolean b2 = false;
                        Label_0022: {
                            if (n <= 0L || Looper.myLooper() != Looper.getMainLooper()) {
                                b2 = b;
                                break Label_0022;
                            }
                            Label_0095: {
                                break Label_0095;
                            Label_0081_Outer:
                                while (true) {
                                    zzac.zza(b, (Object)"Cannot await if then() has been called.");
                                    while (true) {
                                        try {
                                            if (!this.zzamx.await(n, timeUnit)) {
                                                this.zzaa(Status.wb);
                                            }
                                            zzac.zza(this.isReady(), (Object)"Result is not ready.");
                                            return this.get();
                                            b = false;
                                            continue Label_0081_Outer;
                                            b3 = false;
                                            break Label_0041;
                                            b2 = false;
                                            break;
                                        }
                                        catch (InterruptedException ex) {
                                            this.zzaa(Status.vZ);
                                            continue Label_0101_Outer;
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        zzac.zza(b2, (Object)"await must not be called on the UI thread when time is greater than zero.");
                        if (this.wN) {
                            continue;
                        }
                        break;
                    }
                    b3 = b;
                }
                zzac.zza(b3, (Object)"Result has already been consumed.");
                if (this.wQ == null) {
                    continue Label_0101_Outer;
                }
                break;
            }
            continue;
        }
    }
    
    @Override
    public void cancel() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/internal/zzqe.wG:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/internal/zzqe.zzak:Z
        //    11: ifne            21
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/internal/zzqe.wN:Z
        //    18: ifeq            26
        //    21: aload_1        
        //    22: monitorexit    
        //    23: goto            82
        //    26: aload_0        
        //    27: getfield        com/google/android/gms/internal/zzqe.wP:Lcom/google/android/gms/common/internal/zzs;
        //    30: astore_3       
        //    31: aload_3        
        //    32: ifnull          44
        //    35: aload_0        
        //    36: getfield        com/google/android/gms/internal/zzqe.wP:Lcom/google/android/gms/common/internal/zzs;
        //    39: invokeinterface com/google/android/gms/common/internal/zzs.cancel:()V
        //    44: aload_0        
        //    45: getfield        com/google/android/gms/internal/zzqe.vU:Lcom/google/android/gms/common/api/Result;
        //    48: invokestatic    com/google/android/gms/internal/zzqe.zze:(Lcom/google/android/gms/common/api/Result;)V
        //    51: aload_0        
        //    52: iconst_1       
        //    53: putfield        com/google/android/gms/internal/zzqe.zzak:Z
        //    56: aload_0        
        //    57: aload_0        
        //    58: getstatic       com/google/android/gms/common/api/Status.wc:Lcom/google/android/gms/common/api/Status;
        //    61: invokevirtual   com/google/android/gms/internal/zzqe.zzc:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/common/api/Result;
        //    64: invokespecial   com/google/android/gms/internal/zzqe.zzd:(Lcom/google/android/gms/common/api/Result;)V
        //    67: aload_1        
        //    68: monitorexit    
        //    69: goto            82
        //    72: astore_2       
        //    73: aload_1        
        //    74: monitorexit    
        //    75: aload_2        
        //    76: athrow         
        //    77: astore          4
        //    79: goto            44
        //    82: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      31     72     77     Any
        //  35     44     77     82     Landroid/os/RemoteException;
        //  35     44     72     77     Any
        //  44     75     72     77     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0044:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public boolean isCanceled() {
        synchronized (this.wG) {
            return this.zzak;
        }
    }
    
    public final boolean isReady() {
        return this.zzamx.getCount() == 0L;
    }
    
    @Override
    public final void setResultCallback(final ResultCallback<? super R> wk) {
        boolean b = true;
        final Object wg = this.wG;
        // monitorenter(wg)
        while (true) {
            if (wk == null) {
            Label_0047_Outer:
                while (true) {
                Label_0047:
                    while (true) {
                        while (true) {
                            try {
                                this.wK = null;
                                break;
                                boolean b2 = false;
                                zzac.zza(b2, (Object)"Result has already been consumed.");
                                // iftrue(Label_0079:, this.wQ != null)
                                break Label_0047;
                                while (true) {
                                    break;
                                    b2 = b;
                                    continue Label_0047_Outer;
                                    zzac.zza(b, (Object)"Cannot set callbacks if then() has been called.");
                                    continue;
                                }
                            }
                            // iftrue(Label_0073:, this.wN)
                            // iftrue(Label_0084:, !this.isCanceled())
                            finally {
                            }
                            // monitorexit(wg)
                            Label_0073: {
                                final boolean b2 = false;
                            }
                            continue Label_0047_Outer;
                        }
                        Label_0079: {
                            b = false;
                        }
                        continue Label_0047;
                    }
                    Label_0084: {
                        if (this.isReady()) {
                            this.wH.zza(wk, this.get());
                        }
                        else {
                            this.wK = wk;
                        }
                    }
                    // monitorexit(wg)
                    break;
                }
                return;
            }
            continue;
        }
    }
    
    @Override
    public final void setResultCallback(final ResultCallback<? super R> wk, final long n, final TimeUnit timeUnit) {
        boolean b = true;
        final Object wg = this.wG;
        // monitorenter(wg)
        while (true) {
            if (wk == null) {
            Label_0052_Outer:
                while (true) {
                Label_0038_Outer:
                    while (true) {
                        while (true) {
                            try {
                                this.wK = null;
                                // iftrue(Label_0093:, !this.isCanceled())
                                while (true) {
                                    break;
                                    zzac.zza(b, (Object)"Cannot set callbacks if then() has been called.");
                                    continue Label_0052_Outer;
                                }
                                // iftrue(Label_0081:, this.wN)
                                // iftrue(Label_0087:, this.wQ != null)
                                Block_4: {
                                    break Block_4;
                                    final boolean b2;
                                    zzac.zza(b2, (Object)"Result has already been consumed.");
                                    continue Label_0038_Outer;
                                }
                                final boolean b2 = b;
                                continue;
                            }
                            finally {
                            }
                            // monitorexit(wg)
                            Label_0081: {
                                final boolean b2 = false;
                            }
                            continue;
                        }
                        Label_0087: {
                            b = false;
                        }
                        continue Label_0038_Outer;
                    }
                    Label_0093: {
                        if (this.isReady()) {
                            this.wH.zza(wk, this.get());
                        }
                        else {
                            this.wK = wk;
                            this.wH.zza(this, timeUnit.toMillis(n));
                        }
                    }
                    // monitorexit(wg)
                    break;
                }
                return;
            }
            continue;
        }
    }
    
    @Override
    public <S extends Result> TransformedResult<S> then(final ResultTransform<? super R, ? extends S> resultTransform) {
        boolean b = true;
        while (true) {
            Label_0135: {
                if (this.wN) {
                    break Label_0135;
                }
                final boolean b2 = b;
            Label_0051_Outer:
                while (true) {
                    zzac.zza(b2, (Object)"Result has already been consumed.");
                    while (true) {
                    Label_0146:
                        while (true) {
                            Label_0140: {
                                synchronized (this.wG) {
                                    if (this.wQ != null) {
                                        break Label_0140;
                                    }
                                    final boolean b3 = b;
                                    zzac.zza(b3, (Object)"Cannot call then() twice.");
                                    if (this.wK == null) {
                                        zzac.zza(b, (Object)"Cannot call then() if callbacks are set.");
                                        this.wR = true;
                                        this.wQ = new zzrp<R>(this.wI);
                                        final TransformedResult<S> then = this.wQ.then(resultTransform);
                                        if (this.isReady()) {
                                            this.wH.zza(this.wQ, this.get());
                                        }
                                        else {
                                            this.wK = this.wQ;
                                        }
                                        return then;
                                    }
                                    break Label_0146;
                                }
                                break;
                            }
                            final boolean b3 = false;
                            continue Label_0051_Outer;
                        }
                        b = false;
                        continue;
                    }
                }
            }
            final boolean b2 = false;
            continue;
        }
    }
    
    @Override
    public final void zza(final PendingResult.zza zza) {
        boolean b = true;
        while (true) {
            Label_0083: {
                if (this.wN) {
                    break Label_0083;
                }
                final boolean b2 = b;
                zzac.zza(b2, (Object)"Result has already been consumed.");
                if (zza == null) {
                    b = false;
                }
                zzac.zzb(b, (Object)"Callback cannot be null.");
                synchronized (this.wG) {
                    if (this.isReady()) {
                        zza.zzv(this.vU.getStatus());
                    }
                    else {
                        this.wJ.add(zza);
                    }
                    return;
                }
            }
            final boolean b2 = false;
            continue;
        }
    }
    
    protected final void zza(final zzs wp) {
        synchronized (this.wG) {
            this.wP = wp;
        }
    }
    
    public void zza(final zzrq.zzb zzb) {
        this.wL.set(zzb);
    }
    
    public final void zzaa(final Status status) {
        synchronized (this.wG) {
            if (!this.isReady()) {
                this.zzc(this.zzc(status));
                this.wO = true;
            }
        }
    }
    
    @Override
    public Integer zzaqf() {
        return null;
    }
    
    public boolean zzaqq() {
        synchronized (this.wG) {
            if (this.wI.get() == null || !this.wR) {
                this.cancel();
            }
            return this.isCanceled();
        }
    }
    
    public void zzaqs() {
        this.setResultCallback(null);
    }
    
    public void zzaqt() {
        this.wR = (this.wR || zzqe.wF.get());
    }
    
    boolean zzaqu() {
        return false;
    }
    
    protected abstract R zzc(final Status p0);
    
    public final void zzc(final R r) {
    Label_0071_Outer:
        while (true) {
            boolean b = true;
            while (true) {
            Label_0100:
                while (true) {
                    synchronized (this.wG) {
                        if (this.wO || this.zzak || (this.isReady() && this.zzaqu())) {
                            zze(r);
                            break;
                        }
                        if (!this.isReady()) {
                            final boolean b2 = b;
                            zzac.zza(b2, (Object)"Results have already been set");
                            if (!this.wN) {
                                zzac.zza(b, (Object)"Result has already been consumed");
                                this.zzd(r);
                                break;
                            }
                            break Label_0100;
                        }
                    }
                    final boolean b2 = false;
                    continue Label_0071_Outer;
                }
                b = false;
                continue;
            }
        }
    }
    
    public static class zza<R extends Result> extends Handler
    {
        public zza() {
            this(Looper.getMainLooper());
        }
        
        public zza(final Looper looper) {
            super(looper);
        }
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {
                    Log.wtf("BasePendingResult", new StringBuilder(45).append("Don't know how to handle message: ").append(message.what).toString(), (Throwable)new Exception());
                    break;
                }
                case 1: {
                    final Pair pair = (Pair)message.obj;
                    this.zzb((ResultCallback<? super Result>)pair.first, (Result)pair.second);
                    break;
                }
                case 2: {
                    ((zzqe)message.obj).zzaa(Status.wb);
                    break;
                }
            }
        }
        
        public void zza(final ResultCallback<? super R> resultCallback, final R r) {
            this.sendMessage(this.obtainMessage(1, (Object)new Pair((Object)resultCallback, (Object)r)));
        }
        
        public void zza(final zzqe<R> zzqe, final long n) {
            this.sendMessageDelayed(this.obtainMessage(2, (Object)zzqe), n);
        }
        
        public void zzaqw() {
            this.removeMessages(2);
        }
        
        protected void zzb(final ResultCallback<? super R> resultCallback, final R r) {
            try {
                resultCallback.onResult(r);
            }
            catch (RuntimeException ex) {
                zzqe.zze(r);
                throw ex;
            }
        }
    }
    
    private final class zzb
    {
        @Override
        protected void finalize() throws Throwable {
            zzqe.zze(zzqe.this.vU);
            super.finalize();
        }
    }
}
