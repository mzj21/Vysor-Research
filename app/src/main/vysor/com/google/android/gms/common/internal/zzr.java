// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.accounts.Account;
import android.os.IInterface;

public interface zzr extends IInterface
{
    Account getAccount() throws RemoteException;
    
    public abstract static class zza extends Binder implements zzr
    {
        public static zzr zzdr(final IBinder binder) {
            zzr zzr;
            if (binder == null) {
                zzr = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzr) {
                    zzr = (zzr)queryLocalInterface;
                }
                else {
                    zzr = new zzr.zza.zza(binder);
                }
            }
            return zzr;
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
                    parcel2.writeString("com.google.android.gms.common.internal.IAccountAccessor");
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.common.internal.IAccountAccessor");
                    final Account account = this.getAccount();
                    parcel2.writeNoException();
                    if (account != null) {
                        parcel2.writeInt(onTransact);
                        account.writeToParcel(parcel2, onTransact);
                        break;
                    }
                    parcel2.writeInt(0);
                    break;
                }
            }
            return onTransact != 0;
        }
        
        private static class zza implements zzr
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public Account getAccount() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IAccountAccessor");
                    this.zzajf.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    Account account;
                    if (obtain2.readInt() != 0) {
                        account = (Account)Account.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        account = null;
                    }
                    return account;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
