// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzs extends IInterface
{
    void cancel() throws RemoteException;
    
    public abstract static class zza extends Binder implements zzs
    {
        public static zzs zzds(final IBinder binder) {
            zzs zzs;
            if (binder == null) {
                zzs = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.ICancelToken");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzs) {
                    zzs = (zzs)queryLocalInterface;
                }
                else {
                    zzs = new zzs.zza.zza(binder);
                }
            }
            return zzs;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            boolean onTransact = true;
            switch (n) {
                default: {
                    onTransact = super.onTransact(n, parcel, parcel2, n2);
                    break;
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.common.internal.ICancelToken");
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.ICancelToken");
                    this.cancel();
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zzs
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public void cancel() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.ICancelToken");
                    this.zzajf.transact(2, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
        }
    }
}
