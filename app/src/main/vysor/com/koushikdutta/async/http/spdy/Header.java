// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.spdy;

import java.util.Locale;

final class Header
{
    public static final ByteString RESPONSE_STATUS;
    public static final ByteString TARGET_AUTHORITY;
    public static final ByteString TARGET_HOST;
    public static final ByteString TARGET_METHOD;
    public static final ByteString TARGET_PATH;
    public static final ByteString TARGET_SCHEME;
    public static final ByteString VERSION;
    final int hpackSize;
    public final ByteString name;
    public final ByteString value;
    
    static {
        RESPONSE_STATUS = ByteString.encodeUtf8(":status");
        TARGET_METHOD = ByteString.encodeUtf8(":method");
        TARGET_PATH = ByteString.encodeUtf8(":path");
        TARGET_SCHEME = ByteString.encodeUtf8(":scheme");
        TARGET_AUTHORITY = ByteString.encodeUtf8(":authority");
        TARGET_HOST = ByteString.encodeUtf8(":host");
        VERSION = ByteString.encodeUtf8(":version");
    }
    
    public Header(final ByteString name, final ByteString value) {
        this.name = name;
        this.value = value;
        this.hpackSize = 32 + name.size() + value.size();
    }
    
    public Header(final ByteString byteString, final String s) {
        this(byteString, ByteString.encodeUtf8(s));
    }
    
    public Header(final String s, final String s2) {
        this(ByteString.encodeUtf8(s), ByteString.encodeUtf8(s2));
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = o instanceof Header;
        boolean b2 = false;
        if (b) {
            final Header header = (Header)o;
            final boolean equals = this.name.equals(header.name);
            b2 = false;
            if (equals) {
                final boolean equals2 = this.value.equals(header.value);
                b2 = false;
                if (equals2) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        return 31 * (527 + this.name.hashCode()) + this.value.hashCode();
    }
    
    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "%s: %s", this.name.utf8(), this.value.utf8());
    }
}
