// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import android.os.IInterface;

public interface zzy extends IInterface
{
    zzd zza(final zzd p0, final int p1, final int p2) throws RemoteException;
    
    zzd zza(final zzd p0, final SignInButtonConfig p1) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzy
    {
        public static zzy zzdy(final IBinder binder) {
            zzy zzy;
            if (binder == null) {
                zzy = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzy) {
                    zzy = (zzy)queryLocalInterface;
                }
                else {
                    zzy = new zzy.zza.zza(binder);
                }
            }
            return zzy;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            boolean onTransact = false;
            switch (n) {
                default: {
                    onTransact = super.onTransact(n, parcel, parcel2, n2);
                    break;
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.common.internal.ISignInButtonCreator");
                    onTransact = true;
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
                    final zzd zza = this.zza(zzd.zza.zzfe(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    IBinder binder;
                    if (zza != null) {
                        binder = zza.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    parcel2.writeStrongBinder(binder);
                    onTransact = true;
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
                    final zzd zzfe = zzd.zza.zzfe(parcel.readStrongBinder());
                    SignInButtonConfig signInButtonConfig;
                    if (parcel.readInt() != 0) {
                        signInButtonConfig = (SignInButtonConfig)SignInButtonConfig.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        signInButtonConfig = null;
                    }
                    final zzd zza2 = this.zza(zzfe, signInButtonConfig);
                    parcel2.writeNoException();
                    IBinder binder2 = null;
                    if (zza2 != null) {
                        binder2 = zza2.asBinder();
                    }
                    parcel2.writeStrongBinder(binder2);
                    onTransact = true;
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zzy
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public zzd zza(final zzd zzd, final int n, final int n2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.ISignInButtonCreator");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    this.zzajf.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.dynamic.zzd.zza.zzfe(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public zzd zza(final zzd zzd, final SignInButtonConfig signInButtonConfig) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.ISignInButtonCreator");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (signInButtonConfig != null) {
                        obtain.writeInt(1);
                        signInButtonConfig.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.zzajf.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.dynamic.zzd.zza.zzfe(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
