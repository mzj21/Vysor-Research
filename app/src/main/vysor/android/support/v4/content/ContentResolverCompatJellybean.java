// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import android.os.CancellationSignal;
import android.database.Cursor;
import android.net.Uri;
import android.content.ContentResolver;
import android.os.OperationCanceledException;

class ContentResolverCompatJellybean
{
    static boolean isFrameworkOperationCanceledException(final Exception ex) {
        return ex instanceof OperationCanceledException;
    }
    
    public static Cursor query(final ContentResolver contentResolver, final Uri uri, final String[] array, final String s, final String[] array2, final String s2, final Object o) {
        return contentResolver.query(uri, array, s, array2, s2, (CancellationSignal)o);
    }
}
