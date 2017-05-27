// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.support.annotation.NonNull;

public class zza extends Exception
{
    protected final Status fp;
    
    public zza(@NonNull final Status fp) {
        super(fp.getStatusMessage());
        this.fp = fp;
    }
}
