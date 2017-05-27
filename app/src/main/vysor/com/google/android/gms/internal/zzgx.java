// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.OnContextChangedListener;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import java.util.List;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import android.content.Context;
import com.google.android.gms.ads.internal.reward.mediation.client.zza;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import java.util.Set;
import java.util.Date;
import java.util.Collection;
import java.util.HashSet;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.dynamic.zzd;
import java.util.Iterator;
import com.google.ads.mediation.admob.AdMobAdapter;
import android.os.RemoteException;
import org.json.JSONObject;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdapter;

@zziy
public final class zzgx extends zzgr.zza
{
    private final MediationAdapter zzbtz;
    private zzgy zzbua;
    
    public zzgx(final MediationAdapter zzbtz) {
        this.zzbtz = zzbtz;
    }
    
    private Bundle zza(final String s, final int n, final String s2) throws RemoteException {
        final String value = String.valueOf(s);
        while (true) {
            Label_0123: {
                if (value.length() == 0) {
                    break Label_0123;
                }
                final String concat = "Server parameters: ".concat(value);
                zzb.zzdf(concat);
                Bundle bundle = null;
                Label_0141: {
                    Bundle bundle2 = null;
                    Label_0137: {
                        try {
                            bundle = new Bundle();
                            if (s != null) {
                                final JSONObject jsonObject = new JSONObject(s);
                                bundle2 = new Bundle();
                                final Iterator keys = jsonObject.keys();
                                while (keys.hasNext()) {
                                    final String s3 = keys.next();
                                    bundle2.putString(s3, jsonObject.getString(s3));
                                }
                                break Label_0137;
                            }
                            break Label_0141;
                        }
                        catch (Throwable t) {
                            zzb.zzd("Could not get Server Parameters Bundle.", t);
                            throw new RemoteException();
                        }
                        break Label_0123;
                    }
                    bundle = bundle2;
                }
                if (this.zzbtz instanceof AdMobAdapter) {
                    bundle.putString("adJson", s2);
                    bundle.putInt("tagForChildDirectedTreatment", n);
                }
                return bundle;
            }
            final String concat = new String("Server parameters: ");
            continue;
        }
    }
    
    public void destroy() throws RemoteException {
        try {
            this.zzbtz.onDestroy();
        }
        catch (Throwable t) {
            zzb.zzd("Could not destroy adapter.", t);
            throw new RemoteException();
        }
    }
    
    public Bundle getInterstitialAdapterInfo() {
        Bundle interstitialAdapterInfo;
        if (!(this.zzbtz instanceof zzmh)) {
            final String value = String.valueOf(this.zzbtz.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "MediationAdapter is not a v2 MediationInterstitialAdapter: ".concat(value);
            }
            else {
                concat = new String("MediationAdapter is not a v2 MediationInterstitialAdapter: ");
            }
            zzb.zzdf(concat);
            interstitialAdapterInfo = new Bundle();
        }
        else {
            interstitialAdapterInfo = ((zzmh)this.zzbtz).getInterstitialAdapterInfo();
        }
        return interstitialAdapterInfo;
    }
    
    public zzd getView() throws RemoteException {
        if (!(this.zzbtz instanceof MediationBannerAdapter)) {
            final String value = String.valueOf(this.zzbtz.getClass().getCanonicalName());
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
            return zze.zzac(((MediationBannerAdapter)this.zzbtz).getBannerView());
        }
        catch (Throwable t) {
            zzb.zzd("Could not get banner view from adapter.", t);
            throw new RemoteException();
        }
    }
    
    public boolean isInitialized() throws RemoteException {
        if (!(this.zzbtz instanceof MediationRewardedVideoAdAdapter)) {
            final String value = String.valueOf(this.zzbtz.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(value);
            }
            else {
                concat = new String("MediationAdapter is not a MediationRewardedVideoAdAdapter: ");
            }
            zzb.zzdf(concat);
            throw new RemoteException();
        }
        zzb.zzdd("Check if adapter is initialized.");
        try {
            return ((MediationRewardedVideoAdAdapter)this.zzbtz).isInitialized();
        }
        catch (Throwable t) {
            zzb.zzd("Could not check if adapter is initialized.", t);
            throw new RemoteException();
        }
    }
    
