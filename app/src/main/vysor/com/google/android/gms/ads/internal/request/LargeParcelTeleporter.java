// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.request;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import android.os.Parcelable;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable$Creator;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zziy
public final class LargeParcelTeleporter extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<LargeParcelTeleporter> CREATOR;
    final int mVersionCode;
    ParcelFileDescriptor zzcie;
    private Parcelable zzcif;
    private boolean zzcig;
    
    static {
        CREATOR = (Parcelable$Creator)new zzm();
    }
    
    LargeParcelTeleporter(final int mVersionCode, final ParcelFileDescriptor zzcie) {
        this.mVersionCode = mVersionCode;
        this.zzcie = zzcie;
        this.zzcif = null;
        this.zzcig = true;
    }
    
    public LargeParcelTeleporter(final SafeParcelable zzcif) {
        this.mVersionCode = 1;
        this.zzcie = null;
        this.zzcif = (Parcelable)zzcif;
        this.zzcig = false;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        Label_0042: {
            if (this.zzcie != null) {
                break Label_0042;
            }
            final Parcel obtain = Parcel.obtain();
            try {
                this.zzcif.writeToParcel(obtain, 0);
                final byte[] marshall = obtain.marshall();
                obtain.recycle();
                this.zzcie = this.zzi(marshall);
                zzm.zza(this, parcel, n);
            }
            finally {
                obtain.recycle();
            }
        }
    }
    
    public <T extends SafeParcelable> T zza(final Parcelable$Creator<T> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/ads/internal/request/LargeParcelTeleporter.zzcig:Z
        //     4: ifeq            111
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/internal/request/LargeParcelTeleporter.zzcie:Landroid/os/ParcelFileDescriptor;
        //    11: ifnonnull       23
        //    14: ldc             "File descriptor is empty, returning null."
        //    16: invokestatic    com/google/android/gms/internal/zzkn.e:(Ljava/lang/String;)V
        //    19: aconst_null    
        //    20: astore_2       
        //    21: aload_2        
        //    22: areturn        
        //    23: new             Ljava/io/DataInputStream;
        //    26: dup            
        //    27: new             Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
        //    30: dup            
        //    31: aload_0        
        //    32: getfield        com/google/android/gms/ads/internal/request/LargeParcelTeleporter.zzcie:Landroid/os/ParcelFileDescriptor;
        //    35: invokespecial   android/os/ParcelFileDescriptor$AutoCloseInputStream.<init>:(Landroid/os/ParcelFileDescriptor;)V
        //    38: invokespecial   java/io/DataInputStream.<init>:(Ljava/io/InputStream;)V
        //    41: astore_3       
        //    42: aload_3        
        //    43: invokevirtual   java/io/DataInputStream.readInt:()I
        //    46: newarray        B
        //    48: astore          6
        //    50: aload_3        
        //    51: aload           6
        //    53: iconst_0       
        //    54: aload           6
        //    56: arraylength    
        //    57: invokevirtual   java/io/DataInputStream.readFully:([BII)V
        //    60: aload_3        
        //    61: invokestatic    com/google/android/gms/common/util/zzo.zzb:(Ljava/io/Closeable;)V
        //    64: invokestatic    android/os/Parcel.obtain:()Landroid/os/Parcel;
        //    67: astore          7
        //    69: aload           7
        //    71: aload           6
        //    73: iconst_0       
        //    74: aload           6
        //    76: arraylength    
        //    77: invokevirtual   android/os/Parcel.unmarshall:([BII)V
        //    80: aload           7
        //    82: iconst_0       
        //    83: invokevirtual   android/os/Parcel.setDataPosition:(I)V
        //    86: aload_0        
        //    87: aload_1        
        //    88: aload           7
        //    90: invokeinterface android/os/Parcelable$Creator.createFromParcel:(Landroid/os/Parcel;)Ljava/lang/Object;
        //    95: checkcast       Lcom/google/android/gms/common/internal/safeparcel/SafeParcelable;
        //    98: putfield        com/google/android/gms/ads/internal/request/LargeParcelTeleporter.zzcif:Landroid/os/Parcelable;
        //   101: aload           7
        //   103: invokevirtual   android/os/Parcel.recycle:()V
        //   106: aload_0        
        //   107: iconst_0       
        //   108: putfield        com/google/android/gms/ads/internal/request/LargeParcelTeleporter.zzcig:Z
        //   111: aload_0        
        //   112: getfield        com/google/android/gms/ads/internal/request/LargeParcelTeleporter.zzcif:Landroid/os/Parcelable;
        //   115: checkcast       Lcom/google/android/gms/common/internal/safeparcel/SafeParcelable;
        //   118: astore_2       
        //   119: goto            21
        //   122: astore          5
        //   124: new             Ljava/lang/IllegalStateException;
        //   127: dup            
        //   128: ldc             "Could not read from parcel file descriptor"
        //   130: aload           5
        //   132: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   135: athrow         
        //   136: astore          4
        //   138: aload_3        
        //   139: invokestatic    com/google/android/gms/common/util/zzo.zzb:(Ljava/io/Closeable;)V
        //   142: aload           4
        //   144: athrow         
        //   145: astore          8
        //   147: aload           7
        //   149: invokevirtual   android/os/Parcel.recycle:()V
        //   152: aload           8
        //   154: athrow         
        //    Signature:
        //  <T::Lcom/google/android/gms/common/internal/safeparcel/SafeParcelable;>(Landroid/os/Parcelable$Creator<TT;>;)TT;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  42     60     122    136    Ljava/io/IOException;
        //  42     60     136    145    Any
        //  69     101    145    155    Any
        //  124    136    136    145    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0111:
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
    
    protected <T> ParcelFileDescriptor zzi(final byte[] p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_2       
        //     2: invokestatic    android/os/ParcelFileDescriptor.createPipe:()[Landroid/os/ParcelFileDescriptor;
        //     5: astore          5
        //     7: new             Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;
        //    10: dup            
        //    11: aload           5
        //    13: iconst_1       
        //    14: aaload         
        //    15: invokespecial   android/os/ParcelFileDescriptor$AutoCloseOutputStream.<init>:(Landroid/os/ParcelFileDescriptor;)V
        //    18: astore          4
        //    20: new             Ljava/lang/Thread;
        //    23: dup            
        //    24: new             Lcom/google/android/gms/ads/internal/request/LargeParcelTeleporter$1;
        //    27: dup            
        //    28: aload_0        
        //    29: aload           4
        //    31: aload_1        
        //    32: invokespecial   com/google/android/gms/ads/internal/request/LargeParcelTeleporter$1.<init>:(Lcom/google/android/gms/ads/internal/request/LargeParcelTeleporter;Ljava/io/OutputStream;[B)V
        //    35: invokespecial   java/lang/Thread.<init>:(Ljava/lang/Runnable;)V
        //    38: invokevirtual   java/lang/Thread.start:()V
        //    41: aload           5
        //    43: iconst_0       
        //    44: aaload         
        //    45: astore_2       
        //    46: aload_2        
        //    47: areturn        
        //    48: astore_3       
        //    49: aconst_null    
        //    50: astore          4
        //    52: ldc             "Error transporting the ad response"
        //    54: aload_3        
        //    55: invokestatic    com/google/android/gms/internal/zzkn.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    58: invokestatic    com/google/android/gms/ads/internal/zzu.zzgd:()Lcom/google/android/gms/internal/zzkh;
        //    61: aload_3        
        //    62: ldc             "LargeParcelTeleporter.pipeData.2"
        //    64: invokevirtual   com/google/android/gms/internal/zzkh.zza:(Ljava/lang/Throwable;Ljava/lang/String;)V
        //    67: aload           4
        //    69: invokestatic    com/google/android/gms/common/util/zzo.zzb:(Ljava/io/Closeable;)V
        //    72: goto            46
        //    75: astore_3       
        //    76: goto            52
        //    Signature:
        //  <T:Ljava/lang/Object;>([B)Landroid/os/ParcelFileDescriptor;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      20     48     52     Ljava/io/IOException;
        //  20     46     75     79     Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0046:
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
