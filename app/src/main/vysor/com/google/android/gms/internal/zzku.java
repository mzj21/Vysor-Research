// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import android.content.Intent;
import android.content.res.Resources;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import com.google.android.gms.R;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.app.Activity;
import java.util.Iterator;
import java.util.Map;
import android.net.Uri;
import com.google.android.gms.ads.internal.zzu;
import android.net.Uri$Builder;
import android.text.TextUtils;
import android.content.Context;

@zziy
public class zzku
{
    private final Context mContext;
    private int mState;
    private String zzang;
    private final float zzbvv;
    private String zzcro;
    private float zzcrp;
    private float zzcrq;
    private float zzcrr;
    
    public zzku(final Context mContext) {
        this.mState = 0;
        this.mContext = mContext;
        this.zzbvv = mContext.getResources().getDisplayMetrics().density;
    }
    
    public zzku(final Context context, final String zzcro) {
        this(context);
        this.zzcro = zzcro;
    }
    
    static String zzda(final String s) {
        String trim;
        if (TextUtils.isEmpty((CharSequence)s)) {
            trim = "No debug information";
        }
        else {
            final Uri build = new Uri$Builder().encodedQuery(s.replaceAll("\\+", "%20")).build();
            final StringBuilder sb = new StringBuilder();
            final Map<String, String> zzg = zzu.zzfz().zzg(build);
            for (final String s2 : zzg.keySet()) {
                sb.append(s2).append(" = ").append(zzg.get(s2)).append("\n\n");
            }
            trim = sb.toString().trim();
            if (TextUtils.isEmpty((CharSequence)trim)) {
                trim = "No debug information";
            }
        }
        return trim;
    }
    
    private void zzur() {
        if (!(this.mContext instanceof Activity)) {
            zzb.zzde("Can not create dialog without Activity Context");
        }
        else {
            final Resources resources = zzu.zzgd().getResources();
            String string;
            if (resources != null) {
                string = resources.getString(R.string.debug_menu_title);
            }
            else {
                string = "Select a Debug Mode";
            }
            String string2;
            if (resources != null) {
                string2 = resources.getString(R.string.debug_menu_ad_information);
            }
            else {
                string2 = "Ad Information";
            }
            String string3;
            if (resources != null) {
                string3 = resources.getString(R.string.debug_menu_creative_preview);
            }
            else {
                string3 = "Creative Preview";
            }
            String string4;
            if (resources != null) {
                string4 = resources.getString(R.string.debug_menu_troubleshooting);
            }
            else {
                string4 = "Troubleshooting";
            }
            new AlertDialog$Builder(this.mContext).setTitle((CharSequence)string).setItems((CharSequence[])new String[] { string2, string3, string4 }, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    switch (n) {
                        case 0: {
                            zzku.this.zzus();
                            break;
                        }
                        case 1: {
                            zzb.zzdd("Debug mode [Creative Preview] selected.");
                            zzkq.zza(new Runnable() {
                                @Override
                                public void run() {
                                    zzu.zzgh().zzi(zzku.this.mContext, zzku.this.zzang);
                                }
                            });
                            break;
                        }
                        case 2: {
                            zzb.zzdd("Debug mode [Troubleshooting] selected.");
                            zzkq.zza(new Runnable() {
                                @Override
                                public void run() {
                                    zzu.zzgh().zzj(zzku.this.mContext, zzku.this.zzang);
                                }
                            });
                            break;
                        }
                    }
                }
            }).create().show();
        }
    }
    
    private void zzus() {
        if (!(this.mContext instanceof Activity)) {
            zzb.zzde("Can not create dialog without Activity Context");
        }
        else {
            final String zzda = zzda(this.zzcro);
            final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.mContext);
            alertDialog$Builder.setMessage((CharSequence)zzda);
            alertDialog$Builder.setTitle((CharSequence)"Ad Information");
            alertDialog$Builder.setPositiveButton((CharSequence)"Share", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    zzu.zzfz().zzb(zzku.this.mContext, Intent.createChooser(new Intent("android.intent.action.SEND").setType("text/plain").putExtra("android.intent.extra.TEXT", zzda), (CharSequence)"Share via"));
                }
            });
            alertDialog$Builder.setNegativeButton((CharSequence)"Close", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                }
            });
            alertDialog$Builder.create().show();
        }
    }
    
    public void setAdUnitId(final String zzang) {
        this.zzang = zzang;
    }
    
    public void showDialog() {
        if (zzdi.zzbhq.get()) {
            this.zzur();
        }
        else {
            this.zzus();
        }
    }
    
    void zza(final int n, final float zzcrp, final float n2) {
        if (n == 0) {
            this.mState = 0;
            this.zzcrp = zzcrp;
            this.zzcrq = n2;
            this.zzcrr = n2;
        }
        else if (this.mState != -1) {
            if (n == 2) {
                if (n2 > this.zzcrq) {
                    this.zzcrq = n2;
                }
                else if (n2 < this.zzcrr) {
                    this.zzcrr = n2;
                }
                if (this.zzcrq - this.zzcrr > 30.0f * this.zzbvv) {
                    this.mState = -1;
                }
                else {
                    if (this.mState == 0 || this.mState == 2) {
                        if (zzcrp - this.zzcrp >= 50.0f * this.zzbvv) {
                            this.zzcrp = zzcrp;
                            ++this.mState;
                        }
                    }
                    else if ((this.mState == 1 || this.mState == 3) && zzcrp - this.zzcrp <= -50.0f * this.zzbvv) {
                        this.zzcrp = zzcrp;
                        ++this.mState;
                    }
                    if (this.mState == 1 || this.mState == 3) {
                        if (zzcrp > this.zzcrp) {
                            this.zzcrp = zzcrp;
                        }
                    }
                    else if (this.mState == 2 && zzcrp < this.zzcrp) {
                        this.zzcrp = zzcrp;
                    }
                }
            }
            else if (n == 1 && this.mState == 4) {
                this.showDialog();
            }
        }
    }
    
    public void zzcz(final String zzcro) {
        this.zzcro = zzcro;
    }
    
    public void zzg(final MotionEvent motionEvent) {
        for (int historySize = motionEvent.getHistorySize(), i = 0; i < historySize; ++i) {
            this.zza(motionEvent.getActionMasked(), motionEvent.getHistoricalX(0, i), motionEvent.getHistoricalY(0, i));
        }
        this.zza(motionEvent.getActionMasked(), motionEvent.getX(), motionEvent.getY());
    }
}
