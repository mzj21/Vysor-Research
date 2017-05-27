// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzkn;
import java.util.Iterator;
import com.google.android.gms.internal.zzlt;
import java.util.ArrayList;
import android.view.MotionEvent;
import android.app.Activity;
import com.google.android.gms.internal.zzlf;
import com.google.android.gms.internal.zzku;
import android.widget.ViewSwitcher;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.graphics.Rect;
import com.google.android.gms.internal.zzlu;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.internal.zzap;
import java.util.UUID;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzlc;
import java.util.HashSet;
import android.view.View;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.ads.internal.purchase.zzk;
import java.util.List;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.internal.zzdu;
import com.google.android.gms.ads.internal.client.VideoOptionsParcel;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.internal.zzek;
import com.google.android.gms.internal.zzej;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.internal.zzei;
import com.google.android.gms.internal.zzeh;
import com.google.android.gms.internal.zzib;
import com.google.android.gms.internal.zzhx;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.internal.zzkf;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.internal.zzkt;
import com.google.android.gms.internal.zzkm;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.zzau;
import android.content.Context;
import com.google.android.gms.internal.zziy;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

@zziy
public final class zzv implements ViewTreeObserver$OnGlobalLayoutListener, ViewTreeObserver$OnScrollChangedListener
{
    public final Context zzahn;
    boolean zzanx;
    final String zzaqs;
    public String zzaqt;
    final zzau zzaqu;
    public final VersionInfoParcel zzaqv;
    @Nullable
    zza zzaqw;
    @Nullable
    public zzkm zzaqx;
    @Nullable
    public zzkt zzaqy;
    public AdSizeParcel zzaqz;
    @Nullable
    public zzke zzara;
    public zzke.zza zzarb;
    @Nullable
    public zzkf zzarc;
    @Nullable
    zzp zzard;
    @Nullable
    zzq zzare;
    @Nullable
    zzw zzarf;
    @Nullable
    zzy zzarg;
    @Nullable
    zzhx zzarh;
    @Nullable
    zzib zzari;
    @Nullable
    zzeh zzarj;
    @Nullable
    zzei zzark;
    SimpleArrayMap<String, zzej> zzarl;
    SimpleArrayMap<String, zzek> zzarm;
    NativeAdOptionsParcel zzarn;
    @Nullable
    VideoOptionsParcel zzaro;
    @Nullable
    zzdu zzarp;
    @Nullable
    zzd zzarq;
    @Nullable
    List<String> zzarr;
    @Nullable
    zzk zzars;
    @Nullable
    public zzkk zzart;
    @Nullable
    View zzaru;
    public int zzarv;
    boolean zzarw;
    private HashSet<zzkf> zzarx;
    private int zzary;
    private int zzarz;
    private zzlc zzasa;
    private boolean zzasb;
    private boolean zzasc;
    private boolean zzasd;
    
    public zzv(final Context context, final AdSizeParcel adSizeParcel, final String s, final VersionInfoParcel versionInfoParcel) {
        this(context, adSizeParcel, s, versionInfoParcel, null);
    }
    
    zzv(final Context zzahn, final AdSizeParcel zzaqz, final String zzaqt, final VersionInfoParcel zzaqv, zzau zzaqu) {
        this.zzart = null;
        this.zzaru = null;
        this.zzarv = 0;
        this.zzarw = false;
        this.zzanx = false;
        this.zzarx = null;
        this.zzary = -1;
        this.zzarz = -1;
        this.zzasb = true;
        this.zzasc = true;
        this.zzasd = false;
        zzdi.initialize(zzahn);
        if (zzu.zzgd().zztm() != null) {
            final List<String> zzks = zzdi.zzks();
            if (zzaqv.zzcts != 0) {
                zzks.add(Integer.toString(zzaqv.zzcts));
            }
            zzu.zzgd().zztm().zzc(zzks);
        }
        this.zzaqs = UUID.randomUUID().toString();
        if (zzaqz.zzaxj || zzaqz.zzaxl) {
            this.zzaqw = null;
        }
        else {
            (this.zzaqw = new zza(zzahn, zzaqt, (ViewTreeObserver$OnGlobalLayoutListener)this, (ViewTreeObserver$OnScrollChangedListener)this)).setMinimumWidth(zzaqz.widthPixels);
            this.zzaqw.setMinimumHeight(zzaqz.heightPixels);
            this.zzaqw.setVisibility(4);
        }
        this.zzaqz = zzaqz;
        this.zzaqt = zzaqt;
        this.zzahn = zzahn;
        this.zzaqv = zzaqv;
        if (zzaqu == null) {
            zzaqu = new zzau(new zzi(this));
        }
        this.zzaqu = zzaqu;
        this.zzasa = new zzlc(200L);
        this.zzarm = new SimpleArrayMap<String, zzek>();
    }
    
    private void zzh(final boolean b) {
        int n = 1;
        if (this.zzaqw != null && this.zzara != null && this.zzara.zzbyh != null && this.zzara.zzbyh.zzvr() != null && (!b || this.zzasa.tryAcquire())) {
            if (this.zzara.zzbyh.zzvr().zzib()) {
                final int[] array = new int[2];
                this.zzaqw.getLocationOnScreen(array);
                final int zzc = zzm.zzjr().zzc(this.zzahn, array[0]);
                final int zzc2 = zzm.zzjr().zzc(this.zzahn, array[n]);
                if (zzc != this.zzary || zzc2 != this.zzarz) {
                    this.zzary = zzc;
                    this.zzarz = zzc2;
                    final zzlu zzvr = this.zzara.zzbyh.zzvr();
                    final int zzary = this.zzary;
                    final int zzarz = this.zzarz;
                    if (b) {
                        n = 0;
                    }
                    zzvr.zza(zzary, zzarz, n != 0);
                }
            }
            this.zzhf();
        }
    }
    
