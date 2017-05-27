// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.formats.zzi;
import java.util.Iterator;
import android.content.Intent;
import android.content.IntentFilter;
import org.json.JSONException;
import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.ads.internal.zzu;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import android.view.View;
import android.util.DisplayMetrics;
import java.util.UUID;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import java.util.HashSet;
import android.content.BroadcastReceiver;
import android.support.annotation.Nullable;
import android.app.KeyguardManager;
import android.os.PowerManager;
import android.view.WindowManager;
import android.content.Context;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

@zziy
public abstract class zzcj implements ViewTreeObserver$OnGlobalLayoutListener, ViewTreeObserver$OnScrollChangedListener
{
    protected final Object zzakd;
    private boolean zzaoy;
    private zzlc zzasa;
    private final WeakReference<zzke> zzasg;
    private WeakReference<ViewTreeObserver> zzash;
    private final zzcq zzasi;
    protected final zzcl zzasj;
    private final Context zzask;
    private final WindowManager zzasl;
    private final PowerManager zzasm;
    private final KeyguardManager zzasn;
    @Nullable
    private zzcn zzaso;
    private boolean zzasp;
    private boolean zzasq;
    private boolean zzasr;
    private boolean zzass;
    private boolean zzast;
    @Nullable
    BroadcastReceiver zzasu;
    private final HashSet<zzck> zzasv;
    private final zzev zzasw;
    private final zzev zzasx;
    private final zzev zzasy;
    
