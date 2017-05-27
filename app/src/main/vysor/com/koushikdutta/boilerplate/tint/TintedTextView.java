// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate.tint;

import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;

public class TintedTextView extends TextView
{
    public TintedTextView(final Context context) {
        super(context);
        this.init();
    }
    
    public TintedTextView(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public TintedTextView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    private void init() {
        this.setTextColor(TintHelper.getTintColorStateList(this.getContext()));
    }
}
