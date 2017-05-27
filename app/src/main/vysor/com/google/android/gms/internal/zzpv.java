// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.playlog.internal.PlayLoggerContext;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcelable$Creator;
import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.clearcut.LogEventParcelable;
import android.os.IInterface;

public interface zzpv extends IInterface
{
    void zza(final zzpu p0, final LogEventParcelable p1) throws RemoteException;
    
    public abstract static class zza extends Binder implements zzpv
    {
        public static zzpv zzdo(final IBinder binder) {
            zzpv zzpv;
            if (binder == null) {
                zzpv = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzpv) {
                    zzpv = (zzpv)queryLocalInterface;
                }
                else {
                    zzpv = new zzpv.zza.zza(binder);
                }
            }
            return zzpv;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            boolean onTransact = false;
            switch (n) {
                default: {
                    onTransact = super.onTransact(n, parcel, parcel2, n2);
                    break;
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                    onTransact = true;
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                    final zzpu zzdn = zzpu.zza.zzdn(parcel.readStrongBinder());
                    LogEventParcelable logEventParcelable;
                    if (parcel.readInt() != 0) {
                        logEventParcelable = (LogEventParcelable)LogEventParcelable.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        logEventParcelable = null;
                    }
                    this.zza(zzdn, logEventParcelable);
                    onTransact = true;
                    break;
                }
            }
            return onTransact;
        }
        
        private static class zza implements zzpv
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public void zza(final zzpu zzpu, final LogEventParcelable logEventParcelable) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                    IBinder binder = null;
                    if (zzpu != null) {
                        binder = zzpu.asBinder();
                    }
                    obtain.writeStrongBinder(binder);
                    if (logEventParcelable != null) {
                        obtain.writeInt(1);
                        logEventParcelable.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.zzajf.transact(1, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
        }
    }
}
