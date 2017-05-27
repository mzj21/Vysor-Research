// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.util.zzf;
import com.google.android.gms.ads.internal.zzu;
import java.util.HashMap;
import android.support.annotation.Nullable;
import java.util.Map;
import com.google.android.gms.ads.internal.client.zzac;
import com.google.android.gms.ads.internal.client.zzab;

@zziy
public class zzly extends zzab.zza
{
    private final Object zzakd;
    private boolean zzakg;
    private final zzlt zzbkr;
    private final float zzcwo;
    private int zzcwp;
    private zzac zzcwq;
    private boolean zzcwr;
    private boolean zzcws;
    private float zzcwt;
    private float zzcwu;
    
    public zzly(final zzlt zzbkr, final float zzcwo) {
        this.zzakd = new Object();
        this.zzakg = true;
        this.zzbkr = zzbkr;
        this.zzcwo = zzcwo;
    }
    
    private void zzdl(final String s) {
        this.zze(s, null);
    }
    
    private void zze(final String s, @Nullable final Map<String, String> map) {
        HashMap<String, String> hashMap;
        if (map == null) {
            hashMap = new HashMap<String, String>();
        }
        else {
            hashMap = new HashMap<String, String>(map);
        }
        hashMap.put("action", s);
        zzu.zzfz().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                zzly.this.zzbkr.zza("pubVideoCmd", hashMap);
            }
        });
    }
    
    private void zzi(final int n, final int n2) {
        zzu.zzfz().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/google/android/gms/internal/zzly$2.zzcww:Lcom/google/android/gms/internal/zzly;
                //     4: invokestatic    com/google/android/gms/internal/zzly.zzc:(Lcom/google/android/gms/internal/zzly;)Ljava/lang/Object;
                //     7: astore_1       
                //     8: aload_1        
                //     9: monitorenter   
                //    10: aload_0        
                //    11: getfield        com/google/android/gms/internal/zzly$2.zzcwx:I
                //    14: aload_0        
                //    15: getfield        com/google/android/gms/internal/zzly$2.zzcwy:I
                //    18: if_icmpeq       137
                //    21: iconst_1       
                //    22: istore_3       
                //    23: aload_0        
                //    24: getfield        com/google/android/gms/internal/zzly$2.zzcww:Lcom/google/android/gms/internal/zzly;
                //    27: invokestatic    com/google/android/gms/internal/zzly.zzd:(Lcom/google/android/gms/internal/zzly;)Z
                //    30: ifne            142
                //    33: aload_0        
                //    34: getfield        com/google/android/gms/internal/zzly$2.zzcwy:I
                //    37: iconst_1       
                //    38: if_icmpne       142
                //    41: iconst_1       
                //    42: istore          4
                //    44: iload_3        
                //    45: ifeq            148
                //    48: aload_0        
                //    49: getfield        com/google/android/gms/internal/zzly$2.zzcwy:I
                //    52: iconst_1       
                //    53: if_icmpne       148
                //    56: iconst_1       
                //    57: istore          5
                //    59: iload_3        
                //    60: ifeq            154
                //    63: aload_0        
                //    64: getfield        com/google/android/gms/internal/zzly$2.zzcwy:I
                //    67: iconst_2       
                //    68: if_icmpne       154
                //    71: iconst_1       
                //    72: istore          6
                //    74: iload_3        
                //    75: ifeq            160
                //    78: aload_0        
                //    79: getfield        com/google/android/gms/internal/zzly$2.zzcwy:I
                //    82: iconst_3       
                //    83: if_icmpne       160
                //    86: iconst_1       
                //    87: istore          7
                //    89: aload_0        
                //    90: getfield        com/google/android/gms/internal/zzly$2.zzcww:Lcom/google/android/gms/internal/zzly;
                //    93: astore          8
                //    95: aload_0        
                //    96: getfield        com/google/android/gms/internal/zzly$2.zzcww:Lcom/google/android/gms/internal/zzly;
                //    99: invokestatic    com/google/android/gms/internal/zzly.zzd:(Lcom/google/android/gms/internal/zzly;)Z
                //   102: ifne            292
                //   105: iconst_0       
                //   106: istore          9
                //   108: iload           4
                //   110: ifeq            116
                //   113: goto            292
                //   116: aload           8
                //   118: iload           9
                //   120: invokestatic    com/google/android/gms/internal/zzly.zza:(Lcom/google/android/gms/internal/zzly;Z)Z
                //   123: pop            
                //   124: aload_0        
                //   125: getfield        com/google/android/gms/internal/zzly$2.zzcww:Lcom/google/android/gms/internal/zzly;
                //   128: invokestatic    com/google/android/gms/internal/zzly.zze:(Lcom/google/android/gms/internal/zzly;)Lcom/google/android/gms/ads/internal/client/zzac;
                //   131: ifnonnull       166
                //   134: aload_1        
                //   135: monitorexit    
                //   136: return         
                //   137: iconst_0       
                //   138: istore_3       
                //   139: goto            23
                //   142: iconst_0       
                //   143: istore          4
                //   145: goto            44
                //   148: iconst_0       
                //   149: istore          5
                //   151: goto            59
                //   154: iconst_0       
                //   155: istore          6
                //   157: goto            74
                //   160: iconst_0       
                //   161: istore          7
                //   163: goto            89
                //   166: iload           4
                //   168: ifeq            183
                //   171: aload_0        
                //   172: getfield        com/google/android/gms/internal/zzly$2.zzcww:Lcom/google/android/gms/internal/zzly;
                //   175: invokestatic    com/google/android/gms/internal/zzly.zze:(Lcom/google/android/gms/internal/zzly;)Lcom/google/android/gms/ads/internal/client/zzac;
                //   178: invokeinterface com/google/android/gms/ads/internal/client/zzac.zzjw:()V
                //   183: iload           5
                //   185: ifeq            200
                //   188: aload_0        
                //   189: getfield        com/google/android/gms/internal/zzly$2.zzcww:Lcom/google/android/gms/internal/zzly;
                //   192: invokestatic    com/google/android/gms/internal/zzly.zze:(Lcom/google/android/gms/internal/zzly;)Lcom/google/android/gms/ads/internal/client/zzac;
                //   195: invokeinterface com/google/android/gms/ads/internal/client/zzac.zzjx:()V
                //   200: iload           6
                //   202: ifeq            217
                //   205: aload_0        
                //   206: getfield        com/google/android/gms/internal/zzly$2.zzcww:Lcom/google/android/gms/internal/zzly;
                //   209: invokestatic    com/google/android/gms/internal/zzly.zze:(Lcom/google/android/gms/internal/zzly;)Lcom/google/android/gms/ads/internal/client/zzac;
                //   212: invokeinterface com/google/android/gms/ads/internal/client/zzac.zzjy:()V
                //   217: iload           7
                //   219: ifeq            234
                //   222: aload_0        
                //   223: getfield        com/google/android/gms/internal/zzly$2.zzcww:Lcom/google/android/gms/internal/zzly;
                //   226: invokestatic    com/google/android/gms/internal/zzly.zze:(Lcom/google/android/gms/internal/zzly;)Lcom/google/android/gms/ads/internal/client/zzac;
                //   229: invokeinterface com/google/android/gms/ads/internal/client/zzac.onVideoEnd:()V
                //   234: aload_1        
                //   235: monitorexit    
                //   236: goto            136
                //   239: astore_2       
                //   240: aload_1        
                //   241: monitorexit    
                //   242: aload_2        
                //   243: athrow         
                //   244: astore          14
                //   246: ldc             "Unable to call onVideoStart()"
                //   248: aload           14
                //   250: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
                //   253: goto            183
                //   256: astore          13
                //   258: ldc             "Unable to call onVideoPlay()"
                //   260: aload           13
                //   262: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
                //   265: goto            200
                //   268: astore          12
                //   270: ldc             "Unable to call onVideoPause()"
                //   272: aload           12
                //   274: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
                //   277: goto            217
                //   280: astore          11
                //   282: ldc             "Unable to call onVideoEnd()"
                //   284: aload           11
                //   286: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
                //   289: goto            234
                //   292: iconst_1       
                //   293: istore          9
                //   295: goto            116
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                        
                //  -----  -----  -----  -----  ----------------------------
                //  10     136    239    244    Any
                //  171    183    244    256    Landroid/os/RemoteException;
                //  171    183    239    244    Any
                //  188    200    256    268    Landroid/os/RemoteException;
                //  188    200    239    244    Any
                //  205    217    268    280    Landroid/os/RemoteException;
                //  205    217    239    244    Any
                //  222    234    280    292    Landroid/os/RemoteException;
                //  222    234    239    244    Any
                //  234    242    239    244    Any
                //  246    289    239    244    Any
                // 
                // The error that occurred was:
                // 
                // java.lang.IndexOutOfBoundsException: Index: 140, Size: 140
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
        });
    }
    
    public float getAspectRatio() {
        synchronized (this.zzakd) {
            return this.zzcwu;
        }
    }
    
    public int getPlaybackState() {
        synchronized (this.zzakd) {
            return this.zzcwp;
        }
    }
    
    public boolean isMuted() {
        synchronized (this.zzakd) {
            return this.zzcws;
        }
    }
    
    public void pause() {
        this.zzdl("pause");
    }
    
    public void play() {
        this.zzdl("play");
    }
    
    public void zza(final float zzcwt, final int zzcwp, final boolean zzcws, final float zzcwu) {
        synchronized (this.zzakd) {
            this.zzcwt = zzcwt;
            this.zzcws = zzcws;
            final int zzcwp2 = this.zzcwp;
            this.zzcwp = zzcwp;
            this.zzcwu = zzcwu;
            // monitorexit(this.zzakd)
            this.zzi(zzcwp2, zzcwp);
        }
    }
    
    public void zza(final zzac zzcwq) {
        synchronized (this.zzakd) {
            this.zzcwq = zzcwq;
        }
    }
    
    public void zzap(final boolean zzakg) {
        while (true) {
            while (true) {
                synchronized (this.zzakd) {
                    this.zzakg = zzakg;
                    // monitorexit(this.zzakd)
                    if (zzakg) {
                        final String s = "1";
                        this.zze("initialState", zzf.zze("muteStart", s));
                        return;
                    }
                }
                final String s = "0";
                continue;
            }
        }
    }
    
    public float zzju() {
        return this.zzcwo;
    }
    
    public float zzjv() {
        synchronized (this.zzakd) {
            return this.zzcwt;
        }
    }
    
    public void zzn(final boolean b) {
        String s;
        if (b) {
            s = "mute";
        }
        else {
            s = "unmute";
        }
        this.zzdl(s);
    }
}
