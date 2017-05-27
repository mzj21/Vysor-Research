// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.search;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.client.zzad;
import android.content.Context;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;

public final class DynamicHeightSearchAdRequest
{
    private final SearchAdRequest zzcxp;
    
    private DynamicHeightSearchAdRequest(final Builder builder) {
        this.zzcxp = builder.zzcxq.build();
    }
    
    public <T extends CustomEvent> Bundle getCustomEventExtrasBundle(final Class<T> clazz) {
        return this.zzcxp.getCustomEventExtrasBundle(clazz);
    }
    
    @Deprecated
    public <T extends NetworkExtras> T getNetworkExtras(final Class<T> clazz) {
        return this.zzcxp.getNetworkExtras(clazz);
    }
    
    public <T extends MediationAdapter> Bundle getNetworkExtrasBundle(final Class<T> clazz) {
        return this.zzcxp.getNetworkExtrasBundle(clazz);
    }
    
    public String getQuery() {
        return this.zzcxp.getQuery();
    }
    
    public boolean isTestDevice(final Context context) {
        return this.zzcxp.isTestDevice(context);
    }
    
    zzad zzdg() {
        return this.zzcxp.zzdg();
    }
    
    public static final class Builder
    {
        private final SearchAdRequest.Builder zzcxq;
        private final Bundle zzcxr;
        
        public Builder() {
            this.zzcxq = new SearchAdRequest.Builder();
            this.zzcxr = new Bundle();
        }
        
        public Builder addCustomEventExtrasBundle(final Class<? extends CustomEvent> clazz, final Bundle bundle) {
            this.zzcxq.addCustomEventExtrasBundle(clazz, bundle);
            return this;
        }
        
        public Builder addNetworkExtras(final NetworkExtras networkExtras) {
            this.zzcxq.addNetworkExtras(networkExtras);
            return this;
        }
        
        public Builder addNetworkExtrasBundle(final Class<? extends MediationAdapter> clazz, final Bundle bundle) {
            this.zzcxq.addNetworkExtrasBundle(clazz, bundle);
            return this;
        }
        
        public DynamicHeightSearchAdRequest build() {
            this.zzcxq.addNetworkExtrasBundle(AdMobAdapter.class, this.zzcxr);
            return new DynamicHeightSearchAdRequest(this, null);
        }
        
        public Builder setAdBorderSelectors(final String s) {
            this.zzcxr.putString("csa_adBorderSelectors", s);
            return this;
        }
        
        public Builder setAdTest(final boolean b) {
            final Bundle zzcxr = this.zzcxr;
            String s;
            if (b) {
                s = "on";
            }
            else {
                s = "off";
            }
            zzcxr.putString("csa_adtest", s);
            return this;
        }
        
        public Builder setAdjustableLineHeight(final int n) {
            this.zzcxr.putString("csa_adjustableLineHeight", Integer.toString(n));
            return this;
        }
        
        public Builder setAdvancedOptionValue(final String s, final String s2) {
            this.zzcxr.putString(s, s2);
            return this;
        }
        
        public Builder setAttributionSpacingBelow(final int n) {
            this.zzcxr.putString("csa_attributionSpacingBelow", Integer.toString(n));
            return this;
        }
        
        public Builder setBorderSelections(final String s) {
            this.zzcxr.putString("csa_borderSelections", s);
            return this;
        }
        
        public Builder setChannel(final String s) {
            this.zzcxr.putString("csa_channel", s);
            return this;
        }
        
        public Builder setColorAdBorder(final String s) {
            this.zzcxr.putString("csa_colorAdBorder", s);
            return this;
        }
        
        public Builder setColorAdSeparator(final String s) {
            this.zzcxr.putString("csa_colorAdSeparator", s);
            return this;
        }
        
        public Builder setColorAnnotation(final String s) {
            this.zzcxr.putString("csa_colorAnnotation", s);
            return this;
        }
        
        public Builder setColorAttribution(final String s) {
            this.zzcxr.putString("csa_colorAttribution", s);
            return this;
        }
        
        public Builder setColorBackground(final String s) {
            this.zzcxr.putString("csa_colorBackground", s);
            return this;
        }
        
        public Builder setColorBorder(final String s) {
            this.zzcxr.putString("csa_colorBorder", s);
            return this;
        }
        
