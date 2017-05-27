// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.os.Build$VERSION;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.support.v4.view.ViewCompat;
import android.support.annotation.NonNull;
import android.graphics.drawable.Drawable;
import android.view.View;

class AppCompatBackgroundHelper
{
    private int mBackgroundResId;
    private BackgroundTintInfo mBackgroundTint;
    private final AppCompatDrawableManager mDrawableManager;
    private BackgroundTintInfo mInternalBackgroundTint;
    private BackgroundTintInfo mTmpInfo;
    private final View mView;
    
    AppCompatBackgroundHelper(final View mView) {
        this.mBackgroundResId = -1;
        this.mView = mView;
        this.mDrawableManager = AppCompatDrawableManager.get();
    }
    
    private boolean applyFrameworkTintUsingColorFilter(@NonNull final Drawable drawable) {
        boolean b = true;
        if (this.mTmpInfo == null) {
            this.mTmpInfo = new BackgroundTintInfo();
        }
        final BackgroundTintInfo mTmpInfo = this.mTmpInfo;
        mTmpInfo.clear();
        final ColorStateList backgroundTintList = ViewCompat.getBackgroundTintList(this.mView);
        if (backgroundTintList != null) {
            mTmpInfo.mHasTintList = b;
            mTmpInfo.mTintList = backgroundTintList;
        }
        final PorterDuff$Mode backgroundTintMode = ViewCompat.getBackgroundTintMode(this.mView);
        if (backgroundTintMode != null) {
            mTmpInfo.mHasTintMode = b;
            mTmpInfo.mTintMode = backgroundTintMode;
        }
        if (mTmpInfo.mHasTintList || mTmpInfo.mHasTintMode) {
            AppCompatDrawableManager.tintDrawable(drawable, mTmpInfo, this.mView.getDrawableState());
        }
        else {
            b = false;
        }
        return b;
    }
    
    private boolean updateBackgroundTint() {
        boolean b = true;
        if (this.mBackgroundTint == null || !this.mBackgroundTint.mHasTintList) {
            return false;
        }
        if (this.mBackgroundResId >= 0) {
            final ColorStateList tintList = this.mDrawableManager.getTintList(this.mView.getContext(), this.mBackgroundResId, this.mBackgroundTint.mOriginalTintList);
            if (tintList != null) {
                this.mBackgroundTint.mTintList = tintList;
                return b;
            }
        }
        if (this.mBackgroundTint.mTintList == this.mBackgroundTint.mOriginalTintList) {
            return false;
        }
        this.mBackgroundTint.mTintList = this.mBackgroundTint.mOriginalTintList;
        return b;
        b = false;
        return b;
    }
    
    void applySupportBackgroundTint() {
        final Drawable background = this.mView.getBackground();
        if (background != null && (Build$VERSION.SDK_INT != 21 || !this.applyFrameworkTintUsingColorFilter(background))) {
            if (this.mBackgroundTint != null) {
                AppCompatDrawableManager.tintDrawable(background, this.mBackgroundTint, this.mView.getDrawableState());
            }
            else if (this.mInternalBackgroundTint != null) {
                AppCompatDrawableManager.tintDrawable(background, this.mInternalBackgroundTint, this.mView.getDrawableState());
            }
        }
    }
    
    ColorStateList getSupportBackgroundTintList() {
        ColorStateList mTintList;
        if (this.mBackgroundTint != null) {
            mTintList = this.mBackgroundTint.mTintList;
        }
        else {
            mTintList = null;
        }
        return mTintList;
    }
    
    PorterDuff$Mode getSupportBackgroundTintMode() {
        PorterDuff$Mode mTintMode;
        if (this.mBackgroundTint != null) {
            mTintMode = this.mBackgroundTint.mTintMode;
        }
        else {
            mTintMode = null;
        }
        return mTintMode;
    }
    
    void loadFromAttributes(final AttributeSet set, final int n) {
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), set, R.styleable.ViewBackgroundHelper, n, 0);
        try {
            if (obtainStyledAttributes.hasValue(R.styleable.ViewBackgroundHelper_android_background)) {
                this.mBackgroundResId = obtainStyledAttributes.getResourceId(R.styleable.ViewBackgroundHelper_android_background, -1);
                final ColorStateList tintList = this.mDrawableManager.getTintList(this.mView.getContext(), this.mBackgroundResId);
                if (tintList != null) {
                    this.setInternalBackgroundTint(tintList);
                }
            }
            if (obtainStyledAttributes.hasValue(R.styleable.ViewBackgroundHelper_backgroundTint)) {
                ViewCompat.setBackgroundTintList(this.mView, obtainStyledAttributes.getColorStateList(R.styleable.ViewBackgroundHelper_backgroundTint));
            }
            if (obtainStyledAttributes.hasValue(R.styleable.ViewBackgroundHelper_backgroundTintMode)) {
                ViewCompat.setBackgroundTintMode(this.mView, DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(R.styleable.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
        }
        finally {
            obtainStyledAttributes.recycle();
        }
    }
    
    void onSetBackgroundDrawable(final Drawable drawable) {
        this.mBackgroundResId = -1;
        this.setInternalBackgroundTint(null);
        if (this.updateBackgroundTint()) {
            this.applySupportBackgroundTint();
        }
    }
    
    void onSetBackgroundResource(final int mBackgroundResId) {
        this.mBackgroundResId = mBackgroundResId;
        ColorStateList tintList;
        if (this.mDrawableManager != null) {
            tintList = this.mDrawableManager.getTintList(this.mView.getContext(), mBackgroundResId);
        }
        else {
            tintList = null;
        }
        this.setInternalBackgroundTint(tintList);
        if (this.updateBackgroundTint()) {
            this.applySupportBackgroundTint();
        }
    }
    
    void setInternalBackgroundTint(final ColorStateList mTintList) {
        if (mTintList != null) {
            if (this.mInternalBackgroundTint == null) {
                this.mInternalBackgroundTint = new BackgroundTintInfo();
            }
            this.mInternalBackgroundTint.mTintList = mTintList;
            this.mInternalBackgroundTint.mHasTintList = true;
        }
        else {
            this.mInternalBackgroundTint = null;
        }
        this.applySupportBackgroundTint();
    }
    
    void setSupportBackgroundTintList(final ColorStateList mOriginalTintList) {
        if (this.mBackgroundTint == null) {
            this.mBackgroundTint = new BackgroundTintInfo();
        }
        this.mBackgroundTint.mOriginalTintList = mOriginalTintList;
        this.mBackgroundTint.mTintList = null;
        this.mBackgroundTint.mHasTintList = true;
        if (this.updateBackgroundTint()) {
            this.applySupportBackgroundTint();
        }
    }
    
    void setSupportBackgroundTintMode(final PorterDuff$Mode mTintMode) {
        if (this.mBackgroundTint == null) {
            this.mBackgroundTint = new BackgroundTintInfo();
        }
        this.mBackgroundTint.mTintMode = mTintMode;
        this.mBackgroundTint.mHasTintMode = true;
        this.applySupportBackgroundTint();
    }
    
    private static class BackgroundTintInfo extends TintInfo
    {
        public ColorStateList mOriginalTintList;
        
        @Override
        void clear() {
            super.clear();
            this.mOriginalTintList = null;
        }
    }
}
