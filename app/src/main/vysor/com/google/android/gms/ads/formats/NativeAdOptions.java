// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.formats;

import java.lang.annotation.Annotation;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.internal.zziy;

@zziy
public final class NativeAdOptions
{
    public static final int ADCHOICES_BOTTOM_LEFT = 3;
    public static final int ADCHOICES_BOTTOM_RIGHT = 2;
    public static final int ADCHOICES_TOP_LEFT = 0;
    public static final int ADCHOICES_TOP_RIGHT = 1;
    public static final int ORIENTATION_ANY = 0;
    public static final int ORIENTATION_LANDSCAPE = 2;
    public static final int ORIENTATION_PORTRAIT = 1;
    private final boolean zzakn;
    private final int zzako;
    private final boolean zzakp;
    private final int zzakq;
    private final VideoOptions zzakr;
    
    private NativeAdOptions(final Builder builder) {
        this.zzakn = builder.zzakn;
        this.zzako = builder.zzako;
        this.zzakp = builder.zzakp;
        this.zzakq = builder.zzakq;
        this.zzakr = builder.zzakr;
    }
    
    public int getAdChoicesPlacement() {
        return this.zzakq;
    }
    
    public int getImageOrientation() {
        return this.zzako;
    }
    
    @Nullable
    public VideoOptions getVideoOptions() {
        return this.zzakr;
    }
    
    public boolean shouldRequestMultipleImages() {
        return this.zzakp;
    }
    
    public boolean shouldReturnUrlsForImageAssets() {
        return this.zzakn;
    }
    
    public @interface AdChoicesPlacement {
    }
    
    public static final class Builder
    {
        private boolean zzakn;
        private int zzako;
        private boolean zzakp;
        private int zzakq;
        private VideoOptions zzakr;
        
        public Builder() {
            this.zzakn = false;
            this.zzako = 0;
            this.zzakp = false;
            this.zzakq = 1;
        }
        
        public NativeAdOptions build() {
            return new NativeAdOptions(this, null);
        }
        
        public Builder setAdChoicesPlacement(@AdChoicesPlacement final int zzakq) {
            this.zzakq = zzakq;
            return this;
        }
        
        public Builder setImageOrientation(final int zzako) {
            this.zzako = zzako;
            return this;
        }
        
        public Builder setRequestMultipleImages(final boolean zzakp) {
            this.zzakp = zzakp;
            return this;
        }
        
        public Builder setReturnUrlsForImageAssets(final boolean zzakn) {
            this.zzakn = zzakn;
            return this;
        }
        
        public Builder setVideoOptions(final VideoOptions zzakr) {
            this.zzakr = zzakr;
            return this;
        }
    }
}
