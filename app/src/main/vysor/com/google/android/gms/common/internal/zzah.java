// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.text.method.TransformationMethod;
import android.content.res.ColorStateList;
import com.google.android.gms.R;
import android.graphics.Typeface;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.Button;

public final class zzah extends Button
{
    public zzah(final Context context) {
        this(context, null);
    }
    
    public zzah(final Context context, final AttributeSet set) {
        super(context, set, 16842824);
    }
    
    private void zza(final Resources resources) {
        this.setTypeface(Typeface.DEFAULT_BOLD);
        this.setTextSize(14.0f);
        final float density = resources.getDisplayMetrics().density;
        this.setMinHeight((int)(0.5f + density * 48.0f));
        this.setMinWidth((int)(0.5f + density * 48.0f));
    }
    
    private void zzb(final Resources resources, final int n, final int n2) {
        this.setBackgroundDrawable(resources.getDrawable(this.zze(n, this.zzg(n2, R.drawable.common_google_signin_btn_icon_dark, R.drawable.common_google_signin_btn_icon_light, R.drawable.common_google_signin_btn_icon_light), this.zzg(n2, R.drawable.common_google_signin_btn_text_dark, R.drawable.common_google_signin_btn_text_light, R.drawable.common_google_signin_btn_text_light))));
    }
    
    private void zzc(final Resources resources, final int n, final int n2) {
        this.setTextColor((ColorStateList)zzac.zzy(resources.getColorStateList(this.zzg(n2, R.color.common_google_signin_btn_text_dark, R.color.common_google_signin_btn_text_light, R.color.common_google_signin_btn_text_light))));
        switch (n) {
            default: {
                throw new IllegalStateException(new StringBuilder(32).append("Unknown button size: ").append(n).toString());
            }
            case 0: {
                this.setText((CharSequence)resources.getString(R.string.common_signin_button_text));
                break;
            }
            case 1: {
                this.setText((CharSequence)resources.getString(R.string.common_signin_button_text_long));
                break;
            }
            case 2: {
                this.setText((CharSequence)null);
                break;
            }
        }
        this.setTransformationMethod((TransformationMethod)null);
    }
    
    private int zze(final int n, final int n2, int n3) {
        switch (n) {
            default: {
                throw new IllegalStateException(new StringBuilder(32).append("Unknown button size: ").append(n).toString());
            }
            case 2: {
                n3 = n2;
            }
            case 0:
            case 1: {
                return n3;
            }
        }
    }
    
    private int zzg(final int n, int n2, final int n3, final int n4) {
        switch (n) {
            default: {
                throw new IllegalStateException(new StringBuilder(33).append("Unknown color scheme: ").append(n).toString());
            }
            case 1: {
                n2 = n3;
                return n2;
            }
            case 2: {
                n2 = n4;
            }
            case 0: {
                return n2;
            }
        }
    }
    
    public void zza(final Resources resources, final int n, final int n2) {
        this.zza(resources);
        this.zzb(resources, n, n2);
        this.zzc(resources, n, n2);
    }
}
