// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import java.util.Map;

public class zzx
{
    public static String zza(final Map<String, String> map) {
        return zzb(map, "ISO-8859-1");
    }
    
    public static zzb.zza zzb(final zzi zzi) {
        final long currentTimeMillis = System.currentTimeMillis();
        final Map<String, String> zzz = zzi.zzz;
        long zzg = 0L;
        long n = 0L;
        long n2 = 0L;
        final String s = zzz.get("Date");
        if (s != null) {
            zzg = zzg(s);
        }
        final String s2 = zzz.get("Cache-Control");
        String[] split = null;
        int n3 = 0;
        boolean b;
        long long1;
        long long2;
        boolean b2;
        String trim;
        long zzg2;
        long zze;
        long zzd;
        String s3;
        String s4;
        zzb.zza zza;
        String zza2;
        long zzg3;
        zzb.zza zza3 = null;
        Label_0215_Outer:Label_0160_Outer:
        while (true) {
            while (true) {
                while (true) {
                    Label_0084: {
                        if (s2 != null) {
                            split = s2.split(",");
                            n3 = 0;
                            b = false;
                            long1 = n2;
                            long2 = n;
                            break Label_0084;
                        }
                        b = false;
                        b2 = false;
                    Label_0226:
                        while (true) {
                            break Label_0226;
                            Label_0313_Outer:Label_0251_Outer:
                            while (true) {
                            Label_0251:
                                while (true) {
                                Label_0276:
                                    while (true) {
                                    Label_0313:
                                        while (true) {
                                        Block_7_Outer:
                                            while (true) {
                                                try {
                                                    long2 = Long.parseLong(trim.substring(8));
                                                    ++n3;
                                                    break;
                                                    while (true) {
                                                        try {
                                                            long1 = Long.parseLong(trim.substring(23));
                                                            continue Block_7_Outer;
                                                            // iftrue(Label_0154:, !trim.equals((Object)"must-revalidate") && !trim.equals((Object)"proxy-revalidate"))
                                                            // iftrue(Label_0449:, s3 == null)
                                                            // iftrue(Label_0443:, s4 == null)
                                                            // iftrue(Label_0434:, zzg <= 0L || zzg2 < zzg)
                                                            // iftrue(Label_0393:, b2 == false)
                                                            Block_12: {
                                                                Block_11: {
                                                                    while (true) {
                                                                        zzd = (zze = currentTimeMillis + (zzg2 - zzg));
                                                                        break Label_0313;
                                                                        Label_0209: {
                                                                            while (true) {
                                                                                zzg2 = zzg(s3);
                                                                                break Label_0251;
                                                                                Label_0189: {
                                                                                    break Label_0209;
                                                                                }
                                                                                s3 = zzz.get("Expires");
                                                                                continue Label_0251_Outer;
                                                                            }
                                                                            s4 = zzz.get("Last-Modified");
                                                                            break Block_11;
                                                                        }
                                                                        b = true;
                                                                        continue Block_7_Outer;
                                                                        Label_0393: {
                                                                            continue Label_0313_Outer;
                                                                        }
                                                                    }
                                                                    n = long2;
                                                                    n2 = long1;
                                                                    b2 = true;
                                                                    continue Label_0226;
                                                                    zza = new zzb.zza();
                                                                    zza.data = zzi.data;
                                                                    zza.zza = zza2;
                                                                    zza.zze = zze;
                                                                    zza.zzd = zzd;
                                                                    zza.zzb = zzg;
                                                                    zza.zzc = zzg3;
                                                                    zza.zzf = zzz;
                                                                    zza3 = zza;
                                                                    return zza3;
                                                                    zza2 = zzz.get("ETag");
                                                                    break Block_12;
                                                                }
                                                                zzg3 = zzg(s4);
                                                                continue Label_0276;
                                                            }
                                                            zze = currentTimeMillis + 1000L * n;
                                                            // iftrue(Label_0379:, b == false)
                                                            Block_13: {
                                                                break Block_13;
                                                                Label_0379: {
                                                                    zzd = zze + 1000L * n2;
                                                                }
                                                                continue Label_0313;
                                                            }
                                                            zzd = zze;
                                                        }
                                                        catch (Exception ex) {}
                                                        continue Label_0313_Outer;
                                                    }
                                                }
                                                // iftrue(Label_0189:, !trim.startsWith("stale-while-revalidate="))
                                                catch (Exception ex2) {
                                                    continue Label_0313_Outer;
                                                }
                                                break;
                                            }
                                            Label_0434: {
                                                zzd = 0L;
                                            }
                                            zze = 0L;
                                            continue Label_0313;
                                        }
                                        Label_0443: {
                                            zzg3 = 0L;
                                        }
                                        continue Label_0276;
                                    }
                                    Label_0449: {
                                        zzg2 = 0L;
                                    }
                                    continue Label_0251;
                                }
                            }
                            break;
                        }
                    }
                    if (n3 >= split.length) {
                        continue Label_0160_Outer;
                    }
                    break;
                }
                trim = split[n3].trim();
                if (trim.equals("no-cache") || trim.equals("no-store")) {
                    zza3 = null;
                }
                else {
                    if (trim.startsWith("max-age=")) {
                        continue Label_0215_Outer;
                    }
                    continue;
                }
                break;
            }
            break;
        }
        return zza3;
    }
    
    public static String zzb(final Map<String, String> map, String s) {
        final String s2 = map.get("Content-Type");
        if (s2 != null) {
            final String[] split = s2.split(";");
            for (int i = 1; i < split.length; ++i) {
                final String[] split2 = split[i].trim().split("=");
                if (split2.length == 2 && split2[0].equals("charset")) {
                    s = split2[1];
                    break;
                }
            }
        }
        return s;
    }
    
    public static long zzg(final String s) {
        try {
            return DateUtils.parseDate(s).getTime();
        }
        catch (DateParseException ex) {
            return 0L;
        }
    }
}
