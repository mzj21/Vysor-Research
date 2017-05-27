// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.purchase;

import com.google.android.gms.internal.zzkh;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import org.json.JSONException;
import com.google.android.gms.ads.internal.util.client.zzb;
import org.json.JSONObject;
import com.google.android.gms.ads.internal.zzu;
import android.content.Intent;
import android.content.Context;
import com.google.android.gms.internal.zziy;

@zziy
public class zzi
{
    public void zza(final Context context, final boolean b, final GInAppPurchaseManagerInfoParcel gInAppPurchaseManagerInfoParcel) {
        final Intent intent = new Intent();
        intent.setClassName(context, "com.google.android.gms.ads.purchase.InAppPurchaseActivity");
        intent.putExtra("com.google.android.gms.ads.internal.purchase.useClientJar", b);
        GInAppPurchaseManagerInfoParcel.zza(intent, gInAppPurchaseManagerInfoParcel);
        zzu.zzfz().zzb(context, intent);
    }
    
    public String zzcc(final String s) {
        String string = null;
        if (s != null) {
            try {
                string = new JSONObject(s).getString("developerPayload");
            }
            catch (JSONException ex) {
                zzb.zzdf("Fail to parse purchase data");
                string = null;
            }
        }
        return string;
    }
    
    public String zzcd(final String s) {
        String string = null;
        if (s != null) {
            try {
                string = new JSONObject(s).getString("purchaseToken");
            }
            catch (JSONException ex) {
                zzb.zzdf("Fail to parse purchase data");
                string = null;
            }
        }
        return string;
    }
    
    public int zzd(final Intent intent) {
        int intValue;
        if (intent == null) {
            intValue = 5;
        }
        else {
            final Object value = intent.getExtras().get("RESPONSE_CODE");
            if (value == null) {
                zzb.zzdf("Intent with no response code, assuming OK (known issue)");
                intValue = 0;
            }
            else if (value instanceof Integer) {
                intValue = (int)value;
            }
            else if (value instanceof Long) {
                intValue = (int)(long)value;
            }
            else {
                final String value2 = String.valueOf(((Long)value).getClass().getName());
                String concat;
                if (value2.length() != 0) {
                    concat = "Unexpected type for intent response code. ".concat(value2);
                }
                else {
                    concat = new String("Unexpected type for intent response code. ");
                }
                zzb.zzdf(concat);
                intValue = 5;
            }
        }
        return intValue;
    }
    
    public int zzd(final Bundle bundle) {
        final Object value = bundle.get("RESPONSE_CODE");
        int intValue;
        if (value == null) {
            zzb.zzdf("Bundle with null response code, assuming OK (known issue)");
            intValue = 0;
        }
        else if (value instanceof Integer) {
            intValue = (int)value;
        }
        else if (value instanceof Long) {
            intValue = (int)(long)value;
        }
        else {
            final String value2 = String.valueOf(((Long)value).getClass().getName());
            String concat;
            if (value2.length() != 0) {
                concat = "Unexpected type for intent response code. ".concat(value2);
            }
            else {
                concat = new String("Unexpected type for intent response code. ");
            }
            zzb.zzdf(concat);
            intValue = 5;
        }
        return intValue;
    }
    
    public String zze(final Intent intent) {
        String stringExtra;
        if (intent == null) {
            stringExtra = null;
        }
        else {
            stringExtra = intent.getStringExtra("INAPP_PURCHASE_DATA");
        }
        return stringExtra;
    }
    
    public String zzf(final Intent intent) {
        String stringExtra;
        if (intent == null) {
            stringExtra = null;
        }
        else {
            stringExtra = intent.getStringExtra("INAPP_DATA_SIGNATURE");
        }
        return stringExtra;
    }
    
    public void zzt(final Context context) {
        final ServiceConnection serviceConnection = (ServiceConnection)new ServiceConnection() {
            public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
                final com.google.android.gms.ads.internal.purchase.zzb zzb = new com.google.android.gms.ads.internal.purchase.zzb(context.getApplicationContext(), false);
                zzb.zzav(binder);
                final int zzb2 = zzb.zzb(3, context.getPackageName(), "inapp");
                final zzkh zzgd = zzu.zzgd();
                boolean b = false;
                if (zzb2 == 0) {
                    b = true;
                }
                zzgd.zzah(b);
                context.unbindService((ServiceConnection)this);
                zzb.destroy();
            }
            
            public void onServiceDisconnected(final ComponentName componentName) {
            }
        };
        final Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        context.bindService(intent, (ServiceConnection)serviceConnection, 1);
    }
}
