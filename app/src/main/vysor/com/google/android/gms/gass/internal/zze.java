// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.gass.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface zze extends IInterface
{
    GassResponseParcel zza(final GassRequestParcel p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zze
    {
        public static zze zzgr(final IBinder binder) {
            zze zze;
            if (binder == null) {
                zze = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.gass.internal.IGassService");
                if (queryLocalInterface != null && queryLocalInterface instanceof zze) {
                    zze = (zze)queryLocalInterface;
                }
                else {
                    zze = new zze.zza.zza(binder);
                }
            }
            return zze;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            boolean onTransact = false;
            switch (n) {
                default: {
                    onTransact = super.onTransact(n, parcel, parcel2, n2);
                    break;
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.gass.internal.IGassService");
                    onTransact = true;
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.gass.internal.IGassService");
                    GassRequestParcel gassRequestParcel;
                    if (parcel.readInt() != 0) {
                        gassRequestParcel = (GassRequestParcel)GassRequestParcel.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        gassRequestParcel = null;
                    }
                    final GassResponseParcel zza = this.zza(gassRequestParcel);
                    parcel2.writeNoException();
                    if (zza != null) {
                        parcel2.writeInt(1);
                        zza.writeToParcel(parcel2, 1);
                    }
                    else {
                        parcel2.writeInt(0);
                    }
                    onTransact = true;
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zze
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public GassResponseParcel zza(final GassRequestParcel gassRequestParcel) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.gass.internal.IGassService");
                        if (gassRequestParcel != null) {
                            obtain.writeInt(1);
                            gassRequestParcel.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        this.zzajf.transact(1, obtain, obtain2, 0);
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            return (GassResponseParcel)GassResponseParcel.CREATOR.createFromParcel(obtain2);
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    return null;
                }
            }
        }
    }
}
