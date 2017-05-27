// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.content.Intent;
import android.os.RemoteException;
import android.os.Bundle;
import android.os.IInterface;

public interface zzc extends IInterface
{
    Bundle getArguments() throws RemoteException;
    
    int getId() throws RemoteException;
    
    boolean getRetainInstance() throws RemoteException;
    
    String getTag() throws RemoteException;
    
    int getTargetRequestCode() throws RemoteException;
    
    boolean getUserVisibleHint() throws RemoteException;
    
    zzd getView() throws RemoteException;
    
    boolean isAdded() throws RemoteException;
    
    boolean isDetached() throws RemoteException;
    
    boolean isHidden() throws RemoteException;
    
    boolean isInLayout() throws RemoteException;
    
    boolean isRemoving() throws RemoteException;
    
    boolean isResumed() throws RemoteException;
    
    boolean isVisible() throws RemoteException;
    
    void setHasOptionsMenu(final boolean p0) throws RemoteException;
    
    void setMenuVisibility(final boolean p0) throws RemoteException;
    
    void setRetainInstance(final boolean p0) throws RemoteException;
    
    void setUserVisibleHint(final boolean p0) throws RemoteException;
    
    void startActivity(final Intent p0) throws RemoteException;
    
    void startActivityForResult(final Intent p0, final int p1) throws RemoteException;
    
    void zzac(final zzd p0) throws RemoteException;
    
    void zzad(final zzd p0) throws RemoteException;
    
    zzd zzbdu() throws RemoteException;
    
    zzc zzbdv() throws RemoteException;
    
    zzd zzbdw() throws RemoteException;
    
    zzc zzbdx() throws RemoteException;
    
    public abstract static class zza extends Binder implements zzc
    {
        public zza() {
            this.attachInterface((IInterface)this, "com.google.android.gms.dynamic.IFragmentWrapper");
        }
        
