// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.internal.client.zzy;
import android.support.annotation.NonNull;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzc;
import com.google.android.gms.internal.zzej;
import com.google.android.gms.internal.zzek;
import com.google.android.gms.internal.zzeo;
import com.google.android.gms.internal.zzep;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.internal.zzei;
import com.google.android.gms.internal.zzen;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.internal.zzeh;
import com.google.android.gms.internal.zzem;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.internal.zzgp;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.ads.internal.client.zzs;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import android.support.annotation.RequiresPermission;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.client.zzad;
import com.google.android.gms.ads.internal.client.zzr;
import com.google.android.gms.ads.internal.client.zzh;
import android.content.Context;

public class AdLoader
{
    private final Context mContext;
    private final zzh zzajr;
    private final zzr zzajs;
    
    AdLoader(final Context context, final zzr zzr) {
        this(context, zzr, zzh.zzjb());
    }
    
    AdLoader(final Context mContext, final zzr zzajs, final zzh zzajr) {
        this.mContext = mContext;
        this.zzajs = zzajs;
        this.zzajr = zzajr;
    }
    
    private void zza(final zzad zzad) {
        try {
            this.zzajs.zzf(this.zzajr.zza(this.mContext, zzad));
        }
        catch (RemoteException ex) {
            zzb.zzb("Failed to load ad.", (Throwable)ex);
        }
    }
    
    public String getMediationAdapterClassName() {
        try {
            return this.zzajs.getMediationAdapterClassName();
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to get the mediation adapter class name.", (Throwable)ex);
            return null;
        }
    }
    
    public boolean isLoading() {
        try {
            return this.zzajs.isLoading();
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to check if ad is loading.", (Throwable)ex);
            return false;
        }
    }
    
    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(final AdRequest adRequest) {
        this.zza(adRequest.zzdg());
    }
    
    public void loadAd(final PublisherAdRequest publisherAdRequest) {
        this.zza(publisherAdRequest.zzdg());
    }
    
    public static class Builder
    {
        private final Context mContext;
        private final zzs zzajt;
        
        Builder(final Context mContext, final zzs zzajt) {
            this.mContext = mContext;
            this.zzajt = zzajt;
        }
        
        public Builder(final Context context, final String s) {
            this(zzac.zzb(context, "context cannot be null"), zzm.zzjs().zzb(context, s, new zzgp()));
        }
        
        public AdLoader build() {
            try {
                return new AdLoader(this.mContext, this.zzajt.zzey());
            }
            catch (RemoteException ex) {
                zzb.zzb("Failed to build AdLoader.", (Throwable)ex);
                return null;
            }
        }
        
        public Builder forAppInstallAd(final NativeAppInstallAd.OnAppInstallAdLoadedListener onAppInstallAdLoadedListener) {
            try {
                this.zzajt.zza(new zzem(onAppInstallAdLoadedListener));
                return this;
            }
            catch (RemoteException ex) {
                zzb.zzd("Failed to add app install ad listener", (Throwable)ex);
                return this;
            }
        }
        
        public Builder forContentAd(final NativeContentAd.OnContentAdLoadedListener onContentAdLoadedListener) {
            try {
                this.zzajt.zza(new zzen(onContentAdLoadedListener));
                return this;
            }
            catch (RemoteException ex) {
                zzb.zzd("Failed to add content ad listener", (Throwable)ex);
                return this;
            }
        }
        
        public Builder forCustomTemplateAd(final String s, final NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener onCustomTemplateAdLoadedListener, final NativeCustomTemplateAd.OnCustomClickListener onCustomClickListener) {
            try {
                final zzs zzajt = this.zzajt;
                final zzep zzep = new zzep(onCustomTemplateAdLoadedListener);
                zzej zzej;
                if (onCustomClickListener == null) {
                    zzej = null;
                }
                else {
                    zzej = new zzeo(onCustomClickListener);
                }
                zzajt.zza(s, zzep, zzej);
            }
            catch (RemoteException ex) {
                zzb.zzd("Failed to add custom template ad listener", (Throwable)ex);
            }
            return this;
        }
        
        public Builder withAdListener(final AdListener adListener) {
            try {
                this.zzajt.zzb(new zzc(adListener));
                return this;
            }
            catch (RemoteException ex) {
                zzb.zzd("Failed to set AdListener.", (Throwable)ex);
                return this;
            }
        }
        
        public Builder withCorrelator(@NonNull final Correlator correlator) {
            zzac.zzy(correlator);
            try {
                this.zzajt.zzb(correlator.zzdh());
                return this;
            }
            catch (RemoteException ex) {
                zzb.zzd("Failed to set correlator.", (Throwable)ex);
                return this;
            }
        }
        
        public Builder withNativeAdOptions(final NativeAdOptions nativeAdOptions) {
            try {
                this.zzajt.zza(new NativeAdOptionsParcel(nativeAdOptions));
                return this;
            }
            catch (RemoteException ex) {
                zzb.zzd("Failed to specify native ad options", (Throwable)ex);
                return this;
            }
        }
    }
}
