// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.content.Intent;

public class GooglePlayServicesRepairableException extends UserRecoverableException
{
    private final int fF;
    
    GooglePlayServicesRepairableException(final int ff, final String s, final Intent intent) {
        super(s, intent);
        this.fF = ff;
    }
    
    public int getConnectionStatusCode() {
        return this.fF;
    }
}
