// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.StringReader;
import java.io.IOException;
import java.io.Reader;

public final class zzaom
{
    public zzaoh zza(final Reader reader) throws zzaoi, zzaoq {
        zzaoh zzh;
        try {
            final zzapy zzapy = new zzapy(reader);
            zzh = this.zzh(zzapy);
            if (!zzh.aV() && zzapy.bn() != zzapz.bot) {
                throw new zzaoq("Did not consume the entire document.");
            }
        }
        catch (zzaqb zzaqb) {
            throw new zzaoq(zzaqb);
        }
        catch (IOException ex) {
            throw new zzaoi(ex);
        }
        catch (NumberFormatException ex2) {
            throw new zzaoq(ex2);
        }
        return zzh;
    }
    
    public zzaoh zzh(final zzapy zzapy) throws zzaoi, zzaoq {
        final boolean lenient = zzapy.isLenient();
        zzapy.setLenient(true);
        try {
            return zzapi.zzh(zzapy);
        }
        catch (StackOverflowError stackOverflowError) {
            final String value = String.valueOf(zzapy);
            throw new zzaol(new StringBuilder(36 + String.valueOf(value).length()).append("Failed parsing JSON source: ").append(value).append(" to Json").toString(), stackOverflowError);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            final String value2 = String.valueOf(zzapy);
            throw new zzaol(new StringBuilder(36 + String.valueOf(value2).length()).append("Failed parsing JSON source: ").append(value2).append(" to Json").toString(), outOfMemoryError);
        }
        finally {
            zzapy.setLenient(lenient);
        }
    }
    
    public zzaoh zzuq(final String s) throws zzaoq {
        return this.zza(new StringReader(s));
    }
}
