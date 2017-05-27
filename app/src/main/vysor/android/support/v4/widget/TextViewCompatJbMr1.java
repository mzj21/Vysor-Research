// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.support.annotation.Nullable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.TextView;

class TextViewCompatJbMr1
{
    static Drawable[] getCompoundDrawablesRelative(@NonNull final TextView textView) {
        return textView.getCompoundDrawablesRelative();
    }
    
    public static void setCompoundDrawablesRelative(@NonNull final TextView textView, @Nullable Drawable drawable, @Nullable final Drawable drawable2, @Nullable final Drawable drawable3, @Nullable final Drawable drawable4) {
        int n = 1;
        if (textView.getLayoutDirection() != n) {
            n = 0;
        }
        Drawable drawable5;
        if (n != 0) {
            drawable5 = drawable3;
        }
        else {
            drawable5 = drawable;
        }
        if (n == 0) {
            drawable = drawable3;
        }
        textView.setCompoundDrawables(drawable5, drawable2, drawable, drawable4);
    }
    
    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull final TextView textView, int n, final int n2, final int n3, final int n4) {
        int n5 = 1;
        if (textView.getLayoutDirection() != n5) {
            n5 = 0;
        }
        int n6;
        if (n5 != 0) {
            n6 = n3;
        }
        else {
            n6 = n;
        }
        if (n5 == 0) {
            n = n3;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(n6, n2, n, n4);
    }
    
    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull final TextView textView, @Nullable Drawable drawable, @Nullable final Drawable drawable2, @Nullable final Drawable drawable3, @Nullable final Drawable drawable4) {
        int n = 1;
        if (textView.getLayoutDirection() != n) {
            n = 0;
        }
        Drawable drawable5;
        if (n != 0) {
            drawable5 = drawable3;
        }
        else {
            drawable5 = drawable;
        }
        if (n == 0) {
            drawable = drawable3;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(drawable5, drawable2, drawable, drawable4);
    }
}
