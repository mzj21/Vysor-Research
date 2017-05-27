// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.support.customtabs.CustomTabsIntent;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzg;
import com.google.android.gms.ads.internal.overlay.AdLauncherIntentInfoParcel;
import android.support.customtabs.CustomTabsIntent$Builder;
import android.text.TextUtils;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import android.os.Bundle;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.content.Context;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import android.app.Activity;
import android.net.Uri;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;

@zziy
public class zzhf implements MediationInterstitialAdapter
{
    private Uri mUri;
    private Activity zzbul;
    private zzdw zzbum;
    private MediationInterstitialListener zzbun;
    
    public static boolean zzp(final Context context) {
        return zzdw.zzo(context);
    }
    
    @Override
    public void onDestroy() {
        zzb.zzdd("Destroying AdMobCustomTabsAdapter adapter.");
        try {
            this.zzbum.zzd(this.zzbul);
        }
        catch (Exception ex) {
            zzb.zzb("Exception while unbinding from CustomTabsService.", ex);
        }
    }
    
    @Override
    public void onPause() {
        zzb.zzdd("Pausing AdMobCustomTabsAdapter adapter.");
    }
    
    @Override
    public void onResume() {
        zzb.zzdd("Resuming AdMobCustomTabsAdapter adapter.");
    }
    
    @Override
    public void requestInterstitialAd(final Context context, final MediationInterstitialListener zzbun, final Bundle bundle, final MediationAdRequest mediationAdRequest, final Bundle bundle2) {
        this.zzbun = zzbun;
        if (this.zzbun == null) {
            zzb.zzdf("Listener not set for mediation. Returning.");
        }
        else if (!(context instanceof Activity)) {
            zzb.zzdf("AdMobCustomTabs can only work with Activity context. Bailing out.");
            this.zzbun.onAdFailedToLoad(this, 0);
        }
        else if (!zzp(context)) {
            zzb.zzdf("Default browser does not support custom tabs. Bailing out.");
            this.zzbun.onAdFailedToLoad(this, 0);
        }
        else {
            final String string = bundle.getString("tab_url");
            if (TextUtils.isEmpty((CharSequence)string)) {
                zzb.zzdf("The tab_url retrieved from mediation metadata is empty. Bailing out.");
                this.zzbun.onAdFailedToLoad(this, 0);
            }
            else {
                this.zzbul = (Activity)context;
                this.mUri = Uri.parse(string);
                (this.zzbum = new zzdw()).zza((zzdw.zza)new zzdw.zza() {
                    @Override
                    public void zzlh() {
                        zzb.zzdd("Hinting CustomTabsService for the load of the new url.");
                    }
                    
                    @Override
                    public void zzli() {
                        zzb.zzdd("Disconnecting from CustomTabs service.");
                    }
                });
                this.zzbum.zze(this.zzbul);
                this.zzbun.onAdLoaded(this);
            }
        }
    }
    
    @Override
    public void showInterstitial() {
        final CustomTabsIntent build = new CustomTabsIntent$Builder(this.zzbum.zzlf()).build();
        build.intent.setData(this.mUri);
        zzkr.zzcrf.post((Runnable)new Runnable() {
            final /* synthetic */ AdOverlayInfoParcel zzanv = new AdOverlayInfoParcel(new AdLauncherIntentInfoParcel(build.intent), null, new zzg(this) {
                @Override
                public void onPause() {
                    zzb.zzdd("AdMobCustomTabsAdapter overlay is paused.");
                }
                
                @Override
                public void onResume() {
                    zzb.zzdd("AdMobCustomTabsAdapter overlay is resumed.");
                }
                
                @Override
                public void zzed() {
                    zzb.zzdd("AdMobCustomTabsAdapter overlay is closed.");
                    zzhf.this.zzbun.onAdClosed(zzhf.this);
                    zzhf.this.zzbum.zzd(zzhf.this.zzbul);
                }
                
                @Override
                public void zzee() {
                    zzb.zzdd("Opening AdMobCustomTabsAdapter overlay.");
                    zzhf.this.zzbun.onAdOpened(zzhf.this);
                }
            }, null, new VersionInfoParcel(0, 0, false));
            
            @Override
            public void run() {
                zzu.zzfx().zza((Context)zzhf.this.zzbul, this.zzanv);
            }
        });
        zzu.zzgd().zzag(false);
    }
}
