// 
// Decompiled by Procyon v0.5.30
// 

package com.google.firebase;

import com.google.android.gms.common.internal.zzac;
import android.support.annotation.NonNull;

public class FirebaseException extends Exception
{
    protected FirebaseException() {
    }
    
    public FirebaseException(@NonNull final String s) {
        super(zzac.zzh(s, "Detail message must not be empty"));
    }
    
    public FirebaseException(@NonNull final String s, final Throwable t) {
        super(zzac.zzh(s, "Detail message must not be empty"), t);
    }
}
