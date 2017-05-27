// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.formats;

import android.net.Uri;
import android.graphics.drawable.Drawable;

public abstract class NativeAd
{
    protected abstract Object zzdl();
    
    public abstract static class Image
    {
        public abstract Drawable getDrawable();
        
        public abstract double getScale();
        
        public abstract Uri getUri();
    }
}
