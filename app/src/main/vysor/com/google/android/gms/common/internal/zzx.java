// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzx extends IInterface
{
    void zza(final ResolveAccountResponse p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzx
    {
        public static zzx zzdx(final IBinder binder) {
            zzx zzx;
            if (binder == null) {
                zzx = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.IResolveAccountCallbacks");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzx) {
                    zzx = (zzx)queryLocalInterface;
                }
                else {
                    zzx = new zzx.zza.zza(binder);
                }
            }
            return zzx;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            boolean onTransact = false;
            switch (n) {
                default: {
                    onTransact = super.onTransact(n, parcel, parcel2, n2);
                    break;
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.common.internal.IResolveAccountCallbacks");
                    onTransact = true;
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IResolveAccountCallbacks");
                    ResolveAccountResponse resolveAccountResponse;
                    if (parcel.readInt() != 0) {
                        resolveAccountResponse = (ResolveAccountResponse)ResolveAccountResponse.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        resolveAccountResponse = null;
                    }
                    this.zza(resolveAccountResponse);
                    parcel2.writeNoException();
                    onTransact = true;
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zzx
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public void zza(final ResolveAccountResponse resolveAccountResponse) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IResolveAccountCallbacks");
                    if (resolveAccountResponse != null) {
                        obtain.writeInt(1);
                        resolveAccountResponse.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.zzajf.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
