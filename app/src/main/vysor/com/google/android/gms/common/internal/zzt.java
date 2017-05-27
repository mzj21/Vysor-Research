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

public interface zzt extends IInterface
{
    zzd zzaph() throws RemoteException;
    
    int zzapi() throws RemoteException;
    
    public abstract static class zza extends Binder implements zzt
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.common.internal.ICertData");
        }
        
        public static zzt zzdt(final IBinder binder) {
            zzt zzt;
            if (binder == null) {
                zzt = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.ICertData");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzt) {
                    zzt = (zzt)queryLocalInterface;
                }
                else {
                    zzt = new zzt.zza.zza(binder);
                }
            }
            return zzt;
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
                    parcel2.writeString("com.google.android.gms.common.internal.ICertData");
                    onTransact = true;
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.ICertData");
                    final zzd zzaph = this.zzaph();
                    parcel2.writeNoException();
                    IBinder binder;
                    if (zzaph != null) {
                        binder = zzaph.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    parcel2.writeStrongBinder(binder);
                    onTransact = true;
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.ICertData");
                    final int zzapi = this.zzapi();
                    parcel2.writeNoException();
                    parcel2.writeInt(zzapi);
                    onTransact = true;
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zzt
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public zzd zzaph() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.ICertData");
                    this.zzajf.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzd.zza.zzfe(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int zzapi() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.ICertData");
                    this.zzajf.transact(2, obtain, obtain2, 0);
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
