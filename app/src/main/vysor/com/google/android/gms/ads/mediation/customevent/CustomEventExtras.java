// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation.customevent;

import java.util.HashMap;
import com.google.ads.mediation.NetworkExtras;

@Deprecated
public final class CustomEventExtras implements NetworkExtras
{
    private final HashMap<String, Object> zzcxn;
    
    public CustomEventExtras() {
        this.zzcxn = new HashMap<String, Object>();
    }
    
    public Object getExtra(final String s) {
        return this.zzcxn.get(s);
    }
    
    public void setExtra(final String s, final Object o) {
        this.zzcxn.put(s, o);
    }
}
