// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin;

import java.util.Arrays;
import com.google.android.gms.common.internal.zzac;
import android.support.annotation.NonNull;
import android.os.Parcel;
import com.google.android.gms.auth.api.signin.internal.zze;
import java.util.HashSet;
import android.support.annotation.Nullable;
import java.util.Iterator;
import android.text.TextUtils;
import org.json.JSONException;
import java.util.List;
import java.util.Collections;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Collection;
import java.util.Set;
import java.util.ArrayList;
import android.accounts.Account;
import com.google.android.gms.common.api.Scope;
import java.util.Comparator;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class GoogleSignInOptions extends AbstractSafeParcelable implements Optional, ReflectedParcelable
{
    public static final Parcelable$Creator<GoogleSignInOptions> CREATOR;
    public static final GoogleSignInOptions DEFAULT_SIGN_IN;
    private static Comparator<Scope> hc;
    public static final Scope hd;
    public static final Scope he;
    public static final Scope hf;
    private Account ec;
    private final ArrayList<Scope> hg;
    private boolean hh;
    private final boolean hi;
    private final boolean hj;
    private String hk;
    private String hl;
    final int versionCode;
    
    static {
        hd = new Scope("profile");
        he = new Scope("email");
        hf = new Scope("openid");
        DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
        CREATOR = (Parcelable$Creator)new com.google.android.gms.auth.api.signin.zzb();
        GoogleSignInOptions.hc = new Comparator<Scope>() {
            public int zza(final Scope scope, final Scope scope2) {
                return scope.zzaqg().compareTo(scope2.zzaqg());
            }
        };
    }
    
    GoogleSignInOptions(final int versionCode, final ArrayList<Scope> hg, final Account ec, final boolean hh, final boolean hi, final boolean hj, final String hk, final String hl) {
        this.versionCode = versionCode;
        this.hg = hg;
        this.ec = ec;
        this.hh = hh;
        this.hi = hi;
        this.hj = hj;
        this.hk = hk;
        this.hl = hl;
    }
    
    private GoogleSignInOptions(final Set<Scope> set, final Account account, final boolean b, final boolean b2, final boolean b3, final String s, final String s2) {
        this(2, new ArrayList<Scope>(set), account, b, b2, b3, s, s2);
    }
    
    private JSONObject zzahi() {
        final JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray();
            Collections.sort(this.hg, GoogleSignInOptions.hc);
            final Iterator<Scope> iterator = this.hg.iterator();
            while (iterator.hasNext()) {
                jsonArray.put((Object)iterator.next().zzaqg());
            }
        }
        catch (JSONException ex) {
            throw new RuntimeException((Throwable)ex);
        }
        jsonObject.put("scopes", (Object)jsonArray);
        if (this.ec != null) {
            jsonObject.put("accountName", (Object)this.ec.name);
        }
        jsonObject.put("idTokenRequested", this.hh);
        jsonObject.put("forceCodeForRefreshToken", this.hj);
        jsonObject.put("serverAuthRequested", this.hi);
        if (!TextUtils.isEmpty((CharSequence)this.hk)) {
            jsonObject.put("serverClientId", (Object)this.hk);
        }
        if (!TextUtils.isEmpty((CharSequence)this.hl)) {
            jsonObject.put("hostedDomain", (Object)this.hl);
        }
        return jsonObject;
    }
    
    @Nullable
    public static GoogleSignInOptions zzfy(@Nullable final String s) throws JSONException {
        final boolean empty = TextUtils.isEmpty((CharSequence)s);
        GoogleSignInOptions googleSignInOptions = null;
        if (!empty) {
            final JSONObject jsonObject = new JSONObject(s);
            final HashSet<Scope> set = new HashSet<Scope>();
            final JSONArray jsonArray = jsonObject.getJSONArray("scopes");
            for (int length = jsonArray.length(), i = 0; i < length; ++i) {
                set.add(new Scope(jsonArray.getString(i)));
            }
            final String optString = jsonObject.optString("accountName", (String)null);
            Account account;
            if (!TextUtils.isEmpty((CharSequence)optString)) {
                account = new Account(optString, "com.google");
            }
            else {
                account = null;
            }
            googleSignInOptions = new GoogleSignInOptions(set, account, jsonObject.getBoolean("idTokenRequested"), jsonObject.getBoolean("serverAuthRequested"), jsonObject.getBoolean("forceCodeForRefreshToken"), jsonObject.optString("serverClientId", (String)null), jsonObject.optString("hostedDomain", (String)null));
        }
        return googleSignInOptions;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = false;
        if (o != null) {
            try {
                final GoogleSignInOptions googleSignInOptions = (GoogleSignInOptions)o;
                final int size = this.hg.size();
                final int size2 = googleSignInOptions.zzahj().size();
                b = false;
                if (size == size2) {
                    final boolean containsAll = this.hg.containsAll(googleSignInOptions.zzahj());
                    b = false;
                    if (containsAll) {
                        final Account ec = this.ec;
                        b = false;
                        if (ec == null) {
                            final Account account = googleSignInOptions.getAccount();
                            b = false;
                            if (account != null) {
                                return b;
                            }
                        }
                        else {
                            final boolean equals = this.ec.equals((Object)googleSignInOptions.getAccount());
                            b = false;
                            if (!equals) {
                                return b;
                            }
                            b = false;
                        }
                        final boolean empty = TextUtils.isEmpty((CharSequence)this.hk);
                        b = false;
                        if (empty) {
                            final boolean empty2 = TextUtils.isEmpty((CharSequence)googleSignInOptions.zzahn());
                            b = false;
                            if (!empty2) {
                                return b;
                            }
                        }
                        else {
                            final boolean equals2 = this.hk.equals(googleSignInOptions.zzahn());
                            b = false;
                            if (!equals2) {
                                return b;
                            }
                        }
                        final boolean hj = this.hj;
                        final boolean zzahm = googleSignInOptions.zzahm();
                        b = false;
                        if (hj == zzahm) {
                            final boolean hh = this.hh;
                            final boolean zzahk = googleSignInOptions.zzahk();
                            b = false;
                            if (hh == zzahk) {
                                final boolean hi = this.hi;
                                final boolean zzahl = googleSignInOptions.zzahl();
                                b = false;
                                if (hi == zzahl) {
                                    b = true;
                                }
                            }
                        }
                    }
                }
            }
            catch (ClassCastException ex) {}
        }
        return b;
    }
    
    public Account getAccount() {
        return this.ec;
    }
    
    public Scope[] getScopeArray() {
        return this.hg.toArray(new Scope[this.hg.size()]);
    }
    
    @Override
    public int hashCode() {
        final ArrayList<String> list = (ArrayList<String>)new ArrayList<Comparable>();
        final Iterator<Scope> iterator = this.hg.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().zzaqg());
        }
        Collections.sort((List<Comparable>)list);
        return new com.google.android.gms.auth.api.signin.internal.zze().zzq(list).zzq(this.ec).zzq(this.hk).zzbd(this.hj).zzbd(this.hh).zzbd(this.hi).zzahv();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        com.google.android.gms.auth.api.signin.zzb.zza(this, parcel, n);
    }
    
    public String zzahg() {
        return this.zzahi().toString();
    }
    
    public ArrayList<Scope> zzahj() {
        return new ArrayList<Scope>(this.hg);
    }
    
    public boolean zzahk() {
        return this.hh;
    }
    
    public boolean zzahl() {
        return this.hi;
    }
    
    public boolean zzahm() {
        return this.hj;
    }
    
    public String zzahn() {
        return this.hk;
    }
    
    public String zzaho() {
        return this.hl;
    }
    
    public static final class Builder
    {
        private Account ec;
        private boolean hh;
        private boolean hi;
        private boolean hj;
        private String hk;
        private String hl;
        private Set<Scope> hm;
        
        public Builder() {
            this.hm = new HashSet<Scope>();
        }
        
        public Builder(@NonNull final GoogleSignInOptions googleSignInOptions) {
            this.hm = new HashSet<Scope>();
            zzac.zzy(googleSignInOptions);
            this.hm = new HashSet<Scope>(googleSignInOptions.hg);
            this.hi = googleSignInOptions.hi;
            this.hj = googleSignInOptions.hj;
            this.hh = googleSignInOptions.hh;
            this.hk = googleSignInOptions.hk;
            this.ec = googleSignInOptions.ec;
            this.hl = googleSignInOptions.hl;
        }
        
        private String zzfz(final String s) {
            zzac.zzhz(s);
            zzac.zzb(this.hk == null || this.hk.equals(s), (Object)"two different server client ids provided");
            return s;
        }
        
        public GoogleSignInOptions build() {
            if (this.hh && (this.ec == null || !this.hm.isEmpty())) {
                this.requestId();
            }
            return new GoogleSignInOptions(this.hm, this.ec, this.hh, this.hi, this.hj, this.hk, this.hl, null);
        }
        
        public Builder requestEmail() {
            this.hm.add(GoogleSignInOptions.he);
            return this;
        }
        
        public Builder requestId() {
            this.hm.add(GoogleSignInOptions.hf);
            return this;
        }
        
        public Builder requestIdToken(final String s) {
            this.hh = true;
            this.hk = this.zzfz(s);
            return this;
        }
        
        public Builder requestProfile() {
            this.hm.add(GoogleSignInOptions.hd);
            return this;
        }
        
        public Builder requestScopes(final Scope scope, final Scope... array) {
            this.hm.add(scope);
            this.hm.addAll(Arrays.asList(array));
            return this;
        }
        
        public Builder requestServerAuthCode(final String s) {
            return this.requestServerAuthCode(s, false);
        }
        
        public Builder requestServerAuthCode(final String s, final boolean hj) {
            this.hi = true;
            this.hk = this.zzfz(s);
            this.hj = hj;
            return this;
        }
        
        public Builder setAccountName(final String s) {
            this.ec = new Account(zzac.zzhz(s), "com.google");
            return this;
        }
        
        public Builder setHostedDomain(final String s) {
            this.hl = zzac.zzhz(s);
            return this;
        }
    }
}
