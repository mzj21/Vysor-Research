// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.reward.mediation.client;

import org.json.JSONObject;
import android.os.Parcel;
import com.google.android.gms.common.internal.zzab;
import android.text.TextUtils;
import android.support.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONArray;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zziy
public final class RewardItemParcel extends AbstractSafeParcelable
{
    public static final zzc CREATOR;
    public final String type;
    public final int versionCode;
    public final int zzcny;
    
    static {
        CREATOR = new zzc();
    }
    
    public RewardItemParcel(final int versionCode, final String type, final int zzcny) {
        this.versionCode = versionCode;
        this.type = type;
        this.zzcny = zzcny;
    }
    
    public RewardItemParcel(final RewardItem rewardItem) {
        this(1, rewardItem.getType(), rewardItem.getAmount());
    }
    
    public RewardItemParcel(final String s, final int n) {
        this(1, s, n);
    }
    
    @Nullable
    public static RewardItemParcel zza(final JSONArray jsonArray) throws JSONException {
        RewardItemParcel rewardItemParcel;
        if (jsonArray == null || jsonArray.length() == 0) {
            rewardItemParcel = null;
        }
        else {
            rewardItemParcel = new RewardItemParcel(jsonArray.getJSONObject(0).optString("rb_type"), jsonArray.getJSONObject(0).optInt("rb_amount"));
        }
        return rewardItemParcel;
    }
    
    @Nullable
    public static RewardItemParcel zzcp(@Nullable final String s) {
        final boolean empty = TextUtils.isEmpty((CharSequence)s);
        RewardItemParcel zza = null;
        if (!empty) {
            try {
                zza = zza(new JSONArray(s));
            }
            catch (JSONException ex) {
                zza = null;
            }
        }
        return zza;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = false;
        if (o != null) {
            final boolean b2 = o instanceof RewardItemParcel;
            b = false;
            if (b2) {
                final RewardItemParcel rewardItemParcel = (RewardItemParcel)o;
                final boolean equal = zzab.equal(this.type, rewardItemParcel.type);
                b = false;
                if (equal) {
                    final boolean equal2 = zzab.equal(this.zzcny, rewardItemParcel.zzcny);
                    b = false;
                    if (equal2) {
                        b = true;
                    }
                }
            }
        }
        return b;
    }
    
    @Override
    public int hashCode() {
        return zzab.hashCode(this.type, this.zzcny);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
    
    public JSONArray zzsx() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("rb_type", (Object)this.type);
        jsonObject.put("rb_amount", this.zzcny);
        final JSONArray jsonArray = new JSONArray();
        jsonArray.put((Object)jsonObject);
        return jsonArray;
    }
}
