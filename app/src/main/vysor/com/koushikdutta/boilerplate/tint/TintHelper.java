// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate.tint;

import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Color;
import android.util.TypedValue;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import com.koushikdutta.boilerplate.R;
import android.content.Context;
import android.graphics.ColorMatrix;

public class TintHelper
{
    private static final ColorMatrix colorMatrixNegative;
    private static final float[] colorMatrix_Negative;
    
    static {
        colorMatrix_Negative = new float[] { -1.0f, 0.0f, 0.0f, 0.0f, 255.0f, 0.0f, -1.0f, 0.0f, 0.0f, 255.0f, 0.0f, 0.0f, -1.0f, 0.0f, 255.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f };
        colorMatrixNegative = new ColorMatrix(TintHelper.colorMatrix_Negative);
    }
    
    public static int getColorAccent(final Context context) {
        return getStyledColor(context, R.attr.colorAccent);
    }
    
    public static Drawable getColorMatrixStateListDrawable(final Context context, final Drawable drawable, final int n) {
        SelectorDrawable stateListDrawable;
        if (drawable == null) {
            stateListDrawable = null;
        }
        else {
            stateListDrawable = getStateListDrawable(drawable, getColorPrimary(context), n);
            stateListDrawable.porterDuff = true;
        }
        return (Drawable)stateListDrawable;
    }
    
    public static int getColorPrimary(final Context context) {
        return getStyledColor(context, R.attr.colorPrimary);
    }
    
    public static int getColorPrimaryDark(final Context context) {
        return getStyledColor(context, R.attr.colorPrimaryDark);
    }
    
    public static ColorStateList getInverseTintColorStateList(final Context context) {
        return getTintColorStateList(context, getTextColorPrimaryInverse(context));
    }
    
    public static Drawable getStateListDrawable(final Context context, final Drawable drawable, final int n) {
        Object stateListDrawable;
        if (drawable == null) {
            stateListDrawable = null;
        }
        else {
            stateListDrawable = getStateListDrawable(drawable, getColorPrimary(context), n);
        }
        return (Drawable)stateListDrawable;
    }
    
    private static SelectorDrawable getStateListDrawable(final Drawable drawable, final int colored, final int normal) {
        final Drawable mutate = drawable.getConstantState().newDrawable().mutate();
        final SelectorDrawable selectorDrawable = new SelectorDrawable();
        selectorDrawable.colored = colored;
        selectorDrawable.normal = normal;
        selectorDrawable.addState(new int[] { 16842919 }, mutate);
        selectorDrawable.addState(new int[] { 16842908 }, mutate);
        selectorDrawable.addState(new int[] { 16842913 }, mutate);
        selectorDrawable.addState(new int[] { 16842912 }, mutate);
        selectorDrawable.addState(new int[] { 16842914 }, mutate);
        selectorDrawable.addState(new int[] { 16843518 }, mutate);
        selectorDrawable.addState(new int[0], mutate);
        return selectorDrawable;
    }
    
    public static int getStyledColor(final Context context, final int n) {
        final TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(n, typedValue, true);
        return typedValue.data;
    }
    
    public static int getTextColorPrimary(final Context context) {
        return getStyledColor(context, 16842806);
    }
    
    public static int getTextColorPrimaryInverse(final Context context) {
        return getStyledColor(context, 16842809);
    }
    
    public static ColorStateList getTintColorStateList(final Context context) {
        return getTintColorStateList(context, getTextColorPrimary(context));
    }
    
    public static ColorStateList getTintColorStateList(final Context context, final int n) {
        return getTintColorStateList(context, getColorPrimary(context), n);
    }
    
    public static ColorStateList getTintColorStateList(final Context context, final int n, final int n2) {
        return new ColorStateList(new int[][] { { 16842919 }, { 16842908 }, { 16842913 }, { 16842912 }, { 16842914 }, { 16843518 }, new int[0] }, new int[] { n, n, n, n, n, n, n2 });
    }
    
    static void setColorFilter(final Drawable drawable, final int n) {
        final ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setScale(1.0f - Color.red(n) / 255.0f, 1.0f - Color.green(n) / 255.0f, 1.0f - Color.blue(n) / 255.0f, 1.0f);
        colorMatrix.preConcat(TintHelper.colorMatrixNegative);
        colorMatrix.postConcat(TintHelper.colorMatrixNegative);
        drawable.setColorFilter((ColorFilter)new ColorMatrixColorFilter(colorMatrix));
    }
}
