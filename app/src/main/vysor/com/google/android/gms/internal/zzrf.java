// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.CancellationException;
import com.google.android.gms.tasks.Task;
import android.app.Activity;
import com.google.android.gms.tasks.TaskCompletionSource;

public class zzrf extends zzqd
{
    private TaskCompletionSource<Void> wh;
    
    private zzrf(final zzrb zzrb) {
        super(zzrb);
        this.wh = new TaskCompletionSource<Void>();
        this.yY.zza("GmsAvailabilityHelper", this);
    }
    
    public static zzrf zzu(final Activity activity) {
        final zzrb zzs = zzra.zzs(activity);
        zzrf zzrf = zzs.zza("GmsAvailabilityHelper", zzrf.class);
        if (zzrf != null) {
            if (zzrf.wh.getTask().isComplete()) {
                zzrf.wh = new TaskCompletionSource<Void>();
            }
        }
        else {
            zzrf = new zzrf(zzs);
        }
        return zzrf;
    }
    
    public Task<Void> getTask() {
        return this.wh.getTask();
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.wh.setException(new CancellationException("Host activity was destroyed before Google Play services could be made available."));
    }
    
    @Override
    protected void zza(final ConnectionResult connectionResult, final int n) {
        this.wh.setException(zzb.zzl(connectionResult));
    }
    
    @Override
    protected void zzaqk() {
        final int googlePlayServicesAvailable = this.vP.isGooglePlayServicesAvailable((Context)this.yY.zzasq());
        if (googlePlayServicesAvailable == 0) {
            this.wh.setResult(null);
        }
        else {
            this.zzk(new ConnectionResult(googlePlayServicesAvailable, null));
        }
    }
    
    public void zzk(final ConnectionResult connectionResult) {
        this.zzb(connectionResult, 0);
    }
}
