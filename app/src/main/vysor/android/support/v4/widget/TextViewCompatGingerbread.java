// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import java.lang.reflect.AccessibleObject;
import android.util.Log;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.TextView;
import java.lang.reflect.Field;

class TextViewCompatGingerbread
{
    private static final int LINES = 1;
    private static final String LOG_TAG = "TextViewCompatGingerbread";
    private static Field sMaxModeField;
    private static boolean sMaxModeFieldFetched;
    private static Field sMaximumField;
    private static boolean sMaximumFieldFetched;
    private static Field sMinModeField;
    private static boolean sMinModeFieldFetched;
    private static Field sMinimumField;
    private static boolean sMinimumFieldFetched;
    
    static Drawable[] getCompoundDrawablesRelative(@NonNull final TextView textView) {
        return textView.getCompoundDrawables();
    }
    
    static int getMaxLines(final TextView textView) {
        if (!TextViewCompatGingerbread.sMaxModeFieldFetched) {
            TextViewCompatGingerbread.sMaxModeField = retrieveField("mMaxMode");
            TextViewCompatGingerbread.sMaxModeFieldFetched = true;
        }
        if (TextViewCompatGingerbread.sMaxModeField == null || retrieveIntFromField(TextViewCompatGingerbread.sMaxModeField, textView) != 1) {
            return -1;
        }
        if (!TextViewCompatGingerbread.sMaximumFieldFetched) {
            TextViewCompatGingerbread.sMaximumField = retrieveField("mMaximum");
            TextViewCompatGingerbread.sMaximumFieldFetched = true;
        }
        if (TextViewCompatGingerbread.sMaximumField == null) {
            return -1;
        }
        return retrieveIntFromField(TextViewCompatGingerbread.sMaximumField, textView);
        retrieveIntFromField = -1;
        return retrieveIntFromField;
    }
    
    static int getMinLines(final TextView textView) {
        if (!TextViewCompatGingerbread.sMinModeFieldFetched) {
            TextViewCompatGingerbread.sMinModeField = retrieveField("mMinMode");
            TextViewCompatGingerbread.sMinModeFieldFetched = true;
        }
        if (TextViewCompatGingerbread.sMinModeField == null || retrieveIntFromField(TextViewCompatGingerbread.sMinModeField, textView) != 1) {
            return -1;
        }
        if (!TextViewCompatGingerbread.sMinimumFieldFetched) {
            TextViewCompatGingerbread.sMinimumField = retrieveField("mMinimum");
            TextViewCompatGingerbread.sMinimumFieldFetched = true;
        }
        if (TextViewCompatGingerbread.sMinimumField == null) {
            return -1;
        }
        return retrieveIntFromField(TextViewCompatGingerbread.sMinimumField, textView);
        retrieveIntFromField = -1;
        return retrieveIntFromField;
    }
    
    private static Field retrieveField(final String s) {
        AccessibleObject declaredField = null;
        try {
            declaredField = TextView.class.getDeclaredField(s);
            declaredField.setAccessible(true);
            return (Field)declaredField;
        }
        catch (NoSuchFieldException ex) {
            Log.e("TextViewCompatGingerbread", "Could not retrieve " + s + " field.");
            return (Field)declaredField;
        }
    }
    
    private static int retrieveIntFromField(final Field field, final TextView textView) {
        try {
            return field.getInt(textView);
        }
        catch (IllegalAccessException ex) {
            Log.d("TextViewCompatGingerbread", "Could not retrieve value of " + field.getName() + " field.");
            return -1;
        }
    }
    
    static void setTextAppearance(final TextView textView, final int n) {
        textView.setTextAppearance(textView.getContext(), n);
    }
}
