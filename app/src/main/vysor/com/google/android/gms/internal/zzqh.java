// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import android.os.Handler;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.support.annotation.NonNull;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.api.Result;
import android.support.annotation.Nullable;
import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import java.util.Iterator;
import android.support.v4.util.ArrayMap;
import java.util.Collections;
import java.util.WeakHashMap;
import java.util.ArrayList;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.common.zzc;
import android.os.Looper;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import java.util.Set;
import com.google.android.gms.common.api.Api;
import java.util.Map;
import android.content.Context;

final class zzqh implements zzqy
{
    private final Context mContext;
    private final zzqp wV;
    private final zzqr wW;
    private final zzqr wX;
    private final Map<Api.zzc<?>, zzqr> wY;
    private final Set<zzrl> wZ;
    private final Api.zze xa;
    private Bundle xb;
    private ConnectionResult xc;
    private ConnectionResult xd;
    private boolean xe;
    private final Lock xf;
    private int xg;
    private final Looper zzajn;
    
    private zzqh(final Context mContext, final zzqp wv, final Lock xf, final Looper zzajn, final zzc zzc, final Map<Api.zzc<?>, Api.zze> map, final Map<Api.zzc<?>, Api.zze> map2, final zzh zzh, final Api.zza<? extends zzwz, zzxa> zza, final Api.zze xa, final ArrayList<zzqf> list, final ArrayList<zzqf> list2, final Map<Api<?>, Integer> map3, final Map<Api<?>, Integer> map4) {
        this.wZ = Collections.newSetFromMap(new WeakHashMap<zzrl, Boolean>());
        this.xc = null;
        this.xd = null;
        this.xe = false;
        this.xg = 0;
        this.mContext = mContext;
        this.wV = wv;
        this.xf = xf;
        this.zzajn = zzajn;
        this.xa = xa;
        this.wW = new zzqr(mContext, this.wV, xf, zzajn, zzc, map2, null, map4, null, list2, new zza());
        this.wX = new zzqr(mContext, this.wV, xf, zzajn, zzc, map, zzh, map3, zza, list, new zzb());
        final ArrayMap<Api.zzc<?>, zzqr> arrayMap = new ArrayMap<Api.zzc<?>, zzqr>();
        final Iterator<Api.zzc<?>> iterator = map2.keySet().iterator();
        while (iterator.hasNext()) {
            arrayMap.put((Api.zzc<?>)iterator.next(), this.wW);
        }
        final Iterator<Api.zzc<?>> iterator2 = map.keySet().iterator();
        while (iterator2.hasNext()) {
            arrayMap.put((Api.zzc<?>)iterator2.next(), this.wX);
        }
        this.wY = (Map<Api.zzc<?>, zzqr>)Collections.unmodifiableMap((Map<?, ?>)arrayMap);
    }
    
