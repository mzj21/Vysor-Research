// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.util;

import java.util.regex.Matcher;
import android.text.TextUtils;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.regex.Pattern;

public final class zzp
{
    private static final Pattern EW;
    private static final Pattern EX;
    
    static {
        EW = Pattern.compile("\\\\.");
        EX = Pattern.compile("[\\\\\"/\b\f\n\r\t]");
    }
    
    public static boolean zzf(final Object o, final Object o2) {
        boolean equals;
        if (o == null && o2 == null) {
            equals = true;
        }
        else {
            equals = false;
            if (o != null) {
                equals = false;
                if (o2 != null) {
                    Label_0152: {
                        if (!(o instanceof JSONObject) || !(o2 instanceof JSONObject)) {
                            break Label_0152;
                        }
                        final JSONObject jsonObject = (JSONObject)o;
                        final JSONObject jsonObject2 = (JSONObject)o2;
                        final int length = jsonObject.length();
                        final int length2 = jsonObject2.length();
                        equals = false;
                        if (length != length2) {
                            return equals;
                        }
                        final Iterator keys = jsonObject.keys();
                        String s;
                        boolean has;
                        int n;
                        JSONArray jsonArray;
                        JSONArray jsonArray2;
                        int length3;
                        int length4;
                        boolean zzf;
                        Block_14_Outer:Label_0202_Outer:
                        while (true) {
                            Label_0147: {
                                if (!keys.hasNext()) {
                                    break Label_0147;
                                }
                                s = keys.next();
                                has = jsonObject2.has(s);
                                equals = false;
                                if (!has) {
                                    return equals;
                                }
                                try {
                                    if (!zzf(jsonObject.get(s), jsonObject2.get(s))) {
                                        equals = false;
                                        return equals;
                                    }
                                    continue Block_14_Outer;
                                    equals = true;
                                    return equals;
                                    // iftrue(Label_0242:, n >= jsonArray.length())
                                    // iftrue(Label_0010:, length3 != length4)
                                    while (true) {
                                        Block_15: {
                                            while (true) {
                                                while (true) {
                                                    n = 0;
                                                    break Block_15;
                                                    jsonArray = (JSONArray)o;
                                                    jsonArray2 = (JSONArray)o2;
                                                    length3 = jsonArray.length();
                                                    length4 = jsonArray2.length();
                                                    equals = false;
                                                    continue Label_0202_Outer;
                                                }
                                                continue;
                                            }
                                        }
                                        try {
                                            zzf = zzf(jsonArray.get(n), jsonArray2.get(n));
                                            equals = false;
                                            if (zzf) {
                                                ++n;
                                                continue;
                                            }
                                            return equals;
                                            Label_0247: {
                                                equals = o.equals(o2);
                                            }
                                            return equals;
                                            Label_0242:
                                            equals = true;
                                        }
                                        catch (JSONException ex) {
                                            equals = false;
                                        }
                                        break;
                                    }
                                }
                                // iftrue(Label_0247:, !o instanceof JSONArray || !o2 instanceof JSONArray)
                                catch (JSONException ex2) {
                                    equals = false;
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
        Label_0010: {
            return equals;
        }
    }
    
    public static String zzii(String string) {
        if (!TextUtils.isEmpty((CharSequence)string)) {
            final Matcher matcher = zzp.EX.matcher(string);
            StringBuffer sb = null;
            while (matcher.find()) {
                if (sb == null) {
                    sb = new StringBuffer();
                }
                switch (matcher.group().charAt(0)) {
                    default: {
                        continue;
                    }
                    case '\b': {
                        matcher.appendReplacement(sb, "\\\\b");
                        continue;
                    }
                    case '\"': {
                        matcher.appendReplacement(sb, "\\\\\\\"");
                        continue;
                    }
                    case '\\': {
                        matcher.appendReplacement(sb, "\\\\\\\\");
                        continue;
                    }
                    case '/': {
                        matcher.appendReplacement(sb, "\\\\/");
                        continue;
                    }
                    case '\f': {
                        matcher.appendReplacement(sb, "\\\\f");
                        continue;
                    }
                    case '\n': {
                        matcher.appendReplacement(sb, "\\\\n");
                        continue;
                    }
                    case '\r': {
                        matcher.appendReplacement(sb, "\\\\r");
                        continue;
                    }
                    case '\t': {
                        matcher.appendReplacement(sb, "\\\\t");
                        continue;
                    }
                }
            }
            if (sb != null) {
                matcher.appendTail(sb);
                string = sb.toString();
            }
        }
        return string;
    }
}
