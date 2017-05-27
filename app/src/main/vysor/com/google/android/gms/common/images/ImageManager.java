// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import java.util.concurrent.CountDownLatch;
import android.content.res.Configuration;
import android.content.ComponentCallbacks2;
import android.os.SystemClock;
import android.support.v4.util.LruCache;
import android.app.ActivityManager;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.content.Intent;
import android.os.ParcelFileDescriptor;
import android.os.Bundle;
import java.util.ArrayList;
import com.google.android.gms.common.annotation.KeepName;
import android.os.ResultReceiver;
import com.google.android.gms.common.internal.zzc;
import android.widget.ImageView;
import android.annotation.TargetApi;
import android.content.ComponentCallbacks;
import android.graphics.Bitmap;
import java.util.HashMap;
import com.google.android.gms.common.util.zzs;
import java.util.concurrent.Executors;
import android.os.Looper;
import android.os.Handler;
import android.content.Context;
import java.util.Map;
import com.google.android.gms.internal.zzrv;
import java.util.concurrent.ExecutorService;
import android.net.Uri;
import java.util.HashSet;

public final class ImageManager
{
    private static final Object Ae;
    private static HashSet<Uri> Af;
    private static ImageManager Ag;
    private static ImageManager Ah;
    private final ExecutorService Ai;
    private final zzb Aj;
    private final zzrv Ak;
    private final Map<com.google.android.gms.common.images.zza, ImageReceiver> Al;
    private final Map<Uri, ImageReceiver> Am;
    private final Map<Uri, Long> An;
    private final Context mContext;
    private final Handler mHandler;
    
    static {
        Ae = new Object();
        ImageManager.Af = new HashSet<Uri>();
    }
    
    private ImageManager(final Context context, final boolean b) {
        this.mContext = context.getApplicationContext();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.Ai = Executors.newFixedThreadPool(4);
        if (b) {
            this.Aj = new zzb(this.mContext);
            if (zzs.zzaxn()) {
                this.zzatk();
            }
        }
        else {
            this.Aj = null;
        }
        this.Ak = new zzrv();
        this.Al = new HashMap<com.google.android.gms.common.images.zza, ImageReceiver>();
        this.Am = new HashMap<Uri, ImageReceiver>();
        this.An = new HashMap<Uri, Long>();
    }
    
    public static ImageManager create(final Context context) {
        return zzg(context, false);
    }
    
    private Bitmap zza(final com.google.android.gms.common.images.zza.zza zza) {
        Bitmap bitmap;
        if (this.Aj == null) {
            bitmap = null;
        }
        else {
            bitmap = this.Aj.get(zza);
        }
        return bitmap;
    }
    
    @TargetApi(14)
    private void zzatk() {
        this.mContext.registerComponentCallbacks((ComponentCallbacks)new zze(this.Aj));
    }
    
    public static ImageManager zzg(final Context context, final boolean b) {
        ImageManager imageManager;
        if (b) {
            if (ImageManager.Ah == null) {
                ImageManager.Ah = new ImageManager(context, true);
            }
            imageManager = ImageManager.Ah;
        }
        else {
            if (ImageManager.Ag == null) {
                ImageManager.Ag = new ImageManager(context, false);
            }
            imageManager = ImageManager.Ag;
        }
        return imageManager;
    }
    
    public void loadImage(final ImageView imageView, final int n) {
        this.zza(new com.google.android.gms.common.images.zza.zzb(imageView, n));
    }
    
    public void loadImage(final ImageView imageView, final Uri uri) {
        this.zza(new com.google.android.gms.common.images.zza.zzb(imageView, uri));
    }
    
    public void loadImage(final ImageView imageView, final Uri uri, final int n) {
        final com.google.android.gms.common.images.zza.zzb zzb = new com.google.android.gms.common.images.zza.zzb(imageView, uri);
        zzb.zzgh(n);
        this.zza(zzb);
    }
    
    public void loadImage(final OnImageLoadedListener onImageLoadedListener, final Uri uri) {
        this.zza(new com.google.android.gms.common.images.zza.zzc(onImageLoadedListener, uri));
    }
    
    public void loadImage(final OnImageLoadedListener onImageLoadedListener, final Uri uri, final int n) {
        final com.google.android.gms.common.images.zza.zzc zzc = new com.google.android.gms.common.images.zza.zzc(onImageLoadedListener, uri);
        zzc.zzgh(n);
        this.zza(zzc);
    }
    
