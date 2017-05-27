// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import java.util.HashSet;
import android.text.TextUtils;
import java.util.Iterator;
import org.json.JSONException;
import java.util.Collections;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Collection;
import java.util.ArrayList;
import com.google.android.gms.common.internal.zzac;
import java.util.Set;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.zzh;
import android.net.Uri;
import java.util.List;
import com.google.android.gms.common.api.Scope;
import java.util.Comparator;
import com.google.android.gms.common.util.zze;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class GoogleSignInAccount extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<GoogleSignInAccount> CREATOR;
    public static zze gT;
    private static Comparator<Scope> hc;
    List<Scope> fK;
    private String gU;
    private String gV;
    private Uri gW;
    private String gX;
    private long gY;
    private String gZ;
    private String gs;
    private String ha;
    private String hb;
    final int versionCode;
    private String zzbks;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
        GoogleSignInAccount.gT = zzh.zzaxj();
        GoogleSignInAccount.hc = new Comparator<Scope>() {
            public int zza(final Scope scope, final Scope scope2) {
                return scope.zzaqg().compareTo(scope2.zzaqg());
            }
        };
    }
    
    GoogleSignInAccount(final int versionCode, final String zzbks, final String gs, final String gu, final String gv, final Uri gw, final String gx, final long gy, final String gz, final List<Scope> fk, final String ha, final String hb) {
        this.versionCode = versionCode;
        this.zzbks = zzbks;
        this.gs = gs;
        this.gU = gu;
        this.gV = gv;
        this.gW = gw;
        this.gX = gx;
        this.gY = gy;
        this.gZ = gz;
        this.fK = fk;
        this.ha = ha;
        this.hb = hb;
    }
    
    public static GoogleSignInAccount zza(@Nullable final String s, @Nullable final String s2, @Nullable final String s3, @Nullable final String s4, @Nullable final String s5, @Nullable final String s6, @Nullable final Uri uri, @Nullable Long value, @NonNull final String s7, @NonNull final Set<Scope> set) {
        if (value == null) {
            value = GoogleSignInAccount.gT.currentTimeMillis() / 1000L;
        }
        return new GoogleSignInAccount(3, s, s2, s3, s4, uri, null, value, zzac.zzhz(s7), new ArrayList<Scope>(zzac.zzy(set)), s5, s6);
    }
    
    private JSONObject zzahi() {
        final JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray;
        try {
            if (this.getId() != null) {
                jsonObject.put("id", (Object)this.getId());
            }
            if (this.getIdToken() != null) {
                jsonObject.put("tokenId", (Object)this.getIdToken());
            }
            if (this.getEmail() != null) {
                jsonObject.put("email", (Object)this.getEmail());
            }
            if (this.getDisplayName() != null) {
                jsonObject.put("displayName", (Object)this.getDisplayName());
            }
            if (this.getGivenName() != null) {
                jsonObject.put("givenName", (Object)this.getGivenName());
            }
            if (this.getFamilyName() != null) {
                jsonObject.put("familyName", (Object)this.getFamilyName());
            }
            if (this.getPhotoUrl() != null) {
                jsonObject.put("photoUrl", (Object)this.getPhotoUrl().toString());
            }
            if (this.getServerAuthCode() != null) {
                jsonObject.put("serverAuthCode", (Object)this.getServerAuthCode());
            }
            jsonObject.put("expirationTime", this.gY);
            jsonObject.put("obfuscatedIdentifier", (Object)this.zzahf());
            jsonArray = new JSONArray();
            Collections.sort(this.fK, GoogleSignInAccount.hc);
            final Iterator<Scope> iterator = this.fK.iterator();
            while (iterator.hasNext()) {
                jsonArray.put((Object)iterator.next().zzaqg());
            }
        }
        catch (JSONException ex) {
            throw new RuntimeException((Throwable)ex);
        }
        jsonObject.put("grantedScopes", (Object)jsonArray);
        return jsonObject;
    }
    
    @Nullable
    public static GoogleSignInAccount zzfw(@Nullable final String s) throws JSONException {
        final boolean empty = TextUtils.isEmpty((CharSequence)s);
        GoogleSignInAccount zzfx = null;
        if (!empty) {
            final JSONObject jsonObject = new JSONObject(s);
            final String optString = jsonObject.optString("photoUrl", (String)null);
            Uri parse;
            if (!TextUtils.isEmpty((CharSequence)optString)) {
                parse = Uri.parse(optString);
            }
            else {
                parse = null;
            }
            final long long1 = Long.parseLong(jsonObject.getString("expirationTime"));
            final HashSet<Scope> set = new HashSet<Scope>();
            final JSONArray jsonArray = jsonObject.getJSONArray("grantedScopes");
            for (int length = jsonArray.length(), i = 0; i < length; ++i) {
                set.add(new Scope(jsonArray.getString(i)));
            }
            zzfx = zza(jsonObject.optString("id"), jsonObject.optString("tokenId", (String)null), jsonObject.optString("email", (String)null), jsonObject.optString("displayName", (String)null), jsonObject.optString("givenName", (String)null), jsonObject.optString("familyName", (String)null), parse, long1, jsonObject.getString("obfuscatedIdentifier"), set).zzfx(jsonObject.optString("serverAuthCode", (String)null));
        }
        return zzfx;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof GoogleSignInAccount && ((GoogleSignInAccount)o).zzahg().equals(this.zzahg());
    }
    
    @Nullable
    public String getDisplayName() {
        return this.gV;
    }
    
    @Nullable
    public String getEmail() {
        return this.gU;
    }
    
    @Nullable
    public String getFamilyName() {
        return this.hb;
    }
    
    @Nullable
    public String getGivenName() {
        return this.ha;
    }
    
    @NonNull
    public Set<Scope> getGrantedScopes() {
        return new HashSet<Scope>(this.fK);
    }
    
    @Nullable
    public String getId() {
        return this.zzbks;
    }
    
    @Nullable
    public String getIdToken() {
        return this.gs;
    }
    
    @Nullable
    public Uri getPhotoUrl() {
        return this.gW;
    }
    
    @Nullable
    public String getServerAuthCode() {
        return this.gX;
    }
    
    @Override
    public int hashCode() {
        return this.zzahg().hashCode();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public boolean zza() {
        return GoogleSignInAccount.gT.currentTimeMillis() / 1000L >= this.gY - 300L;
    }
    
    public long zzahe() {
        return this.gY;
    }
    
    @NonNull
    public String zzahf() {
        return this.gZ;
    }
    
    public String zzahg() {
        return this.zzahi().toString();
    }
    
    public String zzahh() {
        final JSONObject zzahi = this.zzahi();
        zzahi.remove("serverAuthCode");
        return zzahi.toString();
    }
    
    public GoogleSignInAccount zzfx(final String gx) {
        this.gX = gx;
        return this;
    }
}
