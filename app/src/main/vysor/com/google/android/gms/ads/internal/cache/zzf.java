// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.cache;

import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcelable$Creator;
import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzf extends IInterface
{
    CacheEntryParcel zza(final CacheOffering p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzf
    {
        public static zzf zzi(final IBinder binder) {
            zzf zzf;
            if (binder == null) {
                zzf = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.cache.ICacheService");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzf) {
                    zzf = (zzf)queryLocalInterface;
                }
                else {
                    zzf = new zzf.zza.zza(binder);
                }
            }
            return zzf;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            boolean onTransact = false;
            switch (n) {
                default: {
                    onTransact = super.onTransact(n, parcel, parcel2, n2);
                    break;
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.ads.internal.cache.ICacheService");
                    onTransact = true;
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.cache.ICacheService");
                    CacheOffering cacheOffering;
                    if (parcel.readInt() != 0) {
                        cacheOffering = (CacheOffering)CacheOffering.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        cacheOffering = null;
                    }
                    final CacheEntryParcel zza = this.zza(cacheOffering);
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
        
        private static class zza implements zzf
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public CacheEntryParcel zza(final CacheOffering cacheOffering) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.ads.internal.cache.ICacheService");
                        if (cacheOffering != null) {
                            obtain.writeInt(1);
                            cacheOffering.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        this.zzajf.transact(1, obtain, obtain2, 0);
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            return (CacheEntryParcel)CacheEntryParcel.CREATOR.createFromParcel(obtain2);
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
