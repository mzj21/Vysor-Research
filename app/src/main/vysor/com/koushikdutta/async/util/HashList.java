// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.util;

import java.util.Set;
import java.util.ArrayList;
import java.util.Hashtable;

public class HashList<T>
{
    Hashtable<String, TaggedList<T>> internal;
    
    public HashList() {
        this.internal = new Hashtable<String, TaggedList<T>>();
    }
    
    public void add(final String s, final T t) {
        synchronized (this) {
            ArrayList<T> value = this.get(s);
            if (value == null) {
                this.internal.put(s, (TaggedList<T>)(value = new TaggedList<T>()));
            }
            value.add(t);
        }
    }
    
    public boolean contains(final String s) {
        synchronized (this) {
            final ArrayList<T> value = this.get(s);
            return value != null && value.size() > 0;
        }
    }
    
    public ArrayList<T> get(final String s) {
        synchronized (this) {
            return this.internal.get(s);
        }
    }
    
    public Set<String> keySet() {
        return this.internal.keySet();
    }
    
    public T pop(final String s) {
        synchronized (this) {
            final TaggedList<T> list = this.internal.get(s);
            Object remove = null;
            if (list != null) {
                final int size = list.size();
                remove = null;
                if (size != 0) {
                    remove = list.remove(-1 + list.size());
                }
            }
            return (T)remove;
        }
    }
    
    public ArrayList<T> remove(final String s) {
        synchronized (this) {
            return this.internal.remove(s);
        }
    }
    
    public boolean removeItem(final String s, final T t) {
        synchronized (this) {
            final TaggedList<T> list = this.internal.get(s);
            boolean b = false;
            if (list != null) {
                list.remove(t);
                final int size = list.size();
                b = false;
                if (size == 0) {
                    b = true;
                }
            }
            return b;
        }
    }
    
    public int size() {
        synchronized (this) {
            return this.internal.size();
        }
    }
    
    public <V> V tag(final String s) {
        synchronized (this) {
            final TaggedList<T> list = this.internal.get(s);
            Object tag;
            if (list == null) {
                tag = null;
            }
            else {
                tag = list.tag();
            }
            return (V)tag;
        }
    }
    
    public <V> void tag(final String s, final V v) {
        synchronized (this) {
            TaggedList<T> list = this.internal.get(s);
            if (list == null) {
                list = new TaggedList<T>();
                this.internal.put(s, list);
            }
            list.tag(v);
        }
    }
}
