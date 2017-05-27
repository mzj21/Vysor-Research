// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate;

import android.graphics.drawable.Drawable;
import android.view.View$MeasureSpec;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.ImageView;

public class ResizableImageView extends ImageView
{
    public ResizableImageView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    protected void onMeasure(final int n, final int n2) {
        final Drawable drawable = this.getDrawable();
        if (drawable != null) {
            final int size = View$MeasureSpec.getSize(n);
            this.setMeasuredDimension(size, (int)Math.ceil(size * drawable.getIntrinsicHeight() / drawable.getIntrinsicWidth()));
        }
        else {
            super.onMeasure(n, n2);
        }
    }
}
