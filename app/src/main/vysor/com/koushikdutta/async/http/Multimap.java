// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.net.URLDecoder;
import android.net.Uri;
import java.util.List;
import java.util.LinkedHashMap;

public class Multimap extends LinkedHashMap<String, List<String>> implements Iterable<NameValuePair>
{
    private static final StringDecoder QUERY_DECODER;
    private static final StringDecoder URL_DECODER;
    
    static {
        QUERY_DECODER = (StringDecoder)new StringDecoder() {
            @Override
            public String decode(final String s) {
                return Uri.decode(s);
            }
        };
        URL_DECODER = (StringDecoder)new StringDecoder() {
            @Override
            public String decode(final String s) {
                return URLDecoder.decode(s);
            }
        };
    }
    
    public Multimap() {
    }
    
    public Multimap(final Multimap multimap) {
        this.putAll(multimap);
    }
    
    public Multimap(final List<NameValuePair> list) {
        for (final NameValuePair nameValuePair : list) {
            this.add(nameValuePair.getName(), nameValuePair.getValue());
        }
    }
    
    public static Multimap parse(final String s, final String s2, final boolean b, final StringDecoder stringDecoder) {
        final Multimap multimap = new Multimap();
        if (s != null) {
            final String[] split = s.split(s2);
            for (int length = split.length, i = 0; i < length; ++i) {
                final String[] split2 = split[i].split("=", 2);
                String s3 = split2[0].trim();
                final int length2 = split2.length;
                String s4 = null;
                if (length2 > 1) {
                    s4 = split2[1];
                }
                if (b && s4 != null && s4.endsWith("\"") && s4.startsWith("\"")) {
                    s4 = s4.substring(1, -1 + s4.length());
                }
                if (stringDecoder != null) {
                    s3 = stringDecoder.decode(s3);
                    s4 = stringDecoder.decode(s4);
                }
                multimap.add(s3, s4);
            }
        }
        return multimap;
    }
    
    public static Multimap parseCommaDelimited(final String s) {
        return parse(s, ",", true, null);
    }
    
    public static Multimap parseQuery(final String s) {
        return parse(s, "&", false, Multimap.QUERY_DECODER);
    }
    
    public static Multimap parseSemicolonDelimited(final String s) {
        return parse(s, ";", true, null);
    }
    
    public static Multimap parseUrlEncoded(final String s) {
        return parse(s, "&", false, Multimap.URL_DECODER);
    }
    
    public void add(final String s, final String s2) {
        List<String> list = ((LinkedHashMap<K, List<String>>)this).get(s);
        if (list == null) {
            list = this.newList();
            this.put(s, list);
        }
        list.add(s2);
    }
    
    public String getString(final String s) {
        final List<String> list = ((LinkedHashMap<K, List<String>>)this).get(s);
        String s2;
        if (list == null || list.size() == 0) {
            s2 = null;
        }
        else {
            s2 = list.get(0);
        }
        return s2;
    }
    
    @Override
    public Iterator<NameValuePair> iterator() {
        final ArrayList<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        for (final String s : ((HashMap<String, V>)this).keySet()) {
            final Iterator<String> iterator2 = ((LinkedHashMap<K, List<String>>)this).get(s).iterator();
            while (iterator2.hasNext()) {
                list.add(new BasicNameValuePair(s, iterator2.next()));
            }
        }
        return (Iterator<NameValuePair>)list.iterator();
    }
    
    protected List<String> newList() {
        return new ArrayList<String>();
    }
    
    public void put(final String s, final String s2) {
        final List<String> list = this.newList();
        list.add(s2);
        this.put(s, list);
    }
    
    public interface StringDecoder
    {
        String decode(final String p0);
    }
}
