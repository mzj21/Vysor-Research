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

public interface zzw extends IInterface
{
    zzd zzauz() throws RemoteException;
    
    zzd zzava() throws RemoteException;
    
    public abstract static class zza extends Binder implements zzw
    {
        public static zzw zzdw(final IBinder binder) {
            zzw zzw;
            if (binder == null) {
                zzw = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.IGoogleCertificatesApi");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzw) {
                    zzw = (zzw)queryLocalInterface;
                }
                else {
                    zzw = new zzw.zza.zza(binder);
                }
            }
            return zzw;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            boolean onTransact = false;
            switch (n) {
                default: {
                    onTransact = super.onTransact(n, parcel, parcel2, n2);
                    break;
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.common.internal.IGoogleCertificatesApi");
                    onTransact = true;
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGoogleCertificatesApi");
                    final zzd zzauz = this.zzauz();
                    parcel2.writeNoException();
                    IBinder binder = null;
                    if (zzauz != null) {
                        binder = zzauz.asBinder();
                    }
                    parcel2.writeStrongBinder(binder);
                    onTransact = true;
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGoogleCertificatesApi");
                    final zzd zzava = this.zzava();
                    parcel2.writeNoException();
                    IBinder binder2 = null;
                    if (zzava != null) {
                        binder2 = zzava.asBinder();
                    }
                    parcel2.writeStrongBinder(binder2);
                    onTransact = true;
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zzw
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public zzd zzauz() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGoogleCertificatesApi");
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
            public zzd zzava() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGoogleCertificatesApi");
                    this.zzajf.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzd.zza.zzfe(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
