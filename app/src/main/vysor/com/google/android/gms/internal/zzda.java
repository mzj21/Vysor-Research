// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.os.Build$VERSION;
import android.net.Uri;
import android.content.Intent;
import android.os.Environment;
import com.google.android.gms.common.internal.zzac;
import android.content.Context;

@zziy
public class zzda
{
    private final Context mContext;
    
    public zzda(final Context mContext) {
        zzac.zzb(mContext, "Context can not be null");
        this.mContext = mContext;
    }
    
    public static boolean zzkn() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
    
    public boolean zza(final Intent intent) {
        zzac.zzb(intent, "Intent can not be null");
        final boolean empty = this.mContext.getPackageManager().queryIntentActivities(intent, 0).isEmpty();
        boolean b = false;
        if (!empty) {
            b = true;
        }
        return b;
    }
    
    public boolean zzkj() {
        final Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:"));
        return this.zza(intent);
    }
    
    public boolean zzkk() {
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("sms:"));
        return this.zza(intent);
    }
    
    public boolean zzkl() {
        return zzkn() && this.mContext.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }
    
    public boolean zzkm() {
        return true;
    }
    
    @TargetApi(14)
    public boolean zzko() {
        final Intent setType = new Intent("android.intent.action.INSERT").setType("vnd.android.cursor.dir/event");
        return Build$VERSION.SDK_INT >= 14 && this.zza(setType);
    }
}
