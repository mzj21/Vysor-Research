// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.os;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.Bundle;
import android.os.IInterface;

public interface IResultReceiver extends IInterface
{
    void send(final int p0, final Bundle p1) throws RemoteException;
    
    public abstract static class Stub extends Binder implements IResultReceiver
    {
        private static final String DESCRIPTOR = "android.support.v4.os.IResultReceiver";
        static final int TRANSACTION_send = 1;
        
        public Stub() {
            this.attachInterface((IInterface)this, "android.support.v4.os.IResultReceiver");
        }
        
        public static IResultReceiver asInterface(final IBinder binder) {
            IResultReceiver resultReceiver;
            if (binder == null) {
                resultReceiver = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("android.support.v4.os.IResultReceiver");
                if (queryLocalInterface != null && queryLocalInterface instanceof IResultReceiver) {
                    resultReceiver = (IResultReceiver)queryLocalInterface;
                }
                else {
                    resultReceiver = new Proxy(binder);
                }
            }
            return resultReceiver;
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
                    parcel2.writeString("android.support.v4.os.IResultReceiver");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("android.support.v4.os.IResultReceiver");
                    final int int1 = parcel.readInt();
                    Bundle bundle;
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        bundle = null;
                    }
                    this.send(int1, bundle);
                    break;
                }
            }
            return onTransact;
        }
        
        private static class Proxy implements IResultReceiver
        {
            private IBinder mRemote;
            
            Proxy(final IBinder mRemote) {
                this.mRemote = mRemote;
            }
            
            public IBinder asBinder() {
                return this.mRemote;
            }
            
            public String getInterfaceDescriptor() {
                return "android.support.v4.os.IResultReceiver";
            }
            
            @Override
            public void send(final int n, final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.os.IResultReceiver");
                    obtain.writeInt(n);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(1, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
        }
    }
}
