// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import com.google.android.gms.internal.zziy;

@zziy
public final class VideoOptions
{
    private final boolean zzakg;
    
    private VideoOptions(final Builder builder) {
        this.zzakg = builder.zzakg;
    }
    
    public boolean getStartMuted() {
        return this.zzakg;
    }
    
    public static final class Builder
    {
        private boolean zzakg;
        
        public Builder() {
            this.zzakg = false;
        }
        
        public VideoOptions build() {
            return new VideoOptions(this, null);
        }
        
        public Builder setStartMuted(final boolean zzakg) {
            this.zzakg = zzakg;
            return this;
        }
    }
}
