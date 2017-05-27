// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Collection;
import java.util.Iterator;
import android.text.TextUtils;
import java.util.Map;
import com.koushikdutta.async.util.TaggedList;
import java.util.List;

public class Headers
{
    final Multimap map;
    
    public Headers() {
        this.map = new Multimap() {
            @Override
            protected List<String> newList() {
                return new TaggedList<String>();
            }
        };
    }
    
    public Headers(final Map<String, List<String>> map) {
        (this.map = new Multimap() {
            @Override
            protected List<String> newList() {
                return new TaggedList<String>();
            }
        }).putAll(map);
    }
    
    public static Headers parse(final String s) {
        final String[] split = s.split("\n");
        final Headers headers = new Headers();
        for (int length = split.length, i = 0; i < length; ++i) {
            final String trim = split[i].trim();
            if (!TextUtils.isEmpty((CharSequence)trim)) {
                headers.addLine(trim);
            }
        }
        return headers;
    }
    
    public Headers add(final String s, final String s2) {
        final String lowerCase = s.toLowerCase();
        this.map.add(lowerCase, s2);
        ((TaggedList)this.map.get(lowerCase)).tagNull(s);
        return this;
    }
    
    public Headers addAll(final Headers headers) {
        this.map.putAll(headers.map);
        return this;
    }
    
    public Headers addAll(final String s, final List<String> list) {
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.add(s, iterator.next());
        }
        return this;
    }
    
    public Headers addAll(final Map<String, List<String>> map) {
        for (final String s : map.keySet()) {
            final Iterator<String> iterator2 = map.get(s).iterator();
            while (iterator2.hasNext()) {
                this.add(s, iterator2.next());
            }
        }
        return this;
    }
    
    public Headers addLine(final String s) {
        if (s != null) {
            final String[] split = s.trim().split(":", 2);
            if (split.length == 2) {
                this.add(split[0].trim(), split[1].trim());
            }
            else {
                this.add(split[0].trim(), "");
            }
        }
        return this;
    }
    
    public String get(final String s) {
        return this.map.getString(s.toLowerCase());
    }
    
    public List<String> getAll(final String s) {
        return ((LinkedHashMap<K, List<String>>)this.map).get(s.toLowerCase());
    }
    
    public Multimap getMultiMap() {
        return this.map;
    }
    
    public String remove(final String s) {
        final List<String> removeAll = this.removeAll(s.toLowerCase());
        String s2;
        if (removeAll == null || removeAll.size() == 0) {
            s2 = null;
        }
        else {
            s2 = removeAll.get(0);
        }
        return s2;
    }
    
    public Headers removeAll(final Collection<String> collection) {
        final Iterator<String> iterator = collection.iterator();
        while (iterator.hasNext()) {
            this.remove(iterator.next());
        }
        return this;
    }
    
    public List<String> removeAll(final String s) {
        return ((HashMap<K, List<String>>)this.map).remove(s.toLowerCase());
    }
    
    public Headers set(final String s, final String s2) {
        if (s2 != null && (s2.contains("\n") || s2.contains("\r"))) {
            throw new IllegalArgumentException("value must not contain a new line or line feed");
        }
        final String lowerCase = s.toLowerCase();
        this.map.put(lowerCase, s2);
        ((TaggedList)this.map.get(lowerCase)).tagNull(s);
        return this;
    }
    
    public String toPrefixString(final String s) {
        return this.toStringBuilder().insert(0, s + "\r\n").toString();
    }
    
    @Override
    public String toString() {
        return this.toStringBuilder().toString();
    }
    
    public StringBuilder toStringBuilder() {
        final StringBuilder sb = new StringBuilder(256);
        final Iterator<String> iterator = ((HashMap<String, V>)this.map).keySet().iterator();
        while (iterator.hasNext()) {
            final TaggedList<String> list = ((LinkedHashMap<K, TaggedList<String>>)this.map).get(iterator.next());
            final Iterator<Object> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                sb.append(list.tag()).append(": ").append(iterator2.next()).append("\r\n");
            }
        }
        sb.append("\r\n");
        return sb;
    }
}
