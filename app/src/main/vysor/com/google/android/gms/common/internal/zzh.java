// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import android.content.Context;
import java.util.Iterator;
import java.util.Collection;
import java.util.HashSet;
import java.util.Collections;
import android.view.View;
import android.accounts.Account;
import com.google.android.gms.internal.zzxa;
import com.google.android.gms.common.api.Api;
import java.util.Map;
import com.google.android.gms.common.api.Scope;
import java.util.Set;

public final class zzh
{
    private final Set<Scope> BX;
    private final Map<Api<?>, zza> BY;
    private final zzxa BZ;
    private Integer Ca;
    private final Account ec;
    private final String fo;
    private final Set<Scope> vF;
    private final int vH;
    private final View vI;
    private final String vJ;
    
    public zzh(final Account ec, final Set<Scope> set, Map<Api<?>, zza> empty_MAP, final int vh, final View vi, final String fo, final String vj, final zzxa bz) {
        this.ec = ec;
        Set<Scope> vf;
        if (set == null) {
            vf = (Set<Scope>)Collections.EMPTY_SET;
        }
        else {
            vf = Collections.unmodifiableSet((Set<? extends Scope>)set);
        }
        this.vF = vf;
        if (empty_MAP == null) {
            empty_MAP = Collections.EMPTY_MAP;
        }
        this.BY = (Map<Api<?>, zza>)empty_MAP;
        this.vI = vi;
        this.vH = vh;
        this.fo = fo;
        this.vJ = vj;
        this.BZ = bz;
        final HashSet<Scope> set2 = new HashSet<Scope>(this.vF);
        final Iterator<zza> iterator = this.BY.values().iterator();
        while (iterator.hasNext()) {
            set2.addAll((Collection<?>)iterator.next().hm);
        }
        this.BX = (Set<Scope>)Collections.unmodifiableSet((Set<?>)set2);
    }
    
    public static zzh zzcd(final Context context) {
        return new GoogleApiClient.Builder(context).zzaqd();
    }
    
    public Account getAccount() {
        return this.ec;
    }
    
    @Deprecated
    public String getAccountName() {
        String name;
        if (this.ec != null) {
            name = this.ec.name;
        }
        else {
            name = null;
        }
        return name;
    }
    
    public Account zzatv() {
        Account ec;
        if (this.ec != null) {
            ec = this.ec;
        }
        else {
            ec = new Account("<<default account>>", "com.google");
        }
        return ec;
    }
    
    public int zzauf() {
        return this.vH;
    }
    
    public Set<Scope> zzaug() {
        return this.vF;
    }
    
    public Set<Scope> zzauh() {
        return this.BX;
    }
    
    public Map<Api<?>, zza> zzaui() {
        return this.BY;
    }
    
    public String zzauj() {
        return this.fo;
    }
    
    public String zzauk() {
        return this.vJ;
    }
    
    public View zzaul() {
        return this.vI;
    }
    
    public zzxa zzaum() {
        return this.BZ;
    }
    
    public Integer zzaun() {
        return this.Ca;
    }
    
    public Set<Scope> zzb(final Api<?> api) {
        final zza zza = this.BY.get(api);
        Set<Scope> vf;
        if (zza == null || zza.hm.isEmpty()) {
            vf = this.vF;
        }
        else {
            final HashSet<Scope> set = new HashSet<Scope>(this.vF);
            set.addAll((Collection<?>)zza.hm);
            vf = set;
        }
        return vf;
    }
    
    public void zzc(final Integer ca) {
        this.Ca = ca;
    }
    
    public static final class zza
    {
        public final boolean Cb;
        public final Set<Scope> hm;
        
        public zza(final Set<Scope> set, final boolean cb) {
            zzac.zzy(set);
            this.hm = Collections.unmodifiableSet((Set<? extends Scope>)set);
            this.Cb = cb;
        }
    }
}
