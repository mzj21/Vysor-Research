// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.client.VideoOptionsParcel;
import android.location.Location;
import com.google.android.gms.ads.internal.client.SearchAdRequestParcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcelable$Creator;
import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import java.util.List;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.zza;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.dynamic.zzd;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzgr extends IInterface
{
    void destroy() throws RemoteException;
    
    Bundle getInterstitialAdapterInfo() throws RemoteException;
    
    zzd getView() throws RemoteException;
    
    boolean isInitialized() throws RemoteException;
    
    void pause() throws RemoteException;
    
    void resume() throws RemoteException;
    
    void showInterstitial() throws RemoteException;
    
    void showVideo() throws RemoteException;
    
    void zza(final AdRequestParcel p0, final String p1, final String p2) throws RemoteException;
    
    void zza(final zzd p0, final AdRequestParcel p1, final String p2, final com.google.android.gms.ads.internal.reward.mediation.client.zza p3, final String p4) throws RemoteException;
    
    void zza(final zzd p0, final AdRequestParcel p1, final String p2, final zzgs p3) throws RemoteException;
    
    void zza(final zzd p0, final AdRequestParcel p1, final String p2, final String p3, final zzgs p4) throws RemoteException;
    
    void zza(final zzd p0, final AdRequestParcel p1, final String p2, final String p3, final zzgs p4, final NativeAdOptionsParcel p5, final List<String> p6) throws RemoteException;
    
    void zza(final zzd p0, final AdSizeParcel p1, final AdRequestParcel p2, final String p3, final zzgs p4) throws RemoteException;
    
    void zza(final zzd p0, final AdSizeParcel p1, final AdRequestParcel p2, final String p3, final String p4, final zzgs p5) throws RemoteException;
    
    void zzc(final AdRequestParcel p0, final String p1) throws RemoteException;
    
    void zzj(final zzd p0) throws RemoteException;
    
    zzgu zznm() throws RemoteException;
    
    zzgv zznn() throws RemoteException;
    
    Bundle zzno() throws RemoteException;
    
    Bundle zznp() throws RemoteException;
    
    public abstract static class zza extends Binder implements zzgr
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        }
        
        public static zzgr zzan(final IBinder binder) {
            zzgr zzgr;
            if (binder == null) {
                zzgr = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzgr) {
                    zzgr = (zzgr)queryLocalInterface;
                }
                else {
                    zzgr = new zzgr.zza.zza(binder);
                }
            }
            return zzgr;
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
                    parcel2.writeString("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    break;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    final zzd zzfe = zzd.zza.zzfe(parcel.readStrongBinder());
                    AdSizeParcel adSizeParcel;
                    if (parcel.readInt() != 0) {
                        adSizeParcel = (AdSizeParcel)AdSizeParcel.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        adSizeParcel = null;
                    }
                    AdRequestParcel adRequestParcel;
                    if (parcel.readInt() != 0) {
                        adRequestParcel = (AdRequestParcel)AdRequestParcel.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        adRequestParcel = null;
                    }
                    this.zza(zzfe, adSizeParcel, adRequestParcel, parcel.readString(), zzgs.zza.zzao(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    final zzd view = this.getView();
                    parcel2.writeNoException();
                    IBinder binder = null;
                    if (view != null) {
                        binder = view.asBinder();
                    }
                    parcel2.writeStrongBinder(binder);
                    break;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    final zzd zzfe2 = zzd.zza.zzfe(parcel.readStrongBinder());
                    AdRequestParcel adRequestParcel2;
                    if (parcel.readInt() != 0) {
                        adRequestParcel2 = (AdRequestParcel)AdRequestParcel.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        adRequestParcel2 = null;
                    }
                    this.zza(zzfe2, adRequestParcel2, parcel.readString(), zzgs.zza.zzao(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.showInterstitial();
                    parcel2.writeNoException();
                    break;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.destroy();
                    parcel2.writeNoException();
                    break;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    final zzd zzfe3 = zzd.zza.zzfe(parcel.readStrongBinder());
                    AdSizeParcel adSizeParcel2;
                    if (parcel.readInt() != 0) {
                        adSizeParcel2 = (AdSizeParcel)AdSizeParcel.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        adSizeParcel2 = null;
                    }
                    AdRequestParcel adRequestParcel3;
                    if (parcel.readInt() != 0) {
                        adRequestParcel3 = (AdRequestParcel)AdRequestParcel.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        adRequestParcel3 = null;
                    }
                    this.zza(zzfe3, adSizeParcel2, adRequestParcel3, parcel.readString(), parcel.readString(), zzgs.zza.zzao(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    final zzd zzfe4 = zzd.zza.zzfe(parcel.readStrongBinder());
                    AdRequestParcel adRequestParcel4;
                    if (parcel.readInt() != 0) {
                        adRequestParcel4 = (AdRequestParcel)AdRequestParcel.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        adRequestParcel4 = null;
                    }
                    this.zza(zzfe4, adRequestParcel4, parcel.readString(), parcel.readString(), zzgs.zza.zzao(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.pause();
                    parcel2.writeNoException();
                    break;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.resume();
                    parcel2.writeNoException();
                    break;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    final zzd zzfe5 = zzd.zza.zzfe(parcel.readStrongBinder());
                    AdRequestParcel adRequestParcel5;
                    if (parcel.readInt() != 0) {
                        adRequestParcel5 = (AdRequestParcel)AdRequestParcel.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        adRequestParcel5 = null;
                    }
                    this.zza(zzfe5, adRequestParcel5, parcel.readString(), zza.zza.zzbl(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    break;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    AdRequestParcel adRequestParcel6;
                    if (parcel.readInt() != 0) {
                        adRequestParcel6 = (AdRequestParcel)AdRequestParcel.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        adRequestParcel6 = null;
                    }
                    this.zzc(adRequestParcel6, parcel.readString());
                    parcel2.writeNoException();
                    break;
                }
                case 12: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.showVideo();
                    parcel2.writeNoException();
                    break;
                }
                case 13: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    final boolean initialized = this.isInitialized();
                    parcel2.writeNoException();
                    int n3 = 0;
                    if (initialized) {
                        n3 = onTransact;
                    }
                    parcel2.writeInt(n3);
                    break;
                }
                case 14: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    final zzd zzfe6 = zzd.zza.zzfe(parcel.readStrongBinder());
                    AdRequestParcel adRequestParcel7;
                    if (parcel.readInt() != 0) {
                        adRequestParcel7 = (AdRequestParcel)AdRequestParcel.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        adRequestParcel7 = null;
                    }
                    final String string = parcel.readString();
                    final String string2 = parcel.readString();
                    final zzgs zzao = zzgs.zza.zzao(parcel.readStrongBinder());
                    final int int1 = parcel.readInt();
                    NativeAdOptionsParcel nativeAdOptionsParcel = null;
                    if (int1 != 0) {
                        nativeAdOptionsParcel = (NativeAdOptionsParcel)NativeAdOptionsParcel.CREATOR.createFromParcel(parcel);
                    }
                    this.zza(zzfe6, adRequestParcel7, string, string2, zzao, nativeAdOptionsParcel, parcel.createStringArrayList());
                    parcel2.writeNoException();
                    break;
                }
                case 15: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    final zzgu zznm = this.zznm();
                    parcel2.writeNoException();
                    IBinder binder2 = null;
                    if (zznm != null) {
                        binder2 = zznm.asBinder();
                    }
                    parcel2.writeStrongBinder(binder2);
                    break;
                }
                case 16: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    final zzgv zznn = this.zznn();
                    parcel2.writeNoException();
                    IBinder binder3 = null;
                    if (zznn != null) {
                        binder3 = zznn.asBinder();
                    }
                    parcel2.writeStrongBinder(binder3);
                    break;
                }
                case 17: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    final Bundle zzno = this.zzno();
                    parcel2.writeNoException();
                    if (zzno != null) {
                        parcel2.writeInt(onTransact);
                        zzno.writeToParcel(parcel2, onTransact);
                        break;
                    }
                    parcel2.writeInt(0);
                    break;
                }
                case 18: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    final Bundle interstitialAdapterInfo = this.getInterstitialAdapterInfo();
                    parcel2.writeNoException();
                    if (interstitialAdapterInfo != null) {
                        parcel2.writeInt(onTransact);
                        interstitialAdapterInfo.writeToParcel(parcel2, onTransact);
                        break;
                    }
                    parcel2.writeInt(0);
                    break;
                }
                case 19: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    final Bundle zznp = this.zznp();
                    parcel2.writeNoException();
                    if (zznp != null) {
                        parcel2.writeInt(onTransact);
                        zznp.writeToParcel(parcel2, onTransact);
                        break;
                    }
                    parcel2.writeInt(0);
                    break;
                }
                case 20: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    AdRequestParcel adRequestParcel8;
                    if (parcel.readInt() != 0) {
                        adRequestParcel8 = (AdRequestParcel)AdRequestParcel.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        adRequestParcel8 = null;
                    }
                    this.zza(adRequestParcel8, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    break;
                }
                case 21: {
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzj(zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
            }
            return onTransact != 0;
        }
        
        private static class zza implements zzgr
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
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzajf.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Bundle getInterstitialAdapterInfo() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzajf.transact(18, obtain, obtain2, 0);
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
            public zzd getView() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzajf.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzd.zza.zzfe(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isInitialized() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzajf.transact(13, obtain, obtain2, 0);
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
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzajf.transact(8, obtain, obtain2, 0);
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
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzajf.transact(9, obtain, obtain2, 0);
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
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzajf.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void showVideo() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzajf.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zza(final AdRequestParcel adRequestParcel, final String s, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    if (adRequestParcel != null) {
                        obtain.writeInt(1);
                        adRequestParcel.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    this.zzajf.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zza(final zzd zzd, final AdRequestParcel adRequestParcel, final String s, final com.google.android.gms.ads.internal.reward.mediation.client.zza zza, final String s2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (adRequestParcel != null) {
                        obtain.writeInt(1);
                        adRequestParcel.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(s);
                    IBinder binder2 = null;
                    if (zza != null) {
                        binder2 = zza.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeString(s2);
                    this.zzajf.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zza(final zzd zzd, final AdRequestParcel adRequestParcel, final String s, final zzgs zzgs) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (adRequestParcel != null) {
                        obtain.writeInt(1);
                        adRequestParcel.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(s);
                    IBinder binder2 = null;
                    if (zzgs != null) {
                        binder2 = zzgs.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    this.zzajf.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zza(final zzd zzd, final AdRequestParcel adRequestParcel, final String s, final String s2, final zzgs zzgs) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (adRequestParcel != null) {
                        obtain.writeInt(1);
                        adRequestParcel.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    IBinder binder2 = null;
                    if (zzgs != null) {
                        binder2 = zzgs.asBinder();
                    }
                    obtain.writeStrongBinder(binder2);
                    this.zzajf.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zza(final zzd zzd, final AdRequestParcel adRequestParcel, final String s, final String s2, final zzgs zzgs, final NativeAdOptionsParcel nativeAdOptionsParcel, final List<String> list) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                            IBinder binder;
                            if (zzd != null) {
                                binder = zzd.asBinder();
                            }
                            else {
                                binder = null;
                            }
                            obtain.writeStrongBinder(binder);
                            if (adRequestParcel != null) {
                                obtain.writeInt(1);
                                adRequestParcel.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            obtain.writeString(s);
                            obtain.writeString(s2);
                            IBinder binder2 = null;
                            if (zzgs != null) {
                                binder2 = zzgs.asBinder();
                            }
                            obtain.writeStrongBinder(binder2);
                            if (nativeAdOptionsParcel != null) {
                                obtain.writeInt(1);
                                nativeAdOptionsParcel.writeToParcel(obtain, 0);
                                obtain.writeStringList((List)list);
                                this.zzajf.transact(14, obtain, obtain2, 0);
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
            public void zza(final zzd zzd, final AdSizeParcel adSizeParcel, final AdRequestParcel adRequestParcel, final String s, final zzgs zzgs) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                            IBinder binder;
                            if (zzd != null) {
                                binder = zzd.asBinder();
                            }
                            else {
                                binder = null;
                            }
                            obtain.writeStrongBinder(binder);
                            if (adSizeParcel != null) {
                                obtain.writeInt(1);
                                adSizeParcel.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (adRequestParcel != null) {
                                obtain.writeInt(1);
                                adRequestParcel.writeToParcel(obtain, 0);
                                obtain.writeString(s);
                                IBinder binder2 = null;
                                if (zzgs != null) {
                                    binder2 = zzgs.asBinder();
                                }
                                obtain.writeStrongBinder(binder2);
                                this.zzajf.transact(1, obtain, obtain2, 0);
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
            public void zza(final zzd zzd, final AdSizeParcel adSizeParcel, final AdRequestParcel adRequestParcel, final String s, final String s2, final zzgs zzgs) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                            IBinder binder;
                            if (zzd != null) {
                                binder = zzd.asBinder();
                            }
                            else {
                                binder = null;
                            }
                            obtain.writeStrongBinder(binder);
                            if (adSizeParcel != null) {
                                obtain.writeInt(1);
                                adSizeParcel.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (adRequestParcel != null) {
                                obtain.writeInt(1);
                                adRequestParcel.writeToParcel(obtain, 0);
                                obtain.writeString(s);
                                obtain.writeString(s2);
                                IBinder binder2 = null;
                                if (zzgs != null) {
                                    binder2 = zzgs.asBinder();
                                }
                                obtain.writeStrongBinder(binder2);
                                this.zzajf.transact(6, obtain, obtain2, 0);
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
            public void zzc(final AdRequestParcel adRequestParcel, final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    if (adRequestParcel != null) {
                        obtain.writeInt(1);
                        adRequestParcel.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(s);
                    this.zzajf.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zzj(final zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
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
            public zzgu zznm() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzajf.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzgu.zza.zzaq(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public zzgv zznn() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzajf.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzgv.zza.zzar(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Bundle zzno() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzajf.transact(17, obtain, obtain2, 0);
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
            public Bundle zznp() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzajf.transact(19, obtain, obtain2, 0);
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
        }
    }
}
