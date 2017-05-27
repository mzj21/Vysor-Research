// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.api.GoogleApiClient;
import android.util.Log;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.util.SparseArray;

public class zzqa extends zzqd
{
    private final SparseArray<zza> wq;
    
    private zzqa(final zzrb zzrb) {
        super(zzrb);
        this.wq = (SparseArray<zza>)new SparseArray();
        this.yY.zza("AutoManageHelper", this);
    }
    
    public static zzqa zza(final zzqz zzqz) {
        final zzrb zzc = zzra.zzc(zzqz);
        zzqa zzqa = zzc.zza("AutoManageHelper", zzqa.class);
        if (zzqa == null) {
            zzqa = new zzqa(zzc);
        }
        return zzqa;
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        for (int i = 0; i < this.wq.size(); ++i) {
            ((zza)this.wq.valueAt(i)).dump(s, fileDescriptor, printWriter, array);
        }
    }
    
    @Override
    public void onStart() {
        super.onStart();
        final boolean mStarted = this.mStarted;
        final String value = String.valueOf(this.wq);
        Log.d("AutoManageHelper", new StringBuilder(14 + String.valueOf(value).length()).append("onStart ").append(mStarted).append(" ").append(value).toString());
        if (!this.wy) {
            for (int i = 0; i < this.wq.size(); ++i) {
                ((zza)this.wq.valueAt(i)).ws.connect();
            }
        }
    }
    
    @Override
    public void onStop() {
        super.onStop();
        for (int i = 0; i < this.wq.size(); ++i) {
            ((zza)this.wq.valueAt(i)).ws.disconnect();
        }
    }
    
    public void zza(final int n, final GoogleApiClient googleApiClient, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzac.zzb(googleApiClient, "GoogleApiClient instance cannot be null");
        zzac.zza(this.wq.indexOfKey(n) < 0, (Object)new StringBuilder(54).append("Already managing a GoogleApiClient with id ").append(n).toString());
        Log.d("AutoManageHelper", new StringBuilder(54).append("starting AutoManage for client ").append(n).append(" ").append(this.mStarted).append(" ").append(this.wy).toString());
        this.wq.put(n, (Object)new zza(n, googleApiClient, onConnectionFailedListener));
        if (this.mStarted && !this.wy) {
            final String value = String.valueOf(googleApiClient);
            Log.d("AutoManageHelper", new StringBuilder(11 + String.valueOf(value).length()).append("connecting ").append(value).toString());
            googleApiClient.connect();
        }
    }
    
    @Override
    protected void zza(final ConnectionResult connectionResult, final int n) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (n < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", (Throwable)new Exception());
        }
        else {
            final zza zza = (zza)this.wq.get(n);
            if (zza != null) {
                this.zzfq(n);
                final GoogleApiClient.OnConnectionFailedListener wt = zza.wt;
                if (wt != null) {
                    wt.onConnectionFailed(connectionResult);
                }
            }
        }
    }
    
    @Override
    protected void zzaqk() {
        for (int i = 0; i < this.wq.size(); ++i) {
            ((zza)this.wq.valueAt(i)).ws.connect();
        }
    }
    
    public void zzfq(final int n) {
        final zza zza = (zza)this.wq.get(n);
        this.wq.remove(n);
        if (zza != null) {
            zza.zzaql();
        }
    }
    
    private class zza implements OnConnectionFailedListener
    {
        public final int wr;
        public final GoogleApiClient ws;
        public final OnConnectionFailedListener wt;
        
        public zza(final int wr, final GoogleApiClient ws, final OnConnectionFailedListener wt) {
            this.wr = wr;
            this.ws = ws;
            this.wt = wt;
            ws.registerConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener)this);
        }
        
        public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
            printWriter.append(s).append("GoogleApiClient #").print(this.wr);
            printWriter.println(":");
            this.ws.dump(String.valueOf(s).concat("  "), fileDescriptor, printWriter, array);
        }
        
        @Override
        public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
            final String value = String.valueOf(connectionResult);
            Log.d("AutoManageHelper", new StringBuilder(27 + String.valueOf(value).length()).append("beginFailureResolution for ").append(value).toString());
            zzqa.this.zzb(connectionResult, this.wr);
        }
        
        public void zzaql() {
            this.ws.unregisterConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener)this);
            this.ws.disconnect();
        }
    }
}
