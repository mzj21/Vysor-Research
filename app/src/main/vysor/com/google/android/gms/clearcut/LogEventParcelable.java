// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.clearcut;

import android.os.Parcel;
import java.util.Arrays;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzarp;
import com.google.android.gms.playlog.internal.PlayLoggerContext;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class LogEventParcelable extends AbstractSafeParcelable
{
    public static final zzd CREATOR;
    public PlayLoggerContext uc;
    public byte[] ud;
    public int[] ue;
    public String[] uf;
    public int[] ug;
    public byte[][] uh;
    public boolean ui;
    public final zzarp.zzd uj;
    public final zzb.zzc uk;
    public final zzb.zzc ul;
    public final int versionCode;
    
    static {
        CREATOR = new zzd();
    }
    
    LogEventParcelable(final int versionCode, final PlayLoggerContext uc, final byte[] ud, final int[] ue, final String[] uf, final int[] ug, final byte[][] uh, final boolean ui) {
        this.versionCode = versionCode;
        this.uc = uc;
        this.ud = ud;
        this.ue = ue;
        this.uf = uf;
        this.uj = null;
        this.uk = null;
        this.ul = null;
        this.ug = ug;
        this.uh = uh;
        this.ui = ui;
    }
    
    public LogEventParcelable(final PlayLoggerContext uc, final zzarp.zzd uj, final zzb.zzc uk, final zzb.zzc ul, final int[] ue, final String[] uf, final int[] ug, final byte[][] uh, final boolean ui) {
        this.versionCode = 1;
        this.uc = uc;
        this.uj = uj;
        this.uk = uk;
        this.ul = ul;
        this.ue = ue;
        this.uf = uf;
        this.ug = ug;
        this.uh = uh;
        this.ui = ui;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        if (this != o) {
            if (o instanceof LogEventParcelable) {
                final LogEventParcelable logEventParcelable = (LogEventParcelable)o;
                if (this.versionCode != logEventParcelable.versionCode || !zzab.equal(this.uc, logEventParcelable.uc) || !Arrays.equals(this.ud, logEventParcelable.ud) || !Arrays.equals(this.ue, logEventParcelable.ue) || !Arrays.equals(this.uf, logEventParcelable.uf) || !zzab.equal(this.uj, logEventParcelable.uj) || !zzab.equal(this.uk, logEventParcelable.uk) || !zzab.equal(this.ul, logEventParcelable.ul) || !Arrays.equals(this.ug, logEventParcelable.ug) || !Arrays.deepEquals(this.uh, logEventParcelable.uh) || this.ui != logEventParcelable.ui) {
                    b = false;
                }
            }
            else {
                b = false;
            }
        }
        return b;
    }
    
    @Override
    public int hashCode() {
        return zzab.hashCode(this.versionCode, this.uc, this.ud, this.ue, this.uf, this.uj, this.uk, this.ul, this.ug, this.uh, this.ui);
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder("LogEventParcelable[").append(this.versionCode).append(", ").append(this.uc).append(", ").append("LogEventBytes: ");
        String s;
        if (this.ud == null) {
            s = null;
        }
        else {
            s = new String(this.ud);
        }
        return append.append(s).append(", ").append("TestCodes: ").append(Arrays.toString(this.ue)).append(", ").append("MendelPackages: ").append(Arrays.toString(this.uf)).append(", ").append("LogEvent: ").append(this.uj).append(", ").append("ExtensionProducer: ").append(this.uk).append(", ").append("VeProducer: ").append(this.ul).append(", ").append("ExperimentIDs: ").append(Arrays.toString(this.ug)).append(", ").append("ExperimentTokens: ").append(Arrays.toString(this.uh)).append(", ").append("AddPhenotypeExperimentTokens: ").append(this.ui).append("]").toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzd.zza(this, parcel, n);
    }
}
