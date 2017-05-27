// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.purchase;

import android.database.sqlite.SQLiteDatabase$CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.List;
import android.os.SystemClock;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import java.util.Locale;
import com.google.android.gms.internal.zziy;

@zziy
public class zzh
{
    private static final Object zzakd;
    private static final String zzccd;
    private static zzh zzccf;
    private final zza zzcce;
    
    static {
        zzccd = String.format(Locale.US, "CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER)", "InAppPurchase", "purchase_id", "product_id", "developer_payload", "record_time");
        zzakd = new Object();
    }
    
    zzh(final Context context) {
        this.zzcce = new zza(context, "google_inapp_purchase.db");
    }
    
    public static zzh zzs(final Context context) {
        synchronized (zzh.zzakd) {
            if (zzh.zzccf == null) {
                zzh.zzccf = new zzh(context);
            }
            return zzh.zzccf;
        }
    }
    
    public int getRecordCount() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_1       
        //     2: iconst_0       
        //     3: istore_2       
        //     4: getstatic       com/google/android/gms/ads/internal/purchase/zzh.zzakd:Ljava/lang/Object;
        //     7: astore_3       
        //     8: aload_3        
        //     9: monitorenter   
        //    10: aload_0        
        //    11: invokevirtual   com/google/android/gms/ads/internal/purchase/zzh.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    14: astore          5
        //    16: aload           5
        //    18: ifnonnull       25
        //    21: aload_3        
        //    22: monitorexit    
        //    23: iload_2        
        //    24: ireturn        
        //    25: aload           5
        //    27: ldc             "select count(*) from InAppPurchase"
        //    29: aconst_null    
        //    30: invokevirtual   android/database/sqlite/SQLiteDatabase.rawQuery:(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
        //    33: astore_1       
        //    34: aload_1        
        //    35: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    40: ifeq            77
        //    43: aload_1        
        //    44: iconst_0       
        //    45: invokeinterface android/database/Cursor.getInt:(I)I
        //    50: istore          10
        //    52: iload           10
        //    54: istore_2       
        //    55: aload_1        
        //    56: ifnull          65
        //    59: aload_1        
        //    60: invokeinterface android/database/Cursor.close:()V
        //    65: aload_3        
        //    66: monitorexit    
        //    67: goto            23
        //    70: astore          4
        //    72: aload_3        
        //    73: monitorexit    
        //    74: aload           4
        //    76: athrow         
        //    77: aload_1        
        //    78: ifnull          87
        //    81: aload_1        
        //    82: invokeinterface android/database/Cursor.close:()V
        //    87: aload_3        
        //    88: monitorexit    
        //    89: iconst_0       
        //    90: istore_2       
        //    91: goto            23
        //    94: astore          7
        //    96: aload           7
        //    98: invokevirtual   android/database/sqlite/SQLiteException.getMessage:()Ljava/lang/String;
        //   101: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   104: astore          8
        //   106: aload           8
        //   108: invokevirtual   java/lang/String.length:()I
        //   111: ifeq            141
        //   114: ldc             "Error getting record count"
        //   116: aload           8
        //   118: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   121: astore          9
        //   123: aload           9
        //   125: invokestatic    com/google/android/gms/internal/zzkn.zzdf:(Ljava/lang/String;)V
        //   128: aload_1        
        //   129: ifnull          87
        //   132: aload_1        
        //   133: invokeinterface android/database/Cursor.close:()V
        //   138: goto            87
        //   141: new             Ljava/lang/String;
        //   144: dup            
        //   145: ldc             "Error getting record count"
        //   147: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   150: astore          9
        //   152: goto            123
        //   155: astore          6
        //   157: aload_1        
        //   158: ifnull          167
        //   161: aload_1        
        //   162: invokeinterface android/database/Cursor.close:()V
        //   167: aload           6
        //   169: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  10     23     70     77     Any
        //  25     52     94     155    Landroid/database/sqlite/SQLiteException;
        //  25     52     155    170    Any
        //  59     74     70     77     Any
        //  81     89     70     77     Any
        //  96     128    155    170    Any
        //  132    138    70     77     Any
        //  141    152    155    170    Any
        //  161    170    70     77     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0025:
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
    
    public SQLiteDatabase getWritableDatabase() {
        try {
            return this.zzcce.getWritableDatabase();
        }
        catch (SQLiteException ex) {
            zzb.zzdf("Error opening writable conversion tracking database");
            return null;
        }
    }
    
