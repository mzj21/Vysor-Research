// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.app.ActivityManager;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import android.content.Intent;
import android.content.Context;
import android.content.ActivityNotFoundException;
import com.google.android.gms.ads.internal.overlay.AdLauncherIntentInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzu;
import java.util.Map;
import com.google.android.gms.ads.internal.zze;

@zziy
public final class zzfc implements zzev
{
    private final zze zzbnl;
    private final zzhh zzbnm;
    private final zzex zzbno;
    
    public zzfc(final zzex zzbno, final zze zzbnl, final zzhh zzbnm) {
        this.zzbno = zzbno;
        this.zzbnl = zzbnl;
        this.zzbnm = zzbnm;
    }
    
    private static boolean zzc(final Map<String, String> map) {
        return "1".equals(map.get("custom_close"));
    }
    
    private static int zzd(final Map<String, String> map) {
        final String s = map.get("o");
        if (s == null) {
            return -1;
        }
        int n;
        if ("p".equalsIgnoreCase(s)) {
            n = zzu.zzgb().zzun();
        }
        else if ("l".equalsIgnoreCase(s)) {
            n = zzu.zzgb().zzum();
        }
        else {
            if (!"c".equalsIgnoreCase(s)) {
                return -1;
            }
            n = zzu.zzgb().zzuo();
        }
        return n;
        n = -1;
        return n;
    }
    
    private static void zze(final zzlt zzlt, final Map<String, String> map) {
        final Context context = zzlt.getContext();
        if (TextUtils.isEmpty((CharSequence)map.get("u"))) {
            zzb.zzdf("Destination url cannot be empty.");
        }
        else {
            final zzlu zzvr = zzlt.zzvr();
            final Intent zza = new zza(zzlt).zza(context, map);
            try {
                zzvr.zza(new AdLauncherIntentInfoParcel(zza));
            }
            catch (ActivityNotFoundException ex) {
                zzb.zzdf(ex.getMessage());
            }
        }
    }
    
    private void zzs(final boolean b) {
        if (this.zzbnm != null) {
            this.zzbnm.zzt(b);
        }
    }
    
    @Override
    public void zza(final zzlt zzlt, final Map<String, String> map) {
        final String s = map.get("a");
        if (s == null) {
            zzb.zzdf("Action missing from an open GMSG.");
        }
        else if (this.zzbnl != null && !this.zzbnl.zzer()) {
            this.zzbnl.zzv(map.get("u"));
        }
        else {
            final zzlu zzvr = zzlt.zzvr();
            if ("expand".equalsIgnoreCase(s)) {
                if (zzlt.zzvv()) {
                    zzb.zzdf("Cannot expand WebView that is already expanded.");
                }
                else {
                    this.zzs(false);
                    zzvr.zza(zzc(map), zzd(map));
                }
            }
            else if ("webapp".equalsIgnoreCase(s)) {
                final String s2 = map.get("u");
                this.zzs(false);
                if (s2 != null) {
                    zzvr.zza(zzc(map), zzd(map), s2);
                }
                else {
                    zzvr.zza(zzc(map), zzd(map), map.get("html"), map.get("baseurl"));
                }
            }
            else if ("in_app_purchase".equalsIgnoreCase(s)) {
                final String s3 = map.get("product_id");
                final String s4 = map.get("report_urls");
                if (this.zzbno != null) {
                    if (s4 != null && !s4.isEmpty()) {
                        this.zzbno.zza(s3, new ArrayList<String>(Arrays.asList(s4.split(" "))));
                    }
                    else {
                        this.zzbno.zza(s3, new ArrayList<String>());
                    }
                }
            }
            else if ("app".equalsIgnoreCase(s) && "true".equalsIgnoreCase(map.get("system_browser"))) {
                this.zzs(true);
                zze(zzlt, map);
            }
            else {
                this.zzs(true);
                final String s5 = map.get("u");
                String zza;
                if (!TextUtils.isEmpty((CharSequence)s5)) {
                    zza = zzu.zzfz().zza(zzlt, s5);
                }
                else {
                    zza = s5;
                }
                zzvr.zza(new AdLauncherIntentInfoParcel(map.get("i"), zza, map.get("m"), map.get("p"), map.get("c"), map.get("f"), map.get("e")));
            }
        }
    }
    
    public static class zza
    {
        private final zzlt zzbkr;
        
