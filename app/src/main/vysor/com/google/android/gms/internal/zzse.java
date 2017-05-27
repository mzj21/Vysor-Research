// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import android.util.Log;
import android.os.Build$VERSION;
import com.google.android.gms.common.internal.zzq;

public class zzse
{
    private final String CR;
    private final zzq Dk;
    private final int bX;
    private final String mTag;
    
    private zzse(final String mTag, final String cr) {
        this.CR = cr;
        this.mTag = mTag;
        this.Dk = new zzq(mTag);
        this.bX = this.getLogLevel();
    }
    
    public zzse(final String s, final String... array) {
        this(s, zzd(array));
    }
    
    private int getLogLevel() {
        Label_0328: {
            if (Build$VERSION.SDK_INT != 23) {
                break Label_0328;
            }
            final String value = String.valueOf(this.mTag);
            String concat;
            if (value.length() != 0) {
                concat = "log.tag.".concat(value);
            }
            else {
                concat = new String("log.tag.");
            }
            final String property = System.getProperty(concat);
            if (property == null) {
                break Label_0328;
            }
            int n2 = 0;
            switch (property) {
                default: {
                    n2 = 4;
                    break;
                }
                case "VERBOSE": {
                    n2 = 2;
                    break;
                }
                case "DEBUG": {
                    n2 = 3;
                    break;
                }
                case "INFO": {
                    n2 = 4;
                    break;
                }
                case "WARN": {
                    n2 = 5;
                    break;
                }
                case "ERROR": {
                    n2 = 6;
                    break;
                }
                case "ASSERT": {
                    n2 = 7;
                    break;
                }
                case "SUPPRESS": {
                    n2 = Integer.MAX_VALUE;
                    break;
                }
            }
            return n2;
        }
        int n2;
        for (n2 = 2; 7 >= n2 && !Log.isLoggable(this.mTag, n2); ++n2) {}
        return n2;
    }
    
    private static String zzd(final String... array) {
        String string;
        if (array.length == 0) {
            string = "";
        }
        else {
            final StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (final String s : array) {
                if (sb.length() > 1) {
                    sb.append(",");
                }
                sb.append(s);
            }
            sb.append(']').append(' ');
            string = sb.toString();
        }
        return string;
    }
    
    protected String format(String format, final Object... array) {
        if (array != null && array.length > 0) {
            format = String.format(format, array);
        }
        return this.CR.concat(format);
    }
    
    public void zza(final String s, final Object... array) {
        if (this.zzbf(2)) {
            Log.v(this.mTag, this.format(s, array));
        }
    }
    
    public boolean zzbf(final int n) {
        return this.bX <= n;
    }
    
    public void zze(final String s, final Object... array) {
        Log.i(this.mTag, this.format(s, array));
    }
    
    public void zzf(final String s, final Object... array) {
        Log.w(this.mTag, this.format(s, array));
    }
}