    public void pause() throws RemoteException {
        try {
            this.zzbtz.onPause();
        }
        catch (Throwable t) {
            zzb.zzd("Could not pause adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void resume() throws RemoteException {
        try {
            this.zzbtz.onResume();
        }
        catch (Throwable t) {
            zzb.zzd("Could not resume adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void showInterstitial() throws RemoteException {
        if (!(this.zzbtz instanceof MediationInterstitialAdapter)) {
            final String value = String.valueOf(this.zzbtz.getClass().getCanonicalName());
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
            ((MediationInterstitialAdapter)this.zzbtz).showInterstitial();
        }
        catch (Throwable t) {
            zzb.zzd("Could not show interstitial from adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void showVideo() throws RemoteException {
        if (!(this.zzbtz instanceof MediationRewardedVideoAdAdapter)) {
            final String value = String.valueOf(this.zzbtz.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(value);
            }
            else {
                concat = new String("MediationAdapter is not a MediationRewardedVideoAdAdapter: ");
            }
            zzb.zzdf(concat);
            throw new RemoteException();
        }
        zzb.zzdd("Show rewarded video ad from adapter.");
        try {
            ((MediationRewardedVideoAdAdapter)this.zzbtz).showVideo();
        }
        catch (Throwable t) {
            zzb.zzd("Could not show rewarded video ad from adapter.", t);
            throw new RemoteException();
        }
    }
    
    public void zza(final AdRequestParcel adRequestParcel, final String s, final String s2) throws RemoteException {
        if (!(this.zzbtz instanceof MediationRewardedVideoAdAdapter)) {
            final String value = String.valueOf(this.zzbtz.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(value);
            }
            else {
                concat = new String("MediationAdapter is not a MediationRewardedVideoAdAdapter: ");
            }
            zzb.zzdf(concat);
            throw new RemoteException();
        }
        while (true) {
            zzb.zzdd("Requesting rewarded video ad from adapter.");
            while (true) {
                Label_0235: {
                    while (true) {
                        try {
                            final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter)this.zzbtz;
                            if (adRequestParcel.zzawf == null) {
                                break Label_0235;
                            }
                            final HashSet<String> set = new HashSet<String>(adRequestParcel.zzawf);
                            Date date;
                            if (adRequestParcel.zzawd == -1L) {
                                date = null;
                            }
                            else {
                                date = new Date(adRequestParcel.zzawd);
                            }
                            final zzgw zzgw = new zzgw(date, adRequestParcel.zzawe, set, adRequestParcel.zzawl, adRequestParcel.zzawg, adRequestParcel.zzawh, adRequestParcel.zzaws);
                            if (adRequestParcel.zzawn != null) {
                                final Bundle bundle = adRequestParcel.zzawn.getBundle(mediationRewardedVideoAdAdapter.getClass().getName());
                                mediationRewardedVideoAdAdapter.loadAd(zzgw, this.zza(s, adRequestParcel.zzawh, s2), bundle);
                                return;
                            }
                        }
                        catch (Throwable t) {
                            zzb.zzd("Could not load rewarded video ad from adapter.", t);
                            throw new RemoteException();
                        }
                        final Bundle bundle = null;
                        continue;
                    }
                }
                final HashSet<String> set = null;
                continue;
            }
        }
    }
    
    public void zza(final zzd zzd, final AdRequestParcel adRequestParcel, final String s, final zza zza, final String s2) throws RemoteException {
        if (!(this.zzbtz instanceof MediationRewardedVideoAdAdapter)) {
            final String value = String.valueOf(this.zzbtz.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ".concat(value);
            }
            else {
                concat = new String("MediationAdapter is not a MediationRewardedVideoAdAdapter: ");
            }
            zzb.zzdf(concat);
            throw new RemoteException();
        }
        while (true) {
            zzb.zzdd("Initialize rewarded video adapter.");
            while (true) {
                Label_0254: {
                    while (true) {
                        try {
                            final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter)this.zzbtz;
                            if (adRequestParcel.zzawf == null) {
                                break Label_0254;
                            }
                            final HashSet<String> set = new HashSet<String>(adRequestParcel.zzawf);
                            Date date;
                            if (adRequestParcel.zzawd == -1L) {
                                date = null;
                            }
                            else {
                                date = new Date(adRequestParcel.zzawd);
                            }
                            final zzgw zzgw = new zzgw(date, adRequestParcel.zzawe, set, adRequestParcel.zzawl, adRequestParcel.zzawg, adRequestParcel.zzawh, adRequestParcel.zzaws);
                            if (adRequestParcel.zzawn != null) {
                                final Bundle bundle = adRequestParcel.zzawn.getBundle(mediationRewardedVideoAdAdapter.getClass().getName());
                                mediationRewardedVideoAdAdapter.initialize(zze.zzae(zzd), zzgw, s, new com.google.android.gms.ads.internal.reward.mediation.client.zzb(zza), this.zza(s2, adRequestParcel.zzawh, null), bundle);
                                return;
                            }
                        }
                        catch (Throwable t) {
                            zzb.zzd("Could not initialize rewarded video adapter.", t);
                            throw new RemoteException();
                        }
                        final Bundle bundle = null;
                        continue;
                    }
                }
                final HashSet<String> set = null;
                continue;
            }
        }
    }
    
    public void zza(final zzd zzd, final AdRequestParcel adRequestParcel, final String s, final zzgs zzgs) throws RemoteException {
        this.zza(zzd, adRequestParcel, s, null, zzgs);
    }
    
    public void zza(final zzd zzd, final AdRequestParcel adRequestParcel, final String s, final String s2, final zzgs zzgs) throws RemoteException {
        if (!(this.zzbtz instanceof MediationInterstitialAdapter)) {
            final String value = String.valueOf(this.zzbtz.getClass().getCanonicalName());
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
        while (true) {
            zzb.zzdd("Requesting interstitial ad from adapter.");
            while (true) {
                Label_0254: {
                    while (true) {
                        try {
                            final MediationInterstitialAdapter mediationInterstitialAdapter = (MediationInterstitialAdapter)this.zzbtz;
                            if (adRequestParcel.zzawf == null) {
                                break Label_0254;
                            }
                            final HashSet<String> set = new HashSet<String>(adRequestParcel.zzawf);
                            Date date;
                            if (adRequestParcel.zzawd == -1L) {
                                date = null;
                            }
                            else {
                                date = new Date(adRequestParcel.zzawd);
                            }
                            final zzgw zzgw = new zzgw(date, adRequestParcel.zzawe, set, adRequestParcel.zzawl, adRequestParcel.zzawg, adRequestParcel.zzawh, adRequestParcel.zzaws);
                            if (adRequestParcel.zzawn != null) {
                                final Bundle bundle = adRequestParcel.zzawn.getBundle(mediationInterstitialAdapter.getClass().getName());
                                mediationInterstitialAdapter.requestInterstitialAd(zze.zzae(zzd), new zzgy(zzgs), this.zza(s, adRequestParcel.zzawh, s2), zzgw, bundle);
                                return;
                            }
                        }
                        catch (Throwable t) {
                            zzb.zzd("Could not request interstitial ad from adapter.", t);
                            throw new RemoteException();
                        }
                        final Bundle bundle = null;
                        continue;
                    }
                }
                final HashSet<String> set = null;
                continue;
            }
        }
    }
    
    public void zza(final zzd zzd, final AdRequestParcel adRequestParcel, final String s, final String s2, final zzgs zzgs, final NativeAdOptionsParcel nativeAdOptionsParcel, final List<String> list) throws RemoteException {
        if (!(this.zzbtz instanceof MediationNativeAdapter)) {
            final String value = String.valueOf(this.zzbtz.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "MediationAdapter is not a MediationNativeAdapter: ".concat(value);
            }
            else {
                concat = new String("MediationAdapter is not a MediationNativeAdapter: ");
            }
            zzb.zzdf(concat);
            throw new RemoteException();
        }
        while (true) {
            while (true) {
                Label_0262: {
                    while (true) {
                        try {
                            final MediationNativeAdapter mediationNativeAdapter = (MediationNativeAdapter)this.zzbtz;
                            if (adRequestParcel.zzawf == null) {
                                break Label_0262;
                            }
                            final HashSet<String> set = new HashSet<String>(adRequestParcel.zzawf);
                            Date date;
                            if (adRequestParcel.zzawd == -1L) {
                                date = null;
                            }
                            else {
                                date = new Date(adRequestParcel.zzawd);
                            }
                            final zzhb zzhb = new zzhb(date, adRequestParcel.zzawe, set, adRequestParcel.zzawl, adRequestParcel.zzawg, adRequestParcel.zzawh, nativeAdOptionsParcel, list, adRequestParcel.zzaws);
                            if (adRequestParcel.zzawn != null) {
                                final Bundle bundle = adRequestParcel.zzawn.getBundle(mediationNativeAdapter.getClass().getName());
                                this.zzbua = new zzgy(zzgs);
                                mediationNativeAdapter.requestNativeAd(zze.zzae(zzd), this.zzbua, this.zza(s, adRequestParcel.zzawh, s2), zzhb, bundle);
                                return;
                            }
                        }
                        catch (Throwable t) {
                            zzb.zzd("Could not request native ad from adapter.", t);
                            throw new RemoteException();
                        }
                        final Bundle bundle = null;
                        continue;
                    }
                }
                final HashSet<String> set = null;
                continue;
            }
        }
    }
    
    public void zza(final zzd zzd, final AdSizeParcel adSizeParcel, final AdRequestParcel adRequestParcel, final String s, final zzgs zzgs) throws RemoteException {
        this.zza(zzd, adSizeParcel, adRequestParcel, s, null, zzgs);
    }
    
    public void zza(final zzd zzd, final AdSizeParcel adSizeParcel, final AdRequestParcel adRequestParcel, final String s, final String s2, final zzgs zzgs) throws RemoteException {
        if (!(this.zzbtz instanceof MediationBannerAdapter)) {
            final String value = String.valueOf(this.zzbtz.getClass().getCanonicalName());
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
        while (true) {
            zzb.zzdd("Requesting banner ad from adapter.");
            while (true) {
                Label_0270: {
                    while (true) {
                        try {
                            final MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter)this.zzbtz;
                            if (adRequestParcel.zzawf == null) {
                                break Label_0270;
                            }
                            final HashSet<String> set = new HashSet<String>(adRequestParcel.zzawf);
                            Date date;
                            if (adRequestParcel.zzawd == -1L) {
                                date = null;
                            }
                            else {
                                date = new Date(adRequestParcel.zzawd);
                            }
                            final zzgw zzgw = new zzgw(date, adRequestParcel.zzawe, set, adRequestParcel.zzawl, adRequestParcel.zzawg, adRequestParcel.zzawh, adRequestParcel.zzaws);
                            if (adRequestParcel.zzawn != null) {
                                final Bundle bundle = adRequestParcel.zzawn.getBundle(mediationBannerAdapter.getClass().getName());
                                mediationBannerAdapter.requestBannerAd(zze.zzae(zzd), new zzgy(zzgs), this.zza(s, adRequestParcel.zzawh, s2), com.google.android.gms.ads.zza.zza(adSizeParcel.width, adSizeParcel.height, adSizeParcel.zzaxi), zzgw, bundle);
                                return;
                            }
                        }
                        catch (Throwable t) {
                            zzb.zzd("Could not request banner ad from adapter.", t);
                            throw new RemoteException();
                        }
                        final Bundle bundle = null;
                        continue;
                    }
                }
                final HashSet<String> set = null;
                continue;
            }
        }
    }
    
    public void zzc(final AdRequestParcel adRequestParcel, final String s) throws RemoteException {
        this.zza(adRequestParcel, s, null);
    }
    
    public void zzj(final zzd zzd) throws RemoteException {
        try {
            ((OnContextChangedListener)this.zzbtz).onContextChanged(zze.zzae(zzd));
        }
        catch (Throwable t) {
            zzb.zza("Could not inform adapter of changed context", t);
        }
    }
    
    public zzgu zznm() {
        final NativeAdMapper zznq = this.zzbua.zznq();
        zzgz zzgz;
        if (zznq instanceof NativeAppInstallAdMapper) {
            zzgz = new zzgz((NativeAppInstallAdMapper)zznq);
        }
        else {
            zzgz = null;
        }
        return zzgz;
    }
    
    public zzgv zznn() {
        final NativeAdMapper zznq = this.zzbua.zznq();
        zzha zzha;
        if (zznq instanceof NativeContentAdMapper) {
            zzha = new zzha((NativeContentAdMapper)zznq);
        }
        else {
            zzha = null;
        }
        return zzha;
    }
    
    public Bundle zzno() {
        Bundle zzno;
        if (!(this.zzbtz instanceof zzmg)) {
            final String value = String.valueOf(this.zzbtz.getClass().getCanonicalName());
            String concat;
            if (value.length() != 0) {
                concat = "MediationAdapter is not a v2 MediationBannerAdapter: ".concat(value);
            }
            else {
                concat = new String("MediationAdapter is not a v2 MediationBannerAdapter: ");
            }
            zzb.zzdf(concat);
            zzno = new Bundle();
        }
        else {
            zzno = ((zzmg)this.zzbtz).zzno();
        }
        return zzno;
    }
    
    public Bundle zznp() {
        return new Bundle();
    }
}
