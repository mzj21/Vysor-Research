// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.ViewGroup;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.client.zzab;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.internal.zzly;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzlu;
import com.google.android.gms.internal.zzck;
import android.view.View;
import com.google.android.gms.internal.zzlt;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.content.Context;
import com.google.android.gms.internal.zziy;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

@zziy
public class zzf extends zzc implements ViewTreeObserver$OnGlobalLayoutListener, ViewTreeObserver$OnScrollChangedListener
{
    private boolean zzami;
    
    public zzf(final Context context, final AdSizeParcel adSizeParcel, final String s, final zzgq zzgq, final VersionInfoParcel versionInfoParcel, final zzd zzd) {
        super(context, adSizeParcel, s, zzgq, versionInfoParcel, zzd);
    }
    
    private AdSizeParcel zzb(final zzke.zza zza) {
        AdSizeParcel zzaqz;
        if (zza.zzcop.zzaxm) {
            zzaqz = this.zzall.zzaqz;
        }
        else {
            final String zzchf = zza.zzcop.zzchf;
            AdSize zzjd;
            if (zzchf != null) {
                final String[] split = zzchf.split("[xX]");
                split[0] = split[0].trim();
                split[1] = split[1].trim();
                zzjd = new AdSize(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            }
            else {
                zzjd = this.zzall.zzaqz.zzjd();
            }
            zzaqz = new AdSizeParcel(this.zzall.zzahn, zzjd);
        }
        return zzaqz;
    }
    
    private boolean zzb(@Nullable final zzke zzke, final zzke zzke2) {
        boolean b = false;
        Label_0087: {
            final View zzg;
            if (zzke2.zzchc) {
                zzg = zzn.zzg(zzke2);
                if (zzg == null) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzdf("Could not get mediation view");
                    b = false;
                    return b;
                }
                final View nextView = this.zzall.zzaqw.getNextView();
                if (nextView != null) {
                    if (nextView instanceof zzlt) {
                        ((zzlt)nextView).destroy();
                    }
                    this.zzall.zzaqw.removeView(nextView);
                }
                if (zzn.zzh(zzke2)) {
                    break Label_0087;
                }
            }
            else {
                if (zzke2.zzcoh != null && zzke2.zzbyh != null) {
                    zzke2.zzbyh.zza(zzke2.zzcoh);
                    this.zzall.zzaqw.removeAllViews();
                    this.zzall.zzaqw.setMinimumWidth(zzke2.zzcoh.widthPixels);
                    this.zzall.zzaqw.setMinimumHeight(zzke2.zzcoh.heightPixels);
                    this.zzb(zzke2.zzbyh.getView());
                    break Label_0087;
                }
                break Label_0087;
            }
            while (true) {
                while (true) {
                    View nextView2 = null;
                    Label_0287: {
                        try {
                            this.zzb(zzg);
                            if (this.zzall.zzaqw.getChildCount() > 1) {
                                this.zzall.zzaqw.showNext();
                            }
                            if (zzke != null) {
                                nextView2 = this.zzall.zzaqw.getNextView();
                                if (!(nextView2 instanceof zzlt)) {
                                    break Label_0287;
                                }
                                ((zzlt)nextView2).zza(this.zzall.zzahn, this.zzall.zzaqz, this.zzalg);
                                this.zzall.zzhb();
                            }
                            this.zzall.zzaqw.setVisibility(0);
                            b = true;
                        }
                        catch (Throwable t) {
                            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not add mediation view to view hierarchy.", t);
                            b = false;
                        }
                        break;
                    }
                    if (nextView2 != null) {
                        this.zzall.zzaqw.removeView(nextView2);
                        continue;
                    }
                    continue;
                }
            }
        }
        return b;
    }
    
    private void zze(final zzke zzke) {
        if (this.zzall.zzhc()) {
            if (zzke.zzbyh != null) {
                if (zzke.zzcod != null) {
                    this.zzaln.zza(this.zzall.zzaqz, zzke);
                }
                if (zzke.zzib()) {
                    this.zzaln.zza(this.zzall.zzaqz, zzke).zza(zzke.zzbyh);
                }
                else {
                    zzke.zzbyh.zzvr().zza((zzlu.zzc)new zzlu.zzc() {
                        @Override
                        public void zzet() {
                            zzf.this.zzaln.zza(zzf.this.zzall.zzaqz, zzke).zza(zzke.zzbyh);
                        }
                    });
                }
            }
        }
        else if (this.zzall.zzaru != null && zzke.zzcod != null) {
            this.zzaln.zza(this.zzall.zzaqz, zzke, this.zzall.zzaru);
        }
    }
    
    public void onGlobalLayout() {
        this.zzf(this.zzall.zzara);
    }
    
    public void onScrollChanged() {
        this.zzf(this.zzall.zzara);
    }
    
    public void setManualImpressionsEnabled(final boolean zzami) {
        zzac.zzhq("setManualImpressionsEnabled must be called from the main thread.");
        this.zzami = zzami;
    }
    
