// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import com.google.android.gms.common.zze;
import com.google.android.gms.common.internal.zzac;
import android.content.Context;
import android.os.IBinder;

public abstract class zzg<T>
{
    private final String Ot;
    private T Ou;
    
    protected zzg(final String ot) {
        this.Ot = ot;
    }
    
    protected abstract T zzc(final IBinder p0);
    
    protected final T zzcu(final Context context) throws zza {
        Label_0060: {
            if (this.Ou != null) {
                break Label_0060;
            }
            zzac.zzy(context);
            final Context remoteContext = zze.getRemoteContext(context);
            if (remoteContext == null) {
                throw new zza("Could not get remote context.");
            }
            final ClassLoader classLoader = remoteContext.getClassLoader();
            try {
                this.Ou = this.zzc((IBinder)classLoader.loadClass(this.Ot).newInstance());
                return this.Ou;
            }
            catch (ClassNotFoundException ex) {
                throw new zza("Could not load creator class.", ex);
            }
            catch (InstantiationException ex2) {
                throw new zza("Could not instantiate creator.", ex2);
            }
            catch (IllegalAccessException ex3) {
                throw new zza("Could not access creator.", ex3);
            }
        }
    }
    
    public static class zza extends Exception
    {
        public zza(final String s) {
            super(s);
        }
        
        public zza(final String s, final Throwable t) {
            super(s, t);
        }
    }
}
