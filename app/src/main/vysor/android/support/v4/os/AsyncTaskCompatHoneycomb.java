// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.os;

import android.os.AsyncTask;

class AsyncTaskCompatHoneycomb
{
    static <Params, Progress, Result> void executeParallel(final AsyncTask<Params, Progress, Result> asyncTask, final Params... array) {
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])array);
    }
}
