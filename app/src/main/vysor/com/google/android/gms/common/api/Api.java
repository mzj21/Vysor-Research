// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.IInterface;
import android.support.annotation.Nullable;
import android.os.IBinder;
import android.content.Intent;
import java.util.Set;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.internal.zze;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import java.util.Collections;
import java.util.List;
import com.google.android.gms.common.internal.zzh;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.internal.zzac;

public final class Api<O extends ApiOptions>
{
    private final String mName;
    private final zza<?, O> vi;
    private final zzh<?, O> vj;
    private final zzf<?> vk;
    private final zzi<?> vl;
    
    public Api(final String mName, final zza<C, O> vi, final zzf<C> vk) {
        zzac.zzb(vi, "Cannot construct an Api with a null ClientBuilder");
        zzac.zzb(vk, "Cannot construct an Api with a null ClientKey");
        this.mName = mName;
        this.vi = vi;
        this.vj = null;
        this.vk = vk;
        this.vl = null;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public zzd<?, O> zzapm() {
        Object vi;
        if (this.zzapq()) {
            vi = null;
        }
        else {
            vi = this.vi;
        }
        return (zzd<?, O>)vi;
    }
    
    public zza<?, O> zzapn() {
        zzac.zza(this.vi != null, (Object)"This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
        return this.vi;
    }
    
    public zzh<?, O> zzapo() {
        zzac.zza(false, (Object)"This API was constructed with a ClientBuilder. Use getClientBuilder");
        return null;
    }
    
    public zzc<?> zzapp() {
        if (this.vk != null) {
            return (zzc<?>)this.vk;
        }
        throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
    }
    
    public boolean zzapq() {
        return false;
    }
    
    public interface ApiOptions
    {
        public interface HasOptions extends ApiOptions
        {
        }
        
        public static final class NoOptions implements NotRequiredOptions
        {
        }
        
        public interface NotRequiredOptions extends ApiOptions
        {
        }
        
        public interface Optional extends HasOptions, NotRequiredOptions
        {
        }
    }
    
    public abstract static class zza<T extends zze, O> extends zzd<T, O>
    {
        public abstract T zza(final Context p0, final Looper p1, final com.google.android.gms.common.internal.zzh p2, final O p3, final GoogleApiClient.ConnectionCallbacks p4, final GoogleApiClient.OnConnectionFailedListener p5);
    }
    
    public interface zzb
    {
    }
    
    public static class zzc<C extends zzb>
    {
    }
    
    public abstract static class zzd<T extends zzb, O>
    {
        public int getPriority() {
            return Integer.MAX_VALUE;
        }
        
        public List<Scope> zzp(final O o) {
            return Collections.emptyList();
        }
    }
    
    public interface zze extends zzb
    {
        void disconnect();
        
        void dump(final String p0, final FileDescriptor p1, final PrintWriter p2, final String[] p3);
        
        boolean isConnected();
        
        boolean isConnecting();
        
        void zza(final com.google.android.gms.common.internal.zze.zzf p0);
        
        void zza(final zzr p0, final Set<Scope> p1);
        
        boolean zzahd();
        
        boolean zzahs();
        
        Intent zzaht();
        
        boolean zzapr();
        
        @Nullable
        IBinder zzaps();
    }
    
    public static final class zzf<C extends zze> extends zzc<C>
    {
    }
    
    public interface zzg<T extends IInterface> extends zzb
    {
        void zza(final int p0, final T p1);
        
        T zzh(final IBinder p0);
        
        String zzix();
        
        String zziy();
    }
    
    public abstract static class zzh<T extends zzg, O> extends zzd<T, O>
    {
        public abstract int zzapt();
        
        public abstract T zzr(final O p0);
    }
    
    public static final class zzi<C extends zzg> extends zzc<C>
    {
    }
}
