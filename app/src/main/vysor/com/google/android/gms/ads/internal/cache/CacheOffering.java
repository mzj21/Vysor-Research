// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.cache;

import android.os.Parcel;
import java.util.Iterator;
import java.util.List;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zziy
public class CacheOffering extends AbstractSafeParcelable
{
    public static final zzd CREATOR;
    @Nullable
    public final String url;
    public final int version;
    public final long zzavv;
    public final String zzavw;
    public final String zzavx;
    public final String zzavy;
    public final Bundle zzavz;
    public final boolean zzawa;
    
    static {
        CREATOR = new zzd();
    }
    
    CacheOffering(final int version, @Nullable final String url, final long zzavv, String zzavw, String zzavx, String zzavy, Bundle zzavz, final boolean zzawa) {
        this.version = version;
        this.url = url;
        this.zzavv = zzavv;
        if (zzavw == null) {
            zzavw = "";
        }
        this.zzavw = zzavw;
        if (zzavx == null) {
            zzavx = "";
        }
        this.zzavx = zzavx;
        if (zzavy == null) {
            zzavy = "";
        }
        this.zzavy = zzavy;
        if (zzavz == null) {
            zzavz = new Bundle();
        }
        this.zzavz = zzavz;
        this.zzawa = zzawa;
    }
    
    @Nullable
    public static CacheOffering zzag(final String s) {
        return zze(Uri.parse(s));
    }
    
    @Nullable
    public static CacheOffering zze(final Uri uri) {
        CacheOffering cacheOffering = null;
        try {
            if (!"gcache".equals(uri.getScheme())) {
                cacheOffering = null;
            }
            else {
                final List pathSegments = uri.getPathSegments();
                if (pathSegments.size() != 2) {
                    zzb.zzdf(new StringBuilder(62).append("Expected 2 path parts for namespace and id, found :").append(pathSegments.size()).toString());
                    cacheOffering = null;
                }
                else {
                    final String s = pathSegments.get(0);
                    final String s2 = pathSegments.get(1);
                    uri.getHost();
                    uri.getQueryParameter("url");
                    "1".equals(uri.getQueryParameter("read_only"));
                    if (uri.getQueryParameter("expiration") == null) {
                        final Bundle bundle = new Bundle();
                        for (final String s3 : zzu.zzgb().zzh(uri)) {
                            if (s3.startsWith("tag.")) {
                                bundle.putString(s3.substring("tag.".length()), uri.getQueryParameter(s3));
                            }
                        }
                        goto Label_0238;
                    }
                    goto Label_0228;
                }
            }
        }
        catch (NullPointerException ex) {}
        catch (NumberFormatException ex2) {
            goto Label_0217;
        }
        return cacheOffering;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzd.zza(this, parcel, n);
    }
}
