// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import android.os.Parcelable;
import android.content.Intent;
import com.google.android.gms.common.util.zzj;
import android.os.SystemClock;
import android.util.Log;
import android.text.TextUtils;
import java.util.List;
import com.google.android.gms.common.util.zzd;
import android.content.Context;

public class zzh
{
    private static zzh EH;
    private static Boolean EI;
    private static String TAG;
    
    static {
        zzh.TAG = "WakeLockTracker";
        zzh.EH = new zzh();
    }
    
    public static zzh zzaxf() {
        return zzh.EH;
    }
    
    private static boolean zzcj(final Context context) {
        if (zzh.EI == null) {
            zzh.EI = zzck(context);
        }
        return zzh.EI;
    }
    
    private static boolean zzck(final Context context) {
        try {
            final boolean zzact = zzd.zzact();
            boolean b = false;
            if (zzact) {
                b = (zzc.zzb.Eh.get() != com.google.android.gms.common.stats.zzd.LOG_LEVEL_OFF);
            }
            return b;
        }
        catch (SecurityException ex) {
            return false;
        }
    }
    
    public void zza(final Context context, final String s, final int n, final String s2, final String s3, final String s4, final int n2, final List<String> list) {
        this.zza(context, s, n, s2, s3, s4, n2, list, 0L);
    }
    
    public void zza(final Context context, final String s, final int n, final String s2, final String s3, final String s4, final int n2, final List<String> list, final long n3) {
        if (zzcj(context)) {
            if (TextUtils.isEmpty((CharSequence)s)) {
                final String tag = zzh.TAG;
                final String value = String.valueOf(s);
                String concat;
                if (value.length() != 0) {
                    concat = "missing wakeLock key. ".concat(value);
                }
                else {
                    concat = new String("missing wakeLock key. ");
                }
                Log.e(tag, concat);
            }
            else {
                final long currentTimeMillis = System.currentTimeMillis();
                if (7 == n || 8 == n || 10 == n || 11 == n) {
                    final WakeLockEvent wakeLockEvent = new WakeLockEvent(currentTimeMillis, n, s2, n2, zzf.zzz(list), s, SystemClock.elapsedRealtime(), zzj.zzcn(context), s3, zzf.zzih(context.getPackageName()), zzj.zzco(context), n3, s4);
                    try {
                        context.startService(new Intent().setComponent(com.google.android.gms.common.stats.zzd.En).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", (Parcelable)wakeLockEvent));
                    }
                    catch (Exception ex) {
                        Log.wtf(zzh.TAG, (Throwable)ex);
                    }
                }
            }
        }
    }
}
