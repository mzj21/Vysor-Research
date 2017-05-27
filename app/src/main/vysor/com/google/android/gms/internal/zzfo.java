// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.reward.client.zza;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.zzl;
import java.util.Iterator;
import android.os.Handler;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.LinkedList;
import java.util.List;

@zziy
class zzfo
{
    private final List<zza> zzamv;
    
    zzfo() {
        this.zzamv = new LinkedList<zza>();
    }
    
    void zza(final zzfp zzfp) {
        final Handler zzcrf = zzkr.zzcrf;
        final Iterator<zza> iterator = this.zzamv.iterator();
        while (iterator.hasNext()) {
            zzcrf.post((Runnable)new Runnable() {
                final /* synthetic */ zza zzbow = iterator.next();
                
                @Override
                public void run() {
                    try {
                        this.zzbow.zzb(zzfp);
                    }
                    catch (RemoteException ex) {
                        zzb.zzd("Could not propagate interstitial ad event.", (Throwable)ex);
                    }
                }
            });
        }
        this.zzamv.clear();
    }
    
    void zzc(final zzl zzl) {
        zzl.zza(new zzq.zza() {
            public void onAdClosed() throws RemoteException {
                zzfo.this.zzamv.add(new zzfo.zza() {
                    @Override
                    public void zzb(final zzfp zzfp) throws RemoteException {
                        if (zzfp.zzamy != null) {
                            zzfp.zzamy.onAdClosed();
                        }
                        zzu.zzgo().zzmm();
                    }
                });
            }
            
            public void onAdFailedToLoad(final int n) throws RemoteException {
                zzfo.this.zzamv.add(new zzfo.zza() {
                    @Override
                    public void zzb(final zzfp zzfp) throws RemoteException {
                        if (zzfp.zzamy != null) {
                            zzfp.zzamy.onAdFailedToLoad(n);
                        }
                    }
                });
                zzkn.v("Pooled interstitial failed to load.");
            }
            
            public void onAdLeftApplication() throws RemoteException {
                zzfo.this.zzamv.add(new zzfo.zza() {
                    @Override
                    public void zzb(final zzfp zzfp) throws RemoteException {
                        if (zzfp.zzamy != null) {
                            zzfp.zzamy.onAdLeftApplication();
                        }
                    }
                });
            }
            
            public void onAdLoaded() throws RemoteException {
                zzfo.this.zzamv.add(new zzfo.zza() {
                    @Override
                    public void zzb(final zzfp zzfp) throws RemoteException {
                        if (zzfp.zzamy != null) {
                            zzfp.zzamy.onAdLoaded();
                        }
                    }
                });
                zzkn.v("Pooled interstitial loaded.");
            }
            
            public void onAdOpened() throws RemoteException {
                zzfo.this.zzamv.add(new zzfo.zza() {
                    @Override
                    public void zzb(final zzfp zzfp) throws RemoteException {
                        if (zzfp.zzamy != null) {
                            zzfp.zzamy.onAdOpened();
                        }
                    }
                });
            }
        });
        zzl.zza(new zzw.zza() {
            public void onAppEvent(final String s, final String s2) throws RemoteException {
                zzfo.this.zzamv.add(new zzfo.zza() {
                    @Override
                    public void zzb(final zzfp zzfp) throws RemoteException {
                        if (zzfp.zzboy != null) {
                            zzfp.zzboy.onAppEvent(s, s2);
                        }
                    }
                });
            }
        });
        zzl.zza(new zzhx.zza() {
            public void zza(final zzhw zzhw) throws RemoteException {
                zzfo.this.zzamv.add(new zzfo.zza() {
                    @Override
                    public void zzb(final zzfp zzfp) throws RemoteException {
                        if (zzfp.zzboz != null) {
                            zzfp.zzboz.zza(zzhw);
                        }
                    }
                });
            }
        });
        zzl.zza(new zzdu.zza() {
            public void zza(final zzdt zzdt) throws RemoteException {
                zzfo.this.zzamv.add(new zzfo.zza() {
                    @Override
                    public void zzb(final zzfp zzfp) throws RemoteException {
                        if (zzfp.zzbpa != null) {
                            zzfp.zzbpa.zza(zzdt);
                        }
                    }
                });
            }
        });
        zzl.zza(new zzp.zza() {
            public void onAdClicked() throws RemoteException {
                zzfo.this.zzamv.add(new zzfo.zza() {
                    @Override
                    public void zzb(final zzfp zzfp) throws RemoteException {
                        if (zzfp.zzbpb != null) {
                            zzfp.zzbpb.onAdClicked();
                        }
                    }
                });
            }
        });
        zzl.zza(new zzd.zza() {
            public void onRewardedVideoAdClosed() throws RemoteException {
                zzfo.this.zzamv.add(new zzfo.zza() {
                    @Override
                    public void zzb(final zzfp zzfp) throws RemoteException {
                        if (zzfp.zzbpc != null) {
                            zzfp.zzbpc.onRewardedVideoAdClosed();
                        }
                    }
                });
            }
            
            public void onRewardedVideoAdFailedToLoad(final int n) throws RemoteException {
                zzfo.this.zzamv.add(new zzfo.zza() {
                    @Override
                    public void zzb(final zzfp zzfp) throws RemoteException {
                        if (zzfp.zzbpc != null) {
                            zzfp.zzbpc.onRewardedVideoAdFailedToLoad(n);
                        }
                    }
                });
            }
            
            public void onRewardedVideoAdLeftApplication() throws RemoteException {
                zzfo.this.zzamv.add(new zzfo.zza() {
                    @Override
                    public void zzb(final zzfp zzfp) throws RemoteException {
                        if (zzfp.zzbpc != null) {
                            zzfp.zzbpc.onRewardedVideoAdLeftApplication();
                        }
                    }
                });
            }
            
            public void onRewardedVideoAdLoaded() throws RemoteException {
                zzfo.this.zzamv.add(new zzfo.zza() {
                    @Override
                    public void zzb(final zzfp zzfp) throws RemoteException {
                        if (zzfp.zzbpc != null) {
                            zzfp.zzbpc.onRewardedVideoAdLoaded();
                        }
                    }
                });
            }
            
            public void onRewardedVideoAdOpened() throws RemoteException {
                zzfo.this.zzamv.add(new zzfo.zza() {
                    @Override
                    public void zzb(final zzfp zzfp) throws RemoteException {
                        if (zzfp.zzbpc != null) {
                            zzfp.zzbpc.onRewardedVideoAdOpened();
                        }
                    }
                });
            }
            
            public void onRewardedVideoStarted() throws RemoteException {
                zzfo.this.zzamv.add(new zzfo.zza() {
                    @Override
                    public void zzb(final zzfp zzfp) throws RemoteException {
                        if (zzfp.zzbpc != null) {
                            zzfp.zzbpc.onRewardedVideoStarted();
                        }
                    }
                });
            }
            
            public void zza(final zza zza) throws RemoteException {
                zzfo.this.zzamv.add(new zzfo.zza() {
                    @Override
                    public void zzb(final zzfp zzfp) throws RemoteException {
                        if (zzfp.zzbpc != null) {
                            zzfp.zzbpc.zza(zza);
                        }
                    }
                });
            }
        });
    }
    
    interface zza
    {
        void zzb(final zzfp p0) throws RemoteException;
    }
}
