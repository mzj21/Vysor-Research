// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.net.Uri;

public class zzau
{
    private static final String[] zzahb;
    private String zzagx;
    private String zzagy;
    private String zzagz;
    private String[] zzaha;
    private zzap zzahc;
    
    static {
        zzahb = new String[] { "/aclk", "/pcs/click" };
    }
    
    public zzau(final zzap zzahc) {
        this.zzagx = "googleads.g.doubleclick.net";
        this.zzagy = "/pagead/ads";
        this.zzagz = "ad.doubleclick.net";
        this.zzaha = new String[] { ".doubleclick.net", ".googleadservices.com", ".googlesyndication.com" };
        this.zzahc = zzahc;
    }
    
    private Uri zza(final Uri uri, final Context context, final String s, final boolean b, final View view) throws zzav {
        boolean zzb = false;
        Label_0065: {
            try {
                zzb = this.zzb(uri);
                if (zzb) {
                    if (uri.toString().contains("dc_ms=")) {
                        throw new zzav("Parameter already exists: dc_ms");
                    }
                    break Label_0065;
                }
            }
            catch (UnsupportedOperationException ex) {
                throw new zzav("Provided Uri is not in a valid state");
            }
            if (uri.getQueryParameter("ms") != null) {
                throw new zzav("Query parameter already exists: ms");
            }
        }
        String s2;
        if (b) {
            s2 = this.zzahc.zza(context, s, view);
        }
        else {
            s2 = this.zzahc.zzb(context);
        }
        Uri uri2;
        if (zzb) {
            uri2 = this.zzb(uri, "dc_ms", s2);
        }
        else {
            uri2 = this.zza(uri, "ms", s2);
        }
        return uri2;
    }
    
    private Uri zza(final Uri uri, final String s, final String s2) throws UnsupportedOperationException {
        final String string = uri.toString();
        int n = string.indexOf("&adurl");
        if (n == -1) {
            n = string.indexOf("?adurl");
        }
        Uri uri2;
        if (n != -1) {
            uri2 = Uri.parse(string.substring(0, n + 1) + s + "=" + s2 + "&" + string.substring(n + 1));
        }
        else {
            uri2 = uri.buildUpon().appendQueryParameter(s, s2).build();
        }
        return uri2;
    }
    
    private Uri zzb(final Uri uri, final String s, final String s2) {
        final String string = uri.toString();
        final int index = string.indexOf(";adurl");
        Uri uri2;
        if (index != -1) {
            uri2 = Uri.parse(string.substring(0, index + 1) + s + "=" + s2 + ";" + string.substring(index + 1));
        }
        else {
            final String encodedPath = uri.getEncodedPath();
            final int index2 = string.indexOf(encodedPath);
            uri2 = Uri.parse(string.substring(0, index2 + encodedPath.length()) + ";" + s + "=" + s2 + ";" + string.substring(index2 + encodedPath.length()));
        }
        return uri2;
    }
    
    public Uri zza(final Uri uri, final Context context) throws zzav {
        return this.zza(uri, context, null, false, null);
    }
    
    public Uri zza(final Uri uri, final Context context, final View view) throws zzav {
        try {
            return this.zza(uri, context, uri.getQueryParameter("ai"), true, view);
        }
        catch (UnsupportedOperationException ex) {
            throw new zzav("Provided Uri is not in a valid state");
        }
    }
    
    public void zza(final MotionEvent motionEvent) {
        this.zzahc.zza(motionEvent);
    }
    
    public boolean zza(final Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            final boolean equals = uri.getHost().equals(this.zzagx);
            boolean b = false;
            if (equals) {
                final boolean equals2 = uri.getPath().equals(this.zzagy);
                b = false;
                if (equals2) {
                    b = true;
                }
            }
            return b;
        }
        catch (NullPointerException ex) {
            return false;
        }
    }
    
    public zzap zzaw() {
        return this.zzahc;
    }
    
    @Deprecated
    public Uri zzb(final Uri uri, final Context context) throws zzav {
        return this.zza(uri, context, null);
    }
    
    public void zzb(final String zzagx, final String zzagy) {
        this.zzagx = zzagx;
        this.zzagy = zzagy;
    }
    
    public boolean zzb(final Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            return uri.getHost().equals(this.zzagz);
        }
        catch (NullPointerException ex) {
            return false;
        }
    }
    
    public boolean zzc(final Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            final String host = uri.getHost();
            final String[] zzaha = this.zzaha;
            final int length = zzaha.length;
            int n = 0;
            boolean b;
            while (true) {
                b = false;
                if (n >= length) {
                    break;
                }
                if (host.endsWith(zzaha[n])) {
                    b = true;
                    break;
                }
                ++n;
            }
            return b;
        }
        catch (NullPointerException ex) {
            return false;
        }
    }
    
    public boolean zzd(final Uri uri) {
        final boolean zzc = this.zzc(uri);
        boolean b = false;
        if (zzc) {
            final String[] zzahb = zzau.zzahb;
            final int length = zzahb.length;
            int n = 0;
            while (true) {
                b = false;
                if (n >= length) {
                    break;
                }
                if (uri.getPath().endsWith(zzahb[n])) {
                    b = true;
                    break;
                }
                ++n;
            }
        }
        return b;
    }
    
    public void zzm(final String s) {
        this.zzaha = s.split(",");
    }
}
