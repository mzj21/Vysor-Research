// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import java.util.List;
import android.os.IInterface;

public interface zzef extends IInterface
{
    List<String> getAvailableAssetNames() throws RemoteException;
    
    String getCustomTemplateId() throws RemoteException;
    
    void performClick(final String p0) throws RemoteException;
    
    void recordImpression() throws RemoteException;
    
    String zzaw(final String p0) throws RemoteException;
    
    zzdx zzax(final String p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzef
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
        }
        
        public static zzef zzag(final IBinder binder) {
            zzef zzef;
            if (binder == null) {
                zzef = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzef) {
                    zzef = (zzef)queryLocalInterface;
                }
                else {
                    zzef = new zzef.zza.zza(binder);
                }
            }
            return zzef;
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
                    parcel2.writeString("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
                    onTransact = true;
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
                    final String zzaw = this.zzaw(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(zzaw);
                    onTransact = true;
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
                    final zzdx zzax = this.zzax(parcel.readString());
                    parcel2.writeNoException();
                    IBinder binder;
                    if (zzax != null) {
                        binder = zzax.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    parcel2.writeStrongBinder(binder);
                    onTransact = true;
                    break;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
                    final List<String> availableAssetNames = this.getAvailableAssetNames();
                    parcel2.writeNoException();
                    parcel2.writeStringList((List)availableAssetNames);
                    onTransact = true;
                    break;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
                    final String customTemplateId = this.getCustomTemplateId();
                    parcel2.writeNoException();
                    parcel2.writeString(customTemplateId);
                    onTransact = true;
                    break;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
                    this.performClick(parcel.readString());
                    parcel2.writeNoException();
                    onTransact = true;
                    break;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
                    this.recordImpression();
                    parcel2.writeNoException();
                    onTransact = true;
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zzef
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public List<String> getAvailableAssetNames() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
                    this.zzajf.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return (List<String>)obtain2.createStringArrayList();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String getCustomTemplateId() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
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
            public void performClick(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
                    obtain.writeString(s);
                    this.zzajf.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
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
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
                    this.zzajf.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String zzaw(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
                    obtain.writeString(s);
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
            public zzdx zzax(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
                    obtain.writeString(s);
                    this.zzajf.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzdx.zza.zzab(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
