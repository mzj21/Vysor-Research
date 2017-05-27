// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.text;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.support.annotation.Nullable;
import java.util.Locale;

class TextUtilsCompatJellybeanMr1
{
    public static int getLayoutDirectionFromLocale(@Nullable final Locale locale) {
        return TextUtils.getLayoutDirectionFromLocale(locale);
    }
    
    @NonNull
    public static String htmlEncode(@NonNull final String s) {
        return TextUtils.htmlEncode(s);
    }
}
