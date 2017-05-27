// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import android.support.annotation.Nullable;
import java.io.UnsupportedEncodingException;
import com.google.android.gms.common.util.zzr;

@zziy
public class zzcx
{
    private static boolean zza(final Character.UnicodeBlock unicodeBlock) {
        return unicodeBlock == Character.UnicodeBlock.BOPOMOFO || unicodeBlock == Character.UnicodeBlock.BOPOMOFO_EXTENDED || unicodeBlock == Character.UnicodeBlock.CJK_COMPATIBILITY || unicodeBlock == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || unicodeBlock == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT || unicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || unicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || unicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || unicodeBlock == Character.UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS || unicodeBlock == Character.UnicodeBlock.HANGUL_JAMO || unicodeBlock == Character.UnicodeBlock.HANGUL_SYLLABLES || unicodeBlock == Character.UnicodeBlock.HIRAGANA || unicodeBlock == Character.UnicodeBlock.KATAKANA || unicodeBlock == Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS;
    }
    
    public static int zzae(final String s) {
        try {
            final byte[] array = s.getBytes("UTF-8");
            return zzr.zza(array, 0, array.length, 0);
        }
        catch (UnsupportedEncodingException ex) {
            final byte[] array = s.getBytes();
            return zzr.zza(array, 0, array.length, 0);
        }
    }
    
    @Nullable
    public static String[] zzaf(@Nullable final String s) {
        String[] array;
        if (s == null) {
            array = null;
        }
        else {
            final ArrayList<String> list = new ArrayList<String>();
            final char[] charArray = s.toCharArray();
            final int length = s.length();
            int n = 0;
            int n2 = 0;
            int i;
            int charCount;
            int n3;
            int n4;
            int n6;
            for (i = 0; i < length; i += charCount, n6 = n4, n2 = n3, n = n6) {
                final int codePoint = Character.codePointAt(charArray, i);
                charCount = Character.charCount(codePoint);
                if (zzm(codePoint)) {
                    if (n != 0) {
                        list.add(new String(charArray, n2, i - n2));
                    }
                    list.add(new String(charArray, i, charCount));
                    n3 = n2;
                    n4 = 0;
                }
                else if (Character.isLetterOrDigit(codePoint) || Character.getType(codePoint) == 6 || Character.getType(codePoint) == 8) {
                    if (n == 0) {
                        n2 = i;
                    }
                    n3 = n2;
                    n4 = 1;
                }
                else if (n != 0) {
                    list.add(new String(charArray, n2, i - n2));
                    n3 = n2;
                    n4 = 0;
                }
                else {
                    final int n5 = n;
                    n3 = n2;
                    n4 = n5;
                }
            }
            if (n != 0) {
                list.add(new String(charArray, n2, i - n2));
            }
            array = list.toArray(new String[list.size()]);
        }
        return array;
    }
    
    static boolean zzm(final int n) {
        return Character.isLetter(n) && (zza(Character.UnicodeBlock.of(n)) || zzo(n));
    }
    
    public static byte[] zzn(final int n) {
        final ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putInt(n);
        return allocate.array();
    }
    
    private static boolean zzo(final int n) {
        return (n >= 65382 && n <= 65437) || (n >= 65441 && n <= 65500);
    }
}
