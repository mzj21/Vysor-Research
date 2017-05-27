// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.formats;

import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.zzu;
import android.text.TextUtils;
import com.google.android.gms.internal.zzlu;
import com.google.android.gms.internal.zzev;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.IBinder;
import android.view.MotionEvent;
import java.util.Iterator;
import android.view.View$OnTouchListener;
import java.util.Map;
import android.widget.ImageView;
import com.google.android.gms.internal.zzdx;
import android.os.RemoteException;
import android.widget.ImageView$ScaleType;
import com.google.android.gms.dynamic.zze;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import com.google.android.gms.internal.zzdi;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.view.View$OnClickListener;
import org.json.JSONException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzfz;
import com.google.android.gms.common.internal.zzac;
import android.view.View;
import java.lang.ref.WeakReference;
import com.google.android.gms.internal.zzlt;
import com.google.android.gms.internal.zzau;
import com.google.android.gms.internal.zzir;
import org.json.JSONObject;
import com.google.android.gms.ads.internal.zzq;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.content.Context;
import com.google.android.gms.internal.zziy;

@zziy
public class zzj implements zzi
{
    private final Context mContext;
    private final Object zzakd;
    @Nullable
    private final VersionInfoParcel zzanh;
    private final zzq zzbkj;
    @Nullable
    private final JSONObject zzbkm;
    @Nullable
    private final zzir zzbkn;
    @Nullable
    private final zza zzbko;
    private final zzau zzbkp;
    private boolean zzbkq;
    private zzlt zzbkr;
    private String zzbks;
    @Nullable
    private String zzbkt;
    private WeakReference<View> zzbku;
    
    public zzj(final Context mContext, final zzq zzbkj, @Nullable final zzir zzbkn, final zzau zzbkp, @Nullable final JSONObject zzbkm, @Nullable final zza zzbko, @Nullable final VersionInfoParcel zzanh, @Nullable final String zzbkt) {
        this.zzakd = new Object();
        this.zzbku = null;
        this.mContext = mContext;
        this.zzbkj = zzbkj;
        this.zzbkn = zzbkn;
        this.zzbkp = zzbkp;
        this.zzbkm = zzbkm;
        this.zzbko = zzbko;
        this.zzanh = zzanh;
        this.zzbkt = zzbkt;
    }
    
    @Override
    public Context getContext() {
        return this.mContext;
    }
    
