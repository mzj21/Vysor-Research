// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.WindowManager;
import android.view.View;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.formats.zzg;
import com.google.android.gms.ads.internal.overlay.zzm;
import org.json.JSONObject;
import com.google.android.gms.ads.internal.overlay.zzd;
import java.util.concurrent.Future;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.net.Uri;
import java.util.HashMap;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.text.TextUtils;
import java.util.Map;

@zziy
public final class zzeu
{
    public static final zzev zzbmb;
    public static final zzev zzbmc;
    public static final zzev zzbmd;
    public static final zzev zzbme;
    public static final zzev zzbmf;
    public static final zzev zzbmg;
    public static final zzev zzbmh;
    public static final zzev zzbmi;
    public static final zzev zzbmj;
    public static final zzev zzbmk;
    public static final zzev zzbml;
    public static final zzev zzbmm;
    public static final zzev zzbmn;
    public static final zzev zzbmo;
    public static final zzev zzbmp;
    public static final zzev zzbmq;
    public static final zzev zzbmr;
    public static final zzfd zzbms;
    public static final zzev zzbmt;
    public static final zzev zzbmu;
    public static final zzev zzbmv;
    
    static {
        zzbmb = new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
            }
        };
        zzbmc = new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                final String s = map.get("urls");
                if (TextUtils.isEmpty((CharSequence)s)) {
                    zzb.zzdf("URLs missing in canOpenURLs GMSG.");
                }
                else {
                    final String[] split = s.split(",");
                    final HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
                    final PackageManager packageManager = zzlt.getContext().getPackageManager();
                    for (final String s2 : split) {
                        final String[] split2 = s2.split(";", 2);
                        final String trim = split2[0].trim();
                        String trim2;
                        if (split2.length > 1) {
                            trim2 = split2[1].trim();
                        }
                        else {
                            trim2 = "android.intent.action.VIEW";
                        }
                        hashMap.put(s2, packageManager.resolveActivity(new Intent(trim2, Uri.parse(trim)), 65536) != null);
                    }
                    zzlt.zza("openableURLs", hashMap);
                }
            }
        };
        zzbmd = new zzev() {
            @Override
            public void zza(final zzlt p0, final Map<String, String> p1) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_1        
                //     1: invokeinterface com/google/android/gms/internal/zzlt.getContext:()Landroid/content/Context;
                //     6: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
                //     9: astore_3       
                //    10: aload_2        
                //    11: ldc             "data"
                //    13: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
                //    18: checkcast       Ljava/lang/String;
                //    21: astore          4
                //    23: new             Lorg/json/JSONObject;
                //    26: dup            
                //    27: aload           4
                //    29: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
                //    32: astore          5
                //    34: aload           5
                //    36: ldc             "intents"
                //    38: invokevirtual   org/json/JSONObject.getJSONArray:(Ljava/lang/String;)Lorg/json/JSONArray;
                //    41: astore          7
                //    43: new             Lorg/json/JSONObject;
                //    46: dup            
                //    47: invokespecial   org/json/JSONObject.<init>:()V
                //    50: astore          8
                //    52: iconst_0       
                //    53: istore          9
                //    55: iload           9
                //    57: aload           7
                //    59: invokevirtual   org/json/JSONArray.length:()I
                //    62: if_icmpge       364
                //    65: aload           7
                //    67: iload           9
                //    69: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
                //    72: astore          11
                //    74: aload           11
                //    76: ldc             "id"
                //    78: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
                //    81: astore          12
                //    83: aload           11
                //    85: ldc             "u"
                //    87: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
                //    90: astore          13
                //    92: aload           11
                //    94: ldc             "i"
                //    96: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
                //    99: astore          14
                //   101: aload           11
                //   103: ldc             "m"
                //   105: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
                //   108: astore          15
                //   110: aload           11
                //   112: ldc             "p"
                //   114: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
                //   117: astore          16
                //   119: aload           11
                //   121: ldc             "c"
                //   123: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
                //   126: astore          17
                //   128: aload           11
                //   130: ldc             "f"
                //   132: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
                //   135: pop            
                //   136: aload           11
                //   138: ldc             "e"
                //   140: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
                //   143: pop            
                //   144: new             Landroid/content/Intent;
                //   147: dup            
                //   148: invokespecial   android/content/Intent.<init>:()V
                //   151: astore          20
                //   153: aload           13
                //   155: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
                //   158: ifne            172
                //   161: aload           20
                //   163: aload           13
                //   165: invokestatic    android/net/Uri.parse:(Ljava/lang/String;)Landroid/net/Uri;
                //   168: invokevirtual   android/content/Intent.setData:(Landroid/net/Uri;)Landroid/content/Intent;
                //   171: pop            
                //   172: aload           14
                //   174: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
                //   177: ifne            188
                //   180: aload           20
                //   182: aload           14
                //   184: invokevirtual   android/content/Intent.setAction:(Ljava/lang/String;)Landroid/content/Intent;
                //   187: pop            
                //   188: aload           15
                //   190: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
                //   193: ifne            204
                //   196: aload           20
                //   198: aload           15
                //   200: invokevirtual   android/content/Intent.setType:(Ljava/lang/String;)Landroid/content/Intent;
                //   203: pop            
                //   204: aload           16
                //   206: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
                //   209: ifne            220
                //   212: aload           20
                //   214: aload           16
                //   216: invokevirtual   android/content/Intent.setPackage:(Ljava/lang/String;)Landroid/content/Intent;
                //   219: pop            
                //   220: aload           17
                //   222: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
                //   225: ifne            266
                //   228: aload           17
                //   230: ldc             "/"
                //   232: iconst_2       
                //   233: invokevirtual   java/lang/String.split:(Ljava/lang/String;I)[Ljava/lang/String;
                //   236: astore          24
                //   238: aload           24
                //   240: arraylength    
                //   241: iconst_2       
                //   242: if_icmpne       266
                //   245: aload           20
                //   247: new             Landroid/content/ComponentName;
                //   250: dup            
                //   251: aload           24
                //   253: iconst_0       
                //   254: aaload         
                //   255: aload           24
                //   257: iconst_1       
                //   258: aaload         
                //   259: invokespecial   android/content/ComponentName.<init>:(Ljava/lang/String;Ljava/lang/String;)V
                //   262: invokevirtual   android/content/Intent.setComponent:(Landroid/content/ComponentName;)Landroid/content/Intent;
                //   265: pop            
                //   266: aload_3        
                //   267: aload           20
                //   269: ldc             65536
                //   271: invokevirtual   android/content/pm/PackageManager.resolveActivity:(Landroid/content/Intent;I)Landroid/content/pm/ResolveInfo;
                //   274: ifnull          346
                //   277: iconst_1       
                //   278: istore          21
                //   280: aload           8
                //   282: aload           12
                //   284: iload           21
                //   286: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
                //   289: pop            
                //   290: iinc            9, 1
                //   293: goto            55
                //   296: astore          30
                //   298: aload_1        
                //   299: ldc             "openableIntents"
                //   301: new             Lorg/json/JSONObject;
                //   304: dup            
                //   305: invokespecial   org/json/JSONObject.<init>:()V
                //   308: invokeinterface com/google/android/gms/internal/zzlt.zzb:(Ljava/lang/String;Lorg/json/JSONObject;)V
                //   313: return         
                //   314: astore          6
                //   316: aload_1        
                //   317: ldc             "openableIntents"
                //   319: new             Lorg/json/JSONObject;
                //   322: dup            
                //   323: invokespecial   org/json/JSONObject.<init>:()V
                //   326: invokeinterface com/google/android/gms/internal/zzlt.zzb:(Ljava/lang/String;Lorg/json/JSONObject;)V
                //   331: goto            313
                //   334: astore          10
                //   336: ldc             "Error parsing the intent data."
                //   338: aload           10
                //   340: invokestatic    com/google/android/gms/internal/zzkn.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
                //   343: goto            290
                //   346: iconst_0       
                //   347: istore          21
                //   349: goto            280
                //   352: astore          22
                //   354: ldc             "Error constructing openable urls response."
                //   356: aload           22
                //   358: invokestatic    com/google/android/gms/internal/zzkn.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
                //   361: goto            290
                //   364: aload_1        
                //   365: ldc             "openableIntents"
                //   367: aload           8
                //   369: invokeinterface com/google/android/gms/internal/zzlt.zzb:(Ljava/lang/String;Lorg/json/JSONObject;)V
                //   374: goto            313
                //    Signature:
                //  (Lcom/google/android/gms/internal/zzlt;Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;)V
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                    
                //  -----  -----  -----  -----  ------------------------
                //  23     34     296    313    Lorg/json/JSONException;
                //  34     43     314    334    Lorg/json/JSONException;
                //  65     74     334    346    Lorg/json/JSONException;
                //  280    290    352    364    Lorg/json/JSONException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IndexOutOfBoundsException: Index: 169, Size: 169
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
        };
        zzbme = new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                final String s = map.get("u");
                if (s == null) {
                    zzb.zzdf("URL missing from click GMSG.");
                }
                else {
                Label_0142_Outer:
                    while (true) {
                        final Uri parse = Uri.parse(s);
                        while (true) {
                        Label_0154:
                            while (true) {
                                try {
                                    final zzau zzvt = zzlt.zzvt();
                                    if (zzvt != null && zzvt.zzc(parse)) {
                                        final Uri zza = zzvt.zza(parse, zzlt.getContext(), zzlt.getView());
                                        final Future future = (Future)new zzlb(zzlt.getContext(), zzlt.zzvu().zzcs, zza.toString()).zzqw();
                                        break;
                                    }
                                }
                                catch (zzav zzav) {
                                    final String value = String.valueOf(s);
                                    if (value.length() == 0) {
                                        break Label_0154;
                                    }
                                    final String concat = "Unable to append parameter to URL: ".concat(value);
                                    zzb.zzdf(concat);
                                }
                                final Uri zza = parse;
                                continue Label_0142_Outer;
                            }
                            final String concat = new String("Unable to append parameter to URL: ");
                            continue;
                        }
                    }
                }
            }
        };
        zzbmf = new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                final zzd zzvp = zzlt.zzvp();
                if (zzvp != null) {
                    zzvp.close();
                }
                else {
                    final zzd zzvq = zzlt.zzvq();
                    if (zzvq != null) {
                        zzvq.close();
                    }
                    else {
                        zzb.zzdf("A GMSG tried to close something that wasn't an overlay.");
                    }
                }
            }
        };
        zzbmg = new zzev() {
            private void zzd(final zzlt zzlt) {
                zzb.zzde("Received support message, responding.");
                final com.google.android.gms.ads.internal.zzd zzdp = zzlt.zzdp();
                boolean zzr = false;
                if (zzdp != null) {
                    final zzm zzame = zzdp.zzame;
                    zzr = false;
                    if (zzame != null) {
                        zzr = zzame.zzr(zzlt.getContext());
                    }
                }
                final JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("event", (Object)"checkSupport");
                    jsonObject.put("supports", zzr);
                    zzlt.zzb("appStreaming", jsonObject);
                }
                catch (Throwable t) {}
            }
            
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                if ("checkSupport".equals(map.get("action"))) {
                    this.zzd(zzlt);
                }
                else {
                    final zzd zzvp = zzlt.zzvp();
                    if (zzvp != null) {
                        zzvp.zzf(zzlt, map);
                    }
                }
            }
        };
        zzbmh = new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                zzlt.zzak("1".equals(map.get("custom_close")));
            }
        };
        zzbmi = new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                final String s = map.get("u");
                if (s == null) {
                    zzb.zzdf("URL missing from httpTrack GMSG.");
                }
                else {
                    final Future future = (Future)new zzlb(zzlt.getContext(), zzlt.zzvu().zzcs, s).zzqw();
                }
            }
        };
        zzbmj = new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                final String value = String.valueOf(map.get("string"));
                String concat;
                if (value.length() != 0) {
                    concat = "Received log message: ".concat(value);
                }
                else {
                    concat = new String("Received log message: ");
                }
                zzb.zzde(concat);
            }
        };
        zzbmk = new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                final zzg zzwg = zzlt.zzwg();
                if (zzwg != null) {
                    zzwg.zzlu();
                }
            }
        };
        zzbml = new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                final String s = map.get("tx");
                final String s2 = map.get("ty");
                final String s3 = map.get("td");
                try {
                    final int int1 = Integer.parseInt(s);
                    final int int2 = Integer.parseInt(s2);
                    final int int3 = Integer.parseInt(s3);
                    final zzau zzvt = zzlt.zzvt();
                    if (zzvt != null) {
                        zzvt.zzaw().zza(int1, int2, int3);
                    }
                }
                catch (NumberFormatException ex) {
                    zzb.zzdf("Could not parse touch parameters from gmsg.");
                }
            }
        };
        zzbmm = new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                if (zzdi.zzbee.get()) {
                    zzlt.zzal(!Boolean.parseBoolean(map.get("disabled")));
                }
            }
        };
        zzbmn = new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                final String s = map.get("action");
                if ("pause".equals(s)) {
                    zzlt.zzel();
                }
                else if ("resume".equals(s)) {
                    zzlt.zzem();
                }
            }
        };
        zzbmo = new zzff();
        zzbmp = new zzfg();
        zzbmq = new zzfk();
        zzbmr = new zzet();
        zzbms = new zzfd();
        zzbmt = new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                if (map.keySet().contains("start")) {
                    zzlt.zzvr().zzwp();
                }
                else if (map.keySet().contains("stop")) {
                    zzlt.zzvr().zzwq();
                }
                else if (map.keySet().contains("cancel")) {
                    zzlt.zzvr().zzwr();
                }
            }
        };
        zzbmu = new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                if (map.keySet().contains("start")) {
                    zzlt.zzam(true);
                }
                if (map.keySet().contains("stop")) {
                    zzlt.zzam(false);
                }
            }
        };
        zzbmv = new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                zzlt.zza("locationReady", zzu.zzfz().zza((View)zzlt, (WindowManager)zzlt.getContext().getSystemService("window")));
                zzb.zzdf("GET LOCATION COMPILED");
            }
        };
    }
}
