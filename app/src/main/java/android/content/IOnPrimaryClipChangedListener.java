// 
// Decompiled by Procyon v0.5.30
// 

package android.content;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface IOnPrimaryClipChangedListener extends IInterface {
    void dispatchPrimaryClipChanged() throws RemoteException;

    public abstract static class Stub extends Binder implements IOnPrimaryClipChangedListener {
        private static final String DESCRIPTOR = "android.content.IOnPrimaryClipChangedListener";
        static final int TRANSACTION_dispatchPrimaryClipChanged = 1;

        public Stub() {
            attachInterface(this, "android.content.IOnPrimaryClipChangedListener");
        }

        public static IOnPrimaryClipChangedListener asInterface(final IBinder binder) {
            IOnPrimaryClipChangedListener onPrimaryClipChangedListener;
            if (binder == null) {
                onPrimaryClipChangedListener = null;
            } else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("android.content.IOnPrimaryClipChangedListener");
                if (queryLocalInterface != null && queryLocalInterface instanceof IOnPrimaryClipChangedListener) {
                    onPrimaryClipChangedListener = (IOnPrimaryClipChangedListener) queryLocalInterface;
                } else {
                    onPrimaryClipChangedListener = new Proxy(binder);
                }
            }
            return onPrimaryClipChangedListener;
        }

        public IBinder asBinder() {
            return (IBinder) this;
        }

        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            boolean onTransact = true;
            switch (n) {
                default: {
                    onTransact = super.onTransact(n, parcel, parcel2, n2);
                    break;
                }
                case 1598968902: {
                    parcel2.writeString("android.content.IOnPrimaryClipChangedListener");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("android.content.IOnPrimaryClipChangedListener");
                    this.dispatchPrimaryClipChanged();
                    break;
                }
            }
            return onTransact;
        }

        private static class Proxy implements IOnPrimaryClipChangedListener {
            private IBinder mRemote;

            Proxy(final IBinder mRemote) {
                this.mRemote = mRemote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override
            public void dispatchPrimaryClipChanged() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.content.IOnPrimaryClipChangedListener");
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return "android.content.IOnPrimaryClipChangedListener";
            }
        }
    }
}
