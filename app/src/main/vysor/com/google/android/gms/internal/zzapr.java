// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public final class zzapr extends zzaot<Object>
{
    public static final zzaou bmp;
    private final zzaob bll;
    
    static {
        bmp = new zzaou() {
            @Override
            public <T> zzaot<T> zza(final zzaob zzaob, final zzapx<T> zzapx) {
                Object o;
                if (zzapx.by() == Object.class) {
                    o = new zzapr(zzaob, null);
                }
                else {
                    o = null;
                }
                return (zzaot<T>)o;
            }
        };
    }
    
    private zzapr(final zzaob bll) {
        this.bll = bll;
    }
    
    @Override
    public void zza(final zzaqa zzaqa, final Object o) throws IOException {
        if (o == null) {
            zzaqa.bx();
        }
        else {
            final zzaot<?> zzk = this.bll.zzk(o.getClass());
            if (zzk instanceof zzapr) {
                zzaqa.bv();
                zzaqa.bw();
            }
            else {
                zzk.zza(zzaqa, o);
            }
        }
    }
    
    @Override
    public Object zzb(final zzapy zzapy) throws IOException {
        Object o = null;
        switch (zzapr$2.bmF[zzapy.bn().ordinal()]) {
            default: {
                throw new IllegalStateException();
            }
            case 1: {
                o = new ArrayList<Object>();
                zzapy.beginArray();
                while (zzapy.hasNext()) {
                    ((List<Object>)o).add(this.zzb(zzapy));
                }
                zzapy.endArray();
                break;
            }
            case 2: {
                o = new zzapf<String, Object>();
                zzapy.beginObject();
                while (zzapy.hasNext()) {
                    ((Map<String, Object>)o).put(zzapy.nextName(), this.zzb(zzapy));
                }
                zzapy.endObject();
                break;
            }
            case 3: {
                o = zzapy.nextString();
                break;
            }
            case 4: {
                o = zzapy.nextDouble();
                break;
            }
            case 5: {
                o = zzapy.nextBoolean();
                break;
            }
            case 6: {
                zzapy.nextNull();
                o = null;
                break;
            }
        }
        return o;
    }
}
