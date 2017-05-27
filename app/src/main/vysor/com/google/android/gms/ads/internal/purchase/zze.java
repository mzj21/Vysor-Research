// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.purchase;

import android.os.Bundle;
import android.content.IntentSender$SendIntentException;
import android.app.PendingIntent;
import android.os.IBinder;
import android.content.ComponentName;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzu;
import android.content.Intent;
import com.google.android.gms.internal.zzhw;
import android.content.Context;
import android.app.Activity;
import com.google.android.gms.internal.zziy;
import android.content.ServiceConnection;
import com.google.android.gms.internal.zzhy;

@zziy
public class zze extends zzhy.zza implements ServiceConnection
{
    private final Activity mActivity;
    private zzb zzcbl;
    zzh zzcbm;
    private zzk zzcbo;
    private Context zzcbt;
    private zzhw zzcbu;
    private zzf zzcbv;
    private zzj zzcbw;
    private String zzcbx;
    
    public zze(final Activity mActivity) {
        this.zzcbx = null;
        this.mActivity = mActivity;
        this.zzcbm = zzh.zzs(this.mActivity.getApplicationContext());
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        if (n != 1001) {
            return;
        }
        try {
            final int zzd = zzu.zzgn().zzd(intent);
            while (true) {
                Label_0097: {
                    if (n2 != -1) {
                        break Label_0097;
                    }
                    zzu.zzgn();
                    if (zzd != 0) {
                        break Label_0097;
                    }
                    final boolean zza = this.zzcbo.zza(this.zzcbx, n2, intent);
                    boolean b = false;
                    if (zza) {
                        b = true;
                    }
                    this.zzcbu.recordPlayBillingResolution(zzd);
                    this.mActivity.finish();
                    this.zza(this.zzcbu.getProductId(), b, n2, intent);
                    return;
                }
                this.zzcbm.zza(this.zzcbv);
                boolean b = false;
                continue;
            }
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("Fail to process purchase result.");
            this.mActivity.finish();
            this.zzcbx = null;
        }
        finally {
            this.zzcbx = null;
        }
    }
    
    public void onCreate() {
        final GInAppPurchaseManagerInfoParcel zzc = GInAppPurchaseManagerInfoParcel.zzc(this.mActivity.getIntent());
        this.zzcbw = zzc.zzcbh;
        this.zzcbo = zzc.zzars;
        this.zzcbu = zzc.zzcbf;
        this.zzcbl = new zzb(this.mActivity.getApplicationContext());
        this.zzcbt = zzc.zzcbg;
        if (this.mActivity.getResources().getConfiguration().orientation == 2) {
            this.mActivity.setRequestedOrientation(zzu.zzgb().zzum());
        }
        else {
            this.mActivity.setRequestedOrientation(zzu.zzgb().zzun());
        }
        final Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        this.mActivity.bindService(intent, (ServiceConnection)this, 1);
    }
    
    public void onDestroy() {
        this.mActivity.unbindService((ServiceConnection)this);
        this.zzcbl.destroy();
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        this.zzcbl.zzav(binder);
        try {
            this.zzcbx = this.zzcbo.zzqs();
            final Bundle zzb = this.zzcbl.zzb(this.mActivity.getPackageName(), this.zzcbu.getProductId(), this.zzcbx);
            final PendingIntent pendingIntent = (PendingIntent)zzb.getParcelable("BUY_INTENT");
            if (pendingIntent == null) {
                final int zzd = zzu.zzgn().zzd(zzb);
                this.zzcbu.recordPlayBillingResolution(zzd);
                this.zza(this.zzcbu.getProductId(), false, zzd, null);
                this.mActivity.finish();
            }
            else {
                this.zzcbv = new zzf(this.zzcbu.getProductId(), this.zzcbx);
                this.zzcbm.zzb(this.zzcbv);
                this.mActivity.startIntentSenderForResult(pendingIntent.getIntentSender(), 1001, new Intent(), (int)Integer.valueOf(0), (int)Integer.valueOf(0), (int)Integer.valueOf(0));
            }
        }
        catch (RemoteException ex) {}
        catch (IntentSender$SendIntentException ex2) {
            goto Label_0195;
        }
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        com.google.android.gms.ads.internal.util.client.zzb.zzde("In-app billing service disconnected.");
        this.zzcbl.destroy();
    }
    
    protected void zza(final String s, final boolean b, final int n, final Intent intent) {
        if (this.zzcbw != null) {
            this.zzcbw.zza(s, b, n, intent, this.zzcbv);
        }
    }
}
