// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONException;
import com.google.android.gms.ads.internal.util.client.zzb;
import org.json.JSONObject;

@zziy
public class zzhk
{
    private final boolean zzbvo;
    private final boolean zzbvp;
    private final boolean zzbvq;
    private final boolean zzbvr;
    private final boolean zzbvs;
    
    private zzhk(final zza zza) {
        this.zzbvo = zza.zzbvo;
        this.zzbvp = zza.zzbvp;
        this.zzbvq = zza.zzbvq;
        this.zzbvr = zza.zzbvr;
        this.zzbvs = zza.zzbvs;
    }
    
    public JSONObject toJson() {
        try {
            return new JSONObject().put("sms", this.zzbvo).put("tel", this.zzbvp).put("calendar", this.zzbvq).put("storePicture", this.zzbvr).put("inlineVideo", this.zzbvs);
        }
        catch (JSONException ex) {
            zzb.zzb("Error occured while obtaining the MRAID capabilities.", (Throwable)ex);
            return null;
        }
    }
    
    public static final class zza
    {
        private boolean zzbvo;
        private boolean zzbvp;
        private boolean zzbvq;
        private boolean zzbvr;
        private boolean zzbvs;
        
        public zzhk zznw() {
            return new zzhk(this, null);
        }
        
        public zza zzu(final boolean zzbvo) {
            this.zzbvo = zzbvo;
            return this;
        }
        
        public zza zzv(final boolean zzbvp) {
            this.zzbvp = zzbvp;
            return this;
        }
        
        public zza zzw(final boolean zzbvq) {
            this.zzbvq = zzbvq;
            return this;
        }
        
        public zza zzx(final boolean zzbvr) {
            this.zzbvr = zzbvr;
            return this;
        }
        
        public zza zzy(final boolean zzbvs) {
            this.zzbvs = zzbvs;
            return this;
        }
    }
}
