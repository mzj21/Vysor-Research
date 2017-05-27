// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import android.content.pm.PackageInstaller$SessionInfo;
import android.content.pm.PackageInfo;
import com.google.android.gms.internal.zzsh;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zzsi;
import android.os.Bundle;
import android.os.UserManager;
import com.google.android.gms.common.util.zzs;
import android.app.NotificationManager;
import android.content.Intent;
import android.util.Log;
import android.annotation.TargetApi;
import com.google.android.gms.common.util.zzy;
import android.os.Build;
import com.google.android.gms.common.internal.zzf;
import android.content.res.Resources;
import android.content.pm.PackageManager$NameNotFoundException;
import java.io.InputStream;
import android.net.Uri;
import java.util.NoSuchElementException;
import java.util.Scanner;
import android.net.Uri$Builder;
import android.app.PendingIntent;
import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;

public class zze
{
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 0;
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    public static boolean uX;
    public static boolean uY;
    static boolean uZ;
    private static String va;
    private static int vb;
    private static boolean vc;
    static final AtomicBoolean vd;
    private static final AtomicBoolean ve;
    
    static {
        zze.uX = false;
        zze.uY = false;
        zze.uZ = false;
        zze.va = null;
        zze.vb = 0;
        zze.vc = false;
        vd = new AtomicBoolean();
        ve = new AtomicBoolean();
    }
    
    @Deprecated
    public static PendingIntent getErrorPendingIntent(final int n, final Context context, final int n2) {
        return zzc.zzapd().getErrorResolutionPendingIntent(context, n, n2);
    }
    
    @Deprecated
    public static String getErrorString(final int n) {
        return ConnectionResult.getStatusString(n);
    }
    
    @Deprecated
    public static String getOpenSourceSoftwareLicenseInfo(final Context context) {
        final Uri build = new Uri$Builder().scheme("android.resource").authority("com.google.android.gms").appendPath("raw").appendPath("oss_notice").build();
        String next = null;
        try {
            final InputStream openInputStream = context.getContentResolver().openInputStream(build);
            try {
                next = new Scanner(openInputStream).useDelimiter("\\A").next();
            }
            catch (NoSuchElementException ex) {}
            finally {
                if (openInputStream != null) {
                    openInputStream.close();
                }
            }
        }
        catch (Exception ex2) {
            next = null;
        }
        return next;
        next = null;
        return next;
    }
    
    public static Context getRemoteContext(final Context context) {
        try {
            return context.createPackageContext("com.google.android.gms", 3);
        }
        catch (PackageManager$NameNotFoundException ex) {
            return null;
        }
    }
    
