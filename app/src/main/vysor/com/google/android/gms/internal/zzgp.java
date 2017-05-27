// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEventAdapter;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.android.gms.ads.mediation.NetworkExtras;
import java.util.Map;

@zziy
public final class zzgp extends zzgq.zza
{
    private Map<Class<? extends NetworkExtras>, NetworkExtras> zzbtx;
    
    private <NETWORK_EXTRAS extends com.google.ads.mediation.NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> zzgr zzbs(final String s) throws RemoteException {
        zzgr zzbt;
        try {
            final Class<?> forName = Class.forName(s, false, zzgp.class.getClassLoader());
            if (MediationAdapter.class.isAssignableFrom(forName)) {
                final MediationAdapter mediationAdapter = (MediationAdapter)forName.newInstance();
                zzbt = new zzhc<Object, Object>(mediationAdapter, this.zzbtx.get(mediationAdapter.getAdditionalParametersType()));
            }
            else {
                if (!com.google.android.gms.ads.mediation.MediationAdapter.class.isAssignableFrom(forName)) {
                    zzb.zzdf(new StringBuilder(64 + String.valueOf(s).length()).append("Could not instantiate mediation adapter: ").append(s).append(" (not a valid adapter).").toString());
                    throw new RemoteException();
                }
                zzbt = new zzgx((com.google.android.gms.ads.mediation.MediationAdapter)forName.newInstance());
            }
        }
        catch (Throwable t) {
            zzbt = this.zzbt(s);
        }
        return zzbt;
    }
    
    private zzgr zzbt(final String s) throws RemoteException {
        Label_0106: {
            try {
                zzb.zzdd("Reflection failed, retrying using direct instantiation");
                if ("com.google.ads.mediation.admob.AdMobAdapter".equals(s)) {
                    return new zzgx(new AdMobAdapter());
                }
                if ("com.google.ads.mediation.AdUrlAdapter".equals(s)) {
                    return new zzgx(new AdUrlAdapter());
                }
                break Label_0106;
            }
            catch (Throwable t) {
                zzb.zzd(new StringBuilder(43 + String.valueOf(s).length()).append("Could not instantiate mediation adapter: ").append(s).append(". ").toString(), t);
            }
            throw new RemoteException();
        }
        zzgr.zza zza;
        if ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(s)) {
            zza = new zzgx(new CustomEventAdapter());
        }
        else {
            if (!"com.google.ads.mediation.customevent.CustomEventAdapter".equals(s)) {
                throw new RemoteException();
            }
            final com.google.ads.mediation.customevent.CustomEventAdapter customEventAdapter = new com.google.ads.mediation.customevent.CustomEventAdapter();
            zza = new zzhc<Object, Object>((MediationAdapter<com.google.ads.mediation.NetworkExtras, MediationServerParameters>)customEventAdapter, (com.google.ads.mediation.NetworkExtras)this.zzbtx.get(customEventAdapter.getAdditionalParametersType()));
        }
        return zza;
    }
    
    public zzgr zzbq(final String s) throws RemoteException {
        return this.zzbs(s);
    }
    
    public boolean zzbr(final String s) throws RemoteException {
        try {
            return CustomEvent.class.isAssignableFrom(Class.forName(s, false, zzgp.class.getClassLoader()));
        }
        catch (Throwable t) {
            zzb.zzdf(new StringBuilder(80 + String.valueOf(s).length()).append("Could not load custom event implementation class: ").append(s).append(", assuming old implementation.").toString());
            return false;
        }
    }
    
    public void zzh(final Map<Class<? extends NetworkExtras>, NetworkExtras> zzbtx) {
        this.zzbtx = zzbtx;
    }
}