    private void zzhf() {
        if (this.zzaqw != null) {
            final View viewById = this.zzaqw.getRootView().findViewById(16908290);
            if (viewById != null) {
                final Rect rect = new Rect();
                final Rect rect2 = new Rect();
                this.zzaqw.getGlobalVisibleRect(rect);
                viewById.getGlobalVisibleRect(rect2);
                if (rect.top != rect2.top) {
                    this.zzasb = false;
                }
                if (rect.bottom != rect2.bottom) {
                    this.zzasc = false;
                }
            }
        }
    }
    
    public void destroy() {
        this.zzhe();
        this.zzare = null;
        this.zzarf = null;
        this.zzari = null;
        this.zzarh = null;
        this.zzarp = null;
        this.zzarg = null;
        this.zzi(false);
        if (this.zzaqw != null) {
            this.zzaqw.removeAllViews();
        }
        this.zzgz();
        this.zzhb();
        this.zzara = null;
    }
    
    public void onGlobalLayout() {
        this.zzh(false);
    }
    
    public void onScrollChanged() {
        this.zzh(true);
        this.zzasd = true;
    }
    
    public void zza(final HashSet<zzkf> zzarx) {
        this.zzarx = zzarx;
    }
    
    public HashSet<zzkf> zzgy() {
        return this.zzarx;
    }
    
    public void zzgz() {
        if (this.zzara != null && this.zzara.zzbyh != null) {
            this.zzara.zzbyh.destroy();
        }
    }
    
    public void zzha() {
        if (this.zzara != null && this.zzara.zzbyh != null) {
            this.zzara.zzbyh.stopLoading();
        }
    }
    
    public void zzhb() {
        if (this.zzara == null || this.zzara.zzbtf == null) {
            return;
        }
        try {
            this.zzara.zzbtf.destroy();
        }
        catch (RemoteException ex) {
            zzb.zzdf("Could not destroy mediation adapter.");
        }
    }
    
    public boolean zzhc() {
        return this.zzarv == 0;
    }
    
    public boolean zzhd() {
        boolean b = true;
        if (this.zzarv != (b ? 1 : 0)) {
            b = false;
        }
        return b;
    }
    
    public void zzhe() {
        if (this.zzaqw != null) {
            this.zzaqw.zzhe();
        }
    }
    
    public String zzhg() {
        String s;
        if (this.zzasb && this.zzasc) {
            s = "";
        }
        else if (this.zzasb) {
            if (this.zzasd) {
                s = "top-scrollable";
            }
            else {
                s = "top-locked";
            }
        }
        else if (this.zzasc) {
            if (this.zzasd) {
                s = "bottom-scrollable";
            }
            else {
                s = "bottom-locked";
            }
        }
        else {
            s = "";
        }
        return s;
    }
    
    public void zzhh() {
        if (this.zzarc != null) {
            if (this.zzara != null) {
                this.zzarc.zzm(this.zzara.zzcoj);
                this.zzarc.zzn(this.zzara.zzcok);
                this.zzarc.zzae(this.zzara.zzchc);
            }
            this.zzarc.zzad(this.zzaqz.zzaxj);
        }
    }
    
    public void zzi(final boolean b) {
        if (this.zzarv == 0) {
            this.zzha();
        }
        if (this.zzaqx != null) {
            this.zzaqx.cancel();
        }
        if (this.zzaqy != null) {
            this.zzaqy.cancel();
        }
        if (b) {
            this.zzara = null;
        }
    }
    
    public static class zza extends ViewSwitcher
    {
        private final zzku zzase;
        @Nullable
        private final zzlf zzasf;
        
        public zza(final Context context, final String adUnitId, final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener, final ViewTreeObserver$OnScrollChangedListener viewTreeObserver$OnScrollChangedListener) {
            super(context);
            (this.zzase = new zzku(context)).setAdUnitId(adUnitId);
            if (context instanceof Activity) {
                this.zzasf = new zzlf((Activity)context, (View)this, viewTreeObserver$OnGlobalLayoutListener, viewTreeObserver$OnScrollChangedListener);
            }
            else {
                this.zzasf = new zzlf(null, (View)this, viewTreeObserver$OnGlobalLayoutListener, viewTreeObserver$OnScrollChangedListener);
            }
            this.zzasf.zzva();
        }
        
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            if (this.zzasf != null) {
                this.zzasf.onAttachedToWindow();
            }
        }
        
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            if (this.zzasf != null) {
                this.zzasf.onDetachedFromWindow();
            }
        }
        
        public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
            this.zzase.zzg(motionEvent);
            return false;
        }
        
        public void removeAllViews() {
            final ArrayList<zzlt> list = new ArrayList<zzlt>();
            for (int i = 0; i < this.getChildCount(); ++i) {
                final View child = this.getChildAt(i);
                if (child != null && child instanceof zzlt) {
                    list.add((zzlt)child);
                }
            }
            super.removeAllViews();
            final Iterator<Object> iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator.next().destroy();
            }
        }
        
        public void zzhe() {
            zzkn.v("Disable position monitoring on adFrame.");
            if (this.zzasf != null) {
                this.zzasf.zzvb();
            }
        }
        
        public zzku zzhi() {
            return this.zzase;
        }
    }
}
