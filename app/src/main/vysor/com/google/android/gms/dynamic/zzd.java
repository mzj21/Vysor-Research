// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.IInterface;

public interface zzd extends IInterface
{
    public abstract static class zza extends Binder implements zzd
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.dynamic.IObjectWrapper");
        }
        
        public static zzd zzfe(final IBinder binder) {
            zzd zzd;
            if (binder == null) {
                zzd = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
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
                    parcel2.writeString("com.google.android.gms.dynamic.IObjectWrapper");
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
        }
    }
}
