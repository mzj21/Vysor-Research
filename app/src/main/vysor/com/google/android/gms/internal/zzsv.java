// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import android.os.IInterface;

public interface zzsv extends IInterface
{
    int zza(final zzd p0, final String p1, final boolean p2) throws RemoteException;
    
    zzd zza(final zzd p0, final String p1, final int p2) throws RemoteException;
    
    int zzf(final zzd p0, final String p1) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzsv
    {
        public static zzsv zzff(final IBinder binder) {
            zzsv zzsv;
            if (binder == null) {
                zzsv = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzsv) {
                    zzsv = (zzsv)queryLocalInterface;
                }
                else {
                    zzsv = new zzsv.zza.zza(binder);
                }
            }
            return zzsv;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            boolean onTransact = true;
            switch (n) {
                default: {
                    onTransact = super.onTransact(n, parcel, parcel2, n2);
                    break;
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.dynamite.IDynamiteLoader");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    final int zzf = this.zzf(zzd.zza.zzfe(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zzf);
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    final zzd zza = this.zza(zzd.zza.zzfe(parcel.readStrongBinder()), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    IBinder binder;
                    if (zza != null) {
                        binder = zza.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    parcel2.writeStrongBinder(binder);
                    break;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    final int zza2 = this.zza(zzd.zza.zzfe(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0 && onTransact);
                    parcel2.writeNoException();
                    parcel2.writeInt(zza2);
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zzsv
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public int zza(final zzd zzd, final String s, final boolean b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamite.IDynamiteLoader");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    int n = 0;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
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
            public zzd zza(final zzd zzd, final String s, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamite.IDynamiteLoader");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    this.zzajf.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.dynamic.zzd.zza.zzfe(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int zzf(final zzd zzd, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamite.IDynamiteLoader");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.zzajf.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
