// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.safebrowsing;

import android.os.Parcel;
import android.support.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zziy
public class SafeBrowsingConfigParcel extends AbstractSafeParcelable
{
    public static final zzb CREATOR;
    public final int versionCode;
    public final String zzcnz;
    public final String zzcoa;
    public final boolean zzcob;
    public final boolean zzcoc;
    
    static {
        CREATOR = new zzb();
    }
    
    public SafeBrowsingConfigParcel(final int versionCode, final String zzcnz, final String zzcoa, final boolean zzcob, final boolean zzcoc) {
        this.versionCode = versionCode;
        this.zzcnz = zzcnz;
        this.zzcoa = zzcoa;
        this.zzcob = zzcob;
        this.zzcoc = zzcoc;
    }
    
    @Nullable
    public static SafeBrowsingConfigParcel zzk(final JSONObject jsonObject) throws JSONException {
        SafeBrowsingConfigParcel safeBrowsingConfigParcel;
        if (jsonObject == null) {
            safeBrowsingConfigParcel = null;
        }
        else {
            safeBrowsingConfigParcel = new SafeBrowsingConfigParcel(1, jsonObject.getString("click_string"), jsonObject.getString("report_url"), jsonObject.optBoolean("rendered_ad_enabled", false), jsonObject.optBoolean("non_malicious_reporting_enabled", false));
        }
        return safeBrowsingConfigParcel;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
}
