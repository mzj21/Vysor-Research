// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.database;

import android.text.TextUtils;

public final class DatabaseUtilsCompat
{
    public static String[] appendSelectionArgs(final String[] array, final String[] array2) {
        String[] array3;
        if (array == null || array.length == 0) {
            array3 = array2;
        }
        else {
            array3 = new String[array.length + array2.length];
            System.arraycopy(array, 0, array3, 0, array.length);
            System.arraycopy(array2, 0, array3, array.length, array2.length);
        }
        return array3;
    }
    
    public static String concatenateWhere(final String s, String string) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            if (TextUtils.isEmpty((CharSequence)string)) {
                string = s;
            }
            else {
                string = "(" + s + ") AND (" + string + ")";
            }
        }
        return string;
    }
}
