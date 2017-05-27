// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Writer;
import java.io.StringWriter;

public abstract class zzaoh
{
    public Number aQ() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }
    
    public String aR() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }
    
    public boolean aS() {
        return this instanceof zzaoe;
    }
    
    public boolean aT() {
        return this instanceof zzaok;
    }
    
    public boolean aU() {
        return this instanceof zzaon;
    }
    
    public boolean aV() {
        return this instanceof zzaoj;
    }
    
    public zzaok aW() {
        if (this.aT()) {
            return (zzaok)this;
        }
        final String value = String.valueOf(this);
        throw new IllegalStateException(new StringBuilder(19 + String.valueOf(value).length()).append("Not a JSON Object: ").append(value).toString());
    }
    
    public zzaoe aX() {
        if (this.aS()) {
            return (zzaoe)this;
        }
        throw new IllegalStateException("This is not a JSON Array.");
    }
    
    public zzaon aY() {
        if (this.aU()) {
            return (zzaon)this;
        }
        throw new IllegalStateException("This is not a JSON Primitive.");
    }
    
    Boolean aZ() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }
    
    public boolean getAsBoolean() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }
    
    public double getAsDouble() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }
    
    public int getAsInt() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }
    
    public long getAsLong() {
        throw new UnsupportedOperationException(this.getClass().getSimpleName());
    }
    
    @Override
    public String toString() {
        try {
            final StringWriter stringWriter = new StringWriter();
            final zzaqa zzaqa = new zzaqa(stringWriter);
            zzaqa.setLenient(true);
            zzapi.zzb(this, zzaqa);
            return stringWriter.toString();
        }
        catch (IOException ex) {
            throw new AssertionError((Object)ex);
        }
    }
}
