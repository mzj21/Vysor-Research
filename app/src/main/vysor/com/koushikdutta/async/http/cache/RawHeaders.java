// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.cache;

import java.util.Collections;
import java.util.TreeMap;
import java.util.Set;
import java.util.Collection;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

final class RawHeaders
{
    private static final Comparator<String> FIELD_NAME_COMPARATOR;
    private int httpMinorVersion;
    private final List<String> namesAndValues;
    private int responseCode;
    private String responseMessage;
    private String statusLine;
    
    static {
        FIELD_NAME_COMPARATOR = new Comparator<String>() {
            @Override
            public int compare(final String s, final String s2) {
                int compare;
                if (s == s2) {
                    compare = 0;
                }
                else if (s == null) {
                    compare = -1;
                }
                else if (s2 == null) {
                    compare = 1;
                }
                else {
                    compare = String.CASE_INSENSITIVE_ORDER.compare(s, s2);
                }
                return compare;
            }
        };
    }
    
    public RawHeaders() {
        this.namesAndValues = new ArrayList<String>(20);
        this.httpMinorVersion = 1;
        this.responseCode = -1;
    }
    
    public RawHeaders(final RawHeaders rawHeaders) {
        this.namesAndValues = new ArrayList<String>(20);
        this.httpMinorVersion = 1;
        this.responseCode = -1;
        this.copy(rawHeaders);
    }
    
    public static RawHeaders fromMultimap(final Map<String, List<String>> map) {
        final RawHeaders rawHeaders = new RawHeaders();
        for (final Map.Entry<String, List<String>> entry : map.entrySet()) {
            final String s = entry.getKey();
            final List<String> list = entry.getValue();
            if (s != null) {
                rawHeaders.addAll(s, list);
            }
            else {
                if (list.isEmpty()) {
                    continue;
                }
                rawHeaders.setStatusLine(list.get(-1 + list.size()));
            }
        }
        return rawHeaders;
    }
    
    public static RawHeaders parse(final String s) {
        final String[] split = s.split("\n");
        final RawHeaders rawHeaders = new RawHeaders();
        for (int length = split.length, i = 0; i < length; ++i) {
            final String trim = split[i].trim();
            if (!TextUtils.isEmpty((CharSequence)trim)) {
                if (rawHeaders.getStatusLine() == null) {
                    rawHeaders.setStatusLine(trim);
                }
                else {
                    rawHeaders.addLine(trim);
                }
            }
        }
        return rawHeaders;
    }
    
    public void add(final String s, final String s2) {
        if (s == null) {
            throw new IllegalArgumentException("fieldName == null");
        }
        if (s2 == null) {
            System.err.println("Ignoring HTTP header field '" + s + "' because its value is null");
        }
        else {
            this.namesAndValues.add(s);
            this.namesAndValues.add(s2.trim());
        }
    }
    
