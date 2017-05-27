// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.AuthAccountRequest;
import android.os.RemoteException;
import android.accounts.Account;
import android.os.IInterface;

public interface zze extends IInterface
{
    void zza(final int p0, final Account p1, final zzd p2) throws RemoteException;
    
    void zza(final AuthAccountRequest p0, final zzd p1) throws RemoteException;
    
    void zza(final ResolveAccountRequest p0, final zzx p1) throws RemoteException;
    
    void zza(final zzr p0, final int p1, final boolean p2) throws RemoteException;
    
    void zza(final CheckServerAuthResult p0) throws RemoteException;
    
    void zza(final RecordConsentRequest p0, final zzd p1) throws RemoteException;
    
    void zza(final SignInRequest p0, final zzd p1) throws RemoteException;
    
    void zzaaf(final int p0) throws RemoteException;
    
    void zzb(final zzd p0) throws RemoteException;
    
    void zzcj(final boolean p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zze
    {
        public static zze zzlb(final IBinder binder) {
            zze zze;
            if (binder == null) {
                zze = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
                if (queryLocalInterface != null && queryLocalInterface instanceof zze) {
                    zze = (zze)queryLocalInterface;
                }
                else {
                    zze = new zze.zza.zza(binder);
                }
            }
            return zze;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            boolean onTransact = true;
            switch (n) {
                default: {
                    onTransact = super.onTransact(n, parcel, parcel2, n2);
                    break;
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.signin.internal.ISignInService");
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                    final int int1 = parcel.readInt();
                    AuthAccountRequest authAccountRequest = null;
                    if (int1 != 0) {
                        authAccountRequest = (AuthAccountRequest)AuthAccountRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.zza(authAccountRequest, zzd.zza.zzla(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                    final int int2 = parcel.readInt();
                    CheckServerAuthResult checkServerAuthResult = null;
                    if (int2 != 0) {
                        checkServerAuthResult = (CheckServerAuthResult)CheckServerAuthResult.CREATOR.createFromParcel(parcel);
                    }
                    this.zza(checkServerAuthResult);
                    parcel2.writeNoException();
                    break;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                    this.zzcj(parcel.readInt() != 0 && onTransact);
                    parcel2.writeNoException();
                    break;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                    final int int3 = parcel.readInt();
                    ResolveAccountRequest resolveAccountRequest = null;
                    if (int3 != 0) {
                        resolveAccountRequest = (ResolveAccountRequest)ResolveAccountRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.zza(resolveAccountRequest, zzx.zza.zzdx(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                    this.zzaaf(parcel.readInt());
                    parcel2.writeNoException();
                    break;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                    final int int4 = parcel.readInt();
                    final int int5 = parcel.readInt();
                    Account account = null;
                    if (int5 != 0) {
                        account = (Account)Account.CREATOR.createFromParcel(parcel);
                    }
                    this.zza(int4, account, zzd.zza.zzla(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                    final zzr zzdr = zzr.zza.zzdr(parcel.readStrongBinder());
                    final int int6 = parcel.readInt();
                    final int int7 = parcel.readInt();
                    boolean b = false;
                    if (int7 != 0) {
                        b = onTransact;
                    }
                    this.zza(zzdr, int6, b);
                    parcel2.writeNoException();
                    break;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                    final int int8 = parcel.readInt();
                    RecordConsentRequest recordConsentRequest = null;
                    if (int8 != 0) {
                        recordConsentRequest = (RecordConsentRequest)RecordConsentRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.zza(recordConsentRequest, zzd.zza.zzla(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                    this.zzb(zzd.zza.zzla(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 12: {
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                    final int int9 = parcel.readInt();
                    SignInRequest signInRequest = null;
                    if (int9 != 0) {
                        signInRequest = (SignInRequest)SignInRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.zza(signInRequest, zzd.zza.zzla(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zze
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public void zza(final int n, final Account account, final zzd zzd) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                            obtain.writeInt(n);
                            if (account != null) {
                                obtain.writeInt(1);
                                account.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (zzd != null) {
                                final IBinder binder = zzd.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.zzajf.transact(8, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void zza(final AuthAccountRequest authAccountRequest, final zzd zzd) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                            if (authAccountRequest != null) {
                                obtain.writeInt(1);
                                authAccountRequest.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (zzd != null) {
                                final IBinder binder = zzd.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.zzajf.transact(2, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void zza(final ResolveAccountRequest resolveAccountRequest, final zzx zzx) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                            if (resolveAccountRequest != null) {
                                obtain.writeInt(1);
                                resolveAccountRequest.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (zzx != null) {
                                final IBinder binder = zzx.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.zzajf.transact(5, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void zza(final zzr zzr, final int n, final boolean b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    IBinder binder;
                    if (zzr != null) {
                        binder = zzr.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    int n2 = 0;
                    if (b) {
                        n2 = 1;
                    }
                    obtain.writeInt(n2);
                    this.zzajf.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zza(final CheckServerAuthResult checkServerAuthResult) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    if (checkServerAuthResult != null) {
                        obtain.writeInt(1);
                        checkServerAuthResult.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.zzajf.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zza(final RecordConsentRequest recordConsentRequest, final zzd zzd) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                            if (recordConsentRequest != null) {
                                obtain.writeInt(1);
                                recordConsentRequest.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (zzd != null) {
                                final IBinder binder = zzd.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.zzajf.transact(10, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void zza(final SignInRequest signInRequest, final zzd zzd) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                            if (signInRequest != null) {
                                obtain.writeInt(1);
                                signInRequest.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (zzd != null) {
                                final IBinder binder = zzd.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.zzajf.transact(12, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void zzaaf(final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    obtain.writeInt(n);
                    this.zzajf.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zzb(final zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zzcj(final boolean b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    int n = 0;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.zzajf.transact(4, obtain, obtain2, 0);
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
