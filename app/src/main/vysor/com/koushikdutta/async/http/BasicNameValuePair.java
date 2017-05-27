// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import android.text.TextUtils;

public class BasicNameValuePair implements NameValuePair, Cloneable
{
    private final String name;
    private final String value;
    
    public BasicNameValuePair(final String name, final String value) {
        if (name == null) {
            throw new IllegalArgumentException("Name may not be null");
        }
        this.name = name;
        this.value = value;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        boolean b2 = false;
        if (o != null) {
            if (this == o) {
                b2 = b;
            }
            else {
                final boolean b3 = o instanceof NameValuePair;
                b2 = false;
                if (b3) {
                    final BasicNameValuePair basicNameValuePair = (BasicNameValuePair)o;
                    if (!this.name.equals(basicNameValuePair.name) || !TextUtils.equals((CharSequence)this.value, (CharSequence)basicNameValuePair.value)) {
                        b = false;
                    }
                    b2 = b;
                }
            }
        }
        return b2;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public String getValue() {
        return this.value;
    }
    
    @Override
    public int hashCode() {
        return this.name.hashCode() ^ this.value.hashCode();
    }
    
    @Override
    public String toString() {
        return this.name + "=" + this.value;
    }
}
