// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.purchase;

import com.google.android.gms.ads.internal.client.zzm;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.content.Intent;
import com.google.android.gms.internal.zzhy;
import android.app.Activity;

public class InAppPurchaseActivity extends Activity
{
    public static final String CLASS_NAME = "com.google.android.gms.ads.purchase.InAppPurchaseActivity";
    public static final String SIMPLE_CLASS_NAME = "InAppPurchaseActivity";
    private zzhy zzcxo;
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        while (true) {
            try {
                if (this.zzcxo != null) {
                    this.zzcxo.onActivityResult(n, n2, intent);
                }
                super.onActivityResult(n, n2, intent);
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not forward onActivityResult to in-app purchase manager:", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.zzcxo = zzm.zzjs().zzb(this);
        if (this.zzcxo == null) {
            zzb.zzdf("Could not create in-app purchase manager.");
            this.finish();
        }
        else {
            try {
                this.zzcxo.onCreate();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not forward onCreate to in-app purchase manager:", (Throwable)ex);
                this.finish();
            }
        }
    }
    
    protected void onDestroy() {
        while (true) {
            try {
                if (this.zzcxo != null) {
                    this.zzcxo.onDestroy();
                }
                super.onDestroy();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not forward onDestroy to in-app purchase manager:", (Throwable)ex);
                continue;
            }
            break;
        }
    }
}
