// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.CancellationException;
import java.util.UUID;
import com.google.android.gms.ads.internal.formats.zza;
import java.util.concurrent.Future;
import java.util.Iterator;
import com.google.android.gms.dynamic.zze;
import java.util.ArrayList;
import org.json.JSONArray;
import java.util.concurrent.TimeoutException;
import com.google.android.gms.common.internal.zzac;
import android.os.RemoteException;
import android.graphics.Color;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import java.util.Map;
import com.google.android.gms.ads.internal.formats.zzf;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.content.res.Resources;
import com.google.android.gms.common.util.zzs;
import android.graphics.Rect;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap$Config;
import android.graphics.BitmapFactory$Options;
import java.io.InputStream;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.formats.zzc;
import org.json.JSONException;
import java.util.concurrent.ExecutionException;
import com.google.android.gms.ads.internal.formats.zzj;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Arrays;
import com.google.android.gms.ads.internal.formats.zzi;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;
import java.util.List;
import com.google.android.gms.ads.internal.zzq;
import android.content.Context;
import java.util.concurrent.Callable;

@zziy
public class zzis implements Callable<zzke>
{
    private static final long zzcdo;
    private final Context mContext;
    private final Object zzakd;
    private final zzdq zzalg;
    private final zzir zzbkn;
    private final zzau zzbkp;
    private final zzke.zza zzcck;
    private int zzcdb;
    private final zzky zzcdx;
    private final zzq zzcdy;
    private boolean zzcdz;
    private List<String> zzcea;
    private JSONObject zzceb;
    
    static {
        zzcdo = TimeUnit.SECONDS.toMillis(60L);
    }
    
    public zzis(final Context mContext, final zzq zzcdy, final zzky zzcdx, final zzau zzbkp, final zzke.zza zzcck, final zzdq zzalg) {
        this.zzakd = new Object();
        this.mContext = mContext;
        this.zzcdy = zzcdy;
        this.zzcdx = zzcdx;
        this.zzcck = zzcck;
        this.zzbkp = zzbkp;
        this.zzalg = zzalg;
        (this.zzbkn = this.zza(mContext, zzcck, zzcdy, zzbkp)).zzre();
        this.zzcdz = false;
        this.zzcdb = -2;
        this.zzcea = null;
    }
    
    private zzi.zza zza(final zza zza, final JSONObject jsonObject, final String s) throws ExecutionException, InterruptedException, JSONException {
        final boolean zzrq = this.zzrq();
        zzi.zza zza2 = null;
        if (!zzrq) {
            zza2 = null;
            if (zza != null) {
                zza2 = null;
                if (jsonObject != null) {
                    final JSONObject jsonObject2 = jsonObject.getJSONObject("tracking_urls_and_actions");
                    final String[] zzd = this.zzd(jsonObject2, "impression_tracking_urls");
                    List<String> list;
                    if (zzd == null) {
                        list = null;
                    }
                    else {
                        list = Arrays.asList(zzd);
                    }
                    this.zzcea = list;
                    this.zzceb = jsonObject2.optJSONObject("active_view");
                    final zzi.zza zza3 = zza.zza(this, jsonObject);
                    if (zza3 == null) {
                        com.google.android.gms.ads.internal.util.client.zzb.e("Failed to retrieve ad assets.");
                        zza2 = null;
                    }
                    else {
                        zza3.zzb(new zzj(this.mContext, this.zzcdy, this.zzbkn, this.zzbkp, jsonObject, zza3, this.zzcck.zzcix.zzaqv, s));
                        zza2 = zza3;
                    }
                }
            }
        }
        return zza2;
    }
    
