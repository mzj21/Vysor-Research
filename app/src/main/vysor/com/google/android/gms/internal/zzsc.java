// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzsc extends IInterface
{
    void zzgw(final int p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzsc
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.common.internal.service.ICommonCallbacks");
        }
        
        public static zzsc zzeb(final IBinder binder) {
            zzsc zzsc;
            if (binder == null) {
                zzsc = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.service.ICommonCallbacks");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzsc) {
                    zzsc = (zzsc)queryLocalInterface;
                }
                else {
                    zzsc = new zzsc.zza.zza(binder);
                }
            }
            return zzsc;
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
                    parcel2.writeString("com.google.android.gms.common.internal.service.ICommonCallbacks");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.service.ICommonCallbacks");
                    this.zzgw(parcel.readInt());
                    parcel2.writeNoException();
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zzsc
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public void zzgw(final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.service.ICommonCallbacks");
                    obtain.writeInt(n);
                    this.zzajf.transact(1, obtain, obtain2, 0);
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
