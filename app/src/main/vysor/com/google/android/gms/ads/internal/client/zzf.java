// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import android.location.Location;
import java.util.List;
import android.os.Bundle;
import com.google.android.gms.internal.zziy;

@zziy
public final class zzf
{
    private Bundle mExtras;
    private boolean zzami;
    private long zzawt;
    private int zzawu;
    private List<String> zzawv;
    private boolean zzaww;
    private int zzawx;
    private String zzawy;
    private SearchAdRequestParcel zzawz;
    private String zzaxa;
    private Bundle zzaxb;
    private Bundle zzaxc;
    private List<String> zzaxd;
    private String zzaxe;
    private String zzaxf;
    private boolean zzaxg;
    private Location zzgr;
    
    public zzf() {
        this.zzawt = -1L;
        this.mExtras = new Bundle();
        this.zzawu = -1;
        this.zzawv = new ArrayList<String>();
        this.zzaww = false;
        this.zzawx = -1;
        this.zzami = false;
        this.zzawy = null;
        this.zzawz = null;
        this.zzgr = null;
        this.zzaxa = null;
        this.zzaxb = new Bundle();
        this.zzaxc = new Bundle();
        this.zzaxd = new ArrayList<String>();
        this.zzaxe = null;
        this.zzaxf = null;
        this.zzaxg = false;
    }
    
    public zzf(final AdRequestParcel adRequestParcel) {
        this.zzawt = adRequestParcel.zzawd;
        this.mExtras = adRequestParcel.extras;
        this.zzawu = adRequestParcel.zzawe;
        this.zzawv = adRequestParcel.zzawf;
        this.zzaww = adRequestParcel.zzawg;
        this.zzawx = adRequestParcel.zzawh;
        this.zzami = adRequestParcel.zzawi;
        this.zzawy = adRequestParcel.zzawj;
        this.zzawz = adRequestParcel.zzawk;
        this.zzgr = adRequestParcel.zzawl;
        this.zzaxa = adRequestParcel.zzawm;
        this.zzaxb = adRequestParcel.zzawn;
        this.zzaxc = adRequestParcel.zzawo;
        this.zzaxd = adRequestParcel.zzawp;
        this.zzaxe = adRequestParcel.zzawq;
        this.zzaxf = adRequestParcel.zzawr;
    }
    
    public zzf zza(@Nullable final Location zzgr) {
        this.zzgr = zzgr;
        return this;
    }
    
    public AdRequestParcel zzja() {
        return new AdRequestParcel(7, this.zzawt, this.mExtras, this.zzawu, this.zzawv, this.zzaww, this.zzawx, this.zzami, this.zzawy, this.zzawz, this.zzgr, this.zzaxa, this.zzaxb, this.zzaxc, this.zzaxd, this.zzaxe, this.zzaxf, false);
    }
}