    public zzf zza(final Cursor cursor) {
        zzf zzf;
        if (cursor == null) {
            zzf = null;
        }
        else {
            zzf = new zzf(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
        }
        return zzf;
    }
    
    public void zza(final zzf zzf) {
        if (zzf != null) {
            final SQLiteDatabase writableDatabase;
            synchronized (zzh.zzakd) {
                writableDatabase = this.getWritableDatabase();
                if (writableDatabase == null) {
                    return;
                }
            }
            writableDatabase.delete("InAppPurchase", String.format(Locale.US, "%s = %d", "purchase_id", zzf.zzcby), (String[])null);
        }
        // monitorexit(o)
    }
    
    public void zzb(final zzf zzf) {
        if (zzf != null) {
            final SQLiteDatabase writableDatabase;
            synchronized (zzh.zzakd) {
                writableDatabase = this.getWritableDatabase();
                if (writableDatabase == null) {
                    return;
                }
            }
            final ContentValues contentValues = new ContentValues();
            contentValues.put("product_id", zzf.zzcca);
            contentValues.put("developer_payload", zzf.zzcbz);
            contentValues.put("record_time", SystemClock.elapsedRealtime());
            zzf.zzcby = writableDatabase.insert("InAppPurchase", (String)null, contentValues);
            if (this.getRecordCount() > 20000L) {
                this.zzqr();
            }
        }
        // monitorexit(o)
    }
    
    public List<zzf> zzg(final long p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/google/android/gms/ads/internal/purchase/zzh.zzakd:Ljava/lang/Object;
        //     3: astore_3       
        //     4: aload_3        
        //     5: monitorenter   
        //     6: new             Ljava/util/LinkedList;
        //     9: dup            
        //    10: invokespecial   java/util/LinkedList.<init>:()V
        //    13: astore          4
        //    15: lload_1        
        //    16: lconst_0       
        //    17: lcmp           
        //    18: ifgt            30
        //    21: aload_3        
        //    22: monitorexit    
        //    23: aload           4
        //    25: astore          12
        //    27: goto            236
        //    30: aload_0        
        //    31: invokevirtual   com/google/android/gms/ads/internal/purchase/zzh.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    34: astore          6
        //    36: aload           6
        //    38: ifnonnull       50
        //    41: aload_3        
        //    42: monitorexit    
        //    43: aload           4
        //    45: astore          12
        //    47: goto            236
        //    50: aload           6
        //    52: ldc             "InAppPurchase"
        //    54: aconst_null    
        //    55: aconst_null    
        //    56: aconst_null    
        //    57: aconst_null    
        //    58: aconst_null    
        //    59: ldc             "record_time ASC"
        //    61: lload_1        
        //    62: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //    65: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    68: astore          13
        //    70: aload           13
        //    72: astore          8
        //    74: aload           8
        //    76: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    81: ifeq            112
        //    84: aload           4
        //    86: aload_0        
        //    87: aload           8
        //    89: invokevirtual   com/google/android/gms/ads/internal/purchase/zzh.zza:(Landroid/database/Cursor;)Lcom/google/android/gms/ads/internal/purchase/zzf;
        //    92: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    97: pop            
        //    98: aload           8
        //   100: invokeinterface android/database/Cursor.moveToNext:()Z
        //   105: istore          15
        //   107: iload           15
        //   109: ifne            84
        //   112: aload           8
        //   114: ifnull          124
        //   117: aload           8
        //   119: invokeinterface android/database/Cursor.close:()V
        //   124: aload_3        
        //   125: monitorexit    
        //   126: aload           4
        //   128: astore          12
        //   130: goto            236
        //   133: astore          9
        //   135: aconst_null    
        //   136: astore          8
        //   138: aload           9
        //   140: invokevirtual   android/database/sqlite/SQLiteException.getMessage:()Ljava/lang/String;
        //   143: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   146: astore          10
        //   148: aload           10
        //   150: invokevirtual   java/lang/String.length:()I
        //   153: ifeq            192
        //   156: ldc             "Error extracing purchase info: "
        //   158: aload           10
        //   160: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   163: astore          11
        //   165: aload           11
        //   167: invokestatic    com/google/android/gms/internal/zzkn.zzdf:(Ljava/lang/String;)V
        //   170: aload           8
        //   172: ifnull          124
        //   175: aload           8
        //   177: invokeinterface android/database/Cursor.close:()V
        //   182: goto            124
        //   185: astore          5
        //   187: aload_3        
        //   188: monitorexit    
        //   189: aload           5
        //   191: athrow         
        //   192: new             Ljava/lang/String;
        //   195: dup            
        //   196: ldc             "Error extracing purchase info: "
        //   198: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   201: astore          11
        //   203: goto            165
        //   206: astore          7
        //   208: aload           8
        //   210: ifnull          220
        //   213: aload           8
        //   215: invokeinterface android/database/Cursor.close:()V
        //   220: aload           7
        //   222: athrow         
        //   223: astore          7
        //   225: aconst_null    
        //   226: astore          8
        //   228: goto            208
        //   231: astore          9
        //   233: goto            138
        //   236: aload           12
        //   238: areturn        
        //    Signature:
        //  (J)Ljava/util/List<Lcom/google/android/gms/ads/internal/purchase/zzf;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  6      43     185    192    Any
        //  50     70     133    138    Landroid/database/sqlite/SQLiteException;
        //  50     70     223    231    Any
        //  74     107    231    236    Landroid/database/sqlite/SQLiteException;
        //  74     107    206    208    Any
        //  117    126    185    192    Any
        //  138    170    206    208    Any
        //  175    189    185    192    Any
        //  192    203    206    208    Any
        //  213    223    185    192    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0084:
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
    
    public void zzqr() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/google/android/gms/ads/internal/purchase/zzh.zzakd:Ljava/lang/Object;
        //     3: astore_1       
        //     4: aload_1        
        //     5: monitorenter   
        //     6: aload_0        
        //     7: invokevirtual   com/google/android/gms/ads/internal/purchase/zzh.getWritableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    10: astore_3       
        //    11: aload_3        
        //    12: ifnonnull       18
        //    15: aload_1        
        //    16: monitorexit    
        //    17: return         
        //    18: aload_3        
        //    19: ldc             "InAppPurchase"
        //    21: aconst_null    
        //    22: aconst_null    
        //    23: aconst_null    
        //    24: aconst_null    
        //    25: aconst_null    
        //    26: ldc             "record_time ASC"
        //    28: ldc             "1"
        //    30: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    33: astore          9
        //    35: aload           9
        //    37: astore          5
        //    39: aload           5
        //    41: ifnull          64
        //    44: aload           5
        //    46: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    51: ifeq            64
        //    54: aload_0        
        //    55: aload_0        
        //    56: aload           5
        //    58: invokevirtual   com/google/android/gms/ads/internal/purchase/zzh.zza:(Landroid/database/Cursor;)Lcom/google/android/gms/ads/internal/purchase/zzf;
        //    61: invokevirtual   com/google/android/gms/ads/internal/purchase/zzh.zza:(Lcom/google/android/gms/ads/internal/purchase/zzf;)V
        //    64: aload           5
        //    66: ifnull          76
        //    69: aload           5
        //    71: invokeinterface android/database/Cursor.close:()V
        //    76: aload_1        
        //    77: monitorexit    
        //    78: goto            17
        //    81: astore_2       
        //    82: aload_1        
        //    83: monitorexit    
        //    84: aload_2        
        //    85: athrow         
        //    86: astore          6
        //    88: aconst_null    
        //    89: astore          5
        //    91: aload           6
        //    93: invokevirtual   android/database/sqlite/SQLiteException.getMessage:()Ljava/lang/String;
        //    96: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    99: astore          7
        //   101: aload           7
        //   103: invokevirtual   java/lang/String.length:()I
        //   106: ifeq            138
        //   109: ldc             "Error remove oldest record"
        //   111: aload           7
        //   113: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   116: astore          8
        //   118: aload           8
        //   120: invokestatic    com/google/android/gms/internal/zzkn.zzdf:(Ljava/lang/String;)V
        //   123: aload           5
        //   125: ifnull          76
        //   128: aload           5
        //   130: invokeinterface android/database/Cursor.close:()V
        //   135: goto            76
        //   138: new             Ljava/lang/String;
        //   141: dup            
        //   142: ldc             "Error remove oldest record"
        //   144: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   147: astore          8
        //   149: goto            118
        //   152: astore          4
        //   154: aload           5
        //   156: ifnull          166
        //   159: aload           5
        //   161: invokeinterface android/database/Cursor.close:()V
        //   166: aload           4
        //   168: athrow         
        //   169: astore          4
        //   171: aconst_null    
        //   172: astore          5
        //   174: goto            154
        //   177: astore          6
        //   179: goto            91
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  6      17     81     86     Any
        //  18     35     86     91     Landroid/database/sqlite/SQLiteException;
        //  18     35     169    177    Any
        //  44     64     177    182    Landroid/database/sqlite/SQLiteException;
        //  44     64     152    154    Any
        //  69     84     81     86     Any
        //  91     123    152    154    Any
        //  128    135    81     86     Any
        //  138    149    152    154    Any
        //  159    169    81     86     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 87, Size: 87
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
    
    public class zza extends SQLiteOpenHelper
    {
        public zza(final Context context, final String s) {
            super(context, s, (SQLiteDatabase$CursorFactory)null, 4);
        }
        
        public void onCreate(final SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(zzh.zzccd);
        }
        
        public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
            zzb.zzde(new StringBuilder(64).append("Database updated from version ").append(n).append(" to version ").append(n2).toString());
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS InAppPurchase");
            this.onCreate(sqLiteDatabase);
        }
    }
}