    public static Resources getRemoteResource(final Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        }
        catch (PackageManager$NameNotFoundException ex) {
            return null;
        }
    }
    
    @Deprecated
    public static int isGooglePlayServicesAvailable(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: bipush          9
        //     2: istore_1       
        //     3: aload_0        
        //     4: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //     7: astore_2       
        //     8: aload_0        
        //     9: invokevirtual   android/content/Context.getResources:()Landroid/content/res/Resources;
        //    12: getstatic       com/google/android/gms/R$string.common_google_play_services_unknown_issue:I
        //    15: invokevirtual   android/content/res/Resources.getString:(I)Ljava/lang/String;
        //    18: pop            
        //    19: ldc             "com.google.android.gms"
        //    21: aload_0        
        //    22: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    25: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    28: ifne            35
        //    31: aload_0        
        //    32: invokestatic    com/google/android/gms/common/zze.zzbt:(Landroid/content/Context;)V
        //    35: aload_0        
        //    36: invokestatic    com/google/android/gms/common/util/zzi.zzcl:(Landroid/content/Context;)Z
        //    39: ifne            128
        //    42: iconst_1       
        //    43: istore          5
        //    45: aconst_null    
        //    46: astore          6
        //    48: iload           5
        //    50: ifeq            68
        //    53: aload_2        
        //    54: ldc             "com.android.vending"
        //    56: sipush          8256
        //    59: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //    62: astore          25
        //    64: aload           25
        //    66: astore          6
        //    68: aload_2        
        //    69: ldc             "com.google.android.gms"
        //    71: bipush          64
        //    73: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //    76: astore          9
        //    78: aload_0        
        //    79: invokestatic    com/google/android/gms/common/zzf.zzbz:(Landroid/content/Context;)Lcom/google/android/gms/common/zzf;
        //    82: astore          10
        //    84: iload           5
        //    86: ifeq            192
        //    89: aload           10
        //    91: aload           6
        //    93: getstatic       com/google/android/gms/common/zzd$zzd.uW:[Lcom/google/android/gms/common/zzd$zza;
        //    96: invokevirtual   com/google/android/gms/common/zzf.zza:(Landroid/content/pm/PackageInfo;[Lcom/google/android/gms/common/zzd$zza;)Lcom/google/android/gms/common/zzd$zza;
        //    99: astore          20
        //   101: aload           20
        //   103: ifnonnull       162
        //   106: ldc             "GooglePlayServicesUtil"
        //   108: ldc             "Google Play Store signature invalid."
        //   110: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   113: pop            
        //   114: iload_1        
        //   115: ireturn        
        //   116: astore_3       
        //   117: ldc             "GooglePlayServicesUtil"
        //   119: ldc             "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included."
        //   121: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   124: pop            
        //   125: goto            19
        //   128: iconst_0       
        //   129: istore          5
        //   131: goto            45
        //   134: astore          23
        //   136: ldc             "GooglePlayServicesUtil"
        //   138: ldc             "Google Play Store is missing."
        //   140: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   143: pop            
        //   144: goto            114
        //   147: astore          7
        //   149: ldc             "GooglePlayServicesUtil"
        //   151: ldc             "Google Play services is missing."
        //   153: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   156: pop            
        //   157: iconst_1       
        //   158: istore_1       
        //   159: goto            114
        //   162: aload           10
        //   164: aload           9
        //   166: iconst_1       
        //   167: anewarray       Lcom/google/android/gms/common/zzd$zza;
        //   170: dup            
        //   171: iconst_0       
        //   172: aload           20
        //   174: aastore        
        //   175: invokevirtual   com/google/android/gms/common/zzf.zza:(Landroid/content/pm/PackageInfo;[Lcom/google/android/gms/common/zzd$zza;)Lcom/google/android/gms/common/zzd$zza;
        //   178: ifnonnull       216
        //   181: ldc             "GooglePlayServicesUtil"
        //   183: ldc             "Google Play services signature invalid."
        //   185: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   188: pop            
        //   189: goto            114
        //   192: aload           10
        //   194: aload           9
        //   196: getstatic       com/google/android/gms/common/zzd$zzd.uW:[Lcom/google/android/gms/common/zzd$zza;
        //   199: invokevirtual   com/google/android/gms/common/zzf.zza:(Landroid/content/pm/PackageInfo;[Lcom/google/android/gms/common/zzd$zza;)Lcom/google/android/gms/common/zzd$zza;
        //   202: ifnonnull       216
        //   205: ldc             "GooglePlayServicesUtil"
        //   207: ldc             "Google Play services signature invalid."
        //   209: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   212: pop            
        //   213: goto            114
        //   216: getstatic       com/google/android/gms/common/zze.GOOGLE_PLAY_SERVICES_VERSION_CODE:I
        //   219: invokestatic    com/google/android/gms/common/util/zzl.zzhj:(I)I
        //   222: istore          11
        //   224: aload           9
        //   226: getfield        android/content/pm/PackageInfo.versionCode:I
        //   229: invokestatic    com/google/android/gms/common/util/zzl.zzhj:(I)I
        //   232: iload           11
        //   234: if_icmpge       293
        //   237: getstatic       com/google/android/gms/common/zze.GOOGLE_PLAY_SERVICES_VERSION_CODE:I
        //   240: istore          16
        //   242: aload           9
        //   244: getfield        android/content/pm/PackageInfo.versionCode:I
        //   247: istore          17
        //   249: ldc             "GooglePlayServicesUtil"
        //   251: new             Ljava/lang/StringBuilder;
        //   254: dup            
        //   255: bipush          77
        //   257: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   260: ldc             "Google Play services out of date.  Requires "
        //   262: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   265: iload           16
        //   267: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   270: ldc_w           " but found "
        //   273: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   276: iload           17
        //   278: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   281: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   284: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   287: pop            
        //   288: iconst_2       
        //   289: istore_1       
        //   290: goto            114
        //   293: aload           9
        //   295: getfield        android/content/pm/PackageInfo.applicationInfo:Landroid/content/pm/ApplicationInfo;
        //   298: astore          12
        //   300: aload           12
        //   302: ifnonnull       318
        //   305: aload_2        
        //   306: ldc             "com.google.android.gms"
        //   308: iconst_0       
        //   309: invokevirtual   android/content/pm/PackageManager.getApplicationInfo:(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
        //   312: astore          15
        //   314: aload           15
        //   316: astore          12
        //   318: aload           12
        //   320: getfield        android/content/pm/ApplicationInfo.enabled:Z
        //   323: ifne            349
        //   326: iconst_3       
        //   327: istore_1       
        //   328: goto            114
        //   331: astore          13
        //   333: ldc             "GooglePlayServicesUtil"
        //   335: ldc_w           "Google Play services missing when getting application info."
        //   338: aload           13
        //   340: invokestatic    android/util/Log.wtf:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   343: pop            
        //   344: iconst_1       
        //   345: istore_1       
        //   346: goto            114
        //   349: iconst_0       
        //   350: istore_1       
        //   351: goto            114
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  8      19     116    128    Ljava/lang/Throwable;
        //  53     64     134    147    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  68     78     147    162    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  305    314    331    349    Landroid/content/pm/PackageManager$NameNotFoundException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 166, Size: 166
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
    
    @Deprecated
    public static boolean isUserRecoverableError(final int n) {
        boolean b = false;
        switch (n) {
            default: {
                b = false;
                break;
            }
            case 1:
            case 2:
            case 3:
            case 9: {
                b = true;
                break;
            }
        }
        return b;
    }
    
    private static int zzapk() {
        return zzf.BA;
    }
    
    @Deprecated
    public static boolean zzapl() {
        return "user".equals(Build.TYPE);
    }
    
    @Deprecated
    @TargetApi(19)
    public static boolean zzb(final Context context, final int n, final String s) {
        return zzy.zzb(context, n, s);
    }
    
    @Deprecated
    public static void zzbc(final Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        final int googlePlayServicesAvailable = zzc.zzapd().isGooglePlayServicesAvailable(context);
        if (googlePlayServicesAvailable == 0) {
            return;
        }
        final Intent zza = zzc.zzapd().zza(context, googlePlayServicesAvailable, "e");
        Log.e("GooglePlayServicesUtil", new StringBuilder(57).append("GooglePlayServices not available due to error ").append(googlePlayServicesAvailable).toString());
        if (zza == null) {
            throw new GooglePlayServicesNotAvailableException(googlePlayServicesAvailable);
        }
        throw new GooglePlayServicesRepairableException(googlePlayServicesAvailable, "Google Play Services not available", zza);
    }
    
    @Deprecated
    public static int zzbo(final Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 0;
        }
    }
    
    @Deprecated
    public static void zzbq(final Context context) {
        if (!zze.vd.getAndSet(true)) {
            try {
                final NotificationManager notificationManager = (NotificationManager)context.getSystemService("notification");
                if (notificationManager != null) {
                    notificationManager.cancel(10436);
                }
            }
            catch (SecurityException ex) {}
        }
    }
    
    private static void zzbt(final Context context) {
        if (!zze.ve.get()) {
            zzbx(context);
            if (zze.vb == 0) {
                throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
            }
            if (zze.vb != zze.GOOGLE_PLAY_SERVICES_VERSION_CODE) {
                final int google_PLAY_SERVICES_VERSION_CODE = zze.GOOGLE_PLAY_SERVICES_VERSION_CODE;
                final int vb = zze.vb;
                final String value = String.valueOf("com.google.android.gms.version");
                throw new IllegalStateException(new StringBuilder(290 + String.valueOf(value).length()).append("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected ").append(google_PLAY_SERVICES_VERSION_CODE).append(" but found ").append(vb).append(".  You must have the following declaration within the <application> element:     <meta-data android:name=\"").append(value).append("\" android:value=\"@integer/google_play_services_version\" />").toString());
            }
        }
    }
    
    public static boolean zzbu(final Context context) {
        zzbx(context);
        return zze.uZ;
    }
    
    public static boolean zzbv(final Context context) {
        return zzbu(context) || !zzapl();
    }
    
    @TargetApi(18)
    public static boolean zzbw(final Context context) {
        if (!zzs.zzaxq()) {
            return false;
        }
        final Bundle applicationRestrictions = ((UserManager)context.getSystemService("user")).getApplicationRestrictions(context.getPackageName());
        if (applicationRestrictions == null || !"true".equals(applicationRestrictions.getString("restricted_profile"))) {
            return false;
        }
        return true;
        b = false;
        return b;
    }
    
    private static void zzbx(final Context context) {
        if (!zze.vc) {
            zzby(context);
        }
    }
    
    private static void zzby(final Context context) {
        try {
            zze.va = context.getPackageName();
            final zzsh zzcr = zzsi.zzcr(context);
            zze.vb = zzaa.zzch(context);
            final PackageInfo packageInfo = zzcr.getPackageInfo("com.google.android.gms", 64);
            if (packageInfo != null && com.google.android.gms.common.zzf.zzbz(context).zza(packageInfo, zzd.zzd.uW[1]) != null) {
                zze.uZ = true;
            }
            else {
                zze.uZ = false;
            }
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.w("GooglePlayServicesUtil", "Cannot find Google Play services package name.", (Throwable)ex);
            zze.vc = true;
        }
        finally {
            zze.vc = true;
        }
    }
    
    @Deprecated
    public static boolean zzd(final Context context, final int n) {
        boolean b = true;
        if (n != 18) {
            b = (n == (b ? 1 : 0) && zzs(context, "com.google.android.gms"));
        }
        return b;
    }
    
    @Deprecated
    public static boolean zze(final Context context, final int n) {
        return n == 9 && zzs(context, "com.android.vending");
    }
    
    @Deprecated
    public static boolean zzf(final Context context, final int n) {
        return zzy.zzf(context, n);
    }
    
    @Deprecated
    public static Intent zzfm(final int n) {
        return zzc.zzapd().zza(null, n, null);
    }
    
    static boolean zzfn(final int n) {
        boolean b = false;
        switch (n) {
            default: {
                b = false;
                break;
            }
            case 1:
            case 2:
            case 3:
            case 18:
            case 42: {
                b = true;
                break;
            }
        }
        return b;
    }
    
    @TargetApi(21)
    static boolean zzs(final Context context, final String s) {
        boolean enabled = false;
        final boolean equals = s.equals("com.google.android.gms");
        Label_0027: {
            if (!equals) {
                break Label_0027;
            }
            final boolean zzact = com.google.android.gms.common.util.zzd.zzact();
            enabled = false;
            if (!zzact) {
                break Label_0027;
            }
            return enabled;
        }
        if (zzs.zzaxu()) {
            final Iterator<PackageInstaller$SessionInfo> iterator = (Iterator<PackageInstaller$SessionInfo>)context.getPackageManager().getPackageInstaller().getAllSessions().iterator();
            while (iterator.hasNext()) {
                if (s.equals(iterator.next().getAppPackageName())) {
                    enabled = true;
                    return enabled;
                }
            }
        }
        final PackageManager packageManager = context.getPackageManager();
        try {
            final ApplicationInfo applicationInfo = packageManager.getApplicationInfo(s, 8192);
            enabled = false;
            if (equals) {
                enabled = applicationInfo.enabled;
                return enabled;
            }
            final boolean enabled2 = applicationInfo.enabled;
            enabled = false;
            enabled = (enabled2 && !zzbw(context));
            return enabled;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return enabled;
        }
    }
}
