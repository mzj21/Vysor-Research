// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.request;

import android.os.Parcel;
import org.json.JSONArray;
import org.json.JSONException;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.ArrayList;
import org.json.JSONObject;
import java.util.Collections;
import android.support.annotation.Nullable;
import java.util.List;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zziy
public final class AutoClickProtectionConfigurationParcel extends AbstractSafeParcelable
{
    public static final zzi CREATOR;
    public final int versionCode;
    public final boolean zzchz;
    @Nullable
    public final List<String> zzcia;
    
    static {
        CREATOR = new zzi();
    }
    
    public AutoClickProtectionConfigurationParcel() {
        this(1, false, Collections.emptyList());
    }
    
    public AutoClickProtectionConfigurationParcel(final int versionCode, final boolean zzchz, final List<String> zzcia) {
        this.versionCode = versionCode;
        this.zzchz = zzchz;
        this.zzcia = zzcia;
    }
    
    public AutoClickProtectionConfigurationParcel(final boolean b) {
        this(1, b, Collections.emptyList());
    }
    
    public AutoClickProtectionConfigurationParcel(final boolean b, final List<String> list) {
        this(1, b, list);
    }
    
    @Nullable
    public static AutoClickProtectionConfigurationParcel zzi(final JSONObject jsonObject) {
        AutoClickProtectionConfigurationParcel autoClickProtectionConfigurationParcel;
        if (jsonObject == null) {
            autoClickProtectionConfigurationParcel = new AutoClickProtectionConfigurationParcel();
        }
        else {
            final JSONArray optJSONArray = jsonObject.optJSONArray("reporting_urls");
            final ArrayList<String> list = new ArrayList<String>();
            if (optJSONArray != null) {
                int i = 0;
            Label_0056_Outer:
                while (i < optJSONArray.length()) {
                    while (true) {
                        try {
                            list.add(optJSONArray.getString(i));
                            ++i;
                            continue Label_0056_Outer;
                        }
                        catch (JSONException ex) {
                            zzb.zzd("Error grabbing url from json.", (Throwable)ex);
                            continue;
                        }
                        break;
                    }
                    break;
                }
            }
            autoClickProtectionConfigurationParcel = new AutoClickProtectionConfigurationParcel(jsonObject.optBoolean("enable_protection"), list);
        }
        return autoClickProtectionConfigurationParcel;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzi.zza(this, parcel, n);
    }
}
