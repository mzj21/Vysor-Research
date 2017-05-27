// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import com.google.android.gms.ads.internal.client.zzm;
import android.content.Context;
import android.widget.ImageButton;
import com.google.android.gms.internal.zziy;
import android.view.View$OnClickListener;
import android.widget.FrameLayout;

@zziy
public class zzo extends FrameLayout implements View$OnClickListener
{
    private final ImageButton zzbyz;
    private final zzu zzbza;
    
    public zzo(final Context context, final int n, final zzu zzbza) {
        super(context);
        this.zzbza = zzbza;
        this.setOnClickListener((View$OnClickListener)this);
        (this.zzbyz = new ImageButton(context)).setImageResource(17301527);
        this.zzbyz.setBackgroundColor(0);
        this.zzbyz.setOnClickListener((View$OnClickListener)this);
        this.zzbyz.setPadding(0, 0, 0, 0);
        this.zzbyz.setContentDescription((CharSequence)"Interstitial close button");
        final int zzb = zzm.zzjr().zzb(context, n);
        this.addView((View)this.zzbyz, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(zzb, zzb, 17));
    }
    
    public void onClick(final View view) {
        if (this.zzbza != null) {
            this.zzbza.zzot();
        }
    }
    
    public void zza(final boolean b, final boolean b2) {
        if (b2) {
            if (b) {
                this.zzbyz.setVisibility(4);
            }
            else {
                this.zzbyz.setVisibility(8);
            }
        }
        else {
            this.zzbyz.setVisibility(0);
        }
    }
}
