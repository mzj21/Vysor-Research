// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import android.view.ViewParent;
import android.view.Window;
import android.widget.RelativeLayout$LayoutParams;
import com.google.android.gms.ads.AdSize;
import android.view.ViewGroup$LayoutParams;
import android.view.View$OnClickListener;
import android.view.View;
import android.content.Context;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.zzu;
import android.text.TextUtils;
import java.util.Map;
import com.google.android.gms.common.util.zzf;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.PopupWindow;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.app.Activity;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import java.util.Set;

@zziy
public class zzhh extends zzhm
{
    static final Set<String> zzbuv;
    private int zzajw;
    private int zzajx;
    private final Object zzakd;
    private AdSizeParcel zzapc;
    private final zzlt zzbkr;
    private final Activity zzbul;
    private String zzbuw;
    private boolean zzbux;
    private int zzbuy;
    private int zzbuz;
    private int zzbva;
    private int zzbvb;
    private ImageView zzbvc;
    private LinearLayout zzbvd;
    private zzhn zzbve;
    private PopupWindow zzbvf;
    private RelativeLayout zzbvg;
    private ViewGroup zzbvh;
    
    static {
        zzbuv = zzf.zzc("top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center");
    }
    
    public zzhh(final zzlt zzbkr, final zzhn zzbve) {
        super(zzbkr, "resize");
        this.zzbuw = "top-right";
        this.zzbux = true;
        this.zzbuy = 0;
        this.zzbuz = 0;
        this.zzajx = -1;
        this.zzbva = 0;
        this.zzbvb = 0;
        this.zzajw = -1;
        this.zzakd = new Object();
        this.zzbkr = zzbkr;
        this.zzbul = zzbkr.zzvn();
        this.zzbve = zzbve;
    }
    
    private void zzi(final Map<String, String> map) {
        if (!TextUtils.isEmpty((CharSequence)map.get("mWidth"))) {
            this.zzajw = zzu.zzfz().zzcw(map.get("mWidth"));
        }
        if (!TextUtils.isEmpty((CharSequence)map.get("mHeight"))) {
            this.zzajx = zzu.zzfz().zzcw(map.get("mHeight"));
        }
        if (!TextUtils.isEmpty((CharSequence)map.get("offsetX"))) {
            this.zzbva = zzu.zzfz().zzcw(map.get("offsetX"));
        }
        if (!TextUtils.isEmpty((CharSequence)map.get("offsetY"))) {
            this.zzbvb = zzu.zzfz().zzcw(map.get("offsetY"));
        }
        if (!TextUtils.isEmpty((CharSequence)map.get("allowOffscreen"))) {
            this.zzbux = Boolean.parseBoolean(map.get("allowOffscreen"));
        }
        final String zzbuw = map.get("customClosePosition");
        if (!TextUtils.isEmpty((CharSequence)zzbuw)) {
            this.zzbuw = zzbuw;
        }
    }
    
    private int[] zznt() {
        int[] array;
        if (!this.zznv()) {
            array = null;
        }
        else if (this.zzbux) {
            array = new int[] { this.zzbuy + this.zzbva, this.zzbuz + this.zzbvb };
        }
        else {
            final int[] zzi = zzu.zzfz().zzi(this.zzbul);
            final int[] zzk = zzu.zzfz().zzk(this.zzbul);
            final int n = zzi[0];
            int n2 = this.zzbuy + this.zzbva;
            int n3 = this.zzbuz + this.zzbvb;
            if (n2 < 0) {
                n2 = 0;
            }
            else if (n2 + this.zzajw > n) {
                n2 = n - this.zzajw;
            }
            if (n3 < zzk[0]) {
                n3 = zzk[0];
            }
            else if (n3 + this.zzajx > zzk[1]) {
                n3 = zzk[1] - this.zzajx;
            }
            array = new int[] { n2, n3 };
        }
        return array;
    }
    
