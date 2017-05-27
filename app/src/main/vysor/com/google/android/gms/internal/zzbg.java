// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public class zzbg extends zzbv
{
    private static final Object zzagd;
    private static volatile zzal zzaip;
    private boolean zzaiq;
    
    static {
        zzbg.zzaip = null;
        zzagd = new Object();
    }
    
    public zzbg(final zzbb zzbb, final String s, final String s2, final zzae.zza zza, final int n, final int n2, final boolean zzaiq) {
        super(zzbb, s, s2, zza, n, n2);
        this.zzaiq = false;
        this.zzaiq = zzaiq;
    }
    
    @Override
    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/google/android/gms/internal/zzbg.zzaip:Lcom/google/android/gms/internal/zzal;
        //     3: ifnonnull       75
        //     6: getstatic       com/google/android/gms/internal/zzbg.zzagd:Ljava/lang/Object;
        //     9: astore_3       
        //    10: aload_3        
        //    11: monitorenter   
        //    12: getstatic       com/google/android/gms/internal/zzbg.zzaip:Lcom/google/android/gms/internal/zzal;
        //    15: ifnonnull       73
        //    18: aload_0        
        //    19: getfield        com/google/android/gms/internal/zzbg.zzaiz:Ljava/lang/reflect/Method;
        //    22: astore          5
        //    24: iconst_2       
        //    25: anewarray       Ljava/lang/Object;
        //    28: astore          6
        //    30: aload           6
        //    32: iconst_0       
        //    33: aload_0        
        //    34: getfield        com/google/android/gms/internal/zzbg.zzafz:Lcom/google/android/gms/internal/zzbb;
        //    37: invokevirtual   com/google/android/gms/internal/zzbb.getContext:()Landroid/content/Context;
        //    40: aastore        
        //    41: aload           6
        //    43: iconst_1       
        //    44: aload_0        
        //    45: getfield        com/google/android/gms/internal/zzbg.zzaiq:Z
        //    48: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    51: aastore        
        //    52: new             Lcom/google/android/gms/internal/zzal;
        //    55: dup            
        //    56: aload           5
        //    58: aconst_null    
        //    59: aload           6
        //    61: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    64: checkcast       Ljava/lang/String;
        //    67: invokespecial   com/google/android/gms/internal/zzal.<init>:(Ljava/lang/String;)V
        //    70: putstatic       com/google/android/gms/internal/zzbg.zzaip:Lcom/google/android/gms/internal/zzal;
        //    73: aload_3        
        //    74: monitorexit    
        //    75: aload_0        
        //    76: getfield        com/google/android/gms/internal/zzbg.zzair:Lcom/google/android/gms/internal/zzae$zza;
        //    79: astore_1       
        //    80: aload_1        
        //    81: monitorenter   
        //    82: getstatic       com/google/android/gms/internal/zzbg.zzaip:Lcom/google/android/gms/internal/zzal;
        //    85: ifnull          163
        //    88: aload_0        
        //    89: getfield        com/google/android/gms/internal/zzbg.zzair:Lcom/google/android/gms/internal/zzae$zza;
        //    92: getstatic       com/google/android/gms/internal/zzbg.zzaip:Lcom/google/android/gms/internal/zzal;
        //    95: getfield        com/google/android/gms/internal/zzal.zzdo:Ljava/lang/String;
        //    98: putfield        com/google/android/gms/internal/zzae$zza.zzdo:Ljava/lang/String;
        //   101: aload_0        
        //   102: getfield        com/google/android/gms/internal/zzbg.zzair:Lcom/google/android/gms/internal/zzae$zza;
        //   105: getstatic       com/google/android/gms/internal/zzbg.zzaip:Lcom/google/android/gms/internal/zzal;
        //   108: getfield        com/google/android/gms/internal/zzal.zzye:J
        //   111: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   114: putfield        com/google/android/gms/internal/zzae$zza.zzdu:Ljava/lang/Long;
        //   117: aload_0        
        //   118: getfield        com/google/android/gms/internal/zzbg.zzair:Lcom/google/android/gms/internal/zzae$zza;
        //   121: getstatic       com/google/android/gms/internal/zzbg.zzaip:Lcom/google/android/gms/internal/zzal;
        //   124: getfield        com/google/android/gms/internal/zzal.zzdt:Ljava/lang/String;
        //   127: putfield        com/google/android/gms/internal/zzae$zza.zzdt:Ljava/lang/String;
        //   130: aload_0        
        //   131: getfield        com/google/android/gms/internal/zzbg.zzaiq:Z
        //   134: ifeq            163
        //   137: aload_0        
        //   138: getfield        com/google/android/gms/internal/zzbg.zzair:Lcom/google/android/gms/internal/zzae$zza;
        //   141: getstatic       com/google/android/gms/internal/zzbg.zzaip:Lcom/google/android/gms/internal/zzal;
        //   144: getfield        com/google/android/gms/internal/zzal.zzee:Ljava/lang/String;
        //   147: putfield        com/google/android/gms/internal/zzae$zza.zzee:Ljava/lang/String;
        //   150: aload_0        
        //   151: getfield        com/google/android/gms/internal/zzbg.zzair:Lcom/google/android/gms/internal/zzae$zza;
        //   154: getstatic       com/google/android/gms/internal/zzbg.zzaip:Lcom/google/android/gms/internal/zzal;
        //   157: getfield        com/google/android/gms/internal/zzal.zzef:Ljava/lang/String;
        //   160: putfield        com/google/android/gms/internal/zzae$zza.zzef:Ljava/lang/String;
        //   163: aload_1        
        //   164: monitorexit    
        //   165: return         
        //   166: astore          4
        //   168: aload_3        
        //   169: monitorexit    
        //   170: aload           4
        //   172: athrow         
        //   173: astore_2       
        //   174: aload_1        
        //   175: monitorexit    
        //   176: aload_2        
        //   177: athrow         
        //    Exceptions:
        //  throws java.lang.IllegalAccessException
        //  throws java.lang.reflect.InvocationTargetException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  12     75     166    173    Any
        //  82     165    173    178    Any
        //  168    170    166    173    Any
        //  174    176    173    178    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0163:
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
}
