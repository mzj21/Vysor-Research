// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzw extends IInterface
{
    void onAppEvent(final String p0, final String p1) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzw
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.client.IAppEventListener");
        }
        
        public static zzw zzs(final IBinder binder) {
            zzw zzw;
            if (binder == null) {
                zzw = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAppEventListener");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzw) {
                    zzw = (zzw)queryLocalInterface;
                }
                else {
                    zzw = new zzw.zza.zza(binder);
                }
            }
            return zzw;
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
                    parcel2.writeString("com.google.android.gms.ads.internal.client.IAppEventListener");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAppEventListener");
                    this.onAppEvent(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zzw
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public void onAppEvent(final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAppEventListener");
                    obtain.writeString(s);
                    obtain.writeString(s2);
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
