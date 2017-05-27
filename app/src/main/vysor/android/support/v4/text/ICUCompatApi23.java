// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.text;

import java.lang.reflect.InvocationTargetException;
import android.util.Log;
import java.util.Locale;
import java.lang.reflect.Method;

class ICUCompatApi23
{
    private static final String TAG = "ICUCompatIcs";
    private static Method sAddLikelySubtagsMethod;
    
    static {
        try {
            ICUCompatApi23.sAddLikelySubtagsMethod = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", Locale.class);
        }
        catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
    
    public static String maximizeAndGetScript(final Locale locale) {
        try {
            return ((Locale)ICUCompatApi23.sAddLikelySubtagsMethod.invoke(null, locale)).getScript();
        }
        catch (InvocationTargetException ex) {
            Log.w("ICUCompatIcs", (Throwable)ex);
        }
        catch (IllegalAccessException ex2) {
            Log.w("ICUCompatIcs", (Throwable)ex2);
            goto Label_0042;
        }
    }
}
