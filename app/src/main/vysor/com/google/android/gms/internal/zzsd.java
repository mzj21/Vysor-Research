// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzsd extends IInterface
{
    void zza(final zzsc p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzsd
    {
        public static zzsd zzec(final IBinder binder) {
            zzsd zzsd;
            if (binder == null) {
                zzsd = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.service.ICommonService");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzsd) {
                    zzsd = (zzsd)queryLocalInterface;
                }
                else {
                    zzsd = new zzsd.zza.zza(binder);
                }
            }
            return zzsd;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            boolean onTransact = true;
            switch (n) {
                default: {
                    onTransact = super.onTransact(n, parcel, parcel2, n2);
                    break;
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.common.internal.service.ICommonService");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.service.ICommonService");
                    this.zza(zzsc.zza.zzeb(parcel.readStrongBinder()));
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zzsd
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public void zza(final zzsc zzsc) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.service.ICommonService");
                    IBinder binder = null;
                    if (zzsc != null) {
                        binder = zzsc.asBinder();
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(1, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
        }
    }
}
