// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.flags.impl;

import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.dynamic.zze;
import android.content.Context;
import com.google.android.gms.dynamic.zzd;
import android.content.SharedPreferences;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.internal.zzuz;

@DynamiteApi
public class FlagProviderImpl extends zzuz.zza
{
    private boolean zzaom;
    private SharedPreferences zzbak;
    
    public FlagProviderImpl() {
        this.zzaom = false;
    }
    
    public boolean getBooleanFlagValue(final String s, boolean booleanValue, final int n) {
        if (this.zzaom) {
            booleanValue = zza.zza.zza(this.zzbak, s, booleanValue);
        }
        return booleanValue;
    }
    
    public int getIntFlagValue(final String s, int intValue, final int n) {
        if (this.zzaom) {
            intValue = com.google.android.gms.flags.impl.zza.zzb.zza(this.zzbak, s, intValue);
        }
        return intValue;
    }
    
    public long getLongFlagValue(final String s, long longValue, final int n) {
        if (this.zzaom) {
            longValue = zza.zzc.zza(this.zzbak, s, longValue);
        }
        return longValue;
    }
    
    public String getStringFlagValue(final String s, String zza, final int n) {
        if (this.zzaom) {
            zza = com.google.android.gms.flags.impl.zza.zzd.zza(this.zzbak, s, zza);
        }
        return zza;
    }
    
    public void init(final zzd zzd) {
        final Context context = zze.zzae(zzd);
        if (!this.zzaom) {
            try {
                this.zzbak = zzb.zzn(context.createPackageContext("com.google.android.gms", 0));
                this.zzaom = true;
            }
            catch (PackageManager$NameNotFoundException ex) {}
        }
    }
}
