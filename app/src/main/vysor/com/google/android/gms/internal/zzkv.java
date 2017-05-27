// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import com.google.android.gms.common.util.zzo;
import java.io.InputStream;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzu;
import android.net.Uri$Builder;
import android.net.Uri;
import android.content.Context;

@zziy
public class zzkv
{
    private final Object zzakd;
    private String zzcrv;
    private String zzcrw;
    private boolean zzcrx;
    
    public zzkv() {
        this.zzakd = new Object();
        this.zzcrv = "";
        this.zzcrw = "";
        this.zzcrx = false;
    }
    
    private Uri zze(final Context context, final String s, final String s2) {
        final Uri$Builder buildUpon = Uri.parse(s).buildUpon();
        buildUpon.appendQueryParameter("linkedDeviceId", this.zzap(context));
        buildUpon.appendQueryParameter("adSlotPath", s2);
        return buildUpon.build();
    }
    
    private void zzn(final Context context, final String s) {
        zzu.zzfz().zza(context, this.zze(context, zzdi.zzbhr.get(), s));
    }
    
    public void zza(final Context context, final String s, final String s2, final String s3) {
        final Uri$Builder buildUpon = this.zze(context, zzdi.zzbhu.get(), s3).buildUpon();
        buildUpon.appendQueryParameter("debugData", s2);
        zzu.zzfz().zzc(context, s, buildUpon.build().toString());
    }
    
    public void zzai(final boolean zzcrx) {
        synchronized (this.zzakd) {
            this.zzcrx = zzcrx;
        }
    }
    
    public String zzap(final Context context) {
        synchronized (this.zzakd) {
            if (TextUtils.isEmpty((CharSequence)this.zzcrv)) {
                this.zzcrv = zzu.zzfz().zzh(context, "debug_signals_id.txt");
                if (TextUtils.isEmpty((CharSequence)this.zzcrv)) {
                    this.zzcrv = zzu.zzfz().zzuh();
                    zzu.zzfz().zzd(context, "debug_signals_id.txt", this.zzcrv);
                }
            }
            return this.zzcrv;
        }
    }
    
    public void zzdb(final String zzcrw) {
        synchronized (this.zzakd) {
            this.zzcrw = zzcrw;
        }
    }
    
    public void zzi(final Context context, final String s) {
        if (this.zzk(context, s)) {
            zzb.zzdd("Device is linked for in app preview.");
        }
        else {
            this.zzn(context, s);
        }
    }
    
    public void zzj(final Context context, final String s) {
        if (this.zzl(context, s)) {
            zzb.zzdd("Device is linked for debug signals.");
        }
        else {
            this.zzn(context, s);
        }
    }
    
    boolean zzk(final Context context, final String s) {
        final String zzm = this.zzm(context, this.zze(context, zzdi.zzbhs.get(), s).toString());
        boolean b;
        if (TextUtils.isEmpty((CharSequence)zzm)) {
            zzb.zzdd("Not linked for in app preview.");
            b = false;
        }
        else {
            this.zzdb(zzm.trim());
            b = true;
        }
        return b;
    }
    
    boolean zzl(final Context context, final String s) {
        final String zzm = this.zzm(context, this.zze(context, zzdi.zzbht.get(), s).toString());
        boolean boolean1;
        if (TextUtils.isEmpty((CharSequence)zzm)) {
            zzb.zzdd("Not linked for debug signals.");
            boolean1 = false;
        }
        else {
            boolean1 = Boolean.parseBoolean(zzm.trim());
            this.zzai(boolean1);
        }
        return boolean1;
    }
    
    protected String zzm(final Context context, final String s) {
        final zzlj<String> zza = new zzky(context).zza(s, (zzky.zza<String>)new zzky.zza<String>() {
            public String zzi(final InputStream inputStream) {
                try {
                    final String s = new String(zzo.zza(inputStream, true), "UTF-8");
                    final String zzbny = s;
                    com.google.android.gms.ads.internal.util.client.zzb.zzdd(new StringBuilder(49 + String.valueOf(zzbny).length() + String.valueOf(s).length()).append("Response received from server. \nURL: ").append(zzbny).append("\n Response: ").append(s).toString());
                    return s;
                }
                catch (IOException ex) {
                    final String value = String.valueOf(s);
                    String concat;
                    if (value.length() != 0) {
                        concat = "Error connecting to url: ".concat(value);
                    }
                    else {
                        concat = new String("Error connecting to url: ");
                    }
                    com.google.android.gms.ads.internal.util.client.zzb.zzd(concat, ex);
                    return null;
                }
            }
            
            public String zzuv() {
                final String value = String.valueOf(s);
                String concat;
                if (value.length() != 0) {
                    concat = "Error getting a response from: ".concat(value);
                }
                else {
                    concat = new String("Error getting a response from: ");
                }
                com.google.android.gms.ads.internal.util.client.zzb.zzdf(concat);
                return null;
            }
        });
        try {
            return (String)zza.get(zzdi.zzbhv.get(), TimeUnit.MILLISECONDS);
        }
        catch (TimeoutException ex) {
            final String value = String.valueOf(s);
            if (value.length() == 0) {
                goto Label_0098;
            }
            zzb.zzb("Timeout while retriving a response from: ".concat(value), ex);
            zza.cancel(true);
        }
        catch (InterruptedException ex2) {
            final String value2 = String.valueOf(s);
            String concat;
            if (value2.length() != 0) {
                concat = "Interrupted while retriving a response from: ".concat(value2);
            }
            else {
                concat = new String("Interrupted while retriving a response from: ");
            }
            zzb.zzb(concat, ex2);
            zza.cancel(true);
            goto Label_0092;
        }
        catch (Exception ex3) {
            final String value3 = String.valueOf(s);
            String concat2;
            if (value3.length() != 0) {
                concat2 = "Error retriving a response from: ".concat(value3);
            }
            else {
                concat2 = new String("Error retriving a response from: ");
            }
            zzb.zzb(concat2, ex3);
            goto Label_0092;
        }
    }
    
    public String zzut() {
        synchronized (this.zzakd) {
            return this.zzcrw;
        }
    }
    
    public boolean zzuu() {
        synchronized (this.zzakd) {
            return this.zzcrx;
        }
    }
}