    public zzcj(final Context zzask, final AdSizeParcel adSizeParcel, final zzke zzke, final VersionInfoParcel versionInfoParcel, final zzcq zzasi) {
        this.zzakd = new Object();
        this.zzaoy = false;
        this.zzasq = false;
        this.zzasv = new HashSet<zzck>();
        this.zzasw = new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                if (zzcj.this.zzb(map)) {
                    zzcj.this.zza(zzlt.getView(), map);
                }
            }
        };
        this.zzasx = new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                if (zzcj.this.zzb(map)) {
                    final String value = String.valueOf(zzcj.this.zzasj.zzia());
                    String concat;
                    if (value.length() != 0) {
                        concat = "Received request to untrack: ".concat(value);
                    }
                    else {
                        concat = new String("Received request to untrack: ");
                    }
                    com.google.android.gms.ads.internal.util.client.zzb.zzdd(concat);
                    zzcj.this.destroy();
                }
            }
        };
        this.zzasy = new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                if (zzcj.this.zzb(map) && map.containsKey("isVisible")) {
                    zzcj.this.zzj(Boolean.valueOf("1".equals(map.get("isVisible")) || "true".equals(map.get("isVisible"))));
                }
            }
        };
        this.zzasg = new WeakReference<zzke>(zzke);
        this.zzasi = zzasi;
        this.zzash = new WeakReference<ViewTreeObserver>(null);
        this.zzasr = true;
        this.zzast = false;
        this.zzasa = new zzlc(200L);
        this.zzasj = new zzcl(UUID.randomUUID().toString(), versionInfoParcel, adSizeParcel.zzaxi, zzke.zzcod, zzke.zzib(), adSizeParcel.zzaxl);
        this.zzasl = (WindowManager)zzask.getSystemService("window");
        this.zzasm = (PowerManager)zzask.getApplicationContext().getSystemService("power");
        this.zzasn = (KeyguardManager)zzask.getSystemService("keyguard");
        this.zzask = zzask;
    }
    
    protected void destroy() {
        synchronized (this.zzakd) {
            this.zzhp();
            this.zzhk();
            this.zzasr = false;
            this.zzhm();
        }
    }
    
    boolean isScreenOn() {
        return this.zzasm.isScreenOn();
    }
    
    public void onGlobalLayout() {
        this.zzk(2);
    }
    
    public void onScrollChanged() {
        this.zzk(1);
    }
    
    public void pause() {
        synchronized (this.zzakd) {
            this.zzaoy = true;
            this.zzk(3);
        }
    }
    
    public void resume() {
        synchronized (this.zzakd) {
            this.zzaoy = false;
            this.zzk(3);
        }
    }
    
    public void stop() {
        synchronized (this.zzakd) {
            this.zzasq = true;
            this.zzk(3);
        }
    }
    
    protected int zza(final int n, final DisplayMetrics displayMetrics) {
        return (int)(n / displayMetrics.density);
    }
    
    protected void zza(final View view, final Map<String, String> map) {
        this.zzk(3);
    }
    
    public void zza(final zzck zzck) {
        this.zzasv.add(zzck);
    }
    
    public void zza(final zzcn zzaso) {
        synchronized (this.zzakd) {
            this.zzaso = zzaso;
        }
    }
    
    protected void zza(final JSONObject jsonObject) {
        try {
            final JSONArray jsonArray = new JSONArray();
            final JSONObject jsonObject2 = new JSONObject();
            jsonArray.put((Object)jsonObject);
            jsonObject2.put("units", (Object)jsonArray);
            this.zzb(jsonObject2);
        }
        catch (Throwable t) {
            com.google.android.gms.ads.internal.util.client.zzb.zzb("Skipping active view message.", t);
        }
    }
    
    protected abstract void zzb(final JSONObject p0);
    
    protected boolean zzb(@Nullable final Map<String, String> map) {
        boolean b = false;
        if (map != null) {
            final String s = map.get("hashCode");
            b = (!TextUtils.isEmpty((CharSequence)s) && s.equals(this.zzasj.zzia()));
        }
        return b;
    }
    
    protected void zzc(final zzfz zzfz) {
        zzfz.zza("/updateActiveView", this.zzasw);
        zzfz.zza("/untrackActiveViewUnit", this.zzasx);
        zzfz.zza("/visibilityChanged", this.zzasy);
    }
    
    protected JSONObject zzd(@Nullable final View view) throws JSONException {
        JSONObject jsonObject;
        if (view == null) {
            jsonObject = this.zzhs();
        }
        else {
            final boolean attachedToWindow = zzu.zzgb().isAttachedToWindow(view);
            final int[] array = new int[2];
            final int[] array2 = new int[2];
            while (true) {
                try {
                    view.getLocationOnScreen(array);
                    view.getLocationInWindow(array2);
                    final DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
                    final Rect rect = new Rect();
                    rect.left = array[0];
                    rect.top = array[1];
                    rect.right = rect.left + view.getWidth();
                    rect.bottom = rect.top + view.getHeight();
                    final Rect rect2 = new Rect();
                    rect2.right = this.zzasl.getDefaultDisplay().getWidth();
                    rect2.bottom = this.zzasl.getDefaultDisplay().getHeight();
                    final Rect rect3 = new Rect();
                    final boolean globalVisibleRect = view.getGlobalVisibleRect(rect3, (Point)null);
                    final Rect rect4 = new Rect();
                    final boolean localVisibleRect = view.getLocalVisibleRect(rect4);
                    final Rect rect5 = new Rect();
                    view.getHitRect(rect5);
                    jsonObject = this.zzhq();
                    jsonObject.put("windowVisibility", view.getWindowVisibility()).put("isAttachedToWindow", attachedToWindow).put("viewBox", (Object)new JSONObject().put("top", this.zza(rect2.top, displayMetrics)).put("bottom", this.zza(rect2.bottom, displayMetrics)).put("left", this.zza(rect2.left, displayMetrics)).put("right", this.zza(rect2.right, displayMetrics))).put("adBox", (Object)new JSONObject().put("top", this.zza(rect.top, displayMetrics)).put("bottom", this.zza(rect.bottom, displayMetrics)).put("left", this.zza(rect.left, displayMetrics)).put("right", this.zza(rect.right, displayMetrics))).put("globalVisibleBox", (Object)new JSONObject().put("top", this.zza(rect3.top, displayMetrics)).put("bottom", this.zza(rect3.bottom, displayMetrics)).put("left", this.zza(rect3.left, displayMetrics)).put("right", this.zza(rect3.right, displayMetrics))).put("globalVisibleBoxVisible", globalVisibleRect).put("localVisibleBox", (Object)new JSONObject().put("top", this.zza(rect4.top, displayMetrics)).put("bottom", this.zza(rect4.bottom, displayMetrics)).put("left", this.zza(rect4.left, displayMetrics)).put("right", this.zza(rect4.right, displayMetrics))).put("localVisibleBoxVisible", localVisibleRect).put("hitBox", (Object)new JSONObject().put("top", this.zza(rect5.top, displayMetrics)).put("bottom", this.zza(rect5.bottom, displayMetrics)).put("left", this.zza(rect5.left, displayMetrics)).put("right", this.zza(rect5.right, displayMetrics))).put("screenDensity", (double)displayMetrics.density).put("isVisible", zzu.zzfz().zza(view, this.zzasm, this.zzasn));
                }
                catch (Exception ex) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzb("Failure getting view location.", ex);
                    continue;
                }
                break;
            }
        }
        return jsonObject;
    }
    
    protected void zzd(final zzfz zzfz) {
        zzfz.zzb("/visibilityChanged", this.zzasy);
        zzfz.zzb("/untrackActiveViewUnit", this.zzasx);
        zzfz.zzb("/updateActiveView", this.zzasw);
    }
    
    protected void zzhj() {
        synchronized (this.zzakd) {
            if (this.zzasu == null) {
                final IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                this.zzasu = new BroadcastReceiver() {
                    public void onReceive(final Context context, final Intent intent) {
                        zzcj.this.zzk(3);
                    }
                };
                this.zzask.registerReceiver(this.zzasu, intentFilter);
            }
        }
    }
    
    protected void zzhk() {
        // monitorenter(this.zzakd)
        try {
            if (this.zzasu == null) {
                return;
            }
            try {
                this.zzask.unregisterReceiver(this.zzasu);
                this.zzasu = null;
            }
            catch (IllegalStateException ex) {
                com.google.android.gms.ads.internal.util.client.zzb.zzb("Failed trying to unregister the receiver", ex);
            }
            catch (Exception ex2) {
                zzu.zzgd().zza(ex2, "AbstractActiveViewUnit.stopScreenStatusMonitoring");
            }
        }
        finally {}
    }
    
    public void zzhl() {
        while (true) {
            // monitorenter(this.zzakd)
            while (true) {
                try {
                    if (!this.zzasr) {
                        return;
                    }
                    this.zzass = true;
                    try {
                        this.zza(this.zzht());
                        final String value = String.valueOf(this.zzasj.zzia());
                        if (value.length() != 0) {
                            final String concat = "Untracking ad unit: ".concat(value);
                            com.google.android.gms.ads.internal.util.client.zzb.zzdd(concat);
                            return;
                        }
                    }
                    catch (JSONException ex) {
                        com.google.android.gms.ads.internal.util.client.zzb.zzb("JSON failure while processing active view data.", (Throwable)ex);
                    }
                    catch (RuntimeException ex2) {
                        com.google.android.gms.ads.internal.util.client.zzb.zzb("Failure while processing active view data.", ex2);
                    }
                }
                finally {}
                final String concat = new String("Untracking ad unit: ");
                continue;
            }
        }
    }
    
    protected void zzhm() {
        if (this.zzaso != null) {
            this.zzaso.zza(this);
        }
    }
    
    public boolean zzhn() {
        synchronized (this.zzakd) {
            return this.zzasr;
        }
    }
    
    protected void zzho() {
        final View zzhu = this.zzasi.zzhw().zzhu();
        if (zzhu != null) {
            final ViewTreeObserver viewTreeObserver = this.zzash.get();
            final ViewTreeObserver viewTreeObserver2 = zzhu.getViewTreeObserver();
            if (viewTreeObserver2 != viewTreeObserver) {
                this.zzhp();
                if (!this.zzasp || (viewTreeObserver != null && viewTreeObserver.isAlive())) {
                    this.zzasp = true;
                    viewTreeObserver2.addOnScrollChangedListener((ViewTreeObserver$OnScrollChangedListener)this);
                    viewTreeObserver2.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
                }
                this.zzash = new WeakReference<ViewTreeObserver>(viewTreeObserver2);
            }
        }
    }
    
    protected void zzhp() {
        final ViewTreeObserver viewTreeObserver = this.zzash.get();
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnScrollChangedListener((ViewTreeObserver$OnScrollChangedListener)this);
            viewTreeObserver.removeGlobalOnLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
        }
    }
    
    protected JSONObject zzhq() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("afmaVersion", (Object)this.zzasj.zzhy()).put("activeViewJSON", (Object)this.zzasj.zzhz()).put("timestamp", zzu.zzgf().elapsedRealtime()).put("adFormat", (Object)this.zzasj.zzhx()).put("hashCode", (Object)this.zzasj.zzia()).put("isMraid", this.zzasj.zzib()).put("isStopped", this.zzasq).put("isPaused", this.zzaoy).put("isScreenOn", this.isScreenOn()).put("isNative", this.zzasj.zzic()).put("appMuted", zzu.zzfz().zzfg()).put("appVolume", (double)zzu.zzfz().zzfe()).put("deviceVolume", (double)zzu.zzfz().zzal(this.zzask));
        return jsonObject;
    }
    
    protected abstract boolean zzhr();
    
    protected JSONObject zzhs() throws JSONException {
        return this.zzhq().put("isAttachedToWindow", false).put("isScreenOn", this.isScreenOn()).put("isVisible", false);
    }
    
    protected JSONObject zzht() throws JSONException {
        final JSONObject zzhq = this.zzhq();
        zzhq.put("doneReasonCode", (Object)"u");
        return zzhq;
    }
    
    protected void zzj(final boolean b) {
        final Iterator<zzck> iterator = this.zzasv.iterator();
        while (iterator.hasNext()) {
            iterator.next().zza(this, b);
        }
    }
    
    protected void zzk(final int n) {
        View zzhu;
        boolean zzast;
        while (true) {
            while (true) {
                synchronized (this.zzakd) {
                    if (!this.zzhr() || !this.zzasr) {
                        goto Label_0219;
                    }
                    zzhu = this.zzasi.zzhu();
                    if (zzhu != null && zzu.zzfz().zza(zzhu, this.zzasm, this.zzasn) && zzhu.getGlobalVisibleRect(new Rect(), (Point)null)) {
                        zzast = true;
                        if (this.zzasi.zzhv()) {
                            this.zzhl();
                            goto Label_0219;
                        }
                        break;
                    }
                }
                zzast = false;
                continue;
            }
        }
        boolean b = false;
        if (n == 1) {
            b = true;
        }
        if (b && !this.zzasa.tryAcquire() && zzast == this.zzast) {
            // monitorexit(o)
            goto Label_0219;
        }
        if (!zzast && !this.zzast && n == 1) {
            // monitorexit(o)
            goto Label_0219;
        }
        while (true) {
            try {
                this.zza(this.zzd(zzhu));
                this.zzast = zzast;
                while (true) {
                    this.zzho();
                    this.zzhm();
                    goto Label_0219;
                    final JSONException ex;
                    com.google.android.gms.ads.internal.util.client.zzb.zza("Active view update failed.", (Throwable)ex);
                    continue;
                }
            }
            // monitorexit(o)
            catch (JSONException ex) {
                continue;
            }
            catch (RuntimeException ex) {
                continue;
            }
            break;
        }
    }
    
    public static class zza implements zzcq
    {
        private WeakReference<zzi> zzata;
        
        public zza(final zzi zzi) {
            this.zzata = new WeakReference<zzi>(zzi);
        }
        
        @Nullable
        @Override
        public View zzhu() {
            final zzi zzi = this.zzata.get();
            View zzly;
            if (zzi != null) {
                zzly = zzi.zzly();
            }
            else {
                zzly = null;
            }
            return zzly;
        }
        
        @Override
        public boolean zzhv() {
            return this.zzata.get() == null;
        }
        
        @Override
        public zzcq zzhw() {
            return new zzb(this.zzata.get());
        }
    }
    
    public static class zzb implements zzcq
    {
        private zzi zzatb;
        
        public zzb(final zzi zzatb) {
            this.zzatb = zzatb;
        }
        
        @Override
        public View zzhu() {
            View zzly;
            if (this.zzatb != null) {
                zzly = this.zzatb.zzly();
            }
            else {
                zzly = null;
            }
            return zzly;
        }
        
        @Override
        public boolean zzhv() {
            return this.zzatb == null;
        }
        
        @Override
        public zzcq zzhw() {
            return this;
        }
    }
    
    public static class zzc implements zzcq
    {
        @Nullable
        private final View mView;
        @Nullable
        private final zzke zzatc;
        
        public zzc(final View mView, final zzke zzatc) {
            this.mView = mView;
            this.zzatc = zzatc;
        }
        
        @Override
        public View zzhu() {
            return this.mView;
        }
        
        @Override
        public boolean zzhv() {
            return this.zzatc == null || this.mView == null;
        }
        
        @Override
        public zzcq zzhw() {
            return this;
        }
    }
    
    public static class zzd implements zzcq
    {
        private final WeakReference<View> zzatd;
        private final WeakReference<zzke> zzate;
        
        public zzd(final View view, final zzke zzke) {
            this.zzatd = new WeakReference<View>(view);
            this.zzate = new WeakReference<zzke>(zzke);
        }
        
        @Override
        public View zzhu() {
            return this.zzatd.get();
        }
        
        @Override
        public boolean zzhv() {
            return this.zzatd.get() == null || this.zzate.get() == null;
        }
        
        @Override
        public zzcq zzhw() {
            return new zzc(this.zzatd.get(), this.zzate.get());
        }
    }
}
