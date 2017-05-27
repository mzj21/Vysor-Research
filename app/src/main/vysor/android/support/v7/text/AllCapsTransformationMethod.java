// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.text;

import android.graphics.Rect;
import android.view.View;
import android.content.Context;
import java.util.Locale;
import android.text.method.TransformationMethod;

public class AllCapsTransformationMethod implements TransformationMethod
{
    private Locale mLocale;
    
    public AllCapsTransformationMethod(final Context context) {
        this.mLocale = context.getResources().getConfiguration().locale;
    }
    
    public CharSequence getTransformation(final CharSequence charSequence, final View view) {
        String upperCase;
        if (charSequence != null) {
            upperCase = charSequence.toString().toUpperCase(this.mLocale);
        }
        else {
            upperCase = null;
        }
        return upperCase;
    }
    
    public void onFocusChanged(final View view, final CharSequence charSequence, final boolean b, final int n, final Rect rect) {
    }
}