    private zzlj<zzc> zza(final JSONObject jsonObject, final boolean b, final boolean b2) throws JSONException {
        String s;
        if (b) {
            s = jsonObject.getString("url");
        }
        else {
            s = jsonObject.optString("url");
        }
        final double optDouble = jsonObject.optDouble("scale", 1.0);
        final boolean optBoolean = jsonObject.optBoolean("is_transparent", true);
        zzlj<zzc> zza;
        if (TextUtils.isEmpty((CharSequence)s)) {
            this.zza(0, b);
            zza = new zzlh<zzc>(null);
        }
        else if (b2) {
            zza = new zzlh<zzc>(new zzc(null, Uri.parse(s), optDouble));
        }
        else {
            zza = this.zzcdx.zza(s, (zzky.zza<zzc>)new zzky.zza<zzc>() {
                @TargetApi(19)
                public com.google.android.gms.ads.internal.formats.zzc zzg(final InputStream inputStream) {
                    com.google.android.gms.ads.internal.formats.zzc zzc = null;
                    final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
                    bitmapFactory$Options.inDensity = (int)(160.0 * optDouble);
                    if (!optBoolean) {
                        bitmapFactory$Options.inPreferredConfig = Bitmap$Config.RGB_565;
                    }
                    while (true) {
                        Bitmap decodeStream;
                        while (true) {
                            try {
                                decodeStream = BitmapFactory.decodeStream(inputStream, (Rect)null, bitmapFactory$Options);
                                if (decodeStream == null) {
                                    zzis.this.zza(2, b);
                                    return zzc;
                                }
                            }
                            catch (Exception ex) {
                                zzb.zzb("Error grabbing image.", ex);
                                decodeStream = null;
                                continue;
                            }
                            break;
                        }
                        if (zzs.zzaxr()) {
                            zzkn.v(new StringBuilder(61).append("Decoded image w: ").append(decodeStream.getWidth()).append(" h:").append(decodeStream.getHeight()).append(" bytes: ").append(decodeStream.getAllocationByteCount()).toString());
                        }
                        zzc = new com.google.android.gms.ads.internal.formats.zzc((Drawable)new BitmapDrawable(Resources.getSystem(), decodeStream), Uri.parse(s), optDouble);
                        return zzc;
                    }
                }
                
                public com.google.android.gms.ads.internal.formats.zzc zzrr() {
                    zzis.this.zza(2, b);
                    return null;
                }
            });
        }
        return zza;
    }
    
    private void zza(final zzi.zza zza) {
        if (zza instanceof zzf) {
            final zzf zzf = (zzf)zza;
            final zzb zzb = new zzb();
            final zzev zzcev = new zzev() {
                @Override
                public void zza(final zzlt zzlt, final Map<String, String> map) {
                    zzis.this.zzb(zzf, map.get("asset"));
                }
            };
            zzb.zzcev = zzcev;
            this.zzbkn.zza((zzir.zza)new zzir.zza() {
                @Override
                public void zze(final zzfz zzfz) {
                    zzfz.zza("/nativeAdCustomClick", zzcev);
                }
            });
        }
    }
    
    private zzke zzb(final zzi.zza zza) {
        while (true) {
            while (true) {
                synchronized (this.zzakd) {
                    int zzcdb = this.zzcdb;
                    if (zza == null && this.zzcdb == -2) {
                        zzcdb = 0;
                    }
                    // monitorexit(this.zzakd)
                    if (zzcdb != -2) {
                        final zzi.zza zza2 = null;
                        return new zzke(this.zzcck.zzcix.zzcfu, null, this.zzcck.zzcop.zzbsd, zzcdb, this.zzcck.zzcop.zzbse, this.zzcea, this.zzcck.zzcop.orientation, this.zzcck.zzcop.zzbsj, this.zzcck.zzcix.zzcfx, false, null, null, null, null, null, 0L, this.zzcck.zzaqz, this.zzcck.zzcop.zzchb, this.zzcck.zzcoj, this.zzcck.zzcok, this.zzcck.zzcop.zzchh, this.zzceb, zza2, null, null, null, this.zzcck.zzcop.zzchu, this.zzcck.zzcop.zzchv, null, this.zzcck.zzcop.zzbsg, this.zzcck.zzcop.zzchy);
                    }
                }
                final zzi.zza zza2 = zza;
                continue;
            }
        }
    }
    
