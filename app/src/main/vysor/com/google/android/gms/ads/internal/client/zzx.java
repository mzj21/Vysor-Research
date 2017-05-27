// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable$Creator;
import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.ads.internal.reward.client.zzb;
import com.google.android.gms.internal.zzdz;
import com.google.android.gms.internal.zzhy;
import com.google.android.gms.internal.zzhp;
import android.os.RemoteException;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.dynamic.zzd;
import android.os.IInterface;

public interface zzx extends IInterface
{
    zzs createAdLoaderBuilder(final zzd p0, final String p1, final zzgq p2, final int p3) throws RemoteException;
    
    zzhp createAdOverlay(final zzd p0) throws RemoteException;
    
    zzu createBannerAdManager(final zzd p0, final AdSizeParcel p1, final String p2, final zzgq p3, final int p4) throws RemoteException;
    
    zzhy createInAppPurchaseManager(final zzd p0) throws RemoteException;
    
    zzu createInterstitialAdManager(final zzd p0, final AdSizeParcel p1, final String p2, final zzgq p3, final int p4) throws RemoteException;
    
    zzdz createNativeAdViewDelegate(final zzd p0, final zzd p1) throws RemoteException;
    
    zzb createRewardedVideoAd(final zzd p0, final zzgq p1, final int p2) throws RemoteException;
    
    zzu createSearchAdManager(final zzd p0, final AdSizeParcel p1, final String p2, final int p3) throws RemoteException;
    
    zzz getMobileAdsSettingsManager(final zzd p0) throws RemoteException;
    
