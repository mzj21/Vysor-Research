// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import java.lang.reflect.Field;
import com.google.android.gms.dynamic.zzd;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zze;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import android.content.Context;

public final class zzsu
{
    public static final zzb OA;
    public static final zzb OB;
    public static final zzb OC;
    private static zzsv Ow;
    private static final zzb.zza Ox;
    public static final zzb Oy;
    public static final zzb Oz;
    private final Context OD;
    
    static {
        Ox = (zzb.zza)new zzb.zza() {
            @Override
            public int zzaa(final Context context, final String s) {
                return zzsu.zzaa(context, s);
            }
            
            @Override
            public int zzc(final Context context, final String s, final boolean b) {
                return zzsu.zzc(context, s, b);
            }
        };
        Oy = (zzb)new zzb() {
            @Override
            public zzsu.zzb.zzb zza(final Context context, final String s, final zzsu.zzb.zza zza) {
                final zzsu.zzb.zzb zzb = new zzsu.zzb.zzb();
                zzb.OG = zza.zzc(context, s, true);
                if (zzb.OG != 0) {
                    zzb.OH = 1;
                }
                else {
                    zzb.OF = zza.zzaa(context, s);
                    if (zzb.OF != 0) {
                        zzb.OH = -1;
                    }
                }
                return zzb;
            }
        };
        Oz = (zzb)new zzb() {
            @Override
            public zzsu.zzb.zzb zza(final Context context, final String s, final zzsu.zzb.zza zza) {
                final zzsu.zzb.zzb zzb = new zzsu.zzb.zzb();
                zzb.OF = zza.zzaa(context, s);
                if (zzb.OF != 0) {
                    zzb.OH = -1;
                }
                else {
                    zzb.OG = zza.zzc(context, s, true);
                    if (zzb.OG != 0) {
                        zzb.OH = 1;
                    }
                }
                return zzb;
            }
        };
        OA = (zzb)new zzb() {
            @Override
            public zzsu.zzb.zzb zza(final Context context, final String s, final zzsu.zzb.zza zza) {
                final zzsu.zzb.zzb zzb = new zzsu.zzb.zzb();
                zzb.OF = zza.zzaa(context, s);
                zzb.OG = zza.zzc(context, s, true);
                if (zzb.OF == 0 && zzb.OG == 0) {
                    zzb.OH = 0;
                }
                else if (zzb.OF >= zzb.OG) {
                    zzb.OH = -1;
                }
                else {
                    zzb.OH = 1;
                }
                return zzb;
            }
        };
        OB = (zzb)new zzb() {
            @Override
            public zzsu.zzb.zzb zza(final Context context, final String s, final zzsu.zzb.zza zza) {
                final zzsu.zzb.zzb zzb = new zzsu.zzb.zzb();
                zzb.OF = zza.zzaa(context, s);
                zzb.OG = zza.zzc(context, s, true);
                if (zzb.OF == 0 && zzb.OG == 0) {
                    zzb.OH = 0;
                }
                else if (zzb.OG >= zzb.OF) {
                    zzb.OH = 1;
                }
                else {
                    zzb.OH = -1;
                }
                return zzb;
            }
        };
        OC = (zzb)new zzb() {
            @Override
            public zzsu.zzb.zzb zza(final Context context, final String s, final zzsu.zzb.zza zza) {
                final zzsu.zzb.zzb zzb = new zzsu.zzb.zzb();
                zzb.OF = zza.zzaa(context, s);
                if (zzb.OF != 0) {
                    zzb.OG = zza.zzc(context, s, false);
                }
                else {
                    zzb.OG = zza.zzc(context, s, true);
                }
                if (zzb.OF == 0 && zzb.OG == 0) {
                    zzb.OH = 0;
                }
                else if (zzb.OG >= zzb.OF) {
                    zzb.OH = 1;
                }
                else {
                    zzb.OH = -1;
                }
                return zzb;
            }
        };
    }
    
    private zzsu(final Context context) {
        this.OD = zzac.zzy(context);
    }
    
