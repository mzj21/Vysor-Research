// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;
import com.google.android.gms.ads.internal.util.client.zzb;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Iterator;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.io.BufferedOutputStream;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzu;
import java.net.HttpURLConnection;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.content.Context;

@zziy
public class zzew implements zzev
{
    private final Context mContext;
    private final VersionInfoParcel zzanh;
    
    public zzew(final Context mContext, final VersionInfoParcel zzanh) {
        this.mContext = mContext;
        this.zzanh = zzanh;
    }
    
    protected zzc zza(final zzb zzb) {
        HttpURLConnection httpURLConnection = null;
        Label_0099: {
            zzc zzc;
            try {
                httpURLConnection = (HttpURLConnection)zzb.zzmd().openConnection();
                zzu.zzfz().zza(this.mContext, this.zzanh.zzcs, false, httpURLConnection);
                for (final zza zza : zzb.zzme()) {
                    httpURLConnection.addRequestProperty(zza.getKey(), zza.getValue());
                }
                break Label_0099;
            }
            catch (Exception ex) {
                zzc = new zzc(false, null, ex.toString());
            }
            return zzc;
        }
        if (!TextUtils.isEmpty((CharSequence)zzb.zzmf())) {
            httpURLConnection.setDoOutput(true);
            final byte[] bytes = zzb.zzmf().getBytes();
            httpURLConnection.setFixedLengthStreamingMode(bytes.length);
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.close();
        }
        final ArrayList<zza> list = new ArrayList<zza>();
        if (httpURLConnection.getHeaderFields() != null) {
            for (final Map.Entry<String, List<String>> entry : httpURLConnection.getHeaderFields().entrySet()) {
                final Iterator<String> iterator3 = entry.getValue().iterator();
                while (iterator3.hasNext()) {
                    list.add(new zza(entry.getKey(), iterator3.next()));
                }
            }
        }
        return new zzc(true, new zzd(zzb.zzmc(), httpURLConnection.getResponseCode(), list, zzu.zzfz().zza(new InputStreamReader(httpURLConnection.getInputStream()))), null);
    }
    
    protected JSONObject zza(final zzd zzd) {
        final JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = null;
        Label_0124: {
            try {
                jsonObject.put("http_request_id", (Object)zzd.zzmc());
                if (zzd.getBody() != null) {
                    jsonObject.put("body", (Object)zzd.getBody());
                }
                jsonArray = new JSONArray();
                for (final zza zza : zzd.zzmh()) {
                    jsonArray.put((Object)new JSONObject().put("key", (Object)zza.getKey()).put("value", (Object)zza.getValue()));
                }
                break Label_0124;
            }
            catch (JSONException ex) {
                com.google.android.gms.ads.internal.util.client.zzb.zzb("Error constructing JSON for http response.", (Throwable)ex);
            }
            return jsonObject;
        }
        jsonObject.put("headers", (Object)jsonArray);
        jsonObject.put("response_code", zzd.getResponseCode());
        return jsonObject;
    }
    
