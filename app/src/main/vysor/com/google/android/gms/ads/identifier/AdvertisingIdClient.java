// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.identifier;

import java.util.concurrent.CountDownLatch;
import java.lang.ref.WeakReference;
import android.os.RemoteException;
import android.util.Log;
import android.content.ServiceConnection;
import com.google.android.gms.common.stats.zzb;
import android.content.Intent;
import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.common.zzc;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import java.io.IOException;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzci;
import com.google.android.gms.common.zza;
import android.content.Context;

public class AdvertisingIdClient
{
    private final Context mContext;
    com.google.android.gms.common.zza zzaku;
    zzci zzakv;
    boolean zzakw;
    Object zzakx;
    zza zzaky;
    final long zzakz;
    
    public AdvertisingIdClient(final Context context) {
        this(context, 30000L);
    }
    
    public AdvertisingIdClient(final Context mContext, final long zzakz) {
        this.zzakx = new Object();
        zzac.zzy(mContext);
        this.mContext = mContext;
        this.zzakw = false;
        this.zzakz = zzakz;
    }
    
    public static Info getAdvertisingIdInfo(final Context context) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        final AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(context, -1L);
        try {
            advertisingIdClient.zze(false);
            return advertisingIdClient.getInfo();
        }
        finally {
            advertisingIdClient.finish();
        }
    }
    
    public static void setShouldSkipGmsCoreVersionCheck(final boolean b) {
    }
    
    static zzci zza(final Context context, final com.google.android.gms.common.zza zza) throws IOException {
        try {
            return zzci.zza.zzf(zza.zza(10000L, TimeUnit.MILLISECONDS));
        }
        catch (InterruptedException ex) {
            throw new IOException("Interrupted exception");
        }
        catch (Throwable t) {
            throw new IOException(t);
        }
    }
    
    private void zzdn() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzakx:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzaky:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    11: ifnull          28
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzaky:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    18: invokevirtual   com/google/android/gms/ads/identifier/AdvertisingIdClient$zza.cancel:()V
        //    21: aload_0        
        //    22: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzaky:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    25: invokevirtual   com/google/android/gms/ads/identifier/AdvertisingIdClient$zza.join:()V
        //    28: aload_0        
        //    29: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzakz:J
        //    32: lconst_0       
        //    33: lcmp           
        //    34: ifle            53
        //    37: aload_0        
        //    38: new             Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    41: dup            
        //    42: aload_0        
        //    43: aload_0        
        //    44: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzakz:J
        //    47: invokespecial   com/google/android/gms/ads/identifier/AdvertisingIdClient$zza.<init>:(Lcom/google/android/gms/ads/identifier/AdvertisingIdClient;J)V
        //    50: putfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzaky:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
        //    53: aload_1        
        //    54: monitorexit    
        //    55: return         
        //    56: astore_2       
        //    57: aload_1        
        //    58: monitorexit    
        //    59: aload_2        
        //    60: athrow         
        //    61: astore_3       
        //    62: goto            28
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  7      21     56     61     Any
        //  21     28     61     65     Ljava/lang/InterruptedException;
        //  21     28     56     61     Any
        //  28     59     56     61     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
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
    
    static com.google.android.gms.common.zza zzg(final Context context) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            switch (zzc.zzapd().isGooglePlayServicesAvailable(context)) {
                default: {
                    throw new IOException("Google Play services not available");
                }
                case 0:
                case 2: {
                    break;
                }
            }
        }
        catch (PackageManager$NameNotFoundException ex) {
            throw new GooglePlayServicesNotAvailableException(9);
        }
        final com.google.android.gms.common.zza zza = new com.google.android.gms.common.zza();
        final Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        try {
            if (zzb.zzawu().zza(context, intent, (ServiceConnection)zza, 1)) {
                return zza;
            }
        }
        catch (Throwable t) {
            throw new IOException(t);
        }
        throw new IOException("Connection failure");
    }
    
    @Override
    protected void finalize() throws Throwable {
        this.finish();
        super.finalize();
    }
    
    public void finish() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "Calling this from your main thread can lead to deadlock"
        //     2: invokestatic    com/google/android/gms/common/internal/zzac.zzhr:(Ljava/lang/String;)V
        //     5: aload_0        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.mContext:Landroid/content/Context;
        //    11: ifnull          21
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzaku:Lcom/google/android/gms/common/zza;
        //    18: ifnonnull       24
        //    21: aload_0        
        //    22: monitorexit    
        //    23: return         
        //    24: aload_0        
        //    25: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzakw:Z
        //    28: ifeq            45
        //    31: invokestatic    com/google/android/gms/common/stats/zzb.zzawu:()Lcom/google/android/gms/common/stats/zzb;
        //    34: aload_0        
        //    35: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.mContext:Landroid/content/Context;
        //    38: aload_0        
        //    39: getfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzaku:Lcom/google/android/gms/common/zza;
        //    42: invokevirtual   com/google/android/gms/common/stats/zzb.zza:(Landroid/content/Context;Landroid/content/ServiceConnection;)V
        //    45: aload_0        
        //    46: iconst_0       
        //    47: putfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzakw:Z
        //    50: aload_0        
        //    51: aconst_null    
        //    52: putfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzakv:Lcom/google/android/gms/internal/zzci;
        //    55: aload_0        
        //    56: aconst_null    
        //    57: putfield        com/google/android/gms/ads/identifier/AdvertisingIdClient.zzaku:Lcom/google/android/gms/common/zza;
        //    60: aload_0        
        //    61: monitorexit    
        //    62: goto            23
        //    65: astore_1       
        //    66: aload_0        
        //    67: monitorexit    
        //    68: aload_1        
        //    69: athrow         
        //    70: astore_2       
        //    71: ldc             "AdvertisingIdClient"
        //    73: ldc             "AdvertisingIdClient unbindService failed."
        //    75: aload_2        
        //    76: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    79: pop            
        //    80: goto            45
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  7      23     65     70     Any
        //  24     45     70     83     Ljava/lang/IllegalArgumentException;
        //  24     45     65     70     Any
        //  45     68     65     70     Any
        //  71     80     65     70     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
    
    public Info getInfo() throws IOException {
        zzac.zzhr("Calling this from your main thread can lead to deadlock");
        // monitorexit(o)
        Label_0102: {
            synchronized (this) {
                if (this.zzakw) {
                    break Label_0102;
                }
                synchronized (this.zzakx) {
                    if (this.zzaky == null || !this.zzaky.zzdo()) {
                        throw new IOException("AdvertisingIdClient is not connected.");
                    }
                }
            }
            try {
                this.zze(false);
                if (!this.zzakw) {
                    throw new IOException("AdvertisingIdClient cannot reconnect.");
                }
            }
            catch (Exception ex) {
                throw new IOException("AdvertisingIdClient cannot reconnect.", ex);
            }
        }
        zzac.zzy(this.zzaku);
        zzac.zzy(this.zzakv);
        try {
            final Info info = new Info(this.zzakv.getId(), this.zzakv.zzf(true));
            // monitorexit(this)
            this.zzdn();
            return info;
        }
        catch (RemoteException ex2) {
            Log.i("AdvertisingIdClient", "GMS remote exception ", (Throwable)ex2);
            throw new IOException("Remote exception");
        }
    }
    
    public void start() throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        this.zze(true);
    }
    
    protected void zze(final boolean b) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        zzac.zzhr("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.zzakw) {
                this.finish();
            }
            this.zzaku = zzg(this.mContext);
            this.zzakv = zza(this.mContext, this.zzaku);
            this.zzakw = true;
            if (b) {
                this.zzdn();
            }
        }
    }
    
    public static final class Info
    {
        private final String zzale;
        private final boolean zzalf;
        
        public Info(final String zzale, final boolean zzalf) {
            this.zzale = zzale;
            this.zzalf = zzalf;
        }
        
        public String getId() {
            return this.zzale;
        }
        
        public boolean isLimitAdTrackingEnabled() {
            return this.zzalf;
        }
        
        @Override
        public String toString() {
            final String zzale = this.zzale;
            return new StringBuilder(7 + String.valueOf(zzale).length()).append("{").append(zzale).append("}").append(this.zzalf).toString();
        }
    }
    
    static class zza extends Thread
    {
        private WeakReference<AdvertisingIdClient> zzala;
        private long zzalb;
        CountDownLatch zzalc;
        boolean zzald;
        
        public zza(final AdvertisingIdClient advertisingIdClient, final long zzalb) {
            this.zzala = new WeakReference<AdvertisingIdClient>(advertisingIdClient);
            this.zzalb = zzalb;
            this.zzalc = new CountDownLatch(1);
            this.zzald = false;
            this.start();
        }
        
        private void disconnect() {
            final AdvertisingIdClient advertisingIdClient = this.zzala.get();
            if (advertisingIdClient != null) {
                advertisingIdClient.finish();
                this.zzald = true;
            }
        }
        
        public void cancel() {
            this.zzalc.countDown();
        }
        
        @Override
        public void run() {
            try {
                if (!this.zzalc.await(this.zzalb, TimeUnit.MILLISECONDS)) {
                    this.disconnect();
                }
            }
            catch (InterruptedException ex) {
                this.disconnect();
            }
        }
        
        public boolean zzdo() {
            return this.zzald;
        }
    }
}
