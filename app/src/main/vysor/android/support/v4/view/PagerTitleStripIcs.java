// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.View;
import android.content.Context;
import java.util.Locale;
import android.text.method.SingleLineTransformationMethod;
import android.text.method.TransformationMethod;
import android.widget.TextView;

class PagerTitleStripIcs
{
    public static void setSingleLineAllCaps(final TextView textView) {
        textView.setTransformationMethod((TransformationMethod)new SingleLineAllCapsTransform(textView.getContext()));
    }
    
    private static class SingleLineAllCapsTransform extends SingleLineTransformationMethod
    {
        private static final String TAG = "SingleLineAllCapsTransform";
        private Locale mLocale;
        
        public SingleLineAllCapsTransform(final Context context) {
            this.mLocale = context.getResources().getConfiguration().locale;
        }
        
        public CharSequence getTransformation(final CharSequence charSequence, final View view) {
            final CharSequence transformation = super.getTransformation(charSequence, view);
            String upperCase;
            if (transformation != null) {
                upperCase = transformation.toString().toUpperCase(this.mLocale);
            }
            else {
                upperCase = null;
            }
            return upperCase;
        }
    }
}
