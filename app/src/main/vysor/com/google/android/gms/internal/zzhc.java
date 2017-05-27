// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import java.util.List;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.ads.mediation.MediationInterstitialListener;
import android.app.Activity;
import com.google.android.gms.ads.internal.reward.mediation.client.zza;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.dynamic.zze;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.dynamic.zzd;
import android.os.Bundle;
import java.util.Iterator;
import java.util.Map;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.HashMap;
import org.json.JSONObject;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

@zziy
public final class zzhc<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> extends zzgr.zza
{
    private final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> zzbuf;
    private final NETWORK_EXTRAS zzbug;
    
    public zzhc(final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> zzbuf, final NETWORK_EXTRAS zzbug) {
        this.zzbuf = zzbuf;
        this.zzbug = zzbug;
    }
    
    private SERVER_PARAMETERS zzb(final String s, final int n, final String s2) throws RemoteException {
        HashMap<String, String> hashMap = null;
        Label_0104: {
            if (s != null) {
                try {
                    final JSONObject jsonObject = new JSONObject(s);
                    hashMap = new HashMap<String, String>(jsonObject.length());
                    final Iterator keys = jsonObject.keys();
                    while (keys.hasNext()) {
                        final String s3 = keys.next();
                        hashMap.put(s3, jsonObject.getString(s3));
                    }
                    break Label_0104;
                }
                catch (Throwable t) {
                    zzb.zzd("Could not get MediationServerParameters.", t);
                    throw new RemoteException();
                }
            }
            hashMap = new HashMap<String, String>(0);
        }
        final Class<SERVER_PARAMETERS> serverParametersType = this.zzbuf.getServerParametersType();
        MediationServerParameters mediationServerParameters = null;
        if (serverParametersType != null) {
            mediationServerParameters = serverParametersType.newInstance();
            mediationServerParameters.load(hashMap);
        }
        return (SERVER_PARAMETERS)mediationServerParameters;
    }
    
    public void destroy() throws RemoteException {
        try {
            this.zzbuf.destroy();
        }
        catch (Throwable t) {
            zzb.zzd("Could not destroy adapter.", t);
            throw new RemoteException();
        }
    }
    
    public Bundle getInterstitialAdapterInfo() {
        return new Bundle();
    }
    
    public zzd getView() throws RemoteException {
        if (!(this.zzbuf instanceof MediationBannerAdapter)) {
            final String value = String.valueOf(this.zzbuf.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "MediationAdapter is not a MediationBannerAdapter: ".concat(value);
            }
            else {
                concat = new String("MediationAdapter is not a MediationBannerAdapter: ");
            }
            zzb.zzdf(concat);
            throw new RemoteException();
        }
        try {
            return zze.zzac(((MediationBannerAdapter)this.zzbuf).getBannerView());
        }
        catch (Throwable t) {
            zzb.zzd("Could not get banner view from adapter.", t);
            throw new RemoteException();
        }
    }
    
    public boolean isInitialized() {
        return true;
    }
    
    public void pause() throws RemoteException {
        throw new RemoteException();
    }
    
    public void resume() throws RemoteException {
        throw new RemoteException();
    }
    
    public void showInterstitial() throws RemoteException {
        if (!(this.zzbuf instanceof MediationInterstitialAdapter)) {
            final String value = String.valueOf(this.zzbuf.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "MediationAdapter is not a MediationInterstitialAdapter: ".concat(value);
            }
            else {
                concat = new String("MediationAdapter is not a MediationInterstitialAdapter: ");
            }
            zzb.zzdf(concat);
            throw new RemoteException();
        }
        zzb.zzdd("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter)this.zzbuf).showInterstitial();
        }
        catch (Throwable t) {
            zzb.zzd("Could not show interstitial from adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void showVideo() {
    }
    
    public void zza(final AdRequestParcel adRequestParcel, final String s, final String s2) {
    }
    
    public void zza(final zzd zzd, final AdRequestParcel adRequestParcel, final String s, final zza zza, final String s2) throws RemoteException {
    }
    
    public void zza(final zzd zzd, final AdRequestParcel adRequestParcel, final String s, final zzgs zzgs) throws RemoteException {
        this.zza(zzd, adRequestParcel, s, null, zzgs);
    }
    
    public void zza(final zzd zzd, final AdRequestParcel adRequestParcel, final String s, final String s2, final zzgs zzgs) throws RemoteException {
        if (!(this.zzbuf instanceof MediationInterstitialAdapter)) {
            final String value = String.valueOf(this.zzbuf.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "MediationAdapter is not a MediationInterstitialAdapter: ".concat(value);
            }
            else {
                concat = new String("MediationAdapter is not a MediationInterstitialAdapter: ");
            }
            zzb.zzdf(concat);
            throw new RemoteException();
        }
        zzb.zzdd("Requesting interstitial ad from adapter.");
        try {
            ((MediationInterstitialAdapter)this.zzbuf).requestInterstitialAd(new zzhd<Object, Object>(zzgs), zze.zzae(zzd), this.zzb(s, adRequestParcel.zzawh, s2), zzhe.zzs(adRequestParcel), this.zzbug);
        }
        catch (Throwable t) {
            zzb.zzd("Could not request interstitial ad from adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void zza(final zzd zzd, final AdRequestParcel adRequestParcel, final String s, final String s2, final zzgs zzgs, final NativeAdOptionsParcel nativeAdOptionsParcel, final List<String> list) {
    }
    
    public void zza(final zzd zzd, final AdSizeParcel adSizeParcel, final AdRequestParcel adRequestParcel, final String s, final zzgs zzgs) throws RemoteException {
        this.zza(zzd, adSizeParcel, adRequestParcel, s, null, zzgs);
    }
    
    public void zza(final zzd zzd, final AdSizeParcel adSizeParcel, final AdRequestParcel adRequestParcel, final String s, final String s2, final zzgs zzgs) throws RemoteException {
        if (!(this.zzbuf instanceof MediationBannerAdapter)) {
            final String value = String.valueOf(this.zzbuf.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "MediationAdapter is not a MediationBannerAdapter: ".concat(value);
            }
            else {
                concat = new String("MediationAdapter is not a MediationBannerAdapter: ");
            }
            zzb.zzdf(concat);
            throw new RemoteException();
        }
        zzb.zzdd("Requesting banner ad from adapter.");
        try {
            ((MediationBannerAdapter)this.zzbuf).requestBannerAd(new zzhd<Object, Object>(zzgs), zze.zzae(zzd), this.zzb(s, adRequestParcel.zzawh, s2), zzhe.zzc(adSizeParcel), zzhe.zzs(adRequestParcel), this.zzbug);
        }
        catch (Throwable t) {
            zzb.zzd("Could not request banner ad from adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void zzc(final AdRequestParcel adRequestParcel, final String s) {
    }
    
    public void zzj(final zzd zzd) throws RemoteException {
    }
    
    public zzgu zznm() {
        return null;
    }
    
    public zzgv zznn() {
        return null;
    }
    
    public Bundle zzno() {
        return new Bundle();
    }
    
    public Bundle zznp() {
        return new Bundle();
    }
}
