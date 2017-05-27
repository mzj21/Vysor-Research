// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;

@zziy
public final class zzdb
{
    private String zzbad;
    
    public zzdb() {
        this(zzdi.zzbao.zzkq());
    }
    
    public zzdb(final String s) {
        String zzbad;
        if (TextUtils.isEmpty((CharSequence)s)) {
            zzbad = zzdi.zzbao.zzkq();
        }
        else {
            zzbad = s;
        }
        this.zzbad = zzbad;
    }
    
    public String zzkp() {
        return this.zzbad;
    }
}
