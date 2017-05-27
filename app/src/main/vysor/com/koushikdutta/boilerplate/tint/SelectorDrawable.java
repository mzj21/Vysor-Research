// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate.tint;

import android.graphics.drawable.Drawable;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.StateListDrawable;

class SelectorDrawable extends StateListDrawable
{
    int colored;
    int normal;
    boolean porterDuff;
    
    protected boolean onStateChange(final int[] array) {
        boolean b = false;
        for (int length = array.length, i = 0; i < length; ++i) {
            switch (array[i]) {
                case 16842908:
                case 16842912:
                case 16842913:
                case 16842914:
                case 16842919:
                case 16843518: {
                    b = true;
                    break;
                }
            }
        }
        if (this.porterDuff) {
            if (!b) {
                this.setColorFilter(this.normal, PorterDuff$Mode.SRC_IN);
            }
            else {
                this.setColorFilter(this.colored, PorterDuff$Mode.SRC_IN);
            }
        }
        else if (!b) {
            TintHelper.setColorFilter((Drawable)this, this.normal);
        }
        else {
            TintHelper.setColorFilter((Drawable)this, this.colored);
        }
        return super.onStateChange(array);
    }
}
