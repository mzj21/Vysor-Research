// 
// Decompiled by Procyon v0.5.30
// 

package android.os;

public interface IPowerManager extends IInterface {
    void acquireWakeLock(final IBinder p0, final int p1, final String p2) throws RemoteException;

    boolean isInteractive() throws RemoteException;

    boolean isScreenOn() throws RemoteException;

    void releaseWakeLock(final IBinder p0, final int p1) throws RemoteException;

    public abstract static class Stub extends Binder implements IPowerManager {
        private static final String DESCRIPTOR = "android.os.IPowerManager";
        static final int TRANSACTION_acquireWakeLock = 1;
        static final int TRANSACTION_isInteractive = 4;
        static final int TRANSACTION_isScreenOn = 3;
        static final int TRANSACTION_releaseWakeLock = 2;

        public Stub() {
            attachInterface(this, "android.os.IPowerManager");
        }

        public static IPowerManager asInterface(final IBinder binder) {
            IPowerManager powerManager;
            if (binder == null) {
                powerManager = null;
            } else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("android.os.IPowerManager");
                if (queryLocalInterface != null && queryLocalInterface instanceof IPowerManager) {
                    powerManager = (IPowerManager) queryLocalInterface;
                } else {
                    powerManager = new Proxy(binder);
                }
            }
            return powerManager;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            int onTransact = 1;
            switch (n) {
                default: {
                    onTransact = (super.onTransact(n, parcel, parcel2, n2) ? 1 : 0);
                    break;
                }
                case 1598968902: {
                    parcel2.writeString("android.os.IPowerManager");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("android.os.IPowerManager");
                    this.acquireWakeLock(parcel.readStrongBinder(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    break;
                }
                case 2: {
                    parcel.enforceInterface("android.os.IPowerManager");
                    this.releaseWakeLock(parcel.readStrongBinder(), parcel.readInt());
                    parcel2.writeNoException();
                    break;
                }
                case 3: {
                    parcel.enforceInterface("android.os.IPowerManager");
                    final boolean screenOn = isScreenOn();
                    parcel2.writeNoException();
                    int n3 = 0;
                    if (screenOn) {
                        n3 = onTransact;
                    }
                    parcel2.writeInt(n3);
                    break;
                }
                case 4: {
                    parcel.enforceInterface("android.os.IPowerManager");
                    final boolean interactive = this.isInteractive();
                    parcel2.writeNoException();
                    int n4 = 0;
                    if (interactive) {
                        n4 = onTransact;
                    }
                    parcel2.writeInt(n4);
                    break;
                }
            }
            return onTransact != 0;
        }

        private static class Proxy implements IPowerManager {
            private IBinder mRemote;

            Proxy(final IBinder mRemote) {
                this.mRemote = mRemote;
            }

            @Override
            public void acquireWakeLock(final IBinder binder, final int n, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.os.IPowerManager");
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    obtain.writeString(s);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "android.os.IPowerManager";
            }

            @Override
            public boolean isInteractive() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.os.IPowerManager");
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    final int int1 = obtain2.readInt();
                    boolean b = false;
                    if (int1 != 0) {
                        b = true;
                    }
                    return b;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override
            public boolean isScreenOn() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.os.IPowerManager");
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    final int int1 = obtain2.readInt();
                    boolean b = false;
                    if (int1 != 0) {
                        b = true;
                    }
                    return b;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override
            public void releaseWakeLock(final IBinder binder, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.os.IPowerManager");
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
