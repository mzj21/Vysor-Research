// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.search;

import android.graphics.Color;
import android.content.Context;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.internal.client.zzad;

public final class SearchAdRequest
{
    public static final int BORDER_TYPE_DASHED = 1;
    public static final int BORDER_TYPE_DOTTED = 2;
    public static final int BORDER_TYPE_NONE = 0;
    public static final int BORDER_TYPE_SOLID = 3;
    public static final int CALL_BUTTON_COLOR_DARK = 2;
    public static final int CALL_BUTTON_COLOR_LIGHT = 0;
    public static final int CALL_BUTTON_COLOR_MEDIUM = 1;
    public static final String DEVICE_ID_EMULATOR;
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    private final int mBackgroundColor;
    private final zzad zzaju;
    private final String zzapl;
    private final int zzcxs;
    private final int zzcxt;
    private final int zzcxu;
    private final int zzcxv;
    private final int zzcxw;
    private final int zzcxx;
    private final int zzcxy;
    private final String zzcxz;
    private final int zzcya;
    private final String zzcyb;
    private final int zzcyc;
    private final int zzcyd;
    
    static {
        DEVICE_ID_EMULATOR = zzad.DEVICE_ID_EMULATOR;
    }
    
    private SearchAdRequest(final Builder builder) {
        this.zzcxs = builder.zzcxs;
        this.mBackgroundColor = builder.mBackgroundColor;
        this.zzcxt = builder.zzcxt;
        this.zzcxu = builder.zzcxu;
        this.zzcxv = builder.zzcxv;
        this.zzcxw = builder.zzcxw;
        this.zzcxx = builder.zzcxx;
        this.zzcxy = builder.zzcxy;
        this.zzcxz = builder.zzcxz;
        this.zzcya = builder.zzcya;
        this.zzcyb = builder.zzcyb;
        this.zzcyc = builder.zzcyc;
        this.zzcyd = builder.zzcyd;
        this.zzapl = builder.zzapl;
        this.zzaju = new zzad(builder.zzajv, this);
    }
    
    public int getAnchorTextColor() {
        return this.zzcxs;
    }
    
    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }
    
    public int getBackgroundGradientBottom() {
        return this.zzcxt;
    }
    
    public int getBackgroundGradientTop() {
        return this.zzcxu;
    }
    
    public int getBorderColor() {
        return this.zzcxv;
    }
    
    public int getBorderThickness() {
        return this.zzcxw;
    }
    
    public int getBorderType() {
        return this.zzcxx;
    }
    
    public int getCallButtonColor() {
        return this.zzcxy;
    }
    
    public String getCustomChannels() {
        return this.zzcxz;
    }
    
    public <T extends CustomEvent> Bundle getCustomEventExtrasBundle(final Class<T> clazz) {
        return this.zzaju.getCustomEventExtrasBundle(clazz);
    }
    
    public int getDescriptionTextColor() {
        return this.zzcya;
    }
    
    public String getFontFace() {
        return this.zzcyb;
    }
    
    public int getHeaderTextColor() {
        return this.zzcyc;
    }
    
    public int getHeaderTextSize() {
        return this.zzcyd;
    }
    
    public Location getLocation() {
        return this.zzaju.getLocation();
    }
    
    @Deprecated
    public <T extends NetworkExtras> T getNetworkExtras(final Class<T> clazz) {
        return this.zzaju.getNetworkExtras(clazz);
    }
    
    public <T extends MediationAdapter> Bundle getNetworkExtrasBundle(final Class<T> clazz) {
        return this.zzaju.getNetworkExtrasBundle(clazz);
    }
    
    public String getQuery() {
        return this.zzapl;
    }
    
    public boolean isTestDevice(final Context context) {
        return this.zzaju.isTestDevice(context);
    }
    
    zzad zzdg() {
        return this.zzaju;
    }
    
    public static final class Builder
    {
        private int mBackgroundColor;
        private final zzad.zza zzajv;
        private String zzapl;
        private int zzcxs;
        private int zzcxt;
        private int zzcxu;
        private int zzcxv;
        private int zzcxw;
        private int zzcxx;
        private int zzcxy;
        private String zzcxz;
        private int zzcya;
        private String zzcyb;
        private int zzcyc;
        private int zzcyd;
        
        public Builder() {
            this.zzajv = new zzad.zza();
            this.zzcxx = 0;
        }
        
        public Builder addCustomEventExtrasBundle(final Class<? extends CustomEvent> clazz, final Bundle bundle) {
            this.zzajv.zzb(clazz, bundle);
            return this;
        }
        
        public Builder addNetworkExtras(final NetworkExtras networkExtras) {
            this.zzajv.zza(networkExtras);
            return this;
        }
        
        public Builder addNetworkExtrasBundle(final Class<? extends MediationAdapter> clazz, final Bundle bundle) {
            this.zzajv.zza(clazz, bundle);
            return this;
        }
        
        public Builder addTestDevice(final String s) {
            this.zzajv.zzaj(s);
            return this;
        }
        
        public SearchAdRequest build() {
            return new SearchAdRequest(this, null);
        }
        
        public Builder setAnchorTextColor(final int zzcxs) {
            this.zzcxs = zzcxs;
            return this;
        }
        
        public Builder setBackgroundColor(final int mBackgroundColor) {
            this.mBackgroundColor = mBackgroundColor;
            this.zzcxt = Color.argb(0, 0, 0, 0);
            this.zzcxu = Color.argb(0, 0, 0, 0);
            return this;
        }
        
        public Builder setBackgroundGradient(final int zzcxu, final int zzcxt) {
            this.mBackgroundColor = Color.argb(0, 0, 0, 0);
            this.zzcxt = zzcxt;
            this.zzcxu = zzcxu;
            return this;
        }
        
        public Builder setBorderColor(final int zzcxv) {
            this.zzcxv = zzcxv;
            return this;
        }
        
        public Builder setBorderThickness(final int zzcxw) {
            this.zzcxw = zzcxw;
            return this;
        }
        
        public Builder setBorderType(final int zzcxx) {
            this.zzcxx = zzcxx;
            return this;
        }
        
        public Builder setCallButtonColor(final int zzcxy) {
            this.zzcxy = zzcxy;
            return this;
        }
        
        public Builder setCustomChannels(final String zzcxz) {
            this.zzcxz = zzcxz;
            return this;
        }
        
        public Builder setDescriptionTextColor(final int zzcya) {
            this.zzcya = zzcya;
            return this;
        }
        
        public Builder setFontFace(final String zzcyb) {
            this.zzcyb = zzcyb;
            return this;
        }
        
        public Builder setHeaderTextColor(final int zzcyc) {
            this.zzcyc = zzcyc;
            return this;
        }
        
        public Builder setHeaderTextSize(final int zzcyd) {
            this.zzcyd = zzcyd;
            return this;
        }
        
        public Builder setLocation(final Location location) {
            this.zzajv.zzb(location);
            return this;
        }
        
        public Builder setQuery(final String zzapl) {
            this.zzapl = zzapl;
            return this;
        }
        
        public Builder setRequestAgent(final String s) {
            this.zzajv.zzan(s);
            return this;
        }
        
        public Builder tagForChildDirectedTreatment(final boolean b) {
            this.zzajv.zzo(b);
            return this;
        }
    }
}