    private Integer zzb(final JSONObject jsonObject, final String s) {
        try {
            final JSONObject jsonObject2 = jsonObject.getJSONObject(s);
            return Color.rgb(jsonObject2.getInt("r"), jsonObject2.getInt("g"), jsonObject2.getInt("b"));
        }
        catch (JSONException ex) {
            return null;
        }
    }
    
    private void zzb(final zzef zzef, final String s) {
        try {
            final zzej zzx = this.zzcdy.zzx(zzef.getCustomTemplateId());
            if (zzx != null) {
                zzx.zza(zzef, s);
            }
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd(new StringBuilder(40 + String.valueOf(s).length()).append("Failed to call onCustomClick for asset ").append(s).append(".").toString(), (Throwable)ex);
        }
    }
    
    private JSONObject zzcf(final String s) throws ExecutionException, InterruptedException, TimeoutException, JSONException {
        JSONObject jsonObject;
        if (this.zzrq()) {
            jsonObject = null;
        }
        else {
            final zzlg<JSONObject> zzlg = new zzlg<JSONObject>();
            this.zzbkn.zza((zzir.zza)new zzir.zza() {
                final /* synthetic */ zzb zzcec = new zzb();
                
                @Override
                public void zze(final zzfz zzfz) {
                    zzfz.zza("/nativeAdPreProcess", this.zzcec.zzcev = new zzev() {
                        @Override
                        public void zza(final zzlt zzlt, final Map<String, String> map) {
                            while (true) {
                                zzfz.zzb("/nativeAdPreProcess", zzir.zza.this.zzcec.zzcev);
                                try {
                                    final String s = map.get("success");
                                    if (!TextUtils.isEmpty((CharSequence)s)) {
                                        zzlg.zzh(new JSONObject(s).getJSONArray("ads").getJSONObject(0));
                                        return;
                                    }
                                }
                                catch (JSONException ex) {
                                    com.google.android.gms.ads.internal.util.client.zzb.zzb("Malformed native JSON response.", (Throwable)ex);
                                }
                                zzis.this.zzap(0);
                                zzac.zza(zzis.this.zzrq(), (Object)"Unable to set the ad state error!");
                                zzlg.zzh(null);
                            }
                        }
                    });
                    try {
                        final JSONObject jsonObject = new JSONObject(zzis.this.zzcck.zzcop.body);
                        jsonObject.put("ads_id", (Object)s);
                        zzfz.zza("google.afma.nativeAds.preProcessJsonGmsg", jsonObject);
                    }
                    catch (JSONException ex) {
                        com.google.android.gms.ads.internal.util.client.zzb.zzd("Exception occurred while invoking javascript", (Throwable)ex);
                        zzlg.zzh(null);
                    }
                }
                
                @Override
                public void zzro() {
                    zzlg.zzh(null);
                }
            });
            jsonObject = zzlg.get(zzis.zzcdo, TimeUnit.MILLISECONDS);
        }
        return jsonObject;
    }
    
    private String[] zzd(final JSONObject jsonObject, final String s) throws JSONException {
        final JSONArray optJSONArray = jsonObject.optJSONArray(s);
        String[] array;
        if (optJSONArray == null) {
            array = null;
        }
        else {
            final String[] array2 = new String[optJSONArray.length()];
            for (int i = 0; i < optJSONArray.length(); ++i) {
                array2[i] = optJSONArray.getString(i);
            }
            array = array2;
        }
        return array;
    }
    
