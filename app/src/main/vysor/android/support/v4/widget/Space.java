// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.graphics.Canvas;
import android.view.View$MeasureSpec;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;

public class Space extends View
{
    public Space(final Context context) {
        this(context, null);
    }
    
    public Space(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public Space(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        if (this.getVisibility() == 0) {
            this.setVisibility(4);
        }
    }
    
    private static int getDefaultSize2(final int n, final int n2) {
        int min = n;
        final int mode = View$MeasureSpec.getMode(n2);
        final int size = View$MeasureSpec.getSize(n2);
        switch (mode) {
            case 0: {
                min = n;
                break;
            }
            case Integer.MIN_VALUE: {
                min = Math.min(n, size);
                break;
            }
            case 1073741824: {
                min = size;
                break;
            }
        }
        return min;
    }
    
    public void draw(final Canvas canvas) {
    }
    
    protected void onMeasure(final int n, final int n2) {
        this.setMeasuredDimension(getDefaultSize2(this.getSuggestedMinimumWidth(), n), getDefaultSize2(this.getSuggestedMinimumHeight(), n2));
    }
}
