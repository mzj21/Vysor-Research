// 
// Decompiled by Procyon v0.5.30
// 

package android.support.graphics.drawable;

import org.xmlpull.v1.XmlPullParser;
import android.content.res.TypedArray;

class TypedArrayUtils
{
    private static final String NAMESPACE = "http://schemas.android.com/apk/res/android";
    
    public static boolean getNamedBoolean(final TypedArray typedArray, final XmlPullParser xmlPullParser, final String s, final int n, boolean boolean1) {
        if (hasAttribute(xmlPullParser, s)) {
            boolean1 = typedArray.getBoolean(n, boolean1);
        }
        return boolean1;
    }
    
    public static int getNamedColor(final TypedArray typedArray, final XmlPullParser xmlPullParser, final String s, final int n, int color) {
        if (hasAttribute(xmlPullParser, s)) {
            color = typedArray.getColor(n, color);
        }
        return color;
    }
    
    public static float getNamedFloat(final TypedArray typedArray, final XmlPullParser xmlPullParser, final String s, final int n, float float1) {
        if (hasAttribute(xmlPullParser, s)) {
            float1 = typedArray.getFloat(n, float1);
        }
        return float1;
    }
    
    public static int getNamedInt(final TypedArray typedArray, final XmlPullParser xmlPullParser, final String s, final int n, int int1) {
        if (hasAttribute(xmlPullParser, s)) {
            int1 = typedArray.getInt(n, int1);
        }
        return int1;
    }
    
    public static boolean hasAttribute(final XmlPullParser xmlPullParser, final String s) {
        return xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", s) != null;
    }
}
