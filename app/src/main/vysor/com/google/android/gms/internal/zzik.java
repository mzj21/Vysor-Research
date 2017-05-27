// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.SystemClock;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import android.content.Context;

@zziy
public abstract class zzik extends zzkm
{
    protected final Context mContext;
    protected final Object zzakd;
    protected final zzil.zza zzccj;
    protected final zzke.zza zzcck;
    protected AdResponseParcel zzccl;
    protected final Object zzccn;
    
    protected zzik(final Context mContext, final zzke.zza zzcck, final zzil.zza zzccj) {
        super(true);
        this.zzakd = new Object();
        this.zzccn = new Object();
        this.mContext = mContext;
        this.zzcck = zzcck;
        this.zzccl = zzcck.zzcop;
        this.zzccj = zzccj;
    }
    
    @Override
    public void onStop() {
    }
    
    protected abstract zzke zzam(final int p0);
    
    @Override
    public void zzfc() {
    Label_0103_Outer:
        while (true) {
            while (true) {
                int errorCode2;
                synchronized (this.zzakd) {
                    zzb.zzdd("AdRendererBackgroundTask started.");
                    int errorCode = this.zzcck.errorCode;
                    while (true) {
                        try {
                            this.zzh(SystemClock.elapsedRealtime());
                            zzkr.zzcrf.post((Runnable)new Runnable() {
                                final /* synthetic */ zzke zzamm = zzik.this.zzam(errorCode);
                                
                                @Override
                                public void run() {
                                    synchronized (zzik.this.zzakd) {
                                        zzik.this.zzn(this.zzamm);
                                    }
                                }
                            });
                            return;
                        }
                        catch (zza zza) {
                            errorCode2 = zza.getErrorCode();
                            if (errorCode2 == 3 || errorCode2 == -1) {
                                zzb.zzde(zza.getMessage());
                            }
                            else {
                                zzb.zzdf(zza.getMessage());
                            }
                            if (this.zzccl == null) {
                                this.zzccl = new AdResponseParcel(errorCode2);
                                zzkr.zzcrf.post((Runnable)new Runnable() {
                                    @Override
                                    public void run() {
                                        zzik.this.onStop();
                                    }
                                });
                                errorCode = errorCode2;
                                continue Label_0103_Outer;
                            }
                        }
                        break;
                    }
                }
                this.zzccl = new AdResponseParcel(errorCode2, this.zzccl.zzbsj);
                continue;
            }
        }
    }
    
    protected abstract void zzh(final long p0) throws zza;
    
    protected void zzn(final zzke zzke) {
        this.zzccj.zzb(zzke);
    }
    
    protected static final class zza extends Exception
    {
        private final int zzcdb;
        
        public zza(final String s, final int zzcdb) {
            super(s);
            this.zzcdb = zzcdb;
        }
        
        public int getErrorCode() {
            return this.zzcdb;
        }
    }
}
