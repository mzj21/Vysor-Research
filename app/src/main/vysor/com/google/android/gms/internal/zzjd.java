// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Future;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;

@zziy
public final class zzjd
{
    private final Object zzakd;
    private String zzcaj;
    private String zzcjz;
    private zzlg<zzjg> zzcka;
    zzfy.zzc zzckb;
    public final zzev zzckc;
    public final zzev zzckd;
    public final zzev zzcke;
    
    public zzjd(final String zzcaj, final String zzcjz) {
        this.zzakd = new Object();
        this.zzcka = new zzlg<zzjg>();
        this.zzckc = new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                synchronized (zzjd.this.zzakd) {
                    if (zzjd.this.zzcka.isDone()) {
                        return;
                    }
                    if (!zzjd.this.zzcaj.equals(map.get("request_id"))) {
                        return;
                    }
                }
                final zzjg zzjg = new zzjg(1, map);
                final String value = String.valueOf(zzjg.getType());
                final String value2 = String.valueOf(zzjg.zzsg());
                zzb.zzdf(new StringBuilder(24 + String.valueOf(value).length() + String.valueOf(value2).length()).append("Invalid ").append(value).append(" request error: ").append(value2).toString());
                zzjd.this.zzcka.zzh(zzjg);
            }
            // monitorexit(o)
        };
        this.zzckd = new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                final zzjg zzjg;
                synchronized (zzjd.this.zzakd) {
                    if (zzjd.this.zzcka.isDone()) {
                        return;
                    }
                    zzjg = new zzjg(-2, map);
                    if (!zzjd.this.zzcaj.equals(zzjg.getRequestId())) {
                        return;
                    }
                }
                final String url = zzjg.getUrl();
                if (url == null) {
                    zzb.zzdf("URL missing in loadAdUrl GMSG.");
                }
                // monitorexit(o)
                else {
                    if (url.contains("%40mediation_adapters%40")) {
                        final String replaceAll = url.replaceAll("%40mediation_adapters%40", zzkl.zza(zzlt.getContext(), map.get("check_adapters"), zzjd.this.zzcjz));
                        zzjg.setUrl(replaceAll);
                        final String value = String.valueOf(replaceAll);
                        String concat;
                        if (value.length() != 0) {
                            concat = "Ad request URL modified to ".concat(value);
                        }
                        else {
                            concat = new String("Ad request URL modified to ");
                        }
                        zzkn.v(concat);
                    }
                    zzjd.this.zzcka.zzh(zzjg);
                }
                // monitorexit(o)
            }
        };
        this.zzcke = new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                final zzjg zzjg;
                synchronized (zzjd.this.zzakd) {
                    if (zzjd.this.zzcka.isDone()) {
                        return;
                    }
                    zzjg = new zzjg(-2, map);
                    if (!zzjd.this.zzcaj.equals(zzjg.getRequestId())) {
                        return;
                    }
                }
                zzjg.zzsj();
                zzjd.this.zzcka.zzh(zzjg);
            }
            // monitorexit(o)
        };
        this.zzcjz = zzcjz;
        this.zzcaj = zzcaj;
    }
    
    public void zzb(final zzfy.zzc zzckb) {
        this.zzckb = zzckb;
    }
    
    public zzfy.zzc zzsd() {
        return this.zzckb;
    }
    
    public Future<zzjg> zzse() {
        return this.zzcka;
    }
    
    public void zzsf() {
    }
}
