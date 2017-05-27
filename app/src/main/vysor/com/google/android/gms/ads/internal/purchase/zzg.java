// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.purchase;

import android.os.IBinder;
import android.content.ComponentName;
import com.google.android.gms.ads.internal.zzu;
import android.content.Intent;
import android.content.Context;
import com.google.android.gms.internal.zziy;
import android.content.ServiceConnection;
import com.google.android.gms.internal.zzia;

@zziy
public final class zzg extends zzia.zza implements ServiceConnection
{
    private Context mContext;
    private int mResultCode;
    zzb zzcbl;
    private String zzcbr;
    private zzf zzcbv;
    private boolean zzccb;
    private Intent zzccc;
    
    public zzg(final Context mContext, final String zzcbr, final boolean zzccb, final int mResultCode, final Intent zzccc, final zzf zzcbv) {
        this.zzccb = false;
        this.zzcbr = zzcbr;
        this.mResultCode = mResultCode;
        this.zzccc = zzccc;
        this.zzccb = zzccb;
        this.mContext = mContext;
        this.zzcbv = zzcbv;
    }
    
    public void finishPurchase() {
        final int zzd = zzu.zzgn().zzd(this.zzccc);
        if (this.mResultCode == -1 && zzd == 0) {
            this.zzcbl = new zzb(this.mContext);
            final Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            intent.setPackage("com.android.vending");
            com.google.android.gms.common.stats.zzb.zzawu().zza(this.mContext, intent, (ServiceConnection)this, 1);
        }
    }
    
    public String getProductId() {
        return this.zzcbr;
    }
    
    public Intent getPurchaseData() {
        return this.zzccc;
    }
    
    public int getResultCode() {
        return this.mResultCode;
    }
    
    public boolean isVerified() {
        return this.zzccb;
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        com.google.android.gms.ads.internal.util.client.zzb.zzde("In-app billing service connected.");
        this.zzcbl.zzav(binder);
        final String zzcd = zzu.zzgn().zzcd(zzu.zzgn().zze(this.zzccc));
        if (zzcd != null) {
            if (this.zzcbl.zzm(this.mContext.getPackageName(), zzcd) == 0) {
                zzh.zzs(this.mContext).zza(this.zzcbv);
            }
            com.google.android.gms.common.stats.zzb.zzawu().zza(this.mContext, (ServiceConnection)this);
            this.zzcbl.destroy();
        }
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        com.google.android.gms.ads.internal.util.client.zzb.zzde("In-app billing service disconnected.");
        this.zzcbl.destroy();
    }
}
