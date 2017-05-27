// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.support.customtabs.CustomTabsCallback;
import android.content.ServiceConnection;
import android.app.Activity;
import android.os.Bundle;
import java.util.List;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import android.net.Uri;
import android.content.Context;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsClient;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsSession;

@zziy
public class zzdw implements zzars
{
    @Nullable
    private CustomTabsSession zzbiy;
    @Nullable
    private CustomTabsClient zzbiz;
    @Nullable
    private CustomTabsServiceConnection zzbja;
    @Nullable
    private zza zzbjb;
    
    public static boolean zzo(final Context context) {
        final PackageManager packageManager = context.getPackageManager();
        boolean equals = false;
        if (packageManager != null) {
            final Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
            final ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
            final List queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
            equals = false;
            if (queryIntentActivities != null) {
                equals = false;
                if (resolveActivity != null) {
                    int n = 0;
                    while (true) {
                        final int size = queryIntentActivities.size();
                        equals = false;
                        if (n >= size) {
                            return equals;
                        }
                        if (resolveActivity.activityInfo.name.equals(queryIntentActivities.get(n).activityInfo.name)) {
                            break;
                        }
                        ++n;
                    }
                    equals = resolveActivity.activityInfo.packageName.equals(zzarq.zzfc(context));
                }
            }
        }
        return equals;
    }
    
    public boolean mayLaunchUrl(final Uri uri, final Bundle bundle, final List<Bundle> list) {
        final CustomTabsClient zzbiz = this.zzbiz;
        boolean mayLaunchUrl = false;
        if (zzbiz != null) {
            final CustomTabsSession zzlf = this.zzlf();
            mayLaunchUrl = false;
            if (zzlf != null) {
                mayLaunchUrl = zzlf.mayLaunchUrl(uri, bundle, (List)list);
            }
        }
        return mayLaunchUrl;
    }
    
    @Override
    public void zza(final CustomTabsClient zzbiz) {
        (this.zzbiz = zzbiz).warmup(0L);
        if (this.zzbjb != null) {
            this.zzbjb.zzlh();
        }
    }
    
    public void zza(final zza zzbjb) {
        this.zzbjb = zzbjb;
    }
    
    public void zzd(final Activity activity) {
        if (this.zzbja != null) {
            activity.unbindService((ServiceConnection)this.zzbja);
            this.zzbiz = null;
            this.zzbiy = null;
            this.zzbja = null;
        }
    }
    
    public void zze(final Activity activity) {
        if (this.zzbiz == null) {
            final String zzfc = zzarq.zzfc((Context)activity);
            if (zzfc != null) {
                CustomTabsClient.bindCustomTabsService((Context)activity, zzfc, this.zzbja = new zzarr(this));
            }
        }
    }
    
    @Nullable
    public CustomTabsSession zzlf() {
        if (this.zzbiz == null) {
            this.zzbiy = null;
        }
        else if (this.zzbiy == null) {
            this.zzbiy = this.zzbiz.newSession((CustomTabsCallback)null);
        }
        return this.zzbiy;
    }
    
    @Override
    public void zzlg() {
        this.zzbiz = null;
        this.zzbiy = null;
        if (this.zzbjb != null) {
            this.zzbjb.zzli();
        }
    }
    
    public interface zza
    {
        void zzlh();
        
        void zzli();
    }
}
