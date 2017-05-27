// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONException;
import com.google.android.gms.ads.internal.util.client.zzb;
import org.json.JSONObject;

@zziy
public class zzhm
{
    private final zzlt zzbkr;
    private final String zzbwd;
    
    public zzhm(final zzlt zzlt) {
        this(zzlt, "");
    }
    
    public zzhm(final zzlt zzbkr, final String zzbwd) {
        this.zzbkr = zzbkr;
        this.zzbwd = zzbwd;
    }
    
    public void zza(final int n, final int n2, final int n3, final int n4, final float n5, final int n6) {
        try {
            this.zzbkr.zzb("onScreenInfoChanged", new JSONObject().put("mWidth", n).put("mHeight", n2).put("maxSizeWidth", n3).put("maxSizeHeight", n4).put("density", (double)n5).put("rotation", n6));
        }
        catch (JSONException ex) {
            zzb.zzb("Error occured while obtaining screen information.", (Throwable)ex);
        }
    }
    
    public void zzb(final int n, final int n2, final int n3, final int n4) {
        try {
            this.zzbkr.zzb("onSizeChanged", new JSONObject().put("x", n).put("y", n2).put("mWidth", n3).put("mHeight", n4));
        }
        catch (JSONException ex) {
            zzb.zzb("Error occured while dispatching size change.", (Throwable)ex);
        }
    }
    
    public void zzbx(final String s) {
        try {
            this.zzbkr.zzb("onError", new JSONObject().put("message", (Object)s).put("action", (Object)this.zzbwd));
        }
        catch (JSONException ex) {
            zzb.zzb("Error occurred while dispatching error event.", (Throwable)ex);
        }
    }
    
    public void zzby(final String s) {
        try {
            this.zzbkr.zzb("onReadyEventReceived", new JSONObject().put("js", (Object)s));
        }
        catch (JSONException ex) {
            zzb.zzb("Error occured while dispatching ready Event.", (Throwable)ex);
        }
    }
    
    public void zzbz(final String s) {
        try {
            this.zzbkr.zzb("onStateChanged", new JSONObject().put("state", (Object)s));
        }
        catch (JSONException ex) {
            zzb.zzb("Error occured while dispatching state change.", (Throwable)ex);
        }
    }
    
    public void zzc(final int n, final int n2, final int n3, final int n4) {
        try {
            this.zzbkr.zzb("onDefaultPositionReceived", new JSONObject().put("x", n).put("y", n2).put("mWidth", n3).put("mHeight", n4));
        }
        catch (JSONException ex) {
            zzb.zzb("Error occured while dispatching default position.", (Throwable)ex);
        }
    }
}
