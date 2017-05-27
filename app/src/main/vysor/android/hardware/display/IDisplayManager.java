// 
// Decompiled by Procyon v0.5.30
// 

package android.hardware.display;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.view.DisplayInfo;
import android.os.IInterface;

public interface IDisplayManager extends IInterface
{
    DisplayInfo getDisplayInfo(final int p0) throws RemoteException;
    
    public abstract static class Stub extends Binder implements IDisplayManager
    {
        private static final String DESCRIPTOR = "android.hardware.display.IDisplayManager";
        static final int TRANSACTION_getDisplayInfo = 1;
        
        public Stub() {
            this.attachInterface((IInterface)this, "android.hardware.display.IDisplayManager");
        }
        
        public static IDisplayManager asInterface(final IBinder binder) {
            IDisplayManager displayManager;
            if (binder == null) {
                displayManager = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("android.hardware.display.IDisplayManager");
                if (queryLocalInterface != null && queryLocalInterface instanceof IDisplayManager) {
                    displayManager = (IDisplayManager)queryLocalInterface;
                }
                else {
                    displayManager = new Proxy(binder);
                }
            }
            return displayManager;
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
                    parcel2.writeString("android.hardware.display.IDisplayManager");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("android.hardware.display.IDisplayManager");
                    final DisplayInfo displayInfo = this.getDisplayInfo(parcel.readInt());
                    parcel2.writeNoException();
                    if (displayInfo != null) {
                        parcel2.writeInt(onTransact);
                        displayInfo.writeToParcel(parcel2, onTransact);
                        break;
                    }
                    parcel2.writeInt(0);
                    break;
                }
            }
            return onTransact != 0;
        }
        
        private static class Proxy implements IDisplayManager
        {
            private IBinder mRemote;
            
            Proxy(final IBinder mRemote) {
                this.mRemote = mRemote;
            }
            
            public IBinder asBinder() {
                return this.mRemote;
            }
            
            @Override
            public DisplayInfo getDisplayInfo(final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.hardware.display.IDisplayManager");
                    obtain.writeInt(n);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    DisplayInfo displayInfo;
                    if (obtain2.readInt() != 0) {
                        displayInfo = (DisplayInfo)DisplayInfo.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        displayInfo = null;
                    }
                    return displayInfo;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            public String getInterfaceDescriptor() {
                return "android.hardware.display.IDisplayManager";
            }
        }
    }
}
