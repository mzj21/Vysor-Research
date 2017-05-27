// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzab extends IInterface
{
    float getAspectRatio() throws RemoteException;
    
    int getPlaybackState() throws RemoteException;
    
    boolean isMuted() throws RemoteException;
    
    void pause() throws RemoteException;
    
    void play() throws RemoteException;
    
    void zza(final zzac p0) throws RemoteException;
    
    float zzju() throws RemoteException;
    
    float zzjv() throws RemoteException;
    
    void zzn(final boolean p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzab
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.client.IVideoController");
        }
        
        public static zzab zzw(final IBinder binder) {
            zzab zzab;
            if (binder == null) {
                zzab = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoController");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzab) {
                    zzab = (zzab)queryLocalInterface;
                }
                else {
                    zzab = new zzab.zza.zza(binder);
                }
            }
            return zzab;
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
                    parcel2.writeString("com.google.android.gms.ads.internal.client.IVideoController");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IVideoController");
                    this.play();
                    parcel2.writeNoException();
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IVideoController");
                    this.pause();
                    parcel2.writeNoException();
                    break;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IVideoController");
                    final int int1 = parcel.readInt();
                    int n3 = 0;
                    if (int1 != 0) {
                        n3 = onTransact;
                    }
                    this.zzn(n3 != 0);
                    parcel2.writeNoException();
                    break;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IVideoController");
                    final boolean muted = this.isMuted();
                    parcel2.writeNoException();
                    int n4 = 0;
                    if (muted) {
                        n4 = onTransact;
                    }
                    parcel2.writeInt(n4);
                    break;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IVideoController");
                    final int playbackState = this.getPlaybackState();
                    parcel2.writeNoException();
                    parcel2.writeInt(playbackState);
                    break;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IVideoController");
                    final float zzju = this.zzju();
                    parcel2.writeNoException();
                    parcel2.writeFloat(zzju);
                    break;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IVideoController");
                    final float zzjv = this.zzjv();
                    parcel2.writeNoException();
                    parcel2.writeFloat(zzjv);
                    break;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IVideoController");
                    this.zza(zzac.zza.zzx(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IVideoController");
                    final float aspectRatio = this.getAspectRatio();
                    parcel2.writeNoException();
                    parcel2.writeFloat(aspectRatio);
                    break;
                }
            }
            return onTransact != 0;
        }
        
        private static class zza implements zzab
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public float getAspectRatio() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IVideoController");
                    this.zzajf.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readFloat();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int getPlaybackState() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IVideoController");
                    this.zzajf.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isMuted() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IVideoController");
                    this.zzajf.transact(4, obtain, obtain2, 0);
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
            public void pause() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IVideoController");
                    this.zzajf.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void play() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IVideoController");
                    this.zzajf.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zza(final zzac zzac) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IVideoController");
                    IBinder binder;
                    if (zzac != null) {
                        binder = zzac.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public float zzju() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IVideoController");
                    this.zzajf.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readFloat();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public float zzjv() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IVideoController");
                    this.zzajf.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readFloat();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zzn(final boolean b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IVideoController");
                    int n = 0;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.zzajf.transact(3, obtain, obtain2, 0);
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
