// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;

@zziy
public class zzdc implements zzdd
{
    @Override
    public List<String> zza(final AdRequestInfoParcel adRequestInfoParcel) {
        List<String> list;
        if (adRequestInfoParcel.zzcgk == null) {
            list = Collections.emptyList();
        }
        else {
            list = adRequestInfoParcel.zzcgk;
        }
        return list;
    }
}
