// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.net.Uri;
import android.graphics.drawable.Drawable;
import com.google.android.gms.ads.formats.NativeAd;

@zziy
public class zzdy extends Image
{
    private final Drawable mDrawable;
    private final Uri mUri;
    private final double zzbjp;
    private final zzdx zzblp;
    
    public zzdy(final zzdx p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokespecial   com/google/android/gms/ads/formats/NativeAd$Image.<init>:()V
        //     4: aload_0        
        //     5: aload_1        
        //     6: putfield        com/google/android/gms/internal/zzdy.zzblp:Lcom/google/android/gms/internal/zzdx;
        //     9: aload_0        
        //    10: getfield        com/google/android/gms/internal/zzdy.zzblp:Lcom/google/android/gms/internal/zzdx;
        //    13: invokeinterface com/google/android/gms/internal/zzdx.zzln:()Lcom/google/android/gms/dynamic/zzd;
        //    18: astore          12
        //    20: aload           12
        //    22: ifnull          92
        //    25: aload           12
        //    27: invokestatic    com/google/android/gms/dynamic/zze.zzae:(Lcom/google/android/gms/dynamic/zzd;)Ljava/lang/Object;
        //    30: checkcast       Landroid/graphics/drawable/Drawable;
        //    33: astore_3       
        //    34: aload_0        
        //    35: aload_3        
        //    36: putfield        com/google/android/gms/internal/zzdy.mDrawable:Landroid/graphics/drawable/Drawable;
        //    39: aload_0        
        //    40: getfield        com/google/android/gms/internal/zzdy.zzblp:Lcom/google/android/gms/internal/zzdx;
        //    43: invokeinterface com/google/android/gms/internal/zzdx.getUri:()Landroid/net/Uri;
        //    48: astore          11
        //    50: aload           11
        //    52: astore          5
        //    54: aload_0        
        //    55: aload           5
        //    57: putfield        com/google/android/gms/internal/zzdy.mUri:Landroid/net/Uri;
        //    60: dconst_1       
        //    61: dstore          6
        //    63: aload_0        
        //    64: getfield        com/google/android/gms/internal/zzdy.zzblp:Lcom/google/android/gms/internal/zzdx;
        //    67: invokeinterface com/google/android/gms/internal/zzdx.getScale:()D
        //    72: dstore          9
        //    74: dload           9
        //    76: dstore          6
        //    78: aload_0        
        //    79: dload           6
        //    81: putfield        com/google/android/gms/internal/zzdy.zzbjp:D
        //    84: return         
        //    85: astore_2       
        //    86: ldc             "Failed to get drawable."
        //    88: aload_2        
        //    89: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    92: aconst_null    
        //    93: astore_3       
        //    94: goto            34
        //    97: astore          4
        //    99: ldc             "Failed to get uri."
        //   101: aload           4
        //   103: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   106: aconst_null    
        //   107: astore          5
        //   109: goto            54
        //   112: astore          8
        //   114: ldc             "Failed to get scale."
        //   116: aload           8
        //   118: invokestatic    com/google/android/gms/ads/internal/util/client/zzb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   121: goto            78
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  9      34     85     92     Landroid/os/RemoteException;
        //  39     50     97     112    Landroid/os/RemoteException;
        //  63     74     112    124    Landroid/os/RemoteException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 58, Size: 58
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:635)
        //     at java.util.ArrayList.get(ArrayList.java:411)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
    public Drawable getDrawable() {
        return this.mDrawable;
    }
    
    @Override
    public double getScale() {
        return this.zzbjp;
    }
    
    @Override
    public Uri getUri() {
        return this.mUri;
    }
}
