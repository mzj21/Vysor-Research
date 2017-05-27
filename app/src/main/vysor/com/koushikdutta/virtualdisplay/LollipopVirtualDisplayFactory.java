// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.virtualdisplay;

import android.hardware.display.VirtualDisplay$Callback;
import android.os.Handler;
import android.view.Surface;
import android.media.projection.MediaProjection;
import android.annotation.TargetApi;

@TargetApi(21)
public class LollipopVirtualDisplayFactory implements VirtualDisplayFactory
{
    MediaProjection mediaProjection;
    
    public LollipopVirtualDisplayFactory(final MediaProjection mediaProjection) {
        this.mediaProjection = mediaProjection;
    }
    
    @Override
    public VirtualDisplay createVirtualDisplay(final String s, final int n, final int n2, final int n3, final int n4, final Surface surface, final Handler handler) {
        return new VirtualDisplay() {
            final /* synthetic */ android.hardware.display.VirtualDisplay val$vd = LollipopVirtualDisplayFactory.this.mediaProjection.createVirtualDisplay(s, n, n2, n3, n4, surface, (VirtualDisplay$Callback)null, handler);
            
            @Override
            public void release() {
                this.val$vd.release();
            }
        };
    }
    
    @Override
    public void release() {
        this.mediaProjection.stop();
    }
}
