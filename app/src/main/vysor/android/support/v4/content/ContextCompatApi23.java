// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import android.content.res.ColorStateList;
import android.content.Context;

class ContextCompatApi23
{
    public static int getColor(final Context context, final int n) {
        return context.getColor(n);
    }
    
    public static ColorStateList getColorStateList(final Context context, final int n) {
        return context.getColorStateList(n);
    }
}
