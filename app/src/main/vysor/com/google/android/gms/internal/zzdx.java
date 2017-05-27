// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.dynamic.zzd;
import android.net.Uri;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzdx extends IInterface
{
    double getScale() throws RemoteException;
    
    Uri getUri() throws RemoteException;
    
    zzd zzln() throws RemoteException;
    
    public abstract static class zza extends Binder implements zzdx
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.formats.client.INativeAdImage");
        }
        
        public static zzdx zzab(final IBinder binder) {
            zzdx zzdx;
            if (binder == null) {
                zzdx = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzdx) {
                    zzdx = (zzdx)queryLocalInterface;
                }
                else {
                    zzdx = new zzdx.zza.zza(binder);
                }
            }
            return zzdx;
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
                    parcel2.writeString("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                    onTransact = true;
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                    final zzd zzln = this.zzln();
                    parcel2.writeNoException();
                    IBinder binder;
                    if (zzln != null) {
                        binder = zzln.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    parcel2.writeStrongBinder(binder);
                    onTransact = true;
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                    final Uri uri = this.getUri();
                    parcel2.writeNoException();
                    if (uri != null) {
                        parcel2.writeInt(1);
                        uri.writeToParcel(parcel2, 1);
                    }
                    else {
                        parcel2.writeInt(0);
                    }
                    onTransact = true;
                    break;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                    final double scale = this.getScale();
                    parcel2.writeNoException();
                    parcel2.writeDouble(scale);
                    onTransact = true;
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zzdx
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public double getScale() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                    this.zzajf.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readDouble();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Uri getUri() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                    this.zzajf.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    Uri uri;
                    if (obtain2.readInt() != 0) {
                        uri = (Uri)Uri.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        uri = null;
                    }
                    return uri;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public zzd zzln() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                    this.zzajf.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzd.zza.zzfe(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
