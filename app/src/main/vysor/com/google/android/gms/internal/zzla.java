// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import android.graphics.Bitmap;
import java.util.Map;

@zziy
public class zzla
{
    Map<Integer, Bitmap> zzcsx;
    private AtomicInteger zzcsy;
    
    public zzla() {
        this.zzcsx = new ConcurrentHashMap<Integer, Bitmap>();
        this.zzcsy = new AtomicInteger(0);
    }
    
    public Bitmap zza(final Integer n) {
        return this.zzcsx.get(n);
    }
    
    public int zzb(final Bitmap bitmap) {
        int andIncrement;
        if (bitmap == null) {
            zzb.zzdd("Bitmap is null. Skipping putting into the Memory Map.");
            andIncrement = -1;
        }
        else {
            this.zzcsx.put(this.zzcsy.get(), bitmap);
            andIncrement = this.zzcsy.getAndIncrement();
        }
        return andIncrement;
    }
    
    public void zzb(final Integer n) {
        this.zzcsx.remove(n);
    }
}
