// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.os;

import android.os.Trace;

class TraceJellybeanMR2
{
    public static void beginSection(final String s) {
        Trace.beginSection(s);
    }
    
    public static void endSection() {
        Trace.endSection();
    }
}
