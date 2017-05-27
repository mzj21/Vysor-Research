// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import android.support.v4.app.AppOpsManagerCompat;
import android.os.Process;
import android.os.Binder;
import android.support.annotation.NonNull;
import android.content.Context;

public final class PermissionChecker
{
    public static final int PERMISSION_DENIED = -1;
    public static final int PERMISSION_DENIED_APP_OP = -2;
    public static final int PERMISSION_GRANTED;
    
    public static int checkCallingOrSelfPermission(@NonNull final Context context, @NonNull final String s) {
        String packageName;
        if (Binder.getCallingPid() == Process.myPid()) {
            packageName = context.getPackageName();
        }
        else {
            packageName = null;
        }
        return checkPermission(context, s, Binder.getCallingPid(), Binder.getCallingUid(), packageName);
    }
    
    public static int checkCallingPermission(@NonNull final Context context, @NonNull final String s, final String s2) {
        int checkPermission;
        if (Binder.getCallingPid() == Process.myPid()) {
            checkPermission = -1;
        }
        else {
            checkPermission = checkPermission(context, s, Binder.getCallingPid(), Binder.getCallingUid(), s2);
        }
        return checkPermission;
    }
    
    public static int checkPermission(@NonNull final Context context, @NonNull final String s, final int n, final int n2, String s2) {
        int n3 = -1;
        if (context.checkPermission(s, n, n2) != n3) {
            final String permissionToOp = AppOpsManagerCompat.permissionToOp(s);
            if (permissionToOp == null) {
                n3 = 0;
            }
            else {
                if (s2 == null) {
                    final String[] packagesForUid = context.getPackageManager().getPackagesForUid(n2);
                    if (packagesForUid == null || packagesForUid.length <= 0) {
                        return n3;
                    }
                    s2 = packagesForUid[0];
                }
                if (AppOpsManagerCompat.noteProxyOp(context, permissionToOp, s2) != 0) {
                    n3 = -2;
                }
                else {
                    n3 = 0;
                }
            }
        }
        return n3;
    }
    
    public static int checkSelfPermission(@NonNull final Context context, @NonNull final String s) {
        return checkPermission(context, s, Process.myPid(), Process.myUid(), context.getPackageName());
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface PermissionResult {
    }
}
