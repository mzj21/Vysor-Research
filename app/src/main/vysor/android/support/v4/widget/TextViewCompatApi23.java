// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.support.annotation.StyleRes;
import android.support.annotation.NonNull;
import android.widget.TextView;

class TextViewCompatApi23
{
    public static void setTextAppearance(@NonNull final TextView textView, @StyleRes final int textAppearance) {
        textView.setTextAppearance(textAppearance);
    }
}
