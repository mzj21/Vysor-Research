// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.SharedPreferences$Editor;
import java.util.concurrent.Future;
import android.content.Context;

@zziy
public final class zzkp
{
    public static Future zza(final Context context, final int n) {
        return (Future)new zza() {
            @Override
            public void zzfc() {
                final SharedPreferences$Editor edit = zzkp.zzn(context).edit();
                edit.putInt("request_in_session_count", n);
                edit.apply();
            }
        }.zzqw();
    }
    
    public static Future zza(final Context context, final long n) {
        return (Future)new zza() {
            @Override
            public void zzfc() {
                final SharedPreferences$Editor edit = zzkp.zzn(context).edit();
                edit.putLong("app_last_background_time_ms", n);
                edit.apply();
            }
        }.zzqw();
    }
    
    public static Future zza(final Context context, final zzb zzb) {
        return (Future)new zza() {
            @Override
            public void zzfc() {
                final SharedPreferences zzn = zzkp.zzn(context);
                final Bundle bundle = new Bundle();
                bundle.putBoolean("use_https", zzn.getBoolean("use_https", true));
                if (zzb != null) {
                    zzb.zzh(bundle);
                }
            }
        }.zzqw();
    }
    
    public static Future zza(final Context context, final String s, final long n) {
        return (Future)new zza() {
            @Override
            public void zzfc() {
                final SharedPreferences$Editor edit = zzkp.zzn(context).edit();
                edit.putString("app_settings_json", s);
                edit.putLong("app_settings_last_update_ms", n);
                edit.apply();
            }
        }.zzqw();
    }
    
    public static Future zzb(final Context context, final zzb zzb) {
        return (Future)new zza() {
            @Override
            public void zzfc() {
                final SharedPreferences zzn = zzkp.zzn(context);
                final Bundle bundle = new Bundle();
                bundle.putInt("webview_cache_version", zzn.getInt("webview_cache_version", 0));
                if (zzb != null) {
                    zzb.zzh(bundle);
                }
            }
        }.zzqw();
    }
    
    public static Future zzc(final Context context, final zzb zzb) {
        return (Future)new zza() {
            @Override
            public void zzfc() {
                final SharedPreferences zzn = zzkp.zzn(context);
                final Bundle bundle = new Bundle();
                bundle.putBoolean("content_url_opted_out", zzn.getBoolean("content_url_opted_out", true));
                if (zzb != null) {
                    zzb.zzh(bundle);
                }
            }
        }.zzqw();
    }
    
    public static Future zzc(final Context context, final boolean b) {
        return (Future)new zza() {
            @Override
            public void zzfc() {
                final SharedPreferences$Editor edit = zzkp.zzn(context).edit();
                edit.putBoolean("use_https", b);
                edit.apply();
            }
        }.zzqw();
    }
    
    public static Future zzd(final Context context, final zzb zzb) {
        return (Future)new zza() {
            @Override
            public void zzfc() {
                final SharedPreferences zzn = zzkp.zzn(context);
                final Bundle bundle = new Bundle();
                bundle.putString("content_url_hashes", zzn.getString("content_url_hashes", ""));
                if (zzb != null) {
                    zzb.zzh(bundle);
                }
            }
        }.zzqw();
    }
    
    public static Future zze(final Context context, final zzb zzb) {
        return (Future)new zza() {
            @Override
            public void zzfc() {
                final SharedPreferences zzn = zzkp.zzn(context);
                final Bundle bundle = new Bundle();
                bundle.putBoolean("auto_collect_location", zzn.getBoolean("auto_collect_location", false));
                if (zzb != null) {
                    zzb.zzh(bundle);
                }
            }
        }.zzqw();
    }
    
    public static Future zze(final Context context, final boolean b) {
        return (Future)new zza() {
            @Override
            public void zzfc() {
                final SharedPreferences$Editor edit = zzkp.zzn(context).edit();
                edit.putBoolean("content_url_opted_out", b);
                edit.apply();
            }
        }.zzqw();
    }
    
    public static Future zzf(final Context context, final zzb zzb) {
        return (Future)new zza() {
            @Override
            public void zzfc() {
                final SharedPreferences zzn = zzkp.zzn(context);
                final Bundle bundle = new Bundle();
                bundle.putString("app_settings_json", zzn.getString("app_settings_json", ""));
                bundle.putLong("app_settings_last_update_ms", zzn.getLong("app_settings_last_update_ms", 0L));
                if (zzb != null) {
                    zzb.zzh(bundle);
                }
            }
        }.zzqw();
    }
    
    public static Future zzf(final Context context, final String s) {
        return (Future)new zza() {
            @Override
            public void zzfc() {
                final SharedPreferences$Editor edit = zzkp.zzn(context).edit();
                edit.putString("content_url_hashes", s);
                edit.apply();
            }
        }.zzqw();
    }
    
    public static Future zzf(final Context context, final boolean b) {
        return (Future)new zza() {
            @Override
            public void zzfc() {
                final SharedPreferences$Editor edit = zzkp.zzn(context).edit();
                edit.putBoolean("auto_collect_location", b);
                edit.apply();
            }
        }.zzqw();
    }
    
    public static Future zzg(final Context context, final zzb zzb) {
        return (Future)new zza() {
            @Override
            public void zzfc() {
                final SharedPreferences zzn = zzkp.zzn(context);
                final Bundle bundle = new Bundle();
                bundle.putLong("app_last_background_time_ms", zzn.getLong("app_last_background_time_ms", 0L));
                if (zzb != null) {
                    zzb.zzh(bundle);
                }
            }
        }.zzqw();
    }
    
    public static SharedPreferences zzn(final Context context) {
        return context.getSharedPreferences("admob", 0);
    }
    
    private abstract static class zza extends zzkm
    {
        @Override
        public void onStop() {
        }
    }
    
    public interface zzb
    {
        void zzh(final Bundle p0);
    }
}
