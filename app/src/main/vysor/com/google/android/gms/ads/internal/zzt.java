// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.ads.internal.client.zzm;
import android.view.ViewGroup$LayoutParams;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.Map;
import android.net.Uri$Builder;
import com.google.android.gms.ads.internal.client.zzab;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.internal.zzib;
import com.google.android.gms.internal.zzhx;
import com.google.android.gms.internal.zzdu;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.internal.client.VideoOptionsParcel;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzcd;
import com.google.android.gms.internal.zzkq;
import java.util.concurrent.Callable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnTouchListener;
import com.google.android.gms.internal.zzdi;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebViewClient;
import android.net.Uri;
import android.content.Intent;
import android.os.AsyncTask;
import android.webkit.WebView;
import com.google.android.gms.internal.zzcc;
import java.util.concurrent.Future;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.zzq;
import android.content.Context;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.ads.internal.client.zzu;

@zziy
public class zzt extends zzu.zza
{
    private final Context mContext;
    @Nullable
    private zzq zzamy;
    private final VersionInfoParcel zzanh;
    private final AdSizeParcel zzapc;
    private final Future<zzcc> zzapd;
    private final zzb zzape;
    @Nullable
    private WebView zzapf;
    @Nullable
    private zzcc zzapg;
    private AsyncTask<Void, Void, Void> zzaph;
    
    public zzt(final Context mContext, final AdSizeParcel zzapc, final String s, final VersionInfoParcel zzanh) {
        this.mContext = mContext;
        this.zzanh = zzanh;
        this.zzapc = zzapc;
        this.zzapf = new WebView(this.mContext);
        this.zzapd = this.zzfp();
        this.zzape = new zzb(s);
        this.zzfm();
    }
    
