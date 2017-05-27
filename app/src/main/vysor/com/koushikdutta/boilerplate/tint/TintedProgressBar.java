// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate.tint;

import android.graphics.PorterDuff$Mode;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.ProgressBar;

public class TintedProgressBar extends ProgressBar
{
    public TintedProgressBar(final Context context) {
        super(context);
        this.init(null);
    }
    
    public TintedProgressBar(final Context context, final AttributeSet set) {
        super(context, set);
        this.init(set);
    }
    
    public TintedProgressBar(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init(set);
    }
    
    public void init(final AttributeSet set) {
        TintHelper.getColorPrimary(this.getContext());
        int n = TintHelper.getColorAccent(this.getContext());
        if (set != null) {
            final int attributeResourceValue = set.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "tint", 0);
            if (attributeResourceValue != 0) {
                n = this.getContext().getResources().getColor(attributeResourceValue);
            }
        }
        this.getIndeterminateDrawable().setColorFilter(n, PorterDuff$Mode.SRC_IN);
        this.getProgressDrawable().setColorFilter(n, PorterDuff$Mode.SRC_IN);
    }
}
