// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import android.os.IInterface;

public interface zzcg extends IInterface
{
    zzd zza(final zzd p0, final zzd p1) throws RemoteException;
    
    String zza(final zzd p0, final String p1) throws RemoteException;
    
    boolean zza(final zzd p0) throws RemoteException;
    
    zzd zzb(final zzd p0, final zzd p1) throws RemoteException;
    
    void zzb(final String p0, final String p1) throws RemoteException;
    
    boolean zzb(final zzd p0) throws RemoteException;
    
    boolean zzb(final String p0, final boolean p1) throws RemoteException;
    
    String zzc(final zzd p0) throws RemoteException;
    
    void zzd(final zzd p0) throws RemoteException;
    
    String zzdk() throws RemoteException;
    
    void zzm(final String p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzcg
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.adshield.internal.IAdShieldClient");
        }
        
        public static zzcg zzd(final IBinder binder) {
            zzcg zzcg;
            if (binder == null) {
                zzcg = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzcg) {
                    zzcg = (zzcg)queryLocalInterface;
                }
                else {
                    zzcg = new zzcg.zza.zza(binder);
                }
            }
            return zzcg;
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
                    parcel2.writeString("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    final String zzdk = this.zzdk();
                    parcel2.writeNoException();
                    parcel2.writeString(zzdk);
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    this.zzb(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    break;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    final boolean zza = this.zza(zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    int n3 = 0;
                    if (zza) {
                        n3 = onTransact;
                    }
                    parcel2.writeInt(n3);
                    break;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    final boolean zzb = this.zzb(zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    int n4 = 0;
                    if (zzb) {
                        n4 = onTransact;
                    }
                    parcel2.writeInt(n4);
                    break;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    this.zzm(parcel.readString());
                    parcel2.writeNoException();
                    break;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    final zzd zza2 = this.zza(zzd.zza.zzfe(parcel.readStrongBinder()), zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    IBinder binder;
                    if (zza2 != null) {
                        binder = zza2.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    parcel2.writeStrongBinder(binder);
                    break;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    final String zzc = this.zzc(zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeString(zzc);
                    break;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    final String zza3 = this.zza(zzd.zza.zzfe(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(zza3);
                    break;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    this.zzd(zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    final zzd zzb2 = this.zzb(zzd.zza.zzfe(parcel.readStrongBinder()), zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    IBinder binder2 = null;
                    if (zzb2 != null) {
                        binder2 = zzb2.asBinder();
                    }
                    parcel2.writeStrongBinder(binder2);
                    break;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    final boolean zzb3 = this.zzb(parcel.readString(), parcel.readInt() != 0 && onTransact);
                    parcel2.writeNoException();
                    int n5 = 0;
                    if (zzb3) {
                        n5 = onTransact;
                    }
                    parcel2.writeInt(n5);
                    break;
                }
            }
            return onTransact != 0;
        }
        
        private static class zza implements zzcg
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public zzd zza(final zzd zzd, final zzd zzd2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    IBinder binder2 = null;
                    if (zzd2 != null) {
                        binder2 = zzd2.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    this.zzajf.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.dynamic.zzd.zza.zzfe(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String zza(final zzd zzd, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.zzajf.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean zza(final zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(3, obtain, obtain2, 0);
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
            public zzd zzb(final zzd zzd, final zzd zzd2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    IBinder binder2 = null;
                    if (zzd2 != null) {
                        binder2 = zzd2.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    this.zzajf.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.dynamic.zzd.zza.zzfe(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zzb(final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.zzajf.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean zzb(final zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
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
            public boolean zzb(final String s, final boolean b) throws RemoteException {
                int n = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    obtain.writeString(s);
                    int n2;
                    if (b) {
                        n2 = n;
                    }
                    else {
                        n2 = 0;
                    }
                    obtain.writeInt(n2);
                    this.zzajf.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        n = 0;
                    }
                    return n != 0;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String zzc(final zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zzd(final zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String zzdk() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    this.zzajf.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zzm(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
                    obtain.writeString(s);
                    this.zzajf.transact(5, obtain, obtain2, 0);
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
