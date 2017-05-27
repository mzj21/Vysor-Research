// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import java.io.Serializable;

public class ProtocolVersion implements Serializable, Cloneable
{
    private static final long serialVersionUID = 8950662842175091068L;
    protected final int major;
    protected final int minor;
    protected final String protocol;
    
    public ProtocolVersion(final String protocol, final int major, final int minor) {
        if (protocol == null) {
            throw new IllegalArgumentException("Protocol name must not be null.");
        }
        if (major < 0) {
            throw new IllegalArgumentException("Protocol major version number must not be negative.");
        }
        if (minor < 0) {
            throw new IllegalArgumentException("Protocol minor version number may not be negative");
        }
        this.protocol = protocol;
        this.major = major;
        this.minor = minor;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    public int compareToVersion(final ProtocolVersion protocolVersion) {
        if (protocolVersion == null) {
            throw new IllegalArgumentException("Protocol version must not be null.");
        }
        if (!this.protocol.equals(protocolVersion.protocol)) {
            throw new IllegalArgumentException("Versions for different protocols cannot be compared. " + this + " " + protocolVersion);
        }
        int n = this.getMajor() - protocolVersion.getMajor();
        if (n == 0) {
            n = this.getMinor() - protocolVersion.getMinor();
        }
        return n;
    }
    
    @Override
    public final boolean equals(final Object o) {
        boolean b = true;
        if (this != o) {
            if (!(o instanceof ProtocolVersion)) {
                b = false;
            }
            else {
                final ProtocolVersion protocolVersion = (ProtocolVersion)o;
                if (!this.protocol.equals(protocolVersion.protocol) || this.major != protocolVersion.major || this.minor != protocolVersion.minor) {
                    b = false;
                }
            }
        }
        return b;
    }
    
    public ProtocolVersion forVersion(final int n, final int n2) {
        if (n != this.major || n2 != this.minor) {
            this = new ProtocolVersion(this.protocol, n, n2);
        }
        return this;
    }
    
    public final int getMajor() {
        return this.major;
    }
    
    public final int getMinor() {
        return this.minor;
    }
    
    public final String getProtocol() {
        return this.protocol;
    }
    
    public final boolean greaterEquals(final ProtocolVersion protocolVersion) {
        return this.isComparable(protocolVersion) && this.compareToVersion(protocolVersion) >= 0;
    }
    
    @Override
    public final int hashCode() {
        return this.protocol.hashCode() ^ 100000 * this.major ^ this.minor;
    }
    
    public boolean isComparable(final ProtocolVersion protocolVersion) {
        return protocolVersion != null && this.protocol.equals(protocolVersion.protocol);
    }
    
    public final boolean lessEquals(final ProtocolVersion protocolVersion) {
        return this.isComparable(protocolVersion) && this.compareToVersion(protocolVersion) <= 0;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.protocol);
        sb.append('/');
        sb.append(Integer.toString(this.major));
        sb.append('.');
        sb.append(Integer.toString(this.minor));
        return sb.toString();
    }
}
