// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.security;

import android.content.Intent;
import android.os.AsyncTask;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import android.util.Log;
import com.google.android.gms.common.zze;
import com.google.android.gms.common.internal.zzac;
import android.content.Context;
import java.lang.reflect.Method;
import com.google.android.gms.common.zzc;

public class ProviderInstaller
{
    public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
    private static final zzc azV;
    private static Method azW;
    private static final Object zzaok;
    
    static {
        azV = zzc.zzapd();
        zzaok = new Object();
        ProviderInstaller.azW = null;
    }
    
    public static void installIfNeeded(final Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        zzac.zzb(context, "Context must not be null");
        ProviderInstaller.azV.zzbp(context);
        final Context remoteContext = zze.getRemoteContext(context);
        if (remoteContext == null) {
            Log.e("ProviderInstaller", "Failed to get remote context");
            throw new GooglePlayServicesNotAvailableException(8);
        }
        while (true) {
            while (true) {
                synchronized (ProviderInstaller.zzaok) {
                    try {
                        if (ProviderInstaller.azW == null) {
                            zzdy(remoteContext);
                        }
                        ProviderInstaller.azW.invoke(null, remoteContext);
                        return;
                    }
                    catch (Exception ex) {
                        final String value = String.valueOf(ex.getMessage());
                        if (value.length() != 0) {
                            final String concat = "Failed to install provider: ".concat(value);
                            Log.e("ProviderInstaller", concat);
                            throw new GooglePlayServicesNotAvailableException(8);
                        }
                    }
                }
                final String concat = new String("Failed to install provider: ");
                continue;
            }
        }
    }
    
    public static void installIfNeededAsync(final Context context, final ProviderInstallListener providerInstallListener) {
        zzac.zzb(context, "Context must not be null");
        zzac.zzb(providerInstallListener, "Listener must not be null");
        zzac.zzhq("Must be called on the UI thread");
        new AsyncTask<Void, Void, Integer>() {
            protected Integer zzb(final Void... array) {
                try {
                    ProviderInstaller.installIfNeeded(context);
                    return 0;
                }
                catch (GooglePlayServicesRepairableException ex) {
                    return ex.getConnectionStatusCode();
                }
                catch (GooglePlayServicesNotAvailableException ex2) {
                    return ex2.errorCode;
                }
            }
            
            protected void zzg(final Integer n) {
                if (n == 0) {
                    providerInstallListener.onProviderInstalled();
                }
                else {
                    providerInstallListener.onProviderInstallFailed(n, ProviderInstaller.azV.zza(context, n, "pi"));
                }
            }
        }.execute((Object[])new Void[0]);
    }
    
    private static void zzdy(final Context context) throws ClassNotFoundException, NoSuchMethodException {
        ProviderInstaller.azW = context.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl").getMethod("insertProvider", Context.class);
    }
    
    public interface ProviderInstallListener
    {
        void onProviderInstallFailed(final int p0, final Intent p1);
        
        void onProviderInstalled();
    }
}
