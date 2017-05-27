// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import android.view.View$OnClickListener;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.os.RemoteException;
import com.google.android.gms.internal.zzdt;
import com.google.android.gms.internal.zzdr;
import com.google.android.gms.internal.zzil;
import com.google.android.gms.ads.internal.formats.zzi;
import com.google.android.gms.internal.zzgj;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzgg;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzdq;
import java.util.Map;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzdu;
import android.view.View;
import com.google.android.gms.internal.zzfz;
import com.google.android.gms.internal.zzez;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.internal.zzer;
import com.google.android.gms.ads.internal.overlay.zzg;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzlt;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.content.Context;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzhn;

@zziy
public abstract class zzc extends zzb implements zzh, zzhn
{
    public zzc(final Context context, final AdSizeParcel adSizeParcel, final String s, final zzgq zzgq, final VersionInfoParcel versionInfoParcel, final zzd zzd) {
        super(context, adSizeParcel, s, zzgq, versionInfoParcel, zzd);
    }
    
    protected zzlt zza(final zzke.zza zza, @Nullable final zze zze, @Nullable final com.google.android.gms.ads.internal.safebrowsing.zzc zzc) {
        final View nextView = this.zzall.zzaqw.getNextView();
        final boolean b = nextView instanceof zzlt;
        zzlt zza2 = null;
        if (b) {
            zza2 = (zzlt)nextView;
            if (zzdi.zzbcv.get()) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdd("Reusing webview...");
                zza2.zza(this.zzall.zzahn, this.zzall.zzaqz, this.zzalg);
            }
            else {
                zza2.destroy();
                zza2 = null;
            }
        }
        if (zza2 == null) {
            if (nextView != null) {
                this.zzall.zzaqw.removeView(nextView);
            }
            zza2 = com.google.android.gms.ads.internal.zzu.zzga().zza(this.zzall.zzahn, this.zzall.zzaqz, false, false, this.zzall.zzaqu, this.zzall.zzaqv, this.zzalg, this, this.zzalo);
            if (this.zzall.zzaqz.zzaxk == null) {
                this.zzb(zza2.getView());
            }
        }
        final zzlt zzlt = zza2;
        zzlt.zzvr().zza(this, this, this, this, false, this, null, zze, this, zzc);
        this.zza(zzlt);
        zzlt.zzdh(zza.zzcix.zzcgj);
        return zzlt;
    }
    
    @Override
    public void zza(final int n, final int n2, final int n3, final int n4) {
        this.zzdz();
    }
    
    @Override
    public void zza(final zzdu zzarp) {
        zzac.zzhq("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
        this.zzall.zzarp = zzarp;
    }
    
    protected void zza(final zzfz zzfz) {
        zzfz.zza("/trackActiveViewUnit", new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                if (zzc.this.zzall.zzara != null) {
                    zzc.this.zzaln.zza(zzc.this.zzall.zzaqz, zzc.this.zzall.zzara, zzlt.getView(), zzlt);
                }
                else {
                    com.google.android.gms.ads.internal.util.client.zzb.zzdf("Request to enable ActiveView before adState is available.");
                }
            }
        });
    }
    
    @Override
    protected void zza(final zzke.zza zza, final zzdq zzdq) {
        if (zza.errorCode != -2) {
            zzkr.zzcrf.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    zzc.this.zzb(new zzke(zza, null, null, null, null, null, null, null));
                }
            });
        }
        else {
            if (zza.zzaqz != null) {
                this.zzall.zzaqz = zza.zzaqz;
            }
            if (zza.zzcop.zzchc && !zza.zzcop.zzaxn) {
                this.zzall.zzarv = 0;
                this.zzall.zzaqy = com.google.android.gms.ads.internal.zzu.zzfy().zza(this.zzall.zzahn, this, zza, this.zzall.zzaqu, null, this.zzals, (zzil.zza)this, zzdq);
            }
            else {
                zzkr.zzcrf.post((Runnable)new Runnable() {
                    final /* synthetic */ com.google.android.gms.ads.internal.safebrowsing.zzc zzaly = zzc.this.zzalo.zzamf.zza(zzc.this.zzall.zzahn, zza.zzcop);
                    
                    @Override
                    public void run() {
                        Label_0141: {
                            if (!zza.zzcop.zzchl || zzc.this.zzall.zzarp == null) {
                                break Label_0141;
                            }
                            final String zzbyj = zza.zzcop.zzbyj;
                            String zzcv = null;
                            if (zzbyj != null) {
                                zzcv = com.google.android.gms.ads.internal.zzu.zzfz().zzcv(zza.zzcop.zzbyj);
                            }
                            final zzdr zzdr = new zzdr(zzc.this, zzcv, zza.zzcop.body);
                            zzc.this.zzall.zzarv = 1;
                            try {
                                zzc.this.zzalj = false;
                                zzc.this.zzall.zzarp.zza(zzdr);
                                return;
                            }
                            catch (RemoteException ex) {
                                com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call the onCustomRenderedAdLoadedListener.", (Throwable)ex);
                                zzc.this.zzalj = true;
                            }
                        }
                        final zze zze = new zze(zzc.this.zzall.zzahn, zza);
                        final zzlt zza = zzc.this.zza(zza, zze, this.zzaly);
                        zza.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
                            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                                zze.recordClick();
                                return false;
                            }
                        });
                        zza.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                            public void onClick(final View view) {
                                zze.recordClick();
                            }
                        });
                        zzc.this.zzall.zzarv = 0;
                        zzc.this.zzall.zzaqy = com.google.android.gms.ads.internal.zzu.zzfy().zza(zzc.this.zzall.zzahn, zzc.this, zza, zzc.this.zzall.zzaqu, zza, zzc.this.zzals, (zzil.zza)zzc.this, zzdq);
                    }
                });
            }
        }
    }
    
    @Override
    protected boolean zza(@Nullable final zzke zzke, final zzke zzke2) {
        if (this.zzall.zzhc() && this.zzall.zzaqw != null) {
            this.zzall.zzaqw.zzhi().zzcz(zzke2.zzchh);
        }
        return super.zza(zzke, zzke2);
    }
    
    @Override
    public void zzc(final View zzaru) {
        this.zzall.zzaru = zzaru;
        this.zzb(new zzke(this.zzall.zzarb, null, null, null, null, null, null, null));
    }
    
    @Override
    public void zzen() {
        this.onAdClicked();
    }
    
    @Override
    public void zzeo() {
        this.recordImpression();
        this.zzdv();
    }
    
    @Override
    public void zzep() {
        this.zzdx();
    }
}
