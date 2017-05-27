// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.text.TextUtils;
import java.util.Map;

@zziy
public final class zzet implements zzev
{
    private void zzb(final zzlt zzlt, final Map<String, String> map) {
        final String s = map.get("label");
        String s2 = map.get("start_label");
        final String s3 = map.get("timestamp");
        if (TextUtils.isEmpty((CharSequence)s)) {
            zzb.zzdf("No label given for CSI tick.");
        }
        else if (TextUtils.isEmpty((CharSequence)s3)) {
            zzb.zzdf("No timestamp given for CSI tick.");
        }
        else {
            try {
                final long zzd = this.zzd(Long.parseLong(s3));
                if (TextUtils.isEmpty((CharSequence)s2)) {
                    s2 = "native:view_load";
                }
                zzlt.zzwa().zza(s, s2, zzd);
            }
            catch (NumberFormatException ex) {
                zzb.zzd("Malformed timestamp for CSI tick.", ex);
            }
        }
    }
    
    private void zzc(final zzlt zzlt, final Map<String, String> map) {
        final String s = map.get("value");
        if (TextUtils.isEmpty((CharSequence)s)) {
            zzb.zzdf("No value given for CSI experiment.");
        }
        else {
            final zzdq zzkz = zzlt.zzwa().zzkz();
            if (zzkz == null) {
                zzb.zzdf("No ticker for WebView, dropping experiment ID.");
            }
            else {
                zzkz.zzh("e", s);
            }
        }
    }
    
    private long zzd(final long n) {
        return zzu.zzgf().elapsedRealtime() + (n - zzu.zzgf().currentTimeMillis());
    }
    
    private void zzd(final zzlt zzlt, final Map<String, String> map) {
        final String s = map.get("name");
        final String s2 = map.get("value");
        if (TextUtils.isEmpty((CharSequence)s2)) {
            zzb.zzdf("No value given for CSI extra.");
        }
        else if (TextUtils.isEmpty((CharSequence)s)) {
            zzb.zzdf("No name given for CSI extra.");
        }
        else {
            final zzdq zzkz = zzlt.zzwa().zzkz();
            if (zzkz == null) {
                zzb.zzdf("No ticker for WebView, dropping extra parameter.");
            }
            else {
                zzkz.zzh(s, s2);
            }
        }
    }
    
    @Override
    public void zza(final zzlt zzlt, final Map<String, String> map) {
        final String s = map.get("action");
        if ("tick".equals(s)) {
            this.zzb(zzlt, map);
        }
        else if ("experiment".equals(s)) {
            this.zzc(zzlt, map);
        }
        else if ("extra".equals(s)) {
            this.zzd(zzlt, map);
        }
    }
}
