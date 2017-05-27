// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation;

import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import android.util.AttributeSet;
import com.google.android.gms.ads.BaseAdView;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAdView;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import android.location.Location;
import java.util.Iterator;
import java.util.Set;
import java.util.Date;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationAdapter;
import android.view.View;
import android.os.Bundle;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import android.content.Context;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzmh;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;

@zziy
public abstract class AbstractAdViewAdapter implements MediationBannerAdapter, MediationNativeAdapter, MediationRewardedVideoAdAdapter, zzmh
{
    public static final String AD_UNIT_ID_PARAMETER = "pubid";
    protected AdView zzfz;
    protected InterstitialAd zzga;
    private AdLoader zzgb;
    private Context zzgc;
    private InterstitialAd zzgd;
    private MediationRewardedVideoAdListener zzge;
    final RewardedVideoAdListener zzgf;
    
    public AbstractAdViewAdapter() {
        this.zzgf = new RewardedVideoAdListener() {
            @Override
            public void onRewarded(final RewardItem rewardItem) {
                AbstractAdViewAdapter.this.zzge.onRewarded(AbstractAdViewAdapter.this, rewardItem);
            }
            
            @Override
            public void onRewardedVideoAdClosed() {
                AbstractAdViewAdapter.this.zzge.onAdClosed(AbstractAdViewAdapter.this);
                AbstractAdViewAdapter.this.zzgd = null;
            }
            
            @Override
            public void onRewardedVideoAdFailedToLoad(final int n) {
                AbstractAdViewAdapter.this.zzge.onAdFailedToLoad(AbstractAdViewAdapter.this, n);
            }
            
            @Override
            public void onRewardedVideoAdLeftApplication() {
                AbstractAdViewAdapter.this.zzge.onAdLeftApplication(AbstractAdViewAdapter.this);
            }
            
            @Override
            public void onRewardedVideoAdLoaded() {
                AbstractAdViewAdapter.this.zzge.onAdLoaded(AbstractAdViewAdapter.this);
            }
            
            @Override
            public void onRewardedVideoAdOpened() {
                AbstractAdViewAdapter.this.zzge.onAdOpened(AbstractAdViewAdapter.this);
            }
            
            @Override
            public void onRewardedVideoStarted() {
                AbstractAdViewAdapter.this.zzge.onVideoStarted(AbstractAdViewAdapter.this);
            }
        };
    }
    
    public String getAdUnitId(final Bundle bundle) {
        return bundle.getString("pubid");
    }
    
    @Override
    public View getBannerView() {
        return (View)this.zzfz;
    }
    
    @Override
    public Bundle getInterstitialAdapterInfo() {
        return new MediationAdapter.zza().zzbh(1).zzxg();
    }
    
    @Override
    public void initialize(final Context context, final MediationAdRequest mediationAdRequest, final String s, final MediationRewardedVideoAdListener zzge, final Bundle bundle, final Bundle bundle2) {
        this.zzgc = context.getApplicationContext();
        (this.zzge = zzge).onInitializationSucceeded(this);
    }
    
    @Override
    public boolean isInitialized() {
        return this.zzge != null;
    }
    
    @Override
    public void loadAd(final MediationAdRequest mediationAdRequest, final Bundle bundle, final Bundle bundle2) {
        if (this.zzgc == null || this.zzge == null) {
            com.google.android.gms.ads.internal.util.client.zzb.e("AdMobAdapter.loadAd called before initialize.");
        }
        else {
            (this.zzgd = new InterstitialAd(this.zzgc)).zzd(true);
            this.zzgd.setAdUnitId(this.getAdUnitId(bundle));
            this.zzgd.setRewardedVideoAdListener(this.zzgf);
            this.zzgd.loadAd(this.zza(this.zzgc, mediationAdRequest, bundle2, bundle));
        }
    }
    
    @Override
    public void onDestroy() {
        if (this.zzfz != null) {
            this.zzfz.destroy();
            this.zzfz = null;
        }
        if (this.zzga != null) {
            this.zzga = null;
        }
        if (this.zzgb != null) {
            this.zzgb = null;
        }
        if (this.zzgd != null) {
            this.zzgd = null;
        }
    }
    
    @Override
    public void onPause() {
        if (this.zzfz != null) {
            this.zzfz.pause();
        }
    }
    
    @Override
    public void onResume() {
        if (this.zzfz != null) {
            this.zzfz.resume();
        }
    }
    
