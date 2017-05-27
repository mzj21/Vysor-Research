// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.virtualdisplay;

import org.xml.sax.Attributes;
import android.os.Build$VERSION;
import android.media.MediaFormat;
import android.os.Handler;
import android.content.Context;
import android.annotation.TargetApi;
import android.view.Surface;
import android.media.MediaCodecInfo$CodecCapabilities;
import android.media.MediaCodecInfo;
import android.util.Log;
import android.media.MediaCodec;
import android.graphics.Point;

public abstract class EncoderDevice
{
    protected final String LOGTAG;
    int colorFormat;
    Point encSize;
    protected int height;
    protected Thread lastRecorderThread;
    public String name;
    boolean useEncodingConstraints;
    boolean useSurface;
    protected VirtualDisplayFactory vdf;
    protected MediaCodec venc;
    protected VirtualDisplay virtualDisplay;
    protected int width;
    
    public EncoderDevice(final String name, final int width, final int height) {
        this.LOGTAG = this.getClass().getSimpleName();
        this.useSurface = true;
        this.useEncodingConstraints = true;
        this.width = width;
        this.height = height;
        this.name = name;
        Log.i(this.LOGTAG, "Requested Width: " + width + " Requested Height: " + height);
    }
    
    public static int getSupportedDimension(final int n) {
        int n2 = 144;
        if (n > n2) {
            if (n <= 176) {
                n2 = 176;
            }
            else if (n <= 240) {
                n2 = 240;
            }
            else if (n <= 288) {
                n2 = 288;
            }
            else if (n <= 320) {
                n2 = 320;
            }
            else if (n <= 352) {
                n2 = 352;
            }
            else if (n <= 480) {
                n2 = 480;
            }
            else if (n <= 576) {
                n2 = 576;
            }
            else if (n <= 720) {
                n2 = 720;
            }
            else if (n <= 1024) {
                n2 = 1024;
            }
            else if (n <= 1280) {
                n2 = 1280;
            }
            else {
                n2 = 1920;
            }
        }
        return n2;
    }
    
    private static boolean isRecognizedFormat(final int n) {
        boolean b = false;
        switch (n) {
            default: {
                b = false;
                break;
            }
            case 19:
            case 20:
            case 21:
            case 39:
            case 2130706688: {
                b = true;
                break;
            }
        }
        return b;
    }
    
    private int selectColorFormat(final MediaCodecInfo mediaCodecInfo, final String s) throws Exception {
        final MediaCodecInfo$CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(s);
        Log.i(this.LOGTAG, "Available color formats: " + capabilitiesForType.colorFormats.length);
        for (int i = 0; i < capabilitiesForType.colorFormats.length; ++i) {
            final int n = capabilitiesForType.colorFormats[i];
            if (isRecognizedFormat(n)) {
                Log.i(this.LOGTAG, "Using: " + n);
                return n;
            }
            Log.i(this.LOGTAG, "Not using: " + n);
        }
        throw new Exception("Unable to find suitable color format");
    }
    
