// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.net.Uri;
import android.widget.ImageView;

public final class zzru extends ImageView
{
    private Uri AT;
    private int AU;
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
    }
    
    public int zzatp() {
        return this.AU;
    }
    
    public void zzgj(final int au) {
        this.AU = au;
    }
    
    public void zzq(final Uri at) {
        this.AT = at;
    }
}
