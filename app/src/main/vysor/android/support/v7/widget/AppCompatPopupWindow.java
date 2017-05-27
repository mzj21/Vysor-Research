// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.annotation.TargetApi;
import android.view.View;
import android.support.v4.widget.PopupWindowCompat;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.support.v7.appcompat.R;
import android.support.annotation.StyleRes;
import android.support.annotation.AttrRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.support.annotation.NonNull;
import android.content.Context;
import android.os.Build$VERSION;
import android.widget.PopupWindow;

class AppCompatPopupWindow extends PopupWindow
{
    private static final boolean COMPAT_OVERLAP_ANCHOR = false;
    private static final String TAG = "AppCompatPopupWindow";
    private boolean mOverlapAnchor;
    
    public AppCompatPopupWindow(@NonNull final Context context, @Nullable final AttributeSet set, @AttrRes final int n) {
        super(context, set, n);
        this.init(context, set, n, 0);
    }
    
    public AppCompatPopupWindow(@NonNull final Context context, @Nullable final AttributeSet set, @AttrRes final int n, @StyleRes final int n2) {
        super(context, set, n, n2);
        this.init(context, set, n, n2);
    }
    
    private void init(final Context context, final AttributeSet set, final int n, final int n2) {
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, set, R.styleable.PopupWindow, n, n2);
        if (obtainStyledAttributes.hasValue(R.styleable.PopupWindow_overlapAnchor)) {
            this.setSupportOverlapAnchor(obtainStyledAttributes.getBoolean(R.styleable.PopupWindow_overlapAnchor, false));
        }
        this.setBackgroundDrawable(obtainStyledAttributes.getDrawable(R.styleable.PopupWindow_android_popupBackground));
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (n2 != 0 && sdk_INT < 11 && obtainStyledAttributes.hasValue(R.styleable.PopupWindow_android_popupAnimationStyle)) {
            this.setAnimationStyle(obtainStyledAttributes.getResourceId(R.styleable.PopupWindow_android_popupAnimationStyle, -1));
        }
        obtainStyledAttributes.recycle();
        if (Build$VERSION.SDK_INT < 14) {
            wrapOnScrollChangedListener(this);
        }
    }
    
    private static void wrapOnScrollChangedListener(final PopupWindow popupWindow) {
        try {
            final Field declaredField = PopupWindow.class.getDeclaredField("mAnchor");
            declaredField.setAccessible(true);
            final Field declaredField2 = PopupWindow.class.getDeclaredField("mOnScrollChangedListener");
            declaredField2.setAccessible(true);
            declaredField2.set(popupWindow, new ViewTreeObserver$OnScrollChangedListener() {
                final /* synthetic */ ViewTreeObserver$OnScrollChangedListener val$originalListener = (ViewTreeObserver$OnScrollChangedListener)declaredField2.get(popupWindow);
                
                public void onScrollChanged() {
                    try {
                        final WeakReference weakReference = (WeakReference)declaredField.get(popupWindow);
                        if (weakReference != null) {
                            if (weakReference.get() != null) {
                                this.val$originalListener.onScrollChanged();
                            }
                        }
                    }
                    catch (IllegalAccessException ex) {}
                }
            });
        }
        catch (Exception ex) {
            Log.d("AppCompatPopupWindow", "Exception while installing workaround OnScrollChangedListener", (Throwable)ex);
        }
    }
    
    public boolean getSupportOverlapAnchor() {
        boolean b;
        if (AppCompatPopupWindow.COMPAT_OVERLAP_ANCHOR) {
            b = this.mOverlapAnchor;
        }
        else {
            b = PopupWindowCompat.getOverlapAnchor(this);
        }
        return b;
    }
    
    public void setSupportOverlapAnchor(final boolean mOverlapAnchor) {
        if (AppCompatPopupWindow.COMPAT_OVERLAP_ANCHOR) {
            this.mOverlapAnchor = mOverlapAnchor;
        }
        else {
            PopupWindowCompat.setOverlapAnchor(this, mOverlapAnchor);
        }
    }
    
    public void showAsDropDown(final View view, final int n, int n2) {
        if (AppCompatPopupWindow.COMPAT_OVERLAP_ANCHOR && this.mOverlapAnchor) {
            n2 -= view.getHeight();
        }
        super.showAsDropDown(view, n, n2);
    }
    
    @TargetApi(19)
    public void showAsDropDown(final View view, final int n, int n2, final int n3) {
        if (AppCompatPopupWindow.COMPAT_OVERLAP_ANCHOR && this.mOverlapAnchor) {
            n2 -= view.getHeight();
        }
        super.showAsDropDown(view, n, n2, n3);
    }
    
    public void update(final View view, final int n, int n2, final int n3, final int n4) {
        if (AppCompatPopupWindow.COMPAT_OVERLAP_ANCHOR && this.mOverlapAnchor) {
            n2 -= view.getHeight();
        }
        super.update(view, n, n2, n3, n4);
    }
}
