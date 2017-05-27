// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import android.os.Debug$MemoryInfo;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import android.graphics.Color;
import com.google.android.gms.ads.internal.client.SearchAdRequestParcel;
import java.util.Date;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import android.location.Location;
import android.os.Bundle;
import java.util.HashMap;
import java.util.LinkedList;
import android.support.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.safebrowsing.SafeBrowsingConfigParcel;
import com.google.android.gms.ads.internal.request.AutoClickProtectionConfigurationParcel;
import java.util.List;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzu;
import org.json.JSONObject;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import android.content.Context;
import java.util.Locale;
import java.text.SimpleDateFormat;

@zziy
public final class zzjc
{
    private static final SimpleDateFormat zzcjy;
    
    static {
        zzcjy = new SimpleDateFormat("yyyyMMdd", Locale.US);
    }
    
    public static AdResponseParcel zza(final Context context, final AdRequestInfoParcel adRequestInfoParcel, final String s) {
        AdResponseParcel adResponseParcel = null;
    Label_0142_Outer:
        while (true) {
            while (true) {
            Label_0830:
                while (true) {
                    JSONObject jsonObject;
                    String s2;
                    String optString;
                    String optString2;
                    String optString3;
                    boolean b;
                    String s3 = null;
                    long zzchg;
                    String optString4;
                    String optString5;
                    long n = 0L;
                    String optString6;
                    int n2;
                    AdResponseParcel zza = null;
                    String body;
                    JSONArray optJSONArray;
                    List<String> list;
                    JSONArray optJSONArray2;
                    List<String> list2;
                    JSONArray optJSONArray3;
                    List<String> list3;
                    long zzchb;
                    String optString7;
                    boolean optBoolean;
                    String optString8;
                    String value;
                    String concat;
                    Label_0314_Outer:Label_0344_Outer:Label_0396_Outer:
                    while (true) {
                        Label_0811: {
                            while (true) {
                                Label_0804: {
                                    while (true) {
                                    Label_0789:
                                        while (true) {
                                        Label_0779:
                                            while (true) {
                                            Label_0769:
                                                while (true) {
                                                    try {
                                                        jsonObject = new JSONObject(s);
                                                        s2 = jsonObject.optString("ad_base_url", (String)null);
                                                        optString = jsonObject.optString("ad_url", (String)null);
                                                        optString2 = jsonObject.optString("ad_size", (String)null);
                                                        optString3 = jsonObject.optString("ad_slot_size", optString2);
                                                        if (adRequestInfoParcel == null || adRequestInfoParcel.zzcga == 0) {
                                                            break Label_0314_Outer;
                                                        }
                                                        b = true;
                                                        s3 = jsonObject.optString("ad_json", (String)null);
                                                        if (s3 == null) {
                                                            s3 = jsonObject.optString("ad_html", (String)null);
                                                        }
                                                        if (s3 == null) {
                                                            s3 = jsonObject.optString("body", (String)null);
                                                        }
                                                        zzchg = -1L;
                                                        optString4 = jsonObject.optString("debug_dialog", (String)null);
                                                        optString5 = jsonObject.optString("debug_signals", (String)null);
                                                        if (!jsonObject.has("interstitial_timeout")) {
                                                            break Label_0830;
                                                        }
                                                        n = (long)(1000.0 * jsonObject.getDouble("interstitial_timeout"));
                                                        optString6 = jsonObject.optString("orientation", (String)null);
                                                        n2 = -1;
                                                        if ("portrait".equals(optString6)) {
                                                            n2 = zzu.zzgb().zzun();
                                                        }
                                                        else if ("landscape".equals(optString6)) {
                                                            n2 = zzu.zzgb().zzum();
                                                        }
                                                        if (!TextUtils.isEmpty((CharSequence)s3) || TextUtils.isEmpty((CharSequence)optString)) {
                                                            break Label_0811;
                                                        }
                                                        zza = zzjb.zza(adRequestInfoParcel, context, adRequestInfoParcel.zzaqv.zzcs, optString, null, null, null, null);
                                                        s2 = zza.zzbyj;
                                                        body = zza.body;
                                                        zzchg = zza.zzchg;
                                                        if (body == null) {
                                                            adResponseParcel = new AdResponseParcel(0);
                                                            break;
                                                        }
                                                        optJSONArray = jsonObject.optJSONArray("click_urls");
                                                        if (zza == null) {
                                                            list = null;
                                                            if (optJSONArray != null) {
                                                                list = zza(optJSONArray, list);
                                                            }
                                                            optJSONArray2 = jsonObject.optJSONArray("impression_urls");
                                                            if (zza != null) {
                                                                break Label_0769;
                                                            }
                                                            list2 = null;
                                                            if (optJSONArray2 != null) {
                                                                list2 = zza(optJSONArray2, list2);
                                                            }
                                                            optJSONArray3 = jsonObject.optJSONArray("manual_impression_urls");
                                                            if (zza != null) {
                                                                break Label_0779;
                                                            }
                                                            list3 = null;
                                                            if (optJSONArray3 != null) {
                                                                list3 = zza(optJSONArray3, list3);
                                                            }
                                                            if (zza == null) {
                                                                break Label_0804;
                                                            }
                                                            if (zza.orientation != -1) {
                                                                n2 = zza.orientation;
                                                            }
                                                            if (zza.zzchb > 0L) {
                                                                zzchb = zza.zzchb;
                                                                optString7 = jsonObject.optString("active_view");
                                                                optBoolean = jsonObject.optBoolean("ad_is_javascript", false);
                                                                optString8 = null;
                                                                if (optBoolean) {
                                                                    optString8 = jsonObject.optString("ad_passback_url", (String)null);
                                                                }
                                                                adResponseParcel = new AdResponseParcel(adRequestInfoParcel, s2, body, list, list2, zzchb, jsonObject.optBoolean("mediation", false), jsonObject.optLong("mediation_config_cache_time_milliseconds", -1L), list3, jsonObject.optLong("refresh_interval_milliseconds", -1L), n2, optString2, zzchg, optString4, optBoolean, optString8, optString7, jsonObject.optBoolean("custom_render_allowed", false), b, adRequestInfoParcel.zzcgc, jsonObject.optBoolean("content_url_opted_out", true), jsonObject.optBoolean("prefetch", false), jsonObject.optString("gws_query_id", ""), "mHeight".equals(jsonObject.optString("fluid", "")), jsonObject.optBoolean("native_express", false), RewardItemParcel.zza(jsonObject.optJSONArray("rewards")), zza(jsonObject.optJSONArray("video_start_urls"), null), zza(jsonObject.optJSONArray("video_complete_urls"), null), jsonObject.optBoolean("use_displayed_impression", false), AutoClickProtectionConfigurationParcel.zzi(jsonObject.optJSONObject("auto_protection_configuration")), adRequestInfoParcel.zzcgt, jsonObject.optString("set_cookie", ""), zza(jsonObject.optJSONArray("remote_ping_urls"), null), jsonObject.optBoolean("render_in_browser", adRequestInfoParcel.zzbsh), optString3, SafeBrowsingConfigParcel.zzk(jsonObject.optJSONObject("safe_browsing")), optString5);
                                                                break;
                                                            }
                                                            break Label_0804;
                                                        }
                                                    }
                                                    catch (JSONException ex) {
                                                        value = String.valueOf(ex.getMessage());
                                                        if (value.length() != 0) {
                                                            concat = "Could not parse the inline ad response: ".concat(value);
                                                            zzb.zzdf(concat);
                                                            adResponseParcel = new AdResponseParcel(0);
                                                            break;
                                                        }
                                                        break Label_0789;
                                                    }
                                                    list = zza.zzbsd;
                                                    continue Label_0314_Outer;
                                                }
                                                list2 = zza.zzbse;
                                                continue Label_0344_Outer;
                                            }
                                            list3 = zza.zzche;
                                            continue Label_0396_Outer;
                                        }
                                        concat = new String("Could not parse the inline ad response: ");
                                        continue;
                                    }
                                }
                                zzchb = n;
                                continue;
                            }
                        }
                        body = s3;
                        zza = null;
                        continue;
                    }
                    b = false;
                    continue Label_0142_Outer;
                }
                long n = -1L;
                continue;
            }
        }
        return adResponseParcel;
    }
    
