// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import android.os.Build$VERSION;
import java.util.concurrent.Executor;

public final class ParallelExecutorCompat
{
    public static Executor getParallelExecutor() {
        Executor executor;
        if (Build$VERSION.SDK_INT >= 11) {
            executor = ExecutorCompatHoneycomb.getParallelExecutor();
        }
        else {
            executor = ModernAsyncTask.THREAD_POOL_EXECUTOR;
        }
        return executor;
    }
}
