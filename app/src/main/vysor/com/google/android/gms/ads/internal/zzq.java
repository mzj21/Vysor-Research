// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzfz;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzei;
import com.google.android.gms.internal.zzeh;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.internal.zzej;
import com.google.android.gms.ads.internal.formats.zzh;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.internal.zzil;
import com.google.android.gms.internal.zzgj;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzgg;
import com.google.android.gms.internal.zzdq;
import com.google.android.gms.internal.zzhx;
import com.google.android.gms.internal.zzdu;
import com.google.android.gms.ads.internal.formats.zzi;
import com.google.android.gms.ads.internal.formats.zzg;
import com.google.android.gms.common.internal.zzac;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.ads.internal.formats.zzf;
import com.google.android.gms.internal.zzek;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.ads.internal.formats.zze;
import com.google.android.gms.internal.zzgv;
import android.os.RemoteException;
import com.google.android.gms.internal.zzdx;
import java.util.List;
import android.view.View;
import com.google.android.gms.ads.internal.formats.zza;
import com.google.android.gms.internal.zzgu;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.content.Context;
import com.google.android.gms.internal.zzlt;
import com.google.android.gms.internal.zziy;

@zziy
public class zzq extends zzb
{
    private zzlt zzaoq;
    
    public zzq(final Context context, final zzd zzd, final AdSizeParcel adSizeParcel, final String s, final zzgq zzgq, final VersionInfoParcel versionInfoParcel) {
        super(context, adSizeParcel, s, zzgq, versionInfoParcel, zzd);
    }
    
    private static com.google.android.gms.ads.internal.formats.zzd zza(final zzgu zzgu) throws RemoteException {
        final String headline = zzgu.getHeadline();
        final List images = zzgu.getImages();
        final String body = zzgu.getBody();
        zzdx zzlo;
        if (zzgu.zzlo() != null) {
            zzlo = zzgu.zzlo();
        }
        else {
            zzlo = null;
        }
        return new com.google.android.gms.ads.internal.formats.zzd(headline, images, body, zzlo, zzgu.getCallToAction(), zzgu.getStarRating(), zzgu.getStore(), zzgu.getPrice(), null, zzgu.getExtras(), zzgu.zzdw(), null);
    }
    
    private static zze zza(final zzgv zzgv) throws RemoteException {
        final String headline = zzgv.getHeadline();
        final List images = zzgv.getImages();
        final String body = zzgv.getBody();
        zzdx zzlt;
        if (zzgv.zzlt() != null) {
            zzlt = zzgv.zzlt();
        }
        else {
            zzlt = null;
        }
        return new zze(headline, images, body, zzlt, zzgv.getCallToAction(), zzgv.getAdvertiser(), null, zzgv.getExtras());
    }
    
