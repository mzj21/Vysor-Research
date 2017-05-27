// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation.admob;

import android.text.TextUtils;
import android.os.Bundle;
import android.support.annotation.Keep;
import com.google.ads.mediation.AbstractAdViewAdapter;

@Keep
public final class AdMobAdapter extends AbstractAdViewAdapter
{
    @Override
    protected Bundle zza(Bundle bundle, final Bundle bundle2) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt("gw", 1);
        bundle.putString("mad_hac", bundle2.getString("mad_hac"));
        if (!TextUtils.isEmpty((CharSequence)bundle2.getString("adJson"))) {
            bundle.putString("_ad", bundle2.getString("adJson"));
        }
        bundle.putBoolean("_noRefresh", true);
        return bundle;
    }
}
