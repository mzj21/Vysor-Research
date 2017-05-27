// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.os.Build$VERSION;
import android.support.annotation.NonNull;
import android.widget.ListView;

public final class ListViewCompat
{
    public static void scrollListBy(@NonNull final ListView listView, final int n) {
        if (Build$VERSION.SDK_INT >= 19) {
            ListViewCompatKitKat.scrollListBy(listView, n);
        }
        else {
            ListViewCompatGingerbread.scrollListBy(listView, n);
        }
    }
}
