// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzib extends IInterface
{
    boolean isValidPurchase(final String p0) throws RemoteException;
    
    void zza(final zzia p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzib
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.purchase.client.IPlayStorePurchaseListener");
        }
        
        public static zzib zzbb(final IBinder binder) {
            zzib zzib;
            if (binder == null) {
                zzib = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IPlayStorePurchaseListener");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzib) {
                    zzib = (zzib)queryLocalInterface;
                }
                else {
                    zzib = new zzib.zza.zza(binder);
                }
            }
            return zzib;
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
                    parcel2.writeString("com.google.android.gms.ads.internal.purchase.client.IPlayStorePurchaseListener");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IPlayStorePurchaseListener");
                    final boolean validPurchase = this.isValidPurchase(parcel.readString());
                    parcel2.writeNoException();
                    int n3;
                    if (validPurchase) {
                        n3 = onTransact;
                    }
                    else {
                        n3 = 0;
                    }
                    parcel2.writeInt(n3);
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IPlayStorePurchaseListener");
                    this.zza(zzia.zza.zzba(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
            }
            return onTransact != 0;
        }
        
        private static class zza implements zzib
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public boolean isValidPurchase(final String s) throws RemoteException {
                boolean b = true;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IPlayStorePurchaseListener");
                    obtain.writeString(s);
                    this.zzajf.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        b = false;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zza(final zzia zzia) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IPlayStorePurchaseListener");
                    IBinder binder;
                    if (zzia != null) {
                        binder = zzia.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(2, obtain, obtain2, 0);
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
