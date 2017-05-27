// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.ByteArrayInputStream;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.support.annotation.Nullable;
import java.util.Map;
import android.content.Context;
import java.io.InputStream;

@zziy
public class zzky
{
    private static zzl zzcsk;
    private static final Object zzcsl;
    public static final zza<Void> zzcsm;
    
    static {
        zzcsl = new Object();
        zzcsm = (zza)new zza<Void>() {
            public Void zzj(final InputStream inputStream) {
                return null;
            }
            
            public Void zzux() {
                return null;
            }
        };
    }
    
    public zzky(final Context context) {
        zzaq(context);
    }
    
    private static zzl zzaq(final Context context) {
        synchronized (zzky.zzcsl) {
            if (zzky.zzcsk == null) {
                zzky.zzcsk = zzac.zza(context.getApplicationContext());
            }
            return zzky.zzcsk;
        }
    }
    
    public zzlj<String> zza(final int n, final String s, @Nullable final Map<String, String> map, @Nullable final byte[] array) {
        final zzc zzc = (zzc)new zzc<String>();
        zzky.zzcsk.zze((zzk<Object>)new zzab(n, s, zzc, new zzm.zza() {
            @Override
            public void zze(final zzr zzr) {
                final String zzbny = s;
                final String value = String.valueOf(zzr.toString());
                zzb.zzdf(new StringBuilder(21 + String.valueOf(zzbny).length() + String.valueOf(value).length()).append("Failed to load URL: ").append(zzbny).append("\n").append(value).toString());
                zzc.zzb(null);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws zza {
                Map<String, String> map;
                if (map == null) {
                    map = super.getHeaders();
                }
                else {
                    map = map;
                }
                return map;
            }
            
            @Override
            public byte[] zzp() throws zza {
                byte[] array;
                if (array == null) {
                    array = super.zzp();
                }
                else {
                    array = array;
                }
                return array;
            }
        });
        return (zzlj<String>)zzc;
    }
    
    public <T> zzlj<T> zza(final String s, final zza<T> zza) {
        final zzc<Object> zzc = (zzc<Object>)new zzc<T>();
        zzky.zzcsk.zze(new zzb(s, (zza<Object>)zza, zzc));
        return (zzlj<T>)zzc;
    }
    
    public zzlj<String> zzd(final String s, final Map<String, String> map) {
        return this.zza(0, s, map, null);
    }
    
    public interface zza<T>
    {
        T zzh(final InputStream p0);
        
        T zzrs();
    }
    
    private static class zzb<T> extends zzk<InputStream>
    {
        private final zzm.zzb<T> zzcg;
        private final zzky.zza<T> zzcsr;
        
        public zzb(final String s, final zzky.zza<T> zzcsr, final zzm.zzb<T> zzcg) {
            super(0, s, new zzm.zza() {
                @Override
                public void zze(final zzr zzr) {
                    zzcg.zzb(zzcsr.zzrs());
                }
            });
            this.zzcsr = zzcsr;
            this.zzcg = zzcg;
        }
        
        @Override
        protected zzm<InputStream> zza(final zzi zzi) {
            return (zzm<InputStream>)zzm.zza(new ByteArrayInputStream(zzi.data), zzx.zzb(zzi));
        }
        
        protected void zzk(final InputStream inputStream) {
            this.zzcg.zzb(this.zzcsr.zzh(inputStream));
        }
    }
    
    private class zzc<T> extends zzlg<T> implements zzm.zzb<T>
    {
        @Override
        public void zzb(@Nullable final T t) {
            super.zzh(t);
        }
    }
}
