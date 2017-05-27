// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.Field;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Method;

public abstract class zzapj
{
    public static zzapj bl() {
        try {
            final Class<?> forName = Class.forName("sun.misc.Unsafe");
            final Field declaredField = forName.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            return new zzapj() {
                final /* synthetic */ Method bml = forName.getMethod("allocateInstance", Class.class);
                final /* synthetic */ Object bmm = declaredField.get(null);
                
                @Override
                public <T> T zzf(final Class<T> clazz) throws Exception {
                    return (T)this.bml.invoke(this.bmm, clazz);
                }
            };
        }
        catch (Exception ex) {
            try {
                final Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                declaredMethod.setAccessible(true);
                final int intValue = (int)declaredMethod.invoke(null, Object.class);
                final Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                declaredMethod2.setAccessible(true);
                final zzapj zzapj = new zzapj() {
                    @Override
                    public <T> T zzf(final Class<T> clazz) throws Exception {
                        return (T)declaredMethod2.invoke(null, clazz, intValue);
                    }
                };
            }
            catch (Exception ex2) {
                try {
                    final Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                    declaredMethod3.setAccessible(true);
                    final zzapj zzapj = new zzapj() {
                        @Override
                        public <T> T zzf(final Class<T> clazz) throws Exception {
                            return (T)declaredMethod3.invoke(null, clazz, Object.class);
                        }
                    };
                }
                catch (Exception ex3) {
                    final zzapj zzapj = new zzapj() {
                        @Override
                        public <T> T zzf(final Class<T> clazz) {
                            final String value = String.valueOf(clazz);
                            throw new UnsupportedOperationException(new StringBuilder(16 + String.valueOf(value).length()).append("Cannot allocate ").append(value).toString());
                        }
                    };
                }
            }
        }
    }
    
    public abstract <T> T zzf(final Class<T> p0) throws Exception;
}
