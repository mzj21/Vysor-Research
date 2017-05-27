// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.content.Intent;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzia extends IInterface
{
    void finishPurchase() throws RemoteException;
    
    String getProductId() throws RemoteException;
    
    Intent getPurchaseData() throws RemoteException;
    
    int getResultCode() throws RemoteException;
    
    boolean isVerified() throws RemoteException;
    
    public abstract static class zza extends Binder implements zzia
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
        }
        
        public static zzia zzba(final IBinder binder) {
            zzia zzia;
            if (binder == null) {
                zzia = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzia) {
                    zzia = (zzia)queryLocalInterface;
                }
                else {
                    zzia = new zzia.zza.zza(binder);
                }
            }
            return zzia;
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
                    parcel2.writeString("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                    final String productId = this.getProductId();
                    parcel2.writeNoException();
                    parcel2.writeString(productId);
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                    final Intent purchaseData = this.getPurchaseData();
                    parcel2.writeNoException();
                    if (purchaseData != null) {
                        parcel2.writeInt(onTransact);
                        purchaseData.writeToParcel(parcel2, onTransact);
                        break;
                    }
                    parcel2.writeInt(0);
                    break;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                    final int resultCode = this.getResultCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(resultCode);
                    break;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                    final boolean verified = this.isVerified();
                    parcel2.writeNoException();
                    int n3 = 0;
                    if (verified) {
                        n3 = onTransact;
                    }
                    parcel2.writeInt(n3);
                    break;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                    this.finishPurchase();
                    parcel2.writeNoException();
                    break;
                }
            }
            return onTransact != 0;
        }
        
        private static class zza implements zzia
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public void finishPurchase() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                    this.zzajf.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String getProductId() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                    this.zzajf.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Intent getPurchaseData() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                    this.zzajf.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    Intent intent;
                    if (obtain2.readInt() != 0) {
                        intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        intent = null;
                    }
                    return intent;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int getResultCode() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                    this.zzajf.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isVerified() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
                    this.zzajf.transact(4, obtain, obtain2, 0);
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
        }
    }
}
