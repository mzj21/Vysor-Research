// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import android.os.Parcel;
import java.util.Locale;
import com.google.android.gms.common.internal.zzab;
import org.json.JSONException;
import org.json.JSONObject;
import android.net.Uri;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class WebImage extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<WebImage> CREATOR;
    private final Uri AC;
    private final int mVersionCode;
    private final int zzajw;
    private final int zzajx;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    WebImage(final int mVersionCode, final Uri ac, final int zzajw, final int zzajx) {
        this.mVersionCode = mVersionCode;
        this.AC = ac;
        this.zzajw = zzajw;
        this.zzajx = zzajx;
    }
    
    public WebImage(final Uri uri) throws IllegalArgumentException {
        this(uri, 0, 0);
    }
    
    public WebImage(final Uri uri, final int n, final int n2) throws IllegalArgumentException {
        this(1, uri, n, n2);
        if (uri == null) {
            throw new IllegalArgumentException("url cannot be null");
        }
        if (n < 0 || n2 < 0) {
            throw new IllegalArgumentException("mWidth and mHeight must not be negative");
        }
    }
    
    public WebImage(final JSONObject jsonObject) throws IllegalArgumentException {
        this(zzq(jsonObject), jsonObject.optInt("mWidth", 0), jsonObject.optInt("mHeight", 0));
    }
    
    private static Uri zzq(final JSONObject jsonObject) {
        final boolean has = jsonObject.has("url");
        Uri parse = null;
        if (!has) {
            return parse;
        }
        try {
            parse = Uri.parse(jsonObject.getString("url"));
            return parse;
        }
        catch (JSONException ex) {
            parse = null;
            return parse;
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        if (this != o) {
            if (o == null || !(o instanceof WebImage)) {
                b = false;
            }
            else {
                final WebImage webImage = (WebImage)o;
                if (!zzab.equal(this.AC, webImage.AC) || this.zzajw != webImage.zzajw || this.zzajx != webImage.zzajx) {
                    b = false;
                }
            }
        }
        return b;
    }
    
    public int getHeight() {
        return this.zzajx;
    }
    
    public Uri getUrl() {
        return this.AC;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    public int getWidth() {
        return this.zzajw;
    }
    
    @Override
    public int hashCode() {
        return zzab.hashCode(this.AC, this.zzajw, this.zzajx);
    }
    
    public JSONObject toJson() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("url", (Object)this.AC.toString());
            jsonObject.put("mWidth", this.zzajw);
            jsonObject.put("mHeight", this.zzajx);
            return jsonObject;
        }
        catch (JSONException ex) {
            return jsonObject;
        }
    }
    
    @Override
    public String toString() {
        return String.format(Locale.US, "Image %dx%d %s", this.zzajw, this.zzajx, this.AC.toString());
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
}
