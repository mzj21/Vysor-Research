// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin.internal;

import org.json.JSONException;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.zzac;
import android.content.Context;
import java.util.concurrent.locks.ReentrantLock;
import android.content.SharedPreferences;
import java.util.concurrent.locks.Lock;

public class zzk
{
    private static final Lock hI;
    private static zzk hJ;
    private final Lock hK;
    private final SharedPreferences hL;
    
    static {
        hI = new ReentrantLock();
    }
    
    zzk(final Context context) {
        this.hK = new ReentrantLock();
        this.hL = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }
    
    public static zzk zzbd(final Context context) {
        zzac.zzy(context);
        zzk.hI.lock();
        try {
            if (zzk.hJ == null) {
                zzk.hJ = new zzk(context.getApplicationContext());
            }
            return zzk.hJ;
        }
        finally {
            zzk.hI.unlock();
        }
    }
    
    private String zzy(final String s, final String s2) {
        final String value = String.valueOf(":");
        return new StringBuilder(0 + String.valueOf(s).length() + String.valueOf(value).length() + String.valueOf(s2).length()).append(s).append(value).append(s2).toString();
    }
    
    void zza(final GoogleSignInAccount googleSignInAccount, final GoogleSignInOptions googleSignInOptions) {
        zzac.zzy(googleSignInAccount);
        zzac.zzy(googleSignInOptions);
        final String zzahf = googleSignInAccount.zzahf();
        this.zzx(this.zzy("googleSignInAccount", zzahf), googleSignInAccount.zzahh());
        this.zzx(this.zzy("googleSignInOptions", zzahf), googleSignInOptions.zzahg());
    }
    
    public GoogleSignInAccount zzaic() {
        return this.zzga(this.zzgc("defaultGoogleSignInAccount"));
    }
    
    public GoogleSignInOptions zzaid() {
        return this.zzgb(this.zzgc("defaultGoogleSignInAccount"));
    }
    
    public void zzaie() {
        final String zzgc = this.zzgc("defaultGoogleSignInAccount");
        this.zzge("defaultGoogleSignInAccount");
        this.zzgd(zzgc);
    }
    
    public void zzb(final GoogleSignInAccount googleSignInAccount, final GoogleSignInOptions googleSignInOptions) {
        zzac.zzy(googleSignInAccount);
        zzac.zzy(googleSignInOptions);
        this.zzx("defaultGoogleSignInAccount", googleSignInAccount.zzahf());
        this.zza(googleSignInAccount, googleSignInOptions);
    }
    
    GoogleSignInAccount zzga(final String s) {
        final boolean empty = TextUtils.isEmpty((CharSequence)s);
        GoogleSignInAccount zzfw = null;
        if (!empty) {
            final String zzgc = this.zzgc(this.zzy("googleSignInAccount", s));
            zzfw = null;
            if (zzgc != null) {
                try {
                    zzfw = GoogleSignInAccount.zzfw(zzgc);
                }
                catch (JSONException ex) {
                    zzfw = null;
                }
            }
        }
        return zzfw;
    }
    
    GoogleSignInOptions zzgb(final String s) {
        final boolean empty = TextUtils.isEmpty((CharSequence)s);
        GoogleSignInOptions zzfy = null;
        if (!empty) {
            final String zzgc = this.zzgc(this.zzy("googleSignInOptions", s));
            zzfy = null;
            if (zzgc != null) {
                try {
                    zzfy = GoogleSignInOptions.zzfy(zzgc);
                }
                catch (JSONException ex) {
                    zzfy = null;
                }
            }
        }
        return zzfy;
    }
    
    protected String zzgc(final String s) {
        this.hK.lock();
        try {
            return this.hL.getString(s, (String)null);
        }
        finally {
            this.hK.unlock();
        }
    }
    
    void zzgd(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            this.zzge(this.zzy("googleSignInAccount", s));
            this.zzge(this.zzy("googleSignInOptions", s));
        }
    }
    
    protected void zzge(final String s) {
        this.hK.lock();
        try {
            this.hL.edit().remove(s).apply();
        }
        finally {
            this.hK.unlock();
        }
    }
    
    protected void zzx(final String s, final String s2) {
        this.hK.lock();
        try {
            this.hL.edit().putString(s, s2).apply();
        }
        finally {
            this.hK.unlock();
        }
    }
}
