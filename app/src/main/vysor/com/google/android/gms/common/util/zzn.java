// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.util;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Collections;
import java.util.Map;
import java.net.URI;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

public class zzn
{
    private static final Pattern ET;
    private static final Pattern EU;
    private static final Pattern EV;
    
    static {
        ET = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
        EU = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
        EV = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");
    }
    
    private static String decode(final String s, String s2) {
        Label_0010: {
            if (s2 == null) {
                break Label_0010;
            }
            try {
                return URLDecoder.decode(s, s2);
                s2 = "ISO-8859-1";
                return URLDecoder.decode(s, s2);
            }
            catch (UnsupportedEncodingException ex) {
                throw new IllegalArgumentException(ex);
            }
        }
    }
    
    public static Map<String, String> zza(final URI uri, final String s) {
        Map<String, String> emptyMap = Collections.emptyMap();
        final String rawQuery = uri.getRawQuery();
        if (rawQuery != null && rawQuery.length() > 0) {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            final Scanner scanner = new Scanner(rawQuery);
            scanner.useDelimiter("&");
            while (scanner.hasNext()) {
                final String[] split = scanner.next().split("=");
                if (split.length == 0 || split.length > 2) {
                    throw new IllegalArgumentException("bad parameter");
                }
                final String decode = decode(split[0], s);
                final int length = split.length;
                String decode2 = null;
                if (length == 2) {
                    decode2 = decode(split[1], s);
                }
                hashMap.put(decode, decode2);
            }
            emptyMap = hashMap;
        }
        return emptyMap;
    }
}
