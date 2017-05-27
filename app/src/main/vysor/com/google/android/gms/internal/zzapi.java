// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

public final class zzapi
{
    public static Writer zza(final Appendable appendable) {
        Writer writer;
        if (appendable instanceof Writer) {
            writer = (Writer)appendable;
        }
        else {
            writer = new zza(appendable);
        }
        return writer;
    }
    
    public static void zzb(final zzaoh zzaoh, final zzaqa zzaqa) throws IOException {
        zzapw.bnH.zza(zzaqa, zzaoh);
    }
    
    public static zzaoh zzh(final zzapy zzapy) throws zzaol {
        boolean b = true;
        try {
            zzapy.bn();
            b = false;
            return zzapw.bnH.zzb(zzapy);
        }
        catch (EOFException ex) {
            if (b) {
                return zzaoj.bld;
            }
            throw new zzaoq(ex);
        }
        catch (zzaqb zzaqb) {
            throw new zzaoq(zzaqb);
        }
        catch (IOException ex2) {
            throw new zzaoi(ex2);
        }
        catch (NumberFormatException ex3) {
            throw new zzaoq(ex3);
        }
    }
    
    private static final class zza extends Writer
    {
        private final Appendable bmi;
        private final zzapi.zza.zza bmj;
        
        private zza(final Appendable bmi) {
            this.bmj = new zzapi.zza.zza();
            this.bmi = bmi;
        }
        
        @Override
        public void close() {
        }
        
        @Override
        public void flush() {
        }
        
        @Override
        public void write(final int n) throws IOException {
            this.bmi.append((char)n);
        }
        
        @Override
        public void write(final char[] bmk, final int n, final int n2) throws IOException {
            this.bmj.bmk = bmk;
            this.bmi.append(this.bmj, n, n + n2);
        }
        
        static class zza implements CharSequence
        {
            char[] bmk;
            
            @Override
            public char charAt(final int n) {
                return this.bmk[n];
            }
            
            @Override
            public int length() {
                return this.bmk.length;
            }
            
            @Override
            public CharSequence subSequence(final int n, final int n2) {
                return new String(this.bmk, n, n2 - n);
            }
        }
    }
}
