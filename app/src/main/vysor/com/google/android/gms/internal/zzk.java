// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import java.util.Collections;
import android.net.Uri;
import android.text.TextUtils;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public abstract class zzk<T> implements Comparable<zzk<T>>
{
    private final zzs.zza zzac;
    private final int zzad;
    private final String zzae;
    private final int zzaf;
    private final zzm.zza zzag;
    private Integer zzah;
    private zzl zzai;
    private boolean zzaj;
    private boolean zzak;
    private boolean zzal;
    private long zzam;
    private zzo zzan;
    private zzb.zza zzao;
    
    public zzk(final int zzad, final String zzae, final zzm.zza zzag) {
        zzs.zza zzac;
        if (zzs.zza.zzbj) {
            zzac = new zzs.zza();
        }
        else {
            zzac = null;
        }
        this.zzac = zzac;
        this.zzaj = true;
        this.zzak = false;
        this.zzal = false;
        this.zzam = 0L;
        this.zzao = null;
        this.zzad = zzad;
        this.zzae = zzae;
        this.zzag = zzag;
        this.zza(new zzd());
        this.zzaf = zzb(zzae);
    }
    
    private byte[] zza(final Map<String, String> map, final String s) {
        StringBuilder sb;
        while (true) {
            sb = new StringBuilder();
            while (true) {
                Label_0148: {
                    try {
                        for (final Map.Entry<String, String> entry : map.entrySet()) {
                            sb.append(URLEncoder.encode(entry.getKey(), s));
                            sb.append('=');
                            sb.append(URLEncoder.encode(entry.getValue(), s));
                            sb.append('&');
                        }
                    }
                    catch (UnsupportedEncodingException ex) {
                        final String value = String.valueOf(s);
                        if (value.length() != 0) {
                            final String concat = "Encoding not supported: ".concat(value);
                            throw new RuntimeException(concat, ex);
                        }
                        break Label_0148;
                    }
                    break;
                }
                final String concat = new String("Encoding not supported: ");
                continue;
            }
        }
        return sb.toString().getBytes(s);
    }
    
    private static int zzb(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return 0;
        }
        final Uri parse = Uri.parse(s);
        if (parse == null) {
            return 0;
        }
        final String host = parse.getHost();
        if (host == null) {
            return 0;
        }
        return host.hashCode();
        hashCode = 0;
        return hashCode;
    }
    
    public Map<String, String> getHeaders() throws com.google.android.gms.internal.zza {
        return Collections.emptyMap();
    }
    
    public int getMethod() {
        return this.zzad;
    }
    
    public String getUrl() {
        return this.zzae;
    }
    
    public boolean isCanceled() {
        return false;
    }
    
    @Override
    public String toString() {
        final String value = String.valueOf(Integer.toHexString(this.zzf()));
        String concat;
        if (value.length() != 0) {
            concat = "0x".concat(value);
        }
        else {
            concat = new String("0x");
        }
        final String value2 = String.valueOf(this.getUrl());
        final String value3 = String.valueOf(this.zzr());
        final String value4 = String.valueOf(this.zzah);
        return new StringBuilder(3 + String.valueOf("[ ] ").length() + String.valueOf(value2).length() + String.valueOf(concat).length() + String.valueOf(value3).length() + String.valueOf(value4).length()).append("[ ] ").append(value2).append(" ").append(concat).append(" ").append(value3).append(" ").append(value4).toString();
    }
    
    public final zzk<?> zza(final int n) {
        this.zzah = n;
        return this;
    }
    
    public zzk<?> zza(final zzb.zza zzao) {
        this.zzao = zzao;
        return this;
    }
    
    public zzk<?> zza(final zzl zzai) {
        this.zzai = zzai;
        return this;
    }
    
    public zzk<?> zza(final zzo zzan) {
        this.zzan = zzan;
        return this;
    }
    
    protected abstract zzm<T> zza(final zzi p0);
    
    protected abstract void zza(final T p0);
    
    protected zzr zzb(final zzr zzr) {
        return zzr;
    }
    
    public int zzc(final zzk<T> zzk) {
        final zza zzr = this.zzr();
        final zza zzr2 = zzk.zzr();
        int n;
        if (zzr == zzr2) {
            n = this.zzah - zzk.zzah;
        }
        else {
            n = zzr2.ordinal() - zzr.ordinal();
        }
        return n;
    }
    
    public void zzc(final zzr zzr) {
        if (this.zzag != null) {
            this.zzag.zze(zzr);
        }
    }
    
    public void zzc(final String s) {
        if (zzs.zza.zzbj) {
            this.zzac.zza(s, Thread.currentThread().getId());
        }
        else if (this.zzam == 0L) {
            this.zzam = SystemClock.elapsedRealtime();
        }
    }
    
    void zzd(final String s) {
        if (this.zzai != null) {
            this.zzai.zzf((zzk<Object>)this);
        }
        if (zzs.zza.zzbj) {
            final long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        zzk.this.zzac.zza(s, id);
                        zzk.this.zzac.zzd(this.toString());
                    }
                });
            }
            else {
                this.zzac.zza(s, id);
                this.zzac.zzd(this.toString());
            }
        }
        else {
            final long n = SystemClock.elapsedRealtime() - this.zzam;
            if (n >= 3000L) {
                zzs.zzb("%d ms: %s", n, this.toString());
            }
        }
    }
    
    public int zzf() {
        return this.zzaf;
    }
    
    public String zzg() {
        return this.getUrl();
    }
    
    public zzb.zza zzh() {
        return this.zzao;
    }
    
    @Deprecated
    protected Map<String, String> zzi() throws com.google.android.gms.internal.zza {
        return this.zzm();
    }
    
    @Deprecated
    protected String zzj() {
        return this.zzn();
    }
    
    @Deprecated
    public String zzk() {
        return this.zzo();
    }
    
    @Deprecated
    public byte[] zzl() throws com.google.android.gms.internal.zza {
        final Map<String, String> zzi = this.zzi();
        byte[] zza;
        if (zzi != null && zzi.size() > 0) {
            zza = this.zza(zzi, this.zzj());
        }
        else {
            zza = null;
        }
        return zza;
    }
    
    protected Map<String, String> zzm() throws com.google.android.gms.internal.zza {
        return null;
    }
    
    protected String zzn() {
        return "UTF-8";
    }
    
    public String zzo() {
        final String value = String.valueOf(this.zzn());
        String concat;
        if (value.length() != 0) {
            concat = "application/x-www-form-urlencoded; charset=".concat(value);
        }
        else {
            concat = new String("application/x-www-form-urlencoded; charset=");
        }
        return concat;
    }
    
    public byte[] zzp() throws com.google.android.gms.internal.zza {
        final Map<String, String> zzm = this.zzm();
        byte[] zza;
        if (zzm != null && zzm.size() > 0) {
            zza = this.zza(zzm, this.zzn());
        }
        else {
            zza = null;
        }
        return zza;
    }
    
    public final boolean zzq() {
        return this.zzaj;
    }
    
    public zza zzr() {
        return zza.zzat;
    }
    
    public final int zzs() {
        return this.zzan.zzc();
    }
    
    public zzo zzt() {
        return this.zzan;
    }
    
    public void zzu() {
        this.zzal = true;
    }
    
    public boolean zzv() {
        return this.zzal;
    }
    
    public enum zza
    {
        zzas, 
        zzat, 
        zzau, 
        zzav;
    }
}
