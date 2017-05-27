// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.app.Activity;

class ActivityCompatHoneycomb
{
    static void dump(final Activity activity, final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        activity.dump(s, fileDescriptor, printWriter, array);
    }
    
    static void invalidateOptionsMenu(final Activity activity) {
        activity.invalidateOptionsMenu();
    }
}