    @Override
    public void recordImpression() {
        zzac.zzhq("recordImpression must be called on the main UI thread.");
        this.zzr(true);
        while (true) {
            try {
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("ad", (Object)this.zzbkm);
                jsonObject.put("ads_id", (Object)this.zzbkt);
                this.zzbkn.zza((zzir.zza)new zzir.zza() {
                    @Override
                    public void zze(final zzfz zzfz) {
                        zzfz.zza("google.afma.nativeAds.handleImpressionPing", jsonObject);
                    }
                });
                this.zzbkj.zza(this);
            }
            catch (JSONException ex) {
                zzb.zzb("Unable to create impression JSON.", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public com.google.android.gms.ads.internal.formats.zzb zza(final View$OnClickListener onClickListener) {
        final com.google.android.gms.ads.internal.formats.zza zzlr = this.zzbko.zzlr();
        com.google.android.gms.ads.internal.formats.zzb zzb;
        if (zzlr == null) {
            zzb = null;
        }
        else {
            final com.google.android.gms.ads.internal.formats.zzb zzb2 = new com.google.android.gms.ads.internal.formats.zzb(this.mContext, zzlr);
            zzb2.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
            zzb2.zzlm().setOnClickListener(onClickListener);
            zzb2.zzlm().setContentDescription((CharSequence)zzdi.zzbgd.get());
            zzb = zzb2;
        }
        return zzb;
    }
    
    public void zza(final View view, final zzg zzg) {
        if (this.zzbko instanceof zzd) {
            final zzd zzd = (zzd)this.zzbko;
            final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(-1, -1);
            if (zzd.zzls() != null) {
                ((FrameLayout)view).addView(zzd.zzls(), (ViewGroup$LayoutParams)frameLayout$LayoutParams);
                this.zzbkj.zza(zzg);
            }
            else if (zzd.getImages() != null && zzd.getImages().size() > 0) {
                final zzdx zze = this.zze(zzd.getImages().get(0));
                if (zze != null) {
                    try {
                        final com.google.android.gms.dynamic.zzd zzln = zze.zzln();
                        if (zzln != null) {
                            final Drawable imageDrawable = com.google.android.gms.dynamic.zze.zzae(zzln);
                            final ImageView zzmb = this.zzmb();
                            zzmb.setImageDrawable(imageDrawable);
                            zzmb.setScaleType(ImageView$ScaleType.CENTER_INSIDE);
                            ((FrameLayout)view).addView((View)zzmb, (ViewGroup$LayoutParams)frameLayout$LayoutParams);
                        }
                    }
                    catch (RemoteException ex) {
                        zzb.zzdf("Could not get drawable from image");
                    }
                }
            }
        }
    }
    
    @Override
    public void zza(final View view, final String s, @Nullable final JSONObject jsonObject, @Nullable final JSONObject jsonObject2, @Nullable final JSONObject jsonObject3) {
        while (true) {
            zzac.zzhq("performClick must be called on the main UI thread.");
            while (true) {
                Label_0271: {
                    try {
                        final JSONObject jsonObject4 = new JSONObject();
                        jsonObject4.put("asset", (Object)s);
                        jsonObject4.put("template", (Object)this.zzbko.zzlq());
                        final JSONObject jsonObject5 = new JSONObject();
                        jsonObject5.put("ad", (Object)this.zzbkm);
                        jsonObject5.put("click", (Object)jsonObject4);
                        if (this.zzbkj.zzx(this.zzbko.getCustomTemplateId()) == null) {
                            break Label_0271;
                        }
                        final boolean b = true;
                        jsonObject5.put("has_custom_click_handler", b);
                        if (jsonObject != null) {
                            jsonObject5.put("view_rectangles", (Object)jsonObject);
                        }
                        if (jsonObject2 != null) {
                            jsonObject5.put("click_point", (Object)jsonObject2);
                        }
                        if (jsonObject3 != null) {
                            jsonObject5.put("native_view_rectangle", (Object)jsonObject3);
                        }
                        try {
                            JSONObject optJSONObject = this.zzbkm.optJSONObject("tracking_urls_and_actions");
                            if (optJSONObject == null) {
                                optJSONObject = new JSONObject();
                            }
                            jsonObject4.put("click_signals", (Object)this.zzbkp.zzaw().zza(this.mContext, optJSONObject.optString("click_string"), view));
                            jsonObject5.put("ads_id", (Object)this.zzbkt);
                            this.zzbkn.zza((zzir.zza)new zzir.zza() {
                                @Override
                                public void zze(final zzfz zzfz) {
                                    zzfz.zza("google.afma.nativeAds.handleClickGmsg", jsonObject5);
                                }
                            });
                        }
                        catch (Exception ex) {
                            zzb.zzb("Exception obtaining click signals", ex);
                        }
                    }
                    catch (JSONException ex2) {
                        zzb.zzb("Unable to create click JSON.", (Throwable)ex2);
                    }
                    break;
                }
                final boolean b = false;
                continue;
            }
        }
    }
    
    public void zza(final View view, final Map<String, WeakReference<View>> map, final View$OnTouchListener view$OnTouchListener, final View$OnClickListener view$OnClickListener) {
        if (zzdi.zzbga.get()) {
            view.setOnTouchListener(view$OnTouchListener);
            view.setClickable(true);
            view.setOnClickListener(view$OnClickListener);
            final Iterator<Map.Entry<String, WeakReference<View>>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                final View view2 = iterator.next().getValue().get();
                if (view2 != null) {
                    view2.setOnTouchListener(view$OnTouchListener);
                    view2.setClickable(true);
                    view2.setOnClickListener(view$OnClickListener);
                }
            }
        }
    }
    
    @Override
    public void zza(final View view, final Map<String, WeakReference<View>> map, final JSONObject jsonObject, final JSONObject jsonObject2, final JSONObject jsonObject3) {
        zzac.zzhq("performClick must be called on the main UI thread.");
        for (final Map.Entry<String, WeakReference<View>> entry : map.entrySet()) {
            if (view.equals(entry.getValue().get())) {
                this.zza(view, entry.getKey(), jsonObject, jsonObject2, jsonObject3);
                return;
            }
        }
        if ("2".equals(this.zzbko.zzlq())) {
            this.zza(view, "2099", jsonObject, jsonObject2, jsonObject3);
            return;
        }
        if ("1".equals(this.zzbko.zzlq())) {
            this.zza(view, "1099", jsonObject, jsonObject2, jsonObject3);
        }
    }
    
    @Override
    public void zzb(final View view, final Map<String, WeakReference<View>> map) {
        view.setOnTouchListener((View$OnTouchListener)null);
        view.setClickable(false);
        view.setOnClickListener((View$OnClickListener)null);
        final Iterator<Map.Entry<String, WeakReference<View>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            final View view2 = iterator.next().getValue().get();
            if (view2 != null) {
                view2.setOnTouchListener((View$OnTouchListener)null);
                view2.setClickable(false);
                view2.setOnClickListener((View$OnClickListener)null);
            }
        }
    }
    
    @Override
    public void zzd(final MotionEvent motionEvent) {
        this.zzbkp.zza(motionEvent);
    }
    
    @Nullable
    zzdx zze(final Object o) {
        zzdx zzab;
        if (o instanceof IBinder) {
            zzab = zzdx.zza.zzab((IBinder)o);
        }
        else {
            zzab = null;
        }
        return zzab;
    }
    
    @Override
    public void zzg(final View view) {
        synchronized (this.zzakd) {
            if (this.zzbkq) {
                return;
            }
            if (!view.isShown()) {
                return;
            }
        }
        if (!(!view.getGlobalVisibleRect(new Rect(), (Point)null))) {
            this.recordImpression();
        }
        // monitorexit(o)
    }
    
    @Override
    public void zzh(final View view) {
        this.zzbku = new WeakReference<View>(view);
    }
    
    public zzlt zzlx() {
        this.zzbkr = this.zzma();
        this.zzbkr.getView().setVisibility(8);
        this.zzbkn.zza((zzir.zza)new zzir.zza() {
            @Override
            public void zze(final zzfz zzfz) {
                zzfz.zza("/loadHtml", new zzev() {
                    @Override
                    public void zza(final zzlt zzlt, final Map<String, String> map) {
                        zzj.this.zzbkr.zzvr().zza((zzlu.zza)new zzlu.zza() {
                            @Override
                            public void zza(final zzlt zzlt, final boolean b) {
                                zzj.this.zzbks = map.get("id");
                                final JSONObject jsonObject = new JSONObject();
                                try {
                                    jsonObject.put("messageType", (Object)"htmlLoaded");
                                    jsonObject.put("id", (Object)zzj.this.zzbks);
                                    zzfz.zzb("sendMessageToNativeJs", jsonObject);
                                }
                                catch (JSONException ex) {
                                    com.google.android.gms.ads.internal.util.client.zzb.zzb("Unable to dispatch sendMessageToNativeJs event", (Throwable)ex);
                                }
                            }
                        });
                        final String s = map.get("overlayHtml");
                        final String s2 = map.get("baseUrl");
                        if (TextUtils.isEmpty((CharSequence)s2)) {
                            zzj.this.zzbkr.loadData(s, "text/html", "UTF-8");
                        }
                        else {
                            zzj.this.zzbkr.loadDataWithBaseURL(s2, s, "text/html", "UTF-8", null);
                        }
                    }
                });
                zzfz.zza("/showOverlay", new zzev() {
                    @Override
                    public void zza(final zzlt zzlt, final Map<String, String> map) {
                        zzj.this.zzbkr.getView().setVisibility(0);
                    }
                });
                zzfz.zza("/hideOverlay", new zzev() {
                    @Override
                    public void zza(final zzlt zzlt, final Map<String, String> map) {
                        zzj.this.zzbkr.getView().setVisibility(8);
                    }
                });
                zzj.this.zzbkr.zzvr().zza("/hideOverlay", new zzev() {
                    @Override
                    public void zza(final zzlt zzlt, final Map<String, String> map) {
                        zzj.this.zzbkr.getView().setVisibility(8);
                    }
                });
                zzj.this.zzbkr.zzvr().zza("/sendMessageToSdk", new zzev() {
                    @Override
                    public void zza(final zzlt zzlt, final Map<String, String> map) {
                        final JSONObject jsonObject = new JSONObject();
                        Label_0071: {
                            try {
                                for (final String s : map.keySet()) {
                                    jsonObject.put(s, (Object)map.get(s));
                                }
                                break Label_0071;
                            }
                            catch (JSONException ex) {
                                zzb.zzb("Unable to dispatch sendMessageToNativeJs event", (Throwable)ex);
                            }
                            return;
                        }
                        jsonObject.put("id", (Object)zzj.this.zzbks);
                        zzfz.zzb("sendMessageToNativeJs", jsonObject);
                    }
                });
            }
        });
        return this.zzbkr;
    }
    
    @Override
    public View zzly() {
        View view;
        if (this.zzbku != null) {
            view = this.zzbku.get();
        }
        else {
            view = null;
        }
        return view;
    }
    
    public void zzlz() {
        if (this.zzbko instanceof zzd) {
            this.zzbkj.zzfj();
        }
    }
    
    zzlt zzma() {
        return zzu.zzga().zza(this.mContext, AdSizeParcel.zzk(this.mContext), false, false, this.zzbkp, this.zzanh);
    }
    
    ImageView zzmb() {
        return new ImageView(this.mContext);
    }
    
    protected void zzr(final boolean zzbkq) {
        this.zzbkq = zzbkq;
    }
}
