// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.ArrayList;

@zziy
public class zzcr
{
    private final Object zzakd;
    private final int zzatu;
    private final int zzatv;
    private final int zzatw;
    private final zzcw zzatx;
    private ArrayList<String> zzaty;
    private ArrayList<String> zzatz;
    private int zzaua;
    private int zzaub;
    private int zzauc;
    private int zzaud;
    private String zzaue;
    private String zzauf;
    
    public zzcr(final int zzatu, final int zzatv, final int zzatw, final int n) {
        this.zzakd = new Object();
        this.zzaty = new ArrayList<String>();
        this.zzatz = new ArrayList<String>();
        this.zzaua = 0;
        this.zzaub = 0;
        this.zzauc = 0;
        this.zzaue = "";
        this.zzauf = "";
        this.zzatu = zzatu;
        this.zzatv = zzatv;
        this.zzatw = zzatw;
        this.zzatx = new zzcw(n);
    }
    
    private String zza(final ArrayList<String> list, final int n) {
        String s;
        if (list.isEmpty()) {
            s = "";
        }
        else {
            final StringBuffer sb = new StringBuffer();
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                sb.append(iterator.next());
                sb.append(' ');
                if (sb.length() > n) {
                    break;
                }
            }
            sb.deleteCharAt(-1 + sb.length());
            s = sb.toString();
            if (s.length() >= n) {
                s = s.substring(0, n);
            }
        }
        return s;
    }
    
    private void zzf(@Nullable final String s, final boolean b) {
        if (s != null && s.length() >= this.zzatw) {
            synchronized (this.zzakd) {
                this.zzaty.add(s);
                this.zzaua += s.length();
                if (b) {
                    this.zzatz.add(s);
                }
            }
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = o instanceof zzcr;
        boolean b2 = false;
        if (b) {
            if (o == this) {
                b2 = true;
            }
            else {
                final zzcr zzcr = (zzcr)o;
                final String zzie = zzcr.zzie();
                b2 = false;
                if (zzie != null) {
                    final boolean equals = zzcr.zzie().equals(this.zzie());
                    b2 = false;
                    if (equals) {
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
    
    public int getScore() {
        return this.zzaud;
    }
    
    @Override
    public int hashCode() {
        return this.zzie().hashCode();
    }
    
    @Override
    public String toString() {
        final int zzaub = this.zzaub;
        final int zzaud = this.zzaud;
        final int zzaua = this.zzaua;
        final String value = String.valueOf(this.zza(this.zzaty, 100));
        final String value2 = String.valueOf(this.zza(this.zzatz, 100));
        final String zzaue = this.zzaue;
        final String zzauf = this.zzauf;
        return new StringBuilder(133 + String.valueOf(value).length() + String.valueOf(value2).length() + String.valueOf(zzaue).length() + String.valueOf(zzauf).length()).append("ActivityContent fetchId: ").append(zzaub).append(" score:").append(zzaud).append(" total_length:").append(zzaua).append("\n text: ").append(value).append("\n viewableText").append(value2).append("\n signture: ").append(zzaue).append("\n viewableSignture: ").append(zzauf).toString();
    }
    
    int zza(final int n, final int n2) {
        return n * this.zzatu + n2 * this.zzatv;
    }
    
    public void zzd(final String s, final boolean b) {
        this.zzf(s, b);
        synchronized (this.zzakd) {
            if (this.zzauc < 0) {
                zzb.zzdd("ActivityContent: negative number of WebViews.");
            }
            this.zzij();
        }
    }
    
    public void zze(final String s, final boolean b) {
        this.zzf(s, b);
    }
    
    public boolean zzid() {
        while (true) {
            synchronized (this.zzakd) {
                if (this.zzauc == 0) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public String zzie() {
        return this.zzaue;
    }
    
    public String zzif() {
        return this.zzauf;
    }
    
    public void zzig() {
        synchronized (this.zzakd) {
            this.zzaud -= 100;
        }
    }
    
    public void zzih() {
        synchronized (this.zzakd) {
            --this.zzauc;
        }
    }
    
    public void zzii() {
        synchronized (this.zzakd) {
            ++this.zzauc;
        }
    }
    
    public void zzij() {
        synchronized (this.zzakd) {
            final int zza = this.zza(this.zzaua, this.zzaub);
            if (zza > this.zzaud) {
                this.zzaud = zza;
                this.zzaue = this.zzatx.zza(this.zzaty);
                this.zzauf = this.zzatx.zza(this.zzatz);
            }
        }
    }
    
    int zzik() {
        return this.zzaua;
    }
    
    public void zzl(final int zzaub) {
        this.zzaub = zzaub;
    }
}