    @Nullable
    private static List<String> zza(@Nullable final JSONArray jsonArray, @Nullable List<String> list) throws JSONException {
        if (jsonArray == null) {
            list = null;
        }
        else {
            if (list == null) {
                list = new LinkedList<String>();
            }
            for (int i = 0; i < jsonArray.length(); ++i) {
                list.add(jsonArray.getString(i));
            }
        }
        return list;
    }
    
    @Nullable
    public static JSONObject zza(final Context p0, final zziz p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        com/google/android/gms/internal/zziz.zzcix:Lcom/google/android/gms/ads/internal/request/AdRequestInfoParcel;
        //     4: astore_2       
        //     5: aload_1        
        //     6: getfield        com/google/android/gms/internal/zziz.zzawl:Landroid/location/Location;
        //     9: astore_3       
        //    10: aload_1        
        //    11: getfield        com/google/android/gms/internal/zziz.zzciy:Lcom/google/android/gms/internal/zzjh;
        //    14: astore          4
        //    16: aload_1        
        //    17: getfield        com/google/android/gms/internal/zziz.zzcgb:Landroid/os/Bundle;
        //    20: astore          5
        //    22: aload_1        
        //    23: getfield        com/google/android/gms/internal/zziz.zzciz:Lorg/json/JSONObject;
        //    26: astore          6
        //    28: new             Ljava/util/HashMap;
        //    31: dup            
        //    32: invokespecial   java/util/HashMap.<init>:()V
        //    35: astore          7
        //    37: aload           7
        //    39: ldc_w           "extra_caps"
        //    42: getstatic       com/google/android/gms/internal/zzdi.zzbfr:Lcom/google/android/gms/internal/zzde;
        //    45: invokevirtual   com/google/android/gms/internal/zzde.get:()Ljava/lang/Object;
        //    48: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    51: pop            
        //    52: aload_1        
        //    53: getfield        com/google/android/gms/internal/zziz.zzcgk:Ljava/util/List;
        //    56: invokeinterface java/util/List.size:()I
        //    61: ifle            83
        //    64: aload           7
        //    66: ldc_w           "eid"
        //    69: ldc_w           ","
        //    72: aload_1        
        //    73: getfield        com/google/android/gms/internal/zziz.zzcgk:Ljava/util/List;
        //    76: invokestatic    android/text/TextUtils.join:(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;
        //    79: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    82: pop            
        //    83: aload_2        
        //    84: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcft:Landroid/os/Bundle;
        //    87: ifnull          103
        //    90: aload           7
        //    92: ldc_w           "ad_pos"
        //    95: aload_2        
        //    96: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcft:Landroid/os/Bundle;
        //    99: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   102: pop            
        //   103: aload           7
        //   105: aload_2        
        //   106: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcfu:Lcom/google/android/gms/ads/internal/client/AdRequestParcel;
        //   109: invokestatic    com/google/android/gms/internal/zzjc.zza:(Ljava/util/HashMap;Lcom/google/android/gms/ads/internal/client/AdRequestParcel;)V
        //   112: aload_2        
        //   113: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzaqz:Lcom/google/android/gms/ads/internal/client/AdSizeParcel;
        //   116: getfield        com/google/android/gms/ads/internal/client/AdSizeParcel.zzaxk:[Lcom/google/android/gms/ads/internal/client/AdSizeParcel;
        //   119: ifnonnull       272
        //   122: aload           7
        //   124: ldc_w           "format"
        //   127: aload_2        
        //   128: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzaqz:Lcom/google/android/gms/ads/internal/client/AdSizeParcel;
        //   131: getfield        com/google/android/gms/ads/internal/client/AdSizeParcel.zzaxi:Ljava/lang/String;
        //   134: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   137: pop            
        //   138: aload_2        
        //   139: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzaqz:Lcom/google/android/gms/ads/internal/client/AdSizeParcel;
        //   142: getfield        com/google/android/gms/ads/internal/client/AdSizeParcel.zzaxm:Z
        //   145: ifeq            158
        //   148: aload           7
        //   150: ldc             "fluid"
        //   152: ldc             "mHeight"
        //   154: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   157: pop            
        //   158: aload_2        
        //   159: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzaqz:Lcom/google/android/gms/ads/internal/client/AdSizeParcel;
        //   162: getfield        com/google/android/gms/ads/internal/client/AdSizeParcel.mWidth:I
        //   165: iconst_m1      
        //   166: if_icmpne       181
        //   169: aload           7
        //   171: ldc_w           "smart_w"
        //   174: ldc_w           "full"
        //   177: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   180: pop            
        //   181: aload_2        
        //   182: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzaqz:Lcom/google/android/gms/ads/internal/client/AdSizeParcel;
        //   185: getfield        com/google/android/gms/ads/internal/client/AdSizeParcel.mHeight:I
        //   188: bipush          -2
        //   190: if_icmpne       205
        //   193: aload           7
        //   195: ldc_w           "smart_h"
        //   198: ldc_w           "auto"
        //   201: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   204: pop            
        //   205: aload_2        
        //   206: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzaqz:Lcom/google/android/gms/ads/internal/client/AdSizeParcel;
        //   209: getfield        com/google/android/gms/ads/internal/client/AdSizeParcel.zzaxk:[Lcom/google/android/gms/ads/internal/client/AdSizeParcel;
        //   212: ifnull          567
        //   215: new             Ljava/lang/StringBuilder;
        //   218: dup            
        //   219: invokespecial   java/lang/StringBuilder.<init>:()V
        //   222: astore          19
        //   224: aload_2        
        //   225: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzaqz:Lcom/google/android/gms/ads/internal/client/AdSizeParcel;
        //   228: getfield        com/google/android/gms/ads/internal/client/AdSizeParcel.zzaxk:[Lcom/google/android/gms/ads/internal/client/AdSizeParcel;
        //   231: astore          20
        //   233: aload           20
        //   235: arraylength    
        //   236: istore          21
        //   238: iconst_0       
        //   239: istore          22
        //   241: iconst_0       
        //   242: istore          23
        //   244: iload           22
        //   246: iload           21
        //   248: if_icmpge       523
        //   251: aload           20
        //   253: iload           22
        //   255: aaload         
        //   256: astore          72
        //   258: aload           72
        //   260: getfield        com/google/android/gms/ads/internal/client/AdSizeParcel.zzaxm:Z
        //   263: ifeq            368
        //   266: iconst_1       
        //   267: istore          23
        //   269: goto            1585
        //   272: aload_2        
        //   273: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzaqz:Lcom/google/android/gms/ads/internal/client/AdSizeParcel;
        //   276: getfield        com/google/android/gms/ads/internal/client/AdSizeParcel.zzaxk:[Lcom/google/android/gms/ads/internal/client/AdSizeParcel;
        //   279: astore          13
        //   281: aload           13
        //   283: arraylength    
        //   284: istore          14
        //   286: iconst_0       
        //   287: istore          15
        //   289: iconst_0       
        //   290: istore          16
        //   292: iconst_0       
        //   293: istore          17
        //   295: iload           17
        //   297: iload           14
        //   299: if_icmpge       158
        //   302: aload           13
        //   304: iload           17
        //   306: aaload         
        //   307: astore          18
        //   309: aload           18
        //   311: getfield        com/google/android/gms/ads/internal/client/AdSizeParcel.zzaxm:Z
        //   314: ifne            339
        //   317: iload           16
        //   319: ifne            339
        //   322: aload           7
        //   324: ldc_w           "format"
        //   327: aload           18
        //   329: getfield        com/google/android/gms/ads/internal/client/AdSizeParcel.zzaxi:Ljava/lang/String;
        //   332: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   335: pop            
        //   336: iconst_1       
        //   337: istore          16
        //   339: aload           18
        //   341: getfield        com/google/android/gms/ads/internal/client/AdSizeParcel.zzaxm:Z
        //   344: ifeq            1591
        //   347: iload           15
        //   349: ifne            1591
        //   352: aload           7
        //   354: ldc             "fluid"
        //   356: ldc             "mHeight"
        //   358: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   361: pop            
        //   362: iconst_1       
        //   363: istore          15
        //   365: goto            1591
        //   368: aload           19
        //   370: invokevirtual   java/lang/StringBuilder.length:()I
        //   373: ifeq            385
        //   376: aload           19
        //   378: ldc_w           "|"
        //   381: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   384: pop            
        //   385: aload           72
        //   387: getfield        com/google/android/gms/ads/internal/client/AdSizeParcel.mWidth:I
        //   390: iconst_m1      
        //   391: if_icmpne       503
        //   394: aload           72
        //   396: getfield        com/google/android/gms/ads/internal/client/AdSizeParcel.widthPixels:I
        //   399: i2f            
        //   400: aload           4
        //   402: getfield        com/google/android/gms/internal/zzjh.zzcgg:F
        //   405: fdiv           
        //   406: f2i            
        //   407: istore          73
        //   409: aload           19
        //   411: iload           73
        //   413: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   416: pop            
        //   417: aload           19
        //   419: ldc_w           "x"
        //   422: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   425: pop            
        //   426: aload           72
        //   428: getfield        com/google/android/gms/ads/internal/client/AdSizeParcel.mHeight:I
        //   431: bipush          -2
        //   433: if_icmpne       513
        //   436: aload           72
        //   438: getfield        com/google/android/gms/ads/internal/client/AdSizeParcel.heightPixels:I
        //   441: i2f            
        //   442: aload           4
        //   444: getfield        com/google/android/gms/internal/zzjh.zzcgg:F
        //   447: fdiv           
        //   448: f2i            
        //   449: istore          76
        //   451: aload           19
        //   453: iload           76
        //   455: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   458: pop            
        //   459: goto            1585
        //   462: astore          8
        //   464: aload           8
        //   466: invokevirtual   org/json/JSONException.getMessage:()Ljava/lang/String;
        //   469: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   472: astore          9
        //   474: aload           9
        //   476: invokevirtual   java/lang/String.length:()I
        //   479: ifeq            1570
        //   482: ldc_w           "Problem serializing ad request to JSON: "
        //   485: aload           9
        //   487: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   490: astore          10
        //   492: aload           10
        //   494: invokestatic    com/google/android/gms/internal/zzkn.zzdf:(Ljava/lang/String;)V
        //   497: aconst_null    
        //   498: astore          11
        //   500: aload           11
        //   502: areturn        
        //   503: aload           72
        //   505: getfield        com/google/android/gms/ads/internal/client/AdSizeParcel.mWidth:I
        //   508: istore          73
        //   510: goto            409
        //   513: aload           72
        //   515: getfield        com/google/android/gms/ads/internal/client/AdSizeParcel.mHeight:I
        //   518: istore          76
        //   520: goto            451
        //   523: iload           23
        //   525: ifeq            556
        //   528: aload           19
        //   530: invokevirtual   java/lang/StringBuilder.length:()I
        //   533: ifeq            546
        //   536: aload           19
        //   538: iconst_0       
        //   539: ldc_w           "|"
        //   542: invokevirtual   java/lang/StringBuilder.insert:(ILjava/lang/String;)Ljava/lang/StringBuilder;
        //   545: pop            
        //   546: aload           19
        //   548: iconst_0       
        //   549: ldc_w           "320x50"
        //   552: invokevirtual   java/lang/StringBuilder.insert:(ILjava/lang/String;)Ljava/lang/StringBuilder;
        //   555: pop            
        //   556: aload           7
        //   558: ldc_w           "sz"
        //   561: aload           19
        //   563: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   566: pop            
        //   567: aload_2        
        //   568: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcga:I
        //   571: ifeq            644
        //   574: aload           7
        //   576: ldc_w           "native_version"
        //   579: aload_2        
        //   580: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcga:I
        //   583: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   586: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   589: pop            
        //   590: aload           7
        //   592: ldc_w           "native_templates"
        //   595: aload_2        
        //   596: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzarr:Ljava/util/List;
        //   599: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   602: pop            
        //   603: aload           7
        //   605: ldc_w           "native_image_orientation"
        //   608: aload_2        
        //   609: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzarn:Lcom/google/android/gms/ads/internal/formats/NativeAdOptionsParcel;
        //   612: invokestatic    com/google/android/gms/internal/zzjc.zzc:(Lcom/google/android/gms/ads/internal/formats/NativeAdOptionsParcel;)Ljava/lang/String;
        //   615: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   618: pop            
        //   619: aload_2        
        //   620: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgl:Ljava/util/List;
        //   623: invokeinterface java/util/List.isEmpty:()Z
        //   628: ifne            644
        //   631: aload           7
        //   633: ldc_w           "native_custom_templates"
        //   636: aload_2        
        //   637: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgl:Ljava/util/List;
        //   640: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   643: pop            
        //   644: aload_2        
        //   645: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzaqz:Lcom/google/android/gms/ads/internal/client/AdSizeParcel;
        //   648: getfield        com/google/android/gms/ads/internal/client/AdSizeParcel.zzaxn:Z
        //   651: ifeq            667
        //   654: aload           7
        //   656: ldc_w           "ene"
        //   659: iconst_1       
        //   660: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   663: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   666: pop            
        //   667: aload           7
        //   669: ldc_w           "slotname"
        //   672: aload_2        
        //   673: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzaqt:Ljava/lang/String;
        //   676: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   679: pop            
        //   680: aload           7
        //   682: ldc_w           "pn"
        //   685: aload_2        
        //   686: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.applicationInfo:Landroid/content/pm/ApplicationInfo;
        //   689: getfield        android/content/pm/ApplicationInfo.packageName:Ljava/lang/String;
        //   692: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   695: pop            
        //   696: aload_2        
        //   697: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcfv:Landroid/content/pm/PackageInfo;
        //   700: ifnull          722
        //   703: aload           7
        //   705: ldc_w           "vc"
        //   708: aload_2        
        //   709: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcfv:Landroid/content/pm/PackageInfo;
        //   712: getfield        android/content/pm/PackageInfo.versionCode:I
        //   715: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   718: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   721: pop            
        //   722: aload           7
        //   724: ldc_w           "ms"
        //   727: aload_1        
        //   728: getfield        com/google/android/gms/internal/zziz.zzcfw:Ljava/lang/String;
        //   731: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   734: pop            
        //   735: aload           7
        //   737: ldc_w           "seq_num"
        //   740: aload_2        
        //   741: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcfx:Ljava/lang/String;
        //   744: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   747: pop            
        //   748: aload           7
        //   750: ldc_w           "session_id"
        //   753: aload_2        
        //   754: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcfy:Ljava/lang/String;
        //   757: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   760: pop            
        //   761: aload           7
        //   763: ldc_w           "js"
        //   766: aload_2        
        //   767: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzaqv:Lcom/google/android/gms/ads/internal/util/client/VersionInfoParcel;
        //   770: getfield        com/google/android/gms/ads/internal/util/client/VersionInfoParcel.zzcs:Ljava/lang/String;
        //   773: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   776: pop            
        //   777: aload           7
        //   779: aload           4
        //   781: aload_1        
        //   782: getfield        com/google/android/gms/internal/zziz.zzciv:Lcom/google/android/gms/internal/zzjl$zza;
        //   785: aload_2        
        //   786: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgy:Landroid/os/Bundle;
        //   789: aload_1        
        //   790: getfield        com/google/android/gms/internal/zziz.zzciu:Landroid/os/Bundle;
        //   793: invokestatic    com/google/android/gms/internal/zzjc.zza:(Ljava/util/HashMap;Lcom/google/android/gms/internal/zzjh;Lcom/google/android/gms/internal/zzjl$zza;Landroid/os/Bundle;Landroid/os/Bundle;)V
        //   796: aload           7
        //   798: aload_1        
        //   799: getfield        com/google/android/gms/internal/zziz.zzciw:Ljava/lang/String;
        //   802: invokestatic    com/google/android/gms/internal/zzjc.zza:(Ljava/util/HashMap;Ljava/lang/String;)V
        //   805: aload           7
        //   807: ldc_w           "platform"
        //   810: getstatic       android/os/Build.MANUFACTURER:Ljava/lang/String;
        //   813: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   816: pop            
        //   817: aload           7
        //   819: ldc_w           "submodel"
        //   822: getstatic       android/os/Build.MODEL:Ljava/lang/String;
        //   825: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   828: pop            
        //   829: aload_3        
        //   830: ifnull          1430
        //   833: aload           7
        //   835: aload_3        
        //   836: invokestatic    com/google/android/gms/internal/zzjc.zza:(Ljava/util/HashMap;Landroid/location/Location;)V
        //   839: aload_2        
        //   840: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.versionCode:I
        //   843: iconst_2       
        //   844: if_icmplt       860
        //   847: aload           7
        //   849: ldc_w           "quality_signals"
        //   852: aload_2        
        //   853: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcfz:Landroid/os/Bundle;
        //   856: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   859: pop            
        //   860: aload_2        
        //   861: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.versionCode:I
        //   864: iconst_4       
        //   865: if_icmplt       891
        //   868: aload_2        
        //   869: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgc:Z
        //   872: ifeq            891
        //   875: aload           7
        //   877: ldc_w           "forceHttps"
        //   880: aload_2        
        //   881: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgc:Z
        //   884: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   887: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   890: pop            
        //   891: aload           5
        //   893: ifnull          907
        //   896: aload           7
        //   898: ldc_w           "content_info"
        //   901: aload           5
        //   903: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   906: pop            
        //   907: aload_2        
        //   908: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.versionCode:I
        //   911: iconst_5       
        //   912: if_icmplt       1466
        //   915: aload           7
        //   917: ldc_w           "u_sd"
        //   920: aload_2        
        //   921: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgg:F
        //   924: invokestatic    java/lang/Float.valueOf:(F)Ljava/lang/Float;
        //   927: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   930: pop            
        //   931: aload           7
        //   933: ldc_w           "sh"
        //   936: aload_2        
        //   937: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgf:I
        //   940: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   943: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   946: pop            
        //   947: aload           7
        //   949: ldc_w           "sw"
        //   952: aload_2        
        //   953: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcge:I
        //   956: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   959: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   962: pop            
        //   963: aload_2        
        //   964: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.versionCode:I
        //   967: bipush          6
        //   969: if_icmplt       1022
        //   972: aload_2        
        //   973: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgh:Ljava/lang/String;
        //   976: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   979: istore          54
        //   981: iload           54
        //   983: ifne            1006
        //   986: aload           7
        //   988: ldc_w           "view_hierarchy"
        //   991: new             Lorg/json/JSONObject;
        //   994: dup            
        //   995: aload_2        
        //   996: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgh:Ljava/lang/String;
        //   999: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //  1002: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1005: pop            
        //  1006: aload           7
        //  1008: ldc_w           "correlation_id"
        //  1011: aload_2        
        //  1012: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgi:J
        //  1015: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //  1018: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1021: pop            
        //  1022: aload_2        
        //  1023: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.versionCode:I
        //  1026: bipush          7
        //  1028: if_icmplt       1044
        //  1031: aload           7
        //  1033: ldc_w           "request_id"
        //  1036: aload_2        
        //  1037: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgj:Ljava/lang/String;
        //  1040: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1043: pop            
        //  1044: aload_2        
        //  1045: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.versionCode:I
        //  1048: bipush          11
        //  1050: if_icmplt       1076
        //  1053: aload_2        
        //  1054: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgn:Lcom/google/android/gms/ads/internal/request/CapabilityParcel;
        //  1057: ifnull          1076
        //  1060: aload           7
        //  1062: ldc_w           "capability"
        //  1065: aload_2        
        //  1066: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgn:Lcom/google/android/gms/ads/internal/request/CapabilityParcel;
        //  1069: invokevirtual   com/google/android/gms/ads/internal/request/CapabilityParcel.toBundle:()Landroid/os/Bundle;
        //  1072: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1075: pop            
        //  1076: aload_2        
        //  1077: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.versionCode:I
        //  1080: bipush          12
        //  1082: if_icmplt       1108
        //  1085: aload_2        
        //  1086: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgo:Ljava/lang/String;
        //  1089: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //  1092: ifne            1108
        //  1095: aload           7
        //  1097: ldc_w           "anchor"
        //  1100: aload_2        
        //  1101: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgo:Ljava/lang/String;
        //  1104: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1107: pop            
        //  1108: aload_2        
        //  1109: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.versionCode:I
        //  1112: bipush          13
        //  1114: if_icmplt       1133
        //  1117: aload           7
        //  1119: ldc_w           "android_app_volume"
        //  1122: aload_2        
        //  1123: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgp:F
        //  1126: invokestatic    java/lang/Float.valueOf:(F)Ljava/lang/Float;
        //  1129: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1132: pop            
        //  1133: aload_2        
        //  1134: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.versionCode:I
        //  1137: bipush          18
        //  1139: if_icmplt       1158
        //  1142: aload           7
        //  1144: ldc_w           "android_app_muted"
        //  1147: aload_2        
        //  1148: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgv:Z
        //  1151: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1154: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1157: pop            
        //  1158: aload_2        
        //  1159: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.versionCode:I
        //  1162: bipush          14
        //  1164: if_icmplt       1190
        //  1167: aload_2        
        //  1168: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgq:I
        //  1171: ifle            1190
        //  1174: aload           7
        //  1176: ldc_w           "target_api"
        //  1179: aload_2        
        //  1180: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgq:I
        //  1183: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1186: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1189: pop            
        //  1190: aload_2        
        //  1191: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.versionCode:I
        //  1194: bipush          15
        //  1196: if_icmplt       1224
        //  1199: aload_2        
        //  1200: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgr:I
        //  1203: iconst_m1      
        //  1204: if_icmpne       1533
        //  1207: iconst_m1      
        //  1208: istore          46
        //  1210: aload           7
        //  1212: ldc_w           "scroll_index"
        //  1215: iload           46
        //  1217: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1220: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1223: pop            
        //  1224: aload_2        
        //  1225: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.versionCode:I
        //  1228: bipush          16
        //  1230: if_icmplt       1249
        //  1233: aload           7
        //  1235: ldc_w           "_activity_context"
        //  1238: aload_2        
        //  1239: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgs:Z
        //  1242: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1245: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1248: pop            
        //  1249: aload_2        
        //  1250: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.versionCode:I
        //  1253: bipush          18
        //  1255: if_icmplt       1307
        //  1258: aload_2        
        //  1259: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgw:Ljava/lang/String;
        //  1262: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //  1265: istore          41
        //  1267: iload           41
        //  1269: ifne            1292
        //  1272: aload           7
        //  1274: ldc_w           "app_settings"
        //  1277: new             Lorg/json/JSONObject;
        //  1280: dup            
        //  1281: aload_2        
        //  1282: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgw:Ljava/lang/String;
        //  1285: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //  1288: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1291: pop            
        //  1292: aload           7
        //  1294: ldc             "render_in_browser"
        //  1296: aload_2        
        //  1297: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzbsh:Z
        //  1300: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1303: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1306: pop            
        //  1307: aload_2        
        //  1308: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.versionCode:I
        //  1311: bipush          18
        //  1313: if_icmplt       1332
        //  1316: aload           7
        //  1318: ldc_w           "android_num_video_cache_tasks"
        //  1321: aload_2        
        //  1322: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgx:I
        //  1325: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1328: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1331: pop            
        //  1332: aload           7
        //  1334: invokestatic    com/google/android/gms/internal/zzjc.zza:(Ljava/util/HashMap;)V
        //  1337: aload           7
        //  1339: ldc_w           "cache_state"
        //  1342: aload           6
        //  1344: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1347: pop            
        //  1348: aload_2        
        //  1349: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.versionCode:I
        //  1352: bipush          19
        //  1354: if_icmplt       1370
        //  1357: aload           7
        //  1359: ldc_w           "gct"
        //  1362: aload_2        
        //  1363: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgz:Ljava/lang/String;
        //  1366: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1369: pop            
        //  1370: iconst_2       
        //  1371: invokestatic    com/google/android/gms/internal/zzkn.zzbf:(I)Z
        //  1374: ifeq            1417
        //  1377: invokestatic    com/google/android/gms/ads/internal/zzu.zzfz:()Lcom/google/android/gms/internal/zzkr;
        //  1380: aload           7
        //  1382: invokevirtual   com/google/android/gms/internal/zzkr.zzan:(Ljava/util/Map;)Lorg/json/JSONObject;
        //  1385: iconst_2       
        //  1386: invokevirtual   org/json/JSONObject.toString:(I)Ljava/lang/String;
        //  1389: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1392: astore          37
        //  1394: aload           37
        //  1396: invokevirtual   java/lang/String.length:()I
        //  1399: ifeq            1555
        //  1402: ldc_w           "Ad Request JSON: "
        //  1405: aload           37
        //  1407: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //  1410: astore          38
        //  1412: aload           38
        //  1414: invokestatic    com/google/android/gms/internal/zzkn.v:(Ljava/lang/String;)V
        //  1417: invokestatic    com/google/android/gms/ads/internal/zzu.zzfz:()Lcom/google/android/gms/internal/zzkr;
        //  1420: aload           7
        //  1422: invokevirtual   com/google/android/gms/internal/zzkr.zzan:(Ljava/util/Map;)Lorg/json/JSONObject;
        //  1425: astore          11
        //  1427: goto            500
        //  1430: aload_2        
        //  1431: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcfu:Lcom/google/android/gms/ads/internal/client/AdRequestParcel;
        //  1434: getfield        com/google/android/gms/ads/internal/client/AdRequestParcel.versionCode:I
        //  1437: iconst_2       
        //  1438: if_icmplt       839
        //  1441: aload_2        
        //  1442: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcfu:Lcom/google/android/gms/ads/internal/client/AdRequestParcel;
        //  1445: getfield        com/google/android/gms/ads/internal/client/AdRequestParcel.zzawl:Landroid/location/Location;
        //  1448: ifnull          839
        //  1451: aload           7
        //  1453: aload_2        
        //  1454: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcfu:Lcom/google/android/gms/ads/internal/client/AdRequestParcel;
        //  1457: getfield        com/google/android/gms/ads/internal/client/AdRequestParcel.zzawl:Landroid/location/Location;
        //  1460: invokestatic    com/google/android/gms/internal/zzjc.zza:(Ljava/util/HashMap;Landroid/location/Location;)V
        //  1463: goto            839
        //  1466: aload           7
        //  1468: ldc_w           "u_sd"
        //  1471: aload           4
        //  1473: getfield        com/google/android/gms/internal/zzjh.zzcgg:F
        //  1476: invokestatic    java/lang/Float.valueOf:(F)Ljava/lang/Float;
        //  1479: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1482: pop            
        //  1483: aload           7
        //  1485: ldc_w           "sh"
        //  1488: aload           4
        //  1490: getfield        com/google/android/gms/internal/zzjh.zzcgf:I
        //  1493: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1496: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1499: pop            
        //  1500: aload           7
        //  1502: ldc_w           "sw"
        //  1505: aload           4
        //  1507: getfield        com/google/android/gms/internal/zzjh.zzcge:I
        //  1510: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1513: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1516: pop            
        //  1517: goto            963
        //  1520: astore          56
        //  1522: ldc_w           "Problem serializing view hierarchy to JSON"
        //  1525: aload           56
        //  1527: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //  1530: goto            1006
        //  1533: aload_2        
        //  1534: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgr:I
        //  1537: istore          46
        //  1539: goto            1210
        //  1542: astore          43
        //  1544: ldc_w           "Problem creating json from app settings"
        //  1547: aload           43
        //  1549: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //  1552: goto            1292
        //  1555: new             Ljava/lang/String;
        //  1558: dup            
        //  1559: ldc_w           "Ad Request JSON: "
        //  1562: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //  1565: astore          38
        //  1567: goto            1412
        //  1570: new             Ljava/lang/String;
        //  1573: dup            
        //  1574: ldc_w           "Problem serializing ad request to JSON: "
        //  1577: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //  1580: astore          10
        //  1582: goto            492
        //  1585: iinc            22, 1
        //  1588: goto            244
        //  1591: iload           16
        //  1593: ifeq            1601
        //  1596: iload           15
        //  1598: ifne            158
        //  1601: iinc            17, 1
        //  1604: goto            295
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  28     459    462    500    Lorg/json/JSONException;
        //  503    981    462    500    Lorg/json/JSONException;
        //  986    1006   1520   1533   Lorg/json/JSONException;
        //  1006   1267   462    500    Lorg/json/JSONException;
        //  1272   1292   1542   1555   Lorg/json/JSONException;
        //  1292   1567   462    500    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_1292:
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
    
    private static void zza(final HashMap<String, Object> hashMap) {
        final Bundle bundle = new Bundle();
        final Bundle bundle2 = new Bundle();
        bundle2.putString("cl", "134102376");
        bundle2.putString("rapid_rc", "dev");
        bundle2.putString("rapid_rollup", "HEAD");
        bundle.putBundle("build_meta", bundle2);
        bundle.putString("mf", Boolean.toString(zzdi.zzbft.get()));
        hashMap.put("sdk_env", bundle);
    }
    
    private static void zza(final HashMap<String, Object> hashMap, final Location location) {
        final HashMap<String, Float> hashMap2 = new HashMap<String, Float>();
        final Float value = 1000.0f * location.getAccuracy();
        final Long value2 = 1000L * location.getTime();
        final Long value3 = (long)(1.0E7 * location.getLatitude());
        final Long value4 = (long)(1.0E7 * location.getLongitude());
        hashMap2.put("radius", value);
        hashMap2.put("lat", (Float)value3);
        hashMap2.put("long", (Float)value4);
        hashMap2.put("time", (Float)value2);
        hashMap.put("uule", hashMap2);
    }
    
    private static void zza(final HashMap<String, Object> hashMap, final AdRequestParcel adRequestParcel) {
        final String zzub = zzkl.zzub();
        if (zzub != null) {
            hashMap.put("abf", zzub);
        }
        if (adRequestParcel.zzawd != -1L) {
            hashMap.put("cust_age", zzjc.zzcjy.format(new Date(adRequestParcel.zzawd)));
        }
        if (adRequestParcel.extras != null) {
            hashMap.put("extras", adRequestParcel.extras);
        }
        if (adRequestParcel.zzawe != -1) {
            hashMap.put("cust_gender", adRequestParcel.zzawe);
        }
        if (adRequestParcel.zzawf != null) {
            hashMap.put("kw", adRequestParcel.zzawf);
        }
        if (adRequestParcel.zzawh != -1) {
            hashMap.put("tag_for_child_directed_treatment", adRequestParcel.zzawh);
        }
        if (adRequestParcel.zzawg) {
            hashMap.put("adtest", "on");
        }
        if (adRequestParcel.versionCode >= 2) {
            if (adRequestParcel.zzawi) {
                hashMap.put("d_imp_hdr", 1);
            }
            if (!TextUtils.isEmpty((CharSequence)adRequestParcel.zzawj)) {
                hashMap.put("ppid", adRequestParcel.zzawj);
            }
            if (adRequestParcel.zzawk != null) {
                zza(hashMap, adRequestParcel.zzawk);
            }
        }
        if (adRequestParcel.versionCode >= 3 && adRequestParcel.zzawm != null) {
            hashMap.put("url", adRequestParcel.zzawm);
        }
        if (adRequestParcel.versionCode >= 5) {
            if (adRequestParcel.zzawo != null) {
                hashMap.put("custom_targeting", adRequestParcel.zzawo);
            }
            if (adRequestParcel.zzawp != null) {
                hashMap.put("category_exclusions", adRequestParcel.zzawp);
            }
            if (adRequestParcel.zzawq != null) {
                hashMap.put("request_agent", adRequestParcel.zzawq);
            }
        }
        if (adRequestParcel.versionCode >= 6 && adRequestParcel.zzawr != null) {
            hashMap.put("request_pkg", adRequestParcel.zzawr);
        }
        if (adRequestParcel.versionCode >= 7) {
            hashMap.put("is_designed_for_families", adRequestParcel.zzaws);
        }
    }
    
    private static void zza(final HashMap<String, Object> hashMap, final SearchAdRequestParcel searchAdRequestParcel) {
        if (Color.alpha(searchAdRequestParcel.zzazp) != 0) {
            hashMap.put("acolor", zzaw(searchAdRequestParcel.zzazp));
        }
        if (Color.alpha(searchAdRequestParcel.backgroundColor) != 0) {
            hashMap.put("bgcolor", zzaw(searchAdRequestParcel.backgroundColor));
        }
        if (Color.alpha(searchAdRequestParcel.zzazq) != 0 && Color.alpha(searchAdRequestParcel.zzazr) != 0) {
            hashMap.put("gradientto", zzaw(searchAdRequestParcel.zzazq));
            hashMap.put("gradientfrom", zzaw(searchAdRequestParcel.zzazr));
        }
        if (Color.alpha(searchAdRequestParcel.zzazs) != 0) {
            hashMap.put("bcolor", zzaw(searchAdRequestParcel.zzazs));
        }
        hashMap.put("bthick", Integer.toString(searchAdRequestParcel.zzazt));
        String s = null;
        switch (searchAdRequestParcel.zzazu) {
            default: {
                s = null;
                break;
            }
            case 0: {
                s = "none";
                break;
            }
            case 1: {
                s = "dashed";
                break;
            }
            case 2: {
                s = "dotted";
                break;
            }
            case 3: {
                s = "solid";
                break;
            }
        }
        if (s != null) {
            hashMap.put("btype", s);
        }
        final int zzazv = searchAdRequestParcel.zzazv;
        String s2 = null;
        switch (zzazv) {
            case 2: {
                s2 = "dark";
                break;
            }
            case 0: {
                s2 = "light";
                break;
            }
            case 1: {
                s2 = "medium";
                break;
            }
        }
        if (s2 != null) {
            hashMap.put("callbuttoncolor", s2);
        }
        if (searchAdRequestParcel.zzazw != null) {
            hashMap.put("channel", searchAdRequestParcel.zzazw);
        }
        if (Color.alpha(searchAdRequestParcel.zzazx) != 0) {
            hashMap.put("dcolor", zzaw(searchAdRequestParcel.zzazx));
        }
        if (searchAdRequestParcel.zzazy != null) {
            hashMap.put("font", searchAdRequestParcel.zzazy);
        }
        if (Color.alpha(searchAdRequestParcel.zzazz) != 0) {
            hashMap.put("hcolor", zzaw(searchAdRequestParcel.zzazz));
        }
        hashMap.put("headersize", Integer.toString(searchAdRequestParcel.zzbaa));
        if (searchAdRequestParcel.zzbab != null) {
            hashMap.put("q", searchAdRequestParcel.zzbab);
        }
    }
    
    private static void zza(final HashMap<String, Object> hashMap, final zzjh zzjh, final zzjl.zza zza, final Bundle bundle, final Bundle bundle2) {
        hashMap.put("am", zzjh.zzclr);
        hashMap.put("cog", zzac(zzjh.zzcls));
        hashMap.put("coh", zzac(zzjh.zzclt));
        if (!TextUtils.isEmpty((CharSequence)zzjh.zzclu)) {
            hashMap.put("carrier", zzjh.zzclu);
        }
        hashMap.put("gl", zzjh.zzclv);
        if (zzjh.zzclw) {
            hashMap.put("simulator", 1);
        }
        if (zzjh.zzclx) {
            hashMap.put("is_sidewinder", 1);
        }
        hashMap.put("ma", zzac(zzjh.zzcly));
        hashMap.put("sp", zzac(zzjh.zzclz));
        hashMap.put("hl", zzjh.zzcma);
        if (!TextUtils.isEmpty((CharSequence)zzjh.zzcmb)) {
            hashMap.put("mv", zzjh.zzcmb);
        }
        hashMap.put("muv", zzjh.zzcmc);
        if (zzjh.zzcmd != -2) {
            hashMap.put("cnt", zzjh.zzcmd);
        }
        hashMap.put("gnt", zzjh.zzcme);
        hashMap.put("pt", zzjh.zzcmf);
        hashMap.put("rm", zzjh.zzcmg);
        hashMap.put("riv", zzjh.zzcmh);
        final Bundle bundle3 = new Bundle();
        bundle3.putString("build", zzjh.zzcmm);
        final Bundle bundle4 = new Bundle();
        bundle4.putBoolean("is_charging", zzjh.zzcmj);
        bundle4.putDouble("battery_level", zzjh.zzcmi);
        bundle3.putBundle("battery", bundle4);
        final Bundle bundle5 = new Bundle();
        bundle5.putInt("active_network_state", zzjh.zzcml);
        bundle5.putBoolean("active_network_metered", zzjh.zzcmk);
        if (zza != null) {
            final Bundle bundle6 = new Bundle();
            bundle6.putInt("predicted_latency_micros", 0);
            bundle6.putLong("predicted_down_throughput_bps", 0L);
            bundle6.putLong("predicted_up_throughput_bps", 0L);
            bundle5.putBundle("predictions", bundle6);
        }
        bundle3.putBundle("network", bundle5);
        final Bundle bundle7 = new Bundle();
        bundle7.putBoolean("is_browser_custom_tabs_capable", zzjh.zzcmn);
        bundle3.putBundle("browser", bundle7);
        if (bundle != null) {
            bundle3.putBundle("android_mem_info", zzg(bundle));
        }
        final Bundle bundle8 = new Bundle();
        bundle8.putBundle("parental_controls", bundle2);
        bundle3.putBundle("play_store", bundle8);
        hashMap.put("device", bundle3);
    }
    
    private static void zza(final HashMap<String, Object> hashMap, final String s) {
        final Bundle bundle = new Bundle();
        bundle.putString("doritos", s);
        hashMap.put("pii", bundle);
    }
    
    private static Integer zzac(final boolean b) {
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        return n;
    }
    
    private static String zzaw(final int n) {
        return String.format(Locale.US, "#%06x", 0xFFFFFF & n);
    }
    
    private static String zzc(final NativeAdOptionsParcel nativeAdOptionsParcel) {
        int zzblc;
        if (nativeAdOptionsParcel != null) {
            zzblc = nativeAdOptionsParcel.zzblc;
        }
        else {
            zzblc = 0;
        }
        String s = null;
        switch (zzblc) {
            default: {
                s = "any";
                break;
            }
            case 1: {
                s = "portrait";
                break;
            }
            case 2: {
                s = "landscape";
                break;
            }
        }
        return s;
    }
    
    public static JSONObject zzc(final AdResponseParcel adResponseParcel) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        if (adResponseParcel.zzbyj != null) {
            jsonObject.put("ad_base_url", (Object)adResponseParcel.zzbyj);
        }
        if (adResponseParcel.zzchf != null) {
            jsonObject.put("ad_size", (Object)adResponseParcel.zzchf);
        }
        jsonObject.put("native", adResponseParcel.zzaxl);
        if (adResponseParcel.zzaxl) {
            jsonObject.put("ad_json", (Object)adResponseParcel.body);
        }
        else {
            jsonObject.put("ad_html", (Object)adResponseParcel.body);
        }
        if (adResponseParcel.zzchh != null) {
            jsonObject.put("debug_dialog", (Object)adResponseParcel.zzchh);
        }
        if (adResponseParcel.zzchy != null) {
            jsonObject.put("debug_signals", (Object)adResponseParcel.zzchy);
        }
        if (adResponseParcel.zzchb != -1L) {
            jsonObject.put("interstitial_timeout", adResponseParcel.zzchb / 1000.0);
        }
        if (adResponseParcel.orientation == zzu.zzgb().zzun()) {
            jsonObject.put("orientation", (Object)"portrait");
        }
        else if (adResponseParcel.orientation == zzu.zzgb().zzum()) {
            jsonObject.put("orientation", (Object)"landscape");
        }
        if (adResponseParcel.zzbsd != null) {
            jsonObject.put("click_urls", (Object)zzl(adResponseParcel.zzbsd));
        }
        if (adResponseParcel.zzbse != null) {
            jsonObject.put("impression_urls", (Object)zzl(adResponseParcel.zzbse));
        }
        if (adResponseParcel.zzche != null) {
            jsonObject.put("manual_impression_urls", (Object)zzl(adResponseParcel.zzche));
        }
        if (adResponseParcel.zzchk != null) {
            jsonObject.put("active_view", (Object)adResponseParcel.zzchk);
        }
        jsonObject.put("ad_is_javascript", adResponseParcel.zzchi);
        if (adResponseParcel.zzchj != null) {
            jsonObject.put("ad_passback_url", (Object)adResponseParcel.zzchj);
        }
        jsonObject.put("mediation", adResponseParcel.zzchc);
        jsonObject.put("custom_render_allowed", adResponseParcel.zzchl);
        jsonObject.put("content_url_opted_out", adResponseParcel.zzchm);
        jsonObject.put("prefetch", adResponseParcel.zzchn);
        if (adResponseParcel.zzbsj != -1L) {
            jsonObject.put("refresh_interval_milliseconds", adResponseParcel.zzbsj);
        }
        if (adResponseParcel.zzchd != -1L) {
            jsonObject.put("mediation_config_cache_time_milliseconds", adResponseParcel.zzchd);
        }
        if (!TextUtils.isEmpty((CharSequence)adResponseParcel.zzchq)) {
            jsonObject.put("gws_query_id", (Object)adResponseParcel.zzchq);
        }
        String s;
        if (adResponseParcel.zzaxm) {
            s = "mHeight";
        }
        else {
            s = "";
        }
        jsonObject.put("fluid", (Object)s);
        jsonObject.put("native_express", adResponseParcel.zzaxn);
        if (adResponseParcel.zzchs != null) {
            jsonObject.put("video_start_urls", (Object)zzl(adResponseParcel.zzchs));
        }
        if (adResponseParcel.zzcht != null) {
            jsonObject.put("video_complete_urls", (Object)zzl(adResponseParcel.zzcht));
        }
        if (adResponseParcel.zzchr != null) {
            jsonObject.put("rewards", (Object)adResponseParcel.zzchr.zzsx());
        }
        jsonObject.put("use_displayed_impression", adResponseParcel.zzchu);
        jsonObject.put("auto_protection_configuration", (Object)adResponseParcel.zzchv);
        jsonObject.put("render_in_browser", adResponseParcel.zzbsh);
        return jsonObject;
    }
    
