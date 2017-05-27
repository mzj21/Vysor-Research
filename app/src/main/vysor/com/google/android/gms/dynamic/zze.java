// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import java.lang.reflect.Field;
import android.os.IBinder;

public final class zze<T> extends zzd.zza
{
    private final T mWrappedObject;
    
    private zze(final T mWrappedObject) {
        this.mWrappedObject = mWrappedObject;
    }
    
    public static <T> zzd zzac(final T t) {
        return new zze<Object>(t);
    }
    
    public static <T> T zzae(final zzd zzd) {
        Object o;
        if (zzd instanceof zze) {
            o = ((zze)zzd).mWrappedObject;
        }
        else {
            final IBinder binder = zzd.asBinder();
            final Field[] declaredFields = binder.getClass().getDeclaredFields();
            if (declaredFields.length == 1) {
                final Field field = declaredFields[0];
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                    try {
                        o = field.get(binder);
                        return (T)o;
                    }
                    catch (NullPointerException ex) {
                        throw new IllegalArgumentException("Binder object is null.", ex);
                    }
                    catch (IllegalAccessException ex2) {
                        throw new IllegalArgumentException("Could not access the field in remoteBinder.", ex2);
                    }
                }
                throw new IllegalArgumentException("IObjectWrapper declared field not private!");
            }
            throw new IllegalArgumentException(new StringBuilder(64).append("Unexpected number of IObjectWrapper declared fields: ").append(declaredFields.length).toString());
        }
        return (T)o;
    }
}
