// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.util.zzs;
import android.text.TextUtils;
import com.google.android.gms.common.stats.zzf;
import com.google.android.gms.common.stats.zzh;
import android.util.Log;
import com.google.android.gms.common.util.zzw;
import com.google.android.gms.common.util.zzz;
import android.os.PowerManager;
import com.google.android.gms.common.internal.zzac;
import android.content.Context;
import android.os.WorkSource;
import android.os.PowerManager$WakeLock;

public class zzxb
{
    private static boolean DEBUG;
    private static String TAG;
    private static String aAo;
    private final String EA;
    private final String Ey;
    private final PowerManager$WakeLock aAp;
    private final int aAq;
    private final String aAr;
    private boolean aAs;
    private int aAt;
    private int aAu;
    private WorkSource agC;
    private final Context mContext;
    
    static {
        zzxb.TAG = "WakeLock";
        zzxb.aAo = "*gcore*:";
        zzxb.DEBUG = false;
    }
    
    public zzxb(final Context context, final int n, final String s) {
        String packageName;
        if (context == null) {
            packageName = null;
        }
        else {
            packageName = context.getPackageName();
        }
        this(context, n, s, null, packageName);
    }
    
    public zzxb(final Context context, final int n, final String s, final String s2, final String s3) {
        this(context, n, s, s2, s3, null);
    }
    
    public zzxb(final Context context, final int aAq, final String ey, final String aAr, String packageName, final String ea) {
        this.aAs = true;
        zzac.zzh(ey, "Wake lock name can NOT be empty");
        this.aAq = aAq;
        this.aAr = aAr;
        this.EA = ea;
        this.mContext = context.getApplicationContext();
        if (!"com.google.android.gms".equals(context.getPackageName())) {
            final String value = String.valueOf(zzxb.aAo);
            final String value2 = String.valueOf(ey);
            String concat;
            if (value2.length() != 0) {
                concat = value.concat(value2);
            }
            else {
                concat = new String(value);
            }
            this.Ey = concat;
        }
        else {
            this.Ey = ey;
        }
        this.aAp = ((PowerManager)context.getSystemService("power")).newWakeLock(aAq, ey);
        if (zzz.zzcp(this.mContext)) {
            if (zzw.zzij(packageName)) {
                packageName = context.getPackageName();
            }
            this.zzc(this.agC = zzz.zzy(context, packageName));
        }
    }
    
    private void zzd(final WorkSource workSource) {
        try {
            this.aAp.setWorkSource(workSource);
        }
        catch (IllegalArgumentException ex) {
            Log.wtf(zzxb.TAG, ex.toString());
        }
    }
    
    private void zzm(final String s, final long n) {
        final boolean zzop = this.zzop(s);
        final String zzp = this.zzp(s, zzop);
        synchronized (this) {
            if ((this.aAs && (this.aAt++ == 0 || zzop)) || (!this.aAs && this.aAu == 0)) {
                zzh.zzaxf().zza(this.mContext, zzf.zza(this.aAp, zzp), 7, this.Ey, zzp, this.EA, this.aAq, zzz.zzb(this.agC), n);
                ++this.aAu;
            }
        }
    }
    
    private void zzoo(final String s) {
        final boolean zzop = this.zzop(s);
        final String zzp = this.zzp(s, zzop);
        synchronized (this) {
            Label_0060: {
                if (this.aAs) {
                    final int aAt = -1 + this.aAt;
                    this.aAt = aAt;
                    if (aAt == 0 || zzop) {
                        break Label_0060;
                    }
                }
                if (this.aAs || this.aAu != 1) {
                    return;
                }
            }
            zzh.zzaxf().zza(this.mContext, zzf.zza(this.aAp, zzp), 8, this.Ey, zzp, this.EA, this.aAq, zzz.zzb(this.agC));
            --this.aAu;
        }
    }
    
    private boolean zzop(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && !s.equals(this.aAr);
    }
    
    private String zzp(String s, final boolean b) {
        if (this.aAs) {
            if (!b) {
                s = this.aAr;
            }
        }
        else {
            s = this.aAr;
        }
        return s;
    }
    
    public void acquire(final long n) {
        if (!zzs.zzaxn() && this.aAs) {
            final String tag = zzxb.TAG;
            final String value = String.valueOf(this.Ey);
            String concat;
            if (value.length() != 0) {
                concat = "Do not acquire with timeout on reference counted WakeLocks before ICS. wakelock: ".concat(value);
            }
            else {
                concat = new String("Do not acquire with timeout on reference counted WakeLocks before ICS. wakelock: ");
            }
            Log.wtf(tag, concat);
        }
        this.zzm(null, n);
        this.aAp.acquire(n);
    }
    
    public boolean isHeld() {
        return this.aAp.isHeld();
    }
    
    public void release() {
        this.zzoo(null);
        this.aAp.release();
    }
    
    public void setReferenceCounted(final boolean b) {
        this.aAp.setReferenceCounted(b);
        this.aAs = b;
    }
    
    public void zzc(final WorkSource agC) {
        if (agC != null && zzz.zzcp(this.mContext)) {
            if (this.agC != null) {
                this.agC.add(agC);
            }
            else {
                this.agC = agC;
            }
            this.zzd(this.agC);
        }
    }
}
