// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zziy
public final class SearchAdRequestParcel extends AbstractSafeParcelable
{
    public static final zzao CREATOR;
    public final int backgroundColor;
    public final int versionCode;
    public final int zzazp;
    public final int zzazq;
    public final int zzazr;
    public final int zzazs;
    public final int zzazt;
    public final int zzazu;
    public final int zzazv;
    public final String zzazw;
    public final int zzazx;
    public final String zzazy;
    public final int zzazz;
    public final int zzbaa;
    public final String zzbab;
    
    static {
        CREATOR = new zzao();
    }
    
    SearchAdRequestParcel(final int versionCode, final int zzazp, final int backgroundColor, final int zzazq, final int zzazr, final int zzazs, final int zzazt, final int zzazu, final int zzazv, final String zzazw, final int zzazx, final String zzazy, final int zzazz, final int zzbaa, final String zzbab) {
        this.versionCode = versionCode;
        this.zzazp = zzazp;
        this.backgroundColor = backgroundColor;
        this.zzazq = zzazq;
        this.zzazr = zzazr;
        this.zzazs = zzazs;
        this.zzazt = zzazt;
        this.zzazu = zzazu;
        this.zzazv = zzazv;
        this.zzazw = zzazw;
        this.zzazx = zzazx;
        this.zzazy = zzazy;
        this.zzazz = zzazz;
        this.zzbaa = zzbaa;
        this.zzbab = zzbab;
    }
    
    public SearchAdRequestParcel(final SearchAdRequest searchAdRequest) {
        this.versionCode = 1;
        this.zzazp = searchAdRequest.getAnchorTextColor();
        this.backgroundColor = searchAdRequest.getBackgroundColor();
        this.zzazq = searchAdRequest.getBackgroundGradientBottom();
        this.zzazr = searchAdRequest.getBackgroundGradientTop();
        this.zzazs = searchAdRequest.getBorderColor();
        this.zzazt = searchAdRequest.getBorderThickness();
        this.zzazu = searchAdRequest.getBorderType();
        this.zzazv = searchAdRequest.getCallButtonColor();
        this.zzazw = searchAdRequest.getCustomChannels();
        this.zzazx = searchAdRequest.getDescriptionTextColor();
        this.zzazy = searchAdRequest.getFontFace();
        this.zzazz = searchAdRequest.getHeaderTextColor();
        this.zzbaa = searchAdRequest.getHeaderTextSize();
        this.zzbab = searchAdRequest.getQuery();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzao.zza(this, parcel, n);
    }
}