    @Override
    public void requestBannerAd(final Context context, final MediationBannerListener mediationBannerListener, final Bundle bundle, final AdSize adSize, final MediationAdRequest mediationAdRequest, final Bundle bundle2) {
        (this.zzfz = new AdView(context)).setAdSize(new AdSize(adSize.getWidth(), adSize.getHeight()));
        this.zzfz.setAdUnitId(this.getAdUnitId(bundle));
        this.zzfz.setAdListener(new zzc(this, mediationBannerListener));
        this.zzfz.loadAd(this.zza(context, mediationAdRequest, bundle2, bundle));
    }
    
    @Override
    public void requestInterstitialAd(final Context context, final MediationInterstitialListener mediationInterstitialListener, final Bundle bundle, final MediationAdRequest mediationAdRequest, final Bundle bundle2) {
        (this.zzga = new InterstitialAd(context)).setAdUnitId(this.getAdUnitId(bundle));
        this.zzga.setAdListener(new zzd(this, mediationInterstitialListener));
        this.zzga.loadAd(this.zza(context, mediationAdRequest, bundle2, bundle));
    }
    
    @Override
    public void requestNativeAd(final Context context, final MediationNativeListener mediationNativeListener, final Bundle bundle, final NativeMediationAdRequest nativeMediationAdRequest, final Bundle bundle2) {
        final zze zze = new zze(this, mediationNativeListener);
        final AdLoader.Builder withAdListener = this.zza(context, bundle.getString("pubid")).withAdListener(zze);
        final NativeAdOptions nativeAdOptions = nativeMediationAdRequest.getNativeAdOptions();
        if (nativeAdOptions != null) {
            withAdListener.withNativeAdOptions(nativeAdOptions);
        }
        if (nativeMediationAdRequest.isAppInstallAdRequested()) {
            withAdListener.forAppInstallAd(zze);
        }
        if (nativeMediationAdRequest.isContentAdRequested()) {
            withAdListener.forContentAd(zze);
        }
        (this.zzgb = withAdListener.build()).loadAd(this.zza(context, nativeMediationAdRequest, bundle2, bundle));
    }
    
    @Override
    public void showInterstitial() {
        this.zzga.show();
    }
    
    @Override
    public void showVideo() {
        this.zzgd.show();
    }
    
    protected abstract Bundle zza(final Bundle p0, final Bundle p1);
    
    AdLoader.Builder zza(final Context context, final String s) {
        return new AdLoader.Builder(context, s);
    }
    
    AdRequest zza(final Context context, final MediationAdRequest mediationAdRequest, final Bundle bundle, final Bundle bundle2) {
        final AdRequest.Builder builder = new AdRequest.Builder();
        final Date birthday = mediationAdRequest.getBirthday();
        if (birthday != null) {
            builder.setBirthday(birthday);
        }
        final int gender = mediationAdRequest.getGender();
        if (gender != 0) {
            builder.setGender(gender);
        }
        final Set<String> keywords = mediationAdRequest.getKeywords();
        if (keywords != null) {
            final Iterator<String> iterator = keywords.iterator();
            while (iterator.hasNext()) {
                builder.addKeyword(iterator.next());
            }
        }
        final Location location = mediationAdRequest.getLocation();
        if (location != null) {
            builder.setLocation(location);
        }
        if (mediationAdRequest.isTesting()) {
            builder.addTestDevice(zzm.zzjr().zzar(context));
        }
        if (mediationAdRequest.taggedForChildDirectedTreatment() != -1) {
            builder.tagForChildDirectedTreatment(mediationAdRequest.taggedForChildDirectedTreatment() == 1);
        }
        builder.setIsDesignedForFamilies(mediationAdRequest.isDesignedForFamilies());
        builder.addNetworkExtrasBundle(AdMobAdapter.class, this.zza(bundle, bundle2));
        return builder.build();
    }
    
    static class zza extends NativeAppInstallAdMapper
    {
        private final NativeAppInstallAd zzgh;
        
        public zza(final NativeAppInstallAd zzgh) {
            this.zzgh = zzgh;
            this.setHeadline(zzgh.getHeadline().toString());
            this.setImages(zzgh.getImages());
            this.setBody(zzgh.getBody().toString());
            this.setIcon(zzgh.getIcon());
            this.setCallToAction(zzgh.getCallToAction().toString());
            if (zzgh.getStarRating() != null) {
                this.setStarRating(zzgh.getStarRating());
            }
            if (zzgh.getStore() != null) {
                this.setStore(zzgh.getStore().toString());
            }
            if (zzgh.getPrice() != null) {
                this.setPrice(zzgh.getPrice().toString());
            }
            this.setOverrideImpressionRecording(true);
            this.setOverrideClickHandling(true);
            this.zza(zzgh.getVideoController());
        }
        
