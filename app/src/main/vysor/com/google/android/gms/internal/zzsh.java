// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import com.google.android.gms.common.util.zzs;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.ApplicationInfo;
import android.content.Context;

public class zzsh
{
    protected final Context mContext;
    
    public zzsh(final Context mContext) {
        this.mContext = mContext;
    }
    
    public int checkPermission(final String s, final String s2) {
        return this.mContext.getPackageManager().checkPermission(s, s2);
    }
    
    public ApplicationInfo getApplicationInfo(final String s, final int n) throws PackageManager$NameNotFoundException {
        return this.mContext.getPackageManager().getApplicationInfo(s, n);
    }
    
    public PackageInfo getPackageInfo(final String s, final int n) throws PackageManager$NameNotFoundException {
        return this.mContext.getPackageManager().getPackageInfo(s, n);
    }
    
    @TargetApi(19)
    public boolean zzg(final int n, final String s) {
        Label_0029: {
            if (!zzs.zzaxr()) {
                break Label_0029;
            }
            try {
                ((AppOpsManager)this.mContext.getSystemService("appops")).checkPackage(n, s);
                boolean b = true;
                Label_0026: {
                    return b;
                }
            Block_5_Outer:
                while (true) {
                    int n2 = 0;
                    while (true) {
                        Label_0058: {
                            break Label_0058;
                            Label_0089:
                            ++n2;
                            break Label_0058;
                            b = true;
                            return b;
                            return true;
                        }
                        final String[] packagesForUid;
                        final int length = packagesForUid.length;
                        b = false;
                        continue;
                    }
                    final String[] packagesForUid = this.mContext.getPackageManager().getPackagesForUid(n);
                    b = false;
                    b = false;
                    continue Block_5_Outer;
                }
            }
            // iftrue(Label_0089:, !s.equals((Object)packagesForUid[n2]))
            // iftrue(Label_0026:, n2 >= length)
            // iftrue(Label_0026:, s == null)
            // iftrue(Label_0026:, packagesForUid == null)
            catch (SecurityException ex) {
                return false;
            }
        }
    }
    
    public CharSequence zzik(final String s) throws PackageManager$NameNotFoundException {
        return this.mContext.getPackageManager().getApplicationLabel(this.mContext.getPackageManager().getApplicationInfo(s, 0));
    }
}
