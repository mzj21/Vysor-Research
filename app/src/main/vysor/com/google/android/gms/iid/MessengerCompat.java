// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.iid;

import android.os.Binder;
import android.os.RemoteException;
import android.annotation.TargetApi;
import android.os.Message;
import android.os.Build$VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Messenger;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class MessengerCompat implements Parcelable
{
    public static final Parcelable$Creator<MessengerCompat> CREATOR;
    Messenger agg;
    zzb agh;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<MessengerCompat>() {
            public MessengerCompat zznc(final Parcel parcel) {
                final IBinder strongBinder = parcel.readStrongBinder();
                MessengerCompat messengerCompat;
                if (strongBinder != null) {
                    messengerCompat = new MessengerCompat(strongBinder);
                }
                else {
                    messengerCompat = null;
                }
                return messengerCompat;
            }
            
            public MessengerCompat[] zztu(final int n) {
                return new MessengerCompat[n];
            }
        };
    }
    
    public MessengerCompat(final Handler handler) {
        if (Build$VERSION.SDK_INT >= 21) {
            this.agg = new Messenger(handler);
        }
        else {
            this.agh = new zza(handler);
        }
    }
    
    public MessengerCompat(final IBinder binder) {
        if (Build$VERSION.SDK_INT >= 21) {
            this.agg = new Messenger(binder);
        }
        else {
            this.agh = zzb.zza.zzgw(binder);
        }
    }
    
    public static int zzc(final Message message) {
        int n;
        if (Build$VERSION.SDK_INT >= 21) {
            n = zzd(message);
        }
        else {
            n = message.arg2;
        }
        return n;
    }
    
    @TargetApi(21)
    private static int zzd(final Message message) {
        return message.sendingUid;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean equals = false;
        if (o != null) {
            try {
                equals = this.getBinder().equals(((MessengerCompat)o).getBinder());
            }
            catch (ClassCastException ex) {
                equals = false;
            }
        }
        return equals;
    }
    
    public IBinder getBinder() {
        IBinder binder;
        if (this.agg != null) {
            binder = this.agg.getBinder();
        }
        else {
            binder = this.agh.asBinder();
        }
        return binder;
    }
    
    @Override
    public int hashCode() {
        return this.getBinder().hashCode();
    }
    
    public void send(final Message message) throws RemoteException {
        if (this.agg != null) {
            this.agg.send(message);
        }
        else {
            this.agh.send(message);
        }
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (this.agg != null) {
            parcel.writeStrongBinder(this.agg.getBinder());
        }
        else {
            parcel.writeStrongBinder(this.agh.asBinder());
        }
    }
    
    private final class zza extends zzb.zza
    {
        Handler handler;
        
        zza(final Handler handler) {
            this.handler = handler;
        }
        
        public void send(final Message message) throws RemoteException {
            message.arg2 = Binder.getCallingUid();
            this.handler.dispatchMessage(message);
        }
    }
}
