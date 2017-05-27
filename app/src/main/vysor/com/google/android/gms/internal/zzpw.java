// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzab;
import android.content.ContentResolver;
import android.util.Log;
import java.nio.ByteBuffer;
import com.google.android.gms.common.internal.zzac;
import android.content.Context;
import java.nio.charset.Charset;
import com.google.android.gms.clearcut.zzb;

public class zzpw implements com.google.android.gms.clearcut.zzb.zzb
{
    private static final Charset UTF_8;
    static Boolean uC;
    final zza uD;
    
    static {
        UTF_8 = Charset.forName("UTF-8");
        zzpw.uC = null;
    }
    
    public zzpw() {
        this(new zza(null));
    }
    
    public zzpw(final Context context) {
        this(new zza(context));
    }
    
    zzpw(final zza zza) {
        this.uD = zzac.zzy(zza);
    }
    
    static boolean zza(final long n, final long n2, final long n3) {
        if (n2 < 0L || n3 < 0L) {
            throw new IllegalArgumentException(new StringBuilder(72).append("negative values not supported: ").append(n2).append("/").append(n3).toString());
        }
        return n3 > 0L && zzpx.zzd(n, n3) < n2;
    }
    
    static long zzai(final long n) {
        return zzpt.zzm(ByteBuffer.allocate(8).putLong(n).array());
    }
    
    static long zzd(final String s, final long n) {
        long n2;
        if (s == null || s.isEmpty()) {
            n2 = zzai(n);
        }
        else {
            final byte[] bytes = s.getBytes(zzpw.UTF_8);
            final ByteBuffer allocate = ByteBuffer.allocate(8 + bytes.length);
            allocate.put(bytes);
            allocate.putLong(n);
            n2 = zzpt.zzm(allocate.array());
        }
        return n2;
    }
    
    static zzb zzhb(final String s) {
        zzb zzb;
        if (s == null) {
            zzb = null;
        }
        else {
            String substring = "";
            final int index = s.indexOf(44);
            int n = 0;
            if (index >= 0) {
                substring = s.substring(0, index);
                n = index + 1;
            }
            final int index2 = s.indexOf(47, n);
            if (index2 <= 0) {
                final String value = String.valueOf(s);
                String concat;
                if (value.length() != 0) {
                    concat = "Failed to parse the rule: ".concat(value);
                }
                else {
                    concat = new String("Failed to parse the rule: ");
                }
                Log.e("LogSamplerImpl", concat);
                zzb = null;
            }
            else {
                long long1;
                long long2;
                try {
                    long1 = Long.parseLong(s.substring(n, index2));
                    long2 = Long.parseLong(s.substring(index2 + 1));
                    if (long1 < 0L || long2 < 0L) {
                        Log.e("LogSamplerImpl", new StringBuilder(72).append("negative values not supported: ").append(long1).append("/").append(long2).toString());
                        zzb = null;
                        return zzb;
                    }
                }
                catch (NumberFormatException ex) {
                    final String value2 = String.valueOf(s);
                    String concat2;
                    if (value2.length() != 0) {
                        concat2 = "parseLong() failed while parsing: ".concat(value2);
                    }
                    else {
                        concat2 = new String("parseLong() failed while parsing: ");
                    }
                    Log.e("LogSamplerImpl", concat2, (Throwable)ex);
                    zzb = null;
                    return zzb;
                }
                zzb = new zzb(substring, long1, long2);
            }
        }
        return zzb;
    }
    
    @Override
    public boolean zzh(String value, final int n) {
        boolean zza = true;
        if (value == null || value.isEmpty()) {
            if (n >= 0) {
                value = String.valueOf(n);
            }
            else {
                value = null;
            }
        }
        if (value != null) {
            final long zzapb = this.uD.zzapb();
            final zzb zzhb = zzhb(this.uD.zzhc(value));
            if (zzhb != null) {
                zza = zza(zzd(zzhb.uE, zzapb), zzhb.uF, zzhb.uG);
            }
        }
        return zza;
    }
    
    static class zza
    {
        final ContentResolver mContentResolver;
        
        zza(final Context context) {
            if (context == null || !zzbn(context)) {
                this.mContentResolver = null;
            }
            else {
                zzafz.zzb(this.mContentResolver = context.getContentResolver(), "gms:playlog:service:sampling_");
            }
        }
        
        private static boolean zzbn(final Context context) {
            if (zzpw.uC == null) {
                zzpw.uC = (context.checkCallingOrSelfPermission("com.google.android.providers.gsf.permission.READ_GSERVICES") == 0);
            }
            return zzpw.uC;
        }
        
        long zzapb() {
            long long1 = 0L;
            if (this.mContentResolver != null) {
                long1 = zzafz.getLong(this.mContentResolver, "android_id", long1);
            }
            return long1;
        }
        
        String zzhc(final String s) {
            String zza;
            if (this.mContentResolver == null) {
                zza = null;
            }
            else {
                final ContentResolver mContentResolver = this.mContentResolver;
                final String value = String.valueOf("gms:playlog:service:sampling_");
                final String value2 = String.valueOf(s);
                String concat;
                if (value2.length() != 0) {
                    concat = value.concat(value2);
                }
                else {
                    concat = new String(value);
                }
                zza = zzafz.zza(mContentResolver, concat, null);
            }
            return zza;
        }
    }
    
    static class zzb
    {
        public final String uE;
        public final long uF;
        public final long uG;
        
        public zzb(final String ue, final long uf, final long ug) {
            this.uE = ue;
            this.uF = uf;
            this.uG = ug;
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean b = true;
            if (o != this) {
                if (!(o instanceof zzb)) {
                    b = false;
                }
                else {
                    final zzb zzb = (zzb)o;
                    if (!zzab.equal(this.uE, zzb.uE) || !zzab.equal(this.uF, zzb.uF) || !zzab.equal(this.uG, zzb.uG)) {
                        b = false;
                    }
                }
            }
            return b;
        }
        
        @Override
        public int hashCode() {
            return zzab.hashCode(this.uE, this.uF, this.uG);
        }
    }
}
