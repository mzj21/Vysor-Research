// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.client.zzab;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.internal.client.VideoOptionsParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.os.RemoteException;
import android.os.Bundle;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzl;
import com.google.android.gms.ads.internal.client.zzu;

@zziy
public class zzft extends zzu.zza
{
    private final String zzang;
    private final zzfn zzbpg;
    @Nullable
    private zzl zzbpl;
    private final zzfp zzbps;
    @Nullable
    private zzib zzbpt;
    private String zzbpu;
    
    public zzft(final Context context, final String s, final zzgq zzgq, final VersionInfoParcel versionInfoParcel, final zzd zzd) {
        this(s, new zzfn(context, zzgq, versionInfoParcel, zzd));
    }
    
    zzft(final String zzang, final zzfn zzbpg) {
        this.zzang = zzang;
        this.zzbpg = zzbpg;
        this.zzbps = new zzfp();
        com.google.android.gms.ads.internal.zzu.zzgo().zza(zzbpg);
    }
    
    private void zzmu() {
        if (this.zzbpl != null && this.zzbpt != null) {
            this.zzbpl.zza(this.zzbpt, this.zzbpu);
        }
    }
    
    static boolean zzq(final AdRequestParcel adRequestParcel) {
        final Bundle zzk = zzfq.zzk(adRequestParcel);
        return zzk != null && zzk.containsKey("gw");
    }
    
    static boolean zzr(final AdRequestParcel adRequestParcel) {
        final Bundle zzk = zzfq.zzk(adRequestParcel);
        return zzk != null && zzk.containsKey("_ad");
    }
    
    void abort() {
        if (this.zzbpl == null) {
            this.zzbpl = this.zzbpg.zzbf(this.zzang);
            this.zzbps.zzc(this.zzbpl);
            this.zzmu();
        }
    }
    
    public void destroy() throws RemoteException {
        if (this.zzbpl != null) {
            this.zzbpl.destroy();
        }
    }
    
    @Nullable
    public String getMediationAdapterClassName() throws RemoteException {
        String mediationAdapterClassName;
        if (this.zzbpl != null) {
            mediationAdapterClassName = this.zzbpl.getMediationAdapterClassName();
        }
        else {
            mediationAdapterClassName = null;
        }
        return mediationAdapterClassName;
    }
    
    public boolean isLoading() throws RemoteException {
        return this.zzbpl != null && this.zzbpl.isLoading();
    }
    
    public boolean isReady() throws RemoteException {
        return this.zzbpl != null && this.zzbpl.isReady();
    }
    
    public void pause() throws RemoteException {
        if (this.zzbpl != null) {
            this.zzbpl.pause();
        }
    }
    
    public void resume() throws RemoteException {
        if (this.zzbpl != null) {
            this.zzbpl.resume();
        }
    }
    
    public void setManualImpressionsEnabled(final boolean manualImpressionsEnabled) throws RemoteException {
        this.abort();
        if (this.zzbpl != null) {
            this.zzbpl.setManualImpressionsEnabled(manualImpressionsEnabled);
        }
    }
    
    public void setUserId(final String s) {
    }
    
    public void showInterstitial() throws RemoteException {
        if (this.zzbpl != null) {
            this.zzbpl.showInterstitial();
        }
        else {
            zzb.zzdf("Interstitial ad must be loaded before showInterstitial().");
        }
    }
    
    public void stopLoading() throws RemoteException {
        if (this.zzbpl != null) {
            this.zzbpl.stopLoading();
        }
    }
    
    public void zza(final AdSizeParcel adSizeParcel) throws RemoteException {
        if (this.zzbpl != null) {
            this.zzbpl.zza(adSizeParcel);
        }
    }
    
    public void zza(final VideoOptionsParcel videoOptionsParcel) {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }
    
    public void zza(final zzp zzbpb) throws RemoteException {
        this.zzbps.zzbpb = zzbpb;
        if (this.zzbpl != null) {
            this.zzbps.zzc(this.zzbpl);
        }
    }
    
    public void zza(final zzq zzamy) throws RemoteException {
        this.zzbps.zzamy = zzamy;
        if (this.zzbpl != null) {
            this.zzbps.zzc(this.zzbpl);
        }
    }
    
    public void zza(final zzw zzboy) throws RemoteException {
        this.zzbps.zzboy = zzboy;
        if (this.zzbpl != null) {
            this.zzbps.zzc(this.zzbpl);
        }
    }
    
    public void zza(final zzy zzy) throws RemoteException {
        this.abort();
        if (this.zzbpl != null) {
            this.zzbpl.zza(zzy);
        }
    }
    
    public void zza(final com.google.android.gms.ads.internal.reward.client.zzd zzbpc) {
        this.zzbps.zzbpc = zzbpc;
        if (this.zzbpl != null) {
            this.zzbps.zzc(this.zzbpl);
        }
    }
    
    public void zza(final zzdu zzbpa) throws RemoteException {
        this.zzbps.zzbpa = zzbpa;
        if (this.zzbpl != null) {
            this.zzbps.zzc(this.zzbpl);
        }
    }
    
    public void zza(final zzhx zzboz) throws RemoteException {
        this.zzbps.zzboz = zzboz;
        if (this.zzbpl != null) {
            this.zzbps.zzc(this.zzbpl);
        }
    }
    
    public void zza(final zzib zzbpt, final String zzbpu) throws RemoteException {
        this.zzbpt = zzbpt;
        this.zzbpu = zzbpu;
        this.zzmu();
    }
    
    public boolean zzb(final AdRequestParcel adRequestParcel) throws RemoteException {
        if (zzdi.zzbdg.get()) {
            AdRequestParcel.zzj(adRequestParcel);
        }
        if (!zzq(adRequestParcel)) {
            this.abort();
        }
        if (zzfq.zzm(adRequestParcel)) {
            this.abort();
        }
        if (adRequestParcel.zzawk != null) {
            this.abort();
        }
        boolean b;
        if (this.zzbpl != null) {
            b = this.zzbpl.zzb(adRequestParcel);
        }
        else {
            final zzfq zzgo = com.google.android.gms.ads.internal.zzu.zzgo();
            if (zzr(adRequestParcel)) {
                zzgo.zzb(adRequestParcel, this.zzang);
            }
            final zzfs.zza zza = zzgo.zza(adRequestParcel, this.zzang);
            if (zza != null) {
                if (!zza.zzbpp) {
                    zza.zzmt();
                }
                this.zzbpl = zza.zzbpl;
                zza.zzbpn.zza(this.zzbps);
                this.zzbps.zzc(this.zzbpl);
                this.zzmu();
                b = zza.zzbpq;
            }
            else {
                this.abort();
                b = this.zzbpl.zzb(adRequestParcel);
            }
        }
        return b;
    }
    
    @Nullable
    public com.google.android.gms.dynamic.zzd zzds() throws RemoteException {
        com.google.android.gms.dynamic.zzd zzds;
        if (this.zzbpl != null) {
            zzds = this.zzbpl.zzds();
        }
        else {
            zzds = null;
        }
        return zzds;
    }
    
    @Nullable
    public AdSizeParcel zzdt() throws RemoteException {
        AdSizeParcel zzdt;
        if (this.zzbpl != null) {
            zzdt = this.zzbpl.zzdt();
        }
        else {
            zzdt = null;
        }
        return zzdt;
    }
    
    public void zzdv() throws RemoteException {
        if (this.zzbpl != null) {
            this.zzbpl.zzdv();
        }
        else {
            zzb.zzdf("Interstitial ad must be loaded before pingManualTrackingUrl().");
        }
    }
    
    public zzab zzdw() {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }
}