    private static List<Drawable> zzh(final List<zzc> list) throws RemoteException {
        final ArrayList<Drawable> list2 = new ArrayList<Drawable>();
        final Iterator<zzc> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add((Drawable)zze.zzae(iterator.next().zzln()));
        }
        return list2;
    }
    
    zzir zza(final Context context, final zzke.zza zza, final zzq zzq, final zzau zzau) {
        return new zzir(context, zza, zzq, zzau);
    }
    
    zzit zza(final Context context, final zzau zzau, final zzke.zza zza, final zzdq zzdq, final zzq zzq) {
        return new zzit(context, zzau, zza, zzdq, zzq);
    }
    
    public zzlj<zzc> zza(final JSONObject jsonObject, final String s, final boolean b, final boolean b2) throws JSONException {
        JSONObject jsonObject2;
        if (b) {
            jsonObject2 = jsonObject.getJSONObject(s);
        }
        else {
            jsonObject2 = jsonObject.optJSONObject(s);
        }
        if (jsonObject2 == null) {
            jsonObject2 = new JSONObject();
        }
        return this.zza(jsonObject2, b, b2);
    }
    
    public List<zzlj<zzc>> zza(final JSONObject jsonObject, final String s, final boolean b, final boolean b2, final boolean b3) throws JSONException {
        JSONArray jsonArray;
        if (b) {
            jsonArray = jsonObject.getJSONArray(s);
        }
        else {
            jsonArray = jsonObject.optJSONArray(s);
        }
        final ArrayList<zzlj<zzc>> list = new ArrayList<zzlj<zzc>>();
        ArrayList<zzlj<zzc>> list2;
        if (jsonArray == null || jsonArray.length() == 0) {
            this.zza(0, b);
            list2 = list;
        }
        else {
            int length;
            if (b3) {
                length = jsonArray.length();
            }
            else {
                length = 1;
            }
            for (int i = 0; i < length; ++i) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                if (jsonObject2 == null) {
                    jsonObject2 = new JSONObject();
                }
                list.add(this.zza(jsonObject2, b, b2));
            }
            list2 = list;
        }
        return list2;
    }
    
    public Future<zzc> zza(final JSONObject jsonObject, final String s, final boolean b) throws JSONException {
        JSONObject jsonObject2 = jsonObject.getJSONObject(s);
        final boolean optBoolean = jsonObject2.optBoolean("require", true);
        if (jsonObject2 == null) {
            jsonObject2 = new JSONObject();
        }
        return this.zza(jsonObject2, optBoolean, b);
    }
    
    public void zza(final int n, final boolean b) {
        if (b) {
            this.zzap(n);
        }
    }
    
    public void zzap(final int zzcdb) {
        synchronized (this.zzakd) {
            this.zzcdz = true;
            this.zzcdb = zzcdb;
        }
    }
    
    public zzlj<zzlt> zzc(final JSONObject jsonObject, final String s) throws JSONException {
        final JSONObject optJSONObject = jsonObject.optJSONObject(s);
        zzlj<zzlt> zzh;
        if (optJSONObject == null) {
            zzh = new zzlh<zzlt>(null);
        }
        else if (TextUtils.isEmpty((CharSequence)optJSONObject.optString("vast_xml"))) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("Required field 'vast_xml' is missing");
            zzh = new zzlh<zzlt>(null);
        }
        else {
            zzh = this.zza(this.mContext, this.zzbkp, this.zzcck, this.zzalg, this.zzcdy).zzh(optJSONObject);
        }
        return zzh;
    }
    
    protected zza zzf(final JSONObject jsonObject) throws ExecutionException, InterruptedException, JSONException, TimeoutException {
        Object o;
        if (this.zzrq() || jsonObject == null) {
            o = null;
        }
        else {
            final String string = jsonObject.getString("template_id");
            final boolean b = this.zzcck.zzcix.zzarn != null && this.zzcck.zzcix.zzarn.zzblb;
            final boolean b2 = this.zzcck.zzcix.zzarn != null && this.zzcck.zzcix.zzarn.zzbld;
            if ("2".equals(string)) {
                o = new zziu(b, b2);
            }
            else if ("1".equals(string)) {
                o = new zziv(b, b2);
            }
            else {
                if ("3".equals(string)) {
                    final String string2 = jsonObject.getString("custom_template_id");
                    final zzlg zzlg = new zzlg();
                    zzkr.zzcrf.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            zzlg.zzh(zzis.this.zzcdy.zzfi().get(string2));
                        }
                    });
                    if (zzlg.get(zzis.zzcdo, TimeUnit.MILLISECONDS) != null) {
                        o = new zziw(b);
                        return (zza)o;
                    }
                    final String value = String.valueOf(jsonObject.getString("custom_template_id"));
                    String concat;
                    if (value.length() != 0) {
                        concat = "No handler for custom template: ".concat(value);
                    }
                    else {
                        concat = new String("No handler for custom template: ");
                    }
                    com.google.android.gms.ads.internal.util.client.zzb.e(concat);
                }
                else {
                    this.zzap(0);
                }
                o = null;
            }
        }
        return (zza)o;
    }
    
    public zzlj<com.google.android.gms.ads.internal.formats.zza> zzg(final JSONObject jsonObject) throws JSONException {
        final JSONObject optJSONObject = jsonObject.optJSONObject("attribution");
        zzlj<com.google.android.gms.ads.internal.formats.zza> zza;
        if (optJSONObject == null) {
            zza = new zzlh<com.google.android.gms.ads.internal.formats.zza>(null);
        }
        else {
            final String optString = optJSONObject.optString("text");
            final int optInt = optJSONObject.optInt("text_size", -1);
            final Integer zzb = this.zzb(optJSONObject, "text_color");
            final Integer zzb2 = this.zzb(optJSONObject, "bg_color");
            final int optInt2 = optJSONObject.optInt("animation_ms", 1000);
            final int optInt3 = optJSONObject.optInt("presentation_ms", 4000);
            int zzble;
            if (this.zzcck.zzcix.zzarn != null && this.zzcck.zzcix.zzarn.versionCode >= 2) {
                zzble = this.zzcck.zzcix.zzarn.zzble;
            }
            else {
                zzble = 1;
            }
            List<zzlj<zzc>> zza2 = new ArrayList<zzlj<zzc>>();
            if (optJSONObject.optJSONArray("images") != null) {
                zza2 = this.zza(optJSONObject, "images", false, false, true);
            }
            else {
                zza2.add(this.zza(optJSONObject, "image", false, false));
            }
            zza = zzli.zza(zzli.zzo((List<zzlj<Object>>)zza2), (zzli.zza<List<Object>, com.google.android.gms.ads.internal.formats.zza>)new zzli.zza<List<zzc>, com.google.android.gms.ads.internal.formats.zza>() {
                public zza zzj(final List<zzc> list) {
                    if (list != null) {
                        while (true) {
                            while (true) {
                                try {
                                    if (list.isEmpty()) {
                                        break;
                                    }
                                    final String zzcel = optString;
                                    final List zzi = zzh(list);
                                    final Integer zzcem = zzb2;
                                    final Integer zzcen = zzb;
                                    if (optInt > 0) {
                                        final Integer value = optInt;
                                        return new zza(zzcel, zzi, zzcem, zzcen, value, optInt3 + optInt2, zzble);
                                    }
                                }
                                catch (RemoteException ex) {
                                    com.google.android.gms.ads.internal.util.client.zzb.zzb("Could not get attribution icon", (Throwable)ex);
                                    return null;
                                }
                                final Integer value = null;
                                continue;
                            }
                        }
                    }
                    final zza zza = null;
                    return zza;
                }
            });
        }
        return zza;
    }
    
    public zzke zzrp() {
        try {
            this.zzbkn.zzrf();
            final String string = UUID.randomUUID().toString();
            final JSONObject zzcf = this.zzcf(string);
            final zzi.zza zza = this.zza(this.zzf(zzcf), zzcf, string);
            this.zza(zza);
            return this.zzb(zza);
        }
        catch (JSONException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Malformed native JSON response.", (Throwable)ex);
        }
        catch (TimeoutException ex2) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Timeout when loading native ad.", ex2);
            goto Label_0068;
        }
        catch (InterruptedException ex3) {
            goto Label_0068;
        }
        catch (ExecutionException ex4) {
            goto Label_0068;
        }
        catch (CancellationException ex5) {
            goto Label_0068;
        }
    }
    
    public boolean zzrq() {
        synchronized (this.zzakd) {
            return this.zzcdz;
        }
    }
    
    public interface zza<T extends zzi.zza>
    {
        T zza(final zzis p0, final JSONObject p1) throws JSONException, InterruptedException, ExecutionException;
    }
    
    class zzb
    {
        public zzev zzcev;
    }
}
