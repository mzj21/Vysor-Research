// 
// Decompiled by Procyon v0.5.30
// 

package android.support.graphics.drawable;

import android.graphics.PorterDuff$Mode;
import android.graphics.Region;
import android.graphics.Rect;
import android.graphics.ColorFilter;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.annotation.TargetApi;
import android.support.v4.graphics.drawable.TintAwareDrawable;
import android.graphics.drawable.Drawable;

@TargetApi(21)
abstract class VectorDrawableCommon extends Drawable implements TintAwareDrawable
{
    Drawable mDelegateDrawable;
    
    static TypedArray obtainAttributes(final Resources resources, final Resources$Theme resources$Theme, final AttributeSet set, final int[] array) {
        TypedArray typedArray;
        if (resources$Theme == null) {
            typedArray = resources.obtainAttributes(set, array);
        }
        else {
            typedArray = resources$Theme.obtainStyledAttributes(set, array, 0, 0);
        }
        return typedArray;
    }
    
    public void applyTheme(final Resources$Theme resources$Theme) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.applyTheme(this.mDelegateDrawable, resources$Theme);
        }
    }
    
    public void clearColorFilter() {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.clearColorFilter();
        }
        else {
            super.clearColorFilter();
        }
    }
    
    public ColorFilter getColorFilter() {
        ColorFilter colorFilter;
        if (this.mDelegateDrawable != null) {
            colorFilter = DrawableCompat.getColorFilter(this.mDelegateDrawable);
        }
        else {
            colorFilter = null;
        }
        return colorFilter;
    }
    
    public Drawable getCurrent() {
        Drawable drawable;
        if (this.mDelegateDrawable != null) {
            drawable = this.mDelegateDrawable.getCurrent();
        }
        else {
            drawable = super.getCurrent();
        }
        return drawable;
    }
    
    public int getLayoutDirection() {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.getLayoutDirection(this.mDelegateDrawable);
        }
        return 0;
    }
    
    public int getMinimumHeight() {
        int n;
        if (this.mDelegateDrawable != null) {
            n = this.mDelegateDrawable.getMinimumHeight();
        }
        else {
            n = super.getMinimumHeight();
        }
        return n;
    }
    
    public int getMinimumWidth() {
        int n;
        if (this.mDelegateDrawable != null) {
            n = this.mDelegateDrawable.getMinimumWidth();
        }
        else {
            n = super.getMinimumWidth();
        }
        return n;
    }
    
    public boolean getPadding(final Rect rect) {
        boolean b;
        if (this.mDelegateDrawable != null) {
            b = this.mDelegateDrawable.getPadding(rect);
        }
        else {
            b = super.getPadding(rect);
        }
        return b;
    }
    
    public int[] getState() {
        int[] array;
        if (this.mDelegateDrawable != null) {
            array = this.mDelegateDrawable.getState();
        }
        else {
            array = super.getState();
        }
        return array;
    }
    
    public Region getTransparentRegion() {
        Region region;
        if (this.mDelegateDrawable != null) {
            region = this.mDelegateDrawable.getTransparentRegion();
        }
        else {
            region = super.getTransparentRegion();
        }
        return region;
    }
    
    public boolean isAutoMirrored() {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.isAutoMirrored(this.mDelegateDrawable);
        }
        return false;
    }
    
    public void jumpToCurrentState() {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.jumpToCurrentState(this.mDelegateDrawable);
        }
    }
    
    protected void onBoundsChange(final Rect bounds) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setBounds(bounds);
        }
        else {
            super.onBoundsChange(bounds);
        }
    }
    
    protected boolean onLevelChange(final int level) {
        boolean b;
        if (this.mDelegateDrawable != null) {
            b = this.mDelegateDrawable.setLevel(level);
        }
        else {
            b = super.onLevelChange(level);
        }
        return b;
    }
    
    public void setAutoMirrored(final boolean b) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setAutoMirrored(this.mDelegateDrawable, b);
        }
    }
    
    public void setChangingConfigurations(final int n) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setChangingConfigurations(n);
        }
        else {
            super.setChangingConfigurations(n);
        }
    }
    
    public void setColorFilter(final int n, final PorterDuff$Mode porterDuff$Mode) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setColorFilter(n, porterDuff$Mode);
        }
        else {
            super.setColorFilter(n, porterDuff$Mode);
        }
    }
    
    public void setFilterBitmap(final boolean filterBitmap) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setFilterBitmap(filterBitmap);
        }
    }
    
    public void setHotspot(final float n, final float n2) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setHotspot(this.mDelegateDrawable, n, n2);
        }
    }
    
    public void setHotspotBounds(final int n, final int n2, final int n3, final int n4) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setHotspotBounds(this.mDelegateDrawable, n, n2, n3, n4);
        }
    }
    
    public boolean setState(final int[] array) {
        boolean b;
        if (this.mDelegateDrawable != null) {
            b = this.mDelegateDrawable.setState(array);
        }
        else {
            b = super.setState(array);
        }
        return b;
    }
}
