// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.text.ParseException;
import java.util.Date;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.sql.Time;

public final class zzapu extends zzaot<Time>
{
    public static final zzaou bmp;
    private final DateFormat bmP;
    
    static {
        bmp = new zzaou() {
            @Override
            public <T> zzaot<T> zza(final zzaob zzaob, final zzapx<T> zzapx) {
                Object o;
                if (zzapx.by() == Time.class) {
                    o = new zzapu();
                }
                else {
                    o = null;
                }
                return (zzaot<T>)o;
            }
        };
    }
    
    public zzapu() {
        this.bmP = new SimpleDateFormat("hh:mm:ss a");
    }
    
    @Override
    public void zza(final zzaqa zzaqa, final Time time) throws IOException {
        // monitorenter(this)
        Label_0019: {
            if (time != null) {
                break Label_0019;
            }
            String format = null;
            try {
                while (true) {
                    zzaqa.zzut(format);
                    return;
                    format = this.bmP.format(time);
                    continue;
                }
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    
    public Time zzn(final zzapy zzapy) throws IOException {
        synchronized (this) {
            Time time;
            if (zzapy.bn() == zzapz.bos) {
                zzapy.nextNull();
                time = null;
            }
            else {
                try {
                    time = new Time(this.bmP.parse(zzapy.nextString()).getTime());
                }
                catch (ParseException ex) {
                    throw new zzaoq(ex);
                }
            }
            return time;
        }
    }
}
