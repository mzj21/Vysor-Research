// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzac;

public class BooleanResult implements Result
{
    private final Status fp;
    private final boolean vu;
    
    public BooleanResult(final Status status, final boolean vu) {
        this.fp = zzac.zzb(status, "Status must not be null");
        this.vu = vu;
    }
    
    @Override
    public final boolean equals(final Object o) {
        boolean b = true;
        if (o != this) {
            if (!(o instanceof BooleanResult)) {
                b = false;
            }
            else {
                final BooleanResult booleanResult = (BooleanResult)o;
                if (!this.fp.equals(booleanResult.fp) || this.vu != booleanResult.vu) {
                    b = false;
                }
            }
        }
        return b;
    }
    
    @Override
    public Status getStatus() {
        return this.fp;
    }
    
    public boolean getValue() {
        return this.vu;
    }
    
    @Override
    public final int hashCode() {
        final int n = 31 * (527 + this.fp.hashCode());
        int n2;
        if (this.vu) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        return n2 + n;
    }
}
