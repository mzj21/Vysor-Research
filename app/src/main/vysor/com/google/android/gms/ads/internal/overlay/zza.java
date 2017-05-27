// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.net.Uri;
import android.text.TextUtils;
import android.content.ActivityNotFoundException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzkn;
import android.content.Intent;
import android.content.Context;
import com.google.android.gms.internal.zziy;

@zziy
public class zza
{
    public boolean zza(final Context context, final Intent intent, final zzp zzp) {
        try {
            final String value = String.valueOf(intent.toURI());
            String concat;
            if (value.length() != 0) {
                concat = "Launching an intent: ".concat(value);
            }
            else {
                concat = new String("Launching an intent: ");
            }
            zzkn.v(concat);
            zzu.zzfz().zzb(context, intent);
            if (zzp != null) {
                zzp.zzdu();
            }
        }
        catch (ActivityNotFoundException ex) {
            zzb.zzdf(ex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean zza(final Context context, final AdLauncherIntentInfoParcel adLauncherIntentInfoParcel, final zzp zzp) {
        boolean b = false;
        if (adLauncherIntentInfoParcel == null) {
            zzb.zzdf("No intent data for launcher overlay.");
        }
        else if (adLauncherIntentInfoParcel.intent != null) {
            b = this.zza(context, adLauncherIntentInfoParcel.intent, zzp);
        }
        else {
            final Intent intent = new Intent();
            if (TextUtils.isEmpty((CharSequence)adLauncherIntentInfoParcel.url)) {
                zzb.zzdf("Open GMSG did not contain a URL.");
                b = false;
            }
            else {
                if (!TextUtils.isEmpty((CharSequence)adLauncherIntentInfoParcel.mimeType)) {
                    intent.setDataAndType(Uri.parse(adLauncherIntentInfoParcel.url), adLauncherIntentInfoParcel.mimeType);
                }
                else {
                    intent.setData(Uri.parse(adLauncherIntentInfoParcel.url));
                }
                intent.setAction("android.intent.action.VIEW");
                if (!TextUtils.isEmpty((CharSequence)adLauncherIntentInfoParcel.packageName)) {
                    intent.setPackage(adLauncherIntentInfoParcel.packageName);
                }
                if (!TextUtils.isEmpty((CharSequence)adLauncherIntentInfoParcel.zzbwf)) {
                    final String[] split = adLauncherIntentInfoParcel.zzbwf.split("/", 2);
                    if (split.length < 2) {
                        final String value = String.valueOf(adLauncherIntentInfoParcel.zzbwf);
                        String concat;
                        if (value.length() != 0) {
                            concat = "Could not parse component name from open GMSG: ".concat(value);
                        }
                        else {
                            concat = new String("Could not parse component name from open GMSG: ");
                        }
                        zzb.zzdf(concat);
                        b = false;
                        return b;
                    }
                    intent.setClassName(split[0], split[1]);
                }
                final String zzbwg = adLauncherIntentInfoParcel.zzbwg;
                Label_0265: {
                    if (TextUtils.isEmpty((CharSequence)zzbwg)) {
                        break Label_0265;
                    }
                    while (true) {
                        try {
                            final int int1 = Integer.parseInt(zzbwg);
                            intent.addFlags(int1);
                            b = this.zza(context, intent, zzp);
                        }
                        catch (NumberFormatException ex) {
                            zzb.zzdf("Could not parse intent flags.");
                            final int int1 = 0;
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        return b;
    }
}
