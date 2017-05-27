// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IBinder;
import com.google.android.gms.dynamic.zzd;
import android.os.IInterface;

public interface zzea extends IInterface
{
    IBinder zza(final zzd p0, final zzd p1, final zzd p2, final int p3) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzea
    {
        public static zzea zzad(final IBinder binder) {
            zzea zzea;
            if (binder == null) {
                zzea = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegateCreator");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzea) {
                    zzea = (zzea)queryLocalInterface;
                }
                else {
                    zzea = new zzea.zza.zza(binder);
                }
            }
            return zzea;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            boolean onTransact = true;
            switch (n) {
                default: {
                    onTransact = super.onTransact(n, parcel, parcel2, n2);
                    break;
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegateCreator");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegateCreator");
                    final IBinder zza = this.zza(zzd.zza.zzfe(parcel.readStrongBinder()), zzd.zza.zzfe(parcel.readStrongBinder()), zzd.zza.zzfe(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(zza);
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zzea
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public IBinder zza(final zzd zzd, final zzd zzd2, final zzd zzd3, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegateCreator");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    IBinder binder2;
                    if (zzd2 != null) {
                        binder2 = zzd2.asBinder();
                    }
                    else {
                        binder2 = null;
                    }
                    obtain.writeStrongBinder(binder2);
                    IBinder binder3 = null;
                    if (zzd3 != null) {
                        binder3 = zzd3.asBinder();
                    }
                    obtain.writeStrongBinder(binder3);
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
