// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import android.app.Activity;
import java.util.Map;
import com.google.android.gms.ads.internal.client.zzm;
import android.view.Display;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.content.Context;

@zziy
public class zzhl extends zzhm implements zzev
{
    private final Context mContext;
    private final WindowManager zzasl;
    private final zzlt zzbkr;
    private final zzda zzbvt;
    DisplayMetrics zzbvu;
    private float zzbvv;
    int zzbvw;
    int zzbvx;
    private int zzbvy;
    int zzbvz;
    int zzbwa;
    int zzbwb;
    int zzbwc;
    
    public zzhl(final zzlt zzbkr, final Context mContext, final zzda zzbvt) {
        super(zzbkr);
        this.zzbvw = -1;
        this.zzbvx = -1;
        this.zzbvz = -1;
        this.zzbwa = -1;
        this.zzbwb = -1;
        this.zzbwc = -1;
        this.zzbkr = zzbkr;
        this.mContext = mContext;
        this.zzbvt = zzbvt;
        this.zzasl = (WindowManager)mContext.getSystemService("window");
    }
    
    private void zznx() {
        this.zzbvu = new DisplayMetrics();
        final Display defaultDisplay = this.zzasl.getDefaultDisplay();
        defaultDisplay.getMetrics(this.zzbvu);
        this.zzbvv = this.zzbvu.density;
        this.zzbvy = defaultDisplay.getRotation();
    }
    
    private void zzoc() {
        final int[] array = new int[2];
        this.zzbkr.getLocationOnScreen(array);
        this.zze(zzm.zzjr().zzc(this.mContext, array[0]), zzm.zzjr().zzc(this.mContext, array[1]));
    }
    
    private zzhk zzof() {
        return new zzhk.zza().zzv(this.zzbvt.zzkj()).zzu(this.zzbvt.zzkk()).zzw(this.zzbvt.zzko()).zzx(this.zzbvt.zzkl()).zzy(this.zzbvt.zzkm()).zznw();
    }
    
    @Override
    public void zza(final zzlt zzlt, final Map<String, String> map) {
        this.zzoa();
    }
    
    public void zze(final int n, final int n2) {
        int n3;
        if (this.mContext instanceof Activity) {
            n3 = zzu.zzfz().zzk((Activity)this.mContext)[0];
        }
        else {
            n3 = 0;
        }
        if (this.zzbkr.zzdt() == null || !this.zzbkr.zzdt().zzaxj) {
            this.zzbwb = zzm.zzjr().zzc(this.mContext, this.zzbkr.getMeasuredWidth());
            this.zzbwc = zzm.zzjr().zzc(this.mContext, this.zzbkr.getMeasuredHeight());
        }
        this.zzc(n, n2 - n3, this.zzbwb, this.zzbwc);
        this.zzbkr.zzvr().zzd(n, n2);
    }
    
    void zzny() {
        this.zzbvw = zzm.zzjr().zzb(this.zzbvu, this.zzbvu.widthPixels);
        this.zzbvx = zzm.zzjr().zzb(this.zzbvu, this.zzbvu.heightPixels);
        final Activity zzvn = this.zzbkr.zzvn();
        if (zzvn == null || zzvn.getWindow() == null) {
            this.zzbvz = this.zzbvw;
            this.zzbwa = this.zzbvx;
        }
        else {
            final int[] zzh = zzu.zzfz().zzh(zzvn);
            this.zzbvz = zzm.zzjr().zzb(this.zzbvu, zzh[0]);
            this.zzbwa = zzm.zzjr().zzb(this.zzbvu, zzh[1]);
        }
    }
    
    void zznz() {
        if (this.zzbkr.zzdt().zzaxj) {
            this.zzbwb = this.zzbvw;
            this.zzbwc = this.zzbvx;
        }
        else {
            this.zzbkr.measure(0, 0);
        }
    }
    
    public void zzoa() {
        this.zznx();
        this.zzny();
        this.zznz();
        this.zzod();
        this.zzoe();
        this.zzoc();
        this.zzob();
    }
    
    void zzob() {
        if (zzb.zzbf(2)) {
            zzb.zzde("Dispatching Ready Event.");
        }
        this.zzby(this.zzbkr.zzvu().zzcs);
    }
    
    void zzod() {
        this.zza(this.zzbvw, this.zzbvx, this.zzbvz, this.zzbwa, this.zzbvv, this.zzbvy);
    }
    
    void zzoe() {
        this.zzbkr.zzb("onDeviceFeaturesReceived", this.zzof().toJson());
    }
}