        public Builder setColorDomainLink(final String s) {
            this.zzcxr.putString("csa_colorDomainLink", s);
            return this;
        }
        
        public Builder setColorText(final String s) {
            this.zzcxr.putString("csa_colorText", s);
            return this;
        }
        
        public Builder setColorTitleLink(final String s) {
            this.zzcxr.putString("csa_colorTitleLink", s);
            return this;
        }
        
        public Builder setCssWidth(final int n) {
            this.zzcxr.putString("csa_width", Integer.toString(n));
            return this;
        }
        
        public Builder setDetailedAttribution(final boolean b) {
            this.zzcxr.putString("csa_detailedAttribution", Boolean.toString(b));
            return this;
        }
        
        @Deprecated
        public Builder setFontFamily(final int n) {
            return this.setFontFamily(Integer.toString(n));
        }
        
        public Builder setFontFamily(final String s) {
            this.zzcxr.putString("csa_fontFamily", s);
            return this;
        }
        
        public Builder setFontFamilyAttribution(final String s) {
            this.zzcxr.putString("csa_fontFamilyAttribution", s);
            return this;
        }
        
        public Builder setFontSizeAnnotation(final int n) {
            this.zzcxr.putString("csa_fontSizeAnnotation", Integer.toString(n));
            return this;
        }
        
        public Builder setFontSizeAttribution(final int n) {
            this.zzcxr.putString("csa_fontSizeAttribution", Integer.toString(n));
            return this;
        }
        
        public Builder setFontSizeDescription(final int n) {
            this.zzcxr.putString("csa_fontSizeDescription", Integer.toString(n));
            return this;
        }
        
        public Builder setFontSizeDomainLink(final int n) {
            this.zzcxr.putString("csa_fontSizeDomainLink", Integer.toString(n));
            return this;
        }
        
        public Builder setFontSizeTitle(final int n) {
            this.zzcxr.putString("csa_fontSizeTitle", Integer.toString(n));
            return this;
        }
        
        public Builder setHostLanguage(final String s) {
            this.zzcxr.putString("csa_hl", s);
            return this;
        }
        
        public Builder setIsClickToCallEnabled(final boolean b) {
            this.zzcxr.putString("csa_clickToCall", Boolean.toString(b));
            return this;
        }
        
        public Builder setIsLocationEnabled(final boolean b) {
            this.zzcxr.putString("csa_location", Boolean.toString(b));
            return this;
        }
        
        public Builder setIsPlusOnesEnabled(final boolean b) {
            this.zzcxr.putString("csa_plusOnes", Boolean.toString(b));
            return this;
        }
        
        public Builder setIsSellerRatingsEnabled(final boolean b) {
            this.zzcxr.putString("csa_sellerRatings", Boolean.toString(b));
            return this;
        }
        
        public Builder setIsSiteLinksEnabled(final boolean b) {
            this.zzcxr.putString("csa_siteLinks", Boolean.toString(b));
            return this;
        }
        
        public Builder setIsTitleBold(final boolean b) {
            this.zzcxr.putString("csa_titleBold", Boolean.toString(b));
            return this;
        }
        
        public Builder setIsTitleUnderlined(final boolean b) {
            this.zzcxr.putString("csa_noTitleUnderline", Boolean.toString(!b));
            return this;
        }
        
        public Builder setLocationColor(final String s) {
            this.zzcxr.putString("csa_colorLocation", s);
            return this;
        }
        
        public Builder setLocationFontSize(final int n) {
            this.zzcxr.putString("csa_fontSizeLocation", Integer.toString(n));
            return this;
        }
        
        public Builder setLongerHeadlines(final boolean b) {
            this.zzcxr.putString("csa_longerHeadlines", Boolean.toString(b));
            return this;
        }
        
        public Builder setNumber(final int n) {
            this.zzcxr.putString("csa_number", Integer.toString(n));
            return this;
        }
        
        public Builder setPage(final int n) {
            this.zzcxr.putString("csa_adPage", Integer.toString(n));
            return this;
        }
        
        public Builder setQuery(final String query) {
            this.zzcxq.setQuery(query);
            return this;
        }
        
        public Builder setVerticalSpacing(final int n) {
            this.zzcxr.putString("csa_verticalSpacing", Integer.toString(n));
            return this;
        }
    }
}