    public void addAll(final String s, final List<String> list) {
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.add(s, iterator.next());
        }
    }
    
    public void addLine(final String s) {
        final int index = s.indexOf(":");
        if (index == -1) {
            this.add("", s);
        }
        else {
            this.add(s.substring(0, index), s.substring(index + 1));
        }
    }
    
    public void copy(final RawHeaders rawHeaders) {
        this.namesAndValues.addAll(rawHeaders.namesAndValues);
        this.statusLine = rawHeaders.statusLine;
        this.httpMinorVersion = rawHeaders.httpMinorVersion;
        this.responseCode = rawHeaders.responseCode;
        this.responseMessage = rawHeaders.responseMessage;
    }
    
    public String get(final String s) {
        for (int i = -2 + this.namesAndValues.size(); i >= 0; i -= 2) {
            if (s.equalsIgnoreCase(this.namesAndValues.get(i))) {
                return this.namesAndValues.get(i + 1);
            }
        }
        return null;
    }
    
    public RawHeaders getAll(final Set<String> set) {
        final RawHeaders rawHeaders = new RawHeaders();
        for (int i = 0; i < this.namesAndValues.size(); i += 2) {
            final String s = this.namesAndValues.get(i);
            if (set.contains(s)) {
                rawHeaders.add(s, this.namesAndValues.get(i + 1));
            }
        }
        return rawHeaders;
    }
    
    public String getFieldName(final int n) {
        final int n2 = n * 2;
        String s;
        if (n2 < 0 || n2 >= this.namesAndValues.size()) {
            s = null;
        }
        else {
            s = this.namesAndValues.get(n2);
        }
        return s;
    }
    
    public int getHttpMinorVersion() {
        int httpMinorVersion;
        if (this.httpMinorVersion != -1) {
            httpMinorVersion = this.httpMinorVersion;
        }
        else {
            httpMinorVersion = 1;
        }
        return httpMinorVersion;
    }
    
    public int getResponseCode() {
        return this.responseCode;
    }
    
    public String getResponseMessage() {
        return this.responseMessage;
    }
    
    public String getStatusLine() {
        return this.statusLine;
    }
    
    public String getValue(final int n) {
        final int n2 = 1 + n * 2;
        String s;
        if (n2 < 0 || n2 >= this.namesAndValues.size()) {
            s = null;
        }
        else {
            s = this.namesAndValues.get(n2);
        }
        return s;
    }
    
    public int length() {
        return this.namesAndValues.size() / 2;
    }
    
    public void removeAll(final String s) {
        for (int i = 0; i < this.namesAndValues.size(); i += 2) {
            if (s.equalsIgnoreCase(this.namesAndValues.get(i))) {
                this.namesAndValues.remove(i);
                this.namesAndValues.remove(i);
            }
        }
    }
    
    public void set(final String s, final String s2) {
        this.removeAll(s);
        this.add(s, s2);
    }
    
    public void setStatusLine(final String s) {
        final String trim = s.trim();
        this.statusLine = trim;
        if (trim != null && trim.startsWith("HTTP/")) {
            final String trim2 = trim.trim();
            final int n = 1 + trim2.indexOf(" ");
            if (n != 0) {
                if (trim2.charAt(n - 2) != '1') {
                    this.httpMinorVersion = 0;
                }
                int length = n + 3;
                if (length > trim2.length()) {
                    length = trim2.length();
                }
                this.responseCode = Integer.parseInt(trim2.substring(n, length));
                if (length + 1 <= trim2.length()) {
                    this.responseMessage = trim2.substring(length + 1);
                }
            }
        }
    }
    
    public String toHeaderString() {
        final StringBuilder sb = new StringBuilder(256);
        sb.append(this.statusLine).append("\r\n");
        for (int i = 0; i < this.namesAndValues.size(); i += 2) {
            sb.append(this.namesAndValues.get(i)).append(": ").append(this.namesAndValues.get(i + 1)).append("\r\n");
        }
        sb.append("\r\n");
        return sb.toString();
    }
    
    public Map<String, List<String>> toMultimap() {
        final TreeMap<Object, Object> treeMap = new TreeMap<Object, Object>((Comparator<? super Object>)RawHeaders.FIELD_NAME_COMPARATOR);
        for (int i = 0; i < this.namesAndValues.size(); i += 2) {
            final String s = this.namesAndValues.get(i);
            final String s2 = this.namesAndValues.get(i + 1);
            final ArrayList<String> list = new ArrayList<String>();
            final List<? extends T> list2 = treeMap.get(s);
            if (list2 != null) {
                list.addAll((Collection<?>)list2);
            }
            list.add(s2);
            treeMap.put(s, Collections.unmodifiableList((List<?>)list));
        }
        if (this.statusLine != null) {
            treeMap.put(null, Collections.unmodifiableList((List<?>)Collections.singletonList((T)this.statusLine)));
        }
        return Collections.unmodifiableMap((Map<? extends String, ? extends List<String>>)treeMap);
    }
}
