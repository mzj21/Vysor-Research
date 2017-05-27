// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzac;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaj;
import com.google.android.gms.R;
import android.content.Context;
import com.google.android.gms.common.api.Status;

@Deprecated
public final class zzqw
{
    private static zzqw yP;
    private static Object zzaok;
    private final String yQ;
    private final Status yR;
    private final String yS;
    private final String yT;
    private final String yU;
    private final boolean yV;
    private final boolean yW;
    private final String zzcpe;
    
    static {
        zzqw.zzaok = new Object();
    }
    
    zzqw(final Context context) {
        boolean b = true;
        final Resources resources = context.getResources();
        final int identifier = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(R.string.common_google_play_services_unknown_issue));
        if (identifier != 0) {
            final boolean b2 = resources.getInteger(identifier) != 0 && b;
            if (b2) {
                b = false;
            }
            this.yW = b;
            b = b2;
        }
        else {
            this.yW = false;
        }
        this.yV = b;
        final zzaj zzaj = new zzaj(context);
        this.yS = zzaj.getString("firebase_database_url");
        this.yU = zzaj.getString("google_storage_bucket");
        this.yT = zzaj.getString("gcm_defaultSenderId");
        this.yQ = zzaj.getString("google_api_key");
        String zzcpe = zzaa.zzcg(context);
        if (zzcpe == null) {
            zzcpe = zzaj.getString("google_app_id");
        }
        if (TextUtils.isEmpty((CharSequence)zzcpe)) {
            this.yR = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.zzcpe = null;
        }
        else {
            this.zzcpe = zzcpe;
            this.yR = Status.vY;
        }
    }
    
    zzqw(final String s, final boolean b) {
        this(s, b, null, null, null);
    }
    
    zzqw(final String zzcpe, final boolean yv, final String ys, final String yu, final String yt) {
        this.zzcpe = zzcpe;
        this.yQ = null;
        this.yR = Status.vY;
        this.yV = yv;
        this.yW = !yv;
        this.yS = ys;
        this.yT = yt;
        this.yU = yu;
    }
    
    public static String zzasl() {
        return zzhf("getGoogleAppId").zzcpe;
    }
    
    public static boolean zzasm() {
        return zzhf("isMeasurementExplicitlyDisabled").yW;
    }
    
    public static Status zzb(final Context context, final String s, final boolean b) {
        zzac.zzb(context, "Context must not be null.");
        zzac.zzh(s, "App ID must be nonempty.");
        Status status;
        synchronized (zzqw.zzaok) {
            if (zzqw.yP != null) {
                status = zzqw.yP.zzhe(s);
            }
            else {
                zzqw.yP = new zzqw(s, b);
                status = zzqw.yP.yR;
            }
        }
        return status;
    }
    
    public static Status zzcb(final Context context) {
        zzac.zzb(context, "Context must not be null.");
        synchronized (zzqw.zzaok) {
            if (zzqw.yP == null) {
                zzqw.yP = new zzqw(context);
            }
            return zzqw.yP.yR;
        }
    }
    
    private static zzqw zzhf(final String s) {
        synchronized (zzqw.zzaok) {
            if (zzqw.yP == null) {
                throw new IllegalStateException(new StringBuilder(34 + String.valueOf(s).length()).append("Initialize must be called before ").append(s).append(".").toString());
            }
        }
        // monitorexit(o)
        return zzqw.yP;
    }
    
    Status zzhe(final String s) {
        Status vy;
        if (this.zzcpe != null && !this.zzcpe.equals(s)) {
            final String zzcpe = this.zzcpe;
            vy = new Status(10, new StringBuilder(97 + String.valueOf(zzcpe).length()).append("Initialize was called with two different Google App IDs.  Only the first app ID will be used: '").append(zzcpe).append("'.").toString());
        }
        else {
            vy = Status.vY;
        }
        return vy;
    }
}
