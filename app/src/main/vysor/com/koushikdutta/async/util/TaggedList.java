// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.util;

import java.util.ArrayList;

public class TaggedList<T> extends ArrayList<T>
{
    private Object tag;
    
    public <V> V tag() {
        synchronized (this) {
            return (V)this.tag;
        }
    }
    
    public <V> void tag(final V tag) {
        synchronized (this) {
            this.tag = tag;
        }
    }
    
    public <V> void tagNull(final V tag) {
        synchronized (this) {
            if (this.tag == null) {
                this.tag = tag;
            }
        }
    }
}