    public void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by BannerAdManager.");
    }
    
    @Override
    protected zzlt zza(final zzke.zza zza, @Nullable final zze zze, @Nullable final com.google.android.gms.ads.internal.safebrowsing.zzc zzc) {
        if (this.zzall.zzaqz.zzaxk == null && this.zzall.zzaqz.zzaxm) {
            this.zzall.zzaqz = this.zzb(zza);
        }
        return super.zza(zza, zze, zzc);
    }
    
    protected void zza(@Nullable final zzke zzke, final boolean b) {
        super.zza(zzke, b);
        if (zzn.zzh(zzke)) {
            zzn.zza(zzke, new zza());
        }
    }
    
    public boolean zza(@Nullable final zzke zzke, final zzke zzke2) {
        boolean b;
        if (!super.zza(zzke, zzke2)) {
            b = false;
        }
        else if (this.zzall.zzhc() && !this.zzb(zzke, zzke2)) {
            this.zzh(0);
            b = false;
        }
        else {
            if (zzke2.zzchu) {
                this.zzf(zzke2);
                com.google.android.gms.ads.internal.zzu.zzgx().zza((View)this.zzall.zzaqw, (ViewTreeObserver$OnGlobalLayoutListener)this);
                com.google.android.gms.ads.internal.zzu.zzgx().zza((View)this.zzall.zzaqw, (ViewTreeObserver$OnScrollChangedListener)this);
                if (!zzke2.zzcoe) {
                    final Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            zzf.this.zzf(zzf.this.zzall.zzara);
                        }
                    };
                    zzlu zzvr;
                    if (zzke2.zzbyh != null) {
                        zzvr = zzke2.zzbyh.zzvr();
                    }
                    else {
                        zzvr = null;
                    }
                    if (zzvr != null) {
                        zzvr.zza((zzlu.zze)new zzlu.zze() {
                            @Override
                            public void zzes() {
                                if (!zzke2.zzcoe) {
                                    com.google.android.gms.ads.internal.zzu.zzfz();
                                    zzkr.zzb(runnable);
                                }
                            }
                        });
                    }
                }
            }
            else if (!this.zzall.zzhd() || zzdi.zzbfu.get()) {
                this.zza(zzke2, false);
            }
            zzly zzwb;
            if (zzke2.zzbyh != null) {
                zzwb = zzke2.zzbyh.zzwb();
                final zzlu zzvr2 = zzke2.zzbyh.zzvr();
                if (zzvr2 != null) {
                    zzvr2.zzwo();
                }
            }
            else {
                zzwb = null;
            }
            if (this.zzall.zzaro != null && zzwb != null) {
                zzwb.zzap(this.zzall.zzaro.zzbac);
            }
            this.zze(zzke2);
            b = true;
        }
        return b;
    }
    
    public boolean zzb(final AdRequestParcel adRequestParcel) {
        return super.zzb(this.zze(adRequestParcel));
    }
    
    @Nullable
    public zzab zzdw() {
        zzac.zzhq("getVideoController must be called from the main thread.");
        zzly zzwb;
        if (this.zzall.zzara != null && this.zzall.zzara.zzbyh != null) {
            zzwb = this.zzall.zzara.zzbyh.zzwb();
        }
        else {
            zzwb = null;
        }
        return zzwb;
    }
    
    AdRequestParcel zze(AdRequestParcel adRequestParcel) {
        if (adRequestParcel.zzawi != this.zzami) {
            adRequestParcel = new AdRequestParcel(adRequestParcel.versionCode, adRequestParcel.zzawd, adRequestParcel.extras, adRequestParcel.zzawe, adRequestParcel.zzawf, adRequestParcel.zzawg, adRequestParcel.zzawh, adRequestParcel.zzawi || this.zzami, adRequestParcel.zzawj, adRequestParcel.zzawk, adRequestParcel.zzawl, adRequestParcel.zzawm, adRequestParcel.zzawn, adRequestParcel.zzawo, adRequestParcel.zzawp, adRequestParcel.zzawq, adRequestParcel.zzawr, adRequestParcel.zzaws);
        }
        return adRequestParcel;
    }
    
    protected boolean zzec() {
        boolean b = true;
        if (!com.google.android.gms.ads.internal.zzu.zzfz().zza(this.zzall.zzahn.getPackageManager(), this.zzall.zzahn.getPackageName(), "android.permission.INTERNET")) {
            zzm.zzjr().zza((ViewGroup)this.zzall.zzaqw, this.zzall.zzaqz, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
            b = false;
        }
        if (!com.google.android.gms.ads.internal.zzu.zzfz().zzac(this.zzall.zzahn)) {
            zzm.zzjr().zza((ViewGroup)this.zzall.zzaqw, this.zzall.zzaqz, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
            b = false;
        }
        if (!b && this.zzall.zzaqw != null) {
            this.zzall.zzaqw.setVisibility(0);
        }
        return b;
    }
    
    void zzf(@Nullable final zzke zzke) {
        if (zzke != null && !zzke.zzcoe && this.zzall.zzaqw != null && com.google.android.gms.ads.internal.zzu.zzfz().zza((View)this.zzall.zzaqw, this.zzall.zzahn) && this.zzall.zzaqw.getGlobalVisibleRect(new Rect(), (Point)null)) {
            if (zzke != null && zzke.zzbyh != null && zzke.zzbyh.zzvr() != null) {
                zzke.zzbyh.zzvr().zza((zzlu.zze)null);
            }
            this.zza(zzke, false);
            zzke.zzcoe = true;
        }
    }
    
    public class zza
    {
        public void onClick() {
            zzf.this.onAdClicked();
        }
    }
}