    public static zzsu zza(final Context context, final zzb zzb, final String s) throws zza {
        final zzb.zzb zza = zzb.zza(context, s, zzsu.Ox);
        Log.i("DynamiteModule", new StringBuilder(68 + String.valueOf(s).length() + String.valueOf(s).length()).append("Considering local module ").append(s).append(":").append(zza.OF).append(" and remote module ").append(s).append(":").append(zza.OG).toString());
        if (zza.OH == 0 || (zza.OH == -1 && zza.OF == 0) || (zza.OH == 1 && zza.OG == 0)) {
            throw new zza(new StringBuilder(91).append("No acceptable module found. Local version is ").append(zza.OF).append(" and remote version is ").append(zza.OG).append(".").toString());
        }
        if (zza.OH != -1) {
            if (zza.OH == 1) {
                try {
                    return zza(context, s, zza.OG);
                }
                catch (zza zza2) {
                    final String value = String.valueOf(zza2.getMessage());
                    String concat;
                    if (value.length() != 0) {
                        concat = "Failed to load remote module: ".concat(value);
                    }
                    else {
                        concat = new String("Failed to load remote module: ");
                    }
                    Log.w("DynamiteModule", concat);
                    if (zza.OF != 0 && zzb.zza(context, s, (zzb.zza)new zzb.zza() {
                        final /* synthetic */ int OE = zza.OF;
                        
                        @Override
                        public int zzaa(final Context context, final String s) {
                            return this.OE;
                        }
                        
                        @Override
                        public int zzc(final Context context, final String s, final boolean b) {
                            return 0;
                        }
                    }).OH == -1) {
                        return zzac(context, s);
                    }
                    throw new zza("Remote load failed. No local fallback found.", (Throwable)zza2);
                }
            }
            throw new zza(new StringBuilder(47).append("VersionPolicy returned invalid code:").append(zza.OH).toString());
        }
        return zzac(context, s);
    }
    
    private static zzsu zza(final Context context, final String s, final int n) throws zza {
        Log.i("DynamiteModule", new StringBuilder(51 + String.valueOf(s).length()).append("Selected remote version of ").append(s).append(", version >= ").append(n).toString());
        final zzsv zzcv = zzcv(context);
        if (zzcv == null) {
            throw new zza("Failed to create IDynamiteLoader.");
        }
        zzd zza;
        try {
            zza = zzcv.zza(zze.zzac(context), s, n);
            if (zze.zzae(zza) == null) {
                throw new zza("Failed to load remote module.");
            }
        }
        catch (RemoteException ex) {
            throw new zza("Failed to load remote module.", (Throwable)ex);
        }
        return new zzsu((Context)zze.zzae(zza));
    }
    
    public static int zzaa(final Context context, final String s) {
        int int1 = 0;
        try {
            final ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            final String value = String.valueOf("com.google.android.gms.dynamite.descriptors.");
            final String value2 = String.valueOf("ModuleDescriptor");
            final Class<?> loadClass = classLoader.loadClass(new StringBuilder(1 + String.valueOf(value).length() + String.valueOf(s).length() + String.valueOf(value2).length()).append(value).append(s).append(".").append(value2).toString());
            final Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            final Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (!declaredField.get(null).equals(s)) {
                final String value3 = String.valueOf(declaredField.get(null));
                Log.e("DynamiteModule", new StringBuilder(51 + String.valueOf(value3).length() + String.valueOf(s).length()).append("Module descriptor id '").append(value3).append("' didn't match expected id '").append(s).append("'").toString());
                int1 = 0;
            }
            else {
                int1 = declaredField2.getInt(null);
            }
        }
        catch (ClassNotFoundException ex2) {
            Log.w("DynamiteModule", new StringBuilder(45 + String.valueOf(s).length()).append("Local module descriptor class for ").append(s).append(" not found.").toString());
        }
        catch (Exception ex) {
            final String value4 = String.valueOf(ex.getMessage());
            String concat;
            if (value4.length() != 0) {
                concat = "Failed to load module descriptor class: ".concat(value4);
            }
            else {
                concat = new String("Failed to load module descriptor class: ");
            }
            Log.e("DynamiteModule", concat);
            goto Label_0250;
        }
        return int1;
    }
    
    public static int zzab(final Context context, final String s) {
        return zzc(context, s, false);
    }
    
    private static zzsu zzac(final Context context, final String s) {
        final String value = String.valueOf(s);
        String concat;
        if (value.length() != 0) {
            concat = "Selected local version of ".concat(value);
        }
        else {
            concat = new String("Selected local version of ");
        }
        Log.i("DynamiteModule", concat);
        return new zzsu(context.getApplicationContext());
    }
    
    public static int zzc(final Context context, final String s, final boolean b) {
        final zzsv zzcv = zzcv(context);
        int zza;
        if (zzcv == null) {
            zza = 0;
        }
        else {
            try {
                zza = zzcv.zza(zze.zzac(context), s, b);
            }
            catch (RemoteException ex) {
                final String value = String.valueOf(ex.getMessage());
                String concat;
                if (value.length() != 0) {
                    concat = "Failed to retrieve remote module version: ".concat(value);
                }
                else {
                    concat = new String("Failed to retrieve remote module version: ");
                }
                Log.w("DynamiteModule", concat);
                zza = 0;
            }
        }
        return zza;
    }
    
