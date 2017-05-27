// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.virtualdisplay;

import android.os.Handler;
import android.view.Surface;

public interface VirtualDisplayFactory
{
    VirtualDisplay createVirtualDisplay(final String p0, final int p1, final int p2, final int p3, final int p4, final Surface p5, final Handler p6);
    
    void release();
}