    public Surface createDisplaySurface() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       android/os/Build$VERSION.SDK_INT:I
        //     3: bipush          18
        //     5: if_icmplt       12
        //     8: aload_0        
        //     9: invokevirtual   com/koushikdutta/virtualdisplay/EncoderDevice.signalEnd:()V
        //    12: aload_0        
        //    13: aconst_null    
        //    14: putfield        com/koushikdutta/virtualdisplay/EncoderDevice.mMediaCodec:Landroid/media/MediaCodec;
        //    17: aconst_null    
        //    18: astore_1       
        //    19: invokestatic    android/media/MediaCodecList.getCodecCount:()I
        //    22: istore          56
        //    24: iconst_0       
        //    25: istore          57
        //    27: iload           57
        //    29: iload           56
        //    31: if_icmpge       265
        //    34: iload           57
        //    36: invokestatic    android/media/MediaCodecList.getCodecInfoAt:(I)Landroid/media/MediaCodecInfo;
        //    39: astore          58
        //    41: aload           58
        //    43: invokevirtual   android/media/MediaCodecInfo.isEncoder:()Z
        //    46: ifne            52
        //    49: goto            1247
        //    52: aload           58
        //    54: invokevirtual   android/media/MediaCodecInfo.getSupportedTypes:()[Ljava/lang/String;
        //    57: astore          59
        //    59: aload           59
        //    61: arraylength    
        //    62: istore          60
        //    64: iconst_0       
        //    65: istore          61
        //    67: iload           61
        //    69: iload           60
        //    71: if_icmpge       1247
        //    74: aload           59
        //    76: iload           61
        //    78: aaload         
        //    79: ldc             "video/avc"
        //    81: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //    84: ifeq            258
        //    87: aload_1        
        //    88: ifnonnull       94
        //    91: aload           58
        //    93: astore_1       
        //    94: aload_0        
        //    95: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.LOGTAG:Ljava/lang/String;
        //    98: aload           58
        //   100: invokevirtual   android/media/MediaCodecInfo.getName:()Ljava/lang/String;
        //   103: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   106: pop            
        //   107: aload           58
        //   109: ldc             "video/avc"
        //   111: invokevirtual   android/media/MediaCodecInfo.getCapabilitiesForType:(Ljava/lang/String;)Landroid/media/MediaCodecInfo$CodecCapabilities;
        //   114: astore          63
        //   116: aload           63
        //   118: getfield        android/media/MediaCodecInfo$CodecCapabilities.colorFormats:[I
        //   121: astore          64
        //   123: aload           64
        //   125: arraylength    
        //   126: istore          65
        //   128: iconst_0       
        //   129: istore          66
        //   131: iload           66
        //   133: iload           65
        //   135: if_icmpge       179
        //   138: aload           64
        //   140: iload           66
        //   142: iaload         
        //   143: istore          72
        //   145: aload_0        
        //   146: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.LOGTAG:Ljava/lang/String;
        //   149: new             Ljava/lang/StringBuilder;
        //   152: dup            
        //   153: invokespecial   java/lang/StringBuilder.<init>:()V
        //   156: ldc             "colorFormat: "
        //   158: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   161: iload           72
        //   163: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   166: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   169: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   172: pop            
        //   173: iinc            66, 1
        //   176: goto            131
        //   179: aload           63
        //   181: getfield        android/media/MediaCodecInfo$CodecCapabilities.profileLevels:[Landroid/media/MediaCodecInfo$CodecProfileLevel;
        //   184: astore          67
        //   186: aload           67
        //   188: arraylength    
        //   189: istore          68
        //   191: iconst_0       
        //   192: istore          69
        //   194: iload           69
        //   196: iload           68
        //   198: if_icmpge       258
        //   201: aload           67
        //   203: iload           69
        //   205: aaload         
        //   206: astore          70
        //   208: aload_0        
        //   209: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.LOGTAG:Ljava/lang/String;
        //   212: new             Ljava/lang/StringBuilder;
        //   215: dup            
        //   216: invokespecial   java/lang/StringBuilder.<init>:()V
        //   219: ldc             "profile/level: "
        //   221: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   224: aload           70
        //   226: getfield        android/media/MediaCodecInfo$CodecProfileLevel.profile:I
        //   229: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   232: ldc             "/"
        //   234: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   237: aload           70
        //   239: getfield        android/media/MediaCodecInfo$CodecProfileLevel.level:I
        //   242: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   245: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   248: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   251: pop            
        //   252: iinc            69, 1
        //   255: goto            194
        //   258: iinc            61, 1
        //   261: goto            67
        //   264: astore_2       
        //   265: ldc             "/system/etc/media_profiles.xml"
        //   267: invokestatic    com/koushikdutta/async/util/StreamUtility.readFile:(Ljava/lang/String;)Ljava/lang/String;
        //   270: astore          49
        //   272: new             La/h;
        //   275: dup            
        //   276: ldc             "MediaSettings"
        //   278: invokespecial   a/h.<init>:(Ljava/lang/String;)V
        //   281: astore          50
        //   283: aload           50
        //   285: ldc             "VideoEncoderCap"
        //   287: invokevirtual   a/h.b:(Ljava/lang/String;)La/c;
        //   290: astore          51
        //   292: new             Ljava/util/ArrayList;
        //   295: dup            
        //   296: invokespecial   java/util/ArrayList.<init>:()V
        //   299: astore          52
        //   301: new             Lcom/koushikdutta/virtualdisplay/EncoderDevice$1;
        //   304: dup            
        //   305: aload_0        
        //   306: aload           52
        //   308: invokespecial   com/koushikdutta/virtualdisplay/EncoderDevice$1.<init>:(Lcom/koushikdutta/virtualdisplay/EncoderDevice;Ljava/util/ArrayList;)V
        //   311: astore          53
        //   313: aload           51
        //   315: aload           53
        //   317: invokevirtual   a/c.a:(La/d;)V
        //   320: new             Ljava/io/StringReader;
        //   323: dup            
        //   324: aload           49
        //   326: invokespecial   java/io/StringReader.<init>:(Ljava/lang/String;)V
        //   329: astore          54
        //   331: aload           54
        //   333: aload           50
        //   335: invokevirtual   a/h.b:()Lorg/xml/sax/ContentHandler;
        //   338: invokestatic    a/g.a:(Ljava/io/Reader;Lorg/xml/sax/ContentHandler;)V
        //   341: aload           52
        //   343: invokevirtual   java/util/ArrayList.size:()I
        //   346: iconst_1       
        //   347: if_icmpeq       968
        //   350: new             Ljava/lang/Exception;
        //   353: dup            
        //   354: ldc             "derp"
        //   356: invokespecial   java/lang/Exception.<init>:(Ljava/lang/String;)V
        //   359: athrow         
        //   360: astore_3       
        //   361: aload_0        
        //   362: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.LOGTAG:Ljava/lang/String;
        //   365: ldc             "Error getting media profiles"
        //   367: aload_3        
        //   368: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   371: pop            
        //   372: bipush          6
        //   374: invokestatic    android/media/CamcorderProfile.get:(I)Landroid/media/CamcorderProfile;
        //   377: astore          48
        //   379: aload           48
        //   381: astore          7
        //   383: aload           7
        //   385: ifnonnull       398
        //   388: iconst_5       
        //   389: invokestatic    android/media/CamcorderProfile.get:(I)Landroid/media/CamcorderProfile;
        //   392: astore          47
        //   394: aload           47
        //   396: astore          7
        //   398: aload           7
        //   400: ifnonnull       1049
        //   403: sipush          640
        //   406: istore          8
        //   408: sipush          480
        //   411: istore          9
        //   413: ldc             2000000
        //   415: istore          10
        //   417: bipush          30
        //   419: istore          11
        //   421: aload_0        
        //   422: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.useEncodingConstraints:Z
        //   425: ifeq            535
        //   428: iload           8
        //   430: iload           9
        //   432: invokestatic    java/lang/Math.max:(II)I
        //   435: istore          35
        //   437: iload           8
        //   439: iload           9
        //   441: invokestatic    java/lang/Math.min:(II)I
        //   444: istore          36
        //   446: aload_0        
        //   447: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mWidth:I
        //   450: aload_0        
        //   451: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mHeight:I
        //   454: if_icmple       1080
        //   457: aload_0        
        //   458: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mWidth:I
        //   461: iload           35
        //   463: if_icmple       496
        //   466: iload           35
        //   468: i2d            
        //   469: aload_0        
        //   470: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mWidth:I
        //   473: i2d            
        //   474: ddiv           
        //   475: dstore          43
        //   477: aload_0        
        //   478: iload           35
        //   480: putfield        com/koushikdutta/virtualdisplay/EncoderDevice.mWidth:I
        //   483: aload_0        
        //   484: dload           43
        //   486: aload_0        
        //   487: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mHeight:I
        //   490: i2d            
        //   491: dmul           
        //   492: d2i            
        //   493: putfield        com/koushikdutta/virtualdisplay/EncoderDevice.mHeight:I
        //   496: aload_0        
        //   497: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mHeight:I
        //   500: iload           36
        //   502: if_icmple       535
        //   505: iload           36
        //   507: i2d            
        //   508: aload_0        
        //   509: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mHeight:I
        //   512: i2d            
        //   513: ddiv           
        //   514: dstore          41
        //   516: aload_0        
        //   517: iload           36
        //   519: putfield        com/koushikdutta/virtualdisplay/EncoderDevice.mHeight:I
        //   522: aload_0        
        //   523: dload           41
        //   525: aload_0        
        //   526: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mWidth:I
        //   529: i2d            
        //   530: dmul           
        //   531: d2i            
        //   532: putfield        com/koushikdutta/virtualdisplay/EncoderDevice.mWidth:I
        //   535: aload_0        
        //   536: aload_0        
        //   537: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mWidth:I
        //   540: bipush          16
        //   542: idiv           
        //   543: putfield        com/koushikdutta/virtualdisplay/EncoderDevice.mWidth:I
        //   546: aload_0        
        //   547: bipush          16
        //   549: aload_0        
        //   550: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mWidth:I
        //   553: imul           
        //   554: putfield        com/koushikdutta/virtualdisplay/EncoderDevice.mWidth:I
        //   557: aload_0        
        //   558: aload_0        
        //   559: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mHeight:I
        //   562: bipush          16
        //   564: idiv           
        //   565: putfield        com/koushikdutta/virtualdisplay/EncoderDevice.mHeight:I
        //   568: aload_0        
        //   569: bipush          16
        //   571: aload_0        
        //   572: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mHeight:I
        //   575: imul           
        //   576: putfield        com/koushikdutta/virtualdisplay/EncoderDevice.mHeight:I
        //   579: aload_0        
        //   580: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.LOGTAG:Ljava/lang/String;
        //   583: new             Ljava/lang/StringBuilder;
        //   586: dup            
        //   587: invokespecial   java/lang/StringBuilder.<init>:()V
        //   590: ldc             "Width: "
        //   592: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   595: aload_0        
        //   596: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mWidth:I
        //   599: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   602: ldc             " Height: "
        //   604: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   607: aload_0        
        //   608: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mHeight:I
        //   611: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   614: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   617: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   620: pop            
        //   621: aload_0        
        //   622: new             Landroid/graphics/Point;
        //   625: dup            
        //   626: aload_0        
        //   627: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mWidth:I
        //   630: aload_0        
        //   631: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mHeight:I
        //   634: invokespecial   android/graphics/Point.<init>:(II)V
        //   637: putfield        com/koushikdutta/virtualdisplay/EncoderDevice.encSize:Landroid/graphics/Point;
        //   640: ldc             "video/avc"
        //   642: aload_0        
        //   643: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mWidth:I
        //   646: aload_0        
        //   647: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mHeight:I
        //   650: invokestatic    android/media/MediaFormat.createVideoFormat:(Ljava/lang/String;II)Landroid/media/MediaFormat;
        //   653: astore          13
        //   655: aload_0        
        //   656: iload           10
        //   658: invokevirtual   com/koushikdutta/virtualdisplay/EncoderDevice.getBitrate:(I)I
        //   661: istore          14
        //   663: aload_0        
        //   664: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.LOGTAG:Ljava/lang/String;
        //   667: new             Ljava/lang/StringBuilder;
        //   670: dup            
        //   671: invokespecial   java/lang/StringBuilder.<init>:()V
        //   674: ldc_w           "Bitrate: "
        //   677: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   680: iload           14
        //   682: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   685: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   688: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   691: pop            
        //   692: aload           13
        //   694: ldc_w           "bitrate"
        //   697: iload           14
        //   699: invokevirtual   android/media/MediaFormat.setInteger:(Ljava/lang/String;I)V
        //   702: aload           13
        //   704: ldc_w           "frame-rate"
        //   707: iload           11
        //   709: invokevirtual   android/media/MediaFormat.setInteger:(Ljava/lang/String;I)V
        //   712: aload_0        
        //   713: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.LOGTAG:Ljava/lang/String;
        //   716: new             Ljava/lang/StringBuilder;
        //   719: dup            
        //   720: invokespecial   java/lang/StringBuilder.<init>:()V
        //   723: ldc_w           "Frame rate: "
        //   726: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   729: iload           11
        //   731: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   734: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   737: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   740: pop            
        //   741: aload           13
        //   743: ldc_w           "repeat-previous-frame-after"
        //   746: getstatic       java/util/concurrent/TimeUnit.MILLISECONDS:Ljava/util/concurrent/TimeUnit;
        //   749: sipush          1000
        //   752: iload           11
        //   754: idiv           
        //   755: i2l            
        //   756: invokevirtual   java/util/concurrent/TimeUnit.toMicros:(J)J
        //   759: invokevirtual   android/media/MediaFormat.setLong:(Ljava/lang/String;J)V
        //   762: aload           13
        //   764: ldc_w           "i-frame-interval"
        //   767: bipush          30
        //   769: invokevirtual   android/media/MediaFormat.setInteger:(Ljava/lang/String;I)V
        //   772: aload_0        
        //   773: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.LOGTAG:Ljava/lang/String;
        //   776: ldc_w           "Creating encoder"
        //   779: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   782: pop            
        //   783: aload_0        
        //   784: invokevirtual   com/koushikdutta/virtualdisplay/EncoderDevice.supportsSurface:()Z
        //   787: ifeq            1161
        //   790: aload_0        
        //   791: aload           13
        //   793: invokevirtual   com/koushikdutta/virtualdisplay/EncoderDevice.setSurfaceFormat:(Landroid/media/MediaFormat;)V
        //   796: aload_0        
        //   797: ldc             "video/avc"
        //   799: invokestatic    android/media/MediaCodec.createEncoderByType:(Ljava/lang/String;)Landroid/media/MediaCodec;
        //   802: putfield        com/koushikdutta/virtualdisplay/EncoderDevice.mMediaCodec:Landroid/media/MediaCodec;
        //   805: aload_0        
        //   806: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.LOGTAG:Ljava/lang/String;
        //   809: ldc_w           "Created encoder"
        //   812: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   815: pop            
        //   816: aload_0        
        //   817: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.LOGTAG:Ljava/lang/String;
        //   820: ldc_w           "Configuring encoder"
        //   823: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   826: pop            
        //   827: aload_0        
        //   828: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mMediaCodec:Landroid/media/MediaCodec;
        //   831: aload           13
        //   833: aconst_null    
        //   834: aconst_null    
        //   835: iconst_1       
        //   836: invokevirtual   android/media/MediaCodec.configure:(Landroid/media/MediaFormat;Landroid/view/Surface;Landroid/media/MediaCrypto;I)V
        //   839: aload_0        
        //   840: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.LOGTAG:Ljava/lang/String;
        //   843: ldc_w           "Creating input surface"
        //   846: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   849: pop            
        //   850: getstatic       android/os/Build$VERSION.SDK_INT:I
        //   853: istore          27
        //   855: aconst_null    
        //   856: astore          28
        //   858: iload           27
        //   860: bipush          18
        //   862: if_icmplt       888
        //   865: aload_0        
        //   866: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.useSurface:Z
        //   869: istore          34
        //   871: aconst_null    
        //   872: astore          28
        //   874: iload           34
        //   876: ifeq            888
        //   879: aload_0        
        //   880: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mMediaCodec:Landroid/media/MediaCodec;
        //   883: invokevirtual   android/media/MediaCodec.createInputSurface:()Landroid/view/Surface;
        //   886: astore          28
        //   888: aload_0        
        //   889: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.LOGTAG:Ljava/lang/String;
        //   892: ldc_w           "Starting Encoder"
        //   895: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   898: pop            
        //   899: aload_0        
        //   900: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mMediaCodec:Landroid/media/MediaCodec;
        //   903: invokevirtual   android/media/MediaCodec.start:()V
        //   906: aload_0        
        //   907: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.LOGTAG:Ljava/lang/String;
        //   910: ldc_w           "Surface ready"
        //   913: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   916: pop            
        //   917: aload_0        
        //   918: aload_0        
        //   919: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mMediaCodec:Landroid/media/MediaCodec;
        //   922: invokevirtual   com/koushikdutta/virtualdisplay/EncoderDevice.onSurfaceCreated:(Landroid/media/MediaCodec;)Lcom/koushikdutta/virtualdisplay/EncoderDevice$EncoderRunnable;
        //   925: astore          31
        //   927: new             Ljava/lang/Thread;
        //   930: dup            
        //   931: aload           31
        //   933: ldc_w           "Encoder"
        //   936: invokespecial   java/lang/Thread.<init>:(Ljava/lang/Runnable;Ljava/lang/String;)V
        //   939: astore          32
        //   941: aload_0        
        //   942: aload           32
        //   944: putfield        com/koushikdutta/virtualdisplay/EncoderDevice.lastRecorderThread:Ljava/lang/Thread;
        //   947: aload_0        
        //   948: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.lastRecorderThread:Ljava/lang/Thread;
        //   951: invokevirtual   java/lang/Thread.start:()V
        //   954: aload_0        
        //   955: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.LOGTAG:Ljava/lang/String;
        //   958: ldc_w           "Encoder ready"
        //   961: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   964: pop            
        //   965: aload           28
        //   967: areturn        
        //   968: aload           52
        //   970: iconst_0       
        //   971: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   974: checkcast       Lcom/koushikdutta/virtualdisplay/EncoderDevice$VideoEncoderCap;
        //   977: astore          55
        //   979: aload           55
        //   981: getfield        com/koushikdutta/virtualdisplay/EncoderDevice$VideoEncoderCap.maxFrameWidth:I
        //   984: istore          8
        //   986: aload           55
        //   988: getfield        com/koushikdutta/virtualdisplay/EncoderDevice$VideoEncoderCap.maxFrameHeight:I
        //   991: istore          9
        //   993: aload           55
        //   995: getfield        com/koushikdutta/virtualdisplay/EncoderDevice$VideoEncoderCap.maxBitRate:I
        //   998: istore          10
        //  1000: aload           55
        //  1002: getfield        com/koushikdutta/virtualdisplay/EncoderDevice$VideoEncoderCap.maxFrameRate:I
        //  1005: istore          11
        //  1007: goto            421
        //  1010: astore          5
        //  1012: aload_0        
        //  1013: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.LOGTAG:Ljava/lang/String;
        //  1016: ldc_w           "Error getting camcorder profiles"
        //  1019: aload           5
        //  1021: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //  1024: pop            
        //  1025: aconst_null    
        //  1026: astore          7
        //  1028: goto            383
        //  1031: astore          45
        //  1033: aload_0        
        //  1034: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.LOGTAG:Ljava/lang/String;
        //  1037: ldc_w           "Error getting camcorder profiles"
        //  1040: aload           45
        //  1042: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //  1045: pop            
        //  1046: goto            398
        //  1049: aload           7
        //  1051: getfield        android/media/CamcorderProfile.videoFrameWidth:I
        //  1054: istore          8
        //  1056: aload           7
        //  1058: getfield        android/media/CamcorderProfile.videoFrameHeight:I
        //  1061: istore          9
        //  1063: aload           7
        //  1065: getfield        android/media/CamcorderProfile.videoBitRate:I
        //  1068: istore          10
        //  1070: aload           7
        //  1072: getfield        android/media/CamcorderProfile.videoFrameRate:I
        //  1075: istore          11
        //  1077: goto            421
        //  1080: aload_0        
        //  1081: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mHeight:I
        //  1084: iload           35
        //  1086: if_icmple       1119
        //  1089: iload           35
        //  1091: i2d            
        //  1092: aload_0        
        //  1093: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mHeight:I
        //  1096: i2d            
        //  1097: ddiv           
        //  1098: dstore          39
        //  1100: aload_0        
        //  1101: iload           35
        //  1103: putfield        com/koushikdutta/virtualdisplay/EncoderDevice.mHeight:I
        //  1106: aload_0        
        //  1107: dload           39
        //  1109: aload_0        
        //  1110: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mWidth:I
        //  1113: i2d            
        //  1114: dmul           
        //  1115: d2i            
        //  1116: putfield        com/koushikdutta/virtualdisplay/EncoderDevice.mWidth:I
        //  1119: aload_0        
        //  1120: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mWidth:I
        //  1123: iload           36
        //  1125: if_icmple       535
        //  1128: iload           36
        //  1130: i2d            
        //  1131: aload_0        
        //  1132: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mWidth:I
        //  1135: i2d            
        //  1136: ddiv           
        //  1137: dstore          37
        //  1139: aload_0        
        //  1140: iload           36
        //  1142: putfield        com/koushikdutta/virtualdisplay/EncoderDevice.mWidth:I
        //  1145: aload_0        
        //  1146: dload           37
        //  1148: aload_0        
        //  1149: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.mHeight:I
        //  1152: i2d            
        //  1153: dmul           
        //  1154: d2i            
        //  1155: putfield        com/koushikdutta/virtualdisplay/EncoderDevice.mHeight:I
        //  1158: goto            535
        //  1161: aload_0        
        //  1162: aload_1        
        //  1163: ldc             "video/avc"
        //  1165: invokespecial   com/koushikdutta/virtualdisplay/EncoderDevice.selectColorFormat:(Landroid/media/MediaCodecInfo;Ljava/lang/String;)I
        //  1168: istore          21
        //  1170: aload_0        
        //  1171: iload           21
        //  1173: putfield        com/koushikdutta/virtualdisplay/EncoderDevice.colorFormat:I
        //  1176: aload           13
        //  1178: ldc_w           "color-format"
        //  1181: iload           21
        //  1183: invokevirtual   android/media/MediaFormat.setInteger:(Ljava/lang/String;I)V
        //  1186: goto            796
        //  1189: astore          18
        //  1191: aload_0        
        //  1192: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.LOGTAG:Ljava/lang/String;
        //  1195: ldc_w           "Exception creating mMediaCodec"
        //  1198: aload           18
        //  1200: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //  1203: pop            
        //  1204: new             Ljava/lang/AssertionError;
        //  1207: dup            
        //  1208: aload           18
        //  1210: invokespecial   java/lang/AssertionError.<init>:(Ljava/lang/Object;)V
        //  1213: astore          20
        //  1215: aload           20
        //  1217: athrow         
        //  1218: astore          22
        //  1220: aload_0        
        //  1221: getfield        com/koushikdutta/virtualdisplay/EncoderDevice.LOGTAG:Ljava/lang/String;
        //  1224: ldc_w           "Unable to create codec by type, attempting explicit creation"
        //  1227: aload           22
        //  1229: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //  1232: pop            
        //  1233: aload_0        
        //  1234: aload_1        
        //  1235: invokevirtual   android/media/MediaCodecInfo.getName:()Ljava/lang/String;
        //  1238: invokestatic    android/media/MediaCodec.createByCodecName:(Ljava/lang/String;)Landroid/media/MediaCodec;
        //  1241: putfield        com/koushikdutta/virtualdisplay/EncoderDevice.mMediaCodec:Landroid/media/MediaCodec;
        //  1244: goto            805
        //  1247: iinc            57, 1
        //  1250: goto            27
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  19     252    264    265    Ljava/lang/Exception;
        //  265    360    360    1080   Ljava/lang/Exception;
        //  372    379    1010   1031   Ljava/lang/Exception;
        //  388    394    1031   1049   Ljava/lang/Exception;
        //  783    796    1189   1218   Ljava/lang/Exception;
        //  796    805    1218   1247   Ljava/lang/Exception;
        //  968    1007   360    1080   Ljava/lang/Exception;
        //  1161   1186   1189   1218   Ljava/lang/Exception;
        //  1220   1244   1189   1218   Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 589, Size: 589
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
    
