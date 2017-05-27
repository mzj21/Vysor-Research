// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONException;
import org.json.JSONArray;
import com.google.android.gms.ads.internal.zzu;
import java.util.Collections;
import java.util.ArrayList;
import org.json.JSONObject;
import android.support.annotation.Nullable;
import java.util.List;

@zziy
public final class zzgg
{
    public final String zzbrl;
    public final String zzbrm;
    public final List<String> zzbrn;
    public final String zzbro;
    public final String zzbrp;
    public final List<String> zzbrq;
    public final List<String> zzbrr;
    public final List<String> zzbrs;
    public final String zzbrt;
    public final List<String> zzbru;
    public final List<String> zzbrv;
    @Nullable
    public final String zzbrw;
    @Nullable
    public final String zzbrx;
    public final String zzbry;
    @Nullable
    public final List<String> zzbrz;
    public final String zzbsa;
    
    public zzgg(final String zzbrl, final String zzbrm, final List<String> zzbrn, final String zzbro, final String zzbrp, final List<String> zzbrq, final List<String> zzbrr, final String zzbrt, final String s, final List<String> zzbru, final List<String> zzbrv, final String zzbrw, final String zzbrx, final String zzbry, final List<String> zzbrz, final String zzbsa, final List<String> zzbrs) {
        this.zzbrl = zzbrl;
        this.zzbrm = zzbrm;
        this.zzbrn = zzbrn;
        this.zzbro = zzbro;
        this.zzbrp = zzbrp;
        this.zzbrq = zzbrq;
        this.zzbrr = zzbrr;
        this.zzbrt = zzbrt;
        this.zzbru = zzbru;
        this.zzbrv = zzbrv;
        this.zzbrw = zzbrw;
        this.zzbrx = zzbrx;
        this.zzbry = zzbry;
        this.zzbrz = zzbrz;
        this.zzbsa = zzbsa;
        this.zzbrs = zzbrs;
    }
    
    public zzgg(final JSONObject jsonObject) throws JSONException {
        this.zzbrm = jsonObject.getString("id");
        final JSONArray jsonArray = jsonObject.getJSONArray("adapters");
        final ArrayList list = new ArrayList<String>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); ++i) {
            list.add(jsonArray.getString(i));
        }
        this.zzbrn = Collections.unmodifiableList((List<? extends String>)list);
        this.zzbro = jsonObject.optString("allocation_id", (String)null);
        this.zzbrq = zzu.zzgs().zza(jsonObject, "clickurl");
        this.zzbrr = zzu.zzgs().zza(jsonObject, "imp_urls");
        this.zzbrs = zzu.zzgs().zza(jsonObject, "fill_urls");
        this.zzbru = zzu.zzgs().zza(jsonObject, "video_start_urls");
        this.zzbrv = zzu.zzgs().zza(jsonObject, "video_complete_urls");
        final JSONObject optJSONObject = jsonObject.optJSONObject("ad");
        String string;
        if (optJSONObject != null) {
            string = optJSONObject.toString();
        }
        else {
            string = null;
        }
        this.zzbrl = string;
        final JSONObject optJSONObject2 = jsonObject.optJSONObject("data");
        String string2;
        if (optJSONObject2 != null) {
            string2 = optJSONObject2.toString();
        }
        else {
            string2 = null;
        }
        this.zzbrt = string2;
        String optString;
        if (optJSONObject2 != null) {
            optString = optJSONObject2.optString("class_name");
        }
        else {
            optString = null;
        }
        this.zzbrp = optString;
        this.zzbrw = jsonObject.optString("html_template", (String)null);
        this.zzbrx = jsonObject.optString("ad_base_url", (String)null);
        final JSONObject optJSONObject3 = jsonObject.optJSONObject("assets");
        String string3;
        if (optJSONObject3 != null) {
            string3 = optJSONObject3.toString();
        }
        else {
            string3 = null;
        }
        this.zzbry = string3;
        this.zzbrz = zzu.zzgs().zza(jsonObject, "template_ids");
        final JSONObject optJSONObject4 = jsonObject.optJSONObject("ad_loader_options");
        String string4 = null;
        if (optJSONObject4 != null) {
            string4 = optJSONObject4.toString();
        }
        this.zzbsa = string4;
    }
}