    private void zza(final com.google.android.gms.ads.internal.formats.zzd zzd) {
        zzkr.zzcrf.post((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    if (zzq.this.zzall.zzarj != null) {
                        zzq.this.zzall.zzarj.zza(zzd);
                    }
                }
                catch (RemoteException ex) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call OnAppInstallAdLoadedListener.onAppInstallAdLoaded().", (Throwable)ex);
                }
            }
        });
    }
    
    private void zza(final zze zze) {
        zzkr.zzcrf.post((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    if (zzq.this.zzall.zzark != null) {
                        zzq.this.zzall.zzark.zza(zze);
                    }
                }
                catch (RemoteException ex) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call OnContentAdLoadedListener.onContentAdLoaded().", (Throwable)ex);
                }
            }
        });
    }
    
    private void zza(final zzke zzke, final String s) {
        zzkr.zzcrf.post((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    zzq.this.zzall.zzarm.get(s).zza((zzef)zzke.zzcol);
                }
                catch (RemoteException ex) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call onCustomTemplateAdLoadedListener.onCustomTemplateAdLoaded().", (Throwable)ex);
                }
            }
        });
    }
    
    @Override
    public void pause() {
        throw new IllegalStateException("Native Ad DOES NOT support pause().");
    }
    
    @Override
    public void resume() {
        throw new IllegalStateException("Native Ad DOES NOT support resume().");
    }
    
    @Override
    public void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by NativeAdManager.");
    }
    
    public void zza(final SimpleArrayMap<String, zzek> zzarm) {
        zzac.zzhq("setOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        this.zzall.zzarm = zzarm;
    }
    
    public void zza(final com.google.android.gms.ads.internal.formats.zzg zzg) {
        if (this.zzaoq != null) {
            this.zzaoq.zzb(zzg);
        }
    }
    
    public void zza(final zzi zzi) {
        if (this.zzall.zzara.zzcod != null) {
            com.google.android.gms.ads.internal.zzu.zzgd().zztx().zza(this.zzall.zzaqz, this.zzall.zzara, zzi);
        }
    }
    
    @Override
    public void zza(final zzdu zzdu) {
        throw new IllegalStateException("CustomRendering is NOT supported by NativeAdManager.");
    }
    
    @Override
    public void zza(final zzhx zzhx) {
        throw new IllegalStateException("In App Purchase is NOT supported by NativeAdManager.");
    }
    
    public void zza(final zzke.zza zza, final zzdq zzdq) {
        if (zza.zzaqz != null) {
            this.zzall.zzaqz = zza.zzaqz;
        }
        if (zza.errorCode != -2) {
            zzkr.zzcrf.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    zzq.this.zzb(new zzke(zza, null, null, null, null, null, null, null));
                }
            });
        }
        else {
            this.zzall.zzarv = 0;
            this.zzall.zzaqy = com.google.android.gms.ads.internal.zzu.zzfy().zza(this.zzall.zzahn, this, zza, this.zzall.zzaqu, null, this.zzals, (zzil.zza)this, zzdq);
            final String value = String.valueOf(this.zzall.zzaqy.getClass().getName());
            String concat;
            if (value.length() != 0) {
                concat = "AdRenderer: ".concat(value);
            }
            else {
                concat = new String("AdRenderer: ");
            }
            com.google.android.gms.ads.internal.util.client.zzb.zzdd(concat);
        }
    }
    
    @Override
    protected boolean zza(final AdRequestParcel adRequestParcel, final zzke zzke, final boolean b) {
        return this.zzalk.zzfl();
    }
    
    @Override
    protected boolean zza(final zzke zzke, final zzke zzke2) {
        this.zzb((List<String>)null);
        if (!this.zzall.zzhc()) {
            throw new IllegalStateException("Native ad DOES NOT have custom rendering mode.");
        }
    Label_0128:
        while (true) {
            if (!zzke2.zzchc) {
                final zzi.zza zzcol = zzke2.zzcol;
                if (zzcol instanceof zze && this.zzall.zzark != null) {
                    this.zza((zze)zzke2.zzcol);
                    break Label_0128;
                }
                if (zzcol instanceof com.google.android.gms.ads.internal.formats.zzd && this.zzall.zzarj != null) {
                    this.zza((com.google.android.gms.ads.internal.formats.zzd)zzke2.zzcol);
                    break Label_0128;
                }
                if (zzcol instanceof zzf && this.zzall.zzarm != null && this.zzall.zzarm.get(((zzf)zzcol).getCustomTemplateId()) != null) {
                    this.zza(zzke2, ((zzf)zzcol).getCustomTemplateId());
                    break Label_0128;
                }
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("No matching listener for retrieved native ad template.");
                this.zzh(0);
                return false;
            }
            while (true) {
                Label_0226: {
                    try {
                        zzgu zznm;
                        if (zzke2.zzbtf != null) {
                            zznm = zzke2.zzbtf.zznm();
                        }
                        else {
                            zznm = null;
                        }
                        zzgv zznn;
                        if (zzke2.zzbtf != null) {
                            zznn = zzke2.zzbtf.zznn();
                        }
                        else {
                            zznn = null;
                        }
                        if (zznm != null && this.zzall.zzarj != null) {
                            final com.google.android.gms.ads.internal.formats.zzd zza2 = zza(zznm);
                            zza2.zzb(new zzh(this.zzall.zzahn, this, this.zzall.zzaqu, zznm, zza2));
                            this.zza(zza2);
                        }
                        else {
                            if (zznn == null || this.zzall.zzark == null) {
                                break Label_0226;
                            }
                            final zze zza3 = zza(zznn);
                            zza3.zzb(new zzh(this.zzall.zzahn, this, this.zzall.zzaqu, zznn, zza3));
                            this.zza(zza3);
                        }
                        return super.zza(zzke, zzke2);
                    }
                    catch (RemoteException ex) {
                        com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to get native ad mapper", (Throwable)ex);
                        return super.zza(zzke, zzke2);
                    }
                    continue Label_0128;
                }
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("No matching mapper/listener for retrieved native ad template.");
                this.zzh(0);
                return false;
            }
            break;
        }
    }
    
    public void zzb(final SimpleArrayMap<String, zzej> zzarl) {
        zzac.zzhq("setOnCustomClickListener must be called on the main UI thread.");
        this.zzall.zzarl = zzarl;
    }
    
    public void zzb(final NativeAdOptionsParcel zzarn) {
        zzac.zzhq("setNativeAdOptions must be called on the main UI thread.");
        this.zzall.zzarn = zzarn;
    }
    
    public void zzb(final zzeh zzarj) {
        zzac.zzhq("setOnAppInstallAdLoadedListener must be called on the main UI thread.");
        this.zzall.zzarj = zzarj;
    }
    
    public void zzb(final zzei zzark) {
        zzac.zzhq("setOnContentAdLoadedListener must be called on the main UI thread.");
        this.zzall.zzark = zzark;
    }
    
    public void zzb(@Nullable final List<String> zzarr) {
        zzac.zzhq("setNativeTemplates must be called on the main UI thread.");
        this.zzall.zzarr = zzarr;
    }
    
    public void zzc(final zzlt zzaoq) {
        this.zzaoq = zzaoq;
    }
    
    public void zzfh() {
        if (this.zzall.zzara != null && this.zzaoq != null) {
            com.google.android.gms.ads.internal.zzu.zzgd().zztx().zza(this.zzall.zzaqz, this.zzall.zzara, this.zzaoq.getView(), this.zzaoq);
        }
        else {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("Request to enable ActiveView before adState is available.");
        }
    }
    
    public SimpleArrayMap<String, zzek> zzfi() {
        zzac.zzhq("getOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        return this.zzall.zzarm;
    }
    
    public void zzfj() {
        if (this.zzaoq != null) {
            this.zzaoq.destroy();
            this.zzaoq = null;
        }
    }
    
    public void zzfk() {
        if (this.zzaoq != null && this.zzaoq.zzwb() != null && this.zzall.zzarn != null && this.zzall.zzarn.zzblf != null) {
            this.zzaoq.zzwb().zzap(this.zzall.zzarn.zzblf.zzbac);
        }
    }
    
    @Nullable
    public zzej zzx(final String s) {
        zzac.zzhq("getOnCustomClickListener must be called on the main UI thread.");
        return this.zzall.zzarl.get(s);
    }
}
