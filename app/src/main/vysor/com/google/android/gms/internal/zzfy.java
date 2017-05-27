// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;
import com.google.android.gms.ads.internal.zzu;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.content.Context;

@zziy
public class zzfy
{
    private final Context mContext;
    private final Object zzakd;
    private final VersionInfoParcel zzanh;
    private final String zzbqi;
    private zzkw<zzfv> zzbqj;
    private zzkw<zzfv> zzbqk;
    @Nullable
    private zzd zzbql;
    private int zzbqm;
    
    public zzfy(final Context context, final VersionInfoParcel zzanh, final String zzbqi) {
        this.zzakd = new Object();
        this.zzbqm = 1;
        this.zzbqi = zzbqi;
        this.mContext = context.getApplicationContext();
        this.zzanh = zzanh;
        this.zzbqj = new zzb<zzfv>();
        this.zzbqk = new zzb<zzfv>();
    }
    
    public zzfy(final Context context, final VersionInfoParcel versionInfoParcel, final String s, final zzkw<zzfv> zzbqj, final zzkw<zzfv> zzbqk) {
        this(context, versionInfoParcel, s);
        this.zzbqj = zzbqj;
        this.zzbqk = zzbqk;
    }
    
    private zzd zza(@Nullable final zzau zzau) {
        final zzd zzd = new zzd(this.zzbqk);
        zzu.zzfz().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final zzfv zza = zzfy.this.zza(zzfy.this.mContext, zzfy.this.zzanh, zzau);
                zza.zza((zzfv.zza)new zzfv.zza() {
                    @Override
                    public void zzmx() {
                        zzkr.zzcrf.postDelayed((Runnable)new Runnable() {
                            @Override
                            public void run() {
                                synchronized (zzfy.this.zzakd) {
                                    if (zzd.getStatus() != -1 && zzd.getStatus() != 1) {
                                        zzd.reject();
                                        zzu.zzfz().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                zza.destroy();
                                            }
                                        });
                                        zzkn.v("Could not receive loaded message in a timely manner. Rejecting.");
                                    }
                                }
                            }
                        }, (long)zzfy.zza.zzbqy);
                    }
                });
                zza.zza("/jsLoaded", new zzev() {
                    @Override
                    public void zza(final zzlt zzlt, final Map<String, String> map) {
                        synchronized (zzfy.this.zzakd) {
                            if (zzd.getStatus() != -1 && zzd.getStatus() != 1) {
                                zzfy.this.zzbqm = 0;
                                zzfy.this.zzbqj.zzd(zza);
                                zzd.zzg(zza);
                                zzfy.this.zzbql = zzd;
                                zzkn.v("Successfully loaded JS Engine.");
                            }
                        }
                    }
                });
                final zzld<zzfy$1$3> zzld = new zzld<zzfy$1$3>();
                final zzev zzev = new zzev() {
                    @Override
                    public void zza(final zzlt zzlt, final Map<String, String> map) {
                        synchronized (zzfy.this.zzakd) {
                            com.google.android.gms.ads.internal.util.client.zzb.zzde("JS Engine is requesting an update");
                            if (zzfy.this.zzbqm == 0) {
                                com.google.android.gms.ads.internal.util.client.zzb.zzde("Starting reload.");
                                zzfy.this.zzbqm = 2;
                                zzfy.this.zzb(zzau);
                            }
                            zza.zzb("/requestReload", zzld.get());
                        }
                    }
                };
                zzld.set(zzev);
                zza.zza("/requestReload", zzev);
                if (zzfy.this.zzbqi.endsWith(".js")) {
                    zza.zzbk(zzfy.this.zzbqi);
                }
                else if (zzfy.this.zzbqi.startsWith("<html>")) {
                    zza.zzbm(zzfy.this.zzbqi);
                }
                else {
                    zza.zzbl(zzfy.this.zzbqi);
                }
                zzkr.zzcrf.postDelayed((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        synchronized (zzfy.this.zzakd) {
                            if (zzd.getStatus() != -1 && zzd.getStatus() != 1) {
                                zzd.reject();
                                zzu.zzfz().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        zza.destroy();
                                    }
                                });
                                zzkn.v("Could not receive loaded message in a timely manner. Rejecting.");
                            }
                        }
                    }
                }, (long)zzfy.zza.zzbqx);
            }
        });
        return zzd;
    }
    
    protected zzfv zza(final Context context, final VersionInfoParcel versionInfoParcel, @Nullable final zzau zzau) {
        return new zzfx(context, versionInfoParcel, zzau, null);
    }
    
    protected zzd zzb(@Nullable final zzau zzau) {
        final zzd zza = this.zza(zzau);
        zza.zza(new zzlm.zzc<zzfv>() {
            public void zza(final zzfv zzfv) {
                synchronized (zzfy.this.zzakd) {
                    zzfy.this.zzbqm = 0;
                    if (zzfy.this.zzbql != null && zza != zzfy.this.zzbql) {
                        zzkn.v("New JS engine is loaded, marking previous one as destroyable.");
                        zzfy.this.zzbql.zznb();
                    }
                    zzfy.this.zzbql = zza;
                }
            }
        }, new zzlm.zza() {
            @Override
            public void run() {
                synchronized (zzfy.this.zzakd) {
                    zzfy.this.zzbqm = 1;
                    zzkn.v("Failed loading new engine. Marking new engine destroyable.");
                    zza.zznb();
                }
            }
        });
        return zza;
    }
    
    public zzc zzc(@Nullable final zzau zzau) {
        synchronized (this.zzakd) {
            if (this.zzbql == null || this.zzbql.getStatus() == -1) {
                this.zzbqm = 2;
                this.zzbql = this.zzb(zzau);
                return this.zzbql.zzmz();
            }
            if (this.zzbqm == 0) {
                return this.zzbql.zzmz();
            }
        }
        zzc zzc;
        if (this.zzbqm == 1) {
            this.zzbqm = 2;
            this.zzb(zzau);
            zzc = this.zzbql.zzmz();
        }
        // monitorexit(o)
        else if (this.zzbqm == 2) {
            zzc = this.zzbql.zzmz();
        }
        // monitorexit(o)
        else {
            zzc = this.zzbql.zzmz();
        }
        // monitorexit(o)
        return zzc;
    }
    
    public zzc zzmy() {
        return this.zzc((zzau)null);
    }
    
    static class zza
    {
        static int zzbqx;
        static int zzbqy;
        
        static {
            zza.zzbqx = 60000;
            zza.zzbqy = 10000;
        }
    }
    
    public static class zzb<T> implements zzkw<T>
    {
        @Override
        public void zzd(final T t) {
        }
    }
    
    public static class zzc extends zzln<zzfz>
    {
        private final Object zzakd;
        private final zzd zzbqz;
        private boolean zzbra;
        
        public zzc(final zzd zzbqz) {
            this.zzakd = new Object();
            this.zzbqz = zzbqz;
        }
        
        public void release() {
            synchronized (this.zzakd) {
                if (!this.zzbra) {
                    this.zzbra = true;
                    this.zza(new zzlm.zzc<zzfz>() {
                        public void zzb(final zzfz zzfz) {
                            zzkn.v("Ending javascript session.");
                            ((zzga)zzfz).zznd();
                        }
                    }, new zzlm.zzb());
                    this.zza(new zzlm.zzc<zzfz>() {
                        public void zzb(final zzfz zzfz) {
                            zzkn.v("Releasing engine reference.");
                            zzfy.zzc.this.zzbqz.zzna();
                        }
                    }, new zzlm.zza() {
                        @Override
                        public void run() {
                            zzfy.zzc.this.zzbqz.zzna();
                        }
                    });
                }
            }
        }
    }
    
    public static class zzd extends zzln<zzfv>
    {
        private final Object zzakd;
        private zzkw<zzfv> zzbqk;
        private boolean zzbrc;
        private int zzbrd;
        
        public zzd(final zzkw<zzfv> zzbqk) {
            this.zzakd = new Object();
            this.zzbqk = zzbqk;
            this.zzbrc = false;
            this.zzbrd = 0;
        }
        
        public zzfy.zzc zzmz() {
            while (true) {
                final zzfy.zzc zzc = new zzfy.zzc(this);
                while (true) {
                    synchronized (this.zzakd) {
                        this.zza(new zzlm.zzc<zzfv>() {
                            public void zza(final zzfv zzfv) {
                                zzkn.v("Getting a new session for JS Engine.");
                                ((zzln<zzga>)zzc).zzg(zzfv.zzmw());
                            }
                        }, new zzlm.zza() {
                            @Override
                            public void run() {
                                zzkn.v("Rejecting reference for JS Engine.");
                                zzc.reject();
                            }
                        });
                        if (this.zzbrd >= 0) {
                            final boolean b = true;
                            zzac.zzbr(b);
                            ++this.zzbrd;
                            return zzc;
                        }
                    }
                    final boolean b = false;
                    continue;
                }
            }
        }
        
        protected void zzna() {
            while (true) {
                int n = 1;
                while (true) {
                    synchronized (this.zzakd) {
                        if (this.zzbrd >= n) {
                            zzac.zzbr(n != 0);
                            zzkn.v("Releasing 1 reference for JS Engine");
                            --this.zzbrd;
                            this.zznc();
                            return;
                        }
                    }
                    n = 0;
                    continue;
                }
            }
        }
        
        public void zznb() {
            while (true) {
                boolean b = true;
                while (true) {
                    synchronized (this.zzakd) {
                        if (this.zzbrd >= 0) {
                            zzac.zzbr(b);
                            zzkn.v("Releasing root reference. JS Engine will be destroyed once other references are released.");
                            this.zzbrc = true;
                            this.zznc();
                            return;
                        }
                    }
                    b = false;
                    continue;
                }
            }
        }
        
        protected void zznc() {
            while (true) {
                while (true) {
                    synchronized (this.zzakd) {
                        if (this.zzbrd >= 0) {
                            final boolean b = true;
                            zzac.zzbr(b);
                            if (this.zzbrc && this.zzbrd == 0) {
                                zzkn.v("No reference is left (including root). Cleaning up engine.");
                                this.zza(new zzlm.zzc<zzfv>() {
                                    public void zza(final zzfv zzfv) {
                                        zzu.zzfz().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                zzd.this.zzbqk.zzd(zzfv);
                                                zzfv.destroy();
                                            }
                                        });
                                    }
                                }, new zzlm.zzb());
                            }
                            else {
                                zzkn.v("There are still references to the engine. Not destroying.");
                            }
                            return;
                        }
                    }
                    final boolean b = false;
                    continue;
                }
            }
        }
    }
    
    public static class zze extends zzln<zzfz>
    {
        private zzfy.zzc zzbri;
        
        public zze(final zzfy.zzc zzbri) {
            this.zzbri = zzbri;
        }
        
        public void finalize() {
            this.zzbri.release();
            this.zzbri = null;
        }
        
        @Override
        public int getStatus() {
            return this.zzbri.getStatus();
        }
        
        @Override
        public void reject() {
            this.zzbri.reject();
        }
        
        @Override
        public void zza(final zzlm.zzc<zzfz> zzc, final zzlm.zza zza) {
            this.zzbri.zza(zzc, zza);
        }
        
        public void zzf(final zzfz zzfz) {
            this.zzbri.zzg(zzfz);
        }
    }
}
