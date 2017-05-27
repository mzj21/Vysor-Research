// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.reward.client;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface zza extends IInterface
{
    int getAmount() throws RemoteException;
    
    String getType() throws RemoteException;
    
    public abstract static class zza extends Binder implements zza
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.reward.client.IRewardItem");
        }
        
        public static zza zzbg(final IBinder binder) {
            zza zza;
            if (binder == null) {
                zza = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardItem");
                if (queryLocalInterface != null && queryLocalInterface instanceof zza) {
                    zza = (zza)queryLocalInterface;
                }
                else {
                    zza = new zza(binder);
                }
            }
            return zza;
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
                    parcel2.writeString("com.google.android.gms.ads.internal.reward.client.IRewardItem");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardItem");
                    final String type = this.getType();
                    parcel2.writeNoException();
                    parcel2.writeString(type);
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardItem");
                    final int amount = this.getAmount();
                    parcel2.writeNoException();
                    parcel2.writeInt(amount);
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements com.google.android.gms.ads.internal.reward.client.zza
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public int getAmount() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardItem");
                    this.zzajf.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String getType() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardItem");
                    this.zzajf.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
