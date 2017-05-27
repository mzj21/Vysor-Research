// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.overlay.zzk;
import android.graphics.Color;
import android.text.TextUtils;
import org.json.JSONObject;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.client.zzm;
import java.util.Map;
import android.content.Context;

@zziy
public final class zzff implements zzev
{
    private boolean zzbnr;
    
    private static int zza(final Context context, final Map<String, String> map, final String s, int zzb) {
        final String s2 = map.get(s);
        if (s2 == null) {
            return zzb;
        }
        try {
            zzb = zzm.zzjr().zzb(context, Integer.parseInt(s2));
            return zzb;
        }
        catch (NumberFormatException ex) {
            zzb.zzdf(new StringBuilder(34 + String.valueOf(s).length() + String.valueOf(s2).length()).append("Could not parse ").append(s).append(" in a video GMSG: ").append(s2).toString());
            return zzb;
        }
    }
    
    @Override
    public void zza(final zzlt zzlt, final Map<String, String> map) {
        final String s = map.get("action");
        if (s == null) {
            zzb.zzdf("Action missing from video GMSG.");
        }
        else {
            if (zzb.zzbf(3)) {
                final JSONObject jsonObject = new JSONObject((Map)map);
                jsonObject.remove("google.afma.Notify_dt");
                final String value = String.valueOf(jsonObject.toString());
                zzb.zzdd(new StringBuilder(13 + String.valueOf(s).length() + String.valueOf(value).length()).append("Video GMSG: ").append(s).append(" ").append(value).toString());
            }
            if ("background".equals(s)) {
                final String s2 = map.get("color");
                if (TextUtils.isEmpty((CharSequence)s2)) {
                    zzb.zzdf("Color parameter missing from color video GMSG.");
                }
                else {
                    try {
                        zzlt.setBackgroundColor(Color.parseColor(s2));
                    }
                    catch (IllegalArgumentException ex) {
                        zzb.zzdf("Invalid color parameter in video GMSG.");
                    }
                }
            }
            else {
                final zzls zzvy = zzlt.zzvy();
                if (zzvy == null) {
                    zzb.zzdf("Could not get underlay container for a video GMSG.");
                }
                else {
                    final boolean equals = "new".equals(s);
                    final boolean equals2 = "position".equals(s);
                    if (equals || equals2) {
                        final Context context = zzlt.getContext();
                        final int zza = zza(context, map, "x", 0);
                        final int zza2 = zza(context, map, "y", 0);
                        final int zza3 = zza(context, map, "w", -1);
                        int n = zza(context, map, "h", -1);
                        int min;
                        if (zzdi.zzbge.get()) {
                            min = Math.min(zza3, zzlt.getMeasuredWidth() - zza);
                            n = Math.min(n, zzlt.getMeasuredHeight() - zza2);
                        }
                        else {
                            min = zza3;
                        }
                        while (true) {
                            try {
                                final int int1 = Integer.parseInt(map.get("player"));
                                final boolean boolean1 = Boolean.parseBoolean(map.get("spherical"));
                                if (equals && zzvy.zzvk() == null) {
                                    zzvy.zza(zza, zza2, min, n, int1, boolean1);
                                    return;
                                }
                            }
                            catch (NumberFormatException ex2) {
                                final int int1 = 0;
                                continue;
                            }
                            break;
                        }
                        zzvy.zze(zza, zza2, min, n);
                    }
                    else {
                        final zzk zzvk = zzvy.zzvk();
                        if (zzvk == null) {
                            zzk.zzi(zzlt);
                        }
                        else if ("click".equals(s)) {
                            final Context context2 = zzlt.getContext();
                            final int zza4 = zza(context2, map, "x", 0);
                            final int zza5 = zza(context2, map, "y", 0);
                            final long uptimeMillis = SystemClock.uptimeMillis();
                            final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, (float)zza4, (float)zza5, 0);
                            zzvk.zzf(obtain);
                            obtain.recycle();
                        }
                        else {
                            if ("currentTime".equals(s)) {
                                final String s3 = map.get("time");
                                if (s3 == null) {
                                    zzb.zzdf("Time parameter missing from currentTime video GMSG.");
                                    return;
                                }
                                try {
                                    zzvk.seekTo((int)(1000.0f * Float.parseFloat(s3)));
                                    return;
                                }
                                catch (NumberFormatException ex3) {
                                    final String value2 = String.valueOf(s3);
                                    String concat;
                                    if (value2.length() != 0) {
                                        concat = "Could not parse time parameter from currentTime video GMSG: ".concat(value2);
                                    }
                                    else {
                                        concat = new String("Could not parse time parameter from currentTime video GMSG: ");
                                    }
                                    zzb.zzdf(concat);
                                    return;
                                }
                            }
                            if ("hide".equals(s)) {
                                zzvk.setVisibility(4);
                            }
                            else if ("load".equals(s)) {
                                zzvk.zzmt();
                            }
                            else if ("muted".equals(s)) {
                                if (Boolean.parseBoolean(map.get("muted"))) {
                                    zzvk.zzom();
                                }
                                else {
                                    zzvk.zzon();
                                }
                            }
                            else if ("pause".equals(s)) {
                                zzvk.pause();
                            }
                            else if ("play".equals(s)) {
                                zzvk.play();
                            }
                            else if ("show".equals(s)) {
                                zzvk.setVisibility(0);
                            }
                            else if ("src".equals(s)) {
                                zzvk.zzca(map.get("src"));
                            }
                            else if ("touchMove".equals(s)) {
                                final Context context3 = zzlt.getContext();
                                zzvk.zza(zza(context3, map, "dx", 0), zza(context3, map, "dy", 0));
                                if (!this.zzbnr) {
                                    zzlt.zzvp().zzpa();
                                    this.zzbnr = true;
                                }
                            }
                            else {
                                if ("volume".equals(s)) {
                                    final String s4 = map.get("volume");
                                    if (s4 == null) {
                                        zzb.zzdf("Level parameter missing from volume video GMSG.");
                                        return;
                                    }
                                    try {
                                        zzvk.zza(Float.parseFloat(s4));
                                        return;
                                    }
                                    catch (NumberFormatException ex4) {
                                        final String value3 = String.valueOf(s4);
                                        String concat2;
                                        if (value3.length() != 0) {
                                            concat2 = "Could not parse volume parameter from volume video GMSG: ".concat(value3);
                                        }
                                        else {
                                            concat2 = new String("Could not parse volume parameter from volume video GMSG: ");
                                        }
                                        zzb.zzdf(concat2);
                                        return;
                                    }
                                }
                                if ("watermark".equals(s)) {
                                    zzvk.zzpn();
                                }
                                else {
                                    final String value4 = String.valueOf(s);
                                    String concat3;
                                    if (value4.length() != 0) {
                                        concat3 = "Unknown video action: ".concat(value4);
                                    }
                                    else {
                                        concat3 = new String("Unknown video action: ");
                                    }
                                    zzb.zzdf(concat3);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
