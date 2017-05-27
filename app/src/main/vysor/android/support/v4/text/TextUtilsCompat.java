// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.text;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Build$VERSION;
import java.util.Locale;

public final class TextUtilsCompat
{
    static String ARAB_SCRIPT_SUBTAG;
    static String HEBR_SCRIPT_SUBTAG;
    private static final TextUtilsCompatImpl IMPL;
    public static final Locale ROOT;
    
    static {
        if (Build$VERSION.SDK_INT >= 17) {
            IMPL = (TextUtilsCompatImpl)new TextUtilsCompatJellybeanMr1Impl();
        }
        else {
            IMPL = new TextUtilsCompatImpl();
        }
        ROOT = new Locale("", "");
        TextUtilsCompat.ARAB_SCRIPT_SUBTAG = "Arab";
        TextUtilsCompat.HEBR_SCRIPT_SUBTAG = "Hebr";
    }
    
    public static int getLayoutDirectionFromLocale(@Nullable final Locale locale) {
        return TextUtilsCompat.IMPL.getLayoutDirectionFromLocale(locale);
    }
    
    @NonNull
    public static String htmlEncode(@NonNull final String s) {
        return TextUtilsCompat.IMPL.htmlEncode(s);
    }
    
    private static class TextUtilsCompatImpl
    {
        private static int getLayoutDirectionFromFirstChar(@NonNull final Locale locale) {
            final byte directionality = Character.getDirectionality(locale.getDisplayName(locale).charAt(0));
            int n = 0;
            switch (directionality) {
                case 1:
                case 2: {
                    n = 1;
                    break;
                }
            }
            return n;
        }
        
        public int getLayoutDirectionFromLocale(@Nullable final Locale locale) {
            if (locale == null || locale.equals(TextUtilsCompat.ROOT)) {
                return 0;
            }
            final String maximizeAndGetScript = ICUCompat.maximizeAndGetScript(locale);
            int layoutDirectionFromFirstChar;
            if (maximizeAndGetScript == null) {
                layoutDirectionFromFirstChar = getLayoutDirectionFromFirstChar(locale);
            }
            else {
                if (!maximizeAndGetScript.equalsIgnoreCase(TextUtilsCompat.ARAB_SCRIPT_SUBTAG) && !maximizeAndGetScript.equalsIgnoreCase(TextUtilsCompat.HEBR_SCRIPT_SUBTAG)) {
                    return 0;
                }
                layoutDirectionFromFirstChar = 1;
            }
            return layoutDirectionFromFirstChar;
            layoutDirectionFromFirstChar = 0;
            return layoutDirectionFromFirstChar;
        }
        
        @NonNull
        public String htmlEncode(@NonNull final String s) {
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); ++i) {
                final char char1 = s.charAt(i);
                switch (char1) {
                    default: {
                        sb.append(char1);
                        break;
                    }
                    case 60: {
                        sb.append("&lt;");
                        break;
                    }
                    case 62: {
                        sb.append("&gt;");
                        break;
                    }
                    case 38: {
                        sb.append("&amp;");
                        break;
                    }
                    case 39: {
                        sb.append("&#39;");
                        break;
                    }
                    case 34: {
                        sb.append("&quot;");
                        break;
                    }
                }
            }
            return sb.toString();
        }
    }
    
    private static class TextUtilsCompatJellybeanMr1Impl extends TextUtilsCompatImpl
    {
        @Override
        public int getLayoutDirectionFromLocale(@Nullable final Locale locale) {
            return TextUtilsCompatJellybeanMr1.getLayoutDirectionFromLocale(locale);
        }
        
        @NonNull
        @Override
        public String htmlEncode(@NonNull final String s) {
            return TextUtilsCompatJellybeanMr1.htmlEncode(s);
        }
    }
}
