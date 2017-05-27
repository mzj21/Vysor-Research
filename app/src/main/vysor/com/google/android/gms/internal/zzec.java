// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.zzd;
import android.os.IBinder;
import android.os.Bundle;
import java.util.Iterator;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.ArrayList;
import com.google.android.gms.ads.formats.NativeAd;
import java.util.List;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAppInstallAd;

@zziy
public class zzec extends NativeAppInstallAd
{
    private VideoController zzayu;
    private final zzeb zzblq;
    private final List<Image> zzblr;
    private final zzdy zzbls;
    
    public zzec(final zzeb zzblq) {
        this.zzblr = new ArrayList<Image>();
        this.zzayu = new VideoController();
        this.zzblq = zzblq;
        try {
            final List images = this.zzblq.getImages();
            if (images != null) {
                final Iterator<Object> iterator = images.iterator();
                while (iterator.hasNext()) {
                    final zzdx zze = this.zze(iterator.next());
                    if (zze != null) {
                        this.zzblr.add(new zzdy(zze));
                    }
                }
            }
        }
        catch (RemoteException ex) {
            zzb.zzb("Failed to get image.", (Throwable)ex);
        }
        while (true) {
            while (true) {
                try {
                    final zzdx zzlo = this.zzblq.zzlo();
                    if (zzlo != null) {
                        final zzdy zzbls = new zzdy(zzlo);
                        this.zzbls = zzbls;
                        return;
                    }
                }
                catch (RemoteException ex2) {
                    zzb.zzb("Failed to get icon.", (Throwable)ex2);
                }
                final zzdy zzbls = null;
                continue;
            }
        }
    }
    
    @Override
    public void destroy() {
        try {
            this.zzblq.destroy();
        }
        catch (RemoteException ex) {
            zzb.zzb("Failed to destroy", (Throwable)ex);
        }
    }
    
    @Override
    public CharSequence getBody() {
        try {
            return this.zzblq.getBody();
        }
        catch (RemoteException ex) {
            zzb.zzb("Failed to get body.", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public CharSequence getCallToAction() {
        try {
            return this.zzblq.getCallToAction();
        }
        catch (RemoteException ex) {
            zzb.zzb("Failed to get call to action.", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public Bundle getExtras() {
        try {
            return this.zzblq.getExtras();
        }
        catch (RemoteException ex) {
            zzb.zzb("Failed to get extras", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public CharSequence getHeadline() {
        try {
            return this.zzblq.getHeadline();
        }
        catch (RemoteException ex) {
            zzb.zzb("Failed to get headline.", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public Image getIcon() {
        return this.zzbls;
    }
    
    @Override
    public List<Image> getImages() {
        return this.zzblr;
    }
    
    @Override
    public CharSequence getPrice() {
        try {
            return this.zzblq.getPrice();
        }
        catch (RemoteException ex) {
            zzb.zzb("Failed to get price.", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public Double getStarRating() {
        Double value = null;
        try {
            final double starRating = this.zzblq.getStarRating();
            if (starRating == -1.0) {
                value = null;
            }
            else {
                value = starRating;
            }
        }
        catch (RemoteException ex) {
            zzb.zzb("Failed to get star rating.", (Throwable)ex);
        }
        return value;
    }
    
    @Override
    public CharSequence getStore() {
        try {
            return this.zzblq.getStore();
        }
        catch (RemoteException ex) {
            zzb.zzb("Failed to get store", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public VideoController getVideoController() {
        try {
            if (this.zzblq.zzdw() != null) {
                this.zzayu.zza(this.zzblq.zzdw());
            }
            return this.zzayu;
        }
        catch (RemoteException ex) {
            zzb.zzb("Exception occurred while getting video controller", (Throwable)ex);
            return this.zzayu;
        }
    }
    
    zzdx zze(final Object o) {
        zzdx zzab;
        if (o instanceof IBinder) {
            zzab = zzdx.zza.zzab((IBinder)o);
        }
        else {
            zzab = null;
        }
        return zzab;
    }
    
    protected zzd zzlp() {
        try {
            return this.zzblq.zzlp();
        }
        catch (RemoteException ex) {
            zzb.zzb("Failed to retrieve native ad engine.", (Throwable)ex);
            return null;
        }
    }
}
