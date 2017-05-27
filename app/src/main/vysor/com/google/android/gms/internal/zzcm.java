// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.Map;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.formats.zzi;
import android.view.View;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import java.util.ArrayList;
import java.util.WeakHashMap;
import android.content.Context;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;

@zziy
public class zzcm implements zzcn
{
    private final Object zzakd;
    private final VersionInfoParcel zzanh;
    private final Context zzask;
    private final WeakHashMap<zzke, zzcj> zzatl;
    private final ArrayList<zzcj> zzatm;
    private final zzfy zzatn;
    
    public zzcm(final Context context, final VersionInfoParcel zzanh, final zzfy zzatn) {
        this.zzakd = new Object();
        this.zzatl = new WeakHashMap<zzke, zzcj>();
        this.zzatm = new ArrayList<zzcj>();
        this.zzask = context.getApplicationContext();
        this.zzanh = zzanh;
        this.zzatn = zzatn;
    }
    
    public zzcj zza(final AdSizeParcel adSizeParcel, final zzke zzke) {
        return this.zza(adSizeParcel, zzke, zzke.zzbyh.getView());
    }
    
    public zzcj zza(final AdSizeParcel adSizeParcel, final zzke zzke, final View view) {
        return this.zza(adSizeParcel, zzke, new zzcj.zzd(view, zzke), null);
    }
    
    public zzcj zza(final AdSizeParcel adSizeParcel, final zzke zzke, final View view, final zzfz zzfz) {
        return this.zza(adSizeParcel, zzke, new zzcj.zzd(view, zzke), zzfz);
    }
    
    public zzcj zza(final AdSizeParcel adSizeParcel, final zzke zzke, final zzi zzi) {
        return this.zza(adSizeParcel, zzke, new zzcj.zza(zzi), null);
    }
    
    public zzcj zza(final AdSizeParcel adSizeParcel, final zzke zzke, final zzcq zzcq, @Nullable final zzfz zzfz) {
        zzcj zzcj;
        while (true) {
            while (true) {
                synchronized (this.zzakd) {
                    if (this.zzi(zzke)) {
                        zzcj = this.zzatl.get(zzke);
                        break;
                    }
                    if (zzfz != null) {
                        zzcj = new zzco(this.zzask, adSizeParcel, zzke, this.zzanh, zzcq, zzfz);
                        zzcj.zza(this);
                        this.zzatl.put(zzke, zzcj);
                        this.zzatm.add(zzcj);
                        break;
                    }
                }
                zzcj = new zzcp(this.zzask, adSizeParcel, zzke, this.zzanh, zzcq, this.zzatn);
                continue;
            }
        }
        return zzcj;
    }
    
    @Override
    public void zza(final zzcj zzcj) {
        synchronized (this.zzakd) {
            if (!zzcj.zzhn()) {
                this.zzatm.remove(zzcj);
                final Iterator<Map.Entry<zzke, zzcj>> iterator = this.zzatl.entrySet().iterator();
                while (iterator.hasNext()) {
                    if (iterator.next().getValue() == zzcj) {
                        iterator.remove();
                    }
                }
            }
        }
    }
    // monitorexit(o)
    
    public boolean zzi(final zzke zzke) {
        while (true) {
            synchronized (this.zzakd) {
                final zzcj zzcj = this.zzatl.get(zzke);
                if (zzcj != null && zzcj.zzhn()) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public void zzj(final zzke zzke) {
        synchronized (this.zzakd) {
            final zzcj zzcj = this.zzatl.get(zzke);
            if (zzcj != null) {
                zzcj.zzhl();
            }
        }
    }
    
    public void zzk(final zzke zzke) {
        synchronized (this.zzakd) {
            final zzcj zzcj = this.zzatl.get(zzke);
            if (zzcj != null) {
                zzcj.stop();
            }
        }
    }
    
    public void zzl(final zzke zzke) {
        synchronized (this.zzakd) {
            final zzcj zzcj = this.zzatl.get(zzke);
            if (zzcj != null) {
                zzcj.pause();
            }
        }
    }
    
    public void zzm(final zzke zzke) {
        synchronized (this.zzakd) {
            final zzcj zzcj = this.zzatl.get(zzke);
            if (zzcj != null) {
                zzcj.resume();
            }
        }
    }
}
