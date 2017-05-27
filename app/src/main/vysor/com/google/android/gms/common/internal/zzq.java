// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.util.Log;

public final class zzq
{
    public static final int CO;
    private static final String CP;
    private final String CQ;
    private final String CR;
    
    static {
        CO = 23 - " PII_LOG".length();
        CP = null;
    }
    
    public zzq(final String s) {
        this(s, null);
    }
    
    public zzq(final String cq, final String cr) {
        zzac.zzb(cq, "log tag cannot be null");
        zzac.zzb(cq.length() <= 23, "tag \"%s\" is longer than the %d character maximum", cq, 23);
        this.CQ = cq;
        if (cr == null || cr.length() <= 0) {
            this.CR = null;
        }
        else {
            this.CR = cr;
        }
    }
    
    private String zzhx(String concat) {
        if (this.CR != null) {
            concat = this.CR.concat(concat);
        }
        return concat;
    }
    
    public void zzae(final String s, final String s2) {
        if (this.zzgp(3)) {
            Log.d(s, this.zzhx(s2));
        }
    }
    
    public void zzaf(final String s, final String s2) {
        if (this.zzgp(5)) {
            Log.w(s, this.zzhx(s2));
        }
    }
    
    public void zzag(final String s, final String s2) {
        if (this.zzgp(6)) {
            Log.e(s, this.zzhx(s2));
        }
    }
    
    public void zzb(final String s, final String s2, final Throwable t) {
        if (this.zzgp(4)) {
            Log.i(s, this.zzhx(s2), t);
        }
    }
    
    public void zzc(final String s, final String s2, final Throwable t) {
        if (this.zzgp(5)) {
            Log.w(s, this.zzhx(s2), t);
        }
    }
    
    public void zzd(final String s, final String s2, final Throwable t) {
        if (this.zzgp(6)) {
            Log.e(s, this.zzhx(s2), t);
        }
    }
    
    public void zze(final String s, final String s2, final Throwable t) {
        if (this.zzgp(7)) {
            Log.e(s, this.zzhx(s2), t);
            Log.wtf(s, this.zzhx(s2), t);
        }
    }
    
    public boolean zzgp(final int n) {
        return Log.isLoggable(this.CQ, n);
    }
}
