// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.content.Intent;
import android.net.Uri$Builder;
import android.text.TextUtils;
import android.support.annotation.Nullable;
import android.net.Uri;

public class zzp
{
    private static final Uri CM;
    private static final Uri CN;
    
    static {
        CM = Uri.parse("http://plus.google.com/");
        CN = zzp.CM.buildUpon().appendPath("circles").appendPath("find").build();
    }
    
    private static Uri zzac(final String s, @Nullable final String s2) {
        final Uri$Builder appendQueryParameter = Uri.parse("market://details").buildUpon().appendQueryParameter("id", s);
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            appendQueryParameter.appendQueryParameter("pcampaignid", s2);
        }
        return appendQueryParameter.build();
    }
    
    public static Intent zzad(final String s, @Nullable final String s2) {
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(zzac(s, s2));
        intent.setPackage("com.android.vending");
        intent.addFlags(524288);
        return intent;
    }
    
    public static Intent zzaux() {
        final Intent intent = new Intent("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION");
        intent.setPackage("com.google.android.wearable.app");
        return intent;
    }
    
    public static Intent zzhw(final String s) {
        final Uri fromParts = Uri.fromParts("package", s, (String)null);
        final Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(fromParts);
        return intent;
    }
}
