// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import java.util.HashMap;
import java.util.HashSet;
import android.content.Context;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import java.util.Collections;
import android.location.Location;
import java.util.Date;
import java.util.Set;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.ads.mediation.NetworkExtras;
import java.util.Map;
import android.os.Bundle;
import com.google.android.gms.internal.zziy;

@zziy
public final class zzad
{
    public static final String DEVICE_ID_EMULATOR;
    private final boolean zzami;
    private final int zzawu;
    private final int zzawx;
    private final String zzawy;
    private final String zzaxa;
    private final Bundle zzaxc;
    private final String zzaxe;
    private final boolean zzaxg;
    private final Bundle zzayj;
    private final Map<Class<? extends NetworkExtras>, NetworkExtras> zzayk;
    private final SearchAdRequest zzayl;
    private final Set<String> zzaym;
    private final Set<String> zzayn;
    private final Date zzgn;
    private final Set<String> zzgp;
    private final Location zzgr;
    
    static {
        DEVICE_ID_EMULATOR = zzm.zzjr().zzdc("emulator");
    }
    
    public zzad(final zza zza) {
        this(zza, null);
    }
    
    public zzad(final zza zza, final SearchAdRequest zzayl) {
        this.zzgn = zza.zzgn;
        this.zzaxa = zza.zzaxa;
        this.zzawu = zza.zzawu;
        this.zzgp = Collections.unmodifiableSet((Set<? extends String>)zza.zzayo);
        this.zzgr = zza.zzgr;
        this.zzami = zza.zzami;
        this.zzayj = zza.zzayj;
        this.zzayk = Collections.unmodifiableMap((Map<? extends Class<? extends NetworkExtras>, ? extends NetworkExtras>)zza.zzayp);
        this.zzawy = zza.zzawy;
        this.zzaxe = zza.zzaxe;
        this.zzayl = zzayl;
        this.zzawx = zza.zzawx;
        this.zzaym = Collections.unmodifiableSet((Set<? extends String>)zza.zzayq);
        this.zzaxc = zza.zzaxc;
        this.zzayn = Collections.unmodifiableSet((Set<? extends String>)zza.zzayr);
        this.zzaxg = zza.zzaxg;
    }
    
    public Date getBirthday() {
        return this.zzgn;
    }
    
    public String getContentUrl() {
        return this.zzaxa;
    }
    
    public Bundle getCustomEventExtrasBundle(final Class<? extends CustomEvent> clazz) {
        final Bundle bundle = this.zzayj.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter");
        Bundle bundle2;
        if (bundle != null) {
            bundle2 = bundle.getBundle(clazz.getName());
        }
        else {
            bundle2 = null;
        }
        return bundle2;
    }
    
    public Bundle getCustomTargeting() {
        return this.zzaxc;
    }
    
    public int getGender() {
        return this.zzawu;
    }
    
    public Set<String> getKeywords() {
        return this.zzgp;
    }
    
    public Location getLocation() {
        return this.zzgr;
    }
    
    public boolean getManualImpressionsEnabled() {
        return this.zzami;
    }
    
    @Deprecated
    public <T extends NetworkExtras> T getNetworkExtras(final Class<T> clazz) {
        return (T)this.zzayk.get(clazz);
    }
    
    public Bundle getNetworkExtrasBundle(final Class<? extends MediationAdapter> clazz) {
        return this.zzayj.getBundle(clazz.getName());
    }
    
    public String getPublisherProvidedId() {
        return this.zzawy;
    }
    
    public boolean isDesignedForFamilies() {
        return this.zzaxg;
    }
    
    public boolean isTestDevice(final Context context) {
        return this.zzaym.contains(zzm.zzjr().zzar(context));
    }
    
    public String zzjz() {
        return this.zzaxe;
    }
    
    public SearchAdRequest zzka() {
        return this.zzayl;
    }
    
    public Map<Class<? extends NetworkExtras>, NetworkExtras> zzkb() {
        return this.zzayk;
    }
    
    public Bundle zzkc() {
        return this.zzayj;
    }
    
    public int zzkd() {
        return this.zzawx;
    }
    
    public Set<String> zzke() {
        return this.zzayn;
    }
    
    public static final class zza
    {
        private boolean zzami;
        private int zzawu;
        private int zzawx;
        private String zzawy;
        private String zzaxa;
        private final Bundle zzaxc;
        private String zzaxe;
        private boolean zzaxg;
        private final Bundle zzayj;
        private final HashSet<String> zzayo;
        private final HashMap<Class<? extends NetworkExtras>, NetworkExtras> zzayp;
        private final HashSet<String> zzayq;
        private final HashSet<String> zzayr;
        private Date zzgn;
        private Location zzgr;
        
        public zza() {
            this.zzayo = new HashSet<String>();
            this.zzayj = new Bundle();
            this.zzayp = new HashMap<Class<? extends NetworkExtras>, NetworkExtras>();
            this.zzayq = new HashSet<String>();
            this.zzaxc = new Bundle();
            this.zzayr = new HashSet<String>();
            this.zzawu = -1;
            this.zzami = false;
            this.zzawx = -1;
        }
        
        public void setManualImpressionsEnabled(final boolean zzami) {
            this.zzami = zzami;
        }
        
        @Deprecated
        public void zza(final NetworkExtras networkExtras) {
            if (networkExtras instanceof AdMobExtras) {
                this.zza(AdMobAdapter.class, ((AdMobExtras)networkExtras).getExtras());
            }
            else {
                this.zzayp.put(networkExtras.getClass(), networkExtras);
            }
        }
        
        public void zza(final Class<? extends MediationAdapter> clazz, final Bundle bundle) {
            this.zzayj.putBundle(clazz.getName(), bundle);
        }
        
        public void zza(final Date zzgn) {
            this.zzgn = zzgn;
        }
        
        public void zzai(final String s) {
            this.zzayo.add(s);
        }
        
        public void zzaj(final String s) {
            this.zzayq.add(s);
        }
        
        public void zzak(final String s) {
            this.zzayq.remove(s);
        }
        
        public void zzal(final String zzaxa) {
            this.zzaxa = zzaxa;
        }
        
        public void zzam(final String zzawy) {
            this.zzawy = zzawy;
        }
        
        public void zzan(final String zzaxe) {
            this.zzaxe = zzaxe;
        }
        
        public void zzao(final String s) {
            this.zzayr.add(s);
        }
        
        public void zzb(final Location zzgr) {
            this.zzgr = zzgr;
        }
        
        public void zzb(final Class<? extends CustomEvent> clazz, final Bundle bundle) {
            if (this.zzayj.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter") == null) {
                this.zzayj.putBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter", new Bundle());
            }
            this.zzayj.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter").putBundle(clazz.getName(), bundle);
        }
        
        public void zzf(final String s, final String s2) {
            this.zzaxc.putString(s, s2);
        }
        
        public void zzo(final boolean b) {
            int zzawx;
            if (b) {
                zzawx = 1;
            }
            else {
                zzawx = 0;
            }
            this.zzawx = zzawx;
        }
        
        public void zzp(final boolean zzaxg) {
            this.zzaxg = zzaxg;
        }
        
        public void zzv(final int zzawu) {
            this.zzawu = zzawu;
        }
    }
}