    @TargetApi(18)
    Surface createInputSurface() {
        return this.venc.createInputSurface();
    }
    
    void destroyDisplaySurface(final MediaCodec mediaCodec) {
        if (mediaCodec != null) {
            while (true) {
                try {
                    mediaCodec.stop();
                    mediaCodec.release();
                    if (this.venc == mediaCodec) {
                        this.venc = null;
                        if (this.virtualDisplay != null) {
                            this.virtualDisplay.release();
                            this.virtualDisplay = null;
                        }
                        if (this.vdf != null) {
                            this.vdf.release();
                            this.vdf = null;
                        }
                    }
                }
                catch (Exception ex) {
                    continue;
                }
                break;
            }
        }
    }
    
    public int getBitrate(final int n) {
        return 2000000;
    }
    
    public int getColorFormat() {
        return this.colorFormat;
    }
    
    public Point getEncodingDimensions() {
        return this.encSize;
    }
    
    public MediaCodec getMediaCodec() {
        return this.venc;
    }
    
    public boolean isConnected() {
        return this.venc != null;
    }
    
    public void joinRecorderThread() {
        try {
            if (this.lastRecorderThread != null) {
                this.lastRecorderThread.join();
            }
        }
        catch (InterruptedException ex) {}
    }
    
    protected abstract EncoderRunnable onSurfaceCreated(final MediaCodec p0);
    
