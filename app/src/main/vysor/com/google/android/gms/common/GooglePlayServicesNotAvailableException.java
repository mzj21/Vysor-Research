// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

public final class GooglePlayServicesNotAvailableException extends Exception
{
    public final int errorCode;
    
    public GooglePlayServicesNotAvailableException(final int errorCode) {
        this.errorCode = errorCode;
    }
}