    public void zza(final com.google.android.gms.common.images.zza zza) {
        com.google.android.gms.common.internal.zzc.zzhq("ImageManager.loadImage() must be called in the main thread");
        new zzd(zza).run();
    }
    
    @KeepName
    private final class ImageReceiver extends ResultReceiver
    {
        private final ArrayList<com.google.android.gms.common.images.zza> Ao;
        private final Uri mUri;
        
        ImageReceiver(final Uri mUri) {
            super(new Handler(Looper.getMainLooper()));
            this.mUri = mUri;
            this.Ao = new ArrayList<com.google.android.gms.common.images.zza>();
        }
        
        public void onReceiveResult(final int n, final Bundle bundle) {
            ImageManager.this.Ai.execute(new zzc(this.mUri, (ParcelFileDescriptor)bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }
        
        public void zzatm() {
            final Intent intent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
            intent.putExtra("com.google.android.gms.extras.uri", (Parcelable)this.mUri);
            intent.putExtra("com.google.android.gms.extras.resultReceiver", (Parcelable)this);
            intent.putExtra("com.google.android.gms.extras.priority", 3);
            ImageManager.this.mContext.sendBroadcast(intent);
        }
        
        public void zzb(final com.google.android.gms.common.images.zza zza) {
            com.google.android.gms.common.internal.zzc.zzhq("ImageReceiver.addImageRequest() must be called in the main thread");
            this.Ao.add(zza);
        }
        
        public void zzc(final com.google.android.gms.common.images.zza zza) {
            com.google.android.gms.common.internal.zzc.zzhq("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.Ao.remove(zza);
        }
    }
    
    public interface OnImageLoadedListener
    {
        void onImageLoaded(final Uri p0, final Drawable p1, final boolean p2);
    }
    
    @TargetApi(11)
    private static final class zza
    {
        static int zza(final ActivityManager activityManager) {
            return activityManager.getLargeMemoryClass();
        }
    }
    
    private static final class zzb extends LruCache<com.google.android.gms.common.images.zza.zza, Bitmap>
    {
        public zzb(final Context context) {
            super(zzcc(context));
        }
        
        @TargetApi(11)
        private static int zzcc(final Context context) {
            final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
            boolean b;
            if ((0x100000 & context.getApplicationInfo().flags) != 0x0) {
                b = true;
            }
            else {
                b = false;
            }
            int n;
            if (b && zzs.zzaxk()) {
                n = zza.zza(activityManager);
            }
            else {
                n = activityManager.getMemoryClass();
            }
            return (int)(0.33f * (n * 1048576));
        }
        
        protected int zza(final com.google.android.gms.common.images.zza.zza zza, final Bitmap bitmap) {
            return bitmap.getHeight() * bitmap.getRowBytes();
        }
        
        protected void zza(final boolean b, final com.google.android.gms.common.images.zza.zza zza, final Bitmap bitmap, final Bitmap bitmap2) {
            super.entryRemoved(b, zza, bitmap, bitmap2);
        }
    }
    
    private final class zzc implements Runnable
    {
        private final ParcelFileDescriptor Aq;
        private final Uri mUri;
        
        public zzc(final Uri mUri, final ParcelFileDescriptor aq) {
            this.mUri = mUri;
            this.Aq = aq;
        }
        
        @Override
        public void run() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: ldc             "LoadBitmapFromDiskRunnable can't be executed in the main thread"
            //     2: invokestatic    com/google/android/gms/common/internal/zzc.zzhr:(Ljava/lang/String;)V
            //     5: aload_0        
            //     6: getfield        com/google/android/gms/common/images/ImageManager$zzc.Aq:Landroid/os/ParcelFileDescriptor;
            //     9: astore_1       
            //    10: aconst_null    
            //    11: astore_2       
            //    12: iconst_0       
            //    13: istore_3       
            //    14: aload_1        
            //    15: ifnull          40
            //    18: aload_0        
            //    19: getfield        com/google/android/gms/common/images/ImageManager$zzc.Aq:Landroid/os/ParcelFileDescriptor;
            //    22: invokevirtual   android/os/ParcelFileDescriptor.getFileDescriptor:()Ljava/io/FileDescriptor;
            //    25: invokestatic    android/graphics/BitmapFactory.decodeFileDescriptor:(Ljava/io/FileDescriptor;)Landroid/graphics/Bitmap;
            //    28: astore          14
            //    30: aload           14
            //    32: astore_2       
            //    33: aload_0        
            //    34: getfield        com/google/android/gms/common/images/ImageManager$zzc.Aq:Landroid/os/ParcelFileDescriptor;
            //    37: invokevirtual   android/os/ParcelFileDescriptor.close:()V
            //    40: new             Ljava/util/concurrent/CountDownLatch;
            //    43: dup            
            //    44: iconst_1       
            //    45: invokespecial   java/util/concurrent/CountDownLatch.<init>:(I)V
            //    48: astore          4
            //    50: aload_0        
            //    51: getfield        com/google/android/gms/common/images/ImageManager$zzc.Ap:Lcom/google/android/gms/common/images/ImageManager;
            //    54: invokestatic    com/google/android/gms/common/images/ImageManager.zzg:(Lcom/google/android/gms/common/images/ImageManager;)Landroid/os/Handler;
            //    57: new             Lcom/google/android/gms/common/images/ImageManager$zzf;
            //    60: dup            
            //    61: aload_0        
            //    62: getfield        com/google/android/gms/common/images/ImageManager$zzc.Ap:Lcom/google/android/gms/common/images/ImageManager;
            //    65: aload_0        
            //    66: getfield        com/google/android/gms/common/images/ImageManager$zzc.mUri:Landroid/net/Uri;
            //    69: aload_2        
            //    70: iload_3        
            //    71: aload           4
            //    73: invokespecial   com/google/android/gms/common/images/ImageManager$zzf.<init>:(Lcom/google/android/gms/common/images/ImageManager;Landroid/net/Uri;Landroid/graphics/Bitmap;ZLjava/util/concurrent/CountDownLatch;)V
            //    76: invokevirtual   android/os/Handler.post:(Ljava/lang/Runnable;)Z
            //    79: pop            
            //    80: aload           4
            //    82: invokevirtual   java/util/concurrent/CountDownLatch.await:()V
            //    85: return         
            //    86: astore          9
            //    88: aload_0        
            //    89: getfield        com/google/android/gms/common/images/ImageManager$zzc.mUri:Landroid/net/Uri;
            //    92: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //    95: astore          10
            //    97: ldc             "ImageManager"
            //    99: new             Ljava/lang/StringBuilder;
            //   102: dup            
            //   103: bipush          34
            //   105: aload           10
            //   107: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   110: invokevirtual   java/lang/String.length:()I
            //   113: iadd           
            //   114: invokespecial   java/lang/StringBuilder.<init>:(I)V
            //   117: ldc             "OOM while loading bitmap for uri: "
            //   119: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   122: aload           10
            //   124: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   127: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   130: aload           9
            //   132: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   135: pop            
            //   136: iconst_1       
            //   137: istore_3       
            //   138: aconst_null    
            //   139: astore_2       
            //   140: goto            33
            //   143: astore          12
            //   145: ldc             "ImageManager"
            //   147: ldc             "closed failed"
            //   149: aload           12
            //   151: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   154: pop            
            //   155: goto            40
            //   158: astore          6
            //   160: aload_0        
            //   161: getfield        com/google/android/gms/common/images/ImageManager$zzc.mUri:Landroid/net/Uri;
            //   164: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   167: astore          7
            //   169: ldc             "ImageManager"
            //   171: new             Ljava/lang/StringBuilder;
            //   174: dup            
            //   175: bipush          32
            //   177: aload           7
            //   179: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   182: invokevirtual   java/lang/String.length:()I
            //   185: iadd           
            //   186: invokespecial   java/lang/StringBuilder.<init>:(I)V
            //   189: ldc             "Latch interrupted while posting "
            //   191: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   194: aload           7
            //   196: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   199: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   202: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
            //   205: pop            
            //   206: goto            85
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  18     30     86     143    Ljava/lang/OutOfMemoryError;
            //  33     40     143    158    Ljava/io/IOException;
            //  80     85     158    209    Ljava/lang/InterruptedException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IndexOutOfBoundsException: Index: 100, Size: 100
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
    }
    
    private final class zzd implements Runnable
    {
        private final com.google.android.gms.common.images.zza Ar;
        
        public zzd(final com.google.android.gms.common.images.zza ar) {
            this.Ar = ar;
        }
        
        @Override
        public void run() {
            com.google.android.gms.common.internal.zzc.zzhq("LoadImageRunnable must be executed on the main thread");
            final ImageReceiver imageReceiver = ImageManager.this.Al.get(this.Ar);
            if (imageReceiver != null) {
                ImageManager.this.Al.remove(this.Ar);
                imageReceiver.zzc(this.Ar);
            }
            final com.google.android.gms.common.images.zza.zza at = this.Ar.At;
            if (at.uri == null) {
                this.Ar.zza(ImageManager.this.mContext, ImageManager.this.Ak, true);
            }
            else {
                final Bitmap zza = ImageManager.this.zza(at);
                if (zza != null) {
                    this.Ar.zza(ImageManager.this.mContext, zza, true);
                }
                else {
                    final Long n = ImageManager.this.An.get(at.uri);
                    if (n != null) {
                        if (SystemClock.elapsedRealtime() - n < 3600000L) {
                            this.Ar.zza(ImageManager.this.mContext, ImageManager.this.Ak, true);
                            return;
                        }
                        ImageManager.this.An.remove(at.uri);
                    }
                    this.Ar.zza(ImageManager.this.mContext, ImageManager.this.Ak);
                    ResultReceiver resultReceiver = ImageManager.this.Am.get(at.uri);
                    if (resultReceiver == null) {
                        resultReceiver = new ImageReceiver(at.uri);
                        ImageManager.this.Am.put(at.uri, resultReceiver);
                    }
                    ((ImageReceiver)resultReceiver).zzb(this.Ar);
                    if (!(this.Ar instanceof com.google.android.gms.common.images.zza.zzc)) {
                        ImageManager.this.Al.put(this.Ar, resultReceiver);
                    }
                    synchronized (ImageManager.Ae) {
                        if (!ImageManager.Af.contains(at.uri)) {
                            ImageManager.Af.add(at.uri);
                            ((ImageReceiver)resultReceiver).zzatm();
                        }
                    }
                }
            }
        }
    }
    
    @TargetApi(14)
    private static final class zze implements ComponentCallbacks2
    {
        private final zzb Aj;
        
        public zze(final zzb aj) {
            this.Aj = aj;
        }
        
        public void onConfigurationChanged(final Configuration configuration) {
        }
        
        public void onLowMemory() {
            this.Aj.evictAll();
        }
        
        public void onTrimMemory(final int n) {
            if (n >= 60) {
                this.Aj.evictAll();
            }
            else if (n >= 20) {
                this.Aj.trimToSize(this.Aj.size() / 2);
            }
        }
    }
    
    private final class zzf implements Runnable
    {
        private boolean As;
        private final Bitmap mBitmap;
        private final Uri mUri;
        private final CountDownLatch zzamx;
        
        public zzf(final Uri mUri, final Bitmap mBitmap, final boolean as, final CountDownLatch zzamx) {
            this.mUri = mUri;
            this.mBitmap = mBitmap;
            this.As = as;
            this.zzamx = zzamx;
        }
        
        private void zza(final ImageReceiver imageReceiver, final boolean b) {
            final ArrayList zza = imageReceiver.Ao;
            for (int size = zza.size(), i = 0; i < size; ++i) {
                final com.google.android.gms.common.images.zza zza2 = zza.get(i);
                if (b) {
                    zza2.zza(ImageManager.this.mContext, this.mBitmap, false);
                }
                else {
                    ImageManager.this.An.put(this.mUri, SystemClock.elapsedRealtime());
                    zza2.zza(ImageManager.this.mContext, ImageManager.this.Ak, false);
                }
                if (!(zza2 instanceof com.google.android.gms.common.images.zza.zzc)) {
                    ImageManager.this.Al.remove(zza2);
                }
            }
        }
        
        @Override
        public void run() {
            com.google.android.gms.common.internal.zzc.zzhq("OnBitmapLoadedRunnable must be executed in the main thread");
            final boolean b = this.mBitmap != null;
            Label_0097: {
                if (ImageManager.this.Aj == null) {
                    break Label_0097;
                }
                if (this.As) {
                    ImageManager.this.Aj.evictAll();
                    System.gc();
                    this.As = false;
                    ImageManager.this.mHandler.post((Runnable)this);
                }
                else {
                    if (b) {
                        ImageManager.this.Aj.put(new com.google.android.gms.common.images.zza.zza(this.mUri), this.mBitmap);
                    }
                    break Label_0097;
                }
                return;
            }
            final ImageReceiver imageReceiver = ImageManager.this.Am.remove(this.mUri);
            if (imageReceiver != null) {
                this.zza(imageReceiver, b);
            }
            this.zzamx.countDown();
            synchronized (ImageManager.Ae) {
                ImageManager.Af.remove(this.mUri);
            }
        }
    }
}
