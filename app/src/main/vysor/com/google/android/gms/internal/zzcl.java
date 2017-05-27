// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import org.json.JSONObject;

@zziy
public final class zzcl
{
    private final String zzatf;
    private final JSONObject zzatg;
    private final String zzath;
    private final String zzati;
    private final boolean zzatj;
    private final boolean zzatk;
    
    public zzcl(final String zzath, final VersionInfoParcel versionInfoParcel, final String zzatf, final JSONObject zzatg, final boolean zzatj, final boolean zzatk) {
        this.zzati = versionInfoParcel.zzcs;
        this.zzatg = zzatg;
        this.zzath = zzath;
        this.zzatf = zzatf;
        this.zzatj = zzatj;
        this.zzatk = zzatk;
    }
    
    public String zzhx() {
        return this.zzatf;
    }
    
    public String zzhy() {
        return this.zzati;
    }
    
    public JSONObject zzhz() {
        return this.zzatg;
    }
    
    public String zzia() {
        return this.zzath;
    }
    
    public boolean zzib() {
        return this.zzatj;
    }
    
    public boolean zzic() {
        return this.zzatk;
    }
}
