// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzlj;
import com.google.android.gms.internal.zzkq;
import com.google.android.gms.internal.zzkt;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzkm;
import android.view.View;
import android.view.Window;
import android.graphics.Point;
import android.graphics.Rect;
import android.app.Activity;
import com.google.android.gms.internal.zzlu;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.internal.zzdq;
import com.google.android.gms.internal.zzfz;
import com.google.android.gms.internal.zzhn;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.internal.zzer;
import com.google.android.gms.internal.zzlt;
import android.support.annotation.Nullable;
import android.graphics.Bitmap;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.overlay.zzg;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import java.util.concurrent.Future;
import android.os.RemoteException;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.common.internal.zzac;
import org.json.JSONException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzgh;
import com.google.android.gms.ads.internal.safebrowsing.SafeBrowsingConfigParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import java.util.List;
import com.google.android.gms.internal.zzgg;
import java.util.Collections;
import org.json.JSONObject;
import com.google.android.gms.internal.zzjc;
import com.google.android.gms.internal.zzke;
import android.os.Bundle;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.content.Context;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzfe;
import com.google.android.gms.internal.zzez;

@zziy
public class zzl extends zzc implements zzez, zzfe.zza
{
    protected transient boolean zzanp;
    private int zzanq;
    private boolean zzanr;
    private float zzans;
    
    public zzl(final Context context, final AdSizeParcel adSizeParcel, final String s, final zzgq zzgq, final VersionInfoParcel versionInfoParcel, final zzd zzd) {
        super(context, adSizeParcel, s, zzgq, versionInfoParcel, zzd);
        this.zzanq = -1;
        this.zzanp = false;
    }
    
    private void zzb(final Bundle bundle) {
        com.google.android.gms.ads.internal.zzu.zzfz().zzb(this.zzall.zzahn, this.zzall.zzaqv.zzcs, "gmob-apps", bundle, false);
    }
    
