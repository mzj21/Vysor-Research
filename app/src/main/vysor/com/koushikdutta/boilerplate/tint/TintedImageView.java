// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate.tint;

import android.util.AttributeSet;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class TintedImageView extends ImageView
{
    Drawable original;
    int stateListFilter;
    
    public TintedImageView(final Context context) {
        super(context);
        this.init(null);
    }
    
    public TintedImageView(final Context context, final AttributeSet set) {
        super(context, set);
        this.init(set);
    }
    
    public TintedImageView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init(set);
    }
    
    private void init(final AttributeSet set) {
        int attributeIntValue = 0;
        if (set != null) {
            attributeIntValue = set.getAttributeIntValue("http://schemas.android.com/apk/res-auto", "drawableFilterMode", 0);
        }
        this.stateListFilter = attributeIntValue;
        Drawable imageDrawable;
        if (this.original != null) {
            imageDrawable = this.original;
        }
        else {
            imageDrawable = this.getDrawable();
        }
        this.setImageDrawable(imageDrawable);
    }
    
    public void setImageDrawable(Drawable drawable) {
        this.original = drawable;
        if (this.stateListFilter != 0) {
            if (this.stateListFilter == 1) {
                drawable = TintHelper.getStateListDrawable(this.getContext(), drawable, TintHelper.getTextColorPrimary(this.getContext()));
            }
            else if (this.stateListFilter == 2) {
                drawable = TintHelper.getStateListDrawable(this.getContext(), drawable, TintHelper.getTextColorPrimaryInverse(this.getContext()));
            }
            else if (this.stateListFilter == 3) {
                drawable = TintHelper.getColorMatrixStateListDrawable(this.getContext(), drawable, TintHelper.getTextColorPrimary(this.getContext()));
            }
            else {
                drawable = TintHelper.getColorMatrixStateListDrawable(this.getContext(), drawable, TintHelper.getTextColorPrimaryInverse(this.getContext()));
            }
        }
        super.setImageDrawable(drawable);
    }
    
    public void setImageResource(final int n) {
        this.setImageDrawable(this.getContext().getResources().getDrawable(n));
    }
    
    public void setStateListFilter(final StateListFilter stateListFilter) {
        if (this.stateListFilter != stateListFilter.ordinal()) {
            this.stateListFilter = stateListFilter.ordinal();
            this.setImageDrawable(this.original);
        }
    }
    
    public enum StateListFilter
    {
        ColorFilter, 
        Inverse, 
        None, 
        Normal;
    }
}
