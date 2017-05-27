// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.iid;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.Message;
import android.os.IInterface;

public interface zzb extends IInterface
{
    void send(final Message p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzb
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.iid.IMessengerCompat");
        }
        
        public static zzb zzgw(final IBinder binder) {
            zzb zzb;
            if (binder == null) {
                zzb = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.iid.IMessengerCompat");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzb) {
                    zzb = (zzb)queryLocalInterface;
                }
                else {
                    zzb = new zzb.zza.zza(binder);
                }
            }
            return zzb;
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            boolean onTransact = false;
            switch (n) {
                default: {
                    onTransact = super.onTransact(n, parcel, parcel2, n2);
                    break;
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.iid.IMessengerCompat");
                    onTransact = true;
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.iid.IMessengerCompat");
                    Message message;
                    if (parcel.readInt() != 0) {
                        message = (Message)Message.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        message = null;
                    }
                    this.send(message);
                    onTransact = true;
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zzb
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public void send(final Message message) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.iid.IMessengerCompat");
                    if (message != null) {
                        obtain.writeInt(1);
                        message.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.zzajf.transact(1, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
        }
    }
}
