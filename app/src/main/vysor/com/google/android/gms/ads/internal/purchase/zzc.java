// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.purchase;

import java.util.ArrayList;
import android.os.Bundle;
import java.util.Iterator;
import java.util.HashMap;
import android.os.RemoteException;
import com.google.android.gms.internal.zzia;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.ads.internal.zzu;
import android.content.Intent;
import android.os.IBinder;
import android.content.ComponentName;
import android.os.SystemClock;
import com.google.android.gms.internal.zzkn;
import java.util.List;
import com.google.android.gms.internal.zzib;
import android.content.Context;
import com.google.android.gms.internal.zziy;
import android.content.ServiceConnection;
import com.google.android.gms.internal.zzkm;

@zziy
public class zzc extends zzkm implements ServiceConnection
{
    private Context mContext;
    private final Object zzakd;
    private zzib zzbpt;
    private boolean zzcbk;
    private zzb zzcbl;
    private zzh zzcbm;
    private List<zzf> zzcbn;
    private zzk zzcbo;
    
    public zzc(final Context context, final zzib zzib, final zzk zzk) {
        this(context, zzib, zzk, new zzb(context), zzh.zzs(context.getApplicationContext()));
    }
    
    zzc(final Context mContext, final zzib zzbpt, final zzk zzcbo, final zzb zzcbl, final zzh zzcbm) {
        this.zzakd = new Object();
        this.zzcbk = false;
        this.zzcbn = null;
        this.mContext = mContext;
        this.zzbpt = zzbpt;
        this.zzcbo = zzcbo;
        this.zzcbl = zzcbl;
        this.zzcbm = zzcbm;
        this.zzcbn = this.zzcbm.zzg(10L);
    }
    
    private void zze(final long n) {
        do {
            if (!this.zzf(n)) {
                zzkn.v("Timeout waiting for pending transaction to be processed.");
            }
        } while (!this.zzcbk);
    }
    
    private boolean zzf(final long n) {
        final long n2 = 60000L - (SystemClock.elapsedRealtime() - n);
        boolean b;
        if (n2 <= 0L) {
            b = false;
        }
        else {
            while (true) {
                try {
                    this.zzakd.wait(n2);
                    b = true;
                }
                catch (InterruptedException ex) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzdf("waitWithTimeout_lock interrupted");
                    continue;
                }
                break;
            }
        }
        return b;
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        synchronized (this.zzakd) {
            this.zzcbl.zzav(binder);
            this.zzqo();
            this.zzcbk = true;
            this.zzakd.notify();
        }
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        com.google.android.gms.ads.internal.util.client.zzb.zzde("In-app billing service disconnected.");
        this.zzcbl.destroy();
    }
    
    @Override
    public void onStop() {
        synchronized (this.zzakd) {
            com.google.android.gms.common.stats.zzb.zzawu().zza(this.mContext, (ServiceConnection)this);
            this.zzcbl.destroy();
        }
    }
    
    protected void zza(final zzf zzf, final String s, final String s2) {
        final Intent intent = new Intent();
        zzu.zzgn();
        intent.putExtra("RESPONSE_CODE", 0);
        zzu.zzgn();
        intent.putExtra("INAPP_PURCHASE_DATA", s);
        zzu.zzgn();
        intent.putExtra("INAPP_DATA_SIGNATURE", s2);
        zzkr.zzcrf.post((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    if (zzc.this.zzcbo.zza(zzf.zzcbz, -1, intent)) {
                        zzc.this.zzbpt.zza(new zzg(zzc.this.mContext, zzf.zzcca, true, -1, intent, zzf));
                    }
                    else {
                        zzc.this.zzbpt.zza(new zzg(zzc.this.mContext, zzf.zzcca, false, -1, intent, zzf));
                    }
                }
                catch (RemoteException ex) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzdf("Fail to verify and dispatch pending transaction");
                }
            }
        });
    }
    
    @Override
    public void zzfc() {
        synchronized (this.zzakd) {
            final Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            intent.setPackage("com.android.vending");
            com.google.android.gms.common.stats.zzb.zzawu().zza(this.mContext, intent, (ServiceConnection)this, 1);
            this.zze(SystemClock.elapsedRealtime());
            com.google.android.gms.common.stats.zzb.zzawu().zza(this.mContext, (ServiceConnection)this);
            this.zzcbl.destroy();
        }
    }
    
    protected void zzqo() {
        if (!this.zzcbn.isEmpty()) {
            final HashMap<String, zzf> hashMap = new HashMap<String, zzf>();
            for (final zzf zzf : this.zzcbn) {
                hashMap.put(zzf.zzcca, zzf);
            }
            String s = null;
            while (true) {
                final Bundle zzn = this.zzcbl.zzn(this.mContext.getPackageName(), s);
                if (zzn == null || zzu.zzgn().zzd(zzn) != 0) {
                    break;
                }
                final ArrayList stringArrayList = zzn.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                final ArrayList stringArrayList2 = zzn.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                final ArrayList stringArrayList3 = zzn.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                final String string = zzn.getString("INAPP_CONTINUATION_TOKEN");
                for (int i = 0; i < stringArrayList.size(); ++i) {
                    if (hashMap.containsKey(stringArrayList.get(i))) {
                        final String s2 = stringArrayList.get(i);
                        final String s3 = stringArrayList2.get(i);
                        final String s4 = stringArrayList3.get(i);
                        final zzf zzf2 = hashMap.get(s2);
                        if (zzf2.zzcbz.equals(zzu.zzgn().zzcc(s3))) {
                            this.zza(zzf2, s3, s4);
                            hashMap.remove(s2);
                        }
                    }
                }
                if (string == null || hashMap.isEmpty()) {
                    break;
                }
                s = string;
            }
            final Iterator<String> iterator2 = hashMap.keySet().iterator();
            while (iterator2.hasNext()) {
                this.zzcbm.zza(hashMap.get(iterator2.next()));
            }
        }
    }
}
