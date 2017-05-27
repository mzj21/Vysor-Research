// 
// Decompiled by Procyon v0.5.30
// 

package android.content;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface IClipboard extends IInterface
{
    void addPrimaryClipChangedListener(final IOnPrimaryClipChangedListener p0, final String p1) throws RemoteException;
    
    ClipData getPrimaryClip(final String p0) throws RemoteException;
    
    ClipDescription getPrimaryClipDescription(final String p0) throws RemoteException;
    
    boolean hasClipboardText(final String p0) throws RemoteException;
    
    boolean hasPrimaryClip(final String p0) throws RemoteException;
    
    void removePrimaryClipChangedListener(final IOnPrimaryClipChangedListener p0) throws RemoteException;
    
    void setPrimaryClip(final ClipData p0, final String p1) throws RemoteException;
    
    public abstract static class Stub extends Binder implements IClipboard
    {
        private static final String DESCRIPTOR = "android.content.IClipboard";
        static final int TRANSACTION_addPrimaryClipChangedListener = 5;
        static final int TRANSACTION_getPrimaryClip = 2;
        static final int TRANSACTION_getPrimaryClipDescription = 3;
        static final int TRANSACTION_hasClipboardText = 7;
        static final int TRANSACTION_hasPrimaryClip = 4;
        static final int TRANSACTION_removePrimaryClipChangedListener = 6;
        static final int TRANSACTION_setPrimaryClip = 1;
        
        public Stub() {
            this.attachInterface((IInterface)this, "android.content.IClipboard");
        }
        
        public static IClipboard asInterface(final IBinder binder) {
            IClipboard clipboard;
            if (binder == null) {
                clipboard = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("android.content.IClipboard");
                if (queryLocalInterface != null && queryLocalInterface instanceof IClipboard) {
                    clipboard = (IClipboard)queryLocalInterface;
                }
                else {
                    clipboard = new Proxy(binder);
                }
            }
            return clipboard;
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
                    parcel2.writeString("android.content.IClipboard");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("android.content.IClipboard");
                    ClipData clipData;
                    if (parcel.readInt() != 0) {
                        clipData = (ClipData)ClipData.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        clipData = null;
                    }
                    this.setPrimaryClip(clipData, parcel.readString());
                    parcel2.writeNoException();
                    break;
                }
                case 2: {
                    parcel.enforceInterface("android.content.IClipboard");
                    final ClipData primaryClip = this.getPrimaryClip(parcel.readString());
                    parcel2.writeNoException();
                    if (primaryClip != null) {
                        parcel2.writeInt(onTransact);
                        primaryClip.writeToParcel(parcel2, onTransact);
                        break;
                    }
                    parcel2.writeInt(0);
                    break;
                }
                case 3: {
                    parcel.enforceInterface("android.content.IClipboard");
                    final ClipDescription primaryClipDescription = this.getPrimaryClipDescription(parcel.readString());
                    parcel2.writeNoException();
                    if (primaryClipDescription != null) {
                        parcel2.writeInt(onTransact);
                        primaryClipDescription.writeToParcel(parcel2, onTransact);
                        break;
                    }
                    parcel2.writeInt(0);
                    break;
                }
                case 4: {
                    parcel.enforceInterface("android.content.IClipboard");
                    final boolean hasPrimaryClip = this.hasPrimaryClip(parcel.readString());
                    parcel2.writeNoException();
                    int n3 = 0;
                    if (hasPrimaryClip) {
                        n3 = onTransact;
                    }
                    parcel2.writeInt(n3);
                    break;
                }
                case 5: {
                    parcel.enforceInterface("android.content.IClipboard");
                    this.addPrimaryClipChangedListener(IOnPrimaryClipChangedListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    break;
                }
                case 6: {
                    parcel.enforceInterface("android.content.IClipboard");
                    this.removePrimaryClipChangedListener(IOnPrimaryClipChangedListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 7: {
                    parcel.enforceInterface("android.content.IClipboard");
                    final boolean hasClipboardText = this.hasClipboardText(parcel.readString());
                    parcel2.writeNoException();
                    int n4 = 0;
                    if (hasClipboardText) {
                        n4 = onTransact;
                    }
                    parcel2.writeInt(n4);
                    break;
                }
            }
            return onTransact != 0;
        }
        
        private static class Proxy implements IClipboard
        {
            private IBinder mRemote;
            
            Proxy(final IBinder mRemote) {
                this.mRemote = mRemote;
            }
            
            @Override
            public void addPrimaryClipChangedListener(final IOnPrimaryClipChangedListener onPrimaryClipChangedListener, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.content.IClipboard");
                    IBinder binder;
                    if (onPrimaryClipChangedListener != null) {
                        binder = onPrimaryClipChangedListener.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.mRemote;
            }
            
            public String getInterfaceDescriptor() {
                return "android.content.IClipboard";
            }
            
            @Override
            public ClipData getPrimaryClip(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.content.IClipboard");
                    obtain.writeString(s);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    ClipData clipData;
                    if (obtain2.readInt() != 0) {
                        clipData = (ClipData)ClipData.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        clipData = null;
                    }
                    return clipData;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public ClipDescription getPrimaryClipDescription(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.content.IClipboard");
                    obtain.writeString(s);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    ClipDescription clipDescription;
                    if (obtain2.readInt() != 0) {
                        clipDescription = (ClipDescription)ClipDescription.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        clipDescription = null;
                    }
                    return clipDescription;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean hasClipboardText(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.content.IClipboard");
                    obtain.writeString(s);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    final int int1 = obtain2.readInt();
                    boolean b = false;
                    if (int1 != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean hasPrimaryClip(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.content.IClipboard");
                    obtain.writeString(s);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    final int int1 = obtain2.readInt();
                    boolean b = false;
                    if (int1 != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void removePrimaryClipChangedListener(final IOnPrimaryClipChangedListener onPrimaryClipChangedListener) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.content.IClipboard");
                    IBinder binder;
                    if (onPrimaryClipChangedListener != null) {
                        binder = onPrimaryClipChangedListener.asBinder();
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
            public void setPrimaryClip(final ClipData clipData, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.content.IClipboard");
                    if (clipData != null) {
                        obtain.writeInt(1);
                        clipData.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(s);
                    this.mRemote.transact(1, obtain, obtain2, 0);
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
