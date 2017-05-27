// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import android.os.IBinder;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.google.android.gms.internal.zzgl;
import org.json.JSONArray;
import com.google.android.gms.internal.zzlu;
import android.view.View$OnClickListener;
import com.google.android.gms.internal.zzgr;
import java.util.List;
import com.google.android.gms.internal.zzke;
import org.json.JSONException;
import java.util.Iterator;
import android.text.TextUtils;
import org.json.JSONObject;
import android.os.Bundle;
import android.net.Uri;
import com.google.android.gms.internal.zzdx;
import android.util.Base64;
import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import java.io.ByteArrayOutputStream;
import android.graphics.Bitmap;
import java.util.concurrent.CountDownLatch;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;
import com.google.android.gms.internal.zzlt;
import com.google.android.gms.internal.zzev;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.formats.zze;
import com.google.android.gms.internal.zzgv;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.ads.internal.client.zzab;
import com.google.android.gms.ads.internal.formats.zza;
import com.google.android.gms.ads.internal.formats.zzd;
import com.google.android.gms.internal.zzgu;
import com.google.android.gms.internal.zziy;

@zziy
public class zzn
{
    private static zzd zza(final zzgu zzgu) throws RemoteException {
        return new zzd(zzgu.getHeadline(), zzgu.getImages(), zzgu.getBody(), zzgu.zzlo(), zzgu.getCallToAction(), zzgu.getStarRating(), zzgu.getStore(), zzgu.getPrice(), null, zzgu.getExtras(), null, null);
    }
    
    private static zze zza(final zzgv zzgv) throws RemoteException {
        return new zze(zzgv.getHeadline(), zzgv.getImages(), zzgv.getBody(), zzgv.zzlt(), zzgv.getCallToAction(), zzgv.getAdvertiser(), null, zzgv.getExtras());
    }
    
