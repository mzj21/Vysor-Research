// 
// Decompiled by Procyon v0.5.30
// 

package android.view;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.graphics.Point;
import android.os.IInterface;

public interface IWindowManager extends IInterface
{
    void getBaseDisplaySize(final int p0, final Point p1) throws RemoteException;
    
    void getInitialDisplaySize(final int p0, final Point p1) throws RemoteException;
    
    void getRealDisplaySize(final Point p0) throws RemoteException;
    
    int getRotation() throws RemoteException;
    
    void removeRotationWatcher(final IRotationWatcher p0) throws RemoteException;
    
    int watchRotation(final IRotationWatcher p0) throws RemoteException;
    
    public abstract static class Stub extends Binder implements IWindowManager
    {
        private static final String DESCRIPTOR = "android.view.IWindowManager";
        static final int TRANSACTION_getBaseDisplaySize = 3;
        static final int TRANSACTION_getInitialDisplaySize = 1;
        static final int TRANSACTION_getRealDisplaySize = 2;
        static final int TRANSACTION_getRotation = 4;
        static final int TRANSACTION_removeRotationWatcher = 6;
        static final int TRANSACTION_watchRotation = 5;
        
        public Stub() {
            this.attachInterface((IInterface)this, "android.view.IWindowManager");
        }
        
        public static IWindowManager asInterface(final IBinder binder) {
            IWindowManager windowManager;
            if (binder == null) {
                windowManager = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("android.view.IWindowManager");
                if (queryLocalInterface != null && queryLocalInterface instanceof IWindowManager) {
                    windowManager = (IWindowManager)queryLocalInterface;
                }
                else {
                    windowManager = new Proxy(binder);
                }
            }
            return windowManager;
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
                    parcel2.writeString("android.view.IWindowManager");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("android.view.IWindowManager");
                    final int int1 = parcel.readInt();
                    final Point point = new Point();
                    this.getInitialDisplaySize(int1, point);
                    parcel2.writeNoException();
                    if (point != null) {
                        parcel2.writeInt(onTransact);
                        point.writeToParcel(parcel2, onTransact);
                        break;
                    }
                    parcel2.writeInt(0);
                    break;
                }
                case 2: {
                    parcel.enforceInterface("android.view.IWindowManager");
                    final Point point2 = new Point();
                    this.getRealDisplaySize(point2);
                    parcel2.writeNoException();
                    if (point2 != null) {
                        parcel2.writeInt(onTransact);
                        point2.writeToParcel(parcel2, onTransact);
                        break;
                    }
                    parcel2.writeInt(0);
                    break;
                }
                case 3: {
                    parcel.enforceInterface("android.view.IWindowManager");
                    final int int2 = parcel.readInt();
                    final Point point3 = new Point();
                    this.getBaseDisplaySize(int2, point3);
                    parcel2.writeNoException();
                    if (point3 != null) {
                        parcel2.writeInt(onTransact);
                        point3.writeToParcel(parcel2, onTransact);
                        break;
                    }
                    parcel2.writeInt(0);
                    break;
                }
                case 4: {
                    parcel.enforceInterface("android.view.IWindowManager");
                    final int rotation = this.getRotation();
                    parcel2.writeNoException();
                    parcel2.writeInt(rotation);
                    break;
                }
                case 5: {
                    parcel.enforceInterface("android.view.IWindowManager");
                    final int watchRotation = this.watchRotation(IRotationWatcher.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(watchRotation);
                    break;
                }
                case 6: {
                    parcel.enforceInterface("android.view.IWindowManager");
                    this.removeRotationWatcher(IRotationWatcher.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
            }
            return onTransact != 0;
        }
        
        private static class Proxy implements IWindowManager
        {
            private IBinder mRemote;
            
            Proxy(final IBinder mRemote) {
                this.mRemote = mRemote;
            }
            
            public IBinder asBinder() {
                return this.mRemote;
            }
            
            @Override
            public void getBaseDisplaySize(final int n, final Point point) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.view.IWindowManager");
                    obtain.writeInt(n);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        point.readFromParcel(obtain2);
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void getInitialDisplaySize(final int n, final Point point) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.view.IWindowManager");
                    obtain.writeInt(n);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        point.readFromParcel(obtain2);
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            public String getInterfaceDescriptor() {
                return "android.view.IWindowManager";
            }
            
            @Override
            public void getRealDisplaySize(final Point point) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.view.IWindowManager");
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        point.readFromParcel(obtain2);
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int getRotation() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.view.IWindowManager");
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void removeRotationWatcher(final IRotationWatcher rotationWatcher) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.view.IWindowManager");
                    IBinder binder;
                    if (rotationWatcher != null) {
                        binder = rotationWatcher.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int watchRotation(final IRotationWatcher rotationWatcher) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.view.IWindowManager");
                    IBinder binder;
                    if (rotationWatcher != null) {
                        binder = rotationWatcher.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
