// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.dynamic.zzd;
import java.util.List;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzgv extends IInterface
{
    String getAdvertiser() throws RemoteException;
    
    String getBody() throws RemoteException;
    
    String getCallToAction() throws RemoteException;
    
    Bundle getExtras() throws RemoteException;
    
    String getHeadline() throws RemoteException;
    
    List getImages() throws RemoteException;
    
    boolean getOverrideClickHandling() throws RemoteException;
    
    boolean getOverrideImpressionRecording() throws RemoteException;
    
    void recordImpression() throws RemoteException;
    
    void zzk(final zzd p0) throws RemoteException;
    
    void zzl(final zzd p0) throws RemoteException;
    
    zzdx zzlt() throws RemoteException;
    
    void zzm(final zzd p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzgv
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
        }
        
        public static zzgv zzar(final IBinder binder) {
            zzgv zzgv;
            if (binder == null) {
                zzgv = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzgv) {
                    zzgv = (zzgv)queryLocalInterface;
                }
                else {
                    zzgv = new zzgv.zza.zza(binder);
                }
            }
            return zzgv;
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
                    parcel2.writeString("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    final String headline = this.getHeadline();
                    parcel2.writeNoException();
                    parcel2.writeString(headline);
                    break;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    final List images = this.getImages();
                    parcel2.writeNoException();
                    parcel2.writeList(images);
                    break;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    final String body = this.getBody();
                    parcel2.writeNoException();
                    parcel2.writeString(body);
                    break;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    final zzdx zzlt = this.zzlt();
                    parcel2.writeNoException();
                    IBinder binder;
                    if (zzlt != null) {
                        binder = zzlt.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    parcel2.writeStrongBinder(binder);
                    break;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    final String callToAction = this.getCallToAction();
                    parcel2.writeNoException();
                    parcel2.writeString(callToAction);
                    break;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    final String advertiser = this.getAdvertiser();
                    parcel2.writeNoException();
                    parcel2.writeString(advertiser);
                    break;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    this.recordImpression();
                    parcel2.writeNoException();
                    break;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    this.zzk(zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    this.zzl(zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    final boolean overrideImpressionRecording = this.getOverrideImpressionRecording();
                    parcel2.writeNoException();
                    int n3 = 0;
                    if (overrideImpressionRecording) {
                        n3 = onTransact;
                    }
                    parcel2.writeInt(n3);
                    break;
                }
                case 12: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    final boolean overrideClickHandling = this.getOverrideClickHandling();
                    parcel2.writeNoException();
                    int n4 = 0;
                    if (overrideClickHandling) {
                        n4 = onTransact;
                    }
                    parcel2.writeInt(n4);
                    break;
                }
                case 13: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    final Bundle extras = this.getExtras();
                    parcel2.writeNoException();
                    if (extras != null) {
                        parcel2.writeInt(onTransact);
                        extras.writeToParcel(parcel2, onTransact);
                        break;
                    }
                    parcel2.writeInt(0);
                    break;
                }
                case 14: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    this.zzm(zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
            }
            return onTransact != 0;
        }
        
        private static class zza implements zzgv
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public String getAdvertiser() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
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
            public String getBody() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    this.zzajf.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String getCallToAction() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    this.zzajf.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Bundle getExtras() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    this.zzajf.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    Bundle bundle;
                    if (obtain2.readInt() != 0) {
                        bundle = (Bundle)Bundle.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        bundle = null;
                    }
                    return bundle;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String getHeadline() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    this.zzajf.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public List getImages() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    this.zzajf.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readArrayList(this.getClass().getClassLoader());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean getOverrideClickHandling() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    this.zzajf.transact(12, obtain, obtain2, 0);
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
            public boolean getOverrideImpressionRecording() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    this.zzajf.transact(11, obtain, obtain2, 0);
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
            public void recordImpression() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    this.zzajf.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zzk(final zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
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
            public void zzl(final zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public zzdx zzlt() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    this.zzajf.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzdx.zza.zzab(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zzm(final zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(14, obtain, obtain2, 0);
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
