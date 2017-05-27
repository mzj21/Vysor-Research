// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.reward.client;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzd extends IInterface
{
    void onRewardedVideoAdClosed() throws RemoteException;
    
    void onRewardedVideoAdFailedToLoad(final int p0) throws RemoteException;
    
    void onRewardedVideoAdLeftApplication() throws RemoteException;
    
    void onRewardedVideoAdLoaded() throws RemoteException;
    
    void onRewardedVideoAdOpened() throws RemoteException;
    
    void onRewardedVideoStarted() throws RemoteException;
    
    void zza(final com.google.android.gms.ads.internal.reward.client.zza p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzd
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
        }
        
        public static zzd zzbj(final IBinder binder) {
            zzd zzd;
            if (binder == null) {
                zzd = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzd) {
                    zzd = (zzd)queryLocalInterface;
                }
                else {
                    zzd = new zzd.zza.zza(binder);
                }
            }
            return zzd;
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            boolean onTransact = true;
            switch (n) {
                default: {
                    onTransact = super.onTransact(n, parcel, parcel2, n2);
                    break;
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                    this.onRewardedVideoAdLoaded();
                    parcel2.writeNoException();
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                    this.onRewardedVideoAdOpened();
                    parcel2.writeNoException();
                    break;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                    this.onRewardedVideoStarted();
                    parcel2.writeNoException();
                    break;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                    this.onRewardedVideoAdClosed();
                    parcel2.writeNoException();
                    break;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                    this.zza(zza.zza.zzbg(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                    this.onRewardedVideoAdLeftApplication();
                    parcel2.writeNoException();
                    break;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                    this.onRewardedVideoAdFailedToLoad(parcel.readInt());
                    parcel2.writeNoException();
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zzd
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public void onRewardedVideoAdClosed() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                    this.zzajf.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onRewardedVideoAdFailedToLoad(final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                    obtain.writeInt(n);
                    this.zzajf.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onRewardedVideoAdLeftApplication() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                    this.zzajf.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onRewardedVideoAdLoaded() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                    this.zzajf.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onRewardedVideoAdOpened() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                    this.zzajf.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onRewardedVideoStarted() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                    this.zzajf.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zza(final com.google.android.gms.ads.internal.reward.client.zza zza) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                    IBinder binder;
                    if (zza != null) {
                        binder = zza.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(5, obtain, obtain2, 0);
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
