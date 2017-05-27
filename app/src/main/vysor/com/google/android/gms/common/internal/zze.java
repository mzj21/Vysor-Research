// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.RemoteException;
import android.content.ComponentName;
import android.os.Message;
import android.app.PendingIntent;
import java.util.Collections;
import android.os.DeadObjectException;
import android.content.Intent;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import android.support.annotation.NonNull;
import android.support.annotation.BinderThread;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.CallSuper;
import com.google.android.gms.common.ConnectionResult;
import android.accounts.Account;
import com.google.android.gms.common.api.CommonStatusCodes;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.os.Bundle;
import android.content.ServiceConnection;
import android.util.Log;
import android.os.Looper;
import com.google.android.gms.common.zzc;
import android.os.Handler;
import android.content.Context;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import android.os.IInterface;

public abstract class zze<T extends IInterface>
{
    public static final String[] Bs;
    private int Ba;
    private long Bb;
    private long Bc;
    private int Bd;
    private long Be;
    private final zzn Bf;
    private final Object Bg;
    private zzv Bh;
    private zzf Bi;
    private T Bj;
    private final ArrayList<zze<?>> Bk;
    private zzh Bl;
    private int Bm;
    private final zzb Bn;
    private final zzc Bo;
    private final int Bp;
    private final String Bq;
    protected AtomicInteger Br;
    private final Context mContext;
    final Handler mHandler;
    private final com.google.android.gms.common.zzc xn;
    private final Looper zzajn;
    private final Object zzakd;
    
    static {
        Bs = new String[] { "service_esmobile", "service_googleme" };
    }
    
    protected zze(final Context context, final Looper looper, final int n, final zzb zzb, final zzc zzc, final String s) {
        this(context, looper, zzn.zzcf(context), com.google.android.gms.common.zzc.zzapd(), n, zzac.zzy(zzb), zzac.zzy(zzc), s);
    }
    
    protected zze(final Context context, final Looper looper, final zzn zzn, final com.google.android.gms.common.zzc zzc, final int bp, final zzb bn, final zzc bo, final String bq) {
        this.zzakd = new Object();
        this.Bg = new Object();
        this.Bk = new ArrayList<zze<?>>();
        this.Bm = 1;
        this.Br = new AtomicInteger(0);
        this.mContext = zzac.zzb(context, "Context must not be null");
        this.zzajn = zzac.zzb(looper, "Looper must not be null");
        this.Bf = zzac.zzb(zzn, "Supervisor must not be null");
        this.xn = zzac.zzb(zzc, "API availability must not be null");
        this.mHandler = new zzd(looper);
        this.Bp = bp;
        this.Bn = bn;
        this.Bo = bo;
        this.Bq = bq;
    }
    
    private boolean zza(final int n, final int n2, final T t) {
        boolean b;
        synchronized (this.zzakd) {
            if (this.Bm != n) {
                // monitorexit(this.zzakd)
                b = false;
            }
            else {
                this.zzb(n2, t);
                b = true;
            }
        }
        return b;
    }
    
    private void zzats() {
        if (this.Bl != null) {
            final String value = String.valueOf(this.zzix());
            final String value2 = String.valueOf(this.zzatq());
            Log.e("GmsClient", new StringBuilder(70 + String.valueOf(value).length() + String.valueOf(value2).length()).append("Calling connect() while still connected, missing disconnect() for ").append(value).append(" on ").append(value2).toString());
            this.Bf.zzb(this.zzix(), this.zzatq(), (ServiceConnection)this.Bl, this.zzatr());
            this.Br.incrementAndGet();
        }
        this.Bl = new zzh(this.Br.get());
        if (!this.Bf.zza(this.zzix(), this.zzatq(), (ServiceConnection)this.Bl, this.zzatr())) {
            final String value3 = String.valueOf(this.zzix());
            final String value4 = String.valueOf(this.zzatq());
            Log.e("GmsClient", new StringBuilder(34 + String.valueOf(value3).length() + String.valueOf(value4).length()).append("unable to connect to service: ").append(value3).append(" on ").append(value4).toString());
            this.zza(16, null, this.Br.get());
        }
    }
    
    private void zzatt() {
        if (this.Bl != null) {
            this.Bf.zzb(this.zzix(), this.zzatq(), (ServiceConnection)this.Bl, this.zzatr());
            this.Bl = null;
        }
    }
    
