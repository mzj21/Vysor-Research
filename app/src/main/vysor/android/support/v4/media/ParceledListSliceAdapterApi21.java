// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import java.lang.reflect.InvocationTargetException;
import android.media.browse.MediaBrowser$MediaItem;
import java.util.List;
import java.lang.reflect.Constructor;

class ParceledListSliceAdapterApi21
{
    private static Constructor sConstructor;
    
    static {
        try {
            ParceledListSliceAdapterApi21.sConstructor = Class.forName("android.content.pm.ParceledListSlice").getConstructor(List.class);
        }
        catch (ClassNotFoundException ex) {}
        catch (NoSuchMethodException ex2) {
            goto Label_0022;
        }
    }
    
    static Object newInstance(final List<MediaBrowser$MediaItem> list) {
        try {
            return ParceledListSliceAdapterApi21.sConstructor.newInstance(list);
        }
        catch (InstantiationException ex) {}
        catch (IllegalAccessException ex2) {
            goto Label_0020;
        }
        catch (InvocationTargetException ex2) {
            goto Label_0020;
        }
    }
}
