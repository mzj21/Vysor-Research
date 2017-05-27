// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.reward.client;

import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import android.os.Parcelable$Creator;
import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzb extends IInterface
{
    void destroy() throws RemoteException;
    
    boolean isLoaded() throws RemoteException;
    
    void pause() throws RemoteException;
    
    void resume() throws RemoteException;
    
    void setUserId(final String p0) throws RemoteException;
    
    void show() throws RemoteException;
    
    void zza(final RewardedVideoAdRequestParcel p0) throws RemoteException;
    
    void zza(final zzd p0) throws RemoteException;
    
    void zzf(final com.google.android.gms.dynamic.zzd p0) throws RemoteException;
    
    void zzg(final com.google.android.gms.dynamic.zzd p0) throws RemoteException;
    
    void zzh(final com.google.android.gms.dynamic.zzd p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzb
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
        }
        
        public static zzb zzbh(final IBinder binder) {
            zzb zzb;
            if (binder == null) {
                zzb = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzb) {
                    zzb = (zzb)queryLocalInterface;
                }
                else {
                    zzb = new zzb.zza.zza(binder);
                }
            }
            return zzb;
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            int onTransact = 1;
            switch (n) {
                default: {
                    onTransact = (super.onTransact(n, parcel, parcel2, n2) ? 1 : 0);
                    break;
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    RewardedVideoAdRequestParcel rewardedVideoAdRequestParcel;
                    if (parcel.readInt() != 0) {
                        rewardedVideoAdRequestParcel = (RewardedVideoAdRequestParcel)RewardedVideoAdRequestParcel.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        rewardedVideoAdRequestParcel = null;
                    }
                    this.zza(rewardedVideoAdRequestParcel);
                    parcel2.writeNoException();
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    this.show();
                    parcel2.writeNoException();
                    break;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    this.zza(zzd.zza.zzbj(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    this.setUserId(parcel.readString());
                    parcel2.writeNoException();
                    break;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    final boolean loaded = this.isLoaded();
                    parcel2.writeNoException();
                    int n3;
                    if (loaded) {
                        n3 = onTransact;
                    }
                    else {
                        n3 = 0;
                    }
                    parcel2.writeInt(n3);
                    break;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    this.pause();
                    parcel2.writeNoException();
                    break;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    this.resume();
                    parcel2.writeNoException();
                    break;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    this.destroy();
                    parcel2.writeNoException();
                    break;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    this.zzf(com.google.android.gms.dynamic.zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    this.zzg(com.google.android.gms.dynamic.zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    this.zzh(com.google.android.gms.dynamic.zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
            }
            return onTransact != 0;
        }
        
        private static class zza implements zzb
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public void destroy() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    this.zzajf.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isLoaded() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    this.zzajf.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    final int int1 = obtain2.readInt();
                    boolean b = false;
                    if (int1 != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void pause() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    this.zzajf.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void resume() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    this.zzajf.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setUserId(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    obtain.writeString(s);
                    this.zzajf.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void show() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    this.zzajf.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zza(final RewardedVideoAdRequestParcel rewardedVideoAdRequestParcel) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    if (rewardedVideoAdRequestParcel != null) {
                        obtain.writeInt(1);
                        rewardedVideoAdRequestParcel.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.zzajf.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zza(final zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zzf(final com.google.android.gms.dynamic.zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zzg(final com.google.android.gms.dynamic.zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zzh(final com.google.android.gms.dynamic.zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