    private void zzaa(final String s) {
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(s));
        this.mContext.startActivity(intent);
    }
    
    private void zzfm() {
        this.zzj(0);
        this.zzapf.setVerticalScrollBarEnabled(false);
        this.zzapf.getSettings().setJavaScriptEnabled(true);
        this.zzapf.setWebViewClient((WebViewClient)new WebViewClient() {
            public void onReceivedError(final WebView webView, final WebResourceRequest webResourceRequest, final WebResourceError webResourceError) {
                if (zzt.this.zzamy == null) {
                    return;
                }
                try {
                    zzt.this.zzamy.onAdFailedToLoad(0);
                }
                catch (RemoteException ex) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call AdListener.onAdFailedToLoad().", (Throwable)ex);
                }
            }
            
            public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
                boolean b;
                if (s.startsWith(zzt.this.zzfo())) {
                    b = false;
                }
                else {
                    if (s.startsWith(zzdi.zzbgv.get())) {
                        while (true) {
                            if (zzt.this.zzamy == null) {
                                break Label_0059;
                            }
                            try {
                                zzt.this.zzamy.onAdFailedToLoad(3);
                                zzt.this.zzj(0);
                                b = true;
                                return b;
                            }
                            catch (RemoteException ex) {
                                com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call AdListener.onAdFailedToLoad().", (Throwable)ex);
                                continue;
                            }
                            break;
                        }
                    }
                    if (s.startsWith(zzdi.zzbgw.get())) {
                        while (true) {
                            if (zzt.this.zzamy == null) {
                                break Label_0124;
                            }
                            try {
                                zzt.this.zzamy.onAdFailedToLoad(0);
                                zzt.this.zzj(0);
                                b = true;
                                return b;
                            }
                            catch (RemoteException ex2) {
                                com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call AdListener.onAdFailedToLoad().", (Throwable)ex2);
                                continue;
                            }
                            break;
                        }
                    }
                    if (s.startsWith(zzdi.zzbgx.get())) {
                        while (true) {
                            if (zzt.this.zzamy == null) {
                                break Label_0188;
                            }
                            try {
                                zzt.this.zzamy.onAdLoaded();
                                zzt.this.zzj(zzt.this.zzy(s));
                                b = true;
                                return b;
                            }
                            catch (RemoteException ex3) {
                                com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call AdListener.onAdLoaded().", (Throwable)ex3);
                                continue;
                            }
                            break;
                        }
                    }
                    if (s.startsWith("gmsg://")) {
                        b = true;
                    }
                    else {
                        while (true) {
                            if (zzt.this.zzamy == null) {
                                break Label_0262;
                            }
                            try {
                                zzt.this.zzamy.onAdLeftApplication();
                                zzt.this.zzaa(zzt.this.zzz(s));
                                b = true;
                            }
                            catch (RemoteException ex4) {
                                com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call AdListener.onAdLeftApplication().", (Throwable)ex4);
                                continue;
                            }
                            break;
                        }
                    }
                }
                return b;
            }
        });
        this.zzapf.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                if (zzt.this.zzapg == null) {
                    return false;
                }
                try {
                    zzt.this.zzapg.zza(motionEvent);
                    return false;
                }
                catch (RemoteException ex) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Unable to process ad data", (Throwable)ex);
                    return false;
                }
            }
        });
    }
    
    private Future<zzcc> zzfp() {
        return (Future<zzcc>)zzkq.zza((Callable<Object>)new Callable<zzcc>() {
            public zzcc zzfq() throws Exception {
                return new zzcc(zzt.this.zzanh.zzcs, zzt.this.mContext, false);
            }
        });
    }
    
    private String zzz(String string) {
        if (this.zzapg != null) {
            Uri uri = Uri.parse(string);
            while (true) {
                try {
                    uri = this.zzapg.zzd(uri, this.mContext);
                    string = uri.toString();
                }
                catch (RemoteException ex) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Unable to process ad data", (Throwable)ex);
                    continue;
                }
                catch (zzcd zzcd) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Unable to parse ad click url", zzcd);
                    continue;
                }
                break;
            }
        }
        return string;
    }
    
    public void destroy() throws RemoteException {
        zzac.zzhq("destroy must be called on the main UI thread.");
        this.zzaph.cancel(true);
        this.zzapd.cancel(true);
        this.zzapf.destroy();
        this.zzapf = null;
    }
    
    @Nullable
    public String getMediationAdapterClassName() throws RemoteException {
        return null;
    }
    
    public boolean isLoading() throws RemoteException {
        return false;
    }
    
    public boolean isReady() throws RemoteException {
        return false;
    }
    
    public void pause() throws RemoteException {
        zzac.zzhq("pause must be called on the main UI thread.");
    }
    
    public void resume() throws RemoteException {
        zzac.zzhq("resume must be called on the main UI thread.");
    }
    
    public void setManualImpressionsEnabled(final boolean b) throws RemoteException {
    }
    
    public void setUserId(final String s) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }
    
    public void showInterstitial() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }
    
    public void stopLoading() throws RemoteException {
    }
    
    public void zza(final AdSizeParcel adSizeParcel) throws RemoteException {
        throw new IllegalStateException("AdSize must be set before initialization");
    }
    
    public void zza(final VideoOptionsParcel videoOptionsParcel) {
        throw new IllegalStateException("Unused method");
    }
    
    public void zza(final zzp zzp) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }
    
    public void zza(final zzq zzamy) throws RemoteException {
        this.zzamy = zzamy;
    }
    
    public void zza(final zzw zzw) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }
    
    public void zza(final zzy zzy) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }
    
    public void zza(final zzd zzd) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }
    
    public void zza(final zzdu zzdu) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }
    
    public void zza(final zzhx zzhx) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }
    
    public void zza(final zzib zzib, final String s) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }
    
    public boolean zzb(final AdRequestParcel adRequestParcel) throws RemoteException {
        zzac.zzb(this.zzapf, "This Search Ad has already been torn down");
        this.zzape.zzi(adRequestParcel);
        this.zzaph = (AsyncTask<Void, Void, Void>)new zza().execute((Object[])new Void[0]);
        return true;
    }
    
    public com.google.android.gms.dynamic.zzd zzds() throws RemoteException {
        zzac.zzhq("getAdFrame must be called on the main UI thread.");
        return zze.zzac(this.zzapf);
    }
    
    public AdSizeParcel zzdt() throws RemoteException {
        return this.zzapc;
    }
    
    public void zzdv() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }
    
    @Nullable
    public zzab zzdw() {
        return null;
    }
    
    String zzfn() {
        final Uri$Builder uri$Builder = new Uri$Builder();
        uri$Builder.scheme("https://").appendEncodedPath((String)zzdi.zzbgy.get());
        uri$Builder.appendQueryParameter("query", this.zzape.getQuery());
        uri$Builder.appendQueryParameter("pubId", this.zzape.zzfs());
        final Map<String, String> zzft = this.zzape.zzft();
        for (final String s : zzft.keySet()) {
            uri$Builder.appendQueryParameter(s, (String)zzft.get(s));
        }
        final Uri build = uri$Builder.build();
        if (this.zzapg == null) {
            goto Label_0231;
        }
        try {
            final Uri zzc = this.zzapg.zzc(build, this.mContext);
            final String value = String.valueOf(this.zzfo());
            final String value2 = String.valueOf(zzc.getEncodedQuery());
            return new StringBuilder(1 + String.valueOf(value).length() + String.valueOf(value2).length()).append(value).append("#").append(value2).toString();
        }
        catch (RemoteException ex) {}
        catch (zzcd zzcd) {
            goto Label_0224;
        }
    }
    
    String zzfo() {
        final String zzfr = this.zzape.zzfr();
        String s;
        if (TextUtils.isEmpty((CharSequence)zzfr)) {
            s = "www.google.com";
        }
        else {
            s = zzfr;
        }
        final String value = String.valueOf("https://");
        final String s2 = zzdi.zzbgy.get();
        return new StringBuilder(0 + String.valueOf(value).length() + String.valueOf(s).length() + String.valueOf(s2).length()).append(value).append(s).append(s2).toString();
    }
    
    void zzj(final int n) {
        if (this.zzapf != null) {
            this.zzapf.setLayoutParams(new ViewGroup$LayoutParams(-1, n));
        }
    }
    
    int zzy(final String s) {
        final String queryParameter = Uri.parse(s).getQueryParameter("mHeight");
        final boolean empty = TextUtils.isEmpty((CharSequence)queryParameter);
        int zzb = 0;
        if (!empty) {
            try {
                zzb = zzm.zzjr().zzb(this.mContext, Integer.parseInt(queryParameter));
            }
            catch (NumberFormatException ex) {
                zzb = 0;
            }
        }
        return zzb;
    }
    
    private class zza extends AsyncTask<Void, Void, Void>
    {
        protected Void doInBackground(final Void... array) {
            try {
                zzt.this.zzapg = zzt.this.zzapd.get(zzdi.zzbha.get(), TimeUnit.MILLISECONDS);
                return null;
            }
            catch (InterruptedException ex) {}
            catch (TimeoutException ex2) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("Timed out waiting for ad data");
                return null;
            }
            catch (ExecutionException ex3) {
                goto Label_0041;
            }
        }
        
        protected void onPostExecute(final Void void1) {
            final String zzfn = zzt.this.zzfn();
            if (zzt.this.zzapf != null) {
                zzt.this.zzapf.loadUrl(zzfn);
            }
        }
    }
    
    private static class zzb
    {
        private final String zzapj;
        private final Map<String, String> zzapk;
        private String zzapl;
        private String zzapm;
        
        public zzb(final String zzapj) {
            this.zzapj = zzapj;
            this.zzapk = new TreeMap<String, String>();
        }
        
        public String getQuery() {
            return this.zzapl;
        }
        
        public String zzfr() {
            return this.zzapm;
        }
        
        public String zzfs() {
            return this.zzapj;
        }
        
        public Map<String, String> zzft() {
            return this.zzapk;
        }
        
        public void zzi(final AdRequestParcel adRequestParcel) {
            this.zzapl = adRequestParcel.zzawk.zzbab;
            Bundle bundle;
            if (adRequestParcel.zzawn != null) {
                bundle = adRequestParcel.zzawn.getBundle(AdMobAdapter.class.getName());
            }
            else {
                bundle = null;
            }
            if (bundle != null) {
                final String s = zzdi.zzbgz.get();
                for (final String s2 : bundle.keySet()) {
                    if (s.equals(s2)) {
                        this.zzapm = bundle.getString(s2);
                    }
                    else {
                        if (!s2.startsWith("csa_")) {
                            continue;
                        }
                        this.zzapk.put(s2.substring("csa_".length()), bundle.getString(s2));
                    }
                }
            }
        }
    }
}
