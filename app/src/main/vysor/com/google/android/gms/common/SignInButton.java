// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.dynamic.zzg;
import android.util.Log;
import com.google.android.gms.common.internal.zzag;
import android.content.res.TypedArray;
import com.google.android.gms.R;
import com.google.android.gms.common.internal.zzah;
import android.widget.Button;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import android.view.View$OnClickListener;
import android.widget.FrameLayout;

public final class SignInButton extends FrameLayout implements View$OnClickListener
{
    public static final int COLOR_AUTO = 2;
    public static final int COLOR_DARK = 0;
    public static final int COLOR_LIGHT = 1;
    public static final int SIZE_ICON_ONLY = 2;
    public static final int SIZE_STANDARD = 0;
    public static final int SIZE_WIDE = 1;
    private int mColor;
    private int mSize;
    private View vg;
    private View$OnClickListener vh;
    
    public SignInButton(final Context context) {
        this(context, null);
    }
    
    public SignInButton(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public SignInButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.vh = null;
        this.zzb(context, set);
        this.setStyle(this.mSize, this.mColor);
    }
    
    private static Button zza(final Context context, final int n, final int n2) {
        final zzah zzah = new zzah(context);
        zzah.zza(context.getResources(), n, n2);
        return zzah;
    }
    
    private void zzb(final Context context, final AttributeSet set) {
        final TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(set, R.styleable.SignInButton, 0, 0);
        try {
            this.mSize = obtainStyledAttributes.getInt(R.styleable.SignInButton_buttonSize, 0);
            this.mColor = obtainStyledAttributes.getInt(R.styleable.SignInButton_colorScheme, 2);
        }
        finally {
            obtainStyledAttributes.recycle();
        }
    }
    
    private void zzca(final Context context) {
        if (this.vg != null) {
            this.removeView(this.vg);
        }
        while (true) {
            try {
                this.vg = zzag.zzb(context, this.mSize, this.mColor);
                this.addView(this.vg);
                this.vg.setEnabled(this.isEnabled());
                this.vg.setOnClickListener((View$OnClickListener)this);
            }
            catch (zzg.zza zza) {
                Log.w("SignInButton", "Sign in button not found, using placeholder instead");
                this.vg = (View)zza(context, this.mSize, this.mColor);
                continue;
            }
            break;
        }
    }
    
    public void onClick(final View view) {
        if (this.vh != null && view == this.vg) {
            this.vh.onClick((View)this);
        }
    }
    
    public void setColorScheme(final int n) {
        this.setStyle(this.mSize, n);
    }
    
    public void setEnabled(final boolean b) {
        super.setEnabled(b);
        this.vg.setEnabled(b);
    }
    
    public void setOnClickListener(final View$OnClickListener vh) {
        this.vh = vh;
        if (this.vg != null) {
            this.vg.setOnClickListener((View$OnClickListener)this);
        }
    }
    
    @Deprecated
    public void setScopes(final Scope[] array) {
        this.setStyle(this.mSize, this.mColor);
    }
    
    public void setSize(final int n) {
        this.setStyle(n, this.mColor);
    }
    
    public void setStyle(final int mSize, final int mColor) {
        this.mSize = mSize;
        this.mColor = mColor;
        this.zzca(this.getContext());
    }
    
    @Deprecated
    public void setStyle(final int n, final int n2, final Scope[] array) {
        this.setStyle(n, n2);
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface ButtonSize {
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface ColorScheme {
    }
}
