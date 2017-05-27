// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public final class zzany
{
    private final Field bkC;
    
    public zzany(final Field bkC) {
        zzaoz.zzy(bkC);
        this.bkC = bkC;
    }
    
    public <T extends Annotation> T getAnnotation(final Class<T> clazz) {
        return this.bkC.getAnnotation(clazz);
    }
}
