// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.client.zzm;
import android.text.TextUtils;
import android.support.annotation.Nullable;
import java.util.HashMap;
import com.google.android.gms.ads.internal.util.client.zza;
import java.util.Map;
import com.google.android.gms.ads.internal.zzu;
import java.lang.ref.WeakReference;
import android.content.Context;
import com.google.android.gms.common.api.Releasable;

@zziy
public abstract class zzfj implements Releasable
{
    protected Context mContext;
    protected String zzbnw;
    protected WeakReference<zzlt> zzbnx;
    
    public zzfj(final zzlt zzlt) {
        this.mContext = zzlt.getContext();
        this.zzbnw = zzu.zzfz().zzg(this.mContext, zzlt.zzvu().zzcs);
        this.zzbnx = new WeakReference<zzlt>(zzlt);
    }
    
    private void zza(final String s, final Map<String, String> map) {
        final zzlt zzlt = this.zzbnx.get();
        if (zzlt != null) {
            zzlt.zza(s, map);
        }
    }
    
    private String zzbe(final String s) {
        String s2 = "internal";
        switch (s) {
            case "error":
            case "playerFailed":
            case "inProgress":
            case "contentLengthMissing": {
                s2 = "internal";
                break;
            }
            case "noCacheDir":
            case "expireFailed": {
                s2 = "io";
                break;
            }
            case "badUrl":
            case "downloadTimeout": {
                s2 = "network";
                break;
            }
            case "sizeExceeded":
            case "externalAbort": {
                s2 = "policy";
                break;
            }
        }
        return s2;
    }
    
    public abstract void abort();
    
    @Override
    public void release() {
    }
    
    protected void zza(final String s, final String s2, final int n) {
        zza.zzctj.post((Runnable)new Runnable() {
            @Override
            public void run() {
                final HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("event", "precacheComplete");
                hashMap.put("src", s);
                hashMap.put("cachedSrc", s2);
                hashMap.put("totalBytes", Integer.toString(n));
                zzfj.this.zza("onPrecacheEvent", hashMap);
            }
        });
    }
    
    protected void zza(final String s, final String s2, final int n, final int n2, final boolean b) {
        zza.zzctj.post((Runnable)new Runnable() {
            @Override
            public void run() {
                final HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("event", "precacheProgress");
                hashMap.put("src", s);
                hashMap.put("cachedSrc", s2);
                hashMap.put("bytesLoaded", Integer.toString(n));
                hashMap.put("totalBytes", Integer.toString(n2));
                String s;
                if (b) {
                    s = "1";
                }
                else {
                    s = "0";
                }
                hashMap.put("cacheReady", s);
                zzfj.this.zza("onPrecacheEvent", hashMap);
            }
        });
    }
    
    public void zza(final String s, final String s2, final String s3, @Nullable final String s4) {
        zza.zzctj.post((Runnable)new Runnable() {
            @Override
            public void run() {
                final HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("event", "precacheCanceled");
                hashMap.put("src", s);
                if (!TextUtils.isEmpty((CharSequence)s2)) {
                    hashMap.put("cachedSrc", s2);
                }
                hashMap.put("type", zzfj.this.zzbe(s3));
                hashMap.put("reason", s3);
                if (!TextUtils.isEmpty((CharSequence)s4)) {
                    hashMap.put("message", s4);
                }
                zzfj.this.zza("onPrecacheEvent", hashMap);
            }
        });
    }
    
    public abstract boolean zzbc(final String p0);
    
    protected String zzbd(final String s) {
        return zzm.zzjr().zzdc(s);
    }
}