    private void zzb(final int bm, final T bj) {
        boolean b = true;
        if ((bm == 3 && b) != (bj != null && b)) {
            b = false;
        }
        while (true) {
            zzac.zzbs(b);
            Label_0107: {
                Label_0099: {
                    synchronized (this.zzakd) {
                        this.zzc(this.Bm = bm, this.Bj = bj);
                        switch (bm) {
                            case 2: {
                                this.zzats();
                                break;
                            }
                            case 3: {
                                break Label_0099;
                            }
                            case 1: {
                                break Label_0107;
                            }
                        }
                        return;
                    }
                }
                this.zza(bj);
                return;
            }
            this.zzatt();
        }
    }
    
    public void disconnect() {
        this.Br.incrementAndGet();
        final ArrayList<zze<?>> bk = this.Bk;
        final Object bg;
        synchronized (bk) {
            for (int size = this.Bk.size(), i = 0; i < size; ++i) {
                this.Bk.get(i).zzaud();
            }
            this.Bk.clear();
            // monitorexit(bk)
            bg = this.Bg;
            // monitorenter(bg)
            final zze zze = this;
            final zzv zzv = null;
            zze.Bh = zzv;
            final Object o = bg;
            // monitorexit(o)
            final zze zze2 = this;
            final int n = 1;
            final IInterface interface1 = null;
            zze2.zzb(n, (T)interface1);
            return;
        }
        try {
            final zze zze = this;
            final zzv zzv = null;
            zze.Bh = zzv;
            final Object o = bg;
            // monitorexit(o)
            final zze zze2 = this;
            final int n = 1;
            final IInterface interface1 = null;
            zze2.zzb(n, (T)interface1);
        }
        finally {
        }
        // monitorexit(bg)
    }
    
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        int bm;
        IInterface bj = null;
        SimpleDateFormat simpleDateFormat;
        PrintWriter append;
        long bc;
        String value;
        PrintWriter append2;
        long bb;
        String value2;
        PrintWriter append3;
        long be;
        String value3;
        Label_0095_Outer:Label_0260_Outer:
        while (true) {
            while (true) {
                Label_0540: {
                Label_0529:
                    while (true) {
                    Label_0496:
                        while (true) {
                            Label_0486: {
                                Label_0476: {
                                    Label_0466: {
                                        synchronized (this.zzakd) {
                                            bm = this.Bm;
                                            bj = this.Bj;
                                            // monitorexit(this.zzakd)
                                            printWriter.append(s).append("mConnectState=");
                                            switch (bm) {
                                                default: {
                                                    printWriter.print("UNKNOWN");
                                                    printWriter.append(" mService=");
                                                    if (bj == null) {
                                                        printWriter.println("null");
                                                        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
                                                        if (this.Bc > 0L) {
                                                            append = printWriter.append(s).append("lastConnectedTime=");
                                                            bc = this.Bc;
                                                            value = String.valueOf(simpleDateFormat.format(new Date(this.Bc)));
                                                            append.println(new StringBuilder(21 + String.valueOf(value).length()).append(bc).append(" ").append(value).toString());
                                                        }
                                                        if (this.Bb > 0L) {
                                                            printWriter.append(s).append("lastSuspendedCause=");
                                                            switch (this.Ba) {
                                                                default: {
                                                                    printWriter.append(String.valueOf(this.Ba));
                                                                    append2 = printWriter.append(" lastSuspendedTime=");
                                                                    bb = this.Bb;
                                                                    value2 = String.valueOf(simpleDateFormat.format(new Date(this.Bb)));
                                                                    append2.println(new StringBuilder(21 + String.valueOf(value2).length()).append(bb).append(" ").append(value2).toString());
                                                                    break;
                                                                }
                                                                case 1: {
                                                                    break Label_0529;
                                                                }
                                                                case 2: {
                                                                    break Label_0540;
                                                                }
                                                            }
                                                        }
                                                        if (this.Be > 0L) {
                                                            printWriter.append(s).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.Bd));
                                                            append3 = printWriter.append(" lastFailedTime=");
                                                            be = this.Be;
                                                            value3 = String.valueOf(simpleDateFormat.format(new Date(this.Be)));
                                                            append3.println(new StringBuilder(21 + String.valueOf(value3).length()).append(be).append(" ").append(value3).toString());
                                                        }
                                                        return;
                                                    }
                                                    break Label_0496;
                                                }
                                                case 2: {
                                                    break;
                                                }
                                                case 3: {
                                                    break Label_0466;
                                                }
                                                case 4: {
                                                    break Label_0476;
                                                }
                                                case 1: {
                                                    break Label_0486;
                                                }
                                            }
                                        }
                                        printWriter.print("CONNECTING");
                                        continue Label_0095_Outer;
                                    }
                                    printWriter.print("CONNECTED");
                                    continue Label_0095_Outer;
                                }
                                printWriter.print("DISCONNECTING");
                                continue Label_0095_Outer;
                            }
                            printWriter.print("DISCONNECTED");
                            continue Label_0095_Outer;
                        }
                        printWriter.append(this.zziy()).append("@").println(Integer.toHexString(System.identityHashCode(bj.asBinder())));
                        continue Label_0260_Outer;
                    }
                    printWriter.append("CAUSE_SERVICE_DISCONNECTED");
                    continue;
                }
                printWriter.append("CAUSE_NETWORK_LOST");
                continue;
            }
        }
    }
    
    public Account getAccount() {
        return null;
    }
    
    public final Context getContext() {
        return this.mContext;
    }
    
    public final Looper getLooper() {
        return this.zzajn;
    }
    
    public boolean isConnected() {
        while (true) {
            synchronized (this.zzakd) {
                if (this.Bm == 3) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public boolean isConnecting() {
        while (true) {
            synchronized (this.zzakd) {
                if (this.Bm == 2) {
                    return true;
                }
            }
            return false;
        }
    }
    
    @CallSuper
    protected void onConnectionFailed(final ConnectionResult connectionResult) {
        this.Bd = connectionResult.getErrorCode();
        this.Be = System.currentTimeMillis();
    }
    
    @CallSuper
    protected void onConnectionSuspended(final int ba) {
        this.Ba = ba;
        this.Bb = System.currentTimeMillis();
    }
    
    protected void zza(final int n, @Nullable final Bundle bundle, final int n2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(5, n2, -1, (Object)new zzk(n, bundle)));
    }
    
    @BinderThread
    protected void zza(final int n, final IBinder binder, final Bundle bundle, final int n2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, n2, -1, (Object)new zzj(n, binder, bundle)));
    }
    
    @CallSuper
    protected void zza(@NonNull final T t) {
        this.Bc = System.currentTimeMillis();
    }
    
    public void zza(@NonNull final zzf zzf) {
        this.Bi = zzac.zzb(zzf, "Connection progress callbacks cannot be null.");
        this.zzb(2, null);
    }
    
    public void zza(final zzf zzf, final ConnectionResult connectionResult) {
        this.Bi = zzac.zzb(zzf, "Connection progress callbacks cannot be null.");
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.Br.get(), connectionResult.getErrorCode(), (Object)connectionResult.getResolution()));
    }
    
    @WorkerThread
    public void zza(final zzr p0, final Set<Scope> p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/google/android/gms/common/internal/zze.zzagl:()Landroid/os/Bundle;
        //     4: astore          7
        //     6: new             Lcom/google/android/gms/common/internal/GetServiceRequest;
        //     9: dup            
        //    10: aload_0        
        //    11: getfield        com/google/android/gms/common/internal/zze.Bp:I
        //    14: invokespecial   com/google/android/gms/common/internal/GetServiceRequest.<init>:(I)V
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/common/internal/zze.mContext:Landroid/content/Context;
        //    21: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    24: invokevirtual   com/google/android/gms/common/internal/GetServiceRequest.zzht:(Ljava/lang/String;)Lcom/google/android/gms/common/internal/GetServiceRequest;
        //    27: aload           7
        //    29: invokevirtual   com/google/android/gms/common/internal/GetServiceRequest.zzo:(Landroid/os/Bundle;)Lcom/google/android/gms/common/internal/GetServiceRequest;
        //    32: astore          8
        //    34: aload_2        
        //    35: ifnull          45
        //    38: aload           8
        //    40: aload_2        
        //    41: invokevirtual   com/google/android/gms/common/internal/GetServiceRequest.zzf:(Ljava/util/Collection;)Lcom/google/android/gms/common/internal/GetServiceRequest;
        //    44: pop            
        //    45: aload_0        
        //    46: invokevirtual   com/google/android/gms/common/internal/zze.zzahd:()Z
        //    49: ifeq            112
        //    52: aload           8
        //    54: aload_0        
        //    55: invokevirtual   com/google/android/gms/common/internal/zze.zzatv:()Landroid/accounts/Account;
        //    58: invokevirtual   com/google/android/gms/common/internal/GetServiceRequest.zzd:(Landroid/accounts/Account;)Lcom/google/android/gms/common/internal/GetServiceRequest;
        //    61: aload_1        
        //    62: invokevirtual   com/google/android/gms/common/internal/GetServiceRequest.zzb:(Lcom/google/android/gms/common/internal/zzr;)Lcom/google/android/gms/common/internal/GetServiceRequest;
        //    65: pop            
        //    66: aload_0        
        //    67: getfield        com/google/android/gms/common/internal/zze.Bg:Ljava/lang/Object;
        //    70: astore          10
        //    72: aload           10
        //    74: monitorenter   
        //    75: aload_0        
        //    76: getfield        com/google/android/gms/common/internal/zze.Bh:Lcom/google/android/gms/common/internal/zzv;
        //    79: ifnull          151
        //    82: aload_0        
        //    83: getfield        com/google/android/gms/common/internal/zze.Bh:Lcom/google/android/gms/common/internal/zzv;
        //    86: new             Lcom/google/android/gms/common/internal/zze$zzg;
        //    89: dup            
        //    90: aload_0        
        //    91: aload_0        
        //    92: getfield        com/google/android/gms/common/internal/zze.Br:Ljava/util/concurrent/atomic/AtomicInteger;
        //    95: invokevirtual   java/util/concurrent/atomic/AtomicInteger.get:()I
        //    98: invokespecial   com/google/android/gms/common/internal/zze$zzg.<init>:(Lcom/google/android/gms/common/internal/zze;I)V
        //   101: aload           8
        //   103: invokeinterface com/google/android/gms/common/internal/zzv.zza:(Lcom/google/android/gms/common/internal/zzu;Lcom/google/android/gms/common/internal/GetServiceRequest;)V
        //   108: aload           10
        //   110: monitorexit    
        //   111: return         
        //   112: aload_0        
        //   113: invokevirtual   com/google/android/gms/common/internal/zze.zzaty:()Z
        //   116: ifeq            66
        //   119: aload           8
        //   121: aload_0        
        //   122: invokevirtual   com/google/android/gms/common/internal/zze.getAccount:()Landroid/accounts/Account;
        //   125: invokevirtual   com/google/android/gms/common/internal/GetServiceRequest.zzd:(Landroid/accounts/Account;)Lcom/google/android/gms/common/internal/GetServiceRequest;
        //   128: pop            
        //   129: goto            66
        //   132: astore          5
        //   134: ldc             "GmsClient"
        //   136: ldc_w           "service died"
        //   139: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   142: pop            
        //   143: aload_0        
        //   144: iconst_1       
        //   145: invokevirtual   com/google/android/gms/common/internal/zze.zzgl:(I)V
        //   148: goto            111
        //   151: ldc             "GmsClient"
        //   153: ldc_w           "mServiceBroker is null, client disconnected"
        //   156: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   159: pop            
        //   160: goto            108
        //   163: astore          11
        //   165: aload           10
        //   167: monitorexit    
        //   168: aload           11
        //   170: athrow         
        //   171: astore_3       
        //   172: ldc             "GmsClient"
        //   174: ldc_w           "Remote exception occurred"
        //   177: aload_3        
        //   178: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   181: pop            
        //   182: goto            111
        //    Signature:
        //  (Lcom/google/android/gms/common/internal/zzr;Ljava/util/Set<Lcom/google/android/gms/common/api/Scope;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  0      75     132    151    Landroid/os/DeadObjectException;
        //  0      75     171    185    Landroid/os/RemoteException;
        //  75     111    163    171    Any
        //  112    129    132    151    Landroid/os/DeadObjectException;
        //  112    129    171    185    Landroid/os/RemoteException;
        //  151    168    163    171    Any
        //  168    171    132    151    Landroid/os/DeadObjectException;
        //  168    171    171    185    Landroid/os/RemoteException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0108:
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
    
    protected Bundle zzagl() {
        return new Bundle();
    }
    
    public boolean zzahd() {
        return false;
    }
    
    public boolean zzahs() {
        return false;
    }
    
    public Intent zzaht() {
        throw new UnsupportedOperationException("Not a sign in API");
    }
    
    public Bundle zzaoe() {
        return null;
    }
    
    public boolean zzapr() {
        return true;
    }
    
    @Nullable
    public IBinder zzaps() {
        IBinder binder;
        synchronized (this.Bg) {
            if (this.Bh == null) {
                // monitorexit(this.Bg)
                binder = null;
            }
            else {
                binder = this.Bh.asBinder();
            }
        }
        return binder;
    }
    
    protected String zzatq() {
        return "com.google.android.gms";
    }
    
    @Nullable
    protected final String zzatr() {
        String s;
        if (this.Bq == null) {
            s = this.mContext.getClass().getName();
        }
        else {
            s = this.Bq;
        }
        return s;
    }
    
    public void zzatu() {
        final int googlePlayServicesAvailable = this.xn.isGooglePlayServicesAvailable(this.mContext);
        if (googlePlayServicesAvailable != 0) {
            this.zzb(1, null);
            this.Bi = (zzf)new zzi();
            this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.Br.get(), googlePlayServicesAvailable));
        }
        else {
            this.zza((zzf)new zzi());
        }
    }
    
    public final Account zzatv() {
        Account account;
        if (this.getAccount() != null) {
            account = this.getAccount();
        }
        else {
            account = new Account("<<default account>>", "com.google");
        }
        return account;
    }
    
    protected final void zzatw() {
        if (!this.isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }
    
    public final T zzatx() throws DeadObjectException {
        synchronized (this.zzakd) {
            if (this.Bm == 4) {
                throw new DeadObjectException();
            }
        }
        this.zzatw();
        zzac.zza(this.Bj != null, (Object)"Client is connected but service is null");
        final IInterface bj = this.Bj;
        // monitorexit(o)
        return (T)bj;
    }
    
    public boolean zzaty() {
        return false;
    }
    
    protected Set<Scope> zzatz() {
        return (Set<Scope>)Collections.EMPTY_SET;
    }
    
    void zzc(final int n, final T t) {
    }
    
    public void zzgl(final int n) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, this.Br.get(), n));
    }
    
    @Nullable
    protected abstract T zzh(final IBinder p0);
    
    @NonNull
    protected abstract String zzix();
    
    @NonNull
    protected abstract String zziy();
    
    private abstract class zza extends zze<Boolean>
    {
        public final Bundle Bt;
        public final int statusCode;
        
        protected zza(final int statusCode, final Bundle bt) {
            super(true);
            this.statusCode = statusCode;
            this.Bt = bt;
        }
        
        protected abstract boolean zzaua();
        
        @Override
        protected void zzaub() {
        }
        
        protected void zzc(final Boolean b) {
            if (b == null) {
                zze.this.zzb(1, null);
            }
            else {
                switch (this.statusCode) {
                    default: {
                        zze.this.zzb(1, null);
                        final Bundle bt = this.Bt;
                        PendingIntent pendingIntent = null;
                        if (bt != null) {
                            pendingIntent = (PendingIntent)this.Bt.getParcelable("pendingIntent");
                        }
                        this.zzm(new ConnectionResult(this.statusCode, pendingIntent));
                        break;
                    }
                    case 0: {
                        if (!this.zzaua()) {
                            zze.this.zzb(1, null);
                            this.zzm(new ConnectionResult(8, null));
                            break;
                        }
                        break;
                    }
                    case 10: {
                        zze.this.zzb(1, null);
                        throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
                    }
                }
            }
        }
        
        protected abstract void zzm(final ConnectionResult p0);
    }
    
    public interface zzb
    {
        void onConnected(@Nullable final Bundle p0);
        
        void onConnectionSuspended(final int p0);
    }
    
    public interface zzc
    {
        void onConnectionFailed(@NonNull final ConnectionResult p0);
    }
    
    final class zzd extends Handler
    {
        public zzd(final Looper looper) {
            super(looper);
        }
        
        private void zza(final Message message) {
            final zze zze = (zze)message.obj;
            zze.zzaub();
            zze.unregister();
        }
        
        private boolean zzb(final Message message) {
            boolean b = true;
            if (message.what != 2 && message.what != (b ? 1 : 0) && message.what != 5) {
                b = false;
            }
            return b;
        }
        
        public void handleMessage(final Message message) {
            if (zze.this.Br.get() != message.arg1) {
                if (this.zzb(message)) {
                    this.zza(message);
                }
            }
            else if ((message.what == 1 || message.what == 5) && !zze.this.isConnecting()) {
                this.zza(message);
            }
            else if (message.what == 3) {
                final boolean b = message.obj instanceof PendingIntent;
                PendingIntent pendingIntent = null;
                if (b) {
                    pendingIntent = (PendingIntent)message.obj;
                }
                final ConnectionResult connectionResult = new ConnectionResult(message.arg2, pendingIntent);
                zze.this.Bi.zzh(connectionResult);
                zze.this.onConnectionFailed(connectionResult);
            }
            else if (message.what == 4) {
                zze.this.zzb(4, null);
                if (zze.this.Bn != null) {
                    zze.this.Bn.onConnectionSuspended(message.arg2);
                }
                zze.this.onConnectionSuspended(message.arg2);
                zze.this.zza(4, 1, null);
            }
            else if (message.what == 2 && !zze.this.isConnected()) {
                this.zza(message);
            }
            else if (this.zzb(message)) {
                ((zze)message.obj).zzauc();
            }
            else {
                Log.wtf("GmsClient", new StringBuilder(45).append("Don't know how to handle message: ").append(message.what).toString(), (Throwable)new Exception());
            }
        }
    }
    
    protected abstract class zze<TListener>
    {
        private boolean Bv;
        private TListener mListener;
        
        public zze(final TListener mListener) {
            this.mListener = mListener;
            this.Bv = false;
        }
        
        public void unregister() {
            this.zzaud();
            synchronized (com.google.android.gms.common.internal.zze.this.Bk) {
                com.google.android.gms.common.internal.zze.this.Bk.remove(this);
            }
        }
        
        protected abstract void zzaub();
        
        public void zzauc() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: monitorenter   
            //     2: aload_0        
            //     3: getfield        com/google/android/gms/common/internal/zze$zze.mListener:Ljava/lang/Object;
            //     6: astore_2       
            //     7: aload_0        
            //     8: getfield        com/google/android/gms/common/internal/zze$zze.Bv:Z
            //    11: ifeq            62
            //    14: aload_0        
            //    15: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //    18: astore          5
            //    20: ldc             "GmsClient"
            //    22: new             Ljava/lang/StringBuilder;
            //    25: dup            
            //    26: bipush          47
            //    28: aload           5
            //    30: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //    33: invokevirtual   java/lang/String.length:()I
            //    36: iadd           
            //    37: invokespecial   java/lang/StringBuilder.<init>:(I)V
            //    40: ldc             "Callback proxy "
            //    42: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    45: aload           5
            //    47: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    50: ldc             " being reused. This is not safe."
            //    52: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    55: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //    58: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
            //    61: pop            
            //    62: aload_0        
            //    63: monitorexit    
            //    64: aload_2        
            //    65: ifnull          101
            //    68: aload_0        
            //    69: aload_2        
            //    70: invokevirtual   com/google/android/gms/common/internal/zze$zze.zzv:(Ljava/lang/Object;)V
            //    73: aload_0        
            //    74: monitorenter   
            //    75: aload_0        
            //    76: iconst_1       
            //    77: putfield        com/google/android/gms/common/internal/zze$zze.Bv:Z
            //    80: aload_0        
            //    81: monitorexit    
            //    82: aload_0        
            //    83: invokevirtual   com/google/android/gms/common/internal/zze$zze.unregister:()V
            //    86: return         
            //    87: astore_1       
            //    88: aload_0        
            //    89: monitorexit    
            //    90: aload_1        
            //    91: athrow         
            //    92: astore          4
            //    94: aload_0        
            //    95: invokevirtual   com/google/android/gms/common/internal/zze$zze.zzaub:()V
            //    98: aload           4
            //   100: athrow         
            //   101: aload_0        
            //   102: invokevirtual   com/google/android/gms/common/internal/zze$zze.zzaub:()V
            //   105: goto            73
            //   108: astore_3       
            //   109: aload_0        
            //   110: monitorexit    
            //   111: aload_3        
            //   112: athrow         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                        
            //  -----  -----  -----  -----  ----------------------------
            //  2      64     87     92     Any
            //  68     73     92     101    Ljava/lang/RuntimeException;
            //  75     82     108    113    Any
            //  88     90     87     92     Any
            //  109    111    108    113    Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IndexOutOfBoundsException: Index: 64, Size: 64
            //     at java.util.ArrayList.rangeCheck(ArrayList.java:635)
            //     at java.util.ArrayList.get(ArrayList.java:411)
            //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
            //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        public void zzaud() {
            synchronized (this) {
                this.mListener = null;
            }
        }
        
        protected abstract void zzv(final TListener p0);
    }
    
    public interface zzf
    {
        void zzh(@NonNull final ConnectionResult p0);
    }
    
    public static final class zzg extends zzu.zza
    {
        private zze Bw;
        private final int Bx;
        
        public zzg(@NonNull final zze bw, final int bx) {
            this.Bw = bw;
            this.Bx = bx;
        }
        
        private void zzaue() {
            this.Bw = null;
        }
        
        @BinderThread
        public void zza(final int n, @NonNull final IBinder binder, @Nullable final Bundle bundle) {
            zzac.zzb(this.Bw, "onPostInitComplete can be called only once per call to getRemoteService");
            this.Bw.zza(n, binder, bundle, this.Bx);
            this.zzaue();
        }
        
        @BinderThread
        public void zzb(final int n, @Nullable final Bundle bundle) {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", (Throwable)new Exception());
        }
    }
    
    public final class zzh implements ServiceConnection
    {
        private final int Bx;
        
        public zzh(final int bx) {
            this.Bx = bx;
        }
        
        public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
            zzac.zzb(binder, "Expecting a valid IBinder");
            synchronized (zze.this.Bg) {
                zze.this.Bh = zzv.zza.zzdv(binder);
                // monitorexit(zze.zza(this.Bu))
                zze.this.zza(0, null, this.Bx);
            }
        }
        
        public void onServiceDisconnected(final ComponentName componentName) {
            synchronized (zze.this.Bg) {
                zze.this.Bh = null;
                // monitorexit(zze.zza(this.Bu))
                zze.this.mHandler.sendMessage(zze.this.mHandler.obtainMessage(4, this.Bx, 1));
            }
        }
    }
    
    protected class zzi implements zzf
    {
        @Override
        public void zzh(@NonNull final ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                zze.this.zza(null, zze.this.zzatz());
            }
            else if (zze.this.Bo != null) {
                zze.this.Bo.onConnectionFailed(connectionResult);
            }
        }
    }
    
    protected final class zzj extends zza
    {
        public final IBinder By;
        
        public zzj(final int n, final IBinder by, final Bundle bundle) {
            super(n, bundle);
            this.By = by;
        }
        
        @Override
        protected boolean zzaua() {
            while (true) {
                boolean b = false;
                try {
                    final String interfaceDescriptor = this.By.getInterfaceDescriptor();
                    if (!zze.this.zziy().equals(interfaceDescriptor)) {
                        final String value = String.valueOf(zze.this.zziy());
                        Log.e("GmsClient", new StringBuilder(34 + String.valueOf(value).length() + String.valueOf(interfaceDescriptor).length()).append("service descriptor mismatch: ").append(value).append(" vs. ").append(interfaceDescriptor).toString());
                        return b;
                    }
                }
                catch (RemoteException ex) {
                    Log.w("GmsClient", "service probably died");
                    b = false;
                    return b;
                }
                final IInterface zzh = zze.this.zzh(this.By);
                b = false;
                if (zzh == null) {
                    return b;
                }
                final boolean zza = zze.this.zza(2, 3, zzh);
                b = false;
                if (zza) {
                    final Bundle zzaoe = zze.this.zzaoe();
                    if (zze.this.Bn != null) {
                        zze.this.Bn.onConnected(zzaoe);
                    }
                    b = true;
                    return b;
                }
                return b;
            }
        }
        
        @Override
        protected void zzm(final ConnectionResult connectionResult) {
            if (zze.this.Bo != null) {
                zze.this.Bo.onConnectionFailed(connectionResult);
            }
            zze.this.onConnectionFailed(connectionResult);
        }
    }
    
    protected final class zzk extends zza
    {
        public zzk(final int n, @Nullable final Bundle bundle) {
            super(n, bundle);
        }
        
        @Override
        protected boolean zzaua() {
            zze.this.Bi.zzh(ConnectionResult.uJ);
            return true;
        }
        
        @Override
        protected void zzm(final ConnectionResult connectionResult) {
            zze.this.Bi.zzh(connectionResult);
            zze.this.onConnectionFailed(connectionResult);
        }
    }
}
