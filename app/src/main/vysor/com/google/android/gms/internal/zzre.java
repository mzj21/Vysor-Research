// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzac;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.Iterator;
import java.util.Map;
import java.util.Collections;
import java.util.WeakHashMap;
import java.util.Set;

public class zzre
{
    private final Set<zzrd<?>> pA;
    
    public zzre() {
        this.pA = Collections.newSetFromMap(new WeakHashMap<zzrd<?>, Boolean>());
    }
    
    public void release() {
        final Iterator<zzrd<?>> iterator = this.pA.iterator();
        while (iterator.hasNext()) {
            iterator.next().clear();
        }
        this.pA.clear();
    }
    
    public <L> zzrd<L> zza(@NonNull final L l, @NonNull final Looper looper, @NonNull final String s) {
        zzac.zzb(l, "Listener must not be null");
        zzac.zzb(looper, "Looper must not be null");
        zzac.zzb(s, "Listener type must not be null");
        final zzrd<Object> zzrd = new zzrd<Object>(looper, l, s);
        this.pA.add(zzrd);
        return (zzrd<L>)zzrd;
    }
    
    public <L> zzrd<L> zzb(@NonNull final L l, final Looper looper) {
        return this.zza(l, looper, "NO_TYPE");
    }
}
