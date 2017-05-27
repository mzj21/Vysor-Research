// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.concurrent.Future;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzu;
import android.content.Context;
import org.json.JSONException;
import org.json.JSONArray;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

@zziy
public class zzgm
{
    public List<String> zza(final JSONObject jsonObject, final String s) throws JSONException {
        final JSONArray optJSONArray = jsonObject.optJSONArray(s);
        List<String> unmodifiableList;
        if (optJSONArray != null) {
            final ArrayList list = new ArrayList<String>(optJSONArray.length());
            for (int i = 0; i < optJSONArray.length(); ++i) {
                list.add(optJSONArray.getString(i));
            }
            unmodifiableList = Collections.unmodifiableList((List<? extends String>)list);
        }
        else {
            unmodifiableList = null;
        }
        return unmodifiableList;
    }
    
    public void zza(final Context context, final String s, final zzke zzke, final String s2, final boolean b, final List<String> list) {
        if (list != null && !list.isEmpty()) {
            String s3;
            if (b) {
                s3 = "1";
            }
            else {
                s3 = "0";
            }
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                String s4 = iterator.next().replaceAll("@gw_adlocid@", s2).replaceAll("@gw_adnetrefresh@", s3).replaceAll("@gw_qdata@", zzke.zzcof.zzbsi).replaceAll("@gw_sdkver@", s).replaceAll("@gw_sessid@", zzu.zzgd().getSessionId()).replaceAll("@gw_seqnum@", zzke.zzcfx);
                if (!TextUtils.isEmpty((CharSequence)zzke.zzcog)) {
                    s4 = s4.replaceAll("@gw_adnetstatus@", zzke.zzcog);
                }
                if (zzke.zzbte != null) {
                    s4 = s4.replaceAll("@gw_adnetid@", zzke.zzbte.zzbrm).replaceAll("@gw_allocid@", zzke.zzbte.zzbro);
                }
                final Future future = (Future)new zzlb(context, s, s4).zzqw();
            }
        }
    }
}
