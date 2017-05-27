// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Binder;
import android.os.RemoteException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;

public interface zzu extends IInterface
{
    void zza(final int p0, final IBinder p1, final Bundle p2) throws RemoteException;
    
    void zzb(final int p0, final Bundle p1) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzu
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.common.internal.IGmsCallbacks");
        }
        
        public static zzu zzdu(final IBinder binder) {
            zzu zzu;
            if (binder == null) {
                zzu = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.IGmsCallbacks");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzu) {
                    zzu = (zzu)queryLocalInterface;
                }
                else {
                    zzu = new zzu.zza.zza(binder);
                }
            }
            return zzu;
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
                    parcel2.writeString("com.google.android.gms.common.internal.IGmsCallbacks");
                    onTransact = true;
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsCallbacks");
                    final int int1 = parcel.readInt();
                    final IBinder strongBinder = parcel.readStrongBinder();
                    final int int2 = parcel.readInt();
                    Bundle bundle = null;
                    if (int2 != 0) {
                        bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.zza(int1, strongBinder, bundle);
                    parcel2.writeNoException();
                    onTransact = true;
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsCallbacks");
                    final int int3 = parcel.readInt();
                    final int int4 = parcel.readInt();
                    Bundle bundle2 = null;
                    if (int4 != 0) {
                        bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.zzb(int3, bundle2);
                    parcel2.writeNoException();
                    onTransact = true;
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zzu
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public void zza(final int n, final IBinder binder, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsCallbacks");
                    obtain.writeInt(n);
                    obtain.writeStrongBinder(binder);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
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
            public void zzb(final int n, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsCallbacks");
                    obtain.writeInt(n);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
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
