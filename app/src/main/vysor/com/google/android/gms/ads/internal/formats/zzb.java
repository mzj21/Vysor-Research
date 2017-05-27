// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.formats;

import android.view.ViewGroup;
import java.util.Iterator;
import java.util.List;
import android.widget.ImageView;
import com.google.android.gms.ads.internal.client.zzm;
import android.graphics.Typeface;
import android.widget.TextView;
import android.text.TextUtils;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.gms.ads.internal.zzu;
import android.view.ViewGroup$LayoutParams;
import android.graphics.drawable.shapes.Shape;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.RectF;
import android.graphics.drawable.shapes.RoundRectShape;
import android.widget.RelativeLayout$LayoutParams;
import com.google.android.gms.common.internal.zzac;
import android.content.Context;
import android.support.annotation.Nullable;
import android.graphics.drawable.AnimationDrawable;
import com.google.android.gms.internal.zziy;
import android.widget.RelativeLayout;

@zziy
class zzb extends RelativeLayout
{
    private static final float[] zzbjl;
    private final RelativeLayout zzbjm;
    @Nullable
    private AnimationDrawable zzbjn;
    
    static {
        zzbjl = new float[] { 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f };
    }
    
    public zzb(final Context context, final zza zza) {
        super(context);
        zzac.zzy(zza);
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-2, -2);
        switch (zza.zzll()) {
            default: {
                layoutParams.addRule(10);
                layoutParams.addRule(11);
                break;
            }
            case 0: {
                layoutParams.addRule(10);
                layoutParams.addRule(9);
                break;
            }
            case 3: {
                layoutParams.addRule(12);
                layoutParams.addRule(9);
                break;
            }
            case 2: {
                layoutParams.addRule(12);
                layoutParams.addRule(11);
                break;
            }
        }
        final ShapeDrawable shapeDrawable = new ShapeDrawable((Shape)new RoundRectShape(zzb.zzbjl, (RectF)null, (float[])null));
        shapeDrawable.getPaint().setColor(zza.getBackgroundColor());
        (this.zzbjm = new RelativeLayout(context)).setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        zzu.zzgb().zza((View)this.zzbjm, (Drawable)shapeDrawable);
        final RelativeLayout$LayoutParams layoutParams2 = new RelativeLayout$LayoutParams(-2, -2);
        if (!TextUtils.isEmpty((CharSequence)zza.getText())) {
            final RelativeLayout$LayoutParams layoutParams3 = new RelativeLayout$LayoutParams(-2, -2);
            final TextView textView = new TextView(context);
            textView.setLayoutParams((ViewGroup$LayoutParams)layoutParams3);
            textView.setId(1195835393);
            textView.setTypeface(Typeface.DEFAULT);
            textView.setText((CharSequence)zza.getText());
            textView.setTextColor(zza.getTextColor());
            textView.setTextSize((float)zza.getTextSize());
            textView.setPadding(zzm.zzjr().zzb(context, 4), 0, zzm.zzjr().zzb(context, 4), 0);
            this.zzbjm.addView((View)textView);
            layoutParams2.addRule(1, textView.getId());
        }
        final ImageView imageView = new ImageView(context);
        imageView.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
        imageView.setId(1195835394);
        final List<Drawable> zzlj = zza.zzlj();
        if (zzlj.size() > 1) {
            this.zzbjn = new AnimationDrawable();
            final Iterator<Drawable> iterator = zzlj.iterator();
            while (iterator.hasNext()) {
                this.zzbjn.addFrame((Drawable)iterator.next(), zza.zzlk());
            }
            zzu.zzgb().zza((View)imageView, (Drawable)this.zzbjn);
        }
        else if (zzlj.size() == 1) {
            imageView.setImageDrawable((Drawable)zzlj.get(0));
        }
        this.zzbjm.addView((View)imageView);
        this.addView((View)this.zzbjm);
    }
    
    public void onAttachedToWindow() {
        if (this.zzbjn != null) {
            this.zzbjn.start();
        }
        super.onAttachedToWindow();
    }
    
    public ViewGroup zzlm() {
        return (ViewGroup)this.zzbjm;
    }
}