    @Override
    public void zza(final zzlt zzlt, final Map<String, String> map) {
        zzkq.zza(new Runnable() {
            @Override
            public void run() {
                com.google.android.gms.ads.internal.util.client.zzb.zzdd("Received Http request.");
                final JSONObject zzay = zzew.this.zzay(map.get("http_request"));
                if (zzay == null) {
                    com.google.android.gms.ads.internal.util.client.zzb.e("Response should not be null.");
                }
                else {
                    zzkr.zzcrf.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            zzlt.zzb("fetchHttpRequestCompleted", zzay);
                            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Dispatched http response.");
                        }
                    });
                }
            }
        });
    }
    
    public JSONObject zzay(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lorg/json/JSONObject;
        //     3: dup            
        //     4: aload_1        
        //     5: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //     8: astore_2       
        //     9: new             Lorg/json/JSONObject;
        //    12: dup            
        //    13: invokespecial   org/json/JSONObject.<init>:()V
        //    16: astore_3       
        //    17: ldc_w           ""
        //    20: astore          4
        //    22: aload_2        
        //    23: ldc             "http_request_id"
        //    25: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //    28: astore          4
        //    30: aload_0        
        //    31: aload_0        
        //    32: aload_2        
        //    33: invokevirtual   com/google/android/gms/internal/zzew.zzc:(Lorg/json/JSONObject;)Lcom/google/android/gms/internal/zzew$zzb;
        //    36: invokevirtual   com/google/android/gms/internal/zzew.zza:(Lcom/google/android/gms/internal/zzew$zzb;)Lcom/google/android/gms/internal/zzew$zzc;
        //    39: astore          10
        //    41: aload           10
        //    43: invokevirtual   com/google/android/gms/internal/zzew$zzc.isSuccess:()Z
        //    46: ifeq            120
        //    49: aload_3        
        //    50: ldc_w           "response"
        //    53: aload_0        
        //    54: aload           10
        //    56: invokevirtual   com/google/android/gms/internal/zzew$zzc.zzmg:()Lcom/google/android/gms/internal/zzew$zzd;
        //    59: invokevirtual   com/google/android/gms/internal/zzew.zza:(Lcom/google/android/gms/internal/zzew$zzd;)Lorg/json/JSONObject;
        //    62: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    65: pop            
        //    66: aload_3        
        //    67: ldc_w           "success"
        //    70: iconst_1       
        //    71: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //    74: pop            
        //    75: aload_3        
        //    76: areturn        
        //    77: astore          16
        //    79: ldc_w           "The request is not a valid JSON."
        //    82: invokestatic    com/google/android/gms/internal/zzkn.e:(Ljava/lang/String;)V
        //    85: new             Lorg/json/JSONObject;
        //    88: dup            
        //    89: invokespecial   org/json/JSONObject.<init>:()V
        //    92: ldc_w           "success"
        //    95: iconst_0       
        //    96: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //    99: astore          18
        //   101: aload           18
        //   103: astore_3       
        //   104: goto            75
        //   107: astore          17
        //   109: new             Lorg/json/JSONObject;
        //   112: dup            
        //   113: invokespecial   org/json/JSONObject.<init>:()V
        //   116: astore_3       
        //   117: goto            75
        //   120: aload_3        
        //   121: ldc_w           "response"
        //   124: new             Lorg/json/JSONObject;
        //   127: dup            
        //   128: invokespecial   org/json/JSONObject.<init>:()V
        //   131: ldc             "http_request_id"
        //   133: aload           4
        //   135: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   138: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   141: pop            
        //   142: aload_3        
        //   143: ldc_w           "success"
        //   146: iconst_0       
        //   147: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //   150: pop            
        //   151: aload_3        
        //   152: ldc_w           "reason"
        //   155: aload           10
        //   157: invokevirtual   com/google/android/gms/internal/zzew$zzc.getReason:()Ljava/lang/String;
        //   160: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   163: pop            
        //   164: goto            75
        //   167: astore          5
        //   169: aload_3        
        //   170: ldc_w           "response"
        //   173: new             Lorg/json/JSONObject;
        //   176: dup            
        //   177: invokespecial   org/json/JSONObject.<init>:()V
        //   180: ldc             "http_request_id"
        //   182: aload           4
        //   184: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   187: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   190: pop            
        //   191: aload_3        
        //   192: ldc_w           "success"
        //   195: iconst_0       
        //   196: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //   199: pop            
        //   200: aload_3        
        //   201: ldc_w           "reason"
        //   204: aload           5
        //   206: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   209: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   212: pop            
        //   213: goto            75
        //   216: astore          6
        //   218: goto            75
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  0      9      77     120    Lorg/json/JSONException;
        //  22     75     167    221    Ljava/lang/Exception;
        //  85     101    107    120    Lorg/json/JSONException;
        //  120    164    167    221    Ljava/lang/Exception;
        //  169    213    216    221    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0075:
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
    
    protected zzb zzc(final JSONObject jsonObject) {
        String optString = null;
        String optString3 = null;
        URL url = null;
        ArrayList<zza> list = null;
    Label_0093_Outer:
        while (true) {
            optString = jsonObject.optString("http_request_id");
            final String optString2 = jsonObject.optString("url");
            optString3 = jsonObject.optString("post_body", (String)null);
            while (true) {
                JSONObject optJSONObject = null;
            Label_0115:
                while (true) {
                    try {
                        url = new URL(optString2);
                        list = new ArrayList<zza>();
                        JSONArray optJSONArray = jsonObject.optJSONArray("headers");
                        if (optJSONArray == null) {
                            optJSONArray = new JSONArray();
                        }
                        for (int i = 0; i < optJSONArray.length(); ++i) {
                            optJSONObject = optJSONArray.optJSONObject(i);
                            if (optJSONObject != null) {
                                break Label_0115;
                            }
                        }
                        break;
                    }
                    catch (MalformedURLException ex) {
                        com.google.android.gms.ads.internal.util.client.zzb.zzb("Error constructing http request.", ex);
                        url = null;
                        continue Label_0093_Outer;
                    }
                    break;
                }
                list.add(new zza(optJSONObject.optString("key"), optJSONObject.optString("value")));
                continue;
            }
        }
        return new zzb(optString, url, list, optString3);
    }
    
    @zziy
    static class zza
    {
        private final String mValue;
        private final String zzbaf;
        
        public zza(final String zzbaf, final String mValue) {
            this.zzbaf = zzbaf;
            this.mValue = mValue;
        }
        
        public String getKey() {
            return this.zzbaf;
        }
        
        public String getValue() {
            return this.mValue;
        }
    }
    
    @zziy
    static class zzb
    {
        private final String zzbna;
        private final URL zzbnb;
        private final ArrayList<zza> zzbnc;
        private final String zzbnd;
        
        public zzb(final String zzbna, final URL zzbnb, final ArrayList<zza> zzbnc, final String zzbnd) {
            this.zzbna = zzbna;
            this.zzbnb = zzbnb;
            if (zzbnc == null) {
                this.zzbnc = new ArrayList<zza>();
            }
            else {
                this.zzbnc = zzbnc;
            }
            this.zzbnd = zzbnd;
        }
        
        public String zzmc() {
            return this.zzbna;
        }
        
        public URL zzmd() {
            return this.zzbnb;
        }
        
        public ArrayList<zza> zzme() {
            return this.zzbnc;
        }
        
        public String zzmf() {
            return this.zzbnd;
        }
    }
    
    @zziy
    class zzc
    {
        private final zzd zzbne;
        private final boolean zzbnf;
        private final String zzbng;
        
        public zzc(final boolean zzbnf, final zzd zzbne, final String zzbng) {
            this.zzbnf = zzbnf;
            this.zzbne = zzbne;
            this.zzbng = zzbng;
        }
        
        public String getReason() {
            return this.zzbng;
        }
        
        public boolean isSuccess() {
            return this.zzbnf;
        }
        
        public zzd zzmg() {
            return this.zzbne;
        }
    }
    
    @zziy
    static class zzd
    {
        private final String zzbjs;
        private final String zzbna;
        private final int zzbnh;
        private final List<zza> zzbni;
        
        public zzd(final String zzbna, final int zzbnh, final List<zza> zzbni, final String zzbjs) {
            this.zzbna = zzbna;
            this.zzbnh = zzbnh;
            if (zzbni == null) {
                this.zzbni = new ArrayList<zza>();
            }
            else {
                this.zzbni = zzbni;
            }
            this.zzbjs = zzbjs;
        }
        
        public String getBody() {
            return this.zzbjs;
        }
        
        public int getResponseCode() {
            return this.zzbnh;
        }
        
        public String zzmc() {
            return this.zzbna;
        }
        
        public Iterable<zza> zzmh() {
            return this.zzbni;
        }
    }
}