    public static zzqh zza(final Context context, final zzqp zzqp, final Lock lock, final Looper looper, final zzc zzc, final Map<Api.zzc<?>, Api.zze> map, final zzh zzh, final Map<Api<?>, Integer> map2, final Api.zza<? extends zzwz, zzxa> zza, final ArrayList<zzqf> list) {
        Api.zze zze = null;
        final ArrayMap<Api.zzc, Api.zze> arrayMap = new ArrayMap<Api.zzc, Api.zze>();
        final ArrayMap<Api.zzc, Api.zze> arrayMap2 = (ArrayMap<Api.zzc, Api.zze>)new ArrayMap<Api.zzc<?>, Api.zze>();
        for (final Map.Entry<Api.zzc<?>, Api.zze> entry : map.entrySet()) {
            final Api.zze zze2 = entry.getValue();
            if (zze2.zzahs()) {
                zze = zze2;
            }
            if (zze2.zzahd()) {
                arrayMap.put((Api.zzc)entry.getKey(), zze2);
            }
            else {
                arrayMap2.put((Api.zzc<?>)entry.getKey(), zze2);
            }
        }
        zzac.zza(!arrayMap.isEmpty(), (Object)"CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        final ArrayMap<Api<?>, Integer> arrayMap3 = new ArrayMap<Api<?>, Integer>();
        final ArrayMap<Api<?>, Integer> arrayMap4 = new ArrayMap<Api<?>, Integer>();
        for (final Api<?> api : map2.keySet()) {
            final Api.zzc<?> zzapp = api.zzapp();
            if (arrayMap.containsKey(zzapp)) {
                arrayMap3.put(api, map2.get(api));
            }
            else {
                if (!arrayMap2.containsKey(zzapp)) {
                    throw new IllegalStateException("Each API in the apiTypeMap must have a corresponding client in the clients map.");
                }
                arrayMap4.put(api, map2.get(api));
            }
        }
        final ArrayList<zzqf> list2 = new ArrayList<zzqf>();
        final ArrayList<zzqf> list3 = new ArrayList<zzqf>();
        for (final zzqf zzqf : list) {
            if (arrayMap3.containsKey(zzqf.tv)) {
                list2.add(zzqf);
            }
            else {
                if (!arrayMap4.containsKey(zzqf.tv)) {
                    throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the apiTypeMap");
                }
                list3.add(zzqf);
            }
        }
        return new zzqh(context, zzqp, lock, looper, zzc, (Map<Api.zzc<?>, Api.zze>)arrayMap, (Map<Api.zzc<?>, Api.zze>)arrayMap2, zzh, zza, zze, list2, list3, arrayMap3, arrayMap4);
    }
    
    private void zzara() {
        this.xd = null;
        this.xc = null;
        this.wW.connect();
        this.wX.connect();
    }
    
    private void zzarb() {
        if (zzc(this.xc)) {
            if (zzc(this.xd) || this.zzare()) {
                this.zzarc();
            }
            else if (this.xd != null) {
                if (this.xg == 1) {
                    this.zzard();
                }
                else {
                    this.zzb(this.xd);
                    this.wW.disconnect();
                }
            }
        }
        else if (this.xc != null && zzc(this.xd)) {
            this.wX.disconnect();
            this.zzb(this.xc);
        }
        else if (this.xc != null && this.xd != null) {
            ConnectionResult connectionResult = this.xc;
            if (this.wX.yo < this.wW.yo) {
                connectionResult = this.xd;
            }
            this.zzb(connectionResult);
        }
    }
    
    private void zzarc() {
        switch (this.xg) {
            default: {
                Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", (Throwable)new AssertionError());
                break;
            }
            case 2: {
                this.wV.zzn(this.xb);
            }
            case 1: {
                this.zzard();
                break;
            }
        }
        this.xg = 0;
    }
    
    private void zzard() {
        final Iterator<zzrl> iterator = this.wZ.iterator();
        while (iterator.hasNext()) {
            iterator.next().zzahr();
        }
        this.wZ.clear();
    }
    
    private boolean zzare() {
        return this.xd != null && this.xd.getErrorCode() == 4;
    }
    
    @Nullable
    private PendingIntent zzarf() {
        PendingIntent activity;
        if (this.xa == null) {
            activity = null;
        }
        else {
            activity = PendingIntent.getActivity(this.mContext, this.wV.getSessionId(), this.xa.zzaht(), 134217728);
        }
        return activity;
    }
    
    private void zzb(final int n, final boolean b) {
        this.wV.zzc(n, b);
        this.xd = null;
        this.xc = null;
    }
    
    private void zzb(final ConnectionResult connectionResult) {
        switch (this.xg) {
            default: {
                Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", (Throwable)new Exception());
                break;
            }
            case 2: {
                this.wV.zzd(connectionResult);
            }
            case 1: {
                this.zzard();
                break;
            }
        }
        this.xg = 0;
    }
    
    private static boolean zzc(final ConnectionResult connectionResult) {
        return connectionResult != null && connectionResult.isSuccess();
    }
    
    private boolean zze(final zzqc.zza<? extends Result, ? extends Api.zzb> zza) {
        final Api.zzc<? extends Api.zzb> zzapp = zza.zzapp();
        zzac.zzb(this.wY.containsKey(zzapp), (Object)"GoogleApiClient is not configured to use the API required for this call.");
        return this.wY.get(zzapp).equals(this.wX);
    }
    
    private void zzm(final Bundle xb) {
        if (this.xb == null) {
            this.xb = xb;
        }
        else if (xb != null) {
            this.xb.putAll(xb);
        }
    }
    
    @Override
    public ConnectionResult blockingConnect() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public ConnectionResult blockingConnect(final long n, @NonNull final TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void connect() {
        this.xg = 2;
        this.xe = false;
        this.zzara();
    }
    
    @Override
    public void disconnect() {
        this.xd = null;
        this.xc = null;
        this.xg = 0;
        this.wW.disconnect();
        this.wX.disconnect();
        this.zzard();
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        printWriter.append(s).append("authClient").println(":");
        this.wX.dump(String.valueOf(s).concat("  "), fileDescriptor, printWriter, array);
        printWriter.append(s).append("anonClient").println(":");
        this.wW.dump(String.valueOf(s).concat("  "), fileDescriptor, printWriter, array);
    }
    
    @Nullable
    @Override
    public ConnectionResult getConnectionResult(@NonNull final Api<?> api) {
        ConnectionResult connectionResult;
        if (this.wY.get(api.zzapp()).equals(this.wX)) {
            if (this.zzare()) {
                connectionResult = new ConnectionResult(4, this.zzarf());
            }
            else {
                connectionResult = this.wX.getConnectionResult(api);
            }
        }
        else {
            connectionResult = this.wW.getConnectionResult(api);
        }
        return connectionResult;
    }
    
    @Override
    public boolean isConnected() {
        boolean b = true;
        this.xf.lock();
        try {
            if (!this.wW.isConnected() || ((this.zzaqz() || this.zzare()) && this.xg != (b ? 1 : 0))) {
                b = false;
            }
            return b;
        }
        finally {
            this.xf.unlock();
        }
    }
    
    @Override
    public boolean isConnecting() {
        this.xf.lock();
        try {
            return this.xg == 2;
        }
        finally {
            this.xf.unlock();
        }
    }
    
    @Override
    public boolean zza(final zzrl zzrl) {
        boolean b = true;
        this.xf.lock();
        try {
            if ((this.isConnecting() || this.isConnected()) && !this.zzaqz()) {
                this.wZ.add(zzrl);
                if (this.xg == 0) {
                    this.xg = 1;
                }
                this.xd = null;
                this.wX.connect();
            }
            else {
                this.xf.unlock();
                b = false;
            }
            return b;
        }
        finally {
            this.xf.unlock();
        }
    }
    
    @Override
    public void zzaqb() {
        this.xf.lock();
        try {
            final boolean connecting = this.isConnecting();
            this.wX.disconnect();
            this.xd = new ConnectionResult(4);
            if (connecting) {
                new Handler(this.zzajn).post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        zzqh.this.xf.lock();
                        try {
                            zzqh.this.zzarb();
                        }
                        finally {
                            zzqh.this.xf.unlock();
                        }
                    }
                });
            }
            else {
                this.zzard();
            }
        }
        finally {
            this.xf.unlock();
        }
    }
    
    @Override
    public void zzaqy() {
        this.wW.zzaqy();
        this.wX.zzaqy();
    }
    
    public boolean zzaqz() {
        return this.wX.isConnected();
    }
    
    @Override
    public <A extends Api.zzb, R extends Result, T extends zzqc.zza<R, A>> T zzc(@NonNull T t) {
        if (this.zze(t)) {
            if (this.zzare()) {
                ((zzqc.zza)t).zzz(new Status(4, null, this.zzarf()));
            }
            else {
                t = (T)this.wX.zzc((zzqc.zza<R, A>)t);
            }
        }
        else {
            t = (T)this.wW.zzc((zzqc.zza<R, A>)t);
        }
        return t;
    }
    
    @Override
    public <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zzd(@NonNull T t) {
        if (this.zze(t)) {
            if (this.zzare()) {
                ((zzqc.zza)t).zzz(new Status(4, null, this.zzarf()));
            }
            else {
                t = (T)this.wX.zzd((zzqc.zza<? extends Result, A>)t);
            }
        }
        else {
            t = (T)this.wW.zzd((zzqc.zza<? extends Result, A>)t);
        }
        return t;
    }
    
    private class zza implements zzqy.zza
    {
        @Override
        public void zzc(final int n, final boolean b) {
            zzqh.this.xf.lock();
            try {
                if (zzqh.this.xe || zzqh.this.xd == null || !zzqh.this.xd.isSuccess()) {
                    zzqh.this.xe = false;
                    zzqh.this.zzb(n, b);
                }
                else {
                    zzqh.this.xe = true;
                    zzqh.this.wX.onConnectionSuspended(n);
                    zzqh.this.xf.unlock();
                }
            }
            finally {
                zzqh.this.xf.unlock();
            }
        }
        
        @Override
        public void zzd(@NonNull final ConnectionResult connectionResult) {
            zzqh.this.xf.lock();
            try {
                zzqh.this.xc = connectionResult;
                zzqh.this.zzarb();
            }
            finally {
                zzqh.this.xf.unlock();
            }
        }
        
        @Override
        public void zzn(@Nullable final Bundle bundle) {
            zzqh.this.xf.lock();
            try {
                zzqh.this.zzm(bundle);
                zzqh.this.xc = ConnectionResult.uJ;
                zzqh.this.zzarb();
            }
            finally {
                zzqh.this.xf.unlock();
            }
        }
    }
    
    private class zzb implements zzqy.zza
    {
        @Override
        public void zzc(final int n, final boolean b) {
            zzqh.this.xf.lock();
            try {
                if (zzqh.this.xe) {
                    zzqh.this.xe = false;
                    zzqh.this.zzb(n, b);
                }
                else {
                    zzqh.this.xe = true;
                    zzqh.this.wW.onConnectionSuspended(n);
                    zzqh.this.xf.unlock();
                }
            }
            finally {
                zzqh.this.xf.unlock();
            }
        }
        
        @Override
        public void zzd(@NonNull final ConnectionResult connectionResult) {
            zzqh.this.xf.lock();
            try {
                zzqh.this.xd = connectionResult;
                zzqh.this.zzarb();
            }
            finally {
                zzqh.this.xf.unlock();
            }
        }
        
        @Override
        public void zzn(@Nullable final Bundle bundle) {
            zzqh.this.xf.lock();
            try {
                zzqh.this.xd = ConnectionResult.uJ;
                zzqh.this.zzarb();
            }
            finally {
                zzqh.this.xf.unlock();
            }
        }
    }
}