    static zzke.zza zzc(final zzke.zza zza) {
        try {
            final String string = zzjc.zzc(zza.zzcop).toString();
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("pubid", (Object)zza.zzcix.zzaqt);
            final zzgg zzgg = new zzgg(string, null, Collections.singletonList("com.google.ads.mediation.admob.AdMobAdapter"), null, null, Collections.emptyList(), Collections.emptyList(), jsonObject.toString(), null, Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList());
            final AdResponseParcel zzcop = zza.zzcop;
            final Object o = new zzke.zza(zza.zzcix, new AdResponseParcel(zza.zzcix, zzcop.zzbyj, zzcop.body, Collections.emptyList(), Collections.emptyList(), zzcop.zzchb, true, zzcop.zzchd, Collections.emptyList(), zzcop.zzbsj, zzcop.orientation, zzcop.zzchf, zzcop.zzchg, zzcop.zzchh, zzcop.zzchi, zzcop.zzchj, null, zzcop.zzchl, zzcop.zzaxl, zzcop.zzcgc, zzcop.zzchm, zzcop.zzchn, zzcop.zzchq, zzcop.zzaxm, zzcop.zzaxn, null, Collections.emptyList(), Collections.emptyList(), zzcop.zzchu, zzcop.zzchv, zzcop.zzcgt, zzcop.zzcgu, zzcop.zzbsg, zzcop.zzbsh, zzcop.zzchw, null, zzcop.zzchy), new zzgh(Collections.singletonList(zzgg), -1L, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), zzcop.zzbsg, zzcop.zzbsh, "", -1L, 0, 1, null, 0, -1, -1L, false), zza.zzaqz, zza.errorCode, zza.zzcoj, zza.zzcok, null);
            return (zzke.zza)o;
        }
        catch (JSONException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzb("Unable to generate ad state for an interstitial ad with pooling.", (Throwable)ex);
            final Object o = zza;
            return (zzke.zza)o;
        }
    }
    
    @Override
    public void showInterstitial() {
        zzac.zzhq("showInterstitial must be called on the main UI thread.");
        if (this.zzall.zzara == null) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("The interstitial has not loaded.");
        }
        else {
            if (zzdi.zzbdy.get()) {
                String s;
                if (this.zzall.zzahn.getApplicationContext() != null) {
                    s = this.zzall.zzahn.getApplicationContext().getPackageName();
                }
                else {
                    s = this.zzall.zzahn.getPackageName();
                }
                if (!this.zzanp) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzdf("It is not recommended to show an interstitial before onAdLoaded completes.");
                    final Bundle bundle = new Bundle();
                    bundle.putString("appid", s);
                    bundle.putString("action", "show_interstitial_before_load_finish");
                    this.zzb(bundle);
                }
                if (!com.google.android.gms.ads.internal.zzu.zzfz().zzai(this.zzall.zzahn)) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzdf("It is not recommended to show an interstitial when app is not in foreground.");
                    final Bundle bundle2 = new Bundle();
                    bundle2.putString("appid", s);
                    bundle2.putString("action", "show_interstitial_app_not_in_foreground");
                    this.zzb(bundle2);
                }
            }
            if (!this.zzall.zzhd()) {
                if (this.zzall.zzara.zzchc && this.zzall.zzara.zzbtf != null) {
                    try {
                        this.zzall.zzara.zzbtf.showInterstitial();
                    }
                    catch (RemoteException ex) {
                        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not show interstitial.", (Throwable)ex);
                        this.zzfa();
                    }
                }
                else if (this.zzall.zzara.zzbyh == null) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzdf("The interstitial failed to load.");
                }
                else if (this.zzall.zzara.zzbyh.zzvv()) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzdf("The interstitial is already showing.");
                }
                else {
                    this.zzall.zzara.zzbyh.zzaj(true);
                    if (this.zzall.zzara.zzcod != null) {
                        this.zzaln.zza(this.zzall.zzaqz, this.zzall.zzara);
                    }
                    Bitmap zzaj;
                    if (this.zzall.zzanx) {
                        zzaj = com.google.android.gms.ads.internal.zzu.zzfz().zzaj(this.zzall.zzahn);
                    }
                    else {
                        zzaj = null;
                    }
                    this.zzanq = com.google.android.gms.ads.internal.zzu.zzgu().zzb(zzaj);
                    if (zzdi.zzbfn.get() && zzaj != null) {
                        final Future future = (Future)new zza(this.zzanq).zzqw();
                    }
                    else {
                        final InterstitialAdParameterParcel interstitialAdParameterParcel = new InterstitialAdParameterParcel(this.zzall.zzanx, this.zzez(), false, 0.0f, -1);
                        int n = this.zzall.zzara.zzbyh.getRequestedOrientation();
                        if (n == -1) {
                            n = this.zzall.zzara.orientation;
                        }
                        com.google.android.gms.ads.internal.zzu.zzfx().zza(this.zzall.zzahn, new AdOverlayInfoParcel(this, this, this, this.zzall.zzara.zzbyh, n, this.zzall.zzaqv, this.zzall.zzara.zzchh, interstitialAdParameterParcel));
                    }
                }
            }
        }
    }
    
    @Override
    protected zzlt zza(final zzke.zza zza, @Nullable final zze zze, @Nullable final com.google.android.gms.ads.internal.safebrowsing.zzc zzc) {
        final zzlt zza2 = com.google.android.gms.ads.internal.zzu.zzga().zza(this.zzall.zzahn, this.zzall.zzaqz, false, false, this.zzall.zzaqu, this.zzall.zzaqv, this.zzalg, this, this.zzalo);
        zza2.zzvr().zza(this, null, this, this, zzdi.zzbcp.get(), this, this, zze, null, zzc);
        this.zza(zza2);
        zza2.zzdh(zza.zzcix.zzcgj);
        zzfe.zza(zza2, (zzfe.zza)this);
        return zza2;
    }
    
    public void zza(final zzke.zza zza, final zzdq zzdq) {
        int n = 1;
        if (!zzdi.zzbdi.get()) {
            super.zza(zza, zzdq);
        }
        else if (zza.errorCode != -2) {
            super.zza(zza, zzdq);
        }
        else {
            final Bundle bundle = zza.zzcix.zzcfu.zzawn.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
            int n2;
            if (bundle == null || !bundle.containsKey("gw")) {
                n2 = n;
            }
            else {
                n2 = 0;
            }
            if (zza.zzcop.zzchc) {
                n = 0;
            }
            if (n2 != 0 && n != 0) {
                this.zzall.zzarb = zzc(zza);
            }
            super.zza(this.zzall.zzarb, zzdq);
        }
    }
    
    @Override
    public void zza(final boolean zzanr, final float zzans) {
        this.zzanr = zzanr;
        this.zzans = zzans;
    }
    
    @Override
    public boolean zza(final AdRequestParcel adRequestParcel, final zzdq zzdq) {
        boolean zza;
        if (this.zzall.zzara != null) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("An interstitial is already loading. Aborting.");
            zza = false;
        }
        else {
            zza = super.zza(adRequestParcel, zzdq);
        }
        return zza;
    }
    
    @Override
    protected boolean zza(final AdRequestParcel adRequestParcel, final zzke zzke, final boolean b) {
        if (this.zzall.zzhc() && zzke.zzbyh != null) {
            com.google.android.gms.ads.internal.zzu.zzgb().zzl(zzke.zzbyh);
        }
        return this.zzalk.zzfl();
    }
    
    public boolean zza(@Nullable final zzke zzke, final zzke zzke2) {
        boolean b;
        if (!super.zza(zzke, zzke2)) {
            b = false;
        }
        else {
            if (!this.zzall.zzhc() && this.zzall.zzaru != null && zzke2.zzcod != null) {
                this.zzaln.zza(this.zzall.zzaqz, zzke2, this.zzall.zzaru);
            }
            b = true;
        }
        return b;
    }
    
    @Override
    public void zzb(RewardItemParcel zzchr) {
        if (this.zzall.zzara != null) {
            if (this.zzall.zzara.zzcht != null) {
                com.google.android.gms.ads.internal.zzu.zzfz().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, this.zzall.zzara.zzcht);
            }
            if (this.zzall.zzara.zzchr != null) {
                zzchr = this.zzall.zzara.zzchr;
            }
        }
        this.zza(zzchr);
    }
    
    @Override
    protected void zzdx() {
        this.zzfa();
        super.zzdx();
    }
    
    @Override
    protected void zzea() {
        super.zzea();
        this.zzanp = true;
    }
    
    @Override
    public void zzee() {
        this.recordImpression();
        super.zzee();
        if (this.zzall.zzara != null && this.zzall.zzara.zzbyh != null) {
            final zzlu zzvr = this.zzall.zzara.zzbyh.zzvr();
            if (zzvr != null) {
                zzvr.zzwo();
            }
        }
    }
    
    protected boolean zzez() {
        final boolean b = this.zzall.zzahn instanceof Activity;
        boolean b2 = false;
        if (b) {
            final Window window = ((Activity)this.zzall.zzahn).getWindow();
            b2 = false;
            if (window != null) {
                final View decorView = window.getDecorView();
                b2 = false;
                if (decorView != null) {
                    final Rect rect = new Rect();
                    final Rect rect2 = new Rect();
                    window.getDecorView().getGlobalVisibleRect(rect, (Point)null);
                    window.getDecorView().getWindowVisibleDisplayFrame(rect2);
                    b2 = (rect.bottom != 0 && rect2.bottom != 0 && rect.top == rect2.top);
                }
            }
        }
        return b2;
    }
    
    public void zzfa() {
        com.google.android.gms.ads.internal.zzu.zzgu().zzb(Integer.valueOf(this.zzanq));
        if (this.zzall.zzhc()) {
            this.zzall.zzgz();
            this.zzall.zzara = null;
            this.zzall.zzanx = false;
            this.zzanp = false;
        }
    }
    
    @Override
    public void zzfb() {
        if (this.zzall.zzara != null && this.zzall.zzara.zzcoi != null) {
            com.google.android.gms.ads.internal.zzu.zzfz().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, this.zzall.zzara.zzcoi);
        }
        this.zzeb();
    }
    
    @Override
    public void zzg(final boolean zzanx) {
        this.zzall.zzanx = zzanx;
    }
    
    @zziy
    private class zza extends zzkm
    {
        private final int zzant;
        
        public zza(final int zzant) {
            this.zzant = zzant;
        }
        
        @Override
        public void onStop() {
        }
        
        @Override
        public void zzfc() {
            final boolean zzanx = zzl.this.zzall.zzanx;
            final boolean zzez = zzl.this.zzez();
            final boolean zza = zzl.this.zzanr;
            final float zzb = zzl.this.zzans;
            int zzant;
            if (zzl.this.zzall.zzanx) {
                zzant = this.zzant;
            }
            else {
                zzant = -1;
            }
            final InterstitialAdParameterParcel interstitialAdParameterParcel = new InterstitialAdParameterParcel(zzanx, zzez, zza, zzb, zzant);
            final int requestedOrientation = zzl.this.zzall.zzara.zzbyh.getRequestedOrientation();
            int orientation;
            if (requestedOrientation == -1) {
                orientation = zzl.this.zzall.zzara.orientation;
            }
            else {
                orientation = requestedOrientation;
            }
            zzkr.zzcrf.post((Runnable)new Runnable() {
                final /* synthetic */ AdOverlayInfoParcel zzanv = new AdOverlayInfoParcel(zzl.this, zzl.this, zzl.this, zzl.this.zzall.zzara.zzbyh, orientation, zzl.this.zzall.zzaqv, zzl.this.zzall.zzara.zzchh, interstitialAdParameterParcel);
                
                @Override
                public void run() {
                    com.google.android.gms.ads.internal.zzu.zzfx().zza(zzl.this.zzall.zzahn, this.zzanv);
                }
            });
        }
    }
}
