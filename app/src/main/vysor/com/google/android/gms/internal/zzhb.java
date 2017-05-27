// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import android.support.annotation.Nullable;
import android.location.Location;
import java.util.Set;
import java.util.Date;
import java.util.List;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;

@zziy
public final class zzhb implements NativeMediationAdRequest
{
    private final NativeAdOptionsParcel zzand;
    private final List<String> zzane;
    private final int zzawu;
    private final boolean zzaxg;
    private final int zzbty;
    private final Date zzgn;
    private final Set<String> zzgp;
    private final boolean zzgq;
    private final Location zzgr;
    
    public zzhb(@Nullable final Date zzgn, final int zzawu, @Nullable final Set<String> zzgp, @Nullable final Location zzgr, final boolean zzgq, final int zzbty, final NativeAdOptionsParcel zzand, final List<String> zzane, final boolean zzaxg) {
        this.zzgn = zzgn;
        this.zzawu = zzawu;
        this.zzgp = zzgp;
        this.zzgr = zzgr;
        this.zzgq = zzgq;
        this.zzbty = zzbty;
        this.zzand = zzand;
        this.zzane = zzane;
        this.zzaxg = zzaxg;
    }
    
    @Override
    public Date getBirthday() {
        return this.zzgn;
    }
    
    @Override
    public int getGender() {
        return this.zzawu;
    }
    
    @Override
    public Set<String> getKeywords() {
        return this.zzgp;
    }
    
    @Override
    public Location getLocation() {
        return this.zzgr;
    }
    
    @Override
    public NativeAdOptions getNativeAdOptions() {
        NativeAdOptions build;
        if (this.zzand == null) {
            build = null;
        }
        else {
            final NativeAdOptions.Builder setRequestMultipleImages = new NativeAdOptions.Builder().setReturnUrlsForImageAssets(this.zzand.zzblb).setImageOrientation(this.zzand.zzblc).setRequestMultipleImages(this.zzand.zzbld);
            if (this.zzand.versionCode >= 2) {
                setRequestMultipleImages.setAdChoicesPlacement(this.zzand.zzble);
            }
            if (this.zzand.versionCode >= 3 && this.zzand.zzblf != null) {
                setRequestMultipleImages.setVideoOptions(new VideoOptions.Builder().setStartMuted(this.zzand.zzblf.zzbac).build());
            }
            build = setRequestMultipleImages.build();
        }
        return build;
    }
    
    @Override
    public boolean isAppInstallAdRequested() {
        return this.zzane != null && this.zzane.contains("2");
    }
    
    @Override
    public boolean isContentAdRequested() {
        return this.zzane != null && this.zzane.contains("1");
    }
    
    @Override
    public boolean isDesignedForFamilies() {
        return this.zzaxg;
    }
    
    @Override
    public boolean isTesting() {
        return this.zzgq;
    }
    
    @Override
    public int taggedForChildDirectedTreatment() {
        return this.zzbty;
    }
}
