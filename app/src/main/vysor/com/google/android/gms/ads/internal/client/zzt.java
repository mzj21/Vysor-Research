// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IBinder;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.dynamic.zzd;
import android.os.IInterface;

public interface zzt extends IInterface
{
    IBinder zza(final zzd p0, final String p1, final zzgq p2, final int p3) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzt
    {
        public static zzt zzp(final IBinder binder) {
            zzt zzt;
            if (binder == null) {
                zzt = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilderCreator");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzt) {
                    zzt = (zzt)queryLocalInterface;
                }
                else {
                    zzt = new zzt.zza.zza(binder);
                }
            }
            return zzt;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            boolean onTransact = true;
            switch (n) {
                default: {
                    onTransact = super.onTransact(n, parcel, parcel2, n2);
                    break;
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.ads.internal.client.IAdLoaderBuilderCreator");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilderCreator");
                    final IBinder zza = this.zza(zzd.zza.zzfe(parcel.readStrongBinder()), parcel.readString(), zzgq.zza.zzam(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(zza);
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
            public IBinder zza(final zzd zzd, final String s, final zzgq zzgq, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdLoaderBuilderCreator");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    IBinder binder2 = null;
                    if (zzgq != null) {
                        binder2 = zzgq.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeInt(n);
                    this.zzajf.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
