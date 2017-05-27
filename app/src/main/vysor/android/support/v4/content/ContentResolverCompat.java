// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import android.support.v4.os.OperationCanceledException;
import android.database.Cursor;
import android.support.v4.os.CancellationSignal;
import android.net.Uri;
import android.content.ContentResolver;
import android.os.Build$VERSION;

public final class ContentResolverCompat
{
    private static final ContentResolverCompatImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 16) {
            IMPL = (ContentResolverCompatImpl)new ContentResolverCompatImplJB();
        }
        else {
            IMPL = (ContentResolverCompatImpl)new ContentResolverCompatImplBase();
        }
    }
    
    public static Cursor query(final ContentResolver contentResolver, final Uri uri, final String[] array, final String s, final String[] array2, final String s2, final CancellationSignal cancellationSignal) {
        return ContentResolverCompat.IMPL.query(contentResolver, uri, array, s, array2, s2, cancellationSignal);
    }
    
    interface ContentResolverCompatImpl
    {
        Cursor query(final ContentResolver p0, final Uri p1, final String[] p2, final String p3, final String[] p4, final String p5, final CancellationSignal p6);
    }
    
    static class ContentResolverCompatImplBase implements ContentResolverCompatImpl
    {
        @Override
        public Cursor query(final ContentResolver contentResolver, final Uri uri, final String[] array, final String s, final String[] array2, final String s2, final CancellationSignal cancellationSignal) {
            if (cancellationSignal != null) {
                cancellationSignal.throwIfCanceled();
            }
            return contentResolver.query(uri, array, s, array2, s2);
        }
    }
    
    static class ContentResolverCompatImplJB extends ContentResolverCompatImplBase
    {
        @Override
        public Cursor query(final ContentResolver contentResolver, final Uri uri, final String[] array, final String s, final String[] array2, final String s2, final CancellationSignal cancellationSignal) {
            Label_0031: {
                if (cancellationSignal == null) {
                    break Label_0031;
                }
                try {
                    Object cancellationSignalObject = cancellationSignal.getCancellationSignalObject();
                    return ContentResolverCompatJellybean.query(contentResolver, uri, array, s, array2, s2, cancellationSignalObject);
                    cancellationSignalObject = null;
                    return ContentResolverCompatJellybean.query(contentResolver, uri, array, s, array2, s2, cancellationSignalObject);
                }
                catch (Exception ex) {
                    if (ContentResolverCompatJellybean.isFrameworkOperationCanceledException(ex)) {
                        throw new OperationCanceledException();
                    }
                    throw ex;
                }
            }
        }
    }
}