    public void registerVirtualDisplay(final Context context, final VirtualDisplayFactory vdf, final int n) {
        assert this.virtualDisplay == null;
        final Surface displaySurface = this.createDisplaySurface();
        if (displaySurface == null) {
            Log.e(this.LOGTAG, "Unable to create surface");
        }
        else {
            Log.e(this.LOGTAG, "Created surface");
            this.vdf = vdf;
            this.virtualDisplay = vdf.createVirtualDisplay(this.name, this.width, this.height, n, 3, displaySurface, null);
        }
    }
    
    @TargetApi(18)
    void setSurfaceFormat(final MediaFormat mediaFormat) {
        mediaFormat.setInteger("color-format", this.colorFormat = 2130708361);
    }
    
    public void setUseEncodingConstraints(final boolean useEncodingConstraints) {
        this.useEncodingConstraints = useEncodingConstraints;
    }
    
    @TargetApi(18)
    void signalEnd() {
        if (this.venc == null) {
            return;
        }
        try {
            this.venc.signalEndOfInputStream();
        }
        catch (Exception ex) {}
    }
    
    public void stop() {
        if (Build$VERSION.SDK_INT >= 18) {
            this.signalEnd();
        }
        this.venc = null;
        if (this.virtualDisplay != null) {
            this.virtualDisplay.release();
            this.virtualDisplay = null;
        }
        if (this.vdf != null) {
            this.vdf.release();
            this.vdf = null;
        }
    }
    
