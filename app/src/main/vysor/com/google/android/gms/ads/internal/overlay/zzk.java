// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.widget.TextView;
import com.google.android.gms.internal.zzkr;
import android.text.TextUtils;
import android.annotation.TargetApi;
import android.view.MotionEvent;
import java.util.Map;
import java.util.HashMap;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.common.internal.zzc;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import com.google.android.gms.internal.zzdq;
import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzlt;
import com.google.android.gms.internal.zziy;
import android.widget.FrameLayout;

@zziy
public class zzk extends FrameLayout implements zzh
{
    private final zzlt zzbkr;
    private String zzbnt;
    private final FrameLayout zzbyr;
    private final zzy zzbys;
    @Nullable
    private zzi zzbyt;
    private boolean zzbyu;
    private boolean zzbyv;
    private long zzbyw;
    private long zzbyx;
    
    public zzk(final Context context, final zzlt zzbkr, final int n, final boolean b, final zzdq zzdq) {
        super(context);
        this.zzbkr = zzbkr;
        this.addView((View)(this.zzbyr = new FrameLayout(context)), (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        zzc.zzu(zzbkr.zzdp());
        this.zzbyt = zzbkr.zzdp().zzamd.zza(context, zzbkr, n, b, zzdq);
        if (this.zzbyt != null) {
            this.zzbyr.addView((View)this.zzbyt, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1, 17));
            if (zzdi.zzbbo.get()) {
                this.zzpn();
            }
        }
        (this.zzbys = new zzy(this)).zzqh();
        if (this.zzbyt != null) {
            this.zzbyt.zza(this);
        }
        if (this.zzbyt == null) {
            this.zzl("AdVideoUnderlay Error", "Allocating player failed.");
        }
    }
    
    private void zza(final String s, final String... array) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("event", s);
        final int length = array.length;
        int i = 0;
        String s2 = null;
        while (i < length) {
            String s3 = array[i];
            if (s2 != null) {
                hashMap.put(s2, s3);
                s3 = null;
            }
            ++i;
            s2 = s3;
        }
        this.zzbkr.zza("onVideoEvent", hashMap);
    }
    
    public static void zzi(final zzlt zzlt) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("event", "no_video_view");
        zzlt.zza("onVideoEvent", hashMap);
    }
    
    private void zzpp() {
        if (this.zzbkr.zzvn() != null && !this.zzbyu && !(this.zzbyv = ((0x80 & this.zzbkr.zzvn().getWindow().getAttributes().flags) != 0x0))) {
            this.zzbkr.zzvn().getWindow().addFlags(128);
            this.zzbyu = true;
        }
    }
    
    private void zzpq() {
        if (this.zzbkr.zzvn() != null && this.zzbyu && !this.zzbyv) {
            this.zzbkr.zzvn().getWindow().clearFlags(128);
            this.zzbyu = false;
        }
    }
    
    public void destroy() {
        this.zzbys.cancel();
        if (this.zzbyt != null) {
            this.zzbyt.stop();
        }
        this.zzpq();
    }
    
    public void onPaused() {
        this.zza("pause", new String[0]);
        this.zzpq();
    }
    
    public void pause() {
        if (this.zzbyt != null) {
            this.zzbyt.pause();
        }
    }
    
    public void play() {
        if (this.zzbyt != null) {
            this.zzbyt.play();
        }
    }
    
    public void seekTo(final int n) {
        if (this.zzbyt != null) {
            this.zzbyt.seekTo(n);
        }
    }
    
    public void zza(final float n) {
        if (this.zzbyt != null) {
            this.zzbyt.zza(n);
        }
    }
    
    public void zza(final float n, final float n2) {
        if (this.zzbyt != null) {
            this.zzbyt.zza(n, n2);
        }
    }
    
    public void zzca(final String zzbnt) {
        this.zzbnt = zzbnt;
    }
    
    public void zzd(final int n, final int n2, final int n3, final int n4) {
        if (n3 != 0 && n4 != 0) {
            final FrameLayout$LayoutParams layoutParams = new FrameLayout$LayoutParams(n3, n4);
            layoutParams.setMargins(n, n2, 0, 0);
            this.zzbyr.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            this.requestLayout();
        }
    }
    
    @TargetApi(14)
    public void zzf(final MotionEvent motionEvent) {
        if (this.zzbyt != null) {
            this.zzbyt.dispatchTouchEvent(motionEvent);
        }
    }
    
    public void zzl(final String s, @Nullable final String s2) {
        this.zza("error", "what", s, "extra", s2);
    }
    
    public void zzmt() {
        if (this.zzbyt != null) {
            if (!TextUtils.isEmpty((CharSequence)this.zzbnt)) {
                this.zzbyt.setVideoPath(this.zzbnt);
            }
            else {
                this.zza("no_src", new String[0]);
            }
        }
    }
    
    public void zzom() {
        if (this.zzbyt != null) {
            this.zzbyt.zzom();
        }
    }
    
    public void zzon() {
        if (this.zzbyt != null) {
            this.zzbyt.zzon();
        }
    }
    
    public void zzpi() {
        zzkr.zzcrf.post((Runnable)new Runnable() {
            @Override
            public void run() {
                zzk.this.zza("surfaceCreated", new String[0]);
            }
        });
    }
    
    public void zzpj() {
        if (this.zzbyt != null && this.zzbyx == 0L) {
            this.zza("canplaythrough", "duration", String.valueOf(this.zzbyt.getDuration() / 1000.0f), "videoWidth", String.valueOf(this.zzbyt.getVideoWidth()), "videoHeight", String.valueOf(this.zzbyt.getVideoHeight()));
        }
    }
    
    public void zzpk() {
        this.zzpp();
    }
    
    public void zzpl() {
        this.zza("ended", new String[0]);
        this.zzpq();
    }
    
    public void zzpm() {
        this.zzbyx = this.zzbyw;
        zzkr.zzcrf.post((Runnable)new Runnable() {
            @Override
            public void run() {
                zzk.this.zza("surfaceDestroyed", new String[0]);
            }
        });
    }
    
    @TargetApi(14)
    public void zzpn() {
        if (this.zzbyt != null) {
            final TextView textView = new TextView(this.zzbyt.getContext());
            final String value = String.valueOf(this.zzbyt.zzog());
            String concat;
            if (value.length() != 0) {
                concat = "AdMob - ".concat(value);
            }
            else {
                concat = new String("AdMob - ");
            }
            textView.setText((CharSequence)concat);
            textView.setTextColor(-65536);
            textView.setBackgroundColor(-256);
            this.zzbyr.addView((View)textView, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2, 17));
            this.zzbyr.bringChildToFront((View)textView);
        }
    }
    
    void zzpo() {
        if (this.zzbyt != null) {
            final long zzbyw = this.zzbyt.getCurrentPosition();
            if (this.zzbyw != zzbyw && zzbyw > 0L) {
                this.zza("timeupdate", "time", String.valueOf(zzbyw / 1000.0f));
                this.zzbyw = zzbyw;
            }
        }
    }
}
