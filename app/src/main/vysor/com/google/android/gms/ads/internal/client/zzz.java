// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.dynamic.zzd;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzz extends IInterface
{
    void initialize() throws RemoteException;
    
    void setAppMuted(final boolean p0) throws RemoteException;
    
    void setAppVolume(final float p0) throws RemoteException;
    
    void zzb(final zzd p0, final String p1) throws RemoteException;
    
    void zzw(final String p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzz
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
        }
        
        public static zzz zzu(final IBinder binder) {
            zzz zzz;
            if (binder == null) {
                zzz = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzz) {
                    zzz = (zzz)queryLocalInterface;
                }
                else {
                    zzz = new zzz.zza.zza(binder);
                }
            }
            return zzz;
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
                    parcel2.writeString("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
                    this.initialize();
                    parcel2.writeNoException();
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
                    this.setAppVolume(parcel.readFloat());
                    parcel2.writeNoException();
                    break;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
                    this.zzw(parcel.readString());
                    parcel2.writeNoException();
                    break;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
                    this.setAppMuted(parcel.readInt() != 0 && onTransact);
                    parcel2.writeNoException();
                    break;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
                    this.zzb(zzd.zza.zzfe(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zzz
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public void initialize() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
                    this.zzajf.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setAppMuted(final boolean b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
                    int n = 0;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.zzajf.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setAppVolume(final float n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
                    obtain.writeFloat(n);
                    this.zzajf.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zzb(final zzd zzd, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.zzajf.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zzw(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
                    obtain.writeString(s);
                    this.zzajf.transact(3, obtain, obtain2, 0);
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
