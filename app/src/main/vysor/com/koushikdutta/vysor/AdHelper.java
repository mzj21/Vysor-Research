// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.vysor;

import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import android.util.AttributeSet;
import com.google.android.gms.ads.BaseAdView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdRequest;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import com.google.android.gms.ads.AdSize;
import android.content.Context;
import com.google.android.gms.ads.AdView;
import android.view.View;
import android.app.Activity;

public class AdHelper
{
    public static View createView(final Activity activity) {
        final AdView adView = new AdView((Context)activity);
        adView.setAdUnitId("ca-app-pub-0806259031233516/8955332168");
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, -2));
        adView.loadAd(new AdRequest.Builder().addTestDevice("3BA33B72ED56C5958B628A27DBD50456").addTestDevice("5787B62A2CBAC3C2BF675E65EDC2CAB3").build());
        return (View)adView;
    }
    
    public static InterstitialAd loadInterstitial(final Context context) {
        final InterstitialAd interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId("ca-app-pub-0806259031233516/1432065361");
        final AdRequest build = new AdRequest.Builder().addTestDevice("3BA33B72ED56C5958B628A27DBD50456").addTestDevice("5787B62A2CBAC3C2BF675E65EDC2CAB3").build();
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }
            
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                interstitialAd.show();
            }
            
            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });
        interstitialAd.loadAd(build);
        return interstitialAd;
    }
}