    public boolean supportsSurface() {
        return Build$VERSION.SDK_INT >= 19 && this.useSurface;
    }
    
    public void useSurface(final boolean useSurface) {
        this.useSurface = useSurface;
    }
    
    protected abstract class EncoderRunnable implements Runnable
    {
        MediaCodec venc;
        
        public EncoderRunnable(final MediaCodec venc) {
            this.venc = venc;
        }
        
        protected void cleanup() {
            EncoderDevice.this.destroyDisplaySurface(this.venc);
            this.venc = null;
        }
        
        protected abstract void encode() throws Exception;
        
        @Override
        public final void run() {
            while (true) {
                try {
                    this.encode();
                    this.cleanup();
                    Log.i(EncoderDevice.this.LOGTAG, "=======ENCODING COMPELTE=======");
                }
                catch (Exception ex) {
                    Log.e(EncoderDevice.this.LOGTAG, "Encoder error", (Throwable)ex);
                    continue;
                }
                break;
            }
        }
    }
    
    private static class VideoEncoderCap
    {
        int maxBitRate;
        int maxFrameHeight;
        int maxFrameRate;
        int maxFrameWidth;
        
        public VideoEncoderCap(final Attributes attributes) {
            this.maxFrameWidth = Integer.valueOf(attributes.getValue("maxFrameWidth"));
            this.maxFrameHeight = Integer.valueOf(attributes.getValue("maxFrameHeight"));
            this.maxBitRate = Integer.valueOf(attributes.getValue("maxBitRate"));
            this.maxFrameRate = Integer.valueOf(attributes.getValue("maxFrameRate"));
        }
    }
}
