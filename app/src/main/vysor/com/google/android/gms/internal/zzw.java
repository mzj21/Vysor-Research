// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.net.URI;
import java.io.IOException;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import java.util.Iterator;
import java.util.Map;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.HttpClient;

public class zzw implements zzy
{
    protected final HttpClient zzcd;
    
    public zzw(final HttpClient zzcd) {
        this.zzcd = zzcd;
    }
    
    private static void zza(final HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, final zzk<?> zzk) throws com.google.android.gms.internal.zza {
        final byte[] zzp = zzk.zzp();
        if (zzp != null) {
            httpEntityEnclosingRequestBase.setEntity((HttpEntity)new ByteArrayEntity(zzp));
        }
    }
    
    private static void zza(final HttpUriRequest httpUriRequest, final Map<String, String> map) {
        for (final String s : map.keySet()) {
            httpUriRequest.setHeader(s, (String)map.get(s));
        }
    }
    
    static HttpUriRequest zzb(final zzk<?> zzk, final Map<String, String> map) throws com.google.android.gms.internal.zza {
        Object o = null;
        switch (zzk.getMethod()) {
            default: {
                throw new IllegalStateException("Unknown request method.");
            }
            case -1: {
                final byte[] zzl = zzk.zzl();
                if (zzl != null) {
                    o = new HttpPost(zzk.getUrl());
                    ((HttpPost)o).addHeader("Content-Type", zzk.zzk());
                    ((HttpPost)o).setEntity((HttpEntity)new ByteArrayEntity(zzl));
                    break;
                }
                o = new HttpGet(zzk.getUrl());
                break;
            }
            case 0: {
                o = new HttpGet(zzk.getUrl());
                break;
            }
            case 3: {
                o = new HttpDelete(zzk.getUrl());
                break;
            }
            case 1: {
                o = new HttpPost(zzk.getUrl());
                ((HttpPost)o).addHeader("Content-Type", zzk.zzo());
                zza((HttpEntityEnclosingRequestBase)o, zzk);
                break;
            }
            case 2: {
                o = new HttpPut(zzk.getUrl());
                ((HttpPut)o).addHeader("Content-Type", zzk.zzo());
                zza((HttpEntityEnclosingRequestBase)o, zzk);
                break;
            }
            case 4: {
                o = new HttpHead(zzk.getUrl());
                break;
            }
            case 5: {
                o = new HttpOptions(zzk.getUrl());
                break;
            }
            case 6: {
                o = new HttpTrace(zzk.getUrl());
                break;
            }
            case 7: {
                o = new zza(zzk.getUrl());
                ((zza)o).addHeader("Content-Type", zzk.zzo());
                zza((HttpEntityEnclosingRequestBase)o, zzk);
                break;
            }
        }
        return (HttpUriRequest)o;
    }
    
    @Override
    public HttpResponse zza(final zzk<?> zzk, final Map<String, String> map) throws IOException, com.google.android.gms.internal.zza {
        final HttpUriRequest zzb = zzb(zzk, map);
        zza(zzb, map);
        zza(zzb, zzk.getHeaders());
        this.zza(zzb);
        final HttpParams params = zzb.getParams();
        final int zzs = zzk.zzs();
        HttpConnectionParams.setConnectionTimeout(params, 5000);
        HttpConnectionParams.setSoTimeout(params, zzs);
        return this.zzcd.execute(zzb);
    }
    
    protected void zza(final HttpUriRequest httpUriRequest) throws IOException {
    }
    
    public static final class zza extends HttpEntityEnclosingRequestBase
    {
        public zza() {
        }
        
        public zza(final String s) {
            this.setURI(URI.create(s));
        }
        
        public String getMethod() {
            return "PATCH";
        }
    }
}
