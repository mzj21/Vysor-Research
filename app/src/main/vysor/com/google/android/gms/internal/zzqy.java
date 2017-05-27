// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.Result;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.ConnectionResult;

public interface zzqy
{
    ConnectionResult blockingConnect();
    
    ConnectionResult blockingConnect(final long p0, final TimeUnit p1);
    
    void connect();
    
    void disconnect();
    
    void dump(final String p0, final FileDescriptor p1, final PrintWriter p2, final String[] p3);
    
    @Nullable
    ConnectionResult getConnectionResult(@NonNull final Api<?> p0);
    
    boolean isConnected();
    
    boolean isConnecting();
    
    boolean zza(final zzrl p0);
    
    void zzaqb();
    
    void zzaqy();
    
     <A extends Api.zzb, R extends Result, T extends zzqc.zza<R, A>> T zzc(@NonNull final T p0);
    
     <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zzd(@NonNull final T p0);
    
    public interface zza
    {
        void zzc(final int p0, final boolean p1);
        
        void zzd(final ConnectionResult p0);
        
        void zzn(final Bundle p0);
    }
}
