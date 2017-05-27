// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public abstract class StatsEvent extends AbstractSafeParcelable implements ReflectedParcelable
{
    public abstract int getEventType();
    
    public abstract long getTimeMillis();
    
    @Override
    public String toString() {
        final long timeMillis = this.getTimeMillis();
        final String value = String.valueOf("\t");
        final int eventType = this.getEventType();
        final String value2 = String.valueOf("\t");
        final long zzawq = this.zzawq();
        final String value3 = String.valueOf(this.zzawt());
        return new StringBuilder(51 + String.valueOf(value).length() + String.valueOf(value2).length() + String.valueOf(value3).length()).append(timeMillis).append(value).append(eventType).append(value2).append(zzawq).append(value3).toString();
    }
    
    public abstract long zzawq();
    
    public abstract String zzawt();
}
