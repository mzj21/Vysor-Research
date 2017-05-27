// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import java.util.List;
import android.os.Bundle;
import android.location.Location;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcelable$Creator;
import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.internal.zzib;
import com.google.android.gms.internal.zzhx;
import com.google.android.gms.internal.zzdu;
import com.google.android.gms.ads.internal.reward.client.zzd;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzu extends IInterface
{
    void destroy() throws RemoteException;
    
    String getMediationAdapterClassName() throws RemoteException;
    
    boolean isLoading() throws RemoteException;
    
    boolean isReady() throws RemoteException;
    
    void pause() throws RemoteException;
    
    void resume() throws RemoteException;
    
    void setManualImpressionsEnabled(final boolean p0) throws RemoteException;
    
    void setUserId(final String p0) throws RemoteException;
    
    void showInterstitial() throws RemoteException;
    
    void stopLoading() throws RemoteException;
    
    void zza(final AdSizeParcel p0) throws RemoteException;
    
    void zza(final VideoOptionsParcel p0) throws RemoteException;
    
    void zza(final zzp p0) throws RemoteException;
    
    void zza(final zzq p0) throws RemoteException;
    
    void zza(final zzw p0) throws RemoteException;
    
    void zza(final zzy p0) throws RemoteException;
    
    void zza(final zzd p0) throws RemoteException;
    
    void zza(final zzdu p0) throws RemoteException;
    
    void zza(final zzhx p0) throws RemoteException;
    
    void zza(final zzib p0, final String p1) throws RemoteException;
    
    boolean zzb(final AdRequestParcel p0) throws RemoteException;
    
    com.google.android.gms.dynamic.zzd zzds() throws RemoteException;
    
    AdSizeParcel zzdt() throws RemoteException;
    
    void zzdv() throws RemoteException;
    
    zzab zzdw() throws RemoteException;
    
    public abstract static class zza extends Binder implements zzu
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.client.IAdManager");
        }
        
        public static zzu zzq(final IBinder binder) {
            zzu zzu;
            if (binder == null) {
                zzu = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzu) {
                    zzu = (zzu)queryLocalInterface;
                }
                else {
                    zzu = new zzu.zza.zza(binder);
                }
            }
            return zzu;
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
                    parcel2.writeString("com.google.android.gms.ads.internal.client.IAdManager");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    final com.google.android.gms.dynamic.zzd zzds = this.zzds();
                    parcel2.writeNoException();
                    IBinder binder = null;
                    if (zzds != null) {
                        binder = zzds.asBinder();
                    }
                    parcel2.writeStrongBinder(binder);
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.destroy();
                    parcel2.writeNoException();
                    break;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    final boolean ready = this.isReady();
                    parcel2.writeNoException();
                    int n3;
                    if (ready) {
                        n3 = onTransact;
                    }
                    else {
                        n3 = 0;
                    }
                    parcel2.writeInt(n3);
                    break;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    final int int1 = parcel.readInt();
                    AdRequestParcel adRequestParcel = null;
                    if (int1 != 0) {
                        adRequestParcel = (AdRequestParcel)AdRequestParcel.CREATOR.createFromParcel(parcel);
                    }
                    final boolean zzb = this.zzb(adRequestParcel);
                    parcel2.writeNoException();
                    int n4 = 0;
                    if (zzb) {
                        n4 = onTransact;
                    }
                    parcel2.writeInt(n4);
                    break;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.pause();
                    parcel2.writeNoException();
                    break;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.resume();
                    parcel2.writeNoException();
                    break;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zza(zzq.zza.zzm(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zza(zzw.zza.zzs(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.showInterstitial();
                    parcel2.writeNoException();
                    break;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.stopLoading();
                    parcel2.writeNoException();
                    break;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzdv();
                    parcel2.writeNoException();
                    break;
                }
                case 12: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    final AdSizeParcel zzdt = this.zzdt();
                    parcel2.writeNoException();
                    if (zzdt != null) {
                        parcel2.writeInt(onTransact);
                        zzdt.writeToParcel(parcel2, onTransact);
                        break;
                    }
                    parcel2.writeInt(0);
                    break;
                }
                case 13: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    final int int2 = parcel.readInt();
                    AdSizeParcel adSizeParcel = null;
                    if (int2 != 0) {
                        adSizeParcel = (AdSizeParcel)AdSizeParcel.CREATOR.createFromParcel(parcel);
                    }
                    this.zza(adSizeParcel);
                    parcel2.writeNoException();
                    break;
                }
                case 14: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zza(zzhx.zza.zzax(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 15: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zza(zzib.zza.zzbb(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    break;
                }
                case 18: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    final String mediationAdapterClassName = this.getMediationAdapterClassName();
                    parcel2.writeNoException();
                    parcel2.writeString(mediationAdapterClassName);
                    break;
                }
                case 19: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zza(zzdu.zza.zzaa(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 20: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zza(zzp.zza.zzl(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 21: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zza(zzy.zza.zzt(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 22: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    final int int3 = parcel.readInt();
                    boolean manualImpressionsEnabled = false;
                    if (int3 != 0) {
                        manualImpressionsEnabled = (onTransact != 0);
                    }
                    this.setManualImpressionsEnabled(manualImpressionsEnabled);
                    parcel2.writeNoException();
                    break;
                }
                case 23: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    final boolean loading = this.isLoading();
                    parcel2.writeNoException();
                    int n5 = 0;
                    if (loading) {
                        n5 = onTransact;
                    }
                    parcel2.writeInt(n5);
                    break;
                }
                case 24: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zza(zzd.zza.zzbj(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 25: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    this.setUserId(parcel.readString());
                    parcel2.writeNoException();
                    break;
                }
                case 26: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    final zzab zzdw = this.zzdw();
                    parcel2.writeNoException();
                    IBinder binder2 = null;
                    if (zzdw != null) {
                        binder2 = zzdw.asBinder();
                    }
                    parcel2.writeStrongBinder(binder2);
                    break;
                }
                case 29: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    final int int4 = parcel.readInt();
                    VideoOptionsParcel videoOptionsParcel = null;
                    if (int4 != 0) {
                        videoOptionsParcel = (VideoOptionsParcel)VideoOptionsParcel.CREATOR.createFromParcel(parcel);
                    }
                    this.zza(videoOptionsParcel);
                    parcel2.writeNoException();
                    break;
                }
            }
            return onTransact != 0;
        }
        
        private static class zza implements zzu
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public void destroy() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzajf.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String getMediationAdapterClassName() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzajf.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isLoading() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzajf.transact(23, obtain, obtain2, 0);
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
            public boolean isReady() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
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
            public void pause() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzajf.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void resume() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzajf.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setManualImpressionsEnabled(final boolean b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    int n = 0;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.zzajf.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setUserId(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    obtain.writeString(s);
                    this.zzajf.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void showInterstitial() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzajf.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void stopLoading() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzajf.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zza(final AdSizeParcel adSizeParcel) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    if (adSizeParcel != null) {
                        obtain.writeInt(1);
                        adSizeParcel.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.zzajf.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zza(final VideoOptionsParcel videoOptionsParcel) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    if (videoOptionsParcel != null) {
                        obtain.writeInt(1);
                        videoOptionsParcel.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.zzajf.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zza(final zzp zzp) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    IBinder binder;
                    if (zzp != null) {
                        binder = zzp.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zza(final zzq zzq) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    IBinder binder;
                    if (zzq != null) {
                        binder = zzq.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zza(final zzw zzw) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    IBinder binder;
                    if (zzw != null) {
                        binder = zzw.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zza(final zzy zzy) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    IBinder binder;
                    if (zzy != null) {
                        binder = zzy.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zza(final zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zza(final zzdu zzdu) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    IBinder binder;
                    if (zzdu != null) {
                        binder = zzdu.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zza(final zzhx zzhx) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    IBinder binder;
                    if (zzhx != null) {
                        binder = zzhx.asBinder();
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
            
            @Override
            public void zza(final zzib zzib, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    IBinder binder;
                    if (zzib != null) {
                        binder = zzib.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    this.zzajf.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean zzb(final AdRequestParcel adRequestParcel) throws RemoteException {
                while (true) {
                    boolean b = true;
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                        if (adRequestParcel != null) {
                            obtain.writeInt(1);
                            adRequestParcel.writeToParcel(obtain, 0);
                        }
                        else {
                            obtain.writeInt(0);
                        }
                        this.zzajf.transact(4, obtain, obtain2, 0);
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            return b;
                        }
                    }
                    finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                    b = false;
                    return b;
                }
            }
            
            @Override
            public com.google.android.gms.dynamic.zzd zzds() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzajf.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.dynamic.zzd.zza.zzfe(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public AdSizeParcel zzdt() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzajf.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    AdSizeParcel adSizeParcel;
                    if (obtain2.readInt() != 0) {
                        adSizeParcel = (AdSizeParcel)AdSizeParcel.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        adSizeParcel = null;
                    }
                    return adSizeParcel;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zzdv() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzajf.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public zzab zzdw() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzajf.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzab.zza.zzw(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