    zzz getMobileAdsSettingsManagerWithClientJarVersion(final zzd p0, final int p1) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzx
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.client.IClientApi");
        }
        
        public static zzx asInterface(final IBinder binder) {
            zzx zzx;
            if (binder == null) {
                zzx = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.client.IClientApi");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzx) {
                    zzx = (zzx)queryLocalInterface;
                }
                else {
                    zzx = new zzx.zza.zza(binder);
                }
            }
            return zzx;
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            boolean onTransact = false;
            switch (n) {
                default: {
                    onTransact = super.onTransact(n, parcel, parcel2, n2);
                    break;
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.ads.internal.client.IClientApi");
                    onTransact = true;
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    final zzd zzfe = zzd.zza.zzfe(parcel.readStrongBinder());
                    AdSizeParcel adSizeParcel;
                    if (parcel.readInt() != 0) {
                        adSizeParcel = (AdSizeParcel)AdSizeParcel.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        adSizeParcel = null;
                    }
                    final zzu bannerAdManager = this.createBannerAdManager(zzfe, adSizeParcel, parcel.readString(), zzgq.zza.zzam(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    IBinder binder = null;
                    if (bannerAdManager != null) {
                        binder = bannerAdManager.asBinder();
                    }
                    parcel2.writeStrongBinder(binder);
                    onTransact = true;
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    final zzd zzfe2 = zzd.zza.zzfe(parcel.readStrongBinder());
                    AdSizeParcel adSizeParcel2;
                    if (parcel.readInt() != 0) {
                        adSizeParcel2 = (AdSizeParcel)AdSizeParcel.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        adSizeParcel2 = null;
                    }
                    final zzu interstitialAdManager = this.createInterstitialAdManager(zzfe2, adSizeParcel2, parcel.readString(), zzgq.zza.zzam(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    IBinder binder2 = null;
                    if (interstitialAdManager != null) {
                        binder2 = interstitialAdManager.asBinder();
                    }
                    parcel2.writeStrongBinder(binder2);
                    onTransact = true;
                    break;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    final zzs adLoaderBuilder = this.createAdLoaderBuilder(zzd.zza.zzfe(parcel.readStrongBinder()), parcel.readString(), zzgq.zza.zzam(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    IBinder binder3 = null;
                    if (adLoaderBuilder != null) {
                        binder3 = adLoaderBuilder.asBinder();
                    }
                    parcel2.writeStrongBinder(binder3);
                    onTransact = true;
                    break;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    final zzz mobileAdsSettingsManager = this.getMobileAdsSettingsManager(zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    IBinder binder4 = null;
                    if (mobileAdsSettingsManager != null) {
                        binder4 = mobileAdsSettingsManager.asBinder();
                    }
                    parcel2.writeStrongBinder(binder4);
                    onTransact = true;
                    break;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    final zzdz nativeAdViewDelegate = this.createNativeAdViewDelegate(zzd.zza.zzfe(parcel.readStrongBinder()), zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    IBinder binder5 = null;
                    if (nativeAdViewDelegate != null) {
                        binder5 = nativeAdViewDelegate.asBinder();
                    }
                    parcel2.writeStrongBinder(binder5);
                    onTransact = true;
                    break;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    final zzb rewardedVideoAd = this.createRewardedVideoAd(zzd.zza.zzfe(parcel.readStrongBinder()), zzgq.zza.zzam(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    IBinder binder6 = null;
                    if (rewardedVideoAd != null) {
                        binder6 = rewardedVideoAd.asBinder();
                    }
                    parcel2.writeStrongBinder(binder6);
                    onTransact = true;
                    break;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    final zzhy inAppPurchaseManager = this.createInAppPurchaseManager(zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    IBinder binder7 = null;
                    if (inAppPurchaseManager != null) {
                        binder7 = inAppPurchaseManager.asBinder();
                    }
                    parcel2.writeStrongBinder(binder7);
                    onTransact = true;
                    break;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    final zzhp adOverlay = this.createAdOverlay(zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    IBinder binder8 = null;
                    if (adOverlay != null) {
                        binder8 = adOverlay.asBinder();
                    }
                    parcel2.writeStrongBinder(binder8);
                    onTransact = true;
                    break;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    final zzz mobileAdsSettingsManagerWithClientJarVersion = this.getMobileAdsSettingsManagerWithClientJarVersion(zzd.zza.zzfe(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    IBinder binder9 = null;
                    if (mobileAdsSettingsManagerWithClientJarVersion != null) {
                        binder9 = mobileAdsSettingsManagerWithClientJarVersion.asBinder();
                    }
                    parcel2.writeStrongBinder(binder9);
                    onTransact = true;
                    break;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    final zzd zzfe3 = zzd.zza.zzfe(parcel.readStrongBinder());
                    AdSizeParcel adSizeParcel3;
                    if (parcel.readInt() != 0) {
                        adSizeParcel3 = (AdSizeParcel)AdSizeParcel.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        adSizeParcel3 = null;
                    }
                    final zzu searchAdManager = this.createSearchAdManager(zzfe3, adSizeParcel3, parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    IBinder binder10 = null;
                    if (searchAdManager != null) {
                        binder10 = searchAdManager.asBinder();
                    }
                    parcel2.writeStrongBinder(binder10);
                    onTransact = true;
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zzx
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public zzs createAdLoaderBuilder(final zzd zzd, final String s, final zzgq zzgq, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    IBinder binder2 = null;
                    if (zzgq != null) {
                        binder2 = zzgq.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeInt(n);
                    this.zzajf.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzs.zza.zzo(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public zzhp createAdOverlay(final zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzhp.zza.zzat(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public zzu createBannerAdManager(final zzd zzd, final AdSizeParcel adSizeParcel, final String s, final zzgq zzgq, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (adSizeParcel != null) {
                        obtain.writeInt(1);
                        adSizeParcel.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(s);
                    IBinder binder2 = null;
                    if (zzgq != null) {
                        binder2 = zzgq.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeInt(n);
                    this.zzajf.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzu.zza.zzq(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public zzhy createInAppPurchaseManager(final zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzhy.zza.zzay(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public zzu createInterstitialAdManager(final zzd zzd, final AdSizeParcel adSizeParcel, final String s, final zzgq zzgq, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (adSizeParcel != null) {
                        obtain.writeInt(1);
                        adSizeParcel.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(s);
                    IBinder binder2 = null;
                    if (zzgq != null) {
                        binder2 = zzgq.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeInt(n);
                    this.zzajf.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzu.zza.zzq(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public zzdz createNativeAdViewDelegate(final zzd zzd, final zzd zzd2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    IBinder binder2 = null;
                    if (zzd2 != null) {
                        binder2 = zzd2.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    this.zzajf.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzdz.zza.zzac(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public zzb createRewardedVideoAd(final zzd zzd, final zzgq zzgq, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    IBinder binder2 = null;
                    if (zzgq != null) {
                        binder2 = zzgq.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeInt(n);
                    this.zzajf.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzb.zza.zzbh(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public zzu createSearchAdManager(final zzd zzd, final AdSizeParcel adSizeParcel, final String s, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (adSizeParcel != null) {
                        obtain.writeInt(1);
                        adSizeParcel.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    this.zzajf.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzu.zza.zzq(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public zzz getMobileAdsSettingsManager(final zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzz.zza.zzu(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public zzz getMobileAdsSettingsManagerWithClientJarVersion(final zzd zzd, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    this.zzajf.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzz.zza.zzu(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
