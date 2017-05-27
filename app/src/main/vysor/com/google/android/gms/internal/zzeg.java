// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeAd;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.List;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

@zziy
public class zzeg implements NativeCustomTemplateAd
{
    private final zzef zzblv;
    
    public zzeg(final zzef zzblv) {
        this.zzblv = zzblv;
    }
    
    @Override
    public List<String> getAvailableAssetNames() {
        try {
            return this.zzblv.getAvailableAssetNames();
        }
        catch (RemoteException ex) {
            zzb.zzb("Failed to get available asset names.", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public String getCustomTemplateId() {
        try {
            return this.zzblv.getCustomTemplateId();
        }
        catch (RemoteException ex) {
            zzb.zzb("Failed to get custom template id.", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public NativeAd.Image getImage(final String s) {
        while (true) {
            try {
                final zzdx zzax = this.zzblv.zzax(s);
                if (zzax != null) {
                    return new zzdy(zzax);
                }
            }
            catch (RemoteException ex) {
                zzb.zzb("Failed to get image.", (Throwable)ex);
            }
            return null;
        }
    }
    
    @Override
    public CharSequence getText(final String s) {
        try {
            return this.zzblv.zzaw(s);
        }
        catch (RemoteException ex) {
            zzb.zzb("Failed to get string.", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public void performClick(final String s) {
        try {
            this.zzblv.performClick(s);
        }
        catch (RemoteException ex) {
            zzb.zzb("Failed to perform click.", (Throwable)ex);
        }
    }
    
    @Override
    public void recordImpression() {
        try {
            this.zzblv.recordImpression();
        }
        catch (RemoteException ex) {
            zzb.zzb("Failed to record impression.", (Throwable)ex);
        }
    }
}
