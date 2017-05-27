// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collection;
import java.util.Arrays;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import java.util.TreeMap;
import java.util.Map;
import android.database.Cursor;
import java.util.Iterator;
import android.content.ContentResolver;
import java.util.HashSet;
import java.util.HashMap;
import java.util.regex.Pattern;
import android.net.Uri;

public class zzafz
{
    public static final Uri CONTENT_URI;
    public static final Uri aRT;
    public static final Pattern aRU;
    public static final Pattern aRV;
    static HashMap<String, String> aRW;
    private static Object aRX;
    static HashSet<String> aRY;
    
    static {
        CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
        aRT = Uri.parse("content://com.google.android.gsf.gservices/prefix");
        aRU = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
        aRV = Pattern.compile("^(0|false|f|off|no|n)$", 2);
        zzafz.aRY = new HashSet<String>();
    }
    
    public static long getLong(final ContentResolver contentResolver, final String s, long long1) {
        final String string = getString(contentResolver, s);
        if (string == null) {
            return long1;
        }
        try {
            long1 = Long.parseLong(string);
            return long1;
        }
        catch (NumberFormatException ex) {
            return long1;
        }
    }
    
    public static String getString(final ContentResolver contentResolver, final String s) {
        return zza(contentResolver, s, null);
    }
    
    public static String zza(final ContentResolver contentResolver, final String s, String s2) {
        final Object arx;
        synchronized (zzafz.class) {
            zza(contentResolver);
            arx = zzafz.aRX;
            if (zzafz.aRW.containsKey(s)) {
                final String s3 = zzafz.aRW.get(s);
                if (s3 != null) {
                    s2 = s3;
                }
                return s2;
            }
            // monitorexit(zzafz.class)
            final Iterator<String> iterator = zzafz.aRY.iterator();
            while (iterator.hasNext()) {
                if (s.startsWith(iterator.next())) {
                    return s2;
                }
            }
        }
        final Cursor query = contentResolver.query(zzafz.CONTENT_URI, (String[])null, (String)null, new String[] { s }, (String)null);
        Label_0130: {
            if (query == null) {
                break Label_0130;
            }
            try {
                if (!query.moveToFirst()) {
                    zzafz.aRW.put(s, null);
                }
                else {
                    final String string = query.getString(1);
                    synchronized (zzafz.class) {
                        if (arx == zzafz.aRX) {
                            zzafz.aRW.put(s, string);
                        }
                        // monitorexit(zzafz.class)
                        if (string != null) {
                            s2 = string;
                        }
                    }
                }
            }
            finally {
                if (query != null) {
                    query.close();
                }
            }
        }
        return s2;
    }
    
    public static Map<String, String> zza(final ContentResolver contentResolver, final String... array) {
        final Cursor query = contentResolver.query(zzafz.aRT, (String[])null, (String)null, array, (String)null);
        final TreeMap<String, String> treeMap = new TreeMap<String, String>();
        if (query != null) {
            try {
                while (query.moveToNext()) {
                    treeMap.put(query.getString(0), query.getString(1));
                }
            }
            finally {
                query.close();
            }
            query.close();
        }
        return treeMap;
    }
    
    private static void zza(final ContentResolver contentResolver) {
        if (zzafz.aRW == null) {
            zzafz.aRW = new HashMap<String, String>();
            zzafz.aRX = new Object();
            new Thread("Gservices") {
                @Override
                public void run() {
                    Looper.prepare();
                    contentResolver.registerContentObserver(zzafz.CONTENT_URI, true, (ContentObserver)new ContentObserver(new Handler(Looper.myLooper())) {
                        public void onChange(final boolean b) {
                            synchronized (zzafz.class) {
                                zzafz.aRW.clear();
                                zzafz.aRX = new Object();
                                if (!zzafz.aRY.isEmpty()) {
                                    zzafz.zzb(contentResolver, (String[])zzafz.aRY.toArray(new String[zzafz.aRY.size()]));
                                }
                            }
                        }
                    });
                    Looper.loop();
                }
            }.start();
        }
    }
    
    public static void zzb(final ContentResolver contentResolver, final String... array) {
        final Map<String, String> zza = zza(contentResolver, array);
        synchronized (zzafz.class) {
            zza(contentResolver);
            zzafz.aRY.addAll((Collection<?>)Arrays.asList(array));
            for (final Map.Entry<String, String> entry : zza.entrySet()) {
                zzafz.aRW.put(entry.getKey(), entry.getValue());
            }
        }
    }
    // monitorexit(zzafz.class)
}
