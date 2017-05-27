// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.util;

import java.util.Hashtable;

public class UntypedHashtable
{
    private Hashtable<String, Object> hash;
    
    public UntypedHashtable() {
        this.hash = new Hashtable<String, Object>();
    }
    
    public <T> T get(final String s) {
        return (T)this.hash.get(s);
    }
    
    public <T> T get(final String s, T t) {
        final T value = this.get(s);
        if (value != null) {
            t = value;
        }
        return t;
    }
    
    public void put(final String s, final Object o) {
        this.hash.put(s, o);
    }
    
    public void remove(final String s) {
        this.hash.remove(s);
    }
}