    static zzev zza(@Nullable final zzgu zzgu, @Nullable final zzgv zzgv, final zzf.zza zza) {
        return new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                final View view = zzlt.getView();
                if (view != null) {
                    Label_0073: {
                        Label_0066: {
                            try {
                                if (zzgu == null) {
                                    break Label_0073;
                                }
                                if (zzgu.getOverrideClickHandling()) {
                                    break Label_0066;
                                }
                                zzgu.zzk(com.google.android.gms.dynamic.zze.zzac(view));
                                zza.onClick();
                            }
                            catch (RemoteException ex) {
                                zzb.zzd("Unable to call handleClick on mapper", (Throwable)ex);
                            }
                            return;
                        }
                        zza(zzlt);
                        return;
                    }
                    if (zzgv != null) {
                        if (!zzgv.getOverrideClickHandling()) {
                            zzgv.zzk(com.google.android.gms.dynamic.zze.zzac(view));
                            zza.onClick();
                        }
                        else {
                            zza(zzlt);
                        }
                    }
                }
            }
        };
    }
    
    static zzev zza(final CountDownLatch countDownLatch) {
        return new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                countDownLatch.countDown();
                zzlt.getView().setVisibility(0);
            }
        };
    }
    
    private static String zza(@Nullable final Bitmap bitmap) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String concat;
        if (bitmap == null) {
            zzb.zzdf("Bitmap is null. Returning empty string");
            concat = "";
        }
        else {
            bitmap.compress(Bitmap$CompressFormat.PNG, 100, (OutputStream)byteArrayOutputStream);
            final String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
            final String value = String.valueOf("data:image/png;base64,");
            final String value2 = String.valueOf(encodeToString);
            if (value2.length() != 0) {
                concat = value.concat(value2);
            }
            else {
                concat = new String(value);
            }
        }
        return concat;
    }
    
    static String zza(@Nullable final zzdx zzdx) {
        String s;
        if (zzdx == null) {
            zzb.zzdf("Image is null. Returning empty string");
            s = "";
        }
        else {
            try {
                final Uri uri = zzdx.getUri();
                if (uri != null) {
                    s = uri.toString();
                    return s;
                }
            }
            catch (RemoteException ex) {
                zzb.zzdf("Unable to get image uri. Trying data uri next");
            }
            s = zzb(zzdx);
        }
        return s;
    }
    
    private static JSONObject zza(@Nullable final Bundle bundle, final String s) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject2;
        if (bundle == null || TextUtils.isEmpty((CharSequence)s)) {
            jsonObject2 = jsonObject;
        }
        else {
            final JSONObject jsonObject3 = new JSONObject(s);
            final Iterator keys = jsonObject3.keys();
            while (keys.hasNext()) {
                final String s2 = keys.next();
                if (bundle.containsKey(s2)) {
                    if ("image".equals(jsonObject3.getString(s2))) {
                        final Object value = bundle.get(s2);
                        if (value instanceof Bitmap) {
                            jsonObject.put(s2, (Object)zza((Bitmap)value));
                        }
                        else {
                            zzb.zzdf("Invalid type. An image type extra should return a bitmap");
                        }
                    }
                    else if (bundle.get(s2) instanceof Bitmap) {
                        zzb.zzdf("Invalid asset type. Bitmap should be returned only for image type");
                    }
                    else {
                        jsonObject.put(s2, (Object)String.valueOf(bundle.get(s2)));
                    }
                }
            }
            jsonObject2 = jsonObject;
        }
        return jsonObject2;
    }
    
    public static void zza(@Nullable final zzke zzke, final zzf.zza zza) {
        if (zzke != null && zzh(zzke)) {
            final zzlt zzbyh = zzke.zzbyh;
            View view;
            if (zzbyh != null) {
                view = zzbyh.getView();
            }
            else {
                view = null;
            }
            if (view == null) {
                zzb.zzdf("AdWebView is null");
            }
            else {
                List<String> zzbrz;
                while (true) {
                    while (true) {
                        Label_0093: {
                            try {
                                if (zzke.zzbte == null) {
                                    break Label_0093;
                                }
                                zzbrz = zzke.zzbte.zzbrz;
                                if (zzbrz != null && !zzbrz.isEmpty()) {
                                    break;
                                }
                                zzb.zzdf("No template ids present in mediation response");
                            }
                            catch (RemoteException ex) {
                                zzb.zzd("Error occurred while recording impression and registering for clicks", (Throwable)ex);
                            }
                            return;
                        }
                        zzbrz = null;
                        continue;
                    }
                }
                zzgu zznm;
                if (zzke.zzbtf != null) {
                    zznm = zzke.zzbtf.zznm();
                }
                else {
                    zznm = null;
                }
                final zzgr zzbtf = zzke.zzbtf;
                zzgv zznn = null;
                if (zzbtf != null) {
                    zznn = zzke.zzbtf.zznn();
                }
                if (zzbrz.contains("2") && zznm != null) {
                    zznm.zzl(com.google.android.gms.dynamic.zze.zzac(view));
                    if (!zznm.getOverrideImpressionRecording()) {
                        zznm.recordImpression();
                    }
                    zzbyh.zzvr().zza("/nativeExpressViewClicked", zza(zznm, null, zza));
                }
                else if (zzbrz.contains("1") && zznn != null) {
                    zznn.zzl(com.google.android.gms.dynamic.zze.zzac(view));
                    if (!zznn.getOverrideImpressionRecording()) {
                        zznn.recordImpression();
                    }
                    zzbyh.zzvr().zza("/nativeExpressViewClicked", zza(null, zznn, zza));
                }
                else {
                    zzb.zzdf("No matching template id and mapper");
                }
            }
        }
    }
    
    private static void zza(final zzlt zzlt) {
        final View$OnClickListener zzwf = zzlt.zzwf();
        if (zzwf != null) {
            zzwf.onClick(zzlt.getView());
        }
    }
    
    private static void zza(final zzlt zzlt, final zzd zzd, final String s) {
        zzlt.zzvr().zza((zzlu.zza)new zzlu.zza() {
            @Override
            public void zza(final zzlt zzlt, final boolean b) {
                JSONObject jsonObject = null;
                JSONArray jsonArray = null;
                Label_0186: {
                    try {
                        jsonObject = new JSONObject();
                        jsonObject.put("headline", (Object)zzd.getHeadline());
                        jsonObject.put("body", (Object)zzd.getBody());
                        jsonObject.put("call_to_action", (Object)zzd.getCallToAction());
                        jsonObject.put("price", (Object)zzd.getPrice());
                        jsonObject.put("star_rating", (Object)String.valueOf(zzd.getStarRating()));
                        jsonObject.put("store", (Object)zzd.getStore());
                        jsonObject.put("icon", (Object)zzn.zza(zzd.zzlo()));
                        jsonArray = new JSONArray();
                        final List images = zzd.getImages();
                        if (images != null) {
                            final Iterator<Object> iterator = images.iterator();
                            while (iterator.hasNext()) {
                                jsonArray.put((Object)zzn.zza(zze(iterator.next())));
                            }
                        }
                        break Label_0186;
                    }
                    catch (JSONException ex) {
                        com.google.android.gms.ads.internal.util.client.zzb.zzd("Exception occurred when loading assets", (Throwable)ex);
                    }
                    return;
                }
                jsonObject.put("images", (Object)jsonArray);
                jsonObject.put("extras", (Object)zza(zzd.getExtras(), s));
                final JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("assets", (Object)jsonObject);
                jsonObject2.put("template_id", (Object)"2");
                zzlt.zza("google.afma.nativeExpressAds.loadAssets", jsonObject2);
            }
        });
    }
    
    private static void zza(final zzlt zzlt, final zze zze, final String s) {
        zzlt.zzvr().zza((zzlu.zza)new zzlu.zza() {
            @Override
            public void zza(final zzlt zzlt, final boolean b) {
                JSONObject jsonObject = null;
                JSONArray jsonArray = null;
                Label_0155: {
                    try {
                        jsonObject = new JSONObject();
                        jsonObject.put("headline", (Object)zze.getHeadline());
                        jsonObject.put("body", (Object)zze.getBody());
                        jsonObject.put("call_to_action", (Object)zze.getCallToAction());
                        jsonObject.put("advertiser", (Object)zze.getAdvertiser());
                        jsonObject.put("logo", (Object)zzn.zza(zze.zzlt()));
                        jsonArray = new JSONArray();
                        final List images = zze.getImages();
                        if (images != null) {
                            final Iterator<Object> iterator = images.iterator();
                            while (iterator.hasNext()) {
                                jsonArray.put((Object)zzn.zza(zze(iterator.next())));
                            }
                        }
                        break Label_0155;
                    }
                    catch (JSONException ex) {
                        com.google.android.gms.ads.internal.util.client.zzb.zzd("Exception occurred when loading assets", (Throwable)ex);
                    }
                    return;
                }
                jsonObject.put("images", (Object)jsonArray);
                jsonObject.put("extras", (Object)zza(zze.getExtras(), s));
                final JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("assets", (Object)jsonObject);
                jsonObject2.put("template_id", (Object)"1");
                zzlt.zza("google.afma.nativeExpressAds.loadAssets", jsonObject2);
            }
        });
    }
    
    private static void zza(final zzlt zzlt, final CountDownLatch countDownLatch) {
        zzlt.zzvr().zza("/nativeExpressAssetsLoaded", zza(countDownLatch));
        zzlt.zzvr().zza("/nativeExpressAssetsLoadingFailed", zzb(countDownLatch));
    }
    
    public static boolean zza(final zzlt zzlt, final zzgl zzgl, final CountDownLatch countDownLatch) {
        while (true) {
            try {
                final boolean zzb = zzb(zzlt, zzgl, countDownLatch);
                if (!zzb) {
                    countDownLatch.countDown();
                }
                return zzb;
            }
            catch (RemoteException ex) {
                zzb.zzd("Unable to invoke load assets", (Throwable)ex);
                final boolean zzb = false;
                continue;
            }
            catch (RuntimeException ex2) {
                countDownLatch.countDown();
                throw ex2;
            }
            break;
        }
    }
    
    static zzev zzb(final CountDownLatch countDownLatch) {
        return new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                zzb.zzdf("Adapter returned an ad, but assets substitution failed");
                countDownLatch.countDown();
                zzlt.destroy();
            }
        };
    }
    
    private static String zzb(final zzdx zzdx) {
        Drawable drawable;
        try {
            final com.google.android.gms.dynamic.zzd zzln = zzdx.zzln();
            if (zzln == null) {
                zzb.zzdf("Drawable is null. Returning empty string");
                return "";
            }
            drawable = com.google.android.gms.dynamic.zze.zzae(zzln);
            if (!(drawable instanceof BitmapDrawable)) {
                zzb.zzdf("Drawable is not an instance of BitmapDrawable. Returning empty string");
                return "";
            }
        }
        catch (RemoteException ex) {
            zzb.zzdf("Unable to get drawable. Returning empty string");
            return "";
        }
        return zza(((BitmapDrawable)drawable).getBitmap());
    }
    
    private static boolean zzb(final zzlt zzlt, final zzgl zzgl, final CountDownLatch countDownLatch) throws RemoteException {
        boolean b = false;
        final View view = zzlt.getView();
        if (view == null) {
            zzb.zzdf("AdWebView is null");
        }
        else {
            view.setVisibility(4);
            final List<String> zzbrz = zzgl.zzbte.zzbrz;
            if (zzbrz == null || zzbrz.isEmpty()) {
                zzb.zzdf("No template ids present in mediation response");
                b = false;
            }
            else {
                zza(zzlt, countDownLatch);
                final zzgu zznm = zzgl.zzbtf.zznm();
                final zzgv zznn = zzgl.zzbtf.zznn();
                if (zzbrz.contains("2") && zznm != null) {
                    zza(zzlt, zza(zznm), zzgl.zzbte.zzbry);
                }
                else {
                    if (!zzbrz.contains("1") || zznn == null) {
                        zzb.zzdf("No matching template id and mapper");
                        b = false;
                        return b;
                    }
                    zza(zzlt, zza(zznn), zzgl.zzbte.zzbry);
                }
                final String zzbrw = zzgl.zzbte.zzbrw;
                final String zzbrx = zzgl.zzbte.zzbrx;
                if (zzbrx != null) {
                    zzlt.loadDataWithBaseURL(zzbrx, zzbrw, "text/html", "UTF-8", null);
                }
                else {
                    zzlt.loadData(zzbrw, "text/html", "UTF-8");
                }
                b = true;
            }
        }
        return b;
    }
    
    @Nullable
    private static zzdx zze(final Object o) {
        zzdx zzab;
        if (o instanceof IBinder) {
            zzab = zzdx.zza.zzab((IBinder)o);
        }
        else {
            zzab = null;
        }
        return zzab;
    }
    
    @Nullable
    public static View zzg(@Nullable final zzke zzke) {
        View view;
        if (zzke == null) {
            zzb.e("AdState is null");
            view = null;
        }
        else if (zzh(zzke) && zzke.zzbyh != null) {
            view = zzke.zzbyh.getView();
        }
        else {
            while (true) {
                while (true) {
                    Label_0097: {
                        try {
                            if (zzke.zzbtf == null) {
                                break Label_0097;
                            }
                            final com.google.android.gms.dynamic.zzd view2 = zzke.zzbtf.getView();
                            if (view2 == null) {
                                zzb.zzdf("View in mediation adapter is null.");
                                view = null;
                            }
                            else {
                                view = (View)com.google.android.gms.dynamic.zze.zzae(view2);
                            }
                        }
                        catch (RemoteException ex) {
                            zzb.zzd("Could not get View from mediation adapter.", (Throwable)ex);
                            view = null;
                        }
                        break;
                    }
                    final com.google.android.gms.dynamic.zzd view2 = null;
                    continue;
                }
            }
        }
        return view;
    }
    
    public static boolean zzh(@Nullable final zzke zzke) {
        return zzke != null && zzke.zzchc && zzke.zzbte != null && zzke.zzbte.zzbrw != null;
    }
}