        public zza(final zzlt zzbkr) {
            this.zzbkr = zzbkr;
        }
        
        public Intent zza(final Context context, final Map<String, String> map) {
            final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
            String zza = map.get("u");
            final boolean empty = TextUtils.isEmpty((CharSequence)zza);
            Intent intent = null;
            if (!empty) {
                if (this.zzbkr != null) {
                    zza = zzu.zzfz().zza(this.zzbkr, zza);
                }
                final Uri parse = Uri.parse(zza);
                final boolean boolean1 = Boolean.parseBoolean(map.get("use_first_package"));
                final boolean boolean2 = Boolean.parseBoolean(map.get("use_running_process"));
                Uri uri;
                if ("http".equalsIgnoreCase(parse.getScheme())) {
                    uri = parse.buildUpon().scheme("https").build();
                }
                else if ("https".equalsIgnoreCase(parse.getScheme())) {
                    uri = parse.buildUpon().scheme("http").build();
                }
                else {
                    uri = null;
                }
                final ArrayList list = new ArrayList<ResolveInfo>();
                final Intent zzf = this.zzf(parse);
                final Intent zzf2 = this.zzf(uri);
                final ResolveInfo zza2 = this.zza(context, zzf, list);
                if (zza2 != null) {
                    intent = this.zza(zzf, zza2);
                }
                else {
                    if (zzf2 != null) {
                        final ResolveInfo zza3 = this.zza(context, zzf2);
                        if (zza3 != null) {
                            intent = this.zza(zzf, zza3);
                            if (this.zza(context, intent) != null) {
                                return intent;
                            }
                        }
                    }
                    if (list.size() == 0) {
                        intent = zzf;
                    }
                    else {
                        if (boolean2 && activityManager != null) {
                            final List runningAppProcesses = activityManager.getRunningAppProcesses();
                            if (runningAppProcesses != null) {
                                for (final ResolveInfo resolveInfo : list) {
                                    final Iterator<ActivityManager$RunningAppProcessInfo> iterator2 = runningAppProcesses.iterator();
                                    while (iterator2.hasNext()) {
                                        if (iterator2.next().processName.equals(resolveInfo.activityInfo.packageName)) {
                                            intent = this.zza(zzf, resolveInfo);
                                            return intent;
                                        }
                                    }
                                }
                            }
                        }
                        if (boolean1) {
                            intent = this.zza(zzf, list.get(0));
                        }
                        else {
                            intent = zzf;
                        }
                    }
                }
            }
            return intent;
        }
        
        public Intent zza(final Intent intent, final ResolveInfo resolveInfo) {
            final Intent intent2 = new Intent(intent);
            intent2.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
            return intent2;
        }
        
        public ResolveInfo zza(final Context context, final Intent intent) {
            return this.zza(context, intent, new ArrayList<ResolveInfo>());
        }
        
        public ResolveInfo zza(final Context context, final Intent intent, final ArrayList<ResolveInfo> list) {
            final PackageManager packageManager = context.getPackageManager();
            ResolveInfo resolveInfo = null;
            if (packageManager != null) {
                final List queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
                final ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
            Label_0107:
                while (true) {
                    if (queryIntentActivities != null && resolveActivity != null) {
                        for (int i = 0; i < queryIntentActivities.size(); ++i) {
                            final ResolveInfo resolveInfo2 = queryIntentActivities.get(i);
                            if (resolveActivity != null && resolveActivity.activityInfo.name.equals(resolveInfo2.activityInfo.name)) {
                                final ResolveInfo resolveInfo3 = resolveActivity;
                                break Label_0107;
                            }
                        }
                    }
                    Label_0127: {
                        break Label_0127;
                        list.addAll(queryIntentActivities);
                        final ResolveInfo resolveInfo3;
                        resolveInfo = resolveInfo3;
                        return resolveInfo;
                    }
                    final ResolveInfo resolveInfo3 = null;
                    continue Label_0107;
                }
            }
            return resolveInfo;
        }
        
        public Intent zzf(final Uri data) {
            Intent intent;
            if (data == null) {
                intent = null;
            }
            else {
                intent = new Intent("android.intent.action.VIEW");
                intent.addFlags(268435456);
                intent.setData(data);
                intent.setAction("android.intent.action.VIEW");
            }
            return intent;
        }
    }
}
