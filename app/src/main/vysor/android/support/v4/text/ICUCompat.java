// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.text;

import java.util.Locale;
import android.os.Build$VERSION;

public final class ICUCompat
{
    private static final ICUCompatImpl IMPL;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 21) {
            IMPL = (ICUCompatImpl)new ICUCompatImplLollipop();
        }
        else if (sdk_INT >= 14) {
            IMPL = (ICUCompatImpl)new ICUCompatImplIcs();
        }
        else {
            IMPL = (ICUCompatImpl)new ICUCompatImplBase();
        }
    }
    
    public static String maximizeAndGetScript(final Locale locale) {
        return ICUCompat.IMPL.maximizeAndGetScript(locale);
    }
    
    interface ICUCompatImpl
    {
        String maximizeAndGetScript(final Locale p0);
    }
    
    static class ICUCompatImplBase implements ICUCompatImpl
    {
        @Override
        public String maximizeAndGetScript(final Locale locale) {
            return null;
        }
    }
    
    static class ICUCompatImplIcs implements ICUCompatImpl
    {
        @Override
        public String maximizeAndGetScript(final Locale locale) {
            return ICUCompatIcs.maximizeAndGetScript(locale);
        }
    }
    
    static class ICUCompatImplLollipop implements ICUCompatImpl
    {
        @Override
        public String maximizeAndGetScript(final Locale locale) {
            return ICUCompatApi23.maximizeAndGetScript(locale);
        }
    }
}
