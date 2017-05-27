// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.sql.Timestamp;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.text.DateFormat;
import java.util.Date;

final class zzanw implements zzaog<Date>, zzaop<Date>
{
    private final DateFormat bkA;
    private final DateFormat bkB;
    private final DateFormat bkz;
    
    zzanw() {
        this(DateFormat.getDateTimeInstance(2, 2, Locale.US), DateFormat.getDateTimeInstance(2, 2));
    }
    
    public zzanw(final int n, final int n2) {
        this(DateFormat.getDateTimeInstance(n, n2, Locale.US), DateFormat.getDateTimeInstance(n, n2));
    }
    
    zzanw(final String s) {
        this(new SimpleDateFormat(s, Locale.US), new SimpleDateFormat(s));
    }
    
    zzanw(final DateFormat bkz, final DateFormat bkA) {
        this.bkz = bkz;
        this.bkA = bkA;
        (this.bkB = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)).setTimeZone(TimeZone.getTimeZone("UTC"));
    }
    
    private Date zza(final zzaoh zzaoh) {
        final DateFormat bkA = this.bkA;
        synchronized (bkA) {
            try {
                return this.bkA.parse(zzaoh.aR());
            }
            catch (ParseException ex2) {
                final zzanw zzanw = this;
                final DateFormat dateFormat = zzanw.bkz;
                final zzaoh zzaoh2 = zzaoh;
                final String s = zzaoh2.aR();
                final Date parse = dateFormat.parse(s);
                final Date date = parse;
                final DateFormat dateFormat2 = bkA;
                // monitorexit(dateFormat2)
                return date;
            }
        }
        try {
            final zzanw zzanw = this;
            final DateFormat dateFormat = zzanw.bkz;
            final zzaoh zzaoh2 = zzaoh;
            final String s = zzaoh2.aR();
            final Date date;
            final Date parse = date = dateFormat.parse(s);
            final DateFormat dateFormat2 = bkA;
            // monitorexit(dateFormat2)
            return date;
        }
        catch (ParseException ex3) {
            try {
                final Date date = this.bkB.parse(zzaoh.aR());
            }
            // monitorexit(bkA)
            catch (ParseException ex) {
                throw new zzaoq(zzaoh.aR(), ex);
            }
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(zzanw.class.getSimpleName());
        sb.append('(').append(this.bkA.getClass().getSimpleName()).append(')');
        return sb.toString();
    }
    
    @Override
    public zzaoh zza(final Date date, final Type type, final zzaoo zzaoo) {
        synchronized (this.bkA) {
            return new zzaon(this.bkz.format(date));
        }
    }
    
    public Date zza(final zzaoh zzaoh, final Type type, final zzaof zzaof) throws zzaol {
        if (!(zzaoh instanceof zzaon)) {
            throw new zzaol("The date should be a string value");
        }
        Date zza = this.zza(zzaoh);
        if (type != Date.class) {
            if (type == Timestamp.class) {
                zza = new Timestamp(zza.getTime());
            }
            else {
                if (type != java.sql.Date.class) {
                    final String value = String.valueOf(this.getClass());
                    final String value2 = String.valueOf(type);
                    throw new IllegalArgumentException(new StringBuilder(23 + String.valueOf(value).length() + String.valueOf(value2).length()).append(value).append(" cannot deserialize to ").append(value2).toString());
                }
                zza = new java.sql.Date(zza.getTime());
            }
        }
        return zza;
    }
}
