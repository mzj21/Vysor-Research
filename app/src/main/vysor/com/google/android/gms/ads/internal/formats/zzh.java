// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.formats;

import com.google.android.gms.internal.zzlt;
import com.google.android.gms.dynamic.zze;
import android.view.View$OnTouchListener;
import java.lang.ref.WeakReference;
import java.util.Map;
import android.view.View;
import android.view.View$OnClickListener;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import org.json.JSONObject;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zzau;
import android.content.Context;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.internal.zzgv;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzgu;
import com.google.android.gms.internal.zziy;

@zziy
public class zzh extends zzj
{
    private Object zzakd;
    @Nullable
    private zzgu zzbkh;
    @Nullable
    private zzgv zzbki;
    private final zzq zzbkj;
    @Nullable
    private zzi zzbkk;
    private boolean zzbkl;
    
    private zzh(final Context context, final zzq zzbkj, final zzau zzau, final zza zza) {
        super(context, zzbkj, null, zzau, null, zza, null, null);
        this.zzbkl = false;
        this.zzakd = new Object();
        this.zzbkj = zzbkj;
    }
    
    public zzh(final Context context, final zzq zzq, final zzau zzau, final zzgu zzbkh, final zza zza) {
        this(context, zzq, zzau, zza);
        this.zzbkh = zzbkh;
    }
    
    public zzh(final Context context, final zzq zzq, final zzau zzau, final zzgv zzbki, final zza zza) {
        this(context, zzq, zzau, zza);
        this.zzbki = zzbki;
    }
    
    @Override
    public void recordImpression() {
        while (true) {
            zzac.zzhq("recordImpression must be called on the main UI thread.");
            Label_0096: {
                synchronized (this.zzakd) {
                    this.zzr(true);
                    if (this.zzbkk != null) {
                        this.zzbkk.recordImpression();
                        this.zzbkj.recordImpression();
                    }
                    else {
                        try {
                            if (this.zzbkh == null || this.zzbkh.getOverrideImpressionRecording()) {
                                break Label_0096;
                            }
                            this.zzbkh.recordImpression();
                            this.zzbkj.recordImpression();
                        }
                        catch (RemoteException ex) {
                            zzb.zzd("Failed to call recordImpression", (Throwable)ex);
                        }
                    }
                    return;
                }
            }
            if (this.zzbki != null && !this.zzbki.getOverrideImpressionRecording()) {
                this.zzbki.recordImpression();
                this.zzbkj.recordImpression();
            }
        }
    }
    
    @Nullable
    @Override
    public com.google.android.gms.ads.internal.formats.zzb zza(final View$OnClickListener view$OnClickListener) {
        return null;
    }
    
    @Override
    public void zza(final View view, final Map<String, WeakReference<View>> map, final View$OnTouchListener view$OnTouchListener, final View$OnClickListener view$OnClickListener) {
        synchronized (this.zzakd) {
            this.zzbkl = true;
            while (true) {
                try {
                    if (this.zzbkh != null) {
                        this.zzbkh.zzl(zze.zzac(view));
                    }
                    else if (this.zzbki != null) {
                        this.zzbki.zzl(zze.zzac(view));
                    }
                    this.zzbkl = false;
                    return;
                }
                catch (RemoteException ex) {
                    zzb.zzd("Failed to call prepareAd", (Throwable)ex);
                    continue;
                }
                continue;
            }
        }
    }
    
    @Override
    public void zza(final View view, final Map<String, WeakReference<View>> map, final JSONObject jsonObject, final JSONObject jsonObject2, final JSONObject jsonObject3) {
        zzac.zzhq("performClick must be called on the main UI thread.");
        synchronized (this.zzakd) {
            if (this.zzbkk != null) {
                this.zzbkk.zza(view, map, jsonObject, jsonObject2, jsonObject3);
                this.zzbkj.onAdClicked();
            }
            else {
                try {
                    if (this.zzbkh != null && !this.zzbkh.getOverrideClickHandling()) {
                        this.zzbkh.zzk(zze.zzac(view));
                        this.zzbkj.onAdClicked();
                    }
                    if (this.zzbki != null && !this.zzbki.getOverrideClickHandling()) {
                        this.zzbki.zzk(zze.zzac(view));
                        this.zzbkj.onAdClicked();
                    }
                }
                catch (RemoteException ex) {
                    zzb.zzd("Failed to call performClick", (Throwable)ex);
                }
            }
        }
    }
    
    @Override
    public void zzb(final View view, final Map<String, WeakReference<View>> map) {
        synchronized (this.zzakd) {
            try {
                if (this.zzbkh != null) {
                    this.zzbkh.zzm(zze.zzac(view));
                }
                else if (this.zzbki != null) {
                    this.zzbki.zzm(zze.zzac(view));
                }
            }
            catch (RemoteException ex) {
                zzb.zzd("Failed to call untrackView", (Throwable)ex);
            }
        }
    }
    
    public void zzc(@Nullable final zzi zzbkk) {
        synchronized (this.zzakd) {
            this.zzbkk = zzbkk;
        }
    }
    
    public boolean zzlv() {
        synchronized (this.zzakd) {
            return this.zzbkl;
        }
    }
    
    public zzi zzlw() {
        synchronized (this.zzakd) {
            return this.zzbkk;
        }
    }
    
    @Nullable
    @Override
    public zzlt zzlx() {
        return null;
    }
}
