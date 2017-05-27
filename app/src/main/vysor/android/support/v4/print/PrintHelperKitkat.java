// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.print;

import android.os.CancellationSignal$OnCancelListener;
import android.print.PrintDocumentInfo;
import android.print.PrintDocumentInfo$Builder;
import android.os.Bundle;
import android.print.PrintDocumentAdapter$LayoutResultCallback;
import android.print.PrintDocumentAdapter;
import android.print.PrintAttributes$MediaSize;
import android.print.PrintManager;
import android.print.PrintAttributes$Builder;
import android.print.PageRange;
import android.os.AsyncTask;
import android.print.PrintAttributes$Margins;
import java.io.InputStream;
import java.io.IOException;
import android.util.Log;
import android.graphics.Rect;
import android.graphics.BitmapFactory;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Bitmap$Config;
import java.io.FileNotFoundException;
import android.net.Uri;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.print.PrintDocumentAdapter$WriteResultCallback;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.graphics.Bitmap;
import android.print.PrintAttributes;
import android.graphics.BitmapFactory$Options;
import android.content.Context;

class PrintHelperKitkat
{
    public static final int COLOR_MODE_COLOR = 2;
    public static final int COLOR_MODE_MONOCHROME = 1;
    private static final String LOG_TAG = "PrintHelperKitkat";
    private static final int MAX_PRINT_SIZE = 3500;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    int mColorMode;
    final Context mContext;
    BitmapFactory$Options mDecodeOptions;
    protected boolean mIsMinMarginsHandlingCorrect;
    private final Object mLock;
    int mOrientation;
    protected boolean mPrintActivityRespectsOrientation;
    int mScaleMode;
    
    PrintHelperKitkat(final Context mContext) {
        this.mDecodeOptions = null;
        this.mLock = new Object();
        this.mScaleMode = 2;
        this.mColorMode = 2;
        this.mPrintActivityRespectsOrientation = true;
        this.mIsMinMarginsHandlingCorrect = true;
        this.mContext = mContext;
    }
    
