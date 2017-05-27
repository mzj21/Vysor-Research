// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzs;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import org.json.JSONObject;
import java.util.Map;
import android.view.View;
import com.google.android.gms.ads.internal.client.zzm;
import java.lang.ref.WeakReference;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.google.android.gms.ads.internal.zzq;
import android.content.Context;

@zziy
public class zzit
{
    private final Context mContext;
    private final Object zzakd;
    private final zzdq zzalg;
    private int zzary;
    private int zzarz;
    private zzlc zzasa;
    private final zzq zzbkj;
    private final zzau zzbkp;
    private final zzke.zza zzcck;
    private ViewTreeObserver$OnGlobalLayoutListener zzcew;
    private ViewTreeObserver$OnScrollChangedListener zzcex;
    
    public zzit(final Context mContext, final zzau zzbkp, final zzke.zza zzcck, final zzdq zzalg, final zzq zzbkj) {
        this.zzakd = new Object();
        this.zzary = -1;
        this.zzarz = -1;
        this.mContext = mContext;
        this.zzbkp = zzbkp;
        this.zzcck = zzcck;
        this.zzalg = zzalg;
        this.zzbkj = zzbkj;
        this.zzasa = new zzlc(200L);
    }
    
    private ViewTreeObserver$OnGlobalLayoutListener zza(final WeakReference<zzlt> weakReference) {
        if (this.zzcew == null) {
            this.zzcew = (ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    zzit.this.zza(weakReference, false);
                }
            };
        }
        return this.zzcew;
    }
    
    private void zza(final WeakReference<zzlt> weakReference, final boolean b) {
        if (weakReference != null) {
            final zzlt zzlt = weakReference.get();
            if (zzlt != null && zzlt.getView() != null && (!b || this.zzasa.tryAcquire())) {
                while (true) {
                    final View view = zzlt.getView();
                    final int[] array = new int[2];
                    view.getLocationOnScreen(array);
                    final int zzc = zzm.zzjr().zzc(this.mContext, array[0]);
                    final int zzc2 = zzm.zzjr().zzc(this.mContext, array[1]);
                    while (true) {
                        Label_0183: {
                            synchronized (this.zzakd) {
                                if (this.zzary != zzc || this.zzarz != zzc2) {
                                    this.zzary = zzc;
                                    this.zzarz = zzc2;
                                    final zzlu zzvr = zzlt.zzvr();
                                    final int zzary = this.zzary;
                                    final int zzarz = this.zzarz;
                                    if (b) {
                                        break Label_0183;
                                    }
                                    final boolean b2 = true;
                                    zzvr.zza(zzary, zzarz, b2);
                                }
                                break;
                            }
                        }
                        final boolean b2 = false;
                        continue;
                    }
                }
            }
        }
    }
    
    private ViewTreeObserver$OnScrollChangedListener zzb(final WeakReference<zzlt> weakReference) {
        if (this.zzcex == null) {
            this.zzcex = (ViewTreeObserver$OnScrollChangedListener)new ViewTreeObserver$OnScrollChangedListener() {
                public void onScrollChanged() {
                    zzit.this.zza(weakReference, true);
                }
            };
        }
        return this.zzcex;
    }
    
    private void zzj(final zzlt zzlt) {
        final zzlu zzvr = zzlt.zzvr();
        zzvr.zza("/video", zzeu.zzbmo);
        zzvr.zza("/videoMeta", zzeu.zzbmp);
        zzvr.zza("/precache", zzeu.zzbmq);
        zzvr.zza("/delayPageLoaded", zzeu.zzbmt);
        zzvr.zza("/instrument", zzeu.zzbmr);
        zzvr.zza("/log", zzeu.zzbmj);
        zzvr.zza("/videoClicked", zzeu.zzbmk);
        zzvr.zza("/trackActiveViewUnit", new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                zzit.this.zzbkj.zzfh();
            }
        });
    }
    
    public zzlj<zzlt> zzh(final JSONObject jsonObject) {
        final zzlg<zzlt> zzlg = new zzlg<zzlt>();
        zzu.zzfz().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    final zzlt zzrt = zzit.this.zzrt();
                    zzit.this.zzbkj.zzc(zzrt);
                    final WeakReference weakReference = new WeakReference(zzrt);
                    zzrt.zzvr().zza(zzit.this.zza(weakReference), zzit.this.zzb(weakReference));
                    zzit.this.zzj(zzrt);
                    zzrt.zzvr().zza((zzlu.zzb)new zzlu.zzb() {
                        @Override
                        public void zzk(final zzlt zzlt) {
                            zzrt.zza("google.afma.nativeAds.renderVideo", jsonObject);
                        }
                    });
                    zzrt.zzvr().zza((zzlu.zza)new zzlu.zza() {
                        @Override
                        public void zza(final zzlt zzlt, final boolean b) {
                            zzit.this.zzbkj.zzfk();
                            zzlg.zzh(zzlt);
                        }
                    });
                    zzrt.loadUrl(zzir.zza(zzit.this.zzcck, zzdi.zzbfw.get()));
                }
                catch (Exception ex) {
                    zzb.zzd("Exception occurred while getting video view", ex);
                    zzlg.zzh(null);
                }
            }
        });
        return zzlg;
    }
    
    zzlt zzrt() {
        return zzu.zzga().zza(this.mContext, AdSizeParcel.zzk(this.mContext), false, false, this.zzbkp, this.zzcck.zzcix.zzaqv, this.zzalg, null, this.zzbkj.zzdp());
    }
}
