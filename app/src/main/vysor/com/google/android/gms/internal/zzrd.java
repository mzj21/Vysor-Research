// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Message;
import android.os.Handler;
import com.google.android.gms.common.internal.zzac;
import android.support.annotation.NonNull;
import android.os.Looper;

public final class zzrd<L>
{
    private volatile L mListener;
    private final zza ze;
    private final zzb<L> zf;
    
    zzrd(@NonNull final Looper looper, @NonNull final L l, @NonNull final String s) {
        this.ze = new zza(looper);
        this.mListener = zzac.zzb(l, "Listener must not be null");
        this.zf = new zzb<L>((Object)l, zzac.zzhz(s));
    }
    
    public void clear() {
        this.mListener = null;
    }
    
    public void zza(final zzc<? super L> zzc) {
        zzac.zzb(zzc, "Notifier must not be null");
        this.ze.sendMessage(this.ze.obtainMessage(1, (Object)zzc));
    }
    
    @NonNull
    public zzb<L> zzasr() {
        return this.zf;
    }
    
    void zzb(final zzc<? super L> zzc) {
        final L mListener = this.mListener;
        if (mListener == null) {
            zzc.zzarg();
        }
        else {
            try {
                zzc.zzt(mListener);
            }
            catch (RuntimeException ex) {
                zzc.zzarg();
                throw ex;
            }
        }
    }
    
    private final class zza extends Handler
    {
        public zza(final Looper looper) {
            super(looper);
        }
        
        public void handleMessage(final Message message) {
            boolean b = true;
            if (message.what != (b ? 1 : 0)) {
                b = false;
            }
            zzac.zzbs(b);
            zzrd.this.zzb((zzc<? super L>)message.obj);
        }
    }
    
    public static final class zzb<L>
    {
        private final L mListener;
        private final String zh;
        
        private zzb(final L mListener, final String zh) {
            this.mListener = mListener;
            this.zh = zh;
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean b = true;
            if (this != o) {
                if (!(o instanceof zzb)) {
                    b = false;
                }
                else {
                    final zzb zzb = (zzb)o;
                    if (this.mListener != zzb.mListener || !this.zh.equals(zzb.zh)) {
                        b = false;
                    }
                }
            }
            return b;
        }
        
        @Override
        public int hashCode() {
            return 31 * System.identityHashCode(this.mListener) + this.zh.hashCode();
        }
    }
    
    public interface zzc<L>
    {
        void zzarg();
        
        void zzt(final L p0);
    }
}
