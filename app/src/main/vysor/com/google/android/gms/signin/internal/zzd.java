// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Status;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import android.os.IInterface;

public interface zzd extends IInterface
{
    void zza(final ConnectionResult p0, final AuthAccountResult p1) throws RemoteException;
    
    void zza(final Status p0, final GoogleSignInAccount p1) throws RemoteException;
    
    void zzb(final SignInResponse p0) throws RemoteException;
    
    void zzed(final Status p0) throws RemoteException;
    
    void zzee(final Status p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzd
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.signin.internal.ISignInCallbacks");
        }
        
        public static zzd zzla(final IBinder binder) {
            zzd zzd;
            if (binder == null) {
                zzd = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzd) {
                    zzd = (zzd)queryLocalInterface;
                }
                else {
                    zzd = new zzd.zza.zza(binder);
                }
            }
            return zzd;
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
                    parcel2.writeString("com.google.android.gms.signin.internal.ISignInCallbacks");
                    onTransact = true;
                    break;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
                    ConnectionResult connectionResult;
                    if (parcel.readInt() != 0) {
                        connectionResult = (ConnectionResult)ConnectionResult.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        connectionResult = null;
                    }
                    AuthAccountResult authAccountResult;
                    if (parcel.readInt() != 0) {
                        authAccountResult = (AuthAccountResult)AuthAccountResult.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        authAccountResult = null;
                    }
                    this.zza(connectionResult, authAccountResult);
                    parcel2.writeNoException();
                    onTransact = true;
                    break;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
                    Status status;
                    if (parcel.readInt() != 0) {
                        status = (Status)Status.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        status = null;
                    }
                    this.zzed(status);
                    parcel2.writeNoException();
                    onTransact = true;
                    break;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
                    Status status2;
                    if (parcel.readInt() != 0) {
                        status2 = (Status)Status.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        status2 = null;
                    }
                    this.zzee(status2);
                    parcel2.writeNoException();
                    onTransact = true;
                    break;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
                    Status status3;
                    if (parcel.readInt() != 0) {
                        status3 = (Status)Status.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        status3 = null;
                    }
                    GoogleSignInAccount googleSignInAccount;
                    if (parcel.readInt() != 0) {
                        googleSignInAccount = (GoogleSignInAccount)GoogleSignInAccount.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        googleSignInAccount = null;
                    }
                    this.zza(status3, googleSignInAccount);
                    parcel2.writeNoException();
                    onTransact = true;
                    break;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
                    SignInResponse signInResponse;
                    if (parcel.readInt() != 0) {
                        signInResponse = (SignInResponse)SignInResponse.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        signInResponse = null;
                    }
                    this.zzb(signInResponse);
                    parcel2.writeNoException();
                    onTransact = true;
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zzd
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public void zza(final ConnectionResult connectionResult, final AuthAccountResult authAccountResult) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInCallbacks");
                            if (connectionResult != null) {
                                obtain.writeInt(1);
                                connectionResult.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (authAccountResult != null) {
                                obtain.writeInt(1);
                                authAccountResult.writeToParcel(obtain, 0);
                                this.zzajf.transact(3, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        obtain.writeInt(0);
                        continue;
                    }
                }
            }
            
            @Override
            public void zza(final Status status, final GoogleSignInAccount googleSignInAccount) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInCallbacks");
                            if (status != null) {
                                obtain.writeInt(1);
                                status.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (googleSignInAccount != null) {
                                obtain.writeInt(1);
                                googleSignInAccount.writeToParcel(obtain, 0);
                                this.zzajf.transact(7, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        obtain.writeInt(0);
                        continue;
                    }
                }
            }
            
            @Override
            public void zzb(final SignInResponse signInResponse) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInCallbacks");
                    if (signInResponse != null) {
                        obtain.writeInt(1);
                        signInResponse.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.zzajf.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zzed(final Status status) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInCallbacks");
                    if (status != null) {
                        obtain.writeInt(1);
                        status.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.zzajf.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zzee(final Status status) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInCallbacks");
                    if (status != null) {
                        obtain.writeInt(1);
                        status.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.zzajf.transact(6, obtain, obtain2, 0);
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