        @Override
        public void trackView(final View view) {
            if (view instanceof NativeAdView) {
                ((NativeAdView)view).setNativeAd(this.zzgh);
            }
        }
    }
    
    static class zzb extends NativeContentAdMapper
    {
        private final NativeContentAd zzgi;
        
        public zzb(final NativeContentAd zzgi) {
            this.zzgi = zzgi;
            this.setHeadline(zzgi.getHeadline().toString());
            this.setImages(zzgi.getImages());
            this.setBody(zzgi.getBody().toString());
            if (zzgi.getLogo() != null) {
                this.setLogo(zzgi.getLogo());
            }
            this.setCallToAction(zzgi.getCallToAction().toString());
            this.setAdvertiser(zzgi.getAdvertiser().toString());
            this.setOverrideImpressionRecording(true);
            this.setOverrideClickHandling(true);
        }
        
        @Override
        public void trackView(final View view) {
            if (view instanceof NativeAdView) {
                ((NativeAdView)view).setNativeAd(this.zzgi);
            }
        }
    }
    
    static final class zzc extends AdListener implements zza
    {
        final AbstractAdViewAdapter zzgj;
        final MediationBannerListener zzgk;
        
        public zzc(final AbstractAdViewAdapter zzgj, final MediationBannerListener zzgk) {
            this.zzgj = zzgj;
            this.zzgk = zzgk;
        }
        
        @Override
        public void onAdClicked() {
            this.zzgk.onAdClicked(this.zzgj);
        }
        
        @Override
        public void onAdClosed() {
            this.zzgk.onAdClosed(this.zzgj);
        }
        
        @Override
        public void onAdFailedToLoad(final int n) {
            this.zzgk.onAdFailedToLoad(this.zzgj, n);
        }
        
        @Override
        public void onAdLeftApplication() {
            this.zzgk.onAdLeftApplication(this.zzgj);
        }
        
        @Override
        public void onAdLoaded() {
            this.zzgk.onAdLoaded(this.zzgj);
        }
        
        @Override
        public void onAdOpened() {
            this.zzgk.onAdOpened(this.zzgj);
        }
    }
    
    static final class zzd extends AdListener implements zza
    {
        final AbstractAdViewAdapter zzgj;
        final MediationInterstitialListener zzgl;
        
        public zzd(final AbstractAdViewAdapter zzgj, final MediationInterstitialListener zzgl) {
            this.zzgj = zzgj;
            this.zzgl = zzgl;
        }
        
        @Override
        public void onAdClicked() {
            this.zzgl.onAdClicked(this.zzgj);
        }
        
        @Override
        public void onAdClosed() {
            this.zzgl.onAdClosed(this.zzgj);
        }
        
        @Override
        public void onAdFailedToLoad(final int n) {
            this.zzgl.onAdFailedToLoad(this.zzgj, n);
        }
        
        @Override
        public void onAdLeftApplication() {
            this.zzgl.onAdLeftApplication(this.zzgj);
        }
        
        @Override
        public void onAdLoaded() {
            this.zzgl.onAdLoaded(this.zzgj);
        }
        
        @Override
        public void onAdOpened() {
            this.zzgl.onAdOpened(this.zzgj);
        }
    }
    
    static final class zze extends AdListener implements OnAppInstallAdLoadedListener, OnContentAdLoadedListener, zza
    {
        final AbstractAdViewAdapter zzgj;
        final MediationNativeListener zzgm;
        
        public zze(final AbstractAdViewAdapter zzgj, final MediationNativeListener zzgm) {
            this.zzgj = zzgj;
            this.zzgm = zzgm;
        }
        
        @Override
        public void onAdClicked() {
            this.zzgm.onAdClicked(this.zzgj);
        }
        
        @Override
        public void onAdClosed() {
            this.zzgm.onAdClosed(this.zzgj);
        }
        
        @Override
        public void onAdFailedToLoad(final int n) {
            this.zzgm.onAdFailedToLoad(this.zzgj, n);
        }
        
        @Override
        public void onAdLeftApplication() {
            this.zzgm.onAdLeftApplication(this.zzgj);
        }
        
        @Override
        public void onAdLoaded() {
        }
        
        @Override
        public void onAdOpened() {
            this.zzgm.onAdOpened(this.zzgj);
        }
        
        @Override
        public void onAppInstallAdLoaded(final NativeAppInstallAd nativeAppInstallAd) {
            this.zzgm.onAdLoaded(this.zzgj, new AbstractAdViewAdapter.zza(nativeAppInstallAd));
        }
        
        @Override
        public void onContentAdLoaded(final NativeContentAd nativeContentAd) {
            this.zzgm.onAdLoaded(this.zzgj, new zzb(nativeContentAd));
        }
    }
}
