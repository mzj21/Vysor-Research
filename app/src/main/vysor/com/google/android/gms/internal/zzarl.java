// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Array;

public final class zzarl
{
    private static void zza(final String s, final Object o, final StringBuffer sb, final StringBuffer sb2) throws IllegalAccessException, InvocationTargetException {
        if (o != null) {
            if (o instanceof zzark) {
                final int length = sb.length();
                if (s != null) {
                    sb2.append(sb).append(zzuz(s)).append(" <\n");
                    sb.append("  ");
                }
                final Class<?> class1 = o.getClass();
                for (final Field field : class1.getFields()) {
                    final int modifiers = field.getModifiers();
                    final String name = field.getName();
                    if (!"cachedSize".equals(name) && (modifiers & 0x1) == 0x1 && (modifiers & 0x8) != 0x8 && !name.startsWith("_") && !name.endsWith("_")) {
                        final Class<?> type = field.getType();
                        final Object value = field.get(o);
                        if (type.isArray()) {
                            if (type.getComponentType() == Byte.TYPE) {
                                zza(name, value, sb, sb2);
                            }
                            else {
                                int length3;
                                if (value == null) {
                                    length3 = 0;
                                }
                                else {
                                    length3 = Array.getLength(value);
                                }
                                for (int j = 0; j < length3; ++j) {
                                    zza(name, Array.get(value, j), sb, sb2);
                                }
                            }
                        }
                        else {
                            zza(name, value, sb, sb2);
                        }
                    }
                }
                final Method[] methods = class1.getMethods();
            Label_0463:
                for (int length4 = methods.length, k = 0; k < length4; ++k) {
                    final String name2 = methods[k].getName();
                Label_0362:
                    while (true) {
                        if (!name2.startsWith("set")) {
                            break Label_0362;
                        }
                        while (true) {
                            final String substring = name2.substring(3);
                            try {
                                final String value2 = String.valueOf(substring);
                                String concat;
                                if (value2.length() != 0) {
                                    concat = "has".concat(value2);
                                }
                                else {
                                    concat = new String("has");
                                }
                                if (!(boolean)class1.getMethod(concat, (Class<?>[])new Class[0]).invoke(o, new Object[0])) {
                                    break;
                                }
                            }
                            catch (NoSuchMethodException ex) {
                                continue Label_0362;
                            }
                            try {
                                final String value3 = String.valueOf(substring);
                                String concat2;
                                if (value3.length() != 0) {
                                    concat2 = "get".concat(value3);
                                }
                                else {
                                    concat2 = new String("get");
                                }
                                zza(substring, class1.getMethod(concat2, (Class<?>[])new Class[0]).invoke(o, new Object[0]), sb, sb2);
                                continue Label_0362;
                            }
                            catch (NoSuchMethodException ex2) {
                                continue Label_0362;
                            }
                            break Label_0463;
                        }
                        break;
                    }
                }
                if (s != null) {
                    sb.setLength(length);
                    sb2.append(sb).append(">\n");
                }
            }
            else {
                sb2.append(sb).append(zzuz(s)).append(": ");
                if (o instanceof String) {
                    sb2.append("\"").append(zzgg((String)o)).append("\"");
                }
                else if (o instanceof byte[]) {
                    zza((byte[])o, sb2);
                }
                else {
                    sb2.append(o);
                }
                sb2.append("\n");
            }
        }
    }
    
    private static void zza(final byte[] array, final StringBuffer sb) {
        if (array == null) {
            sb.append("\"\"");
        }
        else {
            sb.append('\"');
            for (int i = 0; i < array.length; ++i) {
                final int n = 0xFF & array[i];
                if (n == 92 || n == 34) {
                    sb.append('\\').append((char)n);
                }
                else if (n >= 32 && n < 127) {
                    sb.append((char)n);
                }
                else {
                    sb.append(String.format("\\%03o", n));
                }
            }
            sb.append('\"');
        }
    }
    
    public static <T extends zzark> String zzg(final T t) {
        String s;
        if (t == null) {
            s = "";
        }
        else {
            final StringBuffer sb = new StringBuffer();
            try {
                zza(null, t, new StringBuffer(), sb);
                s = sb.toString();
            }
            catch (IllegalAccessException ex) {
                final String value = String.valueOf(ex.getMessage());
                if (value.length() != 0) {
                    s = "Error printing proto: ".concat(value);
                }
                else {
                    s = new String("Error printing proto: ");
                }
            }
            catch (InvocationTargetException ex2) {
                final String value2 = String.valueOf(ex2.getMessage());
                if (value2.length() != 0) {
                    s = "Error printing proto: ".concat(value2);
                }
                else {
                    s = new String("Error printing proto: ");
                }
            }
        }
        return s;
    }
    
    private static String zzgg(String concat) {
        if (!concat.startsWith("http") && concat.length() > 200) {
            concat = String.valueOf(concat.substring(0, 200)).concat("[...]");
        }
        return zzii(concat);
    }
    
    private static String zzii(final String s) {
        final int length = s.length();
        final StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 >= ' ' && char1 <= '~' && char1 != '\"' && char1 != '\'') {
                sb.append(char1);
            }
            else {
                sb.append(String.format("\\u%04x", (int)char1));
            }
        }
        return sb.toString();
    }
    
    private static String zzuz(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (i == 0) {
                sb.append(Character.toLowerCase(char1));
            }
            else if (Character.isUpperCase(char1)) {
                sb.append('_').append(Character.toLowerCase(char1));
            }
            else {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
}
