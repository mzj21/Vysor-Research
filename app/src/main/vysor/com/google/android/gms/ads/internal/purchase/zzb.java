// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.purchase;

import android.os.Bundle;
import android.os.IBinder;
import android.content.Context;
import com.google.android.gms.internal.zziy;

@zziy
public class zzb
{
    private final Context mContext;
    Object zzcbi;
    private final boolean zzcbj;
    
    public zzb(final Context context) {
        this(context, true);
    }
    
    public zzb(final Context mContext, final boolean zzcbj) {
        this.mContext = mContext;
        this.zzcbj = zzcbj;
    }
    
    public void destroy() {
        this.zzcbi = null;
    }
    
    public void zzav(final IBinder binder) {
        try {
            this.zzcbi = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService$Stub").getDeclaredMethod("asInterface", IBinder.class).invoke(null, binder);
        }
        catch (Exception ex) {
            if (this.zzcbj) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.");
            }
        }
    }
    
    public int zzb(final int n, final String s, final String s2) {
        try {
            final Class<?> loadClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
            return (int)loadClass.getDeclaredMethod("isBillingSupported", Integer.TYPE, String.class, String.class).invoke(loadClass.cast(this.zzcbi), n, s, s2);
        }
        catch (Exception ex) {
            if (this.zzcbj) {
                com.google.android.gms.ads.internal.util.client.zzb.zzd("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", ex);
            }
            return 5;
        }
    }
    
    public Bundle zzb(final String s, final String s2, final String s3) {
        try {
            final Class<?> loadClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
            return (Bundle)loadClass.getDeclaredMethod("getBuyIntent", Integer.TYPE, String.class, String.class, String.class, String.class).invoke(loadClass.cast(this.zzcbi), 3, s, s2, "inapp", s3);
        }
        catch (Exception ex) {
            if (this.zzcbj) {
                com.google.android.gms.ads.internal.util.client.zzb.zzd("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", ex);
            }
            return null;
        }
    }
    
    public int zzm(final String s, final String s2) {
        try {
            final Class<?> loadClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
            return (int)loadClass.getDeclaredMethod("consumePurchase", Integer.TYPE, String.class, String.class).invoke(loadClass.cast(this.zzcbi), 3, s, s2);
        }
        catch (Exception ex) {
            if (this.zzcbj) {
                com.google.android.gms.ads.internal.util.client.zzb.zzd("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", ex);
            }
            return 5;
        }
    }
    
    public Bundle zzn(final String s, final String s2) {
        try {
            final Class<?> loadClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
            return (Bundle)loadClass.getDeclaredMethod("getPurchases", Integer.TYPE, String.class, String.class, String.class).invoke(loadClass.cast(this.zzcbi), 3, s, "inapp", s2);
        }
        catch (Exception ex) {
            if (this.zzcbj) {
                com.google.android.gms.ads.internal.util.client.zzb.zzd("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", ex);
            }
            return null;
        }
    }
}