    private Bitmap convertBitmapForColorMode(Bitmap bitmap, final int n) {
        if (n == 1) {
            final Bitmap bitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap$Config.ARGB_8888);
            final Canvas canvas = new Canvas(bitmap2);
            final Paint paint = new Paint();
            final ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(0.0f);
            paint.setColorFilter((ColorFilter)new ColorMatrixColorFilter(colorMatrix));
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
            canvas.setBitmap((Bitmap)null);
            bitmap = bitmap2;
        }
        return bitmap;
    }
    
    private Matrix getMatrix(final int n, final int n2, final RectF rectF, final int n3) {
        final Matrix matrix = new Matrix();
        final float n4 = rectF.width() / n;
        float n5;
        if (n3 == 2) {
            n5 = Math.max(n4, rectF.height() / n2);
        }
        else {
            n5 = Math.min(n4, rectF.height() / n2);
        }
        matrix.postScale(n5, n5);
        matrix.postTranslate((rectF.width() - n5 * n) / 2.0f, (rectF.height() - n5 * n2) / 2.0f);
        return matrix;
    }
    
    private static boolean isPortrait(final Bitmap bitmap) {
        return bitmap.getWidth() <= bitmap.getHeight();
    }
    
    private Bitmap loadBitmap(final Uri uri, final BitmapFactory$Options bitmapFactory$Options) throws FileNotFoundException {
        if (uri == null || this.mContext == null) {
            throw new IllegalArgumentException("bad argument to loadBitmap");
        }
        InputStream openInputStream = null;
        try {
            openInputStream = this.mContext.getContentResolver().openInputStream(uri);
            final Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream, (Rect)null, bitmapFactory$Options);
            if (openInputStream == null) {
                return decodeStream;
            }
            try {
                openInputStream.close();
                return decodeStream;
            }
            catch (IOException ex) {
                Log.w("PrintHelperKitkat", "close fail ", (Throwable)ex);
            }
        }
        finally {
            Label_0079: {
                if (openInputStream == null) {
                    break Label_0079;
                }
                try {
                    openInputStream.close();
                }
                catch (IOException ex2) {
                    Log.w("PrintHelperKitkat", "close fail ", (Throwable)ex2);
                }
            }
        }
    }
    
    private Bitmap loadConstrainedBitmap(final Uri uri, final int n) throws FileNotFoundException {
        if (n <= 0 || uri == null || this.mContext == null) {
            throw new IllegalArgumentException("bad argument to getScaledBitmap");
        }
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        bitmapFactory$Options.inJustDecodeBounds = true;
        this.loadBitmap(uri, bitmapFactory$Options);
        final int outWidth = bitmapFactory$Options.outWidth;
        final int outHeight = bitmapFactory$Options.outHeight;
        Bitmap loadBitmap = null;
        if (outWidth > 0) {
            loadBitmap = null;
            if (outHeight > 0) {
                int i;
                int inSampleSize;
                for (i = Math.max(outWidth, outHeight), inSampleSize = 1; i > n; i >>>= 1, inSampleSize <<= 1) {}
                loadBitmap = null;
                if (inSampleSize > 0) {
                    final int n2 = Math.min(outWidth, outHeight) / inSampleSize;
                    loadBitmap = null;
                    if (n2 > 0) {
                        final Object mLock = this.mLock;
                        final BitmapFactory$Options mDecodeOptions;
                        synchronized (mLock) {
                            this.mDecodeOptions = new BitmapFactory$Options();
                            this.mDecodeOptions.inMutable = true;
                            this.mDecodeOptions.inSampleSize = inSampleSize;
                            mDecodeOptions = this.mDecodeOptions;
                            // monitorexit(mLock)
                            final PrintHelperKitkat printHelperKitkat = this;
                            final Uri uri2 = uri;
                            final BitmapFactory$Options bitmapFactory$Options2 = mDecodeOptions;
                            final Bitmap loadBitmap2 = printHelperKitkat.loadBitmap(uri2, bitmapFactory$Options2);
                            loadBitmap = loadBitmap2;
                            final PrintHelperKitkat printHelperKitkat2 = this;
                            final Object o = printHelperKitkat2.mLock;
                            final Object o3;
                            final Object o2 = o3 = o;
                            synchronized (o3) {
                                final PrintHelperKitkat printHelperKitkat3 = this;
                                final BitmapFactory$Options bitmapFactory$Options3 = null;
                                printHelperKitkat3.mDecodeOptions = bitmapFactory$Options3;
                            }
                        }
                        try {
                            final PrintHelperKitkat printHelperKitkat = this;
                            final Uri uri2 = uri;
                            final BitmapFactory$Options bitmapFactory$Options2 = mDecodeOptions;
                            final Bitmap loadBitmap2 = loadBitmap = printHelperKitkat.loadBitmap(uri2, bitmapFactory$Options2);
                            final PrintHelperKitkat printHelperKitkat2 = this;
                            final Object o = printHelperKitkat2.mLock;
                            final Object o3;
                            final Object o2 = o3 = o;
                            // monitorenter(o3)
                            final PrintHelperKitkat printHelperKitkat3 = this;
                            final BitmapFactory$Options bitmapFactory$Options3 = null;
                            printHelperKitkat3.mDecodeOptions = bitmapFactory$Options3;
                        }
                        finally {
                            synchronized (this.mLock) {
                                this.mDecodeOptions = null;
                            }
                            // monitorexit(this.mLock)
                        }
                    }
                }
            }
        }
        return loadBitmap;
    }
    
    private void writeBitmap(final PrintAttributes printAttributes, final int n, final Bitmap bitmap, final ParcelFileDescriptor parcelFileDescriptor, final CancellationSignal cancellationSignal, final PrintDocumentAdapter$WriteResultCallback printDocumentAdapter$WriteResultCallback) {
        PrintAttributes build;
        if (this.mIsMinMarginsHandlingCorrect) {
            build = printAttributes;
        }
        else {
            build = this.copyAttributes(printAttributes).setMinMargins(new PrintAttributes$Margins(0, 0, 0, 0)).build();
        }
        new AsyncTask<Void, Void, Throwable>() {
            protected Throwable doInBackground(final Void... p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        android/support/v4/print/PrintHelperKitkat$2.val$cancellationSignal:Landroid/os/CancellationSignal;
                //     4: invokevirtual   android/os/CancellationSignal.isCanceled:()Z
                //     7: ifeq            15
                //    10: aconst_null    
                //    11: astore_2       
                //    12: goto            434
                //    15: new             Landroid/print/pdf/PrintedPdfDocument;
                //    18: dup            
                //    19: aload_0        
                //    20: getfield        android/support/v4/print/PrintHelperKitkat$2.this$0:Landroid/support/v4/print/PrintHelperKitkat;
                //    23: getfield        android/support/v4/print/PrintHelperKitkat.mContext:Landroid/content/Context;
                //    26: aload_0        
                //    27: getfield        android/support/v4/print/PrintHelperKitkat$2.val$pdfAttributes:Landroid/print/PrintAttributes;
                //    30: invokespecial   android/print/pdf/PrintedPdfDocument.<init>:(Landroid/content/Context;Landroid/print/PrintAttributes;)V
                //    33: astore_3       
                //    34: aload_0        
                //    35: getfield        android/support/v4/print/PrintHelperKitkat$2.this$0:Landroid/support/v4/print/PrintHelperKitkat;
                //    38: aload_0        
                //    39: getfield        android/support/v4/print/PrintHelperKitkat$2.val$bitmap:Landroid/graphics/Bitmap;
                //    42: aload_0        
                //    43: getfield        android/support/v4/print/PrintHelperKitkat$2.val$pdfAttributes:Landroid/print/PrintAttributes;
                //    46: invokevirtual   android/print/PrintAttributes.getColorMode:()I
                //    49: invokestatic    android/support/v4/print/PrintHelperKitkat.access$100:(Landroid/support/v4/print/PrintHelperKitkat;Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;
                //    52: astore          4
                //    54: aload_0        
                //    55: getfield        android/support/v4/print/PrintHelperKitkat$2.val$cancellationSignal:Landroid/os/CancellationSignal;
                //    58: invokevirtual   android/os/CancellationSignal.isCanceled:()Z
                //    61: istore          5
                //    63: aconst_null    
                //    64: astore_2       
                //    65: iload           5
                //    67: ifne            434
                //    70: aload_3        
                //    71: iconst_1       
                //    72: invokevirtual   android/print/pdf/PrintedPdfDocument.startPage:(I)Landroid/graphics/pdf/PdfDocument$Page;
                //    75: astore          9
                //    77: aload_0        
                //    78: getfield        android/support/v4/print/PrintHelperKitkat$2.this$0:Landroid/support/v4/print/PrintHelperKitkat;
                //    81: getfield        android/support/v4/print/PrintHelperKitkat.mIsMinMarginsHandlingCorrect:Z
                //    84: ifeq            223
                //    87: new             Landroid/graphics/RectF;
                //    90: dup            
                //    91: aload           9
                //    93: invokevirtual   android/graphics/pdf/PdfDocument$Page.getInfo:()Landroid/graphics/pdf/PdfDocument$PageInfo;
                //    96: invokevirtual   android/graphics/pdf/PdfDocument$PageInfo.getContentRect:()Landroid/graphics/Rect;
                //    99: invokespecial   android/graphics/RectF.<init>:(Landroid/graphics/Rect;)V
                //   102: astore          10
                //   104: aload_0        
                //   105: getfield        android/support/v4/print/PrintHelperKitkat$2.this$0:Landroid/support/v4/print/PrintHelperKitkat;
                //   108: aload           4
                //   110: invokevirtual   android/graphics/Bitmap.getWidth:()I
                //   113: aload           4
                //   115: invokevirtual   android/graphics/Bitmap.getHeight:()I
                //   118: aload           10
                //   120: aload_0        
                //   121: getfield        android/support/v4/print/PrintHelperKitkat$2.val$fittingMode:I
                //   124: invokestatic    android/support/v4/print/PrintHelperKitkat.access$200:(Landroid/support/v4/print/PrintHelperKitkat;IILandroid/graphics/RectF;I)Landroid/graphics/Matrix;
                //   127: astore          11
                //   129: aload_0        
                //   130: getfield        android/support/v4/print/PrintHelperKitkat$2.this$0:Landroid/support/v4/print/PrintHelperKitkat;
                //   133: getfield        android/support/v4/print/PrintHelperKitkat.mIsMinMarginsHandlingCorrect:Z
                //   136: ifeq            324
                //   139: aload           9
                //   141: invokevirtual   android/graphics/pdf/PdfDocument$Page.getCanvas:()Landroid/graphics/Canvas;
                //   144: aload           4
                //   146: aload           11
                //   148: aconst_null    
                //   149: invokevirtual   android/graphics/Canvas.drawBitmap:(Landroid/graphics/Bitmap;Landroid/graphics/Matrix;Landroid/graphics/Paint;)V
                //   152: aload_3        
                //   153: aload           9
                //   155: invokevirtual   android/print/pdf/PrintedPdfDocument.finishPage:(Landroid/graphics/pdf/PdfDocument$Page;)V
                //   158: aload_0        
                //   159: getfield        android/support/v4/print/PrintHelperKitkat$2.val$cancellationSignal:Landroid/os/CancellationSignal;
                //   162: invokevirtual   android/os/CancellationSignal.isCanceled:()Z
                //   165: istore          14
                //   167: iload           14
                //   169: ifeq            354
                //   172: aload_3        
                //   173: invokevirtual   android/print/pdf/PrintedPdfDocument.close:()V
                //   176: aload_0        
                //   177: getfield        android/support/v4/print/PrintHelperKitkat$2.val$fileDescriptor:Landroid/os/ParcelFileDescriptor;
                //   180: astore          18
                //   182: aload           18
                //   184: ifnull          194
                //   187: aload_0        
                //   188: getfield        android/support/v4/print/PrintHelperKitkat$2.val$fileDescriptor:Landroid/os/ParcelFileDescriptor;
                //   191: invokevirtual   android/os/ParcelFileDescriptor.close:()V
                //   194: aload_0        
                //   195: getfield        android/support/v4/print/PrintHelperKitkat$2.val$bitmap:Landroid/graphics/Bitmap;
                //   198: astore          19
                //   200: aconst_null    
                //   201: astore_2       
                //   202: aload           4
                //   204: aload           19
                //   206: if_acmpeq       434
                //   209: aload           4
                //   211: invokevirtual   android/graphics/Bitmap.recycle:()V
                //   214: aconst_null    
                //   215: astore_2       
                //   216: goto            434
                //   219: astore_2       
                //   220: goto            434
                //   223: new             Landroid/print/pdf/PrintedPdfDocument;
                //   226: dup            
                //   227: aload_0        
                //   228: getfield        android/support/v4/print/PrintHelperKitkat$2.this$0:Landroid/support/v4/print/PrintHelperKitkat;
                //   231: getfield        android/support/v4/print/PrintHelperKitkat.mContext:Landroid/content/Context;
                //   234: aload_0        
                //   235: getfield        android/support/v4/print/PrintHelperKitkat$2.val$attributes:Landroid/print/PrintAttributes;
                //   238: invokespecial   android/print/pdf/PrintedPdfDocument.<init>:(Landroid/content/Context;Landroid/print/PrintAttributes;)V
                //   241: astore          21
                //   243: aload           21
                //   245: iconst_1       
                //   246: invokevirtual   android/print/pdf/PrintedPdfDocument.startPage:(I)Landroid/graphics/pdf/PdfDocument$Page;
                //   249: astore          22
                //   251: new             Landroid/graphics/RectF;
                //   254: dup            
                //   255: aload           22
                //   257: invokevirtual   android/graphics/pdf/PdfDocument$Page.getInfo:()Landroid/graphics/pdf/PdfDocument$PageInfo;
                //   260: invokevirtual   android/graphics/pdf/PdfDocument$PageInfo.getContentRect:()Landroid/graphics/Rect;
                //   263: invokespecial   android/graphics/RectF.<init>:(Landroid/graphics/Rect;)V
                //   266: astore          10
                //   268: aload           21
                //   270: aload           22
                //   272: invokevirtual   android/print/pdf/PrintedPdfDocument.finishPage:(Landroid/graphics/pdf/PdfDocument$Page;)V
                //   275: aload           21
                //   277: invokevirtual   android/print/pdf/PrintedPdfDocument.close:()V
                //   280: goto            104
                //   283: astore          6
                //   285: aload_3        
                //   286: invokevirtual   android/print/pdf/PrintedPdfDocument.close:()V
                //   289: aload_0        
                //   290: getfield        android/support/v4/print/PrintHelperKitkat$2.val$fileDescriptor:Landroid/os/ParcelFileDescriptor;
                //   293: astore          7
                //   295: aload           7
                //   297: ifnull          307
                //   300: aload_0        
                //   301: getfield        android/support/v4/print/PrintHelperKitkat$2.val$fileDescriptor:Landroid/os/ParcelFileDescriptor;
                //   304: invokevirtual   android/os/ParcelFileDescriptor.close:()V
                //   307: aload           4
                //   309: aload_0        
                //   310: getfield        android/support/v4/print/PrintHelperKitkat$2.val$bitmap:Landroid/graphics/Bitmap;
                //   313: if_acmpeq       321
                //   316: aload           4
                //   318: invokevirtual   android/graphics/Bitmap.recycle:()V
                //   321: aload           6
                //   323: athrow         
                //   324: aload           11
                //   326: aload           10
                //   328: getfield        android/graphics/RectF.left:F
                //   331: aload           10
                //   333: getfield        android/graphics/RectF.top:F
                //   336: invokevirtual   android/graphics/Matrix.postTranslate:(FF)Z
                //   339: pop            
                //   340: aload           9
                //   342: invokevirtual   android/graphics/pdf/PdfDocument$Page.getCanvas:()Landroid/graphics/Canvas;
                //   345: aload           10
                //   347: invokevirtual   android/graphics/Canvas.clipRect:(Landroid/graphics/RectF;)Z
                //   350: pop            
                //   351: goto            139
                //   354: aload_3        
                //   355: new             Ljava/io/FileOutputStream;
                //   358: dup            
                //   359: aload_0        
                //   360: getfield        android/support/v4/print/PrintHelperKitkat$2.val$fileDescriptor:Landroid/os/ParcelFileDescriptor;
                //   363: invokevirtual   android/os/ParcelFileDescriptor.getFileDescriptor:()Ljava/io/FileDescriptor;
                //   366: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/FileDescriptor;)V
                //   369: invokevirtual   android/print/pdf/PrintedPdfDocument.writeTo:(Ljava/io/OutputStream;)V
                //   372: aload_3        
                //   373: invokevirtual   android/print/pdf/PrintedPdfDocument.close:()V
                //   376: aload_0        
                //   377: getfield        android/support/v4/print/PrintHelperKitkat$2.val$fileDescriptor:Landroid/os/ParcelFileDescriptor;
                //   380: astore          15
                //   382: aload           15
                //   384: ifnull          394
                //   387: aload_0        
                //   388: getfield        android/support/v4/print/PrintHelperKitkat$2.val$fileDescriptor:Landroid/os/ParcelFileDescriptor;
                //   391: invokevirtual   android/os/ParcelFileDescriptor.close:()V
                //   394: aload_0        
                //   395: getfield        android/support/v4/print/PrintHelperKitkat$2.val$bitmap:Landroid/graphics/Bitmap;
                //   398: astore          16
                //   400: aconst_null    
                //   401: astore_2       
                //   402: aload           4
                //   404: aload           16
                //   406: if_acmpeq       434
                //   409: aload           4
                //   411: invokevirtual   android/graphics/Bitmap.recycle:()V
                //   414: aconst_null    
                //   415: astore_2       
                //   416: goto            434
                //   419: astore          8
                //   421: goto            307
                //   424: astore          17
                //   426: goto            394
                //   429: astore          20
                //   431: goto            194
                //   434: aload_2        
                //   435: areturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  0      63     219    223    Ljava/lang/Throwable;
                //  70     167    283    324    Any
                //  172    182    219    223    Ljava/lang/Throwable;
                //  187    194    429    434    Ljava/io/IOException;
                //  187    194    219    223    Ljava/lang/Throwable;
                //  194    214    219    223    Ljava/lang/Throwable;
                //  223    280    283    324    Any
                //  285    295    219    223    Ljava/lang/Throwable;
                //  300    307    419    424    Ljava/io/IOException;
                //  300    307    219    223    Ljava/lang/Throwable;
                //  307    324    219    223    Ljava/lang/Throwable;
                //  324    372    283    324    Any
                //  372    382    219    223    Ljava/lang/Throwable;
                //  387    394    424    429    Ljava/io/IOException;
                //  387    394    219    223    Ljava/lang/Throwable;
                //  394    414    219    223    Ljava/lang/Throwable;
                // 
                // The error that occurred was:
                // 
                // java.lang.IndexOutOfBoundsException: Index: 200, Size: 200
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
            
            protected void onPostExecute(final Throwable t) {
                if (cancellationSignal.isCanceled()) {
                    printDocumentAdapter$WriteResultCallback.onWriteCancelled();
                }
                else if (t == null) {
                    printDocumentAdapter$WriteResultCallback.onWriteFinished(new PageRange[] { PageRange.ALL_PAGES });
                }
                else {
                    Log.e("PrintHelperKitkat", "Error writing printed content", t);
                    printDocumentAdapter$WriteResultCallback.onWriteFailed((CharSequence)null);
                }
            }
        }.execute((Object[])new Void[0]);
    }
    
    protected PrintAttributes$Builder copyAttributes(final PrintAttributes printAttributes) {
        final PrintAttributes$Builder setMinMargins = new PrintAttributes$Builder().setMediaSize(printAttributes.getMediaSize()).setResolution(printAttributes.getResolution()).setMinMargins(printAttributes.getMinMargins());
        if (printAttributes.getColorMode() != 0) {
            setMinMargins.setColorMode(printAttributes.getColorMode());
        }
        return setMinMargins;
    }
    
    public int getColorMode() {
        return this.mColorMode;
    }
    
    public int getOrientation() {
        int mOrientation;
        if (this.mOrientation == 0) {
            mOrientation = 1;
        }
        else {
            mOrientation = this.mOrientation;
        }
        return mOrientation;
    }
    
    public int getScaleMode() {
        return this.mScaleMode;
    }
    
    public void printBitmap(final String s, final Bitmap bitmap, final OnPrintFinishCallback onPrintFinishCallback) {
        if (bitmap != null) {
            final int mScaleMode = this.mScaleMode;
            final PrintManager printManager = (PrintManager)this.mContext.getSystemService("print");
            PrintAttributes$MediaSize mediaSize;
            if (isPortrait(bitmap)) {
                mediaSize = PrintAttributes$MediaSize.UNKNOWN_PORTRAIT;
            }
            else {
                mediaSize = PrintAttributes$MediaSize.UNKNOWN_LANDSCAPE;
            }
            printManager.print(s, (PrintDocumentAdapter)new PrintDocumentAdapter() {
                private PrintAttributes mAttributes;
                
                public void onFinish() {
                    if (onPrintFinishCallback != null) {
                        onPrintFinishCallback.onFinish();
                    }
                }
                
                public void onLayout(final PrintAttributes printAttributes, final PrintAttributes mAttributes, final CancellationSignal cancellationSignal, final PrintDocumentAdapter$LayoutResultCallback printDocumentAdapter$LayoutResultCallback, final Bundle bundle) {
                    int n = 1;
                    this.mAttributes = mAttributes;
                    final PrintDocumentInfo build = new PrintDocumentInfo$Builder(s).setContentType(n).setPageCount(n).build();
                    if (mAttributes.equals((Object)printAttributes)) {
                        n = 0;
                    }
                    printDocumentAdapter$LayoutResultCallback.onLayoutFinished(build, (boolean)(n != 0));
                }
                
                public void onWrite(final PageRange[] array, final ParcelFileDescriptor parcelFileDescriptor, final CancellationSignal cancellationSignal, final PrintDocumentAdapter$WriteResultCallback printDocumentAdapter$WriteResultCallback) {
                    PrintHelperKitkat.this.writeBitmap(this.mAttributes, mScaleMode, bitmap, parcelFileDescriptor, cancellationSignal, printDocumentAdapter$WriteResultCallback);
                }
            }, new PrintAttributes$Builder().setMediaSize(mediaSize).setColorMode(this.mColorMode).build());
        }
    }
    
    public void printBitmap(final String s, final Uri uri, final OnPrintFinishCallback onPrintFinishCallback) throws FileNotFoundException {
        final PrintDocumentAdapter printDocumentAdapter = new PrintDocumentAdapter() {
            private PrintAttributes mAttributes;
            Bitmap mBitmap = null;
            AsyncTask<Uri, Boolean, Bitmap> mLoadBitmap;
            final /* synthetic */ int val$fittingMode = PrintHelperKitkat.this.mScaleMode;
            
            private void cancelLoad() {
                synchronized (PrintHelperKitkat.this.mLock) {
                    if (PrintHelperKitkat.this.mDecodeOptions != null) {
                        PrintHelperKitkat.this.mDecodeOptions.requestCancelDecode();
                        PrintHelperKitkat.this.mDecodeOptions = null;
                    }
                }
            }
            
            public void onFinish() {
                super.onFinish();
                this.cancelLoad();
                if (this.mLoadBitmap != null) {
                    this.mLoadBitmap.cancel(true);
                }
                if (onPrintFinishCallback != null) {
                    onPrintFinishCallback.onFinish();
                }
                if (this.mBitmap != null) {
                    this.mBitmap.recycle();
                    this.mBitmap = null;
                }
            }
            
            public void onLayout(final PrintAttributes printAttributes, final PrintAttributes mAttributes, final CancellationSignal cancellationSignal, final PrintDocumentAdapter$LayoutResultCallback printDocumentAdapter$LayoutResultCallback, final Bundle bundle) {
                while (true) {
                    int n = 1;
                    synchronized (this) {
                        this.mAttributes = mAttributes;
                        // monitorexit(this)
                        if (cancellationSignal.isCanceled()) {
                            printDocumentAdapter$LayoutResultCallback.onLayoutCancelled();
                            return;
                        }
                    }
                    if (this.mBitmap != null) {
                        final PrintDocumentInfo build = new PrintDocumentInfo$Builder(s).setContentType(n).setPageCount(n).build();
                        if (mAttributes.equals((Object)printAttributes)) {
                            n = 0;
                        }
                        printDocumentAdapter$LayoutResultCallback.onLayoutFinished(build, (boolean)(n != 0));
                        return;
                    }
                    this.mLoadBitmap = (AsyncTask<Uri, Boolean, Bitmap>)new AsyncTask<Uri, Boolean, Bitmap>() {
                        protected Bitmap doInBackground(final Uri... array) {
                            try {
                                return PrintHelperKitkat.this.loadConstrainedBitmap(uri, 3500);
                            }
                            catch (FileNotFoundException ex) {
                                return null;
                            }
                        }
                        
                        protected void onCancelled(final Bitmap bitmap) {
                            printDocumentAdapter$LayoutResultCallback.onLayoutCancelled();
                            PrintDocumentAdapter.this.mLoadBitmap = null;
                        }
                        
                        protected void onPostExecute(Bitmap bitmap) {
                            super.onPostExecute((Object)bitmap);
                            Label_0111: {
                                if (bitmap == null || (PrintHelperKitkat.this.mPrintActivityRespectsOrientation && PrintHelperKitkat.this.mOrientation != 0)) {
                                    break Label_0111;
                                }
                            Label_0174_Outer:
                                while (true) {
                                    while (true) {
                                        Label_0195: {
                                            while (true) {
                                                synchronized (this) {
                                                    final PrintAttributes$MediaSize mediaSize = PrintDocumentAdapter.this.mAttributes.getMediaSize();
                                                    // monitorexit(this)
                                                    if (mediaSize != null && mediaSize.isPortrait() != isPortrait(bitmap)) {
                                                        final Matrix matrix = new Matrix();
                                                        matrix.postRotate(90.0f);
                                                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                                                    }
                                                    if ((PrintDocumentAdapter.this.mBitmap = bitmap) == null) {
                                                        break Label_0195;
                                                    }
                                                    final PrintDocumentInfo build = new PrintDocumentInfo$Builder(s).setContentType(1).setPageCount(1).build();
                                                    if (!mAttributes.equals((Object)printAttributes)) {
                                                        final boolean b = true;
                                                        printDocumentAdapter$LayoutResultCallback.onLayoutFinished(build, b);
                                                        PrintDocumentAdapter.this.mLoadBitmap = null;
                                                        return;
                                                    }
                                                }
                                                final boolean b = false;
                                                continue Label_0174_Outer;
                                            }
                                        }
                                        printDocumentAdapter$LayoutResultCallback.onLayoutFailed((CharSequence)null);
                                        continue;
                                    }
                                }
                            }
                        }
                        
                        protected void onPreExecute() {
                            cancellationSignal.setOnCancelListener((CancellationSignal$OnCancelListener)new CancellationSignal$OnCancelListener() {
                                public void onCancel() {
                                    PrintDocumentAdapter.this.cancelLoad();
                                    AsyncTask.this.cancel(false);
                                }
                            });
                        }
                    }.execute((Object[])new Uri[0]);
                }
            }
            
            public void onWrite(final PageRange[] array, final ParcelFileDescriptor parcelFileDescriptor, final CancellationSignal cancellationSignal, final PrintDocumentAdapter$WriteResultCallback printDocumentAdapter$WriteResultCallback) {
                PrintHelperKitkat.this.writeBitmap(this.mAttributes, this.val$fittingMode, this.mBitmap, parcelFileDescriptor, cancellationSignal, printDocumentAdapter$WriteResultCallback);
            }
        };
        final PrintManager printManager = (PrintManager)this.mContext.getSystemService("print");
        final PrintAttributes$Builder printAttributes$Builder = new PrintAttributes$Builder();
        printAttributes$Builder.setColorMode(this.mColorMode);
        if (this.mOrientation == 1 || this.mOrientation == 0) {
            printAttributes$Builder.setMediaSize(PrintAttributes$MediaSize.UNKNOWN_LANDSCAPE);
        }
        else if (this.mOrientation == 2) {
            printAttributes$Builder.setMediaSize(PrintAttributes$MediaSize.UNKNOWN_PORTRAIT);
        }
        printManager.print(s, (PrintDocumentAdapter)printDocumentAdapter, printAttributes$Builder.build());
    }
    
    public void setColorMode(final int mColorMode) {
        this.mColorMode = mColorMode;
    }
    
    public void setOrientation(final int mOrientation) {
        this.mOrientation = mOrientation;
    }
    
    public void setScaleMode(final int mScaleMode) {
        this.mScaleMode = mScaleMode;
    }
    
    public interface OnPrintFinishCallback
    {
        void onFinish();
    }
}
