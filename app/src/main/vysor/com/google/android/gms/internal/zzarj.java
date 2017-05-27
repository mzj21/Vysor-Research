// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public class zzarj extends IOException
{
    public zzarj(final String s) {
        super(s);
    }
    
    static zzarj cT() {
        return new zzarj("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
    }
    
    static zzarj cU() {
        return new zzarj("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }
    
    static zzarj cV() {
        return new zzarj("CodedInputStream encountered a malformed varint.");
    }
    
    static zzarj cW() {
        return new zzarj("Protocol message contained an invalid tag (zero).");
    }
    
    static zzarj cX() {
        return new zzarj("Protocol message end-group tag did not match expected tag.");
    }
    
    static zzarj cY() {
        return new zzarj("Protocol message tag had invalid wire type.");
    }
    
    static zzarj cZ() {
        return new zzarj("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }
}
