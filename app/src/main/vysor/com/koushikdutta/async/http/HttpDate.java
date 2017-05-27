// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.text.DateFormat;

public final class HttpDate
{
    private static final String[] BROWSER_COMPATIBLE_DATE_FORMATS;
    private static final ThreadLocal<DateFormat> STANDARD_DATE_FORMAT;
    
    static {
        STANDARD_DATE_FORMAT = new ThreadLocal<DateFormat>() {
            @Override
            protected DateFormat initialValue() {
                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                return simpleDateFormat;
            }
        };
        BROWSER_COMPATIBLE_DATE_FORMATS = new String[] { "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z", "EEE MMM d yyyy HH:mm:ss z" };
    }
    
    public static String format(final Date date) {
        return HttpDate.STANDARD_DATE_FORMAT.get().format(date);
    }
    
    public static Date parse(final String s) {
        Date date;
        if (s == null) {
            date = null;
        }
        else {
            try {
                date = HttpDate.STANDARD_DATE_FORMAT.get().parse(s);
            }
            catch (ParseException ex) {
                final String[] browser_COMPATIBLE_DATE_FORMATS = HttpDate.BROWSER_COMPATIBLE_DATE_FORMATS;
                final int length = browser_COMPATIBLE_DATE_FORMATS.length;
                int i = 0;
                while (i < length) {
                    final String s2 = browser_COMPATIBLE_DATE_FORMATS[i];
                    try {
                        date = new SimpleDateFormat(s2, Locale.US).parse(s);
                        return date;
                    }
                    catch (ParseException ex2) {
                        ++i;
                        continue;
                    }
                    break;
                }
                date = null;
            }
        }
        return date;
    }
}
