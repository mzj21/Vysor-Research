// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.formats;

import android.os.Parcel;
import com.google.android.gms.ads.formats.NativeAdOptions;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.VideoOptionsParcel;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zziy
public class NativeAdOptionsParcel extends AbstractSafeParcelable
{
    public static final zzk CREATOR;
    public final int versionCode;
    public final boolean zzblb;
    public final int zzblc;
    public final boolean zzbld;
    public final int zzble;
    @Nullable
    public final VideoOptionsParcel zzblf;
    
    static {
        CREATOR = new zzk();
    }
    
    public NativeAdOptionsParcel(final int versionCode, final boolean zzblb, final int zzblc, final boolean zzbld, final int zzble, final VideoOptionsParcel zzblf) {
        this.versionCode = versionCode;
        this.zzblb = zzblb;
        this.zzblc = zzblc;
        this.zzbld = zzbld;
        this.zzble = zzble;
        this.zzblf = zzblf;
    }
    
    public NativeAdOptionsParcel(final NativeAdOptions nativeAdOptions) {
        final boolean shouldReturnUrlsForImageAssets = nativeAdOptions.shouldReturnUrlsForImageAssets();
        final int imageOrientation = nativeAdOptions.getImageOrientation();
        final boolean shouldRequestMultipleImages = nativeAdOptions.shouldRequestMultipleImages();
        final int adChoicesPlacement = nativeAdOptions.getAdChoicesPlacement();
        VideoOptionsParcel videoOptionsParcel;
        if (nativeAdOptions.getVideoOptions() != null) {
            videoOptionsParcel = new VideoOptionsParcel(nativeAdOptions.getVideoOptions());
        }
        else {
            videoOptionsParcel = null;
        }
        this(3, shouldReturnUrlsForImageAssets, imageOrientation, shouldRequestMultipleImages, adChoicesPlacement, videoOptionsParcel);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzk.zza(this, parcel, n);
    }
}
