// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.doubleclick;

import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzz;
import java.util.List;
import android.content.Context;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import android.location.Location;
import java.util.Set;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import java.util.Date;
import com.google.android.gms.ads.internal.client.zzad;

public final class PublisherAdRequest
{
    public static final String DEVICE_ID_EMULATOR;
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    public static final int GENDER_FEMALE = 2;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_UNKNOWN;
    private final zzad zzaju;
    
    static {
        DEVICE_ID_EMULATOR = zzad.DEVICE_ID_EMULATOR;
    }
    
    private PublisherAdRequest(final Builder builder) {
        this.zzaju = new zzad(builder.zzajv);
    }
    
    public static void updateCorrelator() {
    }
    
    public Date getBirthday() {
        return this.zzaju.getBirthday();
    }
    
    public String getContentUrl() {
        return this.zzaju.getContentUrl();
    }
    
    public <T extends CustomEvent> Bundle getCustomEventExtrasBundle(final Class<T> clazz) {
        return this.zzaju.getCustomEventExtrasBundle(clazz);
    }
    
    public Bundle getCustomTargeting() {
        return this.zzaju.getCustomTargeting();
    }
    
    public int getGender() {
        return this.zzaju.getGender();
    }
    
    public Set<String> getKeywords() {
        return this.zzaju.getKeywords();
    }
    
    public Location getLocation() {
        return this.zzaju.getLocation();
    }
    
    public boolean getManualImpressionsEnabled() {
        return this.zzaju.getManualImpressionsEnabled();
    }
    
    @Deprecated
    public <T extends NetworkExtras> T getNetworkExtras(final Class<T> clazz) {
        return this.zzaju.getNetworkExtras(clazz);
    }
    
    public <T extends MediationAdapter> Bundle getNetworkExtrasBundle(final Class<T> clazz) {
        return this.zzaju.getNetworkExtrasBundle(clazz);
    }
    
    public String getPublisherProvidedId() {
        return this.zzaju.getPublisherProvidedId();
    }
    
    public boolean isTestDevice(final Context context) {
        return this.zzaju.isTestDevice(context);
    }
    
    public zzad zzdg() {
        return this.zzaju;
    }
    
    public static final class Builder
    {
        private final zzad.zza zzajv;
        
        public Builder() {
            this.zzajv = new zzad.zza();
        }
        
        public Builder addCategoryExclusion(final String s) {
            this.zzajv.zzao(s);
            return this;
        }
        
        public Builder addCustomEventExtrasBundle(final Class<? extends CustomEvent> clazz, final Bundle bundle) {
            this.zzajv.zzb(clazz, bundle);
            return this;
        }
        
        public Builder addCustomTargeting(final String s, final String s2) {
            this.zzajv.zzf(s, s2);
            return this;
        }
        
        public Builder addCustomTargeting(final String s, final List<String> list) {
            if (list != null) {
                this.zzajv.zzf(s, zzz.zzhy(",").zza(list));
            }
            return this;
        }
        
        public Builder addKeyword(final String s) {
            this.zzajv.zzai(s);
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
        
        public PublisherAdRequest build() {
            return new PublisherAdRequest(this, null);
        }
        
        public Builder setBirthday(final Date date) {
            this.zzajv.zza(date);
            return this;
        }
        
        public Builder setContentUrl(final String s) {
            zzac.zzb(s, "Content URL must be non-null.");
            zzac.zzh(s, "Content URL must be non-empty.");
            zzac.zzb(s.length() <= 512, "Content URL must not exceed %d in length.  Provided length was %d.", 512, s.length());
            this.zzajv.zzal(s);
            return this;
        }
        
        public Builder setGender(final int n) {
            this.zzajv.zzv(n);
            return this;
        }
        
        public Builder setIsDesignedForFamilies(final boolean b) {
            this.zzajv.zzp(b);
            return this;
        }
        
        public Builder setLocation(final Location location) {
            this.zzajv.zzb(location);
            return this;
        }
        
        @Deprecated
        public Builder setManualImpressionsEnabled(final boolean manualImpressionsEnabled) {
            this.zzajv.setManualImpressionsEnabled(manualImpressionsEnabled);
            return this;
        }
        
        public Builder setPublisherProvidedId(final String s) {
            this.zzajv.zzam(s);
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
