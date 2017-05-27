// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.content.Context;
import com.google.android.gms.common.zze;
import android.os.RemoteException;
import android.util.Log;
import android.os.Binder;
import android.accounts.Account;

public class zza extends zzr.zza
{
    int AV;
    
    public static Account zza(final zzr zzr) {
        Account account = null;
        if (zzr == null) {
            return account;
        }
        final long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            account = zzr.getAccount();
            return account;
        }
        catch (RemoteException ex) {
            Log.w("AccountAccessor", "Remote account accessor probably died");
            Binder.restoreCallingIdentity(clearCallingIdentity);
            account = null;
            return account;
        }
        finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }
    
    public boolean equals(final Object o) {
        boolean equals;
        if (this == o) {
            equals = true;
        }
        else if (!(o instanceof zza)) {
            equals = false;
        }
        else {
            final zza zza = (zza)o;
            equals = null.equals((Object)null);
        }
        return equals;
    }
    
    public Account getAccount() {
        final int callingUid = Binder.getCallingUid();
        if (callingUid != this.AV) {
            if (!zze.zzf(null, callingUid)) {
                throw new SecurityException("Caller is not GooglePlayServices");
            }
            this.AV = callingUid;
        }
        return null;
    }
}
