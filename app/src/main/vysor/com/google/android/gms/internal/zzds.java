// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import android.view.View;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.doubleclick.CustomRenderedAd;

@zziy
public class zzds implements CustomRenderedAd
{
    private final zzdt zzbix;
    
    public zzds(final zzdt zzbix) {
        this.zzbix = zzbix;
    }
    
    @Override
    public String getBaseUrl() {
        try {
            return this.zzbix.zzle();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not delegate getBaseURL to CustomRenderedAd", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public String getContent() {
        try {
            return this.zzbix.getContent();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not delegate getContent to CustomRenderedAd", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public void onAdRendered(final View view) {
        try {
            final zzdt zzbix = this.zzbix;
            zzd zzac;
            if (view != null) {
                zzac = zze.zzac(view);
            }
            else {
                zzac = null;
            }
            zzbix.zzi(zzac);
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not delegate onAdRendered to CustomRenderedAd", (Throwable)ex);
        }
    }
    
    @Override
    public void recordClick() {
        try {
            this.zzbix.recordClick();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not delegate recordClick to CustomRenderedAd", (Throwable)ex);
        }
    }
    
    @Override
    public void recordImpression() {
        try {
            this.zzbix.recordImpression();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not delegate recordImpression to CustomRenderedAd", (Throwable)ex);
        }
    }
}