    private static Bundle zzg(final Bundle bundle) {
        final Bundle bundle2 = new Bundle();
        bundle2.putString("runtime_free", Long.toString(bundle.getLong("runtime_free_memory", -1L)));
        bundle2.putString("runtime_max", Long.toString(bundle.getLong("runtime_max_memory", -1L)));
        bundle2.putString("runtime_total", Long.toString(bundle.getLong("runtime_total_memory", -1L)));
        final Debug$MemoryInfo debug$MemoryInfo = (Debug$MemoryInfo)bundle.getParcelable("debug_memory_info");
        if (debug$MemoryInfo != null) {
            bundle2.putString("debug_info_dalvik_private_dirty", Integer.toString(debug$MemoryInfo.dalvikPrivateDirty));
            bundle2.putString("debug_info_dalvik_pss", Integer.toString(debug$MemoryInfo.dalvikPss));
            bundle2.putString("debug_info_dalvik_shared_dirty", Integer.toString(debug$MemoryInfo.dalvikSharedDirty));
            bundle2.putString("debug_info_native_private_dirty", Integer.toString(debug$MemoryInfo.nativePrivateDirty));
            bundle2.putString("debug_info_native_pss", Integer.toString(debug$MemoryInfo.nativePss));
            bundle2.putString("debug_info_native_shared_dirty", Integer.toString(debug$MemoryInfo.nativeSharedDirty));
            bundle2.putString("debug_info_other_private_dirty", Integer.toString(debug$MemoryInfo.otherPrivateDirty));
            bundle2.putString("debug_info_other_pss", Integer.toString(debug$MemoryInfo.otherPss));
            bundle2.putString("debug_info_other_shared_dirty", Integer.toString(debug$MemoryInfo.otherSharedDirty));
        }
        return bundle2;
    }
    
    @Nullable
    static JSONArray zzl(final List<String> list) throws JSONException {
        final JSONArray jsonArray = new JSONArray();
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            jsonArray.put((Object)iterator.next());
        }
        return jsonArray;
    }
}
