// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzgq extends IInterface
{
    zzgr zzbq(final String p0) throws RemoteException;
    
    boolean zzbr(final String p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzgq
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
        }
        
        public static zzgq zzam(final IBinder binder) {
            zzgq zzgq;
            if (binder == null) {
                zzgq = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzgq) {
                    zzgq = (zzgq)queryLocalInterface;
                }
                else {
                    zzgq = new zzgq.zza.zza(binder);
                }
            }
            return zzgq;
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            int onTransact = 1;
            switch (n) {
                default: {
                    onTransact = (super.onTransact(n, parcel, parcel2, n2) ? 1 : 0);
                    break;
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
                    final zzgr zzbq = this.zzbq(parcel.readString());
                    parcel2.writeNoException();
                    IBinder binder;
                    if (zzbq != null) {
                        binder = zzbq.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    parcel2.writeStrongBinder(binder);
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
                    final boolean zzbr = this.zzbr(parcel.readString());
                    parcel2.writeNoException();
                    int n3;
                    if (zzbr) {
                        n3 = onTransact;
                    }
                    else {
                        n3 = 0;
                    }
                    parcel2.writeInt(n3);
                    break;
                }
            }
            return onTransact != 0;
        }
        
        private static class zza implements zzgq
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public zzgr zzbq(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
                    obtain.writeString(s);
                    this.zzajf.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzgr.zza.zzan(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean zzbr(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
                    obtain.writeString(s);
                    this.zzajf.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    final int int1 = obtain2.readInt();
                    boolean b = false;
                    if (int1 != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
