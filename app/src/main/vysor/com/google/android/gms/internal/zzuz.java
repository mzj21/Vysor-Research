// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.dynamic.zzd;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzuz extends IInterface
{
    boolean getBooleanFlagValue(final String p0, final boolean p1, final int p2) throws RemoteException;
    
    int getIntFlagValue(final String p0, final int p1, final int p2) throws RemoteException;
    
    long getLongFlagValue(final String p0, final long p1, final int p2) throws RemoteException;
    
    String getStringFlagValue(final String p0, final String p1, final int p2) throws RemoteException;
    
    void init(final zzd p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzuz
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.flags.IFlagProvider");
        }
        
        public static zzuz asInterface(final IBinder binder) {
            zzuz zzuz;
            if (binder == null) {
                zzuz = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.flags.IFlagProvider");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzuz) {
                    zzuz = (zzuz)queryLocalInterface;
                }
                else {
                    zzuz = new zzuz.zza.zza(binder);
                }
            }
            return zzuz;
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
                    parcel2.writeString("com.google.android.gms.flags.IFlagProvider");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.flags.IFlagProvider");
                    this.init(zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.flags.IFlagProvider");
                    final boolean booleanFlagValue = this.getBooleanFlagValue(parcel.readString(), parcel.readInt() != 0 && onTransact, parcel.readInt());
                    parcel2.writeNoException();
                    int n3 = 0;
                    if (booleanFlagValue) {
                        n3 = onTransact;
                    }
                    parcel2.writeInt(n3);
                    break;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.flags.IFlagProvider");
                    final int intFlagValue = this.getIntFlagValue(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(intFlagValue);
                    break;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.flags.IFlagProvider");
                    final long longFlagValue = this.getLongFlagValue(parcel.readString(), parcel.readLong(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(longFlagValue);
                    break;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.flags.IFlagProvider");
                    final String stringFlagValue = this.getStringFlagValue(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(stringFlagValue);
                    break;
                }
            }
            return onTransact != 0;
        }
        
        private static class zza implements zzuz
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public boolean getBooleanFlagValue(final String s, final boolean b, final int n) throws RemoteException {
                int n2 = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.flags.IFlagProvider");
                    obtain.writeString(s);
                    int n3;
                    if (b) {
                        n3 = n2;
                    }
                    else {
                        n3 = 0;
                    }
                    obtain.writeInt(n3);
                    obtain.writeInt(n);
                    this.zzajf.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        n2 = 0;
                    }
                    return n2 != 0;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int getIntFlagValue(final String s, final int n, final int n2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.flags.IFlagProvider");
                    obtain.writeString(s);
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    this.zzajf.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public long getLongFlagValue(final String s, final long n, final int n2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.flags.IFlagProvider");
                    obtain.writeString(s);
                    obtain.writeLong(n);
                    obtain.writeInt(n2);
                    this.zzajf.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String getStringFlagValue(final String s, final String s2, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.flags.IFlagProvider");
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    obtain.writeInt(n);
                    this.zzajf.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void init(final zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.flags.IFlagProvider");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
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
