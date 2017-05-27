// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.cache;

final class HeaderParser
{
    public static void parseCacheControl(final String s, final CacheControlHandler cacheControlHandler) {
        if (s != null) {
            int i = 0;
            while (i < s.length()) {
                final int n = i;
                final int skipUntil = skipUntil(s, i, "=,");
                final String trim = s.substring(n, skipUntil).trim();
                if (skipUntil == s.length() || s.charAt(skipUntil) == ',') {
                    i = skipUntil + 1;
                    cacheControlHandler.handle(trim, null);
                }
                else {
                    final int skipWhitespace = skipWhitespace(s, skipUntil + 1);
                    String s2;
                    if (skipWhitespace < s.length() && s.charAt(skipWhitespace) == '\"') {
                        final int n2 = skipWhitespace + 1;
                        final int skipUntil2 = skipUntil(s, n2, "\"");
                        s2 = s.substring(n2, skipUntil2);
                        i = skipUntil2 + 1;
                    }
                    else {
                        i = skipUntil(s, skipWhitespace, ",");
                        s2 = s.substring(skipWhitespace, i).trim();
                    }
                    cacheControlHandler.handle(trim, s2);
                }
            }
        }
    }
    
    public static int parseSeconds(final String s) {
        try {
            final long long1 = Long.parseLong(s);
            int n;
            if (long1 > 2147483647L) {
                n = Integer.MAX_VALUE;
            }
            else if (long1 < 0L) {
                n = 0;
            }
            else {
                n = (int)long1;
            }
            return n;
        }
        catch (NumberFormatException ex) {
            return -1;
        }
    }
    
    private static int skipUntil(final String s, int n, final String s2) {
        while (n < s.length() && s2.indexOf(s.charAt(n)) == -1) {
            ++n;
        }
        return n;
    }
    
    private static int skipWhitespace(final String s, int i) {
        while (i < s.length()) {
            final char char1 = s.charAt(i);
            if (char1 != ' ' && char1 != '\t') {
                break;
            }
            ++i;
        }
        return i;
    }
    
    public interface CacheControlHandler
    {
        void handle(final String p0, final String p1);
    }
}
