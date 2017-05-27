// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import java.text.ParseException;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.text.DateFormat;
import java.util.Date;

public final class zzapm extends zzaot<Date>
{
    public static final zzaou bmp;
    private final DateFormat bkA;
    private final DateFormat bkB;
    private final DateFormat bkz;
    
    static {
        bmp = new zzaou() {
            @Override
            public <T> zzaot<T> zza(final zzaob zzaob, final zzapx<T> zzapx) {
                Object o;
                if (zzapx.by() == Date.class) {
                    o = new zzapm();
                }
                else {
                    o = null;
                }
                return (zzaot<T>)o;
            }
        };
    }
    
    public zzapm() {
        this.bkz = DateFormat.getDateTimeInstance(2, 2, Locale.US);
        this.bkA = DateFormat.getDateTimeInstance(2, 2);
        this.bkB = bm();
    }
    
    private static DateFormat bm() {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }
    
    private Date zzur(final String s) {
        synchronized (this) {
            try {
                return this.bkA.parse(s);
            }
            catch (ParseException ex2) {
                try {
                    final Date date = this.bkz.parse(s);
                }
                catch (ParseException ex3) {
                    try {
                        final Date date = this.bkB.parse(s);
                    }
                    catch (ParseException ex) {
                        throw new zzaoq(s, ex);
                    }
                }
            }
        }
    }
    
    @Override
    public void zza(final zzaqa zzaqa, final Date date) throws IOException {
        // monitorenter(this)
        Label_0014: {
            if (date != null) {
                break Label_0014;
            }
            try {
                zzaqa.bx();
                return;
                zzaqa.zzut(this.bkz.format(date));
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    
    public Date zzk(final zzapy zzapy) throws IOException {
        Date zzur;
        if (zzapy.bn() == zzapz.bos) {
            zzapy.nextNull();
            zzur = null;
        }
        else {
            zzur = this.zzur(zzapy.nextString());
        }
        return zzur;
    }
}
