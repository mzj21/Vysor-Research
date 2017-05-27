// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.request;

import com.google.android.gms.ads.internal.safebrowsing.SafeBrowsingConfigParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import java.util.List;
import android.os.Bundle;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import android.os.Messenger;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.content.pm.PackageInfo;
import android.content.pm.ApplicationInfo;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcelable$Creator;
import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzk extends IInterface
{
    void zza(final AdRequestInfoParcel p0, final zzl p1) throws RemoteException;
    
    AdResponseParcel zzd(final AdRequestInfoParcel p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzk
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.request.IAdRequestService");
        }
        
        public static zzk zzbe(final IBinder binder) {
            zzk zzk;
            if (binder == null) {
                zzk = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzk) {
                    zzk = (zzk)queryLocalInterface;
                }
                else {
                    zzk = new zzk.zza.zza(binder);
                }
            }
            return zzk;
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
                    parcel2.writeString("com.google.android.gms.ads.internal.request.IAdRequestService");
                    onTransact = true;
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
                    final int int1 = parcel.readInt();
                    AdRequestInfoParcel adRequestInfoParcel = null;
                    if (int1 != 0) {
                        adRequestInfoParcel = (AdRequestInfoParcel)AdRequestInfoParcel.CREATOR.createFromParcel(parcel);
                    }
                    final AdResponseParcel zzd = this.zzd(adRequestInfoParcel);
                    parcel2.writeNoException();
                    if (zzd != null) {
                        parcel2.writeInt(1);
                        zzd.writeToParcel(parcel2, 1);
                    }
                    else {
                        parcel2.writeInt(0);
                    }
                    onTransact = true;
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
                    final int int2 = parcel.readInt();
                    AdRequestInfoParcel adRequestInfoParcel2 = null;
                    if (int2 != 0) {
                        adRequestInfoParcel2 = (AdRequestInfoParcel)AdRequestInfoParcel.CREATOR.createFromParcel(parcel);
                    }
                    this.zza(adRequestInfoParcel2, zzl.zza.zzbf(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    onTransact = true;
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zzk
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public void zza(final AdRequestInfoParcel adRequestInfoParcel, final zzl zzl) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.request.IAdRequestService");
                            if (adRequestInfoParcel != null) {
                                obtain.writeInt(1);
                                adRequestInfoParcel.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (zzl != null) {
                                final IBinder binder = zzl.asBinder();
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
            public AdResponseParcel zzd(final AdRequestInfoParcel adRequestInfoParcel) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.ads.internal.request.IAdRequestService");
                        if (adRequestInfoParcel != null) {
                            obtain.writeInt(1);
                            adRequestInfoParcel.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        this.zzajf.transact(1, obtain, obtain2, 0);
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            return (AdResponseParcel)AdResponseParcel.CREATOR.createFromParcel(obtain2);
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    return null;
                }
            }
        }
    }
}
