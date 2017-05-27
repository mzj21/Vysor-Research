// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collection;

public class zzux
{
    private final Collection<zzuw> zzbah;
    private final Collection<zzuw.zzd> zzbai;
    private final Collection<zzuw.zzd> zzbaj;
    
    public zzux() {
        this.zzbah = new ArrayList<zzuw>();
        this.zzbai = new ArrayList<zzuw.zzd>();
        this.zzbaj = new ArrayList<zzuw.zzd>();
    }
    
    public static void initialize(final Context context) {
        zzva.zzbhn().initialize(context);
    }
    
    public void zza(final zzuw zzuw) {
        this.zzbah.add(zzuw);
    }
}