        public static zzc zzfd(final IBinder binder) {
            zzc zzc;
            if (binder == null) {
                zzc = null;
            }
            else {
                final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                if (queryLocalInterface != null && queryLocalInterface instanceof zzc) {
                    zzc = (zzc)queryLocalInterface;
                }
                else {
                    zzc = new zzc.zza.zza(binder);
                }
            }
            return zzc;
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
                    parcel2.writeString("com.google.android.gms.dynamic.IFragmentWrapper");
                    break;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final zzd zzbdu = this.zzbdu();
                    parcel2.writeNoException();
                    IBinder binder = null;
                    if (zzbdu != null) {
                        binder = zzbdu.asBinder();
                    }
                    parcel2.writeStrongBinder(binder);
                    break;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final Bundle arguments = this.getArguments();
                    parcel2.writeNoException();
                    if (arguments != null) {
                        parcel2.writeInt(onTransact);
                        arguments.writeToParcel(parcel2, onTransact);
                        break;
                    }
                    parcel2.writeInt(0);
                    break;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final int id = this.getId();
                    parcel2.writeNoException();
                    parcel2.writeInt(id);
                    break;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final zzc zzbdv = this.zzbdv();
                    parcel2.writeNoException();
                    IBinder binder2 = null;
                    if (zzbdv != null) {
                        binder2 = zzbdv.asBinder();
                    }
                    parcel2.writeStrongBinder(binder2);
                    break;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final zzd zzbdw = this.zzbdw();
                    parcel2.writeNoException();
                    IBinder binder3 = null;
                    if (zzbdw != null) {
                        binder3 = zzbdw.asBinder();
                    }
                    parcel2.writeStrongBinder(binder3);
                    break;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final boolean retainInstance = this.getRetainInstance();
                    parcel2.writeNoException();
                    int n3;
                    if (retainInstance) {
                        n3 = onTransact;
                    }
                    else {
                        n3 = 0;
                    }
                    parcel2.writeInt(n3);
                    break;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final String tag = this.getTag();
                    parcel2.writeNoException();
                    parcel2.writeString(tag);
                    break;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final zzc zzbdx = this.zzbdx();
                    parcel2.writeNoException();
                    IBinder binder4 = null;
                    if (zzbdx != null) {
                        binder4 = zzbdx.asBinder();
                    }
                    parcel2.writeStrongBinder(binder4);
                    break;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final int targetRequestCode = this.getTargetRequestCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(targetRequestCode);
                    break;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final boolean userVisibleHint = this.getUserVisibleHint();
                    parcel2.writeNoException();
                    int n4 = 0;
                    if (userVisibleHint) {
                        n4 = onTransact;
                    }
                    parcel2.writeInt(n4);
                    break;
                }
                case 12: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final zzd view = this.getView();
                    parcel2.writeNoException();
                    IBinder binder5 = null;
                    if (view != null) {
                        binder5 = view.asBinder();
                    }
                    parcel2.writeStrongBinder(binder5);
                    break;
                }
                case 13: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final boolean added = this.isAdded();
                    parcel2.writeNoException();
                    int n5 = 0;
                    if (added) {
                        n5 = onTransact;
                    }
                    parcel2.writeInt(n5);
                    break;
                }
                case 14: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final boolean detached = this.isDetached();
                    parcel2.writeNoException();
                    int n6 = 0;
                    if (detached) {
                        n6 = onTransact;
                    }
                    parcel2.writeInt(n6);
                    break;
                }
                case 15: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final boolean hidden = this.isHidden();
                    parcel2.writeNoException();
                    int n7 = 0;
                    if (hidden) {
                        n7 = onTransact;
                    }
                    parcel2.writeInt(n7);
                    break;
                }
                case 16: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final boolean inLayout = this.isInLayout();
                    parcel2.writeNoException();
                    int n8 = 0;
                    if (inLayout) {
                        n8 = onTransact;
                    }
                    parcel2.writeInt(n8);
                    break;
                }
                case 17: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final boolean removing = this.isRemoving();
                    parcel2.writeNoException();
                    int n9 = 0;
                    if (removing) {
                        n9 = onTransact;
                    }
                    parcel2.writeInt(n9);
                    break;
                }
                case 18: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final boolean resumed = this.isResumed();
                    parcel2.writeNoException();
                    int n10 = 0;
                    if (resumed) {
                        n10 = onTransact;
                    }
                    parcel2.writeInt(n10);
                    break;
                }
                case 19: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final boolean visible = this.isVisible();
                    parcel2.writeNoException();
                    int n11 = 0;
                    if (visible) {
                        n11 = onTransact;
                    }
                    parcel2.writeInt(n11);
                    break;
                }
                case 20: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzac(zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
                case 21: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final int int1 = parcel.readInt();
                    boolean hasOptionsMenu = false;
                    if (int1 != 0) {
                        hasOptionsMenu = (onTransact != 0);
                    }
                    this.setHasOptionsMenu(hasOptionsMenu);
                    parcel2.writeNoException();
                    break;
                }
                case 22: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final int int2 = parcel.readInt();
                    boolean menuVisibility = false;
                    if (int2 != 0) {
                        menuVisibility = (onTransact != 0);
                    }
                    this.setMenuVisibility(menuVisibility);
                    parcel2.writeNoException();
                    break;
                }
                case 23: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final int int3 = parcel.readInt();
                    boolean retainInstance2 = false;
                    if (int3 != 0) {
                        retainInstance2 = (onTransact != 0);
                    }
                    this.setRetainInstance(retainInstance2);
                    parcel2.writeNoException();
                    break;
                }
                case 24: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final int int4 = parcel.readInt();
                    boolean userVisibleHint2 = false;
                    if (int4 != 0) {
                        userVisibleHint2 = (onTransact != 0);
                    }
                    this.setUserVisibleHint(userVisibleHint2);
                    parcel2.writeNoException();
                    break;
                }
                case 25: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final int int5 = parcel.readInt();
                    Intent intent = null;
                    if (int5 != 0) {
                        intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
                    }
                    this.startActivity(intent);
                    parcel2.writeNoException();
                    break;
                }
                case 26: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    final int int6 = parcel.readInt();
                    Intent intent2 = null;
                    if (int6 != 0) {
                        intent2 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                    }
                    this.startActivityForResult(intent2, parcel.readInt());
                    parcel2.writeNoException();
                    break;
                }
                case 27: {
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzad(zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    break;
                }
            }
            return onTransact != 0;
        }
        
        private static class zza implements zzc
        {
            private IBinder zzajf;
            
            zza(final IBinder zzajf) {
                this.zzajf = zzajf;
            }
            
            public IBinder asBinder() {
                return this.zzajf;
            }
            
            @Override
            public Bundle getArguments() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzajf.transact(3, obtain, obtain2, 0);
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
            public int getId() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzajf.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean getRetainInstance() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzajf.transact(7, obtain, obtain2, 0);
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
            public String getTag() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzajf.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int getTargetRequestCode() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzajf.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean getUserVisibleHint() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
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
            public zzd getView() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzajf.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzd.zza.zzfe(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isAdded() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
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
            public boolean isDetached() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzajf.transact(14, obtain, obtain2, 0);
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
            public boolean isHidden() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzajf.transact(15, obtain, obtain2, 0);
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
            public boolean isInLayout() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzajf.transact(16, obtain, obtain2, 0);
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
            public boolean isRemoving() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzajf.transact(17, obtain, obtain2, 0);
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
            public boolean isResumed() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzajf.transact(18, obtain, obtain2, 0);
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
            public boolean isVisible() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzajf.transact(19, obtain, obtain2, 0);
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
            public void setHasOptionsMenu(final boolean b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    int n = 0;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.zzajf.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setMenuVisibility(final boolean b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
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
            public void setRetainInstance(final boolean b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    int n = 0;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.zzajf.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setUserVisibleHint(final boolean b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    int n = 0;
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.zzajf.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void startActivity(final Intent intent) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.zzajf.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void startActivityForResult(final Intent intent, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(n);
                    this.zzajf.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void zzac(final zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
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
            public void zzad(final zzd zzd) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    IBinder binder;
                    if (zzd != null) {
                        binder = zzd.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.zzajf.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public zzd zzbdu() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
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
            public zzc zzbdv() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzajf.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzc.zza.zzfd(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public zzd zzbdw() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzajf.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzd.zza.zzfe(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public zzc zzbdx() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zzajf.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzc.zza.zzfd(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
