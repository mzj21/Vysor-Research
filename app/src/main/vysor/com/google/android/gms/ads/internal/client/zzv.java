// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcelable$Creator;
import android.os.Parcel;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IBinder;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.dynamic.zzd;
import android.os.IInterface;

public interface zzv extends IInterface
{
    IBinder zza(final zzd p0, final AdSizeParcel p1, final String p2, final zzgq p3, final int p4) throws RemoteException;
    
    IBinder zza(final zzd p0, final AdSizeParcel p1, final String p2, final zzgq p3, final int p4, final int p5) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzv
    {
        public static zzv zzr(final IBinder binder) {
            zzv zzv;
            if (binder == null) {
                zzv = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzv) {
                    zzv = (zzv)queryLocalInterface;
                }
                else {
                    zzv = new zzv.zza.zza(binder);
                }
            }
            return zzv;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            boolean onTransact = false;
            switch (n) {
                default: {
                    onTransact = super.onTransact(n, parcel, parcel2, n2);
                    break;
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                    onTransact = true;
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                    final zzd zzfe = zzd.zza.zzfe(parcel.readStrongBinder());
                    final int int1 = parcel.readInt();
                    AdSizeParcel adSizeParcel = null;
                    if (int1 != 0) {
                        adSizeParcel = (AdSizeParcel)AdSizeParcel.CREATOR.createFromParcel(parcel);
                    }
                    final IBinder zza = this.zza(zzfe, adSizeParcel, parcel.readString(), zzgq.zza.zzam(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(zza);
                    onTransact = true;
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                    final zzd zzfe2 = zzd.zza.zzfe(parcel.readStrongBinder());
                    final int int2 = parcel.readInt();
                    AdSizeParcel adSizeParcel2 = null;
                    if (int2 != 0) {
                        adSizeParcel2 = (AdSizeParcel)AdSizeParcel.CREATOR.createFromParcel(parcel);
                    }
                    final IBinder zza2 = this.zza(zzfe2, adSizeParcel2, parcel.readString(), zzgq.zza.zzam(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(zza2);
                    onTransact = true;
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zzv
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public IBinder zza(final zzd zzd, final AdSizeParcel adSizeParcel, final String s, final zzgq zzgq, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (adSizeParcel != null) {
                        obtain.writeInt(1);
                        adSizeParcel.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
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
            
            @Override
            public IBinder zza(final zzd zzd, final AdSizeParcel adSizeParcel, final String s, final zzgq zzgq, final int n, final int n2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (adSizeParcel != null) {
                        obtain.writeInt(1);
                        adSizeParcel.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(s);
                    IBinder binder2 = null;
                    if (zzgq != null) {
                        binder2 = zzgq.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    this.zzajf.transact(2, obtain, obtain2, 0);
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
