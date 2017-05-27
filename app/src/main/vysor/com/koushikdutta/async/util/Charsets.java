// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.util;

import java.nio.charset.Charset;

public class Charsets
{
    public static final Charset ISO_8859_1;
    public static final Charset US_ASCII;
    public static final Charset UTF_8;
    
    static {
        US_ASCII = Charset.forName("US-ASCII");
        UTF_8 = Charset.forName("UTF-8");
        ISO_8859_1 = Charset.forName("ISO-8859-1");
    }
}