    public void execute(final Map<String, String> map) {
        synchronized (this.zzakd) {
            if (this.zzbul == null) {
                this.zzbx("Not an activity context. Cannot resize.");
                return;
            }
            if (this.zzbkr.zzdt() == null) {
                this.zzbx("Webview is not yet available, size is not set.");
                return;
            }
        }
        if (this.zzbkr.zzdt().zzaxj) {
            this.zzbx("Is interstitial. Cannot resize an interstitial.");
        }
        // monitorexit(o)
        else if (this.zzbkr.zzvv()) {
            this.zzbx("Cannot resize an expanded banner.");
        }
        // monitorexit(o)
        else {
            this.zzi(map);
            if (!this.zzns()) {
                this.zzbx("Invalid mWidth and mHeight options. Cannot resize.");
            }
            // monitorexit(o)
            else {
                final Window window = this.zzbul.getWindow();
                if (window == null || window.getDecorView() == null) {
                    this.zzbx("Activity context is not ready, cannot get window or decor view.");
                }
                // monitorexit(o)
                else {
                    final int[] zznt = this.zznt();
                    if (zznt == null) {
                        this.zzbx("Resize location out of screen or close button is not visible.");
                    }
                    // monitorexit(o)
                    else {
                        final int zzb = zzm.zzjr().zzb((Context)this.zzbul, this.zzajw);
                        final int zzb2 = zzm.zzjr().zzb((Context)this.zzbul, this.zzajx);
                        final ViewParent parent = this.zzbkr.getView().getParent();
                    Label_0898_Outer:
                        while (true) {
                        Label_0854_Outer:
                            while (true) {
                            Label_0871_Outer:
                                while (true) {
                                Label_0915_Outer:
                                    while (true) {
                                    Label_0932_Outer:
                                        while (true) {
                                        Label_0888_Outer:
                                            while (true) {
                                                while (true) {
                                                    RelativeLayout$LayoutParams relativeLayout$LayoutParams = null;
                                                    int n = 0;
                                                    Label_1072: {
                                                    Label_0758_Outer:
                                                        while (true) {
                                                        Label_0790_Outer:
                                                            while (true) {
                                                                while (true) {
                                                                Label_0806_Outer:
                                                                    while (true) {
                                                                        while (true) {
                                                                            while (true) {
                                                                                Label_0746: {
                                                                                    if (parent == null || !(parent instanceof ViewGroup)) {
                                                                                        break Label_0746;
                                                                                    }
                                                                                    ((ViewGroup)parent).removeView(this.zzbkr.getView());
                                                                                    if (this.zzbvf == null) {
                                                                                        this.zzbvh = (ViewGroup)parent;
                                                                                        (this.zzbvc = new ImageView((Context)this.zzbul)).setImageBitmap(zzu.zzfz().zzk(this.zzbkr.getView()));
                                                                                        this.zzapc = this.zzbkr.zzdt();
                                                                                        this.zzbvh.addView((View)this.zzbvc);
                                                                                        break Label_0746;
                                                                                    }
                                                                                    Label_0736: {
                                                                                        break Label_0736;
                                                                                    Block_21_Outer:
                                                                                        while (true) {
                                                                                            this.zzbvd.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                                                                                                public void onClick(final View view) {
                                                                                                    zzhh.this.zzt(true);
                                                                                                }
                                                                                            });
                                                                                            this.zzbvd.setContentDescription((CharSequence)"Close button");
                                                                                            this.zzbvg.addView((View)this.zzbvd, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
                                                                                            try {
                                                                                                this.zzbvf.showAtLocation(window.getDecorView(), 0, zzm.zzjr().zzb((Context)this.zzbul, zznt[0]), zzm.zzjr().zzb((Context)this.zzbul, zznt[1]));
                                                                                                this.zzb(zznt[0], zznt[1]);
                                                                                                this.zzbkr.zza(new AdSizeParcel((Context)this.zzbul, new AdSize(this.zzajw, this.zzajx)));
                                                                                                this.zzc(zznt[0], zznt[1]);
                                                                                                this.zzbz("resized");
                                                                                                // monitorexit(o)
                                                                                                return;
                                                                                                // iftrue(Label_1069:, !zzbuw.equals((Object)"bottom-right"))
                                                                                                // iftrue(Label_1069:, !zzbuw.equals((Object)"top-left"))
                                                                                                // monitorexit(o)
                                                                                                // iftrue(Label_1069:, !zzbuw.equals((Object)"center"))
                                                                                                // iftrue(Label_1069:, !zzbuw.equals((Object)"bottom-center"))
                                                                                                while (true) {
                                                                                                Block_22_Outer:
                                                                                                    while (true) {
                                                                                                        n = 4;
                                                                                                        break Label_1072;
                                                                                                        while (true) {
                                                                                                            while (true) {
                                                                                                                n = 5;
                                                                                                                break Label_1072;
                                                                                                                relativeLayout$LayoutParams.addRule(12);
                                                                                                                relativeLayout$LayoutParams.addRule(9);
                                                                                                                continue Block_21_Outer;
                                                                                                                n = 1;
                                                                                                                break Label_1072;
                                                                                                                n = 0;
                                                                                                                break Label_1072;
                                                                                                                relativeLayout$LayoutParams.addRule(10);
                                                                                                                relativeLayout$LayoutParams.addRule(9);
                                                                                                                continue Block_21_Outer;
                                                                                                                relativeLayout$LayoutParams.addRule(10);
                                                                                                                relativeLayout$LayoutParams.addRule(14);
                                                                                                                continue Block_21_Outer;
                                                                                                                continue Label_0898_Outer;
                                                                                                            }
                                                                                                            continue Label_0854_Outer;
                                                                                                        }
                                                                                                        this.zzbx("Webview is detached, probably in the middle of a resize or expand.");
                                                                                                        return;
                                                                                                        while (true) {
                                                                                                            n = 2;
                                                                                                            break Label_1072;
                                                                                                            continue Label_0790_Outer;
                                                                                                        }
                                                                                                        relativeLayout$LayoutParams.addRule(12);
                                                                                                        relativeLayout$LayoutParams.addRule(14);
                                                                                                        continue Block_21_Outer;
                                                                                                        continue Block_22_Outer;
                                                                                                    }
                                                                                                    Block_20: {
                                                                                                        break Block_20;
                                                                                                        this.zzbvf.dismiss();
                                                                                                        break;
                                                                                                    }
                                                                                                    n = 3;
                                                                                                    break Label_1072;
                                                                                                    relativeLayout$LayoutParams.addRule(12);
                                                                                                    relativeLayout$LayoutParams.addRule(11);
                                                                                                    continue Block_21_Outer;
                                                                                                    relativeLayout$LayoutParams.addRule(13);
                                                                                                    continue Block_21_Outer;
                                                                                                    continue Label_0854_Outer;
                                                                                                }
                                                                                            }
                                                                                            // iftrue(Label_1069:, !zzbuw.equals((Object)"bottom-left"))
                                                                                            // iftrue(Label_1069:, !zzbuw.equals((Object)"top-center"))
                                                                                            catch (RuntimeException ex) {
                                                                                                final String value = String.valueOf(ex.getMessage());
                                                                                                String concat;
                                                                                                if (value.length() != 0) {
                                                                                                    concat = "Cannot show popup window: ".concat(value);
                                                                                                }
                                                                                                else {
                                                                                                    concat = new String("Cannot show popup window: ");
                                                                                                }
                                                                                                this.zzbx(concat);
                                                                                                this.zzbvg.removeView(this.zzbkr.getView());
                                                                                                if (this.zzbvh != null) {
                                                                                                    this.zzbvh.removeView((View)this.zzbvc);
                                                                                                    this.zzbvh.addView(this.zzbkr.getView());
                                                                                                    this.zzbkr.zza(this.zzapc);
                                                                                                }
                                                                                            }
                                                                                            // monitorexit(o)
                                                                                            return;
                                                                                        }
                                                                                    }
                                                                                }
                                                                                (this.zzbvg = new RelativeLayout((Context)this.zzbul)).setBackgroundColor(0);
                                                                                this.zzbvg.setLayoutParams(new ViewGroup$LayoutParams(zzb, zzb2));
                                                                                (this.zzbvf = zzu.zzfz().zza((View)this.zzbvg, zzb, zzb2, false)).setOutsideTouchable(true);
                                                                                this.zzbvf.setTouchable(true);
                                                                                this.zzbvf.setClippingEnabled(!this.zzbux);
                                                                                this.zzbvg.addView(this.zzbkr.getView(), -1, -1);
                                                                                this.zzbvd = new LinearLayout((Context)this.zzbul);
                                                                                relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(zzm.zzjr().zzb((Context)this.zzbul, 50), zzm.zzjr().zzb((Context)this.zzbul, 50));
                                                                                final String zzbuw = this.zzbuw;
                                                                                switch (zzbuw.hashCode()) {
                                                                                    case -1012429441: {
                                                                                        continue Label_0790_Outer;
                                                                                    }
                                                                                    case 1755462605: {
                                                                                        continue;
                                                                                    }
                                                                                    case -1364013995: {
                                                                                        continue Label_0915_Outer;
                                                                                    }
                                                                                    case -655373719: {
                                                                                        continue Label_0932_Outer;
                                                                                    }
                                                                                    case 1288627767: {
                                                                                        continue Label_0806_Outer;
                                                                                    }
                                                                                    case 1163912186: {
                                                                                        continue Label_0758_Outer;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            }
                                                                            break;
                                                                        }
                                                                        break;
                                                                    }
                                                                    break;
                                                                }
                                                                break;
                                                            }
                                                            break;
                                                        }
                                                        Label_1069: {
                                                            n = -1;
                                                        }
                                                    }
                                                    switch (n) {
                                                        default: {
                                                            relativeLayout$LayoutParams.addRule(10);
                                                            relativeLayout$LayoutParams.addRule(11);
                                                            continue Label_0898_Outer;
                                                        }
                                                        case 0: {
                                                            continue Label_0871_Outer;
                                                        }
                                                        case 1: {
                                                            continue Label_0915_Outer;
                                                        }
                                                        case 2: {
                                                            continue;
                                                        }
                                                        case 3: {
                                                            continue Label_0854_Outer;
                                                        }
                                                        case 4: {
                                                            continue Label_0932_Outer;
                                                        }
                                                        case 5: {
                                                            continue Label_0888_Outer;
                                                        }
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            break;
                                        }
                                        break;
                                    }
                                    break;
                                }
                                break;
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
    
    public void zza(final int zzbuy, final int zzbuz, final boolean b) {
        synchronized (this.zzakd) {
            this.zzbuy = zzbuy;
            this.zzbuz = zzbuz;
            if (this.zzbvf != null && b) {
                final int[] zznt = this.zznt();
                if (zznt != null) {
                    this.zzbvf.update(zzm.zzjr().zzb((Context)this.zzbul, zznt[0]), zzm.zzjr().zzb((Context)this.zzbul, zznt[1]), this.zzbvf.getWidth(), this.zzbvf.getHeight());
                    this.zzc(zznt[0], zznt[1]);
                }
                else {
                    this.zzt(true);
                }
            }
        }
    }
    
    void zzb(final int n, final int n2) {
        if (this.zzbve != null) {
            this.zzbve.zza(n, n2, this.zzajw, this.zzajx);
        }
    }
    
    void zzc(final int n, final int n2) {
        this.zzb(n, n2 - zzu.zzfz().zzk(this.zzbul)[0], this.zzajw, this.zzajx);
    }
    
    public void zzd(final int zzbuy, final int zzbuz) {
        this.zzbuy = zzbuy;
        this.zzbuz = zzbuz;
    }
    
    boolean zzns() {
        return this.zzajw > -1 && this.zzajx > -1;
    }
    
    public boolean zznu() {
        while (true) {
            synchronized (this.zzakd) {
                if (this.zzbvf != null) {
                    return true;
                }
            }
            return false;
        }
    }
    
    boolean zznv() {
        boolean b = false;
        final int[] zzi = zzu.zzfz().zzi(this.zzbul);
        final int[] zzk = zzu.zzfz().zzk(this.zzbul);
        final int n = zzi[0];
        final int n2 = zzi[1];
        if (this.zzajw < 50 || this.zzajw > n) {
            zzb.zzdf("Width is too small or too large.");
        }
        else if (this.zzajx < 50 || this.zzajx > n2) {
            zzb.zzdf("Height is too small or too large.");
            b = false;
        }
        else if (this.zzajx == n2 && this.zzajw == n) {
            zzb.zzdf("Cannot resize to a full-screen ad.");
            b = false;
        }
        else {
            if (this.zzbux) {
                final String zzbuw = this.zzbuw;
                int n3 = -1;
                switch (zzbuw.hashCode()) {
                    case -1012429441: {
                        if (zzbuw.equals("top-left")) {
                            n3 = 0;
                            break;
                        }
                        break;
                    }
                    case 1755462605: {
                        if (zzbuw.equals("top-center")) {
                            n3 = 1;
                            break;
                        }
                        break;
                    }
                    case -1364013995: {
                        if (zzbuw.equals("center")) {
                            n3 = 2;
                            break;
                        }
                        break;
                    }
                    case -655373719: {
                        if (zzbuw.equals("bottom-left")) {
                            n3 = 3;
                            break;
                        }
                        break;
                    }
                    case 1288627767: {
                        if (zzbuw.equals("bottom-center")) {
                            n3 = 4;
                            break;
                        }
                        break;
                    }
                    case 1163912186: {
                        if (zzbuw.equals("bottom-right")) {
                            n3 = 5;
                            break;
                        }
                        break;
                    }
                }
                int n4 = 0;
                int n5 = 0;
                switch (n3) {
                    default: {
                        n4 = -50 + (this.zzbuy + this.zzbva + this.zzajw);
                        n5 = this.zzbuz + this.zzbvb;
                        break;
                    }
                    case 0: {
                        n4 = this.zzbuy + this.zzbva;
                        n5 = this.zzbuz + this.zzbvb;
                        break;
                    }
                    case 1: {
                        n4 = -25 + (this.zzbuy + this.zzbva + this.zzajw / 2);
                        n5 = this.zzbuz + this.zzbvb;
                        break;
                    }
                    case 2: {
                        n4 = -25 + (this.zzbuy + this.zzbva + this.zzajw / 2);
                        n5 = -25 + (this.zzbuz + this.zzbvb + this.zzajx / 2);
                        break;
                    }
                    case 3: {
                        n4 = this.zzbuy + this.zzbva;
                        n5 = -50 + (this.zzbuz + this.zzbvb + this.zzajx);
                        break;
                    }
                    case 4: {
                        n4 = -25 + (this.zzbuy + this.zzbva + this.zzajw / 2);
                        n5 = -50 + (this.zzbuz + this.zzbvb + this.zzajx);
                        break;
                    }
                    case 5: {
                        n4 = -50 + (this.zzbuy + this.zzbva + this.zzajw);
                        n5 = -50 + (this.zzbuz + this.zzbvb + this.zzajx);
                        break;
                    }
                }
                b = false;
                if (n4 < 0) {
                    return b;
                }
                final int n6 = n4 + 50;
                b = false;
                if (n6 > n) {
                    return b;
                }
                final int n7 = zzk[0];
                b = false;
                if (n5 < n7) {
                    return b;
                }
                final int n8 = n5 + 50;
                final int n9 = zzk[1];
                b = false;
                if (n8 > n9) {
                    return b;
                }
            }
            b = true;
        }
        return b;
    }
    
    public void zzt(final boolean b) {
        synchronized (this.zzakd) {
            if (this.zzbvf != null) {
                this.zzbvf.dismiss();
                this.zzbvg.removeView(this.zzbkr.getView());
                if (this.zzbvh != null) {
                    this.zzbvh.removeView((View)this.zzbvc);
                    this.zzbvh.addView(this.zzbkr.getView());
                    this.zzbkr.zza(this.zzapc);
                }
                if (b) {
                    this.zzbz("default");
                    if (this.zzbve != null) {
                        this.zzbve.zzep();
                    }
                }
                this.zzbvf = null;
                this.zzbvg = null;
                this.zzbvh = null;
                this.zzbvd = null;
            }
        }
    }
}