    private static zzsv zzcv(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             Lcom/google/android/gms/internal/zzsu;.class
        //     2: monitorenter   
        //     3: getstatic       com/google/android/gms/internal/zzsu.Ow:Lcom/google/android/gms/internal/zzsv;
        //     6: ifnull          20
        //     9: getstatic       com/google/android/gms/internal/zzsu.Ow:Lcom/google/android/gms/internal/zzsv;
        //    12: astore          6
        //    14: ldc             Lcom/google/android/gms/internal/zzsu;.class
        //    16: monitorexit    
        //    17: goto            146
        //    20: invokestatic    com/google/android/gms/common/zzc.zzapd:()Lcom/google/android/gms/common/zzc;
        //    23: aload_0        
        //    24: invokevirtual   com/google/android/gms/common/zzc.isGooglePlayServicesAvailable:(Landroid/content/Context;)I
        //    27: ifeq            39
        //    30: ldc             Lcom/google/android/gms/internal/zzsu;.class
        //    32: monitorexit    
        //    33: aconst_null    
        //    34: astore          6
        //    36: goto            146
        //    39: aload_0        
        //    40: ldc_w           "com.google.android.gms"
        //    43: iconst_3       
        //    44: invokevirtual   android/content/Context.createPackageContext:(Ljava/lang/String;I)Landroid/content/Context;
        //    47: invokevirtual   android/content/Context.getClassLoader:()Ljava/lang/ClassLoader;
        //    50: ldc_w           "com.google.android.gms.chimera.container.DynamiteLoaderImpl"
        //    53: invokevirtual   java/lang/ClassLoader.loadClass:(Ljava/lang/String;)Ljava/lang/Class;
        //    56: invokevirtual   java/lang/Class.newInstance:()Ljava/lang/Object;
        //    59: checkcast       Landroid/os/IBinder;
        //    62: invokestatic    com/google/android/gms/internal/zzsv$zza.zzff:(Landroid/os/IBinder;)Lcom/google/android/gms/internal/zzsv;
        //    65: astore          6
        //    67: aload           6
        //    69: ifnull          122
        //    72: aload           6
        //    74: putstatic       com/google/android/gms/internal/zzsu.Ow:Lcom/google/android/gms/internal/zzsv;
        //    77: ldc             Lcom/google/android/gms/internal/zzsu;.class
        //    79: monitorexit    
        //    80: goto            146
        //    83: astore_1       
        //    84: ldc             Lcom/google/android/gms/internal/zzsu;.class
        //    86: monitorexit    
        //    87: aload_1        
        //    88: athrow         
        //    89: astore_2       
        //    90: aload_2        
        //    91: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //    94: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    97: astore_3       
        //    98: aload_3        
        //    99: invokevirtual   java/lang/String.length:()I
        //   102: ifeq            131
        //   105: ldc_w           "Failed to load IDynamiteLoader from GmsCore: "
        //   108: aload_3        
        //   109: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   112: astore          4
        //   114: ldc             "DynamiteModule"
        //   116: aload           4
        //   118: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   121: pop            
        //   122: ldc             Lcom/google/android/gms/internal/zzsu;.class
        //   124: monitorexit    
        //   125: aconst_null    
        //   126: astore          6
        //   128: goto            146
        //   131: new             Ljava/lang/String;
        //   134: dup            
        //   135: ldc_w           "Failed to load IDynamiteLoader from GmsCore: "
        //   138: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   141: astore          4
        //   143: goto            114
        //   146: aload           6
        //   148: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  3      33     83     89     Any
        //  39     77     89     146    Ljava/lang/Exception;
        //  39     77     83     89     Any
        //  77     87     83     89     Any
        //  90     143    83     89     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0039:
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
    
    public Context zzbdy() {
        return this.OD;
    }
    
    public IBinder zzjd(final String s) throws zza {
        try {
            return (IBinder)this.OD.getClassLoader().loadClass(s).newInstance();
        }
        catch (ClassNotFoundException ex) {}
        catch (InstantiationException ex2) {
            goto Label_0023;
        }
        catch (IllegalAccessException ex2) {
            goto Label_0023;
        }
    }
    
    public static class zza extends Exception
    {
        private zza(final String s) {
            super(s);
        }
        
        private zza(final String s, final Throwable t) {
            super(s, t);
        }
    }
    
    public interface zzb
    {
        zzsu.zzb.zzb zza(final Context p0, final String p1, final zza p2);
        
        public interface zza
        {
            int zzaa(final Context p0, final String p1);
            
            int zzc(final Context p0, final String p1, final boolean p2);
        }
        
        public static class zzb
        {
            public int OF;
            public int OG;
            public int OH;
            
            public zzb() {
                this.OF = 0;
                this.OG = 0;
                this.OH = 0;
            }
        }
    }
}
