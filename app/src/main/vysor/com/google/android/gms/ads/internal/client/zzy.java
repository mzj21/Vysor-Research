// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzy extends IInterface
{
    long getValue() throws RemoteException;
    
    public abstract static class zza extends Binder implements zzy
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
        }
        
        public static zzy zzt(final IBinder binder) {
            zzy zzy;
            if (binder == null) {
                zzy = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzy) {
                    zzy = (zzy)queryLocalInterface;
                }
                else {
                    zzy = new zzy.zza.zza(binder);
                }
            }
            return zzy;
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            boolean onTransact = true;
            switch (n) {
                default: {
                    onTransact = super.onTransact(n, parcel, parcel2, n2);
                    break;
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                    final long value = this.getValue();
                    parcel2.writeNoException();
                    parcel2.writeLong(value);
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
            public long getValue() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                    this.zzajf.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
