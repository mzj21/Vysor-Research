// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v7.content.res.AppCompatResources;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build$VERSION;
import android.widget.ImageView;

public class AppCompatImageHelper
{
    private final ImageView mView;
    
    public AppCompatImageHelper(final ImageView mView) {
        this.mView = mView;
    }
    
    boolean hasOverlappingRendering() {
        final Drawable background = this.mView.getBackground();
        return Build$VERSION.SDK_INT < 21 || !(background instanceof RippleDrawable);
    }
    
    public void loadFromAttributes(final AttributeSet set, final int n) {
        TintTypedArray obtainStyledAttributes = null;
        try {
            Drawable imageDrawable = this.mView.getDrawable();
            obtainStyledAttributes = null;
            if (imageDrawable == null) {
                obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), set, R.styleable.AppCompatImageView, n, 0);
                final int resourceId = obtainStyledAttributes.getResourceId(R.styleable.AppCompatImageView_srcCompat, -1);
                if (resourceId != -1) {
                    imageDrawable = AppCompatResources.getDrawable(this.mView.getContext(), resourceId);
                    if (imageDrawable != null) {
                        this.mView.setImageDrawable(imageDrawable);
                    }
                }
            }
            if (imageDrawable != null) {
                DrawableUtils.fixDrawable(imageDrawable);
            }
        }
        finally {
            if (obtainStyledAttributes != null) {
                obtainStyledAttributes.recycle();
            }
        }
    }
    
    public void setImageResource(final int n) {
        if (n != 0) {
            final Drawable drawable = AppCompatResources.getDrawable(this.mView.getContext(), n);
            if (drawable != null) {
                DrawableUtils.fixDrawable(drawable);
            }
            this.mView.setImageDrawable(drawable);
        }
        else {
            this.mView.setImageDrawable((Drawable)null);
        }
    }
}
