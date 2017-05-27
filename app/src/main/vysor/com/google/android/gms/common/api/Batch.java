// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.internal.zzqe;

public final class Batch extends zzqe<BatchResult>
{
    private int vo;
    private boolean vp;
    private boolean vq;
    private final PendingResult<?>[] vr;
    private final Object zzakd;
    
    private Batch(final List<PendingResult<?>> list, final GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzakd = new Object();
        this.vo = list.size();
        this.vr = (PendingResult<?>[])new PendingResult[this.vo];
        if (list.isEmpty()) {
            this.zzc(new BatchResult(Status.vY, this.vr));
        }
        else {
            for (int i = 0; i < list.size(); ++i) {
                (this.vr[i] = list.get(i)).zza((PendingResult.zza)new PendingResult.zza() {
                    @Override
                    public void zzv(final Status status) {
                    Label_0076_Outer:
                        while (true) {
                            while (true) {
                            Label_0105:
                                while (true) {
                                    synchronized (Batch.this.zzakd) {
                                        if (Batch.this.isCanceled()) {
                                            break;
                                        }
                                        if (status.isCanceled()) {
                                            Batch.this.vq = true;
                                            Batch.this.vo--;
                                            if (Batch.this.vo == 0) {
                                                if (!Batch.this.vq) {
                                                    break Label_0105;
                                                }
                                                Batch.this.cancel();
                                            }
                                            break;
                                        }
                                    }
                                    if (!status.isSuccess()) {
                                        Batch.this.vp = true;
                                        continue Label_0076_Outer;
                                    }
                                    continue Label_0076_Outer;
                                }
                                Status vy;
                                if (Batch.this.vp) {
                                    vy = new Status(13);
                                }
                                else {
                                    vy = Status.vY;
                                }
                                Batch.this.zzc(new BatchResult(vy, Batch.this.vr));
                                continue;
                            }
                        }
                    }
                });
            }
        }
    }
    
    @Override
    public void cancel() {
        super.cancel();
        final PendingResult<?>[] vr = this.vr;
        for (int length = vr.length, i = 0; i < length; ++i) {
            vr[i].cancel();
        }
    }
    
    public BatchResult createFailedResult(final Status status) {
        return new BatchResult(status, this.vr);
    }
    
    public static final class Builder
    {
        private GoogleApiClient kv;
        private List<PendingResult<?>> vt;
        
        public Builder(final GoogleApiClient kv) {
            this.vt = new ArrayList<PendingResult<?>>();
            this.kv = kv;
        }
        
        public <R extends Result> BatchResultToken<R> add(final PendingResult<R> pendingResult) {
            final BatchResultToken<R> batchResultToken = new BatchResultToken<R>(this.vt.size());
            this.vt.add(pendingResult);
            return batchResultToken;
        }
        
        public Batch build() {
            return new Batch(this.vt, this.kv, null);
        }
    }
}
