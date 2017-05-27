// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.support.annotation.NonNull;
import android.content.Context;
import android.os.Build$VERSION;

public final class AppOpsManagerCompat
{
    private static final AppOpsManagerImpl IMPL;
    public static final int MODE_ALLOWED = 0;
    public static final int MODE_DEFAULT = 3;
    public static final int MODE_IGNORED = 1;
    
    static {
        if (Build$VERSION.SDK_INT >= 23) {
            IMPL = (AppOpsManagerImpl)new AppOpsManager23();
        }
        else {
            IMPL = new AppOpsManagerImpl();
        }
    }
    
    public static int noteOp(@NonNull final Context context, @NonNull final String s, final int n, @NonNull final String s2) {
        return AppOpsManagerCompat.IMPL.noteOp(context, s, n, s2);
    }
    
    public static int noteProxyOp(@NonNull final Context context, @NonNull final String s, @NonNull final String s2) {
        return AppOpsManagerCompat.IMPL.noteProxyOp(context, s, s2);
    }
    
    public static String permissionToOp(@NonNull final String s) {
        return AppOpsManagerCompat.IMPL.permissionToOp(s);
    }
    
    private static class AppOpsManager23 extends AppOpsManagerImpl
    {
        @Override
        public int noteOp(final Context context, final String s, final int n, final String s2) {
            return AppOpsManagerCompat23.noteOp(context, s, n, s2);
        }
        
        @Override
        public int noteProxyOp(final Context context, final String s, final String s2) {
            return AppOpsManagerCompat23.noteProxyOp(context, s, s2);
        }
        
        @Override
        public String permissionToOp(final String s) {
            return AppOpsManagerCompat23.permissionToOp(s);
        }
    }
    
    private static class AppOpsManagerImpl
    {
        public int noteOp(final Context context, final String s, final int n, final String s2) {
            return 1;
        }
        
        public int noteProxyOp(final Context context, final String s, final String s2) {
            return 1;
        }
        
        public String permissionToOp(final String s) {
            return null;
        }
    }
}
