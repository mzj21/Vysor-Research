// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import com.google.android.gms.common.internal.zzac;
import com.google.ads.mediation.admob.AdMobAdapter;
import android.content.Context;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import android.location.Location;
import java.util.Set;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import java.util.Date;
import com.google.android.gms.ads.internal.client.zzad;

public final class AdRequest
{
    public static final String DEVICE_ID_EMULATOR;
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    public static final int GENDER_FEMALE = 2;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_UNKNOWN = 0;
    public static final int MAX_CONTENT_URL_LENGTH = 512;
    private final zzad zzaju;
    
    static {
        DEVICE_ID_EMULATOR = zzad.DEVICE_ID_EMULATOR;
    }
    
    private AdRequest(final Builder builder) {
        this.zzaju = new zzad(builder.zzajv);
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
    
    public int getGender() {
        return this.zzaju.getGender();
    }
    
    public Set<String> getKeywords() {
        return this.zzaju.getKeywords();
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
            (this.zzajv = new zzad.zza()).zzaj(AdRequest.DEVICE_ID_EMULATOR);
        }
        
        public Builder addCustomEventExtrasBundle(final Class<? extends CustomEvent> clazz, final Bundle bundle) {
            this.zzajv.zzb(clazz, bundle);
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
            if (clazz.equals(AdMobAdapter.class) && bundle.getBoolean("_emulatorLiveAds")) {
                this.zzajv.zzak(AdRequest.DEVICE_ID_EMULATOR);
            }
            return this;
        }
        
        public Builder addTestDevice(final String s) {
            this.zzajv.zzaj(s);
            return this;
        }
        
        public AdRequest build() {
            return new AdRequest(this, null);
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
