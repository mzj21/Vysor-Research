// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.request;

import java.util.List;
import com.google.android.gms.ads.internal.safebrowsing.SafeBrowsingConfigParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcelable$Creator;
import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzl extends IInterface
{
    void zzb(final AdResponseParcel p0) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzl
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.request.IAdResponseListener");
        }
        
        public static zzl zzbf(final IBinder binder) {
            zzl zzl;
            if (binder == null) {
                zzl = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdResponseListener");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzl) {
                    zzl = (zzl)queryLocalInterface;
                }
                else {
                    zzl = new zzl.zza.zza(binder);
                }
            }
            return zzl;
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
                    parcel2.writeString("com.google.android.gms.ads.internal.request.IAdResponseListener");
                    onTransact = true;
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.request.IAdResponseListener");
                    AdResponseParcel adResponseParcel;
                    if (parcel.readInt() != 0) {
                        adResponseParcel = (AdResponseParcel)AdResponseParcel.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        adResponseParcel = null;
                    }
                    this.zzb(adResponseParcel);
                    parcel2.writeNoException();
                    onTransact = true;
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zzl
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public void zzb(final AdResponseParcel adResponseParcel) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.request.IAdResponseListener");
                    if (adResponseParcel != null) {
                        obtain.writeInt(1);
                        adResponseParcel.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
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
