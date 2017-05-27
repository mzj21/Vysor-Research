// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.os;

import android.os.Build$VERSION;
import android.os.AsyncTask;

public final class AsyncTaskCompat
{
    public static <Params, Progress, Result> AsyncTask<Params, Progress, Result> executeParallel(final AsyncTask<Params, Progress, Result> asyncTask, final Params... array) {
        if (asyncTask == null) {
            throw new IllegalArgumentException("task can not be null");
        }
        if (Build$VERSION.SDK_INT >= 11) {
            AsyncTaskCompatHoneycomb.executeParallel((android.os.AsyncTask<Params, Object, Object>)asyncTask, array);
        }
        else {
            asyncTask.execute((Object[])array);
        }
        return asyncTask;
    }
}
