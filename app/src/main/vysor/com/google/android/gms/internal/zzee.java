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
import com.google.android.gms.ads.formats.NativeContentAd;

@zziy
public class zzee extends NativeContentAd
{
    private final List<Image> zzblr;
    private final zzed zzblt;
    private final zzdy zzblu;
    
    public zzee(final zzed zzblt) {
        this.zzblr = new ArrayList<Image>();
        this.zzblt = zzblt;
        try {
            final List images = this.zzblt.getImages();
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
                    final zzdx zzlt = this.zzblt.zzlt();
                    if (zzlt != null) {
                        final zzdy zzblu = new zzdy(zzlt);
                        this.zzblu = zzblu;
                        return;
                    }
                }
                catch (RemoteException ex2) {
                    zzb.zzb("Failed to get icon.", (Throwable)ex2);
                }
                final zzdy zzblu = null;
                continue;
            }
        }
    }
    
    @Override
    public void destroy() {
        try {
            this.zzblt.destroy();
        }
        catch (RemoteException ex) {
            zzb.zzb("Failed to destroy", (Throwable)ex);
        }
    }
    
    @Override
    public CharSequence getAdvertiser() {
        try {
            return this.zzblt.getAdvertiser();
        }
        catch (RemoteException ex) {
            zzb.zzb("Failed to get attribution.", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public CharSequence getBody() {
        try {
            return this.zzblt.getBody();
        }
        catch (RemoteException ex) {
            zzb.zzb("Failed to get body.", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public CharSequence getCallToAction() {
        try {
            return this.zzblt.getCallToAction();
        }
        catch (RemoteException ex) {
            zzb.zzb("Failed to get call to action.", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public Bundle getExtras() {
        try {
            return this.zzblt.getExtras();
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to get extras", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public CharSequence getHeadline() {
        try {
            return this.zzblt.getHeadline();
        }
        catch (RemoteException ex) {
            zzb.zzb("Failed to get headline.", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public List<Image> getImages() {
        return this.zzblr;
    }
    
    @Override
    public Image getLogo() {
        return this.zzblu;
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
            return this.zzblt.zzlp();
        }
        catch (RemoteException ex) {
            zzb.zzb("Failed to retrieve native ad engine.", (Throwable)ex);
            return null;
        }
    }
}
