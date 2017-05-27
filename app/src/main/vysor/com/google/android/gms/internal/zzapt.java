// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.text.ParseException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.sql.Date;

public final class zzapt extends zzaot<Date>
{
    public static final zzaou bmp;
    private final DateFormat bmP;
    
    static {
        bmp = new zzaou() {
            @Override
            public <T> zzaot<T> zza(final zzaob zzaob, final zzapx<T> zzapx) {
                Object o;
                if (zzapx.by() == Date.class) {
                    o = new zzapt();
                }
                else {
                    o = null;
                }
                return (zzaot<T>)o;
            }
        };
    }
    
    public zzapt() {
        this.bmP = new SimpleDateFormat("MMM d, yyyy");
    }
    
    @Override
    public void zza(final zzaqa zzaqa, final Date date) throws IOException {
        // monitorenter(this)
        Label_0019: {
            if (date != null) {
                break Label_0019;
            }
            String format = null;
            try {
                while (true) {
                    zzaqa.zzut(format);
                    return;
                    format = this.bmP.format(date);
                    continue;
                }
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    
    public Date zzm(final zzapy zzapy) throws IOException {
        synchronized (this) {
            Date date;
            if (zzapy.bn() == zzapz.bos) {
                zzapy.nextNull();
                date = null;
            }
            else {
                try {
                    date = new Date(this.bmP.parse(zzapy.nextString()).getTime());
                }
                catch (ParseException ex) {
                    throw new zzaoq(ex);
                }
            }
            return date;
        }
    }
}
